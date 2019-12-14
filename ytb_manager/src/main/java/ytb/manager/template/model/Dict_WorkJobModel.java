
package ytb.manager.template.model;

import ytb.common.context.model.Ytb_Model;

import java.util.Date;

public class Dict_WorkJobModel extends Ytb_Model {

    private int workJobId;
    private int workJobTypeId;
    private String title;
    private String titleAlias;
    private int createBy;
    private Date createTime;
    private int orderNo;
    private int isDefault;
    private int workJobNumber;

    private String workJobTypeName;
    private String userName;

    public int getWorkJobId() {
        return workJobId;
    }

    public void setWorkJobId(int workJobId) {
        this.workJobId = workJobId;
    }

    public int getWorkJobTypeId() {
        return workJobTypeId;
    }

    public void setWorkJobTypeId(int workJobTypeId) {
        this.workJobTypeId = workJobTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleAlias() {
        return titleAlias;
    }

    public void setTitleAlias(String titleAlias) {
        this.titleAlias = titleAlias;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getWorkJobTypeName() {
        return workJobTypeName;
    }

    public void setWorkJobTypeName(String workJobTypeName) {
        this.workJobTypeName = workJobTypeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWorkJobNumber() {
        return workJobNumber;
    }

    public void setWorkJobNumber(int workJobNumber) {
        this.workJobNumber = workJobNumber;
    }
}
