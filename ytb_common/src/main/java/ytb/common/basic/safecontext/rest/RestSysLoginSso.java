package ytb.common.basic.safecontext.rest;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.utils.YtbUtils;
import ytb.common.basic.safecontext.rest.impl.CacheMangerRestProcess;
import ytb.common.basic.loginsso.rest.impl.ConfigRestProcess;
import ytb.common.basic.loginsso.rest.impl.SysLoginSsoRestProcess;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.impl.DefaultUserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台元数据字典模块的Rest类
 * Package: ytb.manager.metadata.rest
 * Author: cch/leijiming
 * Date: Created in 2018/8/23 16:50
 */
@RestController
@RequestMapping("/rest")
//@Scope("prototype")
public final class RestSysLoginSso implements IRestProcess {
    /*
     *   cmdType="context"
     *   cmd="logout"
     *   cmd="refreshToken"
     *   cmd="getLogSso"
     *   cmdType="config"
     *   cmd="getConfig"
     *  */
    @ApiOperation(value="context服务", notes="根据消息体处理上下文与配置")
    @ApiImplicitParam(name = "data", value = "请求消息体", required = true, dataType =
            "String", paramType = "body")
    @PostMapping(value = "context",produces = {"Application/json;charset=UTF-8"})
    public String loginSso(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler(new DefaultUserContext()).parseRequest(this, data, request, response);
    }


    public MsgResponse process(MsgHandler msgHandler, HttpServletRequest request, HttpServletResponse response) throws Exception {

        switch (msgHandler.req.cmdtype) {
            case "context":
                return new SysLoginSsoRestProcess().process(msgHandler, request, response);
            case "config":
                return new ConfigRestProcess().process(msgHandler, request, response);
            case "cacheManager":
                return new CacheMangerRestProcess().process(msgHandler, request, response);
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);
    }

    @CrossOrigin    //图形验证码
    @GetMapping("/context/getPicCode")
    public void getPicCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MsgHandler msgHandler = new MsgHandler();
        try {
            //msgHandler.parseRequest(data); @RequestBody String data,
            String ip= YtbUtils.getIpAddr(request);
            SafeContext.genPicCode(ip, response);
            return;
        } catch (YtbError e) {
            msgHandler.buildMsg( e );
        } catch (Exception e) {
            msgHandler.buildMsg(e);
        }

        response.setCharacterEncoding(UTF_8);
        response.setContentType("application/json");
        response.getWriter().write(msgHandler.resp.toJSONString());
    }


}

