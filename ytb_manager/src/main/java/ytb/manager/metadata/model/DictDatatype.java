package ytb.manager.metadata.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DictDatatype implements Serializable {
    /**
     * 字典ID
     */
    private Integer dataInnerId;

    /**
     * 分类编号
     */
    private Integer typeId;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 字典编号
     */
    private Integer dataId;

    /**
     * 字典名称
     */
    private String dataName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getDataInnerId() {
        return dataInnerId;
    }

    public void setDataInnerId(Integer dataInnerId) {
        this.dataInnerId = dataInnerId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        sb.append(", dataInnerId=").append(dataInnerId);
        sb.append(", typeId=").append(typeId);
        sb.append(", typeName=").append(typeName);
        sb.append(", dataId=").append(dataId);
        sb.append(", dataName=").append(dataName);
        sb.append(", remark=").append(remark);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}