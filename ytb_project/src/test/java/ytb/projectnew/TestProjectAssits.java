package ytb.projectnew;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;

/**
 * Package: ytb
 * Author: ZCS
 * Date: Created in 2019/3/1 18:00
 */
public class TestProjectAssits extends ITestImpl {

    String url_182 = "http://project.youtobon.com/rest/project/template";
    String url_base = "http://mysql.kunlong.com/rest/project/template";

    String url_image = "http://mysql.kunlong.com/rest/project/image";
    String url_upload = "http://mysql.kunlong.com/rest/project/upload";
    String url_projectCenter = "http://mysql.kunlong.com/rest/projectCenter";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    MsgRequest req = new MsgRequest();
    String token = "fbe501bc9c124b28a5658162d32f325e";
    String data;

    public void suiteSetUp() {

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
    @JTestClass.title("发起协助")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0001() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "addAssistDoc";

        req.msgBody = JSONObject.parseObject("{\"templateIdIni\":1,\"userId\":125}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("点击协助文档加载协助的文档列表")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0002() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getUserAssitsTemplateList";

        req.msgBody = JSONObject.parseObject("{\"templateIdIni\":1,\"userId\":123}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }



    @JTest
    @JTestClass.title("协助接收方查看文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0003() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "assitsReceviceViewDoc";

        req.msgBody = JSONObject.parseObject("{\"templateIdIni\":1,\"userId\":125}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("协助接收方递交文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0004() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "assitsReceviceSubmitDoc";

        req.msgBody = JSONObject.parseObject("{\"templateIdIni\":1,\"userId\":125}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("查看协助文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0005() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "selectAssitsDocument";

        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("templateIdAssits", 455);
        String ret = httpclient.post(url_base, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("请求: " + req.toJSONString());
        System.out.println("响应: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("协助发起方审核文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0006() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "assitsSendAuditorDoc";

        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("auditStatus", 6);
        req.msgBody.put("templateIdAssist", 1521);
        req.msgBody.put("talkId", 1418);
        req.msgBody.put("pkId", 18802);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("请求: " + req.toJSONString());
        System.out.println("响应: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("协助发起方审核文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0007() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "showUserAssistFee";

        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("talkId", 1530);
        req.msgBody.put("userIds", "1301,1200");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("请求: " + req.toJSONString());
        System.out.println("响应: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("协助发起方审核文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0008() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "payPhase";

        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("talkId", 1650);
        req.msgBody.put("passWord", "e10adc3949ba59abbe56e057f20f883e");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("请求: " + req.toJSONString());
        System.out.println("响应: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("任务里面的加工")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0009() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "chooseTask";

        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("talkId", 2830);
        req.msgBody.put("projectTypeId", 95);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("请求: " + req.toJSONString());
        System.out.println("响应: " + resp.toJSONString());
    }


    public static void main(String args[]) {

        run(TestProjectAssits.class, "test0009");

    }

}
