package ytb.manager.metadata.model;

/**
 *  Created by ZYB on 2018/9/13 14:55
 */
public class ZTreeModelDto {
    private int id;
    private String name;
    private int parentId;

    public ZTreeModelDto(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public ZTreeModelDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
