package ytb.project.model.tagtable;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.common.context.model.Ytb_Model;
import ytb.project.model.ProjectTalkModel;

import java.math.BigDecimal;

public class ProjectChngPModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.project_chng_p";

	private Integer chngId;

	private Integer projectId;


	private Integer talkId;

	private Integer documentId;

	private String projectNamePre="";

	private String projectName;

	private String phase;

	private BigDecimal feePre;

	private BigDecimal feeNow;

	private BigDecimal feeDiff;

	private BigDecimal feePayed = BigDecimal.ZERO;

	private BigDecimal feePp = BigDecimal.ZERO;

	private BigDecimal feeInvoice = BigDecimal.ZERO;

	private BigDecimal feePhase = BigDecimal.ZERO;

	private BigDecimal feeMakeup = BigDecimal.ZERO;

	private BigDecimal rateGp = BigDecimal.ZERO;

	public ProjectChngPModel() {
	}

	public ProjectChngPModel(UserProjectContext oldContext, UserProjectContext newContext) {
		ProjectModel oldPm = oldContext.getProjectModel();
		ProjectTalkModel oldPtm = oldContext.getProjectTalkModel();
		ProjectModel newPm = newContext.getProjectModel();
		ProjectTalkModel newPtm = newContext.getProjectTalkModel();

		ProjectChngPModel chngPModel=this;
		chngPModel.setProjectId(newPm.getProjectId());
		chngPModel.setTalkId(newPtm.getTalkId());
		chngPModel.setDocumentId(newPtm.selectChangeTemplate().getDocumentId());
		//原项目名称
		chngPModel.setProjectName(newPm.getProjectName());
		//新项目名称
		chngPModel.setProjectNamePre(oldPm.getProjectName());
		//项目进度
		chngPModel.setPhase("第" + (oldPtm.getPhase() - oldPm.getPhaseStart()+1) + "阶段");

	}

	public Integer getTalkId() {
		return talkId;
	}

	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}

	public Integer getChngId() {
		return chngId;
	}

	public ProjectChngPModel setChngId(Integer chngId) {
		this.chngId = chngId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public ProjectChngPModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public ProjectChngPModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public String  getProjectNamePre() {
		return projectNamePre;
	}

	public ProjectChngPModel setProjectNamePre( String projectNamePre ) {
		this.projectNamePre = projectNamePre;
		return this;
	}

	public String  getProjectName() {
		return projectName;
	}

	public ProjectChngPModel setProjectName( String projectName ) {
		this.projectName = projectName;
		return this;
	}

	public String  getPhase() {
		return phase;
	}

	public ProjectChngPModel setPhase( String phase ) {
		this.phase = phase;
		return this;
	}

	public BigDecimal  getFeePre() {
		return feePre;
	}

	public ProjectChngPModel setFeePre( BigDecimal feePre ) {
		this.feePre = feePre;
		return this;
	}

	public BigDecimal  getFeeNow() {
		return feeNow;
	}

	public ProjectChngPModel setFeeNow( BigDecimal feeNow ) {
		this.feeNow = feeNow;
		return this;
	}

	public BigDecimal  getFeeDiff() {
		return feeDiff;
	}

	public ProjectChngPModel setFeeDiff( BigDecimal feeDiff ) {
		this.feeDiff = feeDiff;
		return this;
	}

	public BigDecimal  getFeePayed() {
		return feePayed;
	}

	public ProjectChngPModel setFeePayed( BigDecimal feePayed ) {
		this.feePayed = feePayed;
		return this;
	}

	public BigDecimal  getFeePp() {
		return feePp;
	}

	public ProjectChngPModel setFeePp( BigDecimal feePp ) {
		this.feePp = feePp;
		return this;
	}

	public BigDecimal  getFeeInvoice() {
		return feeInvoice;
	}

	public ProjectChngPModel setFeeInvoice( BigDecimal feeInvoice ) {
		this.feeInvoice = feeInvoice;
		return this;
	}

	public BigDecimal  getFeePhase() {
		return feePhase;
	}

	public ProjectChngPModel setFeePhase( BigDecimal feePhase ) {
		this.feePhase = feePhase;
		return this;
	}

	public BigDecimal  getFeeMakeup() {
		return feeMakeup;
	}

	public ProjectChngPModel setFeeMakeup( BigDecimal feeMakeup ) {
		this.feeMakeup = feeMakeup;
		return this;
	}

	public BigDecimal  getRateGp() {
		return rateGp;
	}

	public ProjectChngPModel setRateGp( BigDecimal rateGp ) {
		this.rateGp = rateGp;
		return this;
	}

}
