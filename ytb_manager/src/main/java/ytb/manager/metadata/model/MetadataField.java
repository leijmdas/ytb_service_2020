package ytb.manager.metadata.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 */
public class MetadataField implements Serializable {

    private transient static final long serialVersionUID = 1L;
    /**
     * 字段标识号
     */
    private Integer fieldId;

    /**
     * 元数据主表ID
     */
    private Integer metadataId;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段描述
     */
    private String fieldMemo;

    /**
     * 字段类型
     */
    private String fieldType;


    /**
     * 必须填
     */
    private String fieldRemark;

    /**
     * 字段大小长度
     */
    private Integer fieldSize;

    /**
     * 是否显示
     */
    private Boolean fieldVisible;

    /**
     * 显示大小
     */
    private Integer fieldDisplaysize;

    /**
     * 是否只读
     */
    private Boolean fieldReadonly;

    /**
     * 默认值
     */
    private String fieldDefault;

    /**
     * 显示顺序
     */
    private Integer fieldOrder;

    /**
     * 数据来源
     */
    private Integer fieldSrc;

    /**
     * 数据池
     */
    private String refPool;

    /**
     * 关联表名称
     */
    private String refTable;

    /**
     * 关联外表字段
     */
    private String refField;

    /**
     * 关联外表显示字段
     */
    private String refDisplayid;

    /**
     * 关联外表条件
     */
    private String refFilter;

    /**
     * 是否主键
     */
    private Boolean fieldPk;

    /**
     * 自动生成
     */
    private Boolean fieldAuto;

    /**
     * 是否为null
     */
    private Boolean fieldIsnull;

    /**
     * 是否计算字段
     */
    private Boolean fieldIscal;

    private String refObject;

    private String fieldFormat;

    /**
     * 显示控件
     */
    private Integer fieldComponent;

    public Integer getFieldComponent() {
        return fieldComponent;
    }

    public void setFieldComponent(Integer fieldComponent) {
        this.fieldComponent = fieldComponent;
    }

    public String getFieldFormat() {
        return fieldFormat;
    }

    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat;
    }


    public String getFieldRemark() {
        return fieldRemark;
    }

    public void setFieldRemark(String fieldRemark) {
        this.fieldRemark = fieldRemark;
    }

    private String refParameter;

    public String getRefObject() {
        return refObject;
    }

    public void setRefObject(String refObject) {
        this.refObject = refObject;
    }

    public String getRefParameter() {
        return refParameter;
    }

    public void setRefParameter(String refParameter) {
        this.refParameter = refParameter;
    }


    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Integer metadataId) {
        this.metadataId = metadataId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldMemo() {
        return fieldMemo;
    }

    public void setFieldMemo(String fieldMemo) {
        this.fieldMemo = fieldMemo;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }


    public Integer getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(Integer fieldSize) {
        this.fieldSize = fieldSize;
    }

    public Boolean getFieldVisible() {
        return fieldVisible;
    }

    public void setFieldVisible(Boolean fieldVisible) {
        this.fieldVisible = fieldVisible;
    }

    public Integer getFieldDisplaysize() {
        return fieldDisplaysize;
    }

    public void setFieldDisplaysize(Integer fieldDisplaysize) {
        this.fieldDisplaysize = fieldDisplaysize;
    }

    public Boolean getFieldReadonly() {
        return fieldReadonly;
    }

    public void setFieldReadonly(Boolean fieldReadonly) {
        this.fieldReadonly = fieldReadonly;
    }

    public String getFieldDefault() {
        return fieldDefault;
    }

    public void setFieldDefault(String fieldDefault) {
        this.fieldDefault = fieldDefault;
    }

    public Integer getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(Integer fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public Integer getFieldSrc() {
        return fieldSrc;
    }

    public void setFieldSrc(Integer fieldSrc) {
        this.fieldSrc = fieldSrc;
    }

    public String getRefPool() {
        return refPool;
    }

    public void setRefPool(String refPool) {
        this.refPool = refPool;
    }

    public String getRefTable() {
        return refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

    public String getRefField() {
        return refField;
    }

    public void setRefField(String refField) {
        this.refField = refField;
    }

    public String getRefDisplayid() {
        return refDisplayid;
    }

    public void setRefDisplayid(String refDisplayid) {
        this.refDisplayid = refDisplayid;
    }

    public String getRefFilter() {
        return refFilter;
    }

    public void setRefFilter(String refFilter) {
        this.refFilter = refFilter;
    }

    public Boolean getFieldPk() {
        return fieldPk;
    }

    public void setFieldPk(Boolean fieldPk) {
        this.fieldPk = fieldPk;
    }

    public Boolean getFieldAuto() {
        return fieldAuto;
    }

    public void setFieldAuto(Boolean fieldAuto) {
        this.fieldAuto = fieldAuto;
    }

    public Boolean getFieldIsnull() {
        return fieldIsnull;
    }

    public void setFieldIsnull(Boolean fieldIsnull) {
        this.fieldIsnull = fieldIsnull;
    }

    public Boolean getFieldIscal() {
        return fieldIscal;
    }

    public void setFieldIscal(Boolean fieldIscal) {
        this.fieldIscal = fieldIscal;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);


    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}