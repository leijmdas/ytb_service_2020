package ytb.manager.templateexcel.model.HtmlComponent;

/**
 * LXZ
 */
public class File extends Metadata {

    private String desc;

    private int documentId;

    private String filename;

    public File() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
