package ytb.project.model.projectFolderView.projectTemplate;

import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;

//拷贝加工采购岗位文档
public class CopyTemplateModelPp extends CopyTemplateModel{


    //采购加工模板拷贝
    public ProjectTemplateModel copyTemplate(UserProjectContext context, ProjectContext pc, int folderId) throws UnsupportedEncodingException {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();
        if (context.getDict_ProjectTypeModel().isProcessing()) {//加工
            Dict_TemplateModel ProReport =
                    getInst().getTemplateRepositoryService().getRequirements(pm.getProjectTypeId(),
                            ProjectConst.Processing_inspection_report);//加工检验报告
            Dict_TemplateModel ProCheckList =
                    getInst().getTemplateRepositoryService().getRequirements(pm.getProjectTypeId(),
                            ProjectConst.Processing_checklist);//加工检验报告
            projectTemplateModel = copyTemplate(context, pc, ProReport, folderId);
            projectTemplateModel = copyTemplate(context, pc, ProCheckList, folderId);
        } else     if (context.getDict_ProjectTypeModel().isPurchase())   {//采购
            Dict_TemplateModel PurReport =
                    getInst().getTemplateRepositoryService().getRequirements(pm.getProjectTypeId(),
                            ProjectConst.Procurement_inspection_report);//采购检验报告
            projectTemplateModel = copyTemplate(context, pc, PurReport, folderId);
        }
        else {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "不是加工采购！");
        }
        return projectTemplateModel;
    }


    //采购加工模板拷贝
    ProjectTemplateModel copyTemplate(UserProjectContext context, ProjectContext pc,
                                      Dict_TemplateModel dtm,  int folderId) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        CopyTemplateInfo templateInfo=new CopyTemplateInfo();
        templateInfo.getTitleInfo().setProjectName(pm.getProjectName());
        templateInfo.getTitleInfo().setStatus((byte)1);
        templateInfo.getTitleInfo().setTaskName("");
        templateInfo.setFolderId(folderId);
        templateInfo.setAuthor(ptm.getUserId2());
        templateInfo.setAuditor(pm.getUserId1());
        templateInfo.setDocVer("1.0.0.0");
        projectTemplateModel = copyTemplate(templateInfo, dtm);

        projectTemplateUserControl.addProjectTemplateUserCopyTemplatePp (context,
                projectTemplateModel.getTemplateId());
        pc.setDocumentId(projectTemplateModel.getDocumentId());
        pc.modifyHeader(context);
        return projectTemplateModel;
    }



}
