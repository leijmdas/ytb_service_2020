package ytb.manager.template.dao;


import org.apache.ibatis.annotations.Param;
import ytb.manager.template.model.Dict_TemplateModel;

import java.util.List;
import java.util.Map;

public interface TemplateMapper {

    //查询文档模板集合
    List<Dict_TemplateModel> getDocTemplateList(Dict_TemplateModel template);

    List<Dict_TemplateModel> getDocTplList(@Param("repositoryId") int repositoryId,
                                           @Param("workJobId") int workJobId,
                                           @Param("docTypeArr") Integer[] docTypeArr);

    //根据ID查询模板信息
    Dict_TemplateModel getTemplate(int templateId);

    List<Dict_TemplateModel> getTemplates(@Param("repositoryId") int repositoryId,
                                          @Param("workJobId") int workJobId);

    //根据项目id查询模板集合
    List getTemplateBypro(int ProjectTypeId);

    //根据岗位id查询模板集合
    List<Dict_TemplateModel> getTemplateBywor(int WorkJobId);

    //添加模板
    void addTemplate(Dict_TemplateModel templateModel);

    //删除模板
    int delTemplate(int templateId);

    //修改模板
    void modifyTemplate(Dict_TemplateModel templateModel);

    //获取模板详细信息
    List<Dict_TemplateModel> getDocTemplateListinfo(Dict_TemplateModel template);

    //根据模板状态查询
    List getTemplateByState(int State);

    //查询已发布的模板
    List getDocTemplate(Dict_TemplateModel template);

    //查询通用模板
    List<Dict_TemplateModel> getDocTemplate_gen(int docType);

    //修改模板状态
    void modifyTemplateByState(Dict_TemplateModel t);

    //查询岗位的名称和文档信息
    List getDocTemplateListinfoByw(Dict_TemplateModel t);

    //文档模板页面编辑保存
    void modifyTemplateContentsNew(Dict_TemplateModel t);

    void modifyDocumentIdAlias(Dict_TemplateModel t);

    //修改原始文档资源
    void modifyTemplateDocument(Dict_TemplateModel t);

    //获取模板信息和关联的仓库名称,编号
    Dict_TemplateModel getTemplateRepo(int templateId);

    //获取需求模板文档
    Dict_TemplateModel getRequirements(@Param("projectTypeId") int projectTypeId, @Param("docType") int docType);

    //根据岗位ID获取岗位分类下面的文档模板列表
    List<Dict_TemplateModel> getTemplateListByWorkJobId(int workJobId);

    Dict_TemplateModel getChangeTemplate(@Param("docType") int docType, @Param("subType") int subType);


}
