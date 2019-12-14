package ytb.user.model;

import java.util.Date;


public class UserLoginModel {
    /**
     * 表主键
     */
    private Integer userId;

    /**
     * 公司用户ID
     */
    private Integer companyUserId;

    /**
     * 1:个人用户  2:公司用户
     */
    private Integer userType;

    /**
     * 0可用 1禁用
     */
    private Integer userStatus;

    /**
     * 登录账号
     */
    private String loginMobile;

    /**
     * 用户昵称
     */


    /**
     * 密码
     */
    private String password;

    /**
     * QQ号(第三方登录用)
     */
    private String qqNumber;

    /**
     * 微信（第三方登录用）
     */
    private Integer wxNumber;

    /**
     * 登录次数
     */
    private Integer loginNumber;

    /**
     * 浏览次数
     */
    private Integer viewNumber;

    /**
     * 注册时间
     */
    private Date registeredTime;

    /**
     * 注册的IP
     */
    private String registeredIp;

    /**
     * 用户头像
     */
    private String userHead;

    /**
     * 用户签名
     */
    private String userSign;

    /**
     * 最近登录时间
     */
    private Date loginTime;

    /**
     * 最近登录IP
     */
    private String loginIp;

    /**
     * 是否在线
     */
    private boolean isOnline;

    /**
     * 用户登录后的授权码
     */
    private String loginToken;

    /**
     * 邦邦号
     */
    private String bangBangNo;

    /**
     * 登录失败次数
     */
    private int loginErrorTimes;

    /**
     * 锁定时间
     */
    private Date lockTime;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 是否锁定
     */

    private boolean isLock = false;


    private String nickName = "";


    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(Integer companyUserId) {
        this.companyUserId = companyUserId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getLoginMobile() {
        return loginMobile;
    }

    public void setLoginMobile(String loginMobile) {
        this.loginMobile = loginMobile;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public Integer getWxNumber() {
        return wxNumber;
    }

    public void setWxNumber(Integer wxNumber) {
        this.wxNumber = wxNumber;
    }

    public Integer getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(Integer loginNumber) {
        this.loginNumber = loginNumber;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Date getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Date registeredTime) {
        this.registeredTime = registeredTime;
    }

    public String getRegisteredIp() {
        return registeredIp;
    }

    public void setRegisteredIp(String registeredIp) {
        this.registeredIp = registeredIp;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getBangBangNo() {
        return bangBangNo;
    }

    public void setBangBangNo(String bangBangNo) {
        this.bangBangNo = bangBangNo;
    }

    public int getLoginErrorTimes() {
        return loginErrorTimes;
    }

    public void setLoginErrorTimes(int loginErrorTimes) {
        this.loginErrorTimes = loginErrorTimes;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(boolean lock) {
        isLock = lock;
    }


}
