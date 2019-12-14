package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class AccountUserOut implements Serializable {
    /**
     * 用户外部
     */
    private Integer outId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * 账户类型
     */
    private Byte accountType;

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
     * 账户状态
     */
    private Byte status;

    /**
     * 记录时间
     */
    private Date createTime;

    /**
     * 是否默认
     */
    private Boolean isDefault;

    /**
     * 持卡人姓名
     */
    private String cardHolder;

    /**
     * 持卡人身份证
     */
    private String cardHolderId;

    private static final long serialVersionUID = 1L;

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
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

    public Byte getAccountType() {
        return accountType;
    }

    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardHolderId() {
        return cardHolderId;
    }

    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", outId=").append(outId);
        sb.append(", userId=").append(userId);
        sb.append(", companyId=").append(companyId);
        sb.append(", accountType=").append(accountType);
        sb.append(", accountNo=").append(accountNo);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", bankName=").append(bankName);
        sb.append(", branchName=").append(branchName);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", cardHolder=").append(cardHolder);
        sb.append(", cardHolderId=").append(cardHolderId);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}