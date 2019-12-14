package ytb.project.model.projectFolderView.projectTemplate;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.manager.template.model.Dict_TemplateModel;
import java.io.UnsupportedEncodingException;

//终止模板拷贝
public class CopyTemplateModelStop extends CopyTemplateModel {

    //生成采购加工清单
    public ProjectTemplateModel copyTemplate(UserProjectContext context, int folderId,Dict_TemplateModel dict_tm) throws
            UnsupportedEncodingException {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        CopyTemplateInfo templateInfo = new CopyTemplateInfo();
        templateInfo.getTitleInfo();
        templateInfo.getTitleInfo().setProjectName(pm.getProjectName());
        templateInfo.getTitleInfo().setStatus((byte)1);
        templateInfo.getTitleInfo().setTaskName("");
        templateInfo.setFolderId(folderId);
        templateInfo.setAuthor(pm.getUserId1());
        templateInfo.setAuditor( ptm.getUserId2());
        templateInfo.setDocVer("1.1.0.0");
        //  拷贝项目终止书
        ProjectTemplateModel templateModel = copyTemplate( templateInfo,dict_tm);

        projectTemplateUserControl.addProjectTemplateUserReqStop(context,templateModel.getTemplateId());

        return templateModel;
    }
}
