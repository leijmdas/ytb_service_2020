package ytb.common.MyBatis;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface ISqlSessionBuilder {
    public static String DB_NAME_COMMON = "ytb_common";
    public static String DB_NAME_ACTIVITI = "ytb_activiti";
    public static String DB_NAME_TASKLOG = "ytb_tasklog";
    public static String DB_NAME_MANAGER = "ytb_manager";
    public static String DB_NAME_USER = "ytb_user";
    public static String DB_NAME_ACCOUNT = "ytb_account";
    public static String DB_NAME_BANGBANG = "ytb_bangbang";
    public static String DB_NAME_PROJECT = "ytb_project";

    //name->factroy
    final static  ConcurrentHashMap<String, SqlSessionFactory>
            map_factroy = new ConcurrentHashMap<>();

    SqlSession getSession_common(boolean isAutoCommit);

    SqlSession getSession_manager(boolean isAutoCommit);

    SqlSession getSession_user(boolean isAutoCommit);

    SqlSession getSession_project(boolean isAutoCommit);

    SqlSession getSession_bangbang(boolean isAutoCommit);

    SqlSession getSession_account(boolean isAutoCommit);

    SqlSession getSession_tasklog(boolean isAutoCommit);

    SqlSession getSession_activiti(boolean isAutoCommit);

    SqlSession getSession(String dbname, boolean isAutoCommit);

    int selectAutoID(SqlSession session);

    int updateSql(StringBuilder sql);

    List<Map<String, Object>> selectTable(StringBuilder sql);

}
