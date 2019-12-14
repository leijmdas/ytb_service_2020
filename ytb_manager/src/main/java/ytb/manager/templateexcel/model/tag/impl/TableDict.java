package ytb.manager.templateexcel.model.tag.impl;

import ytb.manager.templateexcel.model.HtmlComponent.CellData;

import java.util.List;

public class TableDict {

    //数据库名
    private String dbName;

    //表名
    private String tableName;

    //字段列表
    private List<CellData> fields;

    //数据来源
    private short refSrc;

    //引用对象
    private String refObject;
    //引用参数
    private String refParam;

    //增删行
    private boolean metadataAddDel;

    public TableDict() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<CellData> getFields() {
        return fields;
    }

    public void setFields(List<CellData> fields) {
        this.fields = fields;
    }

    public short getRefSrc() {
        return refSrc;
    }

    public void setRefSrc(short refSrc) {
        this.refSrc = refSrc;
    }

    public String getRefObject() {
        return refObject;
    }

    public void setRefObject(String refObject) {
        this.refObject = refObject;
    }

    public String getRefParam() {
        return refParam;
    }

    public void setRefParam(String refParam) {
        this.refParam = refParam;
    }

    public boolean isMetadataAddDel() {
        return metadataAddDel;
    }

    public void setMetadataAddDel(boolean metadataAddDel) {
        this.metadataAddDel = metadataAddDel;
    }
}
