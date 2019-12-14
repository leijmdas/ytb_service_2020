package ytb.common.testcase

import ytb.common.ManagerCommon.ManagerLogin
import com.alibaba.fastjson.JSONObject
import com.jtest.NodesFactroy.Inject.Inject
import com.jtest.NodesFactroy.Node.HttpClientNode
import com.jtest.annotation.JTest
import com.jtest.annotation.JTestClass
import com.jtest.testframe.ITestImpl
import ytb.common.MyBatis.SqlSessionBuilder
import ytb.common.basic.safecontext.service.SafeContext
import ytb.common.basic.safecontext.service.impl.RestRightCacheService
import ytb.common.RestMessage.MsgHandler
import ytb.common.RestMessage.MsgRequest
import ytb.common.RestMessage.MsgResponse
import ytb.common.context.service.impl.YtbContext

@JTestClass.author("leijmdas")
public class TestRestGroovy extends ITestImpl {
    String url  = "http://localhost/rest/context";
    String url_base = "http://localhost/rest/service";
    String url_getPicCode = "http://localhost/rest/context/getPicCode";
    MsgRequest req = new MsgRequest();
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    String token = "c1f119fd8ef24954b2485b6f4625779a";
    String apiKey = "171";
    String data ;

    Map<String, Object> map_data = JSONObject.parseObject(data, Map.class);
    static SqlSessionBuilder getSqlSessionBuilder(){
        return   YtbContext.getSqlSessionBuilder()

    }
    public void suiteSetUp() {
        map_data = JSONObject.parseObject(data, Map.class);
        System.out.println(JSONObject.toJSONString(map_data));
    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {

//        req.token = token;
//        req.setApiKey(apiKey);
        new ManagerLogin().login(req);
        req.setSign("1534043004468");
        req.setCmdtype("demoModel");
        req.setCmd("selectList");
        data = req.toJSONStringPretty();

    }

    @Override
    public void tearDown() {
    }


    @JTest
    @JTestClass.title("test0001_httprest1")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/test")
    @JTestClass.exp("ok")
    public void test0001_httprest () {
        String url_base = "http://admin.youtobon.com/rest/service";

        String ret = httpclient.post(url_base+"/demoModel",req.toJSONStringPretty() , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("test0002_httprest2")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:8080/rest/test")
    @JTestClass.exp("ok")
    public void test0002_httprest2() {

        String ret = httpclient.post(url_base+"/test", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }

    @JTest
    @JTestClass.title("test0001_httprest1")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/test")
    @JTestClass.exp("ok")
    public void test0003_httprest1() {

        String ret = httpclient.post(url_base+"/test",data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        println ret
    }


    @JTest
    @JTestClass.title("test0004_httprest2")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:8080/rest/test")
    @JTestClass.exp("ok")
    public void test0004_httprest2() {

        String ret = httpclient.post(url_base+"/demoprjtype", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        RestRightCacheService.getList().each {
            println it
        }
        println RestRightCacheService.findCheckRight("UserManager","setUserSign");

    }

    @JTest
    @JTestClass.title("test0004_httprest2")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:8080/rest/test")
    @JTestClass.exp("ok")
    public void test0005_checkValid_userRight() {
        MsgRequest r=new MsgRequest();
        r.token="05b5963672d14023a0ab82acf3638d69";
        r.cmdtype="ss"
        r.cmd="project"
        SafeContext.checkUserRightValid(r);
    }


    @JTest
    @JTestClass.title("test0006_restLoginSSO")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/context")
    @JTestClass.exp("ok")
    public void test0006_restLoginSSO_rightOK() {

        String ret = httpclient.post(url_base + "/context", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        println json.toJSONString()

    }

    @JTest
    @JTestClass.title("test0007_restLoginSSO_Right_timein")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/context")
    @JTestClass.exp("ok")
    public void test0007_restLoginSSO_Right_timein() {

        String ret = httpclient.post(url_base + "/context", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        println json.toJSONString()    //println json.getJSONObject("msgBody").toJSONString()

    }

    @JTest
    @JTestClass.title("test0008_logout")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/context")
    @JTestClass.exp("ok")
    public void test0008_logout() {
        String token="33cc4d29d6c84ee6b2331362e2b6418f"
        data ="""
{"token":"${token}","cmdtype":"context","cmd":"logout","reqtime":1534043004468,"seqno":1534043004468} 
"""
        String url_base="http://localhost:80/rest";

        String ret = httpclient.post(url_base+"/context",data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);

        checkEQ(0,json.getInteger("retcode"));
        println json.toJSONString()
    }


    @JTest
    @JTestClass.title("test0009_refreshToken")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/context")
    @JTestClass.exp("ok")
    public void test0009_refreshToken() {
        data = '''
{"msgBody":{"refresh_token":"3e3b396d45414c49b1b9e12b28cee2f0"},"token":"5bc0b54149f644918a0e862160a09030","cmdtype":"context","cmd":"refreshToken","reqtime":1534043004468,"seqno":1534043004468} '''
        println data
        String ret = httpclient.post(url_base+"/context",data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);

        checkEQ(0,json.getInteger("retcode"));
        println json.toJSONString()
    }
//    data = '''
//{"msgBody":{"config_item":"SUCCESS"},"token":"6994e243180441888535ff1f62495510","cmdtype":"config","cmd":"getConfig","reqtime":1534043004468,"seqno":1534043004468} '''

    @JTest
    @JTestClass.title("test0010_config_getConfig")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/context")
    @JTestClass.exp("ok")
    public void test0010_config_getConfig() {
        map_data.put("cmdtype", "config")
        map_data.put("cmd", "getConfig")
        Map<String, Object> msgBody = new LinkedHashMap<>();
        msgBody.put("config_item", "FILE_PATH_TEMP")
        map_data.put("msgBody", msgBody)
        println JSONObject.toJSONString(map_data);
        String ret = httpclient.post(url_base + "/context", JSONObject.toJSONString(map_data), "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);

        checkEQ(0, json.getInteger("retcode"));
        println JSONObject.toJSONString(map_data);//        println json.toJSONString()
    }

    @JTest
    @JTestClass.title("test0011_config_getErrorCode")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/context")
    @JTestClass.exp("ok")
    public void test0011_config_getErrorCode() {
        println YtbContext.getYtb_context().getError_msg("SUCCESS")

    }
    //apple5917<apple5917@163.com>
    @JTest
    @JTestClass.title("test0012_cacheManager_refresh")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/context")
    @JTestClass.exp("ok")
    public void test0012_cacheManager_refresh() {
        map_data.put("cmdtype", "cacheManager")
        map_data.put("cmd", "refresh")

        println JSONObject.toJSONString(map_data);
        String ret = httpclient.post(url_base + "/context", JSONObject.toJSONString(map_data), "application/json");
        httpclient.checkStatusCode(200);//        JSONObject json = JSONObject.parseObject(ret);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }


    //apple5917<apple5917@163.com>
    @JTest
    @JTestClass.title("test0013_cacheManager_refreshUser")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/context")
    @JTestClass.exp("ok")
    public void test0013_cacheManager_refreshUser() {
        map_data.put("cmdtype", "cacheManager")
        map_data.put("cmd", "refreshUser")
        Map<String, Object> msgBody = new LinkedHashMap<>();
        msgBody.put("user_id", "17")
        map_data.put("msgBody", msgBody)
        println JSONObject.toJSONString(map_data);
        String ret = httpclient.post(url_base + "/context", JSONObject.toJSONString(map_data), "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("获取图形码")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/context/getPicCode")
    @JTestClass.exp("ok")
    public void test0014_genPicCode() {
        map_data.put("cmdtype", "context")
        map_data.put("cmd", "getPicCode")
        Map<String, Object> msgBody = new LinkedHashMap<>();
        map_data.put("msgBody", msgBody)
        String s=JSONObject.toJSONString(map_data);
        JSONObject req=JSONObject.parseObject(s);
        String ret = httpclient.post(url_getPicCode, MsgHandler.toJSONStringPretty(req), "application/json");
        httpclient.checkStatusCode(200);
        System.out.println(url_getPicCode);
        System.out.println(MsgHandler.toJSONStringPretty(req));

    }

    @JTest
    @JTestClass.title("检查图形码")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/context/picCode")
    @JTestClass.exp("ok")
    public void test0015_checkPicCode() {

        map_data.put("cmdtype", "context")
        map_data.put("cmd", "checkPicCode")
        Map<String, Object> msgBody = new LinkedHashMap<>();

        msgBody.put("picCode", "123")
        map_data.put("msgBody", msgBody)
        println JSONObject.toJSONString(map_data);
        String ret = httpclient.post(url_base + "/context", JSONObject.toJSONString(map_data), "application/json");
        System.out.println(ret);
        httpclient.checkStatusCode(200);
    }
    @JTest
    @JTestClass.title("test0016_getuserType_role")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/context/picCode")
    @JTestClass.exp("ok")
    public void test0016_getuserType_role() {

    }
    @JTest
    @JTestClass.title("test0017_logout")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/context/picCode")
    @JTestClass.exp("ok")
    public void test0017_logout() {

        map_data.put("cmdtype", "context")
        map_data.put("cmd", "logout")
        Map<String, Object> msgBody = new LinkedHashMap<>();
        map_data.put("token","f7a1246dd3844723b34de416ef0b2166");
        map_data.put("msgBody", msgBody)
        println JSONObject.toJSONString(map_data);
        String ret = httpclient.post("http://admin.youtobon.com/rest/context", JSONObject.toJSONString(map_data), "application/json");
        //String ret = httpclient.post("http://localhost/rest/context", JSONObject.toJSONString(map_data), "application/json");
        System.out.println(ret);
        httpclient.checkStatusCode(200);
    }


    @JTest
    @JTestClass.title("test0001_getUserLoginInfo 测试前台用户权限")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test0018_getUserLoginInfo() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "context";
        req.cmd = "getLogSso";
        req.msgBody = JSONObject.parseObject("{}");
        //url="http://project.youtobon.com/rest/context";
        String ret = httpclient.post(url, req.toJSONStringPretty(), "application/json");

        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toJSONStringPretty());
        System.out.println("req:"+req.toJSONStringPretty());
        System.out.println(url);

    }

    @JTest
    @JTestClass.title("test0013_cacheManager_refreshUser")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/context")
    @JTestClass.exp("ok")
    public void test0019_cacheManager_refreshAllUser() {
        map_data.put("cmdtype", "cacheManager")
        map_data.put("cmd", "refreshUser")
        Map<String, Object> msgBody = new LinkedHashMap<>();
        map_data.put("msgBody", msgBody)
        println JSONObject.toJSONString(map_data);
        String url = "http://project.youtobon.com/rest/context";
        String ret = httpclient.post( url , JSONObject.toJSONString(map_data), "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(MsgHandler.toJSONStringPretty(map_data));
    }

    @JTest
    @JTestClass.title("test0020_getAreaByIP")
    @JTestClass.pre("")
    @JTestClass.step("post  ")
    @JTestClass.exp("ok")
    public void test0020_getAreaByIP() {

        String a = YtbContext.getYtb_context().getAreaByIP("111.222.241.139");
        //String b=YtbContext.getYtb_context().getAreaByIP("mysql.kunlong.com");
        JSONObject json = JSONObject.parseObject(a);
        System.out.print(MsgHandler.toJSONStringPretty(json));
    }
    @JTest
    @JTestClass.title("test0021_clearSessionTimeout")
    @JTestClass.pre("")
    @JTestClass.step("post  ")
    @JTestClass.exp("ok")
    public void test0021_clearSessionTimeout() {
        map_data.put("token",token)
        map_data.put("cmdtype", "cacheManager")
        map_data.put("cmd", "clearSessionTimeout")
        Map<String, Object> msgBody = new LinkedHashMap<>();
        map_data.put("msgBody", msgBody)
        println JSONObject.toJSONString(map_data);
        //String url = "http://admin.youtobon.com/rest/context";
        String url = "http://localhost/rest/context";
        String ret = httpclient.post( url , JSONObject.toJSONString(map_data), "application/json");
        System.out.println(MsgHandler.toJSONStringPretty(map_data));
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(MsgHandler.toJSONStringPretty(map_data));
    }
    @JTest
    @JTestClass.title("test0022_crefreshErrorCode")
    @JTestClass.pre("")
    @JTestClass.step("post  ")
    @JTestClass.exp("ok")
    public void test0022_crefreshErrorCode() {
        map_data.put("token",token)
        map_data.put("cmdtype", "cacheManager")
        map_data.put("cmd", "refreshErrorCode")
        Map<String, Object> msgBody = new LinkedHashMap<>();
        map_data.put("msgBody", msgBody)
        println JSONObject.toJSONString(map_data);
        String url = "http://localhost/rest/context";
        String ret = httpclient.post( url , JSONObject.toJSONString(map_data), "application/json");
        System.out.println(MsgHandler.toJSONStringPretty(map_data));
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(MsgHandler.toJSONStringPretty(map_data));
    }


    @JTest
    @JTestClass.title("获取图形码")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/context/getPicCode")
    @JTestClass.exp("ok")
    public void test0023_genPicCode() {
        map_data.put("cmdtype", "context")
        map_data.put("cmd", "getPicCode")
        Map<String, Object> msgBody = new LinkedHashMap<>();
        map_data.put("msgBody", msgBody)
        String s=JSONObject.toJSONString(map_data);
        JSONObject req=JSONObject.parseObject(s);
        String url_getPicCode = "http://localhost/rest/context";
        String ret = httpclient.post(url_getPicCode, MsgHandler.toJSONStringPretty(req), "application/json");
        httpclient.checkStatusCode(200);
        System.out.println(url_getPicCode);
        System.out.println(MsgHandler.toJSONStringPretty(req));

    }

    @JTest
    @JTestClass.title("获取用户列表")
    @JTestClass.pre("")
    @JTestClass.step("url_context")
    @JTestClass.exp("ok")
    public void test_0024_getLoginSSO() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "context";
        req.cmd = "getLogSso";
        String url_context="http://mysql.kunlong.com/rest/context";

        String ret = httpclient.post(url_context, req.toJSONString(), "application/json");
        MsgResponse resp=MsgResponse.parseResponse(ret);
        System.err.println(req.toJSONStringPretty());
        System.err.println(resp.toJSONStringPretty());
        httpclient.checkStatusCode(200);

        checkEQ(0, resp.getRetcode());
    }

    public static void main(String[] args) {
        run(TestRestGroovy.class, 24);

    }
}
