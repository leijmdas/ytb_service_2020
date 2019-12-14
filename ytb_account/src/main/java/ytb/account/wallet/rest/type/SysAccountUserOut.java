package ytb.account.wallet.rest.type;

import ytb.account.wallet.rest.impl.sq.SysAccountUserOutServer;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class SysAccountUserOut {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {


        /**
         * 获取所有外部账户信息
         * */
        if (req.cmd.equals("accountOutInfo")) {

            SysAccountUserOutServer sysAccountUserInfoServer = new SysAccountUserOutServer();

            return sysAccountUserInfoServer.accountOutInfo(req, handler, request, response);


        }
        if (req.cmd.equals("accountInfo")) {

            SysAccountUserOutServer sysAccountUserOutServer = new SysAccountUserOutServer();

            return sysAccountUserOutServer.accountInfo(req, handler, request, response);
        }


        /**
         * 绑定新银行卡
         * */
        if (req.cmd.equals("newUserOutInfo")) {

            SysAccountUserOutServer sysAccountUserOutServer = new SysAccountUserOutServer();

            return sysAccountUserOutServer.insert(req, handler, request, response);
        }

        /**
         * 解绑
         * */
        if (req.cmd.equals("deleteAccountInfo")) {

            SysAccountUserOutServer sysAccountUserOutServer = new SysAccountUserOutServer();

            return sysAccountUserOutServer.deleteAccountInfo(req, handler, request, response);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

        //return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
