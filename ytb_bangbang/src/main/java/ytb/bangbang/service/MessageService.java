package ytb.bangbang.service;

import ytb.bangbang.model.GroupMessage;
import ytb.bangbang.model.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {

    void addMessage(Message message);

    int getMsgCounts(int userId);

    void setIsRead(int userId,int inviteId,int type);

    /**
     * 获取好友验证/群系统消息列表接口
     * @param userId
     * @return
     */

    List<Message> getMsgList(int userId);

    void deleteMsg(int messageId);

    int updateStateById(Map<String,Object> map);

    int changeState(int userId,int inviteId,int type);

    List<GroupMessage> getGroupMsgList(int userId);
}
