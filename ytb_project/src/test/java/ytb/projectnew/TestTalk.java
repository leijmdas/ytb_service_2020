package ytb.projectnew;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.Flow;
import ytb.common.TagTable;
import ytb.common.TestProjectConst;
import ytb.common.UserLogin;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


//测试洽谈
public class TestTalk extends ITestImpl {
    TagTable t = new TagTable();
    Flow f = new Flow();

    String url_projectCenter = TestProjectConst.url_projectCenter;
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    @Inject("ytb_project")
    JDbNode dbProject;
    @Inject("ytb_account")
    JDbNode dbAccount;

    @Inject("ytb_manager")
    JDbNode dbManager;
    MsgRequest req = new MsgRequest();
    String tokenPa = "";
    UserProjectContext context=new UserProjectContext();


    String friends = TestProjectConst.friends;//"123,124,125";
    int paId = TestProjectConst.paId;
    int req_id ;

    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }

    @Override
    public void setUp() throws Exception {
        tokenPa = new UserLogin().login(paId);
        req.token = tokenPa;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();

    }


    @JTest
    @JTestClass.title("发布项目第一步")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/ / ")
    @JTestClass.exp("ok")
    public void test0001_releaseProject() throws Exception {
        f.publishFirst(req);
    }

    ProjectModel publishFirst(String friends) throws Exception {
        return f.publishFirst(req,friends);

    }

    @JTest
    @JTestClass.title("乙方确认")
    @JTestClass.pre("")
    @JTestClass.step("test0002_releaseProjectInvitePBConfirm ")
    @JTestClass.exp("ok")
    public void test0002_releaseProjectInvitePBConfirm() throws Exception {
        ProjectModel m = f.publishFirst(req,friends);

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "releaseProjectNext";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", ProjectConstState.PUBLISH_PASS);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("templateId", req_id);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        dbProject.clearSql().appendSql("select status from project where project_id="+m.getProjectId());
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");
        Arrays.stream(friends.split(",")).forEach(
                x->System.out.println(x)
        );
        Map<String, String> map_takid2token = new LinkedHashMap<>();
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql("select talk_id from project_invite where user_id2=");
            dbProject.appendSql(f).appendSql(" and project_id=").appendSql(m.getProjectId());
            dbProject.execSelect().checkRecordNumber(1);
            List<Map<String, Object>> r = dbProject.select();
            String userToken = new UserLogin().login(Integer.parseInt(f));
            map_takid2token.put(r.get(0).get("talk_id").toString(), userToken);

        }

        //confirm 审核邀请申请
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 0);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("eventType", 2);
        for(String  talkId:map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            System.out.println(req.toJSONString());
            httpclient.checkStatusCode(200);
            dbProject.clearSql().appendSql("select phase from project_talk where talk_id="+talkId);
            dbProject.execSelect().checkRecord("phase=200");

        }

        dbProject.clearSql().appendSql("select 1 from work_group where project_id="+m.getProjectId());
        dbProject.checkRecordNumber(map_takid2token.keySet().size());

    }

    @JTest
    @JTestClass.title("PBSubmitTalk乙方确认")
    @JTestClass.pre("")
    @JTestClass.step("test0003_releaseProjectInvitePBConfirmPBSubmitTalk ")
    @JTestClass.exp("ok")
    public void test0003_releaseProjectInvitePBConfirmPBSubmitTalk() throws Exception {
        ProjectModel m = f.publishFirst(req,friends);

        req.cmdtype = "projectRelease";
        req.cmd = "ReleaseProjectNext";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", ProjectConstState.PUBLISH_PASS);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("docListId", req_id);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        dbProject.clearSql().appendSql("select status from project where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");
        Arrays.stream(friends.split(",")).forEach(
                x -> System.out.println(x)
        );
        Map<String, String> map_takid2token = new LinkedHashMap<>();
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql("select talk_id from project_invite where user_id2=");
            dbProject.appendSql(f).appendSql(" and project_id=").appendSql(m.getProjectId());
            dbProject.execSelect().checkRecordNumber(1);
            List<Map<String, Object>> r = dbProject.select();
            String userToken = new UserLogin().login(Integer.parseInt(f));
            map_takid2token.put(r.get(0).get("talk_id").toString(), userToken);

        }

        //confirm 审核邀请申请
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 0);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("eventType", 2);
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            System.out.println(req.toJSONString());
            httpclient.checkStatusCode(200);
            dbProject.clearSql().appendSql("select * from project_talk where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase=200|phase_status=0");

        }

        //提交洽谈
        dbProject.clearSql().appendSql("select 1 from work_group where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(map_takid2token.keySet().size());
        req.cmdtype = "projectFlow";
        req.cmd = "submitDoc";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("projectId", m.getProjectId());
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);

            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            System.out.println(req.toString());
            httpclient.checkStatusCode(200);
            System.out.println(ret);
            dbProject.clearSql().appendSql("select * from project_talk where talk_id=" + talkId);
            dbProject.checkRecordNumber(1).execSelect().checkRecord("phase=200|phase_status=200");

        }
        System.out.println("ytb.check 一方提交洽谈OK！");
    }

    @JTest
    @JTestClass.title("PBSubmitTalk乙方确认test0004_releaseProjectInvitePBConfirmPBSubmitTalkPAConfirm")
    @JTestClass.pre("")
    @JTestClass.step("test0004_releaseProjectInvitePBConfirmPBSubmitTalkPAConfirm ")
    @JTestClass.exp("ok")
    public void test0004_releaseProjectInvitePBConfirmPBSubmitTalkPAConfirm() throws Exception {
        ProjectModel m = f.publishFirst(req,friends);

        req.cmdtype = "projectRelease";
        req.cmd = "ReleaseProjectNext";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", ProjectConstState.PUBLISH_PASS);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("docListId", req_id);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        dbProject.clearSql().appendSql("select status from project where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");
        Arrays.stream(friends.split(",")).forEach(
                x -> System.out.println(x)
        );
        Map<String, String> map_takid2token = new LinkedHashMap<>();
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql("select talk_id from project_invite where user_id2=");
            dbProject.appendSql(f).appendSql(" and project_id=").appendSql(m.getProjectId());
            dbProject.execSelect().checkRecordNumber(1);
            List<Map<String, Object>> r = dbProject.select();
            String userToken = new UserLogin().login(Integer.parseInt(f));
            map_takid2token.put(r.get(0).get("talk_id").toString(), userToken);

        }

        //confirm 审核邀请申请
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 0);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("eventType", 2);
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            System.out.println(req.toJSONString());
            httpclient.checkStatusCode(200);
            dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase=200");

        }

        //乙方提交洽谈审核
//        dbProject.clearSql().appendSql("select 1 from work_group where project_id=" + m.getProjectId());
//        dbProject.checkRecordNumber(map_takid2token.keySet().size());
//        req.cmdtype = "projectFlow";
//        req.cmd = "submitDoc";
//        req.msgBody = JSONObject.parseObject("{}");
//        req.msgBody.put("projectId", m.getProjectId());
//        for (String talkId : map_takid2token.keySet()) {
//            req.msgBody.put("talkId", talkId);
//            req.setToken(map_takid2token.get(talkId));
//            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
//            httpclient.checkStatusCode(200);
//            //System.out.println(ret);
//            dbProject.clearSql().appendSql("select * from project_talk where talk_id=" + talkId);
//            dbProject.checkRecordNumber(1).execSelect().checkRecord("phase_status=200");
//
//        }
//        System.out.println("ytb.check PB提交洽谈OK！");
        f.pBSubmit(req, m.getProjectId(), map_takid2token);

        //甲方审核通过
//        req.cmd = "verifyProjectDoc";
//        for (String talkId : map_takid2token.keySet()) {
//            req.setToken(tokenPa);
//            req.msgBody.put("talkId", talkId);
//            req.msgBody.put("status", ProjectConst.FOLDER_STATUS_PASS_PB);
//            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
//            httpclient.checkStatusCode(200);
//            System.out.println(ret);
//            dbProject.clearSql().appendSql("select *  from project_talk where talk_id=");
//            dbProject.appendSql(talkId);
//            dbProject.checkRecordNumber(1).execSelect().checkRecord("phase=200|phase_status=300");
//            dbProject.clearSql().appendSql("select talk_id from project_talk  where talk_id=" + talkId);
//            dbProject.execSelect().checkRecord("talk_id="+talkId);
//
//        }
//        System.out.println("ytb.check PA确认洽谈OK！");
        f.pAAudit(req, tokenPa, map_takid2token);

    }

    @JTest
    @JTestClass.title("test0005_releaseProjectInvitePBConfirmPBSubmitTalkPAConfirmCancel")
    @JTestClass.pre("")
    @JTestClass.step("test0005_releaseProjectInvitePBConfirmPBSubmitTalkPAConfirmCancel ")
    @JTestClass.exp("ok")
    public void test0005_releaseProjectInvitePBConfirmPBSubmitTalkPAConfirmCancel() throws Exception {
        ProjectModel m = f.publishFirst(req,friends);

        req.cmdtype = "projectRelease";
        req.cmd = "ReleaseProjectNext";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", ProjectConstState.PUBLISH_PASS);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("docListId", req_id);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        dbProject.clearSql().appendSql("select status from project where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");
        Arrays.stream(friends.split(",")).forEach(
                x -> System.out.println(x)
        );
        Map<String, String> map_takid2token = new LinkedHashMap<>();
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql("select talk_id from project_invite where user_id2=");
            dbProject.appendSql(f).appendSql(" and project_id=").appendSql(m.getProjectId());
            dbProject.execSelect().checkRecordNumber(1);
            List<Map<String, Object>> r = dbProject.select();
            String userToken = new UserLogin().login(Integer.parseInt(f));
            map_takid2token.put(r.get(0).get("talk_id").toString(), userToken);

        }

        //confirm 审核邀请申请
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 0);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("eventType", 2);
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            System.out.println(req.toJSONString());
            httpclient.checkStatusCode(200);
            dbProject.clearSql().appendSql("select phase from project_invite where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase=100");

        }

        //提交洽谈
        dbProject.clearSql().appendSql("select 1 from work_group where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(map_takid2token.keySet().size());
        req.cmdtype = "projectFlow";
        req.cmd = "submitDoc";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("projectId", m.getProjectId());
        for (String talkId : map_takid2token.keySet()) {
            req.msgBody.put("talkId", talkId);
            req.setToken(map_takid2token.get(talkId));
            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            httpclient.checkStatusCode(200);
            //System.out.println(ret);
            dbProject.clearSql().appendSql("select * from project_talk where talk_id=" + talkId);
            dbProject.checkRecordNumber(1).execSelect().checkRecord("phase_status=200");

        }
        System.out.println("ytb.check PB提交洽谈OK！");
        req.cmd="verifyProjectDoc";

        for (String talkId : map_takid2token.keySet()) {
            req.setToken(tokenPa);
            req.msgBody.put("talkId", talkId);
            req.msgBody.put("status", ProjectConst.FOLDER_STATUS_NOTPASS_PB);
            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            httpclient.checkStatusCode(200);
            System.out.println(ret);
            dbProject.clearSql().appendSql("select * from project_talk where talk_id=");
            dbProject.appendSql(talkId);
            dbProject.checkRecordNumber(1).execSelect().checkRecord("phase=200|phase_status=0");

        }
        System.out.println("ytb.check PA确认洽谈OK！");

    }


    @JTest
    @JTestClass.title("洽谈终止")
    @JTestClass.pre("")
    @JTestClass.step("test0006_releaseProjectTalkTermination ")
    @JTestClass.exp("ok")
    public void test0006_releaseProjectTalkTermination() throws Exception {
        ProjectModel m = f.publishFirst(req,friends);

        req.cmdtype = "projectRelease";
        req.cmd = "ReleaseProjectNext";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", ProjectConstState.PUBLISH_PASS);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("docListId", req_id);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        dbProject.clearSql().appendSql("select status from project where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");
        Arrays.stream(friends.split(",")).forEach(
                x -> System.out.println(x)
        );
        Map<String, String> map_takid2token = new LinkedHashMap<>();
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql("select talk_id from project_invite where user_id2=");
            dbProject.appendSql(f).appendSql(" and project_id=").appendSql(m.getProjectId());
            dbProject.execSelect().checkRecordNumber(1);
            List<Map<String, Object>> r = dbProject.select();
            String userToken = new UserLogin().login(Integer.parseInt(f));
            map_takid2token.put(r.get(0).get("talk_id").toString(), userToken);

        }

        //confirm 审核邀请申请
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 0);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("eventType", 2);
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            System.out.println(req.toJSONString());
            httpclient.checkStatusCode(200);
            dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase=200");

        }

        //TERM TALK
        req.cmdtype = "projectFlow";
        req.cmd="talkTermination";

        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            req.msgBody.put("Notify", "Notify test");
            req.msgBody.put("description", "description test");
            ret = httpclient.post(url_projectCenter, req.toString(), "application/json");
            System.out.println(req.toJSONString());
            httpclient.checkStatusCode(200);
            dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase="+ProjectConstState.CHNAGE_TYPE_TALK_TERM);

        }
        System.out.println("ytb.check  PB洽谈TERM！");

    }

    @JTest
    @JTestClass.title("申请洽谈终止")
    @JTestClass.pre("")
    @JTestClass.step("test0007_releaseProjectApplyTalkTermination ")
    @JTestClass.exp("ok")
    public void test0007_releaseProjectApplyTalkTermination() throws Exception {
        ProjectModel m = f.publishFirst(req,friends);

        req.cmdtype = "projectRelease";
        req.cmd = "ReleaseProjectNext";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", ProjectConstState.PUBLISH_PASS);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("docListId", req_id);
        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        dbProject.clearSql().appendSql("select status from project where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");
        Arrays.stream(friends.split(",")).forEach(
                x -> System.out.println(x)
        );
        Map<String, String> map_takid2token = new LinkedHashMap<>();
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql("select talk_id from project_invite where user_id2=");
            dbProject.appendSql(f).appendSql(" and project_id=").appendSql(m.getProjectId());
            dbProject.execSelect().checkRecordNumber(1);
            List<Map<String, Object>> r = dbProject.select();
            String userToken = new UserLogin().login(Integer.parseInt(f));
            map_takid2token.put(r.get(0).get("talk_id").toString(), userToken);

        }

        //confirm 审核邀请申请
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 0);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("eventType", 2);
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            System.out.println(req.toJSONString());
            httpclient.checkStatusCode(200);
            dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase=200");

        }

        //TERM TALK
        req.cmdtype = "projectFlow";
        req.cmd="applyTalkEnd";

        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            ret = httpclient.post(url_projectCenter, req.toString(), "application/json");
            System.out.println(req.toJSONString());
            System.out.println(ret);
            httpclient.checkStatusCode(200);
            dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase="+ProjectConst.TalkPhase);

        }
        System.out.println("ytb.check  PB洽谈TERM！");

    }

    @JTest
    @JTestClass.title("首次支付预付款")
    @JTestClass.pre("")
    @JTestClass.step("test0008_releaseProjectConfirmTalkPayFirst ")
    @JTestClass.exp("ok")
    public void test0008_releaseProjectConfirmTalkPayFirst() throws Exception {
        ProjectModel m = f.publishFirst(req,friends);

        req.cmdtype = "projectRelease";
        req.cmd = "ReleaseProjectNext";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", ProjectConstState.PUBLISH_PASS);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("docListId", req_id);
        String ret = f.postProject(httpclient,req) ;
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

        dbProject.clearSql().appendSql("select status from project where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");
        Arrays.stream(friends.split(",")).forEach(System.out::println);
        Map<String, String> map_takid2token = new LinkedHashMap<>();
        Map<String, String> map_user2token = new LinkedHashMap<>();
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql("select talk_id from project_invite ");
            dbProject.appendSql(" where user_id2=").appendSql(f);
            dbProject.appendSql(" and project_id=").appendSql(m.getProjectId());
            dbProject.execSelect().checkRecordNumber(1);
            List<Map<String, Object>> r = dbProject.select();
            String userToken = new UserLogin().login(Integer.parseInt(f));
            map_takid2token.put(r.get(0).get("talk_id").toString(), userToken);
            map_user2token.put( f, userToken);

        }

        //confirm 审核邀请申请
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 0);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("eventType", 2);
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            ret = f.postProject(req) ;
            dbProject.clearSql().appendSql("select * from project_talk where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase=200|phase_status=0");
        }

        for (String pbId : map_user2token.keySet()) {
            req.setToken(map_user2token.get(pbId));
            Flow f = new Flow().findPbReqWorkplan(Integer.parseInt(pbId), m.getProjectId());
            t.exportAllTables(httpclient, req, f);
        }
        //PB提交需求计划审核
        f.pBSubmit(req, m.getProjectId(), map_takid2token);
        //甲方审核通过
        f.pAAudit(req, tokenPa, map_takid2token);

        //cost导出有问题，测试自己预置数据
        for (String talkId : map_takid2token.keySet()) {

            UserProjectContext context = new UserProjectContext();
            context.buildModel(Integer.parseInt(talkId));
            f.setupCost(context);
        }

        //payProject
        req.cmdtype = "projectFlow";
        req.cmd = "payProject";
        dbAccount.clearSql().appendSql("update account_user_inner  ");
        dbAccount.appendSql(" set balance=100000, ");
        dbAccount.appendSql(" balance_agent=0");
        dbAccount.appendSql(" where user_id=").appendSql(paId);
        dbAccount.execSql();
        int i = 0;//只有一个支付成功
        for (String talkId : map_takid2token.keySet()) {
            //req.setToken(map_takid2token.get(talkId));
            req.setToken(tokenPa);
            req.msgBody.put("talkId", talkId);
            req.msgBody.put("fee", "3333");
            req.msgBody.put("password", UserLogin.payPasswodEn);
            f.postProject(httpclient, req, i == 0 ? 0 : 1001);

            dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + talkId);
            int phase = i == 0 ? ProjectConst.Phase1 : ProjectConst.TalkPhase;
            dbProject.execSelect().checkRecord("phase=" + phase);
            i++;
        }
        dbAccount.clearSql().appendSql("select * from  account_user_inner  " );
        dbAccount.appendSql(" where user_id=").appendSql(paId);
        dbAccount.execSelect();
        dbAccount.checkRecord("balance=76669.00|balance_agent=23331.00");
        System.out.println("ytb.check  payFirst！");

    }


    //测试邀请确认
    @JTest
    @JTestClass.title("test0009_releaseProjectConfirmTalkPayFirst")
    @JTestClass.pre("")
    @JTestClass.step("test0009_releaseProjectConfirmTalkPayFirst")
    @JTestClass.exp("ok")
    public void test0009_releaseProjectConfirmTalkPayFirst() throws Exception {

        req.token = "cf2580a34379426e8ba8e214de66163d";
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", "pb");
        req.msgBody.put("projectId", 7);
        req.msgBody.put("eventType", 2);
        req.msgBody.put("talkId", 7);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        System.out.println("resp: " + resp.toJSONString());
    }

    // StringBuilder sb=VersionRule.buildFirstTemplateTitle(1,
    // "E-3-【项目名称】-【电路板名称1】-设计说明书Va.p.m.n.xlsx","项目","电路");
    public static void main(String args[]) {
        run(TestTalk.class, 8);

    }
}
