package ytb.bangbang.util;


import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.GroupUserDao;
import ytb.bangbang.dao.UserFriendsDao;
import ytb.bangbang.model.FriendsInfoModel;
import ytb.bangbang.model.Group_InfoModel;
import ytb.bangbang.model.Group_UserModel;
import ytb.user.model.UserInfoModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResult {

    /**
     * 输出成功信息
     *
     * @param
     */
    public Map success(UserInfoModel userInfo, List<FriendsInfoModel> userFriendsModelList, List<Group_InfoModel> group_infoModelList) {

        //组装我们需要的信息，并返回
        Map result = new HashMap();
        UserInfo mine = new UserInfo();
        mine.nickName = userInfo.getNickName();
        mine.getInfo(userInfo);
        result.put("mine", mine);
        result.put("friend", getFriendsList(userFriendsModelList));
        result.put("group", getGroupList(group_infoModelList));
        return result;
    }

    /**
     * 输出成功信息
     *
     * @param
     */
    public List success(Group_InfoModel groupInfo) {
        //组装我们需要的信息，并返回
        return getGroupUsers(groupInfo);
    }


    //获取群组成员信息
    private List getGroupUsers(Group_InfoModel groupInfo) {
        //得到组员ID
        SqlSession session = MyBatisUtil.getSession();
        GroupUserDao groupUserDao = session.getMapper(GroupUserDao.class);
        //获取成员信息
        List<Group_UserModel> groupUserModelList = groupUserDao.getGroupUserByGroupId(groupInfo.getGroup_id());
        UserCenterService userLoginService = new UserCenterServiceImpl();
        UserInfoModel mineUser = null;
        List groupUserList = new ArrayList<>();
        for (Group_UserModel group_userModel : groupUserModelList) {
            mineUser = userLoginService.getUserInfoById(group_userModel.getUserId());
            groupUserList.add(new UserInfo().GetGroupUsers(mineUser));
        }
        session.close();
        return groupUserList;
    }


    //获取好友列表的对象信息
    private Map getFriendsList(List<FriendsInfoModel> userFriendsModelList) {
        //获取好友记录信息
        SqlSession session = MyBatisUtil.getSession();
        List<Object> friendList = new ArrayList<>();
        UserCenterService userLoginService = new UserCenterServiceImpl();
        UserFriendsDao userFriendsDao = session.getMapper(UserFriendsDao.class);
        UserInfoModel friendInfo;
        for (FriendsInfoModel frendsInfoModel : userFriendsModelList) {
            friendInfo = userLoginService.getUserInfoById(frendsInfoModel.getFriendId());
            if (friendInfo != null) {
                friendInfo.setNickName(frendsInfoModel.getRemarks());
                friendList.add(new UserInfo().GetFriendInfo(friendInfo));
            }
        }
        Map result = Maps.newHashMap();
        result.put("groupname", "我的好友");
        result.put("list", friendList);
        return result;
    }

    //获取群组列表信息
    private List<Object> getGroupList(List<Group_InfoModel> groupInfoList) {
        //获取群信息
        List<Object> result = new ArrayList<>();
        for (Group_InfoModel groupInfo : groupInfoList) {
            result.add(new GroupInfo().getGroupInfo(groupInfo));
        }
        return result;
    }


    //群组信息
    class GroupInfo {
        public String groupname = "";
        public String id = "";
        public String groupIcon = "";

        //群组信息
        public Object getGroupInfo(Group_InfoModel groupInfo) {
            if (null == groupInfo) {
                System.out.println("群组信息:  为空");
                return null;
            }
            this.id = "" + groupInfo.getGroup_id();
            this.groupname = groupInfo.getGroupName();
            this.groupIcon = groupInfo.getGroupIcon();
            return this;
        }
    }


    //用户信息
    private class UserInfo {
        public int id = 0;
        public String nickName = "";
        public String remark = "";
        public String status = "online";            //在线状态 online：在线、hide：隐身
        public String sign = "";
        public String userHead = "";


        //用户信息
        public Object GetUserInfo(UserInfoModel userInfo) {
            if (null == userInfo) {
                System.out.println("用户个人信息:  为空");
                return new String("{}");
            }
            System.out.println("用户个人信息: " + userInfo.getUserHead());
            return getInfo(userInfo);
        }


        //好友信息
        public Object GetFriendInfo(UserInfoModel userInfo) {
            if (null == userInfo) {
                System.out.println("用户好友信息:  为空");
                return new String("{}");
            }
            System.out.println("用户好友信息: " + userInfo.getUserHead());
            return getInfo(userInfo);
        }

        //群成员信息
        public Object GetGroupUsers(UserInfoModel userInfo) {
            if (null == userInfo) {
                System.out.println("群用户信息:  为空");
                return new String("{}");
            }
            //System.out.println("群用户信息: " + userInfo.getUserHead());
            return getInfo(userInfo);
        }

        private Object getInfo(UserInfoModel userInfo) {
            if (null == userInfo) {
                System.out.println("信息:  为空");
                return new String("{}");
            }
            this.id = userInfo.getUserId();
            this.remark = userInfo.getNickName();
            this.status = userInfo.getIsOnline() ? "online" : "hide";
            this.sign = userInfo.getUserSign();
            this.userHead = userInfo.getUserHead();
            return this;
        }

    }
}
