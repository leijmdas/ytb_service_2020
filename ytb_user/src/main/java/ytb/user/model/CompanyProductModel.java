package ytb.user.model;

import java.util.Date;

/**
 * 公司上市产品
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/6 20:05
 * Copyright: Copyright (c) 2018
 */
public class CompanyProductModel {
    private int productId = 0;
    private int companyId = 0;
    private  String productName ="";
    private String  productRule ="";
    private String productImg ="";
    private int productSellNumber = 0;
    private Date createTime =new Date();
    private Date updateTime =new Date();

    public String getProductName() {
        return productName;
    }

    public void setProductName(String projductName) {
        this.productName = projductName;
    }

    public String getProductRule() {
        return productRule;
    }

    public void setProductRule(String projductRule) {
        this.productRule = projductRule;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String projductImg) {
        this.productImg = projductImg;
    }

    public int getProductSellNumber() {
        return productSellNumber;
    }

    public void setProductSellNumber(int projductSellNumber) {
        this.productSellNumber = projductSellNumber;
    }

    public int getProjectId() {
        return productId;
    }

    public void setProjectId(int productId) {
        this.productId = productId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
