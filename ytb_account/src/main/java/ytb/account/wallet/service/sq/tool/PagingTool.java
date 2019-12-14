package ytb.account.wallet.service.sq.tool;

import ytb.manager.charges.pojo.Paging;
import ytb.common.RestMessage.MsgRequest;

public class PagingTool {
    /***
     * 分页
     * */
    public static Paging getPaging(MsgRequest req) {
        Paging paging = null;
        if (
                req.msgBody.getInteger("currPage") != null &
                        req.msgBody.getInteger("pageSize") != null &
                        req.msgBody.getString("toOrder") != null &
                        req.msgBody.getString("orderBy") != null
        ) {
            paging = new ytb.manager.charges.pojo.Paging();
            paging.setCurrPage(req.msgBody.getInteger("currPage"));
            paging.setPageSize(req.msgBody.getInteger("pageSize"));
            paging.setToOrder(req.msgBody.getString("toOrder"));
            paging.setOrderBy(req.msgBody.getString("orderBy"));
        }
        return paging;
    }
}
