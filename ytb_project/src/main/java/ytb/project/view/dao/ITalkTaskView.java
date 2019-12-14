package ytb.project.view.dao;

import ytb.project.context.UserProjectContext;
import ytb.project.service.IFlowFolderView;
import ytb.project.view.model.ProjectTaskViewResult;

public interface ITalkTaskView extends IFlowFolderView {

    ProjectTaskViewResult getProjectTaskView(UserProjectContext context, int userId, int phase, String type);

    ProjectTaskViewResult getPaProjectTask(UserProjectContext context, int userId, int phase);

    ProjectTaskViewResult getPbProjectTask(UserProjectContext context, int userId, int phase);

    ProjectTaskViewResult getPmProjectTask(UserProjectContext context, int userId, int phase);


}
