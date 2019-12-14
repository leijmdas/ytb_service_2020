package ytb.project.context;

import com.alibaba.fastjson.JSONObject;
import ytb.common.ytblog.YtbLog;
import ytb.project.common.ProjectConst;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class ProjectContext extends TemplateDocumentHeader implements IProjectContext {


    static ProjectSrvContext getInst() {
        return ProjectSrvContext.getInst();
    }

    //文档作者
    Long userId;
    //projectId 项目id
    int projectId;
    int talkId;
    //phase
    int phase;
    int phaseStatus;

    Integer changeType;
    //通用文档头id
    int documentId;
    //组标识
    int groupId;
    //阶段文件夹
    int folderId;

    //模板仓库标识
    Integer repositoryId;
    Integer projectTemplateId ;
    ProjectContext() {
    }

    public ProjectContext(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        setProjectUserType(context.buildProjectUserType());

        if (pm != null) {
            setUserId(Long.valueOf(pm.getUserId1()));
            setProjectId(pm.getProjectId());
            setTalkId(0);
            setPhase(ProjectConst.RequestIn);
            if (pm.isChange()) {
                setChangeType(pm.getChangeType());
            }
        }
        if (ptm != null) {
            setUserId(Long.valueOf(ptm.getUserId2()));
            setTalkId(ptm.getTalkId());
            setPhase(ptm.getPhase());
            setGroupId(ptm.getGroupId());
        }
    }


    JSONObject buildHeader() {

        JSONObject header = new JSONObject();
        header.fluentPut("projectUserType", getProjectUserType());
        header.fluentPut("userId", getUserId());

        header.fluentPut("projectId", getProjectId());
        header.fluentPut("talkId", getTalkId());
        header.fluentPut("phase", getPhase());
        header.fluentPut("documentId", getDocumentId());

        header.fluentPut("groupId", getGroupId() > 0 ? getGroupId() : null);
        header.fluentPut("changeType", getChangeType());

        return header;
    }

    //确立模板文档关系
    public int modifyHeader(UserProjectContext context) throws UnsupportedEncodingException {
        YtbLog.logDebug("buildProjectUserType",getProjectUserType());
        setProjectUserType(context.buildProjectUserType());

        getInst().getiDocumentToolService().modifyJsonHeader(context, getProjectId(), getDocumentId(), buildHeader());
        return getDocumentId();
    }

    public int modifyHeader(UserProjectContext context,int documentID) throws UnsupportedEncodingException {
        JSONObject header = new JSONObject();
        header.fluentPut("documentID", documentID);

        getInst().getiDocumentToolService().modifyJsonHeader(context, getProjectId(), getDocumentId(), header);
        return getDocumentId();
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

            //检查关键字段有输入
    public void check(){
        if (this.getUserId() == null || this.getUserId() <= 0) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "userId must >0");
        }
        if (this.getProjectId() <= 0) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "getProjectId() must >0");
        }
        if (this.getPhase() <= 0) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "getPhase() must >0");
        }
    }

    //检查需求说明书与工作计划书存在
    public void checkReqWorkplan() {
        if (this.getReqId() <= 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "无需求说明书");

        }
        if (this.getWorkplanId() <= 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "无工作计划书");

        }
    }

    //修改需求工作计划ID
    public void setUpReqWorkplan(List<ProjectTemplateModel> models) {
        for (ProjectTemplateModel model : models) {
            if (model.isTemplateReq()) {
                this.setReqId(model.getDocumentId());
            }
            if (model.isTemplateWorkplan()) {
                this.setWorkplanId(model.getDocumentId());
            }
        }

    }

    @Override
    public int getProjectTypeId() {
        return projectTypeId;
    }

    @Override
    public void setProjectTypeId(int projectTypeId) {
        this.projectTypeId = projectTypeId;
    }
    public int getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(int phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public Integer getProjectTemplateId() {
        return projectTemplateId;
    }

    public void setProjectTemplateId(Integer projectTemplateId) {
        this.projectTemplateId = projectTemplateId;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public Integer getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
    }

    public int getProjectId() {
        return projectId;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public Long getUserId() {
        return  userId ;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
}
