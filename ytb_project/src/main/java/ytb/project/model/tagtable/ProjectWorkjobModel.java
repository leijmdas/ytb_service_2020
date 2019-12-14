package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

public class ProjectWorkjobModel extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.project_workjob";

	private Integer projectWorkjobId;

	private Integer projectId;

	private Integer documentId;

	private Integer reqItemId;

	private Integer workJobId;

	private String workJob;

	private Short phaseType;

	private Boolean isDefault;

	private String titleAlias;

	public Integer  getProjectWorkjobId() {
		return projectWorkjobId;
	}

	public ProjectWorkjobModel setProjectWorkjobId(Integer projectWorkjobId ) {
		this.projectWorkjobId = projectWorkjobId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public ProjectWorkjobModel setProjectId(Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public ProjectWorkjobModel setDocumentId(Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getReqItemId() {
		return reqItemId;
	}

	public ProjectWorkjobModel setReqItemId(Integer reqItemId ) {
		this.reqItemId = reqItemId;
		return this;
	}

	public Integer  getWorkJobId() {
		return workJobId;
	}

	public ProjectWorkjobModel setWorkJobId(Integer workJobId ) {
		this.workJobId = workJobId;
		return this;
	}

	public String  getWorkJob() {
		return workJob;
	}

	public ProjectWorkjobModel setWorkJob(String workJob ) {
		this.workJob = workJob;
		return this;
	}

	public Short  getPhaseType() {
		return phaseType;
	}

	public ProjectWorkjobModel setPhaseType(Short phaseType ) {
		this.phaseType = phaseType;
		return this;
	}

	public Boolean  getIsDefault() {
		return isDefault;
	}

	public ProjectWorkjobModel setIsDefault(Boolean isDefault ) {
		this.isDefault = isDefault;
		return this;
	}

	public String  getTitleAlias() {
		return titleAlias;
	}

	public ProjectWorkjobModel setTitleAlias(String titleAlias ) {
		this.titleAlias = titleAlias;
		return this;
	}

}
