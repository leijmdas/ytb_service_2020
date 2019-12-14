package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.AnswerDao;
import ytb.bangbang.model.Answer;
import ytb.bangbang.service.AnswerService;
import ytb.bangbang.util.MyBatisUtil;

public class AnswerServiceImpl implements AnswerService {
    @Override
    public int addAnswer(Answer answer) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            AnswerDao answerDao=session.getMapper(AnswerDao.class);
            return answerDao.addAnswer(answer);
        }finally {
            session.close();
        }

    }

    @Override
    public void setIsRead(int inviteId, int toUserId) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            AnswerDao answerDao=session.getMapper(AnswerDao.class);
            answerDao.setIsRead(inviteId,toUserId);
        }finally {
            session.close();
        }
    }
}
