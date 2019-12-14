package ytb.project.dao;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.CustomTaskModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewCustomTaskModel;

import java.util.List;
import java.util.Map;

public interface CustomTaskMapper {

    int addCustomTask(CustomTaskModel customTask);
    //修改新项目标识
    void modifyCustomTask(@Param("customTaskId") int customTaskId, @Param("newProjectId")  int newProjectid);

    List<Map<String, Object>> selectCustomTask(@Param("projectId") int projectId, @Param("phase") int phase, @Param("userId") int userId);

    List<ViewCustomTaskModel> selectViewCustomTaskModel(@Param("projectId") int projectId, @Param("phase") int phase, @Param("userId") int userId);

    List<ViewCustomTaskModel> getCustomTaskList(@Param("userId") int userId,@Param("projectId")
                                                int projectId);

}
