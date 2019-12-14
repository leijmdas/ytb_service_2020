package ytb.manager.projectStat.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.projectStat.model.ProjectStat;
import ytb.manager.projectStat.service.StatManagerService;
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
public class ProjectStatPageRestProcess {

    StatManagerService statService = new StatManagerService();

    public MsgResponse process(MsgRequest req, RestHandler handler) {

        //pageGetStatList
        if (req.cmd.equals("pageGetStatTree")) {
            Integer statType = req.msgBody.getInteger("statType");
            List<ProjectStat> list = statService.pageGetStatTrees(statType == null ? 0 : statType);
            handler.resp.getMsgBody().put("list", JSONObject.toJSON(list));
            handler.resp.setRetcode(0);
            handler.resp.setRetmsg("成功");
            return handler.resp;
        } else if (req.cmd.equals("pageGetStatList")) {
            Integer statType = req.msgBody.getInteger("statType");
            List<ProjectStat> list = statService.pageGetStatList(statType);
            handler.resp.getMsgBody().put("list", JSONObject.toJSON(list));
            return handler.resp;
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);

    }


}
