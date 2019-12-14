package ytb.project.model;

import com.google.gson.Gson;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Invoice  extends Ytb_ModelSkipNull implements Serializable {
    private Integer id;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 机械编号
     */
    private Integer machineNumber;

    /**
     * 发票代码
     */
    private String invoiceCode;

    /**
     * 发票号码
     */
    private String invoiceNumber;

    /**
     * 开票日期
     */
    private Date dateOfInvoice;

    /**
     * 校验码
     */
    private String checkCode;

    /**
     * Buy_名称
     */
    private String buyName;

    /**
     * Buy_纳税人识别号
     */
    private String buyTaxNumber;

    /**
     * Buy_地址
     */
    private String buyAddress;

    /**
     * Buy_电话
     */
    private String buyPhone;

    /**
     * Buy_开户银行
     */
    private String buyOpeningBank;

    /**
     * Buy_账号
     */
    private String buyAccount;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 收款人
     */
    private String payee;

    /**
     * 复核人
     */
    private String toReview;

    /**
     * 开票人
     */
    private String drawer;

    /**
     * Sale_名称
     */
    private String saleName;

    /**
     * Sale_税号
     */
    private String saleTaxNumber;

    /**
     * Sale_地址
     */
    private String saleAddress;

    /**
     * Sale_电话
     */
    private String salePhone;

    /**
     * Sale_开户银行
     */
    private String saleOpeningBank;

    /**
     * Sale_账号
     */
    private String saleAccount;

    /**
     * 合计金额
     */
    private String totalSum;

    /**
     * 合计税额
     */
    private String aggregateTax;

    /**
     * 价税合计
     */
    private String totalPriceAndTax;

    /**
     * state
     */
    private String invoiceState;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(Integer machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(Date dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public String getBuyTaxNumber() {
        return buyTaxNumber;
    }

    public void setBuyTaxNumber(String buyTaxNumber) {
        this.buyTaxNumber = buyTaxNumber;
    }

    public String getBuyAddress() {
        return buyAddress;
    }

    public void setBuyAddress(String buyAddress) {
        this.buyAddress = buyAddress;
    }

    public String getBuyPhone() {
        return buyPhone;
    }

    public void setBuyPhone(String buyPhone) {
        this.buyPhone = buyPhone;
    }

    public String getBuyOpeningBank() {
        return buyOpeningBank;
    }

    public void setBuyOpeningBank(String buyOpeningBank) {
        this.buyOpeningBank = buyOpeningBank;
    }

    public String getBuyAccount() {
        return buyAccount;
    }

    public void setBuyAccount(String buyAccount) {
        this.buyAccount = buyAccount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getToReview() {
        return toReview;
    }

    public void setToReview(String toReview) {
        this.toReview = toReview;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleTaxNumber() {
        return saleTaxNumber;
    }

    public void setSaleTaxNumber(String saleTaxNumber) {
        this.saleTaxNumber = saleTaxNumber;
    }

    public String getSaleAddress() {
        return saleAddress;
    }

    public void setSaleAddress(String saleAddress) {
        this.saleAddress = saleAddress;
    }

    public String getSalePhone() {
        return salePhone;
    }

    public void setSalePhone(String salePhone) {
        this.salePhone = salePhone;
    }

    public String getSaleOpeningBank() {
        return saleOpeningBank;
    }

    public void setSaleOpeningBank(String saleOpeningBank) {
        this.saleOpeningBank = saleOpeningBank;
    }

    public String getSaleAccount() {
        return saleAccount;
    }

    public void setSaleAccount(String saleAccount) {
        this.saleAccount = saleAccount;
    }

    public String getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(String totalSum) {
        this.totalSum = totalSum;
    }

    public String getAggregateTax() {
        return aggregateTax;
    }

    public void setAggregateTax(String aggregateTax) {
        this.aggregateTax = aggregateTax;
    }

    public String getTotalPriceAndTax() {
        return totalPriceAndTax;
    }

    public void setTotalPriceAndTax(String totalPriceAndTax) {
        this.totalPriceAndTax = totalPriceAndTax;
    }

    public String getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(String invoiceState) {
        this.invoiceState = invoiceState;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", qrCode=").append(qrCode);
        sb.append(", machineNumber=").append(machineNumber);
        sb.append(", invoiceCode=").append(invoiceCode);
        sb.append(", invoiceNumber=").append(invoiceNumber);
        sb.append(", dateOfInvoice=").append(dateOfInvoice);
        sb.append(", checkCode=").append(checkCode);
        sb.append(", buyName=").append(buyName);
        sb.append(", buyTaxNumber=").append(buyTaxNumber);
        sb.append(", buyAddress=").append(buyAddress);
        sb.append(", buyPhone=").append(buyPhone);
        sb.append(", buyOpeningBank=").append(buyOpeningBank);
        sb.append(", buyAccount=").append(buyAccount);
        sb.append(", remarks=").append(remarks);
        sb.append(", payee=").append(payee);
        sb.append(", toReview=").append(toReview);
        sb.append(", drawer=").append(drawer);
        sb.append(", saleName=").append(saleName);
        sb.append(", saleTaxNumber=").append(saleTaxNumber);
        sb.append(", saleAddress=").append(saleAddress);
        sb.append(", salePhone=").append(salePhone);
        sb.append(", saleOpeningBank=").append(saleOpeningBank);
        sb.append(", saleAccount=").append(saleAccount);
        sb.append(", totalSum=").append(totalSum);
        sb.append(", aggregateTax=").append(aggregateTax);
        sb.append(", totalPriceAndTax=").append(totalPriceAndTax);
        sb.append(", invoiceState=").append(invoiceState);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}