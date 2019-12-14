package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.OfflineMsgModel;

import java.util.List;

/**
 * 用户离线消息
 */
public interface OfflineMsgDao {
    /**
     * 获取离线消息
     * @param userId
     * @return
     */
    List<OfflineMsgModel> GetRecordUserList(@Param("userId") int userId);

    /**
     * 添加离线消息
     * @param userId
     * @param msgBody
     * @return
     */
    int AddOfflineMsg(@Param("userId") int userId,@Param("msgBody") String msgBody,@Param("type") int type);

    /**
     * 删除离线消息
     * @param userId
     * @return
     */
    int DeleteOfflineMsg(@Param("userId") int userId);

    /**
     * 获取离线消息条数
     */
    int getOfflineMsgCount(@Param("userId") int userId);
}
