package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

/**
 * @Author hj
 * @Description //搜索供应商商品参数
 * @Date 2018/11/20
 **/
public class SupplierParam  extends Ytb_ModelSkipNull {
    //产品名称
    private String productName;
    //厂家
    private String productFactory;
    //商品类别
    private String projectTypeId;
    //信用等级
    private String creditGrade;
    //发布时间
    private String createTime;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductFactory() {
        return productFactory;
    }

    public void setProductFactory(String productFactory) {
        this.productFactory = productFactory;
    }

    public String getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(String projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(String creditGrade) {
        this.creditGrade = creditGrade;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SearchParamModel{" +
                "productName='" + productName + '\'' +
                ", productFactory='" + productFactory + '\'' +
                ", projectTypeId='" + projectTypeId + '\'' +
                ", creditGrade='" + creditGrade + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
