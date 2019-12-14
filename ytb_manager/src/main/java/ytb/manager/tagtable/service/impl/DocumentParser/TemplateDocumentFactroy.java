package ytb.manager.tagtable.service.impl.DocumentParser;

import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.manager.tagtable.service.ITemplateDocumentFactroy;
import ytb.manager.template.model.Dict_document;
import ytb.manager.templateexcel.service.TemplateDocumentService;
import ytb.manager.templateexcel.service.impl.TemplateDocumentServiceImpl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TemplateDocumentFactroy implements ITemplateDocumentFactroy {


    public TemplateDocumentInfo parseTemplate(Integer projectId, Integer documentId, boolean costFlag) throws IOException {

        TemplateDocumentInfo documentInfo = projectId == 0 ? parseTemplateManager(documentId) :
                parseTemplateProject(documentId);
        documentInfo.setCheckCostFlag(costFlag);
        return documentInfo;

    }

    //管理模板
    TemplateDocumentInfo parseTemplateManager(int documentId) throws IOException {

        TemplateDocumentService service = TemplateDocumentServiceImpl.getTemplateDocumentService();
        JSONObject json = service.getDict_document(documentId);
        TemplateDocumentInfo documentInfo = new TemplateDocumentInfo();
        documentInfo.setDocJson(json);
        documentInfo.parseHeader();
        return documentInfo;
    }


    byte[] selectProjectDocument(int documentId) throws IOException {
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select document from ytb_project.project_document ");
        sql.append(" where document_id=").append(documentId);
        return YtbSql.selectOne(sql, byte[].class);

    }

    //项目文档
    TemplateDocumentInfo parseTemplateProject(int documentId) throws IOException {

        byte[] bDocument = selectProjectDocument(documentId);
        JSONObject document = JSONObject.parseObject(new String(bDocument, "UTF-8"));
        TemplateDocumentInfo documentInfo = new TemplateDocumentInfo();
        documentInfo.setDocJson(document);
        documentInfo.parseHeader();
        return documentInfo;
    }

    public int modifyProjectTemplate(Dict_document dict_document) {
        StringBuilder sql = new StringBuilder();
        sql.append(" update  ytb_project.project_document  set document=#{document} ");
        sql.append(" where document_id=#{documentId}");
        return YtbSql.update(sql, dict_document);
    }

    public   void modifyProjectTemplate(int documentId, JSONObject document) throws UnsupportedEncodingException {

        Dict_document dict_document = new Dict_document();
        dict_document.setDocumentId(documentId);
        dict_document.setDocument(document.toJSONString().getBytes("UTF-8"));

       modifyProjectTemplate(dict_document);

    }

    public void modifyManagerDocument(int documentId, JSONObject document) throws UnsupportedEncodingException {
        TemplateDocumentService service = TemplateDocumentServiceImpl.getTemplateDocumentService();
        Dict_document dict_document = service.getDocumentBy(documentId);
        dict_document.setDocument(document.toJSONString().getBytes("UTF-8"));
        service.modifyDocument(dict_document);

    }

    public int modifyManagerDocument(Dict_document document) throws UnsupportedEncodingException {
        TemplateDocumentService service = TemplateDocumentServiceImpl.getTemplateDocumentService();

        return service.modifyDocument(document);

    }

}
