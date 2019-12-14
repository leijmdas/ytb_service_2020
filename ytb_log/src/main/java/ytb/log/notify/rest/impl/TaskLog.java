package ytb.log.notify.rest.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.log.notify.model.*;
import ytb.log.notify.service.TaskNotifyService;
import ytb.log.notify.service.impl.TaskNotifyServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TaskLog {

    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";
    public MsgResponse process(MsgRequest req, RestHandler handler) {

        TaskNotifyService taskNotifyService = new TaskNotifyServiceImpl();

        //获取所有通知模板列表
        if (req.cmd.equals("getTemplateNotifyList")) {

            String notifyChannel = req.msgBody.getString("notifyChannel");
            if (notifyChannel != null && !"".equals(notifyChannel)) {
                Map map = new HashMap();
                map.put("notifyChannel",notifyChannel);
                List<Template_notifyModel> list = taskNotifyService.getTemplateNotifyList(map);
                JSONArray json = new JSONArray();
                for (Template_notifyModel tnm : list) {
                    json.add(tnm);
                }
                msgBody = "{\"list\":" + json.toJSONString() + "}";
            } else {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);

            }
        }   //根据ID查询通知模板
        else if (req.cmd.equals("getTemplateNotify")) {
            String templateId = req.msgBody.getString("templateId");
            if (templateId == null || "".equals(templateId)) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);

            } else {
                List<Template_notifyModel> list = taskNotifyService.getTemplateNotify(Integer.parseInt(templateId));
                JSONArray json = new JSONArray();
                for (Template_notifyModel tnm : list) {
                    json.add(tnm);
                }
                System.out.println(JSONObject.toJSONString(list));
                msgBody = "{\"list\":" + json.toJSONString() + "}";
            }
        }
        //添加通知模板
        else if (req.cmd.equals("addTemplateNotify")) {

            Template_notifyModel tnm = req.msgBody.toJavaObject(req.msgBody, Template_notifyModel.class);
            if (tnm != null) {
                taskNotifyService.addTemplateNotify(tnm);
            } else {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);

            }
        }
        //修改通知模板
        else if (req.cmd.equals("modifyTemplateNotify")) {
            Template_notifyModel tnm = req.msgBody.toJavaObject(req.msgBody, Template_notifyModel.class);
            if (tnm != null) {
                taskNotifyService.modifyTemplateNotify(tnm);
            } else {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);

            }
        }
        //删除通知模板
        else if (req.cmd.equals("delTemplateNotify")) {
            String templateId = req.msgBody.getString("templateId");
            if (templateId == null || "".equals(templateId)) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);

            } else {
                taskNotifyService.delTemplateNotify(Integer.parseInt(templateId));
            }

        }

        //查询所有策略模板
        else if (req.cmd.equals("getTemplatePolicyList")) {

            List<Template_policyModel> list = taskNotifyService.getTemplatePolicyList();
            JSONArray json = new JSONArray();
            if (list == null || "".equals(list)) {
                msgBody = "{\"list\":}";
            } else {
                for (Template_policyModel tpm : list) {
                    json.add(tpm);
                }
                msgBody = "{\"list\":" + json.toJSONString() + "}";
            }
        }
        //添加策略模板
        else if (req.cmd.equals("addTemplatePolicy")) {
            Template_policyModel tpm = req.msgBody.toJavaObject(req.msgBody, Template_policyModel.class);
            if (tpm == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);

            } else {
                taskNotifyService.addTemplatePolicy(tpm);
            }
        }
        //修改策略模板
        else if (req.cmd.equals("modifyTemplatePolicy")) {
            Template_policyModel tpm = req.msgBody.toJavaObject(req.msgBody, Template_policyModel.class);
            if (tpm == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);

            } else {
                taskNotifyService.modifyTemplatePolicy(tpm);
            }
        }
        //删除策略模板
        else if (req.cmd.equals("delTemplatePolicy")) {

            String templateId = req.msgBody.getString("templateId");
            if (templateId == null || "".equals(templateId)) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);

            } else {
                taskNotifyService.delTemplatePolicy(Integer.parseInt(templateId));
            }
        }
        //根据ID获取策略模板
        else if (req.cmd.equals("getTemplatePolicy")) {

            String templateId = req.msgBody.getString("templateId");
            if (templateId == null || "".equals(templateId)) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);

            } else {
                JSONArray json = new JSONArray();
                List<Template_policyModel> list = taskNotifyService.getTemplatePolicy(Integer.parseInt(templateId));
                for (Template_policyModel tpm : list) {
                    json.add(tpm);
                }
                msgBody = "{\"list\":" + json.toJSONString() + "}";
            }
        }

        //获取工作流任务
        else if (req.cmd.equals("getTaskLogTaskList")) {
            String userId =getLoginSsoJson(req.token).getUserId().toString();
            String object_type = req.msgBody.getString("object_type");
            if (object_type != null && !"".equals(object_type)) {
                List<TaskLog_TaskModel> list = taskNotifyService.getTaskLogTaskList(Integer.parseInt(object_type), userId);
                if (list.size() > 0) {
                    JSONArray json = new JSONArray();
                    for (TaskLog_TaskModel ttm : list) {
                        if (ttm == null) {
                            System.err.println(ttm);
                            continue;
                        }
                        JSONObject jsonObject = new JSONObject();
                        jsonObject = JSONObject.parseObject(ttm.getTaskParamIn());
                        jsonObject.fluentPut("taskId", ttm.getTaskId());
                        jsonObject.fluentPut("taskStat", ttm.getTaskStat());
                        json.add(jsonObject);
                    }
                    msgBody = "{\"list\":" + json.toJSONString() + "}";
                } else {
                    msgBody = "{\"list\":\"\"}";
                }
            } else {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
            }
        }
        //获取工作流任务通过id
        else if (req.cmd.equals("getTaskLogTaskById")) {

            String taskId = req.msgBody.getString("taskId");
            if (taskId != null && !"".equals(taskId)) {
                TaskLog_TaskModel taskLog_taskModel = taskNotifyService.getTaskLogTask(Integer.parseInt(taskId));
                JSONArray json = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject = JSONObject.parseObject(taskLog_taskModel.getTaskParamIn());
                jsonObject.fluentPut("taskId", taskLog_taskModel.getTaskId());
                jsonObject.fluentPut("taskStat", taskLog_taskModel.getTaskStat());
                json.add(jsonObject);
                msgBody = "{\"list\":" + json.toJSONString() + "}";
            }
        }
        //认证
        else if (req.cmd.equals("certificationUser")) {
            String objectType = req.msgBody.getString("objectType");
            Integer userId =getLoginSsoJson(req.token).getUserId();
            String taskParamIn = req.msgBody.getString("taskParamIn");
            if (taskParamIn != null && !"".equals(taskParamIn) && objectType != null && !"".equals(objectType) && userId != null && !"".equals(userId)) {
                taskNotifyService.certificationUser(userId, Integer.parseInt(objectType), taskParamIn);
            } else {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
            }
        }
        //审核
        else if (req.cmd.equals("approval")) {
            String taskId = req.msgBody.getString("taskId");
            String task_remark = req.msgBody.getString("task_remark");
            String task_stat = req.msgBody.getString("task_stat");
            if (taskId != null && !"".equals(taskId)) {
                taskNotifyService.approval(Integer.parseInt(taskId), task_remark, task_stat);
            } else {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
            }
        }
        //认领
        else if (req.cmd.equals("claimTask")) {
            String taskId = req.msgBody.getString("taskId");
            String userId = req.msgBody.getString("userId");
            if (taskId != null && !"".equals(taskId) && userId != null && !"".equals(userId)) {
                taskNotifyService.claimTask(userId, Integer.parseInt(taskId));
            } else {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
            }
        }

        //获取用户通知信息
        else if(req.cmd.equals("getTaskNotifyListByType")){
            Integer userId = getLoginSsoJson(req.token).getUserId();

            Map params = new HashMap();
            params.put("page",req.msgBody.getInteger("currPage"));
            params.put("limit",req.msgBody.getInteger("pageSize"));
            params.put("type",req.getMsgBody().getInteger("type"));
            params.put("userId",userId);

            Query query = new Query(params);
            List<Map<String,Object>>  list = taskNotifyService.getTaskNotifyListByType(query);
            Integer totalCount = taskNotifyService.getTaskNumByType(userId,req.getMsgBody().getInteger("type"));


            PageUtils pageUtil = new PageUtils(list, totalCount, query.getLimit(), query.getPage());
            Integer unReadCount = taskNotifyService.getUnReadNumber(query);
            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+",\"unReadCount\":"+unReadCount+"}";

        }

        //获取用户系统通知列表
        else if(req.cmd.equals("getUserSysNoticesList")){

            Integer userId = getLoginSsoJson(req.token).getUserId();

            Map params = new HashMap();
            params.put("page",req.msgBody.getInteger("currPage"));
            params.put("limit",req.msgBody.getInteger("pageSize"));
            params.put("userId",userId);

            Query query = new Query(params);
            List<SystemNoticeModel>  list = taskNotifyService.getUserSysNotices(query);


            PageUtils pageUtil = new PageUtils(list, list.size(), query.getLimit(), query.getPage());

            //未读的系统通知
            Integer unReadCount = taskNotifyService.getUnReadSysNoticesNumber(userId);

            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+",\"unReadCount\":"+unReadCount+"}";

        }



        //获取用户未读记录总数
        else if(req.cmd.equals("getUnReadCountTask")){

            Integer userId = getLoginSsoJson(req.token).getUserId();


            Map<String,Object> map = taskNotifyService.getUnReadTaskNotify(userId);

            Integer sysUnRead = taskNotifyService.getUnReadSysNoticesNumber(userId);

            map.put("systemCount",sysUnRead);

            msgBody="{\"list\":"+ JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue)+"}";

        }

        //单个删除
        else if(req.cmd.equals("delTaskNotify")){

            Integer notifyId = req.msgBody.getInteger("notifyId");

            taskNotifyService.deleteTaskNotifyById(notifyId);
        }

        //批量删除
        else if(req.cmd.equals("delTaskNotifyMore")){
            JSONArray array =  req.msgBody.getJSONArray("notifyId");
            taskNotifyService.deleteTaskNotifyByList(array);
        }


        else if(req.cmd.equals("sendMsg")){
            int toUserId = req.msgBody.getInteger("toUserId");//接收方ID
            int userId = req.msgBody.getInteger("userId");    //发送方ID
            String phone = req.msgBody.getString("phone");
            String type = req.msgBody.getString("type");      //发送通知类型(模板名称)
            int serviceType = req.msgBody.getInteger("serviceType");
            JSONObject jsonObject = JSON.parseObject(req.msgBody.getString("params"));
            taskNotifyService.sendMsg(userId,toUserId,jsonObject,type,phone,serviceType);
        }

        //标记已读
        else if(req.cmd.equals("setSignRead")){
            Integer notifyId = req.msgBody.getInteger("notifyId");

            taskNotifyService.setSignRead(notifyId);

        }

        //系统通知标记已读
        else if(req.cmd.equals("setSysNoticesSignRead")){
            String messageId = req.msgBody.getString("messageId");

            int userId = getLoginSsoJson(req.token).getUserId();
            taskNotifyService.addSysNoticesReadInfo(Integer.parseInt(messageId),userId);

        }


        //批量删除系统通知
        else if(req.cmd.equals("deleteSysNotices")){
            JSONArray msgArray = req.msgBody.getJSONArray("id");
            taskNotifyService.signSysMarkRead(msgArray,req.token);
        }

        //单个删除系统通知
        else if(req.cmd.equals("deleteOneSysNotices")){
            Integer id = req.msgBody.getInteger("id");
            Integer userId = getLoginSsoJson(req.token).getUserId();
            Integer readStatus = req.msgBody.getInteger("readStatus");
            taskNotifyService.deleteOneSysNotices(id,userId,readStatus);
        }


        else {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    private LoginSsoJson getLoginSsoJson(String token){
        LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

        LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        return loginSsoJson;
    }


}
