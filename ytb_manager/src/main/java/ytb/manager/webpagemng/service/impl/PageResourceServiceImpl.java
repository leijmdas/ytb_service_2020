package ytb.manager.webpagemng.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.webpagemng.dao.PageResourceMapper;
import ytb.manager.webpagemng.model.PageResource;
import ytb.manager.webpagemng.service.PageResourceService;
import ytb.manager.webpagemng.utils.PageData;
import ytb.manager.webpagemng.utils.PageHelper;
import ytb.manager.webpagemng.utils.PageQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lxz 2018/12/15 17:10
 */
public class PageResourceServiceImpl implements PageResourceService {

    public static PageResourceService pageResourceService = new PageResourceServiceImpl();

    public PageResourceService getPageResourceService() {
        return pageResourceService;
    }

    private SqlSession getSessionManager(boolean isAutoCommit) {
        return YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(isAutoCommit);
    }

    @Override
    public void add(PageResource pageResource) {
        try (SqlSession sqlSession = getSessionManager(true)) {
            PageResourceMapper mapper = sqlSession.getMapper(PageResourceMapper.class);
            mapper.insert(pageResource);
        }
    }

    @Override
    public void removeByResId(int resId) {
        try (SqlSession sqlSession = getSessionManager(true)) {
            PageResourceMapper mapper = sqlSession.getMapper(PageResourceMapper.class);
            mapper.deleteByResId(resId);
        }
    }

    @Override
    public void modify(PageResource pageResource) {
        try (SqlSession sqlSession = getSessionManager(true)) {
            PageResourceMapper mapper = sqlSession.getMapper(PageResourceMapper.class);
            mapper.update(pageResource);
        }
    }

    @Override
    public PageResource selectOne(PageResource pageResource) {
        try (SqlSession sqlSession = getSessionManager(true)) {
            PageResourceMapper mapper = sqlSession.getMapper(PageResourceMapper.class);
            return mapper.selectOne(pageResource);
        }
    }

    @Override
    public PageData<PageResource> pageQuery(PageResource pageResource, int pageNo, int limit) {
        PageQuery pageQuery = PageHelper.getPageQuery(pageNo, limit);
        pageQuery.put("pr", pageResource);
        try (SqlSession sqlSession = getSessionManager(true)) {
            PageResourceMapper mapper = sqlSession.getMapper(PageResourceMapper.class);
            List<PageResource> pageResources = mapper.selectList(pageQuery);
            int totalCount = mapper.count(pageQuery);
            return new PageData<>(pageNo, limit, totalCount, pageResources);
        }
    }

    @Override
    public List<PageResource> selectAll(PageResource pageResource) {
        Map<String, Object> map = new HashMap<>();
        map.put("pr", pageResource);
        try (SqlSession sqlSession = getSessionManager(true)) {
            PageResourceMapper mapper = sqlSession.getMapper(PageResourceMapper.class);
            return mapper.selectList(map);
        }
    }
}
