package ytb.manager.template.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbUtils;
import ytb.manager.context.MyBatisUtil;
import ytb.manager.template.dao.TemplateMapper;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.service.TemplateService;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * @author lxz 2019/2/15 14:29
 */
public class TemplateServiceImpl implements TemplateService {

    public List<Dict_TemplateModel> getDocTemplateList(int repositoryId, int workJobId, Integer[] docTypeArr) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            return mapper.getDocTplList(repositoryId, workJobId, docTypeArr);
        }
    }

    public void addTemplate(Dict_TemplateModel templateModel) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            templateModel.setUuid(YtbUtils.getUUID(true));
            mapper.addTemplate(templateModel);
        } catch (Exception e) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }

    public void modifyTemplate(Dict_TemplateModel templateModel) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            mapper.modifyTemplate(templateModel);
        }
    }

    @Override
    public void modifyDocumentIdAlias(Dict_TemplateModel templateModel) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            mapper.modifyDocumentIdAlias(templateModel);
        }

    }

    public void delTemplate(int templateId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            int effectRow = mapper.delTemplate(templateId);
            if (effectRow == 0) {
                throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "templateId=" + templateId);
            }
        }
    }

    @Override
    public Dict_TemplateModel getTemplateBy(int templateId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            TemplateMapper mapper = session.getMapper(TemplateMapper.class);
            return mapper.getTemplate(templateId);
        }
    }
}
