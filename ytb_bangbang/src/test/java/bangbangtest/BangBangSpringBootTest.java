package bangbangtest;

import org.springframework.boot.SpringApplication;
import ytb.bangbang.rest.BangbangRest;
import ytb.bangbang.rest.ProjectBangBangRest;
import ytb.common.test.CorsConfig;

/**
 * @author lxz 2019/1/7 19:22
 */

public class BangBangSpringBootTest {

    public static void main(String[] args) {
        Class[] classes = new Class[]{
                CorsConfig.class,
                BangbangRest.class,
                ProjectBangBangRest.class
        };
        SpringApplication.run(classes, args);
    }


}
