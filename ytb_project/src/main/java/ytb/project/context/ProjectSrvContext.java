package ytb.project.context;

import ytb.bangbang.service.GroupService;
import ytb.bangbang.service.UserFriendService;
import ytb.bangbang.service.impl.GroupServiceImpl;
import ytb.bangbang.service.impl.UserFriendServiceImpl;
import ytb.account.context.AccountSrvContext;
import ytb.manager.context.ManagerSrvContext;
import ytb.project.cache.IProjectCacheManager;
import ytb.project.cache.ProjectCacheManager;
import ytb.project.dao.IProjectTradeModelService;
import ytb.project.daoservice.ProjectTradeModelServiceImpl;
import ytb.project.service.IDocumentToolService;
import ytb.project.service.IFlowFolderView;
import ytb.project.service.IProjectFileService;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.impl.ProjectFlowServiceImpl;
import ytb.project.service.impl.flow.FlowFloderView;
import ytb.project.service.impl.pay.IFlowPay;
import ytb.project.service.impl.release.ReleaseView;
import ytb.project.service.template.DocumentToolService;
import ytb.project.service.impl.flow.ProjectFileServiceImpl;
import ytb.project.service.impl.pay.projectpay.FlowPay;
import ytb.project.service.impl.flow.FlowTalk;
import ytb.project.view.service.impl.ProjectTaskView;
import ytb.manager.template.service.ProjectTypeService;
import ytb.manager.template.service.TemplateRepositoryService;
import ytb.manager.template.service.WorkJobTypeService;
import ytb.user.context.UserSrvContext;
import ytb.user.service.CompanyCenterService;
import ytb.user.service.UserCenterService;
import ytb.account.wallet.service.sq.basics.AccountTradeService;
import ytb.account.wallet.service.sq.business.user.ProjecTransactionService;


public class ProjectSrvContext {
    static ProjectSrvContext inst = new ProjectSrvContext();

    public static ProjectSrvContext getInst() {
        return inst;
    }

    public static UserSrvContext getInstUser() {
        return UserSrvContext.getInst();
    }

    public static ManagerSrvContext getInstMngr() {
        return ManagerSrvContext.getInst();
    }

    public static AccountSrvContext getInstAccount() {
        return AccountSrvContext.getInst();
    }

    private ProjectSrvContext() {
    }

    IProjectCacheManager projectCacheManager = new ProjectCacheManager();
    ReleaseView releaseView = new ReleaseView();
    ProjectFlowService projectFlowService = new ProjectFlowServiceImpl();
    GroupService groupService = new GroupServiceImpl();

    ProjectTaskView projectTaskView = new ProjectTaskView();
    protected IProjectFileService projectFileService = new ProjectFileServiceImpl();
    IDocumentFamily documentFamily = new DocumentFamily();
    FlowFloderView flowFolderView = new FlowFloderView();

    FlowTalk flowTalk = new FlowTalk();
    IFlowPay flowPay = new FlowPay ();
    IProjectTradeModelService projectTradeModelService = new ProjectTradeModelServiceImpl();

    UserFriendService userFriendService = new UserFriendServiceImpl();

    public IProjectTradeModelService getProjectTradeModelService() {
        return projectTradeModelService;
    }

    public IDocumentFamily getDocumentFamily() {
        return documentFamily;
    }


    public ProjecTransactionService getProjecTransactionService() {
        return AccountSrvContext.getInst().getProjecTransactionService();
    }


    public FlowTalk getFlowTalk() {
        return flowTalk;
    }

    public void setFlowTalk(FlowTalk flowTalk) {
        this.flowTalk = flowTalk;
    }


    public IProjectCacheManager getProjectCacheManager() {
        return projectCacheManager;
    }

    public void setProjectCacheManager(IProjectCacheManager projectCacheManager) {
        this.projectCacheManager = projectCacheManager;
    }

    public IFlowPay getFlowPay() {
        return flowPay;
    }

    public IFlowFolderView getFlowFolderView() {
        return flowFolderView;
    }

    public ProjectFlowService getProjectFlowService() {
        return projectFlowService;
    }

    public void setProjectFlowService(ProjectFlowService projectFlowService) {
        this.projectFlowService = projectFlowService;
    }

    public ReleaseView getReleaseView() {
        return releaseView;
    }

    public ProjectTaskView getProjectTaskView() {
        return projectTaskView;
    }

    public IProjectFileService getProjectFileService() {
        return projectFileService;
    }



    public UserFriendService getUserFriendService() {
        return userFriendService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public UserCenterService getUserCenterService() {
        return getInstUser().getUserCenterService();
    }

    public IDocumentToolService getiDocumentToolService() {

        return DocumentToolService.getDocumentToolService();
    }

    public CompanyCenterService getCompanyCenterService() {
        return getInstUser().getCompanyCenterService();
    }

    public AccountTradeService getAccountTradeService() {
        return getInstAccount().getAccountTradeService();
    }

    public ProjectTypeService getProjectTypeService() {
        return getInstMngr().getProjectTypeService() ;
    }

    public TemplateRepositoryService getTemplateRepositoryService() {
        return getInstMngr().getTemplateRepositoryService() ;

    }

    public WorkJobTypeService getWorkJobTypeService() {

        return getInstMngr().getWorkJobTypeService() ;
    }


}
