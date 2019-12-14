package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.GroupType;

public interface GroupTypeDao {

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
    int delGroupTyp(@Param("groupTypeId") int groupTypeId);

    /**
     * 修改群聊分组
     * @param groupTypeId
     * @return
     */
    int upGroupTyp(@Param("groupTypeId") int groupTypeId,@Param("groupName") String groupName);

    int getGroupTypCounts(@Param("userId") int userId);

    Integer getGroupTypId(@Param("userId") int userId,@Param("groupName") String groupName);

}
