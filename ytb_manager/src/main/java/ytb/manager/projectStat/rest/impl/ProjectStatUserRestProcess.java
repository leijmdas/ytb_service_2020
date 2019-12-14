package ytb.manager.projectStat.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.projectStat.model.User.User_DayActiveModel;
import ytb.manager.projectStat.model.User.User_StatModel;
import ytb.manager.projectStat.service.impl.ProjectStatUser;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: ljm
 * Date: Created in 2018/11/11
 */
public class ProjectStatUserRestProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, RestHandler handler) {

        if (req.cmd.equals("statUserDayActive")) {
            List<User_DayActiveModel> lst = ProjectStatUser.statUserDayActive();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        } else if (req.cmd.equals("selectUserDayActive")) {
            List<User_DayActiveModel> lst = ProjectStatUser.selectUserDayActive();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }else
        if (req.cmd.equals("statUserProjectType")) {
            List<User_StatModel> lst = ProjectStatUser.statUserProjectType();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }else
        if (req.cmd.equals("selectUserProjectType")) {
            List<User_StatModel> lst = ProjectStatUser.selectUserProjectType();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        } else
        if (req.cmd.equals("statUserCompanyType")) {
            List<User_StatModel> lst = ProjectStatUser.statUserCompanyType();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }else
        if (req.cmd.equals("selectUserCompanyType")) {
            List<User_StatModel> lst = ProjectStatUser.selectUserCompanyType();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }
        else
        if (req.cmd.equals("statUserArea")) {
            List<User_StatModel> lst = ProjectStatUser.statUserArea();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }else
        if (req.cmd.equals("selectUserArea")) {
            List<User_StatModel> lst = ProjectStatUser.selectUserArea();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }
        else
        if (req.cmd.equals("statUserTotal")) {
            List<User_StatModel> lst = ProjectStatUser.statUserTotal();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }else
        if (req.cmd.equals("selectUserTotal")) {
            List<User_StatModel> lst = ProjectStatUser.selectUserTotal();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }
        else
        if (req.cmd.equals("statUserAvg")) {
            List<User_StatModel> lst = ProjectStatUser.statUserAvg();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }else
        if (req.cmd.equals("selectUserAvg")) {
            List<User_StatModel> lst = ProjectStatUser.selectUserAvg();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);
    }



}
