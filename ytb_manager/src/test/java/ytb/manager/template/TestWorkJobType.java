package ytb.manager.template;

import com.alibaba.fastjson.JSONObject;
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
 * @author ljm 2019/3/21 16:21
 */
public class TestWorkJobType extends ITestImpl {

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpClient;

    MsgRequest req = new MsgRequest();

    String urlProjectTemplate = ManagerConst.url_template;

    String token = "15373258c3924df0b1c4836f521c4ef1";

    @Inject("ytb_project")
    JDbNode dbProject;

    @Override
    public void suiteSetUp() throws Exception {
    }

    @Override
    public void setUp() throws Exception {
        req.token = token;
        long l = System.currentTimeMillis();
        req.reqtime = l;
        req.seqno = l;
        req.cmdtype = "";
        req.cmd = "";
        req.msgBody = JSONObject.parseObject("{}");
    }

    @Override
    public void tearDown() throws Exception {
    }

    @Override
    public void suiteTearDown() throws Exception {
    }

    @JTest
    @JTestClass.title("TestProjectTemplate.test0001_projType_addProjType")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0001_modifyState() {

        req.cmdtype = "workJobType";
        req.cmd = "modifyState";
        req.msgBody.put("workJobTypeId",27);
        req.msgBody.put("state",2);

        String resStr = httpClient.post(urlProjectTemplate, req.toJSONString(), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        System.err.println(msgResponse);

        httpClient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }


    public static void main(String[] args) {
        run(TestWorkJobType.class, 1);
    }
}
