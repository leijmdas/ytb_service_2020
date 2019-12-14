package ytb.log;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.log.notify.service.impl.TaskLogTimerImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;
import java.util.UUID;

@JTestClass.author("leijm")
public class TestRest extends ITestImpl {
	String url_base = "http://localhost:80/rest/taskLog_task";
	String token = "c1f119fd8ef24954b2485b6f4625779a";

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
		req.token = token;
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "projectType";
		req.cmd = "getProjectTypeList";
		req.msgBody = JSONObject.parseObject("{\"x\":1}");
		data=new Gson().toJson(req).toString();
		System.out.println(data);
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
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "smslog";
		req.cmd = "sendSmsCode";
		req.msgBody = JSONObject.parseObject("{\"phone\":18772103675}");
		data=new Gson().toJson(req).toString();
		String ret = httpclient.post("http://localhost:80/rest/smsLog_sms",data , "application/json");
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

		String ret = httpclient.post("http://localhost:80/rest/template", data, "application/json");
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

		String ret = httpclient.post("http://localhost:8080/rest/template",data, "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println(data);
		System.out.println(json.get("msgBody"));
		System.out.println(json);
	}

	@JTest
	@JTestClass.title("test0032_getTaskLogTaskList")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test0032_getTaskLogTaskList() {

		req.token = token;
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "tasklog";
		req.cmd = "getTaskLogTaskList";
		req.msgBody = JSONObject.parseObject("{}");
		req.msgBody.put("object_type",12);
		req.msgBody.put("userId",1);
		data=req.toJSONString();
		String ret = httpclient.post(url_base,data, "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println(data);
		System.out.println(json.get("msgBody"));
	}


	@JTest
	@JTestClass.title("查询项目分类列表")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
	@JTestClass.exp("ok")
	public void test0033_rest_smsCode() {

		req.token = token;
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "smslog";
		req.cmd = "sendSmsCode";
		req.msgBody = JSONObject.parseObject("{\"phone\":1352881802}");

		String url = "http://project.youtobon.com/rest/smsLog_sms";
		String ret = httpclient.post(url,req.toJSONString(), "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
	}
	@JTest
	@JTestClass.title("test0034_restexeNode")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/template")
	@JTestClass.exp("ok")
	public void test0034_restexeNode() {

		req.token = token;
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "tasklogTimer";
		req.cmd = "exeOnceTaskLogTimer";
		req.msgBody = JSONObject.parseObject("{ }");
		req.msgBody.put("taskId",10);

		//String ret = httpclient.post(url_base, req.toJSONString(), "application/json");
		//httpclient.checkStatusCode(200);
		//JSONObject json = JSONObject.parseObject(ret);
		//checkEQ(0, json.getInteger("retcode"));
		//System.out.println(req.toJSONStringPretty());
		String ret = new TaskLogTimerImpl().exeOnceTaskLogTimer(req, 10);
		System.out.println(JSONObject.parseObject(ret, MsgResponse.class).toJSONStringPretty());
	}


	public static void main(String[] args) {
		 run(TestRest.class,34);

	}
}
		
		