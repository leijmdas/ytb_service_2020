package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.MessageDao;
import ytb.bangbang.model.GroupMessage;
import ytb.bangbang.model.Message;
import ytb.bangbang.service.MessageService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;
import java.util.Map;


public class MessageServiceImpl implements MessageService {

    @Override
    public void addMessage(Message message) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            MessageDao messageDao=session.getMapper(MessageDao.class);
            messageDao.addMessage(message);

        }finally {
            session.close();
        }

    }

    @Override
    public int getMsgCounts(int userId) {

        try(SqlSession session = MyBatisUtil.getSession()) {
            MessageDao messageDao = session.getMapper(MessageDao.class);
            return messageDao.getMsgCounts(userId);

        }

    }

    @Override
    public void setIsRead(int userId, int inviteId,int type) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            MessageDao messageDao = session.getMapper(MessageDao.class);
            messageDao.setIsRead(userId,inviteId,type);

        } finally {
            session.close();
        }
    }

    @Override
    public List<Message> getMsgList(int userId) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            MessageDao messageDao = session.getMapper(MessageDao.class);
            return messageDao.getMsgList(userId);

        } finally {
            session.close();
        }
    }

    @Override
    public void deleteMsg(int messageId) {
        try(SqlSession session = MyBatisUtil.getSession()) {
            MessageDao messageDao = session.getMapper(MessageDao.class);
            messageDao.deleteMsg(messageId);
        }
    }

    @Override
    public int updateStateById(Map<String, Object> map) {
       try(SqlSession session = MyBatisUtil.getSession()) {
           MessageDao messageDao = session.getMapper(MessageDao.class);
           return messageDao.updateStateById(map);
       }
    }

    @Override
    public int changeState(int userId, int inviteId,int type) {
        try(SqlSession session = MyBatisUtil.getSession()) {
            MessageDao messageDao = session.getMapper(MessageDao.class);
            return messageDao.changeState(userId,inviteId,type);
        }
    }

    @Override
    public List<GroupMessage> getGroupMsgList(int userId) {
        try (SqlSession session = MyBatisUtil.getSession()){
            MessageDao messageDao = session.getMapper(MessageDao.class);
            return messageDao.getGroupMsgList(userId);
        }
    }
}
