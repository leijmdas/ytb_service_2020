package ytb.account.wallet.rest.impl.sq;

import ytb.manager.charges.pojo.Paging;
import ytb.common.utils.pageutil.PageUtils;
import ytb.account.wallet.model.AccountPfDetail;
import ytb.account.wallet.model.AccountPfDetailExample;
import ytb.account.wallet.service.sq.basics.impl.AccountPfDetailServiceImpl;
import ytb.account.wallet.service.sq.basics.impl.AccountPfInnerServiceImpl;

import java.util.List;

public class SysAccountPfDetailServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public PageUtils select(AccountPfDetail data, Paging paging) {

        try {


            AccountPfDetailExample example = new AccountPfDetailExample();
            AccountPfDetailExample.Criteria criteria = example.createCriteria();

            if (data.getPfDetailId() != null) {
                criteria.andPfDetailIdEqualTo(data.getPfDetailId());
            }

            if (data.getTermId() != null) {
                criteria.andTermIdEqualTo(data.getTermId());
            }

            if (data.getPfInnerId() != null) {
                criteria.andPfInnerIdEqualTo(data.getPfInnerId());
            }

            if (data.getTradeId() != null) {
                criteria.andTradeIdEqualTo(data.getTradeId());
            }

            if (data.getTradeItem() != null) {
                criteria.andTradeItemEqualTo(data.getTradeItem());
            }
            if (paging != null) {

                if (
                        paging.getToOrder().equals("desc") || paging.getToOrder().equals("asc")) {
                    example.setOrderByClause(paging.getToOrder() + "  " + paging.getOrderBy() + " ");
                }

            }
            AccountPfDetailServiceImpl accountPfDetailService = new AccountPfDetailServiceImpl();
            List<AccountPfDetail> list = accountPfDetailService.selectByExample(example);


            PageUtils pageUtil = new PageUtils();
            if (paging != null) {
                pageUtil = new PageUtils(list, list.size(), paging.getPageSize(), paging.getCurrPage());
                Integer firstIndex = (paging.getCurrPage() - 1) * paging.getPageSize();
                //到第几条数据结束
                Integer lastIndex = paging.getPageSize();

                example.setOffset(firstIndex);
                example.setLimit(lastIndex);

                list = accountPfDetailService.selectByExample(example);
            }

            pageUtil.setList(list);

            return pageUtil;


        } catch (Exception e) {
            return null;
        }

    }

//    public Boolean newRecord(AccountPfDetail data ) {
//
//        AccountPfDetailServiceImpl accountPfDetailService = new AccountPfDetailServiceImpl();
//
//
//
//
//        AccountPfInner accountPfInner = new AccountPfInner();
//
//         int a = accountPfDetailService.newRecord(data,accountPfInner);
//
//
//
//        return true;
//    }

    /**
     * 新记录入账
     */
    public Boolean newRecordIn(AccountPfDetail data) {

        AccountPfInnerServiceImpl pfInnerService = new AccountPfInnerServiceImpl();


        int a = pfInnerService.newRecordIn(data);

        if (a > 0) {
            return true;
        } else {
            return false;
        }


    }

    /**
     * 新记录出账
     */
    public Boolean newRecordOut(AccountPfDetail data) {

        AccountPfInnerServiceImpl pfInnerService = new AccountPfInnerServiceImpl();


//++

//++

        int a = pfInnerService.newRecordOut(data);

        if (a > 0) {
            return true;
        } else {
            return false;
        }


    }


}
