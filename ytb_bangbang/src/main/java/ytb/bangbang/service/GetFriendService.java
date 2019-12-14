package ytb.bangbang.service;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.UserFriends;
import ytb.bangbang.model.UserFriendsExample;

import java.util.List;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/1/15
 */
public interface GetFriendService {

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
