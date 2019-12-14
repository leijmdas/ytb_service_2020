package ytb.project.service.template;

import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;

public class PATemplateService {

    public static int modifyTemplate(UserProjectContext context,int projectId, int documentId, String document) throws UnsupportedEncodingException {
        if(context.getProjectModel().getStatus()== ProjectConstState.PUBLISH_PASS){
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"项目处于发布状态，不能修改需求文档！");
        }
        return DocumentToolService.getDocumentToolService().modifyJson(context,projectId, documentId, document);

    }
}