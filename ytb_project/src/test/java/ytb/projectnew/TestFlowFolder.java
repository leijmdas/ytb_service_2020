package ytb.projectnew;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.TestProjectConst;
import ytb.common.UserLogin;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.List;
import java.util.Map;

public class TestFlowFolder extends ITestImpl {
    String url_projectCenter = TestProjectConst.url_projectCenter;

    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    MsgRequest req = new MsgRequest();
    int paId = TestProjectConst.paId;
    String token = "966b210c1b3243f795d997a62b2cddb7";

    UserProjectContext context=new UserProjectContext();

    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }


    @Override
    public void setUp() throws Exception {
        token =   new UserLogin().login(paId);
        req.token = token;
    }

    @JTest
    @JTestClass.title("test0001_getMemberDocList")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/ / ")
    @JTestClass.exp("ok")
    public void test0001_getMemberDocList () throws Exception {

        // String ret=getInst().getFlowFolder().getMemberDocList(235,602);
        //  String ret=getInst().getFlowFolder().getMemberDocList(235,1);
        //String ret=getInst().getFlowFolder().getMemberDocList(235,1);
        dbProject.sql("select * from project_talk where talk_id=235");
        List<Map<String, Object>> rs= dbProject.select();
          //rs.stream().forEach(x->System.out.println(x));
    }


    @JTest
    @JTestClass.title("test0001_getMemberDocList")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/ / ")
    @JTestClass.exp("ok")
    public void test0002_getMemberDocList () throws Exception {

        // String ret=getInst().getFlowFolder().getMemberDocList(235,2);
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getMemberDocList";
        req.msgBody = JSONObject.parseObject("{ }");
        req.msgBody.fluentPut("stage",2).fluentPut("talkId",235);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONStringPretty());
    }



    public static void main(String args[]) {
        run(TestFlowFolder.class, 2);

    }
}
