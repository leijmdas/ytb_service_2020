package ytb.project.model;

import ytb.manager.template.model.Dict_document;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;

public class ProjectDocumentModel extends Ytb_Model {


    //文档ID
    private int documentId;
    //
    private  int documentIdV;
    //文档名称
    private String name;
    //文档类型
    private int docType;
    //保存方式
    private int saveMode;
    //图片类型
    private String picType;
    //路径
    private String docPath;
    //文档内容
    private byte[]  document;
    //文档大小
    private int docSize;

    public ProjectDocumentModel(){

    }

    public ProjectDocumentModel(Dict_document dict_document) {
        //源文件设置添加
        if (dict_document == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "dict_documen模板文档为空");
        }
        setDocument(dict_document.getDocument());
        setDocType(dict_document.getDocType());
        setSaveMode(dict_document.getSaveMode());
        setName(dict_document.getName());

    }


    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getName(long userId) {

        return userId + "_" + documentId + "_" + name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public int getSaveMode() {
        return saveMode;
    }

    public void setSaveMode(int saveMode) {
        this.saveMode = saveMode;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public int getDocSize() {
        return docSize;
    }
    public void setDocSize(int docSize) {
        this.docSize = docSize;
    }

    public int getDocumentIdV() {
        return documentIdV;
    }

    public void setDocumentIdV(int documentIdV) {
        this.documentIdV = documentIdV;
    }
}
