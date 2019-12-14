package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

public class CostSumInvoiceModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.cost_sum_invoice";

	private Integer invoiceId;

	private Integer invoiceNo1;

	private String invoiceNo2;

	private Integer invoiceY1;

	private String invoiceY2;

	private Integer invoicePf1;

	private String invoicePf2;

	private String invoiceDesc;

	private Integer projectId;

	private Integer documentId;

	private Integer talkId;

	public Integer  getInvoiceId() {
		return invoiceId;
	}

	public CostSumInvoiceModel setInvoiceId( Integer invoiceId ) {
		this.invoiceId = invoiceId;
		return this;
	}

	public Integer  getInvoiceNo1() {
		return invoiceNo1;
	}

	public CostSumInvoiceModel setInvoiceNo1( Integer invoiceNo1 ) {
		this.invoiceNo1 = invoiceNo1;
		return this;
	}

	public String  getInvoiceNo2() {
		return invoiceNo2;
	}

	public CostSumInvoiceModel setInvoiceNo2( String invoiceNo2 ) {
		this.invoiceNo2 = invoiceNo2;
		return this;
	}

	public Integer  getInvoiceY1() {
		return invoiceY1;
	}

	public CostSumInvoiceModel setInvoiceY1( Integer invoiceY1 ) {
		this.invoiceY1 = invoiceY1;
		return this;
	}

	public String  getInvoiceY2() {
		return invoiceY2;
	}

	public CostSumInvoiceModel setInvoiceY2( String invoiceY2 ) {
		this.invoiceY2 = invoiceY2;
		return this;
	}

	public Integer  getInvoicePf1() {
		return invoicePf1;
	}

	public CostSumInvoiceModel setInvoicePf1( Integer invoicePf1 ) {
		this.invoicePf1 = invoicePf1;
		return this;
	}

	public String  getInvoicePf2() {
		return invoicePf2;
	}

	public CostSumInvoiceModel setInvoicePf2( String invoicePf2 ) {
		this.invoicePf2 = invoicePf2;
		return this;
	}

	public String  getInvoiceDesc() {
		return invoiceDesc;
	}

	public CostSumInvoiceModel setInvoiceDesc( String invoiceDesc ) {
		this.invoiceDesc = invoiceDesc;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public CostSumInvoiceModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public CostSumInvoiceModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public CostSumInvoiceModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

}
