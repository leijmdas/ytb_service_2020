package ytb.bangbang.service;

import ytb.bangbang.model.BBGroupInfo;
import ytb.bangbang.model.BBUserInfo;
import ytb.bangbang.model.UserFriendsGroup;
import ytb.bangbang.model.UserInfo;
import ytb.bangbang.model.bangbang.SearchPersonModel;
import ytb.common.utils.pageutil.PageUtils;
import ytb.user.model.BBUserModel;

import java.util.List;
import java.util.Map;

/**
 * 查询平台好友信息
 */
public interface UserInfoService {

    /**
     * 获取bangbang首页基本信息 (新增备注信息没有返回)
     */
//    Map getBBInfos(int UserID);
    /**
     * 根据条件查询平台所有用户信息
     * @return
     */
    List<BBUserModel> getUserInfoByPage(Map<String,Object> map);

    PageUtils searchPerson(SearchPersonModel searchPersonModel, int pageNo, int limit);


    BBUserInfo getMineInfo(int userId);

    List<BBUserInfo> getFriendList(int userId);

    List<BBGroupInfo> getGroupList(int userId);

    List<UserFriendsGroup> selectUserFriendGroupList(int userId);

    UserInfo getUserInfoById (int inviteId);

    UserInfo getUserInfoByUserId(int userId);

}
