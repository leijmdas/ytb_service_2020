package ytb.bangbang.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.bangbang.model.GroupChatFile;
import ytb.bangbang.model.Record_GroupModel;
import ytb.bangbang.model.Record_UserModel;
import ytb.bangbang.model.SingleChatFile;
import ytb.bangbang.service.*;
import ytb.bangbang.service.impl.*;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author tanc
 * @Description //获取聊天记录
 * @Date 2019/3/18
 **/
public class RecordList {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;
    private RecordUserService service=new RecordUserServiceImpl();
    private GroupInfoService infoService=new GroupInfoServiceImpl();
    private RecordGroupService recordGroupService=new RecordGroupServiceImpl();
    public MsgResponse process(MsgRequest req, MsgHandler handler) {

        if(req.cmd.equals("getRecordList")){
            // 聊天记录
                GetRecordListService service=new GetRecordListServiceImpl();
                int userId=req.msgBody.getInteger("userId");
                int id=req.msgBody.getInteger("id");
                String type=req.msgBody.getString("type");
                List list=new ArrayList();
                try {
                     list=service.getRecordList(userId,id,type);
                }catch (RuntimeException e){
                    retcode=1;
                }
            msgBody="{\"contentList\":"+JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }else if(req.cmd.equals("getUserRecordList")){
            //单聊-聊天记录
            Integer fromUser=req.msgBody.getInteger("fromUser");
            if (fromUser == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "fromUser参数不存在!");
            }
            Integer toUser=req.msgBody.getInteger("toUser");
            if (toUser == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "toUser参数不存在!");
            }
            Integer type=req.msgBody.getInteger("type");
            String beganTime=req.msgBody.getString("beganTime");
            String endTime=req.msgBody.getString("endTime");

            Map<String,Object> parMap=new HashMap<String, Object>();
            parMap.put("fromUser",fromUser);
            parMap.put("toUser",toUser);
            parMap.put("beganTime",beganTime);
            parMap.put("endTime",endTime);
            parMap.put("type",type);

            List<Record_UserModel> recordUserModelList=service.getUserRecords(parMap);
            JSONObject returnJson = new JSONObject();
            returnJson.put("userRecordList",recordUserModelList);
            msgBody = JSON.toJSONString(returnJson, SerializerFeature.WriteMapNullValue);
        }else if(req.cmd.equals("delSingFile")) {
            //单聊删除聊天记录/文件
            Integer recordId=req.msgBody.getInteger("recordId");
            if(recordId==null)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "recordId参数不存在!");
            RecordUserService service=new RecordUserServiceImpl();
            service.delSingFile(recordId);
            msgBody="{}";
        }else if(req.cmd.equals("getFilelist")){
            //获取单聊-聊天记录-图片与视频/文件
            Integer fromUser=req.msgBody.getInteger("fromUser");
            if (fromUser == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "fromUser参数不存在!");
            }
            Integer toUser=req.msgBody.getInteger("toUser");
            if (toUser == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "toUser参数不存在!");
            }
            Integer fileType=req.msgBody.getInteger("fileType");
            if (fileType==null)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "fileType参数不存在!");
            String beganTime=req.msgBody.getString("beganTime");
            String endTime=req.msgBody.getString("endTime");
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("fromUserId",fromUser);
            map.put("toUserId",toUser);
            map.put("fileType",fileType);
            map.put("beganTime",beganTime);
            map.put("endTime",endTime);
            SingleChatFileService service=new SingleChatFileServiceImpl();
            List<SingleChatFile> list= service.getFilelist(map);
            JSONObject obj = new JSONObject();
            obj.put("Filelist",list);

            msgBody = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
         }else if(req.cmd.equals("getGroupRecordList")){
            //群聊-聊天记录
            Integer groupId=req.msgBody.getInteger("groupId");
            if (groupId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupId参数不存在!");
            }
            String beganTime=req.msgBody.getString("beganTime");
            String endTime=req.msgBody.getString("endTime");
            if(!infoService.IsExtisGroup(groupId))
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "该群组不存在!");
            Integer type=req.msgBody.getInteger("type");
            Map<String,Object> parMap=new HashMap<String,Object>();
            parMap.put("groupId",groupId);
            parMap.put("beganTime",beganTime);
            parMap.put("endTime",endTime);
            parMap.put("type",type);
            List<Record_GroupModel> list=recordGroupService.getRecordGroupList(parMap);
            JSONObject returnJson = new JSONObject();
            returnJson.put("groupRecordList",list);
            msgBody = JSON.toJSONString(returnJson, SerializerFeature.WriteMapNullValue);
         }else if(req.cmd.equals("delGroupFile")) {
            //删除群聊聊天记录/文件
            Integer userId=req.msgBody.getInteger("userId");
            Integer recordId=req.msgBody.getInteger("recordId");
            if(recordId==null)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "recordId参数不存在!");

            RecordGroupService service=new RecordGroupServiceImpl();
            Integer fromUser=service.getFromUserById(recordId);
            if(fromUser!=userId)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你没有权限删除该文件!");
            service.delGroupFile(recordId);
            msgBody="{}";
         }else if(req.cmd.equals("getGroupFilelist")){
            //群聊-聊天记录-图片与视频/文件
            Integer groupId=req.msgBody.getInteger("groupId");
            if (groupId == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "groupId参数不存在!");
            }
            Integer fileType=req.msgBody.getInteger("fileType");
            if (fileType==null)
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "fileType参数不存在!");
            String beganTime=req.msgBody.getString("beganTime");
            String endTime=req.msgBody.getString("endTime");
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("groupId",groupId);
            map.put("fileType",fileType);
            map.put("beganTime",beganTime);
            map.put("endTime",endTime);
            GroupChatFileService service=new GroupChatFileServiceImpl();
            List<GroupChatFile> list=service.getFilelist(map);
            JSONObject obj = new JSONObject();
            obj.put("Filelist",list);
            msgBody = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);

    }
}
