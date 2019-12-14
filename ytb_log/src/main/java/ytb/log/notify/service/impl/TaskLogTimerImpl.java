package ytb.log.notify.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import ytb.common.utils.YtbUtils;
import ytb.log.notify.dao.TaskLogTimerMapper;
import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.log.utils.MyBatisUtils;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.model.YtbError;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskLogTimerImpl { //implements TaskLogTimerService {

    public List<TaskLog_TimerModel> getTaskLogTimerList() {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogTimerMapper tlt = session.getMapper(TaskLogTimerMapper.class);
            return tlt.getTaskLogTimerList();
        } finally {
            session.close();
        }
    }

    public int addTaskLogTimer(TaskLog_TimerModel tlm) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogTimerMapper tlt = session.getMapper(TaskLogTimerMapper.class);
            tlt.addTaskLogTimer(tlm);
            return tlm.getTaskId() ;
        } finally {
            session.close();
        }
    }

    public void modifyTaskLogTimer(TaskLog_TimerModel tlm) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogTimerMapper tlt = session.getMapper(TaskLogTimerMapper.class);
            tlt.modifyTaskLogTimer(tlm);
        } finally {
            session.close();
        }
    }
    public void modifyTaskLogTimerStatus(TaskLog_TimerModel tlm) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogTimerMapper tlt = session.getMapper(TaskLogTimerMapper.class);
            tlt.modifyTaskLogTimerStatus(tlm);
        } finally {
            session.close();
        }
    }
    public void delTaskLogTimer(int taskId) {
        SqlSession session = MyBatisUtils.getSession();
        try {
            TaskLogTimerMapper tlt = session.getMapper(TaskLogTimerMapper.class);
            tlt.delTaskLogTimer(taskId);
        } finally {
            session.close();
        }
    }

    public TaskLog_TimerModel getTaskLogTimer(int taskId) {
          SqlSession session = MyBatisUtils.getSession();
          try {
              TaskLogTimerMapper tlt = session.getMapper(TaskLogTimerMapper.class);
              return tlt.getTaskLogTimer(taskId);
          } finally {
              session.close();
          }
    }


    public String exeNode(MsgRequest req, int taskId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("exeNode");
        TaskLog_TimerModel task = getTaskLogTimer(taskId);
        if (task.getExeType() == task.EXETYPE_JAVA) {
            new TimerTaskServiceExeJAVA().execute(req, task);
        } else if (task.getExeType() == task.EXETYPE_REST) {
            new TimerTaskServiceExeRest().execute(req, task);
        } else if (task.getExeType() == task.EXETYPE_SQL) {
            new TimerTaskServiceExeSql().execute(req, task);
        } else {
            System.out.println(task);
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
        }
        return "exe " + task.getExeObject();

    }

    //TaskLogTimerImpl
    public String exeOnceTaskLogTimer(MsgRequest req, int taskId)  {
        TaskLog_TimerModel task = getTaskLogTimer(taskId);
        String url = "/rest/taskLog_task";
        req.cmd = "exeNode";
        System.out.println(req.toJSONStringPretty());
        System.out.println(url);

        ResponseEntity<String> res = YtbUtils.postForEntity(task.getSubsysId(), url, req.toJSONStringPretty());
        if (res.getStatusCode().value() != 200) {
            throw new YtbError(YtbError.CODE_FAIL);
        }
          return res.getBody();
    }


    public void startTaskLogTimer() throws SchedulerException {
        YtbScheduler.start();
    }

    public void stopTaskLogTimer() throws SchedulerException {
        YtbScheduler.stop();
    }

    public Map<String, Boolean> queryTaskLogTimerStartStatus() throws SchedulerException {
        List<TaskLog_TimerModel> lst=getTaskLogTimerList();
        Map<String,Boolean> map=new LinkedHashMap<>();
        for(TaskLog_TimerModel task: lst){
            map.put(task.getTaskId()+"",false);
        }

        return YtbScheduler.queryTaskLogTimerStartStatus(map);
    }


    public void startJob(MsgRequest req, TaskLog_TimerModel task) throws SchedulerException {
        YtbScheduler.startJob(req,task);
    }

    public void stopJob(int taskId) throws SchedulerException {
        YtbScheduler.stopJob(taskId);
    }
}
