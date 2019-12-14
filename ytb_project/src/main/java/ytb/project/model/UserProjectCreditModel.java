package ytb.project.model;

import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;
public class UserProjectCreditModel  extends Ytb_Model {


	public static String TABLE_NAME = "ytb_user.user_project_credit";

	// 用户名称
	private String name;
	//项目名称
	private String projectName;

	private Integer creditId;
	//'用户标识'
	private Integer userId;
	//'项目标识'
	private Integer projectId;
	//'严重超期Q值'
	private BigDecimal delayQ;
	//完成项目Q值'
	private BigDecimal finishQ;
	//'终止项目Q值'
	private BigDecimal stopQ;

	public Integer  getUserId() {
		return userId;
	}

	public UserProjectCreditModel setUserId( Integer userId ) {
		this.userId = userId;
		return this;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer  getProjectId() {
		return projectId;
	}

	public UserProjectCreditModel setProjectId( Integer projectId ) {
		this.projectId = projectId;
		return this;
	}

	public BigDecimal  getDelayQ() {
		return delayQ;
	}

	public UserProjectCreditModel setDelayQ( BigDecimal delayQ ) {
		this.delayQ = delayQ;
		return this;
	}

	public BigDecimal  getFinishQ() {
		return finishQ;
	}

	public UserProjectCreditModel setFinishQ( BigDecimal finishQ ) {
		this.finishQ = finishQ;
		return this;
	}

	public Integer getCreditId() {
		return creditId;
	}

	public void setCreditId(Integer creditId) {
		this.creditId = creditId;
	}

	public BigDecimal  getStopQ() {
		return stopQ;
	}

	public UserProjectCreditModel setStopQ( BigDecimal stopQ ) {
		this.stopQ = stopQ;
		return this;
	}

}
