package ytb.log.notify.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.log.notify.dao.ITasklogTimerLogModelService;
import ytb.log.notify.model.TasklogTimerLogModel;

public class TasklogTimerLogModelServiceImpl implements ITasklogTimerLogModelService {

	public int insert(TasklogTimerLogModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_tasklog.tasklog_timer_log");
		sql.append("(task_id,user_id,mode,status,log_msg,log_time)");
		sql.append("values");		
		sql.append("(#{taskId},#{userId},#{mode},#{status},#{logMsg},#{logTime})");
		return YtbSql.insert(sql,m);

	}
	public void update(TasklogTimerLogModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_tasklog.tasklog_timer_log");
		sql.append("set task_id=#{taskId},user_id=#{userId},mode=#{mode},status=#{status},log_msg=#{logMsg},log_time=#{logTime}");
		sql.append(" where log_id=#{logId}");
		YtbSql.update(sql,m);

	}

	public void delete(int logId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_tasklog.tasklog_timer_log");
		sql.append(" where log_id=#{logId}");
		YtbSql.delete(sql,logId);

	}

	public TasklogTimerLogModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_tasklog.tasklog_timer_log");
		sql.append(" where log_id=#{logId}");
		return YtbSql.selectOne(sql,id,TasklogTimerLogModel.class);

	}


	public List<TasklogTimerLogModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_tasklog.tasklog_timer_log");
		sql.append(" where log_id=#{logId}");
		return YtbSql.selectList(sql,id,TasklogTimerLogModel.class);

	}

	public List<TasklogTimerLogModel>  selectList(TasklogTimerLogModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_tasklog.tasklog_timer_log");
		sql.append(" where log_id=#{logId}");
		return YtbSql.selectList(sql,m,TasklogTimerLogModel.class);

	}

}
