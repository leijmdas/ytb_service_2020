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
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.service.impl.pay.payfee.ProjectCostFee;
import ytb.project.service.project.stop.impl.StopService;
import ytb.common.RestMessage.MsgRequest;

import java.util.Map;


/**
 *
 * 1 按代码规范
 * 2 不要有重复代码
 * 3 函数不能太长
 * 4 不能有if for else 好多{}嵌套
 * 5 尽量封装一些小函数
 *
 */

public class TestProjectCostFee extends ITestImpl {

    int paId = TestProjectConst.paId;
    UserProjectContext context = new UserProjectContext();

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    String url_projectCenter = TestProjectConst.url_projectCenter;
    Flow flow = new Flow();
    StopService stopService = new StopService();

    MsgRequest req = new MsgRequest();
    String token = " ";

    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }

    int talkid = 6483;
    int userId = 202;
    @Inject("ytb_account")
    JDbNode dbAccount;
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;
    ProjectCostFee costFee = new ProjectCostFee();

    @Override
    public void setUp() throws Exception {
        token = new UserLogin().login(paId);
        req.token = token;
        context = new Flow().buildContext(talkid, userId);
    }

    //ProjectResultViewModel viewModel =  new ReleaseView().getProjectLists(context, ProjectConst.TalkPhase);

    @JTest
    @JTestClass.title("test0001_computePhaseCostRate")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0001_computePhaseCostRate() throws Exception {
        Map<Integer, Double> userRateMap = costFee.computeSumCostRate(context);
        System.out.println(userRateMap);
        //userRateMap = costFee.computePhaseCostRate(context,601);
        System.out.println(userRateMap);
        Double d=0d;
        for(Double dd:userRateMap.values()){
            d+=dd;

        }
        System.out.println(d);
    }


    public static void main(String args[]) {
        run(TestProjectCostFee.class, 1);

    }
}
