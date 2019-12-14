package ytb.project.model.projectFolderView.projectTemplate;

import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.service.IProjectFileService;
import ytb.project.service.template.VersionRule;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/*
*
* author: leijming
* date: 2019/3/8
*
* */
public class PBFolderCopyTemplateModel extends CopyTemplateModel {

    //拷贝甲方文件夹下的需求说明书与工作计划书模板
    public ProjectContext copyPaTemplate(UserProjectContext context,int folderId) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectContext pc = new ProjectContext(context);
        pc.setProjectTypeId(pm.getProjectTypeId());
        pc.setFolderId(folderId);
        pc.setPhase(ProjectConst.TalkPhase);
        pc.setPhaseStatus(0);
        List<ProjectTemplateModel> pbModels = new ArrayList<>();
        List<ProjectTemplateModel> paModels = getTemplateListByFolder(pm.getFolderId());
        for (ProjectTemplateModel paModel : paModels) {
            //获取源文件

            CopyTemplateInfo copyInfo = new CopyTemplateInfo();
            copyInfo.setFolderId(folderId);

            copyInfo.setDocSeq(""+ ProjectConstState.TEMPLATE_STAGE_TALK);
            copyInfo.getTitleInfo().setProjectName(pm.getProjectName());
            copyInfo.getTitleInfo().setStatus(VersionRule.COPY_TITLE_SAME);
            copyInfo.getTitleInfo().setTaskName("");
            copyInfo.setDocVer("1.0");

            copyInfo.setAuthor(ptm.getUserId2());
            copyInfo.setAuditor(pm.getUserId1());

            ProjectTemplateModel pbModel = copyTemplate(copyInfo, paModel);
            pbModels.add(pbModel);
            pc.setDocumentId(pbModel.getDocumentId());
            pc.setProjectTemplateId(pbModel.getTemplateId());
            pc.modifyHeader(context);
        }
        pc.setUpReqWorkplan(pbModels);
        pc.checkReqWorkplan();
        ptm.setWorkplanId(pc.getWorkplanId());
        ptm.modifyWorkplanId();
        return pc;
    }


    //阶段拷贝文件夹 200 or 601 copy原文件夹下文档到新阶段家
    public void copyPhaseTemplates(UserProjectContext context, int preFolderId, int newPhase, int newFolderId) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        IProjectFileService fileService = context.getProjectFileService();

        int phase = newPhase - pm.getPhaseStart( )+1;

        List<ProjectTemplateModel> models = fileService.getTemplateListByFolder(preFolderId);
        for (ProjectTemplateModel templateModel : models) {

            CopyTemplateInfo copyInfo = new CopyTemplateInfo();
            copyInfo.setFolderId(newFolderId);
            copyInfo.setDocSeq(templateModel.getDocSeq());

            copyInfo.getTitleInfo().setProjectName(pm.getProjectName());
            copyInfo.getTitleInfo().setStatus((byte) VersionRule.COPY_TITLE_SAME);
            copyInfo.getTitleInfo().setTaskName("");
            String[] s = templateModel.getDocVer().split("\\.");
            String docVer = s[0] + "." + String.valueOf(phase) + ".0.0";
            copyInfo.setDocVer(docVer);

            copyInfo.setAuthor(templateModel.getAuthor());
            copyInfo.setAuditor(pm.getUserId1());

            ProjectTemplateModel model = copyTemplate(copyInfo, templateModel);
            projectTemplateUserControl .addProjectTemplateUserPhase(context, model.getAuthor(), model.getTemplateId()
                    , model.getDocType());
            //阶段copy只需要改documentId
            ProjectContext pc = new ProjectContext(context);
            pc.setProjectId(pm.getProjectId());
            pc.setDocumentId(model.getDocumentId());
            pc.modifyHeader(context,model.getDocumentId());

        }


    }


    //拷贝文档或文件夹 >601
    public void copyPhaseTemplates(UserProjectContext context, ProjectContext pc,
                                   ProjectFolderModel folderPrev,
                                   int newPhase, int newFolderID) throws UnsupportedEncodingException {


        ProjectModel pm = context.getProjectModel();
        IProjectFileService projectFileService= context.getProjectFileService();

        List<ProjectTemplateModel> models = projectFileService.getTemplateListByFolder(folderPrev.getFolderId());
        for (ProjectTemplateModel templateModel : models) {

            CopyTemplateInfo copyInfo = new CopyTemplateInfo();
            copyInfo.setFolderId(newFolderID);

            copyInfo.setDocSeq(templateModel.getDocSeq());
            copyInfo.getTitleInfo().setProjectName(pm.getProjectName());
            copyInfo.getTitleInfo().setStatus((byte)VersionRule.COPY_TITLE_SAME);
            copyInfo.getTitleInfo().setTaskName("");

            String[] s = templateModel.getDocVer().split("\\.");
            String docVer = s[0] + "." +  String.valueOf(newPhase - pm.getPhaseStart()+1) + ".0.0";
            copyInfo.setDocVer(docVer);

            copyInfo.setAuthor(templateModel.getAuthor());
            copyInfo.setAuditor(pm.getUserId1());

            ProjectTemplateModel model = copyTemplate(copyInfo, templateModel);
            //添加文档状态控制
            projectTemplateUserControl .addProjectTemplateUserPM(context, model.getAuthor(),
                    model.getTemplateId(), model.getDocType());

            pc.setProjectId(folderPrev.getProjectId());
            pc.setDocumentId(model.getDocumentId());
            pc.modifyHeader(context);
        }
    }

}
