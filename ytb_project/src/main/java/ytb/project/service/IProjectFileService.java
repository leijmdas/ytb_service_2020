package ytb.project.service;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.ProjectTemplateModel;

import java.util.List;

public interface IProjectFileService extends IProjectFileDAOService {

    //获取文件夹以及子文件夹下面所有文档
    List<ProjectTemplateModel> getTemplatesTree(int folderId, List<ProjectTemplateModel> templateList);

    //修改审核文档文件夹状态
    void auditFolderTemplate(ProjectFolderModel projectFolder, int auditor, int status);
    //阶段中甲方审核时修改文档控制状态
    void paAuditModifyFolderTemplatesStatus(ProjectFolderModel projectFolder, int paUserId, int pbUserId, int status);


    //提交文档和文件夹审核
    void submitFolderAndTemplate(UserProjectContext context,ProjectFolderModel projectFolder, int auditor);
    //提交文件夹审核
    void submitFolder( ProjectFolderModel projectFolder,int folderStatus,int auditor);
    //提交文档审核
    void submitTemplate(ProjectTemplateModel templateModel,int status,int auditor);

}
