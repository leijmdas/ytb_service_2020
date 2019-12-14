package ytb.bangbang.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.bangbang.rest.impl.ProjectUserFriend;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author qsy
 * @Description deal with project center transfer bangbang information
 * @Date 10:22 2019/4/17
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/projectBangBangRest")
@Scope("prototype")
public class ProjectBangBangRest extends RestHandler{

    @RequestMapping(value = "/bangBangRestAjax", produces = "application/json;charset=UTF-8")
    public String projectBangBangRest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response){
        try {
            parseRequest(data);
            resp = process(this);
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }
        return resp.toJSONString();
    }

    /**
     * @Author qsy
     * @Description  deal with request
     * @Date 10:36 2019/4/17
     * @Param [handler, request, response]
     * @return ytb.common.testcase.Rest.RestMessage.MsgResponse
     **/
    public MsgResponse process(MsgHandler handler) throws Exception {
        MsgRequest req = handler.req;
        int userId = handler.getUserContext().getLoginSso().getUserId().intValue();
        req.msgBody.put("userId", userId);
        if (req.cmdtype.equals("FriendsManger")) {
            return  new ProjectUserFriend().process(req, handler);
        }
        return buildMsg(0, "ok", "{\"a\":1}");
    }
}
