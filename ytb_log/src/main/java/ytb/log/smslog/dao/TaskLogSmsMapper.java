package ytb.log.smslog.dao;




import ytb.log.smslog.model.Tasklog_SmsModel;

import java.util.List;

/**
 * Package: ytb.common.basic.activititemplate.dao
 * Author: lzz
 * Date: Created in 2018/8/23 16:30
 */
public interface TaskLogSmsMapper {

    //获取短信通知列表
    List getTaskLogSmsList(String mobile);

    //添加短信通知
    void addTaskLogSms(Tasklog_SmsModel tsm);

    //修改短信通知
    void modifyTaskLogSms();

    //删除短信通知
    void delTaskLogSms();

    Tasklog_SmsModel getTaskLogSms();



}


