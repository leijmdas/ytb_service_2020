package ytb.projectnew;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import ytb.common.*;
import ytb.common.ytblog.YtbLog;
import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.impl.pay.ViewPayResult;
import ytb.project.service.impl.pay.projectpay.FlowPayChange;
import ytb.project.service.project.change.ChangeFlow;
import ytb.project.view.model.ProjectChangeResult;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestPhaseChange extends ITestYtb {

    String url_projectCenter = TestProjectConst.url_projectCenter;
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    UserLogin userLogin = new UserLogin();
    Flow flow = new Flow();
    TagTable tagTable = new TagTable();
    FlowChange testFlowChange = new FlowChange();
    FlowPayChange flowPayChange = new FlowPayChange();
    ChangeFlow changeFlow = new ChangeFlow();

    String friends = TestProjectConst.friends;
    int paId = TestProjectConst.paId;
    int pbId = TestProjectConst.pbId;
    int pmId = TestProjectConst.pmId;

    String tokenPa, tokenPb, tokenPm, token485;
    MsgRequest req = new MsgRequest();
    int projectId = 5;
    int talkId = 389;
    int oldTalkId = 11;
    UserProjectContext oldContext,context ;//= flow.buildContext(6370, 485);

    @Inject("ytb_project")
    JDbNode dbProject;
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_account")
    JDbNode dbAccount;

    @Override
    public void setUp() throws Exception {
        context  = flow.buildContext(talkId, 202);
        oldContext = new UserProjectContext(context,oldTalkId);

        tokenPa = userLogin.login(paId);
        tokenPb = userLogin.login(pbId);
        tokenPm = userLogin.login(pmId);
         token485 = userLogin.login(485);

        testFlowChange.setHttpclient(httpclient);
        testFlowChange.setProjectId(projectId);
        testFlowChange.setTalkId(talkId);
        testFlowChange.setTokenPa(tokenPa);
        testFlowChange.setTokenPb(tokenPb);
        testFlowChange.setTokenPm(tokenPm);

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



    @JTest
    @JTestClass.title("PayPhase PartyAVerify PA审核")
    @JTestClass.pre(" ")
    @JTestClass.step("test0008_littleChange")
    @JTestClass.exp("ok")
    public void test0008_littleChange() throws Exception {
        req.token = token485;

        dbProject.clearSql().appendSql("update ytb_project.project ");
        dbProject.appendSql(" set new_project_id=0 ,change_type=0 ");
        dbProject.appendSql(" where project_id=").appendSql(projectId);
        dbProject.execSql();
        dbProject.clearSql().appendSql("update ytb_project.project_change ");
        dbProject.appendSql(" set new_project_id=0, phase_status = 0 ");
        dbProject.appendSql(" where project_id=").appendSql(projectId);
        dbProject.execSql();

        ProjectChangeResult result = testFlowChange.paApplyChange(req);
        System.err.println(result.toString());

        //计算参数表
        UserProjectContext context = flow.buildContext(result.getNewTalkId(), 485);
        tagTable.exportAllTables(httpclient,req,result.getNewProjectId(),
                context.getProjectTalkModel().selectReqTemplate().getDocumentId());
        tagTable.exportAllTables(httpclient,req,result.getNewProjectId(),
                context.getProjectTalkModel().getWorkplanId());

        testFlowChange.computeChange(req,result);

        testFlowChange.pbSubmitPaAudit(req, result);

        BigDecimal fee = new ChangeFlow().selectFeeDiff(context);
        System.out.println(fee);

        testFlowChange.payChange(req, result);
    }

    @JTest
    @JTestClass.title("PayPhase PartyAVerify PA审核")
    @JTestClass.pre(" ")
    @JTestClass.step("test0009_littleChangePay")
    @JTestClass.exp("ok")
    public void test0009_payReOpenPay() throws Exception {
        //req.token = token485;

        dbProject.clearSql().appendSql("update ytb_project.project ");
        dbProject.appendSql(" set new_project_id=0 ,change_type=701 ");
        dbProject.appendSql(" where project_id=").appendSql(projectId);
        dbProject.execSql();
        dbProject.clearSql().appendSql("update ytb_project.project_change ");
        dbProject.appendSql(" set new_project_id=0, phase_status = 0 ");
        dbProject.appendSql(" where project_id=").appendSql(projectId);
        dbProject.execSql();

        //计算参数表
        //UserProjectContext context = flow.buildContext(6370, 485);
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm=context.getProjectTalkModel();
        //pm.setChangeType(ProjectConstState.CHNAGE_TYPE_SMALL);

        for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
            flow.updateBalanceAgent(memberModel.getUserId(), 100001, 1000001, 100001);

        }
        flow.deleteProjectTradeChange(ptm.getTalkId(), ptm.getPhase());
        // BigDecimal fee = new ChangeFlow().selectFeeDiff(context);
        // new ChangeFlow().computeChange(701, context,context);
        BigDecimal fee = BigDecimal.valueOf(-200);

        ViewPayResult viewPayResult = new ViewPayResult();
        flowPayChange.paPayPhaseChange(context, context,viewPayResult, fee);

        YtbLog.logJtest("paPayPhaseChange viewPayResult",viewPayResult);

    }


    @JTest
    @JTestClass.title("PayPhase PartyAVerify PA审核")
    @JTestClass.pre(" ")
    @JTestClass.step("test0010_bigChangePay")
    @JTestClass.exp("ok")
    public void test0010_payPhaseRefund() throws Exception {
        //req.token = token485;

        dbProject.clearSql().appendSql("update ytb_project.project ");
        dbProject.appendSql(" set new_project_id=0 ,change_type=701");
        dbProject.appendSql(" where project_id=").appendSql(projectId);
        dbProject.execSql();
        dbProject.clearSql().appendSql("update ytb_project.project_change ");
        dbProject.appendSql(" set new_project_id=0, phase_status = 0 ");
        dbProject.appendSql(" where project_id=").appendSql(projectId);
        dbProject.execSql();

        //计算参数表
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm=context.getProjectTalkModel();
        dbProject.clearSql().appendSql("delete from  ytb_project.project_folder ");
        dbProject.appendSql(" where  parent_id = ").appendSql(ptm.getFolderId());
        dbProject.appendSql(" and phase=").appendSql(pm.getPhaseStart());
        dbProject.appendSql(" and folder_type=").appendSql(ProjectConst.FOLDER_TYPE_PHASE);
        dbProject.execSql();

        flow.deleteProjectTradeChange(ptm.getTalkId(), ptm.getPhase());

        for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
            flow.updateBalanceAgent(memberModel.getUserId(), 100001, 1000001, 100001);

        }

        BigDecimal fee = BigDecimal.valueOf(200);
        ViewPayResult viewPayResult = new ViewPayResult();
        flowPayChange.paPayPhaseChange(context, context,viewPayResult, fee);

        YtbLog.logJtest("paPayPhaseChange viewPayResult",viewPayResult);


    }

    @JTest
    @JTestClass.title(" 变更支付test0011_payChange")
    @JTestClass.pre(" ")
    @JTestClass.step("test0011_payChange")
    @JTestClass.exp("ok")
    public void test0011_payChange() throws Exception {


        dbProject.clearSql().appendSql("update ytb_project.project ");
        dbProject.appendSql(" set new_project_id=0 ,change_type=701");
        dbProject.appendSql(" where project_id=").appendSql(projectId);
        dbProject.execSql();
        dbProject.clearSql().appendSql("update ytb_project.project_change ");
        dbProject.appendSql(" set new_project_id=0, phase_status = 0 ");
        dbProject.appendSql(" where project_id=").appendSql(projectId);
        dbProject.execSql();

        //计算参数表
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        dbProject.clearSql().appendSql("delete from  ytb_project.project_folder ");
        dbProject.appendSql(" where  parent_id = ").appendSql(ptm.getFolderId());
        dbProject.appendSql(" and phase=").appendSql(pm.getPhaseStart());
        dbProject.appendSql(" and folder_type=").appendSql(ProjectConst.FOLDER_TYPE_PHASE);
        dbProject.execSql();

        flow.deleteProjectTradeChange(ptm.getTalkId(), ptm.getPhase());

        for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
            flow.updateBalanceAgent(memberModel.getUserId(), 100001, 1000001, 100001);

        }

        //changeFlow.reCopyTemplates(context);
        ProjectChangeResult changeResult = changeFlow.pbAgreeChangeFirst(context,"1,2");
        YtbLog.logJtest("getProjectModel",context.getProjectModel());
        UserProjectContext newContext = new UserProjectContext(context, changeResult.getNewTalkId());

        int changeType = changeFlow.computeChange(newContext, context);

        ViewPayResult viewPayResult = flowPayChange.paPayChange(newContext, context, TestProjectConst.payPasswodEn);

        YtbLog.logJtest("FlowPayChange paPayChange viewPayResult", viewPayResult);


    }

    public static void main(String args[]) throws Exception {

        run(TestPhaseChange.class,  11);


    }

}
