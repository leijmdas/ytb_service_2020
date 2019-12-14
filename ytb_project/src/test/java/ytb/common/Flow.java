package ytb.common;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.testframe.ITestImpl;
import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.tagtable.IBuyPriceListAllModelService;
import ytb.project.dao.tagtable.IProcessListPriceAllModelService;
import ytb.project.dao.tagtable.ICostModelService;
import ytb.project.dao.tagtable.IWorkGroupMemberModelService;
import ytb.project.daoservice.*;
import ytb.project.model.*;
import ytb.project.model.tagtable.BuyPriceListAllModel;
import ytb.project.model.tagtable.CostModel;
import ytb.project.model.tagtable.ProcessListPriceAllModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.manager.tagtable.model.business.ProjectRateModel;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

public class Flow extends ITestImpl {

    transient IWorkGroupMemberModelService workGroupMemberModelService = new WorkGroupMemberModelServiceImpl();
    ICostModelService costModelService = new CostModelServiceImpl();
    TagTable tagTable = new TagTable();
    String projectName = TestProjectConst.projectName;
    int projectId, talkId;

    int projectTypeId = TestProjectConst.projectTypeId;
    int projectTypeId_purchase = TestProjectConst.projectTypeId_purchase;
    int projectTypeId_processing = TestProjectConst.projectTypeId_processing;

    static String url_projectCenter = TestProjectConst.url_projectCenter;

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    int paId = TestProjectConst.paId;
    int pmId = TestProjectConst.pmId;
    String payPasswodEn = TestProjectConst.payPasswodEn;

    public final static int paBalance = TestProjectConst.paBalance;
    final static String  friends = TestProjectConst.friends;
    //乙方邀请组员
    final static String pmFriends =  TestProjectConst.pmFriends;

    ProjectModel pm;

    @Inject("ytb_project")
    JDbNode dbProject;
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_account")
    JDbNode dbAccount;

    public  UserProjectContext context = new UserProjectContext();

    boolean isPp() {
        return projectTypeId == projectTypeId_processing ||
                projectTypeId == projectTypeId_purchase;
    }

    int req_id, workplan_id;

    Map<String, String> map_talkid2token = new LinkedHashMap<>();
    Map<String, String> map_talkid2user = new LinkedHashMap<>();
    Map<String, String> map_user2token = new LinkedHashMap<>();

    public ProjectModel getPm() {
        return pm;
    }

    public void setPm(ProjectModel pm) {
        this.pm = pm;
    }

    //自动登录
    public UserProjectContext buildContext(int talkId, int userId) throws Exception {
        UserProjectContext context = new UserProjectContext().buildModel(talkId);
        String token = new UserLogin().login(userId);
        context.setLoginSso(SafeContext.getLog_ssoAndApiKey(token));
        context.buildModel(talkId);
        return context;
    }

    //已经登录
    public UserProjectContext buildContext(int talkId, String token) throws Exception {
        UserProjectContext context = new UserProjectContext().buildModel(talkId);
        context.setLoginSso(SafeContext.getLog_ssoAndApiKey(token));
        context.buildModel(talkId);
        return context;
    }



    public Map<String, String> getMap_talkid2user() {
        return map_talkid2user;
    }

    public void setMap_talkid2user(Map<String, String> map_talkid2user) {
        this.map_talkid2user = map_talkid2user;
    }

    public Map<String, String> getMap_user2token() {
        return map_user2token;
    }

    public void setMap_user2token(Map<String, String> map_user2token) {
        this.map_user2token = map_user2token;
    }

    public Map<String, String> getMap_talkid2token() {
        return map_talkid2token;
    }

    public UserProjectContext buildModel(String talkId) {
        return context.buildModel(Integer.parseInt(talkId));
    }

    public UserProjectContext getContext() {
        return context;
    }

    public void setContext(UserProjectContext context) {
        this.context = context;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getReq_id() {
        return req_id;
    }

    public void setReq_id(int req_id) {
        this.req_id = req_id;
    }

    public int getWorkplan_id() {
        return workplan_id;
    }

    public void setWorkplan_id(int workplan_id) {
        this.workplan_id = workplan_id;
    }


    //ListPriceAllModelServiceImpl
    public void setupCostProcess(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        BigDecimal cost = new BigDecimal(0).add(TestProjectConst.projectTotalFee_Phase1);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase2);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase3);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase4);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase5);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase6);
        //感谢金
        // cost = cost.add(BigDecimal.valueOf(300));

        ProcessListPriceAllModel allModel = new ProcessListPriceAllModel();
        allModel.setProjectId(pm.getProjectId());
        allModel.setTalkId(ptm.getTalkId());
        allModel.setDocumentId(ptm.getWorkplanId());
        allModel.setAllMoneyDesc("test prcoessing cost");
        allModel.setAllMoneyValue(cost);
        IProcessListPriceAllModelService modelService = new ProcessListPriceAllModelServiceImpl();
        modelService.delete(pm.getProjectId(),ptm.getWorkplanId());
        modelService.insert(allModel);

    }
    public void setupCostBuy(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        BigDecimal cost = new BigDecimal(0).add(TestProjectConst.projectTotalFee_Phase1);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase2);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase3);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase4);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase5);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase6);
        // cost = cost.add(BigDecimal.valueOf(300));
        //感谢金
        BuyPriceListAllModel allModel = new BuyPriceListAllModel();
        allModel.setProjectId(pm.getProjectId());
        allModel.setTalkId(ptm.getTalkId());
        allModel.setDocumentId(ptm.getWorkplanId());
        allModel.setAllMoneyDesc("test prcoessing cost");
        allModel.setAllMoneyValue(cost);
        IBuyPriceListAllModelService modelService = new BuyPriceListAllModelServiceImpl();
        modelService.delete(pm.getProjectId(), ptm.getWorkplanId());
        modelService.insert(allModel);

    }

    public  void setupCost(UserProjectContext context) {
        //乙方
        setupCost(context, context.getProjectTalkModel().getUserId2());
        //组员
        for(String userId:pmFriends.split(",")) {
            setupCost(context, Integer.parseInt(userId));
        }

    }

    void setupCost(UserProjectContext context, int userId) {
        ProjectModel pm=context.getProjectModel();
        ProjectTalkModel ptm=context.getProjectTalkModel();

        CostModel cm = new CostModel();
        cm.setUserId(userId);

        cm.setProjectId(pm.getProjectId());
        cm.setTalkId(ptm.getTalkId());
        cm.setDocumentId(ptm .getWorkplanId());


        cm.setCostPhase1(TestProjectConst.projectTotalFee_Phase1);
        cm.setCostPhase2(TestProjectConst.projectTotalFee_Phase2);
        cm.setCostPhase3(TestProjectConst.projectTotalFee_Phase3);
        cm.setCostPhase4(TestProjectConst.projectTotalFee_Phase4);
        cm.setCostPhase5(TestProjectConst.projectTotalFee_Phase5);
        cm.setCostPhase6(TestProjectConst.projectTotalFee_Phase6);

        BigDecimal cost = new BigDecimal(0).add(TestProjectConst.projectTotalFee_Phase1);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase2);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase3);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase4);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase5);
        cost = cost.add(TestProjectConst.projectTotalFee_Phase6);

        cm.setCostSum( cost );
        ProjectRateModel rateModel = context.selectProjectRateTaxModel();
        //cm.setServiceSum( cost.multiply(new BigDecimal(rateModel.getFeeRate())));
        BigDecimal income = new BigDecimal(cost.floatValue());
        cm.setIncomeSum(income);
        BigDecimal tax  = income.multiply(new BigDecimal(rateModel.getTaxRate()));
        //cm.setTaxSum( tax );

        cm.log();
        costModelService.insert(cm);

    }


    public String postProject( HttpClientNode httpclient, MsgRequest req  )
    {
        String ret = httpclient.post(url_projectCenter, req.toString(), "application/json");
        System.out.println(req.toJSONString());
        System.err.println("payProject:\r\n"+ret);
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        return ret;
    }

    public MsgResponse postProject( HttpClientNode httpclient, MsgRequest req,int expRetcode )
    {
        String ret = httpclient.post(url_projectCenter, req.toString(), "application/json");
        System.out.println(req.toJSONString());
        System.err.println("payProject:\r\n"+ret);
        httpclient.checkStatusCode(200);
        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(expRetcode,resp.getRetcode());
        return resp;
    }
    public String postProject(MsgRequest req) {
        String ret = httpclient.post(url_projectCenter, req.toString(), "application/json");
        System.out.println(req.toJSONString());
        System.err.println("payProject:\r\n" + ret);
        httpclient.checkStatusCode(200);
        return ret;
    }

    public Flow findPaReqWorkplan(int projectId) {
        this.projectId=projectId;
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select folder_id from ytb_project.project ");
        sql.append(" where project_id=").append(projectId);
        Integer fid = YtbSql.selectOne(sql, Integer.class);
        sql.delete(0, sql.length());
        sql.append(" select document_id from ytb_project.project_template "); //doc_type
        sql.append(" where folder_id=").append(fid).append(" order by doc_type ");
        List<Integer> lst = YtbSql.selectList(sql, Integer.class);
        req_id = lst.get(0);
        workplan_id = lst.get(1);
        System.out.println(this);
        return this;
    }


    public Flow findPbReqWorkplan( int talkId ) {
        this.talkId = talkId;
        UserProjectContext context = new UserProjectContext().buildModel(talkId);
        this.projectId=context.getProjectModel().getProjectId();

        StringBuilder sql = new StringBuilder(256);
        sql.append(" select * from ytb_project.project_template  ");
        sql.append(" where folder_id=").append(context.getProjectTalkModel().getWorkplanId());
        sql.append(" order by doc_type ");
        List<ProjectTemplateModel> models = YtbSql.selectList(sql, ProjectTemplateModel.class);
        for (ProjectTemplateModel model : models) {
            if (model.isTemplateReq()) {
                req_id = model.getDocumentId();
            }
            if (model.isTemplateWorkplan()) {
                workplan_id = model.getDocumentId();
            }
        }

        System.out.println(this);
        return this;
    }

    public Flow findPbReqWorkplan(int userId, int projectId) {
        this.projectId = projectId;
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select folder_id from ytb_project.project_folder ");
        sql.append(" where project_id=").append(projectId);
        sql.append(" and phase=").append(200);
        sql.append(" and user_id=").append(userId);
        Integer folderId = YtbSql.selectOne(sql, Integer.class);

        sql.delete(0, sql.length());
        sql.append(" select * from ytb_project.project_template  ");
        sql.append(" where folder_id=").append(folderId).append(" order by doc_type ");
        List<ProjectTemplateModel> lst = YtbSql.selectList(sql, ProjectTemplateModel.class);
        for(ProjectTemplateModel templateModel: lst){
            if(templateModel.isTemplateReq()){
                req_id = templateModel.getDocumentId();
            } if(templateModel.isTemplateWorkplan()){
                workplan_id = templateModel.getDocumentId();
            }
        }

        System.out.println(this);
        return this;
    }

    public ProjectModel publishFirst(MsgRequest req) throws Exception {
        ProjectModel pm =  publishFirstOnly(req, "");
        return  publishNext(req,pm);
    }

    //publish and next
    public ProjectModel publishProcessing(MsgRequest req, String friends) throws Exception {
        this.projectTypeId = this.projectTypeId_processing;
        return publishFirst(req, friends);

    }
    //publish and next
    public ProjectModel publishPurchase(MsgRequest req, String friends) throws Exception {
        this.projectTypeId = this.projectTypeId_purchase;
        return publishFirst(req, friends);

    }

    public List<Integer> payFirstProcessing(MsgRequest req, String tokenPa) throws Exception {
        this.projectTypeId = this.projectTypeId_processing;
        return payFirst(req, tokenPa);
    }

    public List<Integer> payFirstPurchase(MsgRequest req, String tokenPa) throws Exception {
        this.projectTypeId = this.projectTypeId_purchase;
        return payFirst(req, tokenPa);
    }


    //publish and next
    public ProjectModel publishFirst(MsgRequest req, String friends) throws Exception {

        ProjectModel pm = publishFirstOnly(req, friends);

        return publishNext(req, pm);

    }

    ProjectModel  publishNext(MsgRequest req, ProjectModel pm) throws Exception {
        pm = publishFirstOnly(req, friends);

        req.cmdtype = "projectRelease";
        req.cmd = "releaseProjectNext";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", ProjectConstState.PUBLISH_PASS);
        req.msgBody.fluentPut("projectId", pm.getProjectId());
        req.msgBody.fluentPut("templateId", req_id);


        String ret = postProject(httpclient, req);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        return pm;
    }

    ProjectModel publishFirstOnly(MsgRequest req, String friends) throws Exception {
        initPmBalanceNoneZero();

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectRelease";
        req.cmd = "releaseProject";
        req.msgBody.put("projectName",projectName);
        req.msgBody.put("projectTypeId", projectTypeId);
        req.msgBody.put("isPublish", true);
        req.msgBody.put("isInvite", true);
        req.msgBody.put("friends", friends);

        String ret = this.httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        this.httpclient.checkStatusCode(200);
        CheckResp c = new CheckResp(ret);
        c.checkRetcode(0);
        System.out.println(ret);

        System.out.println("req: " + req.toJSONString());
        System.out.println(url_projectCenter);
        String sm =c.getResp().msgBody.getJSONObject("projectModel").toJSONString();
        System.out.println(c.getResp().toJSONString());
        ProjectModel m = JSONObject.parseObject(sm, ProjectModel.class);
        req_id = c.getResp().msgBody.getInteger("reqTemplateId");
        workplan_id = c.getResp().msgBody.getInteger("workplanTemplateId");
        System.out.println("resp: " + m);
        dbProject.clearSql().appendSql("select 1 from project where project_id=" + m.getProjectId());
        dbProject.checkRecordNumber(1);
        if (!friends.isEmpty()) {
            Arrays.stream(friends.split(",")).forEach(System.out::println);
            for (String f : friends.split(",")) {
                dbProject.clearSql().appendSql("select talk_id  from project_invite where user_id2=");
                dbProject.appendSql(f).appendSql(" and project_id=").appendSql(m.getProjectId());
                dbProject.execSelect().checkRecordNumber(1);
                dbProject.select().forEach(System.out::println);
            }
        }
        return m;
    }

    //甲方审核通过
    public void pAAudit(MsgRequest req,String paToken,Map<String, String> map_takid2token) throws Exception {


        req.cmd="verifyProjectDoc";
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(paToken);
            req.msgBody.put("talkId", talkId);
            req.msgBody.put("status", ProjectConst.FOLDER_STATUS_PASS_PB);
            String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            httpclient.checkStatusCode(200);
            System.out.println(ret);
            dbProject.clearSql().appendSql("select *  from project_talk where talk_id=");
            dbProject.appendSql(talkId);
            dbProject.checkRecordNumber(1).execSelect().checkRecord("phase=200|phase_status=300");
            dbProject.clearSql().appendSql("select talk_id from project_talk  where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("talk_id="+talkId);

        }
        System.out.println("ytb.check PA确认洽谈OK！");
    }

    //乙方提交洽谈审核
    public void pBSubmit(MsgRequest req,int projectId, Map<String, String> map_takid2token) throws Exception {
        dbProject.clearSql().appendSql("select 1 from work_group where project_id=" + projectId);
        dbProject.checkRecordNumber(map_takid2token.keySet().size());
        req.cmdtype = "projectFlow";
        req.cmd = "pbSubmitTalkDoc";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("projectId", projectId);
        for (String talkId : map_takid2token.keySet()) {
            req.msgBody.put("talkId", talkId);
            req.setToken(map_takid2token.get(talkId));
            String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
            httpclient.checkStatusCode(200);
            //System.out.println(ret);
            dbProject.clearSql().appendSql("select * from project_talk where talk_id=" + talkId);
            dbProject.checkRecordNumber(1).execSelect().checkRecord("phase_status=200");

        }
        System.out.println("ytb.check PB提交洽谈OK！");

    }

    public void updateBalance(int userId, float balance) throws Exception {
        dbAccount.clearSql().appendSql("update account_user_inner  ");
        dbAccount.appendSql(" set balance =").appendSql(balance);
        dbAccount.appendSql(", payout_agent=0, income_agent= 0");
        dbAccount.appendSql(" where user_id=").appendSql(userId);
        dbAccount.execSql();
    }

    public void updateBalanceAgent(int userId, float balance,float payoutAgent,float incomeAgent) throws Exception {
        dbAccount.clearSql().appendSql("update account_user_inner  ");
        dbAccount.appendSql(" set balance =").appendSql(balance);
        dbAccount.appendSql(" , payout_agent =").appendSql(payoutAgent);
        dbAccount.appendSql(" , income_agent =").appendSql(incomeAgent);
        dbAccount.appendSql(" where user_id=").appendSql(userId);
        YtbLog.logJtest("updateBalanceAgent",dbAccount.getSql());
        dbAccount.execSql();
    }

    void initPmBalanceNoneZero(int userId) throws Exception {
        updateBalance(userId,20000);
    }

    public void initPmBalanceNoneZero() throws Exception {
        initPmBalanceNoneZero(paId);
        //组员
        for (String userId : pmFriends.split(",")) {
            initPmBalanceNoneZero(Integer.parseInt(userId));
        }
        for (String userId : friends.split(",")) {
            initPmBalanceNoneZero(Integer.parseInt(userId));
        }


    }
    public  void preSetPaBalance(int paBalance) throws Exception {
        dbAccount.clearSql().appendSql("update account_user_inner  ");
        dbAccount.appendSql(" set balance= ").appendSql(paBalance);
        dbAccount.appendSql(", balance_agent=0");
        dbAccount.appendSql(" where user_id=").appendSql(paId);
        dbAccount.execSql();
    }

    public void addGroupMember(UserProjectContext context, int pmUserId) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<WorkGroupMemberModel> lst = workGroupMemberModelService.selectList(ptm.getGroupId(), pmUserId);
        if (lst.size() == 0) {
            WorkGroupMemberModel memberModel = new WorkGroupMemberModel();
            memberModel.setUserId(pmUserId);
            memberModel.setProjectId(ptm.getProjectId());
            memberModel.setTalkId(ptm.getTalkId());
            memberModel.setGroupId(ptm.getGroupId());
            memberModel.setDocumentId(ptm.getWorkplanId());
            memberModel.setIsAdmin(WorkGroupMemberModel.IsAdmin_TYPE_PM);
            memberModel.setIsActive(1);
            memberModel.setCreateTime(new Date());

            workGroupMemberModelService.insert(memberModel);
        }

    }


    public void deleteProjectTrade(int talkId, int phase, Byte serviceType) throws Exception {

        dbProject.clearSql().appendSql(" delete  from  project_trade ");
        dbProject.appendSql(" where talk_id = ").appendSql(talkId);
        dbProject.appendSql(" and phase = ").appendSql(phase);
        dbProject.appendSql(" and service_type = ").appendSql(serviceType);
        YtbLog.logJtest("deleteProjectTrade sql",dbProject.getSql());
        dbProject.execSql();
    }

    public void deleteAccountTradeProject(int talkId, int phase ) throws Exception {

        dbProject.clearSql().appendSql(" delete  from  ytb_account.account_trade_project ");
        dbProject.appendSql(" where talk_id = ").appendSql(talkId);
        dbProject.appendSql(" and phase = ").appendSql(phase);
        YtbLog.logJtest("deleteAccountTradeProject sql",dbProject.getSql());
        dbProject.execSql();
    }

    public void deleteProjectTrade(int talkId, int phase) throws Exception {
        deleteProjectTrade(talkId, phase, TradeConst.SERVICE_TYPE_project);
    }

    public void deleteProjectTradeStop(int talkId, int phase) throws Exception {
        deleteProjectTrade(talkId, phase, TradeConst.SERVICE_TYPE_project_STOP);
    }

    public void deleteProjectTradeStop(int talkId) throws Exception {
        new VProjectTradeAccountModelServiceImpl().delete(talkId, TradeConst.SERVICE_TYPE_project_STOP);
    }

    public void deleteProjectTradeChangeStop(int talkId) throws Exception {
        new VProjectTradeAccountModelServiceImpl().delete(talkId, TradeConst.SERVICE_TYPE_project_STOP);
        new VProjectTradeAccountModelServiceImpl().delete(talkId, TradeConst.SERVICE_TYPE_project_CHANGE);
    }

    public void deleteProjectTradeChange(int talkId) throws Exception {
        new VProjectTradeAccountModelServiceImpl().delete(talkId, TradeConst.SERVICE_TYPE_project_CHANGE);
    }

    public void deleteProjectTradeChange(int talkId, int phase) throws Exception {
        deleteProjectTrade(talkId, phase, TradeConst.SERVICE_TYPE_project_CHANGE);
    }




    //协助
    public void checkAssistServiceTypeTradeRecordNumber(int talk_id,int templateId,int exp) throws ClassNotFoundException,
            SQLException,
            InstantiationException, IllegalAccessException {
        List<String> talks = new ArrayList<>();
        talks.addAll( getMap_talkid2token().keySet());
        dbProject.clearSql().appendSql(" select 1 from project_trade ");
        dbProject.appendSql(" where service_type = 3  ");
        dbProject.appendSql(" and template_id = ").appendSql(templateId);
        dbProject.appendSql(" and talk_id = ").appendSql(talk_id);
        dbProject.checkRecordNumber(exp);
        System.out.println("项目全过程共产生 "+exp+" 次支付记录！ ");
    }

    //感谢金
    public void checkTalkServiceTypeTradeRecordNumber(int talk_id,int exp) throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        List<String> talks = new ArrayList<>();
        talks.addAll( getMap_talkid2token().keySet());
        dbProject.clearSql().appendSql(" select 1 from project_trade ");
        dbProject.appendSql(" where service_type = 4  ");
        dbProject.appendSql(" and talk_id=").appendSql( talk_id);
        dbProject.checkRecordNumber(exp);
        System.out.println("项目全过程共产生 "+exp+" 次支付记录！ ");
    }

    public void checkProjectServiceTypeTradeRecordNumber(int exp) throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        List<String> talks = new ArrayList<>();
        talks.addAll( getMap_talkid2token().keySet());
        dbProject.clearSql().appendSql(" select 1 from project_trade ");
        dbProject.appendSql(" where service_type = 1  ");
        dbProject.appendSql(" and talk_id=").appendSql(talks.get(0));
        dbProject.checkRecordNumber(exp);
        System.out.println("项目全过程共产生 "+exp+" 次支付记录！ ");
    }


    //return phase   talkid2token
    public List<Integer> payFirst(MsgRequest req, String tokenPa) throws Exception {

        pm = publishFirst(req, friends);

        dbProject.clearSql().appendSql("select status from project where project_id=" + pm.getProjectId());
        dbProject.checkRecordNumber(1);
        dbProject.execSelect().checkRecord("status=4");
        Arrays.stream(friends.split(",")).forEach(System.out::println);
        map_user2token.clear();
        map_talkid2token.clear();
        map_talkid2user.clear();
        for (String f : friends.split(",")) {
            dbProject.clearSql().appendSql("select talk_id from project_invite where user_id2=");
            dbProject.appendSql(f).appendSql(" and project_id=").appendSql(pm.getProjectId());
            dbProject.execSelect().checkRecordNumber(1);
            List<Map<String, Object>> r = dbProject.select();
            String userToken = new UserLogin().login(Integer.parseInt(f));
            map_talkid2token.put(r.get(0).get("talk_id").toString(), userToken);
            map_user2token.put( f, userToken);
            map_talkid2user.put(r.get(0).get("talk_id").toString(),f);
        }

        //confirm 审核邀请申请
        req.cmd = "approvalPrjApply";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("type", 0);
        req.msgBody.put("projectId", pm.getProjectId());
        req.msgBody.put("eventType", 2);

        for (String talkId : map_talkid2token.keySet()) {
            req.setToken(map_talkid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            String ret = postProject(httpclient,req) ;
            dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase=200");

        }

        for (String pbId : map_user2token.keySet()) {
            req.setToken(map_user2token.get(pbId));
            findPbReqWorkplan(Integer.parseInt(pbId), pm.getProjectId());
            req.msgBody.put("testFlag",true);
            tagTable.exportAllTables(httpclient, req, this);

        }
        //cost导出有问题，测试自己预置数据
        for (String talkId : map_talkid2token.keySet()) {

            UserProjectContext context = new UserProjectContext();
            context.buildModel(Integer.parseInt(talkId));
            if (projectTypeId == projectTypeId_processing) {
                setupCostProcess(context);
            } else if (projectTypeId == projectTypeId_purchase) {
                setupCostBuy(context);
            }  else
            {
                setupCost(context);
            }
        }
        //PB提交需求计划审核
        pBSubmit(req, pm.getProjectId(), map_talkid2token);
        //甲方审核洽谈通过
        pAAudit(req, tokenPa, map_talkid2token);

        //payProject
        req.cmdtype = "projectFlow";
        req.cmd="payProject";
        dbAccount.clearSql().appendSql(" update account_user_inner  " );
        dbAccount.appendSql(" set balance=  ").appendSql(paBalance);
        dbAccount.appendSql(" where user_id=").appendSql(paId).execSql();


        List<Integer> resultLst=new ArrayList<>();
        int i = 0;//只有一个支付成功

        for (String talkId : map_talkid2token.keySet()) {
            //req.setToken(map_takid2token.get(talkId));
            req.setToken(tokenPa);
            req.msgBody.put("talkId", talkId);
            req.msgBody.put("fee", "3333");
            req.msgBody.put("password", UserLogin.payPasswodEn);
            postProject(httpclient, req, i == 0 ? 0 : YtbError.getErrorId(YtbError.CODE_USER_ERROR));

            dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + talkId);
            int phase = i == 0 ? pm.getPhaseStart() : ProjectConst.TalkPhase;
            dbProject.execSelect().checkRecord("phase=" + phase);
            context = buildModel(talkId);
            i++;
            if (!isPp()) {
                addGroupMember(context, pmId);
            }
            resultLst.add(context.getProjectTalkModel().getPhase());
            System.out.println("ytb.check  payFirst！"+ context.getProjectTalkModel().getPhase());
            //context .getProjectTalkModel().getPhase();
        }

        dbAccount.clearSql().appendSql("select * from  account_user_inner  ");

        dbAccount.appendSql(" where user_id=").appendSql(paId);

        if (projectTypeId == projectTypeId_purchase) {
            dbAccount.execSelect().checkRecord("balance=99485.00|payout_agent=815.00");

        } else if (projectTypeId == projectTypeId_processing) {
            dbAccount.execSelect().checkRecord("balance=99499.00|payout_agent=801.00");

        } else {
            dbAccount.execSelect().checkRecord("balance=98885.00|payout_agent=1415.00");
        }


        return resultLst;
    }


    //PB阶段提交
    public void pbPhaseSubmit(MsgRequest req){
        req.cmdtype = "projectFlow";
        req.cmd = "principalSubmit";
        req.msgBody = JSONObject.parseObject("{}");
        int i=0;
        for (String talkId :  map_talkid2token.keySet()) {
            if(i>0){
                break;
            }
            req.setToken(map_talkid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            String ret =  postProject(httpclient, req);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            checkEQ(0, resp.getRetcode());
            i++;
        }
        System.out.println("pb负责人提交");
    }

    //PA阶段审核
    public void paPhaseAudit(MsgRequest req) {
        req.cmdtype = "projectFlow";
        req.cmd = "PartyAVerify";
        req.msgBody = JSONObject.parseObject("{}");
        int i=0;
        for (String talkId : map_talkid2token.keySet()) {
            if(i>0){
               break;
            }
            req.msgBody.put("talkId", talkId);
            req.msgBody.put("status", 3);
            req.msgBody.put("passWord", payPasswodEn);
            String ret =  postProject(httpclient, req);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            checkEQ(0, resp.getRetcode());
            UserProjectContext context =   buildModel(talkId);
            System.out.println(context.getProjectTalkModel());
            i++;
        }
        System.out.println("PA审核");
    }
        //return phase
    public int pay2Next(String tokenPa, Map<String, String> map_takid2token, int newPhase) throws Exception {

        MsgRequest req = new MsgRequest();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();

        req.cmdtype = "projectFlow";
        req.cmd = "principalSubmit";
        req.msgBody = JSONObject.parseObject("{}");
        for (String talkId : map_takid2token.keySet()) {
            req.setToken(map_takid2token.get(talkId));
            req.msgBody.put("talkId", talkId);
            String ret = postProject(httpclient, req);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            checkEQ(0, resp.getRetcode());
            break;//只有一个审核通过的
        }

        System.out.println("pb负责人提交");
        req.token = tokenPa;
        req.cmdtype = "projectFlow";
        req.cmd = "PartyAVerify";
        req.msgBody = JSONObject.parseObject("{}");
        for (String talkId : map_takid2token.keySet()) {

            req.msgBody.put("talkId", talkId);
            req.msgBody.put("status", 3);
            req.msgBody.put("passWord", payPasswodEn);
            String ret = postProject(req);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            checkEQ(0, resp.getRetcode());
            UserProjectContext context =buildModel(talkId);
            System.out.println(context.getProjectTalkModel());
            break;
        }
        System.out.println("PA审核");

        req.token = tokenPa;
        req.cmdtype = "projectFlow";
        req.cmd = "payPhase";
        for (String talkId : map_takid2token.keySet()) {
            UserProjectContext context = buildModel(talkId);
            boolean isLast = context.isLastStage(context.getProjectTalkModel().getPhase());
            int expPhase = isLast ? context.getProjectModel().getPhaseEnd(): newPhase;

            req.msgBody.put("talkId", talkId);
            req.msgBody.put("passWord", UserLogin.payPasswodEn);
            System.out.println(req);
            String ret = postProject(httpclient, req);
            MsgResponse resp = MsgResponse.parseResponse(ret);
            checkEQ(0, resp.getRetcode());
            context = buildModel(talkId);

            dbProject.clearSql().appendSql("select phase from project_talk where talk_id=" + talkId);
            dbProject.execSelect().checkRecord("phase=" + expPhase);
            break;
        }

        System.out.println("PA阶段支付payPhase "+context.getProjectTalkModel().getPhase());
        return context.getProjectTalkModel().getPhase();

    }


}
