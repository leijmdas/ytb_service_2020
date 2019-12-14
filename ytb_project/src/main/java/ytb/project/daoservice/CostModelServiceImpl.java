package ytb.project.daoservice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.dao.tagtable.CostMapper;
import ytb.project.dao.tagtable.ICostModelService;
import ytb.project.model.tagtable.CostModel;

public class CostModelServiceImpl extends DAOService implements ICostModelService {

	public int insert(CostModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.cost");
		sql.append("(project_id,talk_id,document_id,user_id,user_name,area_name,day_pay,cost_phase1,cost_phase2," +
				"cost_phase3,cost_phase4,cost_phase5,cost_phase6,income_sum,cost_sum,update_time,area_id)");
		sql.append("values");
		sql.append("(#{projectId},#{talkId},#{documentId},#{userId},#{userName},#{areaName},#{dayPay},#{costPhase1}," +
				"#{costPhase2},#{costPhase3},#{costPhase4},#{costPhase5},#{costPhase6},#{incomeSum},#{costSum},#{updateTime},#{areaId})");
		return YtbSql.insert(sql,m);


	}
	public void update(CostModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.cost");
		sql.append("set project_id=#{projectId},document_id=#{documentId},user_id=#{userId},user_name=#{userName},area_name=#{areaName},day_pay=#{dayPay},cost_phase1=#{costPhase1},cost_phase2=#{costPhase2},cost_phase3=#{costPhase3},cost_phase4=#{costPhase4},cost_phase5=#{costPhase5},cost_phase6=#{costPhase6},income_sum=#{incomeSum},service_sum=#{serviceSum},tax_sum=#{taxSum},cost_sum=#{costSum},update_time=#{updateTime},area_id=#{areaId}");
		sql.append(" where cost_id=#{costId})");
		YtbSql.update(sql,m);

	}

	public void delete(int costId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.cost");
		sql.append(" where cost_id=#{costId})");
		YtbSql.delete(sql,costId);

	}

	public CostModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.cost");
		sql.append(" where cost_id=#{costId}");
		return YtbSql.selectOne(sql,id,CostModel.class);

	}
	public List<CostModel> selectList(int projectId,int documentId) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select * from ytb_project.cost");
		sql.append(" where project_id= ").append(projectId);
		sql.append(" and document_id=  ").append(documentId);
		return YtbSql.selectList(sql,CostModel.class);

	}

	public List<CostModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.cost");
		sql.append(" where cost_id=#{costId}");
		return YtbSql.selectList(sql,id,CostModel.class);

	}

	public List<CostModel>  selectList(CostModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.cost");
		sql.append(" where cost_id=#{costId}");
		return YtbSql.selectList(sql,m,CostModel.class);

	}

	//通过洽谈id查询费用
	public List<CostModel> getProjectCostByTalk(int talkId, int documentId) {

		try (SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)) {
			CostMapper costMapper = session.getMapper(CostMapper.class);
			return costMapper.getProjectCostByTalk(talkId, documentId);
		}
	}

	//	select * from cost where project_id = #{projectId} and user_id = #{userId} and document_id = #{documentId}
	public List<CostModel> getProjectFeeByTalk(int projectId, int userId, int documentId) {

		try(SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true)){
			CostMapper costMapper = session.getMapper(CostMapper.class);
			return costMapper.getProjectFeeByTalkId(projectId,userId,documentId);
		}
	}

}
