package ytb.log.notify.service.impl;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.common.RestMessage.MsgRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class YtbScheduler {
    static String jobGroup = "jobGroup";
    static String triggerGroup = "triggerGroup";

    static ConcurrentHashMap<Integer, JobDetail> mapJobDetail = new ConcurrentHashMap<>();

    static String YtbScheduler_name = "YtbScheduler";
    static Scheduler sch = null;

    //创建调度器
    public static Scheduler getScheduler() throws SchedulerException {
        if (sch == null) {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            sch = schedulerFactory.getScheduler();
        }
        return sch;
    }

    public static Scheduler getScheduler(String schName) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler(schName);
    }

    static Scheduler schedulerJob() throws SchedulerException{
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TaskLogTimerJob.class).withIdentity("job1", "group1").build();

        //创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                .build();
        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever())
                .build();

        CronScheduleBuilder cronScheduleBuilder1 = CronScheduleBuilder.cronSchedule("0 00-03 21 * * ?");
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 02 ? * *");

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger_1", "tGroup1")
                .withSchedule(cronScheduleBuilder).build();
        //scheduler.scheduleJob(jobDetail, cronTrigger);
        Scheduler scheduler = getScheduler();
        //将任务及其触发器放入调度器
        //scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.scheduleJob(jobDetail, trigger);
        //调度器开始调度任务
        scheduler.start();
        System.out.println(scheduler.isStarted());
        return scheduler;
    }


    static Scheduler startSchedulerJob(MsgRequest req, TaskLog_TimerModel task) throws SchedulerException {

        String jobGroup = "jobGroup";
        String triggerGroup = "triggerGroup";
        JobKey jobKey = JobKey.jobKey("job" + task.getTaskId(), jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey("cronTrigger" + task.getTaskId(), triggerGroup);
        JobDetail jobDetail = JobBuilder.newJob(TaskLogTimerJob.class).withIdentity(jobKey).build();

//          Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
//               .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInHours(24).repeatForever()).build();
        //CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 00-03 21 * * ?"); // 每天2.30
          CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 02 ? * *");
          CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                .withSchedule(cronScheduleBuilder).build();
          jobDetail.getJobDataMap().put("msgRequest", req);
          jobDetail.getJobDataMap().put("taskLog_TimerModel", task);
          jobDetail.getJobDataMap().put("jobDetail", jobDetail);
          jobDetail.getJobDataMap().put("triggerKey", triggerKey);

          Scheduler scheduler = getScheduler();
          //scheduler.scheduleJob(jobDetail, cronTrigger);
          //将任务及其触发器放入调度器 scheduler.scheduleJob(jobDetail, cronTrigger);
          scheduler.scheduleJob(cronTrigger);
          scheduler.addJob(jobDetail, true);
          if (!scheduler.isStarted()) {
              scheduler.start();
          }
          scheduler.getJobDetail(jobKey);
          mapJobDetail.put(task.getTaskId(), jobDetail);
          System.out.println(scheduler.isStarted());
          return scheduler;
      }

    static void addJob(MsgRequest req, TaskLog_TimerModel task) throws SchedulerException {

        JobKey jobKey = JobKey.jobKey("job" + task.getTaskId(), jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey("cronTrigger" + task.getTaskId(), triggerGroup);
        JobDetail jobDetail = JobBuilder.newJob(TaskLogTimerJob.class).withIdentity(jobKey).build();

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 02 ? * *");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                .withSchedule(cronScheduleBuilder).build();
        jobDetail.getJobDataMap().put("msgRequest", req);
        jobDetail.getJobDataMap().put("taskLog_TimerModel", task);
        jobDetail.getJobDataMap().put("jobKey", jobKey);
        jobDetail.getJobDataMap().put("triggerKey", triggerKey);

//        Scheduler scheduler = getScheduler();    scheduler.scheduleJob(cronTrigger);
//        scheduler.addJob(jobDetail, true);
        getScheduler().scheduleJob(jobDetail, cronTrigger);
        getScheduler().start();
        //mapSch.put(task.getTaskId(), getScheduler());
        mapJobDetail.put(task.getTaskId(), jobDetail);

    }

    public static void removeJob(int taskId) throws SchedulerException {
        JobDetail jobDetail = mapJobDetail.get(taskId);
        if(jobDetail==null){
            return;
        }
        JobKey jobKey=(JobKey)jobDetail.getJobDataMap().get("jobKey");
        TriggerKey triggerKey=(TriggerKey)jobDetail.getJobDataMap().get("triggerKey");
        Scheduler sched = getScheduler();
        if(sched.isStarted()) {
            sched.pauseTrigger(triggerKey);//停止触发器
            sched.unscheduleJob(triggerKey);//移除触发器
            sched.deleteJob(jobKey);//删除任务
        }
        mapJobDetail.remove(taskId) ;

    }

    public static JobDetail startJob(MsgRequest req, TaskLog_TimerModel task) throws SchedulerException {
        start();
        JobDetail jobDetail = mapJobDetail.get(task.getTaskId());
        if (jobDetail == null) {
            addJob(req, task);
        }
        return jobDetail;
    }

    public static void stopJob(int taskId) throws SchedulerException {
        removeJob( taskId);
    }


    public static void start() throws SchedulerException {
        if (getScheduler().isStarted()) {
            getScheduler().resumeAll();
        }
        System.out.println("start cheduler!");
    }
    public static void stop() throws SchedulerException {
        getScheduler().pauseAll();
        System.out.println("shutdown cheduler!");
    }

    public static Map<String, Boolean> queryTaskLogTimerStartStatus(Map<String, Boolean> map) throws SchedulerException {
        for (Integer key : mapJobDetail.keySet()) {
            map.put(key.toString(), getScheduler().isStarted());
        }
        return map;
    }

    public static void main(String[] args) throws SchedulerException {
        YtbScheduler mainScheduler = new YtbScheduler();
        mainScheduler.schedulerJob();
    }

}
