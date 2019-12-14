package ytb.manager.tagtable.model.business;

import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;

public class VwareaSalaryModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_manager.vwarea_salary";

	private Integer areaId;

	private String name;

	private BigDecimal dayPay;

	public Integer  getAreaId() {
		return areaId;
	}

	public VwareaSalaryModel setAreaId( Integer areaId ) {
		this.areaId = areaId;
		return this;
	}

	public String  getName() {
		return name;
	}

	public VwareaSalaryModel setName( String name ) {
		this.name = name;
		return this;
	}

	public BigDecimal  getDayPay() {
		return dayPay;
	}

	public VwareaSalaryModel setDayPay( BigDecimal dayPay ) {
		this.dayPay = dayPay;
		return this;
	}

}
