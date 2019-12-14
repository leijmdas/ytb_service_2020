package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

import java.sql.Timestamp;

public class WorkGroupTaskModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.work_group_task";

	private Integer taskId;

	private Integer projectId;

	private Integer talkId;

	private Integer workJobId;

	private Integer documentId;

	private String workJob;

	private Integer userId;

	private String userName;

	private String workTaskJson;

	private String workTask;

	private Boolean isAdmin;

	private Integer createBy;

	private Timestamp createTime;

	private Integer workTaskId;

	public Integer  getTaskId() {
		return taskId;
	}

	public WorkGroupTaskModel setTaskId( Integer taskId ) {
		this.taskId = taskId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public WorkGroupTaskModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public WorkGroupTaskModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

	public Integer  getWorkJobId() {
		return workJobId;
	}

	public WorkGroupTaskModel setWorkJobId( Integer workJobId ) {
		this.workJobId = workJobId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public WorkGroupTaskModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public String  getWorkJob() {
		return workJob;
	}

	public WorkGroupTaskModel setWorkJob( String workJob ) {
		this.workJob = workJob;
		return this;
	}

	public Integer  getUserId() {
		return userId;
	}

	public WorkGroupTaskModel setUserId( Integer userId ) {
		this.userId = userId;
		return this;
	}

	public String  getUserName() {
		return userName;
	}

	public WorkGroupTaskModel setUserName( String userName ) {
		this.userName = userName;
		return this;
	}

	public String  getWorkTaskJson() {
		return workTaskJson;
	}

	public WorkGroupTaskModel setWorkTaskJson( String workTaskJson ) {
		this.workTaskJson = workTaskJson;
		return this;
	}

	public String  getWorkTask() {
		return workTask;
	}

	public WorkGroupTaskModel setWorkTask( String workTask ) {
		this.workTask = workTask;
		return this;
	}

	public Boolean  getIsAdmin() {
		return isAdmin;
	}

	public WorkGroupTaskModel setIsAdmin( Boolean isAdmin ) {
		this.isAdmin = isAdmin;
		return this;
	}

	public Integer  getCreateBy() {
		return createBy;
	}

	public WorkGroupTaskModel setCreateBy( Integer createBy ) {
		this.createBy = createBy;
		return this;
	}

	public Timestamp  getCreateTime() {
		return createTime;
	}

	public WorkGroupTaskModel setCreateTime( Timestamp createTime ) {
		this.createTime = createTime;
		return this;
	}

	public Integer  getWorkTaskId() {
		return workTaskId;
	}

	public WorkGroupTaskModel setWorkTaskId( Integer workTaskId ) {
		this.workTaskId = workTaskId;
		return this;
	}

}
