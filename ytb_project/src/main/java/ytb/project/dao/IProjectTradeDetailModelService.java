package ytb.project.dao;

import ytb.project.model.ProjectTradeDetailModel;
import ytb.project.model.ProjectTradeModel;

import java.util.List;
public interface IProjectTradeDetailModelService {

	int insert(ProjectTradeModel m);
	void update(ProjectTradeModel m);
	void delete(int id);
	ProjectTradeDetailModel selectOne(int id);

	List<ProjectTradeModel> selectList(int id);
	List<ProjectTradeModel> selectList(ProjectTradeModel m);

}
