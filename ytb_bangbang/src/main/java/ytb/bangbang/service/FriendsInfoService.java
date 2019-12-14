package ytb.bangbang.service;


import ytb.bangbang.model.FriendGroupInfo;
import ytb.bangbang.model.GroupInfoFriend;
import ytb.bangbang.model.UserFriends;

import java.util.List;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/1/16
 */
public interface FriendsInfoService {


    FriendGroupInfo getFriendGroupInfo(Integer userId);
    List<UserFriends> selectList(Integer userId);
    List<GroupInfoFriend> selectGroupInfoFriendList(Integer userId);
    List<UserFriends> selectUserFriendsByGroupId(Integer groupId);
    List<UserFriends> selectUserFriendsList(Integer userId);
}
