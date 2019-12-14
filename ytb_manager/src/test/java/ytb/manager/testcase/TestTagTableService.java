package ytb.manager.testcase;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;

import ytb.manager.ManagerCommon.ManagerConst;
import ytb.manager.ManagerCommon.ManagerLogin;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.manager.ManagerCommon.ITestYtb;
import ytb.manager.tagtable.service.impl.SelectProjectMember;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.UnsupportedEncodingException;
import java.util.*;


@JTestClass.author("leijm")
public class TestTagTableService extends ITestYtb {
    String url_manager = ManagerConst.url_manager;
    String url_project = ManagerConst.url_project;

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    MsgRequest req = new MsgRequest();
    String token = "";
    int req_id = 1350;
    int workplan_id = 1359;

    int project_id = 2488;
    int project_document_id = 38605;
    ManagerLogin login = new ManagerLogin();
    int apiKey = 1111;

    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;


    MsgRequest defaultReq() {
        MsgRequest req = new MsgRequest();
        req.token = token;

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportTable";
        req.msgBody = JSONObject.parseObject("{}");
        // req.setApiKey(apiKey + "");
        req.setApiKey(login.getApiKey());
        req.setSign(apiKey + "");
        return req;
    }

    @Override
    public void setUp() {
        token = login.login();
        req = defaultReq();
        req.setApiKey(login.getApiKey());
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
        req.cmd = "project_workjob";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 1375);
        req.msgBody.put("tableName", "project_workjob");

        String ret = httpclient.post(url_manager, req.toString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());

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
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 1375);
        req.msgBody.fluentPut("projectId", 216).fluentPut("documentId", 803);

        String ret = httpclient.post(url_project, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_project);

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
        req.msgBody.put("documentId", workplan_id);
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
        req.msgBody.put("documentId", workplan_id);
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
        req.msgBody.put("documentId", req_id);
        req.msgBody.put("tableName", "ytb_manager.dict_document");

        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toJSONStringPretty());
    }

    @JTest
    @JTestClass.title("exportTableProjectWorkjob")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")

    public void test0016_restTagTableServiceExportTablePrjectWorkjob() throws Exception {

        req.cmdtype = "tagTableServiceProject";
        req.cmd = "exportAllTables";
        req.msgBody.fluentPut("projectId", project_id).fluentPut("documentId", project_document_id);

        String ret = httpclient.post(url_project, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
//        dbProject.appendSql("select * from ytb_project.work_group_member where project_id=").
//                appendSql(project_id).appendSql(" and  document_id=").appendSql(project_document_id)
//                .checkRecordNumber(3).execSelect().checkRecord("project_id=" + project_id);
//
//        dbProject.appendSql("select * from ytb_project.work_group_member where project_id=").
//                appendSql(project_id).appendSql(" and document_id=").appendSql(project_document_id).appendSql(" and is_admin=2")
//                .checkColumn("project_id=" + project_id);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url_project);
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("test0017_restTagTableServiceExportAllTablesb")
    @JTestClass.pre(" exportAllTablesWorkGroupPlan")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0017_restTagTableServiceExportAllTables() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportAllTables";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", workplan_id);
        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(url_manager);
        System.out.println(ret);
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("test0018_exportAllTablesWorkGroupPlan_manager")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")   //4case exportAllTablesWorkGroupPlan
    public void test0018_exportAllTablesWorkGroupPlan_manager() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportAllTables";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", workplan_id);

        String ret = httpclient.post(url_manager, req.toJSONStringPretty(), "application/json");
        httpclient.checkStatusCode(200);//dict_workjob_check
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONStringPretty());
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("test0019_exportexportAllTables_project")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/project")
    @JTestClass.exp("ok")
    public void test0019_exportexportAllTables_project() {

        req.cmdtype = "tagTableServiceProject";
        req.cmd = "exportAllTables";
        req.msgBody.put("projectId", project_id);
        req.msgBody.put("documentId", project_document_id);


        String ret = httpclient.post(url_project, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONString());
        System.out.println(JSONObject.toJSONString(req));
        System.out.println(req.toJSONString());
        System.out.println(url_project);
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("test0020_restSelectTable_project_member")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0020_restSelectTable_project_member() {

        req.cmdtype = "tagTableServiceProject";
        req.cmd = "project_member";
        req.msgBody.fluentPut("projectId", project_id).fluentPut("documentId", project_document_id);

        //String url_project = "http://project.youtobon.com/rest/tagTableService/manager";
        String ret = httpclient.post(url_project, JSONObject.toJSONString(req), "application/json");
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
    @JTestClass.title("test0021_restSelectProjectDutyTask")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0021_restSelectProjectDutyTask() {

        req.cmdtype = "tagTableServiceProject";
        req.cmd = "selectProjectDutyTask";
        req.msgBody.fluentPut("projectId", 444).fluentPut("documentId", 2200);

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url_manager);

    }

    @JTest
    @JTestClass.title("test0022_restGetProject_Rate")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0022_restGetProject_Rate() {
        req.token = token;
        req.cmdtype = "tagTableServiceProject";
        req.cmd = "project_rate";
        req.msgBody.put("projectId", 0);
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());

    }

    @JTest
    @JTestClass.title("test0023_restExpAlltables")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0023_restExpAlltables() {
        req.token = token;
        req.cmdtype = "tagTableServiceProject";
        req.cmd = "exportAllTables";
        req.msgBody.put("projectId", 115);
        req.msgBody.put("documentId", 472);

        //String ret = httpclient.post(url_manager182, req.toJSONString(), "application/json");
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url_manager);

    }


    @JTest
    @JTestClass.title("test0024_exportAllTables")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0024_exportAllTables() {
        req.cmdtype = "tagTableServiceManager";    //req.cmd = "exportTableProjectWorkjob";
        req.cmd = "exportAllTables";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 786);//774 786
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONStringPretty());
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("test0025_restSelectTable_project_member")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0025_restSelectTable_project_member() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "project_member";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 366);


        String ret = httpclient.post(url_manager, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toString());
        System.out.println(url_manager);
        Arrays.stream(resp.msgBody.getJSONArray("list").toArray()).forEach
                (System.out::println);

    }


    @JTest
    @JTestClass.title("test0026_restSelectTable")
    @JTestClass.pre("{\"cmd\":\"selectTable\",\"cmdtype\":\"tagTableServiceProject\",\"tableName\":\"electric_mat\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0026_restSelectTable() {

        req.cmdtype = "tagTableServiceProject";
        req.cmd = "selectTable";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 730);
        req.msgBody.put("tableName", "electric_mat");
        String ret = httpclient.post(url_project, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_project);
        System.out.println(req.toJSONStringPretty());
    }

    @JTest
    @JTestClass.title("exportTableProjectWorkjob")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0027_restTagTableServiceExportTablePrjectWorkjob() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportAllTables";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 897);


        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("test0028_modifyDocByTagTableParam")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0028_modifyDocByTagTableParam() {
        req.cmdtype = "tagTableServiceProject";
        req.cmd = "modifyDocByTagTableParam";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 928);
        //req.msgBody.put("projectId", 1);
        //req.msgBody.put("documentId", 100);

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }

    @JTest
    @JTestClass.title("test0029_listTagTitleRed")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0029_listTagTitleRed() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "listTagTitleRed";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 340);

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
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
    public void test0030_refDocumentReqDir() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "refDocumentReqDir";
        req.msgBody.put("projectId", 0);
        req.msgBody.put("documentId", 12367);
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        System.out.println(url_manager);
        checkEQ(0, resp.getRetcode());
        //System.out.println(new TagTableProjectService().refDocumentReqDir(0, 230));
    }

    @JTest
    @JTestClass.title("test0031_refTagTableParamTask")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0031_refTagTableParamTask() {

        req.cmdtype = "tagTableServiceManager";
        req.cmd = "refTagTableParam";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 230);
        req.msgBody.put("tag", "tagTableParamRef@project.project_name$A1.11");
        req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
        req.msgBody.put("tag", "tagTableParamRef@岗位任务.任务名称(电路)$A.11");
        req.msgBody.put("tag", "tagTableParamRef@岗位任务.任务名称(机械)$A.11");
        req.msgBody.put("tag", "tagTableParamRef@岗位任务.岗位名称(机械)$A.11");
        //req.msgBody.put("tag","tagTableParamRef@岗位任务.岗位名称(电路)$A.11");
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_manager);
        checkEQ(0, resp.getRetcode());

    }


    @JTest
    @JTestClass.title("test0032_refTagTableParamProject")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0032_refTagTableParamProject() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "refTagTableParam";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 973);

        req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
        //req.msgBody.put("tag","tagTableParamRef@岗位任务.任务名称(电路)$A.11");
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 1359);
        req.msgBody.put("tag", "tagTableTextRef@岗位任务.任务名称(机械)$A.11");
        req.msgBody.put("tag", "tagTableTextRef@岗位任务.岗位名称(机械)$A.11");
        //req.msgBody.put("tag", "tagTableParamRef@工作组.工作组名称");
        //req.msgBody.put("tag", "tagTableParamRef@work_group.group_name");
        //req.msgBody.put("tag","tagTableParamRef@test_report_check_sum.percent_sum$A3");
        //req.msgBody.put("tag","tagTableRef@数据字典(项目变更类别)");
        // req.msgBody.fluentPut("projectId", 1).fluentPut("documentId", 423);
        //req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
        //req.msgBody.put("tag", "tagTableParamRef@数据字典.字典名称(项目变更类别)");
        //req.msgBody.fluentPut("projectId", 5).fluentPut("documentId", 14);
        req.msgBody.put("tag", "tagTableParamRef@乙方组长.呢称");
        req.msgBody.put("tag", "tagTableParamRef@乙方组员.呢称");

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_manager);
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }

    //System.out.println(YtbSql.fnDb(ytb_project.fnCheckWorkjobChange", 0, 1350, 1375));
    //TemplateDocumentServiceImpl.getTemplateDocumentService().tagTableRefHandler(0, 1278);
    @JTest
    @JTestClass.title("test0033_restTagTableServiceExportTablePrjectWorkjob")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0033_restTagTableServiceExportTablePrjectWorkjob() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportAllTables";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", req_id);
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 1375);

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }
    @JTest
    @JTestClass.title("test0032_refTagTableParamProject")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0034_testRefTagTableParamProject() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "testRefTagTableParam";
        req.msgBody.fluentPut("projectId", 0).fluentPut("documentId", 973);
        req.msgBody.fluentPut("functionId",10);
//        req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
//        req.msgBody.put("tag", "tagTableParamRef@乙方组长.呢称");
//        req.msgBody.put("tag", "tagTableParamRef@乙方组员.呢称");

        String ret = httpclient.post(url_manager, req.toString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        System.out.println(url_manager);
    }

    @JTest
    @JTestClass.title("test0007_restSelectTable_project_member")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0035_restProject_member() {
        LoginSsoJson loginSsoJson = login.getLogSso(token, req);


        Collection<SelectProjectMember.DayPayInfoResult> dayPayInfos =
                new SelectProjectMember().selectList(2488, 38605, loginSsoJson);
        System.out.println(dayPayInfos);

    }


    @JTest
    @JTestClass.title("test0032_refTagTableParamProject")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0036_refTagTableParamProject() {
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "refTagTableParam"; ;

        req.msgBody.fluentPut("projectId", 1912);//.fluentPut("documentId", 30386);
        req.msgBody.put("tag", "tagTableParamRef@项目.项目名称$A1.11");
        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        System.out.println(req.toJSONStringPretty());
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
    }

    public static void main(String[] args) throws Exception {

         //  run(TestTagTableService.class, 36);//16
         run(TestTagTableService.class, 1);

    }


}


