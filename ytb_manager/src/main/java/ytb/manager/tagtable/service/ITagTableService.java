package ytb.manager.tagtable.service;

import org.apache.ibatis.session.SqlSession;
import ytb.manager.metadata.model.MetadataDict;
import ytb.manager.tagtable.model.business.ProjectRateModel;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface ITagTableService {
    SqlSession getSession();

    SqlSession getSession(boolean isAutoCommit);

    List<Map<String, Object>> selectByTable_metadata(int projectId, int documentId, String tableName);

    ProjectRateModel getProjectRate(int projectid);

    MetadataDict getMetadataDictTable(String metadataName);

    List<MetadataDict> getDictTableAndField(String metadataName);

    String modifyDocByTagTableParam(TemplateDocumentInfo tdInfo, int projectid, int documentId) throws Exception;

    List<String> exportAllTables(TemplateDocumentInfo documentInfo, int projectid, int documentId) throws Exception;

    List<Integer> exportTable(TemplateDocumentInfo tdInfo, int prjectid, int documentId, String tableName) throws IOException;

    int modifyTable(TemplateDocumentInfo tdInfo, int prjectid, int documentId, String tableName) throws IOException;

    int deleteTable(TemplateDocumentInfo tdInfo, int prjectid, int documentId, String tableName) throws IOException;

    int updateSql(StringBuilder sql);

    List<Map<String, Object>> selectTable(Integer projectId, int documentId, String tableName);

    List<Map<String, Object>> selectTable(Map<String, Object> params, String tableName);

    //存贮过程查询结果
    List<Map<String, Object>> selectSp(Integer projectId, int documentId, String spName);

    List<Map<String, Object>> selectTable(StringBuilder sql);

    List<Map<String, Object>> selectDocument(int projectId, int documentId, String tableName) throws UnsupportedEncodingException;

    List<Map<String, Object>> selectProjectDocument(int projectId, int documentId) throws UnsupportedEncodingException;

}
