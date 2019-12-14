package ytb.project.service.project.change.impl;

import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ChangeDaoServiceImpl;
import ytb.project.daoservice.ProjectDAOService;
import ytb.project.daoservice.ProjectInviteDAOService;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.projectTemplate.ProjectChange.CopyTemplateModelChange;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.project.change.IChangeService;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.user.model.UserInfoModel;
import java.io.UnsupportedEncodingException;
import java.util.Date;
/**
 * Package: ytb.project.service.project.change
 * Author: ZCS
 * Date: Created in 2019/3/30 13:42
 */
public abstract class ChangeService extends ComputeChangeType implements IChangeService {

    protected Dict_TemplateModel getChangeTemplate(int chngType) {
        return  getInst().getProjectCacheManager() .findChangeTemplateModel(chngType);
    }

    public ProjectTalkModel getNewProjectTalk(int newProjectId) {
        return ProjectFlowService.getTalkService().getProjectTalkByProjectId(newProjectId);
    }

    public boolean needReApply(UserProjectContext context) {

        ProjectChangeModel pcm = context.getProjectChangeModel();
        if (pcm == null) {
            return false;
        }
        return pcm.getPhaseStatus()== ProjectConstState.PRO_CHANGESTATUS_CANCEL_CHANGE;
    }

    //创建新项目
    public Integer createNewProject(ProjectModel pm){

        ProjectModel newPm = pm.cloneProjectModel();
        //生成新项目名称
        String newProjectName = pm.getProjectName()+"2";
        newPm.setProjectName(newProjectName);
        newPm.setOldTalkId(0);
        newPm.setStatus(ProjectConst.TASK_STATUS_NO_RIGHT);
        int newProjectId = new ProjectDAOService().addProject(newPm);
        return newProjectId;
    }

    //创建变更的新洽谈
    public int createNewTalk(UserProjectContext context, int newProjectId, int newFolderId) {

        ProjectInviteModel pim = new ProjectInviteModel(0,0,0,0,0,0,"");
        int inviteId = new ProjectInviteDAOService().addProjectInvite(pim);

        ProjectTalkModel projectTalkModel = context.getProjectTalkModel().cloneProjectTalkModel();
        projectTalkModel.setPhase(ProjectConst.TalkPhase);//根据大小变更再去修改
        projectTalkModel.setProjectId(newProjectId);
        projectTalkModel.setProjectIdOk(newProjectId);
        projectTalkModel.setTalkId(inviteId);
        projectTalkModel.setFolderId(newFolderId);
        //后面修改
        projectTalkModel.setWorkplanId(0);
        projectTalkModel.setGroupId(0);

        ProjectModel pm = context.getProjectModel();
        pm.getProjectTalkService().addProjectTalk(projectTalkModel);
        return projectTalkModel.getTalkId();
    }


    //创建新的工作组
    public int createNewWorkGroup(UserProjectContext context) {
        return ProjectTalkModel.getWorkGroup().createNewWorkGroup(context);
    }

    //变更阶段修改用户文档状态
    protected void modifyTemplateStatus(int pa, int pb, int documentId, int status) {

        ProjectTemplateUserModel userModelPa = IProjectFileDAOService.getProjectTemplateUser().selectOne(pa,
                documentId);//甲方
        ProjectTemplateUserModel userModelPb = IProjectFileDAOService.getProjectTemplateUser().selectOne(pb, documentId);//乙方
        if (status == 0) {
            if (userModelPb.getActiveStatus() == ProjectConst.ACTIVE_STATUS_ToModify
                    || userModelPb.getActiveStatus() == ProjectConst.ACTIVE_STATUS_GoRead) {//去修改,未提交
                userModelPb.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
                userModelPb.setStatus(ProjectConst.TASK_STATUS_Submitted);
                userModelPa.setStatus(ProjectConst.TASK_STATUS_NotStart);
                userModelPa.setDisplayStatusLight();
            }
        } else if (status == 1) {
            userModelPa.setStatus(ProjectConst.TASK_STATUS_Passing);
            userModelPb.setStatus(ProjectConst.TASK_STATUS_Passing);
        } else if (status == 2) {
            userModelPa.setStatus(ProjectConst.TASK_STATUS_NotPassing);
            userModelPb.setStatus(ProjectConst.TASK_STATUS_NotPassing);
        }

        IProjectFileDAOService.getProjectTemplateUser().modify(userModelPa);
        IProjectFileDAOService.getProjectTemplateUser().modify(userModelPb);

    }

    // 拷贝乙方文件夹
    protected int copyFolderByProject(UserProjectContext context, String folderName, int newProjectId) throws UnsupportedEncodingException {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectFolderModel folder = ptm.fetchTalkFolderModel();
        UserInfoModel infoModel = getInstUser().getUserCenterService().getUserInfoById(folder.getUserId());
        folder.setFolderName(infoModel.getNickName() + "-" + folderName);//设置文件夹名称
        folder.setAuditor(0);
        folder.setUpdateTime(new Date());
        folder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);
        folder.setProjectId(newProjectId);
        folder.setPhase(folder.getPhase());
        return getInst().getProjectFileService().addFolderModel(folder);//新生成项目的文件夹
    }

    protected void copyTemplateByProject(UserProjectContext context, int newFolderId, ProjectContext newPc) throws UnsupportedEncodingException {

        new CopyTemplateModelChange().copyPBProjectTemplate(context, newFolderId, newPc);

    }

    //新增变更记录
    public int addChange(ProjectChangeModel changeModel){
        return new ChangeDaoServiceImpl().addChange(changeModel);
    }

}
