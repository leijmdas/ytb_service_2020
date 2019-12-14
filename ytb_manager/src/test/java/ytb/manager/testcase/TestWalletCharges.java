package ytb.manager.testcase;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;

import java.io.IOException;

@JTestClass.author("zengcsheng")
public class TestWalletCharges extends ITestImpl {
    ///String url_base="http://localhost:80/rest/sysuser";
    String url_base = "http://admin.youtobon.com/rest/charges";
    String token = "8cd0571369f94360badfbd776e7e3cf1";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;


    MsgRequest req = new MsgRequest();
    String data;

    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {
    }


    @JTest
    @JTestClass.title("DistArea-getDistArea地区表 查询")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0001_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "DistArea";
        req.cmd = "getDistArea";
        req.msgBody = JSONObject.parseObject("{}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("DistArea-deleteDistArea地区表 删除")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0002_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "DistArea";
        req.cmd = "deleteDistArea";
        req.msgBody = JSONObject.parseObject("{areaId: 6}}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("DistArea-updateDistArea地区表 修改")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0003_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "DistArea";
        req.cmd = "updateDistArea";
        req.msgBody = JSONObject.parseObject("{  'depthType': 2, 'areaId': 4, 'code': '2','name': '2','parentId': 2}}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("DistArea-insertDistArea地区表 添加")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0004_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "DistArea";
        req.cmd = "insertDistArea";
        req.msgBody = JSONObject.parseObject("{'depthType': 1,'code': '1','name': '1','parentId': 1}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("DistAreaSalary-getDistAreaSalary 地区最低工资表 查询")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0005_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "DistAreaSalary";
        req.cmd = "getDistAreaSalary";
        req.msgBody = JSONObject.parseObject("{}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("DistAreaSalary-updateDistAreaSalary 地区最低工资表 添加")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0006_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "DistAreaSalary";
        req.cmd = "updateDistAreaSalary";
        req.msgBody = JSONObject.parseObject("{'createBy': 2,'versionId': '2','areaId': 2,'auditBy': '2','salary': 2,'salaryId': 2,'status': 1}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("DistAreaSalary-deleteDistAreaSalary地区最低工资表 删除")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0007_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "DistAreaSalary";
        req.cmd = "deleteDistAreaSalary";
        req.msgBody = JSONObject.parseObject("{    'salaryId': 8,}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("DistAreaSalary-insertDistAreaSalary 地区最低工资表 添加")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0008_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "DistAreaSalary";
        req.cmd = "insertDistAreaSalary";
        req.msgBody = JSONObject.parseObject("{'createBy':3,'versionId':'3','areaId':3,'auditBy':'3','salary':7,'status':1}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }



    @JTest
    @JTestClass.title("serviceFee-getServiceFee 项目服务费标准 查询")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0009_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "serviceFee";
        req.cmd = "getServiceFee";
        req.msgBody = JSONObject.parseObject("{}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("serviceFee-insertServiceFee 项目服务费标准 添加")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0010_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "serviceFee";
        req.cmd = "insertServiceFee";
        req.msgBody = JSONObject.parseObject("{'projectType':'1','upperLimit':1,'lowerLimit':1,'feeRate':1.00}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("serviceFee-deleteServiceFee 项目服务费标准 删除")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0011_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "serviceFee";
        req.cmd = "deleteServiceFee";
        req.msgBody = JSONObject.parseObject("{feeId:4}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("serviceFee-updateServiceFee 项目服务费标准 修改")
    @JTestClass.pre("")
    @JTestClass.step("post http://admin.youtobon.com/rest/charges")
    @JTestClass.exp("ok")
    public void test0012_() {

        req.token = "33cc4d29d6c84ee6b2331362e2b6418f";
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "serviceFee";
        req.cmd = "updateServiceFee";
        req.msgBody = JSONObject.parseObject("{'projectType':'2','upperLimit':2,'lowerLimit':2,'feeId':7,'feeRate':2.00}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post("http://admin.youtobon.com/rest/charges", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }








    public static void main(String[] args) {

        run(TestWalletCharges.class, "test_001");

    }
}
		
		