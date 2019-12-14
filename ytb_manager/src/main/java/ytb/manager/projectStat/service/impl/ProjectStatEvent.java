package ytb.manager.projectStat.service.impl;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.common.ytblog.YtbLog;
import ytb.manager.projectStat.model.ProjectStatMoney;
import ytb.manager.projectStat.model.ProjectStatProjectType;
import ytb.manager.projectStat.model.ProjectStatUserArea;
import ytb.manager.projectStat.model.ProjectStatus;

import java.util.List;

public class ProjectStatEvent {
    static String TableName_ProjectStatUserArea = "Project_Stat_User_Area";

    static ProjectStatMoney statTotal() {
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(cost_sum) as totalMoney,");
        sql.append("  avg(cost_sum) as avgMoney,");
        sql.append("  count(1) as projectNum,");
        sql.append("  sum(service_sum) as serviceMoney,");
        sql.append("  sum(tax_sum) as taxMoney");
        sql.append(" from ytb_project.vwproject_talk a,ytb_project.cost_sum b");
        sql.append(" where a.project_id=b.project_id and a.project_id_ok > 0 ");
        YtbLog.logDebug(sql);
        return YtbSql.selectOne(sql, ProjectStatMoney.class);

    }

    static ProjectStatMoney statAgent() {
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(balance_agent) as agentMoney");
        sql.append(",sum(takeout_money) as agentTakoutMoney");
        sql.append(" from ytb_account.account_user_inner a");
        YtbLog.logDebug(sql);
        return YtbSql.selectOne(sql, ProjectStatMoney.class);

    }
    static ProjectStatMoney statPay() {
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(cost_sum) as payMoney");
        sql.append(" from ytb_project.project a,ytb_project.cost_sum b,ytb_project.project_talk c");
        sql.append(" where a.talk_id>0 and a.project_id=b.project_id");
        sql.append(" and a.project_id=c.project_id and c.project_id=c.project_id_ok ");
        sql.append(" and c.status >=").append(ProjectStatus.PRJ_PROCESS_START);
        YtbLog.logDebug(sql);
        return YtbSql.selectOne(sql, ProjectStatMoney.class);

    }

    //统计项目资金总量
    public static ProjectStatMoney stat() {
        ProjectStatMoney sa = statAgent();
        ProjectStatMoney sp = statPay();
        ProjectStatMoney st = statTotal();
        if (st != null) {
            st.setAgentMoney(sa == null ? 0 : sa.getAgentMoney());
            st.setPayMoney(sp == null ? 0 : sp.getPayMoney());
            deleteProjectStatMoney(st);
            insertProjectStatMoney(st);
        }
        return st;
    }

    public static void deleteProjectStatMoney(ProjectStatMoney st) {
        StringBuilder sql=new StringBuilder();
        sql.append("delete from ytb_manager. project_stat_money");
        YtbSql.delete(sql,st);
    }

    public static void insertProjectStatMoney(ProjectStatMoney st) {
        StringBuilder sql=new StringBuilder();
        sql.append("insert into ytb_manager. project_stat_money");
        sql.append("(total_money,service_money,tax_money,pay_money,avg_money,project_num,agent_money)");
        sql.append("values(#{totalMoney},#{serviceMoney},#{taxMoney},#{payMoney},#{avgMoney},#{projectNum},#{agentMoney})");
        YtbSql.insert(sql, st);

    }


    public static List<ProjectStatMoney>  selectProjectStatMoney() {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from  ytb_manager. project_stat_money");
        return  YtbSql.selectList(sql,ProjectStatMoney.class);

    }

    //按项目分类统计资金
    public static List<ProjectStatProjectType>  statProjectType() {
        deleteStatProjectType();

        StringBuilder sql = new StringBuilder();
        sql.append(" insert into project_stat_project_type ");
        sql.append(" select b.ProjectTypeId,max(title) as title,sum(cost_sum) as totalMoney");
        sql.append(" from ytb_project.vwProjectsale a,ytb_manager.dict_project_type b");
        sql.append(" where a.project_type_id=b.project_type_id");
        sql.append(" group by b.project_type_id ");
        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        return selectStatProjectType();

    }

    //按项目分类统计资金
    //按项目分类统计资金
    public static void deleteStatProjectType() {

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from project_stat_project_type ");
        YtbLog.logDebug(sql);
        YtbSql.delete(sql);

    }

    public static List<ProjectStatProjectType> selectStatProjectType() {

        StringBuilder sql = new StringBuilder();
        sql.append(" select * from project_stat_project_type ");
        sql.append(" order by project_type_id");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, ProjectStatProjectType.class);

    }

    //按甲方地区统计项目资金
    static List<ProjectStatUserArea> statUser1Area() {
        StringBuilder sql = new StringBuilder();
        sql.append("select b.area_id,sum(cost_sum) as totalMoney,1 as fromTo");
        sql.append(" from ytb_project.vwProjectsale a,ytb_user.user_info b");
        sql.append(" where a.user_id1=b.user_id");
        sql.append(" group by b.area_id");
        YtbLog.logDebug("statUser1Area", sql);
        return YtbSql.selectList(sql, ProjectStatUserArea.class);

    }

    //按一方地区统计项目资金
    static List<ProjectStatUserArea> statUser2Area() {
        StringBuilder sql = new StringBuilder();
        sql.append("select b.area_id,sum(cost_sum) as totalMoney,2 as fromTo");
        sql.append(" from ytb_project.vwProjectsale a,ytb_user.user_info b");
        sql.append(" where a.user_id2=b.user_id");
        sql.append(" group by b.area_id");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, ProjectStatUserArea.class);
    }

    //按甲方地区统计项目资金
    static void statUser1AreaSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into Project_Stat_User_Area(area_id,total_money1,from_to) ");
        sql.append(" select b.area_id,sum(cost_sum) as totalMoney1,1 as from_to");
        sql.append(" from ytb_project.vwProjectsale a,ytb_user.user_info b");
        sql.append(" where a.user_id1=b.user_id");
        sql.append(" group by b.area_id");
        YtbLog.logDebug(sql);
        YtbSql.update(sql);

    }

    //按一方地区统计项目资金
    static void statUser2AreaSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into  Project_Stat_User_Area(area_id,total_money2,from_to)");
        sql.append(" select b.area_id,sum(cost_sum) as totalMoney2 ,2 as from_to");
        sql.append(" from ytb_project.vwProjectsale a,ytb_user.user_info b");
        sql.append(" where a.user_id2=b.user_id");
        sql.append(" group by b.area_id");
        YtbLog.logDebug(sql);
        YtbSql.update(sql);
    }
    //按一方地区统计项目资金
    static void truncateUserAreaSql() {
        StringBuilder sql = new StringBuilder();
        sql.append("truncate table  Project_Stat_User_Area");
        YtbSql.update(sql);
    }

    //按一方地区统计项目资金
    static void updateUserAreaName() {
        StringBuilder sql = new StringBuilder();
        sql.append("update Project_Stat_User_Area a, dict_area b ");
        sql.append(" set a.name = b.name ");
        sql.append(" where a.area_id = b.area_id");
        YtbSql.update(sql);
    }

    public static List<ProjectStatUserArea> selectUserAreaSql() {
        StringBuilder sql = new StringBuilder();

        sql.append("select c.*,(select name from dict_area d where d.area_id=c.area_id) as name from (");
        sql.append(" select a.area_id,a.total_money1, b.total_money2 from  ").append(TableName_ProjectStatUserArea).append(" a ");
        sql.append(", ").append(TableName_ProjectStatUserArea).append("  b ");
        sql.append(" where a.area_id=b.area_id and a.from_to=1 and b.from_to=2");
        sql.append(" union select a.area_id,a.total_money1, a.total_money2 from   Project_Stat_User_Area  a ");
        sql.append(" where a.from_to=1 and a.area_id not in ( select area_id from Project_Stat_User_Area where from_to=2 )");
        sql.append(" union select b.area_id,b.total_money1  ,b.total_money2 from   Project_Stat_User_Area  b ");
        sql.append(" where b.from_to=2 and b.area_id not in ( select area_id from Project_Stat_User_Area where from_to=1 )");
        sql.append(" ) c order by c.area_id ");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, ProjectStatUserArea.class);
    }

    public static List<ProjectStatUserArea> statUserArea() {
        truncateUserAreaSql();
        statUser1AreaSql();
        statUser2AreaSql();
        updateUserAreaName();
        return selectUserAreaSql();

    }



}
