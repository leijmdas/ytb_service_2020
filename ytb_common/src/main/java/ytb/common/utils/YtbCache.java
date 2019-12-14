package ytb.common.utils;

import ytb.common.ehcache.EhcacheContext;

import java.util.List;

public final class YtbCache {
    public static  void putSysCache(Object key,Object value ){
        EhcacheContext.getEhcacheContext().getCache().put(key,value);
    }

    public static <T> T getSysCache(Object key, Class<T> cls) {
        return EhcacheContext.getEhcacheContext().getCache().get(key, cls);
    }

    public static void putUserCache(Object key, Object value) {
        EhcacheContext.getEhcacheContext().getCacheUser().put(key, value);
    }

    public static <T> T getUserCache(Object key, Class<T> cls) {
        return EhcacheContext.getEhcacheContext().getCacheUser().get(key, cls);
    }

    public static <T> T selectOneSysCache(String tableName,StringBuilder sql, Class<T> cls) {
        T t = getSysCache(sql.toString(), cls);
        if (t == null) {
            t = YtbSql.selectOne(sql, cls);
            putSysCache(sql.toString(), cls);
        }
        return t;
    }

    public static <T> List<T> selectListSysCache(String tableName, StringBuilder sql, Class<T> cls) {
        List<T> lst = getSysCache(sql.toString(), List.class);
        if (lst == null) {
            lst = YtbSql.selectList(sql, cls);
            putSysCache(sql.toString(), lst);
        }
        return lst;
    }

}
