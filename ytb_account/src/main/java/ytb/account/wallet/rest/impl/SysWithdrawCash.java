package ytb.account.wallet.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.account.cashpay.alipay.model.ComeTransferAccountsModel;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.pojo.LoginSsoToInnerId;
import ytb.account.wallet.rest.impl.sq.SysAccountUserInnerServer;
import ytb.account.wallet.service.service.getUserInfo.ObtainAccountId;
import ytb.account.wallet.service.sq.business.pf.account.impl.AccountTradePfServiceImpl;
import ytb.account.wallet.service.sq.business.user.impl.ProjecTransactionServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * 提现
 * <p>
 * withdrawCash 提现审核申请
 * withdrawCashAdopt提现审核通过后的操作
 * <p>
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/19
 */
public class SysWithdrawCash {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;


    /**
     * 提现审核申请
     * <p>
     * 会操作inner 并冻结金额 同时出账总计++
     * <p>
     * 个人detail 会记账
     */


    public MsgResponse withdrawCash(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        try {
            AccountTrade data = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);
            if (data.getBalance().compareTo(BigDecimal.ZERO) == 0) {
                retcode = 1;
                retmsg = "提交审核失败 金额不得为0";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }


            if (loginSso != null) {

                SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

                AccountUserInner accountUserInnerList = sysAccountUserInnerServer.getInnerIdByUser(loginSsoJson);

                if (accountUserInnerList.getPayPassword().equals(req.msgBody.get("password")) == false) //拦截密码
                {
                    retcode = 1;
                    retmsg = "密码错误";
                    return handler.buildMsg(retcode, retmsg, msgBody);
                }

                if (accountUserInnerList != null) {

                    data.setAcId(accountUserInnerList.getInnerId());

                    ObtainAccountId obtainAccountId = new ObtainAccountId();
                    LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

                    if (loginSsoToInnerId != null) {
                        data.setUserId(loginSsoToInnerId.getUserId());
                        data.setCompanyId(loginSsoToInnerId.getCompanyId());
                    } else {
                        retcode = 1;
                        retmsg = "用户类型获取失败";
                        return null;
                    }


                    ProjecTransactionServiceImpl accountTradeService = new ProjecTransactionServiceImpl();
                    int sta = accountTradeService.withdrawCash(data, accountUserInnerList.getInnerId(), data.getBalance());


                    if (sta > 0) {

                        retcode = 0;
                        retmsg = "成功";
                        //审核
                    } else {
                        retcode = 1;
                        retmsg = "提交审核失败";
                    }
                } else {
                    retcode = 1;
                    retmsg = "获取用户信息失败";
                }

            }


        } catch (Exception e) {
            retcode = 1;
            retmsg = "Error";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    /**
     * 提现申请通过操作
     */
    public Boolean withdrawCashAdopt(ComeTransferAccountsModel data) {

        boolean ts = false;
        AccountTradePfServiceImpl accountTradeService = new AccountTradePfServiceImpl();
        int sta = accountTradeService.withdrawCashAdopt(data);

        if (sta > 0) {
            ts = true;
        } else {
            ts = false;
        }

        return ts;
    }


}
