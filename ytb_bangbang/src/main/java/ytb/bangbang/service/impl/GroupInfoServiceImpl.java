package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.GroupInfoDao;
import ytb.bangbang.model.*;
import ytb.bangbang.service.GroupInfoService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;

public class GroupInfoServiceImpl implements GroupInfoService {
    @Override
    public List<GroupInfo> findGroupsByName(String groupName) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
            return dao.findGroupsByName(groupName);
        }

    }

    @Override
    public boolean IsExtisGroup(Integer groupId) {
        boolean flag=false;
        try (SqlSession session = MyBatisUtil.getSession()) {
            GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
            int count = dao.IsExtisGroup(groupId);
            if (count>=1)
                flag=true;
            return flag;
        }
    }

    @Override
    public Group_InfoModel getGroupInfoById(int groupId) {
        try (SqlSession session = MyBatisUtil.getSession()){
            GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
            return dao.getGroupInfoById(groupId);
        }
    }

    @Override
    public List<Group_InfoModel> getGroupInfoByUserId(int userId) {
       try (SqlSession session = MyBatisUtil.getSession()){
           GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
           return dao.getGroupInfoByUserId(userId);
       }
    }

    @Override
    public int AddRecord(Group_InfoModel groupInfoModel) {
       try (SqlSession session = MyBatisUtil.getSession()){
           GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
           return dao.AddRecord(groupInfoModel);
       }
    }

    @Override
    public List<SeachGroup> seachGroup(String groupName, String groupAddress, Integer pageNo, Integer limit) {
        try (SqlSession session = MyBatisUtil.getSession()){
            GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
            return dao.seachGroup(groupName,groupAddress,pageNo,limit);
        }
    }

    @Override
    public Integer getAllCounts(String groupName, String groupAddress) {
        try (SqlSession session = MyBatisUtil.getSession()){
            GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
            return dao.getAllCounts(groupName,groupAddress);
        }
    }

    @Override
    public GroupData viewingGroupData(int groupId) {
        try (SqlSession session = MyBatisUtil.getSession()){
            GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
            return dao.viewingGroupData(groupId);
        }
    }

    @Override
    public List<GroupInfoModel> getGroupInfoListByUid(int userId, int type) {
        try (SqlSession session = MyBatisUtil.getSession()){
            GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
            return dao.getGroupInfoListByUid(userId,type);
        }
    }

    @Override
    public int getGroupTypeById(int groupId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            GroupInfoDao dao=session.getMapper(GroupInfoDao.class);
            return dao.getGroupTypeById(groupId);
        }
    }
}
