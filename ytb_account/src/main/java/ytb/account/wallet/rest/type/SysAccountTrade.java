package ytb.account.wallet.rest.type;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.pojo.Paging;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.rest.impl.SysAccountTradeServer;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class SysAccountTrade {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {


        //获取主表中的所有记录
        if (req.cmd.equals("accountTrade")) {

            AccountTrade data = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);
            //String token = req.token;
            LoginSso loginSso = handler.getUserContext().getLoginSso();//SafeContext.getLog_sso(token);

            if (loginSso != null) {
                LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
/*
            AccountUserInner accountUserInner = getInnerIdByTrade(loginSsoJson);*/

                if (loginSsoJson.getCompanyId() != null) {
                    if (loginSsoJson.getCompanyId() == 0 & loginSsoJson.getUserId() > 0) {
                        data.setUserId(loginSsoJson.getUserId());
                        data.setCompanyId(0);
                    }
                    if (loginSsoJson.getCompanyId() > 0 & loginSsoJson.getUserId() > 0) {
                        data.setUserId(0);
                        data.setCompanyId(loginSsoJson.getCompanyId());
                    } else {
                        data.setCompanyId(0);
                        data.setUserId(0);
                    }
                } else {
                    data.setUserId(0);
                }


            } else {
                retcode = 0;
                retmsg = "获取token信息失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }


            SysAccountTradeServer server = new SysAccountTradeServer();
            Paging paging = structurePaging(req);
            PageUtils list = server.selectAndProject(data, paging);


            msgBody = "{\"list\":" + JSONArray.toJSONString(list) + "}";

////
        } else if (req.cmd.equals("getTradeById")) {


            /// msgBody = "{\"list\":" + JSONArray.toJSONString(list) + "}";
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    //获取主表中的所有记录


    Paging structurePaging(MsgRequest req) {
        Paging paging = new Paging();
        if (
                req.msgBody.getInteger("currPage") != null &
                        req.msgBody.getInteger("pageSize") != null &
                        req.msgBody.getString("toOrder") != null &
                        req.msgBody.getString("orderBy") != null
        ) {
            paging.setCurrPage(req.msgBody.getInteger("currPage"));
            paging.setPageSize(req.msgBody.getInteger("pageSize"));
            paging.setToOrder(req.msgBody.getString("toOrder"));
            paging.setOrderBy(req.msgBody.getString("orderBy"));
            return paging;
        } else {
            return null;
        }

    }

}



