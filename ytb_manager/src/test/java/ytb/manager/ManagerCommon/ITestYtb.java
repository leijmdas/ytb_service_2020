package ytb.manager.ManagerCommon;

import com.jtest.testframe.ITestImpl;
import ytb.common.test.BuildAppCtxt;

public class ITestYtb extends ITestImpl {
    public void suiteSetUp() throws Exception {
        BuildAppCtxt.buildAppContext();

    }

    public void suiteTearDown() throws Exception {
        BuildAppCtxt.exitAppContext();

    }
}
