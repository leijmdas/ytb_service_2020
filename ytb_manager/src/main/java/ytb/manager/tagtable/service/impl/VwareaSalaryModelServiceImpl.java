package ytb.manager.tagtable.service.impl;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.manager.tagtable.dao.IVwareaSalaryModelService;
import ytb.manager.tagtable.model.business.VwareaSalaryModel;

public class VwareaSalaryModelServiceImpl implements IVwareaSalaryModelService {

	public int insert(VwareaSalaryModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_manager.vwarea_salary");
		sql.append("(area_id,name,day_pay)");
		sql.append("values");		
		sql.append("(#{areaId},#{name},#{dayPay})");
		return YtbSql.insert(sql,m);

	}
	public void update(VwareaSalaryModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_manager.vwarea_salary");
		sql.append("set area_id=#{areaId},name=#{name},day_pay=#{dayPay}");
		sql.append(" where null)");
		YtbSql.update(sql,m);

	}



	public void delete(int id){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_manager.vwarea_salary");
		sql.append(" where null=null)");
		YtbSql.delete(sql,id);

	}

	public VwareaSalaryModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.vwarea_salary");
		sql.append(" where null=null");
		return YtbSql.selectOne(sql,id,VwareaSalaryModel.class);

	}


	public List<VwareaSalaryModel> selectList( ) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.vwarea_salary");
	 	return YtbSql.selectList(sql, VwareaSalaryModel.class);

	}

	public List<VwareaSalaryModel>  selectList(VwareaSalaryModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.vwarea_salary");
		sql.append(" where null=null");
		return YtbSql.selectList(sql,m,VwareaSalaryModel.class);

	}

}
