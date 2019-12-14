package ytb.manager.projectStat.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.projectStat.model.ProjectStatMoney;
import ytb.manager.projectStat.model.ProjectStatProjectType;
import ytb.manager.projectStat.model.ProjectStatUserArea;
import ytb.manager.projectStat.service.impl.ProjectStatEvent;
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
public class ProjectStatMoneyRestProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, RestHandler handler) {

        //统计项目资金量
        if (req.cmd.equals("statMoney")) {
            ProjectStatMoney sm = ProjectStatEvent.stat();
            String msgBody="{'list':["+ JSONObject.toJSONString( sm )+"]}" ;
            handler.resp.buildMsg(retcode,retmsg,msgBody);
            return handler.resp;
        }
        //
        else if (req.cmd.equals("selectProjectStatMoney")) {
            List<ProjectStatMoney> sm = ProjectStatEvent.selectProjectStatMoney();
            String msgBody="{'list':"+ JSONObject.toJSONString( sm )+"}" ;
            handler.resp.buildMsg(retcode,retmsg,msgBody);
            return handler.resp;
        }
        //统计项目类型资金量
        else if (req.cmd.equals("statProjectType")) {
            List<ProjectStatProjectType> statUa = ProjectStatEvent.statProjectType();
            String msgBody = "{'list':" + JSONObject.toJSONString(statUa) + "}";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        } else if (req.cmd.equals("selectStatProjectType")) {
            List<ProjectStatProjectType> statUa = ProjectStatEvent.statProjectType();
            String msgBody = "{'list':" + JSONObject.toJSONString(statUa) + "}";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }
        //统计甲方乙方地域资金量
        else if (req.cmd.equals("statUserArea")) {
            List<ProjectStatUserArea> statUa = ProjectStatEvent.statUserArea();
            String msgBody = "{'list':" + JSONObject.toJSONString(statUa) + "}";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        } else if (req.cmd.equals("selectStatUserArea")) {
            List<ProjectStatUserArea> statUa = ProjectStatEvent.selectUserAreaSql();
            String msgBody = "{'list':" + JSONObject.toJSONString(statUa) + "}";
            handler.resp.buildMsg(retcode, retmsg, msgBody);
            return handler.resp;
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);


    }


}
