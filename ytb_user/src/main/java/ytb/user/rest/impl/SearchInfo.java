package ytb.user.rest.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.user.service.SearchPersonService;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.SearchPersonServiceImpl;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hj
 * @Description //搜索页面需要的信息
 * @Date 2018/11/19
 **/
public class SearchInfo {

    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request) {

        if (req.cmd.equals("getUserInfoById")) {//通过userId获取用户基本信息
            Map<String, Object> map = new HashMap<>();
            UserCenterService userCenterService = new UserCenterServiceImpl();
            SearchPersonService searchPersonService = new SearchPersonServiceImpl();

            int userId = req.msgBody.getInteger("userId");
            Integer userType = getLoginSsoJson(req.token).getUserType();

            Map<String, Object> userMap = userCenterService.getUserBaseInfo(userId);
            Map<String, Object> edcution = searchPersonService.getEducationByUserId("" + userId);
            //专业能力标签
            JSONArray majorTagList = userCenterService.getUserTagById(userId, 2);

            //接单范围
            JSONArray json = userCenterService.getProjectScopeList(userId, null);

            //是否重复关注的标志
            Integer uId = null;
            Integer cId = null;
            if (userType == 1) {
                uId = getLoginSsoJson(req.token).getUserId();
                cId = null;
            } else {
                uId = null;
                cId = getLoginSsoJson(req.token).getCompanyId();
            }


            Integer attendFlag = userCenterService.getAttendRepeat(uId, cId, userId);


            Map<String, Object> getFCityByAreaId = userCenterService.getFCityByAreaId((int) userMap.get(
                  "areaId"));


            map.put("userInfo", userMap);

            map.put("parentCityInfo", getFCityByAreaId);

            map.put("userEduInfo", edcution);
            map.put("majorTagList", majorTagList);
            map.put("scopeList", json);
            map.put("attendFlag", attendFlag);
            msgBody = "{\"list\":" + JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue) + "}";
        } else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    private LoginSsoJson getLoginSsoJson(String token) {
        LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

        LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        return loginSsoJson;
    }
}
