package ytb.user.model;

import java.util.Date;

/**
 * 公司收入表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/10 10:34
 */
public class CompanyIncomeModel {
    //表主键
    private int incomeId = 0;

    //公司ID
    private int companyId = 0;

    //收入年份
    private int incomeYear = 0;

    //信息添加时间
    private Date createTime = new Date();

    //收入金额
    private float incomeMoney = 0f;


    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getIncomeYear() {
        return incomeYear;
    }

    public void setIncomeYear(int incomeYear) {
        this.incomeYear = incomeYear;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public float getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(float incomeMoney) {
        this.incomeMoney = incomeMoney;
    }



}
