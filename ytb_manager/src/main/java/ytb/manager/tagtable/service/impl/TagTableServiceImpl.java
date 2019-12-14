package ytb.manager.tagtable.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.sql.SqlMapper;
import com.google.common.base.Joiner;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.StringUtils;
import ytb.common.MyBatis.ISqlSessionBuilder;
import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbUtils;
import ytb.common.ytblog.YtbLog;
import ytb.manager.context.ManagerSrvContext;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.manager.metadata.model.MetadataDict;
import ytb.manager.metadata.model.MetadataField;
import ytb.manager.metadata.model.SelectSql;
import ytb.manager.metadata.service.impl.MetaDataService;
import ytb.manager.tagtable.model.DBTagTable;
import ytb.manager.tagtable.model.business.ProjectRateModel;
import ytb.manager.tagtable.service.ITagTableService;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentFactroy;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.template.model.Dict_document;
import ytb.manager.templateexcel.service.impl.TemplateDocumentServiceImpl;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.impl.YtbContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * leijming
 * 20101031
 * */
public class TagTableServiceImpl extends TemplateDocumentFactroy implements ITagTableService {

    public static ITagTableService getTagTableService() {
        return ManagerSrvContext.getInst().getTagTableService();
    }

    String db_name = ISqlSessionBuilder.DB_NAME_PROJECT;
    String TableName_work_group_task = "work_group_task";

    public  TagTableServiceImpl() {
    }

    @Override
    public SqlSession getSession(boolean isAutoCommit) {
        return YtbContext.getSqlSessionBuilder().getSession_manager(isAutoCommit);
    }

    @Override
    public SqlSession getSession() {
        return getSession(true);
    }


    public MetadataDict getMetadataDictTable(String metadataName) {
        return MetaDataService.getMetaDataService().getMetadataDictTable(metadataName);
    }
    public List<MetadataField> getMetadataFields(int metadataId) {
        return MetaDataService.getMetaDataService().getMetadataFields(metadataId);
    }

    public List<String> getMetadataFields_visible(int metadataId) {

        List<MetadataField> newlst = getMetadataFields(metadataId).stream().filter(x -> x.getFieldVisible()).collect(Collectors.toList());
        List<String> vlst = new ArrayList<>();
        for (MetadataField f : newlst) {
            vlst.add(f.getFieldName());
        }
        return vlst;
    }


    public List<MetadataDict> getDictTableAndField(String metadataName) {
        return MetaDataService.getMetaDataService().getDictTableAndField(metadataName);
    }


    public DBTagTable parseTagTable(TemplateDocumentInfo documentInfo, String tableName) throws IOException {

        return DBTagTable.parseTagTable(documentInfo, tableName);
    }

    public List<String> exportAllTables(TemplateDocumentInfo documentInfo, int projectid, int documentId) throws Exception {
        YtbLog.logDebug(documentInfo.getdBDocHeader()+"\r\nexportAllTables==>" );

        List<String> tableNames = documentInfo.listTagTableName();
        for (String tableName : tableNames) {
            YtbLog.logDebug("exportAllTables==>" + tableName);
            deleteTable(documentInfo, projectid, documentId, tableName);
            exportTable(documentInfo, projectid, documentId, tableName);
        }

        if (documentInfo.isTemplate_TYPE_workplan_NonePp() && projectid > 0) {
            if (documentInfo.getCheckCostFlag()) {
                // checkMemberDayPay(MsgHandler handler,int projectId, int documentId) throws IOException ;
                checkExistCost(projectid, documentId);
            }
        }

        //后处理
        if (documentInfo.isTemplate_TYPE_workplan_NonePp()) {
            YtbLog.logDebug("exportTableWorkGroupTask==>");
            TagTableWorkGroup tagTableWorkGroup = new TagTableWorkGroup();
            int group_id = tagTableWorkGroup.exportTableWorkGroupTaskAll(documentInfo, projectid, documentId);
            tagTableWorkGroup.exportTableWorkplanView(documentInfo, projectid, documentId);

        } else if (documentInfo.isTemplate_TYPE_testReport()) {
            YtbSql.spDb("ytb_project.spSumCheckReport",projectid,documentId);
        }
        return tableNames;
    }

    public TagTableServiceImpl checkMemberDayPay(LoginSso sso, int projectId, int documentId) throws IOException {
        Collection<SelectProjectMember.DayPayInfoResult> dayPayInfos =
                new TagTableWorkGroup().selectProjectMember(sso, projectId, documentId);
        for (SelectProjectMember.DayPayInfoResult payInfoResult : dayPayInfos) {
            int pay = (int) payInfoResult.getDay_pay();
            if (pay == 0) {
                throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "有人员日薪为0!");
            }
        }
        return this;
    }

    public List<Integer> checkExistCost(int projectId, int documentId) {

        StringBuilder sql = new StringBuilder(256);
        sql.append(" select 1 from ytb_project.cost ");
        sql.append(" where project_id=").append(projectId);
        sql.append(" and document_id=").append(documentId);
        return YtbSql.selectList(sql, Integer.class);

    }

    private static boolean isNumber(String typeName) {
        return typeName.equals("BIT") || typeName.equals("TINYINT")
                || typeName.equals("SMALLINT") || typeName.equals("INTEGER")
                || typeName.equals("BIGINT") || typeName.equals("DECIMAL")
                || typeName.equals("TAGIMAGE")|| typeName.equals("MONEY");
    }

    private static boolean isNumValue(String str) {
        try {

            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Integer> exportTable(TemplateDocumentInfo documentInfo, int projectid, int documentId, String tableName) throws IOException {
        int talkId = documentInfo.getdBDocHeader().getTalkId();

        DBTagTable tagTable = parseTagTable(documentInfo,  tableName);
        if (tagTable == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, tableName);
        }
        if (tagTable.getValue() == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, tagTable.getKey());
        }
        String tableName_db = db_name + "." + tagTable.getTableDict().getTableName();
        List<Integer> lst = new ArrayList<>();

        for (DBTagTable.DBTagValue record : tagTable.getValue()) {
            JSONObject r = JSONObject.parseObject("{}");
            r.put("project_id", projectid);
            r.put("talk_id", talkId);
            r.put("document_id", documentId);
            if (checkExistField(db_name, tableName, "user_id")){
                r.put("user_id", documentInfo.getdBDocHeader().getUserId());
            }
            for (DBTagTable.TagField f : record.getItems()) {
                if (f.getFieldName() != null) {
                    if (!checkExistField(db_name, tableName, f.getFieldName()))
                       continue;
//                    if (f.getFieldName().equalsIgnoreCase("user_percent")
//                            || f.getFieldName().equalsIgnoreCase("user_percent_desc")
//                            || f.getFieldName().equalsIgnoreCase("service_sum")
//                            || f.getFieldName().equalsIgnoreCase("tax_sum"))
//                        continue;
                    if (f.getRefField() != null && !f.getRefField().trim().isEmpty()) {
                        r.put(f.getRefField().trim(), f.getRefValue());
                    }
                    r.put(f.getFieldName().trim(), f.getText());
                    if(f.getFieldName().equalsIgnoreCase("cost_sum")){
                        if (f.getText().isEmpty()){
                            r.put(f.getFieldName().trim(), 0);
                        }
                    }
                    if (isNumber(f.getFieldType())) {
                        if (f.getText() == null || f.getText().isEmpty() || !isNumValue(f.getText())) {
                            r.put(f.getFieldName().trim(), 0);
                        }
                    }
                }
            }
            int id = exportTable(tableName_db, r);
            lst.add(id);

        }
        if (documentInfo.isTemplateWorkplan()) {
            if (checkExistField(db_name, tableName, "user_id") && projectid>0) {
                StringBuilder sql = new StringBuilder();
                sql.append(" delete from ").append(tableName_db).append(" ");
                sql.append(" where project_id=").append(projectid);
                sql.append(" and document_id=").append(documentId);
                sql.append(" and user_id=0 ");
                this.updateSql(sql);
            }
        }
        if (tableName.equals(TableName_work_group_task)) {
            updateWorkGroupTaskIsAdmin(projectid, tableName_db);
        }

        return lst;
    }

    //可以用数据字典
    boolean checkExistField(String dbname, String tablename, String colname) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT 1 FROM information_schema.columns ");
        sql.append(" WHERE table_schema='").append(db_name);
        sql.append("' AND table_name = '").append(tablename);
        sql.append("' AND column_name = '").append(colname).append("'");
        return YtbSql.selectList(sql).size() > 0;
    }

    int updateWorkGroupTaskIsAdmin(int project_id, String tableName) {
        StringBuilder sql = new StringBuilder();
        sql.append(" update ").append(tableName).append(" set is_admin=1 ");
        sql.append(" where project_id=").append(project_id).append(" and work_job='负责人'");
        return updateSql(sql);
    }

    @Override
    public int deleteTable(TemplateDocumentInfo templateDocumentInfo, int prjectid, int documentId, String tableName) throws IOException {
        DBTagTable tagTable = parseTagTable(templateDocumentInfo,  tableName);
        JSONObject r = JSONObject.parseObject("{}");
        r.put("project_id", prjectid);
        r.put("document_id", documentId);
        deleteTable(db_name + "." + tagTable.getTableDict().getTableName(), r);
        return 0;
    }

    @Override
    public int modifyTable(TemplateDocumentInfo templateDocumentInfo, int prjectid, int documentId, String tableName) throws IOException {
        DBTagTable tagTable = parseTagTable(templateDocumentInfo,  tableName);
        for (DBTagTable.DBTagValue record : tagTable.getValue()) {
            JSONObject r = JSONObject.parseObject("{}");
            r.put("document_id", documentId);
            r.put("project_id", prjectid);
            for (DBTagTable.TagField f : record.getItems()) {
                r.put(f.getFieldName().trim(), f.getText());
            }
            modifyTable(db_name + "." + tagTable.getTableDict().getTableName(), r);
        }
        return documentId;
    }

    public List<Map<String, Object>> selectTable(SqlSession session, Integer projectId, int documentId, String tableName) {

        SqlMapper m = new SqlMapper(session);
        StringBuilder sql = new StringBuilder("select * from ");
        if (tableName.contains(".")) {
            sql.append(tableName);
        } else {
            sql.append(db_name).append(".").append(tableName);
        }
        sql.append(" where document_id=").append(documentId);
        if(projectId>=0) {
            sql.append(" and  project_id=").append(projectId);
        }
        //System.out.println(sql.toString());
        return m.selectList(sql.toString());
    }

    public List<Map<String, Object>> selectTable(SqlSession session, int documentId, String tableName) {

        SqlMapper sqlMapper = new SqlMapper(session);
        StringBuilder sql = new StringBuilder("select * from ");
        if (tableName.contains(".")) {
            sql.append(tableName);
        } else {
            sql.append(db_name).append(".").append(tableName);
        }
        sql.append(" where document_id=").append(documentId);
        return sqlMapper.selectList(sql.toString());
    }

    public List<Map<String, Object>> selectSp(Integer projectId, int documentId, String spName){
        StringBuilder sql = new StringBuilder("call ").append(spName);
        sql.append("(").append(projectId);
        sql.append(",").append(documentId).append(")");
        return YtbSql.selectList(sql);
    }

    public List<Map<String, Object>> selectTable(Map<String, Object> params, String tableName) {

        StringBuilder sql = new StringBuilder("select * from ");
        if (tableName.contains(".")) {
            sql.append(tableName);
        } else {
            sql.append(db_name).append(".").append(tableName);
        }
        sql.append(" where 1=1 ");
        for (Map.Entry<String, Object> e : params.entrySet()) {
            sql.append(" and ").append(e.getKey()).append("='").append(e.getValue()).append("'");
        }

        //System.out.println(sql.toString());
        return selectTable(sql);
    }

    public List<Map<String, Object>> selectTable(StringBuilder sql) {

        SqlSession session = getSession(true);
        try {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql.toString());
        } finally {
            session.close();
        }

    }

    public List<Map<String, Object>> selectProjectDocument(int projectId, int documentId) throws UnsupportedEncodingException {
        String tableName = "ytb_project.project_document";
        return selectDocument(projectId, documentId, tableName);
    }

    public List<Map<String, Object>> selectDocument(int projectId, int documentId, String tableName) throws UnsupportedEncodingException {

        try (SqlSession session = getSession()) {
            return selectTable(session, documentId, tableName);
        }
    }

    public List<Map<String, Object>> selectTable(Integer projectId, int documentId, String tableName) {
        SqlSession session = getSession(true);
        try {
            return selectTable(session, projectId, documentId, tableName);
        } finally {
            session.close();
        }
    }

    int deleteTable(String tableName, JSONObject record) {
        StringBuilder sql = makeSql_delete(tableName, record);
        return updateSql(sql);
    }

    int modifyTable(String tableName, JSONObject record) {
        //StringBuilder sql =  makeSql_modify(tableName, record,JSONObject.parseObject("{}")) ;
        return 0;
    }

    int exportTable(String tableName, JSONObject record) {
        YtbLog.logDebug("export Table: " + tableName);
        StringBuilder sql = makeSql_insert(tableName, record);
        return updateSql(sql);
    }

//  1、时间戳：   insert into `xqliu`.`c_test`(f_timestamp) values(now())
//  2、日期时间：    insert into `xqliu`.`c_test`(f_datetime) values(now())
//  3、日期：   insert into `xqliu`.`c_test`(f_date) values(date(now()))
//  4、时间：   insert into `xqliu`.`c_test`(f_time) values(time(now()))
    StringBuilder makeSql_insert(String tableName, JSONObject record) {
        StringBuilder sql = new StringBuilder(1024);
        sql.append("insert into ").append(tableName);
        sql.append("(").append(Joiner.on(",").join(record.keySet())).append(") values (");
        List<Object> v = new ArrayList<>();
        YtbLog.logDebug(YtbUtils.toJSONStringPretty(record));
        record.values().stream().forEach(
                x ->
                {
                    if (x == null) {
                        v.add('0');
                    } else if ("String".equals(x.getClass().getSimpleName())) {
                        if (x.toString().trim().equals("now()")) {
                            v.add(x);
                        } else {
                            v.add("'" + x + "'");
                        }
                    } else if ("Date".equals(x.getClass().getSimpleName())) {
                        v.add(x);
                    } else {
                        v.add(x);
                    } // x.getClass().getSimpleName());
                    // Boolean   Date  Integer   String
                });

        sql.append(Joiner.on(",").join(v)).append(")");
        return sql;
    }

    StringBuilder makeSql_delete(String tableName, JSONObject record) {
        StringBuilder sql = new StringBuilder(1024);
        sql.append(" delete from  ").append(tableName).append(" ");
        int i = 0;
        for (String key : record.keySet()) {
            if (i++ == 0) {
                sql.append(" where ");
            } else {
                sql.append(" and ");
            }
            sql.append(key).append(" = ").append(record.getString(key));
        }

        return sql;
    }

    StringBuilder makeSql_modify(String tableName, JSONObject record, JSONObject where) {
        StringBuilder sql = new StringBuilder(1024);
        sql.append("insert into ");
        sql.append(tableName);
        sql.append("(");
        sql.append(Joiner.on(",").join(record.keySet()));
        sql.append(") values (");
        List<String> v = new ArrayList<>();
        record.values().stream().forEach(
                x -> v.add("'" + x + "'")
        );
        sql.append(Joiner.on(",").join(v));
        sql.append(")");
        return sql;
    }

    public int updateSql(StringBuilder sql) {

        try(SqlSession session = getSession()) {
            return updateSql(session, sql);
        }
    }

    int updateSql(SqlSession session, StringBuilder sql) {

        SqlMapper m = new SqlMapper(session);
        m.update(sql.toString());
        return YtbContext.getSqlSessionBuilder().selectAutoID(session);

    }

    <T> T selectOne(SqlSession session, StringBuilder sql, Class<T> cls) {
        //System.out.println(sql.toString());
        SqlMapper m = new SqlMapper(session);
        return m.selectOne(sql.toString(), cls);
    }

    <T> List<T> selectList(SqlSession session, StringBuilder sql, Class<T> cls) {
        //System.out.println(sql.toString());
        SqlMapper m = new SqlMapper(session);
        return m.selectList(sql.toString(), cls);
    }

    @Override
    public List<Map<String, Object>> selectByTable_metadata(int projectId, int documentId, String pTableName) {
        SelectSql selectSql = new SelectSql();
        String table = pTableName.trim();
        if (!table.contains(".")) {
            table = db_name + "." + table;
        } else {
            pTableName = StringUtils.split(pTableName, ".")[1];
        }
        selectSql.setTable(table);
        MetadataDict metadataDict = getMetadataDictTable(pTableName);
        selectSql.setsWhere("project_id=" + projectId + " and document_id=" + documentId);
        if (metadataDict != null && metadataDict.getMetadataSortFields() != null && !metadataDict.getMetadataSortFields().isEmpty()) {
            selectSql.setOrderBy(metadataDict.getMetadataSortFields());
        }
        List<String> vlst = getMetadataFields_visible(metadataDict.getMetadataId());
        String fields = vlst.stream().collect(Collectors.joining(","));
        YtbLog.logDebug("selectByTable_metadata selectSql",selectSql);

        return MetaDataService.getMetaDataService().selectByTable(selectSql, fields);
    }

    @Override
    public ProjectRateModel getProjectRate (int project_id) {
        StringBuilder sql = new StringBuilder();
        if (project_id == 0 ) {
            sql.append(" select 0 as project_id,b.*");
            sql.append(" from  dict_project_type b ");
            sql.append(" where b.ProjectTypeId = 72 ");
        } else {
            sql.append(" select a.project_id,b.*");
            sql.append(" from ytb_project.project a , dict_project_type b ");
            sql.append(" where a.project_type_id = b.project_type_id ");
            sql.append(" and a.project_id=").append(project_id);
        }
        return YtbSql.selectOne(sql, ProjectRateModel.class);

    }
    int modifyTemplateDoc_mngr(int documentId, TemplateDocumentInfo tdInfo) {

        Dict_document doc=new Dict_document();
        byte[] document = YtbUtils.toJSONStringPretty(tdInfo.getDocJson()).getBytes(StandardCharsets.UTF_8);
        //System.out.println(doc);
        doc.setDocument(document);
        doc.setDocumentId(documentId);
        StringBuilder sql = new StringBuilder();
        sql.append("update  ytb_manager.dict_document  set document=#{document} ");
        sql.append( " where document_id=#{documentId}");
        return YtbSql.update(sql,doc);
    }


    int modifyTemplateDoc_project(int documentId, TemplateDocumentInfo tdInfo) {

        Dict_document doc = new Dict_document();
        byte[] document = YtbUtils.toJSONStringPretty(tdInfo.getDocJson()).getBytes(StandardCharsets.UTF_8);

        doc.setDocument(document);
        doc.setDocumentId(documentId);
        StringBuilder sql = new StringBuilder();
        sql.append( " update  ytb_project.project_document  set document=#{document} ");
        sql.append( " where document_id=#{documentId}");
        return YtbSql.update(sql,doc);
    }


    //TagTableServiceImpl
    @Override
    public String modifyDocByTagTableParam(TemplateDocumentInfo tdInfo, int projectid, int documentId) throws Exception {
        DBTagTable dbTagTable = tdInfo.parseTagTableParam();
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ytb_project.").append(dbTagTable.getTableDict().getTableName());
        sql.append(" where project_id=").append(projectid);
        sql.append(" and   document_id=").append(documentId);
        //获取标签关联参数表记录
        List<Map<String, Object>> rs = YtbSql.selectList(sql);
        if (rs.size() == 0) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "标签关联参数表记录");
        }
        //System.out.println(YtbUtils.toJSONStringPretty(rs));
        //修改tdInfo json内容 在本类加个函数  @lxz
        //innerModifyJsonParam(tdInfo, rs.get(0));
        TemplateDocumentServiceImpl.getTemplateDocumentService().innerModifyJsonParam(tdInfo, rs.get(0));

        //修改表内容
        int ret = projectid == 0 ?
                modifyTemplateDoc_mngr(documentId, tdInfo) :
                modifyTemplateDoc_project(documentId, tdInfo);
        return dbTagTable.getTableDict().getTableName();
    }


}
