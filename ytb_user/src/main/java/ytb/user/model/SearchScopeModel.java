package ytb.user.model;

/**
 * @Author hj
 * @Description //接单范围
 * @Date 2018/10/29
 **/
public class SearchScopeModel {
    //接单范围id
   // private int scopeId;
    //项目类别Id
    private int typeId;
    //项目名称
    private String projectName;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    @Override
    public String toString() {
        return "SearchScopeModel{" +
                "typeId=" + typeId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
