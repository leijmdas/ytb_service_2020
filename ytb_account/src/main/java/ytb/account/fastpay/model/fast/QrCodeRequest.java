package ytb.account.fastpay.model.fast;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class QrCodeRequest {
    @JSONField(name = "device_info")
    private String deviceInfo;

    @JSONField(name = "body")
    private String body;

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    @JSONField(name = "total_fee")
    private Integer totalFee;

    @JSONField(name = "spbill_create_ip")
    private String spBillCreateIp;

    @JSONField(name = "attach")
    private String attach;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
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

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpBillCreateIp() {
        return spBillCreateIp;
    }

    public void setSpBillCreateIp(String spBillCreateIp) {
        this.spBillCreateIp = spBillCreateIp;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
