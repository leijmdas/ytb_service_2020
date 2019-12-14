package ytb.manager.tagtable.dao;

import org.apache.ibatis.session.SqlSession;
import ytb.manager.tagtable.model.business.WorkGroupMemberModel;

import java.util.List;

public interface IWorkGroupMemberModelService {

	int insert(SqlSession session,WorkGroupMemberModel memberModel);
	void update(WorkGroupMemberModel m);
	void delete(int id);
	WorkGroupMemberModel selectOne(int id);

	List<WorkGroupMemberModel> selectList(int id);
	List<WorkGroupMemberModel> selectList(WorkGroupMemberModel m);

}
