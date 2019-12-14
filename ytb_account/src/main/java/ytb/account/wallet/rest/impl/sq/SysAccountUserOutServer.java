package ytb.account.wallet.rest.impl.sq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.model.AccountUserInnerExample;
import ytb.account.wallet.model.AccountUserOut;
import ytb.account.wallet.model.AccountUserOutExample;
import ytb.account.wallet.pojo.LoginSsoToInnerId;

import ytb.account.wallet.service.AccountConst.UserOutConst;
import ytb.account.wallet.service.sq.basics.impl.AccountUserInnerServiceImpl;
import ytb.account.wallet.service.sq.basics.impl.AccountUserOutServiceImpl;
import ytb.account.wallet.service.service.getUserInfo.ObtainAccountId;

import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class SysAccountUserOutServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;


    /**
     * 获取所有外部账户信息
     */
    public MsgResponse accountOutInfo(MsgRequest req, MsgHandler handler,
                                      HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountUserOut data = JSONObject.parseObject(req.msgBody.toString(), AccountUserOut.class);
        AccountUserOutServiceImpl accountUserOutService = new AccountUserOutServiceImpl();
        AccountUserOutExample accountUserOutExample = new AccountUserOutExample();
        AccountUserOutExample.Criteria criteria = accountUserOutExample.createCriteria();
        criteria.andStatusEqualTo(UserOutConst.status_effective);

        if (data.getAccountType() != null) {
            criteria.andAccountTypeEqualTo(data.getAccountType());
        }


        String token = req.token;
        if (token != null) {
            //LoginSso loginSso = SafeContext.getLog_sso(token);
            if (loginSso != null) {
                //LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
                ObtainAccountId obtainAccountId = new ObtainAccountId();
                LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

                if (loginSsoToInnerId != null) {
                    criteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
                    criteria.andCompanyIdEqualTo(loginSsoToInnerId.getCompanyId());
                } else {
                    retcode = 1;
                    retmsg = "获取用户信息失败";
                    return null;
                }
                List<AccountUserOut> accountUserOutList = accountUserOutService.selectByExample(accountUserOutExample);
                msgBody = "{\"list\":" + JSONArray.toJSONString(accountUserOutList) + "}";
            }
        } else {
            retcode = 1;
            retmsg = "用户token获取失败";
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    /**
     * 获取所有外部账户信息
     */





    public MsgResponse accountInfo(MsgRequest req, MsgHandler handler,
                                   HttpServletRequest request, HttpServletResponse response) {

//        LoginSso loginSso = handler.getUserContext().getLoginSso();
//        LoginSsoJson loginSsoJson = new LoginSsoJson();

        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();
        //JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        if (loginSso != null) {
            if (loginSsoJson == null) {
                retcode = 1;
                retmsg = "获取账户信息失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
        }
//        String token = req.token;
//        LoginSso loginSso = SafeContext.getLog_sso(token);
//        LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();


        if (loginSsoJson.getUserType() != null) {

            ObtainAccountId obtainAccountId = new ObtainAccountId();
            LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

            if (loginSsoToInnerId != null) {
                criteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
                criteria.andCompanyIdEqualTo(loginSsoToInnerId.getCompanyId());
            } else {
                retcode = 1;
                retmsg = "获取用户信息转换失败-124";

            }


            criteria.andUserIdEqualTo(loginSsoJson.getUserId());
            AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();

            List<AccountUserInner> accountUserInners = accountUserInnerService.selectByExample(accountUserInnerExample);


            if (accountUserInners.size() > 0) {
                AccountUserOutServiceImpl accountUserOutService = new AccountUserOutServiceImpl();
                AccountUserOutExample accountUserOutExample = new AccountUserOutExample();
                AccountUserOutExample.Criteria accountUserOutExampleCriteria = accountUserOutExample.createCriteria();
//筛选用户
                accountUserOutExampleCriteria.andUserIdEqualTo(loginSsoJson.getUserId());


                AccountUserOut data = JSONObject.parseObject(req.msgBody.toString(), AccountUserOut.class);
                List<AccountUserOut> accountUserOutList = accountUserOutService.selectByExample(accountUserOutExample);

                accountUserInners.get(0).setBankCardsNumber(accountUserOutList.size());

                if (data.getAccountType() != null) {
                    accountUserOutExampleCriteria.andAccountTypeEqualTo(data.getAccountType());
                }
                accountUserOutExampleCriteria.andStatusEqualTo(UserOutConst.status_effective);

                accountUserOutList = accountUserOutService.selectByExample(accountUserOutExample);


                if (accountUserInners.get(0).getPayPassword().equals("0") || accountUserInners == null) {
                    accountUserInners.get(0).setPayPassword(null);
                } else {
                    accountUserInners.get(0).setPayPassword(null);
                }


            } else {

                retcode = 1;
                retmsg = "accountUserInners 获取失败 不存在的innerid 请生成" +
                        "可调用userInner-newUserInnerInfo 新增账户带有默认值(个人and公司通用)生成userid";
                return handler.buildMsg(retcode, retmsg, msgBody);

            }

            msgBody = "{\"list\":" + JSONObject.toJSONString(accountUserInners) + "}";


        } else {
            retcode = 1;
            retmsg = "用户类型获取失败";

        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    /**
     * 绑定新银行卡
     */
    public MsgResponse insert(MsgRequest req, MsgHandler handler,
                              HttpServletRequest request, HttpServletResponse response) {

        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountUserOut data = JSONObject.parseObject(req.msgBody.toString(), AccountUserOut.class);
        data.setCreateTime(new Date());

        ObtainAccountId obtainAccountId = new ObtainAccountId();
        LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

        if (loginSsoToInnerId != null) {
            data.setUserId(loginSsoToInnerId.getUserId());
            data.setCompanyId(loginSsoToInnerId.getCompanyId());
        } else {
            retcode = 1;
            retmsg = "获取用户信息失败";
            return null;
        }


        data.setUserId(loginSsoJson.getUserId());
        AccountUserOutServiceImpl service = new AccountUserOutServiceImpl();

        int sta = service.insertSelective(data);
        if (sta > 0) {
        } else {
            retcode = 1;
            retmsg = "数据错误";
        }
        return handler.buildMsg(retcode, retmsg, msgBody);


    }

    /**
     * 解绑银行卡
     */
    public MsgResponse deleteAccountInfo(MsgRequest req, MsgHandler handler,
                                         HttpServletRequest request, HttpServletResponse response) {
//        String token = req.token;
        //      LoginSso loginSso = SafeContext.getLog_sso(token);
        //    LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        AccountUserOut data = JSONObject.parseObject(req.msgBody.toString(), AccountUserOut.class);

        /*
        if (loginSsoJson.getUserType() != null) {
            if (loginSsoJson.getUserType().equals(1)) {
                data.setUserId(loginSsoJson.getUserId());
                data.setCompanyId(0);
            } else if (loginSsoJson.getUserType().equals(2)) {
                data.setUserId(0);
                data.setCompanyId(loginSsoJson.getUserId());
            } else {
                retcode = 1;
                retmsg = "未知的用户类型";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
        } else {
            retcode = 1;
            retmsg = "获取用户类型失败";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }
        */


        if (data.getOutId() != null ) {

            AccountUserOutServiceImpl service = new AccountUserOutServiceImpl();
            AccountUserOut accountUserOut = new AccountUserOut();
            AccountUserOutExample example = new AccountUserOutExample();
            AccountUserOutExample.Criteria criteria = example.createCriteria();

/*
            if (loginSsoJson.getUserType() != null) {
                if (loginSsoJson.getUserType().equals(1)) {
                    accountUserOut.setUserId(loginSsoJson.getUserId());
                    accountUserOut.setCompanyId(0);
                } else if (loginSsoJson.getUserType().equals(2)) {
                    accountUserOut.setUserId(0);
                    accountUserOut.setCompanyId(loginSsoJson.getUserId());
                } else {
                    retcode = 1;
                    retmsg = "未知的用户类型";
                    return handler.buildMsg(retcode, retmsg, msgBody);
                }
            } else {
                retcode = 1;
                retmsg = "获取用户类型失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
*/


            ObtainAccountId obtainAccountId = new ObtainAccountId();
            LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

            if (loginSsoToInnerId != null) {
                criteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
                criteria.andCompanyIdEqualTo(loginSsoToInnerId.getCompanyId());
            } else {

                retcode = 1;
                retmsg = "数据错误";
            }


            criteria.andOutIdEqualTo(data.getOutId());

            accountUserOut.setStatus((byte) 1);
            int sta = service.updateByExampleSelective(accountUserOut, example);
            if (sta > 0) {

            } else {
                retcode = 1;
                retmsg = "数据错误";
            }
        } else {
            retcode = 1;
            retmsg = "数据错误";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);


    }


}
