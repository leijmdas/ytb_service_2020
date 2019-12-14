package ytb.common;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.ytblog.YtbLog;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class UserLogin extends ITestImpl {

    public static String codeMsg = "简单代码写不好，复杂业务写不了！\r\n";

    public static String code="良好的命名胜过千言万语的注释!\n1按代码规范\n" +
            "2不要有重复代码\n" +
            "3函数不能太长\n" +
            "4不能有if for else 好多{}嵌套\n" +
            "5尽量封装一些小函数";

    String url = TestProjectConst.url_login;
    String url_context = TestProjectConst.url_context;

    public static int paId = TestProjectConst.paId;
    public static int pbId = TestProjectConst.pbId;
    public static String friends = TestProjectConst.friends;
    public static String payPasswodEn = TestProjectConst.payPasswodEn;
    public static String passwod = TestProjectConst.passwod;
    public static String passwodEn = TestProjectConst.passwodEn;

    public LoginSso getLoginSso() {
        return loginSso;
    }

    public void setLoginSso(LoginSso loginSso) {
        this.loginSso = loginSso;
    }

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    MsgRequest req = new MsgRequest();

    LoginSso loginSso;
    String token = "";
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;
    @Inject("ytb_user")
    JDbNode dbUser;


    //return token
    public String login(int userId) throws Exception {
        System.out.println(codeMsg);
        System.out.println(code);
        List<Map<String, Object>> lst = selectUserLogin(userId);
        Map<String, Object> m = lst.get(0);
        return login(m.get("login_mobile").toString(), m.get("password").toString());
    }

    public LoginSso getLog_sso(@NotNull String token) throws Exception {
        return SafeContext.getLog_ssoAndApiKey(token);
    }

    public List<Map<String, Object>> selectUserLogin(int userId) throws Exception {
        dbUser.clearSql().appendSql(" select * from user_login ");
        dbUser.appendSql(" where user_id=").appendSql(userId);
        dbUser.appendSql(" limit 1");

        return dbUser.select();
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

    //return token
    public String login(String login_mobile, String password) {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userLogin";
        req.cmd = "login";
        req.msgBody.fluentPut("mode","password");
        req.msgBody.fluentPut("username",login_mobile).fluentPut("password",password);

        String ret = httpclient.post(url,req.toJSONString(), "application/json");
        YtbLog.logJtest("login req",req.toJSONStringPretty());

        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        token = resp.getMsgBody().getString("userToken");

        req.token = token;
        loginSso = SafeContext.getLog_ssoAndApiKey(token);
        YtbLog.logJtest("login resp",resp.toJSONStringPretty());

        return token;
    }


    @JTest
    @JTestClass.title("user登陆")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0001_userLogin() {

        //req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userLogin";
        req.cmd = "login";
        req.msgBody.fluentPut("mode","password").fluentPut("username","18772103675");
        req.msgBody.fluentPut("password","e10adc3949ba59abbe56e057f20f883e");

        String ret = httpclient.post(url,req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        System.out.println(req.toJSONString());
        System.out.println(resp.toJSONString());
        System.out.println(resp.getMsgBody().getString("userToken"));
        System.out.println(url );
    }


    public static void main(String[] args) throws Exception {

        new UserLogin().login(123);
    }
}
