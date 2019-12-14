package ytb.common.ehcache;
/*
leijm
20180901
* */


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import ytb.common.context.service.impl.AppCtxtUtil;

public final class EhcacheContext {
    //static final String AppContext_cache_file = "classpath:ehcache/app_ehcache.xml";
    static final String AppContext_cache_file = "classpath:AppContextCommon.xml";
    static EhcacheContext ehcache_context = new EhcacheContext();

    public static EhcacheContext getEhcacheContext() {
        return ehcache_context;
    }


    static ApplicationContext appContextCache;

    public static ApplicationContext getAppContext() {
        if (appContextCache == null) {
            appContextCache = AppCtxtUtil.getApplicationContext();
            if (appContextCache == null) {
                //appContextCache = new ClassPathXmlApplicationContext(AppContext_cache_file);
            }
        }
        return appContextCache;
    }

    static final String CACHE_NAME_GLOBAL = "sysCache";
    static final String CACHE_NAME_USER = "userCache";
    static final String CACHE_NAME_PICCODE = "picCodeCache";

    public EhcacheContext(){}

    public  CacheManager getCacheManager() {
       System.out.print(getAppContext());
       return getAppContext().getBean("ehCacheManager", CacheManager.class);
    }

    public  Cache getCache() {
        return getCacheManager().getCache(CACHE_NAME_GLOBAL);
    }

    public  Cache getCacheUser() {
        return getCacheManager().getCache(CACHE_NAME_USER);
    }

    public  Cache getCachePicCode() {
        return getCacheManager().getCache(CACHE_NAME_PICCODE);
    }

}
