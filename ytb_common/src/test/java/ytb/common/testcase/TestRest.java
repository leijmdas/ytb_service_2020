package ytb.common.testcase;

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
//import com.jtest.utility.TestLog;
import com.jtest.utility.testlog.TestLog;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ytb.common.ManagerCommon.ITestYtb;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.basic.config.dao.ConfigMapper;
import ytb.common.context.service.impl.AppCtxtUtil;
import ytb.common.utils.YtbUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;

@JTestClass.author("leijm")
public class TestRest extends ITestYtb {
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
	@JTestClass.title("test0001_httprest1")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/rest/test")
	@JTestClass.exp("ok")
	public void test0001_httprestgetConfig() {
		String url_base="http://localhost";

		String ret = httpclient.post(url_base+"/service/getConfig",req.toJSONString() , "application/json");
		httpclient.checkStatusCode(200);
		JSONArray json=JSONObject.parseArray(ret);
		TestLog.log(YtbUtils.toJSONStringSkipNull(json));
		//checkEQ(0,json.getInteger("retcode"));
	}
	
	
	@JTest
	@JTestClass.title("test0002_httprest2")
	@JTestClass.pre("")
	@JTestClass.step("post http://localhost:8080/rest/common")
	@JTestClass.exp("ok")
	public void test0002_httprest2() {
 
		String ret = httpclient.post(url_base+ "/mngr", data, "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		
	}
	
	@JTest
	@JTestClass.title("test0003_rest_template")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/WebMngr/rest/template")
	@JTestClass.exp("ok")
	public void test0003_rest_template() {
		
		String ret = httpclient.post("http://localhost:8080/WebMngr/rest/mngr",data , "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json=JSONObject.parseObject(ret);
		checkEQ(0,json.getInteger("retcode"));
		System.out.println(data);
		System.out.println(json.get("msgBody"));
		System.out.println(json);
	}
	
	@JTest
	@JTestClass.title("test0004_rest_mngr")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com:8080/WebMngr/rest/common")
	@JTestClass.exp("ok")
	public void test0004_rest_mngr() {

		String ret = httpclient.post("http://localhost:80/WebMngr/rest/mngr", data, "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json = JSONObject.parseObject(ret);
		checkEQ(0, json.getInteger("retcode"));
		System.out.println(data);
		System.out.println(json.get("msgBody"));
		System.out.println(json);
	}

	public int addGroupUser(int userId, int groupId, JSONArray groupUserIdArr){
		System.out.println(userId);
		System.out.println(groupId);
		System.out.println(JSONArray.toJSONString(groupUserIdArr));

		return groupId;
	}

	@JTest
	@JTestClass.title("e")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com/rest/service")
	@JTestClass.exp("ok")
	public void test0005_restDemo() {
		String url ="http://localhost/rest/service/demoModel";
		req.cmdtype = "student";
		req.cmd = "select";

		String ret = httpclient.post(url, req.toJSONString(), "application/json");
		httpclient.checkStatusCode(200);
		JSONObject json = JSONObject.parseObject(ret);
		checkEQ(0, json.getInteger("retcode"));
		System.out.println(req.toJSONString());
		System.out.println(json.get("msgBody"));
	}
	@JTest
	@JTestClass.title("e")
	@JTestClass.pre("")
	@JTestClass.step("post http://mysql.kunlong.com/rest/service")
	@JTestClass.exp("ok")
	public void test0006_uid() {

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
	public void test0007_uid() {

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
	public void test0008_uid_utils() {


		System.out.println( YtbUtils.fsGetUID());
		System.out.println( YtbUtils.fsGetUID());
		System.out.println( YtbUtils.fsGetUID());
		System.out.println( YtbUtils.getObjectId());

	}
	//create table user_hp2(id bigint,name varchar(64)) partition by hash(id)
	//partitions 3;
	static LocalDate periodFirstDay(int periodId) {
		String p = String.valueOf(periodId);
		LocalDate localDate = setYearMonthFirstDay(Integer.valueOf(p.substring(0, 4)), Integer.valueOf(p.substring(4, 6)));
		return localDate;
	}

	static LocalDate periodFinishDay(int periodId) {
		String p = String.valueOf(periodId);
		LocalDate localDate = setYearMonthFirstDay(Integer.valueOf(p.substring(0, 4)), Integer.valueOf(p.substring(4, 6)));
		return localDate.plusMonths(1).minusDays(1);
	}

	static LocalDate setYearMonthFirstDay(int year, int month) {

		LocalDate localDate = LocalDate.now().withYear(year);
		return localDate.withMonth(month).withDayOfMonth(1);
	}
	static LocalDate lastYearMonth() {

		return LocalDate.now().minusMonths(1);

	}


	public static void writeExcelOneSheetOnceWrite() throws IOException {

		// 生成EXCEL并指定输出路径
		OutputStream out = new FileOutputStream("E:\\withoutHead1.xlsx");
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

		// 设置SHEET
		Sheet sheet = new Sheet(1, 0);
		sheet.setSheetName("sheet1");

		// 设置标题
		Table table = new Table(1);
		List<List<String>> titles = new ArrayList<List<String>>();
		titles.add(Arrays.asList("用户ID"));
		titles.add(Arrays.asList("名称"));
		titles.add(Arrays.asList("年龄"));
		titles.add(Arrays.asList("生日"));
		table.setHead(titles);

		// 查询数据导出即可 比如说一次性总共查询出100条数据
		List<List<String>> userList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), new Date().toString()));
		}

		writer.write0(userList, sheet, table);
		writer.finish();
	}


	static class MyCls {
		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

		int a;
		int b;

		public String getMERCHANTID() {
			return MERCHANTID;
		}

		public void setMERCHANTID(String MERCHANTID) {
			this.MERCHANTID = MERCHANTID;
		}

		public String getORDERID() {
			return ORDERID;
		}

		public void setORDERID(String ORDERID) {
			this.ORDERID = ORDERID;
		}

		String MERCHANTID; //商户代码 CHAR(15) Y 由建行统一分配
		String ORDERID;
		// POSID 商户柜台代 码 CHAR(9) Y 由建行统一分配， BRANCHID 分行代码 CHAR(9) Y 由建行统一指定 ORDERID 定单号 CHAR(30) Y 由商户提供，最长 30 位 PAYMENT 付款金额 NUMBER(16,2) Y 由商户提供，按实际金额给 出 CURCODE 币种 CHAR(2) Y 缺省为 01－人民币 （只支持人民币支付） REMARK1 备注 1 CHAR(30) N 网银不处理，直接传到城综 网 REMARK2

		Map<String ,Object> model2Map(){

			Map map = JSONObject.parseObject(this.toString(), Map.class);
			return map;
		}

		<T> T  map2Model(Map<String,Object> map,Class<T> cls){

			return JSONObject.parseObject(JSONObject.toJSONString(map), cls);

		}

		public String toString(){
			return JSONObject.toJSONString(this);
		}

	}

//	CREATE TABLE WORKER_NODE
//			(
//					ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
//					HOST_NAME VARCHAR(64) NOT NULL COMMENT 'host name',
//	PORT VARCHAR(64) NOT NULL COMMENT 'port',
//	TYPE INT NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
//	LAUNCH_DATE DATE NOT NULL COMMENT 'launch date',
//	MODIFIED TIMESTAMP NOT NULL COMMENT 'modified time',
//	CREATED TIMESTAMP NOT NULL COMMENT 'created time',
//	PRIMARY KEY(ID)
//)
//	COMMENT='DB WorkerID Assigner for UID Generator',ENGINE = INNODB

	//https://www.cnblogs.com/yeyang/p/10226284.html
	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IOException {

		run(TestRest.class,1);
		//ModelFactory.build("work_group_member");
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(new Date()); //日期-1个月
//		calendar.set(Calendar.YEAR, 2019);
//		calendar.set(Calendar.MONTH, 6);
//		 calendar.add(Calendar.MONTH,  -2);
//		System.out.println(calendar.getTime());
//		System.out.println(new Date());
//		java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
//		String startDate = dateFormat.format(calendar.getTime());
//		System.out.println(startDate);
//		LocalDate localDate = LocalDate.now();
//		localDate=localDate.withYear(2019);
//		localDate=localDate.withMonth(6);
//		localDate=localDate.withDayOfMonth(1);
//		localDate=localDate.minusMonths(1);
//		System.out.println("201905" );
//		System.err.println("201905".substring(0, 4));
//		System.out.println("201905".substring( 4, 6));
//		System.out.println(periodFirstDay(201905));
//		System.out.println(periodFinishDay(201905));
//		java.text.DateFormat dateFormat1  = new java.text.SimpleDateFormat("yyyyMM");
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//		System.out.println(dtf.format (lastYearMonth()));
//		writeExcelOneSheetOnceWrite();
//		java.util.Date date = new java.util.Date();
//		System.out.print(date);
//		MyCls mycls = new MyCls();
//		String s = JSONObject.toJSONString(mycls);
//		MyCls m = JSONObject.parseObject(s, MyCls.class);
//		m.setMERCHANTID("11");
//		m.setORDERID("22222");
//		System.out.println(m);
//		Map map = JSONObject.parseObject(s, Map.class);
//		System.out.println(map);
//		MyCls cls=mycls.map2Model(map,MyCls.class);
//		System.out.println(cls);
//		String invitationCode = StringUtils.upperCase(RandomStringUtils.random(6, true, true));
//		System.out.println(invitationCode);
//		Map<Integer,String> m=new HashMap<>();
//		m.put(1,"22");
//		m.put(100,"33");
//		m.keySet().removeIf(
//				new Predicate<Integer>() {
//					@Override
//					public boolean test(Integer k) {
//						return k>2;
//					}
//				}
//		);
//		System.out.println(m);
	}

}
		
		