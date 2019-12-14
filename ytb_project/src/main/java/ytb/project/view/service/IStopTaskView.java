package ytb.project.view.service;

import ytb.project.context.UserProjectContext;
import ytb.project.view.model.ProjectStopTaskViewResult;

public interface IStopTaskView {
    //获取项目任务
    ProjectStopTaskViewResult getProjectStopView(
            UserProjectContext context,
            int userId,
            int stage,
            String type);

}
