package ytb.manager.sysuser.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.sysuser.dao.SysUserMapper;
import ytb.manager.sysuser.model.SysUserModel;

import java.util.List;
import java.util.Map;

public class SysUserDAOService implements SysUserMapper {
    @Override
    public List<SysUserModel> getSysUserList(Map<String, Object> map) {

        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_manager(true)) {
            SysUserMapper userDao = ss.getMapper(SysUserMapper.class);
            return userDao.getSysUserList(map);
        }
    }

    @Override
    public void addUserInfo(SysUserModel sysUserModel) {

        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_manager(true)) {
            SysUserMapper userDao = ss.getMapper(SysUserMapper.class);
            userDao.addUserInfo(sysUserModel);
            ss.commit();
        }
    }

    @Override
    public void deleteUserbyId(int userId) {
        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_manager(true)) {
            SysUserMapper userDao = ss.getMapper(SysUserMapper.class);
            userDao.deleteUserbyId(userId);
            ss.commit();
        }
    }

    @Override
    public void updateSysUserInfo(SysUserModel sysUserModel) {
        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_manager(true)) {
            SysUserMapper userDao = ss.getMapper(SysUserMapper.class);
            userDao.updateSysUserInfo(sysUserModel);
            ss.commit();
        }
    }

    @Override
    public SysUserModel getSysUserInfoById(int userId) {

        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_manager(true)) {
            SysUserMapper userDao = ss.getMapper(SysUserMapper.class);
            return userDao.getSysUserInfoById(userId);
        }
    }

    @Override
    public void updatePassword(String newPwd, int userId) {

    }



    @Override
    public void updateStatus(Map<String, Object> map) {
        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_manager(true)) {
            SysUserMapper userDao = ss.getMapper(SysUserMapper.class);
            userDao.updateStatus(map);
            ss.commit();
        }
    }

    @Override
    public List<Integer> getUserIdByRoleId(int roleId) {
        return null;
    }

    @Override
    public SysUserModel getUserByUserName(String userName) {
        return null;
    }

    @Override
    public SysUserModel getUserByMobile(String mobile) {
        return null;
    }

    SysUserModel getUserByUserNameModel(String userName) {

        try (SqlSession sqlSession = YtbContext.getSqlSessionBuilder().getSession_manager(true)) {
            SysUserMapper userDao = sqlSession.getMapper(SysUserMapper.class);
            return userDao.getUserByUserName(userName);
        }
    }

    SysUserModel getUserByMobileModel(String mobile) {

        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_manager(true)) {
            SysUserMapper userDao = ss.getMapper(SysUserMapper.class);
            return userDao.getUserByMobile(mobile);
        }
    }

}
