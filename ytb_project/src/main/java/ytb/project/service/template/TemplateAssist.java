package ytb.project.service.template;

import ytb.project.context.ProjectContext;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ProjectFolderAssistModelServiceImpl;
import ytb.project.daoservice.ProjectTemplateAssistModelServiceImpl;
import ytb.project.model.*;
import ytb.project.service.IProjectFileService;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class TemplateAssist extends ProjectTemplateAssistModelServiceImpl {
    ProjectFolderAssistModelServiceImpl assistModelService = new ProjectFolderAssistModelServiceImpl();

    UserProjectContext context;
    //查询共享
    transient public final static int DocumentAssist_TYPE_SHARE = 0;
    //查询协助
    transient public final static int DocumentAssist_TYPE_ASSIST = 1;
    //查询审核
    transient public final static int DocumentAssist_TYPE_AUDIT = 2;


    public static class CreateFolderResult {

        int foldefId;

        Map<Integer, Integer> map_templateId2assistId = new HashMap<>();

        public int getFoldefId() {
            return foldefId;
        }

        public void setFoldefId(int foldefId) {
            this.foldefId = foldefId;
        }

        public Integer getTemplateAssistId(int templateId) {
            if (!map_templateId2assistId.containsKey(templateId)) {
                throw new YtbError(YtbError.CODE_EXISTING_RECORDS, "协助模板新的标识不存在！");
            }
            return map_templateId2assistId.get(templateId);
        }

        public Map<Integer, Integer> getMap_templateId2assistId() {
            return map_templateId2assistId;
        }

        public void setMap_templateId2assistId(Map<Integer, Integer> map_templateId2assistId) {
            this.map_templateId2assistId = map_templateId2assistId;
        }

    }


    void copyDocument(int isShare, int documentId, ProjectTemplateAssistModel assistModel) throws UnsupportedEncodingException {
        if (context == null) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "无法修改文件头!");
        }
        ProjectContext pc = new ProjectContext(context);
        IProjectFileService fileService = ProjectSrvContext.getInst().getProjectFileService();
        if (isShare == DocumentAssist_TYPE_ASSIST) {
            ProjectDocumentModel projectDocument = fileService.getProjectDocument(documentId);
            documentId = fileService.addProjectDocument(projectDocument);
            pc.setDocumentId(documentId);
            pc.modifyHeader(context);

            assistModel.setDocumentId(documentId);

        }

    }

    CreateFolderResult copyTemplates(int isShare, int folderId, int toUserId, int fromUserId, List<Integer> templateIds) throws UnsupportedEncodingException {
        CreateFolderResult result = new CreateFolderResult();
        IProjectFileService pfs = ProjectSrvContext.getInst().getProjectFileService();

        for (int templateId : templateIds) {
            if (!checkExistsTemplatetAssist(folderId, templateId)) {
                ProjectTemplateModel ptm = pfs.getProjectTemplateById(templateId);

                ProjectTemplateAssistModel assistModel = new ProjectTemplateAssistModel(ptm);
                assistModel.setFolderId (folderId);
                assistModel.setAuthor(toUserId);
                assistModel.setAssistType(isShare);
                assistModel.setAuditor(fromUserId);
                //原作者模板的id
                assistModel.setTemplateIdIni(templateId);

                //协助才需要拷贝文档内容
                copyDocument(isShare,ptm.getDocumentId(),assistModel);
                int assistTemplateId = insert(assistModel);

                assistModel.setTemplateId(assistTemplateId);
                result.getMap_templateId2assistId().put(templateId,assistTemplateId);

            }
        }
        return result;
    }

    //协助共享文件夹
    CreateFolderResult createASFolder(UserProjectContext context, int isShare, int toUserId,
                                      int fromUserId, List<Integer> templateIdList) throws UnsupportedEncodingException {
        int projectId = context.getProjectModel().getProjectId();
        int folderId = assistModelService.existAssistFolder(isShare, projectId, toUserId);
        if (folderId == 0) {
            folderId = assistModelService.createASFolder(context, isShare, projectId, toUserId, fromUserId);
        }
        CreateFolderResult result = copyTemplates(isShare, folderId, toUserId, fromUserId, templateIdList);
        result.setFoldefId(folderId);

        return result;
    }


    //协助文件夹,如果存在则不创建
    public CreateFolderResult createAssistFolder(UserProjectContext context, int toUserId,
                                                 int fromUserId, List<Integer> templateIdList) throws UnsupportedEncodingException {
        this.context = context;
        return createASFolder(context, DocumentAssist_TYPE_ASSIST, toUserId, fromUserId, templateIdList);
    }

    //审核文件夹,如果存在则不创建
    public CreateFolderResult createAuditFolder(UserProjectContext context, int toUserId,
                                                int fromUserId, List<Integer> templateIdList) throws UnsupportedEncodingException {
        this.context = context;
        return createASFolder(context, DocumentAssist_TYPE_AUDIT, toUserId, fromUserId, templateIdList);

    }

    //共享文件夹,如果存在则不创建
    public CreateFolderResult createShareFolder(UserProjectContext context, int toUserId,
                                                int fromUserId, List<Integer> templateIdList) throws UnsupportedEncodingException {
        this.context = context;
        return createASFolder(context, DocumentAssist_TYPE_SHARE, toUserId, fromUserId, templateIdList);

    }

    //共享协助取消
    public void unShareFolder(int cancelType, List<Integer> templateIdLst) {
        deleteTemplates(cancelType, templateIdLst);
    }



}
