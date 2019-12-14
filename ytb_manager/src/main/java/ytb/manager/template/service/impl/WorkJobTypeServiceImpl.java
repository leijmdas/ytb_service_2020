package ytb.manager.template.service.impl;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Service;

import ytb.manager.context.ManagerSrvContext;
import ytb.manager.context.MyBatisUtil;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.template.dao.*;
import ytb.manager.template.model.*;

import ytb.manager.template.service.IDocumentService;
import ytb.manager.template.service.WorkJobTypeService;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class WorkJobTypeServiceImpl implements WorkJobTypeService {


    @Override
    public List<Dict_WorkJobTypeModel> getWorkJobTypeList() {
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobTypeMapper mapper = session.getMapper(WorkJobTypeMapper.class);
            return mapper.getWorkJobTypeList();
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public List<Dict_TemplateModel> getTemplateList(Dict_TemplateModel t) {
      try(  SqlSession session = MyBatisUtil.getSession()) {

          TemplateMapper templateMapper = session.getMapper(TemplateMapper.class);
          return templateMapper.getDocTemplateList(t);
      }
    }

    @Override
    public List<Dict_TemplateModel> getTemplates(int repositoryId,int workJobId) {
        try(  SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper templateMapper = session.getMapper(TemplateMapper.class);
            return templateMapper.getTemplates(repositoryId,workJobId);
        }
    }

    @Override
    public Dict_TemplateModel getTemplate(int workJobTypeId) {
        SqlSession session = MyBatisUtil.getSession();
        Dict_TemplateModel template = null;
        try {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            template = TMapper.getTemplate(workJobTypeId);
        } finally {
            session.close();
        }

        return template;
    }

    @Override
    public void addTemplate(Dict_TemplateModel t) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            TMapper.addTemplate(t);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Dict_TemplateModel> getDocTemplateListinfo(Dict_TemplateModel t) {
        SqlSession session = MyBatisUtil.getSession();

        try {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            return  TMapper.getDocTemplateListinfoByw(t);
        } finally {
            session.close();
        }

    }

    /*@Override
    public void modifyTemplate(Dict_TemplateModel t) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            TMapper.modifyTemplate(t);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }*/

    /*@Override
    public void delTemplate(int templateId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            TMapper.delTemplate(templateId);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }*/

    @Override
    public Dict_WorkJobTypeModel getWorkJobType(int workJobTypeId) {
        SqlSession session = MyBatisUtil.getSession();
        WorkJobTypeMapper WMapper = session.getMapper(WorkJobTypeMapper.class);
        Dict_WorkJobTypeModel w = WMapper.getWorkJobType(workJobTypeId);
        session.close();
        return w;

    }

    @Override
    public void addWorkJobType(Dict_WorkJobTypeModel w) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobTypeMapper mapper = session.getMapper(WorkJobTypeMapper.class);
            mapper.addWorkJobType(w);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }

    }

    @Override
    public void delWorkJobType(int workJobTypeId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobTypeMapper workJobTypeMapper = session.getMapper(WorkJobTypeMapper.class);
            WorkJobMapper workJobMapper = session.getMapper(WorkJobMapper.class);
            List<Dict_WorkJobModel> list = workJobMapper.getWorkJobList(workJobTypeId);
            if (list.size() > 0) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "岗位分类下有岗位,不能删除");
            }
            workJobTypeMapper.delWorkJobType(workJobTypeId);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public void modifyWorkJobType(Dict_WorkJobTypeModel w) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobTypeMapper mapper = session.getMapper(WorkJobTypeMapper.class);
            mapper.modifyWorkJobType(w);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public Dict_WorkJobModel getWorkJob(int workJobId) {
        SqlSession session = MyBatisUtil.getSession();
        Dict_WorkJobModel w = null;
        try {
            WorkJobMapper workJobMapper = session.getMapper(WorkJobMapper.class);
            return workJobMapper.getWorkJob(workJobId);
        } finally {
            session.close();
        }

    }

    @Override
    public List<Dict_WorkJobModel> getWorkJobDetailsInfoList(int WorkJobTypeId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobMapper workJobMapper = session.getMapper(WorkJobMapper.class);
            return workJobMapper.getWorkJobList(WorkJobTypeId);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public void addWorkJob(Dict_WorkJobModel workJob) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobMapper workJobMapper = session.getMapper(WorkJobMapper.class);
            workJobMapper.addWorkJob(workJob);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }

    }

    @Override
    public void delWorkJob(int workJobId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobMapper workJobMapper = session.getMapper(WorkJobMapper.class);
            TaskMapper taskMapper = session.getMapper(TaskMapper.class);
            TemplateMapper tplMapper = session.getMapper(TemplateMapper.class);

            List<Dict_TaskModel> taskList = taskMapper.getTaskDetailsInfoList(workJobId);
            if (taskList != null && taskList.size() > 0) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "岗位下有任务,不能删除");
            }

            List<Dict_TemplateModel> tplList = tplMapper.getTemplateBywor(workJobId);
            if (tplList != null && tplList.size() > 0) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "岗位下有文档,不能删除");
            }

            workJobMapper.delWorkJob(workJobId);
        }
    }

    @Override
    public void modifyWorkJob(Dict_WorkJobModel workJob) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobMapper workJobMapper = session.getMapper(WorkJobMapper.class);
            workJobMapper.modifyWorkJob(workJob);
        }
    }

    @Override
    public Dict_WorkJob_Check getConstList(int workJobId) {
        SqlSession session = MyBatisUtil.getSession();
        WorkJobCheckMapper CMapper = session.getMapper(WorkJobCheckMapper.class);
        Dict_WorkJob_Check c = CMapper.getConstraint(workJobId);
        session.close();
        return c;
    }

    @Override
    public Dict_TaskModel getTask(int TaskId) {
        SqlSession session = MyBatisUtil.getSession();
        TaskMapper taskMapper = session.getMapper(TaskMapper.class);

        Dict_TaskModel task = taskMapper.getTask(TaskId);
        session.close();
        return task;
    }

    @Override
    public List getConstraintList(int WorkJobTypeId) {
        SqlSession session = MyBatisUtil.getSession();
        WorkJobCheckMapper CMapper = session.getMapper(WorkJobCheckMapper.class);
        List list = CMapper.getConstraintList(WorkJobTypeId);
        session.close();
        return list;
    }

    @Override
    public List<Dict_TaskModel> getTaskList(int workJobId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TaskMapper taskMapper = session.getMapper(TaskMapper.class);
            return taskMapper.getTaskDetailsInfoList(workJobId);
        }
    }

    @Override
    public void addConstList(Dict_WorkJob_Check Constraint) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            WorkJobCheckMapper CMapper = session.getMapper(WorkJobCheckMapper.class);
            CMapper.addConstraint(Constraint);
            session.commit();
        } finally {
            session.close();
        }

    }

    @Override
    public void removeWorkJobCheckBy(int checkId) {
        SqlSession session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            WorkJobCheckMapper mapper = session.getMapper(WorkJobCheckMapper.class);
            mapper.delWorkJobCheckBy(checkId);
        } finally {
            session.close();
        }
    }

    @Override
    public void modifyWorkJobCheck(Dict_WorkJob_Check workJobCheck) {
        SqlSession session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            WorkJobCheckMapper mapper = session.getMapper(WorkJobCheckMapper.class);
            mapper.modifyWorkJobCheck(workJobCheck);
        } finally {
            session.close();
        }
    }

    @Override
    public void addTask(Dict_TaskModel task) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TaskMapper taskMapper = session.getMapper(TaskMapper.class);
            taskMapper.addTask(task);
        }
    }

    @Override
    public void delConstList(int workJobId) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            WorkJobCheckMapper CMapper = session.getMapper(WorkJobCheckMapper.class);
            CMapper.delConstraint(workJobId);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Dict_WorkJob_Check> getWorkJobCheckDetailsListBy(int templateId) {
        SqlSession session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        List<Dict_WorkJob_Check> list;
        try {
            WorkJobCheckMapper mapper = session.getMapper(WorkJobCheckMapper.class);
            list = mapper.getWorkJobCheckDetailsListBy(templateId);
            return list;
        } finally {
            session.close();
        }
    }


    @Override
    public Dict_TemplateModel getDocTemplateDetails(int templateId) {
        //DocumentServiceImpl documentServiceImpl = new DocumentServiceImpl();
        IDocumentService ds = ManagerSrvContext.getInst().getDocumentService();

        Dict_TemplateModel tplModel = ds.getTemplate(templateId);
        Dict_WorkJobModel workJob = getWorkJob(tplModel.getWorkJobId());
        if (workJob == null) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "文档模板关联的岗位查询为空");
        }
        tplModel.setWorkJobName(workJob.getTitle());
        int documentId = tplModel.getDocumentId();
        if (documentId > 0) {
            tplModel.setDocumentModel(ds.getMngrReDocument(documentId));
        }
        return tplModel;
    }

    @Override
    public void modifyTemplateNewDoc(int documentId, String document) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            DocumentMapper documentMapper = session.getMapper(DocumentMapper.class);
            Dict_document dictDocument = new Dict_document();
            dictDocument.setDocumentId(documentId);
            dictDocument.setDocument(document.getBytes("UTF-8"));
            documentMapper.modifyTemplateNewDoc(dictDocument);
            session.commit();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delTask(int taskId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TaskMapper taskMapper = session.getMapper(TaskMapper.class);
            taskMapper.delTask(taskId);
        }
    }

    @Override
    public void modifyConstList(Dict_WorkJob_Check task) {

        SqlSession session = MyBatisUtil.getSession();
        try {

            WorkJobCheckMapper CMapper = session.getMapper(WorkJobCheckMapper.class);
            CMapper.modifyConstraint(task);

            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void modifyTask(Dict_TaskModel task) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TaskMapper taskMapper = session.getMapper(TaskMapper.class);
            taskMapper.modifyTask(task);
        }

    }

    @Override
    public List<Dict_TemplateModel> getTemplateListByWorkJobId(int workJobId) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            TemplateMapper tplMapper = session.getMapper(TemplateMapper.class);
            return tplMapper.getTemplateListByWorkJobId(workJobId);

        } finally {
            session.close();
        }
    }

    @Override
    public List<Dict_WorkJobModel> getWorkJobListByWorkJobTypeId(int workJobTypeId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobMapper mapper = session.getMapper(WorkJobMapper.class);
            return mapper.getWorkJobList(workJobTypeId);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public void modifyStateById(int workJobTypeId,int state) {
        checkTemplates(0,workJobTypeId);
        try (SqlSession session = MyBatisUtil.getSession()) {
            WorkJobTypeMapper mapper = session.getMapper(WorkJobTypeMapper.class);
            mapper.modifySateById(workJobTypeId,state);
        }
    }

    //检查模板文档内容
    @Override
    public void checkTemplates(int repositoryId, int workJobTypeId) {

        for (Dict_TemplateModel templateModel : getTemplates(repositoryId, workJobTypeId)) {
            if (templateModel.getDocumentId() == 0) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "模板文档无内容！");
            }
        }

    }
}
