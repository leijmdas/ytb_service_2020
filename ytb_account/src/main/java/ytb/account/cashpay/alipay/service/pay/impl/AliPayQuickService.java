package ytb.account.cashpay.alipay.service.pay.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;


import ytb.account.cashpay.alipay.pojo.AliPayTransferAccounts;
import ytb.account.fastpay.model.alipay.AliPayConfigure;
import ytb.account.fastpay.model.alipay.AliPayFundTransToaccountTransfer;
import ytb.account.fastpay.model.alipay.AlipayToaccountTransferModel;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class AliPayQuickService {


    public String transferFacility(AliPayTransferAccounts aliPayRefundModel, AliPayConfigure aliPayConfigure) {


        AlipayToaccountTransferModel alipayToaccountTransferModel = new AlipayToaccountTransferModel();
        alipayToaccountTransferModel.setPayee_real_name(aliPayRefundModel.getPayeeRealName());
        alipayToaccountTransferModel.setAmount(aliPayRefundModel.getAmount());
        alipayToaccountTransferModel.setOut_biz_no(aliPayRefundModel.getOutBizNo());
        alipayToaccountTransferModel.setPayee_account(aliPayRefundModel.getPayeeAccount());
        alipayToaccountTransferModel.setRemark(aliPayRefundModel.getRemark());
        alipayToaccountTransferModel.setPayee_type(aliPayRefundModel.getPayeeType());
        alipayToaccountTransferModel.setPayer_show_name(aliPayRefundModel.getPayerShowName());


        AliPayFundTransToaccountTransfer from = response(alipayToaccountTransferModel, aliPayConfigure);


        AlipayFundTransToaccountTransferResponse response = from.getAlipay_fund_trans_toaccount_transfer_response();
        if (response.getCode().equals("10000") & response.getMsg().equals("Success")) {
            return "{\"order_id\":\"" + response.getOrderId() + "\",\"out_biz_no\":\"" + response.getOutBizNo() + "\"}";
        } else {
            return "";
        }


    }


    public AliPayFundTransToaccountTransfer response(AlipayToaccountTransferModel model, AliPayConfigure aliPayConfigure) {

        AliPayFundTransToaccountTransfer transfer = null;
        try {


            Map<String, Object> bizContent = getToaccountTransferModel(model);

            String aliPayToaccountTrans = AliPayToaccountTrans(bizContent, aliPayConfigure, Response);


            transfer = JSONObject.parseObject(aliPayToaccountTrans, AliPayFundTransToaccountTransfer.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return transfer;

    }


    public static final String Response = "response";
    public static final String Request = "request";


    public String AliPayToaccountTrans(Map<String, Object> bizContent, AliPayConfigure aliPayConfigure, String callMode) throws IOException {

//Response

        AlipayClient alipayClient = aliPayClientConfig(aliPayConfigure);


        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();

        request.setReturnUrl(aliPayConfigure.getReturnUrl());
        request.setNotifyUrl(aliPayConfigure.getNotifyUrl());// 在公共参数中设置回跳和通知地址
        request.setBizContent(JSONObject.toJSON(bizContent).toString());

        String form = "";
        if (callMode == Response) {
            try {
                AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);


                if (response.isSuccess()) {
                    form = response.getBody();

                    AliPayFundTransToaccountTransfer transfer = JSONObject.parseObject(form, AliPayFundTransToaccountTransfer.class);
                    //System.out.println(transfer);
                    //    System.out.println("调用成功");
                } else {
                    form = response.getBody();
                    //System.out.println("调用失败");
                }

            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
        } else if (callMode == Request) {
            try {
                form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
        }

        return form;

    }


    public Map<String, Object> getToaccountTransferModel(AlipayToaccountTransferModel model) {
        Map<String, Object> bizContent = new HashMap<String, Object>();
        model.setPayee_type("ALIPAY_LOGONID");

        bizContent.put("out_biz_no", model.getOut_biz_no());
        bizContent.put("payee_type", model.getPayee_type());
        bizContent.put("payee_account", model.getPayee_account());
        bizContent.put("amount", model.getAmount().setScale(2, RoundingMode.HALF_UP).doubleValue());
        bizContent.put("payer_show_name", model.getPayer_show_name());
        bizContent.put("payee_real_name", model.getPayee_real_name());
        bizContent.put("remark", model.getRemark());


        return bizContent;
    }


    public AlipayClient aliPayClientConfig(AliPayConfigure aliPayConfigure) {
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
