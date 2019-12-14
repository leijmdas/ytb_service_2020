package ytb.bangbang.service.impl;


import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.*;
import ytb.bangbang.model.GroupNoticeModel;
import ytb.bangbang.model.GroupUser;
import ytb.bangbang.model.Group_InfoModel;
import ytb.bangbang.model.Group_UserModel;
import ytb.bangbang.service.GroupInfoService;
import ytb.bangbang.service.GroupService;
import ytb.bangbang.service.GroupUserService;
import ytb.bangbang.util.DictionaryData;
import ytb.bangbang.util.MyBatisUtil;
import ytb.bangbang.util.UserResult;
import ytb.common.ytblog.YtbLog;
import ytb.user.context.UserSrvContext;
import ytb.user.model.UserInfoModel;
import ytb.user.model.UserLoginModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.model.YtbError;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {

    /**
     * 获取群成员信息
     *
     * @param userId
     * @param groupId
     */
    @Override
    public List getGroupUserInfos(int userId, int groupId) {

        List result = new ArrayList();
        try (SqlSession session = MyBatisUtil.getSession()) {
            //数据库连接

            GroupInfoDao groupInfoDao = session.getMapper(GroupInfoDao.class);
            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
            //判断群组是否存在
            int num = groupInfoDao.IsExtisGroup(groupId);
            if (num == 0) {
                return result;
            }
            num = groupUserDao.IsExistence(userId, groupId);
            if (num == 0) {
                return result;
            }
            Group_InfoModel groupInfoModel = groupInfoDao.GetRecord(groupId);
            return new UserResult().success(groupInfoModel);

        }
    }

    /**
     * 获取群组成员
     *
     * @param groupId
     * @return
     */
    public List getGroupUserId(int groupId) {

        List<Integer> userIdList = new ArrayList<>();
        try (SqlSession session = MyBatisUtil.getSession()) {
            //数据库连接
            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
            List<Group_UserModel> groupUserModelList = groupUserDao.getGroupUserByGroupId(groupId);
            for (Group_UserModel groupUserModel : groupUserModelList) {
                userIdList.add(groupUserModel.getUserId());
            }
            return userIdList;
        }
    }

    /**
     * 新建组
     *
     * @param groupName 群组名称
     * @param groupType 群组类型
     * @param userID
     * @return
     */
    @Override
    public int addGroup(String groupName, int groupType, int userID) {

//        SqlSession session = MyBatisUtil.getSession();
//        try {
//            //数据库连接
//            GroupInfoDao groupInfoDao = session.getMapper(GroupInfoDao.class);
//            //新建群组
//            Group_InfoModel groupInfoModel = new Group_InfoModel();
//            groupInfoModel.setGroupName(groupName);
//            groupInfoModel.setGroupType(groupType);
//            groupInfoDao.AddRecord(groupInfoModel);
//            //添加群管理员
//            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
//            groupUserDao.AddRecord(userID, groupInfoModel.getGroup_id());
//            groupUserDao.SetUserType(userID, groupInfoModel.getGroup_id(), DictionaryData.GROUPOWER);
//            session.commit();
//            session.close();
//            int groupId = groupInfoModel.getGroup_id();
//            return groupId;
//        } catch (Exception e) {
//            session.rollback();
//            throw new YtbError(YtbError.CODE_FAIL,e.getMessage());
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
        GroupInfoService groupInfoService = new GroupInfoServiceImpl();
        Group_InfoModel groupInfoModel = new Group_InfoModel();
        groupInfoModel.setGroupName(groupName);
        groupInfoModel.setGroupType(groupType);
        groupInfoService.AddRecord(groupInfoModel);
        Integer groupId = groupInfoModel.getGroup_id();

        GroupUserService groupUserService = new GroupUserServiceImpl();
        GroupUser groupUserModel = new GroupUser();
        groupUserModel.setUserId(userID);
        groupUserModel.setGroupId(groupId);
        groupUserModel.setGroupUserType(1);
        groupUserModel.setGroupTypeId(groupType);
        groupUserService.addRecordu(groupUserModel);

        return groupId;
    }

    /**
     * 删除组
     *
     * @param userId
     * @param groupId
     */
    @Override
    public int deleteGroup(int userId, int groupId) {

        try (SqlSession session = MyBatisUtil.getSession()) {
            //数据库连接
            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
            GroupInfoDao groupInfoDao = session.getMapper(GroupInfoDao.class);
            int type = groupUserDao.GetGroupUserType(userId, groupId);
            if (type != DictionaryData.GROUPOWER) {
                //没权限
                return 6;
            }
            int row = groupInfoDao.DeleteRecord(groupId);
            if (row <= 0) {
                return 1;
            }
            row = groupUserDao.DeleteRecord(groupId);
            if (row <= 0) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 添加群组组成员
     *
     * @param userId
     * @param groupId
     * @param groupUserIdArr
     */
    @Override
    public int addGroupUser(int userId, int groupId, List<Integer> groupUserIdArr) {
        YtbLog.logDebug("start addGroupUser userId  " + userId + " start groupId   " + groupId);
        UserCenterService loginService = UserSrvContext.getInst().getUserCenterService() ;

        try (SqlSession session = MyBatisUtil.getSession()){
            //数据库连接
            GroupInfoDao groupInfoDao = session.getMapper(GroupInfoDao.class);
            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
            int num = groupUserDao.IsExistence(userId, groupId);
            if (num == 0) {   //用户不所于该群组
                session.rollback();
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "用户不属于该群组!");

            }
            int type = groupUserDao.GetGroupUserType(userId, groupId);
            if (type > DictionaryData.GROUPADMIN) {
                //没权限
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你没有权限添加群员!");
            }
            num = groupInfoDao.IsExtisGroup(groupId);
            if (num == 0) {
                //群组不存在
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "群组不存在!");
            }

            GroupUserService groupUserService = new GroupUserServiceImpl();
            GroupInfoService groupInfoService = new GroupInfoServiceImpl();
            Integer groupType = groupInfoService.getGroupTypeById(groupId);
            for (Integer  groupUserId : groupUserIdArr) {
                GroupUser groupUserModel = new GroupUser();
                groupUserModel.setUserId(groupUserId);
                groupUserModel.setGroupId(groupId);
                groupUserModel.setGroupUserType(3);
                groupUserModel.setGroupTypeId(groupType);
                groupUserService.addRecordu(groupUserModel);
            }
        }
        YtbLog.logDebug("end addGroupUser");
        return 0;
    }

    /**
     * 新增工作群组成员信息
     *
     * @param groupId        群组Id
     * @param groupUserIdArr 群组成员Id集合
     * @param userId         用户id（创建者）
     */
    @Override
    public int addWorkGroupUser(int userId, int groupId, JSONArray groupUserIdArr) {

        SqlSession session = null;
        try {
            //数据库连接
            session = MyBatisUtil.getSession();
            GroupInfoDao groupInfoDao = session.getMapper(GroupInfoDao.class);
            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
            UserFriendsDao userFriendsDao = session.getMapper(UserFriendsDao.class);
            UserCenterService loginService = new UserCenterServiceImpl();
            //用户权限判断
            int type = groupUserDao.GetGroupUserType(userId, groupId);
            int num = groupInfoDao.IsExtisGroup(groupId);
            if (type > DictionaryData.GROUPADMIN) {
                //没权限
                return 6;
            }
            if (num == 0) {
                //群组不存在
                return 8;
            }
            int groupUserIdA = 0;
            int groupUserIdB = 0;
            UserInfoModel userLoginModel = null;
            for (int i = 0; i < groupUserIdArr.size(); i++) {
                groupUserIdA = groupUserIdArr.getInteger(i);
                groupUserDao.AddRecord(groupUserIdA, groupId);
                for (int j = i + 1; j < groupUserIdArr.size(); j++) {
                    //非好友时就添加为好友
                    if (userFriendsDao.IsFriend(groupUserIdA, groupUserIdB) == 0) {
                        groupUserIdB = groupUserIdArr.getInteger(j);
                        userLoginModel = loginService.getUserInfoById(groupUserIdB);
//                        userFriendsDao.AddRecord(groupUserIdA, groupUserIdB, userLoginModel.getNickName());
                        userLoginModel = loginService.getUserInfoById(groupUserIdA);
//                        userFriendsDao.AddRecord(groupUserIdB, groupUserIdA, userLoginModel.getNickName());
                    }
                }
            }
            session.commit();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 10;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return 1;
        } finally {
            if (session != null)
                session.close();
        }
        return 0;
    }

    /**
     * 设置用户在群组的身份
     *
     * @param userId        用户Id
     * @param groupUserId   成员Id
     * @param groupId       群组
     * @param groupUserType 组员身份
     * @return
     */
    @Override
    public int setGroupUserType(int userId, int groupUserId, int groupId, int groupUserType) {
        SqlSession session = null;
        try {
            //数据库连接
            session = MyBatisUtil.getSession();
            //判断群组是否存在
            GroupInfoDao groupInfoDao = session.getMapper(GroupInfoDao.class);
            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
            int num = groupInfoDao.IsExtisGroup(groupId);
            if (num == 0) {
                //群组不存在
                return 8;
            }

            //判断该成员用户是否注册
            UserCenterService userLoginService = new UserCenterServiceImpl();
            UserLoginModel mineUser = userLoginService.getUserLoginInfoById(groupUserId);
            if (null == mineUser) {
                return 9;//用户不存在
            }

            //判断当前用户是否在该群组中
            num = groupUserDao.IsExistence(userId, groupId);
            if (num == 0) {
                return 7;
            }
            //用户权限判断
            int type = groupUserDao.GetGroupUserType(userId, groupId);
            if (type == DictionaryData.GROUPOWER) {
                groupUserDao.SetUserType(groupUserId, groupId, groupUserType);
            } else {
                //没权限
                return 6;
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            return 10;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return 1;
        } finally {
            if (session != null)
                session.close();
        }
        return 0;
    }

    /**
     * 搜索群组
     *
     * @param groupName
     * @return
     */
    @Override
    public List<Group_InfoModel> findGroupByGroupName(String groupName) {
        SqlSession session = null;
        List<Group_InfoModel> result = new ArrayList();
        try {
            session = MyBatisUtil.getSession();
            GroupInfoDao groupInfoDao = session.getMapper(GroupInfoDao.class);
            result = groupInfoDao.findGroupByGroupName(groupName);
            session.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * 添加聊天记录
     *
     * @param groupId
     * @param content
     * @param fromId
     * @return
     */
    public int addRecordGroup(int groupId, String content, int fromId) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            RecodGroupDao recodGroupDao = session.getMapper(RecodGroupDao.class);
            int row = recodGroupDao.AddRecordGroup(groupId, content, fromId);
            if (row == 0) {
                session.rollback();
                return 1;
            }
            session.commit();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * 剔除成员或退出群组
     *
     * @param userId
     * @param groupUserId
     * @param groupId
     * @return
     */
    @Override
    public int deleteGroupUser(int userId, int groupUserId, int groupId) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
            int row = -1;
            if (userId == groupUserId) {
                row = groupUserDao.DeleteUser(groupId, groupUserId);
            } else {
                int type = groupUserDao.GetGroupUserType(userId, groupId);
                if (type > DictionaryData.GROUPADMIN) {
                    return 6;//没权限
                } else {
                    row = groupUserDao.DeleteUser(groupId, groupUserId);
                }
            }
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * 发布群组公告
     *
     * @param userId
     * @param groupNoticeModel
     * @return
     */
    @Override
    public int addGroupNotice(int userId, GroupNoticeModel groupNoticeModel) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            GroupNoticeDao groupNoticeDao = session.getMapper(GroupNoticeDao.class);
            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
            Integer type = groupUserDao.GetGroupUserType(userId, groupNoticeModel.getGroupId());
            if (type == null) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            }
            if (type != DictionaryData.GROUPOWER) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "你不是群主，无法添加公告");
            }
            int row = groupNoticeDao.addGroupNotice(groupNoticeModel);
            if (row == 0) {
                session.rollback();
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "添加失败");
            }
            session.commit();
            return groupNoticeModel.getNoticeId();
        } catch (Exception ex) {
            if (session != null) {
                session.rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * 获取群组公布信息
     *
     * @param groupId
     * @return
     */
    @Override
    public List<GroupNoticeModel> getGroupNotice(int groupId) {
        SqlSession session = null;
        List<GroupNoticeModel> groupNoticeModelList = new ArrayList<>();
        try {
            session = MyBatisUtil.getSession();
            GroupNoticeDao groupNoticeDao = session.getMapper(GroupNoticeDao.class);
            groupNoticeModelList = groupNoticeDao.getGroupNotice(groupId);
            return groupNoticeModelList;
        } catch (Exception e) {
            e.printStackTrace();
            return groupNoticeModelList;
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * 删除群组公告
     *
     * @param userId
     * @param noticeId
     * @param groupId
     * @return
     */
    @Override
    public int deleteGroupNotice(int userId, int noticeId, int groupId) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            GroupNoticeDao groupNoticeDao = session.getMapper(GroupNoticeDao.class);
            GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
            int type = groupUserDao.GetGroupUserType(userId, groupId);
            if (type != DictionaryData.GROUPOWER) {
                return 6;//没权限
            }
            int row = groupNoticeDao.deleteGroupNotice(noticeId);
            if (row == 0) {
                session.rollback();
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        } finally {
            if (session != null)
                session.close();
        }
        return 0;
    }

    /**
     * 群@功能处理
     */
    @Override
    public void specialGroupMessage() {


    }
}
