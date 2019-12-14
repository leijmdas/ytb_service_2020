package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.CostModel;

import java.util.List;
public interface ICostModelService {

	int insert(CostModel m);
	void update(CostModel m);
	void delete(int id);
	CostModel selectOne(int id);

	List<CostModel> selectList(int id);

	List<CostModel> selectList(CostModel m);


	//通过洽谈id查询费用
	List<CostModel> getProjectCostByTalk(int talkId, int documentId);

	List<CostModel> getProjectFeeByTalk(int projectId, int userId, int documentId);


}
