package ytb.manager.template.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import ytb.manager.charges.model.ServiceFee;
import ytb.manager.charges.rest.server.ServiceFeeServer;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.context.MyBatisUtil;
import ytb.manager.template.dao.DocumentMapper;
import ytb.manager.template.dao.TemplateMapper;
import ytb.manager.template.dao.TemplateRepositoryMapper;
import ytb.manager.template.model.*;
import ytb.manager.template.service.IDocumentService;
import ytb.manager.template.service.TemplateRepositoryService;
import ytb.manager.template.service.WorkJobTypeService;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.List;


@Service
public class TemplateRepositoryServiceImpl extends WorkJobTypeServiceImpl implements TemplateRepositoryService, WorkJobTypeService {

    WorkJobTypeServiceImpl workJobTypeService = this;

    //获取模板仓库列表
    public List<Template_Repository> getTemplateRepositoryList() {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateRepositoryMapper mapper = session.getMapper(TemplateRepositoryMapper.class);
            return mapper.getTemplateRepositoryList();
        }
    }

    //获取已经发布模板仓库列表
    @Override
    public List<Template_Repository> getPubTemplateRepositoryList(){

        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateRepositoryMapper mapper = session.getMapper(TemplateRepositoryMapper.class);
            return mapper.getPubTemplateRepositoryList();
        }
    }


    @Override
    public String getRepositoryData() {
        List<Template_Repository> repositoryList = getTemplateRepositoryList();
        List<Dict_WorkJobTypeModel> workJobTypeList = workJobTypeService.getWorkJobTypeList();

        ServiceFee serviceFee = new ServiceFee();
        List<ServiceFee> serviceFeeList = new ServiceFeeServer().getServiceFee(serviceFee);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("repositoryList", repositoryList);
        jsonObject.put("workJobTypeList", workJobTypeList);
        jsonObject.put("serviceFeeList", serviceFeeList);
        return jsonObject.toJSONString();
    }

    @Override
    public void addTemplateRepository(Template_Repository tr) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateRepositoryMapper trMapper = session.getMapper(TemplateRepositoryMapper.class);
            trMapper.addTemplateRepository(tr);
        }
    }

    @Override
    public void delTemplateRepository(int repositoryId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateRepositoryMapper trMapper = session.getMapper(TemplateRepositoryMapper.class);
            trMapper.delTemplateRepository(repositoryId);
        }
    }

    @Override
    public void modifyTemplateRepository(Template_Repository tr) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateRepositoryMapper trMapper = session.getMapper(TemplateRepositoryMapper.class);
            trMapper.modifyTemplateRepository(tr);
        }
    }

    public List<Dict_TemplateModel> getDocTemplateList(int repositoryId, int docType) {

        try (SqlSession session = MyBatisUtil.getSession()) {

            Dict_TemplateModel dict_templateModel = new Dict_TemplateModel();
            dict_templateModel.setWorkJobId(0);
            dict_templateModel.setRepositoryId(repositoryId);
            dict_templateModel.setDocType(docType);
            TemplateMapper templateMapper = session.getMapper(TemplateMapper.class);
            return templateMapper.getDocTemplateList(dict_templateModel);
        }
    }


    public void modifyTemplateState(Dict_TemplateModel t) {

    }


    //获取文档模板详细信息
    public Dict_TemplateModel getDocTemplateDetails(int templateId) {

        return  ManagerSrvContext.getInst().getDocumentService().getTemplate(templateId);
    }


    //获取原始文档资源
    @Override
    public JSONObject getMngrReDocument(int documentId) {

        try (SqlSession session = MyBatisUtil.getSession()) {
            DocumentMapper mrdMapper = session.getMapper(DocumentMapper.class);
            Dict_document mrd = mrdMapper.getMngrReDocument(documentId);
            String s = new String(mrd.getDocument(), "UTF-8");
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(mrd);
            jsonObject.fluentPut("document", s);
            return jsonObject;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR,"UnsupportedEncodingException");
        }
    }

    //获取原始文档资源
    public Dict_document getDocument(int documentId) {

        try (SqlSession session = MyBatisUtil.getSession()){
            DocumentMapper documentMapper = session.getMapper(DocumentMapper.class);
            return documentMapper.getMngrReDocument(documentId);

        }

    }

    @Override
    public Dict_TemplateModel getDocTemplateDetails(int templateId, int workJobId, int repositoryId) {
        IDocumentService ds = ManagerSrvContext.getInst().getDocumentService();
        Dict_TemplateModel template = ds.getTemplate(templateId);

        int documentId = template.getDocumentId();
        template.setDocumentModel(ds.getMngrReDocument(documentId));
        if (workJobId > 0) {
            Dict_WorkJobModel workJob = workJobTypeService.getWorkJob(workJobId);
            template.setWorkJobName(workJob.getTitle());
        } else if (repositoryId > 0) {
            Template_Repository templateRepository = getTemplateRepository(repositoryId);
            template.setRepositoryNo(templateRepository.getNo());
            template.setRepositoryName(templateRepository.getName());
        }

        return template;
    }

    //获取需求文档模板
    public Dict_TemplateModel getRequirements(int projectTypeId, int docType) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            return TMapper.getRequirements(projectTypeId, docType);

        }

    }


    public Template_Repository getTemplateRepository(int repositoryId) {

         try (  SqlSession session = MyBatisUtil.getSession()         ){
            TemplateRepositoryMapper repMapper = session.getMapper(TemplateRepositoryMapper.class);
            return repMapper.getTemplateRepository(repositoryId);

        }
    }

    public Dict_TemplateModel getChangeTemplate(int docType, int subType) {

        try(SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper templateMapper = session.getMapper(TemplateMapper.class);
            return templateMapper.getChangeTemplate(docType, subType);
        }

    }

    @Override
    public List<Dict_TemplateModel> getDocTemplateListinfo(Dict_TemplateModel dict_templateModel) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            return mapper.getDocTemplateListinfo(dict_templateModel);
        }
    }

    //检查模板文档内容
    @Override
    public void checkTemplates(int repositoryId, int workJobTypeId) {
        super.checkTemplates(repositoryId, workJobTypeId);
        //ytb.check req workplan 存在
        boolean existReq = false;
        boolean existWorkplan = false;
        for (Dict_TemplateModel templateModel : getTemplates(repositoryId, workJobTypeId)) {
            if (templateModel.isTemplateReq()) {
                existReq = true;
            }
            if (templateModel.isTemplateWorkplan()) {
                existWorkplan = true;
            }
        }
        if (!existReq) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "需求说明书模板不存在！");
        }
        if (!existWorkplan) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "工作计划书模板不存在！");
        }
    }

    @Override
    public void modifyStateById(int repositoryId,int state) {
        checkTemplates(repositoryId,0);
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateRepositoryMapper repositoryMapper = session.getMapper(TemplateRepositoryMapper.class);
            repositoryMapper.modifySateById(repositoryId,state);
        }
    }
}

