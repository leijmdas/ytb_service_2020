package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IProcessListPModelService;
import ytb.project.model.tagtable.ProcessListPModel;

public class ProcessListPModelServiceImpl  extends DAOService implements IProcessListPModelService {

	public int insert(ProcessListPModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.process_list_p");
		sql.append("(project_id,document_id,day,received_user,received_phone,received_addr,received_company_name,received_company_credit_code,received_company_addr,talk_id)");
		sql.append("values");		
		sql.append("(#{projectId},#{documentId},#{day},#{receivedUser},#{receivedPhone},#{receivedAddr},#{receivedCompanyName},#{receivedCompanyCreditCode},#{receivedCompanyAddr},#{talkId})");
		return YtbSql.insert(sql,m);

	}
	public void update(ProcessListPModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.process_list_p");
		sql.append("set project_id=#{projectId},document_id=#{documentId},day=#{day},received_user=#{receivedUser},received_phone=#{receivedPhone},received_addr=#{receivedAddr},received_company_name=#{receivedCompanyName},received_company_credit_code=#{receivedCompanyCreditCode},received_company_addr=#{receivedCompanyAddr},talk_id=#{talkId}");
		sql.append(" where process_list_p_id=#{processListPId}");
		YtbSql.update(sql,m);

	}

	public void delete(int processListPId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.process_list_p");
		sql.append(" where process_list_p_id=#{processListPId}");
		YtbSql.delete(sql,processListPId);

	}

	public ProcessListPModel selectOne(int id) {
			StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_p");
		sql.append(" where process_list_p_id=#{processListPId}");
		return YtbSql.selectOne(sql,id,ProcessListPModel.class);

	}
	public ProcessListPModel selectOne(int projectId, int workplanId) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_p");
		sql.append(" where project_id = ").append(projectId);
		sql.append(" and  document_id = ").append(workplanId);
		return YtbSql.selectOne(sql,ProcessListPModel.class);

	}

		public List<ProcessListPModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_p");
		sql.append(" where process_list_p_id=#{processListPId}");
		return YtbSql.selectList(sql,id,ProcessListPModel.class);

	}

	public List<ProcessListPModel>  selectList(ProcessListPModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_p");
		sql.append(" where process_list_p_id=#{processListPId}");
		return YtbSql.selectList(sql,m,ProcessListPModel.class);

	}

}
