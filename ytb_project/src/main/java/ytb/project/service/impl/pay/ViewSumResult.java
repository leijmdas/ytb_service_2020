package ytb.project.service.impl.pay;

import java.math.BigDecimal;

public final class ViewSumResult {
    //甲方启动项目交易标识
    int preTradeId;

    //changeType
    int changeType ;

    //1.1甲方最加支付
    BigDecimal paPhasePay  ;
    //1.2乙方阶段退款
    BigDecimal pbPhaseRefund  ;

    //2.1 甲方解冻
    BigDecimal feePaUnfreeze = BigDecimal.ZERO;
    //2.2 乙方解冻
    BigDecimal feePbUnfreeze = BigDecimal.ZERO;
    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }
    public int getPreTradeId() {
        return preTradeId;
    }

    public void setPreTradeId(int preTradeId) {
        this.preTradeId = preTradeId;
    }

    public BigDecimal getFeeTotal() {
        return  feePbUnfreeze ;
    }

    public BigDecimal getPaPhasePay() {
        return paPhasePay;
    }
    public void setPaPhasePay(BigDecimal paPhasePay) {
        this.paPhasePay = paPhasePay;
    }

    public BigDecimal getPbPhaseRefund() {
        return pbPhaseRefund;
    }

    public void setPbPhaseRefund(BigDecimal pbPhaseRefund) {
        this.pbPhaseRefund = pbPhaseRefund;
    }

    public BigDecimal getFeePaUnfreeze() {
        return feePaUnfreeze;
    }

    public void setFeePaUnfreeze(BigDecimal feePaUnfreeze) {
        this.feePaUnfreeze = feePaUnfreeze;
    }

    public BigDecimal getFeePbUnfreeze() {
        return feePbUnfreeze;
    }

    public void setFeePbUnfreeze(BigDecimal feePbUnfreeze) {
        this.feePbUnfreeze = feePbUnfreeze;
    }

}
