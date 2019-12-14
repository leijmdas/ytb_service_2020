package ytb.project.context;

import ytb.project.model.ProjectTemplateModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IDocumentFamily {
    int setUpRefAll(ProjectContext pc, List<ProjectTemplateModel> models) throws UnsupportedEncodingException;

    //阶段拷贝建立关系
    int setUpRefAllPhase(ProjectContext pc, List<ProjectTemplateModel> reqPlanModels,
                         List<ProjectTemplateModel> models) throws UnsupportedEncodingException;

    void updateRefPDocTime(int projectId, int pDocumentId);

}
