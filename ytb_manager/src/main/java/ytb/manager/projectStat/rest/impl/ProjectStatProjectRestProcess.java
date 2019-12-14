package ytb.manager.projectStat.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.projectStat.model.ProjectStatProject.StatProjectType;
import ytb.manager.projectStat.model.User.User_StatModel;
import ytb.manager.projectStat.service.impl.ProjectStatProject;
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
public class ProjectStatProjectRestProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, RestHandler handler) {

        if (req.cmd.equals("statProjectArea2Area")) {
            List<User_StatModel> lst = ProjectStatProject.statProjectArea();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;

        }
        else if (req.cmd.equals("selectProjectArea2Area")) {
            List<User_StatModel> lst = ProjectStatProject.selectProjectArea();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }else
        if (req.cmd.equals("statProjectArea")) {
            List<User_StatModel> lst = ProjectStatProject.statProjectArea();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;

        }
        else if (req.cmd.equals("selectProjectArea")) {
            List<User_StatModel> lst = ProjectStatProject.selectProjectArea();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }else
        if (req.cmd.equals("statCompanyType")) {
            List<User_StatModel> lst = ProjectStatProject.statCompanyType();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;

        }   else if (req.cmd.equals("selectCompanyType")) {
            List<User_StatModel> lst = ProjectStatProject.selectCompanyType();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }else
        if (req.cmd.equals("statTotal")) {
            List<User_StatModel> lst = ProjectStatProject.statTotal();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;

        }   else if (req.cmd.equals("selectTotal")) {
            List<User_StatModel> lst = ProjectStatProject.selectTotal();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }

        else
        if (req.cmd.equals("statAvg")) {
            List<User_StatModel> lst = ProjectStatProject.statAvg();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;

        }   else if (req.cmd.equals("selectAvg")) {
            List<User_StatModel> lst = ProjectStatProject.selectAvg();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }    else
        if (req.cmd.equals("statProjectType")) {
            List<StatProjectType> lst = ProjectStatProject.statProjectType();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;

        }   else if (req.cmd.equals("selectProjectType")) {
            List<StatProjectType> lst = ProjectStatProject.selectProjectType();
            String msgBody = "{'list': " + JSONObject.toJSONString(lst) + " }";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }


}
