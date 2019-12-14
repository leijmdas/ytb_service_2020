package ytb.project.service.project.stop.impl;

import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;

public class ComputeStopResult extends Ytb_Model {

    public static Byte Stop_Pay_Action_PA_PAY_PENALTY = 1;
    public static Byte Stop_Pay_Action_PA_PAY  = 2;
    public static Byte Stop_Pay_Action_PB_REFUND = 3;

   // action
    public String getPayRemark() {

        if (action == Stop_Pay_Action_PA_PAY_PENALTY) {

            return "项目终止,甲方支付当前费用+违约金共:" + payFee + "元";

        } else if (action == Stop_Pay_Action_PA_PAY) {

            return "项目终止,甲方支付当前费用:" + payFee + "元";

        } else if (action == Stop_Pay_Action_PB_REFUND) {

            return "项目终止,乙方退回费用:" + payFee + "元";

        }

        return "项目终止,支付类型错误！";
    }

    // 1 payFee甲方支付费用/+违约金/乙方退回费用
    Byte action = Stop_Pay_Action_PA_PAY_PENALTY;
    // 2 总费用（fee+penalty)甲方支付当前费用+违约金 或者 乙方退回费用）
    BigDecimal payFee = BigDecimal.ZERO;

    // 3 费用(甲方支付当前费用或者乙方退回费用）
    BigDecimal fee = BigDecimal.ZERO  ;
    // 4 计算违约金
    long penalty = 0;

    // 5 计算Q分 查询表
    BigDecimal q = BigDecimal.ZERO;

    // 6 计算延期率 ;
    long delayRate = 0 ;

    //① 原计划总工时费 = 原工作计划总工时费x.xx
    BigDecimal costTotal = BigDecimal.ZERO;

    //② 已支付工时费 = 原工作计划阶段支付工时费合计x.xx
    BigDecimal payedPhaseCost = BigDecimal.ZERO;
    // 未支付
    BigDecimal unpayPhaseCost =  BigDecimal.ZERO;

    //③ 采购和加工预算=    (p-3) *总预算/3（p≥4/5，p＜4，则为0）
    //④ 已采购和加工发票总额=     x.xx

    //⑤ 支付当前阶段工时费=   当前p阶段的工时费x.xx
    BigDecimal phaseNowCost =  BigDecimal.ZERO;

    public void computePayFee() {
        payFee = fee.add(BigDecimal.valueOf(penalty));
    }

    public long getPenalty() {
        return penalty;
    }

    public void setPenalty(long penalty) {
        this.penalty = penalty;
    }

    public BigDecimal getQ() {
        return q;
    }

    public void setQ(BigDecimal q) {
        this.q = q;
    }

    public long getDelayRate() {
        return delayRate;
    }

    public void setDelayRate(long delayRate) {
        this.delayRate = delayRate;
    }

    public Byte getAction() {
        return action;
    }

    public void setAction(Byte action) {
        this.action = action;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }


    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    public BigDecimal getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(BigDecimal costTotal) {
        this.costTotal = costTotal;
    }

    public BigDecimal getPayedPhaseCost() {
        return payedPhaseCost;
    }

    public void setPayedPhaseCost(BigDecimal payedPhaseCost) {
        this.payedPhaseCost = payedPhaseCost;
    }

    public BigDecimal getUnpayPhaseCost() {
        return unpayPhaseCost;
    }

    public void setUnpayPhaseCost(BigDecimal unpayPhaseCost) {
        this.unpayPhaseCost = unpayPhaseCost;
    }

    public BigDecimal getPhaseNowCost() {
        return phaseNowCost;
    }

    public void setPhaseNowCost(BigDecimal phaseNowCost) {
        this.phaseNowCost = phaseNowCost;
    }
}

