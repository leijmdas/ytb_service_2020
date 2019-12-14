package ytb.account.wallet.rest.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ytb.account.cashpay.integration.model.IntegratedPayModel;
import ytb.account.cashpay.integration.service.IntegratedPay;
import ytb.account.cashpay.integration.service.db.AccountTradeOutService;
import ytb.account.cashpay.integration.service.db.impl.AccountTradeOutServiceImpl;
import ytb.account.cashpay.integration.service.impl.IntegratedPayService;

import ytb.account.fastpay.model.fast.PaymentInformationModel;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountTradeOut;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.service.getUserInfo.GetUserInfo;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Date;

/**
 * 充值
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/19
 */
public class SysRechargeServer {

    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;


    public MsgResponse accountIncrease(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {

        AccountTrade come = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);
        AccountUserInner accountUserInnerList = GetUserInfo.loginSsoJsonToAccountUserInner(req, handler);
        LoginSsoJson loginSsoJson = GetUserInfo.getLoginSsoJson(handler);


        if (accountUserInnerList == null) {
            retcode = 1;
            retmsg = "失败 accountUserInnerList 获取失败";

            return handler.buildMsg(retcode, retmsg, msgBody);
        }


        if (loginSsoJson == null) {
            retcode = 1;
            retmsg = "失败 loginSsoJson 获取失败";

            return handler.buildMsg(retcode, retmsg, msgBody);
        }



         /*   AccountUserDetail accountUserDetail = new AccountUserDetail();
            accountUserDetail.setInnerId(accountUserInnerList.getInnerId());
            accountUserDetail.setBalance(come.getBalance());
            accountUserDetail.setInTime(new Date());*/

        //    AccountTrade accountTrade = new AccountTrade();


        come.setUserId(loginSsoJson.getUserId());
        come.setCompanyId(loginSsoJson.getCompanyId());
        come.setCreateBy(accountUserInnerList.getUserId());
        come.setToAcId(accountUserInnerList.getInnerId());

         /*       accountTrade.setTradeType(TradeConst.TRADE_TYPE_RECHARGE);//充值
            accountTrade.setStatus(TradeConst.status_passed);//通过
            accountTrade.setCreateTime(new Date());
            accountTrade.setTradeSubtype(come.getTradeSubtype());
            accountTrade.setBalance(come.getBalance());
            accountTrade.setCalFlag(AccountConst.cal_flag_no);*/
        come.setIpAddress(getIpAddress(request));

          /*  AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();

            int outTradeNo = accountTradeService.insertSelective(accountTrade);


            PaymentInformationModel paymentInformation = getPaymentInformation(accountTrade, outTradeNo);
*/

        PaymentInformationModel paymentInformation = structureTradePayment(come);


        if (paymentInformation != null) {
            retcode = 0;
            retmsg = "成功返回 充值方式为：" + come.getTradeSubtype().toString();
            msgBody = "{\"list\":" + JSONArray.toJSONString(paymentInformation) + "}";
        } else {
            retcode = 1;
            retmsg = "失败";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public static PaymentInformationModel structureTradePayment(AccountTrade come) {

        AccountTrade accountTrade = new AccountTrade();
        Date time = new Date();

        accountTrade.setUserId(come.getUserId());
        accountTrade.setCompanyId(come.getCompanyId());
        accountTrade.setCreateBy(come.getUserId());
        accountTrade.setToAcId(come.getToAcId());
        accountTrade.setAcId(come.getToAcId());

        accountTrade.setTradeType(TradeConst.TRADE_TYPE_RECHARGE);//充值
        accountTrade.setTradeSubtype(come.getTradeSubtype());
        accountTrade.setServiceType(TradeConst.SERVICE_TYPE_cash);

        accountTrade.setStatus(TradeConst.status_passed);//通过
        accountTrade.setCreateTime(time);
        accountTrade.setAddTime(time);
        accountTrade.setTermId(intDate(time));

        accountTrade.setBalance(come.getBalance());
        accountTrade.setCalFlag(AccountConst.cal_flag_no);
        accountTrade.setIpAddress(come.getIpAddress());
        accountTrade.setTotalBalance(accountTrade.getBalance());
        AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();

        int outTradeNo = accountTradeService.insertSelective(accountTrade);


        PaymentInformationModel paymentInformation = getPaymentInformation(accountTrade, outTradeNo);
        return paymentInformation;

    }


    private static Integer intDate(Date dt) {

        String s = DateFormat.getDateInstance(DateFormat.DEFAULT).format(dt).replaceAll("-", "");
        return Integer.parseInt(s);
    }


    private static PaymentInformationModel getPaymentInformation(AccountTrade accountTrade, int outTradeNo) {
        // String payMethod = null;
        PaymentInformationModel paymentInformation = null;
        try {


            IntegratedPayModel integratedPay = new IntegratedPayModel();
            integratedPay.setTotalAmount(accountTrade.getBalance());
            integratedPay.setOutTradeNo(Long.valueOf(outTradeNo));
            integratedPay.setBody("Ttb充值");
            integratedPay.setSubject("ytb充值 -" + outTradeNo);
            integratedPay.setIp(accountTrade.getIpAddress());

            IntegratedPay payService = new IntegratedPayService();

            if (accountTrade.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_BANK) {
                //    payMethod = "银行";
            } else if (accountTrade.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_WE_CHAT) {
                //   payMethod = "微信";
                paymentInformation = payService.qrCodePay(integratedPay, payService.WeChat);

            } else if (accountTrade.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_ALIPAY) {
                //     payMethod = "支付宝";
                paymentInformation = payService.qrCodePay(integratedPay, payService.AliPay);

            } else {
                return null;
            }


            AccountTradeOutService accountTradeService = new AccountTradeOutServiceImpl();
            AccountTradeOut accountTradeOut = new AccountTradeOut();
            accountTradeOut.setQrCode(paymentInformation.getQrCode());
            accountTradeOut.setBody(integratedPay.getBody());
            accountTradeOut.setSubject(integratedPay.getSubject());
            accountTradeOut.setPayModel(String.valueOf(accountTrade.getTradeSubtype()));
            accountTradeOut.setTotalAmount(accountTrade.getBalance());
            accountTradeOut.setBuyerId(String.valueOf(accountTrade.getAcId()));
            accountTradeOut.setTradeId(String.valueOf(accountTrade.getTradeId()));
            accountTradeService.insertSelective(accountTradeOut);

            return paymentInformation;
        } catch (Exception e) {
            return null;
        }
    }


    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
