package test;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;

import ytb.account.cashpay.alipay.pojo.AliPayTransferAccounts;
import ytb.account.cashpay.alipay.service.pay.AlipayService;
import ytb.account.cashpay.alipay.service.pay.impl.AlipayServiceImpl;
import ytb.account.cashpay.integration.config.AliPayConfigureTodo;
import ytb.account.fastpay.model.alipay.AliPayConfigure;
import ytb.account.fastpay.model.fast.PaymentInformationModel;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.rest.impl.SysRechargeServer;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.service.autoSettlement.impl.SettlementTradeService;
import ytb.account.wallet.service.service.autoSettlement.impl.TradeAuto;
import ytb.account.wallet.service.sq.business.user.impl.ProjecTransactionServiceImpl;
import ytb.common.RestMessage.MsgRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;


@JTestClass.author("accountIncrease")
public class TestPayCenter extends ITestImpl {
    String url_base = "http://mysql.kunlong.com:80/rest/wallet";
    String token = "415cd0fc7e584fa1b235958f7fb11ec4";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

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
    @JTestClass.title("充值测试")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/wallet")
    @JTestClass.exp("ok")
    public void test0001_accountIncrease() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "transaction";
        req.cmd = "accountIncrease";
        req.msgBody = JSONObject.parseObject("{\"balance\":\"0.01\",\"tradeSubtype\":\"3\"}");
        data = new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
    }


    @JTest
    @JTestClass.title("提现")

    public void test0002_accountIncrease() {

        // balance:1,tradeSubtype:2,outId:146,"password":"e10adc3949ba59abbe56e057f20f883e"
        ProjecTransactionServiceImpl accountTradeService = new ProjecTransactionServiceImpl();
        AccountTrade record
                = new AccountTrade();
        Integer innerId = 69;
        record.setAcId(innerId);
        record.setUserId(202);
        BigDecimal balance = BigDecimal.valueOf(1);
        record.setBalance(balance);
        record.setTotalBalance(balance);

        int sta = accountTradeService.withdrawCash(record, innerId, balance);

        if (sta > 0) {
            System.out.println("成功了");
        }

    }


    @JTest
    @JTestClass.title("充值测试-2")

    public void test0003_structureTradePayment() {
        AccountTrade come = new AccountTrade();

        come.setUserId(1);
        come.setCompanyId(2);
        come.setCreateBy(3);
        come.setToAcId(70);
        come.setTradeSubtype((short) 2);

        come.setBalance(BigDecimal.valueOf(1));

        come.setTradeType(TradeConst.TRADE_TYPE_RECHARGE);//充值
        come.setStatus(TradeConst.status_passed);//通过
        come.setCreateTime(new Date());
        come.setTradeSubtype(come.getTradeSubtype());
        come.setBalance(come.getBalance());
        come.setCalFlag(AccountConst.cal_flag_no);

        come.setIpAddress("111.111.111.11");

        PaymentInformationModel asd = SysRechargeServer.structureTradePayment(come);
        System.out.println(JSONObject.toJSONString(asd));
    }

    /**
     * zc 測試
     * 測試結果：運行成功，有bug
     */
    @JTest
    @JTestClass.title("充值测试-充值金額為負數-10")

    public void test0004_structureTradePayment() {
        AccountTrade come = new AccountTrade();

        come.setUserId(1);
        come.setCompanyId(2);
        come.setCreateBy(3);
        come.setToAcId(70);
        come.setTradeSubtype((short) 3);
        come.setBalance(BigDecimal.valueOf(-10));
        come.setIpAddress("111.111.111.11");

        PaymentInformationModel asd = SysRechargeServer.structureTradePayment(come);
        System.out.println(JSONObject.toJSONString(asd));
    }

    /**
     * zc 測試
     * 測試結果：運行成功，asd为NULL
     */
    @JTest
    @JTestClass.title("充值测试-充值金额为0")

    public void test0005_structureTradePayment() {
        AccountTrade come = new AccountTrade();

        come.setUserId(193);
        come.setCompanyId(2);
        come.setCreateBy(3);
        come.setToAcId(70);
        come.setTradeSubtype((short) 3);

        come.setBalance(BigDecimal.valueOf(0));

        /*come.setTradeType(TradeConst.TRADE_TYPE_RECHARGE);//充值
        come.setStatus(TradeConst.status_passed);//通过
        come.setCreateTime(new Date());
        come.setTradeSubtype(come.getTradeSubtype());
        come.setBalance(come.getBalance());
        come.setCalFlag(AccountConst.cal_flag_no);*/
        come.setIpAddress("111.111.111.11");
        PaymentInformationModel asd = SysRechargeServer.structureTradePayment(come);
        System.out.println(JSONObject.toJSONString(asd));
        System.out.println("====================================================================================");
    }

    /**
     * zc 測試
     * 測試結果：運行成功，asd有返回值
     * 数据库中的CreateBy存的是UserId值
     */
    @JTest
    @JTestClass.title("充值测试-充值金额为10 返回二维码")

    public void test0006_structureTradePayment() {
        AccountTrade come = new AccountTrade();

        come.setUserId(193);
        come.setCompanyId(2);
        come.setCreateBy(3);
        come.setToAcId(70);
        come.setTradeSubtype((short) 2);

        come.setBalance(BigDecimal.valueOf(10));

        /*come.setTradeType(TradeConst.TRADE_TYPE_RECHARGE);//充值
        come.setStatus(TradeConst.status_passed);//通过
        come.setCreateTime(new Date());
        come.setTradeSubtype(come.getTradeSubtype());
        come.setBalance(come.getBalance());
        come.setCalFlag(AccountConst.cal_flag_no);*/
        come.setIpAddress("111.111.111.11");

        PaymentInformationModel asd = SysRechargeServer.structureTradePayment(come);
        System.out.println(JSONObject.toJSONString(asd));

//数据样例
        //{"outTradeNo":"7439","paymentMethod":"WeChat","qrCode":"weixin://wxpay/bizpayurl?pr=RXldhNy","totalAmount":10.00}
        System.out.println("====================================================================================");
    }

    @JTest
    @JTestClass.title("tradeAuto自动结算-2")

    public void test0004_Auto() {
        TradeAuto tradeAuto = new TradeAuto();
        tradeAuto.Cash();
    }

    @JTest
    @JTestClass.title("tradeAuto自动结算-2")

    public void test0005_Project() {
        TradeAuto tradeAuto = new TradeAuto();
        //tradeAuto.Project();
    }


    public void test0006_transferFacility() {
        TradeAuto tradeAuto = new TradeAuto();
        tradeAuto.Project();

        AliPayConfigure aliPayConfigure = new AliPayConfigureTodo().getConfig();

        AliPayTransferAccounts aliPayRefundModel = new AliPayTransferAccounts();

        long asd = 13323;

        aliPayRefundModel.setOutBizNo(String.valueOf(asd));
        aliPayRefundModel.setPayeeAccount("13528818072");
        aliPayRefundModel.setAmount(new BigDecimal(0.1));
        aliPayRefundModel.setPayerShowName("YTB提现");
        aliPayRefundModel.setPayeeRealName("陈重华");
        aliPayRefundModel.setRemark("备注");


        AlipayService aliPayService = new AlipayServiceImpl(aliPayConfigure);

        String from = aliPayService.transferFacility(aliPayRefundModel);

        System.out.println(from);

    }

    public void test0007_SettlementTrade() {

        SettlementTradeService accountTradePfService = new SettlementTradeService();
        accountTradePfService.SettlementTrade();


    }


    public static void main(String[] args) throws IOException {

        run(TestPayCenter.class, "test0006_structureTradePayment");

 /*       Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
        System.out.println(yesterday);*/
    }
}
