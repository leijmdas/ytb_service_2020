package ytb.project;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import ytb.common.Flow;
import ytb.common.UserLogin;
import ytb.common.utils.YtbUtils;
import ytb.project.context.UserProjectContext;
import ytb.project.service.template.TemplateAssist;
import ytb.manager.template.model.Dict_document;
import ytb.manager.template.model.Mngr_Re_Document;
import ytb.manager.template.rest.RestTemplateManager;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Package: projecttest.companyProManagerTest
 * Author: ZCS
 * Date: Created in 2018/10/17 17:20
 */
public class TestProjectDocument extends ITestImpl {
    String url_182 = "http://project.youtobon.com/rest/project/template";
    String url_base = "http://mysql.kunlong.com/rest/project/template";

    String url_image = "http://mysql.kunlong.com/rest/project/image";
    String url_upload = "http://mysql.kunlong.com/rest/project/upload";
    String url_projectCenter = "http://mysql.kunlong.com/rest/projectCenter";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    int projectId = 37;
    int docid = 157;
    int docLstId = 372;

    MsgRequest req = new MsgRequest();
    String token = "d8a8454b7ce34d3d93501d0c57db17c3";
    String data;

    public void suiteSetUp() {
    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() throws Exception {
        token = new UserLogin().login(1300);
        req.token=token;
    }

    @Override
    public void tearDown() {

    }

    @JTest
    @JTestClass.title("修改文档内容test0001_modifyJson")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0001_modifyJson() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "modifyJson";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", 2);
        req.msgBody.put("template", "{}");

        String ret = httpclient.post(url_base, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println("ret: " + url_base);


    }

    @JTest
    @JTestClass.title("查看文档内容test0002_previewJson")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0002_previewJson() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "previewJson";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", docid);

        data = new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base, data, "application/json");
        System.out.println("req: " + data);
        System.out.println("ret: " + ret);
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("增加文档test0003_addJson")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0003_addJson() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "addJson";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", 1);
        JSONObject body = JSONObject.parseObject("{}");
        body.put("header", "{}");
        req.msgBody.put("template", body);

        data = new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base, data, "application/json");
        System.out.println("req: " + data);
        System.out.println("ret: " + ret);
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("图片预览test0004_previewImage")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/image")
    @JTestClass.exp("ok")
    public void test0004_previewImage() {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectImage";
        req.cmd = "previewImage";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", 99);
        byte[] msgBody = Base64.getEncoder().encode(JSONObject.toJSON(req).toString().getBytes());
        data = new Gson().toJson(req).toString();
        String url = url_image + "?msgBody=" + new String(msgBody);
        String ret = httpclient.post(url, data, "application/json");
        System.out.println("req: " + data);
        System.out.println("ret: " + ret);
        System.out.println("url: " + url);
        httpclient.checkStatusCode(200);

    }

    @JTest
    @JTestClass.title("add图片test0005_addImage")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0005_addImage() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectImage";
        req.cmd = "addImage";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", 33);
        byte[] msgBody = Base64.getEncoder().encode(req.toJSONStringPretty().getBytes());

        String filename = getClass().getResource("/test/testfile/2.jpg").getPath();
        System.out.println(filename);
        byte[] bData = Files.toByteArray(new File(filename));
        String url = url_upload + "?documentId=4&msgBody=" + new String(msgBody);
        String ret = httpclient.post(url, bData, ContentType.IMAGE_JPEG);
        System.out.println(url);
        System.out.println("req: " + req.toJSONStringPretty());
        httpclient.checkStatusCode(200);

    }


    @JTest
    @JTestClass.title("add图片test0005_addImage")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0006_modifyImage() throws IOException {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectImage";
        req.cmd = "modifyImage";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", 9);
        byte[] msgBody = Base64.getEncoder().encode(JSONObject.toJSON(req).toString().getBytes());
        data = new Gson().toJson(req).toString();
        String filename = getClass().getResource("/test/testfile/1.jpg").getPath();
        System.out.println(filename);
        byte[] bData = Files.toByteArray(new File(filename));

        String url = url_image + "?documentId=4&msgBody=" + new String(msgBody);
        // String ret = httpclient.post(url, bData, ContentType.IMAGE_JPEG);
        System.out.println("req: " + data);
        httpclient.checkStatusCode(200);

    }

    HttpEntity buildEntity(String url, String msgBody, File postFile) {
        MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
        multipartEntity.setContentType(ContentType.MULTIPART_FORM_DATA);
        multipartEntity.addTextBody("msgBody", msgBody);
        FileBody fundFileBin = new FileBody(postFile);
        multipartEntity.addPart("file", fundFileBin);
        return multipartEntity.build();
    }

    @JTest
    @JTestClass.title("upload图片 test0007_upload")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0007_upload() throws IOException {
        String url_image = "http://mysql.kunlong.com/rest/project/image";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectImage";
        req.cmd = "upload";
        req.msgBody = JSONObject.parseObject("{}");

        Mngr_Re_Document p = new Mngr_Re_Document();
        p.setDocType(Dict_document.DOC_TYPE_RAR);
        p.setSaveMode(Dict_document.SAVEMODE_PATH);
        p.setName("/test/testfile/ini.json");
        String filename1 = getClass().getResource(p.getName()).getPath();
        File postFile = new File(filename1);
        req.msgBody = JSONObject.parseObject(YtbUtils.toJSONStringPretty(p));
        byte[] msgBody = Base64.getEncoder().encode(req.toJSONStringPretty().getBytes("UTF-8"));
        String url = url_image + "?msgBody=" + new String(msgBody);
        HttpEntity entity = buildEntity(url, req.toJSONStringPretty(), postFile);
        String ret = httpclient.post(url, entity);
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        System.out.println(req.toJSONString());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url);
     }


    @JTest
    @JTestClass.title("test0008_download")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/download")
    @JTestClass.exp("ok")
    public void test0008_download() throws IOException {
        String url = "http://localhost/rest/project/download";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "download";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", 871);
        String data = JSONObject.toJSONString(req);
        byte[] res1 = httpclient.postForObject(url, data);
        //System.out.println(new String(res1));

        Files.write(res1, new File("d:/ytb_user/down.txt"));
        System.out.println(req.toJSONStringPretty());
        System.out.println(url);
    }

    @JTest
    @JTestClass.title("删除test0009_deleteDocImage")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0009_deleteDocument() {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "deleteDocument";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", 4);

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post(url_base, data, "application/json");
        System.out.println("req: " + data);
        System.out.println("ret: " + ret);
        httpclient.checkStatusCode(200);

    }


    @JTest
    @JTestClass.title("test0011_download_dbfile")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/download")
    @JTestClass.exp("ok")
    public void test0010_download_dbfile() throws UnsupportedEncodingException {
        String url = "http://localhost:80/rest/project/download";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "download";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", 112);
        String data = JSONObject.toJSONString(req);

        //byte[] rest = restTemplate.postForObject(url, data, byte[].class);
      /*  byte[] res1 = httpclient.postForObject(url, data);
        System.out.println(new String(res1));
        try {
            Files.write(res1, new File("d:/ytb_user/4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url);*/
    }


    @JTest
    @JTestClass.title("test0013_getJsonHeader")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0011_getJsonHeader() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "getJsonHeader";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", docid);

        data = new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base, data, "application/json");
        System.out.println("req: " + data);
        System.out.println("ret: " + ret);
        httpclient.checkStatusCode(201);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("v")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0012_modifyJsonHeader() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "modifyJsonHeader";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("documentId", docid);
        JSONObject header = new JSONObject();
        header.put("documentId", 22190);
        header.put("projectId", 22190);
        req.msgBody.put("header", header);

        data = new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base, data, "application/json");
        System.out.println("req: " + data);
        System.out.println("ret: " + ret);
        httpclient.checkStatusCode(201);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }

    @JTest
    @JTestClass.title("test0013_selectDocument")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0013_selectDocument() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "selectDocument";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("docListId", 32);
        req.msgBody.put("templateId", 45);
        req.msgBody.put("v", 2);

        String ret = httpclient.post(url_base, req.toJSONString(), "application/json");

        httpclient.checkStatusCode(201);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_base);
        checkEQ(0, resp.getRetcode());
    }

    @JTestClass.title("test0014_getReqDocument")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0014_getReqDocument() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "getDocumentReq";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("projectId", 445);
        String ret = httpclient.post(url_base, req.toJSONString(), "application/json");

        httpclient.checkStatusCode(projectId);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_base);
    }

    @JTest
    @JTestClass.title("test0015_selectDocument")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0015_selectDocument() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectDocument";
        req.cmd = "selectDocument";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("docListId", docLstId);
        String ret = httpclient.post(url_base, req.toJSONString(), "application/json");

        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_base);
    }

    @JTest
    @JTestClass.title("test0016_sgetInviteList")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0016_sgetInviteList() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "getInviteList";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 41);
        req.msgBody.put("reqType", 41);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("test0017_projectRelease")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0017_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "selectProjectList";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 41);
        req.msgBody.put("reqType", 41);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("test0017_projectRelease")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0018_projectRelease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyOrUserProManager";
        req.cmd = "getProjectListByComP";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("status", 0);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("test0019_DocumentAssistcreateASFolder")
    @JTestClass.pre("\"msgBody\":{\"test0019_DocumentAssistcreateASFolder\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0019_DocumentAssistcreateASFolder() throws Exception {
        UserProjectContext context = new Flow().buildContext(5103, 202);


        List<Integer> lst = new ArrayList<>();
        lst.add(1309);
        lst.add(1310);
        lst.add(1311);
        new TemplateAssist().createShareFolder(context, 1002, 196, lst);
        new TemplateAssist().createAssistFolder(context, 1002, 195, lst);
        new TemplateAssist().createAuditFolder(context, 1002, 196, lst);

    }

    @JTest
    @JTestClass.title("test0020_projectReleasemyFileFactory")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0020_projectReleasemyFileFactory() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "myFileFactory";
        req.msgBody = JSONObject.parseObject("{}");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("test0021_projectReleasemyFileFactory")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0021_projectReleasemyFileFactory() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "inviteMember";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("projectId",0).fluentPut("userId",123);
        req.msgBody.fluentPut("documentId",1358).fluentPut("userId",123);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }
    @JTest
    @JTestClass.title("test0021_projectReleasemyFileFactory")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0022_getFolders() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getFolders";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("projectId", 56).fluentPut("userId", 13);
        req.msgBody.fluentPut("talkId", 55).fluentPut("phase", 200);
        req.msgBody.fluentPut("userType", "PB");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("test0023_inviteMember")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0023_inviteMember() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "inviteMember";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("projectId", 216).fluentPut("userId", 336);
        req.msgBody.fluentPut("documentId", 803);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("exportTableProjectWorkjob")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0024_restTagTableServiceExportTablePrjectWorkjob() {
        req.cmdtype = "tagTableServiceProject";
        req.cmd = "exportAllTables";
        req.msgBody.fluentPut("projectId", 251).fluentPut("documentId", 1013);
        String url_manager = "http://localhost/rest/tagTableService/manager";

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("upload图片 test0007_upload")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0025_upload() throws IOException {
        String url_image = "http://mysql.kunlong.com/rest/project/image";
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectImage";
        req.cmd = "upload";
        req.msgBody = JSONObject.parseObject("{}");

        Mngr_Re_Document p = new Mngr_Re_Document();
        p.setDocType(Dict_document.DOC_TYPE_RAR);
        p.setSaveMode(Dict_document.SAVEMODE_PATH);
        p.setName("/test/testfile/ini.json");
        String filename1 = getClass().getResource(p.getName()).getPath();
        File postFile = new File(filename1);
        req.msgBody = JSONObject.parseObject(YtbUtils.toJSONStringPretty(p));
        byte[] msgBody = Base64.getEncoder().encode(req.toJSONStringPretty().getBytes("UTF-8"));
        String url = url_image + "?msgBody=" + new String(msgBody);
        HttpEntity entity = buildEntity(url, req.toJSONStringPretty(), postFile);
        String ret = httpclient.post(url, entity);
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        System.out.println(req.toJSONString());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url);
    }
    @JTest
    @JTestClass.title("上传文档test0026_uploadPICC")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/template/template/upload")
    @JTestClass.exp("ok")
    public void test0026_uploadPIC() throws UnsupportedEncodingException {
        String url = "http://localhost:80/rest/template/upload";

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "templateDocument";
        req.cmd = "uploadPIC";
        req.msgBody =  JSONObject.parseObject("{}");

        Mngr_Re_Document p=new Mngr_Re_Document();
        p.setName("1.jpg");      //p.setDocType(Dict_document.DOC_TYPE_XLS);
        p.setSaveMode(Dict_document.SAVEMODE_DB);
        p.setPicType("JPG");
        String filename=getClass().getResource("/test/testfile/1.jpg").getPath();
        File postFile = new File(filename);
        p.setDocPath("/test/testfile/1.jpg");
        req.msgBody =  JSONObject.parseObject(JSONObject.toJSONString(p));
        HttpEntity entity = RestTemplateManager.buildEntity(url, JSONObject.toJSONString(req), postFile);
        String ret = httpclient.post(url,entity);
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(0,resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());

        System.out.println(url);
    }
    @JTest
    @JTestClass.title("test0019_DocumentAssistcreateASFolder")
    @JTestClass.pre("\"msgBody\":{\"test0019_DocumentAssistcreateASFolder\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0027_DocumentAssistcreateASFolder() throws Exception {
        UserProjectContext context = new Flow().buildContext(5163, 202);

        List<Integer> lst = new ArrayList<>();
        lst.add(17432);
        lst.add(17433);
        lst.add(17434);
        new TemplateAssist().createShareFolder(context, 1002, 196, lst);
        new TemplateAssist().createAssistFolder(context, 1002, 195, lst);
        new TemplateAssist().createAuditFolder(context, 1002, 196, lst);

    }

    public static void main(String args[]) {
        run(TestProjectDocument.class, 27);

    }

}

