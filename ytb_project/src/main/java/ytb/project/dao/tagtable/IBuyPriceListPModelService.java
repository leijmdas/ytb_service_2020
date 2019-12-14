package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.BuyPriceListPModel;

import java.util.List;

public interface IBuyPriceListPModelService {

	int insert(BuyPriceListPModel m);

	void update(BuyPriceListPModel m);

	void delete(int id);

	BuyPriceListPModel selectOne(int id);

	BuyPriceListPModel selectOne(int projectId, int workplanId);

	List<BuyPriceListPModel> selectList(int id);

	List<BuyPriceListPModel> selectList(BuyPriceListPModel m);

}
