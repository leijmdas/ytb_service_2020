package ytb.project.dao;
import org.apache.ibatis.annotations.Param;
import ytb.project.model.ProjectPhaseModel;

public interface ProjectPhaseMapper {

    //添加项目阶段
    int addProjectPhase(ProjectPhaseModel projectPhase);

    //查询项目阶段
    ProjectPhaseModel getProjectPhase(@Param("talkId") int talkId,@Param("phase") int phase);

    //查询项目阶段
    ProjectPhaseModel getProjectPhaseByProjectId(@Param("projectId") int projectId,@Param("phase") int phase);


}
