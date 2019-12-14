package ytb.manager.tagtable.service.tagtemplate;

import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.manager.tagtable.model.tagtemplate.Document_RefModel;

import ytb.manager.tagtable.service.IDocumentRefService;

import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.common.context.model.Ytb_Model;


import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

public class TemplateDocumentRefService extends Ytb_Model implements IDocumentRefService {
    protected   String tableName = "ytb_manager.dict_document_ref";

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

   /*projectId=0 使用repositoryId,用于模板测试
    projectId>0不用使用repositoryId为条件，项目
    * */
    @Override
    public void setUpRefAll(int repositoryId, int projectId,long userId) throws
            UnsupportedEncodingException {
        //projectId=0模板管理根据repositoryId进行管理
        TemplateSetUpRefAll rs = projectId == 0 ? new TemplateSetUpRefAll() : new ProjectSetUpRefAll();
        rs.setUpRefAll(repositoryId, projectId,userId);

    }

    @Override
    public List<Dict_TemplateModel> spFindAllTemplateByWorkjobType(int pWworkjob_type,  int projectId)     {
        return YtbSql.spDb(Dict_TemplateModel.class,"spFindAllTemplateByWorkjobType",pWworkjob_type);
    }


    @Override
    public List<Dict_TemplateModel> spFindAllTemplateByRepository(int repositoryId, int projectId)     {
        return YtbSql.spDb(Dict_TemplateModel.class,"spFindAllTemplateByRepository",repositoryId);
    }


    @Override
    public int insertRefReqWorkplan(TemplateDocumentHeader header) {
        insertRefReq(header);
        return insertRefWorkplan(header);
    }

    @Override
    public int insertRefReq(TemplateDocumentHeader header) {
        Document_RefModel m = new Document_RefModel(header);
        m.setUserId(header.getUserId());
        m.setDocType((short) header.getDocType());
        m.setDocumentId(header.getDocumentId());
        m.setpDocType((short) Dict_TemplateModel.Template_TYPE_req);
        m.setpDocumentId(header.getReqId());
        return this.insertRef(m);
    }

    @Override
    public int insertRefWorkplan(TemplateDocumentHeader header) {
        Document_RefModel m = new Document_RefModel(header);
        m.setDocType((short) header.getDocType());
        m.setDocumentId(header.getDocumentId());

        m.setpDocType((short) Dict_TemplateModel.Template_TYPE_workplan);
        m.setpDocumentId(header.getWorkplanId());
        return this.insertRef(m);
    }


    public Document_RefModel findDocumentRefModel(int repositoryId, int projectId, int documentId, short pDocType) {
        Document_RefModel m = new Document_RefModel();
        m.setRepositoryId(repositoryId);
        m.setProjectId(projectId);
        m.setDocumentId(documentId);
        m.setpDocType(pDocType);

        StringBuilder sql = new StringBuilder(256);
        sql.append("select * from ").append(getTableName());
        sql.append(" where project_id=").append(projectId);
        if(projectId==0) {
            sql.append(" and repository_id=").append(repositoryId);
        }
        sql.append(" and document_id=").append(documentId);
        sql.append(" and p_doc_type=").append(pDocType);
        sql.append(" limit 1 ");
        YtbLog.logDebug("find doc ref:");
        YtbLog.logDebug(sql);

        return YtbSql.selectOne(sql, m, Document_RefModel.class);

    }

    @Override
    public Document_RefModel findDocumentChildModel(int repositoryId, int projectId, int pDocumentId, short docType) {
        Document_RefModel m = new Document_RefModel();
        m.setRepositoryId(repositoryId);
        m.setProjectId(projectId);
        m.setpDocumentId(pDocumentId);
        m.setDocType(docType);

        StringBuilder sql = new StringBuilder(256);
        sql.append("select * from ").append(getTableName());
        sql.append(" where project_id=").append(projectId);
        if (projectId == 0) {
            sql.append(" and repository_id=").append(repositoryId);
        }
        sql.append(" and p_document_id=").append(pDocumentId);
        sql.append(" and doc_type=").append(docType) .append(" limit 1 ");
        YtbLog.logDebug("find doc child",sql);
        return YtbSql.selectOne(sql, m, Document_RefModel.class);
    }

    @Override
    public int insertRef(Document_RefModel m) {
        m.setpDocTime(new Timestamp(System.currentTimeMillis()));
        m.setDocTimeSync(new Timestamp(System.currentTimeMillis() -10000000));
        YtbLog.logDebug(m);
        StringBuilder sql = new StringBuilder(256);
        sql.append(" insert into ").append(getTableName());
        sql.append(" (  ref_id,user_id,repository_id,project_id,");
        sql.append(" document_id,doc_type,doc_time_sync,");
        sql.append(" p_document_id,p_doc_type,p_doc_time ,talk_id,phase) ");
        sql.append(" values (  #{refId},#{userId},#{repositoryId},#{projectId},");
        sql.append(" #{documentId},#{docType},#{docTimeSync},");
        sql.append(" #{pDocumentId},#{pDocType},#{pDocTime},#{talkId},#{phase}  )");
        YtbLog.logDebug("find doc ref:"+sql);
        return YtbSql.insert(sql, m);

    }

    @Override
    public void updateRefPDocTime(int projectId, int pDocumentId) {
        StringBuilder sql = new StringBuilder(256);
        sql.append(" update  ").append(getTableName());
        sql.append(" set  p_doc_time=now()");
        sql.append(" where  p_document_id=").append(pDocumentId);
        if (projectId > 0) {
            sql.append(" and  project_id=" ).append(projectId);
        }
        YtbLog.logDebug("updateRefPDocTime pDocumentId:" + pDocumentId + " updateRefPDocTime projectId:" + projectId);
        YtbLog.logDebug(sql);
        YtbSql.update(sql, pDocumentId);
    }

    @Override
    public void updateRefDocTimeSync(int projectId, int documentId) {
        StringBuilder sql = new StringBuilder(256);
        sql.append(" update  ").append(getTableName());
        sql.append(" set doc_time_sync=now()");
        sql.append(" where  document_id=").append(documentId);
        if (projectId > 0) {
            sql.append(" and  project_id= ").append(projectId);
        }
        YtbLog.logDebug("updateRefDocTimeSync projectId:" + projectId);
        YtbLog.logDebug("updateRefDocTimeSync documentId:" + documentId);
        YtbLog.logDebug(sql);
        YtbSql.update(sql, documentId);
    }

    @Override
    public void updateRef(Document_RefModel m) {
        StringBuilder sql = new StringBuilder(256);
        sql.append(" update  ").append(getTableName());
        sql.append(" set  ");
        sql.append(" user_id=#{userId},repository_id=#{repositoryId},project_id=#{projectId},");
        sql.append(" document_id =#{documentId},doc_type=#{docType},doc_time_sync=#{docTimeSync},");
        sql.append(" p_document_id=#{pDocumentId},p_doc_type=#{pDocType},p_doc_time=#{pDocTime}");
        sql.append(" where  ref_id=#{refId}");

        YtbLog.logDebug("find doc ref:");
        YtbLog.logDebug(sql);
        YtbSql.insert(sql, m);
    }

    //加phase
    @Override
    public void deleteRef(TemplateDocumentHeader pHeader) {
        StringBuilder sql = new StringBuilder(256);
        YtbLog.logDebug(this);
        sql.append(" delete from ").append(getTableName());
        if (pHeader.getProjectId() == 0) {
            sql.append(" where repository_id=#{repositoryId} ");
            //System.out.println("deleteRef doc ref repositoryId:" + pHeader.getRepositoryId());
            YtbSql.delete(sql, pHeader.getRepositoryId());
        } else {
            sql.append(" where project_id=#{projectId} ");
            sql.append(" and user_id=  ").append(pHeader.getUserId());
            sql.append(" and phase =  ").append(pHeader.getPhase());
            //System.out.println("deleteRef doc ref projectId:" + pHeader.getProjectId());
            YtbSql.delete(sql, pHeader.getProjectId());
        }
        YtbLog.logDebug(sql);
    }

    @Override
    public void deleteRefByDocumentId(int projectId, int documentId) {
        StringBuilder sql = new StringBuilder(256);
        sql.append(" delete from ").append(getTableName());
        sql.append(" where project_id=  ").append(projectId);
        sql.append(" and document_id=  ").append(documentId);
        YtbLog.logDebug(sql);
        YtbSql.delete(sql );
    }

    @Override
    public void deleteRefByPDocId(int projectId, int pDocId) {
        StringBuilder sql = new StringBuilder(256);
        sql.append(" delete from ").append(getTableName());
        sql.append(" where project_id=  ").append(projectId);
        sql.append(" and p_document_id=").append(pDocId);

        YtbLog.logDebug("delete doc ref projectId: " + projectId);
        YtbLog.logDebug("delete doc ref pDocId: " + pDocId);
        YtbLog.logDebug(sql);
        YtbSql.delete(sql );
    }

    @Override
    public void deleteRefById(int projectId, int refId) {

    }

    @Override
    public int findRefProject(int projectId, int documentId, short pDocType) {
        return  findRef(0,projectId,documentId,pDocType);
    }

    public int findRef(int repositoryId, int projectId, int documentId, short pDocType) {
        Document_RefModel m = findDocumentRefModel(repositoryId, projectId, documentId, pDocType);
        if (m == null) {  //throw new YtbError(YtbError.CODE_DOC_REF_NOT_FOUND);
            return 0;
        }
        //System.out.println(m.getpDocumentId());
        return m.getpDocumentId();
    }

    public int findRefReqId(int repositoryId, int projectId, int documentId) {
        return findRef(repositoryId, projectId, documentId, (short) Dict_TemplateModel.Template_TYPE_req);
    }

    public int findRefWorkplanId(int repositoryId, int projectId, int documentId) {
        return findRef(repositoryId, projectId, documentId, (short) Dict_TemplateModel.Template_TYPE_workplan);

    }

    @Override
    public int findChildWorkplanId(int repositoryId, int projectId, int documentId) {
        return 0;
    }


}
