package ytb.manager.tagtable.service;

import ytb.manager.tagtable.model.tagtemplate.Document_RefModel;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IDocumentRefService {
    //建立关联
    void setUpRefAll(int repositoryId, int projectId, long userId) throws
            UnsupportedEncodingException;
    //更新源文档修改时间
    void updateRefDocTimeSync(int projctId,int documentId);
    //更新目标文档同步时间
    void updateRefPDocTime(int projctId,int pDocumentId);

    List<Dict_TemplateModel> spFindAllTemplateByRepository(int repositoryId, int projectId);

    List<Dict_TemplateModel> spFindAllTemplateByWorkjobType(int pWworkjob_type, int projectId);

    int insertRefReqWorkplan(TemplateDocumentHeader header);

    int insertRefReq(TemplateDocumentHeader header);

    int insertRefWorkplan(TemplateDocumentHeader header);

    int insertRef(Document_RefModel m);

    void updateRef(Document_RefModel m);

    void deleteRef( TemplateDocumentHeader pHeader );

    void deleteRefByDocumentId(int projectId, int documentId);

    void deleteRefByPDocId(int projectId, int pDocId);

    void deleteRefById(int projectId, int refId);

    int findRefProject(int projectId, int documentId, short pDocType);

    int findRef(int repositoryId, int projectId, int documentId, short pDocType);

    //查询关联需求
    int findRefReqId(int repositoryId, int projectId, int documentId);

    //查询子计划书id
    int findRefWorkplanId(int repositoryId, int projectId, int pDocumentId);

    //查询引用计划书id
    int findChildWorkplanId(int repositoryId, int projectId, int documentId);

    //int insertRefWotkplan(Document_RefModel m);
    Document_RefModel findDocumentRefModel(int repositoryId, int projectId, int documentId, short pDocType);

    Document_RefModel findDocumentChildModel(int repositoryId, int projectId, int pDocumentId, short docType);

}
