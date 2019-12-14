package ytb.manager.projectStat.model;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

public class ProjectStatUserArea {
    int areaId;
    String name;
    Float totalMoney1;
    Float totalMoney2;
    transient Short fromTo;

    public Float getTotalMoney1() {
        return totalMoney1;
    }

    public void setTotalMoney1(Float totalMoney1) {
        this.totalMoney1 = totalMoney1;
    }

    public Float getTotalMoney2() {
        return totalMoney2;
    }

    public void setTotalMoney2(Float totalMoney2) {
        this.totalMoney2 = totalMoney2;
    }


    public Short getFromTo() {
        return fromTo;
    }

    public void setFromTo(Short fromTo) {
        this.fromTo = fromTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }



    public String toString() {
        return YtbUtils.toJSONStringPretty(this);
    }
}
