package ytb.account.wallet.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.account.wallet.model.*;
import ytb.account.wallet.rest.impl.sq.SysAccountUserInnerServer;
import ytb.account.wallet.service.sq.business.pf.account.impl.AccountTradePfServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户验证支付密码 消费金额并且记录
 */
public class SysAccountPfRechargeService {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;


    public MsgResponse transfer(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();

        AccountTrade data = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);
        data.setTradeType((short) 105);


        //LoginSso loginSso = SafeContext.getLog_sso(req.token);
        if (loginSso != null) {


            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();


            AccountTradePfServiceImpl accountPfTradeService = new AccountTradePfServiceImpl();
            data.setAcId(data.getToAcId());

            int sta = accountPfTradeService.transferService(data, "2");

            if (sta > 0) {
                //流水
            } else {
                retcode = 1;
                retmsg = "Error";
            }


        } else {
            retcode = 1;
            retmsg = "Error";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }
}
