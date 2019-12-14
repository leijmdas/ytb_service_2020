package ytb.bangbang.service;

import ytb.bangbang.model.*;

import java.util.List;

/**
 * tanc
 */

public interface GroupInfoService {
    /**
     * 搜群-显示群公告
     */
    List<GroupInfo> findGroupsByName(String groupName);

    /**
     * 判断群是否存在
     * @param groupId
     * @return
     */
    boolean IsExtisGroup(Integer groupId);

    Group_InfoModel getGroupInfoById(int groupId);

    List<Group_InfoModel> getGroupInfoByUserId(int userId);

    int AddRecord(Group_InfoModel groupInfoModel);

    List<SeachGroup> seachGroup(String groupName,String groupAddress,Integer pageNo,Integer limit);

    Integer getAllCounts(String groupName,String groupAddress);

    GroupData viewingGroupData(int groupId);

    List<GroupInfoModel> getGroupInfoListByUid(int userId,int type);

    int getGroupTypeById(int groupId);
}
