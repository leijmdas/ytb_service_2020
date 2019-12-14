package ytb.projectnew;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.Flow;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.TestProjectConst;
import ytb.common.UserLogin;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.view.model.ProjectTaskViewResult;
import ytb.project.view.service.impl.ProjectTaskViewPhase;
import ytb.manager.templateexcel.model.tag.TagTable;

/**
 * 项目视图接口测试
 * Package: ytb
 * Author: ZCS
 * Date: Created in 2019/3/8 16:58
 */
public class TestProjectView extends ITestImpl {

    String url_projectCenter = TestProjectConst.url_projectCenter;
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    UserLogin userLogin = new UserLogin();
    Flow flow = new Flow();
    TagTable t = new TagTable();

    String friends = TestProjectConst.friends;
    int paId = TestProjectConst.paId;
    int pbId = TestProjectConst.pbId;
    int pmId = TestProjectConst.pmId;

    String tokenPa,tokenPb,tokenPm;
    MsgRequest req = new MsgRequest();

    @Inject("ytb_project")
    JDbNode dbProject;
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_account")
    JDbNode dbAccount;

    @Override
    public void setUp() throws Exception {
       /* tokenPa = userLogin.login(paId);

        req.token = tokenPa;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();*/
    }

    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }

    @JTest
    @JTestClass.title("测试项目流程视图")
    @JTestClass.pre(" ")
    @JTestClass.step("test0001_PhasegetPbProjectTask")
    @JTestClass.exp("ok")
    public void test0001_PhasegetPbProjectTask() throws Exception {

        UserProjectContext context = new Flow().buildContext(2483,125);

        ProjectTaskViewResult r = new ProjectTaskViewPhase().getPbProjectTask(context, 125, 601);
        System.out.println(r);
    }


    @JTest
    @JTestClass.title("测试项目流程视图")
    @JTestClass.pre(" ")
    @JTestClass.step("test0002_PhasegetPaProjectTask")
    @JTestClass.exp("ok")
    public void test0002_getProjectTaskView() throws Exception {

        UserProjectContext context = new Flow().buildContext(7152, 356);
        // ProjectTaskViewResult r = new ProjectTaskViewPhase().getPbProjectTask(context, 123, 601);
        ProjectTaskViewResult result = new ProjectTaskViewPhase().getProjectTaskView(context,
                485, 602, "PA");
        System.out.println(result);
    }
    // req.cmdtype = "projectFlow"; req.cmd = "getProjectTask";

    public static void main(String args[]) {

        run(TestProjectView.class, 2);

    }


}
