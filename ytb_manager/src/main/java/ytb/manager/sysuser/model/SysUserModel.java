package ytb.manager.sysuser.model;


import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户实体类
 * Package: ytb.manager.sysuser.model
 * Author: ZCS
 * Date: Created in 2018/8/21 9:44
 */
public class SysUserModel implements Serializable{

    private long userId = 0;

    private Byte testFlag = 0;
    private String userName = "";
    private String password = "";
    private String mobile = "";
    private boolean status = true;
    private String userEmail;
    private String bangbangNo="";

    private boolean isAdmin = false;
    private Date startTime = new Date();
    private String startStrTime ="";
    private long createBy = 1;
    private Date createTime = new Date();
    private String createStrTime = "";
    private Date endTime = new Date();
    private String endStrTime = "";
    private String createName = "";

    public Byte getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Byte testFlag) {
        this.testFlag = testFlag;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBangbangNo() {
        return bangbangNo;
    }

    public void setBangbangNo(String bangbangNo) {
        this.bangbangNo = bangbangNo;
    }
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getStartStrTime() {
        return startStrTime;
    }

    public void setStartStrTime(String startStrTime) {
        this.startStrTime = startStrTime;
    }

    public String getEndStrTime() {
        return endStrTime;
    }

    public void setEndStrTime(String endStrTime) {
        this.endStrTime = endStrTime;
    }

    public String getCreateStrTime() {
        return createStrTime;
    }

    public void setCreateStrTime(String createStrTime) {
        this.createStrTime = createStrTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
