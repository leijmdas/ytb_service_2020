package ytb.project.model.projectFolderView;

import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.model.iface.IFolderModel;

import java.util.List;

public class ProjectFolderBase extends ProjectFolderModel implements IFolderModel {
    public int createFolder(UserProjectContext context, int phase) {
        return 0;
    }

    public ProjectFolderModel buildFolder(UserProjectContext context, int phase) {
        this.setPhase(phase);
        return new ProjectFolderModel(context);
    }

    public void checkExistReqWorkplan(ProjectContext pc,List<ProjectTemplateModel> models){
        pc.setUpReqWorkplan(models);
        pc.checkReqWorkplan();

    }

}
