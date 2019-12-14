package ytb.project.view.model;

import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.List;

/**
 * Package: ytb.project.view.model
 * Author: ZCS
 * Date: Created in 2019/4/23 15:36
 */
public class ProjectStopTaskViewResult extends Ytb_ModelSkipNull {

    private List<ViewProjectTemplateUserModel> templateList;//list->templateList

    public List<ViewProjectTemplateUserModel> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<ViewProjectTemplateUserModel> templateList) {
        this.templateList = templateList;
    }
}
