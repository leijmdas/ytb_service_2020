package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

/**
 * @Author hj
 * @Description //搜索购买商商品参数
 * @Date 2018/11/20
 **/
public class PurchaseGoodsParam  extends Ytb_ModelSkipNull {
    //产品名称
    private String PurchaseName;
    //厂家
    private String PurchaseFactory;
    //商品类别
    private String projectTypeId;
    //信用等级
    private String creditGrade;
    //发布时间
    private String createTime;

    public String getPurchaseName() {
        return PurchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        PurchaseName = purchaseName;
    }

    public String getPurchaseFactory() {
        return PurchaseFactory;
    }

    public void setPurchaseFactory(String purchaseFactory) {
        PurchaseFactory = purchaseFactory;
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
        return "PurchaseGoodsParam{" +
                "PurchaseName='" + PurchaseName + '\'' +
                ", PurchaseFactory='" + PurchaseFactory + '\'' +
                ", projectTypeId='" + projectTypeId + '\'' +
                ", creditGrade='" + creditGrade + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
