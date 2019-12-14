package ytb.user.rest.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.user.model.PurchModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.rest.impl
 * Author: ZCS
 * Date: Created in 2018/11/14 15:38
 */
public class AdvicePosted {

    int retcode = 0;
    String retmsg = "成功";
    String msgBody ="{}";

    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request) {

        UserCenterService userCenterService = new UserCenterServiceImpl();

        //新增用户接单范围
        if(req.cmd.equals("addUserProjectScode")){
            JSONArray json = req.msgBody.getJSONArray("json");

            userCenterService.addProjectScope(json,req.token);
        }

        //获取用户接单范围
        else if(req.cmd.equals("getProjectScodeList")){

            Integer userId = getLoginSsoJson(req.token).getUserId();
            Integer companyId = getLoginSsoJson(req.token).getCompanyId();

            if(getLoginSsoJson(req.token).getUserType() == 1){//说明是用户登录
                companyId = null;
            }else{
                userId = null;
            }

            JSONArray json = userCenterService.getProjectScopeList(userId,companyId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(json, SerializerFeature.WriteMapNullValue)+"}";
        }

        //删除用户接单范围
        else if(req.cmd.equals("deleteProjectScode")){
            Integer scodeId = req.msgBody.getInteger("scodeId");

            userCenterService.deleteProjectScode(scodeId);

        }

        //展示、隐藏用户接单范围
        else if(req.cmd.equals("showOrHideProjectScode")){
            Boolean isShow = req.msgBody.getBoolean("isShow");
            Integer scodeId = req.msgBody.getInteger("scodeId");

            userCenterService.updateProjectScodeShowOrHide(scodeId,isShow);

        }

        //获取意向采购清单
        else if(req.cmd.equals("getUserPurchase")){
            Integer userId = getLoginSsoJson(req.token).getUserId();
            Integer companyId = getLoginSsoJson(req.token).getUserId();
            Integer userType = getLoginSsoJson(req.token).getUserType();

            if(userType == 1){
                companyId = null;
            }else{
                userId = null;
            }

            List<PurchModel> list = userCenterService.getUserPurchaseList(userId,companyId);

            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //新增意向采购清单
        else if(req.cmd.equals("addUserPurchase")){
            JSONArray json = req.msgBody.getJSONArray("json");

            userCenterService.saveOrUpdateUserPurchase(json,req.token);
        }

        //删除意向采购清单
        else if(req.cmd.equals("deleteUserPurchase")){
            Integer purchaseId = req.msgBody.getInteger("purchaseId");

            userCenterService.deleteUserPurchase(purchaseId);
        }

        //获取推广销售的清单
        else if(req.cmd.equals("getUserSellList")){

            Integer userId = getLoginSsoJson(req.token).getUserId();
            Integer companyId = getLoginSsoJson(req.token).getUserId();
            Integer userType = getLoginSsoJson(req.token).getUserType();

            if(userType == 1){
                companyId = null;
            }else{
                userId = null;
            }

            List<PurchModel> list =  userCenterService.getUserSellList(userId,companyId);

            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //删除推广销售清单
        else if(req.cmd.equals("deleteUserSell")){

            Integer sellId = req.msgBody.getInteger("sellId");

            userCenterService.deleteUserSell(sellId);

        }

        //新增推广销售清单
        else if(req.cmd.equals("addUserSell")){
            JSONArray json = req.msgBody.getJSONArray("json");

            userCenterService.saveOrUpdateUserSell(json,req.token);
        }

        //获取用户的广告贴
        else if(req.cmd.equals("getUserAdvicesInfo")){

            Integer userId = req.msgBody.getInteger("userId");
            Integer companyId = req.msgBody.getInteger("companyId");
            Integer userType = req.msgBody.getInteger("userType");

            if(userType ==1){
                companyId = null;
            }else {
                userId = null;
            }

            Map<String,Object> map = new HashMap<>();

            List<PurchModel> selllist =  userCenterService.getUserSellList(userId,companyId);

            List<PurchModel> purchlist = userCenterService.getUserPurchaseList(userId,companyId);

            JSONArray json = userCenterService.getProjectScopeList(userId,companyId);

            map.put("scodeList",json);//项目接单范围
            map.put("selllist",selllist);//推广销售
            map.put("purchlist",purchlist);//意向采购
            msgBody="{\"list\":"+ JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue)+"}";

        }

        //设置推广销售清单浏览数量
        else if(req.cmd.equals("setSellViewNumber")){
                Integer sellId = req.msgBody.getInteger("sellId");
                userCenterService.updateSellViewCountById(sellId);
        }

        //设置意向采购单浏览数量
        else if(req.cmd.equals("setPurchaseViewNumber")){
            Integer purchaseId = req.msgBody.getInteger("purchaseId");
            userCenterService.updatePurchaseViewNumberById(purchaseId);
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


