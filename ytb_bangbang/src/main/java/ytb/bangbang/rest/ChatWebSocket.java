package ytb.bangbang.rest;


import com.alibaba.fastjson.JSONObject;
import ytb.bangbang.constant.RTCType;
import ytb.bangbang.model.Group_InfoModel;
import ytb.bangbang.model.OfflineMsgModel;
import ytb.bangbang.service.*;
import ytb.bangbang.service.impl.*;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/chatWebSocket/{userId}")
public class ChatWebSocket {

    private static int onlineCount = 0;
    //ConcurrentHashMap是线程安全的，而HashMap是线程不安全的。
    public static ConcurrentHashMap<Integer, Session> mapUS = new ConcurrentHashMap<>();//根据用户找session
    public static ConcurrentHashMap<Session, Integer> mapSU = new ConcurrentHashMap<>();//根据session找用户

    private GroupService groupService = new GroupServiceImpl();
    private GetRecordListService getRecordListService = new GetRecordListServiceImpl();
    private MessageService messageService = new MessageServiceImpl();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public synchronized void onOpen(Session session, @PathParam("userId") Integer userId) {
        if (mapUS.get(userId) != null) {
            mapUS.remove(userId);
            mapSU.remove(session, userId);
        }
        mapUS.put(userId, session);
        mapSU.put(session, userId);

        //推送离线消息
        List<OfflineMsgModel> offlineMsgList = getRecordListService.getOfflineMsg(userId);

        int msgCount = messageService.getMsgCounts(userId);
        if (msgCount > 0) {
            JSONObject mjson = new JSONObject();
            mjson.put("type", 300);
            JSONObject obj = new JSONObject();
            obj.put("msgCount", msgCount);
            mjson.put("msgBody", obj);
            session.getAsyncRemote().sendText(mjson.toJSONString());
        }

        JSONObject jsonObject;
        for (OfflineMsgModel offlineMsgModel : offlineMsgList) {
            jsonObject = JSONObject.parseObject(offlineMsgModel.getMsgBody());
            if ("group".equals(jsonObject.get("type"))) {
                session.getAsyncRemote().sendText(jsonObject.toJSONString());
            } else {
                onMessage(jsonObject.toString(), session);
            }
        }
        getRecordListService.deleteOfflineMsg(userId);
        //上线通知由客户端自主发起
        onlineCount++;           //在线数加1
        System.out.println("用户用户userId=" + userId + "进入ChatWebSocket！当前在线人数为" + onlineCount);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public synchronized void onClose(Session session) {
        Integer userId = mapSU.get(session);
        mapSU.remove(session);
        if (userId != null) {
            mapUS.remove(userId);
            onlineCount--;           //在线数减1
        }
        System.out.println("用户userId=" + userId + "退出ChatWebSocket！当前在线人数为" + onlineCount);
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        JSONObject jsonObject = JSONObject.parseObject(message);
        Integer type = jsonObject.getInteger("type");
        JSONObject msgBody = jsonObject.getJSONObject("msgBody");

        if (type == 600 || type == 601) {//单聊音视频
            String rtcType = msgBody.getString("rtcType");
            Integer friendId = msgBody.getInteger("friendId");
            Session friendSession = mapUS.get(friendId);
            if (RTCType.INVITE.equals(rtcType) && friendSession == null) {
                msgBody.put("rtcType", RTCType.OFFLINE);
                session.getAsyncRemote().sendText(jsonObject.toString());
            } else {
                friendSession.getAsyncRemote().sendText(jsonObject.toString());
            }
            return;
        }

        String username = msgBody.getJSONObject("mine").getString("username");
        String avatar = msgBody.getJSONObject("mine").getString("avatar");
        Integer id = msgBody.getJSONObject("mine").getInteger("id");
        String mtype = msgBody.getJSONObject("to").getString("type");
        String content = msgBody.getJSONObject("mine").getString("content");
        boolean mine = msgBody.getJSONObject("mine").getBoolean("mine");
        Integer fromid = msgBody.getJSONObject("mine").getInteger("id");
        Integer toId = msgBody.getJSONObject("to").getInteger("id");
        JSONObject obj = new JSONObject();
        JSONObject mjson = new JSONObject();
        mjson.put("username", username);
        mjson.put("avatar", avatar);
        if(type == 200){
            id=toId;
            GroupInfoService infoService=new GroupInfoServiceImpl();
            Group_InfoModel infoModel=infoService.getGroupInfoById(id);
            mjson.put("groupName", infoModel.getGroupName());
        }
        mjson.put("id", id);
        mjson.put("type", mtype);
        mjson.put("content", content);
        mjson.put("mine", false);
        mjson.put("fromid", fromid);
        obj.put("msgBody", mjson);
        switch (type) {
            case 100://单聊
                obj.put("type", 100);
                Session sessionTo = mapUS.get(toId);
                if (sessionTo != null) {
                    sessionTo.getAsyncRemote().sendText(obj.toString());
                    //存储用户聊天记录信息
                    UserFriendService userFriendService = new UserFriendServiceImpl();
                    userFriendService.AddRecordUser(fromid, content, toId);
                } else {
                    //记录用户离线消息
                    getRecordListService.addOfflineMsg(toId, jsonObject.toString(), 100);
                }
                break;
            case 200://群聊
                obj.put("type", 200);
                List<Integer> groupUserIdList = groupService.getGroupUserId(toId);
                GroupService groupService = new GroupServiceImpl();
                for (int groupUserId : groupUserIdList) {
                    if (fromid == groupUserId) {
                        continue;
                    }
                    Session groupSession_to = mapUS.get(groupUserId);
                    if (null == groupSession_to) {
                        //记录用户离线消息
                        getRecordListService.addOfflineMsg(groupUserId, jsonObject.toJSONString(), 200);
                    } else {
                        groupSession_to.getAsyncRemote().sendText(obj.toJSONString());
                    }
                }
                //存储群组聊天记录信息
                groupService.addRecordGroup(toId, content, fromid);
                /**
                 * 特殊处理群组中的@功能
                 */
                break;
//            case 400://客服
//                //传输消息
//                obj.put("type",400);
//                Session kfsession_to = mapUS.get(toId);
//                if (null != kfsession_to) {
//                    kfsession_to.getAsyncRemote().sendText(obj.toString());
//                    //存储用户聊天记录信息
//                    UserFriendService userFriendService = new UserFriendServiceImpl();
//                    userFriendService.AddRecordUser(fromId, jsonObject.getString("content"), toId);
//                } else {
//                    //记录用户离线消息
//                    getRecordListService.addOfflineMsg(toId, jsonObject.toJSONString(),300);
//                }
//                break;
            default:
                break;
        }
    }


    /**
     * 发生错误时调用
     *
     * @param
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

}
