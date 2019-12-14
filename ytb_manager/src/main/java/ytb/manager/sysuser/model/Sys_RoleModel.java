package ytb.manager.sysuser.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Package: ytb.manager.sysuser.model
 * Author: ZCS
 * Date: Created in 2018/8/22 17:25
 */
public class Sys_RoleModel implements  Comparable<Sys_RoleModel>{
    private int roleId = 0;
    private String roleName = "";
    private String desp = "";

    private int createBy = 1;
    private Date createTime = new Date();
    private String createStrTime ="";

    private int userType = 1;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }


    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getCreateStrTime() {
        return createStrTime;
    }

    public void setCreateStrTime(String createStrTime) {
        this.createStrTime = createStrTime;
    }

    private List<Long> roleRightIdList;
    private boolean isSelect = false;//1 未选上  0：选上

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean select) {
        isSelect = select;
    }

    private String roleCode ="";
    public boolean equals(Object arg0){
        if(!(arg0 instanceof Sys_RoleModel)){
            return false;
        }
        Sys_RoleModel sys_RoleModel = (Sys_RoleModel)arg0;

         return sys_RoleModel.roleId==this.roleId;

    }

    //覆盖Object里的hashCode方法
    public int hashCode() {
        return roleId;//返回名字的哈希码。
    }



    public List<Long> getRoleRightIdList() {
        return roleRightIdList;
    }

    public void setRoleRightIdList(List<Long> roleRightIdList) {
        this.roleRightIdList = roleRightIdList;
    }
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
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
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createStrTime = sdf.format( createTime);
    }

    @Override
    public int compareTo(Sys_RoleModel o) {

        return this.roleId-o.roleId;
    }
}
