package ytb.common.utils.pageutil;

/**
 * Package: ytb.common.utils.pageutil
 * <p>
 * Description： TODO
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2018/10/12 18:24
 */
public class PageHelp {
    static PageUtils pageUtils = new PageUtils();
    static  Query  query = new Query();

    public static PageUtils getPageUtils() {
        return pageUtils;
    }

    public static Query getQuery() {
        return query;
    }
}
