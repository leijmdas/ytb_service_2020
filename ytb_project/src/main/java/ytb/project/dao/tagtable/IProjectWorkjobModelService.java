package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.ProjectWorkjobModel;

import java.util.List;
public interface IProjectWorkjobModelService {

	int insert(ProjectWorkjobModel m);
	void update(ProjectWorkjobModel m);
	void delete(int id);
	ProjectWorkjobModel selectOne(int id);

	List<ProjectWorkjobModel> selectList(int documentId);

	List<ProjectWorkjobModel> selectList(ProjectWorkjobModel m);

}
