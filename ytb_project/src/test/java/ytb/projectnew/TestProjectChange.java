package ytb.projectnew;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.project.daoservice.ChangeDaoServiceImpl;
import ytb.project.model.TabPhaseModel;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 项目大小变更测试
 * Package: ytb
 * Author: ZCS
 * Date: Created in 2019/4/3 13:32
 */
public class TestProjectChange extends ITestImpl {
    String url_projectCenter = "http://mysql.kunlong.com/rest/projectCenter";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    MsgRequest req = new MsgRequest();
    String token = "19850a99e7bc4dd09d36c5550b5389fc";
    String data;

    public void suiteSetUp() {

    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @JTest
    @JTestClass.title("变更准备")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0001() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "paApplyChange";

        req.msgBody = JSONObject.parseObject("{\"changeType\":101,\"projectId\":2110," +
                "\"talkId\":5319}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("甲方需求变更页面")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0002() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "pmViewChange";

        req.msgBody = JSONObject.parseObject("{\"talkId\":7005,\"newProjectId\":2964,\"phase\":601}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("确定大小变更")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0003() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "computeChange";

        req.msgBody = JSONObject.parseObject("{\"docType\":100,\"oldTalkId\":5334,\"talkId\":5353}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("甲方审核递交的变更文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0004() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "paAuditChange";

        req.msgBody = JSONObject.parseObject("{\"status\":7,\"newTalkId\":6580,\"talkId\":6579}");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("准备项目变更")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0005() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "paAgreeChange";

        req.msgBody.fluentPut("talkId", 6306);
        req.msgBody.fluentPut("changeItems", "1,2");
        req.msgBody.fluentPut("items", "{　　\n" +
                "                \"ChangeItems\": [\n" +
                "                {　　　　　　\n" +
                "                    \"id\": 1,\n" +
                "                    \"descr\": \"修改项目的功能、性能等\"\n" +
                "                }, {\n" +
                "                    \"id\": 2,\n" +
                "                    \"descr\": \"变更乙方工作组人员\"\n" +
                "                }*/");
        req.msgBody.fluentPut("projectId", 2564);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }



    @JTest
    @JTestClass.title("准备项目变更")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0006() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getProjectTaskTab";

        req.msgBody.fluentPut("talkId", 7064);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("点击去结项")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0007() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "toKnitPro";

        req.msgBody.fluentPut("talkId", 6946);
        req.msgBody.fluentPut("phase", 601);
        req.msgBody.fluentPut("newProjectId", 2932);
        req.msgBody.fluentPut("partyBy", 1);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("点击去结项")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0008() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "getProjectTask";

        req.msgBody.fluentPut("type", "PA");
        req.msgBody.fluentPut("stage", 601);
        req.msgBody.fluentPut("talkId", 269);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("点击去结项")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test0009() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "pbSubmitKnitDoc";

        req.msgBody.fluentPut("talkId", 6395);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("设置user_login表信息")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/ytbsuser")
    @JTestClass.exp("ok")
    public void test0001_userlogin() {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "userLogin";
        req.cmd = "login";

        req.msgBody.fluentPut("mode", "mode");
        req.msgBody.fluentPut("password", "e10adc3949ba59abbe56e057f20f883e");
        req.msgBody.fluentPut("username", "18270054570");
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }


    @JTest
    @JTestClass.title("申请项目终止")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test00010() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "goStopProject";

        req.msgBody.fluentPut("partyBy", 1);
        req.msgBody.fluentPut("stopReason", 801);
        req.msgBody.fluentPut("talkId", 6924);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("申请项目终止")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test00011() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "paViewStop";

        req.msgBody.fluentPut("phase", 601);
        req.msgBody.fluentPut("talkId", 6684);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("组员递交文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test00012() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "memberSubmit";

        req.msgBody.fluentPut("phase", 601);
        req.msgBody.fluentPut("talkId", 6972);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    @JTest
    @JTestClass.title("组员递交文档")
    @JTestClass.pre("\"msgBody\":{\"docType\":200,\"documentId\":204,\"projectId\":0,\"version\":\"1.0\"}")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/project/template")
    @JTestClass.exp("ok")
    public void test00013() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectFlow";
        req.cmd = "memberSubmit";

        req.msgBody.fluentPut("phase", 601);
        req.msgBody.fluentPut("talkId", 6972);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    public static void main(String args[]) {

        run(TestProjectChange.class, "test0008");
        /*TestProjectChange op = new TestProjectChange();
        List<TabPhaseModel> listPhase  = op.getPro();
        for(TabPhaseModel tabPhaseModel : listPhase){
            System.out.println(tabPhaseModel.getPhaseText());
        }*/
    }


    public List<TabPhaseModel>  getPro(){
        int phaseNo = 6;
        int currPhase = 601;
        List<TabPhaseModel> lis =  new ArrayList<>();
        List<TabPhaseModel> list = new ChangeDaoServiceImpl().getChangeModelList(2565);
        List<TabPhaseModel> listPhase = new ArrayList<>();

        for(int i = 0;i< (currPhase - 600); i++){
            TabPhaseModel pp = new TabPhaseModel();
            pp.setPhase(600+(i+1));
            pp.setPhaseText("第"+(i+1)+"阶段");
            listPhase.add(pp);
        }
        TabPhaseModel talkPP = new TabPhaseModel();
        talkPP.setPhaseText("洽谈阶段");
        talkPP.setPhase(200);
        listPhase.add(talkPP);

        for(int i = 0 ;i < list.size();i++){
            TabPhaseModel pp = new TabPhaseModel();
            pp.setPhaseText("变更需求洽谈("+(i+1)+")");
            pp.setPhase(list.get(i).getPhase());
            pp.setChangeType(list.get(i).getChangeType());
            lis.add(pp);
        }


        listPhase.addAll(lis);

        Collections.sort(listPhase, new Comparator<TabPhaseModel>() {
            @Override
            public int compare(TabPhaseModel o1, TabPhaseModel o2) {
                int flag = o1.getPhase().compareTo(o2.getPhase());//先根据phase升序排列
                if(flag == 0) {
                    flag = o2.getChangeType() - o1.getChangeType();//根据changType降序
                }
                return flag;
            }
        });
        return listPhase;
    }
}
