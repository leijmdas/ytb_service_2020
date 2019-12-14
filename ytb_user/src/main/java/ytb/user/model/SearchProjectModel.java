package ytb.user.model;

/**
 * @Author hj
 * @Description //项目类别
 * @Date 2018/11/14
 **/
public class SearchProjectModel {

    //项目类型Id
    private String projectTypeId;
    //父级Id
    private String parentId;
    //项目名称
    private String title;

    public String getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(String projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "SearchProjectModel{" +
                "projectTypeId='" + projectTypeId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

