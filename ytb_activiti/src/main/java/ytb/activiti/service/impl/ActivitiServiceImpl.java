package ytb.activiti.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.task.Task;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.context.ApplicationContext;
import ytb.activiti.service.utils.ActivitiUtil;
import ytb.activiti.service.ActivitiService;

import java.util.HashMap;
import java.util.Map;


/**
 * 注：还未加入Spring
 * Package: ytb.activiti.activitiService.impl
 * Author: ZCS
 * Date: Created in 2018/8/23 16:35
 */
public  class ActivitiServiceImpl implements ActivitiService {




    //认领任务
    public void asSignMents(String userId,String processId) {
        ApplicationContext ctxt =ActivitiUtil.getCtxt();
        TaskService taskService=ctxt.getBean("taskService",TaskService.class);
        Task tasks = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        taskService.claim(tasks.getId(),userId);


    }
    //完成任务
    public void completeTask(String taskId) {
        ApplicationContext ctxt =ActivitiUtil.getCtxt();
        TaskService taskService=ctxt.getBean("taskService",TaskService.class);
        taskService.complete(taskId);

    }

    //启动通知工作流实例
    private String startActiviti(String proc_code,JSONObject notify) {
        ApplicationContext ctxt =ActivitiUtil.getCtxt();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("template_param",notify.toString());
        variables.put("userId",notify.get("userId"));
        RuntimeService runtimeService=ctxt.getBean("runtimeService",RuntimeService.class);
        String processId  = runtimeService.startProcessInstanceByKey(proc_code,variables).getId();
        return processId;

    }
    //启动任务工作流实例
    private String startTaskActiviti(String proc_code,JSONObject task) {
        ApplicationContext ctxt =ActivitiUtil.getCtxt();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("roleId",task.get("toUserId").toString());
        variables.put("userId",task.get("userId").toString());
        RuntimeService runtimeService=ctxt.getBean("runtimeService",RuntimeService.class);
        String processId  = runtimeService.startProcessInstanceByKey(proc_code,variables).getId();
        return processId;
    }
    //通过实例ID获取任务
    private Task getTaskListByp(String processId){
        ApplicationContext ctxt =ActivitiUtil.getCtxt();
        TaskService taskService=ctxt.getBean("taskService",TaskService.class);
        Task tasks = taskService.createTaskQuery().processInstanceId(processId).singleResult();

        return tasks;
    }
    //发送通知
    public String sendNotify(String proc_code, JSONObject notify) {

      String processId = startActiviti(proc_code,notify);
      //获取任务
      Task task = getTaskListByp(processId);

      completeTask(task.getId());

      return processId;
    }
    //申请任务
    public String applicationTask(String proc_code, JSONObject task){

        String processId = startTaskActiviti(proc_code,task);
        Task tasks = getTaskListByp(processId);
        //完成申请任务
        completeTask(tasks.getId());
        return processId;
    }
    //审核
    public void approvalTask(String processId){
        Task tasks = getTaskListByp(processId);
        //完成申请任务
        completeTask(tasks.getId());
    }

}
