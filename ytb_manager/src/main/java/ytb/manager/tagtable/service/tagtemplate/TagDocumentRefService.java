package ytb.manager.tagtable.service.tagtemplate;

import com.alibaba.fastjson.JSONArray;
import com.github.abel533.sql.SqlMapper;
import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.tagtable.model.tagtemplate.RefTagTableParam;
import ytb.manager.tagtable.model.tagtemplate.TagTableRaramResult;
import ytb.manager.tagtable.service.tagfun.TagFunRefTagTableParam;
import ytb.manager.template.model.Dict_document;
import ytb.manager.templateexcel.service.TemplateDocumentService;
import ytb.manager.templateexcel.service.impl.TemplateDocumentServiceImpl;
import ytb.common.context.model.Ytb_Model;

import java.util.List;

public class TagDocumentRefService extends TemplateDocumentRefService {
    static TemplateDocumentService tdService = TemplateDocumentServiceImpl.getTemplateDocumentService();
    String db;

    public void modifyDoc(String tableName, int row, String colName) {

    }

    //String tag=tagTagleParamRef@project.project_name$A1.11
    //String tag=tagTagleParamRef@项目.项目名称$A1.11
    //String tag=tagTagleParamRef@岗位任务.任务名称(电路)$A1.11
    public TagTableRaramResult refTagTableParam(RefTagTableParam rp) {

       return new TagFunRefTagTableParam(this, rp).refResult();

    }

    public TagTableRaramResult testRefTagTableParam(RefTagTableParam rp) {
        //projectId,documentId,functionID
        return new TagFunRefTagTableParam(this, rp).test();

    }

    //tagtemplate.refTagTableParam
    public List<Project_document_dirModel> refReqDir(RefTagTableParam rp) {
        int workplanId = DocumentRefService.getDocumentRefService()
                .findRefReqId(rp.getRepositoryId(), rp.getProjectId(),rp.getDocumentId()   );
        //根据项目找出需求书的ID documentId = projectId == 0 ? documentId : fnGetProject_docReq(projectId, userId);
        //System.out.println(rp.getDocumentId() + " refDocumentReqDir " + rp.getProjectId() + ":");
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select title from ytb_project.project_document_dir ");
        sql.append(" where project_id=").append(rp.getProjectId());
        sql.append(" and document_id=").append(workplanId);
        YtbLog.logDebug("refReqDir sql:"+sql);
        return YtbSql.selectList(sql, Project_document_dirModel.class);

    }

    public void exportDocumentDirList(int projectId, int documentId) {
        YtbLog.logDebug("exportDocumentDirList  " + projectId + ":" + documentId);
        Dict_document dict_document = projectId == 0
                ? tdService.getDocumentBy(documentId) :
                getProjectDocument(projectId, documentId);
        JSONArray ja = TemplateDocumentServiceImpl.getTemplateDocumentService()
                .getDocumentDirList(dict_document, projectId, documentId);

        deleteProject_document_dir(projectId, documentId);
        insertProject_document_dir(projectId, documentId, ja);

    }

    void insertProject_document_dir(int projectId, int documentId, JSONArray ja) {
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_manager(false);
        try {
            SqlMapper m = new SqlMapper(session);
            for (int i = 0; i < ja.size(); i++) {
                ;
                StringBuilder sql = new StringBuilder(128);
                sql.append("insert into  ytb_project.project_document_dir(project_id,document_id,title)");
                sql.append(" values( ").append(projectId);
                sql.append(",").append(documentId).append(",'");
                sql.append(ja.getJSONObject(i).getString("title")).append("')");
                m.insert(sql.toString());
            }
            session.commit();
        } catch (Exception ex) {
            session.rollback();
            throw ex;

        } finally {
            if (session != null) session.close();
        }

    }

      int deleteProject_document_dir(int projectId, int documentId) {
        StringBuilder sql = new StringBuilder(128);
        sql.append(" delete from  ytb_project.project_document_dir ");
        sql.append(" where project_id=").append(projectId);
        sql.append(" and document_id=").append(documentId);

        return YtbSql.update(sql);
    }

    public  static class Project_document_dirModel extends Ytb_Model {
        String title;
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }

    }
//
//    Integer fnGetProject_docReq(int projectId, int userId) {
//        StringBuilder sql = new StringBuilder(128);
//        sql.append("select ytb_project.fnGetProject_docReq(");
//        sql.append(projectId).append(",").append(userId).append(")");
//        Integer ret = YtbSql.selectOne(sql, int.class);
//        return ret == null ? 0 : ret;
//    }
//    Integer fnGetProject_docWorkplan(int projectId,int userId) {
//        StringBuilder sql = new StringBuilder(128);
//        sql.append("select ytb_project.fnGetProject_docWorkplan(");
//        sql.append(projectId).append(",").append(userId).append(")");
//        Integer ret = YtbSql.selectOne(sql, int.class);
//        return ret == null ? 0 : ret;
//    }

    public Dict_document getProjectDocument(int projectId, int documentId) {
        String tableName = "ytb_project.project_document";
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ").append(tableName);
        sql.append(" where document_id=").append(documentId);
        return YtbSql.selectOne(sql, Dict_document.class);
    }

    public Integer fnGetTestProjectID(int projectId) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("select ytb_project.fnGetTestProjectID(");
        sql.append(projectId).append(")");
        return YtbSql.selectOne(sql, Integer.class);

    }

}
