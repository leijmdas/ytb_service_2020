package ytb.project.model;

import ytb.project.context.UserProjectContext;
import ytb.common.context.model.Ytb_Model;
import java.sql.Timestamp;

public class ProjectTradeModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.project_trade";

	private Integer projectTradeId;

	private Integer userId;

	private Integer companyId;

	private Integer projectId;

	private Integer talkId;

	private Short phase;


	private int templateId;

	private Integer tradeId;

	private Integer parentTradeId = 0;

	private Timestamp updateTime;

	private Byte serviceType;

	private byte[] memo;


	public ProjectTradeModel(){

	}

	public ProjectTradeModel(UserProjectContext context, Integer tradeId, Integer pid) {

		ProjectModel pm = context.getProjectModel();
		ProjectTalkModel ptm = context.getProjectTalkModel();

		ProjectTradeModel pt = this;
		pt.setServiceType(serviceType);
		pt.setUserId(pm.getUserId1());
		pt.setCompanyId(pm.getCompanyId1());
		pt.setProjectId(pm.getProjectId());
		pt.setTalkId(ptm == null ? 0 : ptm.getTalkId());
		pt.setPhase(ptm==null?0:(short) ptm.getPhase());
		pt.setTemplateId(0);

		pt.setParentTradeId(pid);
		pt.setTradeId(tradeId);


	}

	public byte[] getMemo() {
		return memo;
	}

	public void setMemo(byte[] memo) {
		this.memo = memo;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public Byte getServiceType() {
		return serviceType;
	}

	public void setServiceType(Byte serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getParentTradeId() {
		return parentTradeId;
	}

	public void setParentTradeId(Integer parentTradeId) {
		this.parentTradeId = parentTradeId;
	}

	public Integer  getProjectTradeId() {
		return projectTradeId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public ProjectTradeModel setProjectTradeId( Integer projectTradeId ) {
		this.projectTradeId = projectTradeId;
		return this;
	}

	public Integer  getUserId() {
		return userId;
	}

	public ProjectTradeModel setUserId( Integer userId ) {
		this.userId = userId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public ProjectTradeModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public ProjectTradeModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

	public Short  getPhase() {
		return phase;
	}

	public ProjectTradeModel setPhase( Short phase ) {
		this.phase = phase;
		return this;
	}

	public Integer  getTradeId() {
		return tradeId;
	}

	public ProjectTradeModel setTradeId( Integer tradeId ) {
		this.tradeId = tradeId;
		return this;
	}

	public Timestamp  getUpdateTime() {
		return updateTime;
	}

	public ProjectTradeModel setUpdateTime( Timestamp updateTime ) {
		this.updateTime = updateTime;
		return this;
	}

}
