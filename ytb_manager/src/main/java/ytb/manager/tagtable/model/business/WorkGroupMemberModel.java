package ytb.manager.tagtable.model.business;

import ytb.common.context.model.Ytb_Model;
import java.sql.Timestamp;

public class WorkGroupMemberModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.work_group_member";

	private Integer memberId;

	private Integer groupId;

	private Integer projectId;

	private Integer talkId;

	private Integer documentId;

	private Integer userId;

	private Integer companyId=0;

	private Byte isAdmin;

	private Byte isActive;

	private Integer orderNo=0;

	private Timestamp createTime;

	public Integer  getMemberId() {
		return memberId;
	}

	public WorkGroupMemberModel setMemberId( Integer memberId ) {
		this.memberId = memberId;
		return this;
	}

	public Integer  getGroupId() {
		return groupId;
	}

	public WorkGroupMemberModel setGroupId( Integer groupId ) {
		this.groupId = groupId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public WorkGroupMemberModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public WorkGroupMemberModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public WorkGroupMemberModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getUserId() {
		return userId;
	}

	public WorkGroupMemberModel setUserId( Integer userId ) {
		this.userId = userId;
		return this;
	}

	public Integer  getCompanyId() {
		return companyId;
	}

	public WorkGroupMemberModel setCompanyId( Integer companyId ) {
		this.companyId = companyId;
		return this;
	}

	public Byte  getIsAdmin() {
		return isAdmin;
	}

	public WorkGroupMemberModel setIsAdmin( Byte isAdmin ) {
		this.isAdmin = isAdmin;
		return this;
	}

	public Byte  getIsActive() {
		return isActive;
	}

	public WorkGroupMemberModel setIsActive( Byte isActive ) {
		this.isActive = isActive;
		return this;
	}

	public Integer  getOrderNo() {
		return orderNo;
	}

	public WorkGroupMemberModel setOrderNo( Integer orderNo ) {
		this.orderNo = orderNo;
		return this;
	}

	public Timestamp  getCreateTime() {
		return createTime;
	}

	public WorkGroupMemberModel setCreateTime( Timestamp createTime ) {
		this.createTime = createTime;
		return this;
	}

}
