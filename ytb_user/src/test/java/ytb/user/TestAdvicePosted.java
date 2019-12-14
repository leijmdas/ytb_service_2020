package ytb.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;

import java.io.IOException;

/**
 * Package: ytb.user
 * Author: ZCS
 * Company: 公司
 * Copyright: Copyright (c) 2018
 */
@JTestClass.author("zengcsheng")
public class TestAdvicePosted extends ITestImpl {
    String url_base = "http://mysql.kunlong.com:80/rest/ytbuser";
    String token = "551970aa6ad3473b9b7f2cd35131e856";

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

    @JTest
    @JTestClass.title("新增项目接单范围")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_1() {

        JSONArray jsonArray = new JSONArray();
        ScodeIdEntity scodeId = new ScodeIdEntity();
        scodeId.setScodeId(null);
        scodeId.setTypeId(89);
        scodeId.setUserId(124);
        jsonArray.add(scodeId);

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "addUserProjectScode";
        req.msgBody = JSONObject.parseObject("{\"json\":"+jsonArray+"}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("获取用户接单范围")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_2() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "getProjectScodeList";
        req.msgBody = JSONObject.parseObject("{\"userId\":124,\"companyId\":\"\"}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }



    @JTest
    @JTestClass.title("删除用户接单范围")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_3() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "deleteProjectScode";
        req.msgBody = JSONObject.parseObject("{\"scodeId\":1}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }



    @JTest
    @JTestClass.title("获取意向采购清单")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_4() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "getUserPurchase";
        req.msgBody = JSONObject.parseObject("{\"userId\":102,\"companyId\":1002}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }



    @JTest
    @JTestClass.title("新增意向采购清单")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_5() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "addUserPurchase";
        req.msgBody = JSONObject.parseObject("{\"userId\":102,\"companyId\":1002}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("删除意向采购清单")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_6() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "deleteUserPurchase";
        req.msgBody = JSONObject.parseObject("{\"purchaseId\":102}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("获取推广销售的清单")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_7() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "getUserSellList";
        req.msgBody = JSONObject.parseObject("{\"userId\":124,\"companyId\":0}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("删除推广销售清单")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test_8() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "deleteUserSell";
        req.msgBody = JSONObject.parseObject("{\"sellId\":124}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("获取广告贴")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0015_userAdvices() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "getUserAdvicesInfo";
        req.msgBody = JSONObject.parseObject("{\"userId\":124,\"companyId\":\"\"}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }

    public static void main(String[] args) throws IOException {

        run(TestUserCenter.class, "test_2");


    }

    class ScodeIdEntity{
       private Integer scodeId = 0;
       private Integer typeId = 0;
       private  Integer userId =0;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getScodeId() {
            return scodeId;
        }

        public void setScodeId(Integer scodeId) {
            this.scodeId = scodeId;
        }

        public Integer getTypeId() {
            return typeId;
        }

        public void setTypeId(Integer typeId) {
            this.typeId = typeId;
        }
    }

}
