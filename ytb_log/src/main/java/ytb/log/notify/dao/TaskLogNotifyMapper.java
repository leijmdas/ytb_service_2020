package ytb.log.notify.dao;




import org.apache.ibatis.annotations.Param;
import ytb.log.notify.model.TaskLog_notifyModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.common.basic.activititemplate.dao
 * Author: lzz
 * Date: Created in 2018/8/23 16:30
 */
public interface TaskLogNotifyMapper {

    //获取通知列表
    List getTaskNotifyList();

    //添加通知
    void addTaskNotify(TaskLog_notifyModel tnm);

    //修改通知
    void modifyTaskNotify(TaskLog_notifyModel tnm);

    //批量删除通知
    void deleteTaskNotifyByList(List notify_id);

    //批量删除通知
    void deleteTaskNotifyById(@Param("notifyId") Integer id);
    //获取通知
    TaskLog_notifyModel getTaskNotify(int notify_id);

    //根据类型查看任务通知类型（1任务通知 2交易通知 3系统通知）
    List<Map<String,Object>> getTaskNotifyListByType(Map<String,Object> map);

    //获取未读的任务的总数
    Integer getUnReadNumber(Map<String,Object> map);

     //获取用户未读的任务通知
    Map<String,Object> getUnReadTaskNotify(@Param("userId") Integer toUserId);

    //获取不同通知类别的总数
    Integer getTaskNumByType(@Param("userId") Integer toUserId,@Param("type") Integer type);

    //标记已读
    void setSignRead(Integer notifyId);

}


