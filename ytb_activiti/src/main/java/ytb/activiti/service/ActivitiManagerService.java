package ytb.activiti.service;


import ytb.common.basic.activititemplate.model.Manager_Template_ProcModel;

import java.util.List;

/**
 * Package: ytb.activiti.activitiService
 * Author: ZCS
 * Date: Created in 2018/8/23 16:35
 */
public interface ActivitiManagerService {

    //查询所有工作流模板
    public List getActivitiTemplateList();

    //添加工作流模板
    public void addActivitiTemplate(Manager_Template_ProcModel mtp);

    //修改工作流模板
    public void modifyActivitiTemplate(Manager_Template_ProcModel mtp);

    //工作流模板上传文档存到数据库
    void modifyActivitiTemplateContent(Manager_Template_ProcModel mtp);

    //删除工作流模板
    public void removeActivitiTemplate(int proc_id);

    //修改工作流模板状态
    public int modifyActivitiStatus(Manager_Template_ProcModel mtp);

    //获取工作流模板
    public Manager_Template_ProcModel getActivitiTemplate(int proc_id);

    //后台中止工作流实例
    public void stopActiviti(String processInstanceId);

    //查询工作流实例
    public List getActivitiInstance();

    //通过用户名查询历史任务
    public List getHistoryTaskListByAssignee(String assignee);

    //通过任务名查询历史任务
    public List getHistoryTaskListByName(String name);

    //查询所有历史任务
    public List getHistoryTaskList();

    //个人查询工作流任务
    public List getActivitiTask(String assignee);

    //角色查询工作流任务
    public List getActivitiTaskByGroup(String role);



}
