package ytb.projectnew;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.*;
import ytb.common.utils.YtbUtils;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.projectFolderView.PhaseFolder;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.project.view.model.ProjectTaskViewResult;
import ytb.project.view.service.impl.ProjectTaskViewPhase;
import ytb.common.RestMessage.MsgRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestProjectTaskViewNew extends ITestImpl {

    String url_projectCenter = TestProjectConst.url_projectCenter;



    int paId = TestProjectConst.paId;
    int pbId = TestProjectConst.pbId;
    TagTable t = new TagTable();
    UserLogin userLoginPa = new UserLogin();
    UserLogin userLoginPb = new UserLogin();

    MsgRequest req = new MsgRequest();

    String tokenPa = "966b210c1b3243f795d997a62b2cddb7";
    String tokenPb = "966b210c1b3243f795d997a62b2cddb7";
    String token = "966b210c1b3243f795d997a62b2cddb7";
    UserProjectContext context = new UserProjectContext();

    public static ProjectSrvContext getInst() {
        return ProjectSrvContext.getInst();
    }

    @Inject("ytb_manager")
    JDbNode dbManager;

    @Inject("ytb_project")
    JDbNode dbProject;

    @Override
    public void setUp() throws Exception {

        tokenPa = userLoginPb.login(paId);
        tokenPb = userLoginPa.login(pbId);
        token = tokenPa;
        req.token = token;
    }

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;


    @JTest
    @JTestClass.title("  test0001_paExportAllTables  ")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/  ")
    @JTestClass.exp("ok")
    public void test0001_paExportAllTables() throws Exception {
        req.token = tokenPa;

        Flow f = new Flow().findPaReqWorkplan(465);
        t.exportAllTablesPa(httpclient, req, f);
        //String ret=tagTable.exportAllTablesPa(httpclient, req, 465,flow.getReq_id()); ;
        //ret=tagTable.exportAllTablesPa(httpclient, req, 465,flow.getWorkplan_id()); ;

    }

    @JTest
    @JTestClass.title("test0002_pbExportAllTables  ")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest  ")
    @JTestClass.exp("ok")
    public void test0002_pbExportAllTables() throws Exception {
        req.token = tokenPb;

        Flow f = new Flow().findPbReqWorkplan(pbId, 475);
         String re1t=t.exportAllTables(httpclient, req, f); ;
        //String ret=tagTable.exportAllTables(httpclient, req, 465,flow.getReq_id()); ;
        //ret=tagTable.exportAllTables(httpclient, req, 465,flow.getWorkplan_id()); ;
        //CheckResp c = new CheckResp(ret);
        //c.checkListSize(10);
        //System.out.println(userLoginPb.getLoginSso().getLoginSsoJson());

    }

    @JTest
    @JTestClass.title("test0003_ProjectTaskViewNewGetProjectTaskView")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com/rest/ / ")
    @JTestClass.exp("ok")
    public void test0003_ProjectTaskViewNewGetProjectTaskView() throws Exception {

        context = new Flow().buildContext(1746, 182);
        List<ProjectFolderModel> folderModels = context.getProjectFileService().getProjectPhaseFolders(
                context.getProjectModel().getProjectId(), 182, 601 );
        System.out.println(folderModels);

        List<ViewProjectTemplateUserModel> vieTemplateList = new ArrayList<>();
        //只有一个文件夹
      /*  for (ProjectFolderModel projectFolder : folderModels) {
            vieTemplateList = UserProjectContext.getInst().getFlowFolderView().getFolderTemplates(projectFolder,
                    vieTemplateList,
                    182, 182);
        }
        System.out.println(vieTemplateList);*/

    }


    @JTest
    @JTestClass.title(" test0004_viewTaskPMPhase viewTask")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com  ")
    @JTestClass.exp("ok")
    public void test0004_viewTaskPMPhase() throws Exception {
        int userIdPb = 1302;
        int phase = 601 ;
        context = new Flow().buildContext(1, userIdPb);

        ProjectTaskViewResult viewResult = new ProjectTaskViewPhase().getPbProjectTask(
                context, userIdPb, phase);
        System.out.println(viewResult);
    }

    @JTest
    @JTestClass.title(" test0005_getPhaseFolder viewTask")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com")
    @JTestClass.exp("ok")
    public void test0005_getPhaseFolder() throws Exception {
        int userIdPm = 202;
        int phase = 602;
        context = new Flow().buildContext(2921, pbId);

        List<ProjectFolderModel> models = new PhaseFolder().getPhaseFolderModels(context);
        // System.out.println(context.getProjectTalkModel());
        //System.out.println(YtbUtils.toJSONStringSkipNull(models));
    }


    @JTest
    @JTestClass.title(" test0005_getPhaseFolder viewTask")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com")
    @JTestClass.exp("ok")
    public void test0006_getPmPhaseFolder() throws Exception {
        int userIdPm = 182;//125 182
        int phase = 601;
        context = new Flow().buildContext(4, userIdPm);

        ProjectTaskViewResult result = new ProjectTaskViewPhase().getPmProjectTask(context, userIdPm, phase);
        System.out.println(context.getProjectTalkModel());
        for (ViewProjectTemplateUserModel model : result.getTemplateList()) {
            System.out.println(model.getFolderId() + ":" + model);
        }
        System.out.println(":" + result.getTemplateList().size());

    }

    @JTest
    @JTestClass.title(" test0005_getPhaseFolder viewTask")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com")
    @JTestClass.exp("ok")
    public void test0007_flowPayPhase() throws Exception {
        int userIdPb = 202;//125 182
        int phase = 601;
        context = new Flow().buildContext(5103, 202);

        //PayResult   result= new FlowPay().payPhase(context, 4667, ProjectConstState.TEMPORARY_PASSWORD);
        System.out.println(context.getProjectModel());


    }

    @JTest
    @JTestClass.title(" test0005_getPhaseFolder viewTask")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com")
    @JTestClass.exp("ok")
    public void test0008_rojectLittleChangeinsert() throws Exception {
        int userIdPb = 202;//125 182
        int phase = 601;
        context = new Flow().buildContext(1, 485);
        // new ChangeFlow().computeChange(context, context);new BigChange().computeChange(context,context);

        Map<String,Object> resultViewModel = ProjectSrvContext.getInst().getFlowFolderView().getProjectTab(context);
        System.out.println(YtbUtils.toJSONStringPretty(resultViewModel));
    }


    public static void main(String args[]) {
        run(TestProjectTaskViewNew.class, 4);

    }

}
