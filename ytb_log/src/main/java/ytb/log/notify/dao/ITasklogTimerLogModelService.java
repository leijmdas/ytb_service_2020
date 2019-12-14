package ytb.log.notify.dao;

import ytb.log.notify.model.TasklogTimerLogModel;

import java.util.List;
public interface ITasklogTimerLogModelService {

	int insert(TasklogTimerLogModel m);
	void update(TasklogTimerLogModel m);
	void delete(int id);
	TasklogTimerLogModel selectOne(int id);

	List<TasklogTimerLogModel> selectList(int id);
	List<TasklogTimerLogModel> selectList(TasklogTimerLogModel m);

}
