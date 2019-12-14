package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class AccountUserDetail implements Serializable {
    /**
     * 用户明细账户
     */
    private Integer detailId;

    /**
     * 账期
     */
    private Short termId;

    /**
     * 用户账户ID
     */
    private Integer innerId;

    /**
     * 交易ID
     */
    private Integer tradeId;

    /**
     * 交易科目
     */
    private String tradeItem;

    /**
     * 原金额
     */
    private BigDecimal originalBalance;

    /**
     * 交易金额
     */
    private BigDecimal tradeBalance;

    /**
     * 账户余额
     */
    private BigDecimal balance;

    /**
     * 入账时间
     */
    private Date inTime;

    /**
     * 交易类型
     */
    private Short tradeType;

    /**
     * 业务类型
     */
    private Short tradeSubtype;

    /**
     * 与trade相同 
     */
    private Byte status;

    /**
     * 交易金额类型
     */
    private Integer balanceType;

    private static final long serialVersionUID = 1L;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
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
        sb.append(", detailId=").append(detailId);
        sb.append(", termId=").append(termId);
        sb.append(", innerId=").append(innerId);
        sb.append(", tradeId=").append(tradeId);
        sb.append(", tradeItem=").append(tradeItem);
        sb.append(", originalBalance=").append(originalBalance);
        sb.append(", tradeBalance=").append(tradeBalance);
        sb.append(", balance=").append(balance);
        sb.append(", inTime=").append(inTime);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", tradeSubtype=").append(tradeSubtype);
        sb.append(", status=").append(status);
        sb.append(", balanceType=").append(balanceType);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}