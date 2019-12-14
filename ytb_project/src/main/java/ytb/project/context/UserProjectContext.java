package ytb.project.context;


import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbUtils;
import ytb.account.context.AccountSrvContext;
import ytb.manager.context.ManagerSrvContext;
import ytb.project.cache.IProjectCacheManager;
import ytb.project.daoservice.ChangeDaoServiceImpl;
import ytb.project.daoservice.ProjectInviteDAOService;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.ViewProjectFolderModel;
import ytb.project.service.IProjectFileService;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.impl.pay.payfee.ProjectRateTaxModel;
import ytb.project.service.impl.pay.payfee.ProjectTax;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.user.context.UserContext;
import ytb.user.context.UserSrvContext;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.model.YtbError;

/*
 * author leijm
 * date: 2019.2.3
 * */

public class UserProjectContext extends UserContext implements IUserProjectContext {
    public static UserSrvContext getInstUser() {
        return UserSrvContext.getInst();
    }

    public static ManagerSrvContext getInstMngr() {
        return ManagerSrvContext.getInst();
    }

    public static AccountSrvContext getInstAccount() {
        return AccountSrvContext.getInst();
    }

    public static ProjectSrvContext getInst() {
        return ProjectSrvContext.getInst();
    }

    public static IProjectFileService getProjectFileService() {
        return getInst().getProjectFileService();
    }

    public static IProjectCacheManager getProjectCacheManager() {
        return getInst().getProjectCacheManager();
    }

    ViewProjectFolderModel viewProjectFolderModel = new ViewProjectFolderModel();

    public IDocumentFamily getDocumentFamily() {
        return ProjectSrvContext.getInst().getDocumentFamily();
    }

    public UserProjectContext() {
    }

    public UserProjectContext(UserProjectContext other) {
        this.setLoginSso(other.getLoginSso());
    }

    public UserProjectContext(UserProjectContext other, int talkId) {
        this.setLoginSso(other.getLoginSso());
        this.buildModel(talkId);
        this.setTalkId(talkId);
    }

    //发布项目有用
    ProjectModel projectModel;
    //如果changeType>0则更新
    ProjectChangeModel projectChangeModel;

    Integer talkId; //同意洽谈才有
    ProjectTalkModel projectTalkModel;
    //申请洽谈有
    ProjectInviteModel projectInviteModel;

    Dict_ProjectTypeModel dict_ptm;
    Dict_TemplateModel dict_tm;

    public Dict_TemplateModel getDict_tm() {
        return dict_tm;
    }

    public static UserProjectContext pre(MsgHandler handler) {
        MsgRequest req = handler.req;

        UserProjectContext context = (UserProjectContext) handler.getUserContext();
        Integer talkId = req.msgBody.getInteger("talkId");

        Integer projectId = req.msgBody.getInteger("projectId");

        return context.refresh(context, talkId, projectId);
    }

    public UserProjectContext refresh() {
        return refresh(this, talkId, projectModel.getProjectId());
    }

    UserProjectContext refresh(UserProjectContext context, Integer talkId, Integer projectId) {

        if (talkId != null && talkId > 0) {
            context.buildModel(talkId);
            if (context.projectTalkModel == null) {
                context.buildModelInvite(talkId);
            }
        } else {
            //Integer projectId = projectModel.getProjectId();
            if (projectId != null) {
                context.buildModelProject(projectId);
            }
        }
        return context;
    }

    public ProjectRateTaxModel selectProjectRateTaxModel()    {

        return projectModel.selectProjectRateTaxModel(this);
    }

    public UserProjectContext buildModelInvite(int talkId) {
        this.talkId = talkId;
        projectInviteModel = new ProjectInviteDAOService().getProjectInviteByTalkId(talkId);
        if (projectInviteModel != null) {
            projectTalkModel = ProjectTalkModel.copyProjectTalkModel(projectInviteModel);
        } else {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "talkId");
        }
        return this;
    }


    //检查存在产品造型或者界面的任务
    public boolean checkExistsModel() {
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select 1 from ytb_project.project_workjob ");
        sql.append(" where project_id=").append(projectModel.getProjectId());
        sql.append(" and talk_id=").append(projectTalkModel == null ? 0 : projectTalkModel.getTalkId());
        sql.append(" and (work_job like '%界面%' or work_job like '%造型%')");
        sql.append(" limit 1 ");
        return YtbSql.selectList(sql, Integer.class).size() > 0;

    }

    //获取本项目的模板文档
    public Dict_TemplateModel findDictTemplateModel(int docType) {
        return findDictTemplateModel(projectModel.getProjectTypeId(), docType);
    }

    public ProjectChangeModel getProjectChangeModel() {
        return projectChangeModel;
    }

    public void setProjectChangeModel(ProjectChangeModel projectChangeModel) {
        this.projectChangeModel = projectChangeModel;
    }

    public ProjectFolderModel getTalkFolderModel() {
        return projectTalkModel.fetchTalkFolderModel();
    }

    public ProjectFolderModel getPhaseFolder() {
        return projectTalkModel.fetchPhaseFolder();
    }

    public ProjectFolderModel getPhaseFolder(int phase) {
        return projectTalkModel.fetchPhaseFolder(phase);
    }

    //获取项目类型指定的模板类型文档
    public Dict_TemplateModel findDictTemplateModel(int projectTypeId, int docType) {
        return getProjectCacheManager().findDictTemplateModel(projectTypeId, docType);
    }

    public boolean isChangeStop() {
        return projectModel.isChangeStop();
    }

    public boolean isChangeBig() {
        return projectModel.isChangeBig();
    }

    public boolean isChangeLittle() {
        return projectModel.isChangeLittle();
    }

    //是否需求变更
    public boolean isChange() {
        return projectModel.isChange();
    }

    public boolean isPhaseIn() {
        return projectTalkModel.getPhase() >= projectModel.getPhaseStart()
                && projectTalkModel.getPhase() <= projectModel.getPhaseEnd();
    }

    public boolean isPhaseIn(int phase) {
        return phase >= projectModel.getPhaseStart() && phase <= projectModel.getPhaseEnd();
    }

    public boolean isLastStage(int phase) {
        return projectModel.isLastStage(phase);
    }

    public boolean isLastStage() {
        return projectModel.isLastStage(projectTalkModel.getPhase());
    }

    public boolean isFinishStage() {
        return projectTalkModel.isFinishStage( );
    }

    public boolean isClose() {
        return projectTalkModel.isClose( );
    }

    public boolean isPa() {
        return getUserId().equals(projectModel.getUserId1());
    }

    public boolean isPb() {
        return getUserId().equals(projectTalkModel.getUserId2());
    }

    public boolean isPm() {
        return projectTalkModel.isPm(getUserId());
    }


    //甲方是公司否？
    public boolean paIsCompanyUser() {
        return projectModel.isCompanyUser();
    }

    //乙方是公司否？
    public boolean pbIsCompanyUser() {
        return projectTalkModel.isCompanyUser();
    }

    public boolean isPurchase() {
        return getDict_ProjectTypeModel().isPurchase();
    }

    public boolean isProcessing() {
        return getDict_ProjectTypeModel().isProcessing();
    }

    public boolean isPurchaseProcessing() {
        return getDict_ProjectTypeModel().isPurchaseProcessing();
    }

    public boolean needInvoice() {
        return new ProjectTax().needInvoice(this);
    }

    //获取项目类别的阶段数
//    public int buildPhaseNo(int projectType) {
//        return projectModel.buildPhaseNo();
//
//    }

    public boolean existsDict_ProjectTypeModel(int projectTypeId) {
        return getInst().getProjectCacheManager().existsDict_ProjectTypeModel(projectTypeId);

    }

    public UserProjectContext buildModel(int talkId) {

        setTalkId(talkId);
        projectTalkModel = ProjectFlowService.getTalkService().getProjectTalkById(talkId);
        if (projectTalkModel == null) {
            //System.err.println("projectTalkModel is null!");
            return this;//throw new YtbError(YtbError.REQ_PARAMETER_NULL, "projectTalkModel not exists!");
        }

        return buildModelProject(projectTalkModel.getProjectId());
    }

    public UserProjectContext buildModelProjectChange() {
        if (projectModel.isChangeStop()) {
            projectChangeModel = new ChangeDaoServiceImpl().getChangeById(
                    projectTalkModel.getTalkId(), 0, projectTalkModel.getPhase());

        } else {
            projectChangeModel = new ChangeDaoServiceImpl().getChangeById(
                    projectTalkModel.getTalkId(), projectModel.getNewProjectId(), projectTalkModel.getPhase());

        }
        return this;
    }

    public UserProjectContext buildModelProject(int projectId) {
        projectModel = getInst().getReleaseView().getProjectDAOService().getProjectById(projectId);
        if (projectModel != null && projectModel.isChange()) {
            buildModelProjectChange();
        }
        return buildDict_ProjectTypeModel();
    }


    public UserProjectContext buildDict_ProjectTypeModel() {
        if (projectModel != null) {
            dict_ptm = getInst().getProjectCacheManager().findDictProjectTypeModel(projectModel.getProjectTypeId());
        }
        return this;
    }


    public ProjectInviteModel getProjectInviteModel() {
        return projectInviteModel;
    }

    public void setProjectInviteModel(ProjectInviteModel projectInviteModel) {
        this.projectInviteModel = projectInviteModel;
    }


    public void setDict_tm(Dict_TemplateModel dict_tm) {
        this.dict_tm = dict_tm;
    }

    public ViewProjectFolderModel getViewProjectFolderModel() {
        return viewProjectFolderModel;
    }

    public void setViewProjectFolderModel(ViewProjectFolderModel viewProjectFolderModel) {
        this.viewProjectFolderModel = viewProjectFolderModel;
    }

    public void setDict_ProjectTypeModel(Dict_ProjectTypeModel dict_ptm) {
        this.dict_ptm = dict_ptm;
    }

    public Dict_ProjectTypeModel getDict_ProjectTypeModel() {
        buildDict_ProjectTypeModel();
        return dict_ptm;
    }


    public ProjectTalkModel getProjectTalkModel() {
        if (projectModel == null) {
            throw new YtbError(YtbError.REQ_PARAMETER_NULL, " projectTalkModel ");
        }
        return projectTalkModel;
    }

    public String getNickName() {
        return getLoginSso().getLoginSsoJson().getNick_name();
    }

    public Integer getUserId() {
        return getLoginSso().getUserId().intValue();
    }

    public Integer getCompanyId() {
        Integer companyId = getLoginSso().getLoginSsoJson().getCompanyId();
        companyId = companyId == null ? 0 : companyId;
        return companyId;
    }

    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }

    public void setProjectTalkModel(ProjectTalkModel projectTalkModel) {
        this.projectTalkModel = projectTalkModel;
    }

    public ProjectModel getProjectModel() {
        if (projectModel == null) {
            throw new YtbError(YtbError.REQ_PARAMETER_NULL, " projectModel ");
        }
        return projectModel;
    }

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }


    public String  buildProjectUserType() {
        ProjectModel pm = getProjectModel();
        ProjectTalkModel ptm = getProjectTalkModel();
        if (isPa()) {
            return "PA" ;
        } else if (ptm == null) {
            return "PN";

        } else {
            if (isPb()) {
                return "PB";
            } else {
                return "PM";
            }
        }
    }

    public String toString() {
        return YtbUtils.toJSONStringSkipNull(this);
    }


}
