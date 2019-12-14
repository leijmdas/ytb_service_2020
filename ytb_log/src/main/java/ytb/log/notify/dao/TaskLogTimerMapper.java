package ytb.log.notify.dao;

import ytb.log.notify.model.TaskLog_TimerModel;

import java.util.List;

public interface TaskLogTimerMapper {
    List<TaskLog_TimerModel> getTaskLogTimerList();

    void addTaskLogTimer(TaskLog_TimerModel tlm);

    void modifyTaskLogTimer(TaskLog_TimerModel tlm);
    void modifyTaskLogTimerStatus(TaskLog_TimerModel tlm);

    void delTaskLogTimer(int taskId);

    TaskLog_TimerModel getTaskLogTimer(int taskId);

}
