package ytb.user;

import org.springframework.boot.SpringApplication;
import ytb.common.test.CorsConfig;
import ytb.common.basic.safecontext.rest.RestSysLoginSso;
import ytb.user.rest.RestYtbUserManager;


/**
 * Package: PACKAGE_NAME
 * Author: ZCS
 * Date: Created in 2018/9/14 12:04
 */
public class SpringBootUser {
    public static void main(String[] args) {
        SpringApplication.run(new Class[]
                {CorsConfig.class,
                        RestSysLoginSso.class,
                        RestYtbUserManager.class}, args);

    }
}
