package ytb.user.model;

import java.util.Date;

/**
 * 用户收件地址
 * Package: model
 * Author: ZCS
 * Date: Created in 2018/9/3 14:33
 * Company: 公司
 */
public class UserAddressModel {

    //表主键
    private int addressId = 0;

    //用户ID >0关联User_Login表当CompanyID=0时
    private Integer userId = 0;

    //公司ID>0 关联公司信息表 当UserID=0时
    private Integer companyId = 0;

    //收件人姓名
    private String name = "";

    //手机号码
    private String phone = "";

    //地址详情
    private String area ="";

    //详细地址
    private String address ="";

    //邮编
    private String zipCode = "";

    //是否默认 0：默认 1：非默认
    private boolean isDefault =false;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    //生成时间
    private Date createTime = new Date();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}
