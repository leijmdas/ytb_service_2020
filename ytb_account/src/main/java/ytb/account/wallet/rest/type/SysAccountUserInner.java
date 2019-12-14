package ytb.account.wallet.rest.type;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.pojo.Paging;
import ytb.common.utils.pageutil.PageUtils;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.rest.impl.sq.SysAccountUserInnerServer;
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
public class SysAccountUserInner {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {


        if (req.cmd.equals("accountInfo")) {

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            return sysAccountUserInnerServer.accountInfo(req, handler, request, response);
        }
/*
        if (req.cmd.equals("accountAccountNewInfo")) {

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            return sysAccountUserInnerServer.accountAccountNewInfo(req, handler, request, response);
        }
*/

        if (req.cmd.equals("accountInfoAndEstablish")) {

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            return sysAccountUserInnerServer.accountInfoAndEstablish(req, handler, request, response);
        }

        if (req.cmd.equals("newUserInnerInfo")) {

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            return sysAccountUserInnerServer.newUserInnerInfo(req, handler, request, response);
        }

        if (req.cmd.equals("newDefaultUserInnerInfo")) {

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            return sysAccountUserInnerServer.newDefaultUserInnerInfo(req, handler, request, response);
        }
        /**
         * 首次设置密码
         * */
        if (req.cmd.equals("SetUpPassword")) {

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            return sysAccountUserInnerServer.SetUpPassword(req, handler, request, response);
        }
        /**
         * 修改密码
         */
        if (req.cmd.equals("modifyPassword")) {

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            return sysAccountUserInnerServer.modifyPassword(req, handler, request, response);
        }
        /**
         * 使用手机验证码修改密码
         */
        if (req.cmd.equals("modifyPasswordByPhone")) {

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            return sysAccountUserInnerServer.modifyPasswordByPhone(req, handler, request, response);
        }

        /**
         * 获取账户列表
         */
        if (req.cmd.equals("accountInfoList")) {

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            AccountUserInner data = JSONObject.parseObject(req.msgBody.toString(), AccountUserInner.class);
            byte type = Byte.valueOf(String.valueOf(req.msgBody.getInteger("type")));

            Paging paging = PagingTool.getPaging(req);


            if (type > 0) {
                if (type != 1 && type != 2) {
                    type = 0;
                }

                PageUtils accountUserInnerList = sysAccountUserInnerServer.accountInfoList(paging, type);

                msgBody = "{\"list\":" + JSONObject.toJSONString(accountUserInnerList) + "}";
            }


        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


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
