package ytb.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ytb.common.test.CorsConfig;
import ytb.common.test.rest.DemoController;
import ytb.common.test.rest.RestDemo;

@SpringBootApplication(scanBasePackages = {"ytb.common"})
@ImportResource({"classpath:AppContextCommon.xml"})
@EnableAsync
@EnableSwagger2
public class SpringBootApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctxt = SpringApplication.run(new Class[]{
                CorsConfig.class }, args);

    }


}
