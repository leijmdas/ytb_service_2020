package ytb.manager.charges.pojo;

import java.util.Date;

public class Paging {

    private Integer pageSize;

    private Integer currPage;

    private String orderBy;

    private String toOrder;

    private Date startTime;

    private Date endTime;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getToOrder() {
        return toOrder;
    }

    public void setToOrder(String toOrder) {
        this.toOrder = toOrder;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
