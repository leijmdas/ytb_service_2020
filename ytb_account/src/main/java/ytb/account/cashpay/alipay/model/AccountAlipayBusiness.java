package ytb.account.cashpay.alipay.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class AccountAlipayBusiness { /**
 * 商户订单号
 */
private String outTradeNo;

    /**
     * 支付宝交易号
     */
    private String tradeNo;

    /**
     * 开发者的app_id
     */
    private String appId;

    /**
     * 商户业务号
     */
    private String outBizNo;

    /**
     * 买家支付宝用户号
     */
    private String buyerId;

    /**
     * 卖家支付宝用户号
     */
    private String sellerId;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 订单金额
     */
    private BigDecimal totalAmount;

    /**
     * 实收金额
     */
    private BigDecimal receiptAmount;

    /**
     * 开票金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 付款金额
     */
    private BigDecimal buyerPayAmount;

    /**
     * 集分宝金额
     */
    private BigDecimal pointAmount;

    /**
     * 总退款金额
     */
    private BigDecimal refundFee;

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 交易创建时间
     */
    private Date gmtCreate;

    /**
     * 交易付款时间
     */
    private Date gmtPayment;

    /**
     * 交易退款时间
     */
    private Date gmtRefund;

    /**
     * 交易结束时间
     */
    private Date gmtClose;

    /**
     * 支付金额信息
     */
    private String fundBillList;

    /**
     * 优惠券信息
     */
    private String voucherDetailList;

    /**
     * 回传参数
     */
    private String passbackParams;

    /**
     * 商品描述
     */
    private String body;

    private static final long serialVersionUID = 1L;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(BigDecimal buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public BigDecimal getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(BigDecimal pointAmount) {
        this.pointAmount = pointAmount;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public Date getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    public String getFundBillList() {
        return fundBillList;
    }

    public void setFundBillList(String fundBillList) {
        this.fundBillList = fundBillList;
    }

    public String getVoucherDetailList() {
        return voucherDetailList;
    }

    public void setVoucherDetailList(String voucherDetailList) {
        this.voucherDetailList = voucherDetailList;
    }

    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", appId=").append(appId);
        sb.append(", outBizNo=").append(outBizNo);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", tradeStatus=").append(tradeStatus);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", receiptAmount=").append(receiptAmount);
        sb.append(", invoiceAmount=").append(invoiceAmount);
        sb.append(", buyerPayAmount=").append(buyerPayAmount);
        sb.append(", pointAmount=").append(pointAmount);
        sb.append(", refundFee=").append(refundFee);
        sb.append(", subject=").append(subject);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtPayment=").append(gmtPayment);
        sb.append(", gmtRefund=").append(gmtRefund);
        sb.append(", gmtClose=").append(gmtClose);
        sb.append(", fundBillList=").append(fundBillList);
        sb.append(", voucherDetailList=").append(voucherDetailList);
        sb.append(", passbackParams=").append(passbackParams);
        sb.append(", body=").append(body);
        sb.append("]");
        return sb.toString();
    }
}
