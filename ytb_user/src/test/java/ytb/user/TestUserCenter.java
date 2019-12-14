package ytb.user;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import ytb.user.model.UserImgModel;
import ytb.user.service.IImageService;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 * Package: ytb_service.usercentertest
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2018/9/26 19:56
 */
@JTestClass.author("zengcsheng")
public class TestUserCenter extends ITestImpl {
    String url_base = "http://localhost/rest/ytbuser";
    String token = "281f8fb41c2c4c9cba71df57a03c98f8";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    MsgRequest req = new MsgRequest();
    String data;

    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @JTest
    @JTestClass.title("设置user_login表信息")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test0001_userlogin() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userCenter";
        req.cmd = "updateUserLoginInfo";
        req.msgBody = JSONObject.parseObject("{\"email\":\"123f\",\"nickName\":\"AXDC\",\"realName\":\"kod\",\"sex\":1,\"userAddress\":\"fsdf\",\"userAge\":23}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("test0002_getUserLoginInfo")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test0002_getUserLoginInfo() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userCenter";
        req.cmd = "getUserLoginInfo";
        req.msgBody = JSONObject.parseObject("{\"userId\":124}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("user登陆")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0003_rest_mngr() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userLogin";
        req.cmd = "login";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("mode","password").fluentPut("username","18270054570");
        req.msgBody.fluentPut("password","e10adc3949ba59abbe56e057f20f883e");


        String ret = httpclient.post(url_base,req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        System.out.println(req.toJSONString());
        System.out.println(resp.toJSONString());
        System.out.println(url_base);
    }

    @JTest
    @JTestClass.title("设置user_login表信息")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test0004_login() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userCenter";
        req.cmd = "getUserLoginList";
        req.msgBody = JSONObject.parseObject("{\"loginMobile\":\"\"}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
        System.out.println(json.get("msgBody"));
    }

    @JTest
    @JTestClass.title("TestUserCenter test0005_previewImage")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser/image/previewImage")
    @JTestClass.exp("ok")
    public void test0005_previewImage() throws IOException {

        String url = "http://localhost:80/rest/ytbuser/image/previewImage";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userImage";
        req.cmd = "previewImage";
        req.msgBody = JSONObject.parseObject("{\"documentId\":56}");
        String req_data = new Gson().toJson(req).toString();
        System.out.println("传递的参数是："+req_data);
        byte[] r_data = Base64.getEncoder().encode(req_data.getBytes());
        String ret = httpclient.post(url + "?msgBody=" + new String(r_data), req_data, "application/json");
        System.out.println(url);
        System.out.println("req:" + req_data);
        //System.out.println("ret:" + ret);
    }

    static class MultiPartPost {
        //    String r='''------WebKitFormBoundaryAyEwfJHpBOm44uLu
//    Content-Disposition: form-data; name="file1"; filename="需求说明书-案例V0.3.xlsx"
//    Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet

//                ------WebKitFormBoundaryAyEwfJHpBOm44uLu
//    Content-Disposition: form-data; name="templateId"
//
//            163
//            ------WebKitFormBoundaryAyEwfJHpBOm44uLu--'''
        //Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryAyEwfJHpBOm44uLu'''
        public static String post(String url, String msgBody, File postFile) throws IOException {

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            //把文件转换成流对象FileBody
            FileBody fundFileBin = new FileBody(postFile);
            //设置传输参数
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
            multipartEntity.addPart("file", fundFileBin);//相当于<input type="talk" name="media"/>
            multipartEntity.addTextBody("msgBody", msgBody);//相当于<input type="talk" name="media"/>
             //multipartEntity.add.addTextBody("dd",  "d" , ContentType.create("text/plain", Consts.UTF_8));
            HttpEntity reqEntity = multipartEntity.build();
            httpPost.setEntity(reqEntity);
            //发起请求   并返回请求的响应
            CloseableHttpResponse response = httpClient.execute(httpPost);
            System.out.println(response.getStatusLine());
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                return EntityUtils.toString(resEntity, "UTF-8");
            }
            return "";
        }
    }

    @JTest
    @JTestClass.title("TestUserCenter test0006_upload")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser/image/previewImage")
    @JTestClass.exp("ok")
    public void test0006_uploadImage() throws IOException {

        String url = "http://localhost:80/rest/ytbuser/image/upload";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userImage";
        req.cmd = "upload";
        req.msgBody =  JSONObject.parseObject("{}");
        UserImgModel p =new UserImgModel();

        p.setDocType(IImageService.DOC_TYPE_PIC);
        p.setSaveMode(IImageService.SAVEMODE_PATH);
        p.setPicType("JPG");
        p.setDocPath("/testfile/1.jpg");
        req.msgBody =  JSONObject.parseObject(JSONObject.toJSONString(p));
        String filename=getClass().getResource("/testfile/1.jpg").getPath();
        File postFile = new File(filename);

        String result=MultiPartPost.post (url,JSONObject.toJSONString(req),postFile);
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(result );
    }


    @JTest
    @JTestClass.title("TestUserCenter test0007_uploadJSON")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser/image/previewImage")
    @JTestClass.exp("ok")
    public void test0007_uploadJSON_db() throws IOException {

        String url = "http://localhost:80/rest/ytbuser/image/upload";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userImage";
        req.cmd = "upload";
        req.msgBody =  JSONObject.parseObject("{}");
        UserImgModel p =new UserImgModel();

        p.setDocType(IImageService.DOC_TYPE_JSON);
        p.setSaveMode(IImageService.SAVEMODE_DB);//        p.setPicType("");
        p.setDocPath("g:/savefile/test.sql");
        req.msgBody =  JSONObject.parseObject(JSONObject.toJSONString(p));
        String filename=getClass().getResource("/testfile/ytb_manager_sys_restlist.sql").getPath();
        File postFile = new File(filename);

        String result=MultiPartPost.post (url,JSONObject.toJSONString(req),postFile);
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(result );
    }

    @JTest
    @JTestClass.title("TestUserCenter test0007_uploadJSON")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser/image/previewImage")
    @JTestClass.exp("ok")
    public void test0008_uploadJSON_path() throws IOException {

        String url = "http://localhost:80/rest/ytbuser/image/upload";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userImage";
        req.cmd = "upload";
        req.msgBody =  JSONObject.parseObject("{}");
        UserImgModel p =new UserImgModel();

        p.setDocType(IImageService.DOC_TYPE_JSON);
        p.setSaveMode(IImageService.SAVEMODE_PATH);//        p.setPicType("");
        p.setDocPath("g:/savefile/test.sql");
        req.msgBody =  JSONObject.parseObject(JSONObject.toJSONString(p));
        String filename=getClass().getResource("/testfile/ytb_manager_sys_restlist.sql").getPath();
        File postFile = new File(filename);

        String result=MultiPartPost.post (url,JSONObject.toJSONString(req),postFile);
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(result );
    }


    @JTest
    @JTestClass.title("获取用户接单范围")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0009_() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "getProjectScodeList";
        req.msgBody = JSONObject.parseObject("{\"userId\":124,\"companyId\":0}");
        data=new Gson().toJson(req).toString();
        System.out.print("传递的参数是："+data);

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }

    @JTest
    @JTestClass.title("新增用户接单范围")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0010() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "addUserProjectScode";
        req.msgBody = JSONObject.parseObject("{\"proTypeId\":124,\"companyId\":0}");
        data=new Gson().toJson(req).toString();
        System.out.print("传递的参数是："+data);

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }


    @JTest
    @JTestClass.title("删除用户接单范围")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0011() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "deleteProjectScode";
        req.msgBody = JSONObject.parseObject("{\"scodeId\":124}");

         System.out.print("传递的参数是："+data);

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }



    @JTest
    @JTestClass.title("获取意向采购清单")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0012() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "advicePosted";
        req.cmd = "getUserPurchase";
        req.msgBody = JSONObject.parseObject("{}");
        data=new Gson().toJson(req).toString();
        System.out.print("传递的参数是："+data);

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }



    @JTest
    @JTestClass.title("test0013_cmpnyLogin")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0013_cmpnyLogin() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyLogin";
        req.cmd = "login";
        req.msgBody = JSONObject.parseObject("{}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }




    @JTest
    @JTestClass.title("test0014_userInfo")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0014_userInfo() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userCenter";
        req.cmd = "getUserBaseInformation";
        req.msgBody = JSONObject.parseObject("{\"userId\":124}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }

    @JTest
    @JTestClass.title("test0014_userInfo")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0015_userInfo() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userCenter";
        req.cmd = "getUserInfoById";
        req.msgBody = JSONObject.parseObject("{\"userId\":124}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }

    @JTest
    @JTestClass.title("添加关注")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0016_userInfo() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userCenter";
        req.cmd = "addUserAttend";
        req.msgBody = JSONObject.parseObject("{\"attendToUserId\":124,\"attendToUserType\":2}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }


    @JTest
    @JTestClass.title("取消关注")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0017_userInfo() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userCenter";
        req.cmd = "cancelAttend";
        req.msgBody = JSONObject.parseObject("{\"userId\":124,\"attendId\":2,\"companyId\":394}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }


    @JTest
    @JTestClass.title("用户注册")
    @JTestClass.pre("")
    @JTestClass.step("post http://localhost:80/rest/ytbuser")
    @JTestClass.exp("ok")
    public void test0017_regiserUser() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "registry";
        req.cmd = "user";
        req.msgBody = JSONObject.parseObject("{\"username\":\"18270054576\",\"password\":'123456',\"code\":'388123',\"model\":'password'}");
        data=new Gson().toJson(req).toString();

        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));

    }


    public static void main(String[] args) throws IOException {

        run(TestUserCenter.class, "");


    }
}
