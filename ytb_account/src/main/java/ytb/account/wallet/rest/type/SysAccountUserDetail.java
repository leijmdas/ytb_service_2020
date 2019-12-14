package ytb.account.wallet.rest.type;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.pojo.Paging;
import ytb.common.utils.pageutil.PageUtils;
import ytb.account.wallet.model.AccountUserDetail;
import ytb.account.wallet.rest.impl.sq.SysAccountUserDetailServer;
import ytb.account.wallet.service.sq.tool.PagingTool;
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
public class SysAccountUserDetail {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {


        /**
         * 获取主表中的所有流水记录
         * */
        if (req.cmd.equals("accountDetailInfo")) {

            SysAccountUserDetailServer server = new SysAccountUserDetailServer();

            return server.detailInfo(req, handler, request, response);
        }


        /**
         * 获取主表中的所有流水记录并分页
         * */
        if (req.cmd.equals("accountDetailByPage")) {

            SysAccountUserDetailServer server = new SysAccountUserDetailServer();
            Paging paging = PagingTool.getPaging(req);
            AccountUserDetail accountUserDetail = JSONObject.parseObject(req.msgBody.toString(), AccountUserDetail.class);

            PageUtils list = server.accountDetailByPage(accountUserDetail, paging);

            msgBody = "{\"list\":" + JSONArray.toJSONString(list) + "}";

            return handler.buildMsg(retcode, retmsg, msgBody);
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

}


