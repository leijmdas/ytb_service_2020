package ytb.project.model.projectFolderView;

import ytb.common.context.model.Ytb_Model;
import java.sql.Timestamp;

public class VwWorkGroupMemberDutyModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.vw_work_group_member_duty";

	//project_member_duty
	private Integer memberDutyId;
	private Integer memberId;

	private String groupId;

	private Integer projectId;

	private Integer documentId;

	private Integer userId;

	private Integer companyId;

	private String isAdmin;

	private Integer orderNo;

	private Timestamp createTime;

	private Integer talkId;

	private Integer workJobId;

	public Integer  getMemberId() {
		return memberId;
	}

	public Integer getMemberDutyId() {
		return memberDutyId;
	}

	public void setMemberDutyId(Integer memberDutyId) {
		this.memberDutyId = memberDutyId;
	}

	public VwWorkGroupMemberDutyModel setMemberId( Integer memberId ) {
		this.memberId = memberId;
		return this;
	}

	public String  getGroupId() {
		return groupId;
	}

	public VwWorkGroupMemberDutyModel setGroupId( String groupId ) {
		this.groupId = groupId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public VwWorkGroupMemberDutyModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public VwWorkGroupMemberDutyModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getUserId() {
		return userId;
	}

	public VwWorkGroupMemberDutyModel setUserId( Integer userId ) {
		this.userId = userId;
		return this;
	}

	public Integer  getCompanyId() {
		return companyId;
	}

	public VwWorkGroupMemberDutyModel setCompanyId( Integer companyId ) {
		this.companyId = companyId;
		return this;
	}

	public String  getIsAdmin() {
		return isAdmin;
	}

	public VwWorkGroupMemberDutyModel setIsAdmin( String isAdmin ) {
		this.isAdmin = isAdmin;
		return this;
	}

	public Integer  getOrderNo() {
		return orderNo;
	}

	public VwWorkGroupMemberDutyModel setOrderNo( Integer orderNo ) {
		this.orderNo = orderNo;
		return this;
	}

	public Timestamp  getCreateTime() {
		return createTime;
	}

	public VwWorkGroupMemberDutyModel setCreateTime( Timestamp createTime ) {
		this.createTime = createTime;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public VwWorkGroupMemberDutyModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

	public Integer  getWorkJobId() {
		return workJobId;
	}

	public VwWorkGroupMemberDutyModel setWorkJobId( Integer workJobId ) {
		this.workJobId = workJobId;
		return this;
	}

}
