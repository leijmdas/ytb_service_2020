package ytb.projectnew;

import com.alibaba.fastjson.JSONObject;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import ytb.common.*;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.ytblog.YtbLog;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.view.model.ProjectTaskViewResult;
import ytb.project.view.service.impl.ProjectTaskViewPhase;
import ytb.manager.tagtable.service.impl.SelectProjectMember;
import ytb.manager.templateexcel.model.tag.TagTable;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import java.util.*;
import java.util.stream.Collectors;

public class TestPhase extends ITestYtb {

    String url_projectCenter = TestProjectConst.url_projectCenter;

    TagTable tagTable = new TagTable();
    Flow flow = new Flow();

    String friends = TestProjectConst.friends;

    int paId = TestProjectConst.paId;
    int pbId = TestProjectConst.pbId;
    int pmId = TestProjectConst.pmId;

    String tokenPa, tokenPb, tokenPm;
    MsgRequest req = new MsgRequest();
    String tokenPb1301 ;
    String tokenPb186;



    @Override
    public void setUp() throws Exception {
        tokenPa = userLogin.login(paId);
        tokenPb = userLogin.login(pbId);
        tokenPm = userLogin.login(pmId);

        tokenPb1301 = userLogin.login(1301);
        req.token = tokenPa;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
    }

    @Override
    public void tearDown() throws Exception {

        System.out.println(UserLogin.codeMsg);
    }

    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }

    @JTest
    @JTestClass.title("发布项目第一步test0001_publishFirst")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/")
    @JTestClass.exp("ok")
    public void test0001_publishFirst() throws Exception {
        flow.publishFirst( req,"" );
        System.out.println("发布成功！");
    }

    @JTest
    @JTestClass.title("test0002_talkPayFirst")
    @JTestClass.pre("")
    @JTestClass.step("test0002_talkPayFirst ")
    @JTestClass.exp("ok")
    public void test0002_talkPayFirst() throws Exception {
        flow.payFirst(req, tokenPa);
        flow.getMap_talkid2token();
        //组员提交，组长审核
    }

    @JTest
    @JTestClass.title("组员提交")
    @JTestClass.pre("")
    @JTestClass.step("test0003_talkPayFirstPmSubmit ")
    @JTestClass.exp("ok")
    public void test0003_talkPayFirstPmSubmit() throws Exception {

        flow.payFirst(req, tokenPa);
        Map<String, String> map_takid2token = flow.getMap_talkid2token();


        //组员提交
        req.token = tokenPb;
        req.cmdtype = "projectFlow";
        req.cmd = "memberSubmit";

        for (String talkId : map_takid2token.keySet()) {
            //pb
            req.setToken(map_takid2token.get(talkId));
            //not pm
            req.setToken(tokenPa);
            //pm
            req.setToken(tokenPm);
            req.msgBody.put("talkId", talkId);
            String ret = flow.postProject(httpclient, req);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            checkEQ(0, resp.getRetcode());

        }
    }

    @JTest
    @JTestClass.title("test0004_talkPayFirstPmSubmitpbAudit")
    @JTestClass.pre("")
    @JTestClass.step("test0004_talkPayFirstPmSubmitpbAudit ")
    @JTestClass.exp("ok")
    public void test0004_talkPayFirstPmSubmitPbAudit() throws Exception {
        flow.payFirst(req, tokenPa);
        Map<String, String> map_takid2token = flow.getMap_talkid2token();

        //组员提交  组长审核
        req.token = tokenPb;
        req.cmdtype = "projectFlow";
        req.cmd = "memberSubmit";
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            String ret = flow.postProject(httpclient, req);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            checkEQ(0, resp.getRetcode());
        }

        //req.token = tokenPa;
        req.cmdtype = "projectFlow";
        req.cmd = "principalVerify";
        int i = 0;
        for (String talkId : flow.getMap_talkid2user().keySet()) {
            if (i > 0) {
                break;
            }
            i++;
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            req.msgBody.put("userId", flow.getMap_talkid2user().get(talkId));
            req.msgBody.put("status", ProjectConst.FOLDER_STATUS_PASS_PM);

            String ret = flow.postProject(httpclient, req);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            checkEQ(0, resp.getRetcode());
        }
        System.out.println(flow.getMap_talkid2user());

    }

    @JTest
    @JTestClass.title("pb负责人提交test0003_PrincipalSubmit")
    @JTestClass.pre("principalSubmit")
    @JTestClass.step("test0003_PBSubmit ")
    @JTestClass.exp("ok")
    public void test0005_PBSubmit() throws Exception {
        flow.payFirst(req, tokenPa);
        Map<String, String> map_takid2token = flow.getMap_talkid2token();

        req.token = tokenPb;
        req.cmdtype = "projectFlow";
        req.cmd = "principalSubmit";
        req.msgBody = JSONObject.parseObject("{}");
        int i = 0;
        for (String talkId : map_takid2token.keySet()) {
            if (i > 0) {
                break;
            }
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            String ret = flow.postProject(httpclient, req);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            checkEQ(0, resp.getRetcode());
            i++;
        }
        System.out.println("pb负责人提交");
    }

    @JTest
    @JTestClass.title("PartyAVerify PA审核")
    @JTestClass.pre("principalSubmit")
    @JTestClass.step("test0004_PartyAVerify")
    @JTestClass.exp("ok")
    public void test0006_PAPayFirst() throws Exception {
        flow.payFirst(req,tokenPa);
        Map<String, String> map_talkid2token = flow.getMap_talkid2token();

        req.token = tokenPb;
        flow.pbPhaseSubmit(req);
        System.out.println("pb负责人提交");

        req.token = tokenPa;
        flow.paPhaseAudit(req);
        System.out.println("PA审核");


    }

    @JTest
    @JTestClass.title("PayPhase PartyAVerify PA审核")
    @JTestClass.pre(" ")
    @JTestClass.step("test0005_PAPayPhase")
    @JTestClass.exp("ok")
    public void test0007_PAPayPhase() throws Exception {
        List<String> lstPhase = new ArrayList<>();
        List<Integer> result = flow.payFirst(req,tokenPa);
        lstPhase.add(result.get(0) + "");

        System.out.println("pb负责人提交");
        req.token = tokenPb;
        flow.pbPhaseSubmit(req);

        System.out.println("PA审核");
        req.token = tokenPa;
        flow.paPhaseAudit(req);

        int  phase = flow.pay2Next(tokenPa, flow.getMap_talkid2token(), ProjectConst.Phase1 + 1);
        lstPhase.add(phase + "");
        UserProjectContext context = new UserProjectContext();
        for(String talkId : flow.getMap_talkid2token().keySet()) {
            context.buildModel(Integer.valueOf(talkId));
            break;
        }
        checkEQ("601,602", lstPhase.stream().collect(Collectors.joining(",")));
        System.out.println("talkid: "+flow.getMap_talkid2token().keySet());
        System.out.println("projectId="+context.getProjectModel().getProjectId());

    }

    @JTest
    @JTestClass.title("PayPhase PartyAVerify test0006_PartyAVerifyPayPhase2Next  ")
    @JTestClass.pre(" ")
    @JTestClass.step("test0006_PartyAVerifyPayPhaseNext")
    @JTestClass.exp("ok")
    public void test0008_paPayPhase2Next() throws Exception {
        List<String> lstPhase = new ArrayList<>();
        List<Integer> result = flow.payFirst(req,tokenPa);
        lstPhase.add(result.get(0) + "");

        //0->200 -> 601->602->603->604->605->606->999;
        for (int i = 1; i < 7; i++) {
            int phase = flow.pay2Next(tokenPa, flow.getMap_talkid2token(), flow.getPm().getPhaseStart() + i);
            lstPhase.add(phase + "");
        }
        checkEQ("601,602,603,604,605,606,606", lstPhase.stream().collect(Collectors.joining(",")));
        UserProjectContext context = new UserProjectContext();
        for(String talkId : flow.getMap_talkid2token().keySet()) {
            context.buildModel(Integer.valueOf(talkId));
            break;
        }
        checkEQ(ProjectConstState.CHNAGE_TYPE_FINISH,context.getProjectTalkModel().getChangeStatus());

        flow.checkProjectServiceTypeTradeRecordNumber(8);
        YtbLog.logJtest("talkid: "+flow.getMap_talkid2token().keySet());
        YtbLog.logJtest("projectId="+context.getProjectModel().getProjectId());

    }

    @JTest
    @JTestClass.title("PayPhase PartyAVerify test0009_paPayPhase2Next  ")
    @JTestClass.pre(" ")
    @JTestClass.step("test0009_paPayPhase2Next")
    @JTestClass.exp("ok")
    public void test0009_testContext() throws Exception {

        UserProjectContext context = new Flow().buildContext(1276,125);
        ProjectTaskViewResult viewResult =  new ProjectTaskViewPhase().getPaProjectTask(context, 125, 601);
        System.out.println(viewResult);
    }

    @JTest
    @JTestClass.title("PayPhase PartyAVerify test0009_paPayPhase2Next  ")
    @JTestClass.pre(" ")
    @JTestClass.step("test0009_paPayPhase2Next")
    @JTestClass.exp("ok")
    public void test0010_addAssistDoc() throws Exception {
        tokenPa = userLogin.login(125);

        req.token = tokenPa;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "addAssistDoc";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("docListId", 12878);
        req.msgBody.put("talkId", 1684);
        req.msgBody.put("toUserId","182,191");
        req.msgBody.put("remark","remark");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(resp.toJSONStringPretty());
    }

    @JTest
    @JTestClass.title("PayPhase PartyAVerify test0011_payPhase  ")
    @JTestClass.pre(" ")
    @JTestClass.step("test0011_payPhase")
    @JTestClass.exp("ok")
    public void test0011_payPhase() throws Exception {
        tokenPa = userLogin.login(485);   //485
        req.token = tokenPa;//"20bc9d3483f047d4b396a584ca72e85e";
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "payPhase";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("talkId", 1747);
        req.msgBody.put("passWord","e10adc3949ba59abbe56e057f20f883e");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(resp.toJSONStringPretty());
    }

    @JTest
    @JTestClass.title("PayPhase PartyAVerify test0011_payPhase  ")
    @JTestClass.pre(" ")
    @JTestClass.step("test0011_payPhase")
    @JTestClass.exp("ok")
    public void test0012_getLogSso() throws Exception {
        LoginSso sso = userLogin.getLog_sso(tokenPa);
        System.out.println(sso);

    }

    @JTest
    @JTestClass.title("PayPhase PartyAVerify test0011_payPhase  ")
    @JTestClass.pre(" ")
    @JTestClass.step("test0011_payPhase")
    @JTestClass.exp("ok")
    public void test0013_exportManager() throws Exception {
        req.token = tokenPb1301;
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportAllTables";
        req.msgBody.fluentPut("projectId", 2488).fluentPut("documentId", 38605);
        String url_manager = "http://localhost/rest/tagTableService/manager";

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());

    }

    @JTest
    @JTestClass.title("test0014_restProject_member")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0014_restProject_member() {
        LoginSsoJson loginSsoJson = userLogin.getLogSso(tokenPb186, req);
        Collection<SelectProjectMember.DayPayInfoResult>  dayPayInfos = new SelectProjectMember().selectList(2488, 38605, loginSsoJson);
        System.out.println(dayPayInfos);

    }


    @JTest
    @JTestClass.title("PayPhase PartyAVerify test0011_payPhase  ")
    @JTestClass.pre(" ")
    @JTestClass.step("test0011_payPhase")
    @JTestClass.exp("ok")
    public void test0015_exportProject() throws Exception {
        req.token = tokenPb1301;
        req.cmdtype = "tagTableServiceProject";
        req.cmd = "exportAllTables";
        req.msgBody.fluentPut("projectId", 2488).fluentPut("documentId", 38605);
        String url_project = "http://localhost/rest/tagTableService/project";
        String ret = httpclient.post(url_project, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(resp.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());

    }

    @JTest
    @JTestClass.title("test0016_restSelectTable_project_member")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0016_restSelectTable_project_member() {

        req.cmdtype = "tagTableServiceProject";
        req.cmd = "project_member";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("projectId", 2488).fluentPut("documentId", 38605);
        String url_project = "http://localhost/rest/tagTableService/project";

        String ret = httpclient.post(url_project, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(url_project);
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());

    }

    @JTest
    @JTestClass.title("test0017_getProjectTaskTab")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0017_getProjectTaskTab() {

        req.cmdtype = "projectFlow";
        req.cmd = "getProjectTaskTab";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("talkId", 6370);

        String ret = httpclient.post(url_projectCenter, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(url_projectCenter);
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());

    }


    @JTest
    @JTestClass.title("test0017_getProjectTaskTab")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0018_getProjectTaskTab() throws Exception {
        req.token =     tokenPa = userLogin.login(485);
        req.cmdtype = "projectFlow";
        req.cmd = "getProjectTask";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("talkId", 6370);
        req.msgBody.fluentPut("type", "PA");
        req.msgBody.fluentPut("stage", 601);

        String ret = httpclient.post(url_projectCenter, JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
        System.out.println(url_projectCenter);
        System.out.println(resp.toJSONStringPretty());

    }

    @JTest
    @JTestClass.title("准备项目变更")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0019_getProjectTaskTab() {

        req.token = tokenPa;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getProjectTaskTab";

        req.msgBody.fluentPut("talkId", 6370);
        req.msgBody.fluentPut("type", "1");
        req.msgBody.fluentPut("stopReason", 801);
        req.msgBody.fluentPut("partyBy", 1);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("准备项目变更")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0020_submitDoc() {

        req.token = tokenPa;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "submitDoc";

        req.msgBody.fluentPut("talkId", 7063);
        req.msgBody.fluentPut("projectId", 3011);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        YtbLog.logJtest("req: " , req.toJSONString());
        checkEQ(0, resp.getRetcode());
        YtbLog.logJtest(url_projectCenter);
        YtbLog.logJtest("resp: " , resp.toJSONString());
    }


    //tradeServiceFeeFlag = 0，无， 1 +， -1 -（暂无用） :  15, 8  简单代码写不好，复杂业务写不了！
    public static void main(String args[]) throws Exception {

        run(TestPhase.class, 8);

    }


}
