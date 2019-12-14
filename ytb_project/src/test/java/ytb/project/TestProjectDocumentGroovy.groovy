package ytb.project

import com.alibaba.fastjson.JSONObject
import com.google.common.io.Files
import com.google.gson.Gson
import com.jtest.NodesFactroy.Inject.Inject
import com.jtest.NodesFactroy.Node.HttpClientNode
import com.jtest.annotation.JTest
import com.jtest.annotation.JTestClass
import com.jtest.testframe.ITestImpl
import org.apache.http.entity.ContentType
import ytb.common.RestMessage.MsgRequest

/**
 * Package: projecttest.companyProManagerTest
 * Author: ZCS
 * Date: Created in 2018/10/17 17:20
 */


class TestProjectDocumentGroovy  extends ITestImpl {
        String url_base="http://mysql.kunlong.com:80/rest/project/template";
        String url_image="http://mysql.kunlong.com:80/rest/project/image";
        @Inject(filename = "node.xml", value = "httpclient")
        HttpClientNode httpclient;
    String r='''------WebKitFormBoundaryAyEwfJHpBOm44uLu
        Content-Disposition: form-data; name="file1"; filename="需求说明书-案例V0.3.xlsx"
        Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet


                ------WebKitFormBoundaryAyEwfJHpBOm44uLu
        Content-Disposition: form-data; name="templateId"

        163
                ------WebKitFormBoundaryAyEwfJHpBOm44uLu--*/
            //Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryAyEwfJHpBOm44uLu'''
    // String ret = httpclient.post(url, r.getBytes(), ContentType.MULTIPART_FORM_DATA);
        MsgRequest req = new MsgRequest();

        String token="8cd0571369f94360badfbd776e7e3cf1";
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
            req.msgBody =  JSONObject.parseObject("{}");
            req.msgBody.put("documentId",1);
            req.msgBody.put("template","{}");

            data=new Gson().toJson(req).toString();
            String ret = httpclient.post(url_base,data , "application/json");
            System.out.println("req: "+data);
            System.out.println("ret: "+ret);
            httpclient.checkStatusCode(200);
            JSONObject json=JSONObject.parseObject(ret);
            checkEQ(0,json.getInteger("retcode"));
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
            req.msgBody =  JSONObject.parseObject("{}");
            req.msgBody.put("documentId",4);

            data=new Gson().toJson(req).toString();
            String ret = httpclient.post(url_base,data , "application/json");
            System.out.println("req: "+data);
            System.out.println("ret: "+ret);
            httpclient.checkStatusCode(200);
            JSONObject json=JSONObject.parseObject(ret);
            checkEQ(0,json.getInteger("retcode"));
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
            req.msgBody =  JSONObject.parseObject("{}");
            req.msgBody.put("documentId",1);
            JSONObject body =  JSONObject.parseObject("{}");
            body.put("header","{}");
            req.msgBody.put("template",body);

            data=new Gson().toJson(req).toString();
            String ret = httpclient.post(url_base,data , "application/json");
            System.out.println("req: " + data);
            System.out.println("ret: " + ret);
            httpclient.checkStatusCode(200);
            JSONObject json = JSONObject.parseObject(ret);
            checkEQ(0, json.getInteger("retcode"));
        }

        @JTest
        @JTestClass.title("图片预览test0004_previewImage")
        @JTestClass.pre("")
        @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
        @JTestClass.exp("ok")
        public void test0004_previewImage() {
            req.token = token;
            req.reqtime = System.currentTimeMillis();
            req.seqno = System.currentTimeMillis();
            req.cmdtype = "projectImage";
            req.cmd = "previewImage";
            req.msgBody =  JSONObject.parseObject("{}");
            req.msgBody.put("documentId",24);
            byte[] msgBody = Base64.getEncoder().encode(JSONObject.toJSON(req).toString().getBytes());
            data=new Gson().toJson(req).toString();
            String url = url_image+"?msgBody="+new String(msgBody);
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
            req.msgBody =  JSONObject.parseObject("{}");
            req.msgBody.put("documentId",33);
            byte[] msgBody = Base64.getEncoder().encode(JSONObject.toJSON(req).toString().getBytes());
            data=new Gson().toJson(req).toString();
            String filename=getClass().getResource("/testfile/2.jpg").getPath();
            System.out.println(filename);
            byte[] bData = Files.toByteArray(new File(filename));

            String url = url_image+"?documentId=4&msgBody="+new String(msgBody);
            String ret = httpclient.post(url, bData, ContentType.IMAGE_JPEG);
            System.out.println("req: " + data);
            System.out.println("ret: " + ret);
            httpclient.checkStatusCode(200);
            //new DocumentToolService().add(bData);

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
            req.msgBody =  JSONObject.parseObject("{}");
            req.msgBody.put("documentId",9);
            byte[] msgBody = Base64.getEncoder().encode(JSONObject.toJSON(req).toString().getBytes());
            data=new Gson().toJson(req).toString();
            String filename=getClass().getResource("/testfile/1.jpg").getPath();
            System.out.println(filename);
            byte[] bData = Files.toByteArray(new File(filename));

            String url = url_image+"?documentId=4&msgBody="+new String(msgBody);
            String ret = httpclient.post(url, bData, ContentType.IMAGE_JPEG);
            System.out.println("req: " + data);
            System.out.println("ret: " + ret);
            httpclient.checkStatusCode(200);

        }

        @JTest
        @JTestClass.title("upload图片 test0007_upload")
        @JTestClass.pre("")
        @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
        @JTestClass.exp("ok")
        public void test0007_upload() throws IOException {
            req.token = token;
            req.reqtime = System.currentTimeMillis();
            req.seqno = System.currentTimeMillis();
            req.cmdtype = "projectImage";
            req.cmd = "upload";
            req.msgBody =  JSONObject.parseObject("{}");
            byte[] msgBody = Base64.getEncoder().encode(JSONObject.toJSON(req).toString().getBytes());
            data=new Gson().toJson(req).toString();
            String filename=getClass().getResource("/testfile/1.jpg").getPath();
            System.out.println(filename);
            byte[] bData = Files.toByteArray(new File(filename));
            String url = url_image+"?msgBody="+new String(msgBody);
            String ret = httpclient.post(url, bData, ContentType.MULTIPART_FORM_DATA);
            System.out.println("req: " + data);
            System.out.println("ret: " + ret);
            httpclient.checkStatusCode(200);

        }

        @JTest
        @JTestClass.title("删除test0008_deleteDocImage")
        @JTestClass.pre("")
        @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
        @JTestClass.exp("ok")
        public void test0008_deleteDocImage() {
            req.token = token;
            req.reqtime = System.currentTimeMillis();
            req.seqno = System.currentTimeMillis();
            req.cmdtype = "projectDocument";
            req.cmd = "deleteDocument";
            req.msgBody =  JSONObject.parseObject("{}");
            req.msgBody.put("documentId",4);

            data=new Gson().toJson(req).toString();

            String ret = httpclient.post(url_base, data, "application/json");
            System.out.println("req: " + data);
            System.out.println("ret: " + ret);
            httpclient.checkStatusCode(200);

        }

        @JTest
        @JTestClass.title("test0009_upload")
        @JTestClass.pre("")
        @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
        @JTestClass.exp("ok")
        public void test0009_upload() throws IOException {
            req.token = token;
            req.reqtime = System.currentTimeMillis();
            req.seqno = System.currentTimeMillis();
            req.cmdtype = "projectImage";
            req.cmd = "upload";
            req.msgBody =  JSONObject.parseObject("{}");
            data=new Gson().toJson(req).toString();

            String url="http://mysql.kunlong.com:80/rest/project/upload";
            File postFile = new File("D:/1.txt");

            String result=TestMultiPartPost.post (url,data,postFile);
            println result;
            println data
        }

    public static void main(  args ) {
        run(TestProjectDocumentGroovy.class, 13);
    }

    }



