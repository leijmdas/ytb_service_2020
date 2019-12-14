package ytb.user.model;

/**
 * Package: ytb.user.model
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2018/9/27 13:52
 */
public class VTagSpecialtyModel {
    private int id = 0;
    private int  parentId = 0;
    private String tagName = "";
    private int tagType;

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
