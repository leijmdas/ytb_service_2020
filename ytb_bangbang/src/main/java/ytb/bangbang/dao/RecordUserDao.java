package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.Record_UserModel;

import java.util.List;
import java.util.Map;

public interface RecordUserDao {
    /**
     * 查询用户的聊天记录信息
     * @param UserId
     * @param FriendId
     * @return
     */
     List<Record_UserModel> GetRecordUserList(@Param("userID") int UserId, @Param("friendID") int FriendId);

    /**
     * 添加聊天记录
     * @param userId
     * @param content
     * @param friendId
     * @return
     */
     int AddRecordUser(@Param("userId") int userId,@Param("content") String content ,@Param("friendId") int friendId);

     List<Record_UserModel> getUserRecords(Map<String,Object> map);

    /**
     * 删除聊天记录
     * @param recordId
     * @return
     */
     int delSingFile(@Param("recordId") int recordId);
}
