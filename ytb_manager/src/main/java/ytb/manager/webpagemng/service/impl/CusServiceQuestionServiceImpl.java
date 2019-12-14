package ytb.manager.webpagemng.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.webpagemng.dao.CusServiceQuestionMapper;
import ytb.manager.webpagemng.model.CusServiceQuestion;
import ytb.manager.webpagemng.service.CusHotDoService;
import ytb.manager.webpagemng.service.CusServiceQuestionService;
import ytb.manager.webpagemng.utils.PageData;
import ytb.manager.webpagemng.utils.PageHelper;
import ytb.manager.webpagemng.utils.PageQuery;

import java.util.List;

/**
 * @author lxz 2018/12/22 15:59
 */
public class CusServiceQuestionServiceImpl implements CusServiceQuestionService {

    private SqlSession getSession() {
        return YtbContext.getSqlSessionBuilder().getSession_manager(true);
    }

    @Override
    public void add(CusServiceQuestion cusServiceQuestion) {
        try (SqlSession session = getSession()) {
            CusServiceQuestionMapper mapper = session.getMapper(CusServiceQuestionMapper.class);
            mapper.insert(cusServiceQuestion);
        }
    }

    @Override
    public void removeByQid(int qId) {
        try (SqlSession session = getSession()) {
            CusServiceQuestionMapper mapper = session.getMapper(CusServiceQuestionMapper.class);
            mapper.deleteByQid(qId);
        }
    }

    @Override
    public void modify(CusServiceQuestion cusServiceQuestion) {
        try (SqlSession session = getSession()) {
            CusServiceQuestionMapper mapper = session.getMapper(CusServiceQuestionMapper.class);
            mapper.update(cusServiceQuestion);
        }
    }

    @Override
    public CusServiceQuestion selectOne(CusServiceQuestion cusServiceQuestion) {
        try (SqlSession session = getSession()) {
            CusServiceQuestionMapper mapper = session.getMapper(CusServiceQuestionMapper.class);
            return mapper.selectOne(cusServiceQuestion);
        }
    }

    @Override
    public PageData<CusServiceQuestion> pageQuery(CusServiceQuestion cusServiceQuestion, int pageNo, int limit) {
        PageQuery pageQuery = PageHelper.getPageQuery(pageNo, limit);
        pageQuery.put("q", cusServiceQuestion);
        try (SqlSession session = getSession()) {
            CusServiceQuestionMapper mapper = session.getMapper(CusServiceQuestionMapper.class);
            List<CusServiceQuestion> list = mapper.selectList(pageQuery);
            int count = mapper.count(pageQuery);
            return new PageData<>(pageNo, limit, count, list);
        }
    }

    @Override
    public PageData<CusServiceQuestion> pageQueryHotSearch(CusServiceQuestion cusServiceQuestion, int pageNo, int limit) {
        if (cusServiceQuestion.getTitle() != null && !"".equals(cusServiceQuestion.getTitle())) {
            new CusHotDoService().updateCountByKeyWord(cusServiceQuestion.getTitle());
        }
        return pageQuery(cusServiceQuestion, pageNo, limit);
    }
}
