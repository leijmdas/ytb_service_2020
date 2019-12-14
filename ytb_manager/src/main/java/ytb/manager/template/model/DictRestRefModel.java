package ytb.manager.template.model;

import ytb.common.context.model.Ytb_Model;

public class DictRestRefModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_manager.dict_rest_ref";

	private Short refId;

	private String refPath;

	private Short refNum;

	private String refParam;

	public Short  getRefId() {
		return refId;
	}

	public DictRestRefModel setRefId( Short refId ) {
		this.refId = refId;
		return this;
	}

	public String  getRefPath() {
		return refPath;
	}

	public DictRestRefModel setRefPath( String refPath ) {
		this.refPath = refPath;
		return this;
	}

	public Short  getRefNum() {
		return refNum;
	}

	public DictRestRefModel setRefNum( Short refNum ) {
		this.refNum = refNum;
		return this;
	}

	public String getRefParam() {
		return refParam;
	}

	public void setRefParam(String refParam) {
		this.refParam = refParam;
	}
}
