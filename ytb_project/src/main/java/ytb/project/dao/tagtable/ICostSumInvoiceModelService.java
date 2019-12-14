package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.CostSumInvoiceModel;

import java.util.List;
public interface ICostSumInvoiceModelService {

	int insert(CostSumInvoiceModel m);
	void update(CostSumInvoiceModel m);
	void delete(int id);
	//根据模板id
	CostSumInvoiceModel selectOne(int talkId,int documentId);

	List<CostSumInvoiceModel> selectList(int id);
	List<CostSumInvoiceModel> selectList(CostSumInvoiceModel m);

}
