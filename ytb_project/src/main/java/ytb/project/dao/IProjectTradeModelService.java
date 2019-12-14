package ytb.project.dao;

import ytb.project.model.ProjectTradeModel;

import java.util.List;

public interface IProjectTradeModelService {

	int insert(ProjectTradeModel m);

	void update(ProjectTradeModel m);

	void delete(int id);

	ProjectTradeModel selectOne(int id);

	ProjectTradeModel selectOneByProjectTalk (Byte serviceType, int projectid, int talkId);

	ProjectTradeModel selectOne(Byte serviceType, int talkId, int userId);

	ProjectTradeModel selectOneByTemplate(int talkId, int templateId);

	List<ProjectTradeModel> selectList(int id);

	List<ProjectTradeModel> selectListByProject(Byte serviceType, int projectId);

	List<ProjectTradeModel> selectList(ProjectTradeModel m);

}
