package ytb.manager.webpagemng.utils;

/**
 * @author lxz 2018/12/22 16:03
 */
public class PageHelper {

    private static PageQuery pageQuery = new PageQuery();

    public static PageQuery getPageQuery(int pageNo, int limit) {
        pageQuery.clear();
        pageQuery.setPageNo(pageNo);
        pageQuery.setLimit(limit);
        pageQuery.setOffset((pageNo - 1) * limit);
        return pageQuery;
    }

}
