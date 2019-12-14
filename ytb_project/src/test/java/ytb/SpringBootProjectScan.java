package ytb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ytb.common.context.service.impl.AppCtxtUtil;
import ytb.common.test.CorsConfig;
import ytb.common.ytblog.YtbLog;
import ytb.common.test.demo.Student;
//    //显示声明CommonsMultipartResolver为mutipartResolver
//    @Bean(name = "multipartResolver")
//    public MultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
//        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
//        resolver.setMaxInMemorySize(40960);
//        resolver.setMaxUploadSize(5 * 1024 * 1024);//上传文件大小 5M 5*1024*1024
//        return resolver;
//    }
//localhost/swagger-ui.html
/**
 * Package: PACKAGE_NAME
 * Author: ZCS
 * Date: Created in 2018/9/14 12:04
 */
// "classpath:AppContextProject.xml",
//.../actuator/beans
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "ytb")
@ImportResource({"classpath:AppContextCommon.xml"})
public class SpringBootProjectScan {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{CorsConfig.class,
                SpringBootProjectScan.class}, args);

        Student student = AppCtxtUtil.getBean(Student.class);
        YtbLog.logJtest("student", student);
        Student student1 = AppCtxtUtil.getBean(Student.class);
        System.out.println(student.hashCode());
        System.out.println(student1.hashCode());
        ServiceDemo serviceDemo = AppCtxtUtil.getBean(ServiceDemo.class);
        String ret = serviceDemo.testRest();
        System.out.print(ret);
    }

    @Service    //@EnableConfigurationProperties(AppConfig.class)
    public static class ServiceDemo {
        @Autowired
        RestTemplate restTemplate;

        public String testRest() {
            return restTemplate.getForObject("https://www.baidu.com", String.class);

        }
    }
}
