package ytb.project;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Package: projecttest.companyProManagerTest
 * Author: ZCS
 * Date: Created in 2018/10/17 17:20
 */
public class TestProjectFlow extends ITestImpl {
    String url_182 = "http://project.youtobon.com/rest/project/template";
    String url_base = "http://mysql.kunlong.com/rest/project/template";

    String url_image = "http://mysql.kunlong.com/rest/project/image";
    String url_upload = "http://mysql.kunlong.com/rest/project/upload";
    String url_projectCenter = "http://mysql.kunlong.com/rest/projectCenter";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    int projectId = 37;
    int docid = 157;
    int docLstId = 372;

    MsgRequest req = new MsgRequest();
    String token = "d302b051474d4b6faed79f9b0e37f1cf";
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
    @JTestClass.title("test0016_sgetInviteList")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0001_sgetInviteList() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "getInviteList";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 41);
        req.msgBody.put("reqType", 41);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("test0017_projectRelease")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0017_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "selectProjectList";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 41);
        req.msgBody.put("reqType", 41);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("test0017_projectRelease")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0018_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getProjectTask";
        req.msgBody = JSONObject.parseObject("{\"talkId\":6924,\"type\":\"PA\",\"stage\":200}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("test0017_projectRelease")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0019_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "memberSubmit";
        req.msgBody = JSONObject.parseObject("{\"talkId\":102,\"type\":\"乙方\",\"stage\":1}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("洽谈支付")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0020_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "payProject";
        req.msgBody = JSONObject.parseObject("{\"talkId\":6127,\"password\":\"45000\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("阶段支付")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0021_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "payPhase";
        req.msgBody = JSONObject.parseObject("{\"talkId\":201,\"fee\":\"45000\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("阶段支付")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0022_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "payPhase";
        req.msgBody = JSONObject.parseObject("{\"talkId\":89,\"passWord\":\"e10adc3949ba59abbe56e057f20f883e\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("阶段支付")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0023_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getMemberDocList";
        req.msgBody = JSONObject.parseObject("{\"talkId\":102,\"stage\":\"1\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("阶段支付")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0024_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getProjectEvent";
        req.msgBody = JSONObject.parseObject("{\"talkId\":286,\"stage\":\"0\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("阶段支付")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0025_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getFolders";
        req.msgBody = JSONObject.parseObject("{\"projectId\":95,\"phase\":\"1\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("点击查看任务")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0026_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "clickViewTask";
        req.msgBody = JSONObject.parseObject("{\"talkId\":328,\"phase\":\"0\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());

    }

    @JTest
    @JTestClass.title("查看任务")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0027_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "viewTask";
        req.msgBody = JSONObject.parseObject("{\"talkId\":328,\"userId\":356,\"stage\":0}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("添加会议任务")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0028_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "addMeetingTask";
        req.msgBody = JSONObject.parseObject("{\"talkId\":334,\"participant\":\"123,356\",\"remark\":\"测试添加会议任务\",\"issue\":\"任务完成时间\",\"startTime\":\"2019-1-7 09:31:50\",\"stopTime\":\"2019-10-7 09:31:50\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("添加自定义任务")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0029_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "addCustomTask";
        req.msgBody = JSONObject.parseObject("{\"talkId\":334,\"receiver\":\"123\",\"remark\":\"测试自定义任务\",\"docListId\":\"0\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("添加物流单号任务")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0030_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "addShipmentNumber";
        req.msgBody = JSONObject.parseObject("{\"talkId\":334,\"number\":\"dhsuh45454556\",\"remark\":\"测试添加物流单号任务\",\"goodsName\":\"分子碳\",\"documentName\":\"超导体文档\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("大变更支付")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0031_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "partyAPayBigChange";
        req.msgBody = JSONObject.parseObject("{\"talkId\":359,\"fee\":\"28456.516\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("审核文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0032_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "verifyProjectDoc";
        req.msgBody = JSONObject.parseObject("{\"talkId\":349,\"projectId\":457,\"status\":3}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("测试获取任务")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0033_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "selectTask";
        req.msgBody = JSONObject.parseObject("{\"talkId\":48}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("添加共享文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0034_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "addShareDoc";
        req.msgBody = JSONObject.parseObject("{\"docListId\":362,\"talkId\":77,\"toUserId\":336,}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("添加协助文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0035_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "addAssistDoc";
        req.msgBody = JSONObject.parseObject("{\"docListId\":362,\"talkId\":77,\"toUserId\":\"336,1002\",\"remark\":\"测试协助\"}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());

    }

    @JTest
    @JTestClass.title("取消分享文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0036_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "cancelShareDoc";
        req.msgBody = JSONObject.parseObject("{\"docListId\":362}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());

    }


    @JTest
    @JTestClass.title("甲方审核阶段文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0037_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "PartyAVerify";
        req.msgBody = JSONObject.parseObject("{\"talkId\":102,\"status\":4}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());

    }


    @JTest
    @JTestClass.title("洽谈中止")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0038_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "talkTermination";
        req.msgBody = JSONObject.parseObject("{\"talkId\":145,\"Notify\":\"由于项目变更,此次无法与您合作，保持联系，期待有机会合作。谢谢您！\",\"description\":\"测试项目终止通知\"}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());

    }


    @JTest
    @JTestClass.title("同意邀请")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0039_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "getInviteList";
        req.msgBody = JSONObject.parseObject("{\"type\":2,\"reqType\":1,\"currPage\":1,\"pageSize\":10}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());

    }

    public static void main(String args[]) {
        run(TestProjectFlow.class, "test0018_projectRelease");
    }


}

