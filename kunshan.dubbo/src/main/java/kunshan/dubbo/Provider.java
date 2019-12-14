package kunshan.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;



//zookeeper启动为什么占用8080端口，修改哪个配置文件可以改变端口？
//        在zookeeper启动的时候，看打印信息显示会启动jetty，启动一个adminServer on port 8080；我不想他占用8080端口，请问哪位大神能告诉我哪个配置文件可以修改端口？
//        是我下载的zookeeper版本不对，我下载的是最新版的alpha版本，里面有jetty的启动；下载其他老的稳定版就没任何问题！
//
//        zookeeper最近的版本中有个内嵌的管理控制台是通过jetty启动，也会占用8080 端口。
//        通过查看zookeeper的官方文档，发现有3种解决途径：
//
//        （1）.删除jetty。
//        （2）修改端口。
//        修改方法的方法有两种，一种是在启动脚本中增加 -Dzookeeper.admin.serverPort=你的端口号.
//      一种是在zoo.cfg中增加admin.serverPort=没有被占用的端口号
//        （3）停用这个服务，在启动脚本中增加”-Dzookeeper.admin.enableServer=false


public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext
                ("dubbo-service.xml");
        System.out.println(context.getDisplayName() + ": here");
        context.start();
        System.out.println("服务已经启动...");
        System.in.read();

    }
}