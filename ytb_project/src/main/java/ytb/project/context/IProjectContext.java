package ytb.project.context;

import ytb.project.model.ProjectTemplateModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IProjectContext {


    void check();

    void setUpReqWorkplan(List<ProjectTemplateModel> models);

    void checkReqWorkplan();

    int modifyHeader(UserProjectContext context) throws UnsupportedEncodingException;


}