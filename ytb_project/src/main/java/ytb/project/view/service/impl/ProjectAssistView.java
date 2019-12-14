package ytb.project.view.service.impl;

import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateUserModel;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.impl.flow.FlowFloderView;
import ytb.project.view.model.ProjectAssitsViewResult;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectEventModel;
import ytb.project.view.service.IProjectAssistView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 协助的页面视图
 */
public final class ProjectAssistView extends FlowFloderView implements IProjectAssistView {

    //获取用户的协助文档   1发起协助方    2:协助接收方
    @Override
    public ProjectAssitsViewResult getProjectAssitsView(
            UserProjectContext context,
            int templateId,
            int userId,
            int type) {

        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectAssitsViewResult result = new ProjectAssitsViewResult();
        Map<String, Object> mapStatus = new HashMap<>();
        int color = 0;//点亮
        int phaseStatus = ProjectConst.ASSIST_PHASESTATUS_START;
        List<ProjectTemplateUserModel> prjectTemplateUserList = IProjectFileDAOService
                .getProjectTemplateUser().getTemplateUserModelsAssist(templateId, userId);

        if (type == 1) {//协助发起方
            for (ProjectTemplateUserModel m : prjectTemplateUserList) {
                if (m.getStatus() != ProjectConst.TASK_STATUS_Submitted) {//color用于判断递交按钮是否可点击（接收方递交后才可点击）
                    color = 1;
                    mapStatus.put("phase", ptm.getPhase());
                }
            }
            mapStatus.put("phaseStatus", ProjectConst.ASSIST_PHASESTATUS_SUBMIT);
            mapStatus.put("assistType",ProjectConst.ASSIST_TYPE_PA);//发起方
        } else {
            for (ProjectTemplateUserModel m : prjectTemplateUserList) {
                if (m.getActiveStatus() != ProjectConst.ACTIVE_STATUS_ToModify) {//color用于判断递交按钮是否可点击（接收方编制过后才可点击）
                    color = 1;
                }
                if (m.getStatus() == ProjectConst.TASK_STATUS_Submitted || m.getStatus() ==ProjectConst.TASK_STATUS_Passing)
                {//接收方递交后隐藏递交按钮
                    color = 1;
                    phaseStatus = ProjectConst.ASSIST_PHASESTATUS_SUBMIT;
                }
            }
            mapStatus.put("phaseStatus", phaseStatus);
            mapStatus.put("assistType",ProjectConst.ASSIST_TYPE_PB);//接收方
            mapStatus.put("assitsFlag",ProjectConst.ASSIST_FLAG);//区别协助、洽谈
            List<ViewProjectEventModel> eventList = getAssistEventModels(context, ptm.getPhase(), userId);
            result.setAuditEventList(eventList);
        }
        //阶段事件

        mapStatus.put("phase", ptm.getPhase());
        mapStatus.put("color", color);//判断协助接收方按钮点亮
        result.setTemplateList(prjectTemplateUserList);
        result.setMapPhaseStatus(mapStatus);
        return result;
    }

}
