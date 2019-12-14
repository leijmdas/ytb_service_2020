package ytb.common.ehcache.CacheService;

import org.springframework.cache.Cache;
import ytb.common.ehcache.SysCacheService;
import ytb.common.ehcache.UserCacheService;
import ytb.common.ehcache.EhcacheContext;
import ytb.common.basic.safecontext.service.impl.RestRightCacheService;
import ytb.common.basic.config.service.ConfigCacheService;

public final class YtbCacheService implements ICacheService,ICacheServiceUser {

    @Override
    public Cache getCache() {
        return EhcacheContext.getEhcacheContext().getCache();
    }
    @Override
    public void refresh() {
        ConfigCacheService.getConfigService().refresh();
        SysCacheService.refreshTables();
        System.out.println("YtbCacheService refresh finished!");
    }

    public void refreshAll() {
        refresh();
        refreshUser();
        System.out.println("YtbCacheService  refresh All cache finished!");
    }

    public void refreshErrorCode() {
        ConfigCacheService.getConfigService().refreshErrorCode();
        System.out.println("YtbCacheService refreshErrorCode finished!");
    }

    public void refreshConfig() {
        ConfigCacheService.getConfigService().refreshConfig();
        System.out.println("YtbCacheService refreshSystem Param finished!");
    }

    public void refreshUser() {
        UserCacheService.refresh();
        RestRightCacheService.getCheckRestRight().refresh();
        System.out.println("YtbCacheService refresh all user's right cache finished!");

    }

    @Override
    public void refresh(Long userId) {
        RestRightCacheService.getCheckRestRight().refresh(userId);
        System.out.println("YtbCacheService refresh one user right cache finished!");
    }

}
