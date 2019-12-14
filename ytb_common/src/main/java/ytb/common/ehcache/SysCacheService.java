package ytb.common.ehcache;

import org.springframework.cache.Cache;
import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.model.YtbError;
import java.util.List;
import java.util.Map;

public final class SysCacheService extends UserCacheService {
    public static Cache getCache() {
        return EhcacheContext.getEhcacheContext().getCache();
    }

    public static void refreshTable(String tableName) {
        YtbLog.logDebug("refreshTable tableName",tableName);
        getCache().evict(tableName);
       }

    public static void refreshTables() {
        YtbLog.logDebug("refreshTables");
        getCache().clear();
    }

    public static List<Map<String, Object>> table2Cache(String tableName) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(tableName);
        return select2Cache(tableName, sql,true);
    }

    public static <T> List<T> table2Cache(String tableName, Class<T> cls) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(tableName);
        return select2Cache(tableName, sql, cls,true);
    }

    public static <T> List<T> table2Cache(String sql, String tableName, Class<T> cls) {
        return select2Cache(tableName, new StringBuilder(sql), cls,true);
    }

    public static void checkCached(String tableName) {

        StringBuilder sql=new StringBuilder();
        sql.append("select metadata_cached from ytb_manager.metadata_dict where metadata_name=#{tableName} ");
        Boolean b = YtbSql.selectOne(sql, tableName, Boolean.class);
        if (!b) {
            throw new YtbError(YtbError.CODE_CACHE_NOT_ALLOWED, tableName);
        }
    }
}