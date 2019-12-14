package ytb.project.service.impl.flow;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.model.UserShareModel;
import ytb.project.service.IFlowFolderAssist;
import ytb.project.service.template.TemplateAssist;

import java.util.List;

public class FlowFolderAssist extends FlowFolder implements IFlowFolderAssist {


    //查询共享
    public List<UserShareModel> getUserShareList(int projectId, int userId) {
        return getProjectFolderAssistList(projectId, userId, TemplateAssist.DocumentAssist_TYPE_SHARE);
    }

    //查询协助
    public List<UserShareModel> getUserAssitList(int projectId, int userId) {
        return getProjectFolderAssistList(projectId, userId, TemplateAssist.DocumentAssist_TYPE_ASSIST);
    }

    //查询审核
    public List<UserShareModel> getUserAuditList(int projectId , int userId) {
        return getProjectFolderAssistList(projectId,userId, TemplateAssist.DocumentAssist_TYPE_AUDIT);
    }


    List<UserShareModel> getProjectFolderAssistList(int projectId , int userId,int assistType){

        StringBuilder sql = new StringBuilder(256);//查询不属于甲方的所有控制文档
        sql.append(" select b.doc_type,a.folder_id,a.project_id,a.user_id,b.document_id,b.title as docName");
        sql.append(" from  ytb_project.project_folder_assist a");
        sql.append(" inner join ytb_project.project_template_assist b");
        sql.append(" on a.folder_id = b.folder_id");
        sql.append(" where a.folder_status = ").append(assistType);
        sql.append(" and a.project_id = ").append(projectId);
        sql.append(" and a.user_id = ").append(userId);

        return YtbSql.selectList(sql, UserShareModel.class);
    }

}
