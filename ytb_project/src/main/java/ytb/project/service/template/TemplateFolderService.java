package ytb.project.service.template;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.common.ytblog.YtbLog;
import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.projectFolderView.templateFolder.TemplateFolderModel;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.service.IProjectFileService;
import ytb.project.service.ProjectFlowService;

import java.util.ArrayList;
import java.util.List;

//文件夹
public class TemplateFolderService {
    ProjectSrvContext getInst(){
        return getInst();
    }

    public static TemplateFolderService getTemplateFolderService() {
        return templateFolderService;
    }

    static TemplateFolderService templateFolderService = new TemplateFolderService();

    ProjectFlowService getProjectFlowService() {
        return getInst().getProjectFlowService();
    }

    IProjectFileService getProjectFileService() {
        return getInst().getProjectFileService();
    }


    public List<TemplateFolderModel> findMyFileDocument(UserProjectContext context) {

        List<TemplateFolderModel>  lst = findMyFileDocument(context.getUserId());
        for(TemplateFolderModel m:lst){
            m.buildPhaseList(context);
        }
        return lst;
    }

    //获取文件夹 pa pb pm
    public List<ProjectTemplateModel> getFolderTemplates(UserProjectContext context,TemplateFolderModel folderModel) {
        ProjectModel pm =  context.getProjectModel();
        ProjectTalkModel ptm =  context.getProjectTalkModel();
        if (ptm.getPhase() <= ProjectConst.TalkPhase) {
            return getFoldersTalk(pm, context.getUserId(), ptm.getTalkId(), ptm.getPhase());
        } else if (folderModel.getUserType().equalsIgnoreCase("pm")) {
            return getFoldersDocPhasePm(context.getUserId(), pm.getProjectId(), ptm.getPhase());
        }

        return getFoldersDocPhasePaPb(0, pm.getProjectId(), ptm.getPhase());

    }

    List<ProjectTemplateModel> getFoldersTalk(ProjectModel projectModel, int userId, int talkId, int phase) {
        return getProjectFileService().getTemplateListByFolder(projectModel.getFolderId());
    }


    List<Integer> getFolders(int userId, int projectId, int phase) {
        StringBuilder sql=new StringBuilder(256);
        sql.append("select folder_id from ytb_project.project_folder ");
        sql.append(" where project_id=").append(projectId);
        sql.append(" and user_id=").append(userId);
        sql.append(" and phase=").append(phase);
        return YtbSql.selectList(sql, Integer.class);
    }

    List<ProjectTemplateModel> getFoldersDocPhasePaPb(int userId, int projectId, int phase) {
        return getFoldersDocPhasePm(userId, projectId, phase);
    }

    List<ProjectTemplateModel> getFoldersDocPhasePm(int userId, int projectId, int phase) {
        List<Integer> flst = getFolders(userId, projectId, phase);
        List<ProjectTemplateModel> lst = new ArrayList<>();
        for (Integer folder : flst) {
            List<ProjectTemplateModel> lst0 = getProjectFileService().getTemplateListByFolder(projectId);
            lst.addAll(lst0);
        }
        return lst;
    }


    public List<TemplateFolderModel> findMyFileDocument_company(UserProjectContext context) {
        return findMyFileDocument(context.getUserId());

    }

    public List<TemplateFolderModel> findMyFileDocument(Integer userId) {
        List<TemplateFolderModel> paLst = findMyFileDocument_PA(userId);
        List<TemplateFolderModel> pbLst = findMyFileDocument_PB(userId);
        List<TemplateFolderModel> pmLst = findMyFileDocument_PM(userId);
        paLst.addAll(pbLst);
        paLst.addAll(pmLst);
        return paLst;
    }

    //List<Map<String,Object>> list = projectReleaseService.getUserSendAndTalkPro(userId);
    public List<TemplateFolderModel> findMyFileDocument_PA(Integer userId) {

        StringBuilder sql = new StringBuilder(128);
        sql.append("select project_id,project_name,0 as phase,");
        sql.append(" company_id1 as company_id,'PA' as userType ");
        sql.append(" from ytb_project.project ");
        sql.append(" where user_id1=").append(userId);
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, TemplateFolderModel.class);
    }

    List<TemplateFolderModel> findMyFileDocument_PB(Integer userId) {
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select a.project_id,a.project_name,b.talk_id,b.phase,");
        sql.append(" company_id2 as company_id,'PB' as userType ");
        sql.append(" from ytb_project.project a, ytb_project.project_talk b  ");
        sql.append(" where a.project_id=b.project_id ") ;
        sql.append(" and b.project_id_ok>0 ") ;
        sql.append(" and user_id2 = ").append(userId);
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, TemplateFolderModel.class);

    }

    List<TemplateFolderModel> findMyFileDocument_PM(Integer userId) {
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select a.project_id,a.project_name,a.talk_id,b.phase,");
        sql.append(" b.company_id2 as company_id,'PM' as userType ");
        sql.append(" from ytb_project.project a ,ytb_project.project_talk b, ytb_project.work_group_member c ");
        sql.append(" where a.project_id=b.project_id and b.group_id=c.group_id") ;
        sql.append(" and b.project_id_ok>0");
        sql.append(" and c.user_id = ").append(userId);
        sql.append(" and c.is_admin= 2");
        sql.append(" and c.is_active= 1");
        YtbLog.logDebug(sql);
        return YtbSql.selectList(sql, TemplateFolderModel.class);

    }
}
