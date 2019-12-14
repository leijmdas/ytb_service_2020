package ytb.common.ManagerCommon;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.testframe.ITestImpl;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

public class ManagerLogin extends ITestImpl {
    String url_login = ManagerConst.url_login;
    String url_context = ManagerConst.url_context;

    String token = " ";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    MsgRequest req = new MsgRequest();

    String apiKey;
    //MsgRequest
    public String login(MsgRequest req) {
        String token = login();
        req.setToken(token);
        req.setApiKey(getApiKey());
        return token;
    }

    public String login() {
        return login("admin");
    }

    //return token
    public String login(String userName) {

        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "user";
        req.cmd = "login";

        req.msgBody.fluentPut("userName", userName).fluentPut("password", "123456");

        String ret = httpclient.post(url_login, req.toString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("传参数是：" + req + "\r\n响应的是：" + resp.toJSONStringPretty());
        JSONObject msgBody = resp.getMsgBody().getJSONArray("list").getJSONObject(0);

        token = msgBody.getString("token");
        apiKey = msgBody.getJSONObject("api_keyModel").getString("apiKey");
        String code="良好的命名胜过千言万语的注释!\n1按代码规范\n" +
                "2不要有重复代码\n" +
                "3函数不能太长\n" +
                "4不能有if for else 好多{}嵌套\n" +
                "5尽量封装一些小函数";
        System.out.println(code);
        return token;
    }

    public String getApiKey() {
        return apiKey;
    }

    public LoginSsoJson getLogSso(String token, MsgRequest req) {


        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "context";
        req.cmd = "getLogSso";

        String ret = httpclient.post(url_context, req.toJSONString(), "application/json");
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.err.println(req.toJSONStringPretty());
        System.err.println(resp.toJSONStringPretty());
        httpclient.checkStatusCode(200);

        checkEQ(0, resp.getRetcode());
        return  resp.getMsgBody().getJSONArray("list").getJSONObject(0).toJavaObject(LoginSsoJson.class);
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public MsgRequest defaultReq() {
        MsgRequest req = new MsgRequest();
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportTable";
        req.msgBody = JSONObject.parseObject("{}");
        req.setApiKey(apiKey + "");
        req.setSign(apiKey + "");
        return req;
    }
}
