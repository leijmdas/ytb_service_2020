package ytb.project.model.projectFolderView;

import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.model.iface.IFolderModel;
import ytb.manager.template.model.Dict_WorkJobModel;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class ProjectDutyFolder extends ProjectFolderBase implements IFolderModel {
    List<PMDutyFolder> pMDutyFolderLst =new ArrayList<>();

    //create Used
    List<VwWorkGroupMemberDutyModel> pmVwDutyList;
    Map<PMDutyFolder,VwWorkGroupMemberDutyModel> map_folder2Model = new HashMap<>();

    public void setParam(int parentId, int userId, int ownerId) {
        this.parentId = parentId;
        this.userId = userId;
        this.ownerId = ownerId;
    }

    //智能硬件-2设计-01-【项目名称】-【岗位名称A/B/C/D/E】文件夹V1.0.0
    @Override
    public int createFolder(UserProjectContext context, int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm=context.getProjectTalkModel();

        ProjectFolderModel folder = new ProjectFolderModel(context);
        folder.setParentId(this.getParentId());
        //智能硬件-2设计-01-【项目名称】-【岗位名称A/B/C/D/E】文件夹V1.0.0
        Dict_WorkJobModel workJobModel=context.getProjectCacheManager().findDictWorkJobModel(getOwnerId());
        folder.setOwnerId(workJobModel.getWorkJobId());

        StringBuilder title = new StringBuilder(pm.getProjectName());
        title.append("-").append(workJobModel.getTitle()).append("-文件夹V1.0.0");
        folder.setFolderName(title.toString());

        folder.setFolderType(ProjectConst.FOLDER_TYPE_DUTY_PROJECT);
        folder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);

        folder.setPhase(phase);
        folder.setDepth(3);
        folder.setAuditor(ptm.getUserId2());
        //创建阶段文件夹
        context.getProjectFileService().addFolderModel(folder);

        setFolderId( folder.getFolderId() );

        //建立人员岗位文件夹
        createPMDutyFolder(context,phase);
        return folder.getFolderId();


    }
    void createPMDutyFolder(UserProjectContext context, int phase){
        ProjectTalkModel ptm=context.getProjectTalkModel();
        for(VwWorkGroupMemberDutyModel memberDutyModel:pmVwDutyList) {
            if(memberDutyModel.getWorkJobId()==this.ownerId) {
                PMDutyFolder dutyFolder = new PMDutyFolder();
                dutyFolder.setOwnerId(this.ownerId);
                dutyFolder.setParentId(this.folderId);
                dutyFolder.setMemberDutyModel(memberDutyModel);
                dutyFolder.setUserId(memberDutyModel.getUserId());
                dutyFolder.setAuditor(ptm.getUserId2());

                dutyFolder.createFolder(context, phase);
                pMDutyFolderLst.add(dutyFolder);
                map_folder2Model.put(dutyFolder,memberDutyModel);
            }
        }
    }
    //phaseCopyTemplate
    public void dictCopyTemplate(UserProjectContext context, ProjectContext pc) throws UnsupportedEncodingException {
        for (PMDutyFolder dutyFolder : pMDutyFolderLst) {

            dutyFolder.dictCopyTemplate(context, pc, map_folder2Model.get(dutyFolder));
        }

    }

    public List<PMDutyFolder> getpMDutyFolderLst() {
        return pMDutyFolderLst;
    }

    public void setpMDutyFolderLst(List<PMDutyFolder> pMDutyFolderLst) {
        this.pMDutyFolderLst = pMDutyFolderLst;
    }

    public List<VwWorkGroupMemberDutyModel> getPmVwDutyList() {
        return pmVwDutyList;
    }

    public void setPmVwDutyList(List<VwWorkGroupMemberDutyModel> pmVwDutyList) {
        this.pmVwDutyList = pmVwDutyList;
    }

}
