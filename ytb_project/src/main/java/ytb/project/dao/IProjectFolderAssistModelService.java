package ytb.project.dao;

import ytb.project.model.ProjectFolderAssistModel;

import java.util.List;
public interface IProjectFolderAssistModelService {

	int insert(ProjectFolderAssistModel m);
	void update(ProjectFolderAssistModel m);
	void delete(int id);
	ProjectFolderAssistModel selectOne(int id);

	List<ProjectFolderAssistModel> selectList(int id);
	List<ProjectFolderAssistModel> selectList(ProjectFolderAssistModel m);

}
