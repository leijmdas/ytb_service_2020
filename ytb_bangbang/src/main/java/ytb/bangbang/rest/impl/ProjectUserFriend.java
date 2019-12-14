package ytb.bangbang.rest.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import ytb.bangbang.model.GroupInfoFriend;
import ytb.bangbang.model.UserFriends;
import ytb.bangbang.service.FriendsInfoService;
import ytb.bangbang.service.impl.FriendsInfoServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * @ClassName ProjectUserFriend
 * @Description deal with project center request
 * @Author qsy
 * @Date 2019/4/17 10:45
 **/
@Component
public class ProjectUserFriend {
    public int retcode = 0;
    public String retmsg = "成功";
    public String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler) {
        FriendsInfoService friendsInfoService = new FriendsInfoServiceImpl();
        if (req.cmd.equals("getUsersGroups")) {
            Integer userId = handler.getUserContext().getLoginSso().getUserId().intValue();
            if (userId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "userId参数不存在!");
            }
            //获取好友list
            List<UserFriends> userFriendsList = friendsInfoService.selectUserFriendsList(userId);
            List<GroupInfoFriend> groupInfoFriendList = friendsInfoService.selectGroupInfoFriendList(userId);
            for(GroupInfoFriend groupInfoFriend : groupInfoFriendList){
                //通过groupId查找好友
                List<UserFriends> userFriendsModel = friendsInfoService.selectUserFriendsByGroupId(groupInfoFriend.getGroupId());
                if(userFriendsModel.size()>0){
                    groupInfoFriend.setUserFriendsModel(userFriendsModel);
                }
            }
            JSONObject r = new JSONObject();
            r.put("friendList",userFriendsList);
            r.put("groupList",groupInfoFriendList);
            msgBody = r.toJSONString();
        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }
}
