package ytb.manager.template.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Service;

import ytb.manager.context.MyBatisUtil;
import ytb.manager.template.dao.DocumentMapper;
import ytb.manager.template.dao.ProjectTypeMapper;
import ytb.manager.template.dao.TemplateMapper;
import ytb.manager.template.model.*;

import ytb.manager.template.service.ProjectTypeService;
import ytb.common.context.model.YtbError;

import java.util.*;


@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    WorkJobTypeServiceImpl workJobTypeService = new WorkJobTypeServiceImpl();

    @Override
    public List<Dict_ProjectTypeModel> getProjectTypeList() {
        SqlSession session = MyBatisUtil.getSession();
        try {
            ProjectTypeMapper PMapper = session.getMapper(ProjectTypeMapper.class);
            return PMapper.getProjectTypeList();
        } finally {
            session.close();
        }
    }

    @Override
    public Dict_ProjectTypeModel getProjectType(int projectTypeId) {

        SqlSession session = MyBatisUtil.getSession();
        try {
            ProjectTypeMapper projectTypeMapper = session.getMapper(ProjectTypeMapper.class);
            return projectTypeMapper.getProjectType(projectTypeId);
        } finally {
            session.close();
        }
    }

    @Override
    public void addProjectType(Dict_ProjectTypeModel projectTypeModel) {
        projectTypeModel.setCreateTime(new Date());
        projectTypeModel.setDepth(projectTypeModel.getParentId() == 0 ? 0 : 1);
        try (SqlSession session = MyBatisUtil.getSession()) {
            ProjectTypeMapper mapper = session.getMapper(ProjectTypeMapper.class);
            mapper.addProjectType(projectTypeModel);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public void delProjectType(int projectTypeId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            ProjectTypeMapper mapper = session.getMapper(ProjectTypeMapper.class);
            List<Dict_ProjectTypeModel> children = mapper.getProjectTypeByParent(projectTypeId);
            Dict_ProjectTypeModel p = mapper.getProjectType(projectTypeId);
            if (children.size() > 0) {//有子类不能删
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "有子类不能删");
            }
            if (p.getState() != 0) {//只有草稿状态才能删
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "只有草稿状态才能删");
            }
            mapper.delProjectType(projectTypeId);
        }
    }


    @Override
    public void modifyProjectType(Dict_ProjectTypeModel projectTypeModel) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            ProjectTypeMapper mapper = session.getMapper(ProjectTypeMapper.class);
            Dict_ProjectTypeModel p = mapper.getProjectType(projectTypeModel.getProjectTypeId());
            if (p.getState() != 0) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "只有草稿状态才能修改");
            } else {//草稿状态
                projectTypeModel.setParentId(p.getParentId());
                projectTypeModel.setDepth(p.getDepth());
                mapper.modifyProjectType(projectTypeModel);
            }
        }
    }

    @Override
    public void modifySateById(int projectTypeId,int state) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            ProjectTypeMapper mapper = session.getMapper(ProjectTypeMapper.class);
            mapper.modifySateById(projectTypeId,state);
        }
    }

    @Override
    public List getTemplateList(Dict_TemplateModel t) {

        SqlSession session = MyBatisUtil.getSession();
        List<Dict_TemplateModel> list = null;
        try {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            list = TMapper.getDocTemplateList(t);
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public List<Dict_TemplateModel> getDocTemplateListinfo(Dict_TemplateModel t) {

        SqlSession session = MyBatisUtil.getSession();
        List<Dict_TemplateModel> list = null;
        try {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            list = TMapper.getDocTemplateListinfo(t);
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Dict_TemplateModel getTemplate(int templateId) {

        SqlSession session = MyBatisUtil.getSession();
        Dict_TemplateModel template = null;

        try {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            template = TMapper.getTemplate(templateId);
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
            t.setCreateTime(new Date());
            TMapper.addTemplate(t);
            session.commit();
        } finally {
            session.close();
        }

    }

    @Override
    public void modifyTemplate(Dict_TemplateModel t) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            mapper.modifyTemplate(t);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public void delTemplate(int templateId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            mapper.delTemplate(templateId);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    public JSONObject getProjTypeDetailsInfo(int projectTypeId) {

        SqlSession session = MyBatisUtil.getSession();
        ProjectTypeMapper PMapper = session.getMapper(ProjectTypeMapper.class);
        Dict_ProjectTypeModel p = PMapper.getProjectType(projectTypeId);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(p);

        if (p.getParentId() != 0) {
            Dict_ProjectTypeModel p1 = PMapper.getProjectType(p.getParentId());
            String parentName = p1.getTitle();
            jsonObject.put("parentName", parentName);

        }
        session.close();
        List<Dict_WorkJobTypeModel> workJobTypeList = workJobTypeService.getWorkJobTypeList();

        JSONArray json = new JSONArray();

        for (Dict_WorkJobTypeModel workjobtype : workJobTypeList) {

            json.add(workjobtype);
        }
        jsonObject.put("list", json);
        session.close();

        return jsonObject;
    }

    public List<Dict_ProjectTypeModel> getProjTypeDetailsInfoList() {
        try (SqlSession session = MyBatisUtil.getSession()) {
            ProjectTypeMapper mapper = session.getMapper(ProjectTypeMapper.class);
            List<Dict_ProjectTypeModel> list = new ArrayList<>();
            List<Dict_ProjectTypeModel> parentList = mapper.getProjectTypeByParent(0);
            for (Dict_ProjectTypeModel p : parentList) {
                list.add(p);
                List<Dict_ProjectTypeModel> childList = mapper.getProjectTypeByParent(p.getProjectTypeId());
                if (childList.size() > 0) {
                    list.addAll(childList);
                }
            }
            return list;
        } catch (Exception ex) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, ex.getMessage());
        }

    }

    public void modifyTemplateContentsNew(Dict_TemplateModel tm) {
        try (SqlSession session = MyBatisUtil.getSession();) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            mapper.modifyTemplateContentsNew(tm);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    //添加文档内容源
    @Override
    public void addMngrReDocument(Dict_document mrd) {
        SqlSession session = MyBatisUtil.getSession();

        try {
            DocumentMapper mrdMapper = session.getMapper(DocumentMapper.class);
            mrdMapper.addMngrReDocument(mrd);
            session.commit();
        } finally {
            session.close();
        }

    }

    //修改文档内容源
    @Override
    public void modifyDocument(Dict_document doc) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            DocumentMapper docMapper = session.getMapper(DocumentMapper.class);
            docMapper.modifyDocument(doc);
            session.commit();
        } finally {
            session.close();
        }

    }

    //文档模板修改文档内容源ID
    @Override
    public void modifyTemplateDocument(Dict_TemplateModel t) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            TemplateMapper TMapper = session.getMapper(TemplateMapper.class);
            TMapper.modifyTemplateDocument(t);
            session.commit();
        } finally {
            session.close();
        }
    }

    //获取原始文档资源
    @Override
    public Dict_document getMngrReDocument(int documentId) {

        SqlSession session = MyBatisUtil.getSession();

        try {
            DocumentMapper mrdMapper = session.getMapper(DocumentMapper.class);
            return mrdMapper.getMngrReDocument(documentId);

        } finally {
            session.close();
        }
    }

    //通过模板ID获取资源文档对象
    public Dict_document getMngrReDocumentByT(int templateId) {
        SqlSession session = MyBatisUtil.getSession();
        Dict_document mrd = null;
        try {
            DocumentMapper mrdMapper = session.getMapper(DocumentMapper.class);
            mrd = mrdMapper.getMngrReDocumentByT(templateId);
        } finally {
            session.close();
        }
        return mrd;
    }

    @Override
    public List<Dict_ProjectTypeModel> getProjectTypeByPar(int ParentId) {
        SqlSession session = MyBatisUtil.getSession();
        List<Dict_ProjectTypeModel> list = null;
        try {
            ProjectTypeMapper PMapper = session.getMapper(ProjectTypeMapper.class);
            list = PMapper.getProjectTypeByParent(ParentId);
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void delMngrReDocument(int documentId) {
        SqlSession session = MyBatisUtil.getSession();
        Dict_document mrd = null;
        try {
            DocumentMapper mrdMapper = session.getMapper(DocumentMapper.class);
            mrdMapper.delMngrReDocument(documentId);
        } finally {
            session.close();
        }
    }

    public List<Dict_ProjectTypeModel> selectTree() {
        List<Dict_ProjectTypeModel> lst = getProjectTypeList();
        List<Dict_ProjectTypeModel> retlst = findSubLst(lst, 0);
        for (Dict_ProjectTypeModel dpt : retlst) {
            List<Dict_ProjectTypeModel> subLst = findSubLst(lst, dpt.getProjectTypeId());
            dpt.setChildren(subLst);
        }
        return retlst;
    }

    List<Dict_ProjectTypeModel> findSubLst(List<Dict_ProjectTypeModel> lst, int pid) {
        List<Dict_ProjectTypeModel> retlst = new ArrayList<>();
        for (Dict_ProjectTypeModel dpt : lst) {
            if (dpt.getParentId() == pid) {
                retlst.add(dpt);
            }
        }
        return retlst;
    }

}
