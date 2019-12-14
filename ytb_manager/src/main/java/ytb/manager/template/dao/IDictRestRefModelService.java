package ytb.manager.template.dao;

import ytb.manager.template.model.DictRestRefModel;

import java.util.List;

public interface IDictRestRefModelService {
	int getRefId(String refPath);

	int insert(DictRestRefModel m);

	void update(DictRestRefModel m);

	void delete(int id);

	DictRestRefModel selectOne(int id);

	DictRestRefModel selectOne(String refPath);

	List<DictRestRefModel> selectList();

	List<DictRestRefModel> selectList(int id);

	List<DictRestRefModel> selectList(DictRestRefModel m);

}
