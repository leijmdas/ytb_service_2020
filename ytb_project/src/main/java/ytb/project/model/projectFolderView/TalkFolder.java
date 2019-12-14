package ytb.project.model.projectFolderView;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.model.iface.IFolderModel;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TalkFolder extends ProjectFolderBase implements IFolderModel {
    List<ProjectDutyFolder> projectDutyFolderLst = new ArrayList<>();

    public ProjectFolderModel getTalkFolderModel(UserProjectContext context) {
       return context.getTalkFolderModel();
    }

    @Override
    public int createFolder(UserProjectContext context, int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectFolderModel folder = new ProjectFolderModel(context);
        folder.setFolderType(ProjectConst.FOLDER_TYPE_PHASE);
        folder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);
        folder.setFolderName(pm.getProjectName() + "-" + phase);

        folder.setParentId(getParentId());
        folder.setUserId(context.getProjectTalkModel().getUserId2());
        folder.setAuditor(pm.getUserId1());

        folder.setPhase(phase);
        folder.setDepth(2);
        folder.setOwnerId(0);

        //创建阶段文件夹
        context.getProjectFileService().addFolderModel(folder);
        setFolderId( folder.getFolderId() );
        return folder.getFolderId();
    }


    //TalkFolder
    public int createFolderStop(UserProjectContext context) {

        //设置文件夹
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectFolderModel folderModel = new ProjectFolderModel(context);

        folderModel.setParentId(ptm.getFolderId());
        folderModel.setFolderType(ProjectConst.FOLDER_TYPE_STOP);
        folderModel.setOwnerId(0);
        folderModel.setDepth(2);
        //folderModel.setPhase(ProjectConst.Phase_Stop);
        folderModel.setPhase(ptm.getPhase());
        folderModel.setFolderName(pm.getProjectName()+"-终止-文件夹V1.0.0");
        context.getProjectFileService().addFolderModel(folderModel);

        return folderModel.getFolderId();

    }

    List<Integer> getWorkJobLst(UserProjectContext context){
        StringBuilder sql=new StringBuilder(128);
        sql.append( " select work_job_id from ytb_project.vw_work_group_member_duty ");
        sql.append( " where group_id=  ").append(context.getProjectTalkModel().getGroupId());
        sql.append( "  group by work_job_id ");
        return YtbSql.selectList(sql,Integer.class);
    }

    List<VwWorkGroupMemberDutyModel> getVwDutyList(UserProjectContext context){
        StringBuilder sql=new StringBuilder(128);
        sql.append(" select * from ytb_project.vw_work_group_member_duty ");
        sql.append( " where group_id=  ").append(context.getProjectTalkModel().getGroupId());
        sql.append("  group by work_job_id ");
        return YtbSql.selectList(sql,VwWorkGroupMemberDutyModel.class);
    }

    //智能硬件-2设计-01-【项目名称】-【岗位名称A/B/C/D/E】文件夹V1.0.0
    public void createProjectDutyFolders(UserProjectContext context, int phase) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        List<VwWorkGroupMemberDutyModel> pmVwDutyList =  getVwDutyList(context);

        List<Integer> workJobLst = getWorkJobLst(context);
        for (Integer workJobId : workJobLst) {
            ProjectDutyFolder folder = new ProjectDutyFolder();
            folder.setPmVwDutyList(pmVwDutyList);
            folder.setParam(this.folderId, ptm.getUserId2(), workJobId);
            folder.createFolder(context, phase);

            projectDutyFolderLst.add(folder);
        }

    }

    public void dictCopyTemplate(UserProjectContext context, ProjectContext pc) throws UnsupportedEncodingException {
        for (ProjectDutyFolder dutyFolder : projectDutyFolderLst) {
            dutyFolder.dictCopyTemplate(context, pc);
        }

    }

    public List<ProjectDutyFolder> getProjectDutyFolderLst() {
        return projectDutyFolderLst;
    }

    public void setProjectDutyFolderLst(List<ProjectDutyFolder> projectDutyFolderLst) {
        this.projectDutyFolderLst = projectDutyFolderLst;
    }

}
