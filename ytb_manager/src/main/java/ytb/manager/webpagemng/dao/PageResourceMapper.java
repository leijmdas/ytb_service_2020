package ytb.manager.webpagemng.dao;

import ytb.manager.webpagemng.model.PageResource;

import java.util.List;
import java.util.Map;

/**
 * @author lxz 2018/12/15 16:40
 */
public interface PageResourceMapper {

    int insert(PageResource pageResource);

    int deleteByResId(int resId);

    int update(PageResource pageResource);

    PageResource selectOne(PageResource pageResource);

    List<PageResource> selectList(Map<String, Object> map);

    int count(Map<String, Object> map);

}
