package ytb.project.daoservice;

import java.util.List;

import ytb.common.ytblog.YtbLog;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.IVProjectTradeAccountModelService;
import ytb.project.model.VProjectTradeAccountModel;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;

public class VProjectTradeAccountModelServiceImpl extends DAOService implements IVProjectTradeAccountModelService {

	public int insert(VProjectTradeAccountModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ").append(VProjectTradeAccountModel.TABLE_NAME);
		sql.append("(project_trade_id,parent_trade_id,trade_id,service_type,user_id,company_id,project_id,talk_id,phase,template_id,update_time,memo,trade_type,trade_subtype,balance)");
		sql.append("values");		
		sql.append("(#{projectTradeId},#{parentTradeId},#{tradeId},#{serviceType},#{userId},#{companyId},#{projectId},#{talkId},#{phase},#{templateId},#{updateTime},#{memo},#{tradeType},#{tradeSubtype},#{balance})");
		return YtbSql.insert(sql,m);

	}
	public void update(VProjectTradeAccountModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ").append(VProjectTradeAccountModel.TABLE_NAME);
		sql.append("set project_trade_id=#{projectTradeId},parent_trade_id=#{parentTradeId},trade_id=#{tradeId},service_type=#{serviceType},user_id=#{userId},company_id=#{companyId},project_id=#{projectId},talk_id=#{talkId},phase=#{phase},template_id=#{templateId},update_time=#{updateTime},memo=#{memo},trade_type=#{tradeType},trade_subtype=#{tradeSubtype},balance=#{balance}");
		sql.append(" where null");
		YtbSql.update(sql,m);

	}

	public void delete(int talkId, int serviceType) {
		StringBuilder sql = new StringBuilder(256);
		sql.append(" delete from ").append(VProjectTradeAccountModel.TABLE_NAME);
		sql.append(" where talk_id= ").append(talkId);
		sql.append(" and service_type= ").append(serviceType);
		YtbSql.delete(sql);

	}

	public VProjectTradeAccountModel selectOne(int ide) {
		StringBuilder sql = new StringBuilder(256);
		sql.append("select *  from ") .append(VProjectTradeAccountModel.TABLE_NAME);
		sql.append(" where talk_id = ").append(ide);
		return YtbSql.selectOne(sql, VProjectTradeAccountModel.class);

	}
//	public List<VProjectTradeAccountModel> selectListByTalk(int talkId ) {
//		StringBuilder sql = new StringBuilder(256);
//		sql.append("select *  from ytb_project.v_project_trade_account");
//		sql.append(" where talk_id = ").append(talkId);
//		sql.append(" and trade_type between   ");
//		sql.append(TradeConst.TRADE_TYPE_INCOME).append(" and ").append(TradeConst.TRADE_TYPE_INCOME_REFUND);
//		sql.append(" and trade_subtype =   ").append(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
//		sql.append(" and service_type not between  ");
//		sql.append(TradeConst.SERVICE_TYPE_assist).append(" and ").append(TradeConst.SERVICE_TYPE_cash);
//		return YtbSql.selectList(sql, VProjectTradeAccountModel.class);
//	}

	//计算乙方冻结金额，计算出解冻金额
	public List<VProjectTradeAccountModel> selectListPb(int projectId, int userIdPa ) {
		StringBuilder sql = new StringBuilder(256);
		sql.append(" select *  from ").append(VProjectTradeAccountModel.TABLE_NAME);
		sql.append(" where project_id = ").append(projectId);
		//sql.append(" and trade_type between   ");
		//sql.append(TradeConst.TRADE_TYPE_INCOME).append(" and ").append(TradeConst.TRADE_TYPE_INCOME_REFUND);
		sql.append(" and trade_subtype in (101,102) ");// .append(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
		sql.append(" and service_type not between  ");
		sql.append(TradeConst.SERVICE_TYPE_assist).append(" and ").append(TradeConst.SERVICE_TYPE_cash);
		sql.append(" and user_id != ").append(userIdPa);
		sql.append(" and status  = ").append(AccountConst.STATUS_OK);

		return YtbSql.selectList(sql, VProjectTradeAccountModel.class);

	}

	//计算甲方冻结金额，计算出解冻金额
	public List<VProjectTradeAccountModel> selectListPa(int projectId, int userIdPa) {

		return selectList(projectId, userIdPa);

	}

	public List<VProjectTradeAccountModel> selectList(int projectId, int userId) {
		StringBuilder sql = new StringBuilder(256);
		sql.append(" select *  from  ").append(VProjectTradeAccountModel.TABLE_NAME);
		;
		sql.append(" where project_id = ").append(projectId);
		sql.append(" and trade_subtype in (101,102) ");// .append(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
		//sql.append(" and trade_type between   ");
		//sql.append(TradeConst.TRADE_TYPE_INCOME).append(" and ").append(TradeConst.TRADE_TYPE_INCOME_REFUND);
		//sql.append(" and trade_subtype = ").append(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
		sql.append(" and service_type not between  ");
		sql.append(TradeConst.SERVICE_TYPE_assist).append(" and ").append(TradeConst.SERVICE_TYPE_cash );
		sql.append(" and user_id = ").append(userId);
		sql.append(" and status  = ").append(AccountConst.STATUS_OK);
		YtbLog.logDebug("VProjectTradeAccountModelServiceImpl selectList",sql.toString());
		return YtbSql.selectList(sql, VProjectTradeAccountModel.class);

	}
	public List<VProjectTradeAccountModel>  selectList(VProjectTradeAccountModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from  ").append(VProjectTradeAccountModel.TABLE_NAME);;
		sql.append(" where null=null");
		sql.append("limt 11");
		return YtbSql.selectList(sql,m,VProjectTradeAccountModel.class);

	}

}
