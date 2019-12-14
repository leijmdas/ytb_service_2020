package ytb.log.notify.service.impl;

import org.quartz.*;
import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.model.YtbError;

import java.util.Date;

public class TaskLogTimerJob implements Job {
    String jobName;

    String jobGroup;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String n=context.getMergedJobDataMap().getString("name");

        System.out.println(context.getJobDetail().getJobDataMap().get("MsgRequest"));
        System.out.println(context.getJobDetail().getJobDataMap().get("TaskLog_TimerModel"));

        MsgRequest req=(MsgRequest)context.getJobDetail().getJobDataMap().get("MsgRequest");
        TaskLog_TimerModel task = (TaskLog_TimerModel) context.getJobDetail().getJobDataMap().get("TaskLog_TimerModel");
        try {
            if (task.getExeType() == task.EXETYPE_JAVA) {
                new TimerTaskServiceExeJAVA().execute(req, task);
            } else if (task.getExeType() == task.EXETYPE_REST) {
                new TimerTaskServiceExeRest().execute(req, task);

            } else if (task.getExeType() == task.EXETYPE_SQL) {
                new TimerTaskServiceExeSql().execute(req, task);

            } else {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JobExecutionException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new JobExecutionException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new JobExecutionException(e);
        }
        System.out.println(new Date());

    }
}
