package ytb.manager.sysuser.model;

import java.util.Date;

/**
 * Package: ytb.manager.sysuser.model
 * Author: ZCS
 * Date: Created in 2018/8/22 12:03
 */
public class Sys_User_RoleModel {
    private int userRoleId = 0;
    private int UserId = 0;
    private int roleId = 0;
    private int createBy = 1;
    private Date createTime = new Date();


    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
