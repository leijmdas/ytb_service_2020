package ytb;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ytb.bangbang.rest.BangbangRest;
import ytb.bangbang.rest.ChatWebSocket;
import ytb.bangbang.rest.ProjectBangBangRest;
import ytb.account.cashpay.alipay.controller.AliPayPaymentController;
import ytb.account.cashpay.wechat.controller.WechatController;
import ytb.common.basic.safecontext.rest.RestSysLoginSso;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.service.impl.AppCtxtUtil;
import ytb.common.test.demo.Student;
import ytb.log.notify.rest.RestTaskLog;
import ytb.log.smslog.rest.RestSmsLog;
import ytb.manager.metadata.rest.RestMetaDataManager;
import ytb.manager.pfUser.rest.RestPfUser;
import ytb.project.rest.RestIndexPageContent;
import ytb.project.rest.RestProjectCenter;
import ytb.project.rest.RestProjectDocument;
import ytb.common.test.CorsConfig;
import ytb.project.rest.RestTagTableProjectService;
import ytb.manager.projectStat.rest.RestProjectStat;
import ytb.manager.sysnotices.rest.RestSysNotices;
import ytb.manager.sysuser.rest.RestSysUserManager;
import ytb.manager.tagtable.rest.RestTagTableService;
import ytb.manager.template.rest.RestTemplateManager;
import ytb.user.rest.RestYtbUserManager;
import ytb.account.wallet.rest.RestSysAccount;
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
/**
 * Package: PACKAGE_NAME
 * Author: ZCS
 * Date: Created in 2018/9/14 12:04
 */
// "classpath:AppContextCommon.xml",
@EnableSwagger2
@SpringBootApplication
@ImportResource({
        "classpath:AppContextProject.xml"})
public class SpringBootProject {

    public static void main(String[] args) {
        SpringApplication.run(
                new Class[]{
                        CorsConfig.class,
                        SpringBootProject.class,
                        RestProjectCenter.class,
                        RestProjectDocument.class,
                        RestIndexPageContent.class,
                        RestYtbUserManager.class,
                        RestTaskLog.class,
                        RestTagTableService.class,
                        RestPfUser.class,
                        RestSysNotices.class,
                        RestSmsLog.class,
                        RestSysLoginSso.class,
                        RestSysAccount.class,
                        WechatController.class,
                        AliPayPaymentController.class,
                        RestTemplateManager.class,
                        BangbangRest.class,
                        RestSysUserManager.class,
                        RestProjectStat.class,
                        RestMetaDataManager.class,
                        RestTemplateManager.class,
                        BangbangRest.class,
                        ChatWebSocket.class,
                        RestTagTableProjectService.class,
                        ProjectBangBangRest.class

                }, args);

        Student student = AppCtxtUtil.getBean(Student.class);
        YtbLog.logJtest("student", student);

    }




}
