package ytb.manager.userCredit.service.impl;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.manager.userCredit.dao.IDictCreditModelService;
import ytb.manager.userCredit.model.DictCreditModel;

public class DictCreditModelServiceImpl implements IDictCreditModelService {

	public int insert(DictCreditModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_manager.dict_credit");
		sql.append("(credit_q,credit_remark)");
		sql.append("values");		
		sql.append("(#{creditQ},#{creditRemark})");
		return YtbSql.insert(sql,m);

	}
	public void update(DictCreditModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_manager.dict_credit");
		sql.append("set credit_q=#{creditQ},credit_remark=#{creditRemark}");
		sql.append(" where credit_grade=#{creditGrade})");
		YtbSql.update(sql,m);

	}

	public void delete(int creditGrade){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_manager.dict_credit");
		sql.append(" where credit_grade=#{creditGrade})");
		YtbSql.delete(sql,creditGrade);

	}

	public DictCreditModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_credit");
		sql.append(" where credit_grade=#{creditGrade}");
		return YtbSql.selectOne(sql,id,DictCreditModel.class);

	}

	@Override
	public List<DictCreditModel> selectList() {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_credit");
		return YtbSql.selectList(sql,DictCreditModel.class);
	}


	public List<DictCreditModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_credit");
		sql.append(" where credit_grade=#{creditGrade}");
		return YtbSql.selectList(sql,id,DictCreditModel.class);

	}

	public List<DictCreditModel>  selectList(DictCreditModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_credit");
		sql.append(" where credit_grade=#{creditGrade}");
		return YtbSql.selectList(sql,m,DictCreditModel.class);

	}

}
