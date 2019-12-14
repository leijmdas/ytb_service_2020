package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.ProcessListPricePModel;

import java.util.List;
public interface IProcessListPricePModelService {

	int insert(ProcessListPricePModel m);
	void update(ProcessListPricePModel m);
	void delete(int id);
	ProcessListPricePModel selectOne(int id);

	List<ProcessListPricePModel> selectList(int id);
	List<ProcessListPricePModel> selectList(ProcessListPricePModel m);

}
