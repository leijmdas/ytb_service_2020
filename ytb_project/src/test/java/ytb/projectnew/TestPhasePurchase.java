package ytb.projectnew;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import ytb.common.Flow;
import ytb.common.ITestYtb;
import ytb.common.TestProjectConst;
import ytb.common.UserLogin;
import ytb.common.ytblog.YtbLog;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.service.impl.talk.MemberDutyTaskService;
import ytb.project.view.model.ProjectTaskViewResult;
import ytb.project.view.service.impl.ProjectTaskViewPhase;
import ytb.manager.templateexcel.model.tag.TagTable;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestPhasePurchase extends ITestYtb {

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
    UserProjectContext context = new UserProjectContext();

    String tokenPa,tokenPb,tokenPm;
    MsgRequest req = new MsgRequest();

    @Inject("ytb_project")
    JDbNode dbProject;
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_account")
    JDbNode dbAccount;

    @Override
    public void setUp() throws Exception {
        context = new Flow().buildContext(11, 202);

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
    @JTestClass.step("test0005_PAPayPhase")
    @JTestClass.exp("ok")
    public void test0007_PAPayPhase() throws Exception {
        List<String> lstPhase = new ArrayList<>();
        List<Integer> result = flow.payFirstPurchase(req,tokenPa);
        System.out.println(result);
        lstPhase.add(result.get(0) + "");

        System.out.println("pb负责人提交");
        req.token = tokenPb;
        flow.pbPhaseSubmit(req);

        System.out.println("PA审核");
        req.token = tokenPa;
        flow.paPhaseAudit(req);
        //ProjectModel pm = flow.getPm();
        //int  phase = flow.pay2Next(tokenPa, flow.getMap_talkid2token(), pm.getPhaseStart()+ 1);
        UserProjectContext context = new UserProjectContext();
        for(String talkId : flow.getMap_talkid2token().keySet()){
            context.buildModel(Integer.parseInt(talkId));
            break;
        }
        lstPhase.add(context.getProjectTalkModel().getPhase()+"");
        checkEQ("601,601", lstPhase.stream().collect(Collectors.joining(",")));
        checkEQ(ProjectConstState.CHNAGE_TYPE_FINISH,context.getProjectTalkModel().getChangeStatus());
        System.out.println("talkid: "+flow.getMap_talkid2token().keySet());
        flow.checkProjectServiceTypeTradeRecordNumber(3);

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
        checkEQ("601,602,603,604,605,606,999", lstPhase.stream().collect(Collectors.joining(",")));
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
    @JTestClass.title("test0011_checkExistDutyPP")
    @JTestClass.pre(" ")
    @JTestClass.step("test0011_checkExistDutyPP")
    @JTestClass.exp("ok")
    public void test0011_checkExistDutyPP() throws Exception {
        MemberDutyTaskService.MemberDutyInfo dutyInfo = new MemberDutyTaskService().checkExistDutyPP(context);
        YtbLog.logJtest("MemberDutyInfo", dutyInfo);

    }


    public static void main(String args[]) throws Exception {

      run(TestPhasePurchase.class, 7); //7,11

    }

}
