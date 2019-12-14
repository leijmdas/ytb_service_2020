package ytb.project.model;

import com.alibaba.fastjson.JSONObject;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.iface.IProjectModel;
import ytb.project.model.iface.IFolderModel;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.impl.pay.payfee.ProjectRateTaxModel;
import ytb.project.service.impl.talk.ProjectTalkService;
import ytb.manager.tagtable.model.business.ProjectRateModel;
import ytb.manager.tagtable.service.impl.TagTableProjectService;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;

import java.util.Date;
import java.util.List;

public class ProjectModel extends Ytb_Model implements IProjectModel {


    // 0--正常项目   1--测试项目
    private Byte testFlag;

    //项目类别
    private int projectTypeId;

    //项目标识
    private int projectId;
    //变更产生新项目
    private Integer newProjectId = 0;

    //项目名称
    private String projectName;
    //变更新项目记录老项目talk标识,在变更完成时会对老项目款项进行解冻。
    private Integer oldTalkId = 0;
    //项目根文件夹
    private int folderId;

    //项目发布人(甲方)
    private int userId1;
    //发布人公司标识
    private int companyId1;

    //发布状态(1草稿 2发布审核中 3审核未通过 4发布中 5发布停止 6重新发布
    private int status;

    //变更状态：最后一次，701小变更  702大变更 800 终止
    private Integer changeType = 0;

    //0--非造型界面 1--造型界面,3阶段项目有用
    private byte modelFlag;
    //项目总阶段数
    private int phaseNo;

    //阶段开始数
    private int phaseStart = 601;
    //支付次数,只有一次
    private  int payTimes = 0;

    //允许发布
    private boolean isPublish;

    //允许自动发出邀请
    private boolean isInvite;

    //浏览数
    private int viewNumber;
    //收藏数
    private int favoriteNumber;

    //创建时间
    private Date enterTime;
    //发布停止时间
    private Date finishTime;

    public int buildPhaseNo(int projectType) {

        int phaseNo = 3;

        if (projectType == Dict_ProjectTypeModel.PT_DEV_DESIGN) {
            phaseNo = 6;

        } else if (projectType == Dict_ProjectTypeModel.PT_PURCHASE
            || projectType == Dict_ProjectTypeModel.PT_PROCESSING) {
            phaseNo = 1;
        } else if (projectType == Dict_ProjectTypeModel.PT_INQUERY) {
            phaseNo = 1;

        } else if (projectType == Dict_ProjectTypeModel.PT_FinishSchool) {

            phaseNo = 8;
        }
        return phaseNo;
    }


    public ProjectModel cloneProjectModel() {
        return JSONObject.parseObject(this.toString(), ProjectModel.class);
    }

    //修改项目文件家
    public void modifyFolder() {
        IFolderModel.getInst().getReleaseView().getProjectDAOService().modifyFolder(projectId, folderId);
    }

    public ProjectRateTaxModel selectProjectRateTaxModel(UserProjectContext context)
    {
        ProjectRateModel rate =  TagTableProjectService.getTagTableService().getProjectRate(getProjectId());
        return new ProjectRateTaxModel(rate,context);
    }

    //查询文件夹下的需求说明书与工作计划书模板
    public List<ProjectTemplateModel> selectPublishTemplates()  {

        return ProjectSrvContext.getInst().getProjectFileService().getTemplateListByFolder(getFolderId());

    }

    public ProjectTemplateModel selectReqTemplate() {
        for (ProjectTemplateModel templateModel : selectPublishTemplates() ){
            if (templateModel.getDocType() == ProjectConst.Template_TYPE_req) {
                return templateModel;
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
    }

    public  ProjectTalkService getProjectTalkService() {
        return ProjectFlowService.getTalkService();

    }

    public  List<ProjectFolderModel> queryAllFolders() {
        return UserProjectContext.getInst().getProjectFileService().getProjectFolders(getProjectId());
    }

    public ProjectFolderModel selectProjectFolder() {

        return UserProjectContext.getInst().getProjectFileService().getFolderModel(folderId);

    }

    public boolean isPa(Integer userId) {
        return userId.equals(getUserId1());
    }

    public boolean isCompanyUser() {
        return getCompanyId1() > 0;
    }

    public boolean isLastStage(int phase) {
        return phase == phaseStart + phaseNo - 1;
    }

    public boolean isFinishStage(int phase) {
        return phase > phaseStart + phaseNo - 1;
    }

    //终止项目不能变更
    public boolean isChangeStop() {
        return changeType == ProjectConstState.CHNAGE_TYPE_STOP;
    }

    public boolean isNoChange () {
        return changeType == ProjectConstState.CHNAGE_TYPE_NONE;
    }

    public boolean isChange() {
        return changeType >= ProjectConstState.CHNAGE_TYPE_SMALL;
    }

    public boolean isChangeLittle () {
        return changeType == ProjectConstState.CHNAGE_TYPE_SMALL;
    }

    public boolean isChangeBig() {
        return changeType == ProjectConstState.CHNAGE_TYPE_BIG;
    }


    public ProjectEventModel addProjectEvent(UserProjectContext context, String remark, int evenType,
                                             int eventUserId, int eventAnother) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        if (ptm == null) {
            context.setProjectTalkModel(new ProjectTalkModel());
            ptm = context.getProjectTalkModel();
            ptm.setProjectId(pm.getProjectId());
        }
       return ptm.addTalkEvent(context, remark, evenType, eventUserId, eventAnother);

    }

    public byte getModelFlag() {
        return modelFlag;
    }

    public void setModelFlag(byte modelFlag) {
        this.modelFlag = modelFlag;
    }

    public void setPublish(boolean publish) {
        isPublish = publish;
    }

    public Byte getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Byte testFlag) {
        this.testFlag = testFlag;
    }

    public Integer getOldTalkId() {
        return oldTalkId;
    }

    public void setOldTalkId(Integer oldTalkId) {
        this.oldTalkId = oldTalkId;
    }

    public Integer getNewProjectId() {
        return newProjectId;
    }

    public void setNewProjectId(Integer newProjectId) {
        this.newProjectId = newProjectId;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getCompanyId1() {
        return companyId1;
    }

    public void setCompanyId1(int companyId1) {
        this.companyId1 = companyId1;
    }

    public int getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(int projectTypeId) {
        this.projectTypeId = projectTypeId;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public int getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(int favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public boolean isPublish() {
        return isPublish;
    }

    public void setIsPublish(boolean publish) {
        isPublish = publish;
    }

    public boolean isInvite() {
        return isInvite;
    }

    public void setInvite(boolean invite) {
        isInvite = invite;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getPhaseStart() {
        return phaseStart;
    }

    public int getPhaseNo() {
        return phaseNo;
    }

    public boolean phaseNoEq3() {
        return phaseNo==3;
    }

    public void setPhaseStart(int phaseStart) {
        this.phaseStart = phaseStart;
    }

    public int getPhaseEnd() {
        return this.phaseStart + phaseNo - 1;
    }

    public void setPhaseNo(int phaseNo) {
        this.phaseNo = phaseNo;
    }

    public int getPayTimes() {
        return payTimes;
    }

    public void setPayTimes(int payTimes) {
        this.payTimes = payTimes;
    }

}
