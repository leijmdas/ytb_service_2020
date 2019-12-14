package ytb.project.model.projectFolderView.projectTemplate;

import ytb.common.context.model.Ytb_Model;

//组合模式
public class CopyTemplateInfo extends Ytb_Model {

    TitleInfo titleInfo = new TitleInfo();

    int folderId;

    public String getDocSeq() {
        return docSeq;
    }

    public void setDocSeq(String docSeq) {
        this.docSeq = docSeq;
    }

    String docSeq;
    String docVer;
    int auditor;
    int author;

    public StringBuilder buildTitle() {
        return  titleInfo.buildTitle();
    }

    public TitleInfo getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(TitleInfo titleInfo) {
        this.titleInfo = titleInfo;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getDocVer() {
        return docVer;
    }

    public void setDocVer(String docVer) {
        this.docVer = docVer;
    }

    public int getAuditor() {
        return auditor;
    }

    public void setAuditor(int auditor) {
        this.auditor = auditor;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }


}
