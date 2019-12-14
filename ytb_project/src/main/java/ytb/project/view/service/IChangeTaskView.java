package ytb.project.view.service;

import ytb.project.context.UserProjectContext;
import ytb.project.view.model.ProjectChangeTaskViewResult;

public interface IChangeTaskView {
    //获取项目任务
    ProjectChangeTaskViewResult getProjectChangeView(UserProjectContext context, int newTalkId,
                                                     String type, int phase);

    ProjectChangeTaskViewResult getPbKnitChangeTask(UserProjectContext context, int
            newTalkId, int usetId ,int phase);

    ProjectChangeTaskViewResult getPmKnitChangeTask(UserProjectContext context, int
            newTalkId,int phase);
}
