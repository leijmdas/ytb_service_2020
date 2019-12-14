package ytb.account.fastpay.service.Alipay;

import com.alibaba.fastjson.JSONObject;
import ytb.account.cashpay.alipay.model.AccountAlipayBusiness;
import ytb.account.wallet.model.AccountTradeOut;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/3/15
 */
public class AlipayNotifySerivce {
    public AccountAlipayBusiness notify(HttpServletRequest request) {
//后续要加上验签！

        AccountAlipayBusiness param = null;

        try {

            param = verifyingSignature(request, AccountAlipayBusiness.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;

    }


    public static <T> T verifyingSignature(HttpServletRequest request, Class<T> clazz) {

        Map<String, String> params = null;

        if (request != null) {
            params = notifyUrlss(request);
            if (params != null) {
                return buildAliPayNotifyParam(params, clazz);
            }
        }

        return null;
    }

    public static Map<String, String> notifyUrlss(HttpServletRequest request) {


        Map<String, String> params = new HashMap<>();

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            params.put(parameterName, request.getParameter(parameterName));
        }


        return params;


    }


    public static <T> T buildAliPayNotifyParam(Map<String, String> params, Class<T> clazz) {
        String json = JSONObject.toJSONString(params);
        try {
            return JSONObject.parseObject(json, clazz);
        } catch (Exception e) {
            return null;
        }

    }

    public static AccountTradeOut busToTradeOut(AccountAlipayBusiness bs) {

        AccountTradeOut accountTradeOut = new AccountTradeOut();
        accountTradeOut.setPayModel("AliPay");
        accountTradeOut.setTotalAmount(bs.getTotalAmount());
        accountTradeOut.setPassbackParams(bs.getPassbackParams());
        accountTradeOut.setSubject(bs.getSubject());
        accountTradeOut.setBody(bs.getBody());
        accountTradeOut.setOutTradeNo(bs.getTradeNo());
        accountTradeOut.setOrderState(bs.getTradeStatus());
        accountTradeOut.setBuyerId(bs.getBuyerId());
        accountTradeOut.setSellerId(bs.getSellerId());
        accountTradeOut.setGmtCreate(bs.getGmtCreate());
        accountTradeOut.setTradeId(bs.getOutTradeNo());
        return accountTradeOut;
    }
}
