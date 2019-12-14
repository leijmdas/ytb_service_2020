package ytb.project.service.impl.release;

import com.alibaba.fastjson.JSONObject;
import ytb.project.common.ProjectCommonUtils;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.*;
import ytb.project.daoservice.ProjectReleaseDAOService;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.PAFolder;
import ytb.project.model.projectFolderView.PBFolder;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUser;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUserControl;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.impl.pay.payassist.paytalk.PayTalkApply;
import ytb.project.service.impl.pay.payassist.paytalk.PayTalkInvite;
import ytb.project.service.impl.pay.payassist.paytalk.ProjectPayTalk;
import ytb.project.service.impl.pay.projectpay.ProjectPay;
import ytb.project.service.project.Notify;
import ytb.project.service.project.request.ProjectInvite;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.user.model.UserInfoModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReleaseService extends ProjectReleaseDAOService {

    List<Integer> addTalkByFriends(UserProjectContext context, ProjectModel pm, String friends) throws UnsupportedEncodingException {

        List<Integer> inviteIds = new ArrayList<>();
        if (friends == null || friends.isEmpty()) {
            return inviteIds;
        }

        List<UserAssistModel> assistModels = new ArrayList<>();

        for (String sfriendId : friends.split(",")) {
            int  friendId=Integer.valueOf(sfriendId);
            if(friendId== pm.getUserId1()){
                continue;
            }

            ProjectInviteModel pim = new ProjectInviteModel();
            pim.setProjectId(pm.getProjectId());
            pim.setPhase(ProjectConst.RequestIn);//-1为草稿状态
            pim.setPhaseStatus(ProjectConstState.PROJECT_INVITE);//1:邀请;2:申请
            pim.setEventType(ProjectConstState.INVITE_STATUS_YES);
            pim.setUserId2(friendId);
            pim.setCompanyId2(0);
            int inviteId = inviteDAOService.addProjectInvite(pim);//添加邀请或者申请
            inviteIds.add(inviteId);

            UserInfoModel userInfoModel = getInstUser().getUserCenterService().getUserInfoById(friendId);

            UserAssistModel assistModel = new UserAssistModel();
            assistModel.setUserId(friendId);
            assistModel.setUserName(userInfoModel.getNickName());
            assistModel.setTalkId(inviteId);
            assistModel.setFlag(UserAssistModel.FLAG_GORUP_MEMBER_NONE);
            assistModel.setMoney(ProjectCommonUtils.getAssistMoney(userInfoModel.getCreditGrade()));
            assistModels.add(assistModel);
        }
        //检查余额足够
        if(!new ProjectPay().checkAccountEnouph(context,assistModels)){
            throw new YtbError(YtbError.CODE_USER_ERROR, "甲方余额不够无法支付洽谈预留金，需要充值！");
        }
        //预留洽谈感谢金
        new PayTalkInvite().paInvite_payPre(context, ProjectConst.TEMPORARY_PASSWORD, 0, assistModels);

        return inviteIds;
    }


    public ReleaseResult releaseProject(UserProjectContext context, String friends) throws UnsupportedEncodingException {
        return releaseProject(0, context, friends);
    }

    // 发布项目草稿
    ReleaseResult releaseProject(int reqTemplateId, UserProjectContext context, String friendsId) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();

        Dict_ProjectTypeModel dict_ptm = context.getDict_ProjectTypeModel();
        if (!dict_ptm.isPublish()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目分类未发布！");
        }
        if (!context.existsDict_ProjectTypeModel(dict_ptm.getProjectTypeId())) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目分类不存在！");
        }

        if ("".equals(pm.getProjectName())) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目名称不能为空！");
        }


        // 添加项目project
        pm.setEnterTime(new Date());
        pm.setStatus(ProjectConstState.PUBLISH_AUDIT);
        pm.setPhaseNo(pm.buildPhaseNo(dict_ptm.getProjectType()));
        getProjectDAOService().addProject(pm);

        // 生成项目邀请
        List<Integer> lst = addTalkByFriends(context, pm, friendsId);

        ReleaseResult result = new ReleaseResult();
        result.setProjectModel(pm);
        result.setLstTalkId(lst);
        ProjectContext pc = new ProjectContext(context);
        PAFolder pAFolder = context.getViewProjectFolderModel().getpAFolder();
        // copyFolderPA publish
        List<ProjectTemplateModel> paModels = pAFolder.copyFolder(reqTemplateId, context, pc);
        result.modifyRelaseResult(paModels);

        return result;
    }

    //确认项目发布与重新发布，停止发布
    public String releaseProjectNext(UserProjectContext context, int publishState, int templateId,
                                     Boolean isTaskPublish) {
        ProjectModel pm = context.getProjectModel();
        if (pm.getUserId1() != context.getUserId()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你不是甲方，不能发布项目！");
        }

        if (publishState == ProjectConstState.PUBLISH_NOPASS) {//3
            pm = getProjectDAOService().getProjectById(pm.getProjectId());
            getProjectDAOService().modifyProjectStatus(publishState, pm.getProjectId());
        }
        //发布
        else if (publishState == ProjectConstState.PUBLISH_PASS) {//4
            pm.addProjectEvent(context, context.getNickName() + "-发布项目：" + pm.getProjectName(), 0, pm.getUserId1
                    (), 0);

            getProjectDAOService().modifyProjectStatus(publishState, pm.getProjectId());

        } else if (publishState == ProjectConstState.PUBLISH_STOP) {//5
            if (pm.getStatus() == ProjectConstState.PUBLISH_PASS) {
                getProjectDAOService().modifyProjectStatus(publishState, pm.getProjectId());
                IProjectFileDAOService.getProjectTemplateUser().versionUpgrade(templateId, 0);//文档版本升级
            }
        } else if (publishState == ProjectConstState.PUBLISH_RE_PUBLISH) {//6
            if (pm.getStatus() == ProjectConstState.PUBLISH_STOP) {
                getProjectDAOService().modifyProjectStatus(ProjectConstState.PUBLISH_PASS, pm.getProjectId());
            }
        } else {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
        }

        return pm.getProjectName() + "-需求说明书V";
    }


    //reqTemplateId需求模板书

    public ReleaseResult releaseProjectPp(UserProjectContext context, ReleaseInfo releaseInfo, String friendss) throws UnsupportedEncodingException {
        ProjectModel pm = new ProjectModel();
        pm.setProjectName(releaseInfo.getProjectName());
        pm.setProjectTypeId(releaseInfo.getProjectTypeId());
        pm.setIsPublish(true);
        pm.setInvite(false);
        pm.setUserId1(context.getUserId());
        pm.setCompanyId1(context.getCompanyId());
        context.setProjectModel(pm);
        context.buildDict_ProjectTypeModel();
        context.setProjectModel(pm);
        ReleaseResult projectResult = releaseProject(releaseInfo.getReqTemplateId(), context, friendss);
        int projectId = projectResult.getProjectModel().getProjectId();
        projectResult.setNewProjectId(projectId);
        //save projectId to custom_task
        getInst().getProjectFlowService().modifyCustomTask(releaseInfo.getCustomTaskid(), projectId);
        return projectResult;
    }


    //审核项目发布
    public void auditProject(int projectId, int status) {
        ProjectModel pm = new ProjectModel();
        String params = "";
        if (status == 4) {
            params = "通过";
        } else {
            params = "不通过";
        }
        if (status == 4) {
            List<ProjectTalkModel> list = pm.getProjectTalkService().getProjectTalk(projectId);
            for (ProjectTalkModel ptm : list) {
                ptm.setPhase(0);//把项目洽谈状态都改为邀请,申请中
                pm.getProjectTalkService().modifyTalkPhase(0, ptm.getTalkId());
            }
            getProjectDAOService().modifyProjectStatus(status, projectId);
        }
        Notify projectNotify = new Notify();
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("params1", pm.getProjectName());
        jsonObject.fluentPut("params2", params);
        projectNotify.sendNotify(0, pm.getUserId1(), 1, ProjectConstState.PROJECT_CheckNotify, "", jsonObject);
    }

    void applyProject_check(UserProjectContext context) {
        if (context.isPa()) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "不能申请自己的项目!");
        }

        ProjectModel pm = context.getProjectModel();
        List<ProjectInviteModel> inviteModels = inviteDAOService.getProjectInvitesByPB(pm.getProjectId(),
                context.getUserId());
        if (inviteModels.size() > 0) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "不能重复申请!");
        }

    }

    //申请项目
    public int applyProject(UserProjectContext context, String remark) {
        applyProject_check(context);

        ProjectModel pm = context.getProjectModel();
        ProjectInviteModel pim = new ProjectInviteModel();
        pim.setProjectId(pm.getProjectId());
        pim.setPhase(ProjectConst.TalkDraft);//-1为草稿状态
        pim.setPhaseStatus(ProjectConstState.PROJECT_APPLY);//1:邀请;2:申请
        pim.setEventType(ProjectConstState.INVITE_STATUS_YES);
        pim.setUserId2(context.getUserId());
        pim.setCompanyId2(context.getCompanyId());
        pim.setRemark(remark);
        inviteDAOService.addProjectInvite(pim);//往洽谈表添加记录

        Notify projectNotify = new Notify();
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("params1", context.getNickName()).fluentPut("params2", pm.getProjectName());
        //发送任务通知
        projectNotify.sendNotify(context.getUserId(), pm.getUserId1(), 1, ProjectConstState.PROJECT_InviteNotify, "", jsonObject);

        return pim.getTalkId();
    }

    int confirmProjectApply_addTalkByInvite(UserProjectContext context, int talkId) {

        ProjectInviteModel inviteModel = inviteDAOService.getProjectInviteByTalkId(talkId);
        ProjectFlowService.getTalkService().addProjectTalk(new ProjectTalkModel(inviteModel));
        //上下文buildmodel
        context.buildModel(talkId);
        return talkId;
    }

    void confirmProjectApply_check(UserProjectContext context) {

        ProjectInviteModel pim = context.getProjectInviteModel();
        context.buildModelProject(pim.getProjectId());

        if (pim.getPhaseStatus() == ProjectConstState.PROJECT_INVITE
                && !pim.getUserId2().equals(context.getUserId())) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目邀请，只有乙方才可以确认通过！");
        }
        if (pim.getPhaseStatus() == ProjectConstState.PROJECT_APPLY
                && !context.getUserId().equals(context.getProjectModel().getUserId1())) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目申请，只有甲方才可以确认通过！");
        }
        if (pim.getPhase() != ProjectConst.RequestIn
                && pim.getEventType() != ProjectConstState.INVITE_STATUS_YES) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目邀请（申请）状态不正确，无法确认通过！");
        }

    }

    //审核 项目邀请,申请copyFolderPB_confirmApply
    public ProjectContext confirmProjectApply(UserProjectContext context, int eventType) throws UnsupportedEncodingException {

        //检查合法性:
        confirmProjectApply_check(context);
        ProjectInviteModel pim = context.getProjectInviteModel();

        //修改状态
        inviteDAOService.updateInviteEventType(pim.getTalkId(), eventType);
        if (eventType == ProjectConstState.INVITE_STATUS_REJECT) {//拒绝邀请
            if (pim.isInvite()) {//邀请
                //拒接邀请，取消感谢金预留
                ProjectPayTalk payTalk = new ProjectPayTalk();
                PayTalkInvite payTalkInvite = new PayTalkInvite();
                ProjectTradeModel projectTradeModel =
                        payTalk.getPayTradeService().selectOneByProjectTalk(pim.getProjectId(), pim.isInvite() ? 0 : pim.getTalkId());

                //UserAssistModel model = payTalk.queryAssistMoney(projectTradeModel.getTradeId(), pim.getUserId2());
                payTalkInvite.pbCancelInvite_payCancel(context, projectTradeModel.getTradeId(), ProjectConst.TEMPORARY_PASSWORD);
            }

            return new ProjectContext(context);
        }
        if (pim.isApply()) {//申请

            PayTalkApply payTalkApply = new PayTalkApply();
            UserCenterService userCenterService = new UserCenterServiceImpl();
            List<UserAssistModel> userAssistModelList = new ArrayList<>();

            ProjectTalkModel ptm = context.getProjectTalkModel();
            UserInfoModel userInfoModel = userCenterService.getUserInfoById(ptm.getUserId2());

            UserAssistModel userAssistModel = new UserAssistModel();
            userAssistModel.setTalkId(pim.getTalkId());
            userAssistModel.setUserName(context.getNickName());
            userAssistModel.setFlag(2);
            userAssistModel.setUserId(ptm.getUserId2());
            userAssistModel.setMoney(ProjectCommonUtils.getAssistMoney(userInfoModel.getCreditGrade()));
            userAssistModelList.add(userAssistModel);

            payTalkApply.paConfirmApply_payPre(context, ProjectConst.TEMPORARY_PASSWORD, 0, userAssistModelList);
        }


        //添加洽谈
        confirmProjectApply_addTalkByInvite(context, pim.getTalkId());
        //------------ ---同意邀请,申请
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        int groupId = ProjectTalkModel.getWorkGroup().createNewWorkGroup(context);

        //乙方拷贝生成文件夹和需求书,工作组计划
        PBFolder pBFolder = context.getViewProjectFolderModel().getpBFolder();
        ProjectContext pc = pBFolder.copyFolder(context);

        //修改洽谈表的阶段，工作计划ID,还有工作组
        ptm.setFolderId(pc.getFolderId());
        ptm.setGroupId(groupId);
        ptm.setWorkplanId(pc.getWorkplanId());
        pm.getProjectTalkService().modifyTalkConfirm(ptm);
        ProjectTalkModel.getWorkGroup().modifyGroupAndMemberDocumentID(ptm);

        //乙方同意邀请
        ptm.addTalkEvent(context, context.isPb() ? "乙方同意邀请" : "甲方同意申请",
                context.isPb() ? 11 : 12, context.getUserId(), pm.getUserId1());

        //甲方同意申请 PA获取项目文件夹下的文档
        //PB获取项目洽谈文件夹下的文档
        new ProjectTemplateUserControl().addProjectTemplateUserConfirmProjectApply(context, pc);

        return pc;
    }

    public void inviteMember(UserProjectContext context,  String userId, int documentId) {

        new ProjectInvite().addTalkInviteStatus(context, userId, documentId);
    }


    public void confirmInvitePm(UserProjectContext context, int projectId, int eventType, int userId) throws UnsupportedEncodingException {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        // 获取洽谈信息
        ProjectInvite projectInvite = new ProjectInvite();

        TalkInviteStatusModel talkInviteStatus = projectInvite.getTalkInviteStatus(projectId, userId, ptm.getWorkplanId());

        talkInviteStatus.setStatus(eventType);
        projectInvite.modifyStatus(talkInviteStatus);


        if (eventType == 2) {
            WorkGroupMemberModel workGroupMember = ProjectTalkModel.getWorkGroup().getWorkGroupMember(ptm.getProjectId(), userId, ptm.getWorkplanId());//获取工作组成员
            if (workGroupMember == null) {
                throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
            }
            //添加组员控制权限
            List<ProjectTemplateModel> projectTemplateList = ptm.selectTalkTemplates();
            for (ProjectTemplateModel templateModel : projectTemplateList) {
                new ProjectTemplateUser().add(context, userId, templateModel.getTemplateId(),
                        ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_GoRead, 1, ptm.getUserId2());
            }
            ProjectTalkModel.getWorkGroup().modifyGroupMemberActive(1, workGroupMember.getMemberId());
        }
    }


}
