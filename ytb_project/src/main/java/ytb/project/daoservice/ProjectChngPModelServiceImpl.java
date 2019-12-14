package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IProjectChngPModelService;
import ytb.project.model.tagtable.ProjectChngPModel;

public class ProjectChngPModelServiceImpl  extends DAOService  implements IProjectChngPModelService {

	public int insert(ProjectChngPModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.project_chng_p");
		sql.append("(project_id,talk_id,document_id,project_name_pre,project_name,phase,fee_pre,fee_now,fee_diff," +
				"fee_payed,fee_pp,fee_invoice,fee_phase,fee_makeup,rate_gp)");
		sql.append("values");		
		sql.append("(#{projectId},#{talkId},#{documentId},#{projectNamePre},#{projectName},#{phase},#{feePre}," +
				"#{feeNow},#{feeDiff},#{feePayed},#{feePp},#{feeInvoice},#{feePhase},#{feeMakeup},#{rateGp})");
		return YtbSql.insert(sql,m);

	}
	public void update(ProjectChngPModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.project_chng_p");
		sql.append("set project_id=#{projectId},document_id=#{documentId},project_name_pre=#{projectNamePre},project_name=#{projectName},phase=#{phase},fee_pre=#{feePre},fee_now=#{feeNow},fee_diff=#{feeDiff},fee_payed=#{feePayed},fee_pp=#{feePp},fee_invoice=#{feeInvoice},fee_phase=#{feePhase},fee_makeup=#{feeMakeup},rate_gp=#{rateGp}");
		sql.append(" where chng_id=#{chngId}");
		YtbSql.update(sql,m);

	}

	public void delete(int chngId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.project_chng_p");
		sql.append(" where chng_id=#{chngId}");
		YtbSql.delete(sql,chngId);

	}

	public void delete(int projectId,int documentId){
		StringBuilder sql=new StringBuilder(256);
		sql.append(" delete from ytb_project.project_chng_p");
		sql.append(" where project_id=").append(projectId);
		sql.append(" and  document_id=").append(documentId);
		YtbSql.delete(sql);

	}

	public ProjectChngPModel selectOneByDocumentId(ProjectChngPModel projectChngPModel) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_chng_p");
		sql.append(" where project_id=#{projectId}");
		sql.append(" and  document_id=#{documentId}");
		return YtbSql.selectOne(sql,projectChngPModel,ProjectChngPModel.class);

	}


	public List<ProjectChngPModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_chng_p");
		sql.append(" where chng_id=#{chngId}");
		return YtbSql.selectList(sql,id,ProjectChngPModel.class);

	}

	public List<ProjectChngPModel>  selectList(ProjectChngPModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_chng_p");
		sql.append(" where chng_id=#{chngId}");
		return YtbSql.selectList(sql,m,ProjectChngPModel.class);

	}

}
