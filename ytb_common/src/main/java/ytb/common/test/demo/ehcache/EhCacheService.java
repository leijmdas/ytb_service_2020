package ytb.common.test.demo.ehcache;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ytb.common.basic.safecontext.model.Api_KeyModel;
import ytb.common.ytblog.YtbLog;


/*leijm
* */
@Service
public class EhCacheService implements IEhCacheDemo {

    @Cacheable(value = "sysCache", key = "#param")
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }


    public long getTimestampNocache(String param) {
        return System.currentTimeMillis();
    }


    public static void demo() throws InterruptedException {

//        // YtbContext.getYtb_context().getEhcache_context().getCache().put("object", new A());
//        IEhCacheDemo cm1 =  EhcacheContext.getEhcache_context().getAppContext().getBean("ehCacheTestService", IEhCacheDemo.class);
//        System.out.println("第一次调用：" + cm1.getTimestamp("param"));
//        Thread.sleep(1000);
//        System.out.println("2秒之后调用：" + cm1.getTimestamp("param"));
//        Thread.sleep(1100);
//        System.out.println("再过1.1秒之后调用：" + cm1.getTimestamp("param"));
//        net.sf.ytb.common.ehcache.Cache c = (net.sf.ytb.common.ehcache.Cache) EhcacheContext.getEhcache_context().getCache().getNativeCache();
//        for (Object it : c.getKeys()) {
//            System.out.println(it);
//        }
//        String ret = EhcacheContext .getEhcache_context().getCache().get("object").get().toString();
//        System.out.println(ret);
//        JSONObject json = JSONObject.parseObject(ret);
//        ret = JSONObject.toJSONString(json, SerializerFeature.PrettyFormat);
//        System.out.println(ret);
//        System.out.println(
//        new Gson().fromJson(ret,Map.class).getClass().getName());

    }

    public static void main(String[] args) throws InterruptedException {

        YtbLog.logError( new Api_KeyModel() );
    }
}
