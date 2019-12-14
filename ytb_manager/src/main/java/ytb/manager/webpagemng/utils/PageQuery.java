package ytb.manager.webpagemng.utils;

import java.util.HashMap;

/**
 * @author lxz 2018/12/22 16:04
 */
public class PageQuery extends HashMap<String, Object> {

    private int pageNo;

    private int limit;

    private int offset;

    public PageQuery() {
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
