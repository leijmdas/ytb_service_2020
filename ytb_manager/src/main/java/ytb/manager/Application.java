package ytb.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ytb.common.test.CorsConfig;


/**
 * docker run --name mngr -p 10080:10080 --add-host mysql.kunlong.com:192.168.222.128 -v /platform/logs:/platform/logs mngr:latest
 * Package: PACKAGE_NAME
 * Author: ZCS
 * Date: Created in 2018/9/14 12:04
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"ytb"})
@ImportResource({"classpath:AppContextCommon.xml"})
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("start mngr");
        Class[] bootLst = new Class[]{
                CorsConfig.class,   Application.class
        };

        SpringApplication.run(bootLst, args);
    }
}
