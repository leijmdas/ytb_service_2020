package ytb.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;
import java.util.UUID;

/**
 * Package: ytb_service.usercentertest
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2018/9/26 19:56
 */
@JTestClass.author("hj")
public class TestSearchPerson extends ITestImpl {
    String url_base = "http://mysql.kunlong.com:80/rest/ytbuser";
    String token = "510c781003e34cc99fa916ebeba32cc4";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    MsgRequest req = new MsgRequest();
    String data;

    @JTest
    @JTestClass.title("获取专业标签")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest")
    @JTestClass.exp("ok")
    public void test0001_tag() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "searchPerson";
        req.cmd = "searchTag";
        req.msgBody = JSONObject.parseObject("{parentId:0}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("获取接单范围")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest")
    @JTestClass.exp("ok")
    public void test0002_scope() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "searchPerson";
        req.cmd = "searchScope";
        req.msgBody = JSONObject.parseObject("{parentId:0}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("人才搜索")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest")
    @JTestClass.exp("ok")
    public void test0003_page() {
        req.token = "d94bec94e53c4871a48ee22faef8f7d0";
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "searchPerson";
        req.cmd = "searchPagePerson";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("pageSize", 10);
        req.msgBody.put("currPage", 1);

        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));

        data = JSON.toJSONString(req);

        String ret = httpclient.post(url_base, data, "application/json");

        MsgResponse msgResponse = MsgResponse.parseResponse(ret);
        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("搜索")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest")
    @JTestClass.exp("ok")
    public void test0004_userInfo() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "searchInfo";
        req.cmd = "getUserInfoById";
        req.msgBody = JSONObject.parseObject("{\"userId\":123}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }


    public static void main(String[] args) throws IOException {
        run(TestSearchPerson.class, 3);
    }
}

