package ytb.manager.sysuser.rest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.utils.YtbUtils;

import ytb.manager.sysuser.rest.impl.SysPower;
import ytb.manager.sysuser.rest.impl.SysRole;
import ytb.manager.sysuser.rest.impl.SysUser;
import ytb.manager.sysuser.service.impl.SysUserContext;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletResponse;

/**
 * 后台管理系统的Rest类
 * Package: ytb.manager.sysuser.rest
 * Author: ZCS
 * Date: Created in 2018/8/21 13:20
 * Copyright: Copyright (c) 2018
 */

@RestController
@RequestMapping("/rest")
//@Scope("prototype")
public class RestSysUserManager implements IRestProcess {


    @RequestMapping(value = "sysuser", produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public String sysUserRest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        SysUserContext suc = new SysUserContext();
        return new MsgHandler(suc).parseRequest(this, data, request, response);
    }


    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        if (handler.req.cmdtype.equals("user") && handler.req.cmd.equals("login")) {

        } else {
            handler.getUserContext().setLoginSso(SafeContext.getLog_ssoAndApiKey(handler.req.token));
            handler.req.msgBody.put("login_userId", handler.getUserContext().getLoginSso().getUserId());
        }
        handler.req.msgBody.put("ip", YtbUtils.getIpAddr(request));

        if (handler.req.cmdtype.equals("user")) {
            return new SysUser().process(handler);
        } else if (handler.req.cmdtype.equals("role")) {
            return new SysRole().process(handler);
        } else if (handler.req.cmdtype.equals("menu")) {
            return new SysPower().process(handler);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);
    }


}




