package ytb.account.wallet.rest.type;

import com.alibaba.fastjson.JSONObject;
import ytb.account.wallet.model.AccountPfOut;
import ytb.account.wallet.rest.impl.sq.SysAccountPfOutServer;
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
public class SysAccountPfOut {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {

        /**
         * 获取主表中的所有记录
         *
         * */
        if (req.cmd.equals("accountOutInfo")) {

            SysAccountPfOutServer server = new SysAccountPfOutServer();
            return server.select(req, handler, request, response);

        }

        if (req.cmd.equals("newOutInfo")) {

            SysAccountPfOutServer server = new SysAccountPfOutServer();
            return server.insert(req, handler, request, response);

        }
        if (req.cmd.equals("delOutInfo")) {

            SysAccountPfOutServer server = new SysAccountPfOutServer();
            AccountPfOut data = JSONObject.parseObject(req.msgBody.toString(), AccountPfOut.class);

            boolean sta = server.deleteById(data);
            if (sta == false) {
                retcode = 1;
                retmsg = "数据错误";
            }


            return handler.buildMsg(retcode, retmsg, msgBody);

        }


        /**
         * 外部用户解绑
         * */
        if (req.cmd.equals("prohibitOutInfo")) {

            SysAccountPfOutServer server = new SysAccountPfOutServer();
            AccountPfOut data = JSONObject.parseObject(req.msgBody.toString(), AccountPfOut.class);
            boolean sta = server.prohibitOutInfo(data);

            if (sta == false) {
                retcode = 1;
                retmsg = "数据错误";
            }


        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }
}
