package ytb.manager.projectStat.service.impl;

import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.manager.projectStat.model.ProjectStatProject.StatProjectType;
import ytb.manager.projectStat.model.ProjectStatus;
import ytb.manager.projectStat.model.User.User_StatModel;

import java.util.List;

public class ProjectStatProject {


    public static void  deleteUserProjectType(int statType) {
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from ytb_manager.user_stat_model ");
        sql.append(" where stat_type=#{statType} ");
        YtbSql.delete(sql,statType);

        StringBuilder sql1 = new StringBuilder();
        sql1.append(" delete from ytb_manager.project_stat_model ");
        sql1.append(" where stat_type=#{statType} ");
        YtbSql.delete(sql1,statType);

    }

    public static List<User_StatModel> statProjectArea() {
        deleteUserProjectType(32);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ytb_manager.project_stat_model(id,name,number1,number2,stat_type) ");
        sql.append(" select area_id as id, '' as name,count(1) as number1,0 as number2, 32 as statType ");
        sql.append(" from ytb_project.vwProject_talk a, ytb_user.user_info b ");
        sql.append(" where a.user_id1=b.user_id ");
        sql.append(" group by area_id ");
        sql.append(" union ");
        sql.append(" select area_id as id, '' as name,0 as number1, count(1) as number2 , 32 as statType ");
        sql.append(" from ytb_project.vwProject_talk a, ytb_user.user_info b ");
        sql.append(" where a.user_id2=b.user_id ");
        sql.append(" group by area_id ");

        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        sql = new StringBuilder();
        sql.append(" update  ytb_manager.project_stat_model a");
        sql.append(" inner  join dict_area b ");
        sql.append(" on a.id = b.area_id ");
        sql.append(" set a.name=b.name ");
        sql.append( " where a.stat_type=32");
        YtbSql.update(sql);
        return selectProjectArea();
    }

    public static List<User_StatModel> selectProjectArea() {

        StringBuilder sql = new StringBuilder();
//        sql.append("create  view if not exists  vw_project_stat_model_view32 as ( select a.id,a.name,a.number1,b.number2 ");
//        sql.append(" from vw_project_stat_model_32 a inner join  vw_project_stat_model_32 b ");
//        sql.append(" on a.id=b.id where a.number1>0 and b.number2>0 ");
//        sql.append(" union ");
//        sql.append(" select a.id,a.name,a.number1,0 as number2 ");
//        sql.append(" from vw_project_stat_model_32 a   ");
//        sql.append(" where a.number1>0   ");
//        sql.append(" and a.id not in (select b.id from vw_project_stat_model_32 b where b.number2>0) ");
//        sql.append(" union ");
//        sql.append(" select a.id,a.name,0 as number1, a.number2 ");
//        sql.append(" from vw_project_stat_model_32 a   ");
//        sql.append(" where a.number2>0   ");
//        sql.append(" and a.id not in (select b.id from vw_project_stat_model_32 b where b.number1>0) ) ");
        sql.append("select * from  vw_project_stat_model_view32");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, User_StatModel.class);
    }

    public static List<User_StatModel> statCompanyType() {
        deleteUserProjectType(33);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ytb_manager.project_stat_model(id,name,number1,number2,stat_type) ");
        sql.append(" select company_type as id, '' as name,count(1) as number1,0 as number2, 33 as statType ");
        sql.append(" from ytb_project.vwProject_talk a, ytb_user.company_info b ");
        sql.append(" where a.company_id1=b.company_id  and  a.company_id1>0");
        sql.append(" group by company_type ");
        sql.append(" union ");
        sql.append(" select company_type as id, '' as name,0 as number1, count(1) as number2 , 33 as statType ");
        sql.append(" from ytb_project.vwProject_talk a, ytb_user.company_info b ");
        sql.append(" where a.company_id2=b.company_id and  a.company_id2>0");
        sql.append(" group by company_type ");

        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        sql = new StringBuilder();
        sql.append(" update  ytb_manager.project_stat_model a");
        sql.append(" inner  join dict_company_type b ");
        sql.append(" on a.id = b.company_type ");
        sql.append(" set a.name=b.remark ");
        sql.append( " where a.stat_type=33");
        YtbSql.update(sql);
        return selectCompanyType();
    }



    public static List<User_StatModel> selectCompanyType() {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from vw_project_stat_model_view33");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, User_StatModel.class);
    }


    public static void  deleteProjectArea2Area(int statType) {
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from ytb_manager.project_stat_model_line ");
        sql.append(" where stat_type=#{statType} ");
        YtbSql.delete(sql,statType);

    }
    public static List<User_StatModel> statProjectArea2Area() {
        deleteProjectArea2Area(34);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into project_stat_model_line ");
        sql.append("( id1,name1,id2,name2,number,stat_type) ");
        sql.append(" select b.area_id as id1, '' as name1,c.area_id as id2, '' as name2,count(1) as number, 34 as statType ");
        sql.append(" from ytb_project.vwProject_talk a, ytb_user.user_info b,  ytb_user.user_info c ");
        sql.append(" where a.user_id1=b.user_id ");
        sql.append(" and  a.user_id2=c.user_id ");
        sql.append(" group by b.area_id ,c.area_id");

        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        sql = new StringBuilder();
        sql.append(" update  ytb_manager.project_stat_model_line a");
        sql.append(" inner  join dict_area b ");
        sql.append(" on a.id1 = b.area_id ");
        sql.append(" set a.name1=b.name ");
        sql.append( " where a.stat_type=34");
        YtbSql.update(sql);
        sql = new StringBuilder();
        sql.append(" update  ytb_manager.project_stat_model_line a");
        sql.append(" inner  join dict_area b ");
        sql.append(" on a.id2 = b.area_id ");
        sql.append(" set a.name2=b.name ");
        sql.append( " where a.stat_type=34");
        YtbSql.update(sql);
        return selectProjectArea2Area();
    }

    public static List<User_StatModel> selectProjectArea2Area() {

        StringBuilder sql = new StringBuilder();

        sql.append("select * from  project_stat_model_line where stat_type=34");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, User_StatModel.class);
    }

    public static List<User_StatModel> statTotal() {
        int type=35;
        deleteUserProjectType(type);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ytb_manager.user_stat_model(id,name,number1,stat_type) ");
        sql.append(" select 1 as id, '发布中总数' as name ,  count(1) as number1 ,35 as statType ");
        sql.append(" from ytb_project.project a ");
        sql.append(" where a.status >= 4");

        sql.append(" union ");
        sql.append(" select 2 as id, '洽谈中总数' as name ,  count(distinct a.project_id) as number1 , 35 as statType ");
        sql.append(" from ytb_project.project_invite a ");
        sql.append(" where  a.phase between ");
        sql.append(ProjectStatus.PRJ_TALK).append(" and ").append(ProjectStatus.PRJ_TALK_STOP);

        sql.append(" union ");
        sql.append(" select 3 as id, '进行中总数' as name ,  count(1) as number1 , 35 as statType ");
        sql.append(" from ytb_project.vwproject_talk a  ");
        sql.append(" where a.phase between ");
        sql.append(ProjectStatus.PRJ_PROCESS_START).append(" and ").append(ProjectStatus.PRJ_PROCESS_END );

        sql.append(" union ");
        sql.append(" select 4 as id, '已完成总数' as name ,  count(1) as number1 , 35 as statType ");
        sql.append(" from ytb_project.vwproject_talk a ");
        sql.append(" where a.phase = ").append(ProjectStatus.PRJ_FINISH);

        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        return selectTotal();
    }

    public static List<User_StatModel> selectTotal() {

        StringBuilder sql = new StringBuilder();
        sql.append(" select  id,  name ,  number1 , stat_type ");
        sql.append(" from user_stat_model a");
        sql.append(" where a.stat_type = 35 ");
        YtbLog.logDebug(sql);

        return YtbSql.selectList(sql, User_StatModel.class);
    }

    public static List<User_StatModel> statAvg() {
        int type=36;
        deleteUserProjectType(type);
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ytb_manager.user_stat_model(id,name,number1,number2,stat_type) ");
        sql.append(" select 1 as id, '平台审核项目平均时间(天)' as name ,  0 as number1 ,1 as number2 ,36 as statType ");
        sql.append(" from ytb_project.project a ");
        sql.append(" where  a.status >= 4");

        sql.append(" union ");
        sql.append(" select 2 as id, '发布进入到洽谈的平均时间(天)' as name , - sum(datediff(a.enter_time,b.enter_time)) as " +
                "number1 ,count(1) as number2, 36 as statType ");
        sql.append(" from ytb_project.project a,ytb_project.vw_project_phase_time b");
        sql.append(" where a.project_id=b.project_id and b.phase =").append(ProjectStatus.PRJ_TALK);
        sql.append(" union ");

        sql.append(" select 3 as id, '洽谈进入到签署完合同的平均时间(天)' as name ,  -sum(datediff(a.enter_time,b.enter_time)) as number1 ,count(a.project_id) as number2,  36 as statType ");
        sql.append(" from ytb_project.project a,ytb_project.vw_project_phase_time b ");
        sql.append(" where  a.project_id=b.project_id and  b.phase >= ").append(ProjectStatus.PRJ_PROCESS_START);
        sql.append(" and b.project_id_ok=b.project_id ");
        sql.append(" union ");

        sql.append(" select 4 as id, '项目进行阶段的平均时间(天)' as name ,   datediff( a.finish_time,a.enter_time ) as number1 ,count(distinct a.project_id) as number2 , 36 as statType ");
        sql.append(" from  ( select  project_id,min(enter_time) as enter_time,max( ifnull(finish_time,now())) as finish_time ");
        sql.append( " from ytb_project.vw_project_phase_time where phase between 600 and 699 ");
        sql.append(" group by project_id ) a ");
        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        sql = new StringBuilder();
        sql.append(" update  ytb_manager.user_stat_model a");
        sql.append(" set a.number1=a.number1/a.number2 ");
        sql.append(" where stat_type=36");
        YtbSql.update(sql);

        return selectAvg();
    }

    public static List<User_StatModel> selectAvg() {

        StringBuilder sql = new StringBuilder();        //sql.append(" select * from vwUser_stat_model1");
        sql.append(" select  *  from user_stat_model a ");
        sql.append(" where a.stat_type = 36 ");
        YtbLog.logDebug(sql);

        return YtbSql.selectList(sql, User_StatModel.class);
    }

    //按照项目分类统计项目总数（甲方、乙方）
    public static List<StatProjectType> statProjectType() {
        deleteUserProjectType(37);
        deleteUserProjectType(38);
        deleteUserProjectType(39);
        StringBuilder sql = new StringBuilder();
        //发布
        sql.append("insert into ytb_manager.project_stat_model(id,name,number1,number2,stat_type) ");
        sql.append(" select project_type_id as id, '' as name ,  count(1) as number1 ,0,37 as statType ");
        sql.append(" from ytb_project.project a ");
        sql.append(" where  a.status >= 4");
        sql.append(" group by project_type_id ");
        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        //申请、洽谈
        sql.delete(0,sql.length());
        sql.append("insert into ytb_manager.project_stat_model(id,name,number1,number2,stat_type) ");
        sql.append(" select project_type_id as id, '' as name, ");
        sql.append("  sum(case when phase=0 or phase=100   then 1 else 0 end) as number1 ,");
        sql.append("  sum(case when phase>=200 and phase<500 then 1 else 0 end) as number2, ");
        sql.append(" 38 as statType ");
        sql.append(" from ytb_project.project a,ytb_project.vw_talk_and_phase b");
        sql.append(" where a.project_id = b.project_id ");
        sql.append(" group by project_type_id ");
        YtbLog.logDebug(sql);
        YtbSql.update(sql);

        //进行、完成
        sql.delete(0,sql.length());
        sql.append("insert into ytb_manager.project_stat_model(id,name,number1,number2,stat_type) ");
        sql.append(" select project_type_id as id, '' as name");
        sql.append(" , sum(case when phase between 600 and 609 then 1 else 0 end) as number1");
        sql.append(" , sum(case when phase=999 then 1 else 0 end) as number2 ");
        sql.append(",39 as statType ");
        sql.append(" from ytb_project.project a,ytb_project.vw_talk_and_phase b");
        sql.append(" where a.project_id = b.project_id ");
        sql.append(" and b.project_id = b.project_id_ok ");
        sql.append(" group by project_type_id ");
        YtbSql.update(sql);

        sql.delete(0, sql.length());
        sql.append(" update  ytb_manager.project_stat_model a");
        sql.append(" inner  join dict_project_type b ");
        sql.append(" on a.id = b.project_type_id");
        sql.append(" set a.name=b.title ");
        sql.append(" where a.stat_type in (37,38,39)");
        YtbSql.update(sql);
        return selectProjectType();
    }

    public static List<StatProjectType> selectProjectType() {

        StringBuilder sql = new StringBuilder();
        sql.append(" drop view vw_StatProjectType0 ");
        YtbLog.logDebug(sql);
        YtbSql.update(sql);
        sql.delete(0,sql.length());
        sql.append(" drop view vw_StatProjectType  ");
        YtbLog.logDebug(sql);
        YtbSql.update(sql);

        sql.delete(0,sql.length());
        sql.append(" create view vw_StatProjectType0 as ");
        sql.append(" select a.id,a.name,  ifnull(b.number1,0) as publish ");
        sql.append(", ifnull(c.number1 ,0) as req , ifnull(c.number2 ,0) as talk  ");
        sql.append(", ifnull(d.number1 ,0) as process , ifnull(d.number2 ,0) as finish  ");
        sql.append(" from ( select  distinct id   ,name from project_stat_model where  stat_type in (37,38,39) ) a");
        sql.append(" left join  project_stat_model as b on b.stat_type=37 and a.id=b.id  ");
        sql.append(" left join  project_stat_model as c on c.stat_type=38 and a.id=c.id  ");
        sql.append(" left join  project_stat_model as d on d.stat_type=39 and a.id=d.id  ");

        YtbSql.update(sql);
        sql.delete(0,sql.length());
        sql.append(" create view vw_StatProjectType  as ");
        sql.append(" select *,publish+req+talk+process+finish as total from vw_StatProjectType0 ");
        YtbSql.update(sql);


        sql.delete(0,sql.length());
        sql.append(" select * from vw_StatProjectType");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, StatProjectType.class);
    }

}
