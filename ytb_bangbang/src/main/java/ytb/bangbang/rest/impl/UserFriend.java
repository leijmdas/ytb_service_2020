package ytb.bangbang.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Component;
import ytb.bangbang.model.*;
import ytb.bangbang.rest.ChatWebSocket;
import ytb.bangbang.service.*;
import ytb.bangbang.service.impl.*;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hj
 * @Description //用户好友管理
 * @Date 2018/9/4
 **/
@Component
public class UserFriend {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler) {
        FriendsInfoService friendsInfoService = new FriendsInfoServiceImpl();
        if (req.cmd.equals("GetApplyUserFriend")) {
            // 获取申请好友记录
            UserFriendService service = new UserFriendServiceImpl();
            int toUserId = req.msgBody.getInteger("toUserId");
            // int toUserId =userId;
            //好友申请七天过期
            service.FriendsApplyIgnore(toUserId);
            //获取记录
            List<Object> list = service.GetApplyUserFriend(toUserId);
            msgBody = "{\"friendsInfo\":" + JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue) + "}";
        } else if (req.cmd.equals("applyUserFriend")) {
            //申请添加好友
            UserFriendService service = new UserFriendServiceImpl();
            int userId = req.msgBody.getInteger("userId");
            int friendId = req.msgBody.getInteger("toUserId");
            int friendsTypeId = req.msgBody.getInteger("friendsTypeId");
            retcode = service.applyAddFriend(userId, friendId, friendsTypeId);
            if (retcode > 0) {
                retmsg = "失败";
            } else {
                MessageService messageService = new MessageServiceImpl();
                Session sessionf = ChatWebSocket.mapUS.get(friendId);//好友session
                if (sessionf != null) {
                    int msgCount = messageService.getMsgCounts(friendId);
                    JSONObject mjson = new JSONObject();
                    mjson.put("type", 300);
                    JSONObject obj = new JSONObject();
                    obj.put("msgCount", msgCount);
                    mjson.put("msgBody", obj);
                    sessionf.getAsyncRemote().sendText(mjson.toJSONString());
                }

            }

            msgBody = "{}";
        } else if (req.cmd.equals("addFriendsType")) {
            int userId = req.msgBody.getInteger("userId");
            String groupName = req.msgBody.getString("groupName");
            if (groupName == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupName参数不存在!");
            }
            FriendsType friendsType = new FriendsType();
            friendsType.setUserId(userId);
            friendsType.setGroupName(groupName);
            FriendsTypeService friendsTypeService = new FriendsTypeServiceImpl();
            friendsTypeService.addFriendsType(friendsType);
            Integer friendsTypeId=friendsType.getFriendsTypeId();
            JSONObject r = new JSONObject();
            r.put("groupId",friendsTypeId);
            msgBody = JSON.toJSONString(r, SerializerFeature.WriteMapNullValue);

        } else if (req.cmd.equals("editFriendsType")) {
            Integer friendsTypeId = req.msgBody.getInteger("friendsTypeId");
            if (friendsTypeId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "friendsTypeId参数不存在!");
            }
            String groupName = req.msgBody.getString("groupName");
            if (groupName == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupName参数不存在!");
            }
            FriendsTypeService friendsTypeService = new FriendsTypeServiceImpl();
            friendsTypeService.editFriendsType(friendsTypeId, groupName);
            msgBody = "{}";

        } else if (req.cmd.equals("delFriendsType")) {
            Integer friendsTypeId = req.msgBody.getInteger("curGroupId");
            if (friendsTypeId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "friendsTypeId参数不存在!");
            }
            Integer moveGroupId=req.msgBody.getInteger("moveGroupId");

            FriendsTypeService friendsTypeService = new FriendsTypeServiceImpl();
            FriendsType friendsType=friendsTypeService.getUserIdByType(friendsTypeId);
            Integer userId=friendsType.getUserId();
            UserFriendService userFriendService=new UserFriendServiceImpl();
            if (userFriendService.existFriends(userId,friendsTypeId)>0){
             //移动好友到指定分组
               if(friendsTypeService.existFriendsType(moveGroupId,userId)<=0)
                   throw new YtbError(YtbError.CODE_DEFINE_ERROR, "指定分组不存在!");
                userFriendService.removeFriend(userId,friendsTypeId,moveGroupId);
            }

            friendsTypeService.delFriendsType(friendsTypeId);
            msgBody = "{}";
        } else if (req.cmd.equals("reFriend")) {
            int userId = req.msgBody.getInteger("userId");
            Integer friendId = req.msgBody.getInteger("friendId");
            if (friendId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "friendId参数不存在!");
            }
            Integer friendsTypeId = req.msgBody.getInteger("friendsTypeId");
            if (friendsTypeId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "friendsTypeId参数不存在!");
            }
            UserFriendService userFriendService = new UserFriendServiceImpl();
            userFriendService.editFriendTyp(userId, friendId, friendsTypeId);
            msgBody = "{}";
        } else if (req.cmd.equals("deleteMsg")) {
            Integer messageId = req.msgBody.getInteger("messageId");
            if (messageId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "messageId参数不存在!");
            }
            MessageService messageService = new MessageServiceImpl();
            messageService.deleteMsg(messageId);
            msgBody = "{}";
        } else if (req.cmd.equals("updateStatus")) {
            Integer status = req.msgBody.getInteger("status");
            JSONArray msgIdArr = req.msgBody.getJSONArray("msgIdArr");
            List<Integer> list = new ArrayList<Integer>();
            for (Object obj : msgIdArr) {
                Integer id = (Integer) obj;
                list.add(id);
            }
            if(list.size()>0){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", status);
            map.put("idList", list);
            MessageService messageService = new MessageServiceImpl();
            messageService.updateStateById(map);
            }
            msgBody = "{}";
        } else if (req.cmd.equals("answer")) {
            //好友申请回复
            Answer answer = new Answer();
            Integer inviteId = req.msgBody.getInteger("inviteId");
            if (inviteId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "inviteId参数不存在!");
            }
            Integer formUserId = req.msgBody.getInteger("formUserId");
            if (formUserId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "formUserId参数不存在!");
            }
            Integer toUserId = req.msgBody.getInteger("toUserId");
            if (toUserId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "toUserId参数不存在!");
            }
            MessageService messageService = new MessageServiceImpl();
//            messageService.setIsRead(formUserId,inviteId);//改变通知表消息状态为已读
            String content = req.msgBody.getString("content");
            answer.setInviteId(inviteId);
            answer.setFormUserId(formUserId);
            answer.setToUserId(toUserId);
            answer.setContent(content);
            answer.setType(1);
            //添加一条记录到回复表
            AnswerService answerService = new AnswerServiceImpl();
//            answerService.setIsRead(inviteId, formUserId);
            answerService.addAnswer(answer);

            messageService.setIsRead(formUserId,inviteId,1);
            messageService.changeState(toUserId,inviteId,1);

            Session session = ChatWebSocket.mapUS.get(toUserId);//好友session
            if (session != null) {
                int msgCount = messageService.getMsgCounts(toUserId);
                JSONObject mjson = new JSONObject();
                mjson.put("type", 300);
                JSONObject obj = new JSONObject();
                obj.put("msgCount", msgCount);
                mjson.put("msgBody", obj);
                session.getAsyncRemote().sendText(mjson.toJSONString());
            }

            msgBody = "{}";
        } else if (req.cmd.equals("getMsgList")) {
            Integer userId = req.msgBody.getInteger("userId");
            MessageService messageService = new MessageServiceImpl();
            int counts = messageService.getMsgCounts(userId);
            List<Message> megList = messageService.getMsgList(userId);
            List<GroupMessage> groupMsgList=messageService.getGroupMsgList(userId);
            JSONObject r = new JSONObject();
            r.put("totalPage", (counts + 2) / 3);
            r.put("list", megList);
            r.put("groupMsgList",groupMsgList);
            msgBody = JSON.toJSONString(r, SerializerFeature.WriteMapNullValue);
        } else if (req.cmd.equals("getfriendsgrouplist")) {
            //获取个人好友分组列表
            int userId = req.msgBody.getInteger("userId");
            UserInfoService userInfoService = new UserInfoServiceImpl();
            List<UserFriendsGroup> friendList = userInfoService.selectUserFriendGroupList(userId);
            JSONObject r = new JSONObject();
            r.put("list", friendList);
            msgBody = JSON.toJSONString(r, SerializerFeature.WriteMapNullValue);
        } else if (req.cmd.equals("getuserfriend")) {
            //查询好友
            int userId = req.msgBody.getInteger("userId");
            FriendGroupInfo info = friendsInfoService.getFriendGroupInfo(userId);
            msgBody = "{\"friendsInfo\":" + JSONObject.toJSONString(info, SerializerFeature.WriteMapNullValue) + "}";
        } else if (req.cmd.equals("isAddFriend")) {
            //是否同意添加好友
            UserFriendService service = new UserFriendServiceImpl();
            FriendApplyInfoService fiservice = new FriendApplyInfoServiceImpl();
            int friendsTypeId = req.msgBody.getInteger("friendsTypeId");//B把A加入某个好友分组
            int inviteId = req.msgBody.getInteger("inviteId");//邀请Id
            int inviteStatus = req.msgBody.getInteger("inviteStatus");//是否同意 ; 2 同意
            Friend_Apply_InfoModel faiModel = fiservice.getFriendApplyInfoByAppId(inviteId);
            int userId = faiModel.getToUserID();
            int friendId = faiModel.getUserID();
//            int friendsTypeIdf = faiModel.getFriendsTypeId();
            retcode = service.isAddFriend(userId, friendId, inviteStatus, friendsTypeId,inviteId);
            if (retcode > 0) {
                retmsg = "失败";
            }else {
                MessageService messageService = new MessageServiceImpl();
                messageService.changeState(friendId,inviteId,1);
                Session sessionw = ChatWebSocket.mapUS.get(friendId);//好友session
                if (sessionw != null){
                    UserInfoService userInfoService=new UserInfoServiceImpl();
                    UserInfo userInfo=userInfoService.getUserInfoById(inviteId);
                    JSONObject json = new JSONObject();
                    json.put("type",400);
                    JSONObject ob = new JSONObject();
                    ob.put("UserInfo",userInfo);
                    json.put("msgBody", ob);
                    sessionw.getAsyncRemote().sendText(json.toJSONString());

                    int msgCount = messageService.getMsgCounts(friendId);
                    JSONObject mjson = new JSONObject();
                    mjson.put("type", 300);
                    JSONObject obj = new JSONObject();
                    obj.put("msgCount", msgCount);
                    mjson.put("msgBody", obj);
                    sessionw.getAsyncRemote().sendText(mjson.toJSONString());
                }
            }
            msgBody = "{}";
        } else if (req.cmd.equals("deleteUserFriend")) {
            //删除好友
            UserFriendService service = new UserFriendServiceImpl();
            int userId = req.msgBody.getInteger("userId");
            int friendId = req.msgBody.getInteger("friendId");
            retcode = service.deleteUserFriend(userId, friendId);
            if (retcode > 0) {
                retmsg = "失败";
            }
            msgBody = "{}";
        } else if (req.cmd.equals("getUsersGroups")) {

            Integer userId = handler.getUserContext().getLoginSso().getUserId().intValue();

            if (userId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "userId参数不存在!");
            }
            List<UserFriends> lst = friendsInfoService.selectList(userId);

            JSONObject r = new JSONObject();
            r.put("list",lst);
            msgBody = r.toJSONString();


        }
        return handler.buildMsg(retcode, retmsg, msgBody);

    }
}
