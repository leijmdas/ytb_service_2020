package ytb.manager.testcase

import com.alibaba.fastjson.JSONObject
import com.jtest.NodesFactroy.Inject.Inject
import com.jtest.NodesFactroy.Node.HttpClientNode
import com.jtest.annotation.JTest
import com.jtest.annotation.JTestClass
import com.jtest.testframe.ITestImpl
import ytb.common.RestMessage.MsgRequest
import ytb.common.RestMessage.MsgResponse

@JTestClass.author("leijm")
class TestPfUser extends ITestImpl {
    String url = "http://localhost/rest/pfUser";
    String url_dicttag = url+"/dictTag";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    String token = "6994e243180441888535ff1f62495510";

    MsgRequest req = new MsgRequest();
    String data;

    public void suiteSetUp() {
        req.token = token;
    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {
        req.token =token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "dictTag";
        req.cmd = "getDictTagTree";
        req.msgBody = JSONObject.parseObject("{ }");

    }



    @JTest
    @JTestClass.title(" test0001_getDictTagTree1")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/pfUser")
    @JTestClass.exp("ok")
    public void test0001_getDictTagTree1() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "dictTag";
        req.cmd = "getDictTagTree";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("tagType", 1);
        String ret = httpclient.post(url_dicttag, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp);
        System.err.println(req.toJSONString());
        System.out.println(url_dicttag);
        checkEQ(0, resp.getRetcode());

    }
    @JTest
    @JTestClass.title(" test0002_getDictTagListByID")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/pfUser")
    @JTestClass.exp("ok")
    public void test0002_getDictTagListByID() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "dictTag";
        req.cmd = "getDictTagListByID";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("tagId", 0);
        req.msgBody.put("tagType", 1);
        String ret = httpclient.post(url_dicttag, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp);
        System.err.println(req.toJSONString());
        System.out.println(url_dicttag);
        checkEQ(0, resp.getRetcode());

    }
    @JTest
    @JTestClass.title("test0003_deleteDictTag")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/pfUser")
    @JTestClass.exp("ok")
    public void test0003_deleteDictTag () {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "dictTag";
        req.cmd = "deleteDictTag";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("tagId", 21);
        String ret = httpclient.post(url_dicttag, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp);
        System.err.println(req.toJSONString());
        System.out.println(url_dicttag);
        checkEQ(0, resp.getRetcode());

    }


    @JTest
    @JTestClass.title("test0004_getUserPlatfor")
    @JTestClass.step("post http://mysql.kunlong.com/rest/pfUser")
    @JTestClass.exp("ok")
    public void test0004_getUserPlatfor () {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "dictTag";
        req.cmd = "deleteDictTag";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("tagId", 21);
        String ret = httpclient.post(url_dicttag, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp);
        System.err.println(req.toJSONString());
        System.out.println(url_dicttag);
        checkEQ(0, resp.getRetcode());

    }



    public static void main(String[] args) {
        run(TestPfUser.class, 1);

    }
}
