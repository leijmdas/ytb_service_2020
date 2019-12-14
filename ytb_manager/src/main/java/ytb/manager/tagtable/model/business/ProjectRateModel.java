package ytb.manager.tagtable.model.business;

import ytb.common.context.model.Ytb_Model;

//项目费率
public class ProjectRateModel extends Ytb_Model {

    protected int projectId;

    //服务费率
    protected float feeRate;
    //普税
    protected float taxRate;
    //专税
    protected  float sTaxRate;
    //个人用户毛利率
    protected float cRate;
    //公司用户毛利率
    protected float pRate;


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public float getFeeRate() {
        return feeRate ;
    }

    public void setFeeRate(float feeRate) {
        this.feeRate = feeRate;
    }

    public float getTaxRate() {
        return taxRate ;
    }

    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }

    public float getsTaxRate() {
        return sTaxRate;
    }

    public void setsTaxRate(float sTaxRate) {
        this.sTaxRate = sTaxRate;
    }

    public float getcRate() {
        return cRate;
    }

    public void setcRate(float cRate) {
        this.cRate = cRate;
    }

    public float getpRate() {
        return pRate;
    }

    public void setpRate(float pRate) {
        this.pRate = pRate;
    }



}
