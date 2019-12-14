package ytb.project.model;

import ytb.common.context.model.Ytb_Model;
import java.sql.Timestamp;

public class ProjectTradeDetailModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.project_trade_detail";

	private Integer tradeId;

	private byte[] memo;

	private Timestamp updateTime;

	public Integer  getTradeId() {
		return tradeId;
	}

	public ProjectTradeDetailModel setTradeId( Integer tradeId ) {
		this.tradeId = tradeId;
		return this;
	}

	public byte[]  getMemo() {
		return memo;
	}

	public ProjectTradeDetailModel setMemo( byte[] memo ) {
		this.memo = memo;
		return this;
	}

	public Timestamp  getUpdateTime() {
		return updateTime;
	}

	public ProjectTradeDetailModel setUpdateTime( Timestamp updateTime ) {
		this.updateTime = updateTime;
		return this;
	}

}
