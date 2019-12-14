package ytb.project.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbUtils;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.DAOService;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUserControl;
import ytb.project.model.projectFolderView.templateFolder.TemplateFolderModel;
import ytb.project.service.*;
import ytb.project.service.impl.pay.IFlowPay;
import ytb.project.service.impl.pay.payassist.ProjectPayAssist;
import ytb.project.service.template.TemplateFolderService;
import ytb.project.service.template.HistoryTemplate;
import ytb.project.service.impl.flow.FlowFolder;
import ytb.project.service.impl.flow.FlowTalk;
import ytb.project.service.impl.pay.PayResult;
import ytb.project.view.model.*;
import ytb.project.view.model.ProjectTaskViewModel.ViewTaskModel;
import ytb.project.view.service.impl.ProjectAssistView;
import ytb.common.RestMessage.MsgBody;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class ProjectFlow extends DAOService {



    public MsgResponse process(MsgRequest req, MsgHandler handler) throws IOException {

        ProjectFlowService projectFlowService = getInst().getProjectFlowService();
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

        //项目中心任务视图
        if (req.cmd.equals("getProjectTask")) {

            String type = req.msgBody.getString("type");
            int phase = req.msgBody.getInteger("stage");

            ProjectTaskViewResult result = getInst().getProjectTaskView().getProjectTaskView(
                    context, loginUserId, phase, type);

            msgBody = result.toString();

        }

        //查询项目分类列表（项目中心页面列表数据展示）
        else if (req.cmd.equals("getProjectTaskTab")) {

            Map<String, Object> resultViewModel = flowFolderView.getProjectTab(context);
            msgBody = "{\"list\":[" + YtbUtils.toJSONStringSkipNull(resultViewModel) + "]}";
        }

        //查看看人的任务
        else if (req.cmd.equals("viewTask")) {
            //要查看人的 任务
            int toUserId = req.msgBody.getInteger("userId");
            int phase = req.msgBody.getInteger("stage");

            ProjectTaskViewResult result = getInst().getProjectTaskView().viewOthersTask(
                    context, loginUserId, toUserId, phase);

            msgBody = result.toString();

        }

        //查看协助文档的状态控制List
        else if (req.cmd.equals("getUserAssitsTemplateList")) {

            int templateId = req.msgBody.getInteger("templateIdIni");
            //1发起协助方    2:协助接收方
            int type = req.msgBody.getInteger("assistType");

            ProjectAssitsViewResult viewResult = new ProjectAssistView()
                    .getProjectAssitsView(context, templateId, loginUserId, type);

            msgBody = YtbUtils.toJSONString(viewResult);
        }


        //文件库
        else if (req.cmd.equals("getFolders")) {
            int phase = req.msgBody.getInteger("phase");
            msgBody = flowFolderView.getFolders(context, loginUserId, talkId, phase);

        } else if (req.cmd.equals("getFoldersNew")) {
            TemplateFolderModel dm = JSONObject.parseObject(req.getMsgBody().toJSONString(), TemplateFolderModel.class);
            List<ProjectTemplateModel> lst = new TemplateFolderService()
                    .getFolderTemplates(context, dm);

            MsgBody m = new MsgBody();
            m.getList().addAll(lst);
            msgBody = m.toString();

        }

        //获取项目过程记录
        else if (req.cmd.equals("getProjectEvent")) {
            int stage = req.msgBody.getInteger("stage");
            List<Map<String, Object>> eventList = projectFlowService.getProjectEventList(context, talkId, stage);
            msgBody = "{'list':" + YtbUtils.toJSONString(eventList) + "}";

        }

        //---- ---提交审核 乙方提交文档甲方审核(洽谈阶段文档)
        else if (req.cmd.equals("pbSubmitTalkDoc")) {

            IFlowTalk.SubmitTalkResult talkResult = getInst().getFlowTalk().pbTalkSubmitTemplate(context);
            msgBody = "{list:[" + talkResult + "]}";

        }

        //洽谈阶段甲方审核文件
        else if (req.cmd.equals("verifyProjectDoc")) {
            //---------- -----甲方审核 ;
            int status = req.msgBody.getInteger("status");

            List<ProjectTemplateModel> lst = getInst().getFlowTalk().paTalkAuditTemplate(context, status);
            msgBody = "{list:" + YtbUtils.toJSONString(lst) + "}";

        }

        //甲方点击终止洽谈
        else if (req.cmd.equals("applyTalkEnd")) {
            FlowTalk.ApplyTalkEndResult r = getInst().getFlowTalk().applyTalkTerm(context, talkId);
            msgBody = "{\"list\":" + r + "}";

        }

        //终止洽谈
        else if (req.cmd.equals("talkTermination")) {
            String type = req.msgBody.getString("type");//PA  PB
            String notify = req.msgBody.getString("Notify");
            String description = req.msgBody.getString("description");
            IFlowTalk.SubmitTalkResult r = getInst().getFlowTalk().talkTerm(context, talkId, notify, description);
            msgBody = "{\"list\":" + JSON.toJSONString(r) + "}";

        } else if (req.cmd.equals("confirmPay")) {
            ProjectContext pc = new ProjectContext(context);
            context.getViewProjectFolderModel().getpBFolder().copyFolder_phaseStart(context, pc,
                    context.getProjectModel().getPhaseStart());
            msgBody = "{\"list\":[" + YtbUtils.toJSONString(pc) + "]}";

        }


        //- ---------组员提交给负责人
        else if (req.cmd.equals("memberSubmit")) {
            flowFolderView.pmSubmit(context, loginUserId);

        }
        //组员递交组长审核
        else if (req.cmd.equals("memberSubmitReady")) {
            msgBody = flowFolderView.pmSubmitPrepare(context, loginUserId);

        }
        //-----负责人审核
        else if (req.cmd.equals("principalVerify")) {
            int status = req.msgBody.getInteger("status");
            String pmUserId = req.msgBody.getString("userId");

            flowFolderView.pbAudit(context, pmUserId, talkId, status, "");

        }

        //乙方递交阶段文件审核
        else if (req.cmd.equals("principalSubmit")) {

            //--- 负责人Phase提交给甲方
            ProjectEventModel pe = flowFolderView.pbPhaseSubmit(context, talkId);
            msgBody = "{'projectEventModel':" + pe + "}";

        }

        //甲方审核阶段递交文档
        else if (req.cmd.equals("PartyAVerify")) {//甲方审核

            int status = req.msgBody.getInteger("status");
            String passWord = req.msgBody.getString("passWord");
            ProjectEventModel pe = flowFolderView.paPhaseAudit(context, talkId, status, passWord);
            msgBody = "{'projectEventModel':" + pe + "}";

        } else if (req.cmd.equals("documentAssistance")) {

            int auditor = req.msgBody.getInteger("auditor");
            int templateId = req.msgBody.getInteger("docListId");
            flowFolderView.sendAssistTemplate(templateId, auditor);

        }

        //-----点击支付(洽谈阶段)
        else if (req.cmd.equals("payProject")) {
            String password = req.msgBody.getString("password");
            PayResult payResult = flowPay.paPayOpenProject(context, password);
            msgBody = "{'payResult':" + payResult + "}";
        }

        //阶段支付
        else if (req.cmd.equals("payPhase")) {
            String password = req.msgBody.getString("passWord");
            PayResult payResult = flowPay.payPhase(context, talkId, password);

            msgBody = "{'payResult':" + payResult + "}";

        }



        else if (req.cmd.equals("getDefineTask")) {
            Map map = projectFlowService.getTask(context, loginUserId, talkId);
            msgBody = "{\"mapList\":" + JSON.toJSONString(map) + "}";

        }


        //显示材料总清单
        else if (req.cmd.equals("chooseTask")) {

            CustomTaskResult customTaskResult = projectFlowService.chooseTask(context);
            msgBody = "{\"list\":" + JSON.toJSONString(customTaskResult) + "}";

        }

        //生成加工清单
        else if (req.cmd.equals("makePuchaseDoc")) {

            int projectTypeId = req.msgBody.getInteger("projectTypeId");
            int docType = req.msgBody.getInteger("docType");//101：采购     102:加工
            CustomTaskResult customTaskResult = flowFolderView.copyTemplatePp(context, projectTypeId, docType);
            msgBody = "{\"list\":" + JSON.toJSONString(customTaskResult) + "}";

        } else if (req.cmd.equals("deleteDoc")) {
            int taskId = req.msgBody.getInteger("taskId");
            flowFolderView.delPurchaseTemplate(taskId);

        } else if (req.cmd.equals("getDocument")) {
            int projectTypeId = req.msgBody.getInteger("projectTypeId");
            flowFolderView.copyTemplatePpMList(context, projectTypeId, talkId, loginUserId);

        } else if (req.cmd.equals("goReleasePro")) {
            int projectTypeId = req.msgBody.getInteger("projectTypeId");
            msgBody = projectFlowService.goReleasePro(context, projectTypeId, talkId);

        }

        //项目中心视图修改文档状态
        else if (req.cmd.equals("modifyProjectTemplateUser")) {
            int templateId = req.msgBody.getInteger("templateId");
            int status = req.msgBody.getInteger("status");
            new ProjectTemplateUserControl().modifyProjectTemplateUserStatus(context, loginUserId, templateId, status);

        }

        else if (req.cmd.equals("checkDocList")) {
            int status = req.msgBody.getInteger("status");
            int templateId = req.msgBody.getInteger("docListId");
            flowFolderView.checkTemplateList(context, templateId, status, talkId);

        } else if (req.cmd.equals("modifyShipmentNumber")) {
            int taskId = req.msgBody.getInteger("taskId");
            String number = req.msgBody.getString("number");
            projectFlowService.modifyShipmentNumber(taskId, number);

        }
        //点击查看任务
        else if (req.cmd.equals("clickViewTask")) {
            List<Map<String, Object>> list = projectFlowService.clickViewTask(context, talkId);
            msgBody = "{\"list\":" + JSON.toJSONString(list) + "}";

        }

        //添加会议通知
        else if (req.cmd.equals("addMeetingTask")) {


            String participant = req.msgBody.getString("participant");
            String issue = req.msgBody.getString("issue");
            String startTime = req.msgBody.getString("startTime");
            String stopTime = req.msgBody.getString("stopTime");
            int docListId = req.msgBody.getInteger("docListId");
            projectFlowService.addMeetingTask(context, talkId, loginUserId, participant, issue, startTime, stopTime, docListId);

        }

        //添加自定义任务
        else if (req.cmd.equals("addCustomTask")) {
            String receiver = req.msgBody.getString("receiver");
            String remark = req.msgBody.getString("remark");
            int docListId = req.msgBody.getInteger("docListId");
            projectFlowService.addCustomTask(context, talkId, receiver, remark, docListId, loginUserId);

        } else if (req.cmd.equals("addShipmentNumber")) {
            String remark = req.msgBody.getString("remark");
            String number = req.msgBody.getString("number");
            String goodsName = req.msgBody.getString("goodsName");
            String documentName = req.msgBody.getString("documentName");
            int toUserId = req.msgBody.getInteger("toUserId");

            projectFlowService.addShipmentNumber(talkId, remark, number, goodsName, documentName, loginUserId, toUserId);

        }

        //添加文档共享
        else if (req.cmd.equals("addShareDoc")) {

            int toUserId = req.msgBody.getInteger("toUserId");
            int templateId = req.msgBody.getInteger("docListId");
            if (toUserId == loginUserId) {
                retmsg = "自己不能给自己分享";
                retcode = 1000;
                return handler.buildMsg(retcode, retmsg, msgBody);
            }

            flowFolderView.addShareTemplate(context, toUserId, loginUserId, templateId);

        }

        //取消共享
        else if (req.cmd.equals("cancelShareDoc")) {
            //cancelType =0取消分享  1=取消协助
            int templateId = req.msgBody.getInteger("docListId");
            int cancelType = req.msgBody.getInteger("cancelType");
            flowFolderView.cancelShareTemplate(templateId, cancelType);
        }

        else if (req.cmd.equals("selectTask")) {
            ViewTaskModel v = projectFlowService.selectTask(context, talkId, loginUserId);
            msgBody = "{\"list\":" + JSON.toJSONString(v) + "}";

        }

        //查看历史记录
        else if (req.cmd.equals("selectHistoryDoc")) {
            int templateId = req.msgBody.getInteger("docListId");
            List<ProjectTemplateVModel> projectTemplateModel = new HistoryTemplate().selectTemplateV(templateId);
            msgBody = "{\"list\":" + JSON.toJSONString(projectTemplateModel) + "}";

        }

        //显示协助用户的费用
        else if (req.cmd.equals("showUserAssistFee")) {
            String userIds = req.msgBody.getString("userIds");

            List<ytb.project.model.UserAssistModel> list = flowFolderView.showUserAssistFee(userIds, context);
            msgBody = "{\"list\":" + JSON.toJSONString(list) + "}";
        }

        //协助的支付
        else if (req.cmd.equals("payAssist")) {
            String payPassword = req.msgBody.getString("payPassword");
            Integer templateId = req.msgBody.getInteger("templateId");
            List<UserAssistModel> monie = req.msgBody.getJSONArray("list").toJavaList(UserAssistModel.class);

            int tradeId = new ProjectPayAssist().payAssistPre(context, payPassword, templateId, monie);
            msgBody = "{\"list\":[" + tradeId + "]}";

        }

        //添加协助
        else if (req.cmd.equals("addAssistDoc")) {

            String toUserId = req.msgBody.getString("toUserId");
            int templateId = req.msgBody.getInteger("docListId");
            String remark = req.msgBody.getString("remark");

            String str[] = toUserId.split(",");
            for (String toUserIds : str) {
                if (loginUserId.equals(Integer.parseInt(toUserIds))) {
                    throw new YtbError(YtbError.CODE_DEFINE_ERROR, "自己不能给自己协助");
                }
            }

            FlowFolder.AssistResult result = flowFolderView.addAssistTemplate(context,
                    templateId, toUserId, loginUserId, remark);
            msgBody = "{\"list\":[" + result.toString() + "]}";

        }

        //协助接收方修改控制状态
        else if (req.cmd.equals("assitsReceviceModifyTempStatus")) {

            //修改协助接收方文档状态控制
            Integer id = req.msgBody.getInteger("pkId");
            flowFolderView.receiverModifyTemplateUserStatuslAssist(id);
        }
        //协助接收方递交文档
        else if (req.cmd.equals("assitsReceviceSubmitDoc")) {

            Integer templateIdAssist = req.msgBody.getInteger("templateIdAssist");
            Integer id = req.msgBody.getInteger("pkId");
            Integer toUserId = req.msgBody.getInteger("auditor");
            flowFolderView.sponsorModifyTemplateUserStatusAssist(context, templateIdAssist, id, toUserId);

        }
        //协助发起方审核文档
        else if (req.cmd.equals("assitsSendAuditorDoc")) {
            int auditStatus = req.msgBody.getInteger("auditStatus");//6通过 7未通过
            Integer templateIdAssist = req.msgBody.getInteger("templateIdAssist");
            Integer id = req.msgBody.getInteger("pkId");
            flowFolderView.sponsorSubmitTemplateUserStatusAssist(context, templateIdAssist, id, auditStatus);
        }

        else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }

}
