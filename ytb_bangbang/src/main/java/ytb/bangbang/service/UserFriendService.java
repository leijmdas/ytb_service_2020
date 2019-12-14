package ytb.bangbang.service;

import ytb.bangbang.model.FriendsInfoModel;

import java.util.List;

public interface UserFriendService {
    /**
     * 申请好友
     * @param userId
     * @param friendId
     * @return
     */
    int applyAddFriend(int userId,int friendId,int friendsTypeId);

    /**
     * 是否同意好友
     * @param userId
     * @param friendId
     * @param isAgree
     * @return
     */
    int isAddFriend(int userId,int friendId,int isAgree,int friendsTypeId,int inviteId);

    /**
     * 删除好友
     * @param userId
     * @param friendId
     * @return
     */
    int deleteUserFriend(int userId,int friendId);

    /**
     * 获取申请记录
     * @param toUserId
     * @return
     */
    List GetApplyUserFriend(int toUserId);

    /**
     * 添加聊天记录
     * @param userId
     * @param content
     * @param friendId
     * @return
     */
    int AddRecordUser(int userId,String content ,int friendId);

    /**
     * 功能：获取全部好友
     * 参数：
     * @param userId
     * @param remarks
     * @return
     */
    List<FriendsInfoModel> GetUserFriend(int userId,String remarks);

    /**
     * 好友申请7天过期
     * @param userId
     */
    void FriendsApplyIgnore(int userId);

    void editFriendTyp(int userId,int friendId,int friendsTypeId);

    int existFriends(int userId,int friendsTypeId);

    int removeFriend(int userId,int friendsTypeId,int moveGroupId);

}
