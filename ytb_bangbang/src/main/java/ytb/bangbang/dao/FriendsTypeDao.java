package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.FriendsType;

/**
 * tanc
 *
 */

public interface FriendsTypeDao {

    /**
     * 新建好友分组
     * @param friendsType
     * @return
     */
    int addFriendsType(FriendsType friendsType);


    /**
     * 重命名好友分组
     * @param friendsTypeId
     * @return
     */
    int editFriendsType(@Param("friendsTypeId") int friendsTypeId,@Param("groupName") String groupName);

    /**
     * 删除好友分组
     * @param friendsTypeId
     * @return
     */
    int delFriendsType(@Param("friendsTypeId") int friendsTypeId);

    int getFriendsTypeCountsById(@Param("userId") int userId);

    FriendsType getUserIdByType(@Param("friendsTypeId") int friendsTypeId);

    int existFriendsType(@Param("friendsTypeId") int friendsTypeId,@Param("userId") int userId);

}
