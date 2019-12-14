package ytb.projectnew;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.Flow;
import ytb.common.TestProjectConst;
import ytb.common.utils.YtbUtils;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.UserAssistModel;
import ytb.project.service.impl.pay.payassist.paytalk.ProjectPayTalk;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.RestMessage.MsgRequest;

import java.util.ArrayList;
import java.util.List;

@JTestClass.author("leijming")
public class TestFlowPayTalkThanks extends ITestImpl {

    ProjectPayTalk projectPayTalk =new ProjectPayTalk();

    String url_projectCenter = TestProjectConst.url_projectCenter;
    Flow flow = new Flow();

    static int paId = TestProjectConst.paId;
    static int talkId   = TestProjectConst.talkId;
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
    UserProjectContext context = new UserProjectContext();

    @Override
    public void setUp() {
        req.token = token;
    }

    @JTest
    @JTestClass.title("协助支付预付款")
    @JTestClass.pre("设置三个帐户的收入冻结款project_balance_agent为0")
    @JTestClass.step("post")
    @JTestClass.exp("检查设置 帐户的支出冻结为300.00")
    public void test0001_payAssist() throws Exception {
        context = new Flow().buildContext(talkId, paId);
        dbAccount.clearSql().appendSql("update account_user_inner ");
        dbAccount.appendSql(" set  balance = 300, balance_agent = 0  ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId);
        dbAccount.execSql();

        List<UserAssistModel> assistMonies = new ArrayList<>();
        for (String userId : unPmFriends.split(",")) {
            UserAssistModel am = new UserAssistModel();
            am.setUserId(Integer.parseInt(userId));
            am.setMoney(100);
            am.setUserName("name");
            //am.setFlag(UserAssistModel.FLAG_GORUP_MEMBER_NONE);
            assistMonies.add(am);
            dbAccount.execSql();
        }

        int tradeId = projectPayTalk.payAssistPre(context, payPasswodEn, assistMonies);
        System.out.println(tradeId);
        dbAccount.clearSql().appendSql("select  balance_agent from account_user_inner ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId);
        dbAccount.execSelect().checkRecord("balance_agent=300.00");
        List<UserAssistModel>  assistMonieList = projectPayTalk.queryAssistMoneies(tradeId);
        System.out.println(YtbUtils.toJSONStringSkipNull(assistMonieList));
        checkEQ(3,assistMonieList.size());
        dbAccount.clearSql().appendSql("select * from account_user_inner ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId);
        dbAccount.execSelect().checkRecord("balance=0.00|balance_agent=300.00");

    }


    @JTest
    @JTestClass.title("协助支付预付款test0002_payAssistConfrim")
    @JTestClass.pre("设置三个帐户的收入冻结款project_balance_agent为0")
    @JTestClass.step("post")
    @JTestClass.exp("检查设置三个帐户的收入冻结为100.00")
    public void test0002_payAssistConfrim() throws Exception {
        context = new Flow().buildContext(talkId, paId);
        dbAccount.clearSql().appendSql("update account_user_inner ");
        dbAccount.appendSql(" set  balance = 300, payout_agent = 0  ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId);
        dbAccount.execSql();
        dbProject.clearSql().appendSql(" delete from  project_trade ");
        dbProject.appendSql(" where service_type  =  ").appendSql(TradeConst.SERVICE_TYPE_thank);
        dbProject.appendSql(" and talk_id  =  ").appendSql(talkId);
        //System.out.println(dbProject.getSql());
        dbProject.execSql();

        List<UserAssistModel> assistMonies = new ArrayList<>();
        for (String userId : unPmFriends.split(",")) {
            UserAssistModel am = new UserAssistModel();
            am.setUserId(Integer.parseInt(userId));
            am.setMoney(100);
            //am.setFlag(UserAssistModel.FLAG_GORUP_MEMBER_NONE);
            assistMonies.add(am);
            dbAccount.clearSql().appendSql("update account_user_inner ");
            dbAccount.appendSql(" set  balance = 0, payout_agent = 0 , income_agent =0  ");
            dbAccount.appendSql(" where user_id = ").appendSql(userId);
            dbAccount.execSql();
        }

        int tradeid = projectPayTalk.payAssistPre(context, payPasswodEn, assistMonies);
        System.out.println(tradeid);
        dbProject.clearSql().appendSql(" select * from  project_trade ");
        dbProject.appendSql(" where service_type  =  ").appendSql(TradeConst.SERVICE_TYPE_thank);
        dbProject.appendSql(" and talk_id  =  ").appendSql(talkId);
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("service_type="+TradeConst.SERVICE_TYPE_thank);

        projectPayTalk.payAssistConfrim(context, tradeid,  assistMonies);

        for (String userId : unPmFriends.split(",")) {
            dbAccount.clearSql().appendSql("select * from account_user_inner ");
            dbAccount.appendSql(" where user_id = ").appendSql(userId);
            dbAccount.execSelect().checkRecord("balance=85.00|income_agent=0.00");
        }
        System.out.println(tradeid);
        dbProject.clearSql().appendSql("select * from project_trade ");
        dbProject.appendSql(" where trade_id = ").appendSql(tradeid);
        dbProject.execSelect().checkRecord("service_type=4");
        flow.checkTalkServiceTypeTradeRecordNumber(talkId,3);

    }

    @JTest
    @JTestClass.title("洽谈支付预付款")
    @JTestClass.pre("设置 帐户的收入冻结款project_balance_agent为0")
    @JTestClass.step("post")
    @JTestClass.exp("检查设置 帐户的支出冻结为100.00")
    public void test0003_projectPayTalk() throws Exception {
        context = new Flow().buildContext(talkId, paId);
        dbAccount.clearSql().appendSql("update account_user_inner ");
        dbAccount.appendSql(" set  balance = 300, balance_agent = 0  ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId);
        dbAccount.execSql();

        List<UserAssistModel> assistMonies = new ArrayList<>();
        for (String userId : unPmFriends.split(",")) {
            UserAssistModel am = new UserAssistModel();
            am.setUserId(Integer.parseInt(userId));
            am.setMoney(100);
            //am.setFlag(UserAssistModel.FLAG_GORUP_MEMBER_NONE);
            am.setUserName("name");
            assistMonies.add(am);
            dbAccount.execSql();
            break;
        }

        int tradeId = projectPayTalk.payAssistPre(context, payPasswodEn, assistMonies);
        System.out.println(tradeId);
        dbAccount.clearSql().appendSql("select  balance_agent from account_user_inner ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId);
        dbAccount.execSelect().checkRecord("payout_agent=100.00");
        List<UserAssistModel>  assistMonieList = projectPayTalk.queryAssistMoneies(tradeId);
        System.out.println(YtbUtils.toJSONStringSkipNull(assistMonieList));
        checkEQ(1,assistMonieList.size());

    }

    @JTest
    @JTestClass.title("协助支付预付款test0002_payAssistConfrim")
    @JTestClass.pre("设置三个帐户的收入冻结款project_balance_agent为0")
    @JTestClass.step("post")
    @JTestClass.exp("检查设置三个帐户的收入冻结为100.00")
    public void test0004_payAssistCancel() throws Exception {
        context = new Flow().buildContext(talkId, paId);
        dbAccount.clearSql().appendSql("update account_user_inner ");
        dbAccount.appendSql(" set  balance = 300, payout_agent = 0  ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId);
        dbAccount.execSql();

        List<UserAssistModel> assistMonies = new ArrayList<>();
        for (String userId : unPmFriends.split(",")) {
            UserAssistModel am = new UserAssistModel();
            am.setUserId(Integer.parseInt(userId));
            am.setMoney(100);
            //am.setFlag(UserAssistModel.FLAG_GORUP_MEMBER_NONE);
            assistMonies.add(am);
            dbAccount.clearSql().appendSql("update account_user_inner ");
            dbAccount.appendSql(" set  balance = 0, payout_agent = 0 ,income_agent =0  ");
            dbAccount.appendSql(" where user_id = ").appendSql(userId);
            dbAccount.execSql();
        }

        int tradeid = projectPayTalk.payAssistPre(context, payPasswodEn, assistMonies);
        projectPayTalk.payAssistCancel(context, tradeid,assistMonies);

        for (String userId : unPmFriends.split(",")) {
            dbAccount.clearSql().appendSql("select * from account_user_inner ");
            dbAccount.appendSql(" where user_id = ").appendSql(userId);
            dbAccount.execSelect().checkRecord("balance=0.00|income_agent=0.00");
        }
        dbAccount.clearSql().appendSql("select * from account_user_inner ");
        dbAccount.appendSql(" where user_id = ").appendSql(paId);
        dbAccount.execSelect().checkRecord("balance=255.00|payout_agent=45.00");

    }

    public static void main(String args[]) {
        run(TestFlowPayTalkThanks.class, 4);
    }
}
