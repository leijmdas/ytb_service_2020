package ytb.account.cashpay.wechat.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import ytb.account.fastpay.model.wechat.WechatPayNotifyResult;
import ytb.account.fastpay.service.Wechat.WechatNotifyService;
import ytb.account.wallet.service.service.cashpay.transaction.user.payment.ExternalPaymentService;


import java.util.concurrent.*;

/**
 * http://localhost:8080//hhh?name=d62&age=23
 */

@RestController
@Scope("prototype")
@RequestMapping(value = "/wechat")


public class WechatController {

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

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody

    public String returnurl(@RequestBody String CommodityInfo) {

        System.out.println(CommodityInfo);

        addTask(new Runnable() {

            @Override
            public void run() {
                WechatNotifyService wechatNotifyService = new WechatNotifyService();
                WechatPayNotifyResult weChatNotifyPayBusiness = wechatNotifyService.notifyPay(CommodityInfo);

                ExternalPaymentService accountRechargeService = new ExternalPaymentService();
                accountRechargeService.wechatRecharge(weChatNotifyPayBusiness);

            }


        });

        String returnWXresult = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        return returnWXresult;

    }

}
