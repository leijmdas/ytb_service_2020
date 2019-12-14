package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.GroupUserDao;
import ytb.bangbang.model.GroupUser;
import ytb.bangbang.model.GroupUserInfo;
import ytb.bangbang.model.Group_UserModel;
import ytb.bangbang.service.GroupUserService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;

public class GroupUserServiceImpl implements GroupUserService {
    @Override
    public List<Group_UserModel> selGroupById(int userId) {
        try (SqlSession session=MyBatisUtil.getSession()){
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            return dao.selGroupById(userId);
        }
    }

    @Override
    public int findGroupOwnerId(int groupId) {
        try (SqlSession session=MyBatisUtil.getSession()){
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            return dao.findGroupOwnerId(groupId);
        }
    }

    @Override
    public boolean IsExistence(int userId, int groupId) {
        boolean flag=false;
        try (SqlSession session=MyBatisUtil.getSession()){
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            int count = dao.IsExistence(userId,groupId);
            if(count>=1)
                flag=true;
            return flag;
        }

    }

    @Override
    public int addUserToGroup(int userId, int groupId, int groupUserType) {
        try (SqlSession session=MyBatisUtil.getSession()){
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            return dao.addUserToGroup(userId,groupId,groupUserType);
        }
    }

    @Override
    public List<Group_UserModel> getGroupUserByGroupId(int groupId) {
        try (SqlSession session=MyBatisUtil.getSession()){
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            return dao.getGroupUserByGroupId(groupId);
        }
    }

    @Override
    public List<GroupUserInfo> getGroupUsersInfo(int groupId) {
        try (SqlSession session=MyBatisUtil.getSession()){
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            return dao.getGroupUsersInfo(groupId);
        }
    }

    @Override
    public int addRecordu(GroupUser groupUser) {
        try (SqlSession session=MyBatisUtil.getSession()){
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            return dao.addRecordu(groupUser);
        }
    }

    @Override
    public int removeToDefaltType(int userId, int groupTypeId, int defaltTypeId) {
        try (SqlSession session=MyBatisUtil.getSession()){
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            return dao.removeToDefaltType(userId,groupTypeId,defaltTypeId);
        }
    }

    @Override
    public int addMember(GroupUser groupUser) {
        try (SqlSession session=MyBatisUtil.getSession()){
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            return dao.addMember(groupUser);
        }
    }

    @Override
    public Integer GetGroupUserType(int userId, int groupId) {
        try (SqlSession session=MyBatisUtil.getSession()) {
            GroupUserDao dao=session.getMapper(GroupUserDao.class);
            return dao.GetGroupUserType(userId,groupId);
        }
    }
}
