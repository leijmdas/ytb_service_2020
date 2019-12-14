package ytb.manager.metadata.model;

/**
 * 数据字典表实体类
 * Package: ytb.manager.metadata.model
 * Author: XZW
 */
public class Sys_DictDataTypeModel {

    //字典id
    private int dataInnerId;
    //分类编号
    private int typeId;
    //分类名称
    private String typeName;
    //字典名称
    private String dataName;
    //字典id
    private int dataId;
    //创建时间
    private String createTime;
    //创建人
    private int createBy;
    //备注
    private String remark;
    private int parentid;

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public void setDataInnerId(int dataInnerId) {
        this.dataInnerId = dataInnerId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDataInnerId() {
        return dataInnerId;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDataName() {
        return dataName;
    }

    public int getDataId() {
        return dataId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public int getCreateBy() {
        return createBy;
    }

    public String getRemark() {
        return remark;
    }
}
