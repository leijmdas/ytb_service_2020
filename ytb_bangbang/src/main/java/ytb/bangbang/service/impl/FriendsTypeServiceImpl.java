package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.FriendsTypeDao;
import ytb.bangbang.model.FriendsType;
import ytb.bangbang.service.FriendsTypeService;
import ytb.bangbang.util.MyBatisUtil;

/**
 * tanc
 */
public class FriendsTypeServiceImpl implements FriendsTypeService {

    @Override
    public void addFriendsType(FriendsType friendsType) {
        try(SqlSession session = MyBatisUtil.getSession()) {
            FriendsTypeDao friendsTypeDao=session.getMapper(FriendsTypeDao.class);
            friendsTypeDao.addFriendsType(friendsType);
        }
    }

    @Override
    public void editFriendsType(int friendsTypeId, String groupName) {
        try (SqlSession session = MyBatisUtil.getSession()){
            FriendsTypeDao friendsTypeDao=session.getMapper(FriendsTypeDao.class);
            friendsTypeDao.editFriendsType(friendsTypeId,groupName);
        }

    }

    @Override
    public void delFriendsType(int friendsTypeId) {
        try(SqlSession session = MyBatisUtil.getSession()) {
            FriendsTypeDao friendsTypeDao=session.getMapper(FriendsTypeDao.class);
            friendsTypeDao.delFriendsType(friendsTypeId);
        }

    }

    @Override
    public int getFriendsTypeCountsById(int userId) {
        try (SqlSession session = MyBatisUtil.getSession()){
            FriendsTypeDao friendsTypeDao=session.getMapper(FriendsTypeDao.class);
            return  friendsTypeDao.getFriendsTypeCountsById(userId);
        }
    }

    @Override
    public FriendsType getUserIdByType(int friendsTypeId) {
       try (SqlSession session = MyBatisUtil.getSession()){
           FriendsTypeDao friendsTypeDao=session.getMapper(FriendsTypeDao.class);
           return friendsTypeDao.getUserIdByType(friendsTypeId);
       }
    }

    @Override
    public int existFriendsType(int friendsTypeId, int userId) {
        try (SqlSession session = MyBatisUtil.getSession()){
            FriendsTypeDao friendsTypeDao=session.getMapper(FriendsTypeDao.class);
            return friendsTypeDao.existFriendsType(friendsTypeId,userId);
        }
    }
}
