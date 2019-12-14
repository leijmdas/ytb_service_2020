package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.BuyPriceListAllModel;

import java.util.List;

public interface IBuyPriceListAllModelService {

	int insert(BuyPriceListAllModel m);

	void update(BuyPriceListAllModel m);

	BuyPriceListAllModel selectOne(int id);

	 void delete(int projectId, int workplanId);

	void delete(int d);

	List<BuyPriceListAllModel> selectList(int id);

	List<BuyPriceListAllModel> selectList(BuyPriceListAllModel listAllModel);

}
