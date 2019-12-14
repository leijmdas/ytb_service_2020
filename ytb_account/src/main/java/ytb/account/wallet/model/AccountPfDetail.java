package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class AccountPfDetail implements Serializable {
    /**
     * 平台账明细账户
     */
    private Integer pfDetailId;

    /**
     * 账期
     */
    private Short termId;

    /**
     * 用户账户ID
     */
    private Integer innerId;

    /**
     * 平台账户ID
     */
    private Integer pfInnerId;

    /**
     * 交易ID
     */
    private Integer tradeId;

    /**
     * 交易科目
     */
    private String tradeItem;

    /**
     * 账户余额
     */
    private BigDecimal balance;

    /**
     * 原金额
     */
    private BigDecimal originalBalance;

    /**
     * 交易金额
     */
    private BigDecimal tradeBalance;

    /**
     * 入账时间
     */
    private Date inTime;

    /**
     * 交易类型
     */
    private Integer tradeType;

    /**
     * 业务类型
     */
    private Integer tradeSubtype;

    /**
     * 服务类型
1--项目款
2--违约金
3--协助金
4--感谢金
5--现金
     */
    private Integer serviceType;

    /**
     * 与trade相同 
     */
    private Byte status;

    /**
     * 交易金额类型
     */
    private Integer balanceType;

    private static final long serialVersionUID = 1L;

    public Integer getPfDetailId() {
        return pfDetailId;
    }

    public void setPfDetailId(Integer pfDetailId) {
        this.pfDetailId = pfDetailId;
    }

    public Short getTermId() {
        return termId;
    }

    public void setTermId(Short termId) {
        this.termId = termId;
    }

    public Integer getInnerId() {
        return innerId;
    }

    public void setInnerId(Integer innerId) {
        this.innerId = innerId;
    }

    public Integer getPfInnerId() {
        return pfInnerId;
    }

    public void setPfInnerId(Integer pfInnerId) {
        this.pfInnerId = pfInnerId;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(String tradeItem) {
        this.tradeItem = tradeItem;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getOriginalBalance() {
        return originalBalance;
    }

    public void setOriginalBalance(BigDecimal originalBalance) {
        this.originalBalance = originalBalance;
    }

    public BigDecimal getTradeBalance() {
        return tradeBalance;
    }

    public void setTradeBalance(BigDecimal tradeBalance) {
        this.tradeBalance = tradeBalance;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getTradeSubtype() {
        return tradeSubtype;
    }

    public void setTradeSubtype(Integer tradeSubtype) {
        this.tradeSubtype = tradeSubtype;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        sb.append(", pfDetailId=").append(pfDetailId);
        sb.append(", termId=").append(termId);
        sb.append(", innerId=").append(innerId);
        sb.append(", pfInnerId=").append(pfInnerId);
        sb.append(", tradeId=").append(tradeId);
        sb.append(", tradeItem=").append(tradeItem);
        sb.append(", balance=").append(balance);
        sb.append(", originalBalance=").append(originalBalance);
        sb.append(", tradeBalance=").append(tradeBalance);
        sb.append(", inTime=").append(inTime);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", tradeSubtype=").append(tradeSubtype);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", status=").append(status);
        sb.append(", balanceType=").append(balanceType);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}