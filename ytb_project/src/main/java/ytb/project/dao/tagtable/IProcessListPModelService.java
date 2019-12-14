package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.ProcessListPModel;

import java.util.List;

public interface IProcessListPModelService {

	int insert(ProcessListPModel m);

	void update(ProcessListPModel m);

	void delete(int id);

	ProcessListPModel selectOne(int id);

	ProcessListPModel selectOne(int projectId, int workplanId);

	List<ProcessListPModel> selectList(int id);

	List<ProcessListPModel> selectList(ProcessListPModel listPModel);

}
