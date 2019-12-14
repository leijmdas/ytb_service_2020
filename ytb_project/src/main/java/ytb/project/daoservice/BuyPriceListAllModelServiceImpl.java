package ytb.project.daoservice;


import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IBuyPriceListAllModelService;
import ytb.project.model.tagtable.BuyPriceListAllModel;

import java.util.List;

public class BuyPriceListAllModelServiceImpl extends DAOService implements IBuyPriceListAllModelService {

	public int insert(BuyPriceListAllModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.buy_price_list_all");
		sql.append("(buy_price_list_all_id,all_money_desc,all_money_value,project_id,document_id)");
		sql.append("values");		
		sql.append("(#{buyPriceListAllId},#{allMoneyDesc},#{allMoneyValue},#{projectId},#{documentId})");
		return YtbSql.insert(sql,m);

	}
	public void update(BuyPriceListAllModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.buy_price_list_all");
		sql.append("set buy_price_list_all_id=#{buyPriceListAllId},all_money_desc=#{allMoneyDesc},all_money_value=#{allMoneyValue},project_id=#{projectId},document_id=#{documentId}");
		sql.append(" where buy_price_list_all_id3=#{buyPriceListAllId}");
		YtbSql.update(sql,m);

	}

	public void delete(int id){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.buy_price_list_all");
		sql.append(" where buy_price_list_all_id=").append(id);
		YtbSql.delete(sql,1);

	}

	public void delete(int projectId,int documentId){
		StringBuilder sql=new StringBuilder(256);
		sql.append(" delete from ytb_project.buy_price_list_all");
		sql.append(" where project_id = ").append(projectId);
		sql.append(" and document_id = ").append(documentId);
		YtbSql.delete( sql );

	}
	public BuyPriceListAllModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.buy_price_list_all");
		sql.append(" where project_id= ").append(id);
		return YtbSql.selectOne(sql, BuyPriceListAllModel.class);

	}


	public List<BuyPriceListAllModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.buy_price_list_all");
		sql.append(" where null=null");
		return YtbSql.selectList(sql,id,BuyPriceListAllModel.class);

	}

	public List<BuyPriceListAllModel>  selectList(BuyPriceListAllModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.buy_price_list_all");
		sql.append(" where null=null");
		return YtbSql.selectList(sql,m,BuyPriceListAllModel.class);

	}

}
