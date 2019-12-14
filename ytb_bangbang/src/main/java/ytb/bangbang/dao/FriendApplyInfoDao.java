package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.Friend_Apply_InfoModel;

import java.util.List;

public interface FriendApplyInfoDao {
    /*
     * 功能： 获取单个用户申请记录
     * 参数：
     * 	userId :用户id  friendId: 好友id
     */
    Friend_Apply_InfoModel GetFriendApplyInfo(@Param("userId") int userId, @Param("friendId") int friendId);
    /*
     * 功能： 添加记录信息
     * 参数：
     * 	obj		:存放了修改记录的相关信息模型对象
     */
    int AddRecord(Friend_Apply_InfoModel obj);


    int getApplyId(@Param("userId") int userId,@Param("toUserId") int toUserId,@Param("friendsTypeId") int friendsTypeId);
    /*
     * 功能： 判断当前申请记录是否存在
     * 参数：
     * 	userId :用户id  friendId: 好友id
     */
    int IsExistence(@Param("userId")int userId,@Param("ToUserID")int friendId);
    /*
     * 功能： 设置记录未已操作/未操作 同意/拒绝
     */
     int setIsAgree(@Param("inviteId")int inviteId, @Param("IsAgree")int IsAgree);
    /*
     * 功能： 获取所有申请记录
     * 参数：
     */
     List<Friend_Apply_InfoModel> GetRecieveList(@Param("ToUserID") int ToUserID);

    /*
     * 功能：删除好友申请
     * 参数：
     */
     int DeleteApplyInfo(@Param("userId")int userId,@Param("ToUserID")int friendId);

    /*
     * 功能：好友申请7天过期
     * 参数：
     */
    void FriendApplyIgnore(@Param("userId")int userId);

    int getFriendsTypeIdByUserId(@Param("userId")int userId,@Param("toUserID")int toUserID);

    /**
     * 根据邀请Id获取邀请信息记录
     * @param inviteId
     * @return
     */
    Friend_Apply_InfoModel  getFriendApplyInfoByAppId(@Param("inviteId") int inviteId);

    Friend_Apply_InfoModel getFriendApplyModel(@Param("inviteId") int inviteId);
}
