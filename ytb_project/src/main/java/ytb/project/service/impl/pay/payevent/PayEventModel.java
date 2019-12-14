package ytb.project.service.impl.pay.payevent;

import java.math.BigDecimal;

public class PayEventModel {
    int changeType;
    String remark;

    int evenType;
    int pa;
    int pb;
    BigDecimal payFee = BigDecimal.ZERO;


    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getEvenType() {
        return evenType;
    }

    public void setEvenType(int evenType) {
        this.evenType = evenType;
    }

    public int getPa() {
        return pa;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }

    public int getPb() {
        return pb;
    }

    public void setPb(int pb) {
        this.pb = pb;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }


}
