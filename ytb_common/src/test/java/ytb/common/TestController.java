package ytb.common;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ytb.common.ManagerCommon.ITestYtb;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.service.impl.AppCtxtUtil;
import ytb.common.utils.YtbUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@JTestClass.author("leijm")
public class TestController extends ITestYtb {
	String url_base="http://localhost:880/hld";

	String url ="http://localhost/service/selectList";

	@Inject(filename = "node.xml", value = "httpclient")
	HttpClientNode httpclient;

	MsgRequest req = new MsgRequest();
	String data;
//	public void suiteSetUp() {
//
//	}
//
//	public void suiteTearDown() throws IOException {
//	}

	@Override
	public void setUp() {
		req.token = UUID.randomUUID().toString();
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

		req.seqno = System.currentTimeMillis();
		req.cmdtype = "projectType";
		req.cmd = "getProjectTypeList";
		req.msgBody = JSONObject.parseObject("{\"x\":1}");

	}

	@Override
	public void tearDown() {
	}


	@JTest
	@JTestClass.title("test0001_c")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/test")
	@JTestClass.exp("ok")
	public void test0001_selectList() {

		String ret = httpclient.post(url_base+"/select",req.toJSONString() , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		System.out.println(ret);
		checkEQ(0,json.getInteger("retcode"));
	}



	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IOException {

	  run(TestController.class,1);

	}

}
		
		