package ytb.manager.sysnotices.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ytb.common.ytblog.YtbLog;
import ytb.manager.sysnotices.rest.impl.SysNotices;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: ytb.manager.sysnotices.rest
 * Author: ZCS
 * Date: Created in 2018/11/22 15:14
 */

@RestController
@RequestMapping("/rest/sysNotices")
public class RestSysNotices implements IRestProcess {

    @RequestMapping(value = "notices", produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public String sysUserRest(@RequestBody String data,HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler().parseRequest(this, data, request, response);
    }

    @Override
    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {

        if (handler.req.cmdtype.equals("systemNotices")) {
            return new SysNotices().process(handler);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);
    }


    public static void main(String[] args) {

//        MsgRequest req = new MsgRequest();
//        req.token = "3261978dab6940858a8a1be554eb3d52";
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date());
//        req.seqno = System.currentTimeMillis();
//        req.cmdtype = "systemNotices";
//        req.cmd = "getSysNoticesList";
//        req.msgBody = JSONObject.parseObject("{\"title\":\"\",\"type\":1,\"currPage\":1,\"pageSize\":5}");
//        System.out.println(new Gson().toJson(req));
//        System.out.println(new RestSysNotices().sysUserRest(new Gson().toJson(req),null,null));
        YtbLog.logError("test log");
    }
}
