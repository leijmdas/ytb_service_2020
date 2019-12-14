package ytb.project.model;

import com.google.gson.Gson;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class InvoiceInfo  extends Ytb_ModelSkipNull implements Serializable {
    private Integer id;

    /**
     * 代开发票id
     */
    private Integer invoiceId;

    /**
     * 商品名称
     */
    private String tradeName;

    /**
     * 规格型号
     */
    private String specificationType;

    /**
     * 单位
     */
    private String company;

    /**
     * 数量
     */
    private String number;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 金额
     */
    private Double amountOfMoney;

    /**
     * 税率
     */
    private String taxRate;

    /**
     * 税额
     */
    private String taxAmount;

    /**
     * tax rate
     */
    private Date addTime;

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

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", invoiceId=").append(invoiceId);
        sb.append(", tradeName=").append(tradeName);
        sb.append(", specificationType=").append(specificationType);
        sb.append(", company=").append(company);
        sb.append(", number=").append(number);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", amountOfMoney=").append(amountOfMoney);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", taxAmount=").append(taxAmount);
        sb.append(", addTime=").append(addTime);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}