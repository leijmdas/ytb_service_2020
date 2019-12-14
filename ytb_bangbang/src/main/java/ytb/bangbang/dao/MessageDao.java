package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.GroupMessage;
import ytb.bangbang.model.Message;

import java.util.List;
import java.util.Map;

public interface MessageDao {
   int addMessage(Message message);

   int getMsgCounts(@Param("userId")int userId);

   int setIsRead(@Param("userId")int userId,@Param("inviteId")int inviteId,@Param("type") int type);

   List<Message> getMsgList(@Param("userId") int userId);

   int deleteMsg(@Param("messageId") int messageId);

   int updateStateById(Map<String,Object> map);

   int changeState(@Param("userId")int userId,@Param("inviteId")int inviteId,@Param("type") int type);

   List<GroupMessage> getGroupMsgList(@Param("userId") int userId);
}
