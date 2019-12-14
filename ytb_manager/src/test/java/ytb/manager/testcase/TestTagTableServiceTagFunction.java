package ytb.manager.testcase;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.manager.ManagerCommon.ManagerConst;
import ytb.manager.ManagerCommon.ManagerLogin;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;


@JTestClass.author("leijm")
public class TestTagTableServiceTagFunction extends ITestImpl {
    String url_manager = ManagerConst.url_manager;
    String url_project = ManagerConst.url_project;

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    MsgRequest req = new MsgRequest();
    String token = "";
    int req_id = 1350;
    int workplan_id = 1359;

    int project_id = 2488;
    int project_document_id = 38605;
    ManagerLogin login = new ManagerLogin();
    int apiKey = 1111;

    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;

    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {
    }

    MsgRequest defaultReq() {
        MsgRequest req = new MsgRequest();
        req.token = token;

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportTable";
        req.msgBody = JSONObject.parseObject("{}");
        // req.setApiKey(apiKey + "");
        req.setApiKey(login.getApiKey());
        req.setSign(apiKey + "");
        return req;
    }

    @Override
    public void setUp() {
        token = login.login();
        req = defaultReq();
        req.setApiKey(login.getApiKey());
    }

    @Override
    public void tearDown() {

    }

    @JTest
    @JTestClass.title("test0001_refTagTableParamTask")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0001_refTagTableParamTask() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "refTagTableParam";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 230);
        req.msgBody.put("tag", "tagTableParamRef@project.project_name$A1.11");
        req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
        req.msgBody.put("tag", "tagTableParamRef@岗位任务.任务名称(电路)$A.11");
        req.msgBody.put("tag", "tagTableParamRef@岗位任务.任务名称(机械)$A.11");
        req.msgBody.put("tag", "tagTableParamRef@岗位任务.岗位名称(机械)$A.11");
        //req.msgBody.put("tag","tagTableParamRef@岗位任务.岗位名称(电路)$A.11");
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_manager);
        checkEQ(0, resp.getRetcode());

    }


    @JTest
    @JTestClass.title("test0002_refTagTableParamProject")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0002_refTagTableParamProject() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "refTagTableParam";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 973);

        req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
        //req.msgBody.put("tag","tagTableParamRef@岗位任务.任务名称(电路)$A.11");
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 1359);
        req.msgBody.put("tag", "tagTableTextRef@岗位任务.任务名称(机械)$A.11");
        req.msgBody.put("tag", "tagTableTextRef@岗位任务.岗位名称(机械)$A.11");
        //req.msgBody.put("tag", "tagTableParamRef@工作组.工作组名称");
        //req.msgBody.put("tag", "tagTableParamRef@work_group.group_name");
        //req.msgBody.put("tag","tagTableParamRef@test_report_check_sum.percent_sum$A3");
        //req.msgBody.put("tag","tagTableRef@数据字典(项目变更类别)");
        // req.msgBody.fluentPut("projectId", 1).fluentPut("documentId", 423);
        //req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
        //req.msgBody.put("tag", "tagTableParamRef@数据字典.字典名称(项目变更类别)");
        //req.msgBody.fluentPut("projectId", 5).fluentPut("documentId", 14);
        req.msgBody.put("tag", "tagTableParamRef@乙方组长.呢称");
        req.msgBody.put("tag", "tagTableParamRef@乙方组员.呢称");

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_manager);
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }



    @JTest
    @JTestClass.title("test0003_refTagTableParamProject")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0003_refTagTableParamProject() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "refTagTableParam"; ;

        req.msgBody.fluentPut("projectId", 1912);//.fluentPut("documentId", 30386);
        req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }


    @JTest
    @JTestClass.title("tagTableRef@工作计划书之总任务一览表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0004_testRefTagTableParamProject() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "testRefTagTableParam";

        req.msgBody.fluentPut("projectId", 1).fluentPut("documentId", 4);
        req.msgBody.fluentPut("functionId",7);
        String ret = httpclient.post(url_manager, req.toString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_manager);
    }

    //11
    @JTest
    @JTestClass.title("tagTableRef@发票图片表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0005_testRefTagTableParamWorkGroup() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "testRefTagTableParam";

        req.msgBody.fluentPut("functionId",11);
        req.msgBody.fluentPut("projectId", 1).fluentPut("documentId", 1);
        req.msgBody.fluentPut("user_id", 2).fluentPut("phase", 0);
        String ret = httpclient.post(url_manager, req.toString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_manager);
    }

    @JTest
    @JTestClass.title("tagTableRef@发票图片表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0006_testRefTagTableParamWorkGroup() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "testRefTagTableParam";

        req.msgBody.fluentPut("functionId", 12);
        req.msgBody.fluentPut("projectId", 1);
        req.msgBody.fluentPut("documentId", 4);

        req.msgBody.fluentPut("groupId", 1);
        String ret = httpclient.post(url_manager, req.toString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_manager);
    }

    @JTest
    @JTestClass.title("test0002_refTagTableParamProject")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0007_refTagTableParamProject() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "refTagTableParam";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 973);

        req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
        req.msgBody.fluentPut("projectId", 0);
        req.msgBody.fluentPut("documentId", 1359);
        req.msgBody.fluentPut("talkId",1);

        req.msgBody.put("tag", "tagTableParamRef@乙方组长.呢称");
        req.msgBody.put("tag", "tagTableParamRef@乙方组员.呢称");
        req.msgBody.put("tag", "tagTableParamRef@乙方组员.呢称列表");

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_manager);
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }

    public static void main(String[] args) throws Exception {

          run(TestTagTableServiceTagFunction.class, 7);

    }


}


