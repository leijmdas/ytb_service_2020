package ytb.project.rest.impl;

import com.alibaba.fastjson.JSON;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.DAOService;
import ytb.project.model.ProjectEventModel;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTemplateUserModel;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUser;
import ytb.project.service.IFlowFolderView;
import ytb.project.service.impl.pay.IFlowPay;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.impl.pay.ViewPayResult;
import ytb.project.service.impl.pay.projectpay.FlowPayChange;
import ytb.project.service.project.change.ApplyResult;
import ytb.project.service.project.change.ChangeFlow;
import ytb.project.view.model.ProjectChangeResult;
import ytb.project.view.model.ProjectChangeTaskViewResult;
import ytb.project.view.service.impl.ChangeTaskViewTalk;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//项目变更接口
public final class ProjectFlowChange extends DAOService
{

    public MsgResponse process(MsgRequest req, MsgHandler handler) throws IOException {

        IFlowFolderView flowFolderView = getInst().getFlowFolderView();
        IFlowPay flowPay = getInst().getFlowPay();

        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";

        //获取用户项目上下文
        UserProjectContext context = UserProjectContext.pre(handler);
        Integer talkId = context.getTalkId();
        Integer loginUserId = context.getUserId();//当前登录人
        Integer loginCompanyId = context.getCompanyId();
        //申请变更
        if (req.cmd.equals("paApplyChange")) {
            ApplyResult applyResult = new ChangeFlow().paApplyChange(context);

            msgBody = "{\"list\":" + applyResult.toString() + "}";
        }

        //准备项目变更
        else if (req.cmd.equals("paAgreeChange")) {
            String displayItems = req.msgBody.getString("items");
            String changeItems = req.msgBody.getString("changeItems");//"1,2,3,4"

            ProjectChangeResult result = new ChangeFlow().paAgreeChange(context, displayItems, changeItems);
            msgBody = "{\"list\":" + result.toString() + "}";


        }

        //甲方需求变更页面
        else if (req.cmd.equals("paViewChange")) {
            int projectChangeId = req.msgBody.getInteger("newProjectId");
            int phase = req.msgBody.getInteger("phase");
            int newTalkId = ProjectFlowService.getTalkService().getProjectTalkByProjectId(projectChangeId).getTalkId();
            ProjectChangeTaskViewResult result = new ChangeTaskViewTalk().getProjectChangeView(
                    context, newTalkId, "PA", phase);
            msgBody = result.toString();
        }

        //乙方需求变更页面
        else if (req.cmd.equals("pbViewChange")) {
            int newProjectId = req.msgBody.getInteger("newProjectId");
            int newTalkId = ProjectFlowService.getTalkService().getProjectTalkByProjectId(newProjectId).getTalkId();
            int phase = req.msgBody.getInteger("phase");
            ProjectChangeTaskViewResult result = new ChangeTaskViewTalk().getProjectChangeView
                    (context, newTalkId, "PB", phase);
            msgBody = result.toString();
        }

        //组员需求变更页面
        else if (req.cmd.equals("pmViewChange")) {
            int newProjectId = req.msgBody.getInteger("newProjectId");
            int newTalkId = ProjectFlowService.getTalkService().getProjectTalkByProjectId(newProjectId).getTalkId();
            int phase = req.msgBody.getInteger("phase");
            ProjectChangeTaskViewResult result = new ChangeTaskViewTalk().getProjectChangeView
                    (context, newTalkId, "PM", phase);
            msgBody = result.toString();
        }

        //乙方递交变更文件
        else if (req.cmd.equals("pbSubmitChange")) {
            int newTalkId = req.msgBody.getInteger("newTalkId");
            UserProjectContext newContext = new UserProjectContext(context, newTalkId);
            new ChangeFlow().pbSubmitChange(context, newContext);
        }

        //甲方审核变更文档
        else if (req.cmd.equals("paAuditChange")) {

            int status = req.msgBody.getInteger("status");//6审核通过  7审核不通过
            Integer newTalkId = req.msgBody.getInteger("newTalkId");
            new ChangeFlow().paAuditChange(context, newTalkId, status);
        }

        //确定大小变更
        else if (req.cmd.equals("computeChange")) {
            int docType = req.msgBody.getInteger("docType");
            int oldTalkId = req.msgBody.getInteger("oldTalkId");
            UserProjectContext oldContext = new UserProjectContext(context, oldTalkId);

            int changeType = new ChangeFlow().computeChangeReturnType(context, oldContext);

            msgBody = "{\"list\":" + JSON.toJSONString(changeType) + "}";
        }

        //乙方结项文档
        else if (req.cmd.equals("pbToKnitProject")) {
            //加载当前阶段组长所要递交的全部文档
            int phase = req.msgBody.getInteger("phase");
            int newProjectId = req.msgBody.getInteger("newProjectId");
            int newTalkId = ProjectFlowService.getTalkService().getProjectTalkByProjectId(newProjectId).getTalkId();

            ProjectChangeTaskViewResult list = new ChangeTaskViewTalk().getPbKnitChangeTask
                    (context, newTalkId, loginUserId, phase);

            msgBody = "{\"list\":" + JSON.toJSONString(list) + "}";
        }

        //组员结项文档
        else if (req.cmd.equals("pmKnitProView")) {
            int phase = req.msgBody.getInteger("phase");
            int newProjectId = req.msgBody.getInteger("newProjectId");
            int newTalkId = ProjectFlowService.getTalkService().getProjectTalkByProjectId(newProjectId).getTalkId();
            //加载当前阶段组员所要递交的全部文档
            ProjectChangeTaskViewResult list = new ChangeTaskViewTalk().getPmKnitChangeTask(context, newTalkId, phase);
            msgBody = "{\"list\":" + JSON.toJSONString(list) + "}";
        }


        //递交结项文档（PB---PA）
        else if (req.cmd.equals("pbSubmitKnitDoc")) {
            //--- 负责人提交结项文档给甲方
            ProjectEventModel pe = flowFolderView.pbSubmitKnitDoc(context, talkId);
            msgBody = "{'projectEventModel':" + pe + "}";
        }


        //递交结项文档（PM---PB）
        else if (req.cmd.equals("pmSubmitKnitDoc")) {

            flowFolderView.pmSubmit(context, loginUserId);
        }

        //乙方审核组员递交结项文档
        else if (req.cmd.equals("pbAuditKnitDoc")) {
            int status = req.msgBody.getInteger("status");
            String pmUserId = req.msgBody.getString("userId");

            flowFolderView.pbAudit(context, pmUserId, talkId, status, "change");
        }


        //甲方审核结项文档
        else if (req.cmd.equals("paAuditKnitDoc")) {
            int status = req.msgBody.getInteger("status");
            ProjectEventModel pe = flowFolderView.paAuditKnitDoc(context, talkId, status);
            msgBody = "{'projectEventModel ':" + pe + "}";
        }


        //partyAPayProChange partyAPayBigChange 项目变更支付，普通变更阶段支付
        else if (req.cmd.equals("paPayChange")) {
            String password = req.msgBody.getString("password");
            Integer newTalkId = req.msgBody.getInteger("newTalkId");
            UserProjectContext newContext = new UserProjectContext(context, newTalkId);
            ViewPayResult viewPayResult = new FlowPayChange().paPayChange(newContext, context, password);
            msgBody = "{\"payResult\":" + viewPayResult.toString() + "}";
        }


        //取消变更
        else if (req.cmd.equals("cancelChange")) {
            new ChangeFlow().cancelChange(context);

        }

        //变更里面分享
        else if (req.cmd.equals("addShareChange")) {
            String userId = req.msgBody.getString("userId");
            int templateId = req.msgBody.getInteger("templateId");
            ProjectModel pm = context.getProjectModel();
            String[] userIdArry = userId.split(",");
            List<ProjectTemplateUserModel> list = new ArrayList<>();
            for (int i = 0; i < userIdArry.length; i++) {
                ProjectTemplateUserModel modle = new ProjectTemplateUserModel();
                modle.setUserId(Integer.parseInt(userIdArry[i]));
                modle.setDisplayStatus(0);
                modle.setTemplateId(templateId);
                modle.setProjectId(pm.getProjectId());
                list.add(modle);
            }

            new ProjectTemplateUser().modifyProjectTemplateUserBatch(list);

        } else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }
}
