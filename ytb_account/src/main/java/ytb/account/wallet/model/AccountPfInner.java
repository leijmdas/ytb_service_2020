package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class AccountPfInner implements Serializable {
    /**
     * 内部账户
     */
    private Integer pfInnerId;

    /**
     * 账期
     */
    private Short termId;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 账户类型
     */
    private Short acType;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 收入
     */
    private BigDecimal balanceIn;

    /**
     * 支出
     */
    private BigDecimal balanceOut;

    /**
     * 托管账户余额(收入 用户充值) 冻结
     */
    private BigDecimal balanceAgent;

    /**
     * 提现（支出 用户提现）冻结费用
     */
    private BigDecimal takeoutMoney;

    /**
     * 账户状态
     */
    private Byte status;

    /**
     * 开户时间
     */
    private Date openTime;

    /**
     * 最后一毛结算时间
     */
    private Date calTime;

    private static final long serialVersionUID = 1L;

    public Integer getPfInnerId() {
        return pfInnerId;
    }

    public void setPfInnerId(Integer pfInnerId) {
        this.pfInnerId = pfInnerId;
    }

    public Short getTermId() {
        return termId;
    }

    public void setTermId(Short termId) {
        this.termId = termId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public Short getAcType() {
        return acType;
    }

    public void setAcType(Short acType) {
        this.acType = acType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalanceIn() {
        return balanceIn;
    }

    public void setBalanceIn(BigDecimal balanceIn) {
        this.balanceIn = balanceIn;
    }

    public BigDecimal getBalanceOut() {
        return balanceOut;
    }

    public void setBalanceOut(BigDecimal balanceOut) {
        this.balanceOut = balanceOut;
    }

    public BigDecimal getBalanceAgent() {
        return balanceAgent;
    }

    public void setBalanceAgent(BigDecimal balanceAgent) {
        this.balanceAgent = balanceAgent;
    }

    public BigDecimal getTakeoutMoney() {
        return takeoutMoney;
    }

    public void setTakeoutMoney(BigDecimal takeoutMoney) {
        this.takeoutMoney = takeoutMoney;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCalTime() {
        return calTime;
    }

    public void setCalTime(Date calTime) {
        this.calTime = calTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pfInnerId=").append(pfInnerId);
        sb.append(", termId=").append(termId);
        sb.append(", payPassword=").append(payPassword);
        sb.append(", acType=").append(acType);
        sb.append(", balance=").append(balance);
        sb.append(", balanceIn=").append(balanceIn);
        sb.append(", balanceOut=").append(balanceOut);
        sb.append(", balanceAgent=").append(balanceAgent);
        sb.append(", takeoutMoney=").append(takeoutMoney);
        sb.append(", status=").append(status);
        sb.append(", openTime=").append(openTime);
        sb.append(", calTime=").append(calTime);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}