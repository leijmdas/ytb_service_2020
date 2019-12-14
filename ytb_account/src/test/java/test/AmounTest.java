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
public class AmounTest extends ITestImpl {
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


    /*--------------------------------------------------------------------------------------------------------------*/


    @JTest
    @JTestClass.title("模拟支付")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0001_accountIncrease() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "recharge";
        req.cmd = "accountIncrease";
        req.msgBody = JSONObject.parseObject("{\"token\":\"33cc4d29d6c84ee6b2331362e2b6418f\",\"reqtime\":1536745347946,\"seqno\":1536745347946,\"cmdtype\":\"recharge\",\"cmd\":\"accountIncrease\",\"msgBody\":\"{balance:985.99}\"}");
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
    @JTestClass.title("模拟微信回调")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/weChatNotify")
    @JTestClass.exp("ok")
    public void test0002_weChatNotify() {
        data = new Gson().toJson("{\"outTradeNo\": \"26\", \"totalFee\": \"98599\" }").toString();

        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/weChatNotify", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("pfInner-accountInnerInfo ZHX虚拟账户 入账")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0003_weChatNotify() {
        data = new Gson().toJson("{\\\"token\\\":\\\"233ffb7c2aa84950a56726470386d648\\\",\\\"reqtime\\\":1536745347946,\\\"seqno\\\":1536745347946,\\\"cmdtype\\\":\\\"pfDetail\\\",\\\"cmd\\\":\\\"newRecordIn\\\",\\\"msgBody\\\":{\\\"pfInnerId\\\":1,\\\"balance\\\":2.1,\\\"tradeType\\\":1,\\\"tradeSubtype\\\":1}}" +
                "    ").toString();

        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/wallet", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("pfInner-newRecordOut ZHX虚拟账户 出账")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0004_weChatNotify() {
        data = new Gson().toJson("{\"token\":\"233ffb7c2aa84950a56726470386d648\",\"reqtime\":1536745347946,\"seqno\":1536745347946,\"cmdtype\":\"pfDetail\",\"cmd\":\"newRecordOut\",\"msgBody\":{\"pfInnerId\":1,\"balance\":2.1,\"tradeType\":1,\"tradeSubtype\":1}}").toString();

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
    public void test0005_weChatNotify() {
        data = new Gson().toJson("{\\\"token\\\":\\\"e6165c13d1024e04a480716ab853dabd\\\",\\\"reqtime\\\":1536745347946,\\\"seqno\\\":1536745347946,\\\"cmdtype\\\":\\\"userDetail\\\",\\\"cmd\\\":\\\"accountDetailByPage\\\",\\\"msgBody\\\":{\\\"pageSize\\\":\\\"2\\\",\\\"currPage\\\":\\\"2 \\\",\\\"orderBy\\\":\\\"asc\\\",\\\"toOrder\\\":\\\"trade_id\\\"}}").toString();

        String ret = httpclient.post("http://mysql.kunlong.com:8080/rest/wallet", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title("提现审核")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0006_newUserInnerInfo() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userInner";
        req.cmd = "withdrawCash";
        req.msgBody = JSONObject.parseObject("{\"token\":\"33cc4d29d6c84ee6b2331362e2b6418f\"" +
                ",\"reqtime\":1536745347946,\"seqno\":1536745347946,\"cmdtype\":\"userInner\",\"cmd\":\"withdrawCash\",\"msgBody\":\" {balance:1,tradeSubtype:2,outId:146,\"password\":\"e10adc3949ba59abbe56e057f20f883e\"} \"}");
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
    @JTestClass.title("转账")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/wallet")
    @JTestClass.exp("ok")
    public void test0007_transfer() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "transaction";
        req.cmd = "transfer";
        req.msgBody = JSONObject.parseObject("{\"token\":\"33cc4d29d6c84ee6b2331362e2b6418f\"" +
                ",\"reqtime\":1536745347946,\"seqno\":1536745347946,\"cmdtype\":\"userInner\",\"cmd\":\"withdrawCash\",\"msgBody\":\" {\n" +
                "\t\\\"inAcId\\\": 29,\n" +
                "\t\\\"balance\\\": 11.11\n" +
                "} \"}");
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


    public static void main(String[] args) {

        run(AmounTest.class, "test00013_weChatNotify");

    }
}
