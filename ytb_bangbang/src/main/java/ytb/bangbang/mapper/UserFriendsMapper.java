package ytb.bangbang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.UserFriends;
import ytb.bangbang.model.UserFriendsExample;

public interface UserFriendsMapper {
    long countByExample(UserFriendsExample example);

    int deleteByExample(UserFriendsExample example);

    int deleteByPrimaryKey(Integer userRltnId);

    int insert(UserFriends record);

    int insertSelective(UserFriends record);

    List<UserFriends> selectByExample(UserFriendsExample example);

    UserFriends selectByPrimaryKey(Integer userRltnId);

    int updateByExampleSelective(@Param("record") UserFriends record, @Param("example") UserFriendsExample example);

    int updateByExample(@Param("record") UserFriends record, @Param("example") UserFriendsExample example);

    int updateByPrimaryKeySelective(UserFriends record);

    int updateByPrimaryKey(UserFriends record);
}