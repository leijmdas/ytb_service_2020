package ytb.bangbang.rest.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.bangbang.model.*;
import ytb.bangbang.rest.ChatWebSocket;
import ytb.bangbang.service.*;
import ytb.bangbang.service.impl.*;
import ytb.bangbang.util.DictionaryData;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author hj
 * @Description //群组管理
 * @Date 2018/9/6
 **/
public class GroupManager {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, MsgHandler handler) {

        if (req.cmd.equals("addGroup")) {
            //创建群组
            String groupName = req.msgBody.getString("groupName");
            if (groupName == null || "".equals(groupName))
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupName参数不能为空!");
            int userId = req.msgBody.getInteger("userId");
            Integer groupType = req.msgBody.getInteger("groupType");
            if (groupType == null)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupType参数不能为空!(1:工作组,2:兴趣组)");
            JSONArray userIdArr = req.msgBody.getJSONArray("userIdArr");
            List<Integer> userList = new ArrayList<Integer>();
            if (userIdArr != null) {
                for (Object obj : userIdArr) {
                    Integer id = (Integer) obj;
                    userList.add(id);
                }
            }
            int groupId = 0;
            try {
                GroupInfoService groupInfoService = new GroupInfoServiceImpl();
                Group_InfoModel groupInfoModel = new Group_InfoModel();
                groupInfoModel.setGroupName(groupName);
                groupInfoModel.setGroupType(groupType);
                groupInfoService.AddRecord(groupInfoModel);
                groupId = groupInfoModel.getGroup_id();

                GroupUserService groupUserService = new GroupUserServiceImpl();
                GroupUser groupUserModel = new GroupUser();
                groupUserModel.setUserId(userId);
                groupUserModel.setGroupId(groupId);
                groupUserModel.setGroupUserType(1);
                groupUserModel.setGroupTypeId(groupType);
                groupUserService.addRecordu(groupUserModel);

                if (userList.size() > 0) {
                    for (Integer uid : userList) {
                        GroupUser gmode = new GroupUser();
                        gmode.setUserId(uid);
                        gmode.setGroupId(groupId);
                        gmode.setGroupUserType(3);
                        gmode.setGroupTypeId(groupType);
                        groupUserService.addMember(gmode);
                    }
                }
            } catch (YtbError e) {
                retcode = 1;
                retmsg = e.getMessage();
                msgBody = "{}";
            }

            JSONObject json = new JSONObject();
            json.put("groupId", groupId);
            json.put("groupTypeId", groupType);
            msgBody = json.toJSONString();
        } else if (req.cmd.equals("addGroupType")) {
            //添加群分组
            int userId = req.msgBody.getInteger("userId");
            String groupName = req.msgBody.getString("groupName");
            if (groupName == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupName参数不存在!");
            }
            GroupTypeService service = new GroupTypeServiceImpl();
            Integer flag = service.getGroupTypId(userId, groupName);
            if (flag != null)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "该群分组已经存在!");
            GroupType model = new GroupType();
            model.setUserId(userId);
            model.setGroupName(groupName);
            service.addGroupTyp(model);
            msgBody = "{}";
        } else if (req.cmd.equals("editGroupType")) {
            //重命名群分组
            int userId = req.msgBody.getInteger("userId");
            Integer groupTypeId = req.msgBody.getInteger("groupTypeId");
            if (groupTypeId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupTypeId参数不存在!");
            }
            String groupName = req.msgBody.getString("groupName");
            if (groupName == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupName参数不存在!");
            }
            GroupTypeService service = new GroupTypeServiceImpl();
            Integer flag = service.getGroupTypId(userId, groupName);
            if (flag != null)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "该群分组已经存在!请重新命名");
            service.upGroupTyp(groupTypeId, groupName);
            msgBody = "{}";
        } else if (req.cmd.equals("delGroupType")) {
            //删除群分组
            int userId = req.msgBody.getInteger("userId");
            Integer groupTypeId = req.msgBody.getInteger("groupTypeId");
            if (groupTypeId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupTypeId参数不存在!");
            }
            GroupTypeService service = new GroupTypeServiceImpl();
            Integer defaltId = service.getGroupTypId(userId, "我的群聊");
            GroupUserService groupUserService = new GroupUserServiceImpl();
            groupUserService.removeToDefaltType(userId, groupTypeId, defaltId);
            service.delGroupTyp(groupTypeId);
            msgBody = "{}";
        } else if (req.cmd.equals("searchGroup")) {
            String groupName = req.msgBody.getString("groupName");
            if (groupName != null) {
                if (groupName.equals(""))
                    groupName = null;
            }
            String groupAddress = req.msgBody.getString("groupAddress");
            if (groupAddress != null) {
                if (groupAddress.equals(""))
                    groupAddress = null;
            }
            Integer pageNo = req.msgBody.getInteger("pageNo");
            if (pageNo == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "pageNo参数不存在!");
            }
            Integer limit = req.msgBody.getInteger("limit");
            if (limit == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "limit参数不存在!");
            }
            GroupInfoService groupInfoService = new GroupInfoServiceImpl();
            Integer totalCount = groupInfoService.getAllCounts(groupName, groupAddress);//总记录数
            Integer totalPage = (totalCount + limit - 1) / limit;//总页数
            if (totalPage <= 1)
                totalPage = 1;
            List<SeachGroup> list = groupInfoService.seachGroup(groupName, groupAddress, (pageNo - 1) * limit, limit);

            JSONObject obj = new JSONObject();
            obj.put("pageNo", pageNo);
            obj.put("totalPage", totalPage);
            obj.put("totalCount", totalCount);
            obj.put("limit", limit);
            obj.put("list", list);
            msgBody = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        } else if (req.cmd.equals("ViewingGroupData")) {
            Integer groupId = req.msgBody.getInteger("groupId");
            if (groupId == null)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupId参数不存在!");
            GroupInfoService service = new GroupInfoServiceImpl();
            GroupData model = service.viewingGroupData(groupId);
            JSONObject obj = new JSONObject();
            obj.put("groupInfo", model);
            msgBody = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        } else if (req.cmd.equals("selGroupById")) {
            //查询工作组和兴趣组
            int userId = req.msgBody.getInteger("userId");
            GroupUserService service = new GroupUserServiceImpl();
            List<Group_UserModel> list = service.selGroupById(userId);
            JSONObject r = new JSONObject();
            r.put("list", list);
            msgBody = JSON.toJSONString(r, SerializerFeature.WriteMapNullValue);
        } else if (req.cmd.equals("findGroupsByName")) {
            //搜群-显示群公告
            String groupName = req.msgBody.getString("groupName");
            GroupInfoService service = new GroupInfoServiceImpl();
            List<GroupInfo> list = service.findGroupsByName(groupName);
            JSONObject r = new JSONObject();
            r.put("list", list);
            msgBody = JSON.toJSONString(r, SerializerFeature.WriteMapNullValue);
        } else if (req.cmd.equals("userApplyGroup")) {
            //申请加群
            Integer userId = req.msgBody.getInteger("userId");
            Integer groupId = req.msgBody.getInteger("groupId");
            if (groupId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupId参数不存在!");
            }
            GroupInfoService groupInfoService = new GroupInfoServiceImpl();
            if (!groupInfoService.IsExtisGroup(groupId)) {
                retcode = 11;
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "该群组不存在!");
            }

            GroupUserService service = new GroupUserServiceImpl();
            if (service.IsExistence(userId, groupId)) {
                retcode = 11;
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "已经是该群组的成员!");
            }

            Integer toUserId = service.findGroupOwnerId(groupId);
            Group_Apply_InfoModel model = new Group_Apply_InfoModel();
            model.setUserId(userId);
            model.setToUserId(toUserId);
            model.setGroupId(groupId);
            model.setType(1);
            model.setIsAgree(1);
            GroupApplyInfoService gservice = new GroupApplyInfoServiceImpl();
            gservice.AddRecord(model);

            MessageService messageService = new MessageServiceImpl();
            Message message = new Message();
            message.setType(2);//群聊消息
            message.setReceiveUserId(toUserId);
            message.setRead(1);
            message.setInviteId(model.getId());
            messageService.addMessage(message);//通知表插入一条数据

            Message messageu = new Message();
            messageu.setType(2);
            messageu.setReceiveUserId(userId);
            messageu.setRead(2);
            messageu.setInviteId(model.getId());
            messageService.addMessage(messageu);

            Session sessionf = ChatWebSocket.mapUS.get(toUserId);//好友session
            if (sessionf != null) {
                int msgCount = messageService.getMsgCounts(toUserId);
                JSONObject mjson = new JSONObject();
                mjson.put("type", 300);
                JSONObject obj = new JSONObject();
                obj.put("msgCount", msgCount);
                mjson.put("msgBody", obj);
                sessionf.getAsyncRemote().sendText(mjson.toJSONString());
            }

            msgBody = "{}";

        } else if (req.cmd.equals("ppinGroups")) {
            //群主拉人进群
            Integer userId = req.msgBody.getInteger("userId");
            Integer groupId = req.msgBody.getInteger("groupId");
            if (groupId == null)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupId参数不能为空!");
            JSONArray userIdArr = req.msgBody.getJSONArray("userIdArr");
            List<Integer> userList = new ArrayList<Integer>();
            if (userIdArr != null) {
                for (Object obj : userIdArr) {
                    Integer id = (Integer) obj;
                    userList.add(id);
                }
            }

            GroupInfoService groupInfoService = new GroupInfoServiceImpl();
            if (!groupInfoService.IsExtisGroup(groupId)) {
                retcode = 11;
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "该群组不存在!");
            }

            GroupUserService service = new GroupUserServiceImpl();
            MessageService messageService = new MessageServiceImpl();
            GroupApplyInfoService gservice = new GroupApplyInfoServiceImpl();

            GroupUserService groupUserService = new GroupUserServiceImpl();
            Integer groupUserType = groupUserService.GetGroupUserType(userId, groupId);
            if (groupUserType != 1)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你没有权限拉人进群!");
            for (Integer uid : userList) {
                if (service.IsExistence(uid, groupId)) {
                    continue;
                }
                Group_Apply_InfoModel model = new Group_Apply_InfoModel();
                model.setUserId(userId);
                model.setToUserId(uid);
                model.setGroupId(groupId);
                model.setType(2);
                model.setIsAgree(1);
                gservice.AddRecord(model);//创建一条拉人记录

                Message message = new Message();
                message.setType(2);//群聊消息
                message.setReceiveUserId(uid);
                message.setRead(1);
                message.setInviteId(model.getId());
                messageService.addMessage(message);//通知表插入一条数据

                Message messageu = new Message();
                messageu.setType(2);
                messageu.setReceiveUserId(userId);
                messageu.setRead(2);
                messageu.setInviteId(model.getId());
                messageService.addMessage(messageu);

                Session sessionf = ChatWebSocket.mapUS.get(uid);//好友session
                if (sessionf != null) {
                    int msgCount = messageService.getMsgCounts(uid);
                    JSONObject mjson = new JSONObject();
                    mjson.put("type", 300);
                    JSONObject obj = new JSONObject();
                    obj.put("msgCount", msgCount);
                    mjson.put("msgBody", obj);
                    sessionf.getAsyncRemote().sendText(mjson.toJSONString());
                }
            }

            msgBody = "{}";
        } else if (req.cmd.equals("answer")) {
            //申请加群回复
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
            String content = req.msgBody.getString("content");
            answer.setInviteId(inviteId);
            answer.setFormUserId(formUserId);
            answer.setToUserId(toUserId);
            answer.setContent(content);
            answer.setType(2);
            AnswerService answerService = new AnswerServiceImpl();
            answerService.addAnswer(answer);
            MessageService messageService = new MessageServiceImpl();
            messageService.setIsRead(formUserId, inviteId, 2);
            messageService.changeState(toUserId, inviteId, 2);
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
        } else if (req.cmd.equals("isAddGroup")) {
            Integer userId = req.msgBody.getInteger("userId");
            Integer inviteId = req.msgBody.getInteger("inviteId");
            if (inviteId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "inviteId参数不存在!");
            }
            Integer inviteStatus = req.msgBody.getInteger("inviteStatus");
            if (inviteStatus == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "inviteStatus参数不存在!");
            }
            Integer groupId = req.msgBody.getInteger("groupId");
            if (groupId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupId参数不存在!");
            }
            GroupInfoService groupInfoService = new GroupInfoServiceImpl();
            Integer groupType = groupInfoService.getGroupTypeById(groupId);
            GroupApplyInfoService service = new GroupApplyInfoServiceImpl();
            Group_Apply_InfoModel groupApplyInfoModel = service.getUserApplyGroupInfo(inviteId);
            Integer applyUserId = groupApplyInfoModel.getUserId();
            Integer applyType = groupApplyInfoModel.getType();
            Integer addUserId = null;
            if (applyType == 1) {
                addUserId = applyUserId;
            }
            if (applyType == 2) {
                addUserId = groupApplyInfoModel.getToUserId();
            }

            if (inviteStatus == DictionaryData.ISAGREEOK) {
                //同意
                service.changeIsAgree(inviteStatus, inviteId);//修改申请表状态
                GroupUserService groupUserService = new GroupUserServiceImpl();
                GroupUser groupUserModel = new GroupUser();
                groupUserModel.setUserId(addUserId);
                groupUserModel.setGroupId(groupId);
                groupUserModel.setGroupUserType(3);
                groupUserModel.setGroupTypeId(groupType);
                groupUserService.addRecordu(groupUserModel);

                MessageService messageService = new MessageServiceImpl();
                messageService.changeState(addUserId, inviteId, 2);
                messageService.setIsRead(userId, inviteId, 2);
                Session sessionAp = ChatWebSocket.mapUS.get(applyUserId);//申请者session
                if (sessionAp != null) {
                    Group_InfoModel groupInfoModel = groupInfoService.getGroupInfoById(groupId);
                    JSONObject json = new JSONObject();
                    json.put("type", 500);
                    JSONObject ob = new JSONObject();
                    ob.put("GroupInfo", groupInfoModel);
                    json.put("msgBody", ob);
                    sessionAp.getAsyncRemote().sendText(json.toJSONString());

                    int msgCount = messageService.getMsgCounts(applyUserId);
                    JSONObject mjson = new JSONObject();
                    mjson.put("type", 300);
                    JSONObject obj = new JSONObject();
                    obj.put("msgCount", msgCount);
                    mjson.put("msgBody", obj);
                    sessionAp.getAsyncRemote().sendText(mjson.toJSONString());
                }

            }
            if (inviteStatus == DictionaryData.ISAGREENOTE) {
                //拒绝
                service.changeIsAgree(inviteStatus, inviteId);

            }


        } else if (req.cmd.equals("getGroupUsers")) {
            Integer groupId = req.msgBody.getInteger("groupId");
            if (groupId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupId参数不存在!");
            }
            GroupUserService service = new GroupUserServiceImpl();
            GroupInfoService infoService = new GroupInfoServiceImpl();
//            List<Group_UserModel> list=service.getGroupUserByGroupId(groupId);
            if (!infoService.IsExtisGroup(groupId))
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "该群组不存在!");
            List<GroupUserInfo> list = service.getGroupUsersInfo(groupId);
            JSONObject returnJson = new JSONObject();
            returnJson.put("groupUsers", list);
            msgBody = JSON.toJSONString(returnJson, SerializerFeature.WriteMapNullValue);
        } else if (req.cmd.equals("addGroupUser")) {
            //添加普通群组成员
            GroupService service = new GroupServiceImpl();
            int userId = req.msgBody.getInteger("userId");
            int groupId = req.msgBody.getInteger("groupId");
            JSONArray userIdArr = req.msgBody.getJSONArray("groupUserIdArr");
            List<Integer> lst = new ArrayList<>();
            for (int i = 0; i < userIdArr.size(); i++) {
                lst.add((int) userIdArr.get(i));
            }

            retcode = service.addGroupUser(userId, groupId, lst);
            if (retcode > 0) {
                retmsg = "失败";
            }
            msgBody = "{}";
        } else if (req.cmd.equals("addWorkGroupUser")) {
            //添加工作群组成员
            GroupService service = new GroupServiceImpl();
            int userId = req.msgBody.getInteger("userId");
            int groupId = req.msgBody.getInteger("groupId");
            JSONArray groupUserIdArr = req.msgBody.getJSONArray("groupUserIdArr");
            retcode = service.addWorkGroupUser(userId, groupId, groupUserIdArr);
            if (retcode > 0) {
                retmsg = "失败";
            }
            msgBody = "{}";
        } else if (req.cmd.equals("setGroupUserType")) {
            //设置用户在群组的身份
            GroupService service = new GroupServiceImpl();
            int userId = req.msgBody.getInteger("userId");
            int groupId = req.msgBody.getInteger("groupId");
            int groupUserId = req.msgBody.getInteger("groupUserId");
            int groupUserType = req.msgBody.getInteger("groupUserType");
            retcode = service.setGroupUserType(userId, groupUserId, groupId, groupUserType);
            if (retcode > 0) {
                retmsg = "失败";
            }
            msgBody = "{}";
        } else if (req.cmd.equals("getGroupUserInfos")) {
            //获取群组所有成员信息
            GroupService service = new GroupServiceImpl();
            int userId = req.msgBody.getInteger("userId");
            int groupId = req.msgBody.getInteger("groupId");
            List list = service.getGroupUserInfos(userId, groupId);
            msgBody = "{\"groupUserlist\":" + JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue) + "}";
        } else if (req.cmd.equals("findGroupByGroupName")) {
            //通过组名查询兴趣组
            GroupService service = new GroupServiceImpl();
            String groupName = req.msgBody.getString("groupName");
            List<Group_InfoModel> list = service.findGroupByGroupName(groupName);
            msgBody = "{\"groupInfolist\":" + JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue) + "}";
        } else if (req.cmd.equals("deleteGroup")) {
            //删除群组
            GroupService service = new GroupServiceImpl();
            int userId = req.msgBody.getInteger("userId");
            int groupId = req.msgBody.getInteger("groupId");
            retcode = service.deleteGroup(userId, groupId);
            if (retcode > 0) {
                retmsg = "失败";
            }
            msgBody = "{}";
        } else if (req.cmd.equals("deleteGroupUser")) {
            //剔除成员或退出群组
            GroupService service = new GroupServiceImpl();
            int userId = req.msgBody.getInteger("userId");
            int groupUserId = req.msgBody.getInteger("groupUserId");
            int groupId = req.msgBody.getInteger("groupId");
            retcode = service.deleteGroupUser(userId, groupUserId, groupId);
            if (retcode > 0) {
                retmsg = "失败";
            }
            msgBody = "{}";
        } else if (req.cmd.equals("addGroupNotice")) {
            //发布群组公告
            int userId = req.msgBody.getInteger("userId");
            GroupNoticeModel groupNoticeModel = req.msgBody.toJavaObject(GroupNoticeModel.class);
            GroupService service = new GroupServiceImpl();
            int noticeId = service.addGroupNotice(userId, groupNoticeModel);
            JSONObject r = new JSONObject();
            r.put("noticeId", noticeId);
            msgBody = r.toJSONString();
        } else if (req.cmd.equals("getGroupNotice")) {
            //获取公告
            GroupService service = new GroupServiceImpl();
            int groupId = req.msgBody.getInteger("groupId");
            List<GroupNoticeModel> list = service.getGroupNotice(groupId);
            msgBody = "{\"groupNoticelist\":" + JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue) + "}";
        } else if (req.cmd.equals("deleteGroupNotice")) {
            //删除群公告
            GroupService service = new GroupServiceImpl();
            int userId = req.msgBody.getInteger("userId");
            int noticeId = req.msgBody.getInteger("noticeId");
            int groupId = req.msgBody.getInteger("groupId");
            retcode = service.deleteGroupNotice(userId, noticeId, groupId);
            if (retcode > 0) {
                retmsg = "失败";
            }
            msgBody = "{}";
        }
        return handler.buildMsg(retcode, retmsg, msgBody);

    }
}
