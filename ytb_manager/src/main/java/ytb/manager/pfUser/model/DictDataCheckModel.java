package ytb.manager.pfUser.model;

import ytb.common.context.model.Ytb_Model;

public class DictDataCheckModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_manager.dict_data_check";

	private Integer checkId;

	private String checkName;

	private String checkSp;

	private String checkRet;

	public Integer  getCheckId() {
		return checkId;
	}

	public DictDataCheckModel setCheckId( Integer checkId ) {
		this.checkId = checkId;
		return this;
	}

	public String  getCheckName() {
		return checkName;
	}

	public DictDataCheckModel setCheckName( String checkName ) {
		this.checkName = checkName;
		return this;
	}

	public String  getCheckSp() {
		return checkSp;
	}

	public DictDataCheckModel setCheckSp( String checkSp ) {
		this.checkSp = checkSp;
		return this;
	}

	public String  getCheckRet() {
		return checkRet;
	}

	public DictDataCheckModel setCheckRet( String checkRet ) {
		this.checkRet = checkRet;
		return this;
	}

}
