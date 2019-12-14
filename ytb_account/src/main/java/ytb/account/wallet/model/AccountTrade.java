package ytb.account.wallet.model;

import com.google.gson.Gson;
import ytb.common.context.model.Ytb_Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 */
public class AccountTrade extends Ytb_Model implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * 服务类型
     */
    private Byte serviceType;

    /**
     * 交易金额
     */
    private BigDecimal balance;

    /**
     * -服务费
     */
    private BigDecimal serviceFee;

    /**
     * -手续费
     */
    private BigDecimal fee;

    /**
     * -税费
     */
    private BigDecimal tax;

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
     * 用户ID
     */
    private Integer userId;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * outid外部支出方式
     */
    private Integer outId;

    /**
     * 总金
     */
    private BigDecimal totalBalance;

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

    private BigDecimal refundBalance;

    private Integer talkId;


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

    public Byte getServiceType() {
        return serviceType;
    }

    public void setServiceType(Byte serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
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

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
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

    public BigDecimal getRefundBalance() {
        return refundBalance;
    }

    public void setRefundBalance(BigDecimal refundBalance) {
        this.refundBalance = refundBalance;
    }

    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", tradeId=").append(tradeId);
//        sb.append(", tradeNo=").append(tradeNo);
//        sb.append(", tradeNoPre=").append(tradeNoPre);
//        sb.append(", projectId=").append(projectId);
//        sb.append(", acId=").append(acId);
//        sb.append(", toAcId=").append(toAcId);
//        sb.append(", tradeType=").append(tradeType);
//        sb.append(", tradeSubtype=").append(tradeSubtype);
//        sb.append(", serviceType=").append(serviceType);
//        sb.append(", balance=").append(balance);
//        sb.append(", serviceFee=").append(serviceFee);
//        sb.append(", fee=").append(fee);
//        sb.append(", tax=").append(tax);
//        sb.append(", status=").append(status);
//        sb.append(", isA=").append(isA);
//        sb.append(", retryTimes=").append(retryTimes);
//        sb.append(", createBy=").append(createBy);
//        sb.append(", createTime=").append(createTime);
//        sb.append(", calFlag=").append(calFlag);
//        sb.append(", userId=").append(userId);
//        sb.append(", companyId=").append(companyId);
//        sb.append(", outId=").append(outId);
//        sb.append(", totalBalance=").append(totalBalance);
//        sb.append(", termId=").append(termId);
//        sb.append(", ipAddress=").append(ipAddress);
//        sb.append(", addTime=").append(addTime);
//        sb.append(", refundBalance=").append(refundBalance);
//        sb.append(", talkId=").append(talkId);
//        sb.append("]");
//        return sb.toString();
//    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}