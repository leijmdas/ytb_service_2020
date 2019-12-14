package bangbangtest;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

public class TestBangBang extends ITestImpl {

    @Inject(filename = "node.xml", value = "httpclient")
    private HttpClientNode httpClient;

    private MsgRequest req = new MsgRequest();

    private static final String BANGBANG_URL = "http://localhost/bangbangRest/Ajax";

    private String token = "15373258c3924df0b1c4836f521c4ef1";

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

    @JTest
    @JTestClass.title("TestProjectTemplate.test0001_FriendsManger_getUsersGroups")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0001_FriendsManger_getUsersGroups() {
        req.cmdtype = "FriendsManger";
        req.cmd = "getUsersGroups";

        req.msgBody.put("userId", 17);

        String reqStr = JSON.toJSONString(req, SerializerFeature.PrettyFormat);
        System.err.println(reqStr);

        String resStr = httpClient.post(BANGBANG_URL, reqStr, "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(resStr);
        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat));

        httpClient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    public static void main(String[] args) {
        run(TestBangBang.class, 1);
    }
}
