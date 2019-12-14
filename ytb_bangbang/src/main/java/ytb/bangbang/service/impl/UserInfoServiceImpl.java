package ytb.bangbang.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.GroupInfoDao;
import ytb.bangbang.dao.UserFriendsDao;
import ytb.bangbang.dao.UserInfoDao;
import ytb.bangbang.model.*;
import ytb.bangbang.model.bangbang.SearchPersonModel;
import ytb.bangbang.service.UserInfoService;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.user.model.BBUserModel;
import ytb.user.model.UserInfoModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.service.impl.YtbContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author hj
 * @Description //TODO
 * @Date 2018/10/12
 **/
public class UserInfoServiceImpl implements UserInfoService {

    private UserCenterService userCenterService = new UserCenterServiceImpl();

    private SqlSession getBBSession() {
        return YtbContext.getSqlSessionBuilder().getSession_bangbang(true);
    }

    /**
     * 获取邦邦用户首页基本信息 (新增备注信息没有返回)
     *
     * @param userID
     * @return
     */
    /*@Override
    public Map getBBInfos(int userID) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            UserCenterService userLoginService = new UserCenterServiceImpl();
            UserInfoModel mineUser = userLoginService.getUserInfoById(userID);

            //需要得到好友列表
            UserFriendsDao userFriendsDao = session.getMapper(UserFriendsDao.class);
            List<FriendsInfoModel> userFriendsModelList = userFriendsDao.GetUserFriend(userID, null);
            //需要得到群组信息
            GroupInfoDao groupInfoDao = session.getMapper(GroupInfoDao.class);
            List<Group_InfoModel> group_infoModelList = groupInfoDao.GetUserGroupInfoList(userID);
            //返回结果
            return new UserResult().success(mineUser, userFriendsModelList, group_infoModelList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null)
                session.close();
        }

    }*/

    /**
     * 根据条件查询平台所有用户信息
     *
     * @param map
     * @return
     */
    @Override
    public List<BBUserModel> getUserInfoByPage(Map<String, Object> map) {
        try {
            UserCenterService userCenterService = new UserCenterServiceImpl();
            List<BBUserModel> list = userCenterService.getUserToBBNoPage(map);
            //tagName":"WIFI模块,电路设计
            List<BBUserModel> returnList = new ArrayList<>();

            String[] str = null;
            for (BBUserModel bbUserModel : list) {
                BBUserModel bb = new BBUserModel();
                if (null != bbUserModel.getTagName() && !"".equals(bbUserModel.getTagName())) {
                    str = bbUserModel.getTagName().split(",");
                }

                bb.setTagNameArr(str);

                bb.setUserAddress(bbUserModel.getUserAddress());
                bb.setNickName(bbUserModel.getNickName());
                bb.setCompanyUserId(bbUserModel.getCompanyUserId());
                bb.setSex(bbUserModel.getSex());
                bb.setLoginMobile(bbUserModel.getLoginMobile());
                bb.setUserAge(bbUserModel.getUserAge());
                bb.setUserHead(bbUserModel.getUserHead());
                if (bbUserModel.getCreditGread() != null) {
                    bb.setCreditGread(bbUserModel.getCreditGread());
                } else {
                    bb.setCreditGread("B");
                }
                bb.setUserId(bbUserModel.getUserId());
                bb.setRegisteredTime(bbUserModel.getRegisteredTime());
                bb.setUserSigin(bbUserModel.getUserSigin());
                bb.setUserId(bbUserModel.getUserId());
                returnList.add(bb);
            }
            return returnList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageUtils searchPerson(SearchPersonModel searchPersonModel, int pageNo, int limit) {

        //接单范围
        UserCenterService service = new UserCenterServiceImpl();

        /*params.put("nickName", searchPersonModel.getNickName());//昵称
        params.put("userGread", searchPersonModel.getUserGread());//信用等级
        params.put("level", searchPersonModel.getLevel());//学历
        params.put("tagId", searchPersonModel.getTagId());//专业能力标签
        params.put("typeId", searchPersonModel.getTypeId());//项目类别
        params.put("userAddress", searchPersonModel.getUserAddress());//用户地址*/


        Map<String, Object> params = (Map<String, Object>) JSON.toJSON(searchPersonModel);

        params.put("page", pageNo);
        params.put("limit", limit);
//////////////////////////////////////////////////////////////////////////////////
        Query query = new Query(params);
        List<Map<String, Object>> list = service.getUserListToBB(query);

        int count = service.getUserListToBBCount(query);
        return new PageUtils(list, count, query.getLimit(), query.getPage());
    }

    @Override
    public BBUserInfo getMineInfo(int userId) {
        UserInfoModel userInfoModel = userCenterService.getUserInfoById(userId);
        BBUserInfo bbUserInfo = new BBUserInfo();
        bbUserInfo.parseToUserInfo(userInfoModel);
        return bbUserInfo;
    }

    @Override
    public List<BBUserInfo> getFriendList(int userId) {
        try (SqlSession session = getBBSession()) {
            UserFriendsDao mapper = session.getMapper(UserFriendsDao.class);
            return mapper.selectUserFriendList(userId);
        }
    }

    @Override
    public List<UserFriendsGroup> selectUserFriendGroupList(int userId) {
        try (SqlSession session = getBBSession()) {
            UserFriendsDao mapper = session.getMapper(UserFriendsDao.class);
            return mapper.selectUserFriendGroupList(userId);
        }
    }

    @Override
    public UserInfo getUserInfoById(int inviteId) {
        try (SqlSession session = getBBSession()){
            UserInfoDao dao=session.getMapper(UserInfoDao.class);
            return  dao.getUserInfoById(inviteId);
        }
    }

    @Override
    public UserInfo getUserInfoByUserId(int userId) {
        try (SqlSession session = getBBSession()){
            UserInfoDao dao=session.getMapper(UserInfoDao.class);
            return  dao.getUserInfoByUserId(userId);
        }
    }

    @Override
    public List<BBGroupInfo> getGroupList(int userId) {
        try (SqlSession session = getBBSession()) {
            GroupInfoDao groupInfoDao = session.getMapper(GroupInfoDao.class);
            List<Group_InfoModel> groupInfoModels = groupInfoDao.getUserCreateGroupList(userId);
            List<BBGroupInfo> list = new ArrayList<>();
            for (Group_InfoModel group : groupInfoModels) {
                list.add(new BBGroupInfo().parseToBBGroupInfo(group));
            }
            return list;
        }
    }
}
