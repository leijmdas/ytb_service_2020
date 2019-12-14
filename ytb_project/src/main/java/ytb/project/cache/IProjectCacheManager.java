package ytb.project.cache;
 
import ytb.project.service.project.stop.impl.StopType;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_WorkJobModel;
import ytb.manager.template.model.T_Stop_ActionModel;

import java.util.List;

public interface IProjectCacheManager {

      Dict_ProjectTypeModel findDictProjectTypeModel(int projectTypeId);

      //publish
      boolean existsDict_ProjectTypeModel(int projectTypeId);

      List<Dict_ProjectTypeModel> findDictProjectTypeModels(int parentId);

      Dict_TemplateModel findStopTemplateModel(StopType stopType);

      Dict_TemplateModel findChangeTemplateModel(int chngType);

      Dict_TemplateModel findDictTemplateModel(int projectTypeId, int docType);

      List<Dict_TemplateModel> findReqWorkplan(int projectType);

      //查询模板，类型范围内
      Dict_TemplateModel findDictTemplateModel(int projectTypeId, int docTypeStart, int docTypeEnd);


      List<Dict_TemplateModel> findCompositeTemplates(int projectType);

      List<Dict_TemplateModel> getTemplatesByWorkjob(int id);

      Dict_WorkJobModel findDictWorkJobModel(int workJobId);

      T_Stop_ActionModel findT_Stop_Action(int templateId, Byte phaseNow);


}
