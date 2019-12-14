package ytb.log.notify.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.activiti.engine.task.Task;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import ytb.activiti.service.ActivitiManagerService;
import ytb.activiti.service.ActivitiService;
import ytb.activiti.service.impl.ActivitiManagerServiceImpl;
import ytb.activiti.service.impl.ActivitiServiceImpl;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.log.notify.dao.*;
import ytb.log.notify.model.*;
import ytb.log.notify.service.TaskNotifyService;

import ytb.log.smslog.service.TaskLogSmsCodeService;
import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;
import ytb.log.utils.MyBatisUtils;
import ytb.log.utils.Parser;
import ytb.common.RestMessage.MsgRequest;

import java.util.*;

@Service
public class TaskNotifyServiceImpl implements TaskNotifyService {
    MsgRequest req = new MsgRequest();
    ActivitiService activitiService = new ActivitiServiceImpl();
    ActivitiManagerService activitiManagerService = new ActivitiManagerServiceImpl();
    TaskLogSmsCodeService taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();

    @Override
    public List getTaskNotifyList() {

        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            return tnMapper.getTaskNotifyList();
        } finally {
            session.close();
        }
    }

    @Override
    public TaskLog_notifyModel getTaskNotify(int notify_id) {
        SqlSession session = MyBatisUtils.getSession();
        TaskLog_notifyModel taskLog_notifyModel = null;
        try {
            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            taskLog_notifyModel = tnMapper.getTaskNotify(notify_id);
        } finally {
            session.close();
        }
        return taskLog_notifyModel;
    }

    @Override
    public void addTaskNotify(TaskLog_notifyModel tnm) {

        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            tnMapper.addTaskNotify(tnm);
            session.commit();
        } finally {
            session.close();
        }

    }

    @Override
    public void deleteTaskNotifyByList(JSONArray notifyIds) {

        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            List<Integer> list = new ArrayList();
            for (int i = 0; i < notifyIds.size(); i++) {

                list.add(notifyIds.getInteger(i));
            }
            tnMapper.deleteTaskNotifyByList(list);
            session.commit();
        } finally {
            session.close();
        }

    }

    @Override
    public void deleteTaskNotifyById(Integer id) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            tnMapper.deleteTaskNotifyById(id);
            session.commit();
        } finally {
            session.close();
        }
    }


    @Override
    public void modifyTaskNotify(TaskLog_notifyModel tnm) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            tnMapper.modifyTaskNotify(tnm);
            session.commit();

        } finally {
            session.close();
        }

    }

    //获取通知模板列表
    @Override
    public List getTemplateNotifyList(Map map) {

        SqlSession session = MyBatisUtils.getSession();
        List<Template_notifyModel> list = null;

        try {
            TemplateNotifyMapper tnMapper = session.getMapper(TemplateNotifyMapper.class);

            list = tnMapper.getTaskTemplateList(map);

        } finally {
            session.close();
        }
        return list;
    }

    //根据ID获取通知模板
    @Override
    public List<Template_notifyModel> getTemplateNotify(int template_id) {

        SqlSession session = MyBatisUtils.getSession();
        try {

            TemplateNotifyMapper tnMapper = session.getMapper(TemplateNotifyMapper.class);
            return tnMapper.getTaskTemplate(template_id);
        } finally {
            session.close();
        }
    }

    //添加通知模板
    @Override
    public void addTemplateNotify(Template_notifyModel tnm) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TemplateNotifyMapper tnMapper = session.getMapper(TemplateNotifyMapper.class);
            tnMapper.addTaskTemplate(tnm);
            session.commit();
        } finally {
            session.close();
        }

    }

    //删除通知模板
    @Override
    public void delTemplateNotify(int template_id) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TemplateNotifyMapper tnMapper = session.getMapper(TemplateNotifyMapper.class);
            tnMapper.delTaskTemplate(template_id);
            session.commit();
        } finally {
            session.close();
        }

    }

    //修改通知模板
    @Override
    public void modifyTemplateNotify(Template_notifyModel tnm) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TemplateNotifyMapper tnMapper = session.getMapper(TemplateNotifyMapper.class);
            tnMapper.modifyTaskTemplate(tnm);
        } finally {
            session.close();
        }
    }

    @Override
    public List<TaskLog_TaskModel> getTaskLogTaskList(int objectType, String userId) {
        SqlSession session = MyBatisUtils.getSession();
        List<TaskLog_TaskModel> list = new ArrayList<TaskLog_TaskModel>();
        String roleId = "2020";
        try {
            TaskLogTaskMapper tltMapper = session.getMapper(TaskLogTaskMapper.class);
            List<Task> tasks = activitiManagerService.getActivitiTaskByGroup(roleId);
            List<Task> taskList = activitiManagerService.getActivitiTask(userId);
            if (tasks.size() > 0) {
                for (Task task : tasks) {
                    TaskLog_TaskModel tlt = tltMapper.getTaskLogTaskByObjType(task.getProcessInstanceId(), objectType);
                    if (tlt != null) {
                        list.add(tlt);
                    }
                }
            }
            if (taskList.size() > 0) {
                for (Task task : taskList) {
                    TaskLog_TaskModel tlt1 = tltMapper.getTaskLogTaskByObjType(task.getProcessInstanceId(), objectType);
                    if (tlt1 != null) {
                        list.add(tlt1);
                    }
                }
            }

        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void addTaskLogTask(TaskLog_TaskModel tlt) {

        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogTaskMapper tltMapper = session.getMapper(TaskLogTaskMapper.class);
            tltMapper.addTaskLogTask(tlt);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void delTaskLogTask(int taskId) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogTaskMapper tltMapper = session.getMapper(TaskLogTaskMapper.class);
            tltMapper.delTaskLogTask(taskId);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public TaskLog_TaskModel getTaskLogTask(int taskId) {
        SqlSession session = MyBatisUtils.getSession();
        TaskLog_TaskModel tlt = null;
        try {
            TaskLogTaskMapper tltMapper = session.getMapper(TaskLogTaskMapper.class);
            tlt = tltMapper.getTaskLogTask(taskId);
        } finally {
            session.close();
        }
        return tlt;
    }

    @Override
    public void modifyTaskLogTask(TaskLog_TaskModel ttm) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogTaskMapper tltMapper = session.getMapper(TaskLogTaskMapper.class);
            tltMapper.modifyTaskLogTask(ttm);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Template_policyModel> getTemplatePolicyList() {
        SqlSession session = MyBatisUtils.getSession();
        List<Template_policyModel> list = null;
        try {
            TemplatePolicyMapper tpMapper = session.getMapper(TemplatePolicyMapper.class);
            list = tpMapper.getTemplatePolicyList();

        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void addTemplatePolicy(Template_policyModel tpm) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TemplatePolicyMapper tpMapper = session.getMapper(TemplatePolicyMapper.class);
            tpMapper.addTemplatePolicy(tpm);
            session.commit();
        } finally {
            session.close();
        }


    }

    @Override
    public void delTemplatePolicy(int template_id) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TemplatePolicyMapper tpMapper = session.getMapper(TemplatePolicyMapper.class);
            tpMapper.delTemplatePolicy(template_id);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void modifyTemplatePolicy(Template_policyModel tpm) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TemplatePolicyMapper tpMapper = session.getMapper(TemplatePolicyMapper.class);
            tpMapper.modifyTemplatePolicy(tpm);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Template_policyModel> getTemplatePolicy(int template_id) {
        SqlSession session = MyBatisUtils.getSession();
        List<Template_policyModel> list = null;
        try {
            TemplatePolicyMapper tpMapper = session.getMapper(TemplatePolicyMapper.class);
            list = tpMapper.getTemplatePolicy(template_id);

        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Template_notifyModel getTemplateNotifyByName(String templateName) {

        try(SqlSession session = MyBatisUtils.getSession()) {
            TemplateNotifyMapper tnMapper = session.getMapper(TemplateNotifyMapper.class);
            return  tnMapper.getTemplateNotify(templateName);

        }

    }

    //工作流任务
    @Override
    public void sendNotify(String proc_code, JSONObject tasks) {
        //启动工作流任务实例,完成申请任务,并返回实例ID
        String processId = activitiService.applicationTask(proc_code, tasks);
        TaskLog_TaskModel ttm = tasks.toJavaObject(tasks, TaskLog_TaskModel.class);
        ttm.setActivitiInstId(processId);
        modifyTaskLogTask(ttm);
    }

    @Override
    public Template_policyModel getTemplatePolicyByType(int templateType, int objectType) {
        SqlSession session = MyBatisUtils.getSession();
        Template_policyModel tpm = null;
        try {
            TemplatePolicyMapper tpMapper = session.getMapper(TemplatePolicyMapper.class);
            tpm = tpMapper.getTemplatePolicyByType(templateType, objectType);
        } finally {
            session.close();
        }
        return tpm;
    }

    //认证
    @Override
    public int certificationUser(int userId, int objectType, String taskParamIn) {

        int status = 0;
        try {

            Template_policyModel tpm = getTemplatePolicyByType(2, objectType);//查询模板
            TaskLog_TaskModel ttm = new TaskLog_TaskModel();//新增任务
            ttm.setTemplateId(tpm.getTemplateId());
            ttm.setTemplateType(2);
            ttm.setUserId(userId);
            ttm.setToUserId(2020);//测试使用
            ttm.setTaskParamIn(taskParamIn);
            addTaskLogTask(ttm);
            //工作流模板proc_code写死
            sendNotify("review", (JSONObject) JSONObject.toJSON(ttm));
        } catch (Exception e) {
            status = 1;
            e.printStackTrace();
        } finally {
            return status;
        }

    }

    //审核认证
    @Override
    public void approval(int taskId, String task_remark, String task_stat) {
        TaskLog_TaskModel tlt = getTaskLogTask(taskId);
        activitiService.approvalTask(tlt.getActivitiInstId());
        tlt.setTaskRemark(task_remark);
        tlt.setFinishTime(new Date());
        tlt.setTaskStat(Integer.parseInt(task_stat));
        modifyTaskLogTask(tlt);
        List<Template_policyModel> list = getTemplatePolicy(tlt.getTemplateId());
        if(list.get(0).getObjectType()==11){  //个人资料审核
            int status = 0;
            if ("2".equals(task_stat)) {
                status = 3;
            } else if ("3".equals(task_stat)) {
                status = 2;
            }
            personalDataReview(tlt,status);
        }else if(list.get(0).getObjectType()==12){//公司资料审核
            if("2".equals(task_stat)) {
                companyDataReview(tlt);
            }
        }else if(list.get(0).getObjectType()==51){//提现审核
            if("2".equals(task_stat)) {
                String callBack =  withdrawApproval(tlt);
              if(callBack.equals("ERROR")){
                  tlt.setTaskStat(3);
                  modifyTaskLogTask(tlt);
              }
            }
        }else if(list.get(0).getObjectType()==52){//钱包审核

        }

    }
    private void personalDataReview(TaskLog_TaskModel tlt,int status){

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://project.youtobon.com/rest/ytbuser";
        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "usercenter";
        req.cmd = "updateUserStatus";
        req.msgBody = JSONObject.parseObject("{\"userId\":" + tlt.getUserId() + ",\"status\":" + status + "}");
        String data = new Gson().toJson(req).toString();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, data, String.class);
        JSONObject param = new JSONObject();
        param.fluentPut("status", tlt.getTaskRemark());

    }

    private void companyDataReview(TaskLog_TaskModel tlt){

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://project.youtobon.com/rest/ytbuser";
        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "companyCenter";
        req.cmd = "updateCompanyInfo";
        JSONObject jsonObject = JSON.parseObject(tlt.getTaskParamIn());
        req.msgBody = JSONObject.parseObject("{\"compImgA\":" + jsonObject.get("compImgA") + ",\"compImgB\":" + jsonObject.get("compImgB")+ ",\"companyId\":"+jsonObject.get("companyId")+"}");
        String data = new Gson().toJson(req).toString();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, data, String.class);
        JSONObject param = new JSONObject();
        param.fluentPut("status", tlt.getTaskRemark());

    }

   /* private String test(TaskLog_TaskModel tlt){

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://project.youtobon.com/rest/pay";
        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "aliPay";
        req.cmd = "transferAccounts";
        JSONObject jsonObject = JSON.parseObject(tlt.getTaskParamIn());
        req.msgBody = jsonObject; *//*JSONObject.parseObject("{\"outBizNo\":" + jsonObject.get("outBizNo") + ",\"payeeType\":" +
                jsonObject.get("payeeType")+ ",\"payeeAccount\":"+jsonObject.get("payeeAccount")+",\"amount\":"+jsonObject.get("amount")+",\"payerShowName\":\"有托邦提现\",\"payeeRealName\":"+jsonObject.get("payeeRealName")+",\"remark\":\"有托邦提现\"}");
*//*      String data = new Gson().toJson(req).toString();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, data, String.class);
        return  responseEntity.toString();
    }*/


    private  String withdrawApproval( TaskLog_TaskModel tlt){

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.1.88/rest/pay";
   /*     req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "aliPay";
        req.cmd = "transferAccounts";
        JSONObject jsonObject = JSON.parseObject(*//*tlt.getTaskParamIn()*//*"{\"tradeId\":\"1155\",\"balance\":\"0.17\",\"totalBalance\":\"0.2\",\"paymentBalance\":\"null\",\"serviceFee\":\"0\",\"tax\":\"0\",\"fee\":\"0.03\",\"status\":\"1\",\"tradeType\":\"20\",\"acId\":\"62\",\"tradeOutId\":\"147\",\"tradeSubtype\":\"3\",\"userId\":\"394\",\"companyId\":\"394\",\"innerId\":\"62\",\"cardHolder\":\"陈重华\",\"bankName\":\"null\",\"branchName\":\"null\",\"cardHolderId\":\"13528818072\",\"accountType\":\"3\",\"outId\":\"null\",}");
        req.msgBody = jsonObject;
        String data = new Gson().toJson(req).toString();
        System.out.print("传递的参数是："+data);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(type);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, data, String.class);*/


        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "aliPay";
        req.cmd = "transferAccounts";
        JSONObject jsonObj = JSON.parseObject(tlt.getTaskParamIn());
        req.msgBody = jsonObj;

        HttpEntity<String> formEntity = new HttpEntity<String>(req.toString(), headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        return  result;
    }

    public static  void main(String argue[]){

        TaskNotifyServiceImpl  kk = new TaskNotifyServiceImpl();
        String ss =  kk.withdrawApproval(null);
        System.out.print("fnmk;sdfk："+ss);
    }



    //认领任务
    @Override
    public void claimTask(String userId, int taskId) {

        TaskLog_TaskModel tlt = getTaskLogTask(taskId);
        activitiService.asSignMents(userId, tlt.getActivitiInstId());
        // tlt.setCreateBy(Integer.parseInt(userId));
        tlt.setAcceptTime(new Date());
        tlt.setTaskStat(1);//修改状态为可审核状态
        modifyTaskLogTask(tlt);
    }

    //发送通知
    public int sendMsg(int userId, int toUserId, JSONObject param, String type, String phone, int serviceType) {

        TaskLog_notifyModel taskLogNotifyModel = new TaskLog_notifyModel();
        Template_notifyModel template_notifyModel = getTemplateNotifyByName(type);
        taskLogNotifyModel.setTemplateParam(param.toString());
        //taskLogNotifyModel.setServiceType(1);
        taskLogNotifyModel.setServiceType(serviceType);
        taskLogNotifyModel.setToUserId(toUserId);
        taskLogNotifyModel.setUserId(userId);
        taskLogNotifyModel.setTemplateId(template_notifyModel.getTemplateId());
        String template = getTemplate(template_notifyModel.getParamCount(), param, template_notifyModel.getTemplate());
        taskLogNotifyModel.setNotify(template);

        String processId = activitiService.sendNotify("Notify", (JSONObject) JSONObject.toJSON(taskLogNotifyModel));
        if (template_notifyModel.getNotifyChannel() == 2 && phone != null && !"".equals(phone)) {
            taskLogSmsCodeService.sendSms(phone, template_notifyModel.getAliSmsTemplate(), param.toJSONString());
        }
        taskLogNotifyModel.setProcId(processId);
        addTaskNotify(taskLogNotifyModel);
        return 0;
    }

    @Override
    public List<Map<String,Object>> getTaskNotifyListByType(Map<String,Object> map) {
        SqlSession session = MyBatisUtils.getSession();
        List<Map<String,Object>> list = null;
        try {
            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            list = tnMapper.getTaskNotifyListByType(map);
        } finally {

            session.close();
        }
        return list;
    }

    @Override
    public Map<String,Object> getUnReadTaskNotify(Integer toUserId) {
        SqlSession session = MyBatisUtils.getSession();
        try {

            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            Map<String,Object> map = tnMapper.getUnReadTaskNotify(toUserId);

            return map;
        } finally {
            session.close();
        }

    }

    @Override
    public Integer getUnReadNumber(Map<String,Object> map) {
        SqlSession session = MyBatisUtils.getSession();
        try {

            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            Integer count = tnMapper.getUnReadNumber(map);

            return count;
        } finally {
            session.close();
        }

    }

    @Override
    public Integer getTaskNumByType(Integer toUserId, Integer type) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            Integer count = tnMapper.getTaskNumByType(toUserId,type);

            return count;
        } finally {
            session.close();
        }
    }

    @Override
    public void setSignRead(Integer notifyId) {
        SqlSession session = MyBatisUtils.getSession();
        try {

            TaskLogNotifyMapper tnMapper = session.getMapper(TaskLogNotifyMapper.class);
            tnMapper.setSignRead(notifyId);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Map<String,Object>> getMessageList(Map<String, Object> map) {

        SqlSession session = MyBatisUtils.getSession();
        List<Map<String,Object>> list = null;
        try {

            SystemNoticeMapper tnMapper = session.getMapper(SystemNoticeMapper.class);
            list = tnMapper.getMessageList(map);
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public Integer addMessageInfo(SystemNoticeModel systemNoticeModel) {

        SqlSession session = MyBatisUtils.getSession();

        try {

            SystemNoticeMapper tnMapper = session.getMapper(SystemNoticeMapper.class);
            tnMapper.addMessageInfo(systemNoticeModel);
            session.commit();
            return systemNoticeModel.getId();
        } finally {
            session.close();
        }
    }

    @Override
    public SystemNoticeModel getMessageById(Integer id) {
        SqlSession session = MyBatisUtils.getSession();
        SystemNoticeModel systemNoticeModel = null;
        try {

            SystemNoticeMapper tnMapper = session.getMapper(SystemNoticeMapper.class);
            systemNoticeModel = tnMapper.getMessageById(id);
            session.commit();
            return systemNoticeModel;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteSysNotices(Integer id) {
        SqlSession session = MyBatisUtils.getSession();
        try {

            SystemNoticeMapper tnMapper = session.getMapper(SystemNoticeMapper.class);
             tnMapper.deleteSysNotices(id);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateSysNotices(SystemNoticeModel systemNoticeModel) {
        SqlSession session = MyBatisUtils.getSession();
        try {

            SystemNoticeMapper tnMapper = session.getMapper(SystemNoticeMapper.class);
            tnMapper.updateSysNotices(systemNoticeModel);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public Integer getUnReadSysNoticesNumber(Integer userId) {
        SqlSession session = MyBatisUtils.getSession();
        try {

            SystemNoticeMapper tnMapper = session.getMapper(SystemNoticeMapper.class);
           Integer count = tnMapper.getUnReadSysNoticesNumber(userId);
           return count;
        } finally {
            session.close();
        }
    }

    @Override
    public List<SystemNoticeModel> getUserSysNotices(Map<String,Object> map) {
        SqlSession session = MyBatisUtils.getSession();
        List<SystemNoticeModel> list = null;
        try {

            SystemNoticeMapper tnMapper = session.getMapper(SystemNoticeMapper.class);
            list = tnMapper.getUserSysNotices(map);
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public Integer addSysNoticesReadInfo(Integer messageId,Integer userId) {
        SqlSession session = MyBatisUtils.getSession();
        try {

            SystemNoticesReadMapper tnMapper = session.getMapper(SystemNoticesReadMapper.class);

            SystemNoticesReadModel systemNoticesReadModel = new SystemNoticesReadModel();
            systemNoticesReadModel.setMessageId(messageId);
            systemNoticesReadModel.setRecId(userId);
            systemNoticesReadModel.setDeleteFlag(1);

            Integer id =  tnMapper.addSysNoticesReadInfo(systemNoticesReadModel);
            session.commit();
            return id;
        } finally {
            session.close();
        }
    }

    @Override
    public void signSysMarkRead(JSONArray msgArray,String token) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            SystemNoticesReadMapper tnMapper = session.getMapper(SystemNoticesReadMapper.class);
            List<SystemNoticesReadModel> readList = new ArrayList<>();
            List<Integer> unReadList = new ArrayList<>();
            for(int i = 0;i<msgArray.size();i++){
                JSONObject jo = msgArray.getJSONObject(i);
                Integer messageId = jo.getInteger("id");
                Integer readStatus = jo.getInteger("readStatus");
                if(readStatus == 1){//表示未读
                    SystemNoticesReadModel systemNoticesReadModel = new SystemNoticesReadModel();
                    systemNoticesReadModel.setDeleteFlag(2);
                    systemNoticesReadModel.setStatus(2);
                    systemNoticesReadModel.setRecId(getLoginSsoJson(token).getUserId());
                    systemNoticesReadModel.setMessageId(messageId);
                    readList.add(systemNoticesReadModel);
                }else{
                    unReadList.add(messageId);
                }
            }

            if(unReadList.size()>0){
                tnMapper.signSysMarkRead(unReadList,getLoginSsoJson(token).getUserId());
            }

            if(readList.size()>0){
                tnMapper.addSysNoticesReadInfoByList(readList);
            }

            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteOneSysNotices(Integer id, Integer userId,Integer status) {
        SqlSession session = MyBatisUtils.getSession();
        try {

            if(status == 2){//表示已读
                SystemNoticesReadMapper tnMapper = session.getMapper(SystemNoticesReadMapper.class);
                tnMapper.deleteOneSysNotices(id,userId);
                session.commit();
            }else{//表示未读
                SystemNoticesReadMapper tnMapper = session.getMapper(SystemNoticesReadMapper.class);

                SystemNoticesReadModel systemNoticesReadModel = new SystemNoticesReadModel();
                systemNoticesReadModel.setMessageId(id);
                systemNoticesReadModel.setRecId(userId);
                systemNoticesReadModel.setStatus(2);
                systemNoticesReadModel.setDeleteFlag(2);
                tnMapper.addSysNoticesReadInfo(systemNoticesReadModel);
                session.commit();
            }


        } finally {
            session.close();
        }
    }

    @Override
    public int getMessageTotal(String title,int type) {
        SqlSession session = MyBatisUtils.getSession();
        try {

            SystemNoticeMapper tnMapper = session.getMapper(SystemNoticeMapper.class);

           return tnMapper.getMessageTotal(title,type);
        } finally {
            session.close();
        }
    }



    //获取发送的消息
    private String getTemplate(int paramCount, JSONObject param, String notifyTemplate) {
        String template = "";

        if (paramCount == 0) {
            template = notifyTemplate;
        } else if (paramCount == 1) {
            template = Parser.parse0(notifyTemplate, param.get("params1"));
        } else if (paramCount == 2) {
            template = Parser.parse0(notifyTemplate, param.get("params1"), param.get("params2"));
        } else if (paramCount == 3) {
            template = Parser.parse0(notifyTemplate, param.get("params1"), param.get("params2"), param.get("params3"));
        }
        return template;
    }

    private LoginSsoJson getLoginSsoJson(String token){
        LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

        LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        return loginSsoJson;
    }

}