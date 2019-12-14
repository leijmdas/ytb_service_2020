package ytb.account.fastpay.model.wechat;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class WechatConfigure {
    /*AppID(应用ID*/
    private String appId;
    /*应用密钥*/
    private String appSecret;
    /*商户号*/
    private String mchId;
    /*apiKey--商户Key*/
    private String apiKey;
    /*回调地址*/
    private String notifyUrl;
    /*P12*/
    private String pVoucherFile;

    private String payreurl;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPayreurl() {
        return payreurl;
    }

    public void setPayreurl(String payreurl) {
        this.payreurl = payreurl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getpVoucherFile() {
        return pVoucherFile;
    }

    public void setpVoucherFile(String pVoucherFile) {
        this.pVoucherFile = pVoucherFile;
    }
}
