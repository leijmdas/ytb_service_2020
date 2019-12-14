package ytb.manager.pfUser.rest;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ytb.manager.pfUser.rest.impl.DictCompanyTypeRestProcess;
import ytb.manager.pfUser.rest.impl.DictTagRestProcess;
import ytb.manager.pfUser.rest.impl.PlatformUserManager;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 后台 Rest类
 * Package: ytb.manager.sysuser.rest
 * Author: leijming
 * Date: Created in 2018/11/21 13:20
 * Copyright: Copyright (c) 2018
 */

@RestController
@RequestMapping("/rest/pfUser")//@Scope("prototype")
public class RestPfUser implements IRestProcess {


    @RequestMapping(value = "dictTag", produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public String sysUserRest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler().parseRequest(this, data, request, response);
    }

    @RequestMapping(value = "dictCompanyType", produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public String companyTypeRest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler().parseRequest(this, data, request, response);
    }

    @RequestMapping(value = "platformUserManager", produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public String platformUserRest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler().parseRequest(this, data, request, response);
    }


    public MsgResponse process(MsgHandler handler, javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {

        if (handler.req.cmdtype.equals("dictTag")) {
            return new DictTagRestProcess().process(handler);
        }
        if (handler.req.cmdtype.equals("dictCompanyType")) {
            return new DictCompanyTypeRestProcess().process(handler);
        }
        if(handler.req.cmdtype.equals("platformUserManager")){

            return new PlatformUserManager().process(handler);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);
    }


}
