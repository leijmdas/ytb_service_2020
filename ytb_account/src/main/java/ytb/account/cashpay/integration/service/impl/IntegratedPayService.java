package ytb.account.cashpay.integration.service.impl;


import ytb.account.cashpay.integration.model.IntegratedPayModel;
import ytb.account.cashpay.alipay.pojo.AliPayTransferAccounts;

import ytb.account.cashpay.alipay.service.pay.impl.AlipayServiceImpl;
import ytb.account.cashpay.integration.config.AliPayConfigureTodo;
import ytb.account.cashpay.integration.config.WechatConfigureTodo;
import ytb.account.cashpay.integration.service.IntegratedPay;
import ytb.account.fastpay.model.alipay.AliPayConfigure;
import ytb.account.fastpay.model.fast.PayOrderGenerate;
import ytb.account.fastpay.model.fast.PaymentInformationModel;
import ytb.account.fastpay.model.wechat.WechatConfigure;
import ytb.account.fastpay.service.PaymentInformation;
import ytb.account.fastpay.service.PaymentInformationService;
import ytb.common.context.model.YtbError;


public class IntegratedPayService implements IntegratedPay {

    public final String WeChat = "WeChat";

    public final String AliPay = "AliPay";

    AliPayConfigure aliPayConfigure = new AliPayConfigureTodo().getConfig();

    WechatConfigure wechatConfigure = new WechatConfigureTodo().getconfig();


    /**
     * 微信./支付宝下单二维码生成
     */


    @Override
    public PaymentInformationModel qrCodePay(IntegratedPayModel integratedPay, String mode) {

        try {

            PaymentInformation paymentInformationService = null;
            PayOrderGenerate payOrderGenerate = new PayOrderGenerate();
            payOrderGenerate.setTotalAmount(integratedPay.getTotalAmount());
            payOrderGenerate.setOutTradeNo(integratedPay.getOutTradeNo());
            payOrderGenerate.setBody(integratedPay.getBody());
            payOrderGenerate.setSubject(integratedPay.getSubject());
            payOrderGenerate.setPaymentMethod(mode);
            payOrderGenerate.setIp(integratedPay.getIp());

            if (mode.equals(AliPay)) {
                AliPayConfigure config = new AliPayConfigureTodo().getConfig();
                paymentInformationService = new PaymentInformationService(config);
            }
            if (mode.equals(WeChat)) {
                WechatConfigure config = new WechatConfigureTodo().getconfig();
                paymentInformationService = new PaymentInformationService(config);
            }

            PaymentInformationModel paymentInformation = paymentInformationService.qrCodePay(payOrderGenerate, mode);
            if (paymentInformation == null) {
                throw new YtbError("获取二维码失败！");
            }

            return paymentInformation;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 阿里对账
     */


    /**
     * 支付宝转账
     */

    @Override
    public String transferFacility(AliPayTransferAccounts aliPayRefundModel) {

        return new AlipayServiceImpl(aliPayConfigure).transferFacility(aliPayRefundModel);

    }


}
