package ytb.project.model.projectFolderView;


import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.common.context.model.YtbError;

import java.util.List;

public class PhaseFolder extends TalkFolder {


    public ProjectFolderModel getPhaseFolderModel(UserProjectContext context, int phase) {
        return context.getPhaseFolder(phase);
    }

    public List<ProjectFolderModel> getPhaseFolderModels(UserProjectContext context) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        if (ptm.getPhase() < ProjectConst.TalkPhase) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目阶段不在进行中！");
        }
        return context.getProjectFileService().getPhaseFolders(ptm.getTalkId(), ptm.getPhase());

    }

    //获取文件夹以及子文件夹下面所有文档
    public List<ProjectTemplateModel> getPhaseAllTemplates(UserProjectContext context) {

        return context.getProjectTalkModel().selectPhaseAllTemplates();

    }
}