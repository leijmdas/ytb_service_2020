package ytb.account.cashpay.integration.service;



import ytb.account.cashpay.integration.model.IntegratedPayModel;
import ytb.account.cashpay.alipay.pojo.AliPayTransferAccounts;
import ytb.account.fastpay.model.fast.PaymentInformationModel;


public interface IntegratedPay {

    public static final String WeChat = "WeChat";

    public static final String AliPay = "AliPay";


    /**
     * 微信./支付宝下单二维码生成
     */


    PaymentInformationModel qrCodePay(IntegratedPayModel integratedPay, String mode);


    /**
     * 支付宝转账
     */

    String transferFacility(AliPayTransferAccounts aliPayRefundModel);


}
