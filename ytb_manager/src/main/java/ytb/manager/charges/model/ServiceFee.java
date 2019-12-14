package ytb.manager.charges.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class ServiceFee implements Serializable {
    /**
     * 服务费标识
     */
    private Integer feeId;

    /**
     * 项目收费类型
     */
    private String projectType;

    /**
     * 费率(%)
     */
    private BigDecimal feeRate;

    /**
     * 起点费率>=
     */
    private Integer lowerLimit;

    /**
     * 上限费率<=
     */
    private Integer upperLimit;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 描述
     */
    private String describeText;

    /**
     * 录入人
     */
    private Integer createBy;

    /**
     * 录入时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getFeeId() {
        return feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescribeText() {
        return describeText;
    }

    public void setDescribeText(String describeText) {
        this.describeText = describeText;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", feeId=").append(feeId);
        sb.append(", projectType=").append(projectType);
        sb.append(", feeRate=").append(feeRate);
        sb.append(", lowerLimit=").append(lowerLimit);
        sb.append(", upperLimit=").append(upperLimit);
        sb.append(", state=").append(state);
        sb.append(", describeText=").append(describeText);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}