package ytb.manager.metadata.model;



/**
 * 元数据子表实体类
 * Package: ytb.manager.metadata.model
 * Author: XZW
 * Date: Created in 2018/8/23 16:00
 */
public class Sys_MetaDataFieldModel {

    //字段标识号
    private int fieldId;
    //元数据主表ID
    private int metaDataId;
    //字段名称
    private String fieldName;
    //字段描述
    private String fieldMemo;
    //字段类型
    private String fieldType;
    //必须填
    private int fieldIsMust;
    //字段大小长度
    private int fieldSize;
    //是否显示
    private int fieldVisible;
    //显示大小
    private int fieldDisplaySize;
    //是否只读
    private Boolean fieldReadOnly;
    //默认值
    private String fieldDefault;
    //显示顺序
    private int fieldOrder;
    //数据来源
    private int fieldSrc;
    //数据池
    private int refPool;
    //关联表名称
    private int refTable;
    //关联外表字段
    private int refField;
    //关联外表显示字段
    private int refDisplayId;
    //关联外表条件
    private int refFilter;
    //是否主键
    private int fieldPK;
    //自动生成
    private int fieldAuto;
    //是否主键
    private int refObject;
    //自动生成
    private int refParameter;

    public int getRefObject() {
        return refObject;
    }

    public void setRefObject(int refObject) {
        this.refObject = refObject;
    }

    public int getRefParameter() {
        return refParameter;
    }

    public void setRefParameter(int refParameter) {
        this.refParameter = refParameter;
    }


    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public void setMetaDataId(int metaDataId) {
        this.metaDataId = metaDataId;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldMemo(String fieldMemo) {
        this.fieldMemo = fieldMemo;
    }



    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public void setFieldVisible(int fieldVisible) {
        this.fieldVisible = fieldVisible;
    }

    public void setFieldDisplaySize(int fieldDisplaySize) {
        this.fieldDisplaySize = fieldDisplaySize;
    }

    public void setFieldReadOnly(Boolean fieldReadOnly) {
        this.fieldReadOnly = fieldReadOnly;
    }

    public void setFieldDefault(String fieldDefault) {
        this.fieldDefault = fieldDefault;
    }

    public void setFieldOrder(int fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public void setFieldSrc(int fieldSrc) {
        this.fieldSrc = fieldSrc;
    }

    public void setRefTable(int refTable) {
        this.refTable = refTable;
    }

    public void setRefField(int refField) {
        this.refField = refField;
    }

    public void setRefDisplayId(int refDisplayId) {
        this.refDisplayId = refDisplayId;
    }

    public void setFieldPK(int fieldPK) {
        this.fieldPK = fieldPK;
    }

    public void setFieldAuto(int fieldAuto) {
        this.fieldAuto = fieldAuto;
    }

    public void setFieldIsMust(int fieldIsMust) {
        this.fieldIsMust = fieldIsMust;
    }

    public void setRefPool(int refPool) {
        this.refPool = refPool;
    }

    public void setRefFilter(int refFilter) {
        this.refFilter = refFilter;
    }

    public int getFieldId() {
        return fieldId;
    }

    public int getMetaDataId() {
        return metaDataId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldMemo() {
        return fieldMemo;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getFieldVisible() {
        return fieldVisible;
    }

    public int getFieldDisplaySize() {
        return fieldDisplaySize;
    }

    public Boolean getFieldReadOnly() {
        return fieldReadOnly;
    }

    public String getFieldDefault() {
        return fieldDefault;
    }

    public int getFieldOrder() {
        return fieldOrder;
    }

    public int getFieldSrc() {
        return fieldSrc;
    }

    public int getRefTable() {
        return refTable;
    }

    public int getRefField() {
        return refField;
    }

    public int getRefDisplayId() {
        return refDisplayId;
    }

    public int getFieldPK() {
        return fieldPK;
    }

    public int getFieldAuto() {
        return fieldAuto;
    }

    public int getFieldIsMust() {
        return fieldIsMust;
    }

    public int getRefPool() {
        return refPool;
    }

    public int getRefFilter() {
        return refFilter;
    }
}
