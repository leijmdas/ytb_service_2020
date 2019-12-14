package ytb.project.service.impl.flow;

import com.alibaba.fastjson.JSONObject;
import ytb.project.common.ProjectCommonUtils;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ProjectInviteDAOService;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.PBFolder;
import ytb.project.service.IFlowTalk;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.impl.pay.payassist.paytalk.PayTalkRequest;
import ytb.project.service.project.Notify;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.user.model.CompanyInfoModel;
import ytb.user.model.UserInfoModel;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

//洽谈
public class FlowTalk extends ProjectFileServiceImpl implements IFlowTalk {
    public static class ApplyTalkEndResult extends Ytb_ModelSkipNull {

        String projectName;
        String nickName;
        String companyName;

        int talkId;
        Integer fee;

        public int getTalkId() {
            return talkId;
        }

        public void setTalkId(int talkId) {
            this.talkId = talkId;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public Integer getFee() {
            return fee;
        }

        public void setFee(Integer fee) {
            this.fee = fee;
        }

    }

    //乙方提交洽谈模板文档
    public SubmitTalkResult pbTalkSubmitTemplate( UserProjectContext context ) throws IOException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        if (ptm.getPhase() != ProjectConst.TalkPhase || ptm.getEventType() == ProjectConst.FOLDER_STATUS_PASS_PB) {
            throw new YtbError(YtbError.CODE_USER_ERROR, "你不在洽谈中(或者已经提交审核)，不需要提交审核！");
        }
        //检查有费用数据
        ptm.checkExistCost(context);

        PBFolder pbFolder = context.getViewProjectFolderModel().getpBFolder();
        List<ProjectTemplateModel> templateModels =  pbFolder.pbSubmitTalkFolder(context);

        ptm.setPhaseStatus(ProjectConst.TalkPhase_STATUS_PA_AUDIT);//洽谈审核中
        ptm.setEventType(ProjectConst.FOLDER_STATUS_SUBMIT_PB);
        pm.getProjectTalkService().modifyPhaseStatusEventType(ptm);
        ptm.addTalkEvent(context, "提交需求书文档,工作组计划", ProjectConst.FOLDER_STATUS_SUBMIT_PB, ptm.getUserId2(), pm.getUserId1());

        return new SubmitTalkResult(context);

    }


    //甲方审核洽谈文档
    public List<ProjectTemplateModel> paTalkAuditTemplate(UserProjectContext context, int evenTypeAudit) {
        /**
         *      status 对应的状态
         *
         * 1 ----- FOLDER_STATUS_WRITE_PM = 1;文件夹编写状态
         * 2 ----- FOLDER_STATUS_SUBMIT_PM = 2;文件夹组员提交组长审核 提交
         * 3 ----- FOLDER_STATUS_PASS_PM = 3;文件夹审核 组长审核通过
         * 4 ----- FOLDER_STATUS_NOTPASS_PM = 4;文件夹审核 组长审核不过
         * 5 ----- FOLDER_STATUS_SUBMIT_PB = 5;组长提交甲方审核
         * 6 ----- FOLDER_STATUS_PASS_PB = 6;甲方审核通过
         * 7 -----  FOLDER_STATUS_NOTPASS_PB = 7;甲方审核不过 */
        if (evenTypeAudit != ProjectConst.FOLDER_STATUS_PASS_PB && evenTypeAudit != ProjectConst.FOLDER_STATUS_NOTPASS_PB) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
        }

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        if (ptm.getPhase() != ProjectConst.TalkPhase
                || ptm.getPhaseStatus() != ProjectConst.TalkPhase_STATUS_PA_AUDIT
                || ptm.getEventType() != ProjectConst.FOLDER_STATUS_SUBMIT_PB) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "乙方不在洽谈需要审核状态中！");
        }

        PBFolder pbFolder = context.getViewProjectFolderModel().getpBFolder();
        List<ProjectTemplateModel> templateModels = pbFolder.paAuditTalkFolder(context, evenTypeAudit);

          boolean isPass = evenTypeAudit == ProjectConst.FOLDER_STATUS_PASS_PB;
        if (isPass) {  //审核通过
            ptm.setPhaseStatus(ProjectConst.TalkPhase_STATUS_PAY);
            ptm.setEventType(ProjectConst.FOLDER_STATUS_PASS_PB);
            pm.getProjectTalkService().confirmTalk(ptm);
        } else { //审核不通过
            ptm.setPhaseStatus(ProjectConst.TalkPhase_STATUS);
            ptm.setEventType(ProjectConst.FOLDER_STATUS_NOTPASS_PB);
            pm.getProjectTalkService().modifyPhaseStatusEventType(ptm);
        }
        ptm.addTalkEvent(context,
                isPass ? "审核需求书文档,工作组计划通过" : "审核需求书文档,工作组计划不通过",
                isPass ? ProjectConst.TASK_STATUS_Passing_PA : ProjectConst.TASK_STATUS_NotPassing_PA,
                ptm.getUserId2(), pm.getUserId1());

        return templateModels;

    }


/*    //甲方确认文档(洽谈确认)
    void paConfirmTalkAndModifyStatus(UserProjectContext context, int talkId) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        pm.getProjectTalkService().modifyPhaseStatusEventType(ProjectConst.TalkPhase_STATUS_PAY,
                ProjectConst.FOLDER_STATUS_PASS_PB, ptm.getTalkId());

        ptm.addTalkEvent(context, "审核需求书文档,工作组计划通过", ProjectConst.TASK_STATUS_Passing_PA, ptm.getUserId2(), pm.getUserId1());

        pm.getProjectTalkService().confirmTalk(ptm);

    }*/

    //申请洽谈中止
    public ApplyTalkEndResult applyTalkTerm(UserProjectContext context,int talkId){

        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();

        int userId = context.isPa() ? ptm.getUserId2() : pm.getUserId1();
        int companyId = context.isPa() ? ptm.getCompanyId2() : pm.getCompanyId1();

        ApplyTalkEndResult talkEndResult = new ApplyTalkEndResult();
        talkEndResult.setProjectName(pm.getProjectName());
        talkEndResult.setTalkId(ptm.getTalkId());

        UserInfoModel userInfoModel = getInst().getUserCenterService().getUserInfoById(userId);
        talkEndResult.setNickName(userInfoModel.getNickName());
        int talkFee = context.isPa() ?  ProjectCommonUtils.getAssistMoney(userInfoModel.getCreditGrade().trim()):0;

        if (companyId > 0) {
            CompanyInfoModel companyInfoModel = getInst().getCompanyCenterService().getCompanyInfoById(companyId);
            talkEndResult.setCompanyName(companyInfoModel.getCompanyName());
        }
        talkEndResult.setFee(talkFee);
        return talkEndResult;
    }

    //乙方洽谈中止
    public SubmitTalkResult talkTerm( UserProjectContext context, int talkId, String notify, String remark ) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        if ( ptm.getPhase() != ProjectConst.TalkPhase ) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "乙方不在洽谈态中不需要中止！");
        }
        if ( ptm.getPhase() == ProjectConst.TalkPhase
                && ptm.getChangeStatus()==ProjectConstState.CHNAGE_TYPE_TALK_TERM) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "已经是洽谈中止状态！");
        }

        PayTalkRequest payTalkRequest = new PayTalkRequest();
        ptm.setChangeStatus(ProjectConstState.CHNAGE_TYPE_TALK_TERM);
        ptm.setRemark(remark);
        ptm.setPhaseStatus(ProjectConst.TalkPhase_STATUS);
        pm.getProjectTalkService().modifyTalkRemark(ptm);
        int userId = context.getUserId();

        List<ViewProjectTemplateUserModel> lstPa = IProjectFileDAOService.getProjectTemplateUser().select(pm.getFolderId(), userId);
        for (ViewProjectTemplateUserModel vptu : lstPa) {
            IProjectFileDAOService.getProjectTemplateUser().modifyByTalkEndStatus(context, vptu
                    .getTemplateId());
        }

        ProjectFolderModel projectFolder = ptm.fetchTalkFolderModel();
        List<ViewProjectTemplateUserModel> lstPb = IProjectFileDAOService.getProjectTemplateUser().select(projectFolder.getFolderId(), userId);
        for (ViewProjectTemplateUserModel vptu : lstPb) {
            IProjectFileDAOService.getProjectTemplateUser().modifyByTalkEndStatus(context,vptu
                    .getTemplateId());
        }

        if (context.isPb()) {//乙方
            ptm.addTalkEvent(context, "洽谈终止", 6, ptm.getUserId2(), pm .getUserId1() );
            Notify projectNotify = new Notify();
            JSONObject jsonObject = new JSONObject();
            StringBuilder  sb = new StringBuilder(notify);
            sb.append(" 说明:").append(remark);
            jsonObject.fluentPut("params1", sb.toString());
            projectNotify.sendNotify(ptm.getUserId2(), pm.getUserId1(), 1, ProjectConstState.PROJECT_TalkEndNotify, "", jsonObject);

            //乙方终止洽谈取消预留的冻结款感谢金
            ProjectInviteDAOService projectInviteDAOService = new ProjectInviteDAOService();
            talkId = (projectInviteDAOService.getProjectInviteByTalkId(talkId).getPhaseStatus() == 1) ? 0:talkId;
            ProjectTradeModel projectTradeModel  = payTalkRequest.getPayTradeService().selectOneByProjectTalk(pm.getProjectId(), talkId);
            List<UserAssistModel> payList = payTalkRequest.queryAssistMoneies(projectTradeModel.getTradeId());
            payTalkRequest.pbStopTalk_payCancel(context,projectTradeModel.getTradeId(),payList);
        }
        else {//甲方
            ptm.addTalkEvent(context, "洽谈终止", 6, pm.getUserId1(), ptm.getUserId2() );
            Notify projectNotify = new Notify();
            JSONObject jsonObject = new JSONObject();
            StringBuilder sb = new StringBuilder(notify);
            sb.append(" 说明:").append(remark);
            jsonObject.fluentPut("params1", sb.toString());
            projectNotify.sendNotify(pm.getUserId1(), ptm.getUserId2(), 1, ProjectConstState.PROJECT_TalkEndNotify, "", jsonObject);

            //甲方终止洽谈支付感谢金
            ProjectInviteDAOService projectInviteDAOService = new ProjectInviteDAOService();
            talkId = (projectInviteDAOService.getProjectInviteByTalkId(talkId).getPhaseStatus() == 1) ? 0:talkId;
            ProjectTradeModel projectTradeModel  = payTalkRequest.getPayTradeService().selectOneByProjectTalk(pm.getProjectId(), talkId);
            List<UserAssistModel> payList = payTalkRequest.queryAssistMoneies(projectTradeModel.getTradeId());
            payTalkRequest.paStopTalk_payConfirm(context,projectTradeModel.getTradeId(),ProjectConst.TEMPORARY_PASSWORD,payList);

        }
        SubmitTalkResult talkResult = new SubmitTalkResult(context);
        talkResult.setProjectId(pm.getProjectId());
        talkResult.setTalkId(talkId);
        talkResult.setPhase(ProjectConst.TalkPhase);
        talkResult.setChangeStatus(ProjectConstState.CHNAGE_TYPE_TALK_TERM);
        talkResult.setPhaseStatus(0);

        return talkResult;
    }

}
