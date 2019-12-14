package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.project.dao.IProjectTradeModelService;
import ytb.project.model.ProjectTradeModel;
import ytb.common.context.model.YtbError;

public class ProjectTradeModelServiceImpl  extends DAOService implements IProjectTradeModelService {

	public int insert(ProjectTradeModel tradeModel) {
		if (tradeModel.getCompanyId() == null) {
			throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "getCompanyId is null!");
		}

		StringBuilder sql= new StringBuilder(256);
		sql.append("insert into ytb_project.project_trade");
		sql.append("(service_type,template_id,user_id,company_id,project_id,talk_id,phase,parent_trade_id,trade_id," +
				"update_time)");
		sql.append("values(#{serviceType},#{templateId},#{userId},#{companyId},#{projectId},#{talkId},#{phase}," +
				"#{parentTradeId},#{tradeId},  #{updateTime})");
		return YtbSql.insert(sql,tradeModel);

	}


	public void update(ProjectTradeModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.project_trade");
		sql.append("set user_id=#{userId},project_id=#{projectId},talk_id=#{talkId},phase=#{phase},trade_id=#{tradeId},update_time=#{updateTime}");
		sql.append(" where project_trade_id=#{projectTradeId})");
		YtbSql.update(sql,m);

	}

	public void delete(int projectTradeId){
		StringBuilder sql = new StringBuilder(256);
		sql.append("delete from ytb_project.project_trade");
		sql.append(" where project_trade_id=#{projectTradeId})");
		YtbSql.delete(sql, projectTradeId);

	}

	public ProjectTradeModel selectOne(Byte serviceType, int talkId, int userId) {
		StringBuilder sql = new StringBuilder(256);
		sql.append(" select *  from ytb_project.project_trade ");
		sql.append(" where talk_id = ").append(talkId);
		sql.append(" and user_id = ").append(userId);
		sql.append(" and service_type = ").append(serviceType);
		sql.append(" and parent_trade_id = 0 ");


		ProjectTradeModel tradeModel = YtbSql.selectOne(sql, ProjectTradeModel.class);
		if (tradeModel == null) {

			YtbLog.logError("ProjectTradeModel selectOne", sql);
		}
		return tradeModel;

	}

	public ProjectTradeModel selectOneByProjectTalk(Byte serviceType,int projectid, int talkId) {
		StringBuilder sql = new StringBuilder(256);
		sql.append(" select *  from ytb_project.project_trade ");
		sql.append(" where talk_id = ").append(talkId);
		sql.append(" and project_id = ").append(projectid);
		sql.append(" and service_type = ").append(serviceType);
		sql.append(" and parent_trade_id = 0 ");
		//sql.append(" limit 1 ");
		YtbLog.logDebug(sql);
		return YtbSql.selectOne(sql, ProjectTradeModel.class);
	}
	public List<ProjectTradeModel>  selectListByProject(Byte serviceType, int projectId ) {
		StringBuilder sql = new StringBuilder(256);
		sql.append(" select *  from ytb_project.project_trade ");
		sql.append(" where project_id = ").append(projectId);
		sql.append(" and service_type = ").append(serviceType);
		sql.append(" and parent_trade_id = 0 ");
		YtbLog.logDebug(sql);
		return YtbSql.selectList(sql, ProjectTradeModel.class);
	}

	public ProjectTradeModel selectOneByTemplate( int talkId, int templateId) {
		StringBuilder sql = new StringBuilder(256);
		sql.append(" select *  from ytb_project.project_trade ");
		sql.append(" where talk_id = ").append(talkId);
		sql.append(" and template_id = ").append(templateId);
		sql.append(" and parent_trade_id = 0 ");

		return YtbSql.selectOne(sql, ProjectTradeModel.class);
	}

	public ProjectTradeModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_trade");
		sql.append(" where project_trade_id=#{projectTradeId}");
		return YtbSql.selectOne(sql, id, ProjectTradeModel.class);

	}

	public List<ProjectTradeModel> selectList(int id) {
		StringBuilder sql = new StringBuilder(256);
		sql.append("select *  from ytb_project.project_trade");
		sql.append(" where project_trade_id=#{projectTradeId}");
		return YtbSql.selectList(sql,id,ProjectTradeModel.class);

	}

	public List<ProjectTradeModel>  selectList(ProjectTradeModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_trade");
		sql.append(" where project_trade_id=#{projectTradeId}");
		return YtbSql.selectList(sql,m,ProjectTradeModel.class);

	}

}
