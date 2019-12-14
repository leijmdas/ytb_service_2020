package ytb.log.notify.model;

public class TaskLog_TimerModel {
    transient public static final int EXETYPE_JAVA = 1;//   1: "1--JAVA类",
    transient public static final int EXETYPE_REST = 2;  //      2: "2--REST URL",
    transient public static final int EXETYPE_SQL = 3;  //        3 :"3--sql"

    int taskId;
    int userId;
    String taskName;
    int exeType;

    String exeObject;
    String params;
    int exeFirstday;
    int exePeriod;
    String exeTime;//char(8)
    int retryTimes;
    int status ;

    int subsysId;
    public int getSubsysId() {
        return subsysId;
    }

    public void setSubsysId(int subsysId) {
        this.subsysId = subsysId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getExeType() {
        return exeType;
    }

    public void setExeType(int exeType) {
        this.exeType = exeType;
    }

    public String getExeObject() {
        return exeObject;
    }

    public void setExeObject(String exeObject) {
        this.exeObject = exeObject;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public int getExeFirstday() {
        return exeFirstday;
    }

    public void setExeFirstday(int exeFirstday) {
        this.exeFirstday = exeFirstday;
    }

    public int getExePeriod() {
        return exePeriod;
    }

    public void setExePeriod(int exePeriod) {
        this.exePeriod = exePeriod;
    }

    public String getExeTime() {
        return exeTime;
    }

    public void setExeTime(String exeTime) {
        this.exeTime = exeTime;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



}
