package ytb.manager.pfUser.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dict_Tag {
    int tagId;
    int parentId;
    String tagName;
    int tagType;
    int createBy;

    List<Dict_Tag> children=new ArrayList<>();

    public List<Dict_Tag> getChildren() {
        return children;
    }

    public void setChildren(List<Dict_Tag> children) {
        this.children = children;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
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

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
