package ytb.project.service.impl.pay.projectpay;

import ytb.common.ytblog.ProjectYtbLog;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.model.YtbError;
import ytb.project.common.PayConst;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ProjectInviteDAOService;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTradeModel;
import ytb.project.model.tagtable.CostModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.impl.pay.IFlowPay;
import ytb.project.service.impl.pay.PayResult;
import ytb.project.service.impl.pay.payassist.paytalk.PayTalkRequest;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;
import ytb.account.wallet.service.AccountConst.TradeConst;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//支付
public  class FlowPay extends ProjectPay implements IFlowPay {

    public FlowPay() {
        serviceType = TradeConst.SERVICE_TYPE_project;
    }

    void paPayOpenProject_check(UserProjectContext context) {
        newBigDecimal(0);
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        if (!context.isPa()) {
            throw new YtbError(context,YtbError.CODE_USER_ERROR, "你不是项目的甲方，不能进行支付!");
        }

        if (pm.getPayTimes() > 0) {
            throw new YtbError(context,YtbError.CODE_USER_ERROR, pm.getProjectName()+"项目已经支付过一次，只能支付一次!"  );
        }
        if (!(ptm.getPhase() == ProjectConst.TalkPhase &&
                ptm.getPhaseStatus() == ProjectConst.TalkPhase_STATUS_PAY)) {
            throw new YtbError(context,YtbError.CODE_USER_ERROR, "项目状态不正确，不可以支付！" + ptm.toString());
        }

    }



    //PA首次支付扣除服务费
    public PayResult paPayOpenProject(UserProjectContext newContext, String password) throws UnsupportedEncodingException {
        ProjectYtbLog.logDebug("Enter FlowPay::paPayProject");

        //检查用户与项目的合法性
        paPayOpenProject_check(newContext);

        ProjectModel pm = newContext.getProjectModel();
        ProjectTalkModel ptm = newContext.getProjectTalkModel();

        PayResult payResult = projectCostFee.buildB2fPaPay(newContext);
        if(!new ProjectPay().checkAccountEnouph(newContext,payResult.getTotalBalance())){
            throw new YtbError(YtbError.CODE_USER_ERROR, "甲方余额不够无法支项目预付款，需要充值！");
        }
        payResult.setPayType(PayConst.PAYTYPE_OPEN);
        //判断项目支付次数是否第一次支且在洽谈阶段
        if (pm.getPayTimes() <= 0 && ptm.getPhase() == ProjectConst.TalkPhase &&
                ptm.getPhaseStatus() == ProjectConst.TalkPhase_STATUS_PAY) {

            TradeInfo info = payResult.buildTradeInfo();
            int tradeId = transactionService.b2fPaPayProject(info);

            if (tradeId == 0) {

                payEventService.addPayEventPaOpen(newContext,payResult.getTotalBalance(),false);
                throw new YtbError(newContext,YtbError.PHASE_PAY_FAILD, payResult.toString());
            }
            int ret = getInst().getReleaseView().getProjectDAOService().modifyProjectPayTimes(pm.getProjectId());
            if (ret == 0) {
                throw new YtbError(newContext,YtbError.CODE_USER_ERROR, "更新项目支付次数失败，一个项目只能支付一次!");
            }
            getPayTradeService().addProjectTradePA(newContext, tradeId);
            getPayTradeService().saveCostModel(tradeId, payResult);
            payResult.setTradeId(tradeId);

            payEventService.addPayEventPaOpen(newContext,payResult.getTotalBalance(),true);
            //修改洽谈表支付时间和phase
            pm.getProjectTalkService().modifyTalkPhasePayDate(newContext,
                    newContext.getProjectModel().getPhaseStart(), 0);
            //创建第一阶段文件夹
            ProjectContext pc = buildPhaseStartFolder(newContext);
            payResult.setFolderId(pc.getFolderId());
            pm.getProjectTalkService().modifyTalkPhaseAndStatus(newContext, pm.getPhaseStart());

        }
        //洽谈确认则取消此乙方的预留冻结款
        PayTalkRequest payTalkRequest = new PayTalkRequest();
        ProjectInviteDAOService projectInviteDAOService = new ProjectInviteDAOService();

        int reqTalkId = projectInviteDAOService.getProjectInviteByTalkId(ptm.getTalkId()).isInvite() ? 0 : ptm.getTalkId();
        ProjectTradeModel projectTradeModel = payTalkRequest.getPayTradeService().selectOneByProjectTalk(pm.getProjectId(), reqTalkId);

        payTalkRequest.paConfirmTalk_payCancel(newContext, projectTradeModel.getTradeId());
        ProjectYtbLog.logDebug("Exit FlowPay::paPayProject", payResult);
        ProjectYtbLog.logRun("paPayOpenProject",payResult);
        //通知
        return payResult;

    }


    void payPhase_check(UserProjectContext context) throws UnsupportedEncodingException {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        if (context.isClose()) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "项目已经结束!");
        }
        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "项目不在进行中!");
        }
        if (ptm.getPhaseStatus() != ProjectConst.TalkPhase_STATUS_PAY) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "项目未审核，不在支付状态中!");
        }

    }

    // 加工采购阶段支付
    ProjectBalanceProjectAgent f2fPayPhasePp(UserProjectContext context, int tradeIdPre, String passWordEn) {

        PayResult payResult = projectCostFee.buildF2fPaPayTotalPp(context);

        TradeInfo tradeInfo = payResult.buildTradeInfoPa(context);
        tradeInfo.setTradeIdPre(tradeIdPre);
        tradeInfo.setPassword(passWordEn);

        List<TradeInfo> tradeInfos = new ArrayList<>();
        TradeInfo toTradeinfo = payResult.buildTradeInfoPb(context);
        toTradeinfo.setTradeIdPre(tradeIdPre);
        tradeInfo.setPassword(passWordEn);
        tradeInfos.add(toTradeinfo);

        return transactionService.f2fTransferPayPa2Pb(tradeInfo, tradeInfos);

    }


    List<AccountTradeProject>  f2bProjectPbUnfreezePp(UserProjectContext context, int tradeIdPre, CostModel costModel ){

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        TradeInfo tradeInfo = getProjectCostFee().buildF2BPbPay(context, ptm.getUserId2(), ptm.getCompanyId2(), costModel);
        tradeInfo.setTradeIdPre(tradeIdPre);
        List<TradeInfo> tradeInfos = new ArrayList<>();
        tradeInfos.add(tradeInfo);
        //payEventService.addPayEvent(context, "甲方阶段支付", 6, pm.getUserId1(), ptm.getUserId2(), costModel.getCostSum());
        payEventService.addPayEventPaPhase(context, costModel.getCostSum());

        //close pay
        List<AccountTradeProject> accountTrades = transactionService.f2bProjectPbUnfreeze(tradeInfos);
        return accountTrades;
    }

    // 加工采购支付，审核通过则支付
    public ProjectBalanceProjectAgent payPhasePp(UserProjectContext context, String passWord) throws UnsupportedEncodingException {
        payPhase_check(context);

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        int tradeIdPre = getTradeIdByTalk(ptm.getTalkId(), pm.getUserId1());

        //冻结款转帐
        ProjectBalanceProjectAgent pbAgent = f2fPayPhasePp(context, tradeIdPre, passWord);

        PayResult payResult = new PayResult(context, pbAgent.getAccountTrade());
        payResult.setPayType(PayConst.PAYTYPE_PHASE);
        payResult.addPaResults(context,PayConst.PAYTYPE_PHASE,pbAgent.getToAccountTrades());

        //记录轨迹
        getPayTradeService().addProjectTradeF2F(context, tradeIdPre, pbAgent);
        // 发通知
        payNotifyService.payNotify(context, ProjectConstState.PROJECT_PREPAY_PB_Notify, pbAgent);
        payEventService.addPayEventPaPmPhase(context,pbAgent);
        //上面位置不能动！

        //close pay
        //计算冻结款
        Map<Integer, CostModel> costModelMap = sumPbUnfreeze(context);
        CostModel costModel = costModelMap.get(ptm.getUserId2());
        List<AccountTradeProject> accountTrades = f2bProjectPbUnfreezePp(context, tradeIdPre, costModel);
        payResult.addPbResults(context,accountTrades);
        YtbLog.logRun("payPhasePp",payResult);

        getPayTradeService().addProjectTradeF2B(context, tradeIdPre, accountTrades);
        // 发通知
        // payNotify(context, ProjectConstState.PROJECT_PREPAY_PB_Notify, pbAgent);

        //payEventService.addPayEvent(context, "乙方解冻项目款", 6, ptm.getUserId2(), 0, costModel.getCostSum());
        payEventService.addPayEventPbFinish(context, costModel.getCostSum());

        //项目进入完成阶段
        pm.getProjectTalkService().modifyTalkChangeStatus(context, ProjectConstState.CHNAGE_TYPE_FINISH);

        return pbAgent;
    }


    // 阶段支付  payPhase f2f :return PayResult : newPhase 加工采购不会进入
    public PayResult payPhase(UserProjectContext context, int talkId, String password) throws UnsupportedEncodingException {

        //状态检查
        payPhase_check(context);

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        int preTradeId = getTradeIdByTalk(ptm.getTalkId(), pm.getUserId1());

        PayResult payResultTotal = new PayResult(context, PayConst.PAYTYPE_PHASE);
        payResultTotal.setPaPayResults(new ArrayList<>());
        //乙方参数
        List<TradeInfo> toInfos = new ArrayList<>();

        if (context.pbIsCompanyUser()) {
            payResultTotal = projectCostFee.buildF2fPaPayTotal(context);
            TradeInfo info = payResultTotal.buildTradeInfoPb(context);
            info.setTradeIdPre(preTradeId);
            toInfos.add(info);
        } else {
            for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
                if (memberModel.getUserId() != pm.getUserId1()) {
                    PayResult payResult = projectCostFee.buildF2fPaPayMember(context, memberModel);
                    payResult.setPayType(PayConst.PAYTYPE_PHASE);
                    payResultTotal.getPaPayResults().add(payResult);
                    payResultTotal.sum(payResult);
                    TradeInfo info = payResult.buildTradeInfoPm(context, memberModel);
                    info.setTradeIdPre(preTradeId);
                    toInfos.add(info);
                }
            }
        }
        YtbLog.logDebug("List<TradeInfo> toInfos",toInfos);


        //甲方参数
        TradeInfo outInfo = payResultTotal.buildTradeInfoPa(context);
        outInfo.setTradeIdPre(preTradeId);
        outInfo.setBalance(payResultTotal.getBalance());
        outInfo.setPassword(password);
        outInfo.log("TradeInfo outInfo");
        //冻结款转帐
        ProjectBalanceProjectAgent pbAgent = transactionService.f2fTransferPayPa2Pb(outInfo, toInfos);
        //记录轨迹
        getPayTradeService().addProjectTradeF2F(context, preTradeId, pbAgent);

        // 发送通知
        payNotifyService.payNotify(context, ProjectConstState.PROJECT_PREPAY_PB_Notify, pbAgent);
        payEventService.addPayEventPaPmPhase(context,pbAgent);
        //上面位置不能动！

        //转入下一阶段
        int newPhase = ProjectConstState.CHNAGE_TYPE_FINISH;
        boolean isLastStage = context.isLastStage(ptm.getPhase());
        if (!isLastStage) {
            //拷贝这个阶段的文档
            newPhase = context.getViewProjectFolderModel().getpBFolder().copyFolder_phase(context, payResultTotal);
            pm.getProjectTalkService().modifyTalkPhaseAndStatus(context, newPhase);
        } else {
            //项目最后阶段 完成支付
            List<AccountTradeProject> accountTrades = payFinishProject(context);
            pm.getProjectTalkService().modifyTalkChangeStatus(context, ProjectConstState.CHNAGE_TYPE_FINISH);

            payResultTotal.addPbResults(context,accountTrades);
            getPayTradeService().addProjectTradeF2B(context, preTradeId, accountTrades);
            payEventService.addPayEventPmFinish(context,accountTrades);

            //payNotify(context, ProjectConstState.PROJECT_PREPAY_PB_Notify, pbAgent);

        }
        //pm.getProjectTalkService().modifyTalkChangeStatus(context, ProjectConstState.CHNAGE_TYPE_FINISH);

        YtbLog.logRun("payPhase payResultTotal: " + payResultTotal.getPhase(), payResultTotal);
        return payResultTotal;

    }



    //加工采购 nouse
//    List<TradeInfo> f2bUnfreezePayClosePm_pp(UserProjectContext context) {
//        System.exit(1);
//        ProjectTalkModel ptm = context.getProjectTalkModel();
//        CostModel costModel = projectCostFee.getProjectCostTotal(context);
//        List<TradeInfo> tradeInfos = new ArrayList<>();
//        TradeInfo tradeInfo = new ProjectCostFee().buildF2BPbPay(context, ptm.getUserId2(), ptm.getCompanyId2(), costModel);
//        tradeInfos.add(tradeInfo);
//        return tradeInfos;
//    }

    //解冻 f2bUnfreezePayClosePm_pp(context)
    public List<AccountTradeProject> payFinishProject(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        if(context.getDict_ProjectTypeModel().isPurchaseProcessing()){
            // f2bUnfreezePayClosePm_pp(context)
            //return f2bUnfreezePayClosePm_pp(context);
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"流程不在此处理！");
        }
        //大变更，要一起解冻原项目的冻结款;小变更通过原项目(包括变更支付)解冻！
        if (pm.getOldTalkId() > 0) {
            UserProjectContext oldContext = new UserProjectContext(context, pm.getOldTalkId());
            //select old talkId
            if (oldContext.isChangeBig()) {
                Map<Integer, CostModel> costModelMap = sumPbUnfreeze(oldContext);
                List<TradeInfo> tradeInfos = f2bUnfreezePayClosePm_nonePp(serviceType,true,oldContext,costModelMap);
                transactionService.f2bProjectPbUnfreeze(tradeInfos);
            }
        }
        Map<Integer, CostModel> costModelMap = sumPbUnfreeze(context);
        List<TradeInfo> tradeInfos = f2bUnfreezePayClosePm_nonePp(serviceType,true,context,costModelMap);
        return transactionService.f2bProjectPbUnfreeze(tradeInfos);
    }



    //非加工采购 项目结项解冻
    public List<TradeInfo> f2bUnfreezePayClosePm_nonePp( int serviceType,boolean isPb,UserProjectContext context,
                                                         Map<Integer, CostModel> costModelMap ) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        // 根据支付记录计算乙方冻结款
        //Map<Integer, CostModel> costModelMap = sumPbUnfreeze(context);

        List<TradeInfo> tradeInfos = new ArrayList<>();
        if(!isPb){

            int paUserId = pm.getUserId1();
            TradeInfo tradeInfo = projectCostFee.buildB2FPaPay(context, paUserId, pm.getCompanyId1(),
                    costModelMap.get(paUserId));
            tradeInfos.add(tradeInfo);
        }else
        {
            if (context.pbIsCompanyUser()) {

                int pbUserId = ptm.getUserId2();
                TradeInfo tradeInfo = projectCostFee.buildF2BPbPay(context, pbUserId, ptm.getCompanyId2(),
                        costModelMap.get(pbUserId));
                tradeInfos.add(tradeInfo);
            } else {
                for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
                    int pmUserId = memberModel.getUserId();
                    if (pmUserId != pm.getUserId1()) {

                        TradeInfo tradeInfo = projectCostFee.buildF2BPbPay(context, pmUserId, 0,
                                costModelMap.get(pmUserId));
                        tradeInfos.add(tradeInfo);
                    }
                }
            }
        }
        return tradeInfos;
    }


}


