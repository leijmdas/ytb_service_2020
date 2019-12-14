package ytb.project.cache;

import ytb.common.ehcache.SysCacheService;
import ytb.project.service.project.stop.impl.StopType;
import ytb.manager.template.model.*;
import ytb.common.context.model.YtbError;
import java.util.ArrayList;
import java.util.List;

public final class ProjectCacheManager implements IProjectCacheManager {
    final static Dict_ProjectTypeModel dict_PTM_NONE = new Dict_ProjectTypeModel();
    final static Dict_WorkJobModel dict_WorkJobModel_NONE = new Dict_WorkJobModel();

    public List<Dict_ProjectTypeModel> findDictProjectTypeModels(int parentId) {
        List<Dict_ProjectTypeModel> lst = new ArrayList<>();
        for (Dict_ProjectTypeModel m : findDictProjectTypes()) {
            if (m.getParentId() == parentId && m.isPublish()) {
                lst.add(m);
            }
        }
        return lst;
    }

    public boolean existsDict_ProjectTypeModel(int projectTypeId) {
        for (Dict_ProjectTypeModel m : findDictProjectTypes()) {
            if (m.getProjectTypeId() == projectTypeId && m.isPublish()) {
                return true;
            }
        }
        return false;
    }

    public Dict_TemplateModel findChangeTemplateModel(int chngType) {

        for (Dict_TemplateModel templateModel : findDictTemplateModels()) {
            if (templateModel.getDocType() == TemplateModel.Template_TYPE_chng) {
                if( templateModel.getSubType() == chngType ) {
                    return templateModel;
                }
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "Dict_TemplateModel");
    }

    public Dict_TemplateModel findStopTemplateModel(StopType stopType) {

        for (Dict_TemplateModel templateModel : findDictTemplateModels()) {
            if (templateModel.getDocType() == TemplateModel.Template_TYPE_stop) {
                if(templateModel.getUser() == stopType.getPaPbUser()
                && templateModel.getPhase() == stopType.getPhase()
                && templateModel.getSubType() == stopType.getSubType()) {
                    return templateModel;
                }
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "Dict_TemplateModel");
    }

    public Dict_ProjectTypeModel findDictProjectTypeModel(int projectTypeId) {
        for (Dict_ProjectTypeModel typeModel : findDictProjectTypes()) {
            if (typeModel.getProjectTypeId() == projectTypeId) {
                return typeModel;
            }
        }
        return dict_PTM_NONE;
    }

    public Dict_WorkJobModel findDictWorkJobModel(int workJobId) {
        for (Dict_WorkJobModel workJobModel : findDictWorkJobModels()) {
            if (workJobModel.getWorkJobId() == workJobId) {
                return workJobModel;
            }
        }
        return dict_WorkJobModel_NONE;
    }

    @Override
    public Dict_TemplateModel findDictTemplateModel(int projectTypeId, int docType) {
        Dict_ProjectTypeModel ptm = findDictProjectTypeModel(projectTypeId);

        for (Dict_TemplateModel templateModel : findDictTemplateModels()) {
            if (templateModel.getRepositoryId() == ptm.getRepositoryId()
                    && templateModel.getDocType() == docType) {
                return templateModel;
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "Dict_TemplateModel");
    }


    @Override
    public Dict_TemplateModel findDictTemplateModel(int projectTypeId, int docTypeStart, int docTypeEnd) {
        Dict_ProjectTypeModel ptm = findDictProjectTypeModel(projectTypeId);
        for (Dict_TemplateModel m : findDictTemplateModels()) {
            if (m.getRepositoryId() == ptm.getRepositoryId()
                    && m.getDocType() >= docTypeStart && m.getDocType() <= docTypeEnd) {
                return m;
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "Dict_TemplateModel");
    }

    public List<Dict_TemplateModel> findCompositeTemplates(int projectTypeId) {
        Dict_ProjectTypeModel projectTypeModel = findDictProjectTypeModel(projectTypeId);

        List<Dict_TemplateModel> models = new ArrayList<>();
        for (Dict_TemplateModel model : findDictTemplateModels()) {
            if (model.getRepositoryId() == projectTypeModel.getRepositoryId() && model.isTemplateComposite()) {
                models.add(model);
            }
        }
        return models;
    }

    public List<Dict_TemplateModel> findReqWorkplan(int projectType) {
        Dict_ProjectTypeModel projectTypeModel = findDictProjectTypeModel(projectType);

        List<Dict_TemplateModel> models = new ArrayList<>();
        for (Dict_TemplateModel model : findDictTemplateModels()) {
            if (model.getRepositoryId() == projectTypeModel.getRepositoryId()) {
                if (model.isTemplateReq()) {
                    models.add(model);
                }
                if (model.isTemplateWorkplan()) {
                    models.add(model);
                }
            }
        }

        return models;
    }

    @Override
    public List<Dict_TemplateModel> getTemplatesByWorkjob(int workJobId) {
        List<Dict_TemplateModel> lst=new ArrayList<>();
        for (Dict_TemplateModel m : findDictTemplateModels()) {
            if (m.getWorkJobId()==workJobId) {
                lst.add(m);
            }
        }
        return lst;
    }

    public T_Stop_ActionModel findT_Stop_Action(int templateId, Byte phaseNow) {
        for (T_Stop_ActionModel actionModel : findT_Stop_Actions()) {
            if (actionModel.getTemplateId().equals(templateId)
                    && actionModel.getcPhase().equals(phaseNow)) {
                return actionModel;
            }
        }
        throw new YtbError(YtbError.CODE_DEFINE_ERROR, "终止处理动作未配置！");

    }

    List<Dict_ProjectTypeModel> findDictProjectTypes() {

        return SysCacheService.table2Cache("dict_project_type", Dict_ProjectTypeModel.class);

    }

    List<Dict_TemplateModel> findDictTemplateModels() {

        return SysCacheService.table2Cache("dict_template", Dict_TemplateModel.class);

    }

    List<T_Stop_ActionModel> findT_Stop_Actions() {

        return SysCacheService.table2Cache("t_stop_action", T_Stop_ActionModel.class);

    }

    List<Dict_WorkJobModel> findDictWorkJobModels() {

        return SysCacheService.table2Cache("dict_work_job", Dict_WorkJobModel.class);

    }



}
