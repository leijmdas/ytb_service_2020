package ytb.account.cashpay.alipay.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ytb.account.cashpay.alipay.model.AccountAlipayBusiness;
import ytb.account.cashpay.integration.config.AliPayConfigureTodo;
import ytb.account.cashpay.integration.service.db.AccountTradeOutService;
import ytb.account.cashpay.integration.service.db.impl.AccountTradeOutServiceImpl;
import ytb.account.fastpay.model.alipay.AliPayConfigure;
import ytb.account.fastpay.service.Alipay.AlipayNotifySerivce;
import ytb.account.wallet.model.AccountTradeOut;
import ytb.account.wallet.model.AccountTradeOutExample;
import ytb.account.wallet.service.service.cashpay.transaction.user.payment.ExternalPaymentService;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.*;

/**
 * http://localhost:8080//hhh?name=d62&age=23
 */

@RestController
@Scope("prototype")
@RequestMapping(value = "/alipay")


public class AliPayNotifyUrlController {
    public static int Surplus = 20;
    // private ExecutorService executorService = Executors.newFixedThreadPool(20);


    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void close() {
        executor.shutdown();
    }

    void addTask(Runnable runnable) {
        executor.execute(runnable);
    }

    <V> V addTask(Callable<V> callable) {
        Future<V> submit = executor.submit(callable);
        try {
            return submit.get();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.toString());
        } catch (ExecutionException e) {
            System.out.println("ExecutionException" + e.toString());
        }
        return null;
    }

    AliPayConfigure aliPayConfigure = new AliPayConfigureTodo().getConfig();


    @RequestMapping(value = "/notifyUrl", method = RequestMethod.POST)
    public String notifyUrl(HttpServletRequest request) {
        AlipayNotifySerivce alipayNotifySerivce = new AlipayNotifySerivce();
        AccountAlipayBusiness business = alipayNotifySerivce.notify(request);


        if (business != null) {
            AccountTradeOut accountTradeOut = alipayNotifySerivce.busToTradeOut(business);
            addTask(new Runnable() {

                @Override
                public void run() {


                    String trade_status = "TRADE_SUCCESS";

                    if (trade_status.equals(business.getTradeStatus())) {

                        try {

                            ExternalPaymentService ExternalPaymentService = new ExternalPaymentService();
                            ExternalPaymentService.alipayRecharge(accountTradeOut);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


            });

            addTask(new Runnable() {
                @Override
                public void run() {
                    AccountTradeOutService accountTradeOutService = new AccountTradeOutServiceImpl();

                    AccountTradeOutExample example = new AccountTradeOutExample();

                    AccountTradeOutExample.Criteria criteria = example.createCriteria();

                    criteria.andTradeIdEqualTo(accountTradeOut.getTradeId());

                    accountTradeOutService.updateByExampleSelective(accountTradeOut, example);


                }
            });


            return "success";
        } else {
            return "failure";
        }

    }


}
