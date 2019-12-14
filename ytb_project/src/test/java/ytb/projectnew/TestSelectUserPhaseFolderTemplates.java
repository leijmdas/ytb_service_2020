package ytb.projectnew;

import ch.qos.logback.classic.Level;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.Flow;
import ytb.common.MngrCheckTemplate;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.TestProjectConst;
import ytb.common.UserLogin;
import ytb.common.ytblog.YtbLog;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.impl.pay.projectpay.FlowPayStop;
import ytb.project.service.project.stop.impl.StopService;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;

import java.util.List;


/**
 * 1 按代码规范
 * 2 不要有重复代码
 * 3 函数不能太长
 * 4 不能有if for else 好多{}嵌套
 * 5 尽量封装一些小函数
 **/

public class TestSelectUserPhaseFolderTemplates extends ITestImpl {

    int paId = TestProjectConst.paId;
    UserProjectContext context = new UserProjectContext();

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    String url_projectCenter = TestProjectConst.url_projectCenter;
    Flow flow = new Flow();
    FlowPayStop payStop = new FlowPayStop();
    StopService stopService = new StopService();

    MsgRequest req = new MsgRequest();
    String token = " ";
    MngrCheckTemplate mngrCheckTemplate=new MngrCheckTemplate();

    public static ProjectSrvContext getInst() {

        return ProjectSrvContext.getInst();
    }

    @Inject("ytb_account")
    JDbNode dbAccount;
    @Inject("ytb_manager")
    JDbNode dbManager;
    @Inject("ytb_project")
    JDbNode dbProject;

    @Override
    public void setUp() throws Exception {
        YtbLog.getRootLogger().setLevel(Level.DEBUG);
        token = new UserLogin().login(paId);        //token = new UserLogin().login(485);

        req.token = token;
        context = new Flow().buildContext(389, 202);
    }


    //  select * from vw_work_group_member_duty
    //  select task_name from project_member_task
    //  selectUserPhaseTemplates
    @JTest
    @JTestClass.title("test0001_selectUserPhaseFolders")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0001_selectUserPhaseFolders() throws Exception {
        context = new Flow().buildContext(1, 485);

        ProjectTalkModel ptm = context.getProjectTalkModel();
        ptm.setPhase(601);
        //List<WorkGroupMemberModel> memberModels = ptm.getWorkGroupMemberModelService().selectListPM(ptm.getGroupId());
        List<WorkGroupMemberModel> allModels = ptm.getWorkGroupMemberModelService().selectListAll(ptm.getGroupId());
        for (WorkGroupMemberModel memberModel : allModels) {
            YtbLog.logJtest(memberModel);
        }
        List<ProjectFolderModel> folderModels = ptm.selectUserPhaseFolders(1302);//1303
        System.out.println(folderModels.size());
        for(ProjectFolderModel folderModel:folderModels){
            System.out.println(folderModel.getFolderId());
        }
        System.out.println(folderModels.size());


    }


    //  select * from vw_work_group_member_duty
    //  select task_name from project_member_task
    //  selectUserPhaseTemplates
    @JTest
    @JTestClass.title("test0002_selectUserPhaseTemplates")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0002_selectUserPhaseTemplates() throws Exception {
        context = new Flow().buildContext(1, 485);

        ProjectTalkModel ptm = context.getProjectTalkModel();
        ptm.setPhase(601);
        //List<WorkGroupMemberModel> memberModels = ptm.getWorkGroupMemberModelService().selectListPM(ptm.getGroupId());
        List<WorkGroupMemberModel> allModels = ptm.getWorkGroupMemberModelService().selectListAll(ptm.getGroupId());
        for (WorkGroupMemberModel memberModel : allModels) {
            YtbLog.logJtest(memberModel);
        }
        List<ProjectFolderModel> folderModels = ptm.selectUserPhaseFolders(1302);
        System.out.println(folderModels.size());

        List<ProjectTemplateModel> models = ptm.selectUserPhaseTemplates(1302,300); //1303
        for (ProjectTemplateModel model : models) {
            System.out.println(model);
        }
        System.out.println(models.size());

    }

    @JTest
    @JTestClass.title("test0003_checkTemplateHeader")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0003_checkTemplateHeaderPAPB() throws Exception {
        context = new Flow().buildContext(493, 1300);

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ptm.setPhase(601);

        List<ProjectTemplateModel> modelsPa  = pm.selectPublishTemplates(); //1303
        List<ProjectTemplateModel> modelsPb = ptm.selectTalkTemplates(); //1303
        for (ProjectTemplateModel model : modelsPb  ) {
            System.out.println(model);
            TemplateDocumentInfo documentInfo = mngrCheckTemplate.parseTemplate(ptm.getProjectId(),
                    model.getDocumentId());
            mngrCheckTemplate.logHeader();
            mngrCheckTemplate.checkHeader("projectUserType","PB");
            break;
        }
        System.out.println(modelsPb.size());
        // System.out.println(ptm.selectWorkGroupMember());
    }
    @JTest
    @JTestClass.title("test0003_checkTemplateHeader")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest")
    @JTestClass.exp("ok")
    public void test0004_checkTemplateHeaderPm() throws Exception {
        context = new Flow().buildContext(493, 1300);

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ptm.setPhase(601);

        List<ProjectTemplateModel> modelsPb = ptm.selectUserPhaseTemplates(193);
        for (ProjectTemplateModel model : modelsPb  ) {
            System.out.println(model);
            TemplateDocumentInfo documentInfo = mngrCheckTemplate.parseTemplate(ptm.getProjectId(),
                    model.getDocumentId());
            mngrCheckTemplate.logHeader();
            mngrCheckTemplate.checkHeader("projectUserType","PM");
            break;
        }
        System.out.println(modelsPb.size());
        // System.out.println(ptm.selectWorkGroupMember());
    }

    //查询测试报告 ，查询模板对应表
    public static void main(String args[]) {
        run(TestSelectUserPhaseFolderTemplates.class, 3);
        // System.out.println(new TagTableRaramResult());
    }
}
