package ytb.project.model.projectFolderView;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewProjectFolderModel {

    PAFolder pAFolder = new PAFolder();
    PBFolder pBFolder = new PBFolder();
    //List<PBFolder> pbFolders=new ArrayList<>();

    List<ProjectFolderModel> getProjectAllFolders(UserProjectContext context) {
        return context.getProjectModel().queryAllFolders();
    }

    List<ProjectFolderModel> getTalkAllFolders(UserProjectContext context) {
        return context.getProjectTalkModel().selectAllFolders();
    }

    //queryTalks
    //queryObeTalk
    public PBFolder getpBFolder() {
        return pBFolder;
    }

    public void setpBFolder(PBFolder pBFolder) {
        this.pBFolder = pBFolder;
    }


    //pbUserId folder
    Map<Integer, PBFolder> map_PBFolder = new HashMap<>();

    public PAFolder getpAFolder() {
        return pAFolder;
    }

    public void setpAFolder(PAFolder pAFolder) {
        this.pAFolder = pAFolder;
    }

    public Map<Integer, PBFolder> getMap_PBFolder() {
        return map_PBFolder;
    }

    public void setMap_PBFolder(Map<Integer, PBFolder> map_PBFolder) {

        this.map_PBFolder = map_PBFolder;
    }


}
