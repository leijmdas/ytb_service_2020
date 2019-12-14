package ytb.project.dao;

import ytb.project.model.tagtable.ProjectMemberTaskModel;

import java.util.List;
public interface IProjectMemberTaskModelService {

	int insert(ProjectMemberTaskModel m);
	void update(ProjectMemberTaskModel m);
	void delete(int id);
	ProjectMemberTaskModel selectOne(int id);

	List<ProjectMemberTaskModel> selectList(int id);
	List<ProjectMemberTaskModel> selectList(ProjectMemberTaskModel m);

}
