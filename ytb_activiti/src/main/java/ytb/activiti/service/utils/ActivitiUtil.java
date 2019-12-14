package ytb.activiti.service.utils;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ActivitiUtil {

    private static ApplicationContext ctxt =  new ClassPathXmlApplicationContext
            ("dbconfig/activiti/activiti.cfg.xml");


    public static ApplicationContext getCtxt() {
        return ctxt ;
    }


}
