package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;

public class ProcessListPriceAllModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.process_list_price_all";

	private Integer processListPriceAllId;

	private String allMoneyDesc;

	private BigDecimal allMoneyValue;

	private Integer projectId;

	private Integer documentId;

	private Integer talkId;

	public Integer  getProcessListPriceAllId() {
		return processListPriceAllId;
	}

	public ProcessListPriceAllModel setProcessListPriceAllId( Integer processListPriceAllId ) {
		this.processListPriceAllId = processListPriceAllId;
		return this;
	}

	public String  getAllMoneyDesc() {
		return allMoneyDesc;
	}

	public ProcessListPriceAllModel setAllMoneyDesc( String allMoneyDesc ) {
		this.allMoneyDesc = allMoneyDesc;
		return this;
	}

	public BigDecimal  getAllMoneyValue() {
		return allMoneyValue;
	}

	public ProcessListPriceAllModel setAllMoneyValue( BigDecimal allMoneyValue ) {
		this.allMoneyValue = allMoneyValue;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public ProcessListPriceAllModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public ProcessListPriceAllModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public ProcessListPriceAllModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

}
