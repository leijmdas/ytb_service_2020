package test;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.AccountUserDetail;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;
import ytb.account.wallet.pojo.ProjectBalanceOutAc;
import ytb.account.wallet.pojo.ProjectBalanceToAc;

import ytb.account.wallet.service.AccountConst.TradeConst;

import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.sq.business.user.ProjecTransactionService;
import ytb.account.wallet.service.sq.business.user.impl.ProjecTransactionServiceImpl;
import ytb.common.RestMessage.MsgRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Package: ytb_service.usercentertest
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2018/9/26 19:56
 */
@JTestClass.author("zengcsheng")
public class ProjecTransactionTest extends ITestImpl {
    String url_base = "http://mysql.kunlong.com:8080/rest/ytbuser";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    @Inject("ytb_account")
    JDbNode dbAccount;

    MsgRequest req = new MsgRequest();
    String data;

    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }



    @JTest
    @JTestClass.title("通用新建订单")
    public void test0001_newOrder() {
        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();

        AccountTradeProject record = new AccountTradeProject();
        record.setBalance(BigDecimal.valueOf(11));
        record.setAcId(37);
        record.setTalkId(11);
        record.setServiceFee(BigDecimal.valueOf(0));
        record.setTradeType(TradeConst.TRADE_TYPE_PAYOUT);
        record.setTradeSubtype(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN);
        record.setStatus(TradeConst.status_passed);
        record.setServiceType(TradeConst.SERVICE_TYPE_project);

        int a = service.b2fPaPayProjectFirst(record);
        System.out.println("订单号为:" + a);

    }


    @JTest
    @JTestClass.title("项目阶段支付 甲方项目冻结款->乙方项目收入冻结")
    public void test0002_projectBalanceAgentToList() {

        ProjectBalanceOutAc projectBalanceOutAc = new ProjectBalanceOutAc();
        //这个钱是乙方全部的和
        projectBalanceOutAc.setBalance(BigDecimal.valueOf(2));
        projectBalanceOutAc.setPassword("e10adc3949ba59abbe56e057f20f883e");
        projectBalanceOutAc.setTradeId(3822);//第一次支付的id


        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();
        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        usera.setBalance(BigDecimal.valueOf(1));
        usera.setToInnerId(69);

        projectBalanceAgentList.add(usera);
        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        usetb.setBalance(BigDecimal.valueOf(1));
        usetb.setToInnerId(70);
        projectBalanceAgentList.add(usetb);

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        ProjectBalanceProjectAgent projectBalanceAgent = service.phasePayPa2Pb(projectBalanceOutAc,
                projectBalanceAgentList);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));

    }


    @JTest
    @JTestClass.title("项目结算-甲方将阶段项目结算完毕后，进行结算")
    public void test0003_projectBalanceToBalanceList() {

        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();

        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        usera.setBalance(BigDecimal.valueOf(11));
        usera.setToInnerId(67);
        projectBalanceAgentList.add(usera);

        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        usetb.setBalance(BigDecimal.valueOf(11));
        usetb.setToInnerId(68);
        projectBalanceAgentList.add(usetb);

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        List<AccountTradeProject> projectBalanceAgent = service.projectPbUnfreeze(projectBalanceAgentList);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));

    }


    @JTest
    @JTestClass.title("直接转账")
    public void test0004_transferService() {

        AccountTradeProject accountTrade = new AccountTradeProject();
        accountTrade.setAcId(67);
        accountTrade.setToAcId(68);
        accountTrade.setBalance(BigDecimal.valueOf(1));

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        int projectBalanceAgent = service.transferService(accountTrade);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));

    }


    @JTest
    @JTestClass.title("阶段退款")
    public void test0005_projectRefund() {
        Integer tradeId = 3826;//这要传阶段订单

        BigDecimal RefundM = BigDecimal.valueOf(1);

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        Boolean sta = service.projectRefund(tradeId, RefundM);
        System.out.println("订单:" + sta);

    }

    @JTest
    @JTestClass.title("balance_agent入账")
    public void test0006_balance_agentRefund() {
        Integer innerId = 68;//这要传阶段订单

        BigDecimal RefundM = BigDecimal.valueOf(1);

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
       // Boolean sta = service.balanceAgentToBalance(innerId, RefundM);
//        if (sta) {
//            System.out.println("success");
//        }


    }

    @JTest
    @JTestClass.title("XXX")
    public void test0007_XXX() {
        System.out.println("xxxxx");
    }

    /**
     * zc 測試
     * 方法：甲方钱减少->系统
     * 測試結果：订单生产成功
     * 数据库中create_by 和 create_time 数据为空
     * 【注意】
     */
    @JTest
    @JTestClass.title("金额为100")
    public void test0008_projectByPAFirstPay() {
        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        AccountTradeProject record  = new AccountTradeProject();
        //record.getBalance()
        //record.getAcId()
        // record.getTradeId()
        record.setBalance(BigDecimal.valueOf(100));
        record.setAcId(37);
        int i = service.b2fPaPayProjectFirst(record);
        System.out.println(i);
        System.out.println("====================================================================================");
//        run(ProjecTransactionTest.class, "test0005_balance_agentRefund");
//        run(ProjecTransactionTest.class, "test0002_projectBalanceAgentToList");
    }
    /**
     * zc 測試
     * 方法：甲方钱减少->系统
     * 測試結果： 余额不足，运行失败
     *
     */
    @JTest
    @JTestClass.title("金额为119")
    public void test0009_projectByPAFirstPay() {
        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        AccountTradeProject record  = new AccountTradeProject();
        //record.getBalance()
        //record.getAcId()
        // record.getTradeId()
        record.setBalance(BigDecimal.valueOf(119));
        record.setAcId(69);

        int i = service.b2fPaPayProjectFirst(record);
        System.out.println(i);
        System.out.println("====================================================================================");

    }
    /**
     * zc 測試
     * 方法：甲方钱减少->系统
     * 測試結果： 金额为负，运行失败
     *
     */
    @JTest
    @JTestClass.title("金额为-99")
    public void test0010_projectByPAFirstPay() {
        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        AccountTradeProject record  = new AccountTradeProject();
        //record.getBalance()
        //record.getAcId()
        // record.getTradeId()
        record.setBalance(BigDecimal.valueOf(-99));
        record.setAcId(69);

        int i = service.b2fPaPayProjectFirst(record);
        System.out.println(i);
        System.out.println("====================================================================================");

    }
    /**
     * zc 測試
     * 方法：甲方结算-》乙方钱
     * 測試結果：代码运行成功， projectBalanceOutAc.setBalance(BigDecimal.valueOf(22));
     * 设置的是22  乙方总和其实是119，数据库中总金额也是119
     * 【注意】
     */
    @JTest
    @JTestClass.title("总金额为119")
    public void test0011_projectByPAFirstPay() {
        ProjectBalanceOutAc projectBalanceOutAc = new ProjectBalanceOutAc();
        //这个钱是乙方全部的和
        projectBalanceOutAc.setBalance(BigDecimal.valueOf(22));
        projectBalanceOutAc.setPassword("e10adc3949ba59abbe56e057f20f883e");
        projectBalanceOutAc.setTradeId(3761);//第一次支付的id


        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();
        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        //usera.setBalance(BigDecimal.valueOf(1));
        usera.setToInnerId(67);
        usera.setBalance(BigDecimal.valueOf(110));
        projectBalanceAgentList.add(usera);

        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        //usetb.setBalance(BigDecimal.valueOf(1));
        usetb.setToInnerId(68);
        usetb.setBalance(BigDecimal.valueOf(9));
        projectBalanceAgentList.add(usetb);

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        ProjectBalanceProjectAgent projectBalanceAgent = service.phasePayPa2Pb(projectBalanceOutAc, projectBalanceAgentList);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));
        System.out.println("====================================================================================");

    }
    /**
     * zc 測試
     * 方法：甲方结算-》乙方钱
     * 測試結果：代码运行成功， projectBalanceOutAc.setTradeId(3764);//第一次支付的id
     * 但是这个Id不是的交易记录不是，资金冻结的记录，是甲方转账给乙方的记录
     * 【注意】
     */
    @JTest
    @JTestClass.title("总金额为110")
    public void test0012_projectByPAFirstPay() {
        ProjectBalanceOutAc projectBalanceOutAc = new ProjectBalanceOutAc();
        //这个钱是乙方全部的和
        projectBalanceOutAc.setBalance(BigDecimal.valueOf(100));
        projectBalanceOutAc.setPassword("e10adc3949ba59abbe56e057f20f883e");
        projectBalanceOutAc.setTradeId(4089);//第一次支付的id


        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();
        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        //usera.setBalance(BigDecimal.valueOf(1));
        usera.setToInnerId(67);
        usera.setBalance(BigDecimal.valueOf(10));
        projectBalanceAgentList.add(usera);

        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        //usetb.setBalance(BigDecimal.valueOf(1));
        usetb.setToInnerId(68);
        usetb.setBalance(BigDecimal.valueOf(90));
        projectBalanceAgentList.add(usetb);

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        ProjectBalanceProjectAgent projectBalanceAgent = service.phasePayPa2Pb(projectBalanceOutAc, projectBalanceAgentList);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));
        System.out.println("====================================================================================");

    }
    /**
     * zc 測試
     * 方法：甲方结算-》乙方钱
     * 測試結果：代码运行失败，  projectBalanceOutAc.setBalance(BigDecimal.valueOf(999999900));
     * 设置的是22  乙方总和其实是110
     * 【注意】
     */
    @JTest
    @JTestClass.title("总金额为110")
    public void test0013_projectByPAFirstPay() {
        ProjectBalanceOutAc projectBalanceOutAc = new ProjectBalanceOutAc();
        //这个钱是乙方全部的和
        projectBalanceOutAc.setBalance(BigDecimal.valueOf(22));
        projectBalanceOutAc.setPassword("e10adc3949ba59abbe56e057f20f883e");
        projectBalanceOutAc.setTradeId(4487);//第一次支付的id


        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();
        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        //usera.setBalance(BigDecimal.valueOf(1));
        usera.setToInnerId(67);
        usera.setBalance(BigDecimal.valueOf(11));
        projectBalanceAgentList.add(usera);

        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        //usetb.setBalance(BigDecimal.valueOf(1));
        usetb.setToInnerId(68);
        usetb.setBalance(BigDecimal.valueOf(11));
        projectBalanceAgentList.add(usetb);

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        ProjectBalanceProjectAgent projectBalanceAgent = service.phasePayPa2Pb(projectBalanceOutAc, projectBalanceAgentList);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));
        System.out.println("====================================================================================");
    }
    /**
     * zc 測試
     * 方法：甲方结算-》乙方钱
     * 測試結果：成功
     */
    @JTest
    @JTestClass.title("总金额为100")
    public void test0014_projectByPAFirstPay() {
        ProjectBalanceOutAc projectBalanceOutAc = new ProjectBalanceOutAc();
        //这个钱是乙方全部的和
        projectBalanceOutAc.setBalance(BigDecimal.valueOf(100));
        projectBalanceOutAc.setPassword("e10adc3949ba59abbe56e057f20f883e");
        projectBalanceOutAc.setTradeId(4487);//第一次支付的id


        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();
        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        //usera.setBalance(BigDecimal.valueOf(1));
        usera.setToInnerId(67);
        usera.setBalance(BigDecimal.valueOf(90));
        projectBalanceAgentList.add(usera);

        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        //usetb.setBalance(BigDecimal.valueOf(1));
        usetb.setToInnerId(68);
        usetb.setBalance(BigDecimal.valueOf(10));
        projectBalanceAgentList.add(usetb);

        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        ProjectBalanceProjectAgent projectBalanceAgent = service.phasePayPa2Pb(projectBalanceOutAc, projectBalanceAgentList);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));
        System.out.println("====================================================================================");
    }

    /**
     * zc 測試
     * 方法：甲方结算-》乙方钱  项目冻结款入账 乙方项目金额冻结--》总金额
     * 測試結果：运行成功
     * 但是account_trade 没有交易记录
     * 【注意】
     */
    @JTest
    @JTestClass.title("总金额为20")
    public void test0015_projectPbUnfreeze() {

        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();
        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        usera.setBalance(BigDecimal.valueOf(10));
        usera.setToInnerId(67);
        //usera.setBalance(BigDecimal.valueOf(10));

        projectBalanceAgentList.add(usera);

        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        usetb.setBalance(BigDecimal.valueOf(10));
        usetb.setToInnerId(68);
        //usetb.setBalance(BigDecimal.valueOf(10));
        projectBalanceAgentList.add(usetb);
        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        List<AccountTradeProject> projectBalanceAgent = service.projectPbUnfreeze(projectBalanceAgentList);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));
        System.out.println("====================================================================================");
    }
    /**
     * zc 測試
     * 方法：甲方结算-》乙方钱  项目冻结款入账 乙方项目金额冻结--》总金额
     * 68的冻结金额只有1121，解冻1221，代码运行失败；金额为负数，运行失败
     * 測試結果：运行失败
     * 【注意】
     */
    @JTest
    @JTestClass.title("总金额异常数据")
    public void test0016_projectPbUnfreeze() {

        List<ProjectBalanceToAc> projectBalanceAgentList = new ArrayList<>();
        ProjectBalanceToAc usera = new ProjectBalanceToAc();
        usera.setBalance(BigDecimal.valueOf(10));
        usera.setToInnerId(67);
        //usera.setBalance(BigDecimal.valueOf(10));

        projectBalanceAgentList.add(usera);

        ProjectBalanceToAc usetb = new ProjectBalanceToAc();
        usetb.setBalance(BigDecimal.valueOf(-1221));
        usetb.setToInnerId(68);
        //usetb.setBalance(BigDecimal.valueOf(10));
        projectBalanceAgentList.add(usetb);
        ProjecTransactionService service = new ProjecTransactionServiceImpl();
        List<AccountTradeProject> projectBalanceAgent = service.projectPbUnfreeze(projectBalanceAgentList);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));
        System.out.println("====================================================================================");
    }
    /**
     * zc 測試
     * 方法：提现审核 提现冻结
     * 測試結果：运行成功，但是account_trade 没有交易记录
     * account_user_detail   trade_balance没对上数据，trade_subtype 为null
     * 【注意】
     */
    @JTest
    @JTestClass.title("金额20")
    public void test0017_withdrawCash() {

        // balance:1,tradeSubtype:2,outId:146,"password":"e10adc3949ba59abbe56e057f20f883e"
        ProjecTransactionServiceImpl accountTradeService = new ProjecTransactionServiceImpl();
        AccountTrade record  = new AccountTrade();
        Integer innerId = 67;
        record.setAcId(innerId);
        record.setUserId(202);
        BigDecimal balance = BigDecimal.valueOf(20);
        record.setBalance(balance);
        record.setTotalBalance(balance);

        int sta = accountTradeService.withdrawCash(record, innerId, balance);

        if (sta > 0) {
            System.out.println("成功了");
        }
        System.out.println("====================================================================================");
    }
    /**
     * zc 測試
     * 方法：提现审核 提现冻结
     * 測試結果：运行失败，余额不足200；金额负数
     * 【注意】
     */
    @JTest
    @JTestClass.title("金额异常")
    public void test0018_withdrawCash() {

        // balance:1,tradeSubtype:2,outId:146,"password":"e10adc3949ba59abbe56e057f20f883e"
        ProjecTransactionServiceImpl accountTradeService = new ProjecTransactionServiceImpl();
        AccountTrade record
                = new AccountTrade();
        Integer innerId = 67;
        record.setAcId(innerId);
        record.setUserId(202);
        BigDecimal balance = BigDecimal.valueOf(200);
        record.setBalance(balance);
        record.setTotalBalance(balance);

        int sta = accountTradeService.withdrawCash(record, innerId, balance);

        if (sta > 0) {
            System.out.println("成功了");
        }
        System.out.println("====================================================================================");
    }
    /**
     * zc 測試
     * 方法： 直接转账到账  甲方流水- -， 资金- - 支出++ , 乙方收入++资金++ 收入++
     * 測試結果：运行成功
     */
    @JTest
    @JTestClass.title("金额1")
    public void test0019_transferService() {

        AccountTradeProject accountTrade = new AccountTradeProject();
        accountTrade.setAcId(67);
        accountTrade.setToAcId(68);
        accountTrade.setBalance(BigDecimal.valueOf(1));
        Short sh = 3;
        accountTrade.setTradeSubtype(sh);
        Short s = 50;
        accountTrade.setTradeType(s);
        Byte by = 1;
        accountTrade.setServiceType(by);

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        int projectBalanceAgent = service.transferService(accountTrade);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));
        System.out.println("====================================================================================");
    }
    /**
     * zc 測試
     * 方法： 直接转账到账  甲方流水- -， 资金- - 支出++ , 乙方收入++资金++ 收入++
     * 測試結果：运行失败
     * 负数  1失败 流水更新失败,未取得用户信息 : 失败 流水更新失败,未取得用户信息；
     * 金额过大 1失败 甲方扣费失败 : 失败 甲方扣费失败
     * 【注意】
     */
    @JTest
    @JTestClass.title("金额异常")
    public void test0020_transferService() {

        AccountTradeProject accountTrade = new AccountTradeProject();
        accountTrade.setAcId(67);
        accountTrade.setToAcId(68);
        accountTrade.setBalance(BigDecimal.valueOf(11));
        Short sh = 3;
        accountTrade.setTradeSubtype(sh);
        Short s = 50;
        accountTrade.setTradeType(s);
        Byte by = 1;
        accountTrade.setServiceType(by);

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        int projectBalanceAgent = service.transferService(accountTrade);
        System.out.println("订单号为:" + JSONObject.toJSON(projectBalanceAgent));
        System.out.println("====================================================================================");
    }

    /**
     * zc 測試
     * 方法： 项目阶段退款
     * 測試結果：运行失败
     *
     * 【注意】
     */
    @JTest
    @JTestClass.title("金额1")
    public void test0021_projectRefund() {

        Integer tradeId = 4521;//这要传阶段订单

        BigDecimal RefundM = BigDecimal.valueOf(1);

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        Boolean sta = service.projectRefund(tradeId, RefundM);
        System.out.println("订单:" + sta);
        System.out.println("====================================================================================");
    }


    /**
     * zc 測試
     * 方法： 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     * 測試結果：运行成功
     * 建议createTime 为空时，设置为当前时间
     */
    @JTest
    @JTestClass.title("金额1")
    public void test0022_frozenAmount() {

        AccountTradeProject accountTrade = new AccountTradeProject();
        accountTrade.setAcId(67);
       // accountTrade.setTradeId(3829);
        accountTrade.setBalance(BigDecimal.valueOf(1));
//        accountUserDetail.setInnerId(accountTrade.getAcId());
//        accountUserDetail.setTradeId(accountTrade.getTradeId());
//        accountUserDetail.setBalance(accountTrade.getBalance());

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        Integer integer = service.frozenAmount(accountTrade);
        System.out.println("订单:" + integer);
        System.out.println("====================================================================================");
    }
    /**
     * zc 測試
     * 方法： 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     * 測試結果：运行失败
     * 负数  1失败 金额不能为负 : 失败 金额不能为负
     * 金额过大 1失败 冻结金额失败 : 失败 冻结金额失败
     * 建议createTime 为空时，设置为当前时间
     * 【注意】
     */
    @JTest
    @JTestClass.title("金额异常")
    public void test0023_frozenAmount() {

        AccountTradeProject accountTrade = new AccountTradeProject();
        accountTrade.setAcId(67);
        // accountTrade.setTradeId(3829);
        accountTrade.setBalance(BigDecimal.valueOf(800));
//        accountUserDetail.setInnerId(accountTrade.getAcId());
//        accountUserDetail.setTradeId(accountTrade.getTradeId());
//
//        accountUserDetail.setBalance(accountTrade.getBalance());

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        Integer integer = service.frozenAmount(accountTrade);
        System.out.println("订单:" + integer);
        System.out.println("====================================================================================");
    }

    /**
     * zc 測試
     * 方法： 直接保存accountTrade  accountUserDetail
     * 測試結果：运行成功
     * 【注意】
     */
    @JTest
    @JTestClass.title("空数据")
    public void test0024_insertTradeAndUserDetailService() {

        AccountTradeProject accountTrade = new AccountTradeProject();
        accountTrade.setTradeType(TradeConst.TRADE_TYPE_PAYOUT);
        accountTrade.setTradeSubtype(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN);
        accountTrade.setStatus(TradeConst.status_passed);
        accountTrade.setServiceType(TradeConst.SERVICE_TYPE_project);
       // accountTrade.setAddTime(new Date());

        AccountUserDetail accountUserDetail = new AccountUserDetail();
        //accountTrade.setTradeId(3829);

        ProjecTransactionServiceImpl service = new ProjecTransactionServiceImpl();
        Integer integer = service.insertTradeAndUserDetailService(accountTrade,accountUserDetail);
        System.out.println("订单:" + integer);
        System.out.println("====================================================================================");
    }

    public static void main(String[] args) {
        run(ProjecTransactionTest.class, "test0001_newOrder");
    }
}
