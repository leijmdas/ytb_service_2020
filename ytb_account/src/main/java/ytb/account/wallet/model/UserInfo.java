package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class UserInfo implements Serializable {
    /**
     * 表主键
     */
    private Integer userId;

    /**
     * 公司用户ID
     */
    private Integer companyUserId;

    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户性别 1:男 2:女
     */
    private Byte sex;

    /**
     * 出生年月
     */
    private Date birthday;

    /**
     * 信用等级
     */
    private String creditGrade;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户标识 1:游客 2:非游客
     */
    private Byte userFlag;

    /**
     * 0:未认证 1:审核中 2:审核失败 3:审核通过
     */
    private Byte status;

    /**
     * 身份证号码
     */
    private String idcard;

    /**
     * 身份证正面
     */
    private String idcardPhoto1;

    /**
     * 身份证反面
     */
    private String idcardPhoto2;

    /**
     * 自我评价
     */
    private String selfEval;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 学历
     */
    private Short schoolId;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 地区ID
     */
    private Integer areaId;

    /**
     * 省份ID
     */
    private Integer provinceId;

    /**
     * 市ID
     */
    private Integer cityId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 详细地址
     */
    private String detailedAddress;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(Byte userFlag) {
        this.userFlag = userFlag;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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

    public String getSelfEval() {
        return selfEval;
    }

    public void setSelfEval(String selfEval) {
        this.selfEval = selfEval;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Short schoolId) {
        this.schoolId = schoolId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", companyUserId=").append(companyUserId);
        sb.append(", nickName=").append(nickName);
        sb.append(", realName=").append(realName);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", creditGrade=").append(creditGrade);
        sb.append(", email=").append(email);
        sb.append(", userFlag=").append(userFlag);
        sb.append(", status=").append(status);
        sb.append(", idcard=").append(idcard);
        sb.append(", idcardPhoto1=").append(idcardPhoto1);
        sb.append(", idcardPhoto2=").append(idcardPhoto2);
        sb.append(", selfEval=").append(selfEval);
        sb.append(", createTime=").append(createTime);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", userAddress=").append(userAddress);
        sb.append(", areaId=").append(areaId);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", cityId=").append(cityId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", detailedAddress=").append(detailedAddress);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}