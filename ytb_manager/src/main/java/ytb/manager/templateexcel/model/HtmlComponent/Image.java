package ytb.manager.templateexcel.model.HtmlComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * LXZ
 */
public class Image extends Metadata {

    private int curIndex = 0;//已上传张数=curIndex-1

    private int limit = 0;

    private String desc = "";

    private List<String> documentIdList = new ArrayList<>();

    public Image() {
    }

    public int getCurIndex() {
        return curIndex;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getDocumentIdList() {
        return documentIdList;
    }

    public void setDocumentIdList(List<String> documentIdList) {
        this.documentIdList = documentIdList;
    }
}
