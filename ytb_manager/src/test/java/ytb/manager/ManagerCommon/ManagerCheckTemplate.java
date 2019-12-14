package ytb.manager.ManagerCommon;

import com.alibaba.fastjson.JSONObject;
import com.jtest.testframe.ITestImpl;
import ytb.common.ytblog.YtbLog;
import ytb.manager.tagtable.service.ITemplateDocumentFactroy;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentFactroy;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;

import java.io.IOException;

public class ManagerCheckTemplate extends ITestImpl {
    ITemplateDocumentFactroy templateDocumentFactroy = new TemplateDocumentFactroy();
    TemplateDocumentInfo documentInfo;

    public TemplateDocumentInfo parseTemplate( Integer documentId) throws IOException {


        return parseTemplate(0,documentId);

    }

    public void logHeader() {
        YtbLog.logJtest("TemplateDocumentHeader", documentInfo.getdBDocHeader());
    }

    public void checkHeader(String paramName, String exp) {
        JSONObject info = JSONObject.parseObject(documentInfo.getdBDocHeader().toString());
        System.out.println(info);
        checkEQ(info.getString(paramName), exp);
    }

    public TemplateDocumentInfo parseTemplate(Integer projectId, Integer documentId) throws IOException {

        documentInfo = templateDocumentFactroy.parseTemplate(projectId, documentId, true);

        return documentInfo;

    }

}
