package ytb.user.model;

import java.util.Date;

/**
 * 用户基本信息表
 * Package: model
 * Author: ZCS
 * Date: Created in 2019年2月14日
 */
public class UserInfoModel {

    //头像
    private String userHead = "";

    //昵称
    private String nickName = "";


    //用户ID，表主键
    private Integer userId = 0;

    //公司用户ID
    private int companyUserId = 0;

    //性别 1:男 2:女
    private int sex = 0;

    //出生年月
    private Date birthday = new Date();

    //信用等级
    private String creditGrade = "B";

    //真实姓名
    private String realName = "";

    //邮箱
    private String email = "";

    //用户标识 1:游客 2:非游客
    private int userFlag = 0;

    //状态 0:未认证 1:审核中 2:审核失败 3:审核通过
    private int status = 0;

    //身份证号码
    private String idcard = "";

    //身份证正面
    private String idcardPhoto1 = "";

    //身份证反面
    private String idcardPhoto2 = "";

    //用户地址
    private String userAddress = "";

    //自我评价
    private String selfEval = "";

    private Integer areaId;

    private Integer provinceId;


    private Integer cityId;
    //User_Login表中的字段
    /**
     * 用户签名
     */
    private String userSign;
    /**
     * 是否在线
     */
    private boolean isOnline;

    private Byte testFlag;

    //详细地址
    private String detailedAddress= "";

    //学历
    private int schoolId = 0;
    private Date updateTime;
    private Date createTime;
    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean online) {
        isOnline = online;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }


    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Byte getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Byte testFlag) {
        this.testFlag = testFlag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(int companyUserId) {
        this.companyUserId = companyUserId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(String creditGrade) {
        this.creditGrade = creditGrade;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(int userFlag) {
        this.userFlag = userFlag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardPhoto1() {
        return idcardPhoto1;
    }

    public void setIdcardPhoto1(String idcardPhoto1) {
        this.idcardPhoto1 = idcardPhoto1;
    }

    public String getIdcardPhoto2() {
        return idcardPhoto2;
    }

    public void setIdcardPhoto2(String idcardPhoto2) {
        this.idcardPhoto2 = idcardPhoto2;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getSelfEval() {
        return selfEval;
    }

    public void setSelfEval(String selfEval) {
        this.selfEval = selfEval;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
