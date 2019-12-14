package ytb.manager.projectStat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbUtils;
import ytb.common.ytblog.YtbLog;
import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.log.notify.service.ITimerTaskService;
import ytb.manager.projectStat.model.ProjectStat;
import ytb.manager.projectStat.service.StatManagerService;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.sql.Date;
import java.util.List;

public class ProjectStatTimerTaskExeOnce implements ITimerTaskService {
    @Override
    public void execute(MsgRequest req, TaskLog_TimerModel task) {
        int i=0;
        StatManagerService ss=new StatManagerService();
        List<ProjectStat> lst = ss.selectList();
        for (ProjectStat ps : lst) {
            i++;
            ps.setRunTime(new Date(System.currentTimeMillis()));
            // System.out.println(i+" : "+ps.getScript());
            //System.out.println(ps.getParams());
            JSONObject json = JSON.parseObject(ps.getParams());
            req.setCmd(json.getString("cmd"));
            req.setCmdtype(json.getString("cmdtype"));
            json.remove("cmd");
            json.remove("cmdtype");
            req.setMsgBody(json);
            YtbLog.logDebug(req.toJSONStringPretty());
            String ret = YtbUtils.postFor(ps.getScript(), req.toJSONStringPretty());
            MsgResponse resp = MsgResponse.parseResponse(ret);
            resp.setMsgBody(JSON.parseObject("{}"));
            ss.updateProjectStat(ps);
            YtbLog.logDebug(resp);
        }

    }
}
