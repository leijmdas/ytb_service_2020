package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.GroupTypeDao;
import ytb.bangbang.model.GroupType;
import ytb.bangbang.service.GroupTypeService;
import ytb.bangbang.util.MyBatisUtil;

public class GroupTypeServiceImpl implements GroupTypeService {

    @Override
    public int addGroupTyp(GroupType groupType) {
        try ( SqlSession session = MyBatisUtil.getSession()){
            GroupTypeDao dao=session.getMapper(GroupTypeDao.class);
            return dao.addGroupTyp(groupType);
        }
    }

    @Override
    public int delGroupTyp(int groupTypeId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            GroupTypeDao dao=session.getMapper(GroupTypeDao.class);
            return  dao.delGroupTyp(groupTypeId);
        }
    }

    @Override
    public int upGroupTyp(int groupTypeId,String groupName) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            GroupTypeDao dao=session.getMapper(GroupTypeDao.class);
            return dao.upGroupTyp(groupTypeId,groupName);
        }
    }

    @Override
    public int getGroupTypCounts(int userId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            GroupTypeDao dao=session.getMapper(GroupTypeDao.class);
            return dao.getGroupTypCounts(userId);
        }
    }

    @Override
    public Integer getGroupTypId(int userId, String groupName) {
        try (SqlSession session = MyBatisUtil.getSession()){
            GroupTypeDao dao=session.getMapper(GroupTypeDao.class);
            return dao.getGroupTypId(userId,groupName);
        }
    }
}
