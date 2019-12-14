package ytb.manager.sysuser.model;

import java.util.Date;

/**
 * Package: ytb.manager.sysuser.model
 * Author: ZCS
 * Date: Created in 2018/8/22 12:09
 */
public class Sys_Role_RightModel {
    private  int rightId = 0;
    private int roleId = 0;
    private int objType = 0;
    private int objId = 0;
    private int createBy = 1;
    private Date createTime = new Date();


    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getObjType() {
        return objType;
    }

    public void setObjType(int objType) {
        this.objType = objType;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
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
}
