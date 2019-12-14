package ytb.project.model.projectFolderView.projectTemplate.ProjectChange;

import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.model.projectFolderView.projectTemplate.CopyTemplateInfo;
import ytb.manager.template.model.Dict_TemplateModel;


//大小
public class CopyTemplateModelSmallChange extends CopyTemplateModelChange  {

    public ProjectTemplateModel copyTemplate(UserProjectContext context, int folderId) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        Dict_TemplateModel dict_tm =
                context.getInstMngr().getTemplateRepositoryService().getChangeTemplate(Dict_TemplateModel.Template_TYPE_chng,
                        ProjectConstState.CHNAGE_TYPE_SMALL);

        CopyTemplateInfo templateInfo=new CopyTemplateInfo();

        templateInfo.getTitleInfo().setTaskName("");
        templateInfo.getTitleInfo().setStatus((byte)2);
        templateInfo.getTitleInfo().setProjectName(pm.getProjectName());

        templateInfo.setFolderId(folderId);
        templateInfo.setAuthor(ptm.getUserId2());
        templateInfo.setDocVer("");
        templateInfo.setAuditor(pm.getUserId1());

        projectTemplateModel =  copyTemplate(templateInfo, dict_tm);
        /*projectTemplateUserControl .addProjectTemplateUserReqChange(  context,
                projectTemplateModel.getTemplateId() );*/
        return projectTemplateModel;
    }

}
