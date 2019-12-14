package ytb.common.utils;

import org.apache.ibatis.session.SqlSession;
import org.springframework.util.StringUtils;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.service.impl.YtbContext;

import java.util.List;
import java.util.Map;

public final class YtbSql {

    public static List<Map<String, Object>> selectList(StringBuilder sql) {
        return YtbContext.getSqlSessionBuilder().selectTable(sql);
    }

    public static <T> List<T> selectList(SqlSession session, StringBuilder sql, Class<T> cls) {
        return YtbContext.getSqlSessionBuilder().selectTable(session,sql, cls);
    }

    public static <T> List<T> selectList(StringBuilder sql, Class<T> cls) {
        return YtbContext.getSqlSessionBuilder().selectTable(sql, cls);
    }

    public static <T> List<T> selectList(StringBuilder sql, Object value, Class<T> cls) {
        return YtbContext.getSqlSessionBuilder().selectTable(sql, value, cls);
    }

    public static <T> T selectOne(StringBuilder sql, Object value, Class<T> cls) {
        return YtbContext.getSqlSessionBuilder().selectOne(sql, value, cls);
    }

    public static <T> T selectOne(StringBuilder sql, Class<T> cls) {
        return YtbContext.getSqlSessionBuilder().selectOne(sql, cls);
    }

    public static int delete(StringBuilder sql) {
        return YtbContext.getSqlSessionBuilder().delete(sql);

    }

    public static int delete(StringBuilder sql, Object value) {
        return YtbContext.getSqlSessionBuilder().delete(sql, value);

    }

    public static int insert(SqlSession session,StringBuilder sql, Object value) {
        return YtbContext.getSqlSessionBuilder().insert(session,sql, value);

    }
    public static int insert(StringBuilder sql, Object value) {
        return YtbContext.getSqlSessionBuilder().insert(sql, value);

    }

    public static int update(StringBuilder sql, Object value) {
        return YtbContext.getSqlSessionBuilder().update(sql, value);

    }

    public static int update(StringBuilder sql) {
        YtbLog.logDebug(sql);
        return YtbContext.getSqlSessionBuilder().update(sql);

    }

    public static int selectAutoID(SqlSession session) {
        return YtbContext.getSqlSessionBuilder().selectAutoID(session);
    }
    //YtbSql 提供了两个函数：分别是调存贮，函数返回
    //fnDb   spDb

    public static String fnDb(String fnName, Object... p) {

        return YtbContext.getSqlSessionBuilder().fnDb(fnName, p);

    }

    public static List<Map<String, Object>> spDb(String fnName, Object... p) {
        int j = 0;
        for (Object i : p) {

            if (i instanceof String &&!p[j].toString().contains("'")) {
                p[j] = StringUtils.quote(p[j].toString());
            }
            j++;
        }
        return YtbContext.getSqlSessionBuilder().spDb(null, fnName, p);
    }

    public static <T> List<T> spDb (Class<T> resultType,String fnName,  Object... p) {
        int j = 0;
        for (Object i : p) {

            if (i instanceof String) {
                p[j] = StringUtils.quote(p[j].toString());
            }
            j++;
        }
        return ( List<T> )YtbContext.getSqlSessionBuilder().spDb(resultType, fnName,  p);

    }



}
