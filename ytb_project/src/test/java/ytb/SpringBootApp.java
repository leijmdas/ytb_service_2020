package ytb;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ytb.check.AppConfig;
import ytb.common.basic.safecontext.dao.LoginSsoMapper;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.MyBatis.template.MybatisManagerTemplate;
import ytb.common.ytblog.YtbLog;
import ytb.common.basic.config.dao.SelectSubSysMapper;
import ytb.common.basic.config.dao.SubSysMapper;
import ytb.common.basic.config.model.SubSysDictModel;
import ytb.common.context.service.impl.AppCtxtUtil;
import ytb.common.context.service.impl.YtbContext;
import ytb.common.test.demo.IStudent;
import ytb.common.test.demo.Student;
import ytb.common.test.demo.ehcache.EhCacheService;
import ytb.project.model.ProjectEventModel;
import ytb.project.view.daoservice.TaskDAOService;

import java.util.List;

//使用 @EnableWebMvc 注解，需要以编程的方式指定视图文件相关配置；
//        使用 @EnableAutoConfiguration 注解，
//        会读取 application.properties 或 application.yml 文件中的配置。
/**
 * Package: PACKAGE_NAME
 * Author: ZCS
 * Date: Created in 2018/9/14 12:04
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "ytb")
@ImportResource({"classpath:AppContextCommon.xml"})
//@PropertySource({"classpath:test.application.yml","classpath:test.jdbc.properties"})
public class SpringBootApp {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpringBootApp.class, args);
       // SpringApplication.run(SpringBootCommon.class, args);

        Student student = AppCtxtUtil.getBean("student", Student.class);
        System.out.println("student:" + student.toString());

        ProjectEventModel eventModel = AppCtxtUtil.getBean("projectEventModel", ProjectEventModel.class);
        YtbLog.logJtest("ProjectEventModel", eventModel);
        YtbLog.logJtest("ProjectEventModel", eventModel.hashCode());
        ProjectEventModel eventModel1 = AppCtxtUtil.getBean(ProjectEventModel.class);
        YtbLog.logJtest("ProjectEventModel", eventModel1.hashCode());
        TaskDAOService taskDAOService = AppCtxtUtil.getBean(TaskDAOService.class);

        YtbLog.logJtest("TaskDAOService", taskDAOService.hashCode());
        taskDAOService.selectCustomTask(0, 0, 0);

//        AppContextAware.show(); YtbAppContextAware.show();
        DefaultKaptcha t = YtbContext.getAppContext().getBean("captchaProducer", DefaultKaptcha.class);
        YtbLog.logJtest("DefaultKaptcha", t.createText());
        DefaultSqlSessionFactory sessionFactory = AppCtxtUtil.getBean("logSqlSessionFactory", DefaultSqlSessionFactory.class);
        //commonSqlSessionFactory
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            LoginSsoMapper newMapper = sqlSession.getMapper(LoginSsoMapper.class);
            LoginSso sso = newMapper.selectByPrimaryKey(131734);
            YtbLog.logJtest("sso", sso);

        }

        SqlSessionTemplate sessionTemplate = AppCtxtUtil.getBean("sqlSessionTemplate", SqlSessionTemplate.class);

//        LoginSso ssoReturn = sessionTemplate.selectOne("ytb.common.basic.loginsso.dao.LoginSsoMapper.selectByPrimaryKey", 131734);
//        YtbLog.logJtest("logsso ", ssoReturn);
//        List<LoginSso> ssoList = sessionTemplate.selectList("ytb.common.basic.loginsso.dao.LoginSsoMapper.selectList", 131738);
//        YtbLog.logJtest("ssoList", YtbUtils.toJSONString(ssoList));

        MybatisManagerTemplate template = AppCtxtUtil.getBean(MybatisManagerTemplate.class);
        List<LoginSso> lst = template.selectList(131731);
        YtbLog.logJtest("lst",lst);

        student = AppCtxtUtil.getBean(Student.class);
        YtbLog.logJtest("student", student.hashCode());
        student = AppCtxtUtil.getBean(Student.class);
        AppConfig appConfig = AppCtxtUtil.getBean(AppConfig.class);

        TestServiceImpl service = AppCtxtUtil.getBean(TestServiceImpl.class);
        LoginSso loginSso = service.selectByPrimaryKey(131738);
        YtbLog.logJtest("LoginSso", loginSso);
        List<SubSysDictModel> subSysDictModels = service.getSubsys_address();
        YtbLog.logJtest("subSysDictModels", subSysDictModels.get(0));
        YtbLog.logJtest("SelectSubSysMapper", service.selectSubsys(1));
        EhCacheService ehCacheService = AppCtxtUtil.getBean(EhCacheService.class);
        YtbLog.logJtest("ehCacheService",ehCacheService.getTimestamp("ehCacheService"));
        Thread.sleep(1001l);
        YtbLog.logJtest("ehCacheService",ehCacheService.getTimestamp("ehCacheService"));
        YtbLog.logJtest("getTimestampNocache",ehCacheService.getTimestampNocache
                ("ehCacheService"));
        Thread.sleep(1001l);
        YtbLog.logJtest("getTimestampNocache", ehCacheService.getTimestampNocache
                ("ehCacheService"));
        IStudent student0 = AppCtxtUtil.getBean(IStudent.class);
        System.out.println("student0:" + student0.toString());

        YtbLog.logJtest("AppConfig", appConfig.getUrl());
        YtbLog.logJtest("TestServiceImpl", service.getUrl());
        YtbLog.logJtest("TestServiceImpl", service.getPort());
    }


    @Service
    //@EnableConfigurationProperties(AppConfig.class)
    public static class TestServiceImpl {
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        @Value("${server.port}")
        String port;
        @Value("${common.url}")
        String url;
//    <!-- 工厂方法-->
//    <bean id="carFactory" class="com.baobaotao.ditype.CarFactory" />
//    <bean id="car5" factory-bean="carFactory" factory-method="createHongQiCar">
//    </bean>
//           <bean class="com.baobaotao.anno.LogonService">
//      <constructor-arg  ref="logDao"></constructor-arg>
//       <constructor-arg ref="userDao"></constructor-arg>
//   </bean>
//         <bean class="com.baobaotao.anno.LogonService">
//       <property name="logDao" ref="logDao"></property>
//       <property name="userDao" ref="userDao"></property>
//   </bean>
        @Autowired
        private SubSysMapper subSysMapper;

        @Autowired
        private LoginSsoMapper ssoMapper;

        @Autowired
        private SelectSubSysMapper selectSubSysMapper;

        public SubSysDictModel selectSubsys(@Param("subsysId") int subsysId) {
            return selectSubSysMapper.selectSubsys(1);
        }

        public void delte(@Param("subsysId") int subsysId) {
            selectSubSysMapper.delete(-1);
        }

        // @Transactional("commonTxManager") //commonTxManager txManagerLog
        @Transactional //commonTxManager txManagerLog
        List<SubSysDictModel> getSubsys_address() {
            return subSysMapper.getSubsys_address();

        }

        @Transactional("txManagerLog")//commonTxManager txManagerLog
        public LoginSso selectByPrimaryKey(Integer ssoid) {
            return ssoMapper.selectByPrimaryKey(ssoid);
        }
    }


}
