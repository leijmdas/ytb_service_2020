package ytb.manager.tagtable.daoservice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.manager.tagtable.dao.IWorkGroupMemberModelService;
import ytb.manager.tagtable.model.business.WorkGroupMemberModel;

public class WorkGroupMemberModelServiceImpl implements IWorkGroupMemberModelService {

	public int insert(SqlSession session,WorkGroupMemberModel memberModel) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.work_group_member");
		sql.append("(group_id,project_id,talk_id,document_id,user_id,company_id,is_admin,is_active,order_no,create_time)");
		sql.append("values");		
		sql.append("(#{groupId},#{projectId},#{talkId},#{documentId},#{userId},#{companyId},#{isAdmin},#{isActive},#{orderNo},#{createTime})");
		return YtbSql.insert(session,sql,memberModel);

	}
	public void update(WorkGroupMemberModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.work_group_member");
		sql.append("set group_id=#{groupId},project_id=#{projectId},talk_id=#{talkId},document_id=#{documentId},user_id=#{userId},company_id=#{companyId},is_admin=#{isAdmin},is_active=#{isActive},order_no=#{orderNo},create_time=#{createTime}");
		sql.append(" where member_id=#{memberId}");
		YtbSql.update(sql,m);

	}

	public void delete(int memberId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.work_group_member");
		sql.append(" where member_id=#{memberId}");
		YtbSql.delete(sql,memberId);

	}

	public WorkGroupMemberModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.work_group_member");
		sql.append(" where member_id=#{memberId}");
		return YtbSql.selectOne(sql,id,WorkGroupMemberModel.class);

	}


	public List<WorkGroupMemberModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.work_group_member");
		sql.append(" where member_id=#{memberId}");
		return YtbSql.selectList(sql,id,WorkGroupMemberModel.class);

	}

	public List<WorkGroupMemberModel>  selectList(WorkGroupMemberModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.work_group_member");
		sql.append(" where member_id=#{memberId}");
		return YtbSql.selectList(sql,m,WorkGroupMemberModel.class);

	}

}
