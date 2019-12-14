package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.OfflineMsgDao;
import ytb.bangbang.dao.RecodGroupDao;
import ytb.bangbang.dao.RecordUserDao;
import ytb.bangbang.model.OfflineMsgModel;
import ytb.bangbang.model.Record_GroupModel;
import ytb.bangbang.model.Record_UserModel;
import ytb.bangbang.service.GetRecordListService;
import ytb.bangbang.util.*;
import ytb.user.model.UserInfoModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取聊天记录信息
 */
public class GetRecordListServiceImpl implements GetRecordListService {

    @Override
    public List getRecordList(int userId, int id, String type) {
        //获取数据库连接
        if (type == null || type.isEmpty()) {
            throw new RuntimeException("type为空");
        }
        List<RecordInfo> result = new ArrayList<>();
        try(SqlSession session = MyBatisUtil.getSession()) {

            RecordUserDao recordUserDao = session.getMapper(RecordUserDao.class);
            UserCenterService userLoginService = new UserCenterServiceImpl();
            //用户的信息
            UserInfoModel userLoginModel = userLoginService.getUserInfoById(userId);
            //好友的信息
            UserInfoModel frinedLoginModel = userLoginService.getUserInfoById(id);

            if (type.equals("friend")) {
                List<Record_UserModel> recordUserList = recordUserDao.GetRecordUserList(userId, id);
                List<Record_UserModel> recordFriendList = recordUserDao.GetRecordUserList(id, userId);
                for (Record_UserModel recordUser : recordUserList) {
                    setRecordInfo(result, userLoginModel, recordUser.getCreateTime(), recordUser.getContent());
                }
                for (Record_UserModel recordFrined : recordFriendList) {
                    setRecordInfo(result, frinedLoginModel, recordFrined.getCreateTime(), recordFrined.getContent());
                }

            } else if (type.equals("group")) {
                //群组聊天记录
                RecodGroupDao recodGroupDao = session.getMapper(RecodGroupDao.class);
                List<Record_GroupModel> recordGroupList = recodGroupDao.GetRecordGroupList(id);
                for (Record_GroupModel groupModel : recordGroupList) {

                    UserInfoModel userInfoModel = userLoginService.getUserInfoById(groupModel.getFromUser());
                    setRecordInfo(result, userInfoModel, groupModel.getCreateTime(), groupModel.getContent());
                }

            }
            return result;
        }
    }

    /**
     * 获取离线消息
     *
     * @param userId
     * @return
     */
    @Override
    public List<OfflineMsgModel> getOfflineMsg(int userId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            OfflineMsgDao offlineMsgDao = session.getMapper(OfflineMsgDao.class);
            return offlineMsgDao.GetRecordUserList(userId);
        }
    }

    @Override
    public int getOfflineMsgCount(int userId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            OfflineMsgDao offlineMsgDao = session.getMapper(OfflineMsgDao.class);
            return offlineMsgDao.getOfflineMsgCount(userId);
        }
    }

    /**
     * 添加离线消息
     *
     * @param userId
     * @param msgBody
     * @return
     */
    @Override
    public int addOfflineMsg(int userId, String msgBody,int type) {

        try(SqlSession session = MyBatisUtil.getSession()) {
            OfflineMsgDao offlineMsgDao = session.getMapper(OfflineMsgDao.class);
            return offlineMsgDao.AddOfflineMsg(userId, msgBody,type);

        }
    }

    /**
     * 删除离线消息
     *
     * @param userId
     * @return
     */
    @Override
    public int deleteOfflineMsg(int userId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            OfflineMsgDao offlineMsgDao = session.getMapper(OfflineMsgDao.class);
            return offlineMsgDao.DeleteOfflineMsg(userId);

        }
    }

    private void setRecordInfo(List<RecordInfo> result, UserInfoModel userInfoModel, String create_time, String content) {
        if (null == userInfoModel) {
            return;
        }
        RecordInfo recordInfo = new RecordInfo();
        recordInfo.username = userInfoModel.getNickName();
        recordInfo.id = userInfoModel.getUserId();
        recordInfo.userHead = userInfoModel.getUserHead();
        recordInfo.timestamp = create_time;
        recordInfo.content = content;
        result.add(recordInfo);
    }

    private class RecordInfo {

        public String username = "";
        public int id = 0;
        public String userHead = "";
        public String timestamp = null;
        public String content = "";
    }
}
