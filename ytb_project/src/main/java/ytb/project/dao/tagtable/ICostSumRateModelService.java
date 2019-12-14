package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.CostSumRateModel;

import java.util.List;
public interface ICostSumRateModelService {

	int insert(CostSumRateModel m);

	void update(CostSumRateModel m);

	void delete(int id);

	CostSumRateModel selectOne(CostSumRateModel costSumRateModel);

	List<CostSumRateModel> selectList(int id);

	List<CostSumRateModel> selectList(CostSumRateModel m);

}
