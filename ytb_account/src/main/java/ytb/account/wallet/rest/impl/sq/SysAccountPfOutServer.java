package ytb.account.wallet.rest.impl.sq;

import com.alibaba.fastjson.JSONObject;
import ytb.account.wallet.model.AccountPfOut;
import ytb.account.wallet.model.AccountPfOutExample;
import ytb.account.wallet.service.sq.basics.impl.AccountPfOutServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SysAccountPfOutServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse select(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {


        AccountPfOut data = JSONObject.parseObject(req.msgBody.toString(), AccountPfOut.class);


        AccountPfOutServiceImpl accountPfOutService = new AccountPfOutServiceImpl();
        AccountPfOutExample accountUserOutExample = new AccountPfOutExample();

        AccountPfOutExample.Criteria criteria = accountUserOutExample.createCriteria();

        if (data.getPfOutId() != null) {
            criteria.andPfOutIdEqualTo(data.getPfOutId());
        }
        if (data.getAccountType() != null) {
            criteria.andAccountTypeEqualTo(data.getAccountType());
        }

        if (data.getAccountNo() != null) {
            criteria.andAccountNoEqualTo(data.getAccountNo());
        }

        if (data.getMobileNo() != null) {
            criteria.andMobileNoEqualTo(data.getMobileNo());
        }

        if (data.getStatus() != null) {
            criteria.andStatusEqualTo(data.getStatus());
        }

        if (data.getIsAgent() != null) {
            criteria.andIsAgentEqualTo(data.getIsAgent());
        }


        List<AccountPfOut> accountPfOuts = accountPfOutService.selectByExample(accountUserOutExample);

        msgBody = "{\"list\":" + JSONObject.toJSONString(accountPfOuts) + "}";
        return handler.buildMsg(retcode, retmsg, msgBody);

    }


    public MsgResponse insert(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        //LoginSso loginSso = handler.getUserContext().getLoginSso();
        //LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();

        AccountPfOut loginSsoJson = JSONObject.parseObject(req.msgBody.toString(), AccountPfOut.class);


        AccountPfOutServiceImpl accountPfOutService = new AccountPfOutServiceImpl();

        int sta = accountPfOutService.insertSelective(loginSsoJson);

        if (sta > 0) {

        } else {
            retcode = 1;
            retmsg = "数据错误";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);

    }


    public Boolean deleteById(AccountPfOut data) {


        AccountPfOutServiceImpl accountPfOutService = new AccountPfOutServiceImpl();

        int sta = accountPfOutService.deleteByPrimaryKey(data.getPfOutId());


        if (sta > 0) {
            return true;
        } else {
            return false;
        }


    }


    public Boolean prohibitOutInfo(AccountPfOut data) {
//        String token = req.token;
//
//        LoginSso loginSso = SafeContext.getLog_sso(token);

        //   LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);


        AccountPfOutServiceImpl accountPfOutService = new AccountPfOutServiceImpl();

        AccountPfOut accountPfOut = new AccountPfOut();
        accountPfOut.setPfOutId(data.getPfOutId());
        accountPfOut.setStatus(data.getStatus());

        int sta = accountPfOutService.updateByPrimaryKeySelective(accountPfOut);


        if (sta > 0) {
            return true;
        } else {
            return false;
        }

    }
}
