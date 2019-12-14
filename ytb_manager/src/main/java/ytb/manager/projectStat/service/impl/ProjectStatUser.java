package ytb.manager.projectStat.service.impl;

import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.manager.projectStat.model.User.User_DayActiveModel;
import ytb.manager.projectStat.model.User.User_StatModel;

import java.util.List;

public class ProjectStatUser {
    public static void deleteUserDayActive() {
        StringBuilder sql = new StringBuilder();
        sql.append("delete  from   ytb_manager.user_day_active ");
        sql.append(" where oprt_date between DATE_ADD(now(), interval - 30 day) and now()");
        YtbLog.logDebug(sql);
        YtbSql.delete(sql);
    }
    //selectUserDayActive
    public static List<User_DayActiveModel> selectUserDayActive() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from   ytb_manager.user_day_active " );
        sql.append(" order by oprt_date desc limit 30 ");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, User_DayActiveModel.class);
    }

    public static List<User_DayActiveModel> statUserDayActive() {
        deleteUserDayActive();
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into ytb_manager.user_day_active (oprt_date,active_number) ");
        sql.append(" select oprt_date, count(distinct user_id) as  active_number ");
        sql.append(" from ytb_tasklog.tasklog_user   ");
        sql.append(" where oprt_date between DATE_ADD(now(), interval - 30 day) and now()");
        sql.append(" and user_id>0 ");
        sql.append(" group by oprt_date  ");

        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        return selectUserDayActive();
    }


    public static void  deleteUserProjectType(int statType) {
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from ytb_manager.user_stat_model ");
        sql.append(" where stat_type=#{statType} ");
        YtbSql.delete(sql,statType);

    }
    //create view vwProject_talk as
    //select a.project_id,a.project_type_id,a.user_id1,a.company_id1,b.user_id2,b.company_id2,b.group_id from ytb_project.project a,ytb_project.project_talk b where a.talk_id=b.talk_id
    //按照项目分类统计人总数（甲方、乙方）
    public static List<User_StatModel> statUserProjectType() {
        deleteUserProjectType(1);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ytb_manager.user_stat_model(id,name,number1,stat_type,number2) ");
        sql.append(" select project_type_id as id, '' as name ,  count(1) as number1 ,1 as statType ");
        sql.append(" , (select count(1) from ytb_project.work_group_member b where b.group_id=a.group_id and b.user_id!=a.user_id1)   ");
        sql.append(" as number2 from ytb_project.vwProject_talk a");
        sql.append(" group by project_type_id ");
        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        sql = new StringBuilder();
        sql.append(" update  ytb_manager.user_stat_model a");
        sql.append(" inner  join dict_projecttype b ");
        sql.append(" on a.id = b.ProjectTypeId");
        sql.append(" set a.name=b.title ");
        sql.append( " where a.stat_type=1");
        YtbSql.update(sql);
        return selectUserProjectType();
    }

    public static List<User_StatModel> selectUserProjectType() {

        StringBuilder sql = new StringBuilder();
        sql.append(" select * from vwUser_stat_model1");
        YtbLog.logDebug(sql);

        return YtbSql.selectList(sql, User_StatModel.class);
    }

    public static List<User_StatModel> statUserArea() {
        deleteUserProjectType(2);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ytb_manager.user_stat_model(id,name,number1,number2,stat_type) ");
        sql.append(" select area_id as id, '' as name,count(1) as number1,0 as number2, 2 as statType ");
        sql.append(" from ytb_project.vwProject_talk a, ytb_user.user_info b ");
        sql.append(" where a.user_id1=b.user_id ");
        sql.append(" group by area_id ");
        sql.append(" union ");
        sql.append(" select area_id as id, '' as name,0 as number1, count(1) as number2 , 2 as statType ");
        sql.append(" from ytb_project.vwProject_talk a, ytb_user.user_info b ");
        sql.append(" where a.user_id2=b.user_id ");
        sql.append(" group by area_id ");

        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        sql = new StringBuilder();
        sql.append(" update  ytb_manager.user_stat_model a");
        sql.append(" inner  join dict_area b ");
        sql.append(" on a.id = b.area_id ");
        sql.append(" set a.name=b.name ");
        sql.append( " where a.stat_type=2");
        YtbSql.update(sql);
        return selectUserArea();
    }

    public static List<User_StatModel> selectUserArea() {

        StringBuilder sql = new StringBuilder();
//        sql.append("create  view if not exists  vwUser_stat_model as ( select a.id,a.name,a.number1,b.number2 ");
//        sql.append(" from vwUser_stat_model2 a inner join  vwUser_stat_model2 b ");
//        sql.append(" on a.id=b.id where a.number1>0 and b.number2>0 ");
//        sql.append(" union ");
//        sql.append(" select a.id,a.name,a.number1,0 as number2 ");
//        sql.append(" from vwUser_stat_model2 a   ");
//        sql.append(" where a.number1>0   ");
//        sql.append(" and a.id not in (select b.id from vwUser_stat_model2 b where b.number2>0) ");
//        sql.append(" union ");
//        sql.append(" select a.id,a.name,0 as number1, a.number2 ");
//        sql.append(" from vwUser_stat_model2 a   ");
//        sql.append(" where a.number2>0   ");
//        sql.append(" and a.id not in (select b.id from vwUser_stat_model2 b where b.number1>0) ) ");
        sql.append("select * from vwUser_stat_model");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, User_StatModel.class);
    }

    public static List<User_StatModel> statUserCompanyType() {
        deleteUserProjectType(3);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ytb_manager.user_stat_model(id,name,number1,number2,stat_type) ");
        sql.append(" select company_type as id, '' as name,count(1) as number1,0 as number2, 3 as statType ");
        sql.append(" from ytb_project.vwProject_talk a, ytb_user.company_info b ");
        sql.append(" where a.company_id1=b.company_id  and  a.company_id1>0");
        sql.append(" group by company_type ");
        sql.append(" union ");
        sql.append(" select company_type as id, '' as name,0 as number1, count(1) as number2 , 3 as statType ");
        sql.append(" from ytb_project.vwProject_talk a, ytb_user.company_info b ");
        sql.append(" where a.company_id2=b.company_id and  a.company_id2>0");
        sql.append(" group by company_type ");

        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        sql = new StringBuilder();
        sql.append(" update  ytb_manager.user_stat_model a");
        sql.append(" inner  join dict_company_type b ");
        sql.append(" on a.id = b.company_type ");
        sql.append(" set a.name=b.remark ");
        sql.append( " where a.stat_type=3");
        YtbSql.update(sql);
        return selectUserCompanyType();
    }



    public static List<User_StatModel> selectUserCompanyType() {

        StringBuilder sql = new StringBuilder();
//        sql.append("create  view if not exists  vwUser_stat_modelView3 as ( select a.id,a.name,a.number1,b.number2 ");
//        sql.append(" from vwUser_stat_model3 a inner join  vwUser_stat_model3 b ");
//        sql.append(" on a.id=b.id where a.number1>0 and b.number2>0 ");
//        sql.append(" union ");
//        sql.append(" select a.id,a.name,a.number1,0 as number2 ");
//        sql.append(" from vwUser_stat_model3 a   ");
//        sql.append(" where a.number1>0   ");
//        sql.append(" and a.id not in (select b.id from vwUser_stat_model3 b where b.number2>0) ");
//        sql.append(" union ");
//        sql.append(" select a.id,a.name,0 as number1, a.number2 ");
//        sql.append(" from vwUser_stat_model3 a   ");
//        sql.append(" where a.number2>0   ");
//        sql.append(" and a.id not in (select b.id from vwUser_stat_model3 b where b.number1>0) ) ");
        sql.append("select * from vwUser_stat_modelView3");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, User_StatModel.class);
    }


    public static List<User_StatModel> statUserTotal() {
        int type=10;
        deleteUserProjectType(type);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ytb_manager.user_stat_model(id,name,number1,stat_type) ");
        sql.append(" select 1 as id, '发布人总数' as name ,  count(1) as number1 ,10 as statType ");
        sql.append(" from ytb_project.project a ");
        sql.append(" where a.status >= 4");
        sql.append(" union ");

        sql.append(" select 2 as id, '申请人总数（准乙方）' as name ,  count(1) as number1 , 10 as statType ");
        sql.append(" from ytb_project.project_invite a ");
        sql.append(" where a.phase >=  0 and a.phase < 200");
        sql.append(" union ");

        sql.append(" select 3 as id, '洽谈人总数（准乙方）' as name ,  count(1) as number1 , 10 as statType ");
        sql.append(" from ytb_project.project_invite a ");
        sql.append(" where  a.phase between 200 and 400 ");
        sql.append(" union ");

        sql.append(" select 4 as id, '项目进行中总人数（甲方1个+乙方工作组人数）' as name ,  count(1) as number1 , 10 as statType ");
        sql.append(" from ytb_project.vwproject_talk a,ytb_project.work_group_member b ");
        sql.append(" where  a.group_id=b.group_id and a.phase between 600 and 609 ");

        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        return selectUserTotal();
    }

    public static List<User_StatModel> selectUserTotal() {

        StringBuilder sql = new StringBuilder();
        //sql.append(" select * from vwUser_stat_model1");
        sql.append(" select  id,  name ,   number1 , stat_type ");
        sql.append(" from user_stat_model a");
        sql.append(" where a.stat_type = 10 ");
        YtbLog.logDebug(sql);

        return YtbSql.selectList(sql, User_StatModel.class);
    }

    public static List<User_StatModel> statUserAvg() {
        int type=11;
        deleteUserProjectType(type);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ytb_manager.user_stat_model(id,name,number1,number2,stat_type) ");
        sql.append(" select 1 as id, '项目发布平均人数' as name ,  count(1) as number1 ,1 as number2 ,11 as statType ");
        sql.append(" from ytb_project.project  a");
        sql.append(" where a.status >= 4");
        sql.append(" union ");

        sql.append(" select 2 as id, '项目申请平均人数（准乙方）' as name ,  count(1) as number1 ,count(distinct a.project_id) as number2, 11 as statType ");
        sql.append(" from ytb_project.project_invite a");
        sql.append(" where a.phase >=  0 and a.phase < 200");
        sql.append(" union ");

        sql.append(" select 3 as id, '项目洽谈平均人数（准乙方）' as name ,  count(1) as number1 ,count(distinct a.project_id) as number2,  11 as statType ");
        sql.append(" from ytb_project.project_invite a ");
        sql.append(" where  a.phase between 200 and 400 ");
        sql.append(" union ");

        sql.append(" select 4 as id, '项目进行平均人数（甲方1个+乙方工作组人数）' as name ,  count(1) as number1 ,count(distinct a.project_id) as number2 , 11 as statType ");
        sql.append(" from ytb_project.vwproject_talk a,ytb_project.work_group_member b ");
        sql.append(" where  a.group_id=b.group_id and a.phase between 600 and 609 ");

        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        sql = new StringBuilder();
        sql.append(" update  ytb_manager.user_stat_model a");
        sql.append(" set a.number1=a.number1/a.number2 ");
        sql.append(" where stat_type=11");
        YtbSql.update(sql);

        return selectUserAvg();
    }

    public static List<User_StatModel> selectUserAvg() {

        StringBuilder sql = new StringBuilder();
        //sql.append(" select * from vwUser_stat_model1");
        sql.append(" select  *  from user_stat_model a");
        sql.append(" where a.stat_type = 11 ");
        YtbLog.logDebug(sql);

        return YtbSql.selectList(sql, User_StatModel.class);
    }
}
