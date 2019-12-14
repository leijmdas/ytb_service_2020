package ytb.bangbang.service;

import ytb.bangbang.model.GroupType;

public interface GroupTypeService {

    /**
     * 新增群聊分组
     * @param groupType
     * @return
     */
    int addGroupTyp(GroupType groupType);

    /**
     * 删除群聊分组
     * @param groupTypeId
     * @return
     */
    int delGroupTyp(int groupTypeId);

    /**
     * 修改群聊分组
     * @param groupTypeId
     * @return
     */
    int upGroupTyp(int groupTypeId,String groupName);

    int getGroupTypCounts(int userId);

    Integer getGroupTypId(int userId,String groupName);

}
