package ytb.bangbang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.GroupUser;
import ytb.bangbang.model.GroupUserExample;

public interface GroupUserMapper {
    long countByExample(GroupUserExample example);

    int deleteByExample(GroupUserExample example);

    int deleteByPrimaryKey(Integer groupUserId);

    int insert(GroupUser record);

    int insertSelective(GroupUser record);

    List<GroupUser> selectByExample(GroupUserExample example);

    GroupUser selectByPrimaryKey(Integer groupUserId);

    int updateByExampleSelective(@Param("record") GroupUser record, @Param("example") GroupUserExample example);

    int updateByExample(@Param("record") GroupUser record, @Param("example") GroupUserExample example);

    int updateByPrimaryKeySelective(GroupUser record);

    int updateByPrimaryKey(GroupUser record);
}