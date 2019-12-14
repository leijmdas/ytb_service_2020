package ytb.manager.template.daoservice;

import java.util.List;

import ytb.common.ehcache.SysCacheService;
import ytb.common.utils.YtbSql;
import ytb.manager.template.dao.IDictRestRefModelService;
import ytb.manager.template.model.DictRestRefModel;

public class DictRestRefModelServiceImpl implements IDictRestRefModelService {

	@Override
	public int getRefId(String refPath) {
		DictRestRefModel m = selectOne(refPath);
		if (m == null) {
			m = new DictRestRefModel();
			m.setRefNum((short) 1);
			m.setRefPath(refPath);
			return insert(m);
		}

		return m.getRefId();
	}

	public int insert(DictRestRefModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_manager.dict_rest_ref");
		sql.append("(ref_path,ref_num)");
		sql.append("values");		
		sql.append("(#{refPath},#{refNum})");
		return YtbSql.insert(sql,m);

	}
	public void update(DictRestRefModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_manager.dict_rest_ref");
		sql.append("set ref_path=#{refPath},ref_num=#{refNum}");
		sql.append(" where ref_id=#{refId})");
		YtbSql.update(sql,m);

	}

	public void delete(int refId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_manager.dict_rest_ref");
		sql.append(" where ref_id=#{refId})");
		YtbSql.delete(sql,refId);

	}

	public DictRestRefModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_rest_ref");
		sql.append(" where ref_id=#{refId}");
		return YtbSql.selectOne(sql,id,DictRestRefModel.class);

	}

	public DictRestRefModel selectOne(String refPath) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_rest_ref");
		sql.append(" where ref_path=#{refPath}");
		return YtbSql.selectOne(sql, refPath, DictRestRefModel.class);

	}

	public List<DictRestRefModel> selectList() {
		//StringBuilder sql = new StringBuilder(256);
		//sql.append("select *  from ytb_manager.dict_rest_ref ");
		//return YtbSql.selectList(sql, DictRestRefModel.class);
		return SysCacheService.table2Cache("dict_rest_ref",DictRestRefModel.class);
	}

	public List<DictRestRefModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_rest_ref");
		sql.append(" where ref_id=#{refId}");
		return YtbSql.selectList(sql,id,DictRestRefModel.class);

	}

	public List<DictRestRefModel>  selectList(DictRestRefModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_manager.dict_rest_ref");
		sql.append(" where ref_id=#{refId}");
		return YtbSql.selectList(sql,m,DictRestRefModel.class);

	}

}
