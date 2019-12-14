package ytb.manager.testcase;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;
import java.util.UUID;


@JTestClass.author("leijm")
public class TestTemplateGenManager extends ITestImpl {
    String url_base = "http://localhost/rest/template";

    String token = "d588ba62851b404284eeca7607201540";
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    MsgRequest req = new MsgRequest();
    String data;

    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {
        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectType";
        req.cmd = "getProjectTypeList";
        req.msgBody = JSONObject.parseObject("{}");
        System.out.println(req.toJSONString());
        System.out.println("通用类模板仓库");
    }

    @Override
    public void tearDown() {

    }


    @JTest
    @JTestClass.title("test0001_getDocTemplate_gen")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/template")
    @JTestClass.exp("ok")
    public void test0001_getDocTemplate_gen()     {
        String url="http://localhost/rest/template";
        req.token = token;
        req.cmdtype = "templateDocument";
        req.cmd = "getDocTemplate_gen";
        req.msgBody = JSONObject.parseObject("{ }");

        String ret = httpclient.post(url ,req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        System.out.println(resp.toJSONString());
        System.out.println(req.toJSONString());
        System.out.println(url);

    }



    public static void main(String[] args) {
        run(TestTemplateGenManager.class, 1);
    }
}


