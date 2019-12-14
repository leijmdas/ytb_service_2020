package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.ProjectChngPModel;

import java.util.List;
public interface IProjectChngPModelService {

	int insert(ProjectChngPModel m);

	void update(ProjectChngPModel m);

	void delete(int id);

	void delete(int projectId, int documentId);

	ProjectChngPModel selectOneByDocumentId(ProjectChngPModel projectChngPModel);

	List<ProjectChngPModel> selectList(int id);

	List<ProjectChngPModel> selectList(ProjectChngPModel projectChngPModel);

}
