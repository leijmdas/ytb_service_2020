package ytb.project.service.impl.flow;

import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ProjectFileDAOService;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUserControl;
import ytb.project.service.IProjectFileService;
import ytb.project.service.IProjectFileDAOService;
import java.util.List;


public class ProjectFileServiceImpl extends ProjectFileDAOService  implements IProjectFileService {


    //获取文件夹下面所有文档
    public List<ProjectTemplateModel> getTemplatesTree(int folderId, List<ProjectTemplateModel> templateModels) {
        //获取该文件夹下的文档
        templateModels.addAll(getTemplateListByFolder(folderId));

        List<ProjectFolderModel> folderList = getFolderModels(folderId);//查询子文件夹
        for (ProjectFolderModel folder  : folderList) {
            getTemplatesTree(folder.getFolderId(), templateModels);
        }
        return templateModels;
    }



    //提交文档和文件夹审核,文档升级
    public void auditFolderTemplate(ProjectFolderModel projectFolder, int auditor, int status) {
        submitFolder(projectFolder, status, auditor);
        //标记文件夹和文件夹下面所有文档为提交审核状态
        List<ProjectTemplateModel> templateModelList = getTemplateListByFolder(projectFolder.getFolderId());
        //查询子文件夹
        for (ProjectTemplateModel templateModel : templateModelList) {
            if (status == 3) {
                submitTemplate(templateModel, 300, auditor);
            } else {
                submitTemplate(templateModel, 400, auditor);
                IProjectFileDAOService.getProjectTemplateUser().versionUpgrade(templateModel.getTemplateId(), 2);//升m档
            }
        }
        //获取该文件夹下的文档
        List<ProjectFolderModel> folderList = getFolderModels(projectFolder.getFolderId());
        for (ProjectFolderModel pf : folderList) {
            auditFolderTemplate(pf, auditor, status);
        }

    }


    //阶段甲方审核
    public void paAuditModifyFolderTemplatesStatus(ProjectFolderModel projectFolder, int paUserId, int pbUserId, int status) {
        new ProjectTemplateUserControl().paAuditModifyTemplatesStatus(projectFolder, paUserId, pbUserId, status);
        //List<ProjectTemplateModel> models = getTemplateListByFolder(projectFolder.getFolderId());

        List<ProjectFolderModel> folderList = getFolderModels(projectFolder.getFolderId());//查询子文件夹
        for (ProjectFolderModel pf : folderList) {
            paAuditModifyFolderTemplatesStatus(pf, paUserId, pbUserId, status);
        }
    }


    //提交文档和文件夹审核
    public void submitFolderAndTemplate(UserProjectContext context, ProjectFolderModel projectFolder, int auditor) {
        //标记文件夹和文件夹下面所有文档为提交审核状态
        int folderStatus = context.isPb() ? ProjectConst.FOLDER_STATUS_SUBMIT_PB : ProjectConst.FOLDER_STATUS_SUBMIT_PM;
        submitFolder(projectFolder, folderStatus, auditor);

        List<ProjectTemplateModel> templateModelList = getTemplateListByFolder(projectFolder.getFolderId());//获取该文件夹下的文档
        for (ProjectTemplateModel templateModel : templateModelList) {
            submitTemplate(templateModel, 200, auditor);
        }

        List<ProjectFolderModel> list = getFolderModels(projectFolder.getFolderId());//查询子文件夹
        for (ProjectFolderModel folderModel : list) {
            submitFolderAndTemplate(context,folderModel, auditor);
        }

    }


    //提交文件夹审核
    public void submitFolder(ProjectFolderModel projectFolder, int folderStatus, int auditor) {

        projectFolder.setFolderStatus(folderStatus);
        projectFolder.setAuditor(auditor);
        modifyFolderModel(projectFolder);
    }

    //提交文档审核,协助
    public void submitTemplate(ProjectTemplateModel templateModel, int status, int auditor) {

        templateModel.setStatus(status);
        templateModel.setAuditor(auditor);
        modifyProjectTemplate(templateModel);
    }




}

