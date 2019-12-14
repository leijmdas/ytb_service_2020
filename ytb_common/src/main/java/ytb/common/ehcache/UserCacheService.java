package ytb.common.ehcache;

import org.springframework.cache.Cache;
import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.model.YtbError;

import java.util.List;
import java.util.Map;

public class UserCacheService {
    static int MAX_CACHE_NUMBER = 10001;
    public static Cache getCache() {
        return EhcacheContext.getEhcacheContext().getCacheUser();
    }

    public static void evict(Object o) {
        getCache().evict(o);
    }
    public static void refresh() {
        getCache().clear();
    }

    public static void checkCached(String tableName) {

        StringBuilder sql=new StringBuilder();
        sql.append("select metadata_cached from ytb_manager.metadata_dict where metadata_name=#{tableName} ");
        Boolean b = YtbSql.selectOne(sql, tableName, Boolean.class);
        if (!b) {
            throw new YtbError(YtbError.CODE_CACHE_NOT_ALLOWED, tableName);
        }
    }

    public static List<Map<String, Object>> select2Cache(String cacheKey, StringBuilder sql) {
        return select2Cache(cacheKey, sql, false);
    }

    public static <T> List<T> select2Cache(String cacheKey, StringBuilder sql, Class<T> cls) {
        return select2Cache(cacheKey, sql, cls, false);
    }

    static List<Map<String, Object>> select2Cache(String cacheKey, StringBuilder sql, boolean check) {

        List<Map<String, Object>> list = getCache().get(cacheKey, List.class);
        if (list == null) {
            if(check){
                checkCached(cacheKey);
            }
            YtbLog.logDebug(sql);
            if (sql.toString().indexOf("limit ") < 0) {
                sql.append(" limit ").append(MAX_CACHE_NUMBER);
            }
            list = YtbSql.selectList(sql);
            getCache().put(cacheKey, list);
            if (list.size() > MAX_CACHE_NUMBER - 1) {
                throw new YtbError(YtbError.CODE_CACHE_TOO_MANNY);
            }
        }
        return list;
    }

    static <T> List<T> select2Cache(String cacheKey, StringBuilder sql, Class<T> cls,boolean check) {
        List<T> lst = getCache().get(cacheKey, List.class);
        if (lst == null) {
            if(check){
                checkCached(cacheKey);
            }
            YtbLog.logDebug("select2Cache",sql);
            if (sql.toString().indexOf("limit ") < 0) {
                sql.append(" limit ").append(MAX_CACHE_NUMBER);
            }
            lst = YtbSql.selectList(sql, cls);
            getCache().put(cacheKey, lst);
            if (lst.size() > MAX_CACHE_NUMBER - 1) {
                throw new YtbError(YtbError.CODE_CACHE_TOO_MANNY);
            }
        }
        return lst;
    }



}
