package ytb.account.wallet.rest.impl.sq;

import com.alibaba.fastjson.JSONObject;
import ytb.account.wallet.model.*;
import ytb.account.wallet.service.sq.basics.impl.AccountPfInnerServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SysAccountPfInnerServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    /**
     * 查询记录
     */
    public MsgResponse select(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {


        AccountPfInner data = JSONObject.parseObject(req.msgBody.toString(), AccountPfInner.class);

        AccountPfInnerServiceImpl accountPfInnerService = new AccountPfInnerServiceImpl();
        AccountPfInnerExample accountUserOutExample = new AccountPfInnerExample();

        AccountPfInnerExample.Criteria criteria = accountUserOutExample.createCriteria();

        if (data.getPfInnerId() != null) {
            criteria.andPfInnerIdEqualTo(data.getPfInnerId());
        }
        if (data.getTermId() != null) {
            criteria.andTermIdEqualTo(data.getTermId());
        }
        if (data.getStatus() != null) {
            criteria.andStatusEqualTo(data.getStatus());
        }
        if (data.getPayPassword() != null) {
            criteria.andPayPasswordEqualTo(data.getPayPassword());
        }


        List<AccountPfInner> accountPfInners = accountPfInnerService.selectByExample(accountUserOutExample);

        msgBody = "{\"list\":" + JSONObject.toJSONString(accountPfInners) + "}";
        return handler.buildMsg(retcode, retmsg, msgBody);

    }

    /**
     * 新增一条记录
     */
    public MsgResponse newInnerInfo(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {


        AccountPfInner data = JSONObject.parseObject(req.msgBody.toString(), AccountPfInner.class);

        AccountPfInnerServiceImpl accountPfInnerService = new AccountPfInnerServiceImpl();


        if (data.getPayPassword() != null) {


            int sta = accountPfInnerService.insertSelective(data);


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

    /**
     * 使用ID修改记录
     */
    public Boolean updateByKey(AccountPfInner data) {


        AccountPfInnerServiceImpl accountPfInnerService = new AccountPfInnerServiceImpl();


        int sta = accountPfInnerService.updateByPrimaryKeySelective(data);


        if (sta > 0) {
            return true;
        } else {
            return false;
        }


    }


}
