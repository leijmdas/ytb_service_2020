package ytb.manager.testcase

import com.alibaba.fastjson.JSONObject
import com.jtest.NodesFactroy.Inject.Inject
import com.jtest.NodesFactroy.Node.HttpClientNode
import com.jtest.annotation.JTest
import com.jtest.annotation.JTestClass
import com.jtest.testframe.ITestImpl
import ytb.manager.ManagerCommon.ManagerLogin
import ytb.manager.projectStat.service.impl.ProjectStatTimerTaskExeOnce
import ytb.common.RestMessage.MsgRequest
import ytb.common.RestMessage.MsgResponse

@JTestClass.author("leijm")
class TestRestProjectStat extends ITestImpl {
    String url = "http://localhost/rest/project/stat";
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    String token = "c1f119fd8ef24954b2485b6f4625779a";

    MsgRequest req = new MsgRequest();
    String data;
    ManagerLogin login = new ManagerLogin();

    public void suiteSetUp() {
        req.token = token;
    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {
        token = login.login(req);
        //req.setApiKey(login.getApiKey());

        //req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatPage";
        req.cmd = "pageGetStatTree";
        String body = "{}";
    }

    @JTest
    @JTestClass.title(" test0001_ipageGetStatTree")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0001_ipageGetStatTree() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatPage";
        req.cmd = "pageGetStatTree";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
        System.out.println( JSONObject.toJSONString(req));
    }

    @JTest
    @JTestClass.title(" test0001_ipageGetStatTree")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0002_ipageGetStatList() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatPage";
        req.cmd = "pageGetStatList";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
        System.out.println(JSONObject.toJSONString(req));
    }
    @JTest
    @JTestClass.title( " test0003_iprojectStatMoney")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0003_iprojectStatMoney() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatMoney";
        req.cmd = "statMoney";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
      }
    @JTest
    @JTestClass.title( " test0004_statProjectType")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0004_statProjectType() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatMoney";
        req.cmd = "statProjectType";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    @JTest
    @JTestClass.title( " test0005_sstatUserArea")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0005_sstatUserArea() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatMoney";
        req.cmd = "statUserArea";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    @JTest
    @JTestClass.title( " test0006_selectProjectStatMoney")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0006_selectProjectStatMoney() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatMoney";
        req.cmd = "selectProjectStatMoney";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    //projectStatUser
    @JTest
    @JTestClass.title( " test0007_projectStatUserSelectUserDayActive")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0007_projectStatUserSelectUserDayActive() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatUser";
        req.cmd = "selectUserDayActive";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    @JTest
    @JTestClass.title( " test0008_projectStatUserStatUserDayActive")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0008_projectStatUserStatUserDayActive() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatUser";
        req.cmd = "statUserDayActive";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    @JTest
    @JTestClass.title( " test0009_projectStatUserStatUserProjectType")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0009_projectStatUserStatUserProjectType() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatUser";
        req.cmd = "statUserProjectType";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    @JTest
    @JTestClass.title( " test0010_projectStatUserselectUserProjectType")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0010_projectStatUserselectUserProjectType() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatUser";
        req.cmd = "selectUserProjectType";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }
    @JTest
    @JTestClass.title( " test0011_projectstatUserCompanyType")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0011_projectstatUserCompanyType () {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatUser";
        req.cmd = "statUserCompanyType";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }
    @JTest
    @JTestClass.title( " test0012_statUserTotal")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0012_statUserTotal () {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatUser";
        req.cmd = "statUserTotal";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url,  JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    @JTest
    @JTestClass.title( " test0013_statUserAvg")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0013_statUserAvg () {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatUser";
        req.cmd = "statUserAvg";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    @JTest
    @JTestClass.title( " test0014_statProjectArea2Area")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0014_statProjectArea2Area () {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatProject";
        req.cmd = "statProjectArea2Area";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    @JTest
    @JTestClass.title( " projectStatProject")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0015_projectStatstatTotal () {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatProject";
        req.cmd = "statTotal";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }
    @JTest
    @JTestClass.title( " test0016_projectStatstatAvg")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0016_projectStatstatAvg() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatProject";
        req.cmd = "statAvg";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }


    @JTest
    @JTestClass.title( " test0017_projectStatstatProjectType")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0017_projectStatstatProjectType() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectStatProject";
        req.cmd = "statProjectType";
        String body = "{}";
        req.msgBody = JSONObject.parseObject(body);
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toString());
    }

    @JTest
    @JTestClass.title( " test0018_ProjectStatTimerTaskExeOnce")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0018_ProjectStatTimerTaskExeOnce() {
        new ProjectStatTimerTaskExeOnce().execute(req,null);


    }
    //cmd: "statMoney" cmdtype: "projectStatMoney"
    public static void main(String[] args) {
        run(TestRestProjectStat.class, 17);
        //Integer row=new CalUserCredit().calTotalUserQ();
    }
}
