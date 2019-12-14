package ytb.manager.templateexcel.service;

import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;

public interface CheckTemplateJobRepeatService {
    void checkJobRepeat (TemplateDocumentInfo info);
}
