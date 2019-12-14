package ytb.account.wallet.rest.type;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.pojo.Paging;
import ytb.common.utils.pageutil.PageUtils;
import ytb.account.wallet.model.AccountPfDetail;
import ytb.account.wallet.rest.impl.sq.SysAccountPfDetailServer;
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
public class SysAccountPfDetail {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {

        /**获取记录*/
        if (req.cmd.equals("accountPfDetailInfo")) {
            SysAccountPfDetailServer sysAccountPfDetailServer = new SysAccountPfDetailServer();


            AccountPfDetail data = JSONObject.parseObject(req.msgBody.toString(), AccountPfDetail.class);
            Paging paging = PagingTool.getPaging(req);
            PageUtils list = sysAccountPfDetailServer.select(data, paging);

            msgBody = "{\"list\":" + JSONObject.toJSONString(list) + "}";

        }
        /**手动新增入账*/
        if (req.cmd.equals("newRecordIn")) {
            SysAccountPfDetailServer innerServer = new SysAccountPfDetailServer();

            AccountPfDetail data = JSONObject.parseObject(req.msgBody.toString(), AccountPfDetail.class);

            Boolean sta = innerServer.newRecordIn(data);

            if (sta == false) {
                retcode = 1;
                retmsg = "失败";
            }

        }
        /**手动新增出账*/
        if (req.cmd.equals("newRecordOut")) {
            SysAccountPfDetailServer innerServer = new SysAccountPfDetailServer();

            AccountPfDetail data = JSONObject.parseObject(req.msgBody.toString(), AccountPfDetail.class);

            Boolean sta = innerServer.newRecordOut(data);

            if (sta == false) {
                retcode = 1;
                retmsg = "失败";
            }

        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
