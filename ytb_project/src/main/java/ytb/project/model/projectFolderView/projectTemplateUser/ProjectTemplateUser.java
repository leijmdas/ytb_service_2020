package ytb.project.model.projectFolderView.projectTemplateUser;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.ProjectTemplateMapper;
import ytb.project.dao.ProjectTemplateUserMapper;
import ytb.project.model.*;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.template.HistoryTemplate;
import ytb.project.service.template.VersionRule;
import ytb.project.service.impl.flow.ProjectFileServiceImpl;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;

import java.util.Date;
import java.util.List;

public class ProjectTemplateUser extends ProjectFileServiceImpl implements IProjectTemplateUser {

    public void versionUpgrade(int templateId, int status) {

        //版本升级
        ProjectTemplateModel templateModel = getProjectTemplateById(templateId);
        ProjectDocumentModel projectDocument = getProjectDocument(templateModel.getDocumentId());
        projectDocument.setDocumentIdV(projectDocument.getDocumentId());
        new HistoryTemplate().addProjectDocumentV(projectDocument);//添加源文件到历史表

        templateModel.setDocumentId(projectDocument.getDocumentId());
        templateModel.setTemplateIdV(templateModel.getTemplateId());
        new HistoryTemplate().addProjectTemplateV(templateModel);//添加文档到历史表

        ProjectTemplateModel mNew = getProjectTemplateById(templateId);
        String docVer = new VersionRule().setVersion(mNew.getDocVer(), status);//版本号变化
        mNew.setDocVer(docVer);
        mNew.setTemplateIdV(templateModel.getTemplateIdV());
        new VersionRule().modifyDocVer(mNew);

    }

    //获取用户协助的文档状态控制
    public List<ProjectTemplateUserModel> getTemplateUserModelsAssist( int templateId, int userId){

        try(SqlSession session = getSession()){
            ProjectTemplateUserMapper templateUserMapper = session.getMapper(ProjectTemplateUserMapper.class);
            return templateUserMapper.getProjectTemplateUserListAssist(templateId,userId);
        }
    }

    public void receiverModifyTemplateUserStatus (int pkId, int status, int activeStatus, int displayStatus){
        updateProjectTemplateUserStatus(pkId,status,activeStatus,displayStatus);
    }

    void updateProjectTemplateUserStatus(int pkId, int status, int activeStatus, int displayStatus){
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectTemplateUserMapper templateUserMapper = session.getMapper(ProjectTemplateUserMapper.class);
            templateUserMapper.updateProjectTemplateUserStatus(pkId,status,activeStatus,displayStatus);
        }finally {
            session.close();
        }
    }


    public List<ViewProjectTemplateUserModel> select(int folderId, int userId) {
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);
        try {
            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            return templateMapper.selectProjectTemplateUser(folderId, userId);

        } finally {
            session.close();
        }

    }


    public void receiverSubmitTemplateUserStatusAssist(ProjectTemplateUserModel ptum){
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectTemplateUserMapper dUSMapper = session.getMapper(ProjectTemplateUserMapper.class);
            dUSMapper.updateProjectTemplateUserStatusAssist(ptum);
            session.commit();
        }finally {
            session.close();
        }
    }

    @Override
    public ProjectTemplateUserModel getProTemplateUserByUserIdAndTemplateIdAssist(int userId, int templateIdAssist) {
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectTemplateUserMapper dUSMapper = session.getMapper(ProjectTemplateUserMapper.class);
            return dUSMapper.getProTemplateUserByUserIdAndTemplateIdAssist(userId,templateIdAssist);
        }finally {
            session.close();
        }
    }

    @Override
    public void smallChangeUpgradeTemplate(UserProjectContext context) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        List<ProjectTemplateModel> list = ptm.selectPhaseAllTemplates();//找到当前阶段文件夹下的所有文档，升级
        for(ProjectTemplateModel templateModel : list){
            IProjectFileDAOService.getProjectTemplateUser().versionUpgrade(templateModel.getTemplateId(), 0);
        }
    }

    @Override
    public void modifyProjectTemplateUserBatch(List<ProjectTemplateUserModel> templateUserList) {
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectTemplateUserMapper dUSMapper = session.getMapper(ProjectTemplateUserMapper.class);
            dUSMapper.modifyProjectTemplateUserBatch(templateUserList);
            session.commit();
        }finally {
            session.close();
        }
    }

    public void modify(ProjectTemplateUserModel templateUserModel) {
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectTemplateUserMapper dUSMapper = session.getMapper(ProjectTemplateUserMapper.class);
            dUSMapper.modifyProjectTemplateUserModel(templateUserModel);
            session.commit();
        }finally {
            session.close();
        }
    }


    public ProjectTemplateUserModel selectOne(int userId, int documentId){
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);

        try{
            ProjectTemplateUserMapper dUSMapper = session.getMapper(ProjectTemplateUserMapper.class);
            return dUSMapper.selectProjectTemplateUser(userId,documentId);
        }finally {
            session.close();
        }
    }
    //洽谈阶段修改用户文档状态
    public void modify(int pa, int pb, int templateId, int status) {

        ProjectTemplateUserModel paDus = selectOne(pa, templateId);//甲方
        ProjectTemplateUserModel pbDus = selectOne(pb, templateId);//乙方
        if (status == 0) {//通过
            if (pbDus.getActiveStatus() == ProjectConst.ACTIVE_STATUS_ToWrite) {//编制,未开始
                pbDus.setStatus(ProjectConst.TASK_STATUS_NotSubmitted);
                pbDus.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToModify);
                paDus.setStatus(ProjectConst.TASK_STATUS_NotSubmitted);
            } else if (pbDus.getActiveStatus() == ProjectConst.ACTIVE_STATUS_ToModify) {//去修改,未提交
                pbDus.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
                pbDus.setStatus(ProjectConst.TASK_STATUS_Submitted);
                paDus.setStatus(ProjectConst.TASK_STATUS_Submitted);
                paDus.setDisplayStatusLight();
            } else if (pbDus.getActiveStatus() == ProjectConst.ACTIVE_STATUS_GoRead
                    && pbDus.getStatus() == ProjectConst.TASK_STATUS_NotStart) {
                pbDus.setStatus(ProjectConst.TASK_STATUS_Viewed);
            }else if(pbDus.getActiveStatus() == ProjectConst.ACTIVE_STATUS_CHANGE){//去变更
                pbDus.setStatus(ProjectConst.TASK_STATUS_NotSubmitted);
                pbDus.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToModify);
            }
        } else if (status == 1) {
            paDus.setStatus(ProjectConst.TASK_STATUS_Passing);
            pbDus.setStatus(ProjectConst.TASK_STATUS_Passing);
        } else if (status == 2) {
            paDus.setStatus(ProjectConst.TASK_STATUS_NotPassing);
            pbDus.setStatus(ProjectConst.TASK_STATUS_NotPassing);
        }
        modify(pbDus);
        if(paDus !=null){
            paDus.setCreateTime(new Date());
            modify(paDus);
        }
    }


    public void add(ProjectTemplateUserModel templateUserModel) {
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);
        try {
            ProjectTemplateUserMapper templateUserMapper = session.getMapper(ProjectTemplateUserMapper.class);
            templateUserMapper.addProjectTemplateUserModel(templateUserModel);
            session.commit();
        } finally {
            session.close();
        }
    }

    //添加文档的控制状态
    public void add(UserProjectContext context,
                    int userId,
                    int templateId,
                    int status,
                    int activeStatus,
                    int displayStatus,
                    int authorId) {
        add(context,
                userId,
                templateId,
                status,
                activeStatus,
                displayStatus,
                authorId,
                0);

    }

    //生成文档状态控制
    public void add(UserProjectContext context,
                    int userId,
                    int templateId,
                    int status,
                    int activeStatus,
                    int displayStatus,
                    int authorId,
                    int templateIdAssits) {
        ProjectTemplateUserModel m = new ProjectTemplateUserModel();
        m.setTemplateId(templateId);
        m.setProjectId(context.getProjectModel().getProjectId());
        m.setTalkId(context.getProjectTalkModel().getTalkId());
        m.setStatus(status);
        m.setUserId(userId);
        m.setActiveStatus(activeStatus);
        m.setDisplayStatus(displayStatus);
        m.setAuthor(authorId);
        m.setTemplateIdAssist(templateIdAssits);

        add(m);
    }


    //修改洽谈终止文档状态
    public void modifyByTalkEndStatus(UserProjectContext context, int templateId) {
        // 甲方
        ProjectTemplateUserModel pa = selectOne(context.getProjectModel().getUserId1(), templateId);
        if (pa != null) {
            pa.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
            pa.setDisplayStatusLight();
        }
        modify(pa);
        // 乙方
        ProjectTemplateUserModel pb = selectOne(context.getProjectTalkModel().getUserId2(), templateId);
        pb.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
        pb.setDisplayStatusLight( );
        modify(pb);
    }

    //组员与负责人
    public void modifyStatusPbPm(int pb, int pm, int templateId) {

        ProjectTemplateUserModel ptuPB = selectOne(pb, templateId);//负责人
        ProjectTemplateUserModel ptuPM = selectOne(pm, templateId);//组员
        if (ptuPM.getActiveStatus() == ProjectConst.ACTIVE_STATUS_ToWrite) {//编制,未开始
            ptuPM.setStatus(ProjectConst.TASK_STATUS_NotSubmitted);
            ptuPM.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToModify);
            ptuPB.setStatus(ProjectConst.TASK_STATUS_NotSubmitted);
        } else if (ptuPM.getActiveStatus() == ProjectConst.ACTIVE_STATUS_ToModify) {//去修改,未提交
            ptuPM.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
            ptuPM.setStatus(ProjectConst.TASK_STATUS_Submitted);
            ptuPB.setStatus(ProjectConst.TASK_STATUS_NotStart);
            ptuPB.setDisplayStatusLight( );
        } else if (ptuPM.getActiveStatus() == ProjectConst.ACTIVE_STATUS_GoRead && ptuPM.getStatus() == ProjectConst.TASK_STATUS_NotStart) {
            ptuPM.setStatus(ProjectConst.TASK_STATUS_Viewed);
        }
        ptuPM.setCreateTime(new Date());
        ptuPB.setCreateTime(new Date());
        modify(ptuPM);
        modify(ptuPB);
    }

    //查询文件是否已经修改
    public int paCheckModifyStatus(int folderId, int color, int userId,int author) {
        //获取该文件夹下的文档
        List<ProjectTemplateModel> lst = getTemplateListByFolder(folderId);
        //查询子文件夹
        List<ProjectFolderModel> list = getFolderModels(folderId);
        for (ProjectTemplateModel t : lst) {
            List<ViewProjectTemplateUserModel> vlst = selectViewProjectTemplateUserModel(folderId, userId, author);
            for (ViewProjectTemplateUserModel m : vlst) {
                if (m.getStatus() == ProjectConst.TASK_STATUS_NotStart) {
                    color = 1;
                    return color;
                }
            }
        }

        for (ProjectFolderModel pf : list) {
            color = paCheckModifyStatus(pf.getFolderId(), color, userId,author);
            if (color == 1) {
                return color;
            }
        }
        return color;
    }

    //修改洽谈审核文档状态
    public void modifyTalkAuditStatus(int ipa, int ipb, int templateId, int status) {

        ProjectTemplateUserModel pa = selectOne(ipa, templateId);//甲方
        ProjectTemplateUserModel pb = selectOne(ipb, templateId);//乙方

        if (status == 1) {//审核通过
            pa.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
            pb.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
        } else {
            pa.setDisplayStatusGray();
            pa.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToAudit);
            pa.setStatus(ProjectConst.TASK_STATUS_NotStart);

            pb.setDisplayStatusLight();//0可以点击 1：灰色不能点击 2：变灰色可点击
            pb.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToWrite);
            pb.setStatus(ProjectConst.TASK_STATUS_NotStart);
        }

        modify(pb);

        modify(pa);
    }


    public List<ViewProjectTemplateUserModel> selectViewProjectTemplateUserModel(int folderId,
                                                                                 int userId,
                                                                                 int author) {
        try(SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)) {
            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            return templateMapper.selectViewProjectTemplateUserModel(folderId, userId,author);

        }
    }


//    public List<ViewProjectTemplateUserModel> selectModel(int folderId, int userId, int author) {
//
//        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);
//        try {
//            ProjectTemplateMapper m = session.getMapper(ProjectTemplateMapper.class);
//            return m.selectViewProjectTemplateUserModel(folderId, userId,author);
//
//        } finally {
//            session.close();
//        }
//
//    }
}
