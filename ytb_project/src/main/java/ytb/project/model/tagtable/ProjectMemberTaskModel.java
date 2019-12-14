package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

import java.sql.Timestamp;

public class ProjectMemberTaskModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.project_member_task";

	private Integer mDutyTaskId;

	private Integer memberDutyId;

	private Integer projectId;

	private String taskName;

	private String taskRemark;

	private Integer folderId;

	private Integer createMode;

	private Timestamp createTime;

	private Timestamp finishTime;

	private Byte taskStatus;

	private Integer templateid;

	public Integer  getMDutyTaskId() {
		return mDutyTaskId;
	}

	public ProjectMemberTaskModel setMDutyTaskId( Integer mDutyTaskId ) {
		this.mDutyTaskId = mDutyTaskId;
		return this;
	}

	public Integer  getMemberDutyId() {
		return memberDutyId;
	}

	public ProjectMemberTaskModel setMemberDutyId( Integer memberDutyId ) {
		this.memberDutyId = memberDutyId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public ProjectMemberTaskModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public String  getTaskName() {
		return taskName;
	}

	public ProjectMemberTaskModel setTaskName( String taskName ) {
		this.taskName = taskName;
		return this;
	}

	public String  getTaskRemark() {
		return taskRemark;
	}

	public ProjectMemberTaskModel setTaskRemark( String taskRemark ) {
		this.taskRemark = taskRemark;
		return this;
	}

	public Integer  getFolderId() {
		return folderId;
	}

	public ProjectMemberTaskModel setFolderId( Integer folderId ) {
		this.folderId = folderId;
		return this;
	}

	public Integer  getCreateMode() {
		return createMode;
	}

	public ProjectMemberTaskModel setCreateMode( Integer createMode ) {
		this.createMode = createMode;
		return this;
	}

	public Timestamp  getCreateTime() {
		return createTime;
	}

	public ProjectMemberTaskModel setCreateTime( Timestamp createTime ) {
		this.createTime = createTime;
		return this;
	}

	public Timestamp  getFinishTime() {
		return finishTime;
	}

	public ProjectMemberTaskModel setFinishTime( Timestamp finishTime ) {
		this.finishTime = finishTime;
		return this;
	}

	public Byte  getTaskStatus() {
		return taskStatus;
	}

	public ProjectMemberTaskModel setTaskStatus( Byte taskStatus ) {
		this.taskStatus = taskStatus;
		return this;
	}

	public Integer  getTemplateid() {
		return templateid;
	}

	public ProjectMemberTaskModel setTemplateid( Integer templateid ) {
		this.templateid = templateid;
		return this;
	}

}
