package test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ytb.account.cashpay.alipay.controller.AliPayNotifyUrlController;
import ytb.account.cashpay.alipay.controller.AliPayPaymentController;

import ytb.account.cashpay.integration.rest.RestSysPay;
import ytb.account.cashpay.wechat.controller.WechatController;
import ytb.common.test.CorsConfig;
import ytb.account.wallet.rest.RestSysAccount;


/**
 * Package: PACKAGE_NAME
 * Author: ZCS
 * Date: Created in 2018/9/14 12:04
 */
@SpringBootApplication
public class YtbAccountApplicationTest {
    public static void main(String[] args) {

        SpringApplication.run(
                new Class[]{
                        CorsConfig.class,
                        RestSysAccount.class,
                        WechatController.class,
                        AliPayPaymentController.class,
                        AliPayNotifyUrlController.class,
                        RestSysPay.class,
                }, args);

    }
}
