package ytb.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.manager.webpagemng.model.CusServiceQuestion;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;

/**
 * @author lxz 2018/12/25 14:20
 */
public class TestRestIndexPageContent extends ITestImpl {

    private String url = "http://localhost:80/rest/indexPageContent";

    private String token = "fc3ffdc9608445fb82bfc62f1aec3689";

    private MsgRequest req;

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {

    }

    @Override
    public void setUp() {
        req = new MsgRequest();
        req.setToken(token);
    }

    @Override
    public void tearDown() {

    }

    @JTest
    @JTestClass.title("TestRestIndexPageContent_test0001_cusServiceCenter_selQueTypeListTree")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/indexPageContent")
    @JTestClass.exp("ok")
    public void test0001_cusServiceCenter_selQueTypeListTree() {
        req.cmdtype = "cusServiceCenter";
        req.cmd = "selQueTypeListTree";
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("======post request======");
        System.out.println(JSON.toJSONString(req, true));
    }

    @JTest
    @JTestClass.title("TestRestIndexPageContent_test0002_cusServiceCenter_questionPageData")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/indexPageContent")
    @JTestClass.exp("ok")
    public void test0002_cusServiceCenter_questionPageData() {
        req.cmdtype = "cusServiceCenter";
        req.cmd = "questionPageData";
        CusServiceQuestion question = new CusServiceQuestion();
        question.setTypeId(9);
        question.setTitle("清风");
        req.msgBody.put("question", question);
        req.msgBody.put("pageNo", 1);
        req.msgBody.put("limit", 10);
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("======post request======");
        System.out.println(JSON.toJSONString(req, true));
    }

    @JTest
    @JTestClass.title("TestRestIndexPageContent_test0003_previewImage")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/indexPageContent/previewImage")
    @JTestClass.exp("ok")
    public void test0003_previewImage() {
        MsgRequest req = new MsgRequest();
        req.msgBody.put("documentId", 771);
        String ret = httpclient.post("http://mysql.kunlong.com:80/rest/indexPageContent/previewImage", JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("======post request======");
        System.out.println(JSON.toJSONString(req, true));
    }

    @JTest
    @JTestClass.title("TestRestIndexPageContent_test0004_cusServiceCenter_selQuestion")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/indexPageContent")
    @JTestClass.exp("ok")
    public void test0004_cusServiceCenter_selQuestion() {
        req.cmdtype = "cusServiceCenter";
        req.cmd = "selQuestion";
        req.msgBody.put("qid", 2);
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("======post request======");
        System.out.println(JSON.toJSONString(req, true));
    }

    @JTest
    @JTestClass.title("TestRestIndexPageContent_test0005_indexPage_sliderBox")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/indexPageContent")
    @JTestClass.exp("ok")
    public void test0005_indexPage_sliderBox() {
        req.cmdtype = "indexPage";
        /**
         * case "sliderBox"://轮播图
         *             case "findProject"://找项目
         *             case "sendDemand"://发需求
         *             case "projectWork"://项目协作介绍
         *             case "successExample"://成功案例
         */
        req.cmd = "successExample";
        req.msgBody.put("pageNo", 1);
        req.msgBody.put("limit", 10);
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("======post request======");
        System.out.println(JSON.toJSONString(req, true));
    }

    @JTest
    @JTestClass.title("TestRestIndexPageContent_test0005_indexPage_sliderBox")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/indexPageContent")
    @JTestClass.exp("ok")
    public void test0006_cusServiceCenter_hotKeyWordList() {
        req.cmdtype = "cusServiceCenter";
        req.cmd = "hotKeyWordList";
        req.msgBody.put("dataTotal", 5);
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("======post request======");
        System.out.println("url=" + url);
        System.out.println(JSON.toJSONString(req, true));
    }

    @JTest
    @JTestClass.title("TestRestIndexPageContent_test0005_indexPage_sliderBox")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/indexPageContent")
    @JTestClass.exp("ok")
    public void test0007_cusServiceCenter_hotKeyWordSelect() {
        req.cmdtype = "cusServiceCenter";
        req.cmd = "hotKeyWordSelect";
        CusServiceQuestion question = new CusServiceQuestion();
        question.setTitle("a");
        req.msgBody.put("question", question);
        req.msgBody.put("pageNo", 1);
        req.msgBody.put("limit", 10);
        String ret = httpclient.post(url, JSONObject.toJSONString(req, SerializerFeature.NotWriteDefaultValue), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("======post request======");
        System.out.println(JSON.toJSONString(req, SerializerFeature.NotWriteDefaultValue, SerializerFeature.PrettyFormat));
    }


    public static void main(String[] args) throws IOException {
        run(TestRestIndexPageContent.class, 7);
    }

}
