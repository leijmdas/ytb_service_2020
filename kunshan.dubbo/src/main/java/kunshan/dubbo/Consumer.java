package kunshan.dubbo;

import com.alibaba.fastjson.JSONObject;
import kunshan.service.DemoService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        //测试常规服务
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("dubbo-client.xml");
        context.start();
        System.out.println("consumer start");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("consumer");
        System.out.println(demoService.getPermissions(1L));

        JSONObject req=new JSONObject();
        req.fluentPut("id",1).fluentPut("name","ljm");
        System.out.println(demoService.exec(req));
        context.stop();
    }
}