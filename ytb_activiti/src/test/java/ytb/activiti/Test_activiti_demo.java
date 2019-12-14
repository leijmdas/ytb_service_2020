package ytb.activiti;

import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

public class Test_activiti_demo extends ITestImpl {
    ClassPathXmlApplicationContext ctxt;//=new ClassPathXmlApplicationContext("activiti_context.xml");
    public void setUp(){
        ctxt=new ClassPathXmlApplicationContext("dbconfig/activiti/activiti_context.xml");

    }
    public void test0001_initCreateTable() {
        //表不存在的话创建表
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("test/activiti.cfg.xml")
                .buildProcessEngine();
        System.out.println("------processEngine:" + processEngine);
       //DbSchemaCreate.main(new String[]{});
    }


    //ClassPathXmlApplicationContext ctxt=new ClassPathXmlApplicationContext("activiti_context.xml");
    @JTest
    @JTestClass.title("工作流test0002_demo_workflow")
    @JTestClass.pre("启动工作流　分配任务")
    @JTestClass.step("任务认领　任务执行完毕")
    @JTestClass.exp("任务已经执行")
    public void test0002_demo_workflow() {
        Deployment deploy;

        RepositoryService repositoryService=ctxt.getBean("repositoryService",RepositoryService.class);
        // 部署流程定义
       // deploy  = repositoryService.createDeployment().addClasspathResource("activitiService.bpmn20/ytb.bpmn20.xml").deploy();
        deploy  = repositoryService.createDeployment().addClasspathResource("dbconfig/activiti/service.bpmn20/test.bpmn").deploy();
        TaskService taskService=ctxt.getBean("taskService",TaskService.class);
        HistoryService historyService=ctxt.getBean("historyService",HistoryService.class);
        RuntimeService runtimeService=ctxt.getBean("runtimeService",RuntimeService.class);

        // 启动流程实例


        String procId = runtimeService.startProcessInstanceByKey("myProcess_1").getId();
        // 获得第一个任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("Users").list();
        for (Task task : tasks) {
            taskService.setVariable(task.getId(), "json", "流程变量设置成功~~\"");
            String s=taskService.getVariable(task.getId(),"json").toString();
            System.out.println(s);
            System.out.println("Following task is available for sales group: " + task.getName());
            // 认领任务这里由foozie认领，因为fozzie是sales组的成员
            taskService.claim(task.getId(), "cc");
        }

         // 查看fozzie现在是否能够获取到该任务
        tasks = taskService.createTaskQuery().taskAssignee("cc").list();
        for (Task task : tasks) {

            System.out.println("Task for fozzie: " + task.getName());
            System.out.println("Task for fozzie getId: " + task.getId());
            // 执行(完成)任务
            taskService.complete(task.getId());
        }

        // 现在fozzie的可执行任务数就为0了
        System.out.println("Number of tasks for fozzie: "
                + taskService.createTaskQuery().taskAssignee("cc").count());


        // 获得第二个任务
       // tasks = taskService.createTaskQuery().taskCandidateGroup("Users1").list();
        tasks = taskService.createTaskQuery().taskAssignee("kk").list();
        for (Task task : tasks) {

            System.out.println("Following task is available for accountancy group:" + task.getName());
            // 认领任务这里由kermit认领，因为kermit是management组的成员
            taskService.claim(task.getId(), "kk");
        }

        // 完成第二个任务结束流程
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }
        //JobExecutorActivate jobExecutorActivate;
        // 核实流程是否结束,输出流程结束时间
        HistoricProcessInstance hinst = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(procId).singleResult();
        System.out.println("Process instance end time: " + hinst.getEndTime());

    }

    @JTest
    @JTestClass.title("用户管理test0003_user")
    @JTestClass.pre(" ")
    @JTestClass.step(" ")
    @JTestClass.exp(" ")
    public void test0003_user() {
//        IdentityService identityService=ctxt.getBean("identityService",IdentityService.class);
//        User user = identityService.newUser("fozzie1");
//        user.setFirstName("fozzie1");
//        user.setLastName("chang");
//        user.setEmail("whatlookingfor@gmail.com");
//        user.setPassword("123");
//        //保存用户到数据库
//        identityService.saveUser(user);
//        Group group = identityService.newGroup("sales");
//        group.setName("sales用户组");
//        group.setType("assignment");
//        //保存用户组
//        identityService.saveGroup(group);
//        //将用户Jonathan加入到用户组hr中
//        identityService.createMembership("fozzie1","sales");

    }

    public void test0004_checkGroup() {
//        IdentityService identityService=ctxt.getBean("identityService",IdentityService.class);
//        //查询属于HR用户组的用户
//        User userInGroup = identityService.createUserQuery().memberOfGroup("sales").singleResult();
//        Assert.notNull(userInGroup);
//        Assert.isTrue(userInGroup.getId().equals("fozzie1"));
//        //查询用户所属组
//        Group groupContainsUser = identityService.createGroupQuery().groupMember("fozzie1").singleResult();
//        Assert.notNull(groupContainsUser);
//        Assert.isTrue(groupContainsUser.getId().equals("sales"));

        ManagementService j;
    }

    //    select distinct RES.* from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ WHERE RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' and ( I.GROUP_ID_ IN ( ? ) ) order by RES.ID_ asc LIMIT ? OFFSET ?
    //     management(String), 2147483647(Integer), 0(Integer)


//
//    名为spring-boot-activiti的数据库并且在application.properties中添加如下配置信息：
//
//            ##############################################################################
//            #########datasource
//##############################################################################
//    spring.datasource.url=jdbc:mysql://localhost:3306/spring-boot-activiti?characterEncoding=utf8&useSSL=true
//    spring.datasource.username=root
//    spring.datasource.password=root
//    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
//#自动创建、更新、验证数据库表结构
//    spring.jpa.properties.hibernate.hbm2ddl.auto=update
//    spring.jpa.show-sql=true

    public static void main(String[] args) {
        run(Test_activiti_demo.class, 2);

    }
}
