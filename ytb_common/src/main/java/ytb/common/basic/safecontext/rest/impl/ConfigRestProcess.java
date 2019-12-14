package ytb.common.basic.loginsso.rest.impl;

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
 * Date: Created in 2018/10/13 18:11
 */
public class ConfigRestProcess implements IRestProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody ="{}" ;

    @Override
    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        if (handler.req.cmd.equals("getConfig")) {
            String config_item = handler.req.msgBody.getString("config_item");
            if (config_item != null) {
                String config_value = YtbContext.getYtb_context().getConfig_value(config_item);
                msgBody = "{\"config_value\": \"" + config_value + " \"}";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }
}
