package ytb.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import ytb.common.redis.RedisUtil;
import ytb.common.test.CorsConfig;
import ytb.common.test.rest.DemoController;
import ytb.common.test.rest.RestDemo;

@SpringBootApplication
@ImportResource({"classpath:AppContextCommon.xml"})
@EnableAsync
public class SpringBootCommon {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctxt = SpringApplication.run(new Class[]{
                CorsConfig.class, SpringBootCommon.class,
                DemoController.class, RestDemo.class}, args);
        Task t = ctxt.getBean(Task.class);
        try {
            t.doTestAsync();
            t.doTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RedisUtil.getInstance().set("rediusKey","redis value");
        RedisUtil.getInstance().expire("rediusKey",4000);
        String a=RedisUtil.getInstance().getValue("rediusKey");
        System.out.println(a);
    }


}
