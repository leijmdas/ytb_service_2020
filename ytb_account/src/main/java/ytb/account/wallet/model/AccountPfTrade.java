package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class AccountPfTrade implements Serializable {
    /**
     * 交易ID
     */
    private Integer tradeId;

    /**
     * 账期
     */
    private Short termId;

    /**
     * 订单编号
     */
    private String tradeNo;

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
    private Integer toPfInnerId;

    /**
     * 原单号
     */
    private String tradeNoPre;

    /**
     * 交易类型
     */
    private Integer tradeType;

    /**
     * 业务类型
     */
    private Short tradeSubtype;

    /**
     * 交易金额
     */
    private BigDecimal balance;

    /**
     * 状态
     */
    private Short status;

    /**
     * 重做次数
     */
    private Byte retryTimes;

    /**
     * 创建者
     */
    private Integer createBy;

    /**
     * 重做时间
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
     * 进度标示
     */
    private Integer progressId;

    /**
     * outid
     */
    private Integer outId;

    /**
     * 已付款金额
     */
    private BigDecimal paymentBalance;

    /**
     * 退款金额
     */
    private BigDecimal refundBalance;

    private static final long serialVersionUID = 1L;

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Short getTermId() {
        return termId;
    }

    public void setTermId(Short termId) {
        this.termId = termId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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

    public Integer getToPfInnerId() {
        return toPfInnerId;
    }

    public void setToPfInnerId(Integer toPfInnerId) {
        this.toPfInnerId = toPfInnerId;
    }

    public String getTradeNoPre() {
        return tradeNoPre;
    }

    public void setTradeNoPre(String tradeNoPre) {
        this.tradeNoPre = tradeNoPre;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Short getTradeSubtype() {
        return tradeSubtype;
    }

    public void setTradeSubtype(Short tradeSubtype) {
        this.tradeSubtype = tradeSubtype;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    public Integer getProgressId() {
        return progressId;
    }

    public void setProgressId(Integer progressId) {
        this.progressId = progressId;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public BigDecimal getPaymentBalance() {
        return paymentBalance;
    }

    public void setPaymentBalance(BigDecimal paymentBalance) {
        this.paymentBalance = paymentBalance;
    }

    public BigDecimal getRefundBalance() {
        return refundBalance;
    }

    public void setRefundBalance(BigDecimal refundBalance) {
        this.refundBalance = refundBalance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tradeId=").append(tradeId);
        sb.append(", termId=").append(termId);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", projectId=").append(projectId);
        sb.append(", acId=").append(acId);
        sb.append(", toPfInnerId=").append(toPfInnerId);
        sb.append(", tradeNoPre=").append(tradeNoPre);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", tradeSubtype=").append(tradeSubtype);
        sb.append(", balance=").append(balance);
        sb.append(", status=").append(status);
        sb.append(", retryTimes=").append(retryTimes);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", calFlag=").append(calFlag);
        sb.append(", userId=").append(userId);
        sb.append(", companyId=").append(companyId);
        sb.append(", progressId=").append(progressId);
        sb.append(", outId=").append(outId);
        sb.append(", paymentBalance=").append(paymentBalance);
        sb.append(", refundBalance=").append(refundBalance);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}