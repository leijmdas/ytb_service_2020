package ytb.manager.metadata.model;

import com.google.gson.Gson;
import ytb.common.context.model.Ytb_Model;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 */
public class MetadataDict extends Ytb_Model implements Serializable {
    /**
     * 元数据标识号
     */
    private Integer metadataId;

    /**
     * 子系统标识号
     */
    private Integer subsysId;

    /**
     * 元数据名称
     */
    private String metadataName;

    /**
     * 元数据别名
     */
    private String metadataAlias;

    /**
     * 元数据描述
     */
    private String metadataMemo;

    /**
     * 关联元数据ID
     */
    private Integer metadataParentid;

    /**
     * 元数据类型
     */
    private Integer metadataType;


    /**
     * 表记录是否缓存
     */
    private Boolean metadataCached;

    /**
     * 归属数据库
     */
    private String metadataDb;

    /**
     * 是否auto创建
     */
    private Boolean metadataAutocreate;

    /**
     * 脚本
     */
    private String metadataStmt;

    /**
     * index
     */
    private String metadataIndex;

    /**
     * 排序方式
     */
    private Integer metadataOrder;

    /**
     * 说明
     */
    private String metadataRemark;

    /**
     * 排序
     */
    private String metadataSortFields;
    private short refSrc;

    private String refObject;
    private String refParam;
    private Boolean metadataReadonly;
    private Boolean metadataAddDel;

    private List<?> field;

    public Boolean getMetadataReadonly() {
        return metadataReadonly;
    }

    public void setMetadataReadonly(Boolean metadataReadonly) {
        this.metadataReadonly = metadataReadonly;
    }

    public Boolean getMetadataAddDel() {
        return metadataAddDel;
    }

    public void setMetadataAddDel(Boolean metadataAddDel) {
        this.metadataAddDel = metadataAddDel;
    }

    public Boolean getMetadataCached() {
        return metadataCached;
    }

    public void setMetadataCached(Boolean metadataCached) {
        this.metadataCached = metadataCached;
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

    public String getMetadataSortFields() {
        return metadataSortFields;
    }

    public void setMetadataSortFields(String metadataSortFields) {
        this.metadataSortFields = metadataSortFields;
    }



    public List<?> getField() {
        return field;
    }

    public void setField(List<?> field) {
        this.field = field;
    }

    private static final long serialVersionUID = 1L;

    public Integer getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Integer metadataId) {
        this.metadataId = metadataId;
    }

    public Integer getSubsysId() {
        return subsysId;
    }

    public void setSubsysId(Integer subsysId) {
        this.subsysId = subsysId;
    }

    public String getMetadataName() {
        return metadataName;
    }

    public void setMetadataName(String metadataName) {
        this.metadataName = metadataName;
    }

    public String getMetadataAlias() {
        return metadataAlias;
    }

    public void setMetadataAlias(String metadataAlias) {
        this.metadataAlias = metadataAlias;
    }

    public String getMetadataMemo() {
        return metadataMemo;
    }

    public void setMetadataMemo(String metadataMemo) {
        this.metadataMemo = metadataMemo;
    }

    public Integer getMetadataParentid() {
        return metadataParentid;
    }

    public void setMetadataParentid(Integer metadataParentid) {
        this.metadataParentid = metadataParentid;
    }

    public Integer getMetadataType() {
        return metadataType;
    }

    public void setMetadataType(Integer metadataType) {
        this.metadataType = metadataType;
    }



    public String getMetadataDb() {
        return metadataDb;
    }

    public void setMetadataDb(String metadataDb) {
        this.metadataDb = metadataDb;
    }

    public Boolean getMetadataAutocreate() {
        return metadataAutocreate;
    }

    public void setMetadataAutocreate(Boolean metadataAutocreate) {
        this.metadataAutocreate = metadataAutocreate;
    }

    public String getMetadataStmt() {
        return metadataStmt;
    }

    public void setMetadataStmt(String metadataStmt) {
        this.metadataStmt = metadataStmt;
    }

    public String getMetadataIndex() {
        return metadataIndex;
    }

    public void setMetadataIndex(String metadataIndex) {
        this.metadataIndex = metadataIndex;
    }

    public Integer getMetadataOrder() {
        return metadataOrder;
    }

    public void setMetadataOrder(Integer metadataOrder) {
        this.metadataOrder = metadataOrder;
    }

    public String getMetadataRemark() {
        return metadataRemark;
    }

    public void setMetadataRemark(String metadataRemark) {
        this.metadataRemark = metadataRemark;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }



}