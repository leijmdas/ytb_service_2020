package ytb.account.cashpay.alipay.controller;


import com.alibaba.fastjson.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import ytb.account.cashpay.alipay.pojo.AliPayTransferAccounts;
import ytb.account.cashpay.alipay.service.pay.AlipayService;

import ytb.account.cashpay.alipay.service.pay.impl.AlipayServiceImpl;
import ytb.account.cashpay.integration.config.AliPayConfigureTodo;

import ytb.account.fastpay.model.alipay.AliPayConfigure;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;


@RestController
@Scope("prototype")
@RequestMapping(value = "/alipay", produces = "application/json; charset=utf-8")
public class AliPayPaymentController {


    AliPayConfigure aliPayConfigure = new AliPayConfigureTodo().getConfig();


    @RequestMapping(value = "/callback/return", method = RequestMethod.POST)
    @ResponseBody

    public String returnurl(@RequestBody String CommodityInfo) {

        // System.out.println(CommodityInfo);

        return CommodityInfo;

    }

    @RequestMapping(value = "/returnUrl", method = RequestMethod.POST)
    @ResponseBody

    public String returnUrl(@RequestBody String returnUrl) {

        //System.out.println(returnUrl);

        return returnUrl;

    }


    //授权回调地址：http://www.youtobon.com/alipay/authRedirect
    @RequestMapping(value = "/authRedirect", method = RequestMethod.POST)
    @ResponseBody

    public String authRedirect(@RequestBody String CommodityInfo) {

        //System.out.println(CommodityInfo);

        return CommodityInfo;

    }




    @RequestMapping(value = "/getToaccountTransResponse", method = RequestMethod.POST)
    @ResponseBody
    public String AliPayToaccountTransferFacility(@RequestBody String data, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {


        AliPayTransferAccounts aliPayRefundModel = JSONObject.parseObject(data, AliPayTransferAccounts.class);


        long asd = 123;

        aliPayRefundModel.setOutBizNo(String.valueOf(asd));
        aliPayRefundModel.setPayeeAccount("13528818072");
        aliPayRefundModel.setAmount(new BigDecimal(0.1));
        aliPayRefundModel.setPayerShowName("YTB提现");
        aliPayRefundModel.setPayeeRealName("陈重华");
        aliPayRefundModel.setRemark("备注");


        AlipayService aliPayService = new AlipayServiceImpl(aliPayConfigure);

        String from = aliPayService.transferFacility(aliPayRefundModel);


        return "";


    }



}
