package ytb.project.model;


import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.common.context.model.Ytb_Model;
import java.util.Date;

public class ProjectFolderModel extends Ytb_Model  {


    protected int ownerId;

    //加个文件夹类型
    protected Byte folderType;
    //父ID
    protected int parentId;
    //文件夹主键
    protected int folderId;
    //文件夹名称
    protected String folderName;
    //文件夹责任人
    protected int userId;
    //审核人
    protected int auditor;

    //项目标识
    protected int projectId;
    //洽谈标识
    protected Integer talkId;
    //项目阶段
    protected int phase;
    //层次
    protected int depth;
    //文件夹状态
    protected int folderStatus;

    //创建人
    protected int createBy;
    //更改时间
    protected Date updateTime;

    public ProjectFolderModel(){

    }
    public ProjectFolderModel(ProjectFolderModel model) {
        this.parentId = model.parentId;
        this.folderId = model.folderId;
        this.folderName = model.folderName;

        this.setFolderType(model.getFolderType());
        this.folderStatus = model.folderStatus;

        this.userId = model.userId;
        this.auditor = model.auditor;

        this.projectId = model.projectId;
        this.talkId = model.talkId;
        this.phase = model.phase;

        this.createBy = model.createBy;
        this.updateTime = model.updateTime;

        this.depth = model.depth;
        this.ownerId = model.ownerId;

    }

    public ProjectFolderModel(UserProjectContext context) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        this.setOwnerId(0);
        this.setParentId(0);
        this.setFolderType(ProjectConst.FOLDER_TYPE_INVALID);
        this.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);

        this.setUserId(ptm == null ? pm.getUserId1() : ptm.getUserId2());
        if (context.isPa()) {
            this.setAuditor(0);
        } else {
            this.setAuditor(ptm == null ? 0 : ptm.getUserId2());
        }
        this.setProjectId(pm.getProjectId());
        this.setTalkId(ptm == null ? 0 : ptm.getTalkId());
        this.setPhase(ptm == null ? phase : ptm.getPhase());
        this.setDepth(0);

        this.setCreateBy(context.getUserId());
        this.setUpdateTime(new Date());

    }

    public Byte getFolderType() {
        return folderType;
    }

    public void setFolderType(Byte folderType) {
        this.folderType = folderType;
    }
    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }


    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getFolderStatus() {
        return folderStatus;
    }

    public void setFolderStatus(int folderStatus) {
        this.folderStatus = folderStatus;
    }

    public int getAuditor() {
        return auditor;
    }

    public void setAuditor(int auditor) {
        this.auditor = auditor;
    }
    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }
}
