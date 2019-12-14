package ytb.project.model.projectFolderView.projectTemplate;

import ytb.project.service.template.VersionRule;
import ytb.common.context.model.Ytb_Model;

public class TitleInfo extends Ytb_Model {

    byte status;
    String projectName;
    String title;
    String taskName;

    public StringBuilder buildTitle() {
        return VersionRule.buildFirstTemplateTitle(this);
}

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }


}
//
//    public StringBuilder buildTitle(Dict_TemplateModel model) {
//        title = model.getTitle();
//        return VersionRule.buildFirstTemplateTitle(status, title, projectName, taskName);
//    }
//
//    public StringBuilder buildTitle(ProjectTemplateModel model) {
//        title=model.getTitle();
//        return VersionRule.buildFirstTemplateTitle(status, title, projectName, taskName);
//    }
