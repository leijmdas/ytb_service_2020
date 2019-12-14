package ytb.account.wallet.service.sq.external.project;

import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.Project;
import ytb.account.wallet.model.ProjectExample;

import java.util.List;

public interface ProjectService{
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer projectId);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer projectId);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}