package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

/**
 * Package: ytb.project.model
 * Author: ZCS
 * Date: Created in 2019/3/26 13:49
 * Company: 公司
 */
public class CustomTaskResult extends Ytb_ModelSkipNull {
    private int templateId;

    private int cusTomTaskId;

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    private String projectName;


    public int getCusTomTaskId() {
        return cusTomTaskId;
    }

    public void setCusTomTaskId(int cusTomTaskId) {
        this.cusTomTaskId = cusTomTaskId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
