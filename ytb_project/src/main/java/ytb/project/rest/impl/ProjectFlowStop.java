package ytb.project.rest.impl;

import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.DAOService;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUserControl;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.project.stop.StopApplyResult;
import ytb.project.service.project.stop.impl.StopType;
import ytb.project.view.model.ProjectStopTaskViewResult;
import ytb.project.view.service.impl.StopTaskViewTalk;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.io.IOException;

//项目终止接口
public final class ProjectFlowStop extends DAOService {

    public MsgResponse process(MsgRequest req, MsgHandler handler) throws IOException {
        ProjectFlowService projectFlowService = getInst().getProjectFlowService();

        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";
        //获取用户项目上下文
        UserProjectContext context = UserProjectContext.pre(handler);
        Integer talkId = context.getTalkId();
        Integer loginUserId = context.getUserId();//当前登录人
        Integer loginCompanyId = context.getCompanyId();

        //申请项目终止
        if (req.cmd.equals("goStopProject")) {

            Byte partyBy = req.msgBody.getByte("partyBy");//终止提出方 1、2
            short stopReason = req.msgBody.getShort("stopReason");//终止原因 801~805
            StopType stopType = new StopType(context, partyBy, stopReason);

            StopApplyResult applyResult = projectFlowService.applyStopProject(context, loginUserId, stopType);
            msgBody = "{'phase':" + applyResult + "}";

        }

        //提醒对方发起项目终止
        else if (req.cmd.equals("noticePartyStopProject")) {

        }

        //对方点击同意/不同意终止
        else if (req.cmd.equals("confirmStopProject")) {
            int status = req.msgBody.getInteger("status");
            projectFlowService.confirmStopProject(context, loginUserId, status);
        }


        //项目终止
        else if (req.cmd.equals("goCompleteProject")) {
            msgBody = projectFlowService.stopProject(context, loginUserId);

        }

        //甲方提出，乙方同意
        else if (req.cmd.equals("confirmStopProjectPB")) {

            int status = req.msgBody.getInteger("status");// 0同意 1不同意
            projectFlowService.confirmStopProjectPB(context, status, loginUserId);
        }


        //乙方提出，甲方方同意
        else if (req.cmd.equals("confirmStopProjectPA")) {

            int status = req.msgBody.getInteger("status");// 0同意 1不同意
            projectFlowService.confirmStopProjectPB(context, status, loginUserId);
        }


        //甲方终止页面
        else if (req.cmd.equals("paViewStop")) {
            int phase = req.msgBody.getInteger("phase");
            ProjectStopTaskViewResult result = new StopTaskViewTalk().getProjectStopView(context, loginUserId,
                    phase, "PA");
            msgBody = result.toString();
        }

        //乙方终止页面
        else if (req.cmd.equals("pbViewStop")) {
            int phase = req.msgBody.getInteger("phase");
            ProjectStopTaskViewResult result = new StopTaskViewTalk().getProjectStopView(context, loginUserId,
                    phase, "PB");
            msgBody = result.toString();
        }

        //组员终止页面
        else if (req.cmd.equals("pmViewStop")) {
            int phase = req.msgBody.getInteger("phase");
            ProjectStopTaskViewResult result = new StopTaskViewTalk().getProjectStopView(context, loginUserId,
                    phase, "PM");
            msgBody = result.toString();
        }

        //修改终止页面视图状态控制
        else if (req.cmd.equals("modifyStopProjectTemplateUser")) {
            int templateId = req.msgBody.getInteger("templateId");

            new ProjectTemplateUserControl().modifyStopProjectTemplateUser(context, loginUserId, templateId);
        }

        //取消终止
        else if(req.cmd.equals("cancelStopProject")){

        }

        else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

}
