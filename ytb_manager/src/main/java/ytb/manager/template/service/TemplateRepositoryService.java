package ytb.manager.template.service;


import com.alibaba.fastjson.JSONObject;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_document;
import ytb.manager.template.model.Template_Repository;

import java.util.List;

public interface TemplateRepositoryService {

    //添加模板仓库
    void addTemplateRepository(Template_Repository tr);

    //删除模板仓库
    void delTemplateRepository(int repositoryId);

    //修改模板仓库
    void modifyTemplateRepository(Template_Repository tr);

    //获取模板仓库列表
    List<Template_Repository> getTemplateRepositoryList();

    //获取已发布模板仓库列表
    List<Template_Repository> getPubTemplateRepositoryList();

    String getRepositoryData();

    //根据模板仓库获取文档模板列表
    List getDocTemplateList(int repositoryId, int docType);

    //根据模板仓库获取文档模板列表
//    List<Dict_TemplateModel> getDocTemplateList(int repositoryId, Integer[] docTypeArr);

   /* //添加文档模板
    void addDocTemplate(Dict_TemplateModel templateModel);*/

    /*//修改文档模板
    void modifyTemplate(Dict_TemplateModel templateModel);*/

    //删除文档模板
    /*void delTemplate(int templateId);*/

    //通过项目修改模板状态
    void modifyTemplateState(Dict_TemplateModel t);

    //获取文档模板详细信息
    Dict_TemplateModel getDocTemplateDetails(int templateId);

    JSONObject getMngrReDocument(int documentId);

    //获取需求文档模板
    Dict_TemplateModel getRequirements(int projectTypeId, int docType);

    //获取源文件
    Dict_document getDocument(int documentId);

    /**
     * 获取文档模板详细信息
     * 与不为0的模板仓库ID或岗位ID关联
     *
     * @param templateId   模板ID
     * @param workJobId    岗位ID
     * @param repositoryId 模板仓库ID
     * @return Dict_TemplateModel
     */
    Dict_TemplateModel getDocTemplateDetails(int templateId, int workJobId, int repositoryId);

    Dict_TemplateModel getChangeTemplate(int docType,int subType);

    //获取模板列表
    List<Dict_TemplateModel> getDocTemplateListinfo(Dict_TemplateModel dict_templateModel);

    //根据id获取模板仓库
    Template_Repository getTemplateRepository(int repositoryId);

    //根据id修改模板仓库状态
    void modifyStateById(int projectTypeId,int state);
}
