package ytb.manager.tagtable.model.tagtemplate;

import ytb.common.context.model.Ytb_Model;

import java.sql.Date;

public class Tag_FunctionModel extends Ytb_Model {
    public final static String  RET_KEY_VALUE = "value";
    public final static String  RET_KEY_LIST = "list";
    public final static String  RET_KEY_TABLE = "table";

    public final static String  RET_DATATYPE_LIST = "LIST";
    public final static String  RET_DATATYPE_STRING = "STRING";
    public final static String  RET_DATATYPE_LIST2STRING = "LIST2STRING";
    public final static String  RET_DATATYPE_TABLE = "TABLE";

    public boolean isRetTable(){
        return RET_KEY_TABLE.equalsIgnoreCase(retKey);
    }

    // tag_function
    int functionId;
    //  标签名称
    String tagName;
    // 中文表名
    String tableName;
    //表名
    String tableId;
    // 显示字段名称
    String fieldDisplayName;
    //   显示字段标识
    String fieldDisplayId;
    // 条件字段名称
    String fieldFilterName;
    // 条件字段标识
    String fieldFilterId;
    // 返回关键字
    String retKey;
    // 返回类型
    String retDatatype;

    String fieldFilterSign;


    int refDocType;

    Boolean bTagTable;

    // 例子
    String demo;
    // 测试结果
    Boolean testOk;
    //测试时间
    Date testTime;

    public String getFieldFilterSign() {
        return fieldFilterSign;
    }

    public void setFieldFilterSign(String fieldFilterSign) {
        this.fieldFilterSign = fieldFilterSign;
    }


    public int getRefDocType() {
        return refDocType;
    }

    public void setRefDocType(int refDocType) {
        this.refDocType = refDocType;
    }


    public Boolean getbTagTable() {
        return bTagTable;
    }

    public void setbTagTable(Boolean bTagTable) {
        this.bTagTable = bTagTable;
    }
    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getFieldDisplayName() {
        return fieldDisplayName;
    }

    public void setFieldDisplayName(String fieldDisplayName) {
        this.fieldDisplayName = fieldDisplayName;
    }

    public String getFieldDisplayId() {
        return fieldDisplayId;
    }

    public void setFieldDisplayId(String fieldDisplayId) {
        this.fieldDisplayId = fieldDisplayId;
    }

    public String getFieldFilterName() {
        return fieldFilterName;
    }

    public void setFieldFilterName(String fieldFilterName) {
        this.fieldFilterName = fieldFilterName;
    }

    public String getFieldFilterId() {
        return fieldFilterId;
    }

    public void setFieldFilterId(String fieldFilterId) {
        this.fieldFilterId = fieldFilterId;
    }

    public String getRetKey() {
        return retKey;
    }

    public void setRetKey(String retKey) {
        this.retKey = retKey;
    }

    public String getRetDatatype() {
        return retDatatype;
    }

    public void setRetDatatype(String retDatatype) {
        this.retDatatype = retDatatype;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public Boolean getTestOk() {
        return testOk;
    }

    public void setTestOk(Boolean testOk) {
        this.testOk = testOk;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }


}
