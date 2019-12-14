package ytb.account.fastpay.service.Alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import ytb.account.fastpay.model.alipay.AliPayConfigure;
import ytb.account.fastpay.model.alipay.AliPayTradePrecreate;
import ytb.account.fastpay.model.alipay.AliPayTradePrecreatePayModel;
import ytb.account.fastpay.model.fast.PayOrderGenerate;
import ytb.account.fastpay.model.fast.PaymentInformationModel;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/3/26
 */
public class AlipayService {

    static AliPayConfigure aliPayConfigure = null;

    public AlipayService(AliPayConfigure configure) {
        aliPayConfigure = configure;
    }


    public static final String AliPay = "AliPay";


    public PaymentInformationModel qrCodeAliPay(PayOrderGenerate orderGenerate) {


        AliPayTradePrecreatePayModel paymentifo = new AliPayTradePrecreatePayModel();

        String pycode = null;


        paymentifo.setOut_trade_no(orderGenerate.getOutTradeNo().toString());
        paymentifo.setTotal_amount(orderGenerate.getTotalAmount());
        paymentifo.setBizContent(orderGenerate.getBody());
        paymentifo.setSubject(orderGenerate.getSubject());

        AliPayTradePrecreate toaccountTransfer = aliPayTradePrecreate(paymentifo);
        if (toaccountTransfer != null) {

            pycode = toaccountTransfer.getAlipay_trade_precreate_response().getQrCode();

            PaymentInformationModel paymentInformation = new PaymentInformationModel();
            paymentInformation.setPaymentMethod(AliPay);
            paymentInformation.setQrCode(pycode);
            paymentInformation.setOutTradeNo(orderGenerate.getOutTradeNo().toString());
            paymentInformation.setTotalAmount(orderGenerate.getTotalAmount());

            return paymentInformation;
        }
        return null;
    }


    public static AliPayTradePrecreate aliPayTradePrecreate(AliPayTradePrecreatePayModel aliPayTradePrecreatePayModel) {


        AliPayTradePrecreate pycode = null;
        try {


            Map<String, Object> bizContent = tradePrecreatePayModel(aliPayTradePrecreatePayModel);
            pycode = getAliPayTradePrecreate(bizContent, aliPayConfigure);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pycode;

    }


    public static Map<String, Object> tradePrecreatePayModel(AliPayTradePrecreatePayModel aliPayTradePrecreatePayModel) {
        Map<String, Object> bizContent = new HashMap<String, Object>();


        //        passbackParams = java.net.URLEncoder.encode(aliPayWapPayModel.getPassback_params(), "UTF-8");


        bizContent.put("out_trade_no", aliPayTradePrecreatePayModel.getOut_trade_no());
        bizContent.put("total_amount", aliPayTradePrecreatePayModel.getTotal_amount().setScale(2, RoundingMode.HALF_UP).doubleValue());
        bizContent.put("subject", aliPayTradePrecreatePayModel.getSubject());
        bizContent.put("bizContent", aliPayTradePrecreatePayModel.getBizContent());
        bizContent.put("timeout_express", aliPayTradePrecreatePayModel.getTimeout_express());


        return bizContent;
    }


    public static AliPayTradePrecreate getAliPayTradePrecreate(Map<String, Object> bizContent, AliPayConfigure aliPayConfigure) throws IOException {

        AlipayClient alipayClient = aliPayClientConfig(aliPayConfigure);

        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();//创建API对应的request类

        alipayRequest.setBizContent(JSONObject.toJSON(bizContent).toString());
        alipayRequest.setReturnUrl(aliPayConfigure.getReturnUrl());
        alipayRequest.setNotifyUrl(aliPayConfigure.getNotifyUrl());// 在公共参数中设置回跳和通知地址777

        try {

            AlipayTradePrecreateResponse response = alipayClient.execute(alipayRequest);

            if (response.getCode().equals("10000")) {
                System.out.println(response.getMsg());

                System.out.println("二维码串 = " + response.getQrCode());
                AliPayTradePrecreate transfer = JSONObject.parseObject(response.getBody(), AliPayTradePrecreate.class);


                return transfer;
            } else {
                return null;
            }


        } catch (AlipayApiException e) {

            e.printStackTrace();

        }
        return null;
    }

    public static AlipayClient aliPayClientConfig(AliPayConfigure aliPayConfigure) {
        // AliPayConfigure aliPayConfigure = new AliPayConfigure();
        AlipayClient alipayClient = new DefaultAlipayClient(
                aliPayConfigure.getURL(),
                aliPayConfigure.getAppId(),
                aliPayConfigure.getAppPrivateKey(),
                aliPayConfigure.getFormat(),
                aliPayConfigure.getCharset(),
                aliPayConfigure.getAlipayPublicKey(),
                aliPayConfigure.getSignType()); // 获得初始化的AlipayClient
        return alipayClient;
    }
}
