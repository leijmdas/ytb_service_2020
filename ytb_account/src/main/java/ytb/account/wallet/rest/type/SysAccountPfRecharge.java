package ytb.account.wallet.rest.type;

import ytb.account.wallet.rest.impl.SysAccountPfRechargeService;
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
public class SysAccountPfRecharge {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {

        if (req.cmd.equals("transfer")) {
            SysAccountPfRechargeService sysAccountPfRecharge = new SysAccountPfRechargeService();

            return sysAccountPfRecharge.transfer(req, handler, request, response);
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

}
