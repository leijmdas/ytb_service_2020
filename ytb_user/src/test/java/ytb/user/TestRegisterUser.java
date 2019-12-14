package ytb.user;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;
import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class TestRegisterUser extends ITestImpl {
    String url_base="http://mysql.kunlong.com/rest/ytbuser";

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
    @JTestClass.title("新增公司收件地址")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test0001_registryCompanyUser() throws Exception {
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
        MsgResponse resp = MsgResponse.parseResponse(ret);

        checkEQ(0,resp.getRetcode());
        System.out.println("请求是："+req.toJSONStringPretty());
        System.out.println("响应是："+resp.toJSONStringPretty());

        System.out.println("请求是："+req.toJSONStringPretty());
        System.out.println("响应是："+ret);
        dbUser.clearSql().appendSql(" select 1 from user_login ");
        dbUser.appendSql(" where login_mobile='123' and user_type=2");
        dbUser.checkRecordNumber(1);
    }

    @JTest
    @JTestClass.title("新增公司收件地址")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test0002_registryPersonUser() throws Exception {
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
        MsgResponse resp = MsgResponse.parseResponse(ret);

        checkEQ(0,resp.getRetcode());
        System.out.println("请求是："+req.toJSONStringPretty());
        System.out.println("响应是："+resp.toJSONStringPretty());

        dbUser.clearSql().appendSql(" select 1 from user_login ");
        dbUser.appendSql(" where login_mobile='123' and user_type=1");
        dbUser.checkRecordNumber(1);
    }

    public static  void main(String args[]){
        run(TestRegisterUser.class,1);
    }

}
