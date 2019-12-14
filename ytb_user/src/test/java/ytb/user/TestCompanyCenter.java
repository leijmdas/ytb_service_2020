package ytb.user;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;
import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;
import ytb.common.RestMessage.MsgRequest;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Package: ytb_service.companycentertest
 * Author: ZCS
 * Date: Created in 2018/10/17 16:55
 */
public class TestCompanyCenter extends ITestImpl {
    String url_base="http://mysql.kunlong.com/rest/ytbuser";
    //String url_user="http://mysql.kunlong.com/rest/ytbuser";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    @Inject("ytb_user")
    JDbNode dbUser;
    @Inject("ytb_tasklog")
    JDbNode dbTasklog;

    MsgRequest req = new MsgRequest();
    String data;

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
    @JTestClass.title("查询公司员工（在职、离职）")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_001() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "getCompanyEmpList";
        req.msgBody =  JSONObject.parseObject("{\"page\":1,\"limit\":5,\"status\":1,\"companyId\":1002,\"nickName\":\"\"}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("查询公司工商资料")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_002() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "getCompanyInfoById";
        req.msgBody =  JSONObject.parseObject("{\"companyId\":1002}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("传的参数是："+data);
        System.out.println("响应的是："+json.get("msgBody"));
    }


    @JTest
    @JTestClass.title("设置公司理念")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_003() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "updateCompanyIdea";
        req.msgBody =  JSONObject.parseObject("{\"companyId\":1002,\"companyIdea\":\"知识\"}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("传的参数是："+data);
        System.out.println("响应的是："+json.get("msgBody"));
    }

    @JTest
    @JTestClass.title("设置公司学历占比")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_004() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "setCompanyEmpEdu";
        req.msgBody =  JSONObject.parseObject("{\"companyId\":1002,\"graduateNumber\":20,\"collegeNumber\":30,\"otherNumber\":50}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("传的参数是："+data);
        System.out.println("响应的是："+json.get("msgBody"));
    }


    @JTest
    @JTestClass.title("获取公司年收入")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_005() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "selectCompanyIncomeList";
        req.msgBody =  JSONObject.parseObject("{\"companyId\":1002,\"graduateNumber\":20,\"collegeNumber\":30,\"otherNumber\":50}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("传的参数是："+data);
        System.out.println("响应的是："+json.get("msgBody"));
    }

    @JTest
    @JTestClass.title("获取公司设备")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_006() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "getCompanyDeviceListById";
        req.msgBody =  JSONObject.parseObject("{\"companyId\":1002,\"graduateNumber\":20,\"collegeNumber\":30,\"otherNumber\":50}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("传的参数是："+data);
        System.out.println("响应的是："+json.get("msgBody"));
    }


    @JTest
    @JTestClass.title("设置公司设备")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_007() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "saveOrUpdateCompDevice";
        req.msgBody =  JSONObject.parseObject("{\"json\":{\"deviceName\":\"机电设备\",\"deviceBrand\":\"KNB\",\"deviceModel\":\"KOJK\",\"deviceNumber\":20,\"deviceId\":}}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("传的参数是："+data);
        System.out.println("响应的是："+json.get("msgBody"));
    }



    @JTest
    @JTestClass.title("删除员工")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_008() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "removeEmp";
        req.msgBody =  JSONObject.parseObject("{\"userId\":169,\"empId\":47}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("传的参数是："+data);
        System.out.println("响应的是："+json.get("msgBody"));
    }

    @JTest
    @JTestClass.title("查询公司员工在职、离职两种状态")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_009() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "getCompanyEmpListByParam";
        req.msgBody =  JSONObject.parseObject("{\"empType\":0,\"nickName\":\"\"}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("传的参数是："+data);
        System.out.println("响应的是："+json.get("msgBody"));
    }

    @JTest
    @JTestClass.title("新增公司收件地址")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_010_addCompanyAddress() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "addCompanyAddress";
        req.msgBody =  JSONObject.parseObject("{\"area\":\"fsdaf\",\"zipCode\":\"fsds\",\"address\":\"dass\",\"phone\":\"kgfhj\",\"name\":\"kfk\"}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("传的参数是："+data);
        System.out.println("响应的是："+json.get("msgBody"));
    }

    @JTest
    @JTestClass.title("新增公司收件地址")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_011registryCompanyUser() throws Exception {
        dbUser.clearSql().appendSql(" delete from user_login ");
        dbUser.appendSql(" where login_mobile=").appendSql("'123'");
        dbUser.appendSql(" and user_type=2");
        dbUser.execSql();
        dbUser.clearSql().appendSql(" delete from company_info ");
        dbUser.appendSql(" where company_name=").appendSql("'123'");
        dbUser.execSql();

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmd  = "company";
        req.cmdtype = "registry";

        TaskLogSmsCodeServiceImpl smsCodeService=new TaskLogSmsCodeServiceImpl();
        Tasklog_Sms_CodeModel sms_codeModel=new Tasklog_Sms_CodeModel();
        sms_codeModel.setPhone("123");
        sms_codeModel.setEndTime(new Date());
        sms_codeModel.setSmsCode("123");
        smsCodeService.deleteSmsCode("123");
        smsCodeService.addTaskLogSmsCode(sms_codeModel);

        req.msgBody.fluentPut("username","123");
        req.msgBody.fluentPut("password","123");
        req.msgBody.fluentPut("code","123");
        req.msgBody.fluentPut("companyName","123");
        req.msgBody.fluentPut("contacts","123");
        req.msgBody.fluentPut("phoneNumber","123");
        req.msgBody.fluentPut("email","123");
        req.msgBody.fluentPut("address","123");
        req.msgBody.fluentPut("licenseUrl","123123");
        req.msgBody.fluentPut("licenseCode","123");


        String ret = httpclient.post(url_base,req.toJSONString() , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("请求是："+req.toJSONStringPretty());
        System.out.println("响应是："+ret);

    }

    @JTest
    @JTestClass.title("新增公司收件地址")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_012registryPersonUser() throws Exception {
        dbUser.clearSql().appendSql(" delete from user_login ");
        dbUser.appendSql(" where login_mobile='123'");
        dbUser.appendSql(" and user_type=1");
        dbUser.execSql();
        dbUser.clearSql().appendSql(" delete from company_info ");
        dbUser.appendSql(" where company_name=").appendSql("'123'");
        dbUser.execSql();

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmd  = "user";
        req.cmdtype = "registry";

        TaskLogSmsCodeServiceImpl smsCodeService=new TaskLogSmsCodeServiceImpl();
        Tasklog_Sms_CodeModel sms_codeModel=new Tasklog_Sms_CodeModel();
        sms_codeModel.setPhone("123");
        sms_codeModel.setEndTime(new Date());
        sms_codeModel.setSmsCode("123");
        smsCodeService.deleteSmsCode("123");
        smsCodeService.addTaskLogSmsCode(sms_codeModel);

        req.msgBody.fluentPut("username","123");
        req.msgBody.fluentPut("password","123");
        req.msgBody.fluentPut("code","123");
        req.msgBody.fluentPut("companyName","123");
        req.msgBody.fluentPut("contacts","123");
        req.msgBody.fluentPut("phoneNumber","123");
        req.msgBody.fluentPut("email","123");
        req.msgBody.fluentPut("address","123");
        req.msgBody.fluentPut("licenseUrl","123123");
        req.msgBody.fluentPut("licenseCode","123");


        String ret = httpclient.post(url_base,req.toJSONString() , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println("请求是："+req.toJSONStringPretty());
        System.out.println("响应是："+ret);

    }

    public static  void main(String args[]){
        run(TestCompanyCenter.class,12);
    }

}
