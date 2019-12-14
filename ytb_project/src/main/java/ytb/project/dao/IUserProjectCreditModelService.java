package ytb.project.dao;

import ytb.project.model.UserProjectCreditModel;

import java.util.List;
public interface IUserProjectCreditModelService {

	int insert(UserProjectCreditModel m);
	void update(UserProjectCreditModel m);
	void delete(int userId,int projectId);
	UserProjectCreditModel selectOne(int id);

	List<UserProjectCreditModel> selectList(int id);
	List<UserProjectCreditModel> selectList(UserProjectCreditModel m);

}
