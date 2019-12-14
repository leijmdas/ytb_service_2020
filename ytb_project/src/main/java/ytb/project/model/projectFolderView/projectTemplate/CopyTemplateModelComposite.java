package ytb.project.model.projectFolderView.projectTemplate;

import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.manager.template.model.Dict_TemplateModel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

//乙方集成文档
public class CopyTemplateModelComposite extends CopyTemplateModel {


    public List<ProjectTemplateModel> copyTemplate(UserProjectContext context, ProjectContext pc, int folderId) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<ProjectTemplateModel> retModels = new ArrayList<>();
        List<Dict_TemplateModel> models = context.getProjectCacheManager().findCompositeTemplates(pm.getProjectTypeId());
        for (Dict_TemplateModel templateModel : models) {
            //通过模板获取源文件

            CopyTemplateInfo templateInfo = new CopyTemplateInfo();
            templateInfo.setFolderId(folderId);
            templateInfo.setDocSeq(""+ProjectConstState.TEMPLATE_STAGE_COMPOSITE_DESIGN);
            templateInfo.getTitleInfo().setStatus((byte)1);
            templateInfo.getTitleInfo().setProjectName(pm.getProjectName());
            templateInfo.getTitleInfo().setTaskName("");
            templateInfo.setDocVer("1.1.0.0");

            templateInfo.setAuthor(ptm.getUserId2());
            templateInfo.setAuditor(pc.getUserId().intValue());

            ProjectTemplateModel model = copyTemplate(templateInfo, templateModel);
            pc.setDocumentId(model.getDocumentId());
            pc.modifyHeader(context);

            projectTemplateUserControl.addProjectTemplateUserCopyTemplateComposite(context, pc, model.getTemplateId());

        }

        return retModels;
    }


}
