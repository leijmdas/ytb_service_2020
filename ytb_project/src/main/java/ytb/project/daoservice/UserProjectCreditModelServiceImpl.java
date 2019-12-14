package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.IUserProjectCreditModelService;
import ytb.project.model.UserProjectCreditModel;

public class UserProjectCreditModelServiceImpl implements IUserProjectCreditModelService {

	public int insert(UserProjectCreditModel creditModel) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_user.user_project_credit");
		sql.append("(user_id,name,project_id,project_name,delay_q,finish_q,stop_q)");
		sql.append("values");		
		sql.append("(#{userId},#{name},#{projectId},#{projectName},#{delayQ},#{finishQ},#{stopQ})");
		return YtbSql.insert(sql,creditModel);

	}


	public void update(UserProjectCreditModel creditModel){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_user.user_project_credit");
		sql.append("set user_id=#{userId},project_id=#{projectId},delay_q=#{delayQ},finish_q=#{finishQ},stop_q=#{stopQ}");
		sql.append(" where null");
		YtbSql.update(sql,creditModel);

	}
	public void delete(int userId,int projectId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_user.user_project_credit");
		sql.append(" where user_id=").append(userId);
		sql.append(" and project_id=").append(projectId);

		YtbSql.delete(sql);

	}

	public UserProjectCreditModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_user.user_project_credit");
		sql.append(" where null=null");
		return YtbSql.selectOne(sql,id,UserProjectCreditModel.class);

	}


	public List<UserProjectCreditModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_user.user_project_credit");
		sql.append(" where null=null");
		return YtbSql.selectList(sql,id,UserProjectCreditModel.class);

	}

	public List<UserProjectCreditModel>  selectList(UserProjectCreditModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_user.user_project_credit");
		sql.append(" where null=null");
		return YtbSql.selectList(sql,m,UserProjectCreditModel.class);

	}

}
