package ytb.projectnew;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.Flow;
import ytb.common.TestProjectConst;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.service.impl.pay.PayResult;
import ytb.project.service.impl.pay.projectpay.FlowPayChange;
import ytb.common.RestMessage.MsgRequest;


public class TestFlowPay extends ITestImpl {
    String url_projectCenter = TestProjectConst.url_projectCenter;
    static int paId = TestProjectConst.paId;
    static int talkId = TestProjectConst.talkId;
    static int projectId = TestProjectConst.projectId;

    static String unPmFriends = TestProjectConst.unPmFriends;
    static String payPasswodEn = TestProjectConst.payPasswodEn;

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }


    @Inject("ytb_account")
    JDbNode dbAccount;
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;

    MsgRequest req = new MsgRequest();
    String token = "966b210c1b3243f795d997a62b2cddb7";
    UserProjectContext context=new UserProjectContext();

    @Override
    public void setUp() {
          req.token=token;
    }


    @JTest
    @JTestClass.title("项目预付款")
    @JTestClass.pre("")
    @JTestClass.step("post")
    @JTestClass.exp("支付")
    public void test0001_prePay() throws Exception {
        dbAccount.clearSql().appendSql("update account_user_inner ");
        dbAccount.appendSql(" set  balance = 10000, payout_agent = 0  ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId).execSql();

        dbProject.clearSql().appendSql("update project set  pay_times=0 ");
        dbProject.appendSql(" where  project_id = 696  ").execSql();
        dbProject.clearSql().appendSql("update project_talk  ");
        dbProject.appendSql("set  phase=200 ,phase_status=300  ");
        dbProject.appendSql(" where  project_id = 696 ").execSql();

        context = new Flow().buildContext(talkId, paId);
        PayResult payResult = new FlowPayChange().paPayOpenProject(context, payPasswodEn);
        System.out.println(payResult);
        dbAccount.clearSql().appendSql("select  balance_agent from account_user_inner ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId);
        dbAccount.execSelect().checkRecord("payout_agent=3570.00");
    }

    public static void main(String args[]) {
        run(TestFlowPay.class, 1);

    }

}
