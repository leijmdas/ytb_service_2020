package ytb.log.notify.service;




import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import ytb.log.notify.model.*;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.activiti.service
 * Author: ZCS
 * Date: Created in 2018/8/23 16:35
 */
public interface TaskNotifyService {

    //获取通知列表
    List getTaskNotifyList();

    //添加通知
    void addTaskNotify(TaskLog_notifyModel tnm);

    //修改通知
    void modifyTaskNotify(TaskLog_notifyModel tnm);

    //批量删除通知
    void deleteTaskNotifyByList(JSONArray notify_id);

    void deleteTaskNotifyById(Integer id);

    //获取通知
    TaskLog_notifyModel getTaskNotify(int notify_id);

    //获取所有通知模板列表
    List<Template_notifyModel> getTemplateNotifyList(Map map);

    //添加通知模板
    void addTemplateNotify(Template_notifyModel tnm );

    //修改通知模板
    void modifyTemplateNotify( Template_notifyModel tnm);

    //删除通知模板
    void delTemplateNotify( int template_id);

    //获取通知
    List<Template_notifyModel> getTemplateNotify(int template_id);

    //获取所有工作流任务
    List<TaskLog_TaskModel> getTaskLogTaskList(int objectType,String userId);

    //添加工作流任务
    void addTaskLogTask(TaskLog_TaskModel ttm);

    //修改工作流任务
    void modifyTaskLogTask(TaskLog_TaskModel ttm);

    //删除工作流任务
    void delTaskLogTask(int taskId);

    //通过id获取工作流任务
    TaskLog_TaskModel getTaskLogTask(int taskId);

    //查询所有策略模板
    List<Template_policyModel> getTemplatePolicyList();

    Template_policyModel getTemplatePolicyByType(int template_type,int object_type);

    //添加策略模板
    void addTemplatePolicy(Template_policyModel tpm);

    //修改策略模板
    void modifyTemplatePolicy(Template_policyModel tpm);

    //删除策略模板
    void delTemplatePolicy(int template_id);

    //根据Id获取策略模板
    List<Template_policyModel> getTemplatePolicy(int template_id);

    //工作流
    void sendNotify(String proc_code,JSONObject tasks);

    //认证
    int certificationUser(int userId, int objectType,String taskParamIn);

    //审核
     void approval(int taskId,String task_remark,String task_stat);

    //认领任务
     void claimTask(String userId,int taskId);

    //发送通知
     int sendMsg(int userId,int toUserId,JSONObject param,String type,String phone,int serviceType);

    //通过模板名称获取模板
    Template_notifyModel  getTemplateNotifyByName(String templateName);

    //获取用户的通知类型
    List<Map<String,Object>> getTaskNotifyListByType(Map<String,Object> map);

    //获取用户未读的任务通知的数据量
    Map<String,Object> getUnReadTaskNotify(Integer toUserId);

    //获取未读的任务的总数
    Integer getUnReadNumber(Map<String,Object> map);

    //获取不同通知类别的总数
    Integer getTaskNumByType(@Param("userId") Integer toUserId, @Param("type") Integer type);

    //标记已读
    void setSignRead(Integer notifyId);


    //获取列表数据（系统通知）
    List<Map<String,Object>>  getMessageList(Map<String,Object> map);

    //新增系统通知（系统通知）
    Integer addMessageInfo(SystemNoticeModel systemNoticeModel);

    //获取系统通知ById
    SystemNoticeModel getMessageById(Integer id);

    //删除系统通知
    void deleteSysNotices(Integer id);

    //修改系统通知
    void updateSysNotices(SystemNoticeModel systemNoticeModel);

    //查询未读的系统的通知
    Integer getUnReadSysNoticesNumber(Integer userId);

    //获取用户系统通知列表
    List<SystemNoticeModel>  getUserSysNotices(Map<String,Object> map);


    //新增用户系统消息记录
    Integer addSysNoticesReadInfo(Integer messageId,Integer userId);

    //标识系统已读
    void signSysMarkRead(JSONArray id,String token);

    //标记单个删除
    void deleteOneSysNotices(Integer id,Integer userId,Integer status);


    //获取总条数
    int getMessageTotal(String title,int type);
}
