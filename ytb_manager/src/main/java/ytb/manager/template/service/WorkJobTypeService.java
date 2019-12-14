package ytb.manager.template.service;

import ytb.manager.template.model.*;

import java.util.List;

public interface WorkJobTypeService {

    //查询岗位分类列表
    public List<Dict_WorkJobTypeModel> getWorkJobTypeList();

    //查询岗位类别
    public void addWorkJobType(Dict_WorkJobTypeModel t);

    //修改岗位类别
    public void modifyWorkJobType(Dict_WorkJobTypeModel t);

    //查询岗位类别
    public Dict_WorkJobTypeModel getWorkJobType(int templateId);

    //删除岗位类别
    public void delWorkJobType(int templateId);

    //通过岗位类别获取模板
    public List<Dict_TemplateModel> getTemplateList(Dict_TemplateModel t);
    public List<Dict_TemplateModel> getTemplates(int repositoryId,int workJobId);

    //通过项目获取模板详细信息
    public List<Dict_TemplateModel> getDocTemplateListinfo(Dict_TemplateModel t);

    //通过岗位类别添加模板
    public void addTemplate(Dict_TemplateModel t);

   /* //通过岗位类别修改模板
    public void modifyTemplate(Dict_TemplateModel t);*/

    //通过岗位类别查询模板
    public Dict_TemplateModel getTemplate(int templateId);

   /* //通过岗位类别删除模板int
    public void delTemplate(int templateId);*/

    //通过岗位类别来获取岗位详细列表
    public List<Dict_WorkJobModel> getWorkJobDetailsInfoList(int WorkJobTypeId);

    //添加岗位
    public void addWorkJob(Dict_WorkJobModel workJob);

    //修改岗位
    public void modifyWorkJob(Dict_WorkJobModel workJob);

    //查询岗位
    public Dict_WorkJobModel getWorkJob(int workJobId);

    //删除岗位
    public void delWorkJob(int workJobId);

    //通过岗位分类ID查询岗位列表
    List<Dict_WorkJobModel> getWorkJobListByWorkJobTypeId(int workJobTypeId);

    //通过岗位获取工作任务列表
    public List getTaskList(int workJobId);

    //添加工作任务列表
    public void addTask(Dict_TaskModel task);

    //修改工作任务列表
    public void modifyTask(Dict_TaskModel task);

    //查询工作任务列表
    public Dict_TaskModel getTask(int workJobId);

    //删除工作任务列表
    public void delTask(int workJobId);

    //通过岗位类别获取真值约束列表
    public List getConstraintList(int WorkJobTypeId);

    //修改真值约束列表
    public void modifyConstList(Dict_WorkJob_Check Constraint);

    //查询真值约束列表
    public Dict_WorkJob_Check getConstList(int workJobId);

    //删除真值约束列表
    public void delConstList(int workJobId);


    /**
     * =====岗位真值约束相关
     */

    //查询需求书岗位真值约束列表 根据模板ID
    List<Dict_WorkJob_Check> getWorkJobCheckDetailsListBy(int templateId);

    //添加真值约束列表
    void addConstList(Dict_WorkJob_Check Constraint);

    //删除真值约束信息
    void removeWorkJobCheckBy(int checkId);

    //修改真值约束信息
    void modifyWorkJobCheck(Dict_WorkJob_Check workJobCheck);

    //获取文档模板详细信息
    Dict_TemplateModel getDocTemplateDetails(int templateId);

    //修改文档模板的内容 Temaplte表docNew关联的document
    public void modifyTemplateNewDoc(int documentId, String document);

    //通过岗位获取岗位类别下的模板列表
    List<Dict_TemplateModel> getTemplateListByWorkJobId(int workJobId);

    //修改岗位类别状态
    void modifyStateById(int workJobTypeId,int state);

    void checkTemplates(int repositoryId,int workJobTypeId);

}
