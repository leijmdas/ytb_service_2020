package ytb.common.context.service.impl;

import org.springframework.context.ApplicationContext;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.MyBatis.SqlSessionBuilder;
import ytb.common.basic.config.service.ConfigCacheService;
import ytb.common.ehcache.CacheService.YtbCacheService;
import ytb.common.basic.config.model.Dict_ErrorCode;
import ytb.common.context.service.IYtbContext;

public final class YtbContext implements IYtbContext {
    static YtbContext inst = new YtbContext();

    public static YtbContext getYtb_context() {
        return inst;
    }

    private YtbContext() {
    }

    public static SqlSessionBuilder sqlSessionBuilder = new SqlSessionBuilder();

    static YtbCacheService ytbCacheManager = new YtbCacheService();

    static SafeContext safeContext = new SafeContext();
    public static SafeContext getSafeContext(){
        return safeContext;
    }
    //static ApplicationContext commonContext = new ClassPathXmlApplicationContext("classpath:commonContext.xml");
    public static ApplicationContext getAppContext() {
        return AppCtxtUtil.getApplicationContext();//commonContext;
    }

    public static SqlSessionBuilder getSqlSessionBuilder() {

        return sqlSessionBuilder;
    }

    public static YtbCacheService getYtbCacheManager() {
        return ytbCacheManager;
    }


    public Dict_ErrorCode getError_msg(String error_code) {
        return ConfigCacheService.getConfigService().getError_msg(error_code);
    }


    public String getConfig_value(String config_item) {
        return ConfigCacheService.getConfigService().getConfig_value(config_item);
    }



}
