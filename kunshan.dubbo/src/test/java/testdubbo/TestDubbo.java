package testdubbo;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.annotation.JTestInject;
import com.jtest.testframe.ITestImpl;
import kunshan.iface.IDubboService;
import kunshan.service.DemoService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.ytblog.YtbLog;

public class TestDubbo extends ITestImpl {

    @JTestInject(filename = "node.xml", value = "httpclient")
    protected HttpClientNode httpclient;

    static String url="http://localhost:8081/common/kunshan";

    @JTest
    @JTestClass.title("test0001_dubbo")
    @JTestClass.pre("qosserver https://blog.csdn" +
            ".net/u013202238/article/details/81432784")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0001_dubbo() {
        //测试常规服务
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("dubbo-client.xml");
        context.start();
        System.out.println("consumer start");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("consumer");
        System.out.println(demoService.getPermissions(1L));

        JSONObject req = new JSONObject();
        req.fluentPut("id", 111).fluentPut("name", "dubbo agent");
        System.out.println(demoService.runAgent(req));
        IDubboService dubboService = context.getBean(IDubboService.class);
        req.fluentPut("dubboService","IDubboService");
        System.out.println(dubboService.runAgent(req));
        context.stop();
    }

    @JTest
    @JTestClass.title("test0002_mvc")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0002_mvc() {
        System.out.println(httpclient);
        JSONObject req = new JSONObject();
        req.fluentPut("id", 1).fluentPut("name", "ljm");
        String ret = httpclient.post(url, req  .toJSONString(),  "application/json");
        YtbLog.logJtest("ret: ", ret);
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        YtbLog.logJtest("resp: ", resp.toJSONString());
    }


    public static void main(String[] args) {
        run(TestDubbo.class, 1);
        //YtbLog.logJtest(new Student());

    }

}