package ytb.common.testcase.flowsnake;

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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@JTestClass.author("leijm")
public class TestUid extends ITestYtb {
	String url_base="http://localhost/rest";
	String url ="http://localhost/rest/service/demoModel";

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
	@JTestClass.title("e")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com/rest/service")
	@JTestClass.exp("ok")
	public void test0001_uid() {

		 ApplicationContext ctxt = new ClassPathXmlApplicationContext
				("classpath:/uid/default-uid-spring.xml");
		ctxt = AppCtxtUtil.getApplicationContext();
		DefaultUidGenerator uidGenerator=ctxt.getBean("defaultUidGenerator",DefaultUidGenerator.class);
		System.out.println(uidGenerator.getUID());
		System.out.println(uidGenerator.getUID());
		System.out.println(uidGenerator.getUID());

	}
	@JTest
	@JTestClass.title("e")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com/rest/service")
	@JTestClass.exp("ok")
	public void test0002_uid() {

		ApplicationContext ctxt = new ClassPathXmlApplicationContext
				("classpath:/uid/cached-uid-spring.xml");
		ctxt = AppCtxtUtil.getApplicationContext();

		DefaultUidGenerator uidGenerator = ctxt.getBean("defaultUidGenerator",
				DefaultUidGenerator.class);
		System.out.println(uidGenerator.getUID());
		System.out.println(uidGenerator.getUID());
		System.out.println(uidGenerator.getUID());
		uidGenerator = ctxt.getBean("defaultUidGenerator",
				DefaultUidGenerator.class);
		System.out.println(uidGenerator.getUID());
		System.out.println(uidGenerator.getUID());

	}
	@JTest
	@JTestClass.title("e")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com/rest/service")
	@JTestClass.exp("ok")
	public void test0003_uid_utils() {


		System.out.println( YtbUtils.fsGetUID());
		System.out.println( YtbUtils.fsGetUID());
		System.out.println( YtbUtils.fsGetUID());

	}
	//https://www.cnblogs.com/yeyang/p/10226284.html
	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IOException {

		run(TestUid.class,3);

	}

}
		
		