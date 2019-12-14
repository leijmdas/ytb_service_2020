package ytb.project.view.service;

import ytb.project.context.UserProjectContext;
import ytb.project.view.model.ProjectAssitsViewResult;

public interface IProjectAssistView {
    //获取用户的协助文档   1发起协助方    2:协助接收方

    ProjectAssitsViewResult getProjectAssitsView (
            UserProjectContext context,
            int templateId,
            int userId,
            int type);

}
