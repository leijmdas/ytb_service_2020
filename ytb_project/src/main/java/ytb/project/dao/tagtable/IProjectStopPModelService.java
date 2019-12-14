package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.ProjectStopPModel;

import java.util.List;
public interface IProjectStopPModelService {

	int insert(ProjectStopPModel m);
	void update(ProjectStopPModel m);
	void delete(int id);
	ProjectStopPModel selectOne(int id);

	List<ProjectStopPModel> selectList(int id);
	List<ProjectStopPModel> selectList(ProjectStopPModel m);

}
