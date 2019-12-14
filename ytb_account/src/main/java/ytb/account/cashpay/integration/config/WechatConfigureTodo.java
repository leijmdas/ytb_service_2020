package ytb.account.cashpay.integration.config;


import ytb.account.fastpay.model.wechat.WechatConfigure;

public class WechatConfigureTodo {

    /*AppID(应用ID*/
    private  String appId = "wx0024dd0ea84c0d01";
    /*应用密钥*/
    private  String appSecret;
    /*商户号*/
    private  String mchId = "1519498421";
    /*apiKey--商户Key*/
    private  String apiKey = "Zhxji325UCaCHUajiukujka53543a888";
    /*回调地址*/
    private  String notifyUrl = "http://www.youtobon.com/wechat/callback";
    /*P12*/
    private  String pVoucherFile = "D:\\apiclient_cert.p12";

    private String payreurl;

    private String token;



    public WechatConfigure getconfig(){
        WechatConfigure wechatConfigure = new WechatConfigure();
        wechatConfigure.setAppId(appId);
        wechatConfigure.setAppSecret(appSecret);
        wechatConfigure.setMchId(mchId);
        wechatConfigure.setApiKey(apiKey);

        wechatConfigure.setNotifyUrl(notifyUrl);
        wechatConfigure.setpVoucherFile(pVoucherFile);
        wechatConfigure.setPayreurl(payreurl);
        wechatConfigure.setToken(token);

        return wechatConfigure;
    }

}
