package ytb.project.view.service;

import ytb.project.context.UserProjectContext;
import ytb.project.view.model.ProjectTaskViewResult;

public interface IProjectTaskView {
    //获取项目任务
    ProjectTaskViewResult getProjectTaskView(
            UserProjectContext context,
            int userId,
            int stage,
            String type);

    ProjectTaskViewResult viewOthersTask(
            UserProjectContext context,
            int loginUserId,
            int toUserId,
            int stage);


}
