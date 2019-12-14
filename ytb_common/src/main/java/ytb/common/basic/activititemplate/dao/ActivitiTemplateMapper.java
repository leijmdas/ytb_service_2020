package ytb.common.basic.activititemplate.dao;



import ytb.common.basic.activititemplate.model.Manager_Template_ProcModel;

import java.util.List;

public interface ActivitiTemplateMapper {

    List<Manager_Template_ProcModel> getActivitiTemplateList();

    void addActivitiTemplate(Manager_Template_ProcModel mtp);

    void modifyActivitiTemplate(Manager_Template_ProcModel mtp);

    void removeActivitiTemplate(int proc_id);

    void modifyActivitiStatus(Manager_Template_ProcModel mtp);

    Manager_Template_ProcModel getActivitiTemplate(int proc_id);
    //工作流模板上传文档存到数据库
    void modifyActivitiTemplateContent(Manager_Template_ProcModel mtp);
}
