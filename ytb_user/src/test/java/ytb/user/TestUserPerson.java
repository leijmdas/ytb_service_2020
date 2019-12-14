package ytb.user;


import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import org.apache.commons.codec.digest.DigestUtils;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;

@JTestClass.author("leijm")
public class TestUserPerson extends ITestImpl {
	String url_base = "http://mysql.kunlong.com/rest/ytbuser";
	String url_base1 = "http://mysql.kunlong.com/rest/context";

	int userid = 200;
	String token = "c1f119fd8ef24954b2485b6f4625779a";
	String token_cmpny = "e10adc3949ba59abbe56e057f20f883e";
	String username_mobile_user = "13612912836";
	String username_mobile_cmpny = "15621420926";
	String companyName = "迪士尼";

	@Inject(filename = "node.xml", value = "httpclient")
	HttpClientNode httpclient;
	MsgRequest req = new MsgRequest();

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
	@JTestClass.title("test0001_getUserLoginInfo 测试前台用户权限")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
	@JTestClass.exp("ok")
	public void test0001_getUserLoginInfo() {
		 String url_base = "http://localhost/rest/context";
		req.token = token;
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "context";
		req.cmd = "getLogSso";
		req.msgBody = JSONObject.parseObject("{}");
		req.msgBody.put("userid", userid);

		String ret = httpclient.post(url_base, req.toJSONString(), "application/json");
		System.out.println(req.toJSONStringPretty());
		System.out.println(url_base);
		httpclient.checkStatusCode(200);
		MsgResponse resp = MsgResponse.parseResponse(ret);
		checkEQ(0, resp.getRetcode());
	}

	@JTest
	@JTestClass.title("user登陆test0002_userLogin")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/ytbuser")
	@JTestClass.exp("ok")
	public void test0002_userLogin() {

		req.token = "";
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "userLogin";
		req.cmd = "login";
		req.msgBody = JSONObject.parseObject("{}");
		req.msgBody.put("username",username_mobile_user);
		req.msgBody.put("companyName",username_mobile_user);

		req.msgBody.put("mode","password");
		String md5Password = DigestUtils.md5Hex("123456");
		req.msgBody.put("password",md5Password);

		String ret = httpclient.post("http://mysql.kunlong.com/rest/ytbuser",req.toJSONString(), "application/json");
		httpclient.checkStatusCode(200);
		MsgResponse resp=MsgResponse.parseResponse(ret);
		checkEQ(0,resp.getRetcode());
		System.out.println(req.toJSONString());
		System.out.println(resp.toJSONStringPretty());
	}

	@JTest
	@JTestClass.title("test0003_CompanyEmpLogin")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com/rest/ytbuser")
	@JTestClass.exp("ok")
	public void test0003_CompanyEmpLogin() {

		req.token = "";
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "companyLogin";
		req.cmd = "login";
		req.msgBody = JSONObject.parseObject("{}");
		req.msgBody.put("username",username_mobile_cmpny);
		req.msgBody.put("companyName",companyName);
		req.msgBody.put("mode","password");
		String md5Password = DigestUtils.md5Hex("000000");
		req.msgBody.put("password",md5Password);
		String ret = httpclient.post(url_base,req.toJSONString(),"application/json");
		httpclient.checkStatusCode(200);
		MsgResponse resp=MsgResponse.parseResponse(ret);
		checkEQ(0,resp.getRetcode());
		System.out.println(req.toJSONStringPretty());
		System.out.println(resp.toJSONStringPretty());

	}

	@JTest
	@JTestClass.title("test0008_logout")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/context")
	@JTestClass.exp("ok")
	public void test0004_logout() {
		req.token = "";
		req.reqtime = System.currentTimeMillis();
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "context";
		req.cmd = "logout";
		req.msgBody = JSONObject.parseObject("{}");
		req.msgBody.put("username",username_mobile_cmpny);
		String url="http://localhost/rest/context";

		String ret = httpclient.post(url,req.toJSONString() , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);

		checkEQ(0,json.getInteger("retcode"));

	}

	public static void main(String[] args) {

		run(TestUserPerson.class,2);
	}


}



		