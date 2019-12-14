package ytb.manager.metadata.model;

import java.util.List;

/**
 * 元数据主表实体类
 * Package: ytb.manager.metadata.model
 * Author: XZW
 * Date: Created in 2018/8/23 15:51
 */
public class Sys_MetaDataDictModel {

    //元数据标识号
    private int metaDataId;
    //元数据名称
    private String metaDataName;
    //元数据别名
    private String metaDataAlias;
    //元数据描述
    private String metaDataMemo;
    //关联元数据ID
    private int metaDataParentId;
    //元数据类型
    private int metaDataType;
    //归属数据库
    private String metaDataDB;
    //是否auto创建
    private int metaDataAutoCreate;
    //脚本
    private String metaDataStmt;
    //表记录是否缓存
    private int isCached = 1;
    //index
    private String metaDataIndex;

    private List<Sys_MetaDataFieldModel> fields;

    public List<Sys_MetaDataFieldModel> getFields() {
        return fields;
    }

    public void setFields(List<Sys_MetaDataFieldModel> fields) {
        this.fields = fields;
    }

    public void setMetaDataId(int metaDataId) {
        this.metaDataId = metaDataId;
    }

    public void setMetaDataName(String metaDataName) {
        this.metaDataName = metaDataName;
    }

    public void setMetaDataAlias(String metaDataAlias) {
        this.metaDataAlias = metaDataAlias;
    }

    public void setMetaDataMemo(String metaDataMemo) {
        this.metaDataMemo = metaDataMemo;
    }

    public void setMetaDataParentId(int metaDataParentId) {
        this.metaDataParentId = metaDataParentId;
    }

    public void setMetaDatatype(int metaDatatype) {
        this.metaDataType = metaDatatype;
    }

    public void setMetaDataDB(String metaDataDB) {
        this.metaDataDB = metaDataDB;
    }

    public void setMetaDataAutoCreate(int metaDataAutoCreate) {
        this.metaDataAutoCreate = metaDataAutoCreate;
    }

    public void setMetaDataStmt(String metaDataStmt) {
        this.metaDataStmt = metaDataStmt;
    }

    public void setIsCached(int isCached) {
        this.isCached = isCached;
    }

    public void setMetaDataType(int metaDataType) {
        this.metaDataType = metaDataType;
    }

    public void setMetaDataIndex(String metaDataIndex) {
        this.metaDataIndex = metaDataIndex;
    }

    public int getMetaDataId() {
        return metaDataId;
    }

    public String getMetaDataName() {
        return metaDataName;
    }

    public String getMetaDataAlias() {
        return metaDataAlias;
    }

    public String getMetaDataMemo() {
        return metaDataMemo;
    }

    public int getMetaDataParentId() {
        return metaDataParentId;
    }

    public int getMetaDatatype() {
        return metaDataType;
    }

    public String getMetaDataDB() {
        return metaDataDB;
    }

    public int getMetaDataAutoCreate() {
        return metaDataAutoCreate;
    }

    public String getMetaDataStmt() {
        return metaDataStmt;
    }

    public int getIsCached() {
        return isCached;
    }

    public int getMetaDataType() {
        return metaDataType;
    }

    public String getMetaDataIndex() {
        return metaDataIndex;
    }
}
