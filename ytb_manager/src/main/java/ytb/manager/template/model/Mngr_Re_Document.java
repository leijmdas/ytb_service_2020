package ytb.manager.template.model;

public class Mngr_Re_Document {
    //文档ID
    private int documentId;
    //文档内容
    private String name;
    //文档内容
    private byte[] document;
    //版本号
    private int rev;

    private int docType;
    private int saveMode;
    private String picType;
    private String docPath;

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

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }
}
