package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IProjectWorkjobModelService;
import ytb.project.model.tagtable.ProjectWorkjobModel;

public class ProjectWorkjobModelServiceImpl  extends DAOService implements IProjectWorkjobModelService {

	public int insert(ProjectWorkjobModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.project_workjob");
		sql.append("(project_id,document_id,req_item_id,work_job_id,work_job,phase_type,is_default,title_alias)");
		sql.append("values");		
		sql.append("(#{projectId},#{documentId},#{reqItemId},#{workJobId},#{workJob},#{phaseType},#{isDefault},#{titleAlias})");
		return YtbSql.insert(sql,m);

	}
	public void update(ProjectWorkjobModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.project_workjob");
		sql.append("set project_id=#{projectId},document_id=#{documentId},req_item_id=#{reqItemId},work_job_id=#{workJobId},work_job=#{workJob},phase_type=#{phaseType},is_default=#{isDefault},title_alias=#{titleAlias}");
		sql.append(" where project_workjob_id=#{projectWorkjobId}");
		YtbSql.update(sql,m);

	}

	public void delete(int projectWorkjobId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.project_workjob");
		sql.append(" where project_workjob_id=#{projectWorkjobId}");
		YtbSql.delete(sql,projectWorkjobId);

	}

	public ProjectWorkjobModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_workjob");
		sql.append(" where project_workjob_id=#{projectWorkjobId}");
		return YtbSql.selectOne(sql,id, ProjectWorkjobModel.class);

	}


	public List<ProjectWorkjobModel> selectList(int documentId) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_workjob");
		sql.append(" where document_id=#{documentId}");
		return YtbSql.selectList(sql,documentId, ProjectWorkjobModel.class);

	}

	public List<ProjectWorkjobModel>  selectList(ProjectWorkjobModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_workjob");
		sql.append(" where project_workjob_id=#{projectWorkjobId}");
		return YtbSql.selectList(sql,m, ProjectWorkjobModel.class);

	}

}
