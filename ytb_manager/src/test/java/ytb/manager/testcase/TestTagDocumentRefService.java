package ytb.manager.testcase;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.manager.tagtable.model.tagtemplate.Document_RefModel;
import ytb.manager.tagtable.service.tagtemplate.DocumentRefService;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.Ytb_Model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;


@JTestClass.author("leijm")
public class TestTagDocumentRefService extends ITestImpl {
    String url_project = "http://localhost/rest/tagTableService/project";
    String url_project_182 = "http://project.youtobon.com/rest/tagTableService/project";
    String url_manager = "http://localhost/rest/tagTableService/manager";
    String url_manager182 = "http://project.youtobon.com/rest/tagTableService/manager";

    MsgRequest req = new MsgRequest();

    String token = "d98c3b969dc34aaa92942c8a9c646f2a";
    int documentId_workgroupPlan = 411;
    int documentId_reqdoc = 341;
    int documentId = 341;
    int project_id = 164;
    int project_document_id = 707;
    int project_documentId_workgroupPlan = 707;
    int apiKey = 1111;

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {
    }

    MsgRequest defaultReq() {
        MsgRequest req = new MsgRequest();
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportTable";
        req.msgBody = JSONObject.parseObject("{}");
        req.setApiKey(apiKey+"");
        req.setSign(apiKey+"");
        return req;
    }

    @Override
    public void setUp() {
        req = defaultReq();
    }

    @Override
    public void tearDown() {

    }

    @JTest
    @JTestClass.title("test0001_restTagTableService_exportTable")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0001_restTagTableService_exportTable() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportTable";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 205);
        req.msgBody.put("tableName", "work_group_task");
        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);
        System.out.println(ret);
        checkEQ(0, resp.getRetcode());
    }


    @JTest
    @JTestClass.title("test0002_restTagTableService_selectTable")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0002_restTagTableService_selectTable() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "selectTable";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 205);
        req.msgBody.put("tableName", "work_group_task");
        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);
        Arrays.stream(resp.msgBody.getJSONArray("list").toArray()).forEach(
                x -> System.out.println(x)
        );
    }
    //{"cmd":"electric_mat","cmdtype":"tagTableServiceProject"}

    /*SONObject s = TemplateDocumentServiceImpl.getTemplateDocumentService().getTemplateTable_json(205, "work_group_task");
    JSONObject j = s.getJSONArray("body").getJSONObject(0).getJSONArray("value").getJSONObject(0);
    DBTagTable table = DBTagTable.parseTagTable(JSONObject.toJSONString(j));
    String ss = RestHandler.toJSONStringPretty(table.getValue()); */
    @JTest
    @JTestClass.title("test0003_restTagTableService_deleteTable")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0003_restTagTableService_deleteTable() {

        req.cmdtype = "tagTableServiceManager";
        req.msgBody.put("projectId", 0);
        req.cmd = "deleteTable";
        req.msgBody.put("documentId", 205);
        req.msgBody.put("tableName", "work_group_task");
        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);
        System.out.println(ret);
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("二次转换工作任务表exportTableWorkgroupTask")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0004_restTagTableServiceExportTableWorkgroup() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportTableWorkgroupTask";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 625);
        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);
        System.out.println(resp.toJSONString());
        checkEQ(0, resp.getRetcode());
    }

    //TagTableServiceImpl.getTagTableService().exportTable(202,"work_group_task");metadata_dict
    @JTest
    @JTestClass.title("test0005_restSelectTable_project_workjob")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0005_restSelectTable_project_workjob() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "selectTable";
        req.msgBody.put("projectId", 102);
        req.msgBody.put("reqDocumentId", 0);
        req.msgBody.put("tableName", "project_workjob");
        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);
        Arrays.stream(resp.msgBody.getJSONArray("list").toArray()).forEach(
                x -> System.out.println(x)
        );
    }

    @JTest
    @JTestClass.title("test0006_restSelectProject_workjob")
    @JTestClass.pre("reqDocumentId")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0006_restSelectProject_workjob() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "project_workjob";
        req.msgBody.put("projectId", 233);
        req.msgBody.put("reqDocumentId", 893);
        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);

        System.out.println(resp.toJSONStringPretty());
    }

    @JTest
    @JTestClass.title("test0007_restSelectTable_project_member")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0007_restSelectTable_project_member() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "project_member";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 411);


        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);
        Arrays.stream(resp.msgBody.getJSONArray("list").toArray()).forEach(
                x -> System.out.println(x)
        );
    }

    @JTest
    @JTestClass.title("test0007_restSelectTable_project_member")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0008_restSelectTable_project_member() {
        //2d30c56a458d4140a4fc1052b439c3b6
         req.token = token;
        req.cmdtype = "repository";
        req.cmd = "getRepositoryData";
        String url_mngr = "http://localhost/rest/template";
        req.msgBody.put("projectId", project_id);
        req.msgBody.put("documentId", project_document_id);
        String ret = httpclient.post(url_mngr, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(req.token);
        checkEQ(0, resp.getRetcode());
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_mngr);
        System.out.println(ret);
    }


    static byte[] fromHexStringToBytes(String hexString) {
        if ((hexString == null) || (hexString.equals("")) || hexString.length() % 2 != 0) {
            return null;
        } else {
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] bytec = hexString.toCharArray();
            byte[] bit = new byte[length];
            for (int i = 0; i < length; i++) {
                int p = 2 * i;
                //两个十六进制字符转换成1个字节，第1个字符转换成byte后左移4位，然后和第2个字符的byte做或运算
                bit[i] = (byte) (fromCharToByte(bytec[p]) << 4 | fromCharToByte(bytec[p + 1]));
            }
            return bit;
        }
    }

    static byte fromCharToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    @JTest
    @JTestClass.title("需求说明书 test0009_restMnager_SelectTableReq")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0009_restMnager_SelectTableReq() throws UnsupportedEncodingException {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "selectTable";
        //req.msgBody.put("projectId",1);
        req.msgBody.put("documentId", 306);
        req.msgBody.put("dbName", "ytb_manager");
        req.msgBody.put("tableName", "dict_document");

        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);
        String hexStr = resp.getMsgBody().getJSONArray("list").getJSONObject(0).getString("document");
        byte[] rr = Base64.getDecoder().decode(hexStr.getBytes("UTF-8"));
        String ss = new String(rr, "UTF-8");
        System.out.println(MsgHandler.toJSONStringPretty(JSONObject.parseObject(ss)));
    }

    @JTest
    @JTestClass.title("需求说明书 test0010_restProject_SelectTableReq")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0010_restProject_SelectTableReq() throws UnsupportedEncodingException {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "selectTable";
        req.msgBody.put("documentId", 197);
        req.msgBody.put("dbName", "ytb_project");
        req.msgBody.put("tableName", "project_document");

        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);
        String bStr = resp.getMsgBody().getJSONArray("list").getJSONObject(0).getString("document");
        String ss = MsgHandler.base64toStr(bStr);
        System.out.println(MsgHandler.toJSONStringPretty(JSONObject.parseObject(ss)));
    }

    @JTest
    @JTestClass.title("需求说明书 test0011_restSelectDocumentTable")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0011_restSelectDocumentTable() throws UnsupportedEncodingException {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "selectDocument";
        req.msgBody.put("documentId", project_document_id);
        req.msgBody.put("tableName", "project_document");

        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        String bStr = resp.getMsgBody().getJSONArray("list").getJSONObject(0).getString("document");
        String s = MsgHandler.base64toStr(bStr);
        System.out.println(s);
    }

    @JTest
    @JTestClass.title("需求说明书 test0012_restSelectMngrDocumentTable")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0012_restSelectMngrDocumentTable() throws UnsupportedEncodingException {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "selectDocument";
        req.msgBody.put("documentId", 306);
        req.msgBody.put("tableName", "ytb_manager.dict_document");

        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);
    }


//    work_group_task  project_work_day
//    project_plan project_plan_sum
//    pp_budget  pp_budget
//    cost cost_sum
    @JTest
    @JTestClass.title("test0013_restTagTableService_lsttagTableName ")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0013_restTagTableService_lstagTableName() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "lstTagTableName";
        req.msgBody.put("documentId", documentId_workgroupPlan);
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.err.println(req.toJSONString());
        System.out.println(url_manager);
        System.out.println(ret);
        checkEQ(0, resp.getRetcode());
    }


    @JTest
    @JTestClass.title("test0014_restTagTableService_exportTablework_group ")
    @JTestClass.pre("work_group_task project_work_day project_plan project_plan_sum pp_budget")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0014_restTagTableService_exportTablework_group() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportTable";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", documentId_workgroupPlan);
      /*  需求说明书导出岗位
          work_group_task */
        req.msgBody.put("tableName", "pp_budget");
        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONString());
        System.out.println(url_manager);
        System.out.println(ret);
        checkEQ(0, resp.getRetcode());
    }


    @JTest
    @JTestClass.title("需求说明书 test0015_restSelectReqMngrDocumentTable")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok tempid199")
    public void test0015_restSelectReqMngrDocumentTable() throws UnsupportedEncodingException {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "selectDocument";
        req.msgBody.put("documentId", documentId_reqdoc);
        req.msgBody.put("tableName", "ytb_manager.dict_document");

        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toJSONStringPretty());
    }

        @JTest
        @JTestClass.title("test0031_refTagTableParamTask")
        @JTestClass.pre("")
        @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
        @JTestClass.exp("ok")
        public void test0016_refTagTableParamTask() {

            req.cmdtype = "tagTableServiceManager";
            req.cmd = "refTagTableParam";
            req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 230);
            req.msgBody.put("tag","tagTableParamRef@project.project_name$A1.11");
            req.msgBody.put("tag","tagTableParamRef@项目.项目名称$A1.11");
            req.msgBody.put("tag","tagTableParamRef@岗位任务.任务名称(电路)$A.11");
            req.msgBody.put("tag","tagTableParamRef@岗位任务.任务名称(机械)$A.11");
            req.msgBody.put("tag","tagTableParamRef@岗位任务.岗位名称(机械)$A.11");
            //req.msgBody.put("tag","tagTableParamRef@岗位任务.岗位名称(电路)$A.11");
            String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
            httpclient.checkStatusCode(200);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            System.out.println(resp.toJSONStringPretty());
            System.out.println(url_manager);
            System.out.println(req.toJSONStringPretty());
            System.out.println(resp.toJSONStringPretty());
            checkEQ(0, resp.getRetcode());
            //System.out.println(new TagTableProjectService().refDocumentReqDir(0, 230));
        }

        @JTest
        @JTestClass.title("test0032_refTagTableParamProject")
        @JTestClass.pre("")
        @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
        @JTestClass.exp("ok")
        public void test0017_refTagTableParamProject() {
            req.cmdtype = "tagTableServiceManager";
            req.cmd = "refTagTableParam";
            req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 1278);

            req.msgBody.put("tag","tagTableParamRef@项目.项目名称$A1.11");
            //req.msgBody.put("tag","tagTableParamRef@岗位任务.任务名称(电路)$A.11");
            req.msgBody.put("tag","tagTableParamRef@岗位任务.任务名称(机械)$A.11");
            req.msgBody.put("tag","tagTableParamRef@岗位任务.岗位名称(机械)$A.11");
            req.msgBody.put("tag","tagTableParamRef@数据字典.字典名称(项目变更类别)");
            req.msgBody.put("tag","tagTableParamRef@test_report_check_sum.percent_sum$A3");

            String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
            httpclient.checkStatusCode(200);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            System.out.println(resp.toJSONStringPretty());
            System.out.println(url_manager);
            System.out.println(req.toJSONStringPretty());
            System.out.println(resp.toJSONStringPretty());
            checkEQ(0, resp.getRetcode());
         }

     public  static class User extends Ytb_Model {
        int userId;
        String userName;

        public int getUserId() {
        return userId;
    }

        public void setUserId ( int userId){
        this.userId = userId;
    }

        public String getUserName () {
        return userName;
    }

        public void setUserName (String userName){
        this.userName = userName;
    }

     }

    @JTest
    @JTestClass.title("exportTableProjectWorkjob")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0018_restTagTableServiceExportTablePrjectWorkjob() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportAllTables";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 411);
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 1278);
        String ret = httpclient.post(url_project, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("test0030_refDocumentReqDir")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0019_refDocumentReqDir() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "refDocumentReqDir";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 1278);
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url_manager);
        checkEQ(0, resp.getRetcode());
        //System.out.println(new TagTableProjectService().refDocumentReqDir(0, 230));
    }

    //run(TestTagTableService.class, 3,1,2);
    @JTest
    @JTestClass.title("test0030_refDocumentReqDir")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0020_insertDictDocumentRefService() {
        Document_RefModel m = new Document_RefModel();
        m.setpDocType((short) 100);
        m.setRepositoryId(0);
        m.setProjectId(0);
        m.setUserId(-1);
        m.setDocumentId(111);
        m.setDocType((short) 200);
        m.setDocTimeSync(new Timestamp(System.currentTimeMillis()));
        m.setpDocTime(new Timestamp( System.currentTimeMillis()));
        m.setpDocumentId(1);
        int id = DocumentRefService.getDocumentRefService().insertRef(m);
        System.out.println(id);
        m.setRefId(id);
        System.out.println(m);
        DocumentRefService.getDocumentRefService().updateRef(m);
        m.setpDocTime(new Timestamp(System.currentTimeMillis()));
        DocumentRefService.getDocumentRefService().updatePDocTime(0, 0, 111, (short) 100, new Timestamp(System.currentTimeMillis()));
        //DocumentRefService.getDocumentRefService().deleteRef(0, id,0);
    }

    public static void main(String[] args) throws Exception {

        //run(TestTagDocumentRefService.class, 19);
        DocumentRefService.getDocumentRefService().setUpRefAll(12,0,0);

    }



}

