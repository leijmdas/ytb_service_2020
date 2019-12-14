package ytb.project.service.impl.pay.projectpay;

import com.alibaba.fastjson.JSONObject;
import ytb.project.common.PayConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ChangeDaoServiceImpl;
import ytb.project.daoservice.ProjectDAOService;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUser;

import ytb.project.service.impl.pay.IFlowPayChange;
import ytb.project.service.impl.pay.PayResult;
import ytb.project.service.impl.pay.ViewPayResult;
import ytb.project.service.project.Notify;
import ytb.project.service.project.change.ChangeFlow;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.context.model.YtbError;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;



/*
* 退款 在原项目
* 支付变更启动金额在新项目
* 终止项目在原项目支付
* */
public final class FlowPayChange extends FlowPayStop implements IFlowPayChange {
    public FlowPayChange() {
        serviceType = TradeConst.SERVICE_TYPE_project_CHANGE;
    }

    public ViewPayResult paPayChange(UserProjectContext newContext, UserProjectContext oldContext,
                                     String password) throws UnsupportedEncodingException {
        //单独校验密码，不成功，就退出
        //checkPassword 甲方的密码
        checkAccountPassword(newContext,password);
        ProjectModel oldPm = oldContext.getProjectModel();

        //变更支付： <0支付，>0退款 差额支付
        BigDecimal fee = new ChangeFlow().selectFeeDiff(newContext);
        ViewPayResult viewPayResult = new ViewPayResult();
        viewPayResult.getViewSumResult().setChangeType(oldPm.getChangeType());

        paPayPhaseChange(newContext,oldContext,viewPayResult,fee);

        //项目状态维护，大变更首阶段文件夹创建
        changeBuildStatusAndFolder(newContext,oldContext);

        return viewPayResult;
    }

    //项目状态维护，大变更首阶段文件夹创建
    public void changeBuildStatusAndFolder(UserProjectContext newContext,
                                                      UserProjectContext oldContext) throws UnsupportedEncodingException {
        ProjectModel pm = newContext.getProjectModel();
        ProjectTalkModel ptm = oldContext.getProjectTalkModel();

        //大小变更文件夹以及状态处理
        if (pm.isChangeBig()) {
            //1 阶段支付后modify old talk change_status=700
            //2 确认支付进入首阶段copy
            pm.getProjectTalkService().modifyTalkPhaseAndStatus(null, null, ProjectConstState.CHNAGE_TYPE_BIG,
                    pm.getOldTalkId());
            ProjectContext pc = buildPhaseStartFolder(newContext);
            pm.getProjectTalkService().modifyTalkPhaseAndStatus(newContext, pm.getPhaseStart());
            //新项目状态进行中：
            new ProjectDAOService().modifyProjectStatus(ProjectConstState.PUBLISH_PASS, pm.getProjectId());
            //changestatus
        } else if (pm.isChangeLittle()) {//小变更支付
            pm.getProjectTalkService().modifyTalkPhaseAndStatus(null, null, ProjectConstState.CHNAGE_TYPE_SMALL,
                    pm.getOldTalkId());
            //修改当前阶段文档升级
            new ProjectTemplateUser().smallChangeUpgradeTemplate(oldContext);
            //paPayLittleChange(newContext);
            //新项目状态进行中：
            new ProjectDAOService().modifyProjectStatus(ProjectConstState.PUBLISH_PASS, pm
                    .getProjectId());
        }

        new ChangeDaoServiceImpl().updateProjectChange(newContext.getProjectModel().getProjectId
                (), ProjectConstState.PRO_CHANGESTATUS_CONFIRM_CHANGE, 0);
    }

    public void paPayPhaseChange(UserProjectContext newContext, UserProjectContext oldContext,
                                 ViewPayResult viewPayResult, BigDecimal fee) throws UnsupportedEncodingException {

        if(viewPayResult.getViewSumResult().getChangeType()==0) {
            viewPayResult.getViewSumResult().setChangeType(oldContext.getProjectModel().getChangeType());
        }

        ProjectModel pm = newContext.getProjectModel();
        ProjectTalkModel ptm = oldContext.getProjectTalkModel();

        //甲方新项目支付或者老项目退款
        if (fee.compareTo(BigDecimal.ZERO) == 0) {
            PayResult payResult = projectCostFee.buildB2fPaPay(oldContext, BigDecimal.ZERO);
            viewPayResult.setPaPhasePayResult(payResult);

        } else if (fee.compareTo(BigDecimal.ZERO) < 0) { //repay
            // 新项目支付追加款， 类型是启动款
            paPayChangeProject(newContext, oldContext, viewPayResult, newBigDecimal(0).subtract(fee));

        } else if (fee.compareTo(BigDecimal.ZERO) > 0) { //refund
            // 原项目乙方退款
            viewPayResult.getViewSumResult().setPbPhaseRefund(fee);
            pbPhaseRefund(oldContext, viewPayResult, fee);
        }
        if (fee.compareTo(BigDecimal.ZERO) <= 0) {
           // ptm.addPayEvent(oldContext, "变更甲方增加预付款", ProjectConst.TASK_STATUS_Passing,
               //  pm.getUserId1(), ptm.getUserId2(), BigDecimal.ZERO.subtract(fee));
            payEventService.addPayEventPaChangeOpen(oldContext,BigDecimal.ZERO.subtract(fee),true);
        } else {
           // ptm.addPayEvent(oldContext, "变更乙方退还冻结款", ProjectConst.TASK_STATUS_Passing,
                //    ptm.getUserId2(), pm.getUserId1(), fee);
            payEventService.addPayEventPbChangeRefund(oldContext,fee,true);
        }

        //大变更乙方老项目阶段支付：支付或者退款 组员按占比例支付
        //大项目结束时不再阶段支付，直接是甲方乙方解冻收款
        if (pm.isChangeBig()) {
            //大变更,原项目阶段支付，提前：
            //项目结束时，乙方的支付解冻老项目独立,因为人员不同
            //甲方的支付，解冻：新老项目一起算 大变更，还要处理原项目阶段支付或者退款
            //大变更阶段支付

            //BigDecimal bigPhasePayFee = new BigDecimal(200);
            //viewPayResult.getViewSumResult().setPaPhasePay( bigPhasePayFee );
            //paPhasePay( oldContext, viewPayResult, bigPhasePayFee );

        }

    }

    //变更支付 服务费 ProjectConst.TEMPORARY_PASSWORD;
    PayResult paPayChangeProject(UserProjectContext newContext, UserProjectContext oldContext,
                                 ViewPayResult viewPayResult, BigDecimal fee) throws UnsupportedEncodingException {

        Byte serviceType = TradeConst.SERVICE_TYPE_project_CHANGE;
        if(!new ProjectPay().checkAccountEnouph(newContext,fee)){
            throw new YtbError(YtbError.CODE_USER_ERROR, "甲方余额不够无法支项目预付款，需要充值！");
        }

        ProjectModel pm = newContext.getProjectModel();
        ProjectTalkModel ptm = newContext.getProjectTalkModel();
        Dict_ProjectTypeModel dict_ptm = newContext.getDict_ProjectTypeModel();

        PayResult payResult = projectCostFee.buildB2fPaPay(newContext, fee);
        payResult.setPayType(PayConst.PAYTYPE_OPEN);
        viewPayResult.setPaOpenPayResult(payResult);
        TradeInfo info = payResult.buildTradeInfo();

        int tradeId = transactionService.b2fPaPayProject(info, serviceType);

        if (tradeId == 0) {
//            addPayEvent(newContext, "甲方预付款失败", ProjectConst.TASK_STATUS_NO_RIGHT,
//                    pm.getUserId1(), ptm.getUserId2(), payResult.getTotalBalance());
            payEventService.addPayEventPaOpen(newContext, payResult.getTotalBalance(), false);
            throw new YtbError(YtbError.PHASE_PAY_FAILD, payResult.toString());
        }

        getPayTradeService().addProjectTradePA(newContext, tradeId);
        getPayTradeService().saveCostModel(tradeId, payResult);
        payResult.setTradeId(tradeId);

//        addPayEvent(newContext, "甲方预付款", ProjectConst.TASK_STATUS_Passing, pm.getUserId1(),
//                ptm.getUserId2(), payResult.getTotalBalance());
//        addPayEvent(newContext, "甲方预付款", ProjectConst.TASK_STATUS_Passing, ptm.getUserId2(),
//                pm.getUserId1(), payResult.getTotalBalance());
        payEventService.addPayEventPaOpen(newContext,payResult.getTotalBalance(),true);
        //修改 老项目oldContext的changeType; 终止就是同一个

        //通知

        return payResult;

    }

    //只有大变更才会加目录
//        if( oldContext.isChangeBig() ) {
//            //修改洽谈表支付时间和phase
//            pm.getProjectTalkService().modifyTalkPhasePayDate(newContext, newContext.getProjectModel().getPhaseStart(), 0);
//
//            //确认支付进入首阶段copy return folderID
//            if (dict_ptm.isPurchaseProcessing()) {
//                int folderid = newContext.getViewProjectFolderModel().getpBFolder().copyFolderPB_phaseStartPp(newContext);
//                payResult.setFolderId(folderid);
//            } else {
//                ProjectContext pc = new ProjectContext(newContext);
//                newContext.getViewProjectFolderModel().getpBFolder().copyFolder_phaseStart
//                        (newContext, pc, newContext.getProjectModel().getPhaseStart());
//                payResult.setFolderId(pc.getFolderId());
//            }
//            pm.getProjectTalkService().modifyTalkPhaseAndStatus(newContext, pm.getPhaseStart());
//
//        }

//    //甲方支付小变更的项目款
//    int paPayLittleChangeFee(ProjectModel pm) {
//
//        TradeInfo info = new TradeInfo();
//        info.setProjectId(pm.getProjectId());
//        info.setServiceType(TradeConst.SERVICE_TYPE_project);
//        info.setBalance(BigDecimal.valueOf(0));
//        info.setServiceFee(info.getBalance().multiply(BigDecimal.valueOf(pm.selectProjectRateTaxModel().getFeeRate())));
//
//        return getInst().getProjecTransactionService().b2fPaPayProject(info);
//
//    }

//    // 分公司和个人
//    ProjectBalanceProjectAgent paPayBigChangeFee (UserProjectContext context) {
//
//        ProjectModel pm = context.getProjectModel();
//        ProjectTalkModel ptm = context.getProjectTalkModel();
//
//        //当前阶段应付的费用
//        BigDecimal phaseFee = new BigDecimal(0);
//        //补偿金
//        BigDecimal repayFee = new BigDecimal(0);
//        List<TradeInfo> toInfos = new ArrayList<>();
//
//        ProjectRateModel projectRate = context.selectProjectRateTaxModel();
//
//        for (WorkGroupMemberModel workGroupMember : ptm.selectWorkGroupMember()) {
//            if (workGroupMember.getUserId() != pm.getUserId1()) {
//                BigDecimal memberChang = new BigDecimal("0");
//                //获取组员的账户
//                List<CostModel> costModelLst = null;//paPayBigChange_getCostModel(ptm, workGroupMember.getUserId());
//                //BigDecimal balance = paPayBigChange_getCost(ptm, workGroupMember.getUserId());
//                //BigDecimal repay = getProjectCostFee().getRepay(ptm.getPhase(), costModelLst);//获取员工的违约金
////                memberChang = memberChang.add(balance).add(repay);//员工的当前阶段费用加上违约金
////                phaseFee = phaseFee.add(balance);//甲方应付的阶段费用
////                repayFee = repayFee.add(repay);//甲方应付的违约金
//
//                //设置组员转账参数
//                if(!context.pbIsCompanyUser()) {
//                    TradeInfo info = new TradeInfo();
//                    info.setUserId(workGroupMember.getUserId());
//                    info.setCompanyId(0);
//                    info.setProjectId(pm.getProjectId());
//                    info.setTax(memberChang.multiply(BigDecimal.valueOf(projectRate.getTaxRate())));
//                    BigDecimal  memberServiceFee = memberChang.multiply(BigDecimal.valueOf(projectRate.getFeeRate()));
//                    info.setBalance(memberChang.subtract(memberServiceFee).subtract(info.getTax()));
//                    toInfos.add(info);
//                }
//            }
//        }
//
//        //变更后的总费用
//        BigDecimal talkChng = phaseFee.add(repayFee);
//        BigDecimal  talkServiceFee = talkChng.multiply(BigDecimal.valueOf(projectRate.getFeeRate()));
//        //设置组长转帐参数公司以组长名义转给公司
//        if (context.pbIsCompanyUser()) {
//            TradeInfo info = new TradeInfo();
//            info.setUserId(ptm.getUserId2());
//            info.setCompanyId(ptm.getCompanyId2());
//            info.setProjectId(pm.getProjectId());
//            info.setTax(talkChng.multiply(BigDecimal.valueOf(projectRate.getTaxRate())));
//            info.setBalance(talkChng.subtract(talkServiceFee).subtract(info.getTax()));
//            toInfos.add(info);
//        }
//
//        //甲方转账参数
//        TradeInfo outInfo = new TradeInfo();
//        outInfo.setUserId(pm.getUserId1());
//        outInfo.setCompanyId(pm.getCompanyId1());
//        outInfo.setProjectId(pm.getProjectId());
//        outInfo.setBalance(talkChng);
//        outInfo.setServiceFee(talkServiceFee);
//        //支付
//        getInst().getProjecTransactionService().b2fPaPayProject(outInfo);
//        outInfo.setBalance(talkChng.subtract(talkServiceFee));
//        outInfo.setServiceFee(BigDecimal.ZERO);
//        //转账
//        return getInst().getProjecTransactionService().f2fTransferPayPa2Pb(outInfo, toInfos);
//    }
//
//    //甲方支付小变更
//      void paPayLittleChange(UserProjectContext context) {
//
//        ProjectModel pm = context.getProjectModel();
//        ProjectTalkModel ptm = context.getProjectTalkModel();
//
//        int tradeId = paPayLittleChangeFee( pm );
//        if (tradeId == 0) {
//            throw new YtbError(YtbError.PHASE_PAY_FAILD);
//        }
//
//        addProjectTradePA(context, tradeId);//703,
//        addPayEvent(context, "甲方支付需求变更款", 6, pm.getUserId1(), ptm.getUserId2(),  0);
//        Notify projectNotify = new Notify();   // projectNotify.sendMsg();   //new ChangeFlow().modifyChangeStatus
//        // (context);
//        ptm.setPhaseStatus(0);
//        /*pm.getProjectTalkService().modifyTalkPhaseAndStatus(ptm.getPhase(),0,ptm
//                .getTalkId());*/
//
//    }


//    //甲方支付大变更
//      void paPayBigChange(UserProjectContext context) {
//        ProjectModel pm = context.getProjectModel();
//        ProjectTalkModel ptm = context.getProjectTalkModel();
//        ProjectBalanceProjectAgent pbAgent = paPayBigChangeFee (context);
//
//        //设置甲方的交易记录
//        addProjectTradePA(context, pbAgent.getAccountTrade().getTradeId());
//
//        ProjectPhaseModel projectPhase = ProjectTalkModel.getPhaseAndEvent().getProjectPhase(ptm.getTalkId(),
//                ptm.getPhase());//703,
//        addPayEvent(context,"甲方支付", 6, pm.getUserId1(), ptm.getUserId2(),
//                pbAgent.getAccountTrade().getBalance() );
//        ptm.setPhaseStatus(704);
//       /* pm.getProjectTalkService().modifyTalkPhaseAndStatus(ptm.getPhase(),704,
//                ptm.getTalkId()); //*/
//        // createNewPro(ptm,  pm);
//        paPayBigChange_notify(context,pbAgent,projectPhase);
//
//    }

    void paPayChange_notify(UserProjectContext context, ProjectBalanceProjectAgent pbAgent) {

        ProjectModel pm = context.getProjectModel();

        Notify projectNotify = new Notify();
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("params1", pm.getProjectName());
        jsonObject.fluentPut("params2", pbAgent.getAccountTrade().getBalance());
        projectNotify.sendNotify(0, pm.getUserId1(), 2, ProjectConstState.PROJECT_PREPAY_PA_Notify, "", jsonObject);
        for (AccountTradeProject accountTrade : pbAgent.getToAccountTrades()) {

            getPayTradeService().addProjectTradePA(context, accountTrade.getTradeId());

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.fluentPut("params1", pm.getProjectName());
            jsonObject1.fluentPut("params2", accountTrade.getBalance());
            projectNotify.sendNotify(0, accountTrade.getUserId(), 2, ProjectConstState.PROJECT_PREPAY_PB_Notify, "", jsonObject1);
//            payEventService.addPayEvent(context, "乙方收到预付款:" + accountTrade.getBalance() + "元", 6,
//                    accountTrade.getUserId(), pm.getUserId1(),  accountTrade.getBalance());//703,
        }
    }

}
