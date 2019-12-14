package ytb.project.model.projectFolderView;

import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.model.iface.IFolderModel;
import ytb.project.model.projectFolderView.projectTemplate.CopyTemplateModelTask;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.manager.template.model.Dict_WorkJobModel;
import ytb.user.model.UserInfoModel;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class PMDutyFolder extends ProjectFolderBase implements IFolderModel {
    VwWorkGroupMemberDutyModel memberDutyModel;

    Map<Integer,Integer> task2FolderId = new HashMap<>();

    public VwWorkGroupMemberDutyModel getMemberDutyModel() {
        return memberDutyModel;
    }

    public void setMemberDutyModel(VwWorkGroupMemberDutyModel memberDutyModel) {
        this.memberDutyModel = memberDutyModel;
    }


    @Override
    public int createFolder(UserProjectContext context, int phase) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectFolderModel folder = new ProjectFolderModel(context);
        folder.setParentId(this.getParentId());

        folder.setFolderType(ProjectConst.FOLDER_TYPE_DUTY_MEMBER);
        folder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);
        folder.setOwnerId(getOwnerId());

        folder.setUserId(getUserId());
        folder.setAuditor(ptm.getUserId2());

        folder.setProjectId(pm.getProjectId());
        folder.setTalkId(ptm.getTalkId());
        folder.setPhase(phase);
        folder.setDepth(4);

        UserInfoModel uiModel = context.getInst().getUserCenterService().getUserInfoById(this.getUserId());

        //智能硬件-2设计-01-【项目名称】-【岗位名称A/B/C/D/E】文件夹V1.0.0 智能硬件-2设计-02-【项目名称】-【昵称An/Bn/Cn】-设计文件夹V1.0.0
        Dict_WorkJobModel workJobModel = context.getProjectCacheManager().findDictWorkJobModel(getOwnerId());
        StringBuilder title = new StringBuilder(pm.getProjectName());
        title.append("-").append(uiModel.getNickName());
        title.append("-").append(workJobModel.getTitle());
        title.append("-文件夹V1.0.0");
        folder.setFolderName(title.toString());

        folder.setCreateBy(context.getUserId());
        folder.setUpdateTime(new Date());
        context.getProjectFileService().addFolderModel(folder);  //创建阶段文件夹

        this.setFolderId(folder.getFolderId());
        this.setPhase(phase);
        //创建任务文件夹
        createTaskFolders(context,phase,folder);
        return folder.getFolderId();
    }


    //阶段岗位任务模板
    public void dictCopyTemplate(UserProjectContext context, ProjectContext pc, VwWorkGroupMemberDutyModel model) throws UnsupportedEncodingException {

        new CopyTemplateModelTask().copyTemplate(context, pc, this, model);
    }

    List<TaskFolder> createTaskFolders(UserProjectContext context, int phase, ProjectFolderModel folderModel) {

        List<TaskFolder> folders = new ArrayList<>();
        List<ProjectMemberTask> memberTasks =
                ProjectTalkModel.getWorkGroup().getProjectMemberTasks(getMemberDutyModel().getMemberDutyId());
        for (ProjectMemberTask pmTask : memberTasks) {
            TaskFolder taskFolder = context.getViewProjectFolderModel().getpBFolder().getTaskFolder();
            taskFolder.createFolder(context, phase, folderModel, getMemberDutyModel().getUserId(), pmTask);
            task2FolderId.put(pmTask.getmDutyTaskId(),taskFolder.getFolderId());

            folders.add(taskFolder);
        }
        return folders;
    }

}