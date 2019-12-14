package ytb.project.daoservice;

import java.sql.Timestamp;
import java.util.List;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.IProjectFolderAssistModelService;
import ytb.project.model.ProjectFolderAssistModel;

public class ProjectFolderAssistModelServiceImpl  extends DAOService  implements IProjectFolderAssistModelService {
	public int existAssistFolder(int isShare, int projectId, int toUserId) {
		StringBuilder sql = new StringBuilder(128);
		sql.append(" select folder_id from ").append(ProjectFolderAssistModel.TABLE_NAME);
		sql.append(" where project_id=").append(projectId);
		sql.append(" and user_id=").append(toUserId);
		sql.append(" and folder_status=").append(isShare);
		List<ProjectFolderAssistModel> lst = YtbSql.selectList(sql, ProjectFolderAssistModel.class);
		return lst.size() > 0 ? lst.get(0).getFolderId() : 0;
	}

	public int createASFolder(UserProjectContext context,int isShare, int projectId, int toUserId, int fromUserId) {
		ProjectFolderAssistModel folderAssist = new ProjectFolderAssistModel();
		folderAssist.setProjectId(projectId);
		folderAssist.setTalkId(context.getProjectTalkModel().getTalkId());
		folderAssist.setFolderType(ProjectConst.FOLDER_TYPE_ASSIST);
		folderAssist.setFolderName("协助文件夹");
		folderAssist.setFolderStatus( (byte)isShare );
		folderAssist.setParentId(0);
		folderAssist.setDepth((byte)0);

		folderAssist.setUserId(toUserId);
		folderAssist.setAuditor(fromUserId);
		folderAssist.setFromUserId(fromUserId);
		folderAssist.setCreateBy(context.getUserId());
		folderAssist.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		return insert(folderAssist);
	}

	public int insert(ProjectFolderAssistModel assistModel) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.project_folder_assist");
		sql.append("(parent_id,folder_type,folder_name,project_id,talk_id,phase,user_id,auditor,from_user_id,depth,folder_status,create_by,update_time)");
		sql.append("values");		
		sql.append("(#{parentId},#{folderType},#{folderName},#{projectId},#{talkId},#{phase},#{userId},#{auditor},#{fromUserId},#{depth},#{folderStatus},#{createBy},#{updateTime})");
		return YtbSql.insert(sql,assistModel);

	}
	public void update(ProjectFolderAssistModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.project_folder_assist");
		sql.append("set parent_id=#{parentId},folder_type=#{folderType},folder_name=#{folderName},project_id=#{projectId},talk_id=#{talkId},phase=#{phase},user_id=#{userId},auditor=#{auditor},from_user_id=#{fromUserId},depth=#{depth},folder_status=#{folderStatus},create_by=#{createBy},update_time=#{updateTime}");
		sql.append(" where folder_id=#{folderId}");
		YtbSql.update(sql,m);

	}

	public void delete(int folderId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.project_folder_assist");
		sql.append(" where folder_id=#{folderId}");
		YtbSql.delete(sql,folderId);

	}

	public ProjectFolderAssistModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_folder_assist");
		sql.append(" where folder_id=#{folderId}");
		return YtbSql.selectOne(sql,id, ProjectFolderAssistModel.class);

	}


	public List<ProjectFolderAssistModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_folder_assist");
		sql.append(" where folder_id=#{folderId}");
		return YtbSql.selectList(sql,id, ProjectFolderAssistModel.class);

	}

	public List<ProjectFolderAssistModel>  selectList(ProjectFolderAssistModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_folder_assist");
		sql.append(" where folder_id=#{folderId}");
		return YtbSql.selectList(sql,m, ProjectFolderAssistModel.class);

	}

}
