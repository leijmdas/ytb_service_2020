package ytb.bangbang.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.bangbang.model.*;
import ytb.bangbang.model.bangbang.SearchPersonModel;
import ytb.bangbang.service.*;
import ytb.bangbang.service.impl.*;
import ytb.common.utils.pageutil.PageUtils;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * @Author hj
 * @Description //用户管理
 * @Date 2018/9/5
 **/
public class UserManager {

    private UserInfoService userInfoService = new UserInfoServiceImpl();
    private UserCenterService userCenterService = new UserCenterServiceImpl();
    private FriendsTypeService friendsTypeService=new FriendsTypeServiceImpl();
    private GroupInfoService groupInfoService=new GroupInfoServiceImpl();
    private GroupTypeService groupTypeService=new GroupTypeServiceImpl();

    private int retcode = 0;
    private String retmsg = "成功";
    private String msgBody = "{}";

    public MsgResponse process(MsgRequest req, MsgHandler handler) {
        Integer userId = handler.getUserContext().getLoginSso().getUserId().intValue();

        if (req.cmd.equals("setUserRemarks")) {
            // 设置用户备注信息
            userId = req.msgBody.getInteger("userId");
            int friendId = req.msgBody.getInteger("friendId");
            String remarks = req.msgBody.getString("remarks");
            SetUserRemarksService service = new SetUserRemarksServiceImpl();
            retcode = service.setUserRemarks(userId, friendId, remarks);
            if (retcode > 0) {
                retmsg = "失败";
            }
            msgBody = "{}";
        } else if (req.cmd.equals("GetUserInfo")) {
            //获取好友用户信息
            int friendId = req.msgBody.getInteger("friendId");
//            GetUserInfoService service = new GetUserInfoServiceImpl();
//            Map friendsUserInfo = service.getUserInfo(friendId);
//            JSONObject json = new JSONObject();
//            json.put("UserInfo", friendsUserInfo);
            UserInfoService service=new UserInfoServiceImpl();
            UserInfo userInfo=service.getUserInfoByUserId(friendId);
            JSONObject json = new JSONObject();
            json.put("UserInfo", userInfo);
            msgBody = json.toJSONString();
            if (null == userInfo) {
                retcode = 1;
                retmsg = "失败";
                msgBody = "{}";
            }
        } else if (req.cmd.equals("getIndexUserInfos")) {
            int friendsTypeCounts=friendsTypeService.getFriendsTypeCountsById(userId);
            if(friendsTypeCounts<=0){
                FriendsType ft1=new FriendsType();
                ft1.setGroupName("我的好友");
                ft1.setUserId(userId);
                friendsTypeService.addFriendsType(ft1);
                FriendsType ft2=new FriendsType();
                ft2.setGroupName("电路设计");
                ft2.setUserId(userId);
                friendsTypeService.addFriendsType(ft2);
                FriendsType ft3=new FriendsType();
                ft3.setGroupName("机械设计");
                ft3.setUserId(userId);
                friendsTypeService.addFriendsType(ft3);
                FriendsType ft4=new FriendsType();
                ft4.setGroupName("软件设计");
                ft4.setUserId(userId);
                friendsTypeService.addFriendsType(ft4);
                FriendsType ft5=new FriendsType();
                ft5.setGroupName("造型设计");
                ft5.setUserId(userId);
                friendsTypeService.addFriendsType(ft5);
                FriendsType ft6=new FriendsType();
                ft6.setGroupName("界面设计");
                ft6.setUserId(userId);
                friendsTypeService.addFriendsType(ft6);
                FriendsType ft7=new FriendsType();
                ft7.setGroupName("加工厂");
                ft7.setUserId(userId);
                friendsTypeService.addFriendsType(ft7);
                FriendsType ft8=new FriendsType();
                ft8.setGroupName("采购商");
                ft8.setUserId(userId);
                friendsTypeService.addFriendsType(ft8);
                FriendsType ft9=new FriendsType();
                ft9.setGroupName("专家顾问");
                ft9.setUserId(userId);
                friendsTypeService.addFriendsType(ft9);
                FriendsType ft10=new FriendsType();
                ft10.setGroupName("同事");
                ft10.setUserId(userId);
                friendsTypeService.addFriendsType(ft10);
            }

            BBUserInfo mineInfo = userInfoService.getMineInfo(userId);
            List<UserFriendsGroup> friendList = userInfoService.selectUserFriendGroupList(userId);
            JSONObject group1Json=new JSONObject();
            group1Json.put("id",1);
            group1Json.put("groupName","我的工作组");
            List<GroupInfoModel> list1= groupInfoService.getGroupInfoListByUid(userId,1);
            group1Json.put("list",list1);

            JSONObject group2Json=new JSONObject();
            group2Json.put("id",2);
            group2Json.put("groupName","我的兴趣组");
            List<GroupInfoModel> list2= groupInfoService.getGroupInfoListByUid(userId,2);
            group2Json.put("list",list2);

            JSONArray groupList=new JSONArray();
            groupList.add(group1Json);
            groupList.add(group2Json);

            JSONObject r = new JSONObject();
            r.put("mine", mineInfo);
            r.put("friend", friendList);
            r.put("group", groupList);
            msgBody = JSON.toJSONString(r, SerializerFeature.WriteMapNullValue);

        } else if (req.cmd.equals("setUserSign")) {
            //用户签名
            SetUserSignService service = new SetUserSignServiceImpl();
            userId = req.msgBody.getInteger("userId");
            String userSign = req.msgBody.getString("userSign");
            retcode = service.setUserSign(userId, userSign);
            if (retcode > 0) {
                retmsg = "失败";
            }
            msgBody = "{}";
        } else if (req.cmd.equals("userInfoPage")) {
            //bangbang平台所有用户信息
            /*UserInfoService service = new UserInfoServiceImpl();
            Map<String, Object> map = new HashMap<>();
            String loginMobile = req.msgBody.getString("loginMobile");
            Integer sex = req.msgBody.getInteger("sex");
            String age = req.msgBody.getString("age");
            Integer tagId = req.msgBody.getInteger("tagId");
            Integer tagType = req.msgBody.getInteger("tagType");
            String schooleId = req.msgBody.getString("schooleId");
            String userGread = req.msgBody.getString("userGread");
            String useAddress = req.msgBody.getString("useAddress");
            map.put("loginMobile", loginMobile);
            map.put("sex", sex);
            map.put("age", age);
            map.put("tagId", tagId);
            map.put("tagType", tagType);
            map.put("schooleId", schooleId);
            map.put("userGread", userGread);
            map.put("useAddress", useAddress);

            List<BBUserModel> userInfoPage = service.getUserInfoByPage(map);
            msgBody = "{\"userInfoPage\":" + JSONObject.toJSONString(userInfoPage, SerializerFeature.WriteMapNullValue) + "}";*/

            SearchPersonModel searchPersonModel = JSON.toJavaObject(req.msgBody, SearchPersonModel.class);

            Integer pageNo = req.msgBody.getInteger("pageNo");
            Integer limit = req.msgBody.getInteger("limit");
            if (pageNo == null || limit == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "pageNo or limit is null");
            }
            PageUtils pageUtils = userInfoService.searchPerson(searchPersonModel, pageNo, limit);
            msgBody = JSON.toJSONString(pageUtils);
        }
        return handler.buildMsg(retcode, retmsg, msgBody);

    }
}
