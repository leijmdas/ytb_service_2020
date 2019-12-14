package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.PpBudgetModel;

import java.util.List;
public interface IPpBudgetModelService {

	int insert(PpBudgetModel m);
	void update(PpBudgetModel m);
	void delete(int id);
	PpBudgetModel selectOne(int id);

	List<PpBudgetModel> selectList(int id);
	List<PpBudgetModel> selectList(PpBudgetModel m);

}
