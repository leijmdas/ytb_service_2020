package ytb.log.notify.rest.impl;


import com.alibaba.fastjson.JSONObject;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.log.notify.model.*;
import ytb.log.notify.service.impl.TaskLogTimerImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;
import java.util.Map;


public class TaskLogTimer {

    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, RestHandler handler) throws Exception {
        System.err.println(req);
        TaskLogTimerImpl taskLogTimerImpl = new TaskLogTimerImpl();
        if (req.cmd.equals("exeOnceTaskLogTimer")) {
            Integer taskId = req.getMsgBody().getInteger("taskId");
            String msgBody = taskLogTimerImpl.exeOnceTaskLogTimer(req, taskId);
            return MsgResponse.parseResponse(msgBody); //            return handler.buildMsg(retcode, retmsg, msgBody);
        } else if (req.cmd.equals("exeNode")) {
            Integer taskId = req.getMsgBody().getInteger("taskId");
            String ret = taskLogTimerImpl.exeNode(req, taskId);
            msgBody = "{'info':'" + ret + "' }";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }
        else if (req.cmd.equals("getTaskLogTimerList")) {
            List<TaskLog_TimerModel> list = taskLogTimerImpl.getTaskLogTimerList();
            msgBody = "{\"list\":" + JSONObject.toJSONString(list) + "}";
            return handler.buildMsg(retcode, retmsg, msgBody);
        } else
        if (req.cmd.equals("addTaskLogTimer")) {
            TaskLog_TimerModel model=JSONObject.parseObject(req.getMsgBody().toJSONString(),TaskLog_TimerModel.class);
            int id = taskLogTimerImpl.addTaskLogTimer(model);
            msgBody = "{\"id\":" + JSONObject.toJSONString(id) + "}";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }
        else
        if (req.cmd.equals("modifyTaskLogTimer")) {
            TaskLog_TimerModel model=JSONObject.parseObject(req.getMsgBody().toJSONString(),TaskLog_TimerModel.class);
            taskLogTimerImpl.modifyTaskLogTimer(model);
            msgBody = "{ }";
            return handler.buildMsg(retcode, retmsg, msgBody);
        } else if (req.cmd.equals("delTaskLogTimer")) {
            int taskId=req.getMsgBody().getInteger("taskId");
            taskLogTimerImpl.delTaskLogTimer(taskId);
            msgBody = "{  }";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }
        else
        if (req.cmd.equals("getTaskLogTimer")) {
            int taskId=req.getMsgBody().getInteger("taskId");
            TaskLog_TimerModel r = taskLogTimerImpl.getTaskLogTimer(taskId);
            msgBody = "{\"list\":[" + JSONObject.toJSONString(r) + "]}";
            return handler.buildMsg(retcode, retmsg, msgBody);
        } else if (req.cmd.equals("startSch")) {
            //Integer taskId = req.getMsgBody().getInteger("taskId");
            taskLogTimerImpl.startTaskLogTimer();
            msgBody = "{ }";
            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("stopSch")) {
            Integer taskId = req.getMsgBody().getInteger("taskId");
            taskLogTimerImpl.stopTaskLogTimer();
            msgBody = "{ }";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }else if (req.cmd.equals("startJob")) {
            TaskLog_TimerModel model=JSONObject.parseObject(req.getMsgBody().toJSONString(),TaskLog_TimerModel.class);
            taskLogTimerImpl.startJob(req,model);
            msgBody = "{ }";
            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("stopJob")) {
            Integer taskId = req.getMsgBody().getInteger("taskId");
            taskLogTimerImpl.stopJob(taskId);
            msgBody = "{ }";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }
        //运行状态
        else if (req.cmd.equals("queryStatus")) {

            Map<String, Boolean> map= taskLogTimerImpl.queryTaskLogTimerStartStatus();
            msgBody = "{'list':["+JSONObject.toJSONString(map)+"]}";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }
        //发布状态
        else
        if (req.cmd.equals("modifyStatus")) {
            TaskLog_TimerModel model=JSONObject.parseObject(req.getMsgBody().toJSONString(),TaskLog_TimerModel.class);
            taskLogTimerImpl.modifyTaskLogTimerStatus(model);
            msgBody = "{ }";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }


    private LoginSsoJson getLoginSsoJson(String token) {
        LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

        LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        return loginSsoJson;
    }

}
