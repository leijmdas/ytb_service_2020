package ytb.project.model.projectFolderView.projectTemplate.ProjectChange;

import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.model.projectFolderView.projectTemplate.CopyTemplateInfo;
import ytb.manager.template.model.Dict_TemplateModel;


//大小
public class CopyTemplateModelBigChange extends CopyTemplateModelChange {

    public ProjectTemplateModel copyTemplate(UserProjectContext context, int folderId) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        Dict_TemplateModel dtm = context.getInstMngr().getTemplateRepositoryService()
                .getChangeTemplate(Dict_TemplateModel.Template_TYPE_chng, ProjectConstState.CHNAGE_TYPE_BIG);

        //拷贝需求变更书（需求告知书）
        CopyTemplateInfo templateInfo = new CopyTemplateInfo();
        templateInfo.getTitleInfo().setTaskName("");
        templateInfo.getTitleInfo().setStatus((byte)1);
        templateInfo.getTitleInfo().setProjectName(pm.getProjectName());

        templateInfo.setFolderId(folderId);
        templateInfo.setAuthor(ptm.getUserId2());
        templateInfo.setDocVer("");
        templateInfo.setAuditor(pm.getUserId1());

        projectTemplateModel = copyTemplate(templateInfo, dtm);
        /*projectTemplateUserControl .addProjectTemplateUserReqChange(context,projectTemplateModel.getTemplateId() );*/
            return projectTemplateModel;
    }
}
