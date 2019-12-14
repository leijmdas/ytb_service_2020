package ytb.account.fastpay.model.wechat;

import org.simpleframework.xml.Element;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class WxPayUnifiedorderRequest {


    @Element(name = "appid")
    private String appid;

    @Element(name = "mch_id")
    private String mchId;

    @Element(name = "nonce_str")
    private String nonceStr;

    @Element(name = "sign")
    private String sign;

    @Element(name = "attach", required = false)
    private String attach;

    @Element(name = "body", required = false)
    private String body;

    @Element(name = "detail", required = false)
    private String detail;

    @Element(name = "notify_url")
    private String notifyUrl;

    @Element(name = "openid", required = false)
    private String openid;

    @Element(name = "out_trade_no")
    private String outTradeNo;

    @Element(name = "spbill_create_ip")
    private String spbillCreateIp;

    @Element(name = "total_fee")
    private Integer totalFee;

    @Element(name = "trade_type")
    private String tradeType;

    @Element(name = "device_info", required = false)
    private String deviceInfo;

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getAppid() {
        return appid;
    }

    public String getMchId() {
        return mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public String getAttach() {
        return attach;
    }

    public String getBody() {
        return body;
    }

    public String getDetail() {
        return detail;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getOpenid() {
        return openid;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }
}
