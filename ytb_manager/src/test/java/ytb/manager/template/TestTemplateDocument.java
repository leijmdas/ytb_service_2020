package ytb.manager.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.manager.ManagerCommon.ManagerConst;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

/**
 * @author lxz 2019/2/12 16:22
 */
public class TestTemplateDocument extends ITestImpl {

    @Inject(filename = "node.xml", value = "httpclient")
    private HttpClientNode httpClient;

    private MsgRequest req = new MsgRequest();

    private String url_manager = ManagerConst.url_manager;
    private String url_project = ManagerConst.url_project;
    //private String url_manager = "http://admin.youtobon.com/rest/template";
    String token = "6ad91a1523ec48ffbe115ea442c121ec";
    String apiKey = "1554960984494";

    @Inject("ytb_manager")
    private JDbNode managerDB;

    @Override
    public void setUp() throws Exception {
        req.token = token;
        long l = System.currentTimeMillis();
        req.reqtime = l;
        req.seqno = l;
        req.cmdtype = "";
        req.cmd = "";
        req.msgBody = JSONObject.parseObject("{}");
        req.setApiKey(apiKey + "");
    }

    @JTest
    @JTestClass.title("TestTemplateParseTool.test0001_tagTableServiceManager_exportAllTables")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0001_tagTableServiceManager_exportAllTables() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportAllTables";

        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 1350);

        System.err.println(JSON.toJSONString(req,SerializerFeature.PrettyFormat));

        String resStr = httpClient.post(url_manager, JSON.toJSONString(req), "application/json");
        MsgResponse response = MsgResponse.parseResponse(resStr);
        System.err.println(JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        httpClient.checkStatusCode(200);

        checkEQ(0, response.getRetcode());
    }

    @JTest
    @JTestClass.title("TestTemplateParseTool.test0002_tagTableServiceProject_exportAllTables")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0002_tagTableServiceProject_exportAllTables() {
        req.token = "8cfc2c511a99494a9a46ce63c7769fac";
        req.cmdtype = "tagTableServiceProject";
        req.cmd = "exportAllTables";

        req.msgBody.put("projectId", 2286);
        req.msgBody.put("documentId", 35205);

        System.err.println(JSON.toJSONString(req,SerializerFeature.PrettyFormat));

        String resStr = httpClient.post(url_project, JSON.toJSONString(req), "application/json");
        MsgResponse response = MsgResponse.parseResponse(resStr);
        System.err.println(JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        httpClient.checkStatusCode(200);

        checkEQ(0, response.getRetcode());
    }

    @JTest
    @JTestClass.title("repository_setUpRefAll")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0003_repository_setUpRefAll() {
        req.cmdtype = "repository";
        req.cmd = "setUpRefAll";

        req.msgBody.put("repositoryId", 45);
        req.msgBody.put("projectId", 0);

        System.err.println(JSON.toJSONString(req,SerializerFeature.PrettyFormat));

        String resStr = httpClient.post(url_manager, JSON.toJSONString(req), "application/json");
        MsgResponse response = MsgResponse.parseResponse(resStr);
        System.err.println(JSON.toJSONString(response, SerializerFeature.PrettyFormat));
    }

    public static void main(String[] args) {
        run(TestTemplateDocument.class, 2);
    }
}
