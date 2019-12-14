package ytb.project.dao;

import ytb.project.model.ProjectTemplateAssistModel;

import java.util.List;
public interface IProjectTemplateAssistModelService {

	int insert(ProjectTemplateAssistModel m);

	void update(ProjectTemplateAssistModel m);

	void delete(int id);

	ProjectTemplateAssistModel selectOne(Integer id);

	ProjectTemplateAssistModel selectOneByIni(int userId, Integer templateId);

	List<ProjectTemplateAssistModel> selectList(int id);

	List<ProjectTemplateAssistModel> selectList(ProjectTemplateAssistModel m);

}
