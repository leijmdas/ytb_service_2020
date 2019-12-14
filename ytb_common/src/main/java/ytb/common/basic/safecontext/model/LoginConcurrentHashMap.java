package ytb.common.basic.safecontext.model;


import ytb.common.basic.safecontext.service.SafeContext;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Package: ytb.user.rest.impl
 * Author: leijm
 * Date: Created in 22018年9月12日
 */
public final class LoginConcurrentHashMap {
    static Map<String, LoginSso> map_logininfo = new ConcurrentHashMap<>();
    static Map<String, Long> map_timeout = new ConcurrentHashMap<>();

    /**
     * 预缓存信息
     */
    /* private static final Map<String, LoginSso> userLoginInfo = new ConcurrentHashMap<String, LoginSso>();*/
    /**
     * 每个缓存生效时间12小时
     */
    static final long CACHE_HOLD_TIME_1H = 1 * 60 * 60 * 1000L;
    static final long CACHE_HOLD_TIME_24H = 24 * 60 * 60 * 1000L;

    /**
     * 存放一个缓存对象，默认保存时间12小时	 * @param cacheName	 * @param obj
     */
    public static void put(String cacheName, LoginSso obj) {
        put(cacheName, obj, CACHE_HOLD_TIME_1H);
    }

    /**
     * 存放一个缓存对象，保存时间为holdTime	 * @param cacheName	 * @param obj	 * @param holdTime
     */
    public static void put(String cacheName, LoginSso obj, long holdTime) {
        map_logininfo.put(cacheName, obj);
        map_timeout.put(cacheName,System.currentTimeMillis()+holdTime);
    }

    /**
     * 取出一个缓存对象	 * @param cacheName	 * @return
     */
    public static LoginSso get(String cacheName) {
        if (cacheName != null && checkCacheName(cacheName)) {
            return map_logininfo.get(cacheName);
        }
        return null;
    }

    /**
     * 删除所有缓存
     */
    public static void removeAll() {
        map_logininfo.clear();
        map_timeout.clear();
    }

    /**
     * 删除某个缓存	 * @param cacheName
     */
    public static void remove(String cacheName) {
        map_logininfo.remove(cacheName);
        map_timeout.remove( cacheName  );
    }


    public static boolean checkCacheName(String cacheName) {
        Long cacheHoldTime = (Long) map_timeout.get(cacheName);
        if (cacheHoldTime == null || cacheHoldTime == 0L) {
            return false;
        }
        if (cacheHoldTime < System.currentTimeMillis()) {
            remove(cacheName);
            return false;
        }
        if(cacheHoldTime > System.currentTimeMillis()) {
            map_timeout.put(cacheName, System.currentTimeMillis() + CACHE_HOLD_TIME_1H);
        }
        return true;
    }

    public static void clear() {
        for (String key : map_logininfo.keySet()) {
            LoginSso ls = get(key);
            if (ls != null) {
                SafeContext.delete(ls);
            }
        }
    }


    public static void clearTimeout() {
        for (String key : map_timeout.keySet()) {
            long t = map_timeout.get(key);
            if (System.currentTimeMillis() - t > CACHE_HOLD_TIME_24H) {
                LoginSso ls = map_logininfo.get(key);
                SafeContext.delete(ls);
            }
        }
    }

    public static String uuid() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");


    }


}
