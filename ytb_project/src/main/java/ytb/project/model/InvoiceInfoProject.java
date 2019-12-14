package ytb.project.model;

import com.google.gson.Gson;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class InvoiceInfoProject  extends Ytb_ModelSkipNull implements Serializable {
    private Integer id;

    /**
     * 代开发票id
     */
    private Integer invoiceId;

    /**
     * 邮递地址
     */
    private String postalAddress;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 开票状态 - 0 未开出 1 - 开出
     */
    private Integer billingStatus;

    /**
     * 开票时间
     */
    private Date addTime;

    /**
     * 1-普通发票 、 2增值发票
     */
    private Integer invoiceType;

    /**
     * 快递商家
     */
    private String expressCompany;

    /**
     * 快递编号
     */
    private String expressCompanyId;

    /**
     * 收件人
     */
    private String addressee;

    /**
     * 联系电话
     */
    private String contactNumber;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(Integer billingStatus) {
        this.billingStatus = billingStatus;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressCompanyId() {
        return expressCompanyId;
    }

    public void setExpressCompanyId(String expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", invoiceId=").append(invoiceId);
        sb.append(", postalAddress=").append(postalAddress);
        sb.append(", projectId=").append(projectId);
        sb.append(", billingStatus=").append(billingStatus);
        sb.append(", addTime=").append(addTime);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", expressCompany=").append(expressCompany);
        sb.append(", expressCompanyId=").append(expressCompanyId);
        sb.append(", addressee=").append(addressee);
        sb.append(", contactNumber=").append(contactNumber);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}