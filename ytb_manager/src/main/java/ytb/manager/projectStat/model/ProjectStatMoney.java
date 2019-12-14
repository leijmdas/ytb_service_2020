package ytb.manager.projectStat.model;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

public class ProjectStatMoney {

    public Integer getPsMoneyId() {
        return psMoneyId;
    }

    public void setPsMoneyId(Integer psMoneyId) {
        this.psMoneyId = psMoneyId;
    }

    //  1-3、7)销售总量、项目平均资金量（总量/总数）、项目已支付总量、项目监管总量
    //  4)按项目分类资金总量
    //  5)按甲方、乙方地域项目资金总量
    //  6)服务费总量
    Integer psMoneyId;
    Integer projectNum;
    Float totalMoney;
    Float serviceMoney;
    Float taxMoney;
    Float avgMoney;
    Float payMoney;
    Float agentMoney;
    transient Float agentTakoutMoney;

    public Float getAgentTakoutMoney() {
        return agentTakoutMoney;
    }

    public void setAgentTakoutMoney(Float agentTakoutMoney) {
        this.agentTakoutMoney = agentTakoutMoney;
    }


    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Float getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(Float serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public Float getTaxMoney() {
        return taxMoney;
    }

    public void setTaxMoney(Float taxMoney) {
        this.taxMoney = taxMoney;
    }

    public Float getAvgMoney() {
        return avgMoney;
    }

    public void setAvgMoney(Float avgMoney) {
        this.avgMoney = avgMoney;
    }

    public Float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Float payMoney) {
        this.payMoney = payMoney;
    }

    public Float getAgentMoney() {
        return agentMoney;
    }

    public void setAgentMoney(Float agentMoney) {
        this.agentMoney = agentMoney;
    }


    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }


    public String toString() {
        return YtbUtils.toJSONStringPretty(this);
    }
}

