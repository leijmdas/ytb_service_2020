package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

public class CostSumRateModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.cost_sum_rate";

	private Integer rateId;

	private Integer projectId;

	private Integer documentId;

	private String rateDesc;

	private String rateValue;

	private String tips;

	private Integer talkId;

	public Integer  getRateId() {
		return rateId;
	}

	public CostSumRateModel setRateId( Integer rateId ) {
		this.rateId = rateId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public CostSumRateModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public CostSumRateModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public String  getRateDesc() {
		return rateDesc;
	}

	public CostSumRateModel setRateDesc( String rateDesc ) {
		this.rateDesc = rateDesc;
		return this;
	}

	public String  getRateValue() {
		return rateValue;
	}

	public CostSumRateModel setRateValue( String rateValue ) {
		this.rateValue = rateValue;
		return this;
	}

	public String  getTips() {
		return tips;
	}

	public CostSumRateModel setTips( String tips ) {
		this.tips = tips;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public CostSumRateModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

}
