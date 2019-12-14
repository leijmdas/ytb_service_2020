package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IWorkGroupTaskModelService;
import ytb.project.model.tagtable.WorkGroupTaskModel;

public class WorkGroupTaskModelServiceImpl  extends DAOService implements IWorkGroupTaskModelService {

	public int insert(WorkGroupTaskModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.work_group_task");
		sql.append("(project_id,talk_id,work_job_id,document_id,work_job,user_id,user_name,work_task_json,work_task,is_admin,create_by,create_time,work_task_id)");
		sql.append("values");		
		sql.append("(#{projectId},#{talkId},#{workJobId},#{documentId},#{workJob},#{userId},#{userName},#{workTaskJson},#{workTask},#{isAdmin},#{createBy},#{createTime},#{workTaskId})");
		return YtbSql.insert(sql,m);

	}
	public void update(WorkGroupTaskModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.work_group_task");
		sql.append("set project_id=#{projectId},talk_id=#{talkId},work_job_id=#{workJobId},document_id=#{documentId},work_job=#{workJob},user_id=#{userId},user_name=#{userName},work_task_json=#{workTaskJson},work_task=#{workTask},is_admin=#{isAdmin},create_by=#{createBy},create_time=#{createTime},work_task_id=#{workTaskId}");
		sql.append(" where task_id=#{taskId}");
		YtbSql.update(sql,m);

	}

	public void delete(int taskId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.work_group_task");
		sql.append(" where task_id=#{taskId}");
		YtbSql.delete(sql,taskId);

	}

	public List<WorkGroupTaskModel> selectList(int talkId,int workplanId) {
		StringBuilder sql=new StringBuilder(256);
		sql.append(" select *  from ytb_project.work_group_task ");
		sql.append(" where talk_id=#{talkId}");
		sql.append(" and document_id=").append(workplanId);
		sql.append(" order by work_job_id ");

		return YtbSql.selectList(sql,talkId,WorkGroupTaskModel.class);

	}


	public List<WorkGroupTaskModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.work_group_task");
		sql.append(" where task_id=#{taskId}");
		return YtbSql.selectList(sql,id,WorkGroupTaskModel.class);

	}

	public List<WorkGroupTaskModel>  selectList(WorkGroupTaskModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.work_group_task");
		sql.append(" where task_id=#{taskId}");
		return YtbSql.selectList(sql,m,WorkGroupTaskModel.class);

	}

}
