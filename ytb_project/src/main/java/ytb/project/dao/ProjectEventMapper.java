package ytb.project.dao;




import org.apache.ibatis.annotations.Param;
import ytb.project.model.ProjectEventViewModel;
import ytb.project.model.ProjectEventModel;

import java.util.List;
import java.util.Map;

public interface ProjectEventMapper {

    //添加项目事件
    int addProjectEvent(ProjectEventModel projectPEvent);

    List<Map<String, Object>> getProjectEvent(Map map);

    List<ProjectEventViewModel> selectProjectEventViewModel(@Param("projectId") int projectId, @Param("eventType") int eventType, @Param("phase") int phase,@Param("userId") int userId);

    List<Map<String, Object>>  selectProjectEventByP(@Param("projectId") int projectId, @Param("phase") int phase);


    List<ProjectEventViewModel>  selectProjectEventViewModelByUserId(@Param("projectId") int projectId,
                                                          @Param("phase") int phase,
                                                          @Param("userId") int userId,
                                                                     @Param("phaseStatus") int phaseStatus);


    List<ProjectEventViewModel> selectPayEventViewModel(@Param("userId") int userId,@Param("talkId") int talkId,@Param("phase") int phase);

    List<ProjectEventViewModel> selectProEventViewModelByPhaseStatus(@Param("projectId") int projectId, @Param("userId") int userId, @Param("phaseStatus") int phaseStatus);

    List<Map<String, Object>> selectProEventByPhaseStatus(@Param("projectId") int projectId, @Param("userId") int userId, @Param("phaseStatus") int phaseStatus);

    List<Map<String, Object>> getBeforeTalkEvent(Map map);

    //获取项目变更阶段事件
    List<ProjectEventViewModel> getProEventInChange(@Param("projectId") int projectId, @Param("userId") int usetId, @Param("phase") int phase);
}
