package ytb.manager.template.service;


import com.alibaba.fastjson.JSONObject;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_document;

import java.util.List;

public interface ProjectTypeService {

    //查询项目分类列表
    public List getProjectTypeList();

    //查询项目分类
    public Dict_ProjectTypeModel getProjectType(int projectTypeId);

    //修改项目分类信息
    public void modifyProjectType(Dict_ProjectTypeModel projectTypeModel );

    void modifySateById(int projectTypeId,int state);

    //添加项目分类信息
    public void addProjectType(Dict_ProjectTypeModel projectTypeModel);

    //删除项目分类信息
    public void delProjectType(int projectTypeId);

    //通过项目获取模板
    public List getTemplateList(Dict_TemplateModel t);

    //通过项目获取模板详细信息
    public List getDocTemplateListinfo(Dict_TemplateModel t);

    //通过项目添加模板
    public void addTemplate(Dict_TemplateModel t);

    //通过项目修改模板
    public void modifyTemplate(Dict_TemplateModel t);

    //通过项目查询模板
    public Dict_TemplateModel getTemplate(int templateId);

    //通过项目删除模板
    public void delTemplate(int templateId);

    //添加文档内容源
    public void addMngrReDocument(Dict_document mrd);

    //修改文档内容源
    public void modifyDocument(Dict_document doc);

    //修改原始文档资源
    void modifyTemplateDocument(Dict_TemplateModel t);

    //获取原始文档资源
    Dict_document getMngrReDocument(int documentId);

    //通过项目模板id获取资源对象
    Dict_document getMngrReDocumentByT(int templateId);

    //获取项目分类的一级分类
    List<Dict_ProjectTypeModel> getProjectTypeByPar(int ParentId);


    //删除原始文档资源
    void delMngrReDocument(int documentId);


    List<Dict_ProjectTypeModel> selectTree();

    JSONObject getProjTypeDetailsInfo(int projectTypeId);

    List<Dict_ProjectTypeModel> getProjTypeDetailsInfoList();

    void modifyTemplateContentsNew(Dict_TemplateModel tm);

}
