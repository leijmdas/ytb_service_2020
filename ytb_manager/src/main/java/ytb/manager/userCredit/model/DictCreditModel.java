package ytb.manager.userCredit.model;

import ytb.common.context.model.Ytb_Model;

public class DictCreditModel extends Ytb_Model {
	public static String TABLE_NAME = "ytb_manager.dict_credit";

	private String creditGrade;

	private Short creditQ;

	private String creditRemark;

	public String  getCreditGrade() {
		return creditGrade;
	}

	public DictCreditModel setCreditGrade(String creditGrade ) {
		this.creditGrade = creditGrade;
		return this;
	}

	public Short  getCreditQ() {
		return creditQ;
	}

	public DictCreditModel setCreditQ(Short creditQ ) {
		this.creditQ = creditQ;
		return this;
	}

	public String  getCreditRemark() {
		return creditRemark;
	}

	public DictCreditModel setCreditRemark(String creditRemark ) {
		this.creditRemark = creditRemark;
		return this;
	}

}
