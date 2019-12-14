package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IPpBudgetModelService;
import ytb.project.model.tagtable.PpBudgetModel;

public class PpBudgetModelServiceImpl  extends DAOService implements IPpBudgetModelService {

	public int insert(PpBudgetModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.pp_budget");
		sql.append("(project_id,document_id,purchase_price,process_count,process_price,sum,tips,ppd_user_name,user_id,purchase_count,talk_id)");
		sql.append("values");		
		sql.append("(#{projectId},#{documentId},#{purchasePrice},#{processCount},#{processPrice},#{sum},#{tips},#{ppdUserName},#{userId},#{purchaseCount},#{talkId})");
		return YtbSql.insert(sql,m);

	}
	public void update(PpBudgetModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.pp_budget");
		sql.append("set project_id=#{projectId},document_id=#{documentId},purchase_price=#{purchasePrice},process_count=#{processCount},process_price=#{processPrice},sum=#{sum},tips=#{tips},ppd_user_name=#{ppdUserName},user_id=#{userId},purchase_count=#{purchaseCount},talk_id=#{talkId}");
		sql.append(" where ppb_id=#{ppbId}");
		YtbSql.update(sql,m);

	}

	public void delete(int ppbId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.pp_budget");
		sql.append(" where ppb_id=#{ppbId}");
		YtbSql.delete(sql,ppbId);

	}

	public PpBudgetModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.pp_budget");
		sql.append(" where ppb_id=#{ppbId}");
		return YtbSql.selectOne(sql,id,PpBudgetModel.class);

	}


	public List<PpBudgetModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.pp_budget");
		sql.append(" where ppb_id=#{ppbId}");
		return YtbSql.selectList(sql,id,PpBudgetModel.class);

	}

	public List<PpBudgetModel>  selectList(PpBudgetModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.pp_budget");
		sql.append(" where ppb_id=#{ppbId}");
		return YtbSql.selectList(sql,m,PpBudgetModel.class);

	}

}
