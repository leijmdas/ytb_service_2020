package ytb.user.model;

import java.util.Date;

/**
 * 用户推广销售清单
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/11/6 19:20
 */
public class UserSellModel {

    //表主键
    private Integer sellId =0;

    //用户ID
    private Integer userId = 0;

    //公司ID
    private Integer companyId = 0;

    //类别
    private Integer typeId = 0;

    //商品名称
    private String productName = "";

    //规格型号
    private String productRule = "";

    //商品厂家
    private String productFactory ="";

    //单价
    private Double productPrice = 0.0;

    //图片
    private String productPicUrl = "";


    //文档路径
    private String docUrl = "";

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

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
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

    public String getProductFactory() {
        return productFactory;
    }

    public void setProductFactory(String productFactory) {
        this.productFactory = productFactory;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPicUrl() {
        return productPicUrl;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl;
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

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }
}
