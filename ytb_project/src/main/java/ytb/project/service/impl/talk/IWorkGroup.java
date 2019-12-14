package ytb.project.service.impl.talk;

import ytb.project.context.UserProjectContext;
import ytb.project.dao.tagtable.WorkGroupMapper;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.ProjectMemberDutyModel;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.model.tagtable.WorkGroupModel;

import java.util.List;
import java.util.Map;

public interface IWorkGroup extends WorkGroupMapper {
    void modifyGroupAndMemberDocumentID(ProjectTalkModel ptm);

    //设置工作组成员
    void addWorkGroupMember(int userId, int groupId, int projectId, int isAdmin, int talkId);

    //添加工作组成员
    void addWorkGroupMember(WorkGroupMemberModel workGroupMember);

    //查询工作组
    WorkGroupMemberModel getWorkGroupMember(int projectId, int userId, int documentId);

    //获取工作组成员
    List<WorkGroupMemberModel> getWorkGroupMember(Integer groupId, Integer userId);

    //获取工作组成员详细信息
    List<Map<String, Object>> getWorkGroupMemberInfo(int groupId);

    // 获取任务
    List<ProjectMemberDutyModel> getProjectMemberDuty(int memberId);

    //通过岗位获取任务
    List<ProjectMemberTask> getProjectMemberTasks(int memberDutyId);

    //添加任务
    void addProjectMemberTask(ProjectMemberTask projectMemberTask);

    int addProjectMemberTask(int projectId, String taskName, String taskRemark,
                             int mode, int templateId, int memberDutyId);

    //删除岗位任务
    void delProjectMemberTask(int mDutyTaskId);

    void modifyPhaseTask(ProjectMemberTask projectMemberTask);

    ProjectMemberTask getPhaseTask(String taskName, int projectId);

    //根据任务ID获取任务
    ProjectMemberTask getProjectMemberTaskById(int mDutyTaskId);

    WorkGroupModel getWorkGroupById(int groupId);

    void modifyGroupMemberActive(Integer isActiveType, Integer memberId);

    int createNewWorkGroup(UserProjectContext context);

//    WorkGroupModel buildWorkGroup(UserProjectContext context);
//    int bbAddGroupAndUser(int userId1, String groupName, int userId);
//    int addGroupAndUser(UserProjectContext context, int bbGoupId);

}
