package ytb.common.testcase.redis;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import org.springframework.data.redis.cache.RedisCacheManager;
import ytb.common.ManagerCommon.ITestYtb;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.service.impl.AppCtxtUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@JTestClass.author("leijm")
public class TestRedis extends ITestYtb {
	String url_base="http://localhost/rest";
	String url ="http://localhost/rest/service/demoModel";

	@Inject(filename = "node.xml", value = "httpclient")
	HttpClientNode httpclient;
	
	MsgRequest req = new MsgRequest();

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

	//@Autowired
	//private StringRedisTemplate stringRedisTemplate;

	@JTest
	@JTestClass.title("test0001_httprest1")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/test")
	@JTestClass.exp("ok")
	public void test0001_redis() {
		RedisCacheManager redisCacheManager = AppCtxtUtil.getBean(RedisCacheManager
				.class);

//		redisCacheManager.opsForValue().set("name", "guanguan");
//		String val = (String) redisCacheManager.getRedisTemplate().opsForValue().get("name");

		//checkEQ("guanguan", val);
	}

//	//https://github.com/baidu/uid-generator
//	@echo off
//	set path=D:\Java8.11\JDK\jre\bin
//	START "demo-project" "%path%\javaw" -jar demo-0.0.1-SNAPSHOT.jar
//			pause



	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IOException {

		run(TestRedis.class,1);

	}

}
		
		