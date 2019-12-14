package ytb.project.model.iface;

import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;
import ytb.project.service.IProjectFileService;

public interface IFolderModel {
    public static ProjectSrvContext getInst() {
        return ProjectSrvContext.getInst();
    }

    default IProjectFileService getProjectFileService() {
        return getInst().getProjectFileService();
    }

    int createFolder(UserProjectContext context, int phase);

    ProjectFolderModel buildFolder(UserProjectContext context, int phase);


}
