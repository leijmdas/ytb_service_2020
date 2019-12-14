package ytb.common.ManagerCommon;


import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.testframe.ITestImpl;
import ytb.common.test.BuildAppCtxt;

public class ITestYtb extends ITestImpl {
    @Inject(filename = "node.xml", value = "httpclient")
    protected HttpClientNode httpclient;
    @Inject("ytb_user")
    protected JDbNode dbUser;

    @Inject("ytb_project")
    protected JDbNode dbProject;
    @Inject("ytb_manager")
    protected JDbNode dbManager;
    @Inject("ytb_account")
    protected JDbNode dbAccount;

    //protected UserLogin userLogin = new UserLogin();

    public void suiteSetUp() throws Exception {
        BuildAppCtxt.buildAppContext();

    }

    public void suiteTearDown() throws Exception {
        BuildAppCtxt.exitAppContext();

    }
}
