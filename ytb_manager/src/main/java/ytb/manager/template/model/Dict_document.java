package ytb.manager.template.model;


import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

//doc_type	文档类型格式
//        1 word
//        2 xls
//        3 ppt
//        4 json
//        5 xml
//        6 pic
//        7 rar
//        save_mode	保存方式
//        1	文件，按路径存取
//        2表，按字段存取
public class Dict_document   {

    transient public static int DOC_TYPE_WORD = 1;
    transient public static int DOC_TYPE_XLS = 2;
    transient public static int DOC_TYPE_PPT = 3;
    transient public static int DOC_TYPE_JSON = 4;
    transient public static int DOC_TYPE_XML = 5;
    transient public static int DOC_TYPE_PIC = 6;
    transient public static int DOC_TYPE_RAR = 7;
    transient public static int DOC_TYPE_TXT = 8;

    transient public static int SAVEMODE_PATH = 1;//文件，按路径存取
    transient public static int SAVEMODE_DB = 2;  //表，按字段存取
    transient public static int SAVEMODE_MONGODB = 3;  //表，按字段存取

    //文档ID
    private int documentId;

    public int getDocumentIdV() {
        return documentIdV;
    }

    public void setDocumentIdV(int documentIdV) {
        this.documentIdV = documentIdV;
    }

    private int documentIdV;

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

    public int getDocSize() {
        return docSize;
    }

    public void setDocSize(int docSize) {
        this.docSize = docSize;
    }

    private int docSize;

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

    public String toString(){
        return YtbUtils.toJSONStringPretty(this);
    }
}
