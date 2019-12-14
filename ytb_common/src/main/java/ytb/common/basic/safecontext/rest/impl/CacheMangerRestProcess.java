package ytb.common.basic.safecontext.rest.impl;

import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.ehcache.SysCacheService;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.impl.YtbContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: leijming
 * Date: Created in 2018/10/17 18:11
 */
public class CacheMangerRestProcess implements IRestProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        if (handler.req.cmd.equals("clearSessionTimeout")) {
            SafeContext.clearSessionTimeout();
            return handler.success("{}");

        }else if (handler.req.cmd.equals("refresh")) {
            YtbContext.getYtbCacheManager().refresh();
            return handler.success("{}");
        } else if (handler.req.cmd.equals("refreshUser")) {
            Long user_id = handler.req.msgBody.getLong("user_id");
            if (user_id != null) {
                YtbContext.getYtbCacheManager().refresh(user_id);
                return handler.buildMsg(retcode, retmsg, "{}");
            } else {
                YtbContext.getYtbCacheManager().refreshUser();
                return handler.buildMsg(retcode, retmsg, "{}");
            }
        } else if (handler.req.cmd.equals("refreshAll")) {
            YtbContext.getYtbCacheManager().refreshAll();
            return handler.buildMsg(retcode, retmsg, "{}");
        } else if (handler.req.cmd.equals("refreshErrorCode")) {
            YtbContext.getYtbCacheManager().refreshErrorCode();
            return handler.buildMsg(retcode, retmsg, "{}");
        } else if (handler.req.cmd.equals("refreshConfig")) {
            YtbContext.getYtbCacheManager().refreshConfig();
            return handler.buildMsg(retcode, retmsg, "{}");
        } else if (handler.req.cmd.equals("refreshTable")) {
            String tableName = handler.req.msgBody.getString("tableName");
            SysCacheService.refreshTable(tableName);
            return handler.buildMsg(retcode, retmsg, "{}");
        } else if (handler.req.cmd.equals("refreshTables")) {
            SysCacheService.refreshTables();
            return handler.success(  "{}");
        }


        throw new YtbError(YtbError.CODE_INVALID_REST);

    }
}
