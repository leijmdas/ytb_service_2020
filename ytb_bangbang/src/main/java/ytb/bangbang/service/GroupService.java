package ytb.bangbang.service;

import com.alibaba.fastjson.JSONArray;
import ytb.bangbang.model.GroupNoticeModel;
import ytb.bangbang.model.Group_InfoModel;
import ytb.bangbang.model.Record_GroupModel;

import java.util.List;

/**
 * 群组
 */
public interface   GroupService {
    /**
     * 获取群组成员
     * @param userId
     * @param groupId
     * @return
     */
     List getGroupUserInfos(int userId, int groupId);

    /**
     * 获取所有成员id
     * @param groupId
     * @return
     */
     List getGroupUserId(int groupId);
    /**
     * 新建群组
     * @param groupName
     * @param groupType
     * @param userID
     * @return
     */
    int  addGroup(String groupName,int groupType,int userID);

    /**
     * 删除群组
     * @param userId
     * @param groupId
     * @return
     */
    int  deleteGroup(int userId,int groupId);

    /**
     * 添加群组组成员
     * @param userId
     * @param groupId
     * @param groupUserIdArr
     */
    int addGroupUser(int userId,int groupId, List<Integer> groupUserIdArr);

    /**
     * 新增工作群组成员
     *
     * @param groupId 群组Id
     * @param groupUserIdArr 群组成员Id的集合
     * @param userId 创建者id
     */
    int addWorkGroupUser(int userId,int groupId, JSONArray groupUserIdArr) ;

    /**
     * 设置用户在群组的身份
     * @param userId 用户Id
     * @param groupUserId 成员Id
     * @param groupId 群组
     * @param groupUserType 组员身份
     * @return
     */
    int setGroupUserType(int userId,int groupUserId,int groupId,int groupUserType);

    /**
     * 获取群组信息
     * @param groupName
     * @return
     */
    List<Group_InfoModel> findGroupByGroupName(String groupName);

    /**
     * 添加聊天记录
     * @param groupId
     * @param content
     * @param fromId
     * @return
     */
    int addRecordGroup(int groupId,String content,int fromId);

    /**
     * 剔除成员或退出群组
     * @param userId
     * @param groupId
     * @return
     */
    int deleteGroupUser(int userId,int groupUserId,int groupId);

    /**
     * 发布群组公告
     * @param userId
     * @param groupNoticeModel
     * @return
     */
    int addGroupNotice(int userId,GroupNoticeModel groupNoticeModel);

    /**
     * 获取群组公布信息
     * @param groupId
     * @return
     */
    List<GroupNoticeModel> getGroupNotice(int groupId);

    /**
     * 删除群组公告
     * @param userId
     * @param noticeId
     * @return
     */
    int deleteGroupNotice(int userId,int noticeId,int groupId);
    /**
     * 群@功能处理
     */
    void specialGroupMessage();

}
