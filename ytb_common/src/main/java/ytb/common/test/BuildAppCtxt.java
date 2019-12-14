package ytb.common.test;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ytb.common.context.service.impl.AppCtxtUtil;

public class BuildAppCtxt {
    public static String xml_path = "classpath:AppContextCommon.xml";

    public static void buildAppContext() {

        ApplicationContext context = new ClassPathXmlApplicationContext(xml_path);
        new AppCtxtUtil().setApplicationContext(context);

    }

    public static void exitAppContext(){
        SpringApplication.exit(AppCtxtUtil.getApplicationContext());
    }

}
