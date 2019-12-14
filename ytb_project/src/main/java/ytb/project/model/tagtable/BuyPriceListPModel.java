package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

public class BuyPriceListPModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.buy_price_list_p";

	private Integer buyPriceListParamId;

	private Integer projectId;

	private Integer documentId;

	private Integer day1;

	private String receiverUser;

	private String receiverPhone;

	private String receiverAddr;

	private String receiverCompanyName;

	private String receiverCompanyCreditCode;

	private String receiverCompanyAddr;

	private Integer day2;

	private String sendUser;

	private String sendPhone;

	private String sendAddr;

	private String sendCompanyName;

	private String sendCompanyCreditCode;

	private String sendCompanyAddr;

	private Integer talkId;

	private String invoiceType;

	private String packageRequirement;


	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Integer  getBuyPriceListParamId() {
		return buyPriceListParamId;
	}

	public BuyPriceListPModel setBuyPriceListParamId( Integer buyPriceListParamId ) {
		this.buyPriceListParamId = buyPriceListParamId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public BuyPriceListPModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public BuyPriceListPModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getDay1() {
		return day1;
	}

	public BuyPriceListPModel setDay1( Integer day1 ) {
		this.day1 = day1;
		return this;
	}

	public String  getReceiverUser() {
		return receiverUser;
	}

	public BuyPriceListPModel setReceiverUser( String receiverUser ) {
		this.receiverUser = receiverUser;
		return this;
	}

	public String  getReceiverPhone() {
		return receiverPhone;
	}

	public BuyPriceListPModel setReceiverPhone( String receiverPhone ) {
		this.receiverPhone = receiverPhone;
		return this;
	}

	public String  getReceiverAddr() {
		return receiverAddr;
	}

	public BuyPriceListPModel setReceiverAddr( String receiverAddr ) {
		this.receiverAddr = receiverAddr;
		return this;
	}

	public String  getReceiverCompanyName() {
		return receiverCompanyName;
	}

	public BuyPriceListPModel setReceiverCompanyName( String receiverCompanyName ) {
		this.receiverCompanyName = receiverCompanyName;
		return this;
	}

	public String  getReceiverCompanyCreditCode() {
		return receiverCompanyCreditCode;
	}

	public BuyPriceListPModel setReceiverCompanyCreditCode( String receiverCompanyCreditCode ) {
		this.receiverCompanyCreditCode = receiverCompanyCreditCode;
		return this;
	}

	public String  getReceiverCompanyAddr() {
		return receiverCompanyAddr;
	}

	public BuyPriceListPModel setReceiverCompanyAddr( String receiverCompanyAddr ) {
		this.receiverCompanyAddr = receiverCompanyAddr;
		return this;
	}

	public Integer  getDay2() {
		return day2;
	}

	public BuyPriceListPModel setDay2( Integer day2 ) {
		this.day2 = day2;
		return this;
	}

	public String  getSendUser() {
		return sendUser;
	}

	public BuyPriceListPModel setSendUser( String sendUser ) {
		this.sendUser = sendUser;
		return this;
	}

	public String  getSendPhone() {
		return sendPhone;
	}

	public BuyPriceListPModel setSendPhone( String sendPhone ) {
		this.sendPhone = sendPhone;
		return this;
	}

	public String  getSendAddr() {
		return sendAddr;
	}

	public BuyPriceListPModel setSendAddr( String sendAddr ) {
		this.sendAddr = sendAddr;
		return this;
	}

	public String  getSendCompanyName() {
		return sendCompanyName;
	}

	public BuyPriceListPModel setSendCompanyName( String sendCompanyName ) {
		this.sendCompanyName = sendCompanyName;
		return this;
	}

	public String  getSendCompanyCreditCode() {
		return sendCompanyCreditCode;
	}

	public BuyPriceListPModel setSendCompanyCreditCode( String sendCompanyCreditCode ) {
		this.sendCompanyCreditCode = sendCompanyCreditCode;
		return this;
	}

	public String  getSendCompanyAddr() {
		return sendCompanyAddr;
	}

	public BuyPriceListPModel setSendCompanyAddr( String sendCompanyAddr ) {
		this.sendCompanyAddr = sendCompanyAddr;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public BuyPriceListPModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}


	public String  getPackageRequirement() {
		return packageRequirement;
	}

	public BuyPriceListPModel setPackageRequirement( String packageRequirement ) {
		this.packageRequirement = packageRequirement;
		return this;
	}

}
