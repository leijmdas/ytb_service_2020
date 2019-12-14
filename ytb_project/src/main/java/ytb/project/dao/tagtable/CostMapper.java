package ytb.project.dao.tagtable;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.tagtable.CostModel;

import java.util.List;

public interface CostMapper {

    //添加项目费用
    void addProjectPee(CostModel costModel);

    //通过项目查询项目费用
    List<CostModel> getProjectCostByTalk(@Param("talkId") int talkId,   @Param("documentId") int documentId);

    List<CostModel> getProjectFeeByTalkId(@Param("projectId") int projectId, @Param("userId") int userId, @Param("documentId") int documentId);


}