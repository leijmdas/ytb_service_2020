package ytb.project.model.projectFolderView.projectTemplateUser;

import ytb.project.common.ProjectConst;
import ytb.project.context.*;
import ytb.project.model.*;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.IProjectFileDAOService;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class ProjectTemplateUserControl extends ProjectTemplateUser implements  IProjectTemplateUser{

    //项目中心修改用模板状态
    public void modifyProjectTemplateUserStatus(
            UserProjectContext context,
            int userId,
            int templateId,
            int status) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        if (userId != pm.getUserId1() && userId != ptm.getUserId2()) {
            modify(ptm.getUserId2(), userId, templateId, status);
        }
        modify(pm.getUserId1(), ptm.getUserId2(), templateId, status);
    }


    //项目中心修改用模板状态
    public void modifyStopProjectTemplateUser(
            UserProjectContext context,
            int userId,
            int templateId) {
        ProjectModel pm = context.getProjectModel();
        ProjectTemplateUserModel projectTemplateUserModel = new ProjectTemplateUserModel();
        projectTemplateUserModel.setProjectId(pm.getProjectId());
        projectTemplateUserModel.setTemplateId(templateId);
        projectTemplateUserModel.setUserId(userId);
        projectTemplateUserModel.setDisplayStatus(0);
        projectTemplateUserModel.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
        projectTemplateUserModel.setStatus(ProjectConst.TASK_STATUS_Viewed);
        new ProjectTemplateUser().modify(projectTemplateUserModel);
    }



    //加工采购拷贝模板
    public void addProjectTemplateUserCopyTemplatePp(UserProjectContext context, int templateId) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        //添加甲方权限
        add(context, pm.getUserId1(), templateId, ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_ToAudit, 1,   ptm.getUserId2());
        //添加负责人的权限
        add(context, ptm.getUserId2(), templateId, ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_ToWrite, 0, ptm.getUserId2());
    }

    public void addProjectTemplateUserCopyTemplateComposite(UserProjectContext context,  ProjectContext pc,
                                                            int templateId) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        //添加甲方权限
        add(context, pm.getUserId1(), templateId, ProjectConst.TASK_STATUS_NO_RIGHT,
                ProjectConst.ACTIVE_STATUS_ToAudit, 1, pc.getUserId().intValue());
        //添加负责人的权限
        add(context, ptm.getUserId2(), templateId, ProjectConst.TASK_STATUS_NotStart,
                ProjectConst.ACTIVE_STATUS_ToWrite, 0, pc.getUserId().intValue());

    }

    public void addProjectTemplateUserConfirmProjectApply(UserProjectContext context, ProjectContext pc) throws UnsupportedEncodingException {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        //甲方同意申请 PA获取项目文件夹下的文档
        List<ProjectTemplateModel> models = context.getProjectFileService().getTemplateListByFolder(pm.getFolderId());
        for (ProjectTemplateModel pa : models) {
            if (pa.isTemplateReq()) {
                add(context, ptm.getUserId2(), pa.getTemplateId(), ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_GoRead, 0,
                        pm.getUserId1());
            }
        }

        //PB获取项目洽谈文件夹下的文档
        ProjectFolderModel pbFolder = ptm.fetchTalkFolderModel();
        List<ProjectTemplateModel> pbModels = context.getProjectFileService().getTemplateListByFolder(pbFolder.getFolderId());
        for (ProjectTemplateModel pb : pbModels) {
            add(context, ptm.getUserId2(), pb.getTemplateId(),
                    ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_ToWrite, 0, ptm.getUserId2());
            add(context, pm.getUserId1(), pb.getTemplateId(),
                    ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_ToAudit, 1, ptm.getUserId2());
        }

        //确定文档关系
        context.getDocumentFamily().setUpRefAll(pc, pbModels);
    }

    //添加组员项目变更的文档状态控制
    public void addProjectChangeTemplateUserForPM(UserProjectContext context){
        ProjectTalkModel ptm = context.getProjectTalkModel();
        //组员
        List<WorkGroupMemberModel> list =  ptm.selectWorkGroupMemberPM();
        List<ProjectTemplateModel> newModelList = ptm.selectTalkTemplates();
        ProjectTemplateUserControl userControl = new ProjectTemplateUserControl();
        for(ProjectTemplateModel templateModel : newModelList){
            for(WorkGroupMemberModel memberModel : list){
                userControl.add(context,memberModel.getUserId(),templateModel.getTemplateId()
                        ,  ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_GoRead,
                        1, 0);//组员
            }

        }
    }

    public void addProjectTemplateUserAssist(UserProjectContext context,
                                             Integer userId,
                                             Integer toUserId,
                                             int templateId,
                                             int templateIdAssist) {
        //动作（1去查阅 2去编制 3去修改 4去审核绿色 5上传文件 6重新上传）act
        // 模板状态（0发布中 1未开始 2已查阅 3编制中 4未递交 5已递交 6通过 7未通过）status
        //添加协助发起人的文档状态
       add(context ,userId,templateId,ProjectConst.TASK_STATUS_NotSubmitted,  ProjectConst.ACTIVE_STATUS_GoRead,1,toUserId,templateIdAssist) ;

        //添加协助接收人的文档状态控制
        add(context, toUserId, templateId,ProjectConst.TASK_STATUS_NotStart,ProjectConst.ACTIVE_STATUS_ToWrite,0,toUserId,templateIdAssist);
    }

    //添加阶段文档控制(项目变更)
    public void addProjectTemplateUserChngBig(UserProjectContext context,
                                              List<ProjectTemplateModel> models) {
        ProjectTalkModel ptm =  context.getProjectTalkModel();
        //获取该文件夹下的文档
        for (ProjectTemplateModel templateModel : models) {
             add(context,ptm.getUserId2(),templateModel.getTemplateId()
                     ,  ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_CHANGE,
                     0, 0);//乙方

             add(context, context.getProjectModel().getUserId1(),templateModel.getTemplateId(),
                     ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_ToAudit, 1,
                     0);//甲方
        }

    }

    //添加阶段文档控制(项目变更)组员
    public void addProjectTemplateUserChngBigToPM(UserProjectContext context,
                                              List<ProjectTemplateModel> models) {
        ProjectTalkModel ptm =  context.getProjectTalkModel();
        List<WorkGroupMemberModel>  workGroupMemberList = ptm.selectWorkGroupMemberPM();//工作组成员

        //获取该文件夹下的文档
        for (ProjectTemplateModel templateModel : models) {
            for(WorkGroupMemberModel memberModel : workGroupMemberList){
                add(context, memberModel.getUserId(),templateModel.getTemplateId(),
                        ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_GoRead, 1,
                        0);//组员
            }
        }


    }


    //添加阶段文档控制
    public void addProjectTemplateUserPhase(UserProjectContext context,
                                            int author,
                                            int templateId,
                                            int docType) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        int activeStatus = docType == ProjectConst.Template_TYPE_archive ? ProjectConst.ACTIVE_STATUS_UploadFile : ProjectConst.ACTIVE_STATUS_ToWrite;

        if (author == ptm.getUserId2()) {
             add(context, pm.getUserId1(), templateId, ProjectConst.TASK_STATUS_NO_RIGHT,
                    ProjectConst.ACTIVE_STATUS_ToAudit, 2, author);//添加甲方权限

            add(context, ptm.getUserId2(), templateId,
                    ProjectConst.TASK_STATUS_NotStart, activeStatus, 0, author);//添加负责人的权限

        } else {

            add(context, pm.getUserId1(), templateId,
                    ProjectConst.TASK_STATUS_NO_RIGHT, ProjectConst.ACTIVE_STATUS_ToAudit, 1, author);//添加甲方权限

            add(context, ptm.getUserId2(), templateId,
                    ProjectConst.TASK_STATUS_NO_RIGHT, ProjectConst.ACTIVE_STATUS_ToAudit, 1, author);//添加负责人的权限

            add(context, author, templateId,
                    ProjectConst.TASK_STATUS_NotStart, activeStatus, 0, author);//添加组员的权限

        }
    }


    //添加阶段文档控制
    public void addProjectTemplateUserPM(UserProjectContext context, int memberUserID, int templateId, int docType) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();

        ProjectTemplateUserModel m = new ProjectTemplateUserModel();
        m.setProjectId(pm.getProjectId());
        m.setTalkId(ptm.getTalkId());
        m.setTemplateId(templateId);
        m.setAuthor(memberUserID);
        int activeStatus = docType == ProjectConst.Template_TYPE_archive ? ProjectConst.ACTIVE_STATUS_UploadFile : ProjectConst.ACTIVE_STATUS_ToWrite;

        //pb
        if (memberUserID == ptm.getUserId2()) {
            //添加甲方权限pa
            m.setUserId(pm.getUserId1());
            m.setStatus(ProjectConst.TASK_STATUS_NO_RIGHT);
            m.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToAudit);
            m.setDisplayStatusGray();
            add(m);
            //添加负责人的权限pb
            m.setUserId(ptm.getUserId2());
            m.setStatus(ProjectConst.TASK_STATUS_NotStart);
            m.setActiveStatus(activeStatus);
            m.setDisplayStatusLight();
            add(m);

        } else {
            //添加甲方权限pa
            m.setUserId(pm.getUserId1());
            m.setStatus(ProjectConst.TASK_STATUS_NO_RIGHT);
            m.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToAudit);
            m.setDisplayStatusGray();
            add(m);
            //添加负责人的权限pb
            m.setUserId(ptm.getUserId2());
            m.setStatus(ProjectConst.TASK_STATUS_NO_RIGHT);
            m.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToAudit);
            m.setDisplayStatusGray();
            add(m);
            //添加组员的权限pm
            m.setUserId(memberUserID);
            m.setStatus(ProjectConst.TASK_STATUS_NotStart);
            m.setActiveStatus(activeStatus);
            m.setDisplayStatusLight();
            add(m);
        }
    }

    //项目变更
    public void addProjectTemplateUserReqChange(UserProjectContext context,UserProjectContext
            oldcontext, int templateId ) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        //添加需求变更书的文档控制状态(甲方、乙方)
        //甲方控制状态
        add(context,pm.getUserId1(),templateId, ProjectConst.ACTIVE_STATUS_GoRead, ProjectConst
                .TASK_STATUS_NotStart, 0, 1);
        //添加乙方文档控制
        add(context, ptm.getUserId2(),templateId,ProjectConst.ACTIVE_STATUS_ToWrite, ProjectConst
                .TASK_STATUS_NotStart, 0, 0);

        //添加组员
        List<WorkGroupMemberModel> list =  ptm.selectWorkGroupMemberPM();
        for(WorkGroupMemberModel memberModel : list){
            add(context,memberModel.getUserId(),templateId,  ProjectConst.TASK_STATUS_NotStart, ProjectConst.ACTIVE_STATUS_GoRead,
                    1, 0);//组员
        }
    }

    //项目终止文档状态控制
    public void addProjectTemplateUserReqStop(UserProjectContext context,int templateId ) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        //添加需求变更书的文档控制状态(甲方、乙方)
        //甲方控制状态
        add(context,pm.getUserId1(),templateId, ProjectConst.ACTIVE_STATUS_GoRead, ProjectConst
                .TASK_STATUS_NotStart, 0, 1);
        //添加乙方文档控制
        add(context, ptm.getUserId2(),templateId,ProjectConst.ACTIVE_STATUS_GoRead, ProjectConst
                .TASK_STATUS_NotStart, 0, 0);
    }


    public void paAuditModifyTemplatesStatus(ProjectFolderModel projectFolder, int paUserId, int pbUserId, int status) {
        IProjectTemplateUser projectTemplateUser = IProjectFileDAOService.getProjectTemplateUser();
        // 获取该文件夹下的文档
        List<ProjectTemplateModel> models =
                ProjectSrvContext.getInst().getProjectFileService().getTemplateListByFolder(projectFolder.getFolderId());
        for (ProjectTemplateModel templateModel :models) {
            //甲方
            ProjectTemplateUserModel pa = selectOne(paUserId, templateModel.getTemplateId());
            //负责人
            ProjectTemplateUserModel pb = selectOne(pbUserId, templateModel.getTemplateId());

            if (projectFolder.getUserId() == pbUserId) {
                //判断是不是负责人的文档
                if (status == ProjectConst.FOLDER_STATUS_PASS_PM) {//审核通过
                    pa.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
                    pa.setCreateTime(new Date());
                    modify(pa);

                } else {
                    pa.setDisplayStatusGray();
                    pa.setStatus(ProjectConst.ACTIVE_STATUS_ToAudit);
                    pa.setCreateTime(new Date());
                    modify(pa);

                    pb.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToWrite);
                    pb.setCreateTime(new Date());
                    modify(pb);

                }
            } else {
                if (status == 3) {//审核通过
                    pa.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
                    pa.setCreateTime(new Date());
                    modify(pa);
                } else {
                    //修改甲方
                    pa.setDisplayStatusGray();
                    pa.setStatus(ProjectConst.ACTIVE_STATUS_ToAudit);
                    pa.setCreateTime(new Date());
                     modify(pa);
                    //负责人

                    pb.setDisplayStatusGray( );
                    pb.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToAudit);
                    pb.setCreateTime(new Date());
                    modify(pb);//修改组长
                    //修改组员
                    ProjectTemplateUserModel pm = projectTemplateUser.selectOne(projectFolder.getUserId(), templateModel.getTemplateId());
                    pm.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToWrite);
                    pm.setCreateTime(new Date());
                    pm.setStatus(ProjectConst.TASK_STATUS_NotStart);
                    modify(pm);

                }
            }
        }
    }


    //阶段中组长提交文档
    public void pbSubmitModifyTemplateUserStatus(ProjectFolderModel projectFolder, int paUserId, int pbUserId) {
        //IProjectTemplateUser projectTemplateUser = ProjectFileDAOService.getProjectTemplateUser();

        List<ProjectTemplateModel> models = ProjectSrvContext.getInst().getProjectFileService().getTemplateListByFolder(projectFolder.getFolderId());//获取该文件夹下的文档
        for (ProjectTemplateModel templateModel : models) {
            if (projectFolder.getUserId() == pbUserId || projectFolder.getUserId() == 0) {
                ProjectTemplateUserModel pa = selectOne(paUserId, templateModel.getTemplateId());
                //甲方
                pa.setDisplayStatusLight();
                pa.setStatus(ProjectConst.TASK_STATUS_NotStart);
                modify(pa);

                ProjectTemplateUserModel pb = selectOne(pbUserId, templateModel.getTemplateId());
                //负责人
                pb.setStatus(ProjectConst.TASK_STATUS_Submitted);
                pb.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
                pb.setCreateTime(new Date());
                modify(pb);

            } else {
                ProjectTemplateUserModel pa = selectOne(paUserId, templateModel.getTemplateId());
                //甲方
                pa.setDisplayStatusLight();
                pa.setStatus(ProjectConst.TASK_STATUS_NotStart);
                modify(pa);
            }
        }
        List<ProjectFolderModel> list = ProjectSrvContext.getInst().getProjectFileService().getFolderModels(projectFolder.getFolderId());//查询子文件夹
        for (ProjectFolderModel pf : list) {
            pbSubmitModifyTemplateUserStatus(pf, paUserId, pbUserId);
        }
    }

}
