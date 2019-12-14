package ytb.user.model;

import java.util.Date;

/**
 * 用户意向采购表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/11/6 19:13
 */
public class UserPurchaseModel {

    //表主键
    private Integer purchaseId = 0;

    //用户ID
    private  Integer userId = 0;

    //公司ID
    private  Integer companyId = 0;

    //类别ID
    private Integer typeId =0;

    //商品名称
    private String purchaseName = "";

    //商品规格型号
    private String purchaseRule = "";

    //商品厂商
    private String purchaseFactory = "";

    //是否公开
    private boolean isPrivate = true;

    //创建时间
    private Date createTime = new Date();


    private Integer viewNumber = 0;

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public String getPurchaseRule() {
        return purchaseRule;
    }

    public void setPurchaseRule(String purchaseRule) {
        this.purchaseRule = purchaseRule;
    }

    public String getPurchaseFactory() {
        return purchaseFactory;
    }

    public void setPurchaseFactory(String purchaseFactory) {
        this.purchaseFactory = purchaseFactory;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
