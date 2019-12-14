package ytb.bangbang.service;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.GroupInfo;
import ytb.bangbang.model.GroupInfoExample;

import java.util.List;

public interface GroupInfoMapperService {
    long countByExample(GroupInfoExample example);

    int deleteByExample(GroupInfoExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    List<GroupInfo> selectByExample(GroupInfoExample example);

    GroupInfo selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByExample(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByPrimaryKeySelective(GroupInfo record);

    int updateByPrimaryKey(GroupInfo record);
}