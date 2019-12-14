package ytb.manager.template.dao;

import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_document;

import java.util.List;

public interface DocumentMapper {


    Dict_document getMngrReDocument(int documentId);

    void addMngrReDocument(Dict_document mrd);

    void delMngrReDocument(int documentId);

    void modifyDocument(Dict_document doc);

    //通过模板id获取文档资源
    Dict_document getMngrReDocumentByT(int templateId);

    void modifyTemplateNewDoc(Dict_document dictDocument);

    List<Dict_TemplateModel> getTemplateListByWorkJobId(int workJobId);
}
