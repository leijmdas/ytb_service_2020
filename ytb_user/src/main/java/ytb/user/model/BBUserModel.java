package ytb.user.model;

import java.util.Date;

/**
 *
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/10/8 17:13
 */
public class BBUserModel {

    private int userId;

    public String getCreditGread() {
        return creditGread;
    }

    public void setCreditGread(String creditGread) {
        this.creditGread = creditGread;
    }

    private int companyUserId;
    private int userType;
    private int userStatus;
    private String loginMobile;
    private String nickName;
    private String userHead;
    private String userSigin;
    private String sex;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String creditGread;
    private String companyName;
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    private String schoolId;

    public String[] getTagNameArr() {
        return tagNameArr;
    }

    public void setTagNameArr(String[] tagNameArr) {
        this.tagNameArr = tagNameArr;
    }

    private String userAddress;
    private  int userAge;
    //private String tagName;
    private Date registeredTime;

    private String  tagName ;

    private String tagNameArr [];



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(int companyUserId) {
        this.companyUserId = companyUserId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getLoginMobile() {
        return loginMobile;
    }

    public void setLoginMobile(String loginMobile) {
        this.loginMobile = loginMobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserSigin() {
        return userSigin;
    }

    public void setUserSigin(String userSigin) {
        this.userSigin = userSigin;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }


    /*public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }*/

    public Date getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Date registeredTime) {
        this.registeredTime = registeredTime;
    }
}
