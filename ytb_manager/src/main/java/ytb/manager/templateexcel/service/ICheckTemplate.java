package ytb.manager.templateexcel.service;

import ytb.manager.tagtable.model.DBTagTable;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;

import java.io.IOException;
import java.util.Map;

public interface ICheckTemplate {
    void checkJobRepeat (TemplateDocumentInfo  info);

    void checkTable(int documentId) throws IOException;

    Map<String, DBTagTable> sortTable(int documentId, String sortModel) throws IOException;

    void checkTable(TemplateDocumentInfo documentInfo) throws IOException;

    Map<String, DBTagTable> sortTable(TemplateDocumentInfo info, String ascDesc) throws IOException;

}
