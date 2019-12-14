package ytb.user.model;

/**
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2019年2月14日
 */
public class UserImgModel {
    //表主键
    private int documentId = 0;

    //文件名称
    private String name = "";

    //用户Id
    private int userId = 0;

    //文档格式类型
    private int docType = 0;

    //保存方式
    private int saveMode = 1;

    //图片类型
    private String picType ="JPG";

    //路径
    private String docPath = "";

    //文档内容
    private byte[] document;

    public int getDocSize() {
        return docSize;
    }

    public void setDocSize(int docSize) {
        this.docSize = docSize;
    }

    private int docSize = 0;

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }
}
