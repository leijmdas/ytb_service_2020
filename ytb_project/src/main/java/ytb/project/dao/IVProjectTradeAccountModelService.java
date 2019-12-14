package ytb.project.dao;

import ytb.project.model.VProjectTradeAccountModel;

import java.util.List;

public interface IVProjectTradeAccountModelService {

	int insert(VProjectTradeAccountModel m);

	void update(VProjectTradeAccountModel m);

	void delete(int talkId, int serviceType);

	VProjectTradeAccountModel selectOne(int id);

	//List<VProjectTradeAccountModel> selectListByTalk(int talkId );

	List<VProjectTradeAccountModel> selectListPa(int projectId, int userIdPa);

	List<VProjectTradeAccountModel> selectListPb(int projectId, int userIdPa );

	List<VProjectTradeAccountModel> selectList(VProjectTradeAccountModel m);

}
