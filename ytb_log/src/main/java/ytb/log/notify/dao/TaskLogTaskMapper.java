package ytb.log.notify.dao;



import org.apache.ibatis.annotations.Param;
import ytb.log.notify.model.TaskLog_TaskModel;

import java.util.List;


/**
 * Package: ytb.common.basic.activititemplate.dao
 * Author: lzz
 * Date: Created in 2018/8/23 16:30
 */
public interface TaskLogTaskMapper {

     //获取所有工作流任务
    List<TaskLog_TaskModel> getTaskLogTaskList(int templateType);

    //添加工作流任务
    void addTaskLogTask(TaskLog_TaskModel ttm);

    //修改工作流任务
    void modifyTaskLogTask(TaskLog_TaskModel ttm);

    //删除工作流任务
    void delTaskLogTask(int taskId);

    //获取工作流任务 BY id
    TaskLog_TaskModel getTaskLogTask(int taskId);

    //通过实例和任务策略类型获取工作流任务
    TaskLog_TaskModel getTaskLogTaskByObjType(@Param("activiti_inst_id") String activiti_inst_id, @Param("object_type")int object_type);

}


