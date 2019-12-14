package ytb.manager.pfUser.service.impl;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.manager.pfUser.dao.IDictDataCheckModelService;
import ytb.manager.pfUser.model.DictDataCheckModel;

import java.util.List;

public class DictDataCheckModelServiceImpl implements IDictDataCheckModelService {

	public int insert(DictDataCheckModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_manager.dict_data_check");
		sql.append("(check_name,check_sp,check_ret)");
		sql.append("values");		
		sql.append("(#{checkName},#{checkSp},#{checkRet})");
		return YtbSql.insert(sql,m);

	}
	public void update(DictDataCheckModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_manager.dict_data_check");
		sql.append("set check_name=#{checkName},check_sp=#{checkSp},check_ret=#{checkRet}");
		sql.append(" where check_id=#{checkId})");
		YtbSql.update(sql,m);

	}

	public void delete(int checkId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_manager.dict_data_check");
		sql.append(" where check_id=#{checkId})");
		YtbSql.delete(sql,checkId);

	}

	public DictDataCheckModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_data_check");
		sql.append(" where check_id=#{checkId}");
		return YtbSql.selectOne(sql,id,DictDataCheckModel.class);

	}


	public List<DictDataCheckModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_data_check");
		sql.append(" where check_id=#{checkId}");
		return YtbSql.selectList(sql,id,DictDataCheckModel.class);

	}

	public List<DictDataCheckModel>  selectList(DictDataCheckModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_data_check");
		sql.append(" where check_id=#{checkId}");
		return YtbSql.selectList(sql,m,DictDataCheckModel.class);

	}

}
