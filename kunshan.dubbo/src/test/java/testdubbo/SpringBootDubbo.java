package testdubbo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//使用 @EnableWebMvc 注解，需要以编程的方式指定视图文件相关配置；
//        使用 @EnableAutoConfiguration 注解，
//        会读取 application.properties 或 application.yml 文件中的配置。

/**
 * Package: PACKAGE_NAME
 * Author: ZCS
 * Date: Created in 2018/9/14 12:04
 */
//@PropertySource({"classpath:test.application.yml","classpath:test.jdbc.properties"})


@SpringBootApplication(scanBasePackages = "kunshan")
@ImportResource({"classpath:dubbo-service.xml"})
//@Profile("dubbo")
public class SpringBootDubbo {


    public static void main(String[] args)  {
        SpringApplication.run(SpringBootDubbo.class, args);
        //org.springframework.web.filter l;

    }




}
