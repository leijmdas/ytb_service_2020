package ytb.project.model.tagtable;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.user.context.UserSrvContext;
import ytb.user.model.UserInfoModel;
import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;

public class ProjectStopPModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.project_stop_p";

	private Integer stopId;

	private Integer projectId;

	private Integer documentId;

	private String projectName;

	private Short phase;

	private String why;

	private BigDecimal fee;

	private String grade;

	private BigDecimal feePay10 = BigDecimal.ZERO;

	private BigDecimal feeBack20= BigDecimal.ZERO;

	private String feeBack21 = "";

	private BigDecimal feeBack22 = BigDecimal.ZERO;

	private BigDecimal cFeePlan = BigDecimal.ZERO;

	private String cFeePayed = "";

	private BigDecimal cFeePp = BigDecimal.ZERO;
	;

	private BigDecimal cFeeInvoice= BigDecimal.ZERO;

	private BigDecimal cFeePhase= BigDecimal.ZERO;

	private BigDecimal cFeePenalty = BigDecimal.ZERO;

	private Integer talkId;
	private BigDecimal q = BigDecimal.ZERO;

	public ProjectStopPModel() {

	}

	public ProjectStopPModel(UserProjectContext context) {

		ProjectModel pm = context.getProjectModel();
		ProjectTalkModel ptm = context.getProjectTalkModel();

		ProjectStopPModel stopPModel =this;
		stopPModel.setProjectId(pm.getProjectId());
		stopPModel.setTalkId(ptm.getTalkId());
		stopPModel.setDocumentId(ptm.selectStopTemplate().getDocumentId());
		//项目名称
		stopPModel.setProjectName(pm.getProjectName());
		//项目进度
		int phase = ptm.getPhase() - pm.getPhaseStart() + 1;
		stopPModel.setPhase((short) phase);

		UserInfoModel infoModel= UserSrvContext.getInst().getUserCenterService().getUserInfoById(ptm.getUserId2());
		stopPModel.setGrade(infoModel.getCreditGrade().trim());


	}

	public BigDecimal getQ() {
		return q;
	}

	public void setQ(BigDecimal q) {
		this.q = q;
	}


	public Integer  getStopId() {
		return stopId;
	}

	public ProjectStopPModel setStopId( Integer stopId ) {
		this.stopId = stopId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public ProjectStopPModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public ProjectStopPModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public String  getProjectName() {
		return projectName;
	}

	public ProjectStopPModel setProjectName( String projectName ) {
		this.projectName = projectName;
		return this;
	}

	public Short  getPhase() {
		return phase;
	}

	public ProjectStopPModel setPhase( Short phase ) {
		this.phase = phase;
		return this;
	}

	public String  getWhy() {
		return why;
	}

	public ProjectStopPModel setWhy( String why ) {
		this.why = why;
		return this;
	}

	public BigDecimal  getFee() {
		return fee;
	}

	public ProjectStopPModel setFee( BigDecimal fee ) {
		this.fee = fee;
		return this;
	}

	public String  getGrade() {
		return grade;
	}

	public ProjectStopPModel setGrade( String grade ) {
		this.grade = grade;
		return this;
	}

	public BigDecimal  getFeePay10() {
		return feePay10;
	}

	public ProjectStopPModel setFeePay10( BigDecimal feePay10 ) {
		this.feePay10 = feePay10;
		return this;
	}

	public BigDecimal  getFeeBack20() {
		return feeBack20;
	}

	public ProjectStopPModel setFeeBack20( BigDecimal feeBack20 ) {
		this.feeBack20 = feeBack20;
		return this;
	}

	public String  getFeeBack21() {
		return feeBack21;
	}

	public ProjectStopPModel setFeeBack21( String feeBack21 ) {
		this.feeBack21 = feeBack21;
		return this;
	}

	public BigDecimal  getFeeBack22() {
		return feeBack22;
	}

	public ProjectStopPModel setFeeBack22( BigDecimal feeBack22 ) {
		this.feeBack22 = feeBack22;
		return this;
	}

	public BigDecimal  getCFeePlan() {
		return cFeePlan;
	}

	public ProjectStopPModel setCFeePlan( BigDecimal cFeePlan ) {
		this.cFeePlan = cFeePlan;
		return this;
	}

	public String  getCFeePayed() {
		return cFeePayed;
	}

	public ProjectStopPModel setCFeePayed( String cFeePayed ) {
		this.cFeePayed = cFeePayed;
		return this;
	}

	public BigDecimal  getCFeePp() {
		return cFeePp;
	}

	public ProjectStopPModel setCFeePp( BigDecimal cFeePp ) {
		this.cFeePp = cFeePp;
		return this;
	}

	public BigDecimal  getCFeeInvoice() {
		return cFeeInvoice;
	}

	public ProjectStopPModel setCFeeInvoice( BigDecimal cFeeInvoice ) {
		this.cFeeInvoice = cFeeInvoice;
		return this;
	}

	public BigDecimal  getCFeePhase() {
		return cFeePhase;
	}

	public ProjectStopPModel setCFeePhase( BigDecimal cFeePhase ) {
		this.cFeePhase = cFeePhase;
		return this;
	}

	public BigDecimal  getCFeePenalty() {
		return cFeePenalty;
	}

	public ProjectStopPModel setCFeePenalty( BigDecimal cFeePenalty ) {
		this.cFeePenalty = cFeePenalty;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public ProjectStopPModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

}
