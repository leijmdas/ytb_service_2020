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

/**
 * Package: projecttest.companyProManagerTest
 * Author: ZCS
 * Date: Created in 2018/10/17 17:20
 */
public class TestProjectRelease extends ITestImpl {
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
    String token = "ccf124ada0414e78a46830fea25e7405";
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
    @JTestClass.title("获取收到的申请、邀请列表")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0001_getApplyRecList() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "getInviteList";

        int reqType =1;//1：邀请  2：申请
        int type = 2;//1：发出     2：收到
        req.msgBody = JSONObject.parseObject("{\"currPage\":1,\"pageSize\":10,\"reqType\":"+reqType+",\"type\":"+type+"}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("获取收到的申请详情列表")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0002_getApplyRecDetailsList() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "clickInviteList";
        req.msgBody = JSONObject.parseObject("{\"currPage\":1,\"pageSize\":10,\"projectId\":59}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }





    @JTest
    @JTestClass.title("添加浏览数")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0014_sgetInviteList() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "addviews";
        req.msgBody = JSONObject.parseObject("{\"projectId\":304}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("项目中心")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0015_sgetInviteList() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "getProjectList";
        req.msgBody = JSONObject.parseObject("{\"status\":0,\"talkId\":}");
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
        req.msgBody = JSONObject.parseObject("{\"talkId\":201,\"type\":\"乙方\",\"stage\":2}");

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
        req.msgBody = JSONObject.parseObject("{\"talkId\":169,\"type\":\"组员\",\"stage\":1}");

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
        req.msgBody = JSONObject.parseObject("{\"talkId\":200,\"fee\":\"45000\"}");

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
    @JTestClass.title("同意申请")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0022_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{\"talkId\":356,\"projectId\":461,\"phase_status\":200}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("版本号升级")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0023_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "versionUpgrade";
        req.msgBody = JSONObject.parseObject("{\"docListId\":66}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }



    @JTest
    @JTestClass.title("版本号升级")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0024_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "selectHistoryDoc";
        req.msgBody = JSONObject.parseObject("{\"docListId\":66}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("版本号升级")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0025_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "inviteMember";
        req.msgBody = JSONObject.parseObject("{\"docListId\":66}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("版本号升级")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0026_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "getUserFriendList";
        req.msgBody = JSONObject.parseObject("{\"remark\":\"10\"}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }





    public static void main(String args[]) {
        run(TestProjectRelease.class, "test0001_getApplyRecList");

    }

}

