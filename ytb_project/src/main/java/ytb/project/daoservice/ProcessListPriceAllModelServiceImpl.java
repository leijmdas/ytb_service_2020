package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IProcessListPriceAllModelService;
import ytb.project.model.tagtable.ProcessListPriceAllModel;

public class ProcessListPriceAllModelServiceImpl   extends DAOService  implements IProcessListPriceAllModelService {

	public int insert(ProcessListPriceAllModel allModel) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.process_list_price_all");
		sql.append("(all_money_desc,all_money_value,project_id,document_id,talk_id)");
		sql.append("values(#{allMoneyDesc},#{allMoneyValue},#{projectId},#{documentId},#{talkId})");
		return YtbSql.insert(sql,allModel);

	}
	public void update(ProcessListPriceAllModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.process_list_price_all");
		sql.append("set all_money_desc=#{allMoneyDesc},all_money_value=#{allMoneyValue},project_id=#{projectId},document_id=#{documentId},talk_id=#{talkId}");
		sql.append(" where process_list_price_all_id=#{processListPriceAllId}");
		YtbSql.update(sql,m);

	}

	public void delete(int id){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.process_list_price_all");
		sql.append(" where process_list_price_all_id=#{processListPriceAllId}");
		YtbSql.delete(sql,id);

	}

	public void delete(int projectId, int documentId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.process_list_price_all");
		sql.append(" where project_id=").append(projectId);
		sql.append(" and document_id=").append(documentId);
		YtbSql.delete(sql);

	}
	public ProcessListPriceAllModel selectOne(int projectId, int documentId) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_price_all");
		sql.append(" where project_id= ").append(projectId);
		sql.append(" and  document_id= ").append(documentId);
		return YtbSql.selectOne(sql, ProcessListPriceAllModel.class);

	}

	public ProcessListPriceAllModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_price_all");
		sql.append(" where process_list_price_all_id=#{processListPriceAllId}");
		return YtbSql.selectOne(sql,id,ProcessListPriceAllModel.class);

	}

	public List<ProcessListPriceAllModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_price_all");
		sql.append(" where process_list_price_all_id=#{processListPriceAllId}");
		return YtbSql.selectList(sql,id,ProcessListPriceAllModel.class);

	}

	public List<ProcessListPriceAllModel>  selectList(ProcessListPriceAllModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_price_all");
		sql.append(" where process_list_price_all_id=#{processListPriceAllId}");
		return YtbSql.selectList(sql,m,ProcessListPriceAllModel.class);

	}

}
