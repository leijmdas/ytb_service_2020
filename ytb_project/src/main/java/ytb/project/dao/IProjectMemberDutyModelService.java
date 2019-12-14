package ytb.project.dao;

import ytb.project.model.tagtable.ProjectMemberDutyModel;

import java.util.List;
public interface IProjectMemberDutyModelService {

	int insert(ProjectMemberDutyModel m);
	void update(ProjectMemberDutyModel m);
	void delete(int id);
	ProjectMemberDutyModel selectOne(int id);

	List<ProjectMemberDutyModel> selectList(int id);
	List<ProjectMemberDutyModel> selectList(ProjectMemberDutyModel m);

}
