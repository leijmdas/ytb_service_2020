package ytb.manager.testcase;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.manager.templateexcel.service.impl.TemplateDocumentServiceImpl;
import ytb.common.RestMessage.MsgRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ytb.manager.tagtable.service.impl.TagTableServiceImpl.getTagTableService;

public class TestRestTemplateExcel extends ITestImpl {

    String url = "http://localhost/rest/template";
    String token = "6ad91a1523ec48ffbe115ea442c121ec";
    private String url_manager = "http://admin.youtobon.com/rest/template";
    String apiKey = "1556087424423";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    MsgRequest req = new MsgRequest();

    @Override
    public void suiteSetUp() {
    }

    @Override
    public void suiteTearDown() throws IOException {

    }

    @Override
    public void setUp() {
        req.setToken(token);
        long l = System.currentTimeMillis();
        req.setReqtime(l);
        req.setSeqno(l);
        req.setApiKey(apiKey+"");
    }

    @Override
    public void tearDown() throws IOException {

    }

    @JTest
    @JTestClass.title("转换模板")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://localhost/rest/template")
    @JTestClass.exp("ok")
    public void test00001() {
        req.cmdtype = "projType";
        req.cmd = "parseDocTemplate";
//       req.msgBody.put("templateId", 469);
        req.msgBody.put("templateId", 543);
        System.out.println("【Post ReqMsg】" + JSON.toJSONString(req, true));
        String r = httpclient.post(url_manager, JSON.toJSONString(req), "application/json");
        System.out.println("【Post ResMsg】");
        System.out.println(JSON.toJSONString(r, true));
    }

    @JTest
    @JTestClass.title("查询需求说明书导出的岗位 写入工作组计划 工作组任务表")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://localhost/rest/template")
    @JTestClass.exp("ok")
    public void test00002() {
        int reqDocumentId = 0;
        List<Map<String, Object>> list = getTagTableService().selectTable(0, reqDocumentId, "project_workjob");

       // list = new TagTableWorkGroup().spInitWorkplanWorkjob(31,105);
      //  System.out.println(list);
      //  TemplateDocumentServiceImpl.getTemplateDocumentService().modifyWorkplanWorkJob(0, 974, list);
    }

    @JTest
    @JTestClass.title("获取文档的目录树表数据")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://localhost/rest/template")
    @JTestClass.exp("ok")
    public void test00003() {
        JSONArray documentDirList = TemplateDocumentServiceImpl.getTemplateDocumentService().getDocumentDirList(0, 974);
        System.out.println(documentDirList);
    }


    public static void main(String[] args) {
        run(TestRestTemplateExcel.class, 1);
    }

}
