package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.Group_Apply_InfoModel;

import java.util.List;

/**
 * 申请加组
 */
public interface GroupApplyInfoDao {
    /**
     * 添加记录
     * @param group_apply_infoModel
     * @return
     */
   int AddRecord(Group_Apply_InfoModel group_apply_infoModel);

    /**
     * 是否同意
     * @param group_apply_infoModel
     * @return
     */
    int setIsAgree(Group_Apply_InfoModel group_apply_infoModel);

    /**
     * 获取申请信息
     * @param toUserId
     * @return
     */
    List<Group_Apply_InfoModel> GetApplyInfo(@Param("toUserId")int toUserId);

    /**
     * 获取个人申请群组记录
     * @param userId
     * @param groupId
     * @return
     */
    Group_Apply_InfoModel GetUserApplyInfo(@Param("userId")int userId,@Param("groupId")int groupId);
    /**
     * 判断该记录是否存在
     * @param userId
     * @param groupId
     * @return
     */
    int IsExistence(@Param("userId")int userId,@Param("groupId")int groupId);

    /**
     * 删除记录
     * @param userId
     * @param groupId
     * @return
     */
   int DeleteApplyInfo(@Param("userId")int userId,@Param("groupId")int groupId);

    Group_Apply_InfoModel getUserApplyGroupInfo(@Param("inviteId") int inviteId);

    Integer getApplyTypeById(@Param("id") int id);

    int changeIsAgree(@Param("inviteStatus") int inviteStatus,@Param("id") int id);

}
