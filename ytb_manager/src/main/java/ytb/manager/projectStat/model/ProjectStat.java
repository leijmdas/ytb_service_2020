package ytb.manager.projectStat.model;


import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

import java.sql.Date;
import java.util.List;

//        create table project_stat    (
//        stat_id    int auto_increment  comment '内部标识'      primary key,
//        stat_name  varchar(64) default ''             not null   comment '统计项名称',
//        p_id       int default '0'                    not null,
//        imp_type   int default '0'                    not null   comment '参数
//        0无
//        1sql
//        2sp
//        3JAVA class',
//        class_name varchar(64) default ''             not null     comment '实现类',
//        run_time   datetime default CURRENT_TIMESTAMP not null          comment '项目统计';
public class ProjectStat {
    int statId;
    String statName;
    int pId;
    int impType;

    String tableView;
    String metadataName;
    String script;
    String params;
    Date runTime;
    public String getTableView() {
        return tableView;
    }

    public void setTableView(String tableView) {
        this.tableView = tableView;
    }

    public String getMetadataName() {
        return metadataName;
    }

    public void setMetadataName(String metadataName) {
        this.metadataName = metadataName;
    }

    public int getStatId() {
        return statId;
    }

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getImpType() {
        return impType;
    }

    public void setImpType(int impType) {
        this.impType = impType;
    }



    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public List<ProjectStat> getChildren() {
        return children;
    }

    public void setChildren(List<ProjectStat> children) {
        this.children = children;
    }


    List< ProjectStat> children;


    public String toString(){
        return YtbUtils.toJSONStringPretty(this);
    }
}
