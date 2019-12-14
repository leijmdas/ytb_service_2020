package ytb.project.view.service.impl;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.impl.flow.FlowFloderView;
import ytb.project.view.model.ProjectStopTaskViewResult;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.project.view.service.IStopTaskView;
import ytb.common.context.model.YtbError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StopTaskViewTalk extends FlowFloderView implements IStopTaskView {

    public ProjectStopTaskViewResult getProjectStopView(UserProjectContext context, int userId, int
            phase, String type) {

        if ("PA".equals(type)) {
            return getPaStopProjectTaskView(context, userId, phase);
        } else if ("PB".equals(type)) {
            return getPbStopProjectTaskView(context, userId, phase);
        } else if ("PM".equals(type)) {
            return getPmStopProjectTaskView(context, userId, phase);
        }

        throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
    }


    Map<String, Object> buildMapPhaseStatus(ProjectTalkModel ptm) {
        Map<String,Object> mapStatus = new HashMap<>();
        mapStatus.put("phase", ptm.getPhase());
        mapStatus.put("phaseStatus", ptm.getPhaseStatus());
        return mapStatus;
    }

    public List<ViewProjectTemplateUserModel> selectViewProjectTemplateUserModel(
            int folderId,
            int userId,
            int author) {
        List<ViewProjectTemplateUserModel> result =
                IProjectFileDAOService.getProjectTemplateUser().selectViewProjectTemplateUserModel(
                        folderId, userId, author);
        //加个判断，只有当点击查看任务才进这里
        for (ViewProjectTemplateUserModel vptu : result){
                if(author == vptu.getShareReceive()){
                    vptu.setViewShare(true);
                }
        }
        return result;

    }

    public ProjectStopTaskViewResult getPaStopProjectTaskView(UserProjectContext context, int userId,int phase) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();
        ProjectStopTaskViewResult result = new ProjectStopTaskViewResult();
        //获取变更的文件夹
        ProjectFolderModel stopFolder =  ptm.fetchStopFolder();
        List<ViewProjectTemplateUserModel> templateList =
                IProjectFileDAOService.getProjectTemplateUser().select(stopFolder.getFolderId(),
                        pm.getUserId1());
        result.setTemplateList(templateList);
        return result;
    }

    //乙方任务洽谈
    public ProjectStopTaskViewResult getPbStopProjectTaskView(UserProjectContext context, int userId,int phase) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectStopTaskViewResult result = new ProjectStopTaskViewResult();
        //获取变更的文件夹
        ProjectFolderModel stopFolder =  ptm.fetchStopFolder();
        List<ViewProjectTemplateUserModel> templateList =
                IProjectFileDAOService.getProjectTemplateUser().select(stopFolder.getFolderId(), userId);
        result.setTemplateList(templateList);
        return result;
    }

    //组员任务界面
    public ProjectStopTaskViewResult getPmStopProjectTaskView(UserProjectContext context, int userId,int phase) {
        ProjectStopTaskViewResult result = new ProjectStopTaskViewResult();

        return result;
    }

}
