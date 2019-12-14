package ytb.account.wallet.service.sq.external.project;

import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.ProjectTrade;
import ytb.account.wallet.model.ProjectTradeExample;

import java.util.List;

public interface ProjectTradeService {
    long countByExample(ProjectTradeExample example);

    int deleteByExample(ProjectTradeExample example);

    int deleteByPrimaryKey(Integer projectTradeId);

    int insert(ProjectTrade record);

    int insertSelective(ProjectTrade record);

    List<ProjectTrade> selectByExample(ProjectTradeExample example);

    ProjectTrade selectByPrimaryKey(Integer projectTradeId);

    int updateByExampleSelective(@Param("record") ProjectTrade record, @Param("example") ProjectTradeExample example);

    int updateByExample(@Param("record") ProjectTrade record, @Param("example") ProjectTradeExample example);

    int updateByPrimaryKeySelective(ProjectTrade record);

    int updateByPrimaryKey(ProjectTrade record);
}