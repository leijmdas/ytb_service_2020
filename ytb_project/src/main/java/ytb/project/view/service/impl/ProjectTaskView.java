package ytb.project.view.service.impl;

import ytb.project.context.UserProjectContext;
import ytb.project.common.ProjectConst;
import ytb.project.service.impl.flow.FlowFloderView;
import ytb.project.model.*;
import ytb.project.view.dao.ITalkTaskView;
import ytb.project.view.model.ProjectTaskViewResult;
import ytb.project.view.service.IProjectTaskView;
import ytb.common.context.model.YtbError;

public final class ProjectTaskView extends FlowFloderView implements IProjectTaskView {

    /**
     * 项目中心任务视图
     * @param context 上下文
     * @param userId 用户ID
     * @param phase 阶段
     * @param type 类型（PA、PB、PM）
     * @return
     */
    public ProjectTaskViewResult getProjectTaskView(UserProjectContext context, int userId, int phase, String type) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        if (pm.isPa(userId) || ptm.isPb(userId) || ptm.isPm(userId)) {
            ITalkTaskView talkTaskView = phase == ProjectConst.TalkPhase
                    ? new ProjectTaskViewTalk() : new ProjectTaskViewPhase();
            ProjectTaskViewResult result = talkTaskView.getProjectTaskView(context, userId, phase, type);
            return result;

        }
        throw new YtbError(YtbError.CODE_DEFINE_ERROR, "阶段不正确或者用户非法!");
    }


    // 查看任务界面
    public ProjectTaskViewResult viewOthersTask(UserProjectContext context, int loginUserId, int toUserId, int phase) {
        ProjectTaskViewResult result;

        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();

        //甲方查看
        if (context.isPa()) {
            result = getProjectTaskView(context, toUserId, phase, toUserId == ptm.getUserId2() ? "PB" : "PM");
        }
        //乙方负责人查看
        else if (context.isPb()) {
            result = getProjectTaskView(context, toUserId, phase, toUserId == pm.getUserId1() ? "PA" : "PM");
        }
        //乙方组员查看
        else if (context.isPm()) {
            result = getProjectTaskView(context, toUserId, phase, toUserId == pm.getUserId1() ? "PA" : "PB");
        } else {
            throw new YtbError(YtbError.CODE_USER_NOT_IN_GROUP);
        }

        return result;
    }

}

