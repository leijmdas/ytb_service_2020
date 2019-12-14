package ytb.account.wallet.rest.type;

import com.alibaba.fastjson.JSONObject;
import ytb.account.wallet.model.AccountPfInner;
import ytb.account.wallet.rest.impl.sq.SysAccountPfInnerServer;
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
public class SysAccountPfInner {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {


        /**获取主表中的所有记录*/
        if (req.cmd.equals("accountInnerInfo")) {

            SysAccountPfInnerServer server = new SysAccountPfInnerServer();
            return server.select(req, handler, request, response);
            /**新增一条记录*/
        } else if (req.cmd.equals("newInnerInfo")) {

            SysAccountPfInnerServer server = new SysAccountPfInnerServer();
            return server.newInnerInfo(req, handler, request, response);

        }
        /**使用ID修改记录*/
        else if (req.cmd.equals("updateByKey")) {
            SysAccountPfInnerServer server = new SysAccountPfInnerServer();
            AccountPfInner data = JSONObject.parseObject(req.msgBody.toString(), AccountPfInner.class);

            boolean sta = server.updateByKey(data);
            if (sta == false) {
                retcode = 1;
                retmsg = "数据错误";
            }
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }
}
