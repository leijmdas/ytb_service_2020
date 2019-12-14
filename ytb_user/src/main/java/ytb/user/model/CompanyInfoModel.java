package ytb.user.model;


import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class CompanyInfoModel implements Serializable {
    /**
     * 主键ID,同User_login申请人的CompanyUserID
     */
    private Integer companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 申请人CompanyUserID
     */
    private Integer companyUserId;

    /**
     * 0:未认证；1:审核中；2;审核失败；3:审核通过
     */
    private Integer status;

    /**
     * 1:游客 2:非游客
     */
    private Integer companyFlag;

    /**
     * 公司联系人
     */
    private String contacts;

    /**
     * 公司联系电话
     */
    private String phoneNumber;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 公司营业执照的工商注册码
     */
    private String licenseCode;

    /**
     * 存储营业执照照片地址
     */
    private String licenseUrl;

    /**
     * 公司理念/公司描述
     */
    private String idea;

    /**
     * 公司邮箱
     */
    private String email;

    /**
     * 大学生人数
     */
    private Integer collegeNumber;

    /**
     * 研究生人数
     */
    private Integer graduateNumber;

    /**
     * 其他人数
     */
    private Integer otherNumber;

    /**
     * 公司信息添加时间
     */
    private Date createTime;

    /**
     * 公司信息修改时间
     */
    private Date upadateTime;

    private static final long serialVersionUID = 1L;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(Integer companyUserId) {
        this.companyUserId = companyUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyFlag() {
        return companyFlag;
    }

    public void setCompanyFlag(Integer companyFlag) {
        this.companyFlag = companyFlag;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCollegeNumber() {
        return collegeNumber;
    }

    public void setCollegeNumber(Integer collegeNumber) {
        this.collegeNumber = collegeNumber;
    }

    public Integer getGraduateNumber() {
        return graduateNumber;
    }

    public void setGraduateNumber(Integer graduateNumber) {
        this.graduateNumber = graduateNumber;
    }

    public Integer getOtherNumber() {
        return otherNumber;
    }

    public void setOtherNumber(Integer otherNumber) {
        this.otherNumber = otherNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpadateTime() {
        return upadateTime;
    }

    public void setUpadateTime(Date upadateTime) {
        this.upadateTime = upadateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyUserId=").append(companyUserId);
        sb.append(", status=").append(status);
        sb.append(", companyFlag=").append(companyFlag);
        sb.append(", contacts=").append(contacts);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", address=").append(address);
        sb.append(", licenseCode=").append(licenseCode);
        sb.append(", licenseUrl=").append(licenseUrl);
        sb.append(", idea=").append(idea);
        sb.append(", email=").append(email);
        sb.append(", collegeNumber=").append(collegeNumber);
        sb.append(", graduateNumber=").append(graduateNumber);
        sb.append(", otherNumber=").append(otherNumber);
        sb.append(", createTime=").append(createTime);
        sb.append(", upadateTime=").append(upadateTime);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}