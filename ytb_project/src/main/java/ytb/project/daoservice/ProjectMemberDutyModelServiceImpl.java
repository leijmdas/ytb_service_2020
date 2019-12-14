package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.IProjectMemberDutyModelService;
import ytb.project.model.tagtable.ProjectMemberDutyModel;

public class ProjectMemberDutyModelServiceImpl implements IProjectMemberDutyModelService {

	public int insert(ProjectMemberDutyModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.project_member_duty");
		sql.append("(talk_id,member_id,work_job_id,order_no)");
		sql.append("values");		
		sql.append("(#{talkId},#{memberId},#{workJobId},#{orderNo})");
		return YtbSql.insert(sql,m);

	}

	public ProjectMemberDutyModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_member_duty");
		sql.append(" where member_duty_id=#{memberDutyId}");
		return YtbSql.selectOne(sql,id,ProjectMemberDutyModel.class);

	}


	public List<ProjectMemberDutyModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_member_duty");
		sql.append(" where member_duty_id=#{memberDutyId}");
		return YtbSql.selectList(sql,id,ProjectMemberDutyModel.class);

	}


	public void update(ProjectMemberDutyModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.project_member_duty");
		sql.append("set talk_id=#{talkId},member_id=#{memberId},work_job_id=#{workJobId},order_no=#{orderNo}");
		sql.append(" where member_duty_id=#{memberDutyId}");
		YtbSql.update(sql,m);

	}

	public void delete(int memberDutyId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.project_member_duty");
		sql.append(" where member_duty_id=#{memberDutyId}");
		YtbSql.delete(sql,memberDutyId);

	}

	public List<ProjectMemberDutyModel>  selectList(ProjectMemberDutyModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_member_duty");
		sql.append(" where member_duty_id=#{memberDutyId}");
		return YtbSql.selectList(sql,m,ProjectMemberDutyModel.class);

	}

}
