package ytb.project.model;

import ytb.common.context.model.Ytb_Model;

import java.sql.Timestamp;

public class ProjectFolderAssistModel extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.project_folder_assist";

	private Integer folderId;

	private Integer parentId;

	private Byte folderType;

	private String folderName;

	private Integer projectId;

	private Integer talkId;

	private Short phase=0;

	private Integer userId;

	private Integer auditor;

	private Integer fromUserId;

	private Byte depth;

	private Byte folderStatus;

	private Integer createBy;

	private Timestamp updateTime;

	public Integer  getFolderId() {
		return folderId;
	}

	public ProjectFolderAssistModel setFolderId(Integer folderId ) {
		this.folderId = folderId;
		return this;
	}

	public Integer  getParentId() {
		return parentId;
	}

	public ProjectFolderAssistModel setParentId(Integer parentId ) {
		this.parentId = parentId;
		return this;
	}

	public Byte  getFolderType() {
		return folderType;
	}

	public ProjectFolderAssistModel setFolderType(Byte folderType ) {
		this.folderType = folderType;
		return this;
	}

	public String  getFolderName() {
		return folderName;
	}

	public ProjectFolderAssistModel setFolderName(String folderName ) {
		this.folderName = folderName;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public ProjectFolderAssistModel setProjectId(Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public ProjectFolderAssistModel setTalkId(Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

	public Short  getPhase() {
		return phase;
	}

	public ProjectFolderAssistModel setPhase(Short phase ) {
		this.phase = phase;
		return this;
	}

	public Integer  getUserId() {
		return userId;
	}

	public ProjectFolderAssistModel setUserId(Integer userId ) {
		this.userId = userId;
		return this;
	}

	public Integer  getAuditor() {
		return auditor;
	}

	public ProjectFolderAssistModel setAuditor(Integer auditor ) {
		this.auditor = auditor;
		return this;
	}

	public Integer  getFromUserId() {
		return fromUserId;
	}

	public ProjectFolderAssistModel setFromUserId(Integer fromUserId ) {
		this.fromUserId = fromUserId;
		return this;
	}

	public Byte  getDepth() {
		return depth;
	}

	public ProjectFolderAssistModel setDepth(Byte depth ) {
		this.depth = depth;
		return this;
	}

	public Byte  getFolderStatus() {
		return folderStatus;
	}

	public ProjectFolderAssistModel setFolderStatus(Byte folderStatus ) {
		this.folderStatus = folderStatus;
		return this;
	}

	public Integer  getCreateBy() {
		return createBy;
	}

	public ProjectFolderAssistModel setCreateBy(Integer createBy ) {
		this.createBy = createBy;
		return this;
	}

	public Timestamp  getUpdateTime() {
		return updateTime;
	}

	public ProjectFolderAssistModel setUpdateTime(Timestamp updateTime ) {
		this.updateTime = updateTime;
		return this;
	}

}
