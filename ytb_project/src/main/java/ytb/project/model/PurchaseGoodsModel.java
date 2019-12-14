package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

/**
 * @Author hj
 * @Description //采购商商品
 * @Date 2018/11/16
 *
 **/
public class PurchaseGoodsModel  extends Ytb_ModelSkipNull {

    //供应商商品Id
    private String purchaseId;
    //公司Id
    private String companyId;
    //用户Id
    private String userId;
    //商品名称
    private String purchaseName;
    //型号
    private String purchaseRule;
    //厂家
    private String purchaseFactory;
    //头像
    private String userHead;
    //昵称
    private String nickName;
    //公司名称
    private String companyName;
    //信用
    private String creditGrade;
    //类别
    private String projectName;
    //发布时间
    private String createTime;
    //浏览数
    private String viewNumber;
    //收藏数
    private String collectionNumber;

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(String creditGrade) {
        this.creditGrade = creditGrade;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(String viewNumber) {
        this.viewNumber = viewNumber;
    }

    public String getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(String collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    @Override
    public String toString() {
        return "PurchaseGoodsModel{" +
                "purchaseId='" + purchaseId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", userId='" + userId + '\'' +
                ", purchaseName='" + purchaseName + '\'' +
                ", purchaseRule='" + purchaseRule + '\'' +
                ", purchaseFactory='" + purchaseFactory + '\'' +
                ", userHead='" + userHead + '\'' +
                ", nickName='" + nickName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", creditGrade='" + creditGrade + '\'' +
                ", projectName='" + projectName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", viewNumber='" + viewNumber + '\'' +
                ", collectionNumber='" + collectionNumber + '\'' +
                '}';
    }
}
