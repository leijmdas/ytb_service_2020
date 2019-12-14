package ytb.project.dao;


import ytb.project.model.ProjectTemplateVModel;
import ytb.project.model.ProjectTemplateModel;

import java.util.List;

public interface projectTemplateVMapper {

    void addProjectTemplate(ProjectTemplateModel projectDocList);

    List<ProjectTemplateVModel> selectHistoryDoc(int docListIdV);

}
