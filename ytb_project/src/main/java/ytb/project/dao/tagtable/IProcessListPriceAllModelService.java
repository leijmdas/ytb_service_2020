package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.ProcessListPriceAllModel;

import java.util.List;
public interface IProcessListPriceAllModelService {

	int insert(ProcessListPriceAllModel m);
	void update(ProcessListPriceAllModel m);
	void delete(int id);

	void delete(int projectId, int documentId);

	ProcessListPriceAllModel selectOne(int id);
	ProcessListPriceAllModel selectOne(int projectId, int documentId);

	List<ProcessListPriceAllModel> selectList(int id);
	List<ProcessListPriceAllModel> selectList(ProcessListPriceAllModel m);

}
