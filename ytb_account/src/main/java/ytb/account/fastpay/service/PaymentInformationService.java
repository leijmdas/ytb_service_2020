package ytb.account.fastpay.service;


import ytb.account.fastpay.model.alipay.AliPayConfigure;
import ytb.account.fastpay.model.fast.PayOrderGenerate;
import ytb.account.fastpay.model.fast.PaymentInformationModel;
import ytb.account.fastpay.model.wechat.WechatConfigure;
import ytb.account.fastpay.service.Alipay.AlipayService;
import ytb.account.fastpay.service.Wechat.WechatService;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class PaymentInformationService implements PaymentInformation {
    public static final String WeChat = "WeChat";

    public static final String AliPay = "AliPay";

    AliPayConfigure aliPayConfigure = null;

    WechatConfigure wechatConfigure = null;

    public PaymentInformationService(WechatConfigure configure) {
        wechatConfigure = configure;
    }

    public PaymentInformationService(AliPayConfigure configure) {
        aliPayConfigure = configure;
    }

    public PaymentInformationService(AliPayConfigure aliPay, WechatConfigure weChat) {
        aliPayConfigure = aliPay;
        wechatConfigure = weChat;
    }

    public PaymentInformationService(WechatConfigure weChat, AliPayConfigure aliPay) {
        aliPayConfigure = aliPay;
        wechatConfigure = weChat;

    }


    @Override
    public PaymentInformationModel qrCodePay(PayOrderGenerate orderGenerate, String mode) {

        if (mode.equals(AliPay)) {
            if (aliPayConfigure != null) {
                AlipayService alipayService = new AlipayService(aliPayConfigure);
                return alipayService.qrCodeAliPay(orderGenerate);
            } else return null;
        } else if (mode.equals(WeChat)) {
            if (wechatConfigure != null) {
                WechatService wechatService = new WechatService(wechatConfigure);
                return wechatService.qrCodePayWeChat(orderGenerate);
            } else return null;
        }
        return null;

    }


    /**
     * -------------------------
     */


}
