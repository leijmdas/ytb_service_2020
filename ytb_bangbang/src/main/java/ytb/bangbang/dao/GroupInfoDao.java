package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.*;

import java.util.List;

public interface GroupInfoDao {

    /**
     * 得到用户所加的群组信息
     * @param UserId
     * @return
     */
     List<Group_InfoModel> GetUserGroupInfoList(@Param("userId") int UserId);

    /**
     * 通过groupId获取群组信息
     * @param groupId
     * @return
     */
     Group_InfoModel GetRecord(@Param("groupId") int groupId);

    /**
     * 创建群组
     * @param groupInfoModel
     * @return
     */
     int AddRecord(Group_InfoModel groupInfoModel);

    /**
     * 删除群组
     * @param groupId
     * @return
     */
     int DeleteRecord(@Param("groupId") int groupId);

    /**
     * 群组是否存在
     * @param groupId
     * @return
     */
     int IsExtisGroup(@Param("groupId") int groupId);

    /**
     * 通过群名获取群组
     * @param groupName
     * @return
     */
    List<Group_InfoModel> findGroupByGroupName(@Param("groupName")String groupName);

    List<Group_InfoModel> getUserCreateGroupList(int userId);

    /**
     * 搜群-显示群公告
     */
    List<GroupInfo> findGroupsByName(@Param("groupName")String groupName);

    Group_InfoModel getGroupInfoById(@Param("groupId") int groupId);

    List<Group_InfoModel> getGroupInfoByUserId(@Param("userId") int userId);

    List<SeachGroup> seachGroup(@Param("groupName") String groupName,@Param("groupAddress") String groupAddress,@Param("pageNo") Integer pageNo,@Param("limit") Integer limit);

    Integer getAllCounts(@Param("groupName") String groupName,@Param("groupAddress") String groupAddress);

    /**
     * 申请加群-->查看群资料
     */
    GroupData viewingGroupData(@Param("groupId") int groupId);

    List<GroupInfoModel> getGroupInfoListByUid(@Param("userId") int userId, @Param("type") int type);

    int getGroupTypeById(@Param("groupId") int groupId);

}
