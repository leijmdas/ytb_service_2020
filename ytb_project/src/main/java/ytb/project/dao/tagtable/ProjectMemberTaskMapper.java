package ytb.project.dao.tagtable;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.tagtable.ProjectMemberTask;

import java.util.List;

public interface ProjectMemberTaskMapper {

    //通过岗位获取任务
    List<ProjectMemberTask> getProjectMemberTasks(int memberDutyId);

    //添加岗位任务
    void addProjectMemberTask (ProjectMemberTask projectMemberTask);

    //通过id获取组员任务
    ProjectMemberTask getProjectMemberTaskById(int mDutyTaskId);

    //删除岗位任务
    void delProjectMemberTask(int mDutyTaskId);

    //获取物流单号
    ProjectMemberTask getPhaseTask(@Param("taskName") String taskName, @Param("projectId") int projectId);

    //修改物流单号
    void modifyPhaseTask(ProjectMemberTask projectMemberTask);
}
