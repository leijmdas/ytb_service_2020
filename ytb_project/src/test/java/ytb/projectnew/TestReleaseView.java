package ytb.projectnew;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.Flow;
import ytb.common.TestProjectConst;
import ytb.common.UserLogin;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.projectview.ProjectResultViewModel;
import ytb.project.service.impl.release.ReleaseView;
import ytb.common.RestMessage.MsgRequest;

public class TestReleaseView extends ITestImpl {
    Flow flow = new Flow();

    int paId = TestProjectConst.paId;
    UserProjectContext context = new UserProjectContext();

    ProjectModel pm;
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    String url_projectCenter = TestProjectConst.url_projectCenter;

    MsgRequest req = new MsgRequest();
    String token = " ";

    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }

    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;
    @Override
    public void setUp() throws Exception {
        token = new UserLogin().login(paId);
        req.token = token;
        context = new Flow().buildContext(6370, 202);
    }


    @JTest
    @JTestClass.title("洽谈中test0001_releaseView_getProjectLists_talkPhase")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0001_releaseView_getProjectLists_talkPhase() throws Exception {
        ProjectResultViewModel viewModel =  new ReleaseView().getProjectLists(context, ProjectConst.TalkPhase);
        System.out.println(viewModel);
    }

    @JTest
    @JTestClass.title("进行中test0002_releaseView_getProjectLists_PhaseIn")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0002_releaseView_getProjectLists_PhaseIn() throws Exception {
        ProjectResultViewModel viewModel =  new ReleaseView().getProjectLists(context,
                ProjectConst.Phase_START);
        System.out.println(viewModel);
    }

    @JTest
    @JTestClass.title("完成test0003_releaseView_getProjectLists_Finish")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0003_releaseView_getProjectLists_Finish() throws Exception {
        ProjectResultViewModel viewModel =  new ReleaseView().getProjectLists(context,
                ProjectConstState.CHNAGE_TYPE_FINISH);
        System.out.println(viewModel);
    }

    //company

    public static void main(String args[]) {
        run(TestReleaseView.class, 3);

    }
}
