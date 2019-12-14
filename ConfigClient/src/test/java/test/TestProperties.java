package test;

import kunshan.property.DemoProperties;
import kunshan.utils.TaskExecutorConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@SpringBootApplication(scanBasePackages = {
        "kunshan.model","kunshan.property","kunshan.utils"})
@RestController
//@ImportResource({"classpath:AppContextCommon.xml"})
public class TestProperties {

    @Value("${foo}")
    String foo;

    @RequestMapping("foo")
    public String hi() {
        return foo;
    }

    public static class  MyRun implements Runnable{

        @Override
        public void run() {
            for (int i=0;i<100;i++){
                System.out.println(i);
            }
        }
    }



    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(TestProperties.class,
                args);
//        DemoProperties dp = ctxt.getBean(DemoProperties.class);
//        System.out.println(dp);
//        SpringApplication.exit(ctxt);
//
//        ThreadPoolTaskExecutor l;
        //ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor)ctxt.getBean
          //      ("TaskExecutor");
        TaskExecutorConfig executorConfig=new TaskExecutorConfig();
        Executor executor=executorConfig.threadPoolTaskExecutor();
                 executor.execute(new MyRun());

        //executor.execute();
    }
}
