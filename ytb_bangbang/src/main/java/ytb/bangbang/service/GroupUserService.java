package ytb.bangbang.service;

import ytb.bangbang.model.GroupUser;
import ytb.bangbang.model.GroupUserInfo;
import ytb.bangbang.model.Group_UserModel;

import java.util.List;

/**
 * tanc
 */

public interface GroupUserService {
    /**
     * 查询工作组和兴趣组
     * @param userId
     * @return
     */
    List<Group_UserModel> selGroupById(int userId);

    int findGroupOwnerId(int groupId);

    /**
     * 判断该用户是否在群中
     */
    boolean IsExistence(int userId,int groupId);

    int addUserToGroup(int userId,int groupId,int groupUserType);

    /**
     * 获取群成员
     * @param groupId
     * @return
     */
    List<Group_UserModel> getGroupUserByGroupId(int groupId);

    List<GroupUserInfo> getGroupUsersInfo(int groupId);

    int addRecordu(GroupUser groupUser);

    /**
     * 将群移到默认分组中
     */
    int removeToDefaltType(int userId,int groupTypeId,int defaltTypeId);

    int addMember(GroupUser groupUser);

    Integer GetGroupUserType(int userId,int groupId);
}

