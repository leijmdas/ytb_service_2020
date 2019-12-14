package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

public class ProcessListPModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.process_list_p";

	private Integer processListPId;

	private Integer projectId;

	private Integer documentId;

	private Integer day;

	private String receivedUser;

	private String receivedPhone;

	private String receivedAddr;

	private String receivedCompanyName;

	private String receivedCompanyCreditCode;

	private String receivedCompanyAddr;

	private Integer talkId;
	private String invoiceType;

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}


	public Integer  getProcessListPId() {
		return processListPId;
	}

	public ProcessListPModel setProcessListPId( Integer processListPId ) {
		this.processListPId = processListPId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public ProcessListPModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public ProcessListPModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getDay() {
		return day;
	}

	public ProcessListPModel setDay( Integer day ) {
		this.day = day;
		return this;
	}

	public String  getReceivedUser() {
		return receivedUser;
	}

	public ProcessListPModel setReceivedUser( String receivedUser ) {
		this.receivedUser = receivedUser;
		return this;
	}

	public String  getReceivedPhone() {
		return receivedPhone;
	}

	public ProcessListPModel setReceivedPhone( String receivedPhone ) {
		this.receivedPhone = receivedPhone;
		return this;
	}

	public String  getReceivedAddr() {
		return receivedAddr;
	}

	public ProcessListPModel setReceivedAddr( String receivedAddr ) {
		this.receivedAddr = receivedAddr;
		return this;
	}

	public String  getReceivedCompanyName() {
		return receivedCompanyName;
	}

	public ProcessListPModel setReceivedCompanyName( String receivedCompanyName ) {
		this.receivedCompanyName = receivedCompanyName;
		return this;
	}

	public String  getReceivedCompanyCreditCode() {
		return receivedCompanyCreditCode;
	}

	public ProcessListPModel setReceivedCompanyCreditCode( String receivedCompanyCreditCode ) {
		this.receivedCompanyCreditCode = receivedCompanyCreditCode;
		return this;
	}

	public String  getReceivedCompanyAddr() {
		return receivedCompanyAddr;
	}

	public ProcessListPModel setReceivedCompanyAddr( String receivedCompanyAddr ) {
		this.receivedCompanyAddr = receivedCompanyAddr;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public ProcessListPModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

}
