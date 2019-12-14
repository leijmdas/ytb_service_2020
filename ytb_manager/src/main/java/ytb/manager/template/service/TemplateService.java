package ytb.manager.template.service;

import ytb.manager.template.model.Dict_TemplateModel;

import java.util.List;

/**
 * 项目模板service接口
 *
 * @author lxz 2019/2/15 14:24
 */
public interface TemplateService {

    //根据模板仓库ID和文档类型获取文档模板列表
    List<Dict_TemplateModel> getDocTemplateList(int repositoryId, int workJobId, Integer[] docTypeArr);

    void addTemplate(Dict_TemplateModel templateModel);

    void modifyTemplate(Dict_TemplateModel templateModel);

    void modifyDocumentIdAlias(Dict_TemplateModel templateModel);

    void delTemplate(int templateId);

    Dict_TemplateModel getTemplateBy(int templateId);
}
