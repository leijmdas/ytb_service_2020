package ytb.log.notify.model;

import ytb.common.context.model.Ytb_Model;

import java.sql.Timestamp;

public class TasklogTimerLogModel  extends Ytb_Model {
	public static String TABLE_NAME = "ytb_tasklog.tasklog_timer_log";

	private Integer logId;

	private Integer taskId;

	private Integer userId;

	private Byte mode;

	private Byte status;

	private String logMsg;

	private Timestamp logTime;

	public Integer  getLogId() {
		return logId;
	}

	public TasklogTimerLogModel setLogId( Integer logId ) {
		this.logId = logId;
		return this;
	}

	public Integer  getTaskId() {
		return taskId;
	}

	public TasklogTimerLogModel setTaskId( Integer taskId ) {
		this.taskId = taskId;
		return this;
	}

	public Integer  getUserId() {
		return userId;
	}

	public TasklogTimerLogModel setUserId( Integer userId ) {
		this.userId = userId;
		return this;
	}

	public Byte  getMode() {
		return mode;
	}

	public TasklogTimerLogModel setMode( Byte mode ) {
		this.mode = mode;
		return this;
	}

	public Byte  getStatus() {
		return status;
	}

	public TasklogTimerLogModel setStatus( Byte status ) {
		this.status = status;
		return this;
	}

	public String  getLogMsg() {
		return logMsg;
	}

	public TasklogTimerLogModel setLogMsg( String logMsg ) {
		this.logMsg = logMsg;
		return this;
	}

	public Timestamp  getLogTime() {
		return logTime;
	}

	public TasklogTimerLogModel setLogTime( Timestamp logTime ) {
		this.logTime = logTime;
		return this;
	}

}
