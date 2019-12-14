package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;
public class BuyPriceListAllModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.buy_price_list_all";

	private Integer buyPriceListAllId;

	private String allMoneyDesc;

	private BigDecimal allMoneyValue;

	private Integer projectId;

	private Integer documentId;

	private Integer talkId;

	public Integer  getBuyPriceListAllId() {
		return buyPriceListAllId;
	}

	public BuyPriceListAllModel setBuyPriceListAllId( Integer buyPriceListAllId ) {
		this.buyPriceListAllId = buyPriceListAllId;
		return this;
	}

	public String  getAllMoneyDesc() {
		return allMoneyDesc;
	}

	public BuyPriceListAllModel setAllMoneyDesc( String allMoneyDesc ) {
		this.allMoneyDesc = allMoneyDesc;
		return this;
	}

	public BigDecimal  getAllMoneyValue() {
		return allMoneyValue;
	}

	public BuyPriceListAllModel setAllMoneyValue( BigDecimal allMoneyValue ) {
		this.allMoneyValue = allMoneyValue;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public BuyPriceListAllModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public BuyPriceListAllModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getTalkId() {
		return talkId;
	}

	public BuyPriceListAllModel setTalkId( Integer talkId ) {
		this.talkId = talkId;
		return this;
	}

}
