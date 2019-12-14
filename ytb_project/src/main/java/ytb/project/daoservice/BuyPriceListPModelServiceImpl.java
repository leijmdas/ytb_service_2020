package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IBuyPriceListPModelService;
import ytb.project.model.tagtable.BuyPriceListPModel;

public class BuyPriceListPModelServiceImpl extends DAOService implements IBuyPriceListPModelService {

	public int insert(BuyPriceListPModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.buy_price_list_p");
		sql.append("(project_id,document_id,day_1,receiver_user,receiver_phone,receiver_addr,receiver_company_name,receiver_company_credit_code,receiver_company_addr,day_2,send_user,send_phone,send_addr,send_company_name,send_company_credit_code,send_company_addr)");
		sql.append("values");		
		sql.append("(#{projectId},#{documentId},#{day1},#{receiverUser},#{receiverPhone},#{receiverAddr},#{receiverCompanyName},#{receiverCompanyCreditCode},#{receiverCompanyAddr},#{day2},#{sendUser},#{sendPhone},#{sendAddr},#{sendCompanyName},#{sendCompanyCreditCode},#{sendCompanyAddr})");
		return YtbSql.insert(sql,m);

	}
	public void update(BuyPriceListPModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.buy_price_list_p");
		sql.append("set project_id=#{projectId},document_id=#{documentId},day_1=#{day1},receiver_user=#{receiverUser},receiver_phone=#{receiverPhone},receiver_addr=#{receiverAddr},receiver_company_name=#{receiverCompanyName},receiver_company_credit_code=#{receiverCompanyCreditCode},receiver_company_addr=#{receiverCompanyAddr},day_2=#{day2},send_user=#{sendUser},send_phone=#{sendPhone},send_addr=#{sendAddr},send_company_name=#{sendCompanyName},send_company_credit_code=#{sendCompanyCreditCode},send_company_addr=#{sendCompanyAddr}");
		sql.append(" where buy_price_list_param_id=#{buyPriceListParamId}");
		YtbSql.update(sql,m);

	}

	public void delete(int buyPriceListParamId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.buy_price_list_p");
		sql.append(" where buy_price_list_param_id=#{buyPriceListParamId}");
		YtbSql.delete(sql,buyPriceListParamId);

	}

	public BuyPriceListPModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.buy_price_list_p");
		sql.append(" where buy_price_list_param_id=#{buyPriceListParamId}");
		return YtbSql.selectOne(sql,id,BuyPriceListPModel.class);

	}

	public BuyPriceListPModel selectOne(int projectId, int workplanId) {
		StringBuilder sql=new StringBuilder(256);
		sql.append(" select *  from ytb_project.buy_price_list_p");
		sql.append(" where project_id = ").append(projectId);
		sql.append(" and  document_id = ").append(workplanId);
		return YtbSql.selectOne(sql, BuyPriceListPModel.class);

	}

	//public ProjectPlanModel getProjectPlan(int projectId, int documentId) {


	public List<BuyPriceListPModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.buy_price_list_p");
		sql.append(" where buy_price_list_param_id=#{buyPriceListParamId}");
		return YtbSql.selectList(sql,id,BuyPriceListPModel.class);

	}

	public List<BuyPriceListPModel>  selectList(BuyPriceListPModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.buy_price_list_p");
		sql.append(" where buy_price_list_param_id=#{buyPriceListParamId}");
		return YtbSql.selectList(sql,m,BuyPriceListPModel.class);

	}

}
