package ytb.common.testcase.cache;

import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.basic.safecontext.service.impl.RestRightCacheService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JTestClass.author("leijm")
public class TestEhCacheTestService extends ITestImpl {


    public  void suiteSetUp() {
    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {
    }

    @Override
    public void tearDown() {
    }


    @JTest
    @JTestClass.title("test0002_stream")
    @JTestClass.pre("")
    @JTestClass.step(" ")
    @JTestClass.exp("ok")
    public void test0001_stream() throws InterruptedException {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        l.stream().forEach(s ->
                System.out.println(s)
        );

    }


    @JTest
    @JTestClass.title("test0002_cache")
    @JTestClass.pre("")
    @JTestClass.step(" ")
    @JTestClass.exp("ok")
    public void test0002_cache() throws InterruptedException {

        //YtbContext.getYtb_context().getEhcache_context().getCache().put("object", new A());
//        IEhCacheDemo cm1 = EhcacheContext.getEhcacheContext().getAppContext().getBean("ehCacheTestService", IEhCacheDemo.class);
//        System.out.println("第一次调用：" + cm1.getTimestamp("param"));
//        Thread.sleep(2000);
//        System.out.println("2秒之后调用：" + cm1.getTimestamp("param"));
//        Thread.sleep(1100);
//        System.out.println("再过11秒之后调用：" + cm1.getTimestamp("param"));
//        net.sf.ehcache.Cache c = (net.sf.ehcache.Cache) EhcacheContext.getEhcacheContext().getCache().getNativeCache();
//        String ret = EhcacheContext.getEhcacheContext().getCache().get("object").get().toString();
//        System.out.println(ret);
//        JSONObject json = JSONObject.parseObject(ret);
//        ret = JSONObject.toJSONString(json, SerializerFeature.PrettyFormat);
//        System.out.println(ret);
//        System.out.println(new Gson().fromJson(ret, Map.class));
//
//        System.out.println(EhcacheContext.getEhcacheContext().getCache().get("344"));
    }


    @JTest
    @JTestClass.title("test0003_CheckRight")
    @JTestClass.pre("")
    @JTestClass.step(" ")
    @JTestClass.exp("ok")
    public void test0003_CheckRight() throws InterruptedException {
        checkEQ(RestRightCacheService.getCheckRestRight().findCheckRight("user","login"),false);
        checkEQ(RestRightCacheService.getCheckRestRight().findCheckRight("user","login"),false);
    }
    //checkValid_Rest
    @JTest
    @JTestClass.title("v")
    @JTestClass.pre("")
    @JTestClass.step(" ")
    @JTestClass.exp("ok")
    public void test0004_checkValid_Rest() throws InterruptedException {
        checkEQ(RestRightCacheService.getCheckRestRight().checkValid_Rest(17l,
                "workflow", "flow"));
        checkEQ(RestRightCacheService.getCheckRestRight().checkValid_Rest(17l,
                "workflow", "flow"));

    }

    public static void main(String[] args) throws InterruptedException {
        run(TestEhCacheTestService.class, 4);
    }


}
