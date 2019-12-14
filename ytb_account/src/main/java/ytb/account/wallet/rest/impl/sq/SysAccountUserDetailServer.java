package ytb.account.wallet.rest.impl.sq;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.pojo.Paging;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.account.wallet.model.AccountUserDetail;
import ytb.account.wallet.model.AccountUserDetailExample;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.service.sq.basics.impl.AccountUserDetailServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SysAccountUserDetailServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;


    public MsgResponse detailInfo(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();

        /*LoginSso*/
        //String token = req.token;

        //LoginSso loginSso = SafeContext.getLog_sso(token);
        //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();
        AccountUserInner accountUserInnerList = sysAccountUserInnerServer.getInnerIdByUser(loginSsoJson);

        AccountUserDetail accountUserDetail = JSONObject.parseObject(req.msgBody.toString(), AccountUserDetail.class);


        if (loginSsoJson.getUserId() != null/* &&accountUserDetail.getInnerId() != null*/) {

            AccountUserDetailExample accountUserDetailExample = new AccountUserDetailExample();
            AccountUserDetailExample.Criteria criteria = accountUserDetailExample.createCriteria();
            AccountUserDetailServiceImpl accountUserDetailService = new AccountUserDetailServiceImpl();

            if (accountUserInnerList != null) {
                accountUserDetail.setInnerId(accountUserInnerList.getInnerId());
                criteria.andInnerIdEqualTo(accountUserDetail.getInnerId());
            } else {
                retcode = 1;
                retmsg = "数据错误";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }


            if (accountUserDetail.getTermId() != null) {
                criteria.andTermIdEqualTo(accountUserDetail.getTermId());
            }
            if (accountUserDetail.getTradeId() != null) {
                criteria.andTradeIdEqualTo(accountUserDetail.getTradeId());
            }
            if (accountUserDetail.getDetailId() != null) {
                criteria.andDetailIdEqualTo(accountUserDetail.getDetailId());
            }
            if (accountUserDetail.getTradeItem() != null) {
                criteria.andTradeItemEqualTo(accountUserDetail.getTradeItem());
            }
            if (accountUserDetail.getTradeType() != null) {
                criteria.andTradeTypeEqualTo(accountUserDetail.getTradeType());
            }

            if (accountUserDetail.getTradeSubtype() != null) {
                criteria.andTradeSubtypeEqualTo(accountUserDetail.getTradeSubtype());
            }


            List<AccountUserDetail> accountUserDetailList = accountUserDetailService.selectByExample(accountUserDetailExample);

            if (accountUserDetailList.size() > 0)


                msgBody = "{\"list\":" + JSONObject.toJSONString(accountUserDetailList) + "}";


        } else {
            retcode = 1;
            retmsg = "数据错误";

        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    Paging structurePaging(MsgRequest req) {
        Paging paging = new Paging();
        if (
                req.msgBody.getInteger("currPage") != null &
                        req.msgBody.getInteger("pageSize") != null &
                        req.msgBody.getString("toOrder") != null &
                        req.msgBody.getString("orderBy") != null
        ) {
            paging.setCurrPage(req.msgBody.getInteger("currPage"));
            paging.setPageSize(req.msgBody.getInteger("pageSize"));
            paging.setToOrder(req.msgBody.getString("toOrder"));
            paging.setOrderBy(req.msgBody.getString("orderBy"));
            return paging;
        } else {
            return null;
        }

    }


    public PageUtils accountDetailByPage(AccountUserDetail accountUserDetail, Paging paging) {


        AccountUserDetailExample example = new AccountUserDetailExample();
        AccountUserDetailExample.Criteria criteria = example.createCriteria();
        AccountUserDetailServiceImpl accountUserDetailService = new AccountUserDetailServiceImpl();

        if (accountUserDetail.getInnerId() != null) {
            criteria.andTermIdEqualTo(accountUserDetail.getTermId());
        }
        if (accountUserDetail.getTermId() != null) {
            criteria.andTermIdEqualTo(accountUserDetail.getTermId());
        }
        if (accountUserDetail.getTradeId() != null) {
            criteria.andTradeIdEqualTo(accountUserDetail.getTradeId());
        }
        if (accountUserDetail.getDetailId() != null) {
            criteria.andDetailIdEqualTo(accountUserDetail.getDetailId());
        }
        if (accountUserDetail.getTradeItem() != null) {
            criteria.andTradeItemEqualTo(accountUserDetail.getTradeItem());
        }
        if (accountUserDetail.getTradeType() != null) {
            criteria.andTradeTypeEqualTo(accountUserDetail.getTradeType());
        }

        if (accountUserDetail.getTradeSubtype() != null) {
            criteria.andTradeSubtypeEqualTo(accountUserDetail.getTradeSubtype());
        }
        if (paging != null) {

            if (
                    paging.getToOrder().equals("desc") || paging.getToOrder().equals("asc")) {
                example.setOrderByClause(paging.getToOrder() + "  " + paging.getOrderBy() + " ");
            }

        }

        List<AccountUserDetail> list = accountUserDetailService.selectByExample(example);

        PageUtils pageUtil = new PageUtils();
        if (paging != null) {
            pageUtil = new PageUtils(list, list.size(), paging.getPageSize(), paging.getCurrPage());
            Integer firstIndex = (paging.getCurrPage() - 1) * paging.getPageSize();
            //到第几条数据结束
            Integer lastIndex = paging.getPageSize();

            example.setOffset(firstIndex);
            example.setLimit(lastIndex);

            list = accountUserDetailService.selectByExample(example);
        }

        pageUtil.setList(list);

        return pageUtil;


    }


}
