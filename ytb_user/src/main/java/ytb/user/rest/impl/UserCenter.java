package ytb.user.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.user.model.*;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.rest.impl
 * Author: ZCS
 * Date: Created in 2018/9/6 19:52
 */
public class UserCenter {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody ="{}";

    public MsgResponse process(MsgRequest req, RestHandler handler,HttpServletRequest request) {
        UserCenterService userCenterService = new UserCenterServiceImpl();

        //根据ID获取信息
        if (req.cmd.equals("getUserLoginInfoById")) {
            int userId = getLoginSsoJson(req.token).getUserId();

            UserLoginModel userLoginModel = userCenterService.getUserLoginInfoById(userId);

            msgBody="{\"list\":"+JSON.toJSON(userLoginModel).toString()+"}";
        }

        //获取用户的学生论文
        else if(req.cmd.equals("getStudentPaperListById")){
            int userId = getLoginSsoJson(req.token).getUserId();
            List<StudentPaperModel> list = userCenterService.getStudentPaperListById(userId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //添加学生论文
        else if(req.cmd.equals("saveOrUpdateStudentPaper")){
            JSONArray json = req.msgBody.getJSONArray("json");
            userCenterService.addStudentPaper(json,req.token);
        }

        //删除学生论文
        else if(req.cmd.equals("deleteStudentPaper")){
            int paperId = req.msgBody.getInteger("paperId");
            int userId = getLoginSsoJson(req.token).getUserId();
            userCenterService.deleteStudentPaper(paperId,userId);

        }

        //修改学生论文
        else if(req.cmd.equals("updateStudentPaper")){
            StudentPaperModel studentPaperModel =  req.msgBody.toJavaObject(req.msgBody,StudentPaperModel.class);
            userCenterService.updateStudentPaper(studentPaperModel);
        }

        //获取用户的收件地址
        else if(req.cmd.equals("getUserAddressListById")){


            Map params = new HashMap();

            Integer userId =  getLoginSsoJson(req.token).getUserId();

            params.put("page",req.msgBody.getInteger("currPage"));
            params.put("limit",req.msgBody.getInteger("pageSize"));
            params.put("userId",userId);
            params.put("companyId","");
            Query query = new Query(params);
            List<UserAddressModel> list = userCenterService.getUserAddressListById(query);

            int total = userCenterService.queryUserAddressTotal(query);
            PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";
        }

        //新增用户收件地址
        else if(req.cmd.equals("addUserAddress")){
            UserAddressModel userAddressModel =  req.msgBody.toJavaObject(req.msgBody,UserAddressModel.class);
            userCenterService.addUserAddress(userAddressModel);
        }

        //删除用户收件地址
        else if(req.cmd.equals("deleteUserAddress")){
            int addressId = req.msgBody.getInteger("addressId");
            int userId = getLoginSsoJson(req.token).getUserId();
            userCenterService.deleteUserAddress(addressId,userId,null);
        }


        //获取用户奖励列表
        else if(req.cmd.equals("getUserAwardListById")){
            int userId = getLoginSsoJson(req.token).getUserId();
            List<UserAwardModel> list = userCenterService.getUserAwardListById(userId);

            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //设置用户奖励
        else if(req.cmd.equals("saveOrUpdateUserAward")){
            JSONArray json = req.msgBody.getJSONArray("json");
            userCenterService.saveOrUpdateUserAward(json,req.token);
        }

        //删除用户奖励
        else if(req.cmd.equals("deleteUserAward")){
            int userId = getLoginSsoJson(req.token).getUserId();
            int awardId = req.msgBody.getInteger("awardId");
            userCenterService.deleteUserAward(userId,awardId);
        }

        //修改用户奖励
        else if(req.cmd.equals("updateUserAward")){
            UserAwardModel userAwardModel =  req.msgBody.toJavaObject(req.msgBody,UserAwardModel.class);
            userCenterService.updateUserAward(userAwardModel);
        }

        //获取个人教育经历
        else if(req.cmd.equals("getUserEducationById")){
            int userId = getLoginSsoJson(req.token).getUserId();
            List<Map<String,Object>> list = userCenterService.getUserEducationById(userId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //新增个人教育经历
        else if(req.cmd.equals("saveOrUpdateUserEducation")){
            //学校名称
            JSONObject json = req.msgBody.getJSONObject("json");
            userCenterService.addUserEducation(json,req.token);

        }

        //删除个人教育经历
        else if(req.cmd.equals("deleteUserEducation")){
            int userId = getLoginSsoJson(req.token).getUserId();
            int educationId = req.msgBody.getInteger("educationId");
            userCenterService.deleteUserEducation(educationId,userId);
        }


        //获取个人刊物论文信息
        else if(req.cmd.equals("getUserPaperById")){
            int userId = getLoginSsoJson(req.token).getUserId();
            List<Map<String,Object>> list = userCenterService.getUserPaperById(userId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //添加个人刊物论文信息
        else if(req.cmd.equals("saveOrUpdateUserPaper")){

            JSONArray json = req.msgBody.getJSONArray("json");

            userCenterService.saveOrUpdateUserPaper(json,req.token);
        }

        //删除个人刊物论文
        else if(req.cmd.equals("deleteUserPaper")){
            int userId = getLoginSsoJson(req.token).getUserId();
            int paperId = req.msgBody.getInteger("paperId");
            userCenterService.deleteUserPaper(paperId,userId);
        }

        //修改个人刊物论文信息
        else if(req.cmd.equals("adduserPaper")){
            UserPaperModel userPaperModel = req.msgBody.toJavaObject(req.msgBody,UserPaperModel.class);
            userCenterService.updateUserPaper(userPaperModel);
        }

        // //获取个人专利列表
        else if(req.cmd.equals("getUserPatentById")){
            int userId = getLoginSsoJson(req.token).getUserId();
            Integer patentType = req.msgBody.getInteger("patentType");
            List<UserPatentModel> list = userCenterService.getUserPatentByIdAndType(userId,patentType);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //设置个人专利
        else if(req.cmd.equals("saveOrUpdateUserPatent")){
            JSONArray json = req.msgBody.getJSONArray("json");
            userCenterService.saveOrUpdateUserPatent(json,req.token);
        }

        //删除个人专利
        else if(req.cmd.equals("deleteUserPatent")){
            int userId = getLoginSsoJson(req.token).getUserId();
            int patentId = req.msgBody.getInteger("patentId");
            userCenterService.deleteUserPatent(userId,patentId);
        }



        //获取用户专业能力标签
        else if(req.cmd.equals("getUserMajorTag")){
            int userId = getLoginSsoJson(req.token).getUserId();
            Integer tagType = req.msgBody.getInteger("tagType");//1 兴趣标签   2专业能力标签

           JSONArray json = userCenterService.getUserTagById(userId,tagType);

            msgBody="{\"list\":"+ JSONObject.toJSONString(json, SerializerFeature.WriteMapNullValue)+"}";

        }


        //新增用户标签
        else if(req.cmd.equals("addUserMajorTag")){
            UserTagModel userTagModel = req.msgBody.toJavaObject(req.msgBody,UserTagModel.class);
            Integer userId = getLoginSsoJson(req.token).getUserId();
            userTagModel.setUserId(userId);
            userCenterService.addUserTag(userTagModel);
        }

        //删除用户标签
        else if(req.cmd.equals("deleteUserTag")){
            int userTagId = req.msgBody.getInteger("userTagId");
            int userId = getLoginSsoJson(req.token).getUserId();
            userCenterService.deleteUserTag(userId,userTagId);
        }

        //修改用户标签
        else if(req.cmd.equals("updateUserTag")){
            UserTagModel userTagModel = req.msgBody.toJavaObject(req.msgBody,UserTagModel.class);
            userCenterService.updateUserTag(userTagModel);
        }

        ///获取个人简历表
        else if(req.cmd.equals("getUserWorkHistroyListById")){
            int userId = getLoginSsoJson(req.token).getUserId();
            List<UserWorkHistroyModel> list = userCenterService.getUserWorkHistroyListById(userId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //设置个人简历
        else if(req.cmd.equals("saveOrUpdateUserWorkHistroy")){

            JSONArray json = req.msgBody.getJSONArray("json");

            userCenterService.saveOrUpdateUserWorkHistroy(json,req.token);
        }

        //删除个人简历
        else if(req.cmd.equals("deleteUserWorkHistroy")){
            Integer userId = getLoginSsoJson(req.token).getUserId();
            int workId = req.msgBody.getInteger("workId");
            if(userId == null || userId.equals("")){
                retcode=1;
                retmsg="删除失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }

            userCenterService.deleteUserWorkHistroy(userId,workId);

        }

        //修改个人简历
        else if(req.cmd.equals("updateUserWorkHistroy")){
            UserWorkHistroyModel userWorkHistroyModel = req.msgBody.toJavaObject(req.msgBody,UserWorkHistroyModel.class);

            userCenterService.updateUserWorkHistroy(userWorkHistroyModel);
        }

        //获取个人工作业绩列表
        else if(req.cmd.equals("getUserWorkPrjList")){
            int userId = getLoginSsoJson(req.token).getUserId();
            List<Map<String,Object>> list = userCenterService.getUserWorkPrjList(userId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";

        }

        //设置个人工作业绩
        else if(req.cmd.equals("saveOrUpdateUserWorkPrj")){

            JSONArray json = req.msgBody.getJSONArray("json");

            userCenterService.saveOrUpdateUserWorkPrj(json,req.token);
        }

        //删除个人工作业绩列表
        else if(req.cmd.equals("deleteUserWorkPrj")){
            int userId = getLoginSsoJson(req.token).getUserId();
            int prjId = req.msgBody.getInteger("prjId");
            userCenterService.deleteUserWorkPrj(userId,prjId);
        }


        //获取用户基本信息
        else if(req.cmd.equals("getUserInfoById")){
            int userId = getLoginSsoJson(req.token).getUserId();
            //个人基本资料
            Map<String,Object> map = userCenterService.getUserBaseInfo(userId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue)+"}";
        }

        //添加用户信息
        else if(req.cmd.equals("addUserInfo")){
            UserInfoModel userInfoModel = req.msgBody.toJavaObject(req.msgBody,UserInfoModel.class);
            userCenterService.addUserInfo(userInfoModel);
        }

        //修改用户信息
        else if(req.cmd.equals("saveOrUpdateUserInfo")){

            Map<String,Object> map = new HashMap<>();
            map.put("userId",getLoginSsoJson(req.token).getUserId());
            map.put("nickName",req.msgBody.getString("nickName"));
            map.put("sex",req.msgBody.getString("sex"));
            map.put("realName",req.msgBody.getString("realName"));
            map.put("userAge",req.msgBody.getDate("userAge"));
            map.put("email",req.msgBody.getString("email"));
            map.put("userAddress",req.msgBody.getString("userAddress"));
            map.put("detailedAddress",req.msgBody.getString("detailedAddress"));
            map.put("userHead",req.msgBody.getString("userHead"));
            map.put("cityId",req.msgBody.getInteger("cityId"));
            map.put("areaId",req.msgBody.getInteger("areaId"));
            map.put("provinceId",req.msgBody.getInteger("provinceId"));
            userCenterService.updateUserInfo(map);
        }

        //修改user_login表信息
        else if(req.cmd.equals("updateUserLoginInfo")){
            UserLoginModel userLoginModel = req.msgBody.toJavaObject(req.msgBody,UserLoginModel.class);
            userLoginModel.setUserId(getLoginSsoJson(req.token).getUserId());
            userCenterService.updateByPrimaryKeySelective(userLoginModel);
        }

        //获取user_login表基本信息
        else if(req.cmd.equals("getUserLognInfo")){
            int userId = getLoginSsoJson(req.token).getUserId();
            UserLoginModel userLoginModel = userCenterService.getUserLoginInfoById(userId);
            msgBody="{\"list\":"+JSON.toJSON(userLoginModel).toString()+"}";
        }

        //获取user_login表基本信息
        else if(req.cmd.equals("updateUserSelfEval")){
            int userId = getLoginSsoJson(req.token).getUserId();
            String selfEval = req.msgBody.getString("selfEval");
            userCenterService.updateUserSelfEval(userId,selfEval);
        }


        //修改身份认证后用户的状态
        else if(req.cmd.equals("updateUserStatus")){
            int userId = getLoginSsoJson(req.token).getUserId();
            int status = req.msgBody.getInteger("status");

            userCenterService.updateUserStatus(userId,status);

        }

        //获取用户上传的图片
        else if(req.cmd.equals("getUserImgInfo")){

            int imgId = req.msgBody.getInteger("imgId");
            String base64Str = userCenterService.getUserImgInfo(imgId);
            msgBody="{\"list\":"+JSON.toJSON(base64Str).toString()+"}";

        }

        else if(req.cmd.equals("getUserToBBNoPage")){
            Map<String,Object> map = new HashMap<>();
            String loginMobile = req.msgBody.getString("loginMobile");
            map.put("loginMobile",loginMobile);

            List<BBUserModel> list = userCenterService.getUserToBBNoPage(map);

            msgBody = "{\"list\":" + JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue) + "}";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }





        //获取用户基本资料
        else if(req.cmd.equals("getUserBaseInformation")){

            try{
                Integer userId = req.msgBody.getInteger("userId");
                Map<String,Object> returnMap = new HashMap<>();

                //个人基本资料
                Map<String,Object> map = userCenterService.getUserBaseInfo(userId);

                //专业能力标签
                JSONArray majorTagList = userCenterService.getUserTagById(userId,2);


                //专业能力标签
                JSONArray interestTagList = userCenterService.getUserTagById(userId,1);


                //个人教育经历
                List<UserEducationModel> userEduList = userCenterService.getUserEduById(userId);

                //工作简历
                List<Map<String,Object>> workPrjList= userCenterService.getUserWorkPrjList(userId);

                //获得奖励
                List<UserAwardModel> awardList = userCenterService.getUserAwardListById(userId);

                //授权发明的专利
                Map<String, Object> patentList =userCenterService.getUserPatentById(userId);

                //学生论文
                List<StudentPaperModel> stuPaperList = userCenterService.getStudentPaperListById(userId);

                //个人刊物
                List<Map<String,Object>> userPaperList = userCenterService.getUserPaperById(userId);


                returnMap.put("userInfo",map);//用户基本信息
                returnMap.put("userEduList",userEduList);//学习简历
                returnMap.put("userResumeList",workPrjList);//工作简历
                returnMap.put("awardList",awardList);//获得的奖励
                returnMap.put("stuPaperList",stuPaperList);//学生论文
                returnMap.put("userPaperList",userPaperList);//个人刊物
                returnMap.put("sqfmList",patentList.get("sqfmList"));//授权发明
                returnMap.put("sqsyList",patentList.get("sqsyList"));//授权实用
                returnMap.put("sqwgList",patentList.get("sqwgList"));//授权外观
                returnMap.put("sqrjList",patentList.get("sqrjList"));//软件著作
                returnMap.put("majorTagList",majorTagList);//专业标签
                returnMap.put("interestTagList",interestTagList);//兴趣标签


                msgBody = "{\"list\":"+JSONObject.toJSONString(returnMap, SerializerFeature.WriteMapNullValue)+"}";
            }catch (Exception e){
                e.printStackTrace();
                throw new YtbError(YtbError.CODE_INVALID_REST);
            }


        }

        //获取专业标签类别
        else if(req.cmd.equals("getTagListInfo")){
            Integer tagId = req.msgBody.getInteger("tagId");
            Integer tagType = req.msgBody.getInteger("tagType");

            List<VTagSpecialtyModel> list = userCenterService.getTagList(tagId,tagType);

            msgBody = "{\"list\":"+JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";

        }


        else if(req.cmd.equals("getUserBlack")){

            Map params = new HashMap();

            params.put("userType",req.msgBody.getInteger("userType"));
            params.put("page",req.msgBody.getInteger("currPage"));
            params.put("limit",req.msgBody.getInteger("pageSize"));
            Query query = new Query(params);
            List<Map<String,Object>> list = userCenterService.getUserBlackList(query);
            int count = userCenterService.getUserBlackTotal(req.msgBody.getInteger("userType"));

            PageUtils pageUtil = new PageUtils(list, count, query.getLimit(), query.getPage());

            msgBody = "{\"list\":"+JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";


        }

        else if(req.cmd.equals("addUserBlack")){

            Integer userId = req.msgBody.getInteger("blackId");
            Integer type = req.msgBody.getInteger("type");

            UserBlackModel userBlackModel = new UserBlackModel();
            userBlackModel.setUserId(userId);
            userBlackModel.setUserType(type);

            userCenterService.insertUserBlack(userBlackModel);
        }

        else if(req.cmd.equals("updateUserBlack")){
            Integer userId = req.msgBody.getInteger("userId");

            userCenterService.updateUserBlackList(userId);

        }

        //获取关注列表
        else if(req.cmd.equals("getUserAttendList")){

            Integer userId = getLoginSsoJson(req.token).getUserId();
            Integer companyId = getLoginSsoJson(req.token).getCompanyId();

            if( getLoginSsoJson(req.token).getUserType() == 1){//标识公司用户登录
                companyId = null;
            }else{
                userId = null;
            }


            List<Map<String,Object>> list = userCenterService.getAttendList(userId,companyId);
            msgBody = "{\"list\":"+JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";

        }


        //添加关注
        else if(req.cmd.equals("addUserAttend")){
            Integer attendToUserId = req.msgBody.getInteger("attendToUserId");//被关注人ID
            Integer attendToUserType = req.msgBody.getInteger("attendToUserType");//被关注人的类别（1：个人    2：公司）

            Integer userType = getLoginSsoJson(req.token).getUserType();
            UserAttendModel userAttendModel = new UserAttendModel();

            Integer userId = null;
            Integer companyId = null;

            if(userType == 1){
                userAttendModel.setUserId(getLoginSsoJson(req.token).getUserId());
                userAttendModel.setCompanyId(0);
                userId = getLoginSsoJson(req.token).getUserId();
            }else{
                userAttendModel.setUserId(0);
                userAttendModel.setCompanyId(getLoginSsoJson(req.token).getCompanyId());
                companyId= getLoginSsoJson(req.token).getCompanyId();
            }
            userAttendModel.setAttendObjId(attendToUserId);
            userAttendModel.setAttendObjType(attendToUserType);

            //判断是否重复关注
            Integer attendFlag = userCenterService.getAttendRepeat(userId,companyId,attendToUserId);
            if(attendFlag >0){
                retmsg = "请勿重复关注";
                return handler.buildMsg(1000, retmsg, msgBody);
            }
            Integer count = userCenterService.addAttend(userAttendModel);
            msgBody = "{\"list\":"+JSONObject.toJSONString(count, SerializerFeature.WriteMapNullValue)+"}";

        }

        //取消关注
        else if(req.cmd.equals("cancelAttend")){

            Map<String,Object> map = new HashMap<>();
            map.put("attendId",req.msgBody.getInteger("attendId"));
            map.put("userId",req.msgBody.getInteger("userId"));
            map.put("companyId",req.msgBody.getInteger("companyId"));

            userCenterService.deleteUserAttend(map);

        }

        //绑定手机号
        else if(req.cmd.equals("boundUserPhone")){
            String newPhone = req.msgBody.getString("newPhone");

            Integer userType = getLoginSsoJson(req.token).getUserType();
            Integer userId = null;
            if(userType == 1){
                userId = getLoginSsoJson(req.token).getUserId();
            }else{
                userId = getLoginSsoJson(req.token).getCompanyId();
            }
            userCenterService.updateUserPhone(userId,newPhone);

        }

        //修改密码
        else if(req.cmd.equals("updatePassword")){
            String newPwd = req.msgBody.getString("newPwd");


            Integer userType = getLoginSsoJson(req.token).getUserType();
            Integer userId = null;
            if(userType == 1){
                userId = getLoginSsoJson(req.token).getUserId();
            }else{
                userId = getLoginSsoJson(req.token).getCompanyId();
            }

            userCenterService.updateUserPassword(userId,newPwd);

        }





        else{
            retcode = 1;
            retmsg = req.cmd+"在"+req.cmdtype+"中不存在";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    private LoginSsoJson getLoginSsoJson(String token){
        LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

        LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        return loginSsoJson;
    }
}
