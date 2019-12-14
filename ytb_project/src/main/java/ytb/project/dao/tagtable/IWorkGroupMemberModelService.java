package ytb.project.dao.tagtable;

import ytb.project.model.tagtable.WorkGroupMemberModel;

import java.util.List;

public interface IWorkGroupMemberModelService {

	int insert(WorkGroupMemberModel m);

	void update(WorkGroupMemberModel m);

	void delete(int id);

	WorkGroupMemberModel selectOne(int id);

	List<WorkGroupMemberModel> selectList(int groupId, int userId);

	List<WorkGroupMemberModel> selectListPM(int groupId);

	List<WorkGroupMemberModel> selectListAll(int groupId);

	List<WorkGroupMemberModel> selectList(int id);

	List<WorkGroupMemberModel> selectList(WorkGroupMemberModel m);

}
