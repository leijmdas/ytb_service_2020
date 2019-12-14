package ytb.project.model.tagtable;

import ytb.project.common.ProjectConst;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CostModel extends Ytb_ModelSkipNull {
	public static String TABLE_NAME = "ytb_project.cost";

	private Integer costId;

	private Integer projectId;

	private Integer talkId;

	private Integer documentId;

	private Integer userId;

	private String userName = "";

	private String areaName = "";

	private BigDecimal dayPay= BigDecimal.ZERO;
	private BigDecimal costPhase0= BigDecimal.ZERO;

	private BigDecimal costPhase1= BigDecimal.ZERO;

	private BigDecimal costPhase2= BigDecimal.ZERO;

	private BigDecimal costPhase3 = BigDecimal.ZERO;

	private BigDecimal costPhase4 = BigDecimal.ZERO;

	private BigDecimal costPhase5 = BigDecimal.ZERO;

	private BigDecimal costPhase6 = BigDecimal.ZERO;
	private BigDecimal costPhase7 = BigDecimal.ZERO;
	private BigDecimal costPhase8 = BigDecimal.ZERO;

	private BigDecimal costPhase9 = BigDecimal.ZERO;

	private BigDecimal incomeSum = BigDecimal.ZERO;


	private BigDecimal costSum = BigDecimal.ZERO;

	private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

	private Integer areaId = 0;

	public Integer getTalkId() {
		return talkId;
	}

	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}

	public Integer  getCostId() {
		return costId;
	}

	public CostModel setCostId( Integer costId ) {
		this.costId = costId;
		return this;
	}

	public Integer  getProjectId() {
		return projectId;
	}

	public CostModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public CostModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Integer  getUserId() {
		return userId;
	}

	public CostModel setUserId( Integer userId ) {
		this.userId = userId;
		return this;
	}

	public String  getUserName() {
		return userName;
	}

	public CostModel setUserName( String userName ) {
		this.userName = userName;
		return this;
	}

	public String  getAreaName() {
		return areaName;
	}

	public CostModel setAreaName( String areaName ) {
		this.areaName = areaName;
		return this;
	}

	public BigDecimal  getDayPay() {
		return dayPay;
	}

	public CostModel setDayPay( BigDecimal dayPay ) {
		this.dayPay = dayPay;
		return this;
	}

	public BigDecimal getCostPhase(int phase) {
		if (ProjectConst.Phase0 == phase) {
			return costPhase0;
		}
		if (ProjectConst.Phase1 == phase) {
			return costPhase1;
		}

		if (ProjectConst.Phase2 == phase) {
			return costPhase2;
		}
		if (ProjectConst.Phase3 == phase) {
			return costPhase3;
		}
		if (ProjectConst.Phase4 == phase) {
			return costPhase4;
		}
		if (ProjectConst.Phase5 == phase) {
			return costPhase5;
		}
		if (ProjectConst.Phase6 == phase) {
			return costPhase6;
		}
		if (ProjectConst.Phase7 == phase) {
			return costPhase7;
		}
		if (ProjectConst.Phase8 == phase) {
			return costPhase8;
		}
		if (ProjectConst.Phase9 == phase) {
			return costPhase9;
		}

		return costPhase0;
	}

	public void setCostPhase(int phase,BigDecimal costPhase) {
		if (ProjectConst.Phase0 == phase) {
			costPhase0 = costPhase;
		}
		if (ProjectConst.Phase1 == phase) {
			costPhase1 = costPhase;
		}

		if (ProjectConst.Phase2 == phase) {
			costPhase2 = costPhase;
		}
		if (ProjectConst.Phase3 == phase) {
			costPhase3 = costPhase;
		}
		if (ProjectConst.Phase4 == phase) {
			costPhase4 = costPhase;

		}
		if (ProjectConst.Phase5 == phase) {
			costPhase5 = costPhase;

		}
		if (ProjectConst.Phase6 == phase) {
			costPhase6 = costPhase;

		}
		if (ProjectConst.Phase7 == phase) {
			costPhase7 = costPhase;
		}
		if (ProjectConst.Phase8 == phase) {
			costPhase8 = costPhase;

		}
		if (ProjectConst.Phase9 == phase) {
			costPhase9 = costPhase;

		}

	}

	public BigDecimal getCostPhase1() {
		return costPhase1;
	}

	public CostModel setCostPhase1( BigDecimal costPhase1 ) {
		this.costPhase1 = costPhase1;
		return this;
	}

	public BigDecimal  getCostPhase2() {
		return costPhase2;
	}

	public CostModel setCostPhase2( BigDecimal costPhase2 ) {
		this.costPhase2 = costPhase2;
		return this;
	}

	public BigDecimal  getCostPhase3() {
		return costPhase3;
	}

	public CostModel setCostPhase3( BigDecimal costPhase3 ) {
		this.costPhase3 = costPhase3;
		return this;
	}

	public BigDecimal  getCostPhase4() {
		return costPhase4;
	}

	public CostModel setCostPhase4( BigDecimal costPhase4 ) {
		this.costPhase4 = costPhase4;
		return this;
	}

	public BigDecimal  getCostPhase5() {
		return costPhase5;
	}

	public CostModel setCostPhase5( BigDecimal costPhase5 ) {
		this.costPhase5 = costPhase5;
		return this;
	}

	public BigDecimal  getCostPhase6() {
		return costPhase6;
	}

	public CostModel setCostPhase6( BigDecimal costPhase6 ) {
		this.costPhase6 = costPhase6;
		return this;
	}

	public BigDecimal  getIncomeSum() {
		return incomeSum;
	}

	public CostModel setIncomeSum( BigDecimal incomeSum ) {
		this.incomeSum = incomeSum;
		return this;
	}



	public BigDecimal  getCostSum() {
		return costSum;
	}

	public CostModel setCostSum( BigDecimal costSum ) {
		this.costSum = costSum;
		return this;
	}

	public Timestamp  getUpdateTime() {
		return updateTime;
	}

	public CostModel setUpdateTime( Timestamp updateTime ) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer  getAreaId() {
		return areaId;
	}

	public CostModel setAreaId( Integer areaId ) {
		this.areaId = areaId;
		return this;
	}

}
