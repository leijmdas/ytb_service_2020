package ytb.manager.projectStat.model;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

public class ProjectStatProjectType {
    int ProjectTypeId;
    String title;

    Float totalMoney;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProjectTypeId() {
        return ProjectTypeId;
    }

    public void setProjectTypeId(int projectTypeId) {
        ProjectTypeId = projectTypeId;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }


    public String toString() {
        return YtbUtils.toJSONStringPretty(this);
    }

}
