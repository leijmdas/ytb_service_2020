package ytb.manager.webpagemng.service;

import ytb.manager.webpagemng.model.PageResource;
import ytb.manager.webpagemng.utils.PageData;

import java.util.List;

/**
 * @author lxz 2018/12/15 17:09
 */
public interface PageResourceService {

    void add(PageResource pageResource);

    void removeByResId(int resId);

    void modify(PageResource pageResource);

    PageResource selectOne(PageResource pageResource);

    List<PageResource> selectAll(PageResource pageResource);

    PageData<PageResource> pageQuery(PageResource pageResource, int pageNo, int limit);

}
