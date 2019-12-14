package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class AccountTradeOut implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * Wechat/Alipay/
     */
    private String payModel;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 回传参数
     */
    private String passbackParams;

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 二维码
     */
    private String qrCode;

    private String orderState;

    /**
     * 买家用户号
     */
    private String buyerId;

    /**
     * 卖家用户号
     */
    private String sellerId;

    /**
     * 交易创建时间
     */
    private Date gmtCreate;

    /**
     * YTB订单号
     */
    private String tradeId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayModel() {
        return payModel;
    }

    public void setPayModel(String payModel) {
        this.payModel = payModel;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", payModel=").append(payModel);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", passbackParams=").append(passbackParams);
        sb.append(", subject=").append(subject);
        sb.append(", body=").append(body);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", qrCode=").append(qrCode);
        sb.append(", orderState=").append(orderState);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", tradeId=").append(tradeId);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}