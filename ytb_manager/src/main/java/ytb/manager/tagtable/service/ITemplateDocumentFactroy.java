package ytb.manager.tagtable.service;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface ITemplateDocumentFactroy {

    TemplateDocumentInfo parseTemplate(Integer projectId, Integer documentId, boolean costFlag) throws IOException;

    void modifyProjectTemplate(int documentId, JSONObject document) throws UnsupportedEncodingException;

    void modifyManagerDocument(int documentId, JSONObject document) throws UnsupportedEncodingException;


}
