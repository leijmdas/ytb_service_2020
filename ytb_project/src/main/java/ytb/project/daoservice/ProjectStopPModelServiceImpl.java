package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IProjectStopPModelService;
import ytb.project.model.tagtable.ProjectStopPModel;

public class ProjectStopPModelServiceImpl extends DAOService implements IProjectStopPModelService {

	public int insert(ProjectStopPModel stopPModel) {
		StringBuilder sql = new StringBuilder(256);
		sql.append("insert into ytb_project.project_stop_p");
		sql.append("(project_id,talk_id,q,document_id,project_name,phase,why,fee,grade,fee_pay10,fee_back20," +
				"fee_back21,fee_back22,c_fee_plan,c_fee_payed,c_fee_pp,c_fee_invoice,c_fee_phase,c_fee_penalty)");
		sql.append("values");
		sql.append("(#{projectId},#{talkId},#{q},#{documentId},#{projectName},#{phase},#{why},#{fee},#{grade}," +
				"#{feePay10}," +
				"#{feeBack20},#{feeBack21},#{feeBack22},#{cFeePlan},#{cFeePayed},#{cFeePp},#{cFeeInvoice},#{cFeePhase},#{cFeePenalty})");
		return YtbSql.insert(sql, stopPModel);

	}

	public void update(ProjectStopPModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.project_stop_p");
		sql.append("set project_id=#{projectId},document_id=#{documentId},project_name=#{projectName},phase=#{phase},why=#{why},fee=#{fee},grade=#{grade},fee_pay10=#{feePay10},fee_back20=#{feeBack20},fee_back21=#{feeBack21},fee_back22=#{feeBack22},c_fee_plan=#{cFeePlan},c_fee_payed=#{cFeePayed},c_fee_pp=#{cFeePp},c_fee_invoice=#{cFeeInvoice},c_fee_phase=#{cFeePhase},c_fee_penalty=#{cFeePenalty},talk_id=#{talkId}");
		sql.append(" where stop_id=#{stopId}");
		YtbSql.update(sql,m);

	}

	public void delete(int documentId){
		StringBuilder sql=new StringBuilder(256);
		sql.append(" delete from ytb_project.project_stop_p");
		sql.append(" where document_id=#{documentId}");
		YtbSql.delete(sql,documentId);

	}

	public ProjectStopPModel selectOne(int documentId) {
		StringBuilder sql = new StringBuilder(256);
		sql.append(" select * from ytb_project.project_stop_p");
		sql.append(" where document_id=#{documentId}");
		return YtbSql.selectOne(sql, documentId, ProjectStopPModel.class);

	}


	public List<ProjectStopPModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_stop_p");
		sql.append(" where stop_id=#{stopId}");
		return YtbSql.selectList(sql,id,ProjectStopPModel.class);

	}

	public List<ProjectStopPModel>  selectList(ProjectStopPModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_stop_p");
		sql.append(" where stop_id=#{stopId}");
		return YtbSql.selectList(sql,m,ProjectStopPModel.class);

	}

}
