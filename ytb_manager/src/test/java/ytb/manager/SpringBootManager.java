package ytb.manager;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ytb.manager.charges.rest.RestCharges;
import ytb.common.test.CorsConfig;
import ytb.common.basic.safecontext.rest.RestSysLoginSso;
import ytb.log.notify.rest.RestTaskLog;
import ytb.manager.metadata.rest.RestMetaDataManager;
import ytb.manager.pfUser.rest.RestPfUser;
import ytb.manager.projectStat.rest.RestProjectStat;
import ytb.manager.sysnotices.rest.RestSysNotices;
import ytb.manager.sysuser.rest.RestSysUserManager;
import ytb.manager.tagtable.rest.RestTagTableService;
import ytb.manager.template.rest.RestTemplateManager;
import ytb.manager.webpagemng.rest.RestWebPageMng;

/**
 * Package: PACKAGE_NAME
 * Author: ZCS
 * Date: Created in 2018/9/14 12:04
 */
@EnableSwagger2
@SpringBootApplication
@ImportResource({"classpath:AppContextCommon.xml"})
public class SpringBootManager {
    public static void main(String[] args) {
        Class[] bootLst = new Class[]{
                CorsConfig.class,
                SpringBootManager.class,
                RestMetaDataManager.class,
                RestSysLoginSso.class,
                RestTemplateManager.class,
                RestTagTableService.class,
                RestSysUserManager.class,
                RestProjectStat.class,
                RestPfUser.class,
                RestSysNotices.class,
                RestCharges.class,
                RestTaskLog.class,
                RestWebPageMng.class

        };
        DruidDataSource l;
        SpringApplication.run(bootLst, args);
    }
}
