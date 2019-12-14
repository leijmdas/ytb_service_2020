package ytb.manager.userCredit.dao;

import ytb.manager.userCredit.model.DictCreditModel;

import java.util.List;
public interface IDictCreditModelService {

	int insert(DictCreditModel m);
	void update(DictCreditModel m);
	void delete(int id);
	DictCreditModel selectOne(int id);
	List<DictCreditModel> selectList( );

	List<DictCreditModel> selectList(int id);
	List<DictCreditModel> selectList(DictCreditModel m);

}
