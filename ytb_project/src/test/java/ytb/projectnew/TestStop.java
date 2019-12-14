package ytb.projectnew;

import ch.qos.logback.classic.Level;
import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import ytb.common.Flow;
import ytb.common.ITestYtb;
import ytb.common.TestProjectConst;
import ytb.common.UserLogin;
import ytb.common.ytblog.YtbLog;
import ytb.common.ytblog.TaskYtbLog;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.impl.pay.ViewPayResult;
import ytb.project.service.impl.pay.projectpay.FlowPayChange;
import ytb.project.service.impl.pay.projectpay.FlowPayStop;
import ytb.project.service.project.stop.StopApplyResult;
import ytb.project.service.project.stop.StopFlow;
import ytb.project.service.project.stop.impl.StopService;
import ytb.project.service.project.stop.impl.StopType;
import ytb.common.RestMessage.MsgRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * 1 按代码规范
 * 2 不要有重复代码
 * 3 函数不能太长
 * 4 不能有if for else 好多{}嵌套
 * 5 尽量封装一些小函数
 **/
//
//        LoggerContext loggerContext= (LoggerContext) LoggerFactory.getILoggerFactory();
//                List<Logger> loggers=loggerContext.getLoggerList() ;
//                for(Logger logger:loggers){
//                System.out.println(logger);
//                logger.setLevel(Level.DEBUG);
//                }
//                ch.qos.logback.classic.Logger l=(ch.qos.logback.classic.Logger)YtbLog.logger;
//                l.setLevel(Level.ERROR);
//                l.debug("ERROR1");
//                l.error("ERROR2");
//
//                l.setLevel(Level.DEBUG);
//                l.debug("ERROR11");
//                l.error("ERROR22")
public class TestStop extends ITestYtb {

    int paId = TestProjectConst.paId;
    UserProjectContext context = new UserProjectContext();

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    String url_projectCenter = TestProjectConst.url_projectCenter;
    Flow flow = new Flow();
    FlowPayStop payStop = new FlowPayStop();
    StopService stopService = new StopService();

    MsgRequest req = new MsgRequest();
    String token = " ";

    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }

    @Inject("ytb_account")
    JDbNode dbAccount;
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;

    @Override
    public void setUp() throws Exception {
        YtbLog.getRootLogger().setLevel(Level.DEBUG);
        token = new UserLogin().login(paId);        //token = new UserLogin().login(485);

        req.token = token;
        context = new Flow().buildContext(389, 202);
    }

    @JTest
    @JTestClass.title("计算甲方取消终止费用test0001_stop_PaCancelCompute")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0001_stop_PaCancelCompute() throws Exception {
        StopType stopType = new StopType(context, StopType.PaPbUser_PA, StopType.SUBTYPE_PA_CANCEL);
        System.out.println(stopType);
        stopService.checkCanStop(context, stopType);
        stopService.compute(context, stopType);
        System.out.println(stopType);
    }

    @JTest
    @JTestClass.title("计算乙方不满意终止费用test0002_stop_PbUnsatisfyCompute")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0002_stop_PbUnsatisfyCompute() throws Exception {
        StopType stopType = new StopType(context, StopType.PaPbUser_PB, StopType.SUBTYPE_PB_Unsatisfy);
        System.out.println(stopType);
        stopService.checkCanStop(context, stopType);
        stopService.compute(context, stopType);
        System.out.println(stopType);
    }

    @JTest
    @JTestClass.title("计算乙方不满意终止告知书")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0003_stop_computeStopPTable() throws Exception {

        StopType stopType = new StopType(context, StopType.PaPbUser_PA, StopType.SUBTYPE_PA_CANCEL);

        System.out.println(stopType);
        //创建终止文件夹 拷贝终止告知书
        StopApplyResult applyResult = new StopApplyResult(context);
        stopService.createStopFolderAndTemplate(context, stopType, applyResult);
        //ytb.check folder and template
        dbProject.clearSql().appendSql(" select 1 from project_folder ");
        dbProject.appendSql(" where folder_id=").appendSql(applyResult.getStopFolderId());
        dbProject.checkRecordNumber(1);

        dbProject.clearSql().appendSql(" select 1 from project_template ");
        dbProject.appendSql(" where folder_id=").appendSql(applyResult.getStopFolderId());
        dbProject.appendSql(" and template_id=").appendSql(applyResult.getStopTemplateId());
        dbProject.checkRecordNumber(1);

        stopService.checkCanStop(context, stopType);

        int stopId = stopService.computeStopPTable(context, stopType);

        //ytb.check stopPtable;
        dbProject.clearSql().appendSql(" select * from project_stop_p ");
        dbProject.appendSql(" where document_id=").appendSql(applyResult.getStopDocumentId());
        dbProject.checkRecordNumber(1);

        System.out.println(stopId);
        System.out.println(applyResult);

        System.out.println(stopService.selectPaPbPayFee(context));
        List<Integer> lst = stopService.payAfter(context);
        System.out.println(lst);
        System.err.println(stopType);
    }

    //context.selectProjectRateTaxModel()
    @JTest
    @JTestClass.title("test0006_log_selectProjectRateTaxModel")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0004_log_selectProjectRateTaxModel() throws Exception {

        TaskYtbLog.logDebug("selectProjectRateTaxModel ", context.selectProjectRateTaxModel());
        YtbLog.logJtest("selectProjectRateTaxModel ", context.selectProjectRateTaxModel());
        new FlowPayChange().f2bUnfreezePayClosePm_nonePp(1, true, context, null);
        checkEQ(1, 1);
    }

    @JTest
    @JTestClass.title("终止项目的支付test0004_payStop_REFUND")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0005_payStop_repay() throws Exception {
        System.out.println(context.selectProjectRateTaxModel());

        int nowPhase = 605;
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ptm.setChangeStatus(0);
        ptm.setPhase(nowPhase);
        pm.setChangeType(ProjectConstState.CHNAGE_TYPE_STOP);
        //flow.deleteProjectTradeStop(ptm.getTalkId(), ptm.getPhase());
        flow.deleteProjectTradeChangeStop(ptm.getTalkId());
        flow.deleteAccountTradeProject(ptm.getTalkId(), 606);
        for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
            flow.updateBalanceAgent(memberModel.getUserId(), 100001, 1000001, 100001);

        }


        dbProject.clearSql().appendSql(" update project  ");
        dbProject.appendSql(" set change_type = ").appendSql(ProjectConstState.CHNAGE_TYPE_STOP);
        dbProject.appendSql(" where project_id = ").appendSql(pm.getProjectId());
        dbProject.execSql();
        //StopType stopType = new StopType(context, StopType.PaPbUser_PA, StopType.SUBTYPE_PA_CANCEL);
        StopType stopType = new StopType(context, StopType.PaPbUser_PA, StopType.SUBTYPE_PA_CANCEL);

        dbProject.clearSql().appendSql(" update project_talk set change_status=0,phase=").appendSql(nowPhase);
        dbProject.appendSql(" where talk_id=").appendSql(ptm.getTalkId());
        dbProject.execSql();
        stopService.checkCanStop(context, stopType);
        StopApplyResult applyResult = new StopApplyResult(context);
        stopService.createStopFolderAndTemplate(context, stopType, applyResult);

        stopService.computeStopPTable(context, stopType);//new StopFlow().selectProjectStopPModel(context));
        BigDecimal fee = new StopFlow().selectPaPbPayFee(context);
        YtbLog.logJtest("selectPaPbPayFee", fee);
        YtbLog.logDebug("stopType", stopType);
        ViewPayResult payStopResult = payStop.paPayStop(context);

//        dbProject.clearSql().appendSql(" update project_talk set change_status=800,phase=").appendSql(nowPhase);
//        dbProject.appendSql(" where talk_id=").appendSql(ptm.getTalkId());
//        ptm.setChangeStatus(ProjectConstState.CHNAGE_TYPE_STOP);
//        new FlowPayStop().paPayStop(context, "");
        //   BigDecimal paFeeFrozen = new PaPayFreezeAccount().sumFrozen(context);
//        payStopResult.checkFeePaFrozen(paFeeFrozen);
//        BigDecimal pbFeeFrozen = new PbPayFreezeAccount().sumFrozen(context);

        YtbLog.logJtest("payStopResult", payStopResult);
    }


    //flow.updateBalanceAgent(pm.getUserId1(), 100001, 1000001, 100001);
    //flow.updateBalanceAgent(ptm.getUserId2(), 100001, 1000001, 100001);
    @JTest
    @JTestClass.title("终止项目的支付test0005_payStop_pay")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0006_payStop_refund_Delay() throws Exception {
        System.out.println(context.selectProjectRateTaxModel());

        int nowPhase = 605;
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ptm.setChangeStatus(0);
        ptm.setPhase(nowPhase);
        pm.setChangeType(ProjectConstState.CHNAGE_TYPE_STOP);
        flow.deleteProjectTradeStop(ptm.getTalkId(), ptm.getPhase());
        flow.deleteAccountTradeProject(ptm.getTalkId(), 606);
        flow.deleteProjectTradeChangeStop(ptm.getTalkId());
        for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
            flow.updateBalanceAgent(memberModel.getUserId(), 100001, 1000001, 100001);

        }


        dbProject.clearSql().appendSql(" update project  ");
        dbProject.appendSql(" set change_type = ").appendSql(ProjectConstState.CHNAGE_TYPE_STOP);
        dbProject.appendSql(" where project_id = ").appendSql(pm.getProjectId());
        dbProject.execSql();
        //StopType stopType = new StopType(context, StopType.PaPbUser_PA, StopType.SUBTYPE_PA_CANCEL);
        StopType stopType = new StopType(context, StopType.PaPbUser_PA, StopType.SUBTYPE_PB_OFFGRADE);

        dbProject.clearSql().appendSql(" update project_talk set change_status=0,phase=").appendSql(nowPhase);
        dbProject.appendSql(" where talk_id=").appendSql(ptm.getTalkId());
        dbProject.execSql();
        stopService.checkCanStop(context, stopType);
        StopApplyResult applyResult = new StopApplyResult(context);
        stopService.createStopFolderAndTemplate(context, stopType, applyResult);

        stopService.computeStopPTable(context, stopType);//new StopFlow().selectProjectStopPModel(context));
        BigDecimal fee = new StopFlow().selectPaPbPayFee(context);
        YtbLog.logJtest("selectPaPbPayFee", fee);
        YtbLog.logDebug("stopType", stopType);
        ViewPayResult payStopResult = payStop.paPayStop(context);

//        dbProject.clearSql().appendSql(" update project_talk set change_status=800,phase=").appendSql(nowPhase);
//        dbProject.appendSql(" where talk_id=").appendSql(ptm.getTalkId());
//        ptm.setChangeStatus(ProjectConstState.CHNAGE_TYPE_STOP);
//        new FlowPayStop().paPayStop(context, "");
        //   BigDecimal paFeeFrozen = new PaPayFreezeAccount().sumFrozen(context);
//        payStopResult.checkFeePaFrozen(paFeeFrozen);
//        BigDecimal pbFeeFrozen = new PbPayFreezeAccount().sumFrozen(context);

        YtbLog.logJtest("payStopResult", payStopResult);
    }

    //  select * from vw_work_group_member_duty
    //  select task_name from project_member_task
    @JTest
    @JTestClass.title("e")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/service")
    @JTestClass.exp("ok")
    public void test0007_restDemo() {
        String url ="http://localhost/rest/service/demoModel";
        req.cmdtype = "student";
        req.cmd = "select";
        System.out.println(req.toJSONString());
        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
         System.out.println(ret);
    }

    //查询测试报告 ，查询模板对应表
    public static void main(String args[]) {
       run(TestStop.class, 7);
//        ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
//        engine.getRepositoryService().activateProcessDefinitionById("1");
          //javax.servlet.http.HttpServletRequest.getHttpServletMapping;
    }
}
