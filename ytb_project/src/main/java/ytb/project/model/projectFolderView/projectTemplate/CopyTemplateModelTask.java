package ytb.project.model.projectFolderView.projectTemplate;

import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.PhaseFolder;
import ytb.project.model.projectFolderView.VwWorkGroupMemberDutyModel;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_WorkJobModel;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

//首阶段岗位任务
public class CopyTemplateModelTask extends CopyTemplateModel {
    public List<ProjectTemplateModel> copyTemplate(UserProjectContext context,
                                                   ProjectContext pc,
                                                   ProjectFolderModel folderModel,
                                                   VwWorkGroupMemberDutyModel model) throws UnsupportedEncodingException {

        List<ProjectTemplateModel> result = new ArrayList<>();
        List<ProjectFolderModel> folderModels = new PhaseFolder().getPhaseFolderModels(context);

        Dict_WorkJobModel workJobModel = context.getProjectCacheManager().findDictWorkJobModel(model.getWorkJobId());
        List<Dict_TemplateModel> models = context.getProjectCacheManager().getTemplatesByWorkjob(model.getWorkJobId());
        List<ProjectMemberTask> memberTasks =
                ProjectTalkModel.getWorkGroup().getProjectMemberTasks(model.getMemberDutyId());
        for (ProjectMemberTask memberTask : memberTasks) {

            CopyTemplateInfo copyInfo = buildTemplateInfo(context, workJobModel, memberTask, folderModel,folderModels);

            List<ProjectTemplateModel> templateModels = buildTemplates(context, pc, copyInfo, models);

            result.addAll(templateModels);

        }
        return result;
    }

    CopyTemplateInfo buildTemplateInfo(UserProjectContext context,
                                       Dict_WorkJobModel workJobModel,
                                       ProjectMemberTask memberTask,
                                       ProjectFolderModel folderModel,
                                       List<ProjectFolderModel> folderModels) {
        ProjectModel pm = context.getProjectModel();
        ProjectFolderModel taskFolder = findTaskFolder(context, memberTask,folderModels);
        CopyTemplateInfo copyInfo = new CopyTemplateInfo();
        // templateInfo.setFolderId(folderModel.getFolderId());

        copyInfo.setFolderId(taskFolder.getFolderId());
        copyInfo.setDocSeq("" + ProjectConstState.TEMPLATE_STAGE_DESIGN);
        // copyInfo.getTitleInfo().setTaskName(memberTask.getTaskName() + "[" + workJobModel.getTitle() + "]");
        copyInfo.getTitleInfo().setTaskName(memberTask.getTaskName());

        copyInfo.getTitleInfo().setStatus((byte) 1);
        copyInfo.getTitleInfo().setProjectName(pm.getProjectName());
        copyInfo.setDocVer("1.1.0.0");
        copyInfo.setAuthor(folderModel.getUserId());
        copyInfo.setAuditor(folderModel.getAuditor());
        return copyInfo;
    }

    ProjectFolderModel findTaskFolder(UserProjectContext context,
                                      ProjectMemberTask memberTask,
                                      List<ProjectFolderModel> folderModels ) {
        //查询阶段文档，然后再查询任务对应的文件夹
        //List<ProjectFolderModel> folderModels = new PhaseFolder().getPhaseFolderModels(context);
        for (ProjectFolderModel folderModel : folderModels) {
            if (folderModel.getOwnerId() == memberTask.getmDutyTaskId()) {
                return folderModel;
            }
        }
        throw new YtbError(YtbError.CODE_DEFINE_ERROR, "任务文件夹不存在!");
    }

    List<ProjectTemplateModel> buildTemplates(UserProjectContext context, ProjectContext pc,
                                              CopyTemplateInfo copyTemplateInfo,
                                              List<Dict_TemplateModel> templateModels) throws UnsupportedEncodingException {
        List<ProjectTemplateModel> result = new ArrayList<>();

        ProjectTalkModel ptm = context.getProjectTalkModel();

        for (Dict_TemplateModel dtm : templateModels) {
            ProjectTemplateModel templateModel = copyTemplate(copyTemplateInfo, dtm);
            result.add(templateModel);
            //添加文档状态控制
            projectTemplateUserControl.addProjectTemplateUserPM(context,
                    copyTemplateInfo.getAuthor(),//文档的作者
                    templateModel.getTemplateId(), dtm.getDocType());
            pc.setUserId((long)templateModel.getAuthor());
            pc.setProjectId(context.getProjectModel().getProjectId());
            pc.setTalkId(ptm.getTalkId());
            pc.setPhase(ptm.getPhase());
            pc.setProjectTemplateId(templateModel.getTemplateId());
            pc.setDocumentId(templateModel.getDocumentId());
            pc.modifyHeader(context);

        }

        return result;
    }


//    public List<ProjectTemplateModel> copyTemplate(UserProjectContext context,
//                                                   ProjectContext pc,
//                                                   ProjectFolderModel folderModel,
//                                                   VwWorkGroupMemberDutyModel model) throws UnsupportedEncodingException {
//        ProjectModel pm = context.getProjectModel();
//        IProjectTalkModel ptm = context.getProjectTalkModel();
//        List<ProjectTemplateModel> models = new ArrayList<>();
//        Dict_WorkJobModel workJobModel = context.getProjectCacheManager().findDictWorkJobModel(model.getWorkJobId());
//
//        List<ProjectMemberTask> pmTaskList =
//                IProjectTalkModel.getWorkGroup().getProjectMemberTaskList(model.getMemberDutyId());
//        List<Dict_TemplateModel> templateList =
//                context.getProjectCacheManager().getTemplateListByWorkJobId(folderModel.getOwnerId());
//        for (ProjectMemberTask pmTask : pmTaskList) {
//            for (Dict_TemplateModel dict_tm : templateList) {
//
//                CopyTemplateInfo templateInfo = new CopyTemplateInfo();
//                templateInfo.setFolderId(folderModel.getFolderId());
//                templateInfo.setDocSeq(""+ ProjectConstState.TEMPLATE_STAGE_DESIGN);
//
//                templateInfo.getTitleInfo().setTaskName(pmTask.getTaskName()+"["+workJobModel.getTitle()+"]");
//                templateInfo.getTitleInfo().setStatus((byte)1);
//                templateInfo.getTitleInfo().setProjectName(pm.getProjectName());
//                templateInfo.setDocVer("1.1.0.0");
//
//                templateInfo.setAuthor(folderModel.getUserId());
//                templateInfo.setAuditor(folderModel.getUserId());
//
//                ProjectTemplateModel tm = copyTemplate(templateInfo, dict_tm);
//                models.add(tm);
//
//                //添加文档状态控制
//                projectTemplateUserControl.addProjectTemplateUserPM(
//                                                                context,
//                                                                folderModel.getUserId(),//文档的作者
//                                                                tm.getTemplateId(),//project_template表主键
//                                                                dict_tm.getDocType());
//                pc.setProjectId(context.getProjectModel().getProjectId());
//                pc.setTalkId(ptm.getTalkId());
//                pc.setProjectTemplateId(tm.getTemplateId());
//                pc.setDocumentId(tm.getDocumentId());
//                pc.modifyHeader(context);
//            }
//        }
//        return models;
//    }

}

