package ytb.manager.templateexcel.model.tag;

import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;

import java.util.ArrayList;
import java.util.List;

/**
 * LXZ
 */
public class Tag extends TagValue {

    private String tagType;

    private String key;

    private List<TagTitle> tagTitle = new ArrayList<>();

    private List<TagValue> value = new ArrayList<>();

    private int depth;

    //tag最大行数
    private int rowLimit = 1;

    //tag已添加的行数
    private int rows = 0;

    //内容起始位置
    private int contentStart;

    //引用数据
    private String refString;

    //是否可见 显示关联会设置该值
    private boolean show = true;

    public Tag() {
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<TagTitle> getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(List<TagTitle> tagTitle) {
        this.tagTitle = tagTitle;
    }

    public List<TagValue> getValue() {
        return value;
    }

    public void setValue(List<TagValue> value) {
        this.value = value;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getContentStart() {
        return contentStart;
    }

    public void setContentStart(int contentStart) {
        this.contentStart = contentStart;
    }

    public String getRefString() {
        return refString;
    }

    public void setRefString(String refString) {
        this.refString = refString;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

}
