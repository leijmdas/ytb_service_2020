package ytb.manager.tagtable.dao;

import ytb.manager.tagtable.model.business.VwareaSalaryModel;

import java.util.List;
public interface IVwareaSalaryModelService {

	int insert(VwareaSalaryModel m);
	void update(VwareaSalaryModel m);
	void delete(int id);
	VwareaSalaryModel selectOne(int id);

	List<VwareaSalaryModel> selectList( );
	List<VwareaSalaryModel> selectList(VwareaSalaryModel m);

}
