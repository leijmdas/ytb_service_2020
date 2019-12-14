package ytb.project.service.impl.release;

public class ReleaseInfo {
    //项目名称
    String  projectName;
    //项目分类
    int projectTypeId;
    //加工采购需求 模板标识
    int reqTemplateId;
    //自定义任务标识
    int customTaskid;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(int projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public int getReqTemplateId() {
        return reqTemplateId;
    }

    public void setReqTemplateId(int reqTemplateId) {
        this.reqTemplateId = reqTemplateId;
    }

    public int getCustomTaskid() {
        return customTaskid;
    }

    public void setCustomTaskid(int customTaskid) {
        this.customTaskid = customTaskid;
    }

}
