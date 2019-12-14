package ytb.account.cashpay.alipay.model;

import java.math.BigDecimal;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/1/22
 */
public class ComeTransferAccountsModel {

    private Integer tradeId;

    private BigDecimal balance;

    private BigDecimal totalBalance;

    private BigDecimal serviceFee;

    private BigDecimal tax;

    private BigDecimal fee;

    private String status;

    private Integer tradeType;

    private String acId;

    private String tradeOutId;

    private Short tradeSubtype;

    private String userId;

    private String companyId;

    private String innerId;

    private String cardHolder;

    private String bankName;

    private String branchName;

    private String cardHolderId;

    private String accountType;

    private String outId;

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId;
    }

    public String getTradeOutId() {
        return tradeOutId;
    }

    public void setTradeOutId(String tradeOutId) {
        this.tradeOutId = tradeOutId;
    }

    public Short getTradeSubtype() {
        return tradeSubtype;
    }

    public void setTradeSubtype(Short tradeSubtype) {
        this.tradeSubtype = tradeSubtype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getInnerId() {
        return innerId;
    }

    public void setInnerId(String innerId) {
        this.innerId = innerId;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCardHolderId() {
        return cardHolderId;
    }

    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }
}
