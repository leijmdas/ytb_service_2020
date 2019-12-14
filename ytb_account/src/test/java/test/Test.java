package test;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;

import java.io.IOException;
import java.util.UUID;

/**
 * Package: ytb_service.usercentertest
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2018/9/26 19:56
 */
@JTestClass.author("zengcsheng")
public class Test extends ITestImpl {
    String url_base = "http://mysql.kunlong.com:8080/rest/ytbuser";

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

    }

    @Override
    public void tearDown() {

    }

    /*-------------------------------userInner-------------------------------------*/
    @JTest
    @JTestClass.title("accountUserInners-newUserInnerInfo 新增账户")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0001_newUserInnerInfo() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userInner";
        req.cmd = "newUserInnerInfo";
        req.msgBody = JSONObject.parseObject("{\"token\":\"33cc4d29d6c84ee6b2331362e2b6418f\",\"reqtime\":1536745347946,\"seqno\":1536745347946,\"cmdtype\":\"recharge\",\"cmd\":\"accountIncrease\",\"msgBody\":\"{balance:985.99}\"}");
        req.token = "d22b358171d04d879159a4d5b2a369c2";
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/wallet", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("userInner-accountInfo 查询基础信息")
    @JTestClass.pre("")
    @JTestClass.step("http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0002_userInnerAccountInfo() {
        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userInner";
        req.cmd = "accountInfo";
        req.msgBody = JSONObject.parseObject("{}");
        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/wallet", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    /*----------------------------------------- userOut---------------------------------------------------------------------*/

    /*userOut*/

    @JTest
    @JTestClass.title("userOut-newUserOutInfo新增银行卡信息")
    @JTestClass.pre("")
    @JTestClass.step("http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0003_newUserOutInfo() {
        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userOut";
        req.cmd = "accountInfo";
        req.msgBody = JSONObject.parseObject("{\"isDefault\": 1,      \"accountNo\": \" cchua\",      \"accountType\": 1,      \"branchName\": \"深圳支行\",      \"bankName\": \"中国银行\",      \"mobileNo\": 135,      \"userId\": 17,      \"status\": 0 }");
        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/wallet", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("userOut-accountInfo 查询用户基本信息 以及有多少张银行卡")
    @JTestClass.pre("")
    @JTestClass.step("http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0004_userOutAccountInfo() {
        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userOut";
        req.cmd = "accountInfo";
        req.msgBody = JSONObject.parseObject("{}");
        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/wallet", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("userOut-newUserOutInfo删除其中一张银行卡")
    @JTestClass.pre("")
    @JTestClass.step("http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0005_deleteAccountInfo() {
        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userOut";
        req.cmd = "deleteAccountInfo";
        req.msgBody = JSONObject.parseObject("{\"outId\":12}");
        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/wallet", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }





    /*-----------------------------------userDetail----------------------------------------------------*/


    @JTest
    @JTestClass.title("userDetail-accountDetailInfo 对账单流水 查询")
    @JTestClass.pre("")
    @JTestClass.step("http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0006_accountDetailInfo() {
        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userDetail";
        req.cmd = "accountDetailInfo";
        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.msgBody = JSONObject.parseObject("{}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/wallet", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("userDetail - accountDetailByPage")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0007_weChatNotify() {
        data = new Gson().toJson("{\\\"token\\\":\\\"33cc4d29d6c84ee6b2331362e2b6418f\\\",\\\"reqtime\\\":1536745347946,\\\"seqno\\\":1536745347946,\\\"cmdtype\\\":\\\"pfOut\\\",\\\"cmd\\\":\\\"prohibitOutInfo\\\",\\\"msgBody\\\":{\\\"pfOutId\\\":2,\\\"status\\\":1}}").toString();

        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/wallet", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    public static void main(String[] args) {

        run(Test.class, "test00013_weChatNotify");

    }
}
