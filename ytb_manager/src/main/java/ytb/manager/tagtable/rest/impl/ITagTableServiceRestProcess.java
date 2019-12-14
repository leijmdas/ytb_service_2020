package ytb.manager.tagtable.rest.impl;

import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.common.RestMessage.MsgHandler;

import java.io.IOException;

public interface ITagTableServiceRestProcess {
    final static String TagTableServiceManager = "tagTableServiceManager";
    final static String TagTableServiceProject = "tagTableServiceProject";

    TemplateDocumentInfo parseTemplate( Integer projectId, Integer documentId ) throws Exception;

    //项目中心模板不是自己的文档不能导出表数据
    //协助文档的文件头header要修改 documentId,否则会导出数据删除别人的数据
    void checkTemplateOwner(MsgHandler handler, Integer projectId, Integer documentId);

    //导出文档中所有关联表：一次扫描
    String exportAllTables(MsgHandler handler, Integer projectId, Integer documentId) throws Exception;

    void checkTemplate(TemplateDocumentInfo documentInfo)  throws IOException;


}