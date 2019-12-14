package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.IProjectTemplateAssistModelService;
import ytb.project.model.ProjectTemplateAssistModel;
import ytb.project.model.ProjectTemplateModel;

public class ProjectTemplateAssistModelServiceImpl  extends DAOService implements IProjectTemplateAssistModelService {
	//协助模板
	transient public final static String Template_ASSIST_TableName = "ytb_project.project_template_assist";

	//协助文件夹
	transient public final static String AssistFolder_TableName = "ytb_project.project_folder_assist";
	//协助文件夹 check协助文档是否已经存在 每个人一个项目只有一个共享或者协助文件夹
	protected boolean checkExistsTemplatetAssist(int folderId, int templateIdIni) {
		StringBuilder sql = new StringBuilder(128);
		sql.append(" select 1 from ").append(Template_ASSIST_TableName);
		sql.append(" where folder_id = ").append(folderId);
		sql.append(" and template_id_ini=").append(templateIdIni);
		return YtbSql.selectList(sql, ProjectTemplateModel.class).size() > 0;
	}

	//原作者 template_id_ini
	protected void deleteTemplates(int cancelType, List<Integer> templateIds) {

		for (int templateId : templateIds) {
			StringBuilder sql = new StringBuilder(128);
			sql.append(" delete from ").append(Template_ASSIST_TableName);
			sql.append(" where template_id_ini=").append(templateId);
			sql.append(" and assist_type=").append(cancelType);
			YtbSql.update(sql);
		}
	}

	public int insert(ProjectTemplateAssistModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.project_template_assist");
		sql.append("(template_id_ini,folder_id,doc_seq,title,doc_ver,document_id,status,doc_type,author,auditor,update_time,assist_type)");
		sql.append("values");		
		sql.append("(#{templateIdIni},#{folderId},#{docSeq},#{title},#{docVer},#{documentId},#{status},#{docType},#{author},#{auditor},#{updateTime},#{assistType})");
		return YtbSql.insert(sql,m);

	}
	public void update(ProjectTemplateAssistModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.project_template_assist");
		sql.append("set template_id_ini=#{templateIdIni},folder_id=#{folderId},doc_seq=#{docSeq},title=#{title},doc_ver=#{docVer},document_id=#{documentId},status=#{status},doc_type=#{docType},author=#{author},auditor=#{auditor},update_time=#{updateTime},assist_type=#{assistType}");
		sql.append(" where template_id=#{templateId})");
		YtbSql.update(sql,m);

	}

	public void delete(int templateId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.project_template_assist");
		sql.append(" where template_id=#{templateId})");
		YtbSql.delete(sql,templateId);

	}

	public ProjectTemplateAssistModel selectOne(Integer templateId) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_template_assist");
		sql.append(" where template_id=").append(templateId);
		return YtbSql.selectOne(sql,ProjectTemplateAssistModel.class);
	}

	public ProjectTemplateAssistModel selectOneByIni(int userId,Integer templateId) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_template_assist");
		sql.append(" where template_id_ini=").append(templateId);
		sql.append(" and author = ").append(userId);
		return YtbSql.selectOne(sql,ProjectTemplateAssistModel.class);
	}

	public List<ProjectTemplateAssistModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_template_assist");
		sql.append(" where template_id=#{templateId}");
		return YtbSql.selectList(sql,id,ProjectTemplateAssistModel.class);

	}

	public List<ProjectTemplateAssistModel>  selectList(ProjectTemplateAssistModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.project_template_assist");
		sql.append(" where template_id=#{templateId}");
		return YtbSql.selectList(sql,m,ProjectTemplateAssistModel.class);

	}

}
