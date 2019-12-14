package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class AccountPfOut implements Serializable {
    /**
     * 外部账户
     */
    private Integer pfOutId;

    /**
     * 账户类型
     */
    private Byte accountType;

    /**
     * 平台托管账户?
     */
    private Byte isAgent;

    /**
     * 账号
     */
    private String accountNo;

    /**
     * 账户绑定手机号
     */
    private String mobileNo;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 支行名称
     */
    private String branchName;

    /**
     * 金额
     */
    private BigDecimal balance;

    /**
     * 账户状态
     */
    private Byte status;

    /**
     * 记录时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getPfOutId() {
        return pfOutId;
    }

    public void setPfOutId(Integer pfOutId) {
        this.pfOutId = pfOutId;
    }

    public Byte getAccountType() {
        return accountType;
    }

    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    public Byte getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Byte isAgent) {
        this.isAgent = isAgent;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pfOutId=").append(pfOutId);
        sb.append(", accountType=").append(accountType);
        sb.append(", isAgent=").append(isAgent);
        sb.append(", accountNo=").append(accountNo);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", bankName=").append(bankName);
        sb.append(", branchName=").append(branchName);
        sb.append(", balance=").append(balance);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}