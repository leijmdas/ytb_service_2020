package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.IProjectMemberTaskModelService;
import ytb.project.model.tagtable.ProjectMemberTaskModel;

public class ProjectMemberTaskModelServiceImpl implements IProjectMemberTaskModelService {

	public int insert(ProjectMemberTaskModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.project_member_task");
		sql.append("(member_duty_id,project_id,task_name,task_remark,folder_id,create_mode,create_time,finish_time,task_status,templateId)");
		sql.append("values");		
		sql.append("(#{memberDutyId},#{projectId},#{taskName},#{taskRemark},#{folderId},#{createMode},#{createTime},#{finishTime},#{taskStatus},#{templateid})");
		return YtbSql.insert(sql,m);

	}
	public void update(ProjectMemberTaskModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.project_member_task");
		sql.append("set member_duty_id=#{memberDutyId},project_id=#{projectId},task_name=#{taskName},task_remark=#{taskRemark},folder_id=#{folderId},create_mode=#{createMode},create_time=#{createTime},finish_time=#{finishTime},task_status=#{taskStatus},templateId=#{templateid}");
		sql.append(" where m_duty_task_id=#{mDutyTaskId}");
		YtbSql.update(sql,m);

	}

	public void delete(int mDutyTaskId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.project_member_task");
		sql.append(" where m_duty_task_id=#{mDutyTaskId}");
		YtbSql.delete(sql,mDutyTaskId);

	}

	public ProjectMemberTaskModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_member_task");
		sql.append(" where m_duty_task_id=#{mDutyTaskId}");
		return YtbSql.selectOne(sql,id,ProjectMemberTaskModel.class);

	}


	public List<ProjectMemberTaskModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_member_task");
		sql.append(" where m_duty_task_id=#{mDutyTaskId}");
		return YtbSql.selectList(sql,id,ProjectMemberTaskModel.class);

	}

	public List<ProjectMemberTaskModel>  selectList(ProjectMemberTaskModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_member_task");
		sql.append(" where m_duty_task_id=#{mDutyTaskId}");
		return YtbSql.selectList(sql,m,ProjectMemberTaskModel.class);

	}

}
