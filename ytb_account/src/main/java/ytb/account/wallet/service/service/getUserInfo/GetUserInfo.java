package ytb.account.wallet.service.service.getUserInfo;

import com.alibaba.fastjson.JSONObject;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.rest.impl.sq.SysAccountUserInnerServer;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/19
 */
public class GetUserInfo {


    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;


    public static AccountUserInner loginSsoJsonToAccountUserInner(MsgRequest req, MsgHandler handler) {
        try {


            AccountUserInner accountUserInner = null;
            LoginSso loginSso = handler.getUserContext().getLoginSso();
            LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();
            AccountTrade accountTrade = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);


            //String token = req.token;
            if (req.token != null & accountTrade.getTradeSubtype() != null & accountTrade.getBalance() != null) {
                //LoginSso loginSso = SafeContext.getLog_sso(token);
                if (loginSso != null) {
                    //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

                    if (loginSsoJson.getUserType() != null) {

                        SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();
                        accountUserInner = sysAccountUserInnerServer.getInnerIdByUser(loginSsoJson);

                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
            return accountUserInner;
        } catch (Exception e) {
            return null;
        }
    }


    public static LoginSsoJson getLoginSsoJson(MsgHandler handler) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();
        return loginSsoJson;
    }


}
