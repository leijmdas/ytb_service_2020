package ytb.manager.charges.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 */
public class DictAreaSalary implements Serializable {
    /**
     * 表标识
     */
    private Integer salaryId;

    /**
     * 版本号
     */
    private String versionId;

    /**
     * 地区标识
     */
    private Integer areaId;

    /**
     * 最低月薪
     */
    private BigDecimal salary;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 录入人
     */
    private Integer createBy;

    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核人
     */
    private String auditBy;

    private DictArea dictArea;

    public DictArea getDictArea() {
        return dictArea;
    }

    public void setDictArea(DictArea dictArea) {
        this.dictArea = dictArea;
    }

    private static final long serialVersionUID = 1L;

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", salaryId=").append(salaryId);
        sb.append(", versionId=").append(versionId);
        sb.append(", areaId=").append(areaId);
        sb.append(", salary=").append(salary);
        sb.append(", status=").append(status);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", auditBy=").append(auditBy);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}