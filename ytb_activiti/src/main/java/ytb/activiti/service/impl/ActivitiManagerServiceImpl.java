package ytb.activiti.service.impl;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import ytb.common.basic.activititemplate.dao.ActivitiTemplateMapper;
import ytb.activiti.service.utils.ActivitiUtil;
import ytb.common.basic.activititemplate.model.Manager_Template_ProcModel;
import ytb.activiti.service.ActivitiManagerService;
import ytb.common.context.service.impl.YtbContext;

import java.util.List;


public  class ActivitiManagerServiceImpl implements ActivitiManagerService {

    //查询所有历史任务
    public List getHistoryTaskList() {
        ApplicationContext ctxt = ActivitiUtil.getCtxt();
        HistoryService historyService = ctxt.getBean("historyService", HistoryService.class);
        List<HistoricTaskInstance> list = historyService//与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()//创建历史任务实例查询
                .list();

        return list;
    }
    //通过任务名查询历史任务
    public List getHistoryTaskListByName(String name) {
        ApplicationContext ctxt =ActivitiUtil.getCtxt();
        HistoryService historyService=ctxt.getBean("historyService",HistoryService.class);
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskName(name)
                .list();
        return list;
    }

    //查询工作流实例
    public List getActivitiInstance() {
        ApplicationContext ctxt =ActivitiUtil.getCtxt();
        RuntimeService runtimeService=ctxt.getBean("runtimeService",RuntimeService.class);
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .active()
                .list();

        return list;
    }

    //个人查询私有工作流任务
    public List getActivitiTask(String assignee) {
        ApplicationContext ctxt =ActivitiUtil.getCtxt();
        TaskService taskService=ctxt.getBean("taskService",TaskService.class);
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();

        return tasks;
    }

    //角色查询工作流任务
    public List getActivitiTaskByGroup(String roleId) {
        ApplicationContext ctxt = ActivitiUtil.getCtxt();
        TaskService taskService = ctxt.getBean("taskService", TaskService.class);
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(roleId).list();

        return tasks;
    }

    //通过用户名查询历史任务
    public List getHistoryTaskListByAssignee(String assignee) {
        ApplicationContext ctxt = ActivitiUtil.getCtxt();
        HistoryService historyService = ctxt.getBean("historyService", HistoryService.class);
        List list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(assignee)
                .list();

        return list;
    }

    //查询所有工作流模板
    public List getActivitiTemplateList() {
          try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_common(true)){
            ActivitiTemplateMapper activitiTemplateMapper = ss.getMapper(ActivitiTemplateMapper.class);
            return  activitiTemplateMapper.getActivitiTemplateList();

        }
    }

    //添加工作流模板
    public void addActivitiTemplate(Manager_Template_ProcModel mtp) {
        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_common(true)){
            ActivitiTemplateMapper activitiTemplateMapper = ss.getMapper(ActivitiTemplateMapper.class);
            mtp.setStatus(0);
            activitiTemplateMapper.addActivitiTemplate(mtp);

        }
    }

    //修改工作流模板状态
    public int modifyActivitiStatus(Manager_Template_ProcModel mtp) {

        ApplicationContext ctxt = ActivitiUtil.getCtxt();
        RepositoryService repositoryService = ctxt.getBean("repositoryService", RepositoryService.class);

        Deployment deploy;
        int state =0;
        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_common(true)){
            if(mtp.getStatus()==1){
                //发布
                Manager_Template_ProcModel mtpm =getActivitiTemplate(mtp.getProc_id());
                deploy  = repositoryService.createDeployment().addBytes(mtpm.getProc_file(),mtpm.getProc_content()).deploy();
                //deploy  = repositoryService.createDeployment().addClasspathResource("dbconfig/activiti/service.bpmn20/Review.bpmn").deploy();
                    if(deploy.getId()==null||deploy.getId().equals("")){
                        state =1;
                     }
            }else if(mtp.getStatus()==2){
                //激活
                List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(mtp.getProc_code()).orderByProcessDefinitionVersion().desc().list();
                if(list==null){
                    state =2;
                }else {
                    repositoryService.activateProcessDefinitionById(list.get(0).getId(), true, null);
                }
            }else if(mtp.getStatus()==3){
                //挂起
                List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(mtp.getProc_code()).orderByProcessDefinitionVersion().desc().list();
                if(list==null){
                    state =2;
                }else {
                    repositoryService.suspendProcessDefinitionById(list.get(0).getId(), true, null);
                }
            }
            if(state==0) {
                ActivitiTemplateMapper activitiTemplateMapper = ss.getMapper(ActivitiTemplateMapper.class);
                activitiTemplateMapper.modifyActivitiStatus(mtp);
                ss.commit();
            }
        }
        return state;
    }
    //修改工作流模板
    public void modifyActivitiTemplate(Manager_Template_ProcModel mtp) {
        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_common(true)){

            ActivitiTemplateMapper activitiTemplateMapper = ss.getMapper(ActivitiTemplateMapper.class);
            activitiTemplateMapper.modifyActivitiTemplate(mtp);
            ss.commit();
        }
    }
    //工作流模板上传文档
    @Override
    public void modifyActivitiTemplateContent(Manager_Template_ProcModel mtp) {
        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_common(true)){

            ActivitiTemplateMapper activitiTemplateMapper = ss.getMapper(ActivitiTemplateMapper.class);
            activitiTemplateMapper.modifyActivitiTemplateContent(mtp);
            ss.commit();
        }
    }

    //删除工作流模板
    public void removeActivitiTemplate(int proc_id) {

        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_common(true)){

            ActivitiTemplateMapper activitiTemplateMapper = ss.getMapper(ActivitiTemplateMapper.class);
            activitiTemplateMapper.removeActivitiTemplate(proc_id);
            ss.commit();
        }
    }
    //获取工作流模板

    public Manager_Template_ProcModel getActivitiTemplate(int proc_id) {

        try (SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_common(true)){

            ActivitiTemplateMapper activitiTemplateMapper = ss.getMapper(ActivitiTemplateMapper.class);
             return activitiTemplateMapper.getActivitiTemplate(proc_id);
        }
    }

    //后台中止工作流实例
    public void stopActiviti(String processInstanceId) {
        ApplicationContext ctxt =ActivitiUtil.getCtxt();
        RuntimeService runtimeService=ctxt.getBean("runtimeService",RuntimeService.class);
        // runtimeService.suspendProcessInstanceById(processInstanceId);//挂起流程
        runtimeService.deleteProcessInstance(processInstanceId,"删除原因");//删除流程,参数要传删除原因

    }



}
