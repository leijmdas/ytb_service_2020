package ytb.common.basic.loginsso.rest.impl;

import org.springframework.util.StringUtils;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.utils.YtbUtils;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class SysLoginSsoRestProcess implements IRestProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取主表中的所有记录
        if (handler.req.cmd.equals("getLogSso")) {
            String token = handler.req.token;
            LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

            msgBody = "{\"list\":[" + loginSso.getLoginSsoJson() + "]}";
            return handler.buildMsg( retcode, retmsg,msgBody );

        } else if (handler.req.cmd.equals("refreshToken")) {
            String token = handler.req.token;
            LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);
            if (loginSso != null) {
                String refresh_token = loginSso.getLoginSsoJson().getRefresh_token();
                loginSso = SafeContext.refreshToken(token, refresh_token);
                msgBody = "{\"list\":[" + loginSso.getJson() + "]}";
                MsgResponse resp = handler.buildMsg(retcode, retmsg, msgBody);
                resp.token = loginSso.getToken();
            }
            throw new YtbError(YtbError.CODE_INVALID_USER);

        } else if (handler.req.cmd.equals("logout")) {
            String token = handler.req.token;
            SafeContext.logout(token);
            return handler.buildMsg(retcode, retmsg, msgBody);
        } else if (handler.req.cmd.equals("getPicCode")) {
            String ip = YtbUtils.getIpAddr(request);
            byte[] ret = SafeContext.genPicCode2Byte(ip, response);
            msgBody = "{'getPicCode':"+StringUtils.quote(MsgHandler.byte2Base64(ret))+"}" ;
            return handler.buildMsg(retcode, retmsg, msgBody);
        }

        //图形验证码
        else if (handler.req.cmd.equals("checkPicCode")) {
            String picCode = handler.req.msgBody.getString("picCode");
            try {
                SafeContext.checkPicCode(
                        YtbUtils.getIpAddr(request) ,
                        picCode);
                return handler.buildMsg(retcode, retmsg, msgBody);
            } catch (IOException e) {
                e.printStackTrace();
                throw new YtbError(YtbError.getErrorId(YtbError.CODE_UNKNOWN_ERROR), e.getMessage());
            }
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }
}
