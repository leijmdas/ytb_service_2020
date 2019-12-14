package ytb.account.fastpay.service.Wechat;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import ytb.account.fastpay.model.fast.PayOrderGenerate;
import ytb.account.fastpay.model.fast.PaymentInformationModel;
import ytb.account.fastpay.model.fast.QrCodeRequest;
import ytb.account.fastpay.model.wechat.WechatConfigure;
import ytb.account.fastpay.model.wechat.WxPayUnifiedorderRequest;
import ytb.account.fastpay.util.HttpUtil;
import ytb.account.fastpay.util.MapUtil;
import ytb.account.fastpay.util.XmlUtil;
import ytb.account.fastpay.util.XmlUtils;

import javax.xml.parsers.ParserConfigurationException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/3/26
 */
public class WechatService {

    WechatConfigure wechatConfigure = null;
    public static final String WeChat = "WeChat";

    public WechatService(WechatConfigure configure) {
        wechatConfigure = configure;
    }

    public static Integer wechatCorrectYuan2Fen(BigDecimal yuan) {
        //（重点）Double直接转BigDecimal丢失精度，此处需要将Double转换为String
        return new BigDecimal(String.valueOf(yuan)).movePointRight(2).intValue();

    }

    public PaymentInformationModel qrCodePayWeChat(PayOrderGenerate orderGenerate) {


        //   WeChatPaymentFacility weChatPaymentFacility = new WeChatPaymentFacility(wechatConfigure);

        QrCodeRequest qrCodeModel = new QrCodeRequest();
        qrCodeModel.setBody(orderGenerate.getBody());
        qrCodeModel.setAttach(orderGenerate.getSubject());
        qrCodeModel.setOutTradeNo(orderGenerate.getOutTradeNo().toString());
        qrCodeModel.setSpBillCreateIp(orderGenerate.getIp());

        Integer fen = wechatCorrectYuan2Fen(orderGenerate.getTotalAmount());
        qrCodeModel.setTotalFee(fen);

        Map<String, String> urlCode = getRequest(qrCodeModel);

        if (urlCode != null) {

            PaymentInformationModel paymentInformation = new PaymentInformationModel();

            paymentInformation.setPaymentMethod(WeChat);
            paymentInformation.setQrCode(urlCode.get("code_url"));
            paymentInformation.setOutTradeNo(String.valueOf(orderGenerate.getOutTradeNo()));
            paymentInformation.setTotalAmount(orderGenerate.getTotalAmount());


            return paymentInformation;

        }

        return null;

    }

    public Map<String, String> getRequest(QrCodeRequest qrCodeRequest) {


        String requestXML = getXmlString(qrCodeRequest);
        Map<String, String> xmlData = null;

        try {
            xmlData = postData("https://" + "api.mch.weixin.qq.com"
                    + "/pay/unifiedorder", requestXML);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


        return xmlData;

    }

    public static Map<String, String> postData(String url, String requestXML) throws ParserConfigurationException {

        String result = HttpUtil.postData(url, requestXML);

        XmlUtils xmlUtil = new XmlUtils();
        Map<String, String> Xmldata = null;

        Xmldata = xmlUtil.XmlAnalysis(result);

        return Xmldata;
    }


    public String getXmlString(QrCodeRequest qrCodeRequest) {

        String requestXML = "";


        WxPayUnifiedorderRequest weChatQrCodeModel = new WxPayUnifiedorderRequest();

        weChatQrCodeModel.setAppid(wechatConfigure.getAppId());
        weChatQrCodeModel.setMchId(wechatConfigure.getMchId());
        weChatQrCodeModel.setNotifyUrl(wechatConfigure.getNotifyUrl());
        weChatQrCodeModel.setAttach(qrCodeRequest.getAttach());
        weChatQrCodeModel.setDeviceInfo(qrCodeRequest.getDeviceInfo());
        weChatQrCodeModel.setNonceStr(uuidHen());
        weChatQrCodeModel.setBody(qrCodeRequest.getBody());
        weChatQrCodeModel.setOutTradeNo(qrCodeRequest.getOutTradeNo());
        weChatQrCodeModel.setTotalFee(qrCodeRequest.getTotalFee());
        weChatQrCodeModel.setSpbillCreateIp(qrCodeRequest.getSpBillCreateIp());
        weChatQrCodeModel.setTradeType("NATIVE");
        weChatQrCodeModel.setSign(sign(MapUtil.buildMap(weChatQrCodeModel), wechatConfigure.getApiKey()));

        requestXML = XmlUtil.toString(weChatQrCodeModel);

        return requestXML;

    }


    public static String uuidHen() {
        String s = UUID.randomUUID().toString();
        String uuid = s.replace("-", "");
        return uuid;
    }

    public static String sign(Map<String, String> params, String signKey) {
        SortedMap<String, String> sortedMap = new TreeMap<>(params);

        StringBuilder toSign = new StringBuilder();
        for (String key : sortedMap.keySet()) {
            String value = params.get(key);
            if (StringUtils.isNotEmpty(value) && !"sign".equals(key) && !"key".equals(key)) {
                toSign.append(key).append("=").append(value).append("&");
            }
        }

        toSign.append("key=").append(signKey);
        return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }
}
