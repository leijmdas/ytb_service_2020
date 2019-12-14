package ytb.manager.webpagemng.model;

import java.util.Date;

/**
 * @author lxz 2018/12/22 14:56
 */
public class CusServiceQuestionType {

    private int id;

    private String typeName;

    private int parentId;

    private Date createTime;

    private int createBy;

    private int sortNo;

    public CusServiceQuestionType() {
    }

    @Override
    public String toString() {
        return "CusServiceQuestionType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", parentId=" + parentId +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", sortNo=" + sortNo +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
}
