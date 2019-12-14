package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.FriendApplyInfoDao;
import ytb.bangbang.model.Friend_Apply_InfoModel;
import ytb.bangbang.service.FriendApplyInfoService;
import ytb.bangbang.util.MyBatisUtil;

    public class FriendApplyInfoServiceImpl implements FriendApplyInfoService {
        @Override
        public int getApplyId(int userId, int toUserId, int friendsTypeId) {
            SqlSession session = MyBatisUtil.getSession();
            try {
                FriendApplyInfoDao applyInfoDao = session.getMapper(FriendApplyInfoDao.class);
                return applyInfoDao.getApplyId(userId, toUserId, friendsTypeId);
            } finally {
                session.close();
            }

        }

    @Override
    public Friend_Apply_InfoModel getFriendApplyInfoByAppId(int inviteId) {
        SqlSession session= null;
        try {
            session=MyBatisUtil.getSession();
            FriendApplyInfoDao applyInfoDao=session.getMapper(FriendApplyInfoDao.class);
            return applyInfoDao.getFriendApplyInfoByAppId(inviteId);
        }finally {
            session.close();
        }
    }

        @Override
        public Friend_Apply_InfoModel getFriendApplyModel(int inviteId) {
            try (SqlSession session = MyBatisUtil.getSession()){
                FriendApplyInfoDao dao=session.getMapper(FriendApplyInfoDao.class);
                return dao.getFriendApplyModel(inviteId);
            }
        }
    }
