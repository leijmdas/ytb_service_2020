package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.WorkGroupTaskModel;

import java.util.List;
public interface IWorkGroupTaskModelService {

	int insert(WorkGroupTaskModel m);

	void update(WorkGroupTaskModel m);

	void delete(int id);

	//WorkGroupTaskModel selectOne(int id);
	List<WorkGroupTaskModel> selectList(int talkId, int workplanId);

	List<WorkGroupTaskModel> selectList(int id);

	List<WorkGroupTaskModel> selectList(WorkGroupTaskModel m);

}
