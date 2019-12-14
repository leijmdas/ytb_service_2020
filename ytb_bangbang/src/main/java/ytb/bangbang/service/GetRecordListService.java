package ytb.bangbang.service;

import ytb.bangbang.model.OfflineMsgModel;

import java.util.List;

/**
 * 获取聊天记录信息
 */
public interface GetRecordListService {
    /**
     * 聊天记录
     * @param userId
     * @param id
     * @param type
     * @return
     */
    List getRecordList(int userId, int id, String type);

    /**
     * 获取离线消息
     * @param userId
     * @return
     */
    List<OfflineMsgModel> getOfflineMsg(int userId);

    /**
     * 获取离线消息条数
     * @param userId
     * @return
     */
    int getOfflineMsgCount(int userId);

    /**
     *  添加离线消息
     * @param userId
     * @param msgBody
     * @return
     */
    int addOfflineMsg(int userId,String msgBody,int type);

    /**
     * 删除离线消息
     * @param userId
     * @return
     */
    int deleteOfflineMsg(int userId);
}
