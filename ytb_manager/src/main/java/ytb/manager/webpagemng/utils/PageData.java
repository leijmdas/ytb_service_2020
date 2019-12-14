package ytb.manager.webpagemng.utils;

import java.util.List;

/**
 * @author lxz 2018/12/22 15:12
 */
public class PageData<T> {

    private int pageNo;

    private int totalPage;

    private int limit;

    private int totalCount;

    private List<T> list;

    public PageData() {
    }

    public PageData(int pageNo, int totalPage, int limit, int totalCount, List<T> list) {
        this.pageNo = pageNo;
        this.totalPage = totalPage;
        this.limit = limit;
        this.totalCount = totalCount;
        this.list = list;
    }

    public PageData(int pageNo, int limit, int totalCount, List<T> list) {
        this.pageNo = pageNo;
        this.limit = limit;
        this.totalCount = totalCount;
        this.list = list;
        this.totalPage = (int) Math.ceil(totalCount / (limit * 1.0));
    }

    @Override
    public String toString() {
        return "PageData{" +
                "pageNo=" + pageNo +
                ", totalPage=" + totalPage +
                ", limit=" + limit +
                ", totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
