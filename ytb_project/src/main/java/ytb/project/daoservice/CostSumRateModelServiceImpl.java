package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.ICostSumRateModelService;
import ytb.project.model.tagtable.CostSumRateModel;

public class CostSumRateModelServiceImpl  extends DAOService  implements ICostSumRateModelService {

	public int insert(CostSumRateModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.cost_sum_rate");
		sql.append("(project_id,document_id,rate_desc,rate_value,tips,talk_id)");
		sql.append("values");		
		sql.append("(#{projectId},#{documentId},#{rateDesc},#{rateValue},#{tips},#{talkId})");
		return YtbSql.insert(sql,m);

	}
	public void update(CostSumRateModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.cost_sum_rate");
		sql.append("set project_id=#{projectId},document_id=#{documentId},rate_desc=#{rateDesc},rate_value=#{rateValue},tips=#{tips},talk_id=#{talkId}");
		sql.append(" where rate_id=#{rateId}");
		YtbSql.update(sql,m);

	}

	public void delete(int rateId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.cost_sum_rate");
		sql.append(" where rate_id=#{rateId}");
		YtbSql.delete(sql,rateId);

	}

	public CostSumRateModel selectOne(CostSumRateModel costSumRateModel) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.cost_sum_rate");
		sql.append(" where project_id=#{projectId}");
		sql.append(" and document_id=#{documentId}");
		return YtbSql.selectOne(sql,costSumRateModel,CostSumRateModel.class);

	}


	public List<CostSumRateModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.cost_sum_rate");
		sql.append(" where rate_id=#{rateId}");
		return YtbSql.selectList(sql,id,CostSumRateModel.class);

	}

	public List<CostSumRateModel>  selectList(CostSumRateModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.cost_sum_rate");
		sql.append(" where rate_id=#{rateId}");
		return YtbSql.selectList(sql,m,CostSumRateModel.class);

	}

}
