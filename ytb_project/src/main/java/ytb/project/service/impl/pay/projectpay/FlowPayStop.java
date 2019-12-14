package ytb.project.service.impl.pay.projectpay;

import ytb.common.ytblog.YtbLog;
import ytb.project.common.PayConst;
import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.CostModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.impl.pay.IFlowPayStop;
import ytb.project.service.impl.pay.PayResult;
import ytb.project.service.impl.pay.ViewPayResult;
import ytb.project.service.impl.pay.projectpay.payfreeze.PaPayFreezeAccount;
import ytb.project.service.impl.pay.projectpay.payfreeze.PbPayFreezeAccount;
import ytb.project.service.project.stop.StopFlow;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/*
*  author : leijm
*  2019 .5.2
*
* */

//项目终止支付
public class FlowPayStop extends FlowPay implements IFlowPayStop {

    public FlowPayStop() {
        serviceType = TradeConst.SERVICE_TYPE_project_STOP;
    }

    // 阶段支付 差额支付
    ViewPayResult phasePayF2FDiff(UserProjectContext context, BigDecimal fee) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ViewPayResult payStopResult = new ViewPayResult();


        int cmp = fee.compareTo(BigDecimal.ZERO)  ;
        BigDecimal payFee = cmp<= 0 ? BigDecimal.ZERO.subtract(fee) : fee;

        //甲方老项目支付或者退款
        if (cmp == 0) {
            int preTradeId = getTradeIdByTalk(ptm.getTalkId(), pm.getUserId1());
            payStopResult.getViewSumResult().setPreTradeId(preTradeId);

            PayResult payResult = projectCostFee.buildB2fPaPay(context,payFee);
            payStopResult.setPaPhasePayResult(payResult);
            payEventService.addPayEventPaPhase(context,payFee);
        } else if (cmp < 0) {//repay
            payStopResult.getViewSumResult().setPaPhasePay(payFee);
            paPhasePay(context,payStopResult,payFee);

        } else if (cmp > 0) {//refund

            payStopResult.getViewSumResult().setPbPhaseRefund(payFee);
            pbPhaseRefund(context, payStopResult, payFee);

        }

        //公司项目不同的人可以看，一样的显示

        return payStopResult;
    }

    /* 1.1 甲方阶段
     * 或者
     * 1.2乙方阶段退款
    *  2.1 甲方解冻，退款
     *  2.2 乙方解冻，收入
     */
    public ViewPayResult paPayStop(UserProjectContext context) throws UnsupportedEncodingException {

        if (!context.isChangeStop()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不是终止，不能以终止方式支付！");
        }
        ProjectModel pm = context.getProjectModel();

        StopFlow stopFlow = new StopFlow();
        stopFlow.checkCanStop(context);

        //终止项目支付： <0支付，>0退款 差额支付

        BigDecimal fee = stopFlow.selectPaPbPayFee(context) ;

        ViewPayResult viewPayResult = phasePayF2FDiff(context,fee);
        viewPayResult.getViewSumResult().setChangeType(pm.getChangeType());

        //乙方老项目阶段支付：支付或者退款,结项,组员按占比例支付
        //这个逻辑同项目正常完成
        //历史大变更
        if (pm.getOldTalkId() > 0) {
            //payUnfreezeChangeBig(context );
        }
        //stopProjectUnfreeze  甲方解冻，退款 乙方解冻，收入
        stopUnfreeze(context, viewPayResult);

        YtbLog.logRun("FlowPayStop paPayStop viewPayResult", viewPayResult);

        //后处理记录用户项目终止
        stopFlow.payAfter(context);

        return viewPayResult;
    }


    //stopProjectUnfreeze
    public void stopUnfreeze(UserProjectContext context, ViewPayResult payStopResult) throws UnsupportedEncodingException {

        BigDecimal paFeeFrozen = new PaPayFreezeAccount().sumFrozen(context);

        payStopResult.checkFeePaFrozen(paFeeFrozen);

        //公司项目不同的人可以看，一样的显示
        if (paFeeFrozen.compareTo(BigDecimal.ZERO) > 0) {
            paUnfreeze(context, payStopResult, paFeeFrozen);
        }
        payEventService.addPayEventPaFinish(context, paFeeFrozen);

        pbUnfreeze(context, payStopResult);

        payEventService.addPayEventPbFinish(context, payStopResult.getViewSumResult().getFeePbUnfreeze());


    }

    //查询已经发生的支付，解冻
    public List<AccountTradeProject> payUnfreezeChangeBig(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();

        //大变更，要一起解冻原项目的冻结款;小变更通过原项目(包括变更支付)解冻！
        if (pm.getOldTalkId() <= 0) {
            return new ArrayList<>();
        }
        UserProjectContext oldContext = new UserProjectContext(context, pm.getOldTalkId());
        //select old talkId
        Map<Integer, CostModel> costModelMap = new HashMap<>();
        List<TradeInfo> tradeInfos = f2bUnfreezePayClosePm_nonePp(serviceType, true, oldContext, costModelMap);
        transactionService.f2bProjectPbUnfreeze(tradeInfos);
        tradeInfos = f2bUnfreezePayClosePm_nonePp(serviceType, false, oldContext, costModelMap);
        return transactionService.f2bProjectPbUnfreeze(tradeInfos);


    }

//    public List<AccountTradeProject> payStopProject(UserProjectContext context,Map<Integer, CostModel> costModelMap ) {
//        ProjectModel pm = context.getProjectModel();
//
//        //大变更，要一起解冻原项目的冻结款;小变更通过原项目(包括变更支付)解冻！
//        if (pm.getOldTalkId() > 0) {
//            UserProjectContext oldContext = new UserProjectContext(context, pm.getOldTalkId());
//            //select old talkId
//            if (oldContext.isChangeBig()) {
//                Map<Integer, CostModel> costModelMap = sumPbUnfreeze(oldContext);
//                List<TradeInfo> tradeInfos = f2bUnfreezePayClosePm_nonePp(oldContext,costModelMap);
//                transactionService.f2bProjectPbUnfreeze(tradeInfos);
//            }
//        }
//        //Map<Integer, CostModel> costModelMap = sumPbUnfreeze(context);
//        List<TradeInfo> tradeInfos = f2bUnfreezePayClosePm_nonePp(context,costModelMap);
//        return transactionService.f2bProjectPbUnfreeze(tradeInfos);
//    }

    //PayFreezeAccount
    //服务费
    public PayResult paUnfreeze(UserProjectContext context, ViewPayResult payStopResult , BigDecimal fee) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();

        Map<Integer, CostModel> costModelMap=new HashMap<>();
        CostModel costModel=new CostModel();
        costModel.setCostSum(fee);
        costModelMap.put(pm.getUserId1(), costModel);
        List<TradeInfo> tradeInfos = f2bUnfreezePayClosePm_nonePp(serviceType,false, context, costModelMap);
        List<AccountTradeProject> tradeProjects = transactionService.f2bProjectPaUnfreeze(tradeInfos, serviceType);

        PayResult payResultRoot = new PayResult(context);
        payStopResult.setUnfreezePaResult(payResultRoot);
        payResultRoot.setPayType(PayConst.PAYTYPE_STOP);
        for(AccountTradeProject tradeProject:tradeProjects) {

            payResultRoot.setUserId(pm.getUserId1());
            payResultRoot.setCompanyId(pm.getCompanyId1());
            payResultRoot.setTotalBalance(tradeProject.getTotalBalance());
            payResultRoot.setServiceFee(tradeProject.getServiceFee());
            payResultRoot.setTax(tradeProject.getTax());
            payResultRoot.setFee(tradeProject.getFee());
            payResultRoot.computeBalance();
            payResultRoot.setPayType(PayConst.PAYTYPE_STOP);
        }
        return payResultRoot;
    }

    //服务费，税费等
    public PayResult pbUnfreeze(UserProjectContext context,  ViewPayResult payStopResult) throws UnsupportedEncodingException {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        BigDecimal sumPbFeeFrozen = BigDecimal.valueOf(0);

        Map<Integer, CostModel> costModelMap = new  HashMap<>();
        //公司项目
        if(context.pbIsCompanyUser()){
            sumPbFeeFrozen = new PbPayFreezeAccount().sumFrozen(context);
            payStopResult.checkFeePbFrozen(sumPbFeeFrozen);
            CostModel costModel = new CostModel();
            costModel.setCostSum(sumPbFeeFrozen);
            if (sumPbFeeFrozen.compareTo(BigDecimal.ZERO) < 0) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "pbUnfreeze sumPbFeeFrozen < 0");
            }
            costModelMap.put(ptm.getUserId2(), costModel);
        }else //个人用户项目
            {
            for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
                int pmUserId = memberModel.getUserId();
                if (pmUserId != pm.getUserId1()) {
                    BigDecimal fee = new PbPayFreezeAccount().sumFrozen(context, pmUserId);
                    sumPbFeeFrozen = sumPbFeeFrozen.add(fee);
                    CostModel costModel = new CostModel();
                    costModel.setCostSum(fee);
                    if (fee.compareTo(BigDecimal.ZERO) < 0) {
                        throw new YtbError(YtbError.CODE_DEFINE_ERROR, "pbUnfreeze fee<0 "+fee);
                    }
                    costModelMap.put(pmUserId, costModel);
                }
            }
        }
        if(sumPbFeeFrozen.compareTo(BigDecimal.ZERO)<0) {
            sumPbFeeFrozen = BigDecimal.ZERO.subtract(sumPbFeeFrozen);
        }

        payStopResult.checkFeePbFrozen(sumPbFeeFrozen);
        //payStopResult.getViewSumResult().setFeePbUnfreeze(sumPbFeeFrozen);
        YtbLog.logDebug("user sumPbFeeFrozen", sumPbFeeFrozen);

        List<TradeInfo> tradeInfos = f2bUnfreezePayClosePm_nonePp(serviceType,true, context, costModelMap);
        List<AccountTradeProject> tradeProjects = transactionService.f2bProjectPbUnfreeze(tradeInfos, serviceType);

        PayResult payResultRoot = new PayResult(context);
        payStopResult.setUnfreezePbResult(payResultRoot);
        payResultRoot.setPayType(PayConst.PAYTYPE_STOP);
        payResultRoot.setPbPayResults(new ArrayList<>());
        for(AccountTradeProject tradeProject:tradeProjects) {
            PayResult payResult = new PayResult(context,tradeProject);

            payResult.computeBalance();
            payResult.setPayType(PayConst.PAYTYPE_STOP);
            payResultRoot.getPbPayResults().add(payResult);

        }
        return payResultRoot;
    }

    //阶段支付
    public PayResult paPhasePay(UserProjectContext context, ViewPayResult payStopResult, BigDecimal fee) throws UnsupportedEncodingException {
        //String password = ProjectConst.TEMPORARY_PASSWORD;

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        Dict_ProjectTypeModel dict_ptm = context.getDict_ProjectTypeModel();
        int preTradeId = getTradeIdByTalk(ptm.getTalkId(), pm.getUserId1());
        payStopResult.getViewSumResult().setPreTradeId(preTradeId);

        PayResult payResultTotal = new PayResult(context, PayConst.PAYTYPE_PHASE);
        payStopResult.setPaPhasePayResult(payResultTotal);

        payResultTotal.setTotalBalance(fee);
        payResultTotal.computeBalance();

        TradeInfo outInfo = payResultTotal.buildTradeInfoPa(context);
        outInfo.setServiceType(serviceType);
        outInfo.setTradeIdPre(preTradeId);
        outInfo.setPassword("");

        outInfo.log("TradeInfo outInfo");
        //乙方参数
        List<TradeInfo> toInfos = new ArrayList<>();
        if (context.pbIsCompanyUser()) {

            TradeInfo info = payResultTotal.buildTradeInfoPb(context);
            info.setTradeIdPre(preTradeId);
            info.setServiceType(serviceType);
            toInfos.add(info);
        } else {
            Map<Integer, Double> userRateMap = projectCostFee.computeSumCostRate(context);

            for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
                if (memberModel.getUserId() != pm.getUserId1()) {
                    PayResult payResult = new PayResult(context, PayConst.PAYTYPE_PHASE);
                    payResult.setTotalBalance(fee.multiply(BigDecimal.valueOf(userRateMap.get(memberModel.getUserId()))));
                    payResult.setUserId(memberModel.getUserId());
                    payResult.setCompanyId(memberModel.getCompanyId());

                    TradeInfo info = payResult.buildTradeInfoPm(context, memberModel);
                    info.setServiceType(serviceType);
                    info.setTradeIdPre(preTradeId);
                    toInfos.add(info);
                }
            }
        }
        YtbLog.logDebug("List<TradeInfo> toInfos",toInfos);


        ProjectBalanceProjectAgent pbAgent = transactionService.f2fTransferPayPa2Pb(outInfo, toInfos, serviceType);
        payResultTotal.addPaResults(context,PayConst.PAYTYPE_PHASE,pbAgent.getToAccountTrades());

        payEventService.addPayEventPaPhase(context,fee);
        YtbLog.logRun("paPayStop paPhasePay",payResultTotal);
        return payResultTotal;

    }

    //pb阶段退款
    public PayResult pbPhaseRefund(UserProjectContext  context, ViewPayResult payStopResult, BigDecimal fee ) throws UnsupportedEncodingException {
        String password = ProjectConst.TEMPORARY_PASSWORD;

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        Dict_ProjectTypeModel dict_ptm = context.getDict_ProjectTypeModel();

        int preTradeId = getTradeIdByTalk(ptm.getTalkId(), pm.getUserId1());
        payStopResult.getViewSumResult().setPreTradeId(preTradeId);

        PayResult payResultTotal = new PayResult(context, PayConst.PAYTYPE_PHASE);
        payStopResult.setPbPhaseRefundResult(payResultTotal);
        payResultTotal.setTotalBalance(fee);
        payResultTotal.computeBalance();

        TradeInfo toInfo = payResultTotal.buildTradeInfoPa(context);
        toInfo.setTradeIdPre(preTradeId);
        toInfo.setPassword(password);
        toInfo.setServiceType(serviceType);
        toInfo.log("TradeInfo outInfo");
        //乙方参数
        List<TradeInfo> outInfos = new ArrayList<>();
        BigDecimal sum= new BigDecimal(0);
        if (context.pbIsCompanyUser()) {

            TradeInfo info = payResultTotal.buildTradeInfoPb(context);
            info.setTradeIdPre(preTradeId);
            outInfos.add(info);
            sum = info.getBalance();

        } else {
            Map<Integer, Double> userRateMap = projectCostFee.computeSumCostRate(context);
            for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
                if (memberModel.getUserId() != pm.getUserId1()) {
                    PayResult payResult = new PayResult(context, PayConst.PAYTYPE_PHASE);
                    payResult.setTotalBalance(fee.multiply(BigDecimal.valueOf(userRateMap.get(memberModel.getUserId()))));
                    payResult.setUserId(memberModel.getUserId());
                    payResult.setCompanyId(memberModel.getCompanyId());

                    TradeInfo info = payResult.buildTradeInfoPm(context, memberModel);
                    info.setTradeIdPre(preTradeId);
                    info.setServiceType(serviceType);
                    outInfos.add(info);

                    sum = sum.add(info.getBalance());
                }
            }
        }
        toInfo.setTotalBalance(sum);
        toInfo.computeBalance();
        YtbLog.logDebug("List<TradeInfo> outInfos",outInfos);

        List<Integer> tradeIds = transactionService.projectRefundsNoPwd(outInfos, toInfo);
        //payResultTotal.addPbResults(context,PayConst.PAYTYPE_PHASE,pbAgent.getToAccountTrades());

        payEventService.addPayEventPbPhase(context,fee);

        YtbLog.logRun("paPayStop pbPhaseRefund",payResultTotal);

        return payResultTotal;

    }


}