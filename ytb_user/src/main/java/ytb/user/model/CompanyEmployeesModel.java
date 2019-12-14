package ytb.user.model;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * 公司员工
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/7 17:54
 */
public class CompanyEmployeesModel {
    //表主键
    private int employeeId = 0;

    //公司ID
    private int companyId = 0;

    //公司用户ID
    private int companyUserId = 0;

    //邀请时间
    private Date inviteTime = new Date();

    //0在职 1离职
    private int isEnable = 0;

    //是否是负责人
    private boolean isAdmin = true;

    //生成时间
    private String nickName = "";
    private Integer userId = 0;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private  String remark = "";




    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Date createTime = new Date();


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(int companyUserId) {
        this.companyUserId = companyUserId;
    }

    public Date getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(Date inviteTime) {
        this.inviteTime = inviteTime;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
