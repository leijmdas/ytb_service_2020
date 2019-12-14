package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class AccountUserInner implements Serializable {
    /**
     * 用户内部账户
     */
    private Integer innerId;

    /**
     * 账期
     */
    private Short termId;

    /**
     * 1:个人用户  2:公司
     */
    private Byte userType;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 托管账户余额(支付冻结)
     */
    private BigDecimal payoutAgent;

    /**
     * 项目收入冻结金额

     */
    private BigDecimal incomeAgent;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 提现冻结费用
     */
    private BigDecimal takeoutMoney;

    /**
     * 收入
     */
    private BigDecimal balanceIn;

    /**
     * 支出
     */
    private BigDecimal balanceOut;

    /**
     * 账户状态
     */
    private Byte status;

    /**
     * 开户时间
     */
    private Date openTime;


    private BigDecimal amountMoney;

    private Integer bankCardsNumber;

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
    }

    public Integer getBankCardsNumber() {
        return bankCardsNumber;
    }

    public void setBankCardsNumber(Integer bankCardsNumber) {
        this.bankCardsNumber = bankCardsNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getInnerId() {
        return innerId;
    }

    public void setInnerId(Integer innerId) {
        this.innerId = innerId;
    }

    public Short getTermId() {
        return termId;
    }

    public void setTermId(Short termId) {
        this.termId = termId;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPayoutAgent() {
        return payoutAgent;
    }

    public void setPayoutAgent(BigDecimal payoutAgent) {
        this.payoutAgent = payoutAgent;
    }

    public BigDecimal getIncomeAgent() {
        return incomeAgent;
    }

    public void setIncomeAgent(BigDecimal incomeAgent) {
        this.incomeAgent = incomeAgent;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public BigDecimal getTakeoutMoney() {
        return takeoutMoney;
    }

    public void setTakeoutMoney(BigDecimal takeoutMoney) {
        this.takeoutMoney = takeoutMoney;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", innerId=").append(innerId);
        sb.append(", termId=").append(termId);
        sb.append(", userType=").append(userType);
        sb.append(", userId=").append(userId);
        sb.append(", companyId=").append(companyId);
        sb.append(", balance=").append(balance);
        sb.append(", payoutAgent=").append(payoutAgent);
        sb.append(", incomeAgent=").append(incomeAgent);
        sb.append(", payPassword=").append(payPassword);
        sb.append(", takeoutMoney=").append(takeoutMoney);
        sb.append(", balanceIn=").append(balanceIn);
        sb.append(", balanceOut=").append(balanceOut);
        sb.append(", status=").append(status);
        sb.append(", openTime=").append(openTime);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}