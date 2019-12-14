
package ytb.manager.template.model;

import ytb.common.context.model.Ytb_Model;

import java.util.Date;


/**
 * LXZ
 */
public class Dict_WorkJobTypeModel extends Ytb_Model {

    private int workJobTypeId;
    private String workJobTypeName;
    private int state;
    private int orderNo;
    private int createBy;
    private Date createTime;
    private int workGroupNum;

    public Dict_WorkJobTypeModel() {

    }

    public int getWorkJobTypeId() {
        return workJobTypeId;
    }

    public void setWorkJobTypeId(int workJobTypeId) {
        this.workJobTypeId = workJobTypeId;
    }

    public String getWorkJobTypeName() {
        return workJobTypeName;
    }

    public void setWorkJobTypeName(String workJobTypeName) {
        this.workJobTypeName = workJobTypeName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
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

    public int getWorkGroupNum() {
        return workGroupNum;
    }

    public void setWorkGroupNum(int workGroupNum) {
        this.workGroupNum = workGroupNum;
    }
}
