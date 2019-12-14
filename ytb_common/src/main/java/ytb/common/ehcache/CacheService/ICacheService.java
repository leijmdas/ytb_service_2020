package ytb.common.ehcache.CacheService;

import org.springframework.cache.Cache;

public interface ICacheService {
    Cache getCache();
    void refresh();
}
