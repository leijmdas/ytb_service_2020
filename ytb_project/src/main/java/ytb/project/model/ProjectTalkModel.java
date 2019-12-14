package ytb.project.model;

import com.alibaba.fastjson.JSONObject;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.tagtable.IWorkGroupMemberModelService;
import ytb.project.daoservice.WorkGroupMemberModelServiceImpl;
import ytb.project.model.iface.IProjectTalkModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.IProjectFileService;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.impl.pay.payevent.PayEventModel;
import ytb.project.service.impl.talk.IPhaseAndEvent;
import ytb.project.service.impl.talk.IWorkGroup;
import ytb.project.service.impl.talk.PhaseAndEvent;
import ytb.project.service.impl.talk.WorkGroup;
import ytb.project.service.impl.talk.TalkWorkPlanService;
import ytb.manager.tagtable.rest.impl.TagTableServiceProjectRestProcess;
import ytb.manager.tagtable.service.impl.TagTableServiceImpl;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


public class ProjectTalkModel extends Ytb_Model implements IProjectTalkModel {

    transient static IWorkGroup workGroup = new WorkGroup();

    transient static IWorkGroupMemberModelService workGroupMemberModelService = new WorkGroupMemberModelServiceImpl();
    transient static IPhaseAndEvent phaseAndEvent = new PhaseAndEvent();
    transient static TalkWorkPlanService workPlanService = new TalkWorkPlanService();

    transient List<WorkGroupMemberModel> memberModels;
    transient Map<Integer,ProjectPhaseModel> phaseModelMap = new LinkedHashMap<>();

    public IWorkGroupMemberModelService getWorkGroupMemberModelService() {
        return workGroupMemberModelService;
    }


    public static IPhaseAndEvent getPhaseAndEvent() {
        return phaseAndEvent;
    }

    public static IWorkGroup getWorkGroup() {
        return workGroup;
    }

    //表主键
    private int talkId;

    //项目Id
    private int projectId;

    //项目洽谈确认标识
    private int projectIdOk;

    //工作组
    private int groupId;

    //乙方ID
    private int userId2;

    //乙方公司ID
    private int companyId2;


    // 项目阶段：统计状态 200, 601-606
    private int phase;
    // 统计状态变化：400：洽谈终止， 变更701,702、终止800，最终变，不会回; 900:项目完成。
    private int changeStatus;

    // 过程状态：0编制中，200审核中，300支付中
    private int phaseStatus;
    // 过程事件：'事件类型',
    private int eventType;

    //洽谈文件夹
    private int folderId;
    //工作组计划书
    private int workplanId;

    // 项目支付时间
    private Date payDate = new Date();

    //创建时间
    private Date enterTime = new Date();
    //更新时间
    private Date finishTime = new Date();

    //请求备注
    private String remark = "";

    public ProjectTalkModel(){

    }

    public ProjectTalkModel cloneProjectTalkModel ( ){
        return JSONObject.parseObject(this.toString(),ProjectTalkModel.class);
    }

    public static ProjectTalkModel copyProjectTalkModel(ProjectInviteModel inviteModel) {
        ProjectTalkModel ptm = new ProjectTalkModel();
        ptm.setProjectId(inviteModel.getProjectId());
        ptm.setTalkId(inviteModel.getTalkId());
        ptm.setPhase(inviteModel.getPhase());
        ptm.setPhaseStatus(inviteModel.getPhaseStatus());

        ptm.setUserId2(inviteModel.getUserId2());
        ptm.setCompanyId2(inviteModel.getCompanyId2());

        ptm.setEventType(inviteModel.getEventType());
        ptm.setGroupId(0);
        ptm.setPayDate(new Date(System.currentTimeMillis() - 1000000));
        return ptm;
    }


    public boolean isFinishStage() {
        return changeStatus == ProjectConstState.CHNAGE_TYPE_FINISH;
    }

    public boolean isClose() {
        return changeStatus != 0 && changeStatus != ProjectConstState.CHNAGE_TYPE_SMALL;
    }


    public ProjectTalkModel(ProjectInviteModel inviteModel) {
        setTalkId(inviteModel.getTalkId());
        setProjectId(inviteModel.getProjectId());
        setPhase(ProjectConst.TalkPhase);
        setPhaseStatus(0);
        setUserId2(inviteModel.getUserId2());
        setCompanyId2(inviteModel.getCompanyId2());

        setEventType(0);
        setGroupId(0);
        setPayDate(new Date(System.currentTimeMillis() - 1000000));
    }

    public boolean checkExistStopTemplate(int stopFolderId) {


        return  selectStopTemplates(stopFolderId).size() > 0  ;

    }

    public boolean checkTalkTerm(){
        return getChangeStatus()== ProjectConstState.CHNAGE_TYPE_TALK_TERM;
    }
    public boolean checkChangeBig(){
        return getChangeStatus()== ProjectConstState.CHNAGE_TYPE_BIG;
    }
    public boolean checkStop(){
        return getChangeStatus()== ProjectConstState.CHNAGE_TYPE_STOP;
    }
    public boolean checkFinish(){
        return getChangeStatus()== ProjectConstState.CHNAGE_TYPE_FINISH;
    }



    public ProjectTemplateModel selectStopTemplateByPhase(int phase) {
        ProjectFolderModel stopFolder = fetchStopFolder(phase);
        return selectStopTemplate(stopFolder.getFolderId());
    }

    public ProjectTemplateModel selectStopTemplate() {
        return selectStopTemplateByPhase(phase);
    }
    public ProjectTemplateModel selectStopTemplate(int stopFolderId) {

        List<ProjectTemplateModel> templateModels = selectStopTemplates(stopFolderId);
        if (templateModels.size() == 0) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "终止通知书");
        }
        return templateModels.get(0);
    }

    List<ProjectTemplateModel> selectStopTemplates(int stopFolderId) {

        return ProjectSrvContext.getInst().getProjectFileService().
                getTemplateListByDocType(stopFolderId, ProjectConst.Template_TYPE_stop);

    }
    public ProjectTemplateModel selectChangeTemplate() {
        List<ProjectTemplateModel> templateModels =  ProjectSrvContext.getInst().getProjectFileService().
                getTemplateListByDocType(getFolderId(), ProjectConst.Template_TYPE_chng);
        if(templateModels.size()==0){
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD,"变更通知书");
        }
        return  templateModels.get(0); 
    }

    public List<ProjectFolderModel> selectAllFolders() {
        return UserProjectContext.getInst().getProjectFileService().getProjectTalkFolders(talkId);

    }

    //按用户查询文件夹
    public List<ProjectFolderModel> selectUserPhaseFolders(int userId) {
        return UserProjectContext.getInst().getProjectFileService()
                .getUserPhaseFolders(talkId, userId, phase);
    }

    public List<ProjectTemplateModel> selectUserPhaseTemplates(int userId,int docType) {
        List<ProjectTemplateModel> templateModels=new ArrayList<>();
        List<ProjectTemplateModel>  templates=selectUserPhaseTemplates(userId);
        for(ProjectTemplateModel model:templates){
            if(model.getDocType()==docType){
                templateModels.add(model);
            }
        }
        return templateModels;
    }

    //按用户查询模板
    public List<ProjectTemplateModel> selectUserPhaseTemplates(int userId) {
        List<ProjectTemplateModel> templateModels = new ArrayList<>();
        List<ProjectFolderModel> models = selectUserPhaseFolders(userId);
        for (ProjectFolderModel model : models) {
            List<ProjectTemplateModel> projectTemplateModels =
                    UserProjectContext.getInst().getProjectFileService().getTemplateListByFolder(model.getFolderId());
            templateModels.addAll(projectTemplateModels);
        }
        return templateModels;
    }

    public void modifyWorkplanId() {
        ProjectFlowService.getTalkService().modifyWorkplanId(workplanId, talkId);
    }

    // 检查有费用数据,人员日薪都不为0
    public void checkExistCost(UserProjectContext context) throws IOException {

        if (selectWorkplanTemplate().isTemplateWorkplan_nonePp()) {
            if (context.getTestFlag() == null) {
                List<Integer> costList =  new TagTableServiceImpl()
                        .checkMemberDayPay(context.getLoginSso(), projectId, workplanId)
                        .checkExistCost(projectId, workplanId);
                if(costList.size()==0){
                    throw new YtbError(YtbError.CODE_USER_ERROR,"工作计划没有配置，无费用记录！");
                }
            }
        }

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        //加工采购不需要计算
        if(pm.phaseNoEq3()) {
            byte modelFlag = context.checkExistsModel() ? (byte) 1 : (byte) 0;
            if ( pm.getModelFlag()!=modelFlag ) {
                UserProjectContext.getInst().getReleaseView().getProjectDAOService()
                        .modifyProjectModelFlag(pm.getProjectId(), modelFlag);
            }
        }
        //检查工作计划
        new TagTableServiceProjectRestProcess().checkTemplate(pm.getProjectId(),ptm.getWorkplanId());

    }


    // 洽谈总文件夹
    public ProjectFolderModel fetchTalkFolderModel() {
        return ProjectSrvContext.getInst().getProjectFileService().getFolderModel(getFolderId());
    }
    // 阶段总文件夹
    public ProjectFolderModel fetchPhaseFolder() {
        return fetchPhaseFolder(phase);
    }

    public ProjectFolderModel fetchStopFolder(int phase) {
        return ProjectSrvContext.getInst().getProjectFileService().getStopFolder(getFolderId(), phase);

    }

    public ProjectFolderModel fetchStopFolder() {
        return ProjectSrvContext.getInst().getProjectFileService().getStopFolder(getFolderId(), phase);

    }

    public boolean existsPhaseFolder(int phase) {
        return fetchPhaseFolder(phase) != null;
    }

    public ProjectFolderModel fetchPhaseFolder(int phase) {
        return ProjectSrvContext.getInst().getProjectFileService().getPhaseFolderByParent(getFolderId(), phase, getProjectId());
    }

    public List<ProjectFolderModel> getMemberFolderModels(int userId) {
        return ProjectSrvContext.getInst().getProjectFileService().getProjectPhaseFolders(getProjectId(), userId,
                phase);
    }

    //查询文件夹下的需求说明书与工作计划书模板
    public List<ProjectTemplateModel> selectTalkTemplates()     {

        return  ProjectSrvContext.getInst().getProjectFileService().getTemplateListByFolder(getFolderId());

    }

    public ProjectTemplateModel selectReqTemplate() {
        for(ProjectTemplateModel templateModel:selectTalkTemplates()){
            if(templateModel.isTemplateReq()){
                return  templateModel;
            }
        }
        return findPbTemplate(ProjectConst.Template_TYPE_req_START);
    }

    public ProjectTemplateModel selectWorkplanTemplate() {
        for(ProjectTemplateModel templateModel:selectTalkTemplates()){
            if(templateModel.isTemplateWorkplan()){
                return  templateModel;
            }
        }
        return findPbTemplate(ProjectConst.Template_TYPE_workplan_START);
    }

    //获取文件夹以及子文件夹下面所有文档
    public List<ProjectTemplateModel> selectPhaseAllTemplates() {
        IProjectFileService projectFileService = UserProjectContext.getInst().getProjectFileService();
        List<ProjectTemplateModel> templateModels = new ArrayList<>();
        ProjectFolderModel phaseFolderModel = fetchPhaseFolder();

        List<ProjectTemplateModel> models = projectFileService.getTemplatesTree(phaseFolderModel.getFolderId(), templateModels);
        return models;
    }

    public ProjectTemplateModel findPbTemplate(int docType) {
        IProjectFileService projectFileService = UserProjectContext.getInst().getProjectFileService();
        List<ProjectFolderModel> folderModels = projectFileService.getUserPhaseFolders(talkId, userId2, phase);
        for (ProjectFolderModel folderModel : folderModels) {
            List<ProjectTemplateModel> models =
                    projectFileService.getTemplateListByDocType(folderModel.getFolderId(),docType);
             if(models.size()>0){
                 return models.get(0);
             }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "模板不存在");
    }

    public ProjectEventModel addTalkEvent(UserProjectContext context, ProjectEventModel eventModel) {

        int phaseId = getProjectPhaseAutoAdd(context).getPhaseId();
        eventModel.setPhaseId(phaseId);
        eventModel.setServiceType(ProjectEventModel.EVENT_SERVICE_DEFUALT);
        phaseAndEvent.addTalkEvent(eventModel);

        return eventModel;
    }


    public ProjectEventModel addTalkEvent(UserProjectContext context, String remark, int evenType, int eventUserId,
                                          int eventAnother) {

        PayEventModel eventModel=new PayEventModel();
        eventModel.setChangeType(0);
        eventModel.setRemark(remark);
        eventModel.setEvenType(evenType); ;
        eventModel.setPa(eventUserId);
        eventModel.setPb(eventAnother);
        eventModel.setPayFee(BigDecimal.ZERO);
        return phaseAndEvent.addEvent(context,eventModel);

    }

    public ProjectEventModel addAssistEvent(UserProjectContext context, String remark, int evenType, int eventUserId,
                                            int eventAnother) {
        PayEventModel eventModel=new PayEventModel();
        eventModel.setChangeType(0);
        eventModel.setRemark(remark);
        eventModel.setEvenType(evenType); ;
        eventModel.setPa(eventUserId);
        eventModel.setPb(eventAnother);
        eventModel.setPayFee(BigDecimal.ZERO);
        return phaseAndEvent.addEventAssist(context,eventModel);

    }

    //事件
    public ProjectEventModel addEvent(UserProjectContext context, PayEventModel payEvent) {
        return phaseAndEvent.addEvent(context,payEvent);

    }



//    public ProjectEventModel addPayEvent(UserProjectContext context, String remark, int evenType,
//                                         int eventUserId, int eventAnother, BigDecimal payFee) {
//        PayEventModel eventModel=new PayEventModel();
//        eventModel.setChangeType(0);
//        eventModel.setRemark(remark);
//        eventModel.setEvenType(evenType); ;
//        eventModel.setPa(eventUserId);
//        eventModel.setPb(eventAnother);
//        eventModel.setPayFee(payFee);
//        return phaseAndEvent.addPayEvent(context,eventModel);
//
//    }

    public ProjectEventModel addPayEvent(UserProjectContext context, PayEventModel eventModel) {

        return phaseAndEvent.addPayEvent(context,eventModel);

    }

    public ProjectPhaseModel getProjectPhaseAutoAdd(UserProjectContext context){

        ProjectTalkModel ptm = context.getProjectTalkModel();

        if(phaseModelMap.containsKey(ptm.getPhase())){
            return phaseModelMap.get(ptm.getPhase());
        }
        ProjectPhaseModel phaseModel = phaseAndEvent.getProjectPhaseAutoAdd(context);
        phaseModelMap.put(ptm.getPhase(), phaseModel);
        return phaseModelMap.get(ptm.getPhase());
    }

    public boolean isPm(int userId) {
        for (WorkGroupMemberModel memberModel : selectWorkGroupMember()) {
            if (memberModel.isPm() && memberModel.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }

    public boolean isCompanyUser() {
        return getCompanyId2() > 0;
    }

    public boolean isPb(Integer userId) {
        return userId.equals(getUserId2());
    }

    public List<WorkGroupMemberModel> selectWorkGroupMember() {
        if (memberModels == null) {
            memberModels = getWorkGroup().getWorkGroupMember(getGroupId(), null);
        }
        return memberModels;
    }

    //只返回组员，PA PB不返回 only PM
    public List<WorkGroupMemberModel> selectWorkGroupMemberPM() {
        return workGroupMemberModelService.selectListPM(getGroupId());
    }

    //获取 结束时间
    public Date getProjectEndTime( ) {
        return getFinishTime();
    }

    //获取 开始时间(实际开始日期)
    public Date getProjectStartTime() {
        return payDate;
    }

    //计划开始日期
    public Date getPlanStartTime() {
        return getProjectStartTime();
    }

    public Date getPlanEndTime(UserProjectContext context) {
        //算一下 实际开始后延
        return workPlanService.getEndTime(context);
    }


    //获取阶段结束时间
    public Date getPhaseEndTime(UserProjectContext context) {
        return workPlanService.getPhaseEndTime(context);
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }


    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectIdOk() {
        return projectIdOk;
    }

    public void setProjectIdOk(int projectIdOk) {
        this.projectIdOk = projectIdOk;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public int getCompanyId2() {
        return companyId2;
    }

    public void setCompanyId2(int companyId2) {
        this.companyId2 = companyId2;
    }


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }


    public int getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(int phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getWorkplanId() {
        return workplanId;
    }

    public void setWorkplanId(int workplanId) {
        this.workplanId = workplanId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public int getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(int changeStatus) {
        this.changeStatus = changeStatus;
    }
}
