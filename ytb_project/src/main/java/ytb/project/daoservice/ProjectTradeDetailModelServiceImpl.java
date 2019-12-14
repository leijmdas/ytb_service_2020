package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.IProjectTradeDetailModelService;
import ytb.project.model.ProjectTradeDetailModel;
import ytb.project.model.ProjectTradeModel;

public class ProjectTradeDetailModelServiceImpl  extends DAOService implements IProjectTradeDetailModelService {

	public int insert(ProjectTradeModel tradeModel) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.project_trade ");
		sql.append("(trade_id,memo)");
		sql.append("values(#{tradeId},#{memo})");
		return YtbSql.insert(sql,tradeModel);

	}

	public void update(ProjectTradeModel tradeModel){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.project_trade ");
		sql.append("set memo=#{memo}");
		sql.append(" where trade_id=#{tradeId} ");
		YtbSql.update(sql,tradeModel);

	}

	public void delete(int tradeId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.project_trade ");
		sql.append(" where trade_id=#{tradeId} ");
		YtbSql.delete(sql,tradeId);

	}

	public ProjectTradeDetailModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append(" select *  from ytb_project.project_trade ");
		sql.append(" where trade_id=#{tradeId}");
		return YtbSql.selectOne(sql,id,ProjectTradeDetailModel.class);

	}


	public List<ProjectTradeModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append(" select *  from ytb_project.project_trade ");
		sql.append(" where trade_id=#{tradeId}");
		return YtbSql.selectList(sql,id,ProjectTradeModel.class);

	}

	public List<ProjectTradeModel>  selectList(ProjectTradeModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_trade ");
		sql.append(" where trade_id=#{tradeId}");
		return YtbSql.selectList(sql,m,ProjectTradeModel.class);

	}

}
