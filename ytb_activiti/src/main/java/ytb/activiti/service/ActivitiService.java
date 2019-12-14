package ytb.activiti.service;


import com.alibaba.fastjson.JSONObject;

/**
 * Package: ytb.activiti.activitiService
 * Author: ZCS
 * Date: Created in 2018/8/23 16:35
 */
public interface ActivitiService {

    /*//启动通知工作流实例
    String  startActiviti(String proc_code,JSONObject notify);*/

    //完成任务
    void  completeTask(String taskId);

    //分配个人任务
    void  asSignMents(String userId, String processId);

    //发送通知
    String sendNotify(String proc_code,JSONObject notify);
    //申请
    String applicationTask(String proc_code, JSONObject task);
    //审核
     void approvalTask(String processId);
}
