package ytb.projectnew;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.Flow;
import ytb.common.TagTable;
import ytb.common.TestProjectConst;
import ytb.common.UserLogin;
import ytb.common.utils.YtbUtils;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.view.model.ProjectChangeResult;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestProcesingChange extends ITestImpl {

    String url_projectCenter = TestProjectConst.url_projectCenter;
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    UserLogin userLogin = new UserLogin();
    Flow flow = new Flow();
    TagTable tagTable = new TagTable();

    String friends = TestProjectConst.friends;
    int paId = TestProjectConst.paId;
    int pbId = TestProjectConst.pbId;
    int pmId = TestProjectConst.pmId;

    String tokenPa, tokenPb, tokenPm;
    MsgRequest req = new MsgRequest();
    int projectId = 2178;
    int talkId = 5402;

    @Inject("ytb_project")
    JDbNode dbProject;
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_account")
    JDbNode dbAccount;

    @Override
    public void setUp() throws Exception {
        tokenPa = userLogin.login(paId);
        tokenPb = userLogin.login(pbId);
        tokenPm = userLogin.login(pmId);

        req.token = tokenPa;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();

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
        flow.publishProcessing( req,"" );
        System.out.println("发布成功！");
    }

    @JTest
    @JTestClass.title("test0002_talkPayFirst")
    @JTestClass.pre("")
    @JTestClass.step("test0002_talkPayFirst ")
    @JTestClass.exp("ok")
    public void test0002_talkPayFirst() throws Exception {
        flow.payFirstProcessing(req, tokenPa);
        flow.getMap_talkid2token();
        //组员提交，组长审核
    }

    @JTest
    @JTestClass.title("组员提交")
    @JTestClass.pre("")
    @JTestClass.step("test0003_talkPayFirstPmSubmit ")
    @JTestClass.exp("ok")
    public void test0003_talkPayFirstPmSubmit() throws Exception {

        flow.payFirstProcessing(req, tokenPa);
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
        flow.payFirstProcessing(req,tokenPa);
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
    @JTestClass.step("test0007_PAPayProcessPhase")
    @JTestClass.exp("ok")
    public void test0007_PAPayProcessPhase() throws Exception {
        List<String> lstPhase = new ArrayList<>();
        List<Integer> result = flow.payFirstProcessing(req,tokenPa);

        lstPhase.add(result.get(0) + "");

        System.out.println("pb负责人提交");
        req.token = tokenPb;
        flow.pbPhaseSubmit(req);

        System.out.println("PA审核");
        req.token = tokenPa;
        flow.paPhaseAudit(req);
        //ProjectModel pm = flow.getPm();
        //int phase = flow.pay2Next(tokenPa, flow.getMap_talkid2token(), pm.getPhaseStart()+ 1);
        UserProjectContext context = new UserProjectContext();
        for(String talkId : flow.getMap_talkid2token().keySet()){
            context.buildModel(Integer.parseInt(talkId));
            break;
        }
        lstPhase.add(context.getProjectTalkModel().getPhase()+"");
        checkEQ("601,999", lstPhase.stream().collect(Collectors.joining(",")));
        flow.checkProjectServiceTypeTradeRecordNumber(3);
        System.out.println("talkid: "+flow.getMap_talkid2token().keySet());
    }

    ProjectChangeResult paApplyChange(){

        req.cmdtype = "projectFlow";
        req.cmd = "paApplyChange";
        req.msgBody.fluentPut("changeType", ProjectConstState.CHNAGE_TYPE_SMALL);
        req.msgBody.fluentPut("projectId", projectId);
        req.msgBody.fluentPut("talkId", talkId);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);

        checkEQ(0, resp.getRetcode());


        req.cmdtype = "projectFlow";
        req.cmd = "paAgreeChange";
        ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        JSONObject result = resp.getMsgBody().getJSONObject("list");
        ProjectChangeResult changeResult=JSONObject.parseObject(YtbUtils.toJSONString(result),ProjectChangeResult.class);

        return changeResult;
    }

//        dbProject.clearSql().appendSql("update ytb_project.project   ");
//        dbProject.appendSql(" set change_status=").appendSql(ProjectConstState.PRO_CHANGESTATUS_PB_MAKE);
//        dbProject.appendSql(" where project_id=").appendSql(projectId);
//        dbProject.execSql();

    void computeChange(MsgRequest req,ProjectChangeResult result,int docType){
        req.cmdtype = "projectFlow";
        req.cmd = "computeChange";

        req.msgBody = JSONObject.parseObject("{\"docType\":100,\"oldTalkId\":5334,\"talkId\":5353}");
        req.msgBody.fluentPut("docType", docType);
        req.msgBody.fluentPut("talkId", result.getOldTalkId());
        req.msgBody.fluentPut("newTalkId", result.getNewTalkId());
        String ret1 = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp1 = MsgResponse.parseResponse(ret1);
        checkEQ(0, resp1.getRetcode());
    }
    @JTest
    @JTestClass.title("PayPhase PartyAVerify PA审核")
    @JTestClass.pre(" ")
    @JTestClass.step("test0007_PAPayProcessPhase")
    @JTestClass.exp("ok")
    public void test0008_littleChange() throws Exception {
        dbProject.clearSql().appendSql("update ytb_project.project ");
        dbProject.appendSql(" set new_project_id=0, change_status = 0 ");
        dbProject.appendSql(" where project_id=").appendSql(projectId);
        dbProject.execSql();

        ProjectChangeResult result = paApplyChange();
        System.err.println(result.toString());

        //计算参数表
        UserProjectContext context = flow .buildContext(result.getNewTalkId(), pbId);
        //tagTable.exportAllTables(httpclient,req,result.getNewProjectId(),
        //        context.getProjectTalkModel().selectReqTemplate().getDocumentId());
       // tagTable.exportAllTables(httpclient,req,result.getNewProjectId(),
           //     context.getProjectTalkModel().getWorkplanId());

//        computeChange(req,result,100);
//        computeChange(req,result,200);

        req.token = tokenPb;
        req.cmdtype = "projectFlow";
        req.cmd = "pbSubmitChange";
        req.msgBody.fluentPut("talkId", talkId);
        req.msgBody.fluentPut("newTalkId", result.getNewTalkId());

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

        req.token = tokenPa;
        req.cmdtype = "projectFlow";
        req.cmd = "paAuditChange";
        req.msgBody.fluentPut("talkId", talkId);
        req.msgBody.fluentPut("newTalkId", result.getNewTalkId());
        req.msgBody.fluentPut("status", 0);

        ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

        System.out.println(resp);
   }


    public static void main(String args[]) throws Exception {

        run(TestProcesingChange.class, 8);

    }

}
