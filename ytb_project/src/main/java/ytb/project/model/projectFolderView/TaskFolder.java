package ytb.project.model.projectFolderView;

import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.ProjectModel;
import ytb.project.model.iface.IFolderModel;
import ytb.project.model.projectFolderView.projectTemplate.CopyTemplateModelTask;
import ytb.project.model.tagtable.ProjectMemberTask;
import java.io.UnsupportedEncodingException;

public class TaskFolder extends ProjectFolderBase implements IFolderModel {


    public int createFolder(UserProjectContext context, int phase, ProjectFolderModel parentFolder, int memberUserId,
                            ProjectMemberTask memberTask) {

        //设置文件夹
        ProjectModel pm = context.getProjectModel();

        ProjectFolderModel taskFolder = new ProjectFolderModel(context);

        taskFolder.setFolderType(ProjectConst.FOLDER_TYPE_TASK);
        taskFolder.setUserId(memberUserId);
        taskFolder.setParentId(parentFolder.getFolderId());
        taskFolder.setOwnerId(memberTask.getmDutyTaskId());

        taskFolder.setPhase(phase);
        taskFolder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);

        // Dict_WorkJobModel workJobModel = context.getProjectCacheManager().findDictWorkJobModel(getOwnerId());
        StringBuilder title = new StringBuilder(pm.getProjectName());
        title.append("-").append(memberTask.getTaskName());
        title.append("-文件夹V1.0.0");
        taskFolder.setFolderName(title.toString());
        taskFolder.setDepth(5);
        context.getProjectFileService().addFolderModel(taskFolder);

        return taskFolder.getFolderId();

    }

    //阶段岗位任务模板
    public void dictCopyTemplate(UserProjectContext context, ProjectContext pc, VwWorkGroupMemberDutyModel model) throws UnsupportedEncodingException {

        new CopyTemplateModelTask().copyTemplate(context, pc, this, model);
    }


    //创见自己定义的任务文件夹
    @Override
    public int createFolder(UserProjectContext context, int phase) {
        ProjectFolderModel phaseFolder = context.getPhaseFolder(phase);

        //设置文件夹
        ProjectModel pm = context.getProjectModel();

        ProjectFolderModel taskFolder = new ProjectFolderModel(context);
        taskFolder.setOwnerId(0);
        taskFolder.setFolderType(ProjectConst.FOLDER_TYPE_TASK_DEFINE);
        taskFolder.setParentId(phaseFolder.getParentId());
        taskFolder.setDepth(phaseFolder.getDepth()+1);

        taskFolder.setPhase(phase);
        taskFolder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);

        StringBuilder title = new StringBuilder(pm.getProjectName());
        title.append("-").append("自定义任务").append("-文件夹V1.0.0");
        taskFolder.setFolderName(title.toString());

        context.getProjectFileService().addFolderModel(taskFolder);
        return taskFolder.getFolderId();
    }

}
