package ytb.manager.template.dao;



import ytb.manager.template.model.Dict_TaskModel;

import java.util.List;

public interface TaskMapper {

    //
    List<Dict_TaskModel> getTaskDetailsInfoList(int workJobId);

    Dict_TaskModel getTask(int TaskId);

    void addTask(Dict_TaskModel task);

    void delTask(int taskId);

    void modifyTask(Dict_TaskModel task);

}
