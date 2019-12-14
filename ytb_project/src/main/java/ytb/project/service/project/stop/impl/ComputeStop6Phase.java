package ytb.project.service.project.stop.impl;


import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.CostModel;

import java.math.BigDecimal;

//乙方提出
public class ComputeStop6Phase extends ComputeStopBase {


    //stopType.getComputeResult().setPenalty(computePenalty(context, stopType));
    //stopType.getComputeResult().setQ(computeQ(context, stopType));
    //stopType.getComputeResult().setDelayRate(computeDelayRate(context));
    //stopType.getComputeResult().setQ(BigDecimal.valueOf(0.5));
    //stopType.getComputeResult().setPayFee(BigDecimal.valueOf(10));

    //pa取消 pay PhaseNow and Penalty
    @Override
    public void paCancel(UserProjectContext context, StopType stopType) {
        paPayPhaseNow(context,stopType,computePenalty(context,stopType));
    }

    @Override
    public long computePenalty(UserProjectContext context, StopType stopType) {

        CostModel costModelTotal = projectCostFee.getProjectCostTotal(context);
        BigDecimal unpay = projectCostFee.sumUnpayPhaseCost(context, costModelTotal);
        int prate = findT_Stop_Action(context, stopType).getPenaltyRate();
        return (long) unpay.floatValue() * prate / 100;
    }


    //no penalty
    void paPayPhaseNow(UserProjectContext context, StopType stopType, long penalty) {

        ComputeStopResult stopResult = stopType.getComputeResult();

        stopResult.setAction(penalty == 0 ?
                ComputeStopResult.Stop_Pay_Action_PA_PAY : ComputeStopResult.Stop_Pay_Action_PA_PAY_PENALTY);
        buildCostCommon(context, stopType);
        // 支付阶段费用
        stopResult.setFee(stopResult.getPhaseNowCost());
        // 违约金
        stopResult.setPenalty(penalty);
        stopResult.computePayFee();

    }

    void pbRefund(UserProjectContext context, StopType stopType, BigDecimal phaseCost) {
        ComputeStopResult stopResult = stopType.getComputeResult();

        stopResult.setAction(ComputeStopResult.Stop_Pay_Action_PB_REFUND);
        buildCostCommon(context,stopType);
        stopResult.setPhaseNowCost(BigDecimal.ZERO);
        stopResult.setUnpayPhaseCost(BigDecimal.ZERO);
        //stopResult.setPayedPhaseCost(BigDecimal.ZERO);

        //退回阶段费用
        stopResult.setFee(phaseCost);
        //违约金
        stopResult.setPenalty(0);
        stopResult.computePayFee();
    }

    //三次验收不合格
    @Override
    public void paOffgrade(UserProjectContext context, StopType stopType) {

        ProjectTalkModel ptm = context.getProjectTalkModel();

        if (ptm.getPhase() <= 603) {
            //支付完当前阶段造型和界面的费用其它退回+无违约金+乙方Q减
            paPayPhaseNow(context, stopType,0);
        } else {
           //不支付当前阶段所有费用且退回造型和界面之外的所有费用+无违约金+乙方Q减
            pbRefund(context,stopType,buildOffgradeRefundCost(context));
        }
    }

    //不支付当前阶段所有费用且退回造型和界面之外的所有费用+无违约金+乙方Q减
    //604退0 605退604,606退605，606
    BigDecimal buildOffgradeRefundCost(UserProjectContext context){
        ProjectTalkModel ptm = context.getProjectTalkModel();
        BigDecimal phaseCost = BigDecimal.ZERO;
        if(ptm.getPhase()>604) {
            CostModel costModelTotal = projectCostFee.getProjectCostTotal(context);
            phaseCost = projectCostFee.getPhaseCost(costModelTotal, 604);
            if(ptm.getPhase()>605) {
                BigDecimal  pc = projectCostFee.getPhaseCost(costModelTotal, 605);
                phaseCost.add(pc);
            }

        }
        return  phaseCost;

    }
    //delay 500%
    @Override
    public void paDelay500(UserProjectContext context, StopType stopType) {

        ProjectTalkModel ptm = context.getProjectTalkModel();
        CostModel costModelTotal = projectCostFee.getProjectCostTotal(context);
        BigDecimal phaseCost = projectCostFee.getPhaseCost(costModelTotal,ptm.getPhase());
        pbRefund(context,stopType,phaseCost);

    }

    //804一样的

    //乙方不满意
    @Override
    public void pbUnsatisfy(UserProjectContext context, StopType stopType) {
        paDelay500(context,stopType);

    }


}
