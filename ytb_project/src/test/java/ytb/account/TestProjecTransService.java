package ytb.account;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.TestProjectConst;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.pojo.ProjectBalanceOutAc;
import ytb.account.wallet.pojo.ProjectBalanceToAc;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.sq.business.user.ProjecTransactionService;
import ytb.account.wallet.service.sq.business.user.impl.ProjecTransactionServiceImpl;
import ytb.account.wallet.service.sq.business.user.pojo.projectRefunds;
import ytb.common.RestMessage.MsgRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author hj
 * @Description //TODO
 * @Date 2019/3/13
 **/
public class TestProjecTransService extends ITestImpl {
    String url_projectCenter = TestProjectConst.url_projectCenter;//"http://mysql.kunlong.com/rest/projectCenter";

    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;
    @Inject("ytb_account")
    JDbNode ytb_account;

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    MsgRequest req = new MsgRequest();
    int paId = TestProjectConst.paId;
    String token = "966b210c1b3243f795d997a62b2cddb7";

    UserProjectContext context = new UserProjectContext();

    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }


    @Override
    public void setUp() throws Exception {
//        token =   new UserLogin().login(paId);
//        req.token = token;
    }

    /**
     * zc 測試
     * 方法： 甲方：balance-减少    balance_out    支出增加    冻结资金   增加
     * 測試結果：运行成功
     */
    @JTest
    @JTestClass.title("金额1")
    public void test001_frozenAmount() throws Exception {

        AccountTrade accountTrade = new AccountTrade();
        accountTrade.setAcId(67);
        accountTrade.setBalance(BigDecimal.valueOf(1));
        ytb_account.clearSql().appendSql("update account_user_inner  ");
        ytb_account.appendSql(" set balance=200,balance_out= 200  ");
        ytb_account.appendSql(" where inner_id= ").appendSql(accountTrade.getAcId());
        ytb_account.execSql();
        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        Integer integer = service.frozenAmount(accountTrade);
        ytb_account.clearSql().appendSql("SELECT balance,balance_out FROM account_user_inner WHERE inner_id =").appendSql(accountTrade.getAcId());
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=199.00|balance_out=201.00");
        System.out.println("ytb.check  payFirst！");
        System.out.println("====================================================================================");
    }

    /**
     * zc 測試
     * 方法： 甲方钱减少->系统
     * 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     */
    @JTest
    @JTestClass.title("金额3")
    public void test002_projectByPAFirstPay() throws Exception {
        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        AccountTradeProject accountTrade = new AccountTradeProject();
        accountTrade.setBalance(BigDecimal.valueOf(3));
        accountTrade.setAcId(37);
        accountTrade.setTradeType(TradeConst.TRADE_TYPE_PAYOUT);
        accountTrade.setTradeSubtype(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN);
        accountTrade.setStatus(TradeConst.status_passed);
        accountTrade.setServiceType(TradeConst.SERVICE_TYPE_project);

        ytb_account.clearSql().appendSql("update account_user_inner  ");
        ytb_account.appendSql(" set balance=200,balance_out=200,balance_agent=200   ");
        ytb_account.appendSql(" where inner_id= ").appendSql(accountTrade.getAcId());
        ytb_account.execSql();

        int i = service.b2fPaPayProjectFirst(accountTrade);
        System.out.println(i);

        ytb_account.clearSql().appendSql("SELECT balance,balance_out,balance_agent ");
        ytb_account.appendSql("FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(accountTrade.getAcId());
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=197.00|balance_out=203.00|balance_agent=203.00");

        ytb_account.clearSql().appendSql("SELECT balance FROM account_trade  WHERE trade_id = (SELECT MAX(trade_id) FROM account_trade) and ac_id = ").appendSql(accountTrade.getAcId());
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=3.00");

        ytb_account.clearSql().appendSql("SELECT trade_balance FROM account_user_detail  WHERE detail_id = (SELECT MAX(detail_id) FROM account_user_detail) and inner_id = ").appendSql(accountTrade.getAcId());
        ytb_account.execSelect();
        ytb_account.checkRecord("trade_balance=3.00");

        System.out.println("ytb.check  payFirst！");
        System.out.println("====================================================================================");
    }

    /**
     * zc 測試
     * 方法：甲方结算-》乙方钱
     */
    @JTest
    @JTestClass.title("总金额为3")
    public void test003_stagePayByPAToPBs() throws Exception {
        ProjectBalanceOutAc projectBalanceOutAc = new ProjectBalanceOutAc();
        //这个钱是乙方全部的和
        projectBalanceOutAc.setBalance(BigDecimal.valueOf(3));
        projectBalanceOutAc.setPassword("e10adc3949ba59abbe56e057f20f883e");
        projectBalanceOutAc.setTradeId(4441);//第一次支付的id

        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();
        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        //usera.setBalance(BigDecimal.valueOf(1));
        usera.setToInnerId(67);
        usera.setBalance(BigDecimal.valueOf(1));
        projectBalanceAgentList.add(usera);

        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        //usetb.setBalance(BigDecimal.valueOf(1));
        usetb.setToInnerId(68);
        usetb.setBalance(BigDecimal.valueOf(2));
        projectBalanceAgentList.add(usetb);

        ytb_account.clearSql().appendSql("update account_user_inner  ");
        ytb_account.appendSql(" set project_balance_agent=200 ");
        ytb_account.appendSql(" where inner_id= ").appendSql(usera.getToInnerId());
        ytb_account.appendSql(" or inner_id= ").appendSql(usetb.getToInnerId());
        ytb_account.execSql();

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
      //  ProjectBalanceAgent projectBalanceAgent = service.phasePayPa2Pb(projectBalanceOutAc,
          //      projectBalanceAgentList);
       // System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));

        ytb_account.clearSql().appendSql("select project_balance_agent ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id=").appendSql(usera.getToInnerId());
        ytb_account.execSelect();
        ytb_account.checkRecord("project_balance_agent=201.00");

        ytb_account.clearSql().appendSql("select  project_balance_agent  ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(usetb.getToInnerId());
        ytb_account.execSelect();
        ytb_account.checkRecord("project_balance_agent=202.00");
        System.out.println("====================================================================================");

    }

    /**
     * zc 測試
     * 方法：甲方结算-》乙方钱
     * 当前使用场景：
     * 项目冻结款入账
     * 乙方项目金额冻结--》总金额
     */
    @JTest
    @JTestClass.title("总金额为3")
    public void test004_projectPbUnfreeze() throws Exception {

        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();
        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        usera.setBalance(BigDecimal.valueOf(1));
        usera.setToInnerId(67);
        projectBalanceAgentList.add(usera);

        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        usetb.setBalance(BigDecimal.valueOf(2));
        usetb.setToInnerId(68);
        projectBalanceAgentList.add(usetb);

        ytb_account.clearSql().appendSql(" UPDATE account_user_inner  ");
        ytb_account.appendSql(" SET balance=200,project_balance_agent=200 ");
        ytb_account.appendSql(" where inner_id= ").appendSql(usera.getToInnerId());
        ytb_account.appendSql(" or inner_id= ").appendSql(usetb.getToInnerId());
        ytb_account.execSql();

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        List<AccountTradeProject> projectBalanceAgent = service.projectPbUnfreeze(projectBalanceAgentList);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));

        ytb_account.clearSql().appendSql("select  balance,project_balance_agent  ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(usera.getToInnerId());
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=201.00|project_balance_agent=199.00");

        ytb_account.clearSql().appendSql("select  balance,project_balance_agent  ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(usetb.getToInnerId());
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=202.00|project_balance_agent=198.00");

    }

    /**
     * zc 測試
     * 方法：提现审核 提现冻结
     * 測試結果：运行成功
     */
    @JTest
    @JTestClass.title("金额20")
    public void test005_withdrawCash()  throws Exception {

        // balance:1,tradeSubtype:2,outId:146,"password":"e10adc3949ba59abbe56e057f20f883e"
        ProjecTransactionServiceImpl accountTradeService = new ProjecTransactionServiceImpl();
        AccountTrade accountTrade  = new AccountTrade();
        Integer innerId = 67;
        accountTrade.setAcId(innerId);
        accountTrade.setUserId(202);
        BigDecimal balance = BigDecimal.valueOf(20);
        accountTrade.setBalance(balance);
        accountTrade.setTotalBalance(balance);
        int sta = accountTradeService.withdrawCash(accountTrade, innerId, balance);
        if (sta > 0) {
            System.out.println("成功了");
        }else{
            System.out.println("失败了");
        }

        ytb_account.clearSql().appendSql("SELECT  ac_id,user_id,total_balance  ");
        ytb_account.appendSql(" FROM account_trade ");
        ytb_account.appendSql(" where trade_id= (SELECT MAX(trade_id) FROM account_trade )");
        ytb_account.execSelect();
        ytb_account.checkRecord("ac_id=67|user_id=202|total_balance=20.00");
        System.out.println("====================================================================================");
    }

    /**
     * zc 測試
     * 方法： 直接转账到账  甲方流水- -， 资金- - 支出++ , 乙方收入++资金++ 收入++
     * total_balance
     */
    @JTest
    @JTestClass.title("金额2")
    public void test006_transferService() throws Exception{

        AccountTrade accountTrade = new AccountTrade();
        accountTrade.setAcId(67);
        accountTrade.setToAcId(68);
        accountTrade.setBalance(BigDecimal.valueOf(1));
        Short sh = 3;
        accountTrade.setTradeSubtype(sh);
        Short s = 50;
        accountTrade.setTradeType(s);
        Byte by = 1;
        accountTrade.setServiceType(by);

        ytb_account.clearSql().appendSql(" UPDATE account_user_inner  ");
        ytb_account.appendSql(" SET balance=200,balance_out=200,balance_in=200 ");
        ytb_account.appendSql(" where inner_id= ").appendSql(accountTrade.getAcId());
        ytb_account.appendSql(" or inner_id= ").appendSql(accountTrade.getToAcId());
        ytb_account.execSql();

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        int projectBalanceAgent = service.transferService(accountTrade);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));

        ytb_account.clearSql().appendSql("select  balance,balance_out  ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(accountTrade.getAcId());
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=199.00|balance_out=201.00");

        ytb_account.clearSql().appendSql("select  balance,balance_in  ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(accountTrade.getToAcId());
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=201.00|balance_in=201.00");

        ytb_account.clearSql().appendSql("select  ac_id,to_ac_id,balance  ");
        ytb_account.appendSql(" FROM account_trade ");
        ytb_account.appendSql(" where trade_id= (select max(trade_id) from account_trade)");
        ytb_account.execSelect();
        ytb_account.checkRecord("ac_id=67|to_ac_id=68|balance=1.00");
        System.out.println("====================================================================================");
    }

    /**
     * zc 測試
     * 方法： 项目阶段退款(业务逻辑需要重新整理)
     * 有問題
     * AccountTrade 中的 getBalance() 有问题
     * 建议改为
     *  if(balance ==null){
     *             return balance;
     *         }else{
     *             return balance.setScale(2,BigDecimal.ROUND_HALF_UP);
     *         }
     */
    @JTest
    @JTestClass.title("金额1")
    public void test007_projectRefund() throws Exception{

        Integer tradeId = 4527;//这要传阶段订单 ac_id 68-》to_ac_id 69
        BigDecimal RefundM = BigDecimal.valueOf(1);

        ytb_account.clearSql().appendSql(" UPDATE account_user_inner  ");
        ytb_account.appendSql(" SET balance_agent=200,project_balance_agent=200 ");
        ytb_account.appendSql(" where inner_id= ").appendSql(68);
        ytb_account.appendSql(" or inner_id= ").appendSql(69);
        ytb_account.execSql();

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        Boolean sta = service.projectRefund(tradeId, RefundM);
        System.out.println("订单:" + sta);

//        ytb_account.clearSql().appendSql("select  balance_agent,project_balance_agent  ");
//        ytb_account.appendSql(" FROM account_user_inner ");
//        ytb_account.appendSql(" where inner_id=").appendSql(68);;
//        ytb_account.execSelect();
//        ytb_account.checkRecord("balance_agent=67|project_balance_agent=");

        System.out.println("====================================================================================");
    }
    /**
     * zc 測試
     * 方法： 冻结金入账
     * balance_agent -》balance
     */
    @JTest
    @JTestClass.title("金額1")
    public void test0008_balanceAgentToBalance() throws Exception{
        Integer innerId = 68;
        BigDecimal RefundM = BigDecimal.valueOf(1);

        ytb_account.clearSql().appendSql(" UPDATE account_user_inner  ");
        ytb_account.appendSql(" SET balance_agent=200,balance=200 ");
        ytb_account.appendSql(" where inner_id= ").appendSql(68);
        ytb_account.appendSql(" or inner_id= ").appendSql(69);
        ytb_account.execSql();

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        //Boolean sta = service.balanceAgentToBalance(innerId, RefundM);
//        if (sta) {
//            System.out.println("success");
//        }else{
//            System.out.println("失敗");
//        }

        ytb_account.clearSql().appendSql("select  balance_agent,balance  ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(68);;
        ytb_account.execSelect();
        ytb_account.checkRecord("balance_agent=199.00|balance=201.00");

        System.out.println("====================================================================================");

    }

    /**
     * zc 測試
     * 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     * 冻结金额
     * 金额balance--
     * 支付冻结balance_agent++
     * 总出账balance_out ++++
     */
    @JTest
    @JTestClass.title("金额1")
    public void test009_frozenAmount() throws Exception{

        AccountTrade accountTrade = new AccountTrade();
        accountTrade.setAcId(67);
        accountTrade.setBalance(BigDecimal.valueOf(1));

        ytb_account.clearSql().appendSql(" UPDATE account_user_inner  ");
        ytb_account.appendSql(" SET balance=200,balance_agent=200,balance_out=200 ");
        ytb_account.appendSql(" where inner_id= ").appendSql(accountTrade.getAcId());
        ytb_account.execSql();

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        Integer integer = service.frozenAmount(accountTrade);
        System.out.println("订单:" + integer);

        ytb_account.clearSql().appendSql("select  balance,balance_agent,balance_out  ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(accountTrade.getAcId());;
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=199.00|balance_agent=201.00|balance_out=201.00");

        ytb_account.clearSql().appendSql("select  balance  ");
        ytb_account.appendSql(" FROM account_trade ");
        ytb_account.appendSql(" where trade_id=(select max(trade_id) from account_trade) and ac_id= ").appendSql(accountTrade.getAcId());;
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=1.00");

        System.out.println("====================================================================================");
    }

    /***
     *  zc 测试
     * 项目阶段退款
     * 甲方支付项目预付款-退款
     * 从甲方的钱包冻结款转到余额
     * tradeId:预付款订单的trade_id
     * refund:退款金额
     * userId:用户ID
     * companyId:退款金额
     * balance_agent -》balance
     * */
    @JTest
    @JTestClass.title("金额10")
    public void test010_nailBalanceAgentToBalance() throws Exception{


        Integer tradeId = 4894;
        BigDecimal refund = BigDecimal.valueOf(1);
        Integer userId = 123 ;
        Integer companyId= 120;
        String localip = "111";
        String pow = "e10adc3949ba59abbe56e057f20f883e";

        ytb_account.clearSql().appendSql(" UPDATE account_user_inner  ");
        ytb_account.appendSql(" SET balance=200,balance_agent=200,balance_out=200 ");
        ytb_account.appendSql(" where inner_id= ").appendSql(68);
        ytb_account.execSql();

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        Integer aBoolean = service.paPayoutAgent2Balance(null);
        if(aBoolean != null){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        ytb_account.clearSql().appendSql("select  balance,balance_agent,balance_out  ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(68);;
        ytb_account.execSelect();
        ytb_account.checkRecord("balance=201.00|balance_agent=199.00|balance_out=199.00");

        System.out.println("====================================================================================");
    }

    /**
     * zc
     * 转帐 退款
     * 从甲方的钱包冻结款转到乙方和组员的冻结款的退款
     * 从乙方和组员的冻结款到甲方的钱包冻结款转
     * projectRefunds - accountTrade:从甲方的钱包冻结款转到乙方和组员的冻结款订单的trade_id
     * projectRefunds - money:退款金额
     * 乙方的 project_balance_agent --》甲方的 balance_agent
     **/
    @JTest
    @JTestClass.title("金额10")
    public void test011_projectRefundList() throws Exception{

        List<projectRefunds> list = new ArrayList<>();
        projectRefunds data = new projectRefunds();
        data.setTradeId(5858);//74->67
        data.setBalance(BigDecimal.valueOf(1));
        list.add(data);
        projectRefunds data1 = new projectRefunds();
        data1.setTradeId(5857);//74->66
        data1.setBalance(BigDecimal.valueOf(2));
        list.add(data1);
        Integer userId = 123 ;
        Integer companyId= 120;
        String pow = "e10adc3949ba59abbe56e057f20f883e";
        String localip = "111";
        ytb_account.clearSql().appendSql(" UPDATE account_user_inner  ");
        ytb_account.appendSql(" SET balance_agent=20000,project_balance_agent=20000 ");
        ytb_account.appendSql(" where inner_id = ").appendSql(74);
        ytb_account.appendSql(" or inner_id = ").appendSql(67);
        ytb_account.appendSql(" or inner_id = ").appendSql(66);
        ytb_account.execSql();

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();

        ytb_account.clearSql().appendSql("select  balance_agent ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(74);;
        ytb_account.execSelect();
        ytb_account.checkRecord("balance_agent=20003.00");

        ytb_account.clearSql().appendSql("select  project_balance_agent ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(67);;
        ytb_account.execSelect();
        ytb_account.checkRecord("project_balance_agent=19999.00");

        ytb_account.clearSql().appendSql("select  project_balance_agent ");
        ytb_account.appendSql(" FROM account_user_inner ");
        ytb_account.appendSql(" where inner_id= ").appendSql(66);;
        ytb_account.execSelect();
        ytb_account.checkRecord("project_balance_agent=19998.00");

        System.out.println("====================================================================================");
    }
    public static void main(String args[]) {
        run(TestProjecTransService.class, "test010_nailBalanceAgentToBalance");

    }


}
