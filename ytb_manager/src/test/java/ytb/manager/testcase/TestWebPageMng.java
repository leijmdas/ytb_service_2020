package ytb.manager.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.manager.webpagemng.model.CusServiceQuestion;
import ytb.manager.webpagemng.model.CusServiceQuestionType;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;

@JTestClass.author("lixiongzi")
public class TestWebPageMng extends ITestImpl {

    private String url = "http://localhost:80/rest/webpagemng";

    private String token = "d98c3b969dc34aaa92942c8a9c646f2a";

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
    @JTestClass.title("TestWebPageMng_test0001_pageResourceMng_selectList")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0001_pageResourceMng_selectList() {
        req.cmdtype = "pageResourceMng";
        req.cmd = "selectPageResourceList";

        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0002_customerServiceCenter_addQueType")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0002_customerServiceCenter_addQueType() {
        req.cmdtype = "customerServiceCenter";
        req.cmd = "addQueType";
        CusServiceQuestionType cusServiceQuestionType = new CusServiceQuestionType();
        cusServiceQuestionType.setTypeName("规则中心XXXXX");
        req.msgBody = JSON.parseObject(JSON.toJSONString(cusServiceQuestionType));
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0003_customerServiceCenter_removeQueType")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0003_customerServiceCenter_removeQueType() {
        req.cmdtype = "customerServiceCenter";
        req.cmd = "removeQueType";
        req.msgBody.put("id", 17);
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0004_customerServiceCenter_updateQueType")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0004_customerServiceCenter_updateQueType() {
        req.cmdtype = "customerServiceCenter";
        req.cmd = "updateQueType";
        CusServiceQuestionType cusServiceQuestionType = new CusServiceQuestionType();
        cusServiceQuestionType.setTypeName("疑难解答（改一改）");
        cusServiceQuestionType.setId(3);
        req.msgBody = JSON.parseObject(JSON.toJSONString(cusServiceQuestionType));
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0005_customerServiceCenter_selQueTypeListTree")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0005_customerServiceCenter_selQueTypeListTree() {
        req.cmdtype = "customerServiceCenter";
        req.cmd = "selQueTypeListTree";
        CusServiceQuestionType cusServiceQuestionType = new CusServiceQuestionType();
        req.msgBody = JSON.parseObject(JSON.toJSONString(cusServiceQuestionType));
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0006_customerServiceCenter_addQueType")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0006_customerServiceCenter_addQueType() {
        req.cmdtype = "customerServiceCenter";
        req.cmd = "addQueType";
        CusServiceQuestionType cusServiceQuestionType = new CusServiceQuestionType();
        cusServiceQuestionType.setTypeName("冻结金额");
        cusServiceQuestionType.setParentId(8);
        req.msgBody = JSON.parseObject(JSON.toJSONString(cusServiceQuestionType));
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0007_customerServiceCenter_addQuestion")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0007_customerServiceCenter_addQuestion() {
        req.cmdtype = "customerServiceCenter";
        req.cmd = "addQuestion";
        CusServiceQuestion question = new CusServiceQuestion();
        question.setTitle("什么是毛利率？");
        question.setContent("毛利率定义：日薪乘以工作天数是人员劳动的成本（类似一件产品的生产成本），该成本还需要增加：税费+平台服务费+添置工具与培训等支出费用，因此需要增加日薪劳务费之外的费用（类似产品销售价格）。");
        req.msgBody = JSON.parseObject(JSON.toJSONString(question));
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0008_customerServiceCenter_removeQuestion")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0008_customerServiceCenter_removeQuestion() {
        req.cmdtype = "customerServiceCenter";
        req.cmd = "removeQuestion";
        req.msgBody.put("qid", 1);
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0009_customerServiceCenter_updateQuestion")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0009_customerServiceCenter_updateQuestion() {
        req.cmdtype = "customerServiceCenter";
        req.cmd = "updateQuestion";
        CusServiceQuestion question = new CusServiceQuestion();
        question.setQid(2);
        question.setTitle("什么是毛利率？GGGG");
        question.setContent("GGGG毛利率定义：日薪乘以工作天数是人员劳动的成本（类似一件产品的生产成本），该成本还需要增加：税费+平台服务费+添置工具与培训等支出费用，因此需要增加日薪劳务费之外的费用（类似产品销售价格）。");
        req.msgBody = JSON.parseObject(JSON.toJSONString(question));
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0010_customerServiceCenter_questionPageData")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0010_customerServiceCenter_questionPageData() {
        req.cmdtype = "customerServiceCenter";
        req.cmd = "questionPageData";
        CusServiceQuestion question = new CusServiceQuestion();
        req.msgBody.put("question", question);
        req.msgBody.put("pageNo", 1);
        req.msgBody.put("limit", 10);
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("TestWebPageMng_test0011_customerServiceCenter_questionPageData")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/webpagemng")
    @JTestClass.exp("ok")
    public void test0011_customerServiceCenter_questionPageData() {
        //按问题类型分页查询问题
        req.cmdtype = "customerServiceCenter";
        req.cmd = "questionPageData";
        CusServiceQuestion question = new CusServiceQuestion();
        question.setTypeId(9);
        req.msgBody.put("question", question);
        req.msgBody.put("pageNo", 1);
        req.msgBody.put("limit", 10);
        System.out.println("======================" + JSONObject.toJSONString(req));
        String ret = httpclient.post(url, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

    }

    public static void main(String[] args) throws IOException {
        run(TestWebPageMng.class, 2);

    }


}

