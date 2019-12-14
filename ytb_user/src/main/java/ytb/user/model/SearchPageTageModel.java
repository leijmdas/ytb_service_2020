package ytb.user.model;

/**
 * @Author hj
 * @Description //搜索页面的标签
 * @Date 2018/10/24
 **/
public class SearchPageTageModel {

    //标签Id
    private String tagId;
    //父级Id
    private String parentId;
    //标签名称
    private String tagName;


    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


    @Override
    public String toString() {
        return "SearchPageTageModel{" +
                "tagId=" + tagId +
                ", parentId=" + parentId +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
