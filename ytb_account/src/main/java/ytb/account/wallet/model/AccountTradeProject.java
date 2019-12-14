package ytb.account.wallet.model;

import com.google.gson.Gson;
import ytb.account.wallet.service.AccountConst.AccountConst;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class AccountTradeProject implements Serializable {
    /**
     * 交易ID
     */
    private Integer tradeId;

    /**
     * 订单编号
     */
    private String tradeNo;

    /**
     * 原交易ID
     */
    private String tradeNoPre;

    /**
     * 项目ID
     */
    private Integer projectId;

    private Integer talkId;

    private Short phase;

    /**
     * 服务类型
1--项目款
2--违约金
3--协助金
4--感谢金
5--现金
     */
    private Byte serviceType;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * 转出账户
     */
    private Integer acId;

    /**
     * 转入账户
     */
    private Integer toAcId;

    /**
     * 交易类型
     */
    private Short tradeType;

    /**
     * 业务类型
     */
    private Short tradeSubtype;

    /**
     * 总金
     */
    private BigDecimal totalBalance;

    /**
     * 交易金额
     */
    private BigDecimal balance;

    /**
     * -服务费
     */
    private BigDecimal serviceFee;

    /**
     * -税费
     */
    private BigDecimal tax;

    /**
     * -手续费
     */
    private BigDecimal fee;

    private BigDecimal refundBalance;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 是否甲方
     */
    private Boolean isA;

    /**
     * 重做次数
     */
    private Byte retryTimes;

    /**
     * 创建者
     */
    private Integer createBy;

    /**
     * 重做时间 时间不能为000000
     */
    private Date createTime;

    /**
     * 结算标志
     */
    private Boolean calFlag;

    /**
     * outid外部支出方式
     */
    private Integer outId;

    /**
     * 账期
     */
    private Integer termId;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 是否开发票
     */
    private Boolean invoice;

    /**
     * 交易金额类型
     */
    private Integer balanceType;

    private static final long serialVersionUID = 1L;
    public boolean checkB2F() {

        return getTradeSubtype().equals(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN)
                || getTradeSubtype().equals(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN_INCOME);
    }

    public boolean checkF2F() {

        return getTradeSubtype().equals(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
    }

    public boolean checkB2B() {

        return getTradeSubtype().equals(AccountConst.TRADE_SUBTYPE_BALANCE_2_BALANCE);
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeNoPre() {
        return tradeNoPre;
    }

    public void setTradeNoPre(String tradeNoPre) {
        this.tradeNoPre = tradeNoPre;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }

    public Short getPhase() {
        return phase;
    }

    public void setPhase(Short phase) {
        this.phase = phase;
    }

    public Byte getServiceType() {
        return serviceType;
    }

    public void setServiceType(Byte serviceType) {
        this.serviceType = serviceType;
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

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public Integer getToAcId() {
        return toAcId;
    }

    public void setToAcId(Integer toAcId) {
        this.toAcId = toAcId;
    }

    public Short getTradeType() {
        return tradeType;
    }

    public void setTradeType(Short tradeType) {
        this.tradeType = tradeType;
    }

    public Short getTradeSubtype() {
        return tradeSubtype;
    }

    public void setTradeSubtype(Short tradeSubtype) {
        this.tradeSubtype = tradeSubtype;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getRefundBalance() {
        return refundBalance;
    }

    public void setRefundBalance(BigDecimal refundBalance) {
        this.refundBalance = refundBalance;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getIsA() {
        return isA;
    }

    public void setIsA(Boolean isA) {
        this.isA = isA;
    }

    public Byte getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Byte retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getCalFlag() {
        return calFlag;
    }

    public void setCalFlag(Boolean calFlag) {
        this.calFlag = calFlag;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Boolean getInvoice() {
        return invoice;
    }

    public void setInvoice(Boolean invoice) {
        this.invoice = invoice;
    }

    public Integer getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(Integer balanceType) {
        this.balanceType = balanceType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tradeId=").append(tradeId);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", tradeNoPre=").append(tradeNoPre);
        sb.append(", projectId=").append(projectId);
        sb.append(", talkId=").append(talkId);
        sb.append(", phase=").append(phase);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", userId=").append(userId);
        sb.append(", companyId=").append(companyId);
        sb.append(", acId=").append(acId);
        sb.append(", toAcId=").append(toAcId);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", tradeSubtype=").append(tradeSubtype);
        sb.append(", totalBalance=").append(totalBalance);
        sb.append(", balance=").append(balance);
        sb.append(", serviceFee=").append(serviceFee);
        sb.append(", tax=").append(tax);
        sb.append(", fee=").append(fee);
        sb.append(", refundBalance=").append(refundBalance);
        sb.append(", status=").append(status);
        sb.append(", isA=").append(isA);
        sb.append(", retryTimes=").append(retryTimes);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", calFlag=").append(calFlag);
        sb.append(", outId=").append(outId);
        sb.append(", termId=").append(termId);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", addTime=").append(addTime);
        sb.append(", invoice=").append(invoice);
        sb.append(", balanceType=").append(balanceType);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}