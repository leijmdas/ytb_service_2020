package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

/**
 * @Author hj
 * @Description //供应商商品
 * @Date 2018/11/16
 *
 **/
public class SupplierCommodityModel extends Ytb_ModelSkipNull {

    //供应商商品Id
    private String sellId;
    //公司Id
    private String companyId;
    //用户Id
    private String userId;
    //商品名称
    private String productName;
    //型号
    private String productRule;
    //价格
    private String productPrice;
    //厂家
    private String productFactory;
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


    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductRule() {
        return productRule;
    }

    public void setProductRule(String productRule) {
        this.productRule = productRule;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductFactory() {
        return productFactory;
    }

    public void setProductFactory(String productFactory) {
        this.productFactory = productFactory;
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

    public String getCollectionNumber() { return collectionNumber; }

    public void setCollectionNumber(String collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    @Override
    public String toString() {
        return "SupplierCommodityModel{" +
                "sellId='" + sellId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", userId='" + userId + '\'' +
                ", productName='" + productName + '\'' +
                ", productRule='" + productRule + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productFactory='" + productFactory + '\'' +
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
