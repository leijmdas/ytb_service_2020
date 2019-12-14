package ytb.project.dao.tagtable;




import org.apache.ibatis.annotations.Param;

import ytb.project.model.tagtable.ProjectPlanModel;

public interface ProjectPlanMapper {


    //查询项目工作里程碑
    ProjectPlanModel getProjectPlan(@Param("projectId") int projectId,@Param("documentId") int documentId);


}
