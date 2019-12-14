package ytb.project.dao;

import org.apache.ibatis.annotations.Param;
import ytb.project.model.ProjectModel;
import ytb.project.model.VProjectTalkConfirmModel;
import ytb.project.model.projectview.ProjectTalkViewModel;
import ytb.project.model.projectview.ProjectViewModel;

import java.util.List;
import java.util.Map;

public interface ProjectMapper {
    //添加项目
    int addProject(ProjectModel projectModel);

    //根据用户查询所有相关项目
    List<ProjectTalkViewModel> getProjectTalkList(@Param("userId") Integer userId, @Param("companyId") Integer companyId,
                                                  @Param("phaseStart") Integer phaseStart,
                                                  @Param("phaseEnd") Integer PhaseEnd,
                                                  @Param("changeStatus") Integer changeStatus);

    List<ProjectViewModel> getProjectList(@Param("userId") Integer userId, @Param("companyId") Integer companyId,
                                          @Param("status") Integer status);

    //根据公司查询项目
    List<Map<String, Object>> getProjectsByCompany(Map map);

    //多条件模糊搜索项目
    List<Map<String, Object>> selectProjectList(Map map);

    //修改项目状态
    void modifyProjectStatus(@Param("status") int status,@Param("projectId") int projectId);

    //根据公司id获取发布中的项目
    List<Map<String, Object>> getReleaseProjectList(Map map);

    //根据公司查询已完成的项目
    List<Map<String, Object>> getProjectByChangeStatus(Map map);

    //修改项目
    void modifyFolder(@Param("projectId") int projectId,@Param("folderId") int folderId);

    //查询公司员工有关的项目
    List<Map<String, Object>> getProjectListByCompUser(Map map);

    //根据projectId查询项目
    ProjectModel getProjectById(int projectId);

    //查询终止项目
    List<Map<String, Object>> getEndProject(Map map);

    //查询已经完成项目
    List<Map<String, Object>> selectFinishProject(Map<String, Object> map);

    //查询终止项目总数
    int selectFinishProjectCount(int userId);

    //查询最新项目
    List<Map<String, Object>> selectNewProject();

    List<Map<String, Object>> selectProjectRun(Map map);

    List<Map<String, Object>> selectProjectTalk(Map map);

    int getProjectListCount(Map map);

    List<Map<String, Object>> getFinishProjectView(@Param("int") int userId, @Param("int") int projectId);

    //查询用户所发布过的项目
    List<Map<String, Object>> getUserSendPro(Map<String, Object> map);

    int getUserSendProCount(int userId);

    //添加预付款时间
    int modifyProjectPayTimes(int projectId);

    //修改项目是否是界面造型
    int modifyProjectModelFlag(@Param("int") int projectId, @Param("byte") byte modelFlag);

    //修改浏览数
    void modifyViewNumber(int projectId);


    //修改收藏数
    void modifyFavorite(int projectId);

    void cancelFavorite(int projectId);

    void modifyStopTime(int projectId);

    //获取公司发布、洽谈过的项目
    List<Map<String, Object>> getCompanySendAndTalkPro(Integer companyId);

    //获取公司发布、洽谈过的项目
    List<Map<String, Object>> getUserSendAndTalkPro(Integer userId);

    int getEndProjectCount(Map map);

    //变更阶段修改项目信息
    void updateProjectInfoInChangeStage(@Param("projectId") Integer projectId,
                                        @Param("newProjectId")Integer newProjectId,
                                        @Param("oldTalkId")Integer oldTalkId,
                                        @Param("changeType") Integer changeType);

    //查询进入洽谈的项目
    VProjectTalkConfirmModel getVProjectTalkConfirmInfo(int projectId);
}
