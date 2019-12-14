package ytb.manager.projectStat.service;

import com.github.abel533.sql.SqlMapper;
import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.projectStat.model.ProjectStat;

import java.util.List;

public class StatManagerService {
    public  List<ProjectStat> pageGetStatList(int statType) {
        StringBuilder sql = new StringBuilder(256);
        sql.append("select * from project_stat where p_id>=0 ");
        sql.append( " and p_id=").append(statType);
        sql.append(" order by p_id,stat_id");
        return selectList(sql);
    }


    public List< ProjectStat> pageGetStatTrees(int statType) {
        return pageGetStatTree(0,statType);
    }

     List< ProjectStat> pageGetStatTree(int p_id,int statType) {
        List< ProjectStat> lst = selectTree(p_id,statType);
         for (ProjectStat ps : lst) {
             List<ProjectStat> children = pageGetStatTree(ps.getStatId(), statType);
             ps.setChildren(children);
         }
         return lst;
     }


    List<ProjectStat> selectTree(int p_id, int statType) {
        StringBuilder sql = new StringBuilder(256);
        sql.append(" select * from project_stat ");
        sql.append(" where p_id=").append(p_id);
        if (p_id == 0 && statType != 0) {
            sql.append(" and stat_id=").append(statType);
        }
        sql.append(" order by p_id,stat_id ");
        return selectList(sql);
    }

    public List<ProjectStat> selectList() {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from project_stat where p_id>0");
        return selectList(sql);
    }

    public List<ProjectStat> selectList(StringBuilder sql) {
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_manager(true);
        try {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql.toString(), ProjectStat.class);
        } finally {
            session.close();
        }
    }

    public void updateProjectStat(ProjectStat ps) {
        StringBuilder sql = new StringBuilder();
        sql.append("update project_stat set run_time=#{runTime} ");
        sql.append(" where stat_id=#{statId}");
        YtbSql.update(sql, ps);
    }


}
