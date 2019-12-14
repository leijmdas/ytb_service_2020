package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;

public class PpBudgetModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.pp_budget";

	private Integer ppbId;

	private Integer projectId;

	private Integer documentId;

	private BigDecimal purchasePrice;

	private Integer processCount;

	private BigDecimal processPrice;

	private BigDecimal sum;

	private String tips;

	private String ppdUserName;

	private Integer userId;

	private Integer purchaseCount;

	private Integer talkId;

	public Integer  getPpbId() {
		return ppbId;
	}

	public PpBudgetModel setPpbId( Integer ppbId ) {
		this.ppbId = ppbId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public PpBudgetModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public PpBudgetModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public BigDecimal  getPurchasePrice() {
		return purchasePrice;
	}

	public PpBudgetModel setPurchasePrice( BigDecimal purchasePrice ) {
		this.purchasePrice = purchasePrice;
		return this;
	}

	public Integer  getProcessCount() {
		return processCount;
	}

	public PpBudgetModel setProcessCount( Integer processCount ) {
		this.processCount = processCount;
		return this;
	}

	public BigDecimal  getProcessPrice() {
		return processPrice;
	}

	public PpBudgetModel setProcessPrice( BigDecimal processPrice ) {
		this.processPrice = processPrice;
		return this;
	}

	public BigDecimal  getSum() {
		return sum;
	}

	public PpBudgetModel setSum( BigDecimal sum ) {
		this.sum = sum;
		return this;
	}

	public String  getTips() {
		return tips;
	}

	public PpBudgetModel setTips( String tips ) {
		this.tips = tips;
		return this;
	}

	public String  getPpdUserName() {
		return ppdUserName;
	}

	public PpBudgetModel setPpdUserName( String ppdUserName ) {
		this.ppdUserName = ppdUserName;
		return this;
	}

	public Integer  getUserId() {
		return userId;
	}

	public PpBudgetModel setUserId( Integer userId ) {
		this.userId = userId;
		return this;
	}

	public Integer  getPurchaseCount() {
		return purchaseCount;
	}

	public PpBudgetModel setPurchaseCount( Integer purchaseCount ) {
		this.purchaseCount = purchaseCount;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public PpBudgetModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

}
