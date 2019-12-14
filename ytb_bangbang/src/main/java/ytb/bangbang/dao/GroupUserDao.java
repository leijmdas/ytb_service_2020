package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.GroupUser;
import ytb.bangbang.model.GroupUserInfo;
import ytb.bangbang.model.Group_UserModel;

import java.util.List;

public interface GroupUserDao {
    /**
     * 获取群组员
     * @param groupId
     * @return
     */
     List<Group_UserModel> getGroupUserByGroupId(@Param("groupId")int groupId);

    /**
     * 判断该用户是否在群组中
     * @param userId
     * @param groupId
     * @return
     */
    int IsExistence(@Param("userId")int userId,@Param("groupId")int groupId);

    /**
     * 添加群组成员
     * @param userId
     * @param groupId
     * @return
     */
   int AddRecord(@Param("userId")int userId,@Param("groupId")int groupId);

    /**
     * 设置用户在群里身份
     * @param userId
     * @param groupId
     * @param type
     * @return
     */
   int SetUserType(@Param("userId")int userId,@Param("groupId")int groupId,@Param("type") int type);

    /**
     * 获取成员身份
     * @param userId
     * @param groupId
     * @return
     */
     Integer GetGroupUserType(@Param("userId")int userId,@Param("groupId")int groupId);

    /**
     * 删除所有成员
     * @param groupId
     * @return
     */
    int DeleteRecord(@Param("groupId") int groupId);

    /**
     * 删除成员
     * @param groupId
     * @param userId
     * @return
     */
    int DeleteUser(@Param("groupId") int groupId,@Param("userId")int userId);

    /**
     * 查询工作组和兴趣组
     */
    List<Group_UserModel> selGroupById(@Param("userId")int userId);


    /**
     * 根据groupId查找群主userId
     */
    int findGroupOwnerId(@Param("groupId") int groupId);

    int addUserToGroup(@Param("userId")int userId,@Param("groupId") int groupId,@Param("groupUserType") int groupUserType);

    List<GroupUserInfo> getGroupUsersInfo(@Param("groupId") int groupId);

    int addRecordu(GroupUser groupUser);

    /**
     * 将群移到默认分组中
     */
    int removeToDefaltType(@Param("userId") int userId,@Param("groupTypeId") int groupTypeId,@Param("defaltTypeId") int defaltTypeId);

    int addMember(GroupUser groupUser);

}
