package ytb.manager.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import ytb.manager.ManagerCommon.ManagerLogin;
import ytb.common.utils.YtbUtils;
import ytb.manager.template.model.Dict_document;
import ytb.manager.template.model.Mngr_Re_Document;
import ytb.manager.template.rest.RestTemplateManager;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.manager.templateexcel.model.xls.TemplateDocumentModel;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;


@JTestClass.author("leijm")
public class TestTemplateManager extends ITestImpl {
    String url_base = "http://localhost:80/rest/template";
    String url = "http://localhost/rest/template";
    String url_admin = "http://admin.youtobon.com/rest/template";


    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    MsgRequest req = new MsgRequest();
    String data;
    String token = "c1f119fd8ef24954b2485b6f4625779a";


    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {
        ManagerLogin login = new ManagerLogin();
        token = login.login();
        req.token = token;

        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectType";
        req.cmd = "getProjectTypeList";
        req.msgBody = JSONObject.parseObject("{}");
        req.setSign(login.getApiKey());
        req.setApiKey(login.getApiKey());
    }

    @Override
    public void tearDown() {

    }

    @JTest
    @JTestClass.title("查询项目分类列表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0001_rest_mngr() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "getProjTypeList";
        req.msgBody = JSONObject.parseObject("{}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post("http://localhost:80/rest/template",data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("查询项目分类")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0002_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "getProjType";
        req.msgBody = JSONObject.parseObject("{\"projectTypeId\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }

    @JTest
    @JTestClass.title("删除项目分类")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/WebMngr/rest/template")
    @JTestClass.exp("ok")
    public void test0003_rest_mngr() {


        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "removeProjType";
        req.msgBody = JSONObject.parseObject("{\"projectTypeId\":5}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("添加项目分类")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0004_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "addProjType";
        req.msgBody = JSONObject.parseObject("{\"WorkJobTypeId\":1,\"ParentId\":1,\"CreateBy\":1,\"Title\":\"测试项目分类添加\",\"Depth\":1,\"State\":1,\"OrderNo\":1,}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("修改项目分类")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0005_rest_mngr() {


        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "modifyProjType";
        req.msgBody = JSONObject.parseObject("{\"projectTypeId\":36,\"workJobTypeId\":2,\"title\":\"测试项目分类修改zz\",\"state\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("通过项目查询文档模板列表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0006_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "getDocTemplateList";
        req.msgBody = JSONObject.parseObject("{\"projectTypeId\":1,\"docType\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("通过项目查询文档模板")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0007_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "getDocTemplate";
        req.msgBody = JSONObject.parseObject("{\"templateId\":1}");
        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("通过项目删除文档模板")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0008_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "removeDocTemplate";
        req.msgBody = JSONObject.parseObject("{\"templateId\":3}");
        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("通过项目添加文档模板")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0009_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "addDocTemplate";
        req.msgBody = JSONObject.parseObject("{\"projectTypeId\":28,\"workJobId\":0,\"contents_init\":\"测试用例\",\"contents_new\":\"测试用例添加模板\",\"documentPath\":\"D://\",\"docType\":1,\"uuid\":\"GJDIOJHPJIR\"}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("通过项目修改文档模板")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0010_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "modifyDocTemplate";
        req.msgBody = JSONObject.parseObject("{\"templateId\":1,\"projectTypeId\":1,\"workJobId\":0,\"contents_init\":\"二人行\",\"contents_new\":\"测试修改\",\"documentPath\":\"E://\",\"docType\":1,\"uuid\":\"测试修改\",\"state\":1,\"createBy\":1}");

        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("通过项目类别获取信息")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0011_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projType";
        req.cmd = "getProjTypeDetailsInfo";
        req.msgBody = JSONObject.parseObject("{\"projectTypeId\":1}");
        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("查询岗位类别列表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0012_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "getWorkJobTypeList";
        req.msgBody = JSONObject.parseObject("{}");
        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("查询岗位类别")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0013_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "getWorkJobType";
        req.msgBody = JSONObject.parseObject("{\"workJobTypeId\":2}");
        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    @JTest
    @JTestClass.title("删除岗位类别")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0014_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "removeWorkJobType";
        req.msgBody = JSONObject.parseObject("{\"workJobTypeId\":8}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    @JTest
    @JTestClass.title("添加岗位类别")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0015_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "addWorkJobType";
        req.msgBody = JSONObject.parseObject("{\"workJobTypeName\":\"It测试添加用例1\",\"workGroupNum\":1,\"createBy\":1,\"orderNo\":1,\"state\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("修改岗位类别")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0016_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "modifyWorkJobType";
        req.msgBody = JSONObject.parseObject("{\"workJobTypeId\":3,\"workJobTypeName\":\"It测试修改用例\",\"workGroupNum\":1,\"createBy\":1,\"orderNo\":3,\"state\":2}");

        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    @JTest
    @JTestClass.title("查询岗位列表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0017_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "getWorkJobList";
        req.msgBody = JSONObject.parseObject("{\"workJobTypeId\":4}");
        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    @JTest
    @JTestClass.title("查询岗位")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0018_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "getWorkJob";
        req.msgBody = JSONObject.parseObject("{\"workJobId\":2}");
        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    @JTest
    @JTestClass.title("删除岗位")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0019_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "removeWorkJob";
        req.msgBody = JSONObject.parseObject("{\"workJobId\":3}");
        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("添加岗位")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0020_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "addWorkJob";
        req.msgBody = JSONObject.parseObject("{\"workJobTypeId\":1,\"title\":\"测试用例添加岗位\",\"title_alias\":1,\"createBy\":1,\"orderNo\":4,\"state\":1}");
        data=new Gson().toJson(req).toString();


        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    @JTest
    @JTestClass.title("修改岗位")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0021_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "modifyWorkJob";
        req.msgBody = JSONObject.parseObject("{\"workJobId\":5,\"workJobTypeId\":1,\"title\":\"测试用例修改岗位\",\"title_alias\":1,\"createBy\":1,\"orderNo\":1,\"state\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("查询工作任务列表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0022_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "getTaskList";
        req.msgBody = JSONObject.parseObject("{\"workJobId\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("查询工作任务")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0023_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "getTask";
        req.msgBody = JSONObject.parseObject("{\"taskId\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("删除工作任务")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0024_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "removeTask";
        req.msgBody = JSONObject.parseObject("{\"taskId\":3}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    @JTest
    @JTestClass.title("添加工作任务")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0025_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "addTask";
        req.msgBody = JSONObject.parseObject("{\"workJobId\":3,\"docType\":1,\"taskName\":\"测试任务添加\",\"taskName_alias\":\"别名\",\"isDefault\":1,\"isOptional\":1,\"orderNo\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("修改工作任务")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0026_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "modifyTask";
        req.msgBody = JSONObject.parseObject("{\"taskId\":3,\"workJobId\":3,\"docType\":1,\"taskName\":\"测试任务修改\",\"taskName_alias\":\"别名\",\"isDefault\":1,\"isOptional\":1,\"orderNo\":3}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("查询真值约束列表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0027_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "getConstList";
        req.msgBody = JSONObject.parseObject("{\"workJobTypeId\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    @JTest
    @JTestClass.title("查询真值约束")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0028_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "getConst";
        req.msgBody = JSONObject.parseObject("{\"constraintId\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("删除真值约束")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0029_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "removeConst";
        req.msgBody = JSONObject.parseObject("{\"constraintId\":3}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("添加真值约束")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0030_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "addConst";
        req.msgBody = JSONObject.parseObject("{\"workJobTypeId\":3,\"title\":\"测试用例添加真值约束\",\"isValid\":1}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("修改真值约束")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0031_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "modifyConst";
        req.msgBody = JSONObject.parseObject("{\"constraintId\":3,\"workJobTypeId\":2,\"title\":\"测试用例修改真值约束\",\"isValid\":1}");

        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:80/rest/template",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("修改真值约束")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
    @JTestClass.exp("ok")
    public void test0032_addUser() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "user";
        req.cmd = "addUserInfo";
        req.msgBody = JSONObject.parseObject("{\"userName\": \"KOP\",\"mobile\":\"123456\",\"password\":\"123456\",\"createBy\":\"1\"}");

        data=new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/sysuser",data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    //IDocumentService documentService=new DocumentServiceImpl();
    @JTest
    @JTestClass.title("test0033_previewJson")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
    @JTestClass.exp("ok")
    public void test0033_previewJson() throws UnsupportedEncodingException {
        String url="http://localhost:80/rest/template";
        req.token = token;//UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "previewJson";
        req.msgBody = JSONObject.parseObject("{\"documentId\":205}");
        data=new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post(url ,data, "application/json");
        httpclient.checkStatusCode(200);
    }



    @JTest
    @JTestClass.title("previewImage")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/template/previewImage")
    @JTestClass.exp("ok")
    public void test0034_previewImage() throws UnsupportedEncodingException {
        String url = "http://localhost:80/rest/template/previewImage";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "previewImage";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId",114);
        byte[] msgBody = Base64.getEncoder().encode(JSONObject.toJSON(req).toString().getBytes());
        data=new Gson().toJson(req).toString();
        System.out.println(data);
        String urlnew = url + "?msgBody="+new String(msgBody);
        String ret = httpclient.post(urlnew, "", "application/json");
        System.out.println(urlnew);
        httpclient.checkStatusCode(200);

    }


    @JTest
    @JTestClass.title("test0035_delReDocument")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0035_delReDocument() throws UnsupportedEncodingException {
        String url="http://localhost:80/rest/template";
        req.token = token;//UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "delReDocument";
        req.msgBody = JSONObject.parseObject("{\"documentId\":119}");
        data=new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post(url,data, "application/json");
        httpclient.checkStatusCode(200);
    }


    @JTest
    @JTestClass.title("上传文档test0036_upload_json")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/template/document/upload")
    @JTestClass.exp("ok")
    public void test0036_upload_json() throws UnsupportedEncodingException {
        String url = "http://localhost/rest/template/upload";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "upload";
        req.msgBody =  JSONObject.parseObject("{}");
        Mngr_Re_Document p=new Mngr_Re_Document();
        p.setName("ini.json");
        p.setDocType(Dict_document.DOC_TYPE_XLS);
        p.setSaveMode(Dict_document.SAVEMODE_DB);
        p.setPicType("");
        String filename=getClass().getResource("/testfile/ini.json").getPath();
        File postFile = new File(filename);
        p.setDocPath("/testfile/ini.json");
        req.msgBody =  JSONObject.parseObject(JSONObject.toJSONString(p));
        HttpEntity entity = buildEntity(url, req.toJSONStringPretty(), postFile);
        String ret = httpclient.post(url ,entity);
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url);

    }

    HttpEntity buildEntity(String url, String msgBody, File postFile) {
        MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
        multipartEntity.setContentType(ContentType.MULTIPART_FORM_DATA);
        multipartEntity.addTextBody("msgBody", msgBody);
        FileBody fundFileBin = new FileBody(postFile);
        multipartEntity.addPart("file", fundFileBin);
        return multipartEntity.build();
    }

    @JTest
    @JTestClass.title("上传文档test0037_uploadPIC")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/template/document/upload")
    @JTestClass.exp("ok")
    public void test0037_uploadPIC() throws UnsupportedEncodingException {
        String url = "http://localhost:80/rest/template/upload";

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "uploadPIC";
        req.msgBody =  JSONObject.parseObject("{}");

        Mngr_Re_Document p=new Mngr_Re_Document();
        p.setName("1.jpg");      //p.setDocType(Dict_document.DOC_TYPE_XLS);
        p.setSaveMode(Dict_document.SAVEMODE_DB);
        p.setPicType("JPG");
        String filename=getClass().getResource("/testfile/1.jpg").getPath();
        File postFile = new File(filename);
        p.setDocPath("/testfile/1.jpg");
        req.msgBody =  JSONObject.parseObject(JSONObject.toJSONString(p));
        HttpEntity entity =RestTemplateManager.buildEntity(url, JSONObject.toJSONString(req), postFile);
        String ret = httpclient.post(url,entity);
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());

        System.out.println(url);
    }


  @JTest
    @JTestClass.title("上传文档test0037_uploadPIC")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/template/document/upload")
    @JTestClass.exp("ok")
    public void test0038_uploadTemplate() throws UnsupportedEncodingException {
        String url = "http://localhost:80/rest/upload";

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "uploadTemplate";
        req.msgBody =  JSONObject.parseObject("{}");
        //req.msgBody.put("templateId",174);
        Mngr_Re_Document p=new Mngr_Re_Document();
        p.setName("1.jpg");
        p.setDocType(Dict_document.DOC_TYPE_XLS);
        p.setSaveMode(Dict_document.SAVEMODE_DB);
        p.setPicType("JPG");
        String filename=getClass().getResource("/testfile/1.jpg").getPath();
        File postFile = new File(filename);
        p.setDocPath("d:/testfile/1.jpg");
        //req.msgBody =  JSONObject.parseObject(JSONObject.toJSONString(p));
        req.msgBody.put("templateId",174);
        HttpEntity entity =RestTemplateManager.buildEntity(url, JSONObject.toJSONString(req), postFile);
        System.err.println("req="+JSONObject.toJSONString(req));
        String result = httpclient.post(url,entity);
        httpclient.checkStatusCode(202);
        System.out.println(url);
        System.out.println(result);
    }

    @JTest
    @JTestClass.title("test0039_modifyDocHeader")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
    @JTestClass.exp("ok")
    public void test0039_modifyReDocumentHeader() throws UnsupportedEncodingException {
        String url="http://localhost:80/rest/template";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "modifyReDocumentHeader";
        req.msgBody = JSONObject.parseObject("{}");
        TemplateDocumentHeader header=new TemplateDocumentHeader();
        header.setProjectId(0);
        header.setDocumentId(215);
        req.msgBody.put("documentId",205);
        req.msgBody.put("header",header);
        System.out.println(JSONObject.toJSONString(req));
        String ret = httpclient.post(url ,JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
    }

    @JTest
    @JTestClass.title("test0040_getReDocumentHeader")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0040_getReDocumentHeader() throws UnsupportedEncodingException {
        String url="http://localhost/rest/template";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "getReDocumentHeader";
        req.msgBody.put("documentId",205);
        System.out.println(JSONObject.toJSONString(req));
        String ret = httpclient.post(url ,JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        System.out.println(ret);
    }

    @JTest
    @JTestClass.title("test0041_selectProjConfig")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0041_selectProjConfig()     {
        String url="http://localhost/rest/template";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projConfig";
        req.cmd = "selectProjConfig";        ;

        System.out.println(JSONObject.toJSONString(req));
        String ret = httpclient.post(url ,JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONString());
        System.out.println("req:"+ MsgHandler.toJSONStringPretty(req));
        System.out.println(url);

    }
    @JTest
    @JTestClass.title("test0042_modifyDocTemplateState")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/template")
    @JTestClass.exp("ok")
    public void test0042_modifyDocTemplateState()     {
        String url="http://localhost/rest/template";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "repository";
        req.cmd = "modifyDocTemplateState";        ;
        req.msgBody.put("repositoryId",0);
        req.msgBody.put("workJobId",0);
        req.msgBody.put("templateId",363);
        req.msgBody.put("state",1);
        req.msgBody.put("docType",9404);

        System.out.println(JSONObject.toJSONString(req));
        String ret = httpclient.post(url ,JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONString());
        System.out.println("req:"+ MsgHandler.toJSONStringPretty(req));
        System.out.println(url);

    }

    @JTest
    @JTestClass.title("test0043_modifyReDocument")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
    @JTestClass.exp("ok")
    public void test0043_modifyReDocument() throws UnsupportedEncodingException {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "modifyReDocument";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", 123);
        TemplateDocumentModel templateDocumentModel = new TemplateDocumentModel();
        req.msgBody.put("document", templateDocumentModel);

        String url = "http://localhost/rest/template";
        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        MsgResponse resp=MsgResponse.parseResponse(ret);
        httpclient.checkStatusCode(200);
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        this.checkEQ(0,resp.getRetcode());
    }


    @JTest
    @JTestClass.title("test0044_selectDocTemplates")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
    @JTestClass.exp("ok")
    public void test0044_selectDocTemplates() throws UnsupportedEncodingException {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "selectDocTemplates";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("repositoryId", 0);
        req.msgBody.put("workJobId", 50);
        req.msgBody.put("docTypes", JSONArray.parseArray("[300,301,302,303,304]"));

        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        MsgResponse resp=MsgResponse.parseResponse(ret);
        httpclient.checkStatusCode(200);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        this.checkEQ(0,resp.getRetcode());
        System.out.println(url);
    }

    @JTest
    @JTestClass.title("test0044_selectDocTemplates")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0045_getDocTemplateList() throws UnsupportedEncodingException {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "repository";
        req.cmd = "getDocTemplateList";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("repositoryId", 14);
        req.msgBody.put("workJobId", 0);
        req.msgBody.put("docTypeArr", JSON.parseArray("[100,101,102]"));

        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        MsgResponse resp=MsgResponse.parseResponse(ret);
        httpclient.checkStatusCode(200);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        this.checkEQ(0,resp.getRetcode());
        System.out.println(url);
    }

    @JTest
    @JTestClass.title("TestTemplateGenManager.test0046_setUpRefAll")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0046_setUpRefAll() {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "repository";
        req.cmd = "setUpRefAll";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("repositoryId", 12).fluentPut("projectId", 0);

        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url);
        httpclient.checkStatusCode(200);
        this.checkEQ(0,resp.getRetcode());
        System.out.println(url);
    }
    //spFindAllTemplateByRepository
    @JTest
    @JTestClass.title("TestTemplateGenManager.test0047_spFindAllTemplateByRepository")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0047_spFindAllTemplateByRepository() {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "repository";
        req.cmd = "spFindAllTemplateByRepository";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("repositoryId", 18).fluentPut("projectId", 0);

        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url);
        httpclient.checkStatusCode(200);
        this.checkEQ(0,resp.getRetcode());
        System.out.println(url);
    }


    @JTest
    @JTestClass.title("TestTemplateGenManager.test0048_spFindAllTemplateByWorkjobType")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
    @JTestClass.exp("ok")
    public void test0048_spFindAllTemplateByWorkjobType() {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "workJobType";
        req.cmd = "spFindAllTemplateByWorkjobType";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("workjobType", 21).fluentPut("projectId", 0);

        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONStringPretty());
        System.out.println(url);
        System.out.println(resp.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        this.checkEQ(0, resp.getRetcode());

    }
    @JTest
    @JTestClass.title("upload图片 test0049_upload")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/document")
    @JTestClass.exp("ok")
    public void test0049_upload() throws IOException {
        String url_image = "http://mysql.kunlong.com/rest/project/image";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectImage";
        req.cmd = "upload";
        req.msgBody = JSONObject.parseObject("{}");

        Mngr_Re_Document p = new Mngr_Re_Document();
        p.setDocType(Dict_document.DOC_TYPE_RAR);
        p.setSaveMode(Dict_document.SAVEMODE_PATH);
        p.setName("/testfile/ini.json");
        String filename1 = getClass().getResource(p.getName()).getPath();
        File postFile = new File(filename1);
        req.msgBody = JSONObject.parseObject(YtbUtils.toJSONStringPretty(p));
        byte[] msgBody = Base64.getEncoder().encode(req.toJSONStringPretty().getBytes("UTF-8"));
        String url = url_image + "?msgBody=" + new String(msgBody);
        HttpEntity entity = buildEntity(url, req.toJSONStringPretty(), postFile);
        String ret = httpclient.post(url, entity);
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        System.out.println(req.toJSONString());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url);
    }


    //{"token":"f9e69d993249455e85bb44ac035b8486","reqtime":1555732874110,"seqno":1555732874110,"apiKey":
     //   "1555732828458","cmdtype":"templateDocument",
         //       "cmd":"getListStopAction","msgBody":{"templateId":495},"sign":"//admin.youtobon.com/rest/template"}
    @JTest
    @JTestClass.title("test0050_getListStopAction")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/template")
    @JTestClass.exp("ok")
    public void test0050_getListStopAction() throws IOException {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "getListStopAction";
        req.msgBody.fluentPut("templateId",495);

        String ret = httpclient.post(url , req.toJSONString(),"application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        System.out.println(req.toJSONString());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url );
    }

    public static void main(String[] args) {
        run(TestTemplateManager.class, 50);
    }

}

