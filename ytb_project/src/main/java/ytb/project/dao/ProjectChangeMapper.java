package ytb.project.dao;

import org.apache.ibatis.annotations.Param;
import ytb.project.model.ProjectChangeModel;
import ytb.project.model.TabPhaseModel;

import java.util.List;

/**
 * Package: ytb.project.dao
 * Author: ZCS
 * Date: Created in 2019/4/11 16:14
 */
public interface ProjectChangeMapper {
    //添加变更记录
    int addChange(ProjectChangeModel changeModel);
    ProjectChangeModel getChangeStop(@Param("talkId") int talkId ,@Param("phase") int phase);

    /**
     * @param talkId 洽谈ID
     * @param phase 阶段
     * @return
     */
    ProjectChangeModel getChangeByTalkAndPhase(@Param("talkId") int talkId ,@Param("phase") int phase);


    /**
     * @param talkId 洽谈ID
     * @param newProjectId 新项目ID
     * @param phase 阶段
     * @return
     */
    ProjectChangeModel getChangeById(@Param("talkId") int talkId,@Param("newProjectId") int
            newProjectId,@Param("phase") int phase);

    List<ProjectChangeModel> getChangeModels(@Param("talkId") int talkId);

    List<TabPhaseModel> getChangeModelList(@Param("projectId") int projectId);

    void updateProjectChange(@Param("newProjectId") Integer newProjectId,@Param("phaseStatus") Integer
            phaseStatus,@Param("eventType") Integer
            eventType);
}
