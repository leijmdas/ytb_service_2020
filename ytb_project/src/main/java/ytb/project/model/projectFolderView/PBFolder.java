package ytb.project.model.projectFolderView;

import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectContext;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.model.iface.IFolderModel;
import ytb.project.model.projectFolderView.projectTemplate.CopyTemplateModelComposite;
import ytb.project.model.projectFolderView.projectTemplate.PBFolderCopyTemplateModel;
import ytb.project.model.projectFolderView.projectTemplateUser.IProjectTemplateUser;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.impl.pay.PayResult;
import ytb.user.model.UserInfoModel;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class PBFolder extends ProjectFolderBase implements IFolderModel {
    TalkFolder talkFolder = new TalkFolder();
    PhaseFolder phaseFolder = new PhaseFolder();

    TaskFolder taskFolder = new TaskFolder();
    Map<Integer, PhaseFolder> map_phaseFolder = new HashMap<>();

    public TaskFolder getTaskFolder() {
        return taskFolder;
    }

    //洽谈申请确认
    public ProjectContext copyFolder(UserProjectContext context) throws UnsupportedEncodingException {

        int folderId = createFolder(context, ProjectConst.TalkPhase);
        return new PBFolderCopyTemplateModel().copyPaTemplate(context, folderId);
        //return  copyPaTemplate(context,folderId);
        //拷贝集成与设计阶段文件夹
        //pBFolder.copyFolder_phaseTalk(context, pc);
        //pc.setFolderId(pbFolderId);

    }

    public TalkFolder getTalkFolder() {
        return talkFolder;
    }

    public Map<Integer, PhaseFolder> getMap_phaseFolder() {
        return map_phaseFolder;
    }

    public void setMap_phaseFolder(Map<Integer, PhaseFolder> map_PhaseFolder) {
        this.map_phaseFolder = map_PhaseFolder;
    }

    //查询文件夹下的需求说明书与工作计划书模板
    public List<ProjectTemplateModel> selectTalkTemplates(UserProjectContext context) {

        List<ProjectTemplateModel> models = context.getProjectTalkModel().selectTalkTemplates();
        checkExistReqWorkplan(new ProjectContext(context), models);
        return models;
    }


    @Override
    public int createFolder(UserProjectContext context, int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectFolderModel folder = new ProjectFolderModel(context);
        UserInfoModel m = context.getInstUser().getUserCenterService().getUserInfoById(ptm.getUserId2());
        folder.setOwnerId(ptm.getTalkId());
        folder.setFolderName(m.getNickName() + pm.getProjectName());//设置文件夹名称
        //项目根文件夹
        folder.setFolderType(ProjectConst.FOLDER_TYPE_TALK);
        folder.setParentId(pm.getFolderId());

        folder.setPhase(phase);
        folder.setUserId(ptm.getUserId2());
        folder.setAuditor(pm.getUserId1());
        folder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);
        folder.setDepth(1);

        context.getProjectFileService().addFolderModel(folder);
        this.setFolderId(folder.getFolderId());
        return folder.getFolderId();
    }

    //拷贝甲方文件夹下的需求说明书与工作计划书模板
    //    public ProjectContext copyPaTemplate(UserProjectContext context,int folderId) throws UnsupportedEncodingException {
    //        return new PBFolderCopyTemplateModel().copyPaTemplate(context, folderId);}


    public List<ProjectTemplateModel> paAuditTalkFolder(UserProjectContext context, int evenTypeAudit) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        boolean isPass = evenTypeAudit == ProjectConst.FOLDER_STATUS_PASS_PB;

        //获取乙方的阶段文件夹
        ProjectFolderModel projectFolder = ptm.fetchTalkFolderModel();
        projectFolder.setFolderStatus(evenTypeAudit);
        projectFolder.setAuditor(pm.getUserId1());
        context.getInst().getProjectFileService().modifyFolderModel(projectFolder);
        List<ProjectTemplateModel> templateModels = selectTalkTemplates(context);

        //审核通过
        for (ProjectTemplateModel templateModel : templateModels) {
            IProjectFileDAOService.getProjectTemplateUser().modifyTalkAuditStatus(pm.getUserId1(), ptm.getUserId2(),
                    templateModel.getTemplateId(), isPass ? 1 : 2);//修改乙方文档状态
            if (!isPass) {
                IProjectFileDAOService.getProjectTemplateUser().versionUpgrade(templateModel.getTemplateId(), 0);
            }

        }

        return templateModels;
    }


    public List<ProjectTemplateModel> paAuditTalkFolderChange(UserProjectContext context, int
            evenTypeAudit) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        //获取乙方的阶段文件夹
        ProjectFolderModel projectFolder = ptm.fetchTalkFolderModel();
        projectFolder.setFolderStatus(evenTypeAudit);
        projectFolder.setAuditor(pm.getUserId1());
        context.getInst().getProjectFileService().modifyFolderModel(projectFolder);
        List<ProjectTemplateModel> templateModels = selectTalkTemplates(context);

        if(evenTypeAudit == ProjectConst.FOLDER_STATUS_NOTPASS_PB){//不通过
            for (ProjectTemplateModel templateModel : templateModels) {

                if (templateModel.getDocType() != 903) {
                    IProjectFileDAOService.getProjectTemplateUser().modifyTalkAuditStatus(pm.getUserId1(), ptm.getUserId2(),
                            templateModel.getTemplateId(),ProjectConst.ACTIVE_STATUS_CHANGE);//修改乙方文档状态
                    IProjectFileDAOService.getProjectTemplateUser().versionUpgrade(templateModel.getTemplateId(), 0);
                }

            }
        }
        return templateModels;
    }



    public List<ProjectTemplateModel> pbSubmitTalkFolder(UserProjectContext context) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectFolderModel talkFolder = ptm.fetchTalkFolderModel();
        talkFolder.setFolderStatus(ProjectConst.FOLDER_STATUS_SUBMIT_PB);//组长递交甲方
        talkFolder.setAuditor(pm.getUserId1());
        ProjectSrvContext.getInst().getProjectFileService().modifyFolderModel(talkFolder);//修改文件夹状态

        IProjectTemplateUser templateUser = IProjectFileDAOService.getProjectTemplateUser();
        List<ProjectTemplateModel> templateModels = selectTalkTemplates(context);
        for (ProjectTemplateModel templateModel : templateModels) {
            //修改乙方文档状态
            templateUser.modify(pm.getUserId1(), ptm.getUserId2(), templateModel.getTemplateId(), 0);
            //修改甲方文档状态
            templateUser.modify(pm.getUserId1(), ptm.getUserId2(), templateModel.getTemplateId(), 0);
        }
        return templateModels;
    }

    //洽谈也要生成设计文档
    public ProjectContext copyFolder_phaseTalk(UserProjectContext context, ProjectContext pc) throws UnsupportedEncodingException {
        ProjectModel pm = context.getProjectModel();
        //支付成功后修改到进行中状态第一阶段
        int phaseFirst = ProjectConst.TalkPhase_SUBFOLDER;
        copyFolder_phaseStart(context, pc, phaseFirst);

        int phaseFirstNew = ProjectConst.TalkPhase;
        pm.getProjectTalkService().modifyTalkPhaseAndStatus(context, phaseFirstNew);

        return pc;
    }

    //采购加工文件夹拷贝,return folderId
    public int copyFolderPB_phaseStartPp(UserProjectContext context) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();

        int folderId = copyFolder_phaseStart(context, new ProjectContext(context), pm.getPhaseStart());
        if (context.getDict_ProjectTypeModel().isPurchase()) {//采购
            ProjectTalkModel.getWorkGroup().addProjectMemberTask(pm.getProjectId(), "添加物流单号", "", 23, 0, 0);
        }

        return folderId;
    }


    // 确认支付进入首阶段copyFolder 600 or 601
    public int copyFolder_phaseStart(UserProjectContext context, ProjectContext pc, int phase) throws UnsupportedEncodingException {

        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectFolderModel projectFolder = ptm.fetchTalkFolderModel();

        //生成第一阶段文档  生成阶段1文件夹 洽谈或者第一阶段
        int folderId = copyFolder_phaseStartByParent(context, pc, phase, projectFolder.getFolderId());
        pc.setFolderId(folderId);
        // 获取并生成集成模板  dictCopyTemplateComposite(context, pc, folderId);
        new CopyTemplateModelComposite().copyTemplate(context, pc, folderId);

        List<ProjectTemplateModel> models = new ArrayList<>();
        models = getProjectFileService().getTemplatesTree(phaseFolder.getPhaseFolderModel(context, pc.getPhase()).getFolderId(), models);
        // 确定文档关系
        context.getDocumentFamily().setUpRefAllPhase(pc, selectTalkTemplates(context), models);

        return folderId;

    }

    public boolean existsPhaseFolder(UserProjectContext context,int phase) {

        return context.getProjectTalkModel().existsPhaseFolder(phase);
    }


    //生成第一阶段文件夹和文件夹下面的文档
    int copyFolder_phaseStartByParent(UserProjectContext context, ProjectContext pc, int phase, int parentFolderId) throws UnsupportedEncodingException {

        //创建阶段文件夹
        TalkFolder talkFolder = context.getViewProjectFolderModel().getpBFolder().getTalkFolder();
        talkFolder.setParentId(parentFolderId);
        if(existsPhaseFolder(context,phase)){
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"第一阶段文件家已经存在！");
        }


        int talkFolderId = talkFolder.createFolder(context, phase);
        //new folder
        //包括下面的人员岗位文件夹
        talkFolder.createProjectDutyFolders(context, phase);
        talkFolder.dictCopyTemplate(context, pc);

        return talkFolderId;
    }


    //阶段拷贝文档 projectFolderPhase现阶段
    //阶段拷贝文档 第一个不在此拷贝
    //根据当前阶段拷贝产生一个新阶段的目录模板 return newPhase
    public int copyFolder_phase(UserProjectContext context, PayResult payResult) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectContext pc = new ProjectContext(context);

        int newPhase = ptm.getPhase() + 1;
        ProjectFolderModel preFhaseFolder = getProjectFileService().getPhaseFolderByParent(ptm.getFolderId(),
                ptm.getPhase(), ptm.getProjectId());

        String newFolderName = pm.getProjectName() + "-" + newPhase;
        int newFolderId = copyFolder_phase(context, pc, preFhaseFolder, newPhase, newFolderName);

        //setup 新阶段根文件夹建立模板文档关系
        ProjectFolderModel newPhaseFolder =
                context.getProjectFileService().getPhaseFolderByParent(ptm.getFolderId(), newPhase, ptm.getProjectId());

        List<ProjectTemplateModel> models = new ArrayList<>();
        models = context.getProjectFileService().getTemplatesTree(newPhaseFolder.getFolderId(), models);

        pc.setPhase(newPhase);
        context.getDocumentFamily().setUpRefAllPhase(pc, selectTalkTemplates(context), models);
        payResult.setFolderId(newFolderId);

        return newPhase;
    }

    // 阶段拷贝文件夹和模板>601
    public int copyFolder_phase(UserProjectContext context, ProjectContext pc, ProjectFolderModel prePhaseFolder,
                                int newphase, String newFolderName) throws UnsupportedEncodingException {

        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectFolderModel newFolder = new ProjectFolderModel(prePhaseFolder);
        newFolder.setFolderName(newFolderName);
        newFolder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);

        newFolder.setUserId(ptm.getUserId2());
        newFolder.setAuditor(0);
        newFolder.setPhase(newphase);
        newFolder.setCreateBy(context.getUserId());

        int newFolderId = context.getProjectFileService().addFolderModel(newFolder);
        new PBFolderCopyTemplateModel().copyPhaseTemplates(context, pc, prePhaseFolder, newphase, newFolderId);

        //获取该文件夹下的文档
        List<ProjectFolderModel> preModels = context.getProjectFileService().getFolderModels(prePhaseFolder.getFolderId());
        for (ProjectFolderModel pfPre : preModels) {
            copyFoldersTree_phase(context, pfPre, newphase, newFolderId);
        }
        return newFolderId;
    }

    //阶段拷贝文件夹
    public void copyFoldersTree_phase(UserProjectContext context, ProjectFolderModel folderPre, int newphase, int newFolderId) throws UnsupportedEncodingException {

        ProjectFolderModel newFolder = new ProjectFolderModel(folderPre);
        //设置新文件夹的父id
        newFolder.setParentId(newFolderId);
        newFolder.setPhase(newphase);
        newFolder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);
        newFolder.setAuditor(0);

        //新文件家
        int newFolderIdSub = context.getProjectFileService().addFolderModel(newFolder);//copy文件夹
        new PBFolderCopyTemplateModel().copyPhaseTemplates(context, folderPre.getFolderId(), newphase, newFolderIdSub);
        //获取该文件夹下的文档
        List<ProjectFolderModel> preFolders = context.getProjectFileService().getFolderModels(folderPre.getFolderId());
        for (ProjectFolderModel pf : preFolders) {
            copyFoldersTree_phase(context, pf, newphase, newFolderIdSub);
        }

    }
}
//采购加工文件夹拷贝
//生成集成文档
//    void dictCopyTemplateComposite(UserProjectContext context, ProjectContext pc, int folderId) throws UnsupportedEncodingException {
//        new CopyTemplateModelComposite().copyTemplate(context, pc, folderId);  }