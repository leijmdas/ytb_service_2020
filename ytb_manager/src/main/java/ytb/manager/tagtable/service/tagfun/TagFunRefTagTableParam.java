package ytb.manager.tagtable.service.tagfun;

import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;
import ytb.common.utils.YtbSql;
import ytb.common.ytblog.ManagerYtbLog;
import ytb.common.ytblog.YtbLog;
import ytb.manager.tagtable.model.tagtemplate.RefTagTableParam;
import ytb.manager.tagtable.model.tagtemplate.TagTableDict;
import ytb.manager.tagtable.model.tagtemplate.TagTableRaramResult;
import ytb.manager.tagtable.model.tagtemplate.Tag_FunctionModel;
import ytb.manager.tagtable.service.tagtemplate.DocumentRefService;
import ytb.manager.tagtable.service.tagtemplate.TagDocumentRefService;
import ytb.manager.tagtable.service.impl.TagTableProjectService;
import ytb.manager.templateexcel.model.tag.impl.TagEnum;
import ytb.common.context.model.YtbError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagFunRefTagTableParam extends RefTagTableParam {
    transient static String dbName = "ytb_project";
    transient TagDocumentRefService td;
    RefTagTableParam rp;

    int docType;
    String tagName;
    String tableName;
    String fieldDisplayName;//输出
    String pField;//条件
    String pValue;//条件值

    public TagFunRefTagTableParam(TagDocumentRefService tagDocument, RefTagTableParam rp) {

        this.td = td;
        this.rp = rp;

        this.setUserId(rp.getUserId());
        this.setGroupId(rp.getGroupId());

        this.setProjectId(rp.getProjectId());
        this.setTalkId(rp.getTalkId());
        this.setPhase(rp.getPhase());
        this.docType = rp.getDocType();
        this.setDocumentId(rp.getDocumentId());

        this.setRepositoryId(rp.getRepositoryId());

        this.setTag(rp.getTag());
    }

    /*pid,dic,fid*/
    public TagTableRaramResult test() {
        this.setProjectId(projectId);
        this.setDocumentId(documentId);
        Tag_FunctionModel m = TagFunService.findTag_FunctionModel(rp.getFunctionId());
        this.setTag(m.getDemo());
        TagTableRaramResult tagTableRaramResult = parse().refResult();
        ManagerYtbLog.logRun("TagFunRefTagTableParam::test", tagTableRaramResult);
        return tagTableRaramResult;
    }


    //String tag=tagTagleParamRef@项目.项目名称$A1.11
    //String tag=tagTagleParamRef@岗位任务.任务名称(电路)$A1.11
      TagFunRefTagTableParam parse() {

        String[] ret = getTag().split("\\@|\\$");
        tagName = ret[0];
        if(tagName.equals(TagEnum.TAG_TABLE_REF)){
            return parseTagTableRef();
        }
          String[] name = ret[1].split("\\.");
          tableName = name[0];
          fieldDisplayName = name.length <= 1 ? "" : name[1];
          String s[] = fieldDisplayName.split("\\(|\\)");
          fieldDisplayName = s[0];
          pValue = s.length > 1 ? s[1] : "";

          pField = TagTableDict.findPField(this.tableName);
          return this;
    }

    public TagFunRefTagTableParam parseTagTableRef() {

        String[] ret = getTag().split("\\@|\\$");
        tagName = ret[0];
        String[] name = ret[1].split("\\(");
        tableName = name[0];
        fieldDisplayName = "";
        String s[] = ret[1].split("\\(|\\)");
        pValue = s.length > 1 ? s[1] : "";
        pField = TagTableDict.findPField(this.tableName);
        return this;
    }

    public TagTableRaramResult refResult() {
        parse();
        String tableName = TagTableDict.findTable(this.tableName);
        if (tableName.equals("project")) {
            return projectRefResult();
        } else if (tableName.equals("work_group_task")) {
            int workplanId = DocumentRefService.getDocumentRefService()
                    .findRefWorkplanId(getRepositoryId(), getProjectId(), getDocumentId());
            return taskOutRefResult(workplanId);
        } else if (tableName.equals("dict_datatype")) {
            return dictDataTypeRefResult();

        } else if (tagName.equals(TagEnum.TAG_TABLE_REF)) {
            Tag_FunctionModel m = TagFunService.findTagTableRef_FunctionModel(this);
            if (m != null) {
                return tagTableRef(m);
            }
        } else {
            Tag_FunctionModel m = TagFunService.findTag_FunctionModel(this);
            if (m != null) {
                return tagFunctionRefResult(m);
            }
        }

        return defaultRefResult();
    }

    //设计文档引用计划书
    TagTableRaramResult taskOutRefResult(int workplanId) {
        StringBuilder sql = new StringBuilder(256);
        sql.append(" select ifnull(");
        sql.append(TagTableDict.findRefField(fieldDisplayName));
        sql.append(",'') as name ");
        sql.append(" from ").append(dbName).append(".");
        sql.append(TagTableDict.findTable(tableName));

        int pid = getProjectId();// td.selectFn(getProjectId())
        sql.append(" where project_id=").append(pid);
        sql.append(" and document_id = ").append(workplanId);
        sql.append(" and ").append(pField);
        sql.append(" like '%").append(pValue).append("%'");
        YtbLog.logDebug(sql);
        String t = YtbSql.selectOne(sql, String.class);
        TagTableRaramResult tagResult =new  TagTableRaramResult();

        if (t == null || t.isEmpty()) {
            t = "未知值";
            tagResult.setVisible(false);
            return tagResult ;
        }
        String[] ts = t.split(";|；");
        tagResult.setVisible(ts.length > 0);
        for (String tt : ts) {
            Map<String, Object> m = new HashMap<>();
            m.put("name", tt);
            tagResult.getList().add(m);
        }
        return tagResult;

    }

    TagTableRaramResult dictDataTypeRefResult() {
        StringBuilder sql = new StringBuilder(256);
        sql.append(" select data_id as id,ifnull(");
        sql.append(TagTableDict.findRefField(fieldDisplayName));
        sql.append(",'') as name ");
        sql.append(" from ytb_manager.dict_datatype");
        sql.append(" where ").append(TagTableDict.findPField(tableName));
        sql.append(" ='").append(pValue).append("'");
        YtbLog.logDebug(sql);
        TagTableRaramResult tagResult=new TagTableRaramResult();
        List<Map<String, Object>> lst=YtbSql.selectList( sql );
        tagResult.getList().addAll(lst);
        return tagResult;

    }


    TagTableRaramResult projectRefResult() {
        StringBuilder sql = new StringBuilder(256);
        sql.append(" select ifnull(");
        sql.append(TagTableDict.findRefField(fieldDisplayName));
        sql.append(",'') as name ");
        sql.append(" from ").append(dbName).append(".");
        sql.append(TagTableDict.findTable(tableName));
        int projectId = getProjectId() == 0 ? td.fnGetTestProjectID(getProjectId()) : getProjectId();
        sql.append(" where project_id=").append(projectId);
        YtbLog.logDebug(sql);
        TagTableRaramResult tagResult=new TagTableRaramResult();
        String value=YtbSql.selectOne(sql,String.class);
        tagResult.setValue(value==null?"":value);
        tagResult.setList(null);
        return tagResult;

    }

    protected String buildDbTableName(String tableId){
        if(tableId.contains(".")){
            return tableId;
        }
        return dbName + "." + tableId;
    }

    TagTableRaramResult tagFunctionRefResult(Tag_FunctionModel m) {
        if(tagName.equals(TagEnum.TAG_TABLE_TEXT_REF)){
            return tagTableTextRef(m);
        }else
        if(tagName.equals(TagEnum.TAG_TABLE_PARAM_REF)){
            return tagTableParamRef(m);
        }else
        if(tagName.equals(TagEnum.TAG_TABLE_REF)){
            return tagTableRef(m);
        }

        return tagTableParamRef(m);
    }

    TagTableRaramResult tagTableRef (Tag_FunctionModel m) {
        if(m.isRetTable()){
            return tagTableRef2Table(m);
        }
        return tagTableRef2ListOrValue(m);
    }

    //tagTableRef@project_task_total
    TagTableRaramResult tagTableRef2Table(Tag_FunctionModel m) {

        String pTable = TagTableDict.findTable(m.getTableId());
        pTable = buildDbTableName(pTable);

        //条件关联projectid documentid
        TagTableRaramResult tagResult = new TagTableRaramResult();
        tagResult.setValue(null);
        tagResult.setList(null);
        YtbLog.logDebug("tagTableRef2Table::Tag_FunctionModel",m);
        YtbLog.logDebug("TagFunRefTagTableParam",this);
        if (m.getbTagTable()) {
//            List<Map<String, Object>> records = new TagTableProjectService()
//                    .selectByTable_metadata(projectId, documentId, pTable);   tagResult.setTable(records);
             StringBuilder sql = new StringBuilder(256);
            sql.append(" select * from ").append(pTable);
            sql.append(" where project_id=").append(projectId);
            sql.append(" and  document_id=").append(documentId);
            if (!m.getFieldFilterId().trim().isEmpty()) {
                String[] fields = m.getFieldFilterId().split(",");
                for (String field : fields) {
                    if (field.trim().equals("user_id")) {
                        sql.append(" and user_id=").append(getUserId());
                    }
                    if (field.trim().equals("phase")) {
                        sql.append(" and phase=").append(getPhase());
                    }
                    if (field.trim().equals("group_id")) {
                        sql.append(" and group_id=").append(getGroupId());
                    }
                }
            }
            YtbLog.logDebug("tagTableRef2Table",sql);
            List<Map<String, Object>> records = YtbSql.selectList(sql);
            tagResult.setTable(records);
        } else {

            List<String> keyValues=new ArrayList<>();
//            if(!m.getFieldFilterId().isEmpty()) {
//                keyValues.add(m.getFieldFilterId()+"='"+pValue+"'");
//            }
            if (!m.getFieldFilterId().trim().isEmpty()) {
                String[] fields = m.getFieldFilterId().split(",");
                for (String field : fields) {
                    if (field.trim().equals("user_id")) {
                        keyValues.add("  user_id="+getUserId());
                    }
                    if (field.trim().equals("phase")) {
                        keyValues.add("  phase="+getPhase());
                    }
                    if (field.trim().equals("group_id")) {
                        keyValues.add("  group_id="+getGroupId());
                    }
                }
            }
            StringBuilder sql = new StringBuilder(256);
            sql.append(" select * from ").append(pTable);
            if(keyValues.size()>0){
                sql.append(" where ").append(StringUtil.join(keyValues.toArray()," and "));
            }
            YtbLog.logDebug("tagTableRef2Table",sql);
            List<Map<String, Object>> records = YtbSql.selectList(sql);
            tagResult.setTable(records);

        }

        tagResult.setVisible(true);
        return tagResult;

    }

    //tagTableRef@project_task_total
    TagTableRaramResult tagTableRef2ListOrValue(Tag_FunctionModel m) {

        String pTable = TagTableDict.findTable(m.getTableId());
        pTable = buildDbTableName(pTable);

        //条件关联projectid documentid
        TagTableRaramResult tagResult = new TagTableRaramResult();
        tagResult.setValue(null);
        if (m.getbTagTable()) {
            List<Map<String, Object>> records = new TagTableProjectService().selectByTable_metadata(projectId, documentId, pTable);
            tagResult.setList(records);
        } else {
            if (m.getFieldFilterId().trim().isEmpty()) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "数据无条件有危险！");
            }
            StringBuilder sql = new StringBuilder(256);
            sql.append(" select * from ").append(pTable);
            sql.append(" where ").append(m.getFieldFilterId());
            //String svalue=m.getFieldFilterId().trim().equals("project_id")?getProjectId()+"":pValue;
            //svalue=m.getFieldFilterId().trim().equals("document_id")?getDocumentId()+"":svalue;
            sql.append("='").append(pValue).append("'");
            YtbLog.logDebug(sql);
            List<Map<String, Object>> records = YtbSql.selectList(sql);
            tagResult.setList(records);

        }

        tagResult.setVisible(true);
        return tagResult;

    }

    TagTableRaramResult tagTableParamRef(Tag_FunctionModel m) {
        return tagTableTextRef(m);
    }


    TagTableRaramResult spTagTableTextRef(String spName, Tag_FunctionModel functionModelm) {
        String s[] = spName.split("\\(|\\)");
        String spParam = s.length > 1 ? StringUtils.quote(s[1]) : "";
        spName = s[0];
        String dbSpName = buildDbTableName(spName);
        String svalue = functionModelm.getFieldFilterId().trim().equals("project_id") ? getProjectId() + "" : pValue;
        svalue = functionModelm.getFieldFilterId().trim().equals("talk_id") ? getTalkId() + "" : pValue;
        svalue = functionModelm.getFieldFilterId().trim().equals("document_id") ? getDocumentId() + "" : svalue;
        int i = 0;
        if (!spName.isEmpty()) i++;
        if (!svalue.isEmpty()) i++;

        List<Map<String, Object>> mapList = spParam.isEmpty() ? YtbSql.spDb(dbSpName, svalue) :
                YtbSql.spDb(dbSpName, spParam, svalue);

        return new TagFunParseResult().parseResult(mapList, functionModelm);
    }

    TagTableRaramResult tagTableTextRef(Tag_FunctionModel m) {
        String t = TagTableDict.findTable(m.getTableId());
        if(t.trim().startsWith("sp")||t.trim().startsWith("fn"))
        {
            return spTagTableTextRef(t,m);
        }

        StringBuilder sql = new StringBuilder(256);
        sql.append(" select ifnull(");
        sql.append(TagTableDict.findRefField(m.getFieldDisplayId()));
        sql.append(",'') as name ");
        sql.append(" from ") ;
        sql.append(buildDbTableName(TagTableDict.findTable(m.getTableId())));

        //条件关联projectid documentid
        if (m.getbTagTable()) {
            sql.append(" where project_id=").append(getProjectId());
            int docid = getDocumentId();
            System.err.println(m);
            if (m.getRefDocType() > 0 && docType != m.getRefDocType()) {
                docid = getProjectId()==0?
                        td.findRef(getRepositoryId(),getProjectId(), getDocumentId(), (short) m.getRefDocType()):
                        td.findRefProject(getProjectId(), getDocumentId(), (short) m.getRefDocType());
            }
            sql.append(" and  document_id=").append(docid);
            if (!m.getFieldFilterId().trim().isEmpty()) {
                sql.append(" and ").append(m.getFieldFilterId());
                if(m.getFieldFilterSign().isEmpty()){
                    m.setFieldFilterSign("=");
                }
                if(m.getFieldFilterSign().trim().equals("like"))
                {
                    sql.append(" like '%").append(pValue).append("%'");
                }else
                //if(m.getFieldFilterSign().equalsIgnoreCase("="))
                {
                    sql.append(" = '").append(pValue).append("'");
                }
            }

        } else {
            if (m.getFieldFilterId().trim().isEmpty()) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "数据无条件有危险！");
            }
            sql.append(" where ").append(m.getFieldFilterId());
            String svalue = m.getFieldFilterId().trim().equals("project_id") ? getProjectId() + "" : pValue;
            svalue = m.getFieldFilterId().trim().equals("document_id") ? getDocumentId() + "" : svalue;
            sql.append("='").append(svalue).append("'");
        }
        YtbLog.logDebug("tagTableTextRef",sql);
        TagTableRaramResult tagResult = new TagTableRaramResult();
        ManagerYtbLog.logDebug("tagTableTextRef sql", sql);
        if (m.getRetDatatype().equalsIgnoreCase("LIST")) {
            tagResult.getList().addAll(YtbSql.selectList(sql));
            tagResult.setVisible(tagResult.getList().size() > 0);
        } else {
            String value = YtbSql.selectOne(sql, String.class);
            tagResult.setValue(value == null ? "" : value);
            tagResult.setList(null);
            tagResult.setVisible(true);
        }

        return tagResult;

    }

    TagTableRaramResult defaultRefResult() {

        StringBuilder sql = new StringBuilder(256);
        sql.append(" select ifnull(");
        sql.append(TagTableDict.findRefField(fieldDisplayName));
        sql.append(",'') as name ");
        sql.append(" from ").append(dbName).append(".");
        sql.append(TagTableDict.findTable(tableName));
        int pid = getProjectId() == 0 ? getProjectId() : getProjectId();
        sql.append(" where project_id=").append(pid);
        sql.append(" and document_id=").append(getDocumentId());
        // System.out.println(sql);
        TagTableRaramResult tagResult = new TagTableRaramResult();
        tagResult.getList().addAll(YtbSql.selectList(sql));
        tagResult.setVisible(tagResult.getList().size() > 0);

        return tagResult;

    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldDisplayName() {
        return fieldDisplayName;
    }

    public void setFieldDisplayName(String fieldDisplayName) {
        this.fieldDisplayName = fieldDisplayName;
    }


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


    public String getpField() {
        return pField;
    }

    public void setpField(String pField) {
        this.pField = pField;
    }

    public String getpValue() {
        return pValue;
    }

    public void setpValue(String pValue) {
        this.pValue = pValue;
    }

}
