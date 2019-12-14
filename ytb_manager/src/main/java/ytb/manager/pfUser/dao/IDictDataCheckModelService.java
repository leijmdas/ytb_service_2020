package ytb.manager.pfUser.dao;

import ytb.manager.pfUser.model.DictDataCheckModel;

import java.util.List;
public interface IDictDataCheckModelService {

	int insert(DictDataCheckModel m);
	void update(DictDataCheckModel m);
	void delete(int id);
	DictDataCheckModel selectOne(int id);

	List<DictDataCheckModel> selectList(int id);
	List<DictDataCheckModel> selectList(DictDataCheckModel m);

}
