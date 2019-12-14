package ytb.projectnew;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.CheckResp;
import ytb.common.Flow;
import ytb.common.TestProjectConst;
import ytb.common.UserLogin;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectSrvContext;
import ytb.project.model.ProjectModel;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.*;

public class TestPublish extends ITestImpl {
    Flow flow = new Flow();

    String url_projectCenter = TestProjectConst.url_projectCenter;
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    int paId = TestProjectConst.paId;
    int pbId = TestProjectConst.pbId;
    String friends = TestProjectConst.friends;

    MsgRequest req = new MsgRequest();
    String tokenPa = "";
    String projectName = TestProjectConst.projectName;
    int req_id;


    @Inject("ytb_manager")
    JDbNode dbManager;

    @Inject("ytb_project")
    JDbNode dbProject;


    @Override
    public void setUp() throws Exception {
        tokenPa = new UserLogin().login(paId);
        req.token = tokenPa;
    }

    public static ProjectSrvContext getInst() {
        return ProjectSrvContext.getInst();
    }

    @JTest
    @JTestClass.title("发布项目第一步")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/ / ")
    @JTestClass.exp("ok")
    public void test0001_releaseProject() throws Exception {
        flow.publishFirst(req,friends);
    }

    @JTest
    @JTestClass.title("发布项目ReleaseProjectNext")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/ / ")
    @JTestClass.exp("ok")
    public void test0002_ReleaseProjectNext() throws Exception {
        ProjectModel m = flow.publishFirst(req);

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "releaseProjectNext";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", ProjectConstState.PUBLISH_PASS);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("templateId", flow.getReq_id());

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        dbProject.clearSql().appendSql("select status from project where project_id=");
        dbProject.appendSql(m.getProjectId()).checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");


    }


    @JTest
    @JTestClass.title("发布同时邀请好友")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest  ")
    @JTestClass.exp("ok")
    public void test0003_ReleaseProjectNextInvitePB() throws Exception {
        ProjectModel m = flow.publishFirst(req,friends);
        req_id = flow.getReq_id();

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
        Arrays.stream(friends.split(",")).forEach(System.out::println);
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql("select talk_id from project_invite where user_id2=");
            dbProject.appendSql(f).appendSql(" and project_id=").appendSql(m.getProjectId());
            dbProject.execSelect().checkRecordNumber(1);
            dbProject.select().forEach(System.out::println);
        }
    }

    @JTest
    @JTestClass.title("乙方确认")
    @JTestClass.pre("")
    @JTestClass.step("test0004_releaseProjectInvitePBCheckReq ")
    @JTestClass.exp("ok")
    public void test0004_releaseProjectInvitePBCheckReq() throws Exception {
        ProjectModel m = flow.publishFirst(req,friends);
        req_id = flow.getReq_id();

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
        Arrays.stream(friends.split(",")).forEach(System.out::println);
        Map<String, String> map_takid2token = new LinkedHashMap<>();
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql(" select talk_id from project_invite where user_id2=");
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
        req.msgBody.put("eventType", ProjectConstState.INVITE_STATUS_PASS);

        for(String  talkId:map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            resp = MsgResponse.parseResponse(ret);
            System.out.println(req.toJSONString());
            System.out.println(resp.toJSONString());
            httpclient.checkStatusCode(200);
            dbProject.clearSql().appendSql("select phase from project_talk where talk_id="+talkId);
            dbProject.execSelect().checkRecord("phase=200");
            //乙方需求工作计划书模板
            int folderId=resp.getMsgBody().getJSONObject("result").getInteger("folderId");
            dbProject.clearSql().appendSql("select template_id from project_template where folder_id="+folderId);
            dbProject.execSelect().checkRecordNumber(2);
            dbProject.clearSql().appendSql("select folder_id from project_talk where talk_id="+talkId);
            dbProject.execSelect().checkRecord("folder_id="+resp.getMsgBody().getJSONObject("result").getInteger(
                    "folderId"));
        }

        dbProject.clearSql().appendSql("select 1 from work_group where project_id="+m.getProjectId());
        dbProject.checkRecordNumber(map_takid2token.keySet().size());
        dbProject.clearSql().appendSql("select 1 from project_folder where project_id="+m.getProjectId());
        // dbProject.checkRecordNumber(7);

    }

    @JTest
    @JTestClass.title("乙方申请test0005_releaseProjectPBApply")
    @JTestClass.pre("")
    @JTestClass.step("test0005_releaseProjectPBApply ")
    @JTestClass.exp("ok")
    public void test0005_releaseProjectPBApply() throws Exception {
        ProjectModel m = flow.publishFirst(req);

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "releaseProjectNext";
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
        dbProject.clearSql().appendSql("select status from project where project_id="+m.getProjectId());
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");


        req.cmd = "applyPrj";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("reqRemark", "test pb apply");
        req.msgBody.put("projectId", m.getProjectId());
        int userIdPb = Integer.parseInt(friends.split(",")[0]);
        req.setToken(new UserLogin().login(userIdPb));
        ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        resp=MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONString());
        System.out.println(resp.toJSONString());
        httpclient.checkStatusCode(200);
        checkEQ(0,resp.getRetcode());
        int retTalkId=resp.getMsgBody().getInteger("talkId");
        dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + retTalkId);
        dbProject.checkRecordNumber(1).execSelect().checkRecord("phase=0");

    }

    @JTest
    @JTestClass.title("乙方申请 甲方同意test0006_releaseProjectPBApplyPAConfirm")
    @JTestClass.pre("")
    @JTestClass.step("test0006_releaseProjectPBApplyPAConfirm ")
    @JTestClass.exp("ok")
    public void test0006_releaseProjectPBApplyPAConfirm() throws Exception {
        ProjectModel m = flow.publishFirst(req);
        req_id = flow.getReq_id();

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "releaseProjectNext";
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


        req.cmd = "applyPrj";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("reqRemark", "test pb apply");
        req.msgBody.put("projectId", m.getProjectId());
        int userIdPb = Integer.parseInt(friends.split(",")[0]);
        req.setToken(new UserLogin().login(userIdPb));
        ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        resp = MsgResponse.parseResponse(ret);
        System.out.println(req.toJSONString());
        System.out.println(resp.toJSONString());
        httpclient.checkStatusCode(200);
        checkEQ(0, resp.getRetcode());
        int retTalkId = resp.getMsgBody().getInteger("talkId");
        dbProject.clearSql().appendSql("select 1 from project_invite where talk_id=" + retTalkId);
        dbProject.checkRecordNumber(1).execSelect() ;

        //confirm 乙方申请
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 0);
        req.msgBody.put("projectId", m.getProjectId());
        req.msgBody.put("eventType", ProjectConstState.INVITE_STATUS_PASS);
        req.setToken(tokenPa);
        req.msgBody.put("talkId", retTalkId);
        ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");

        httpclient.checkStatusCode(200);
        dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + retTalkId);
        dbProject.execSelect().checkRecord("phase=200");
        CheckResp c = new CheckResp(ret).parseProjectReqWorkplanId();
        System.out.println(c);

        dbProject.clearSql().appendSql("select 1 from work_group where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(1);

    }


    public static void main(String args[]) {
         run(TestPublish.class, 4);

    }
}
