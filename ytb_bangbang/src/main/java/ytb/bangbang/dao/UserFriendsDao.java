package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.BBUserInfo;
import ytb.bangbang.model.FriendsInfoModel;
import ytb.bangbang.model.UserFriendsGroup;

import java.util.List;

/**
 * @Author hj
 * @Description //TODO
 * @Date 2018/8/31
 **/
public interface UserFriendsDao {
    /*
     * 功能：查看是否存在好友关系
     * 参数：
     * 	UserId :用户id
     * 	FriendId :好友id
     */
    int IsFriend(@Param("userId")int userId, @Param("friendId")int friendId);
    /*
     * 功能： 添加记录信息
     * 参数
     * userId;用户id
     * friendId:好友id
     * Remarks:备注
     */
     int AddRecord(@Param("userId")int userId,@Param("friendId")int friendId,@Param("remarks") String Remarks,@Param("friendsTypeId")int friendsTypeId);
    /*
     * 功能： 修改备注
     * 参数
     * userId;用户id
     * friendId:好友id
     * Remarks:备注
     */
     int setUserRemarks(@Param("userId")int userId,@Param("friendId")int friendId,@Param("remarks") String Remarks);
    /*
     *  删除记录信息
     *  userId;用户id
     * friendId:好友id
     */
     int  DeleteRecord(@Param("userId")int userId,@Param("friendId")int friendId);
    /*
     * 功能：获取全部好友
     * 参数：
     * 	UserId :用户id
     */
    List<FriendsInfoModel> GetUserFriend(@Param("userId")int userId,@Param("remarks")String remarks);

    List<BBUserInfo> selectUserFriendList(@Param("userId")int userId);

    /**
     * 获取好友和好友分组
     * @param userId
     * @return
     */
    List<UserFriendsGroup> selectUserFriendGroupList(@Param("userId")int userId);

    /**
     * 移动好友至某个分组
     */
    int editFriendTyp(@Param("userId")int userId,@Param("friendId")int friendId,@Param("friendsTypeId") int friendsTypeId);

    int existFriends(@Param("userId")int userId,@Param("friendsTypeId") int friendsTypeId);

    int removeFriend(@Param("userId")int userId,@Param("friendsTypeId") int friendsTypeId,@Param("moveGroupId") int moveGroupId);
}
