package ytb.bangbang.service.impl;

import ytb.bangbang.model.UserBaseInfo;
import ytb.bangbang.service.GetUserInfoService;
import ytb.user.model.UserEducationModel;
import ytb.user.model.UserInfoModel;
import ytb.user.model.UserLoginModel;
import ytb.user.model.UserTagModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取好友用户详情资料信息
 * data : 数据
 * 基本资料
 * 专业标签
 * 最高学历
 * errorMsg : 错误信息
 */
public class GetUserInfoServiceImpl  implements GetUserInfoService {
    @Override
    public Map getUserInfo(int friendId) {

        Map result=new HashMap();
        try{
            //基本资料
        UserCenterService userLoginService = new UserCenterServiceImpl();
        UserInfoModel mineUser = userLoginService.getUserInfoById(friendId);
        UserInfoModel userInfoModel=userLoginService.getUserInfoById(friendId);
        UserBaseInfo userBaseInfo=new UserBaseInfo();
        userBaseInfo.setNickName(mineUser.getNickName());
        userBaseInfo.setSex(userInfoModel.getSex());
        userBaseInfo.setBirthday(userInfoModel.getBirthday());
        userBaseInfo.setUserSign(mineUser.getUserSign());
        userBaseInfo.setCreditGread(userInfoModel.getCreditGrade());
        //专业标签  需要修改，等待表修改好后再做
        List<UserTagModel> userTagList= userLoginService.getUserTagById(friendId);
        //最高学历
        List<Map<String,Object>> userEducationModelList = userLoginService.getUserEducationById(friendId);
        //项目经历


        result.put("userInfo", userBaseInfo);
        result.put("userTagList", userTagList);
        result.put("userEducation", userEducationModelList);
        return  result;
        }catch (Exception e){
            e.printStackTrace();
            return result = null;
        }
    }
}
