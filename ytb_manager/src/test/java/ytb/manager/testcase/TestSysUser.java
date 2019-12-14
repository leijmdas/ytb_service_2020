package ytb.manager.testcase;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ytb.common.ytblog.YtbLog;
import ytb.manager.ManagerCommon.ManagerLogin;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;
import java.util.UUID;

@JTestClass.author("zengcsheng")
public class TestSysUser extends ITestImpl {
	private static final Logger logger = LoggerFactory.getLogger(TestSysUser.class);

	String url_base="http://mysql.kunlong.com/rest/sysuser";
	String url_context="http://127.0.0.1:10080/rest/context";
	@Inject(filename = "node.xml", value = "httpclient")
	HttpClientNode httpclient;
//	@JTestInject(filename = "node.xml", value = "httpclient")
//	HttpClientNode httpclient;

	ManagerLogin login = new ManagerLogin();
	String token = "d98c3b969dc34aaa92942c8a9c646f2a";
	int apiKey = 1111;

	String data;

	MsgRequest req = new MsgRequest();
	public void suiteSetUp() {
	
	}
	
	public void suiteTearDown() throws IOException {
	}

	@Override
	public void setUp() {
		req = login.defaultReq();
		token = login.login(req);
//		req.token = token;
//		req.setApiKey(login.getApiKey());
		logger.info(req.toJSONStringPretty());
	}
	
	@Override
	public void tearDown() {

	}
	
	@JTest
	@JTestClass.title("获取用户列表")
	@JTestClass.pre("")
	@JTestClass.step("url_context")
	@JTestClass.exp("ok")
	public void test_0001_getLoginSSO() {

		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "context";
		req.cmd = "getLogSso";

		String ret = httpclient.post(url_context, req.toJSONString(), "application/json");
		MsgResponse resp = MsgResponse.parseResponse(ret);
		YtbLog.logJtest("req", req);
		YtbLog.logJtest("resp", resp);
		httpclient.checkStatusCode(200);

		checkEQ(0, resp.getRetcode());
	}
	
	
	@JTest
	@JTestClass.title("新增用户信息")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_0002_addUser() {

		//req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "user";
		req.cmd = "addUserInfo";
		req.msgBody = JSONObject.parseObject("{\"userName\":\"客服AAA\",\"mobile\":\"12345678900\",\"password\":\"123456\",\"createBy\":1}");

		String ret = httpclient.post(url_base, req.toJSONString(), "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		
	}
	
	@JTest
	@JTestClass.title("删除用户")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_003() {

		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "user";
		req.cmd = "deleteUserById";
		req.msgBody = JSONObject.parseObject("{\"userId\":37}");
		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base, data, "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
		System.out.println(json);
	}


	@JTest
	@JTestClass.title("修改用户")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_004() {

		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "user";
		req.cmd = "updateUserInfo";
		req.msgBody = JSONObject.parseObject("{\"userName\":\"客服C\",\"mobile\":\"12345678900\",\"password\":\"654321\",\"createBy\":1,\"userId\":22}");

		data=new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
		System.out.println(json);
	}


	@JTest
	@JTestClass.title("获取用户所拥有的角色")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_005() {

		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "role";
		req.cmd = "getUserRoleList";
		req.msgBody = JSONObject.parseObject("{\"userId\":1}");

		data=new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		System.err.println(ret);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
		System.out.println(json);
	}

	@JTest
	@JTestClass.title("启用禁用账户")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_006() {

		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "user";
		req.cmd = "setStatus";
		req.msgBody = JSONObject.parseObject("{\"userId\":22,\"status\":2}");

		data=new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
		System.out.println(json);
	}


	@JTest
	@JTestClass.title("获取角色列表")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:80/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_007() {

		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "role";
		req.cmd = "getRoleList";
		req.msgBody = JSONObject.parseObject("{}");

		data=new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
		System.out.println(json);
	}

	@JTest
	@JTestClass.title("重置用户角色")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_008() {

		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "role";
		req.cmd = "saveUserRole";
		req.msgBody = JSONObject.parseObject("{\"roleId\":\"11,12,13\",\"userId\":1,\"createBy\":1}");

		data=new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}

	@JTest
	@JTestClass.title("获取角色列表")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_009() {
		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "role";
		req.cmd = "getRoleList";
		req.msgBody = JSONObject.parseObject("{}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}


	@JTest
	@JTestClass.title("新增角色信息")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_010() {
		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "role";
		req.cmd = "addRole";
		req.msgBody = JSONObject.parseObject("{\"roleName\":\"财务部\",\"desp\":\"管理财务\",\"createBy\":1}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}

	@JTest
	@JTestClass.title("获取用户的角色信息")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_011() {
		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "role";
		req.cmd = "getUserRoleList";
		req.msgBody = JSONObject.parseObject("{\"userId\":1}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}


	@JTest
	@JTestClass.title("删除角色")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_012() {
		req.token = null;
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "role";
		req.cmd = "deleteRoleById";
		req.msgBody = JSONObject.parseObject("{\"roleId\":1}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}

	@JTest
	@JTestClass.title("修改角色")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_013() {
		req.token = null;
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "role";
		req.cmd = "updateRoleInfo";
		req.msgBody = JSONObject.parseObject("{\"roleId\":1,\"roleName\":\"NBA\",\"createBy\":1}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}


	@JTest
	@JTestClass.title("查询菜单列表")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_014() {
		req.token = null;
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "menu";
		req.cmd = "getMenuList";
		req.msgBody = JSONObject.parseObject("{}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}

	@JTest
	@JTestClass.title("查询接口列表")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_015() {
		req.token = null;
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "menu";
		req.cmd = "getRestList";
		req.msgBody = JSONObject.parseObject("{}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}


	@JTest
	@JTestClass.title("添加菜单信息")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_016() {
		req.token = null;
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "menu";
		req.cmd = "addMenu";
		req.msgBody = JSONObject.parseObject("{}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}


	@JTest
	@JTestClass.title("添加接口信息")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_017() {
		req.token = null;
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "menu";
		req.cmd = "addRestList";
		req.msgBody = JSONObject.parseObject("{}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}


	@JTest
	@JTestClass.title("删除菜单")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_018() {
		req.token = null;
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "menu";
		req.cmd = "deleteMenuById";
		req.msgBody = JSONObject.parseObject("{}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}

	@JTest
	@JTestClass.title("查询菜单列表")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:80/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_019() {
		//req.token = null;
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "user";
		req.cmd = "login";
		req.msgBody = JSONObject.parseObject("{\"userName\":\"admin\",\"password\":\"123456\"}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}

	@JTest
	@JTestClass.title("查询菜单列表")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:80/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_020() {
		//req.token = null;
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "menu";
		req.cmd = "getMenuLeftList";
		req.msgBody = JSONObject.parseObject("{\"userName\":\"admin\",\"password\":\"123456\"}");

		data = new Gson().toJson(req).toString();

		String ret = httpclient.post(url_base,data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println("传的参数是："+data);
		System.out.println("响应的是："+json.get("msgBody"));
	}

	@JTest
	@JTestClass.title("test_021menu")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysuser")
	@JTestClass.exp("ok")
	public void test_021menu() {

		req.token = token;//UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "menu";
		req.cmd = "treeRestList";
		req.msgBody = JSONObject.parseObject("{\"userName\":\"admin\"}");
		data=new Gson().toJson(req).toString();
		String ret = httpclient.post(url_base,data , "application/json");
		System.err.println(ret);
		req.cmdtype = "menu";
		req.cmd = "treeMenuList";
		data = new Gson().toJson(req).toString();
		ret = httpclient.post(url_base, data, "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json = JSONObject.parseObject(ret);
		checkEQ(0, json.getInteger("retcode"));

	}
	public static class Ss{
		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
		public int compareTo(Ss t){
			return i-t.getI();
		}
		public int hasCode(){
			return i;
		}

		@Override
		public boolean equals(Object obj) {
			return i==((Ss)obj).getI();
		}

		int i;

	}

	public static void main(String[] args) {

		 run(TestSysUser.class,1);

	}

}
		
		