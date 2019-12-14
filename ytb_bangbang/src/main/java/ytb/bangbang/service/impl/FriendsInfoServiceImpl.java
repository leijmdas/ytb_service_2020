package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.mapper.UserFriendsMapper;
import ytb.bangbang.model.FriendGroupInfo;
import ytb.bangbang.model.GroupInfoFriend;
import ytb.bangbang.model.UserFriends;
import ytb.bangbang.model.UserFriendsExample;
import ytb.bangbang.service.FriendsInfoService;
import ytb.bangbang.util.MyBatisUtil;
import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

import java.util.List;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/1/16
 */
public class FriendsInfoServiceImpl implements FriendsInfoService {

    @Override
    public List<UserFriends> selectList(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.user_ID,a.friend_ID, b.real_name ");
        sql.append(" from ytb_bangbang.user_friends a ");
        sql.append(" inner join ytb_user.user_info b ");
        sql.append(" on a.friend_ID=b.user_id ");
        sql.append(" where a.user_ID=").append(userId);
        return YtbSql.selectList(sql, UserFriends.class);
    }


    @Override
    public FriendGroupInfo getFriendGroupInfo(Integer userId) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            FriendGroupInfo friendGroupInfo = new FriendGroupInfo();
            UserFriendsMapper friendsMapper = session.getMapper(UserFriendsMapper.class);
            UserFriendsExample friendsExample = new UserFriendsExample();
            friendsExample.createCriteria().andUserIdEqualTo(userId);
            List<UserFriends> friends = friendsMapper.selectByExample(friendsExample);

            friendGroupInfo.setUserFriends(friends);
            return friendGroupInfo;

        } finally {
            session.close();
        }
    }

    @Override
    public List<GroupInfoFriend> selectGroupInfoFriendList(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select gi.group_id,gi.group_name,gi.create_time,gi.group_icon,gi.group_type ");
        sql.append("from  ytb_bangbang.group_info  gi INNER JOIN  ytb_bangbang.group_user gu  on gi.group_id=gu.group_id ");
        sql.append("where gi.group_type=1 and gu.user_id=").append(userId);
        return YtbSql.selectList(sql, GroupInfoFriend.class);
    }

    @Override
    public List<UserFriends> selectUserFriendsByGroupId(Integer groupId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.user_id,a.real_name ");
        sql.append("FROM  ytb_user.user_info a  LEFT JOIN  ytb_bangbang.group_user g  ON g.user_id = a.user_id ");
        sql.append("WHERE g.group_id =").append(groupId);
        return YtbSql.selectList(sql, UserFriends.class);
    }

    @Override
    public List<UserFriends> selectUserFriendsList(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.friend_ID  user_id,b.real_name ");
        sql.append(" from ytb_bangbang.user_friends a ");
        sql.append(" inner join ytb_user.user_info b ");
        sql.append(" on a.friend_ID=b.user_id ");
        sql.append(" where a.user_ID=").append(userId);
        return YtbSql.selectList(sql, UserFriends.class);
    }
}
