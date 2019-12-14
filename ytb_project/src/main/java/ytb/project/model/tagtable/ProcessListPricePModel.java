package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;


public class ProcessListPricePModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.process_list_price_p";

	private Integer processListPricePId;

	private Integer projectId;

	private Integer documentId;

	private Integer day;

	private String receivedUser;

	private String receivedPhone;

	private String receivedAddr;

	private String receivedCompanyName;

	private String receivedCompanyCreditCode;

	private String receivedCompanyAddr;

	private String sendUser;

	private String sendPhone;

	private String sendAddr;

	private String sendCompanyName;

	private String sendCompanyCreditCode;

	private String sendCompanyAddr;

	private Integer talkId;

	private String invoiceType;

	private String packageRequirement;

	public Integer  getProcessListPricePId() {
		return processListPricePId;
	}

	public ProcessListPricePModel setProcessListPricePId( Integer processListPricePId ) {
		this.processListPricePId = processListPricePId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public ProcessListPricePModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public ProcessListPricePModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getDay() {
		return day;
	}

	public ProcessListPricePModel setDay( Integer day ) {
		this.day = day;
		return this;
	}

	public String  getReceivedUser() {
		return receivedUser;
	}

	public ProcessListPricePModel setReceivedUser( String receivedUser ) {
		this.receivedUser = receivedUser;
		return this;
	}

	public String  getReceivedPhone() {
		return receivedPhone;
	}

	public ProcessListPricePModel setReceivedPhone( String receivedPhone ) {
		this.receivedPhone = receivedPhone;
		return this;
	}

	public String  getReceivedAddr() {
		return receivedAddr;
	}

	public ProcessListPricePModel setReceivedAddr( String receivedAddr ) {
		this.receivedAddr = receivedAddr;
		return this;
	}

	public String  getReceivedCompanyName() {
		return receivedCompanyName;
	}

	public ProcessListPricePModel setReceivedCompanyName( String receivedCompanyName ) {
		this.receivedCompanyName = receivedCompanyName;
		return this;
	}

	public String  getReceivedCompanyCreditCode() {
		return receivedCompanyCreditCode;
	}

	public ProcessListPricePModel setReceivedCompanyCreditCode( String receivedCompanyCreditCode ) {
		this.receivedCompanyCreditCode = receivedCompanyCreditCode;
		return this;
	}

	public String  getReceivedCompanyAddr() {
		return receivedCompanyAddr;
	}

	public ProcessListPricePModel setReceivedCompanyAddr( String receivedCompanyAddr ) {
		this.receivedCompanyAddr = receivedCompanyAddr;
		return this;
	}

	public String  getSendUser() {
		return sendUser;
	}

	public ProcessListPricePModel setSendUser( String sendUser ) {
		this.sendUser = sendUser;
		return this;
	}

	public String  getSendPhone() {
		return sendPhone;
	}

	public ProcessListPricePModel setSendPhone( String sendPhone ) {
		this.sendPhone = sendPhone;
		return this;
	}

	public String  getSendAddr() {
		return sendAddr;
	}

	public ProcessListPricePModel setSendAddr( String sendAddr ) {
		this.sendAddr = sendAddr;
		return this;
	}

	public String  getSendCompanyName() {
		return sendCompanyName;
	}

	public ProcessListPricePModel setSendCompanyName( String sendCompanyName ) {
		this.sendCompanyName = sendCompanyName;
		return this;
	}

	public String  getSendCompanyCreditCode() {
		return sendCompanyCreditCode;
	}

	public ProcessListPricePModel setSendCompanyCreditCode( String sendCompanyCreditCode ) {
		this.sendCompanyCreditCode = sendCompanyCreditCode;
		return this;
	}

	public String  getSendCompanyAddr() {
		return sendCompanyAddr;
	}

	public ProcessListPricePModel setSendCompanyAddr( String sendCompanyAddr ) {
		this.sendCompanyAddr = sendCompanyAddr;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public ProcessListPricePModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

	public String  getInvoiceType() {
		return invoiceType;
	}

	public ProcessListPricePModel setInvoiceType( String invoiceType ) {
		this.invoiceType = invoiceType;
		return this;
	}

	public String  getPackageRequirement() {
		return packageRequirement;
	}

	public ProcessListPricePModel setPackageRequirement( String packageRequirement ) {
		this.packageRequirement = packageRequirement;
		return this;
	}

}
