package ytb.manager.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.manager.ManagerCommon.ManagerConst;
import ytb.manager.template.model.*;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

/**
 * @author lxz 2019/2/12 16:21
 */
public class TestProjectTemplate extends ITestImpl {

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpClient;

    MsgRequest req = new MsgRequest();

    String urlProjectTemplate = ManagerConst.url_template;

    String token = "15373258c3924df0b1c4836f521c4ef1";

    @Inject("ytb_project")
    JDbNode dbProject;

    @Override
    public void suiteSetUp() throws Exception {
//        System.out.println("========suiteSetUp========");
    }

    @Override
    public void setUp() throws Exception {
//        System.out.println("========setUp========");
        req.token = token;
        long l = System.currentTimeMillis();
        req.reqtime = l;
        req.seqno = l;
        req.cmdtype = "";
        req.cmd = "";
        req.msgBody = JSONObject.parseObject("{}");
    }

    @Override
    public void tearDown() throws Exception {
//        System.out.println("========tearDown========");
    }

    @Override
    public void suiteTearDown() throws Exception {
//        System.out.println("========suiteTearDown========");
    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0001_projType_addProjType")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0001_projType_addProjType() {
        req.cmdtype = "projType";
        req.cmd = "addProjType";
        Dict_ProjectTypeModel projectTypeModel = new Dict_ProjectTypeModel();

        projectTypeModel.setParentId(714);
        projectTypeModel.setRepositoryId(43);

        projectTypeModel.setTitle("测试22223");
        projectTypeModel.setOrderNo(0);
        projectTypeModel.setState(1);
        projectTypeModel.setProjectType(1);

        projectTypeModel.setStaxRate(0.01f);
        projectTypeModel.setTaxRate(0.01f);
        projectTypeModel.setcRate(0.01f);
        projectTypeModel.setFeeRate(0.01f);
        projectTypeModel.setpRate(0.01f);

        req.msgBody = JSON.parseObject(JSON.toJSONString(projectTypeModel));

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0002_projType_removeProjType")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0002_projType_removeProjType() {
        req.cmdtype = "projType";
        req.cmd = "removeProjType";

        req.msgBody.put("projectTypeId", 714);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0003_projType_modifyProjType")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0003_projType_modifyProjType() {
        req.cmdtype = "projType";
        req.cmd = "modifyProjType";

        Dict_ProjectTypeModel projectTypeModel = new Dict_ProjectTypeModel();
        projectTypeModel.setProjectTypeId(715);
        projectTypeModel.setTitle("xxxx");
        projectTypeModel.setRepositoryId(999);
        projectTypeModel.setProjectType(1);
        projectTypeModel.setOrderNo(999);
        projectTypeModel.setState(1);

        projectTypeModel.setTaxRate(5);
        projectTypeModel.setStaxRate(5);
        projectTypeModel.setFeeRate(5);
        projectTypeModel.setcRate(5);
        projectTypeModel.setpRate(5);

        req.msgBody = JSON.parseObject(JSON.toJSONString(projectTypeModel));

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0004_projType_getProjTypeListData")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0004_projType_getProjTypeListData() {
        req.cmdtype = "projType";
        req.cmd = "getProjTypeListData";

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0005_repository_addRepository")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0005_repository_addRepository() {
        req.cmdtype = "repository";
        req.cmd = "addRepository";

        Template_Repository tr = new Template_Repository();
        tr.setNo("999");
        tr.setName("测试");
        tr.setWorkJobType(21);
        tr.setState(0);
        tr.setOrderNo(999);

        req.msgBody = JSON.parseObject(JSON.toJSONString(tr));

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0006_repository_getRepositoryData")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0006_repository_getRepositoryData() {
        req.cmdtype = "repository";
        req.cmd = "getRepositoryData";

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0007_repository_removeRepository")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0007_repository_removeRepository() {
        req.cmdtype = "repository";
        req.cmd = "removeRepository";

        req.msgBody.put("repositoryId", 46);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0008_repository_modifyRepository")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0008_repository_modifyRepository() {
        req.cmdtype = "repository";
        req.cmd = "modifyRepository";

        Template_Repository tr = new Template_Repository();
        tr.setRepositoryId(47);
        tr.setNo("9999999");
        tr.setName("测试999");
        tr.setWorkJobType(99);
        tr.setState(1);
        tr.setOrderNo(99999);

        req.msgBody = JSON.parseObject(JSON.toJSONString(tr));

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0009_repository_getWorkJobListByWorkJobTypeId")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0009_repository_getWorkJobListByWorkJobTypeId() {
        req.cmdtype = "repository";
        req.cmd = "getWorkJobListByWorkJobTypeId";

        req.msgBody.put("workJobTypeId", 21);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0010_repository_getDocTemplateList")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0010_repository_getDocTemplateList() {
        req.cmdtype = "repository";
        req.cmd = "getDocTemplateList";

        req.msgBody.put("repositoryId", 43);
        req.msgBody.put("docTypeArr", new int[]{100, 101, 102});

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0011_repository_modifyDoctemplate")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0011_repository_modifyDoctemplate() {
        req.cmdtype = "repository";
        req.cmd = "modifyDoctemplate";

        Dict_TemplateModel templateModel = new Dict_TemplateModel();
        templateModel.setTemplateId(548);
        templateModel.setTitle("需求说明书-案例V0.4.1-动态增删目录.xlsxQQQQ");
        templateModel.setAlias("tagDocument@需求说明书QQQ");

        req.msgBody = (JSONObject) JSON.toJSON(templateModel);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0012_repository_addDocTemplate")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0012_repository_addDocTemplate() {
        req.cmdtype = "repository";
        req.cmd = "addDocTemplate";

        Dict_TemplateModel templateModel = new Dict_TemplateModel();
        templateModel.setTitle("XXXXX");
        templateModel.setDocType(100);
        templateModel.setRepositoryId(12);

        req.msgBody = (JSONObject) JSON.toJSON(templateModel);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0013_repository_removeDoctemplate")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0013_repository_removeDoctemplate() {
        req.cmdtype = "repository";
        req.cmd = "removeDoctemplate";

        req.msgBody.put("templateId", 549);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0014_workJobType_getWorkJobTypeList")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0014_workJobType_getWorkJobTypeList() {
        req.cmdtype = "workJobType";
        req.cmd = "getWorkJobTypeList";

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0015_workJobType_addWorkJobType")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0015_workJobType_addWorkJobType() {
        req.cmdtype = "workJobType";
        req.cmd = "addWorkJobType";

        Dict_WorkJobTypeModel wjt = new Dict_WorkJobTypeModel();
        wjt.setWorkJobTypeName("XXX");
        wjt.setWorkGroupNum(5);

        req.msgBody = (JSONObject) JSON.toJSON(wjt);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0016_workJobType_modifyWorkJobType")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0016_workJobType_modifyWorkJobType() {
        req.cmdtype = "workJobType";
        req.cmd = "modifyWorkJobType";

        Dict_WorkJobTypeModel wjt = new Dict_WorkJobTypeModel();
        wjt.setWorkJobTypeId(27);
        wjt.setWorkJobTypeName("XXXQQQ");
        wjt.setWorkGroupNum(55);

        req.msgBody = (JSONObject) JSON.toJSON(wjt);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0017_workJobType_removeWorkJobType")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0017_workJobType_removeWorkJobType() {
        req.cmdtype = "workJobType";
        req.cmd = "removeWorkJobType";

        req.msgBody.put("workJobTypeId", 27);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0018_workJobType_getWorkJobDetailsInfoList")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0018_workJobType_getWorkJobDetailsInfoList() {
        req.cmdtype = "workJobType";
        req.cmd = "getWorkJobDetailsInfoList";

        req.msgBody.put("workJobTypeId", 21);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0019_workJobType_addWorkJob")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0019_workJobType_addWorkJob() {
        req.cmdtype = "workJobType";
        req.cmd = "addWorkJob";

        Dict_WorkJobModel workJob = new Dict_WorkJobModel();
        workJob.setIsDefault(0);
        workJob.setOrderNo(127);
        workJob.setTitle("XXX");
        workJob.setTitleAlias("XXXXX");
        workJob.setWorkJobTypeId(21);

        req.msgBody = (JSONObject) JSON.toJSON(workJob);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0020_workJobType_modifyWorkJob")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0020_workJobType_modifyWorkJob() {
        req.cmdtype = "workJobType";
        req.cmd = "modifyWorkJob";

        Dict_WorkJobModel workJob = new Dict_WorkJobModel();
        workJob.setWorkJobId(62);
        workJob.setIsDefault(1);
        workJob.setOrderNo(-128);
        workJob.setTitle("XXXQ");
        workJob.setTitleAlias("XXXXXQ");

        req.msgBody = (JSONObject) JSON.toJSON(workJob);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0021_workJobType_removeWorkJob")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0021_workJobType_removeWorkJob() {
        req.cmdtype = "workJobType";
        req.cmd = "removeWorkJob";

        req.msgBody.put("workJobId", 62);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0022_workJobType_getTaskDetailsInfoList")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0022_workJobType_getTaskDetailsInfoList() {
        req.cmdtype = "workJobType";
        req.cmd = "getTaskDetailsInfoList";

        req.msgBody.put("workJobId", 50);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0023_workJobType_addTask")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0023_workJobType_addTask() {
        req.cmdtype = "workJobType";
        req.cmd = "addTask";

        Dict_TaskModel task = new Dict_TaskModel();
        task.setTaskName("XXX");
        task.setTaskNameAlias("XXXXXXX");
        task.setDocType(100);
        task.setWorkJobId(50);
        task.setIsDefault(0);
        task.setIsOptional(1);

        req.msgBody = (JSONObject) JSON.toJSON(task);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0024_workJobType_modifyTask")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0024_workJobType_modifyTask() {
        req.cmdtype = "workJobType";
        req.cmd = "modifyTask";

        Dict_TaskModel task = new Dict_TaskModel();
        task.setTaskId(50);
        task.setTaskName("XXXQQQ");
        task.setTaskNameAlias("XXXXXXXQQQ");
        task.setDocType(200);
        task.setIsDefault(1);
        task.setIsOptional(0);
        task.setOrderNo(100);

        req.msgBody = (JSONObject) JSON.toJSON(task);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0025_workJobType_removeTask")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0025_workJobType_removeTask() {
        req.cmdtype = "workJobType";
        req.cmd = "removeTask";

        req.msgBody.put("taskId", 50);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0026_workJobType_getDocTemplateDetailsInfo")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0026_workJobType_getDocTemplateDetailsInfo() {
        req.cmdtype = "workJobType";
        req.cmd = "getDocTemplateDetailsInfo";

        req.msgBody.put("workJobId", 50);
        req.msgBody.put("docTypeArr", new int[]{300, 301, 302, 303, 304});

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0027_workJobType_addDocTemplate")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0027_workJobType_addDocTemplate() {
        req.cmdtype = "workJobType";
        req.cmd = "addDocTemplate";

        Dict_TemplateModel templateModel = new Dict_TemplateModel();
        templateModel.setWorkJobId(50);
        templateModel.setTitle("XXXX");
        templateModel.setAlias("XXXXXXX");
        templateModel.setDocType(300);

        req.msgBody = (JSONObject) JSON.toJSON(templateModel);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0028_workJobType_modifyDocTemplate")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0028_workJobType_modifyDocTemplate() {
        req.cmdtype = "workJobType";
        req.cmd = "modifyDocTemplate";

        Dict_TemplateModel templateModel = new Dict_TemplateModel();
        templateModel.setTemplateId(551);
        templateModel.setTitle("XXXXXXXASDSDDDD");
        templateModel.setAlias("XXXXXXXDDDDDDDD");

        req.msgBody = (JSONObject) JSON.toJSON(templateModel);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0029_workJobType_removeDocTemplate")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0029_workJobType_removeDocTemplate() {
        req.cmdtype = "workJobType";
        req.cmd = "removeDocTemplate";

        req.msgBody.put("templateId", 551);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0030_workJobType_spFindAllTemplateByWorkjobType")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0030_workJobType_spFindAllTemplateByWorkjobType() {
        req.cmdtype = "workJobType";
        req.cmd = "spFindAllTemplateByWorkjobType";

        req.msgBody.put("workJobTypeId", 21);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0031_workJobType_spFindAllTemplateByWorkjobType")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0031_workJobType_spFindAllTemplateByWorkjobType() {
        req.cmdtype = "repository";
        req.cmd = "spFindAllTemplateByRepository";

        req.msgBody.put("repositoryId", 12);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0032_repository_getDocTemplateDetails")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0032_repository_getDocTemplateDetails() {
        req.cmdtype = "repository";
        req.cmd = "getDocTemplateDetails";

        req.msgBody.put("templateId", 548);
        req.msgBody.put("repositoryId", 12);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);

        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0033_projType_modifyState")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0033_projType_modifyState() {
        req.cmdtype = "projType";
        req.cmd = "modifyState";

        req.msgBody.put("projectTypeId", 702);
        req.msgBody.put("state", 1);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0034_workJobType_modifyState")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0034_workJobType_modifyState() throws Exception{
        req.cmdtype = "workJobType";
        req.cmd = "modifyState";

        req.msgBody.put("WorkJobTypeId", 21);
        req.msgBody.put("state", 1);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

//        dbProject.clearSql().appendSql("SELECT state from dict_work_job_type where work_job_type_id=21");
//        dbProject.execSelect().checkRecord("state=" + 0);

    }


    @JTest
    @JTestClass.title("TestProjectTemplate.test0035_repository_modifyState")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0035_repository_modifyState() {
        req.cmdtype = "repository";
        req.cmd = "modifyState";

        req.msgBody.put("repositoryId", 12);
        req.msgBody.put("state", 1);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(urlProjectTemplate, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        resStr = JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat);
        System.err.println(resStr);

        httpClient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }



    public static void main(String[] args) {
        run(TestProjectTemplate.class, 4);
    }
}
