package ytb.account.wallet.service.sq.business.user.impl.F2fRefund;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountTradeProjectMapper;
import ytb.account.wallet.dao.transaction.AccountUserInnerBalanceMapper;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.sq.basics.session.AccountUserDetailSession;
import ytb.account.wallet.service.sq.basics.session.model.TradeProjectToDetailModel;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PmRefund extends Refund2Pa {
    public void pmRefund(SqlSession session,List<TradeInfo> outInfos){

        try {
            for(TradeInfo info:outInfos) {
                // 数据校验
                AccountTradeProject trade = r2fRefundCheck(session,info.getTradeIdPre(),info.getTotalBalance());

                // account_Trade 新增记录  AccountTradeProject
                AccountTradeProject accountTrade = pmRefundTrade(session, trade, info);
                //甲方流水 乙方方流水 account_user_detail 新增记录
                pmRefundTradeDetail(session, trade, accountTrade,  info.getTotalBalance());

                //甲方balance_agent++  乙方project_balance_agent-- 修改account_user_inner
                pmRefundInner(session, trade, info.getTotalBalance());
            }
        } catch (Exception e) {
            session.rollback();
            throw e;
        }
    }



    /**
     * zc
     * account_Trade 新增记录
     *
     * @param sq
     * @param trade
     * @param info
     */
    public AccountTradeProject pmRefundTrade(SqlSession sq, AccountTradeProject trade,TradeInfo info) {
        AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);

        //修改上一订单的退款金额
        AccountTradeProject uptr = new AccountTradeProject();
        uptr.setTradeId(info.getTradeIdPre());
        uptr.setRefundBalance(trade.getRefundBalance().add(info.getTotalBalance()));
        int asfdas = accountTradeMapper.updateByPrimaryKeySelective(uptr);
        if (asfdas <= 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "更新订单失败-ProjecTransactionServiceImpl");
        }

        AccountTradeProject reTrade = new AccountTradeProject();
        reTrade.setTradeNoPre(String.valueOf(trade.getTradeId()));

        reTrade.setTradeNoPre(String.valueOf(info.getTradeIdPre()));
        reTrade.setUserId(info.getUserId());
        reTrade.setCompanyId(info.getCompanyId());

        reTrade.setProjectId(trade.getProjectId());
        reTrade.setTalkId(trade.getTalkId());
        reTrade.setPhase(trade.getPhase());

        reTrade.setAcId(trade.getToAcId());
        reTrade.setToAcId(trade.getAcId());
        reTrade.setAddTime(new Date());
        reTrade.setServiceType(info.getServiceType());

        reTrade.setTradeType(TradeConst.TRADE_TYPE_INCOME_REFUND);
        reTrade.setTradeSubtype(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
        reTrade.setStatus(TradeConst.status_success);
        reTrade.setBalance(info.getBalance());
        reTrade.setTotalBalance(info.getTotalBalance());
        reTrade.setCreateTime(new Date());
        reTrade.setCreateBy(0);
        int asd = accountTradeMapper.insertSelective(reTrade);
        if (asd < 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "用户交易表(account_Trade)添加记录失败-ProjecTransactionServiceImpl");
        }
        return reTrade;
    }

    /**
     * zc
     * 修改account_user_inner
     *
     * @param sq
     * @param trade
     * @param refundM
     */
    public void pmRefundInner(SqlSession sq, AccountTradeProject trade, BigDecimal refundM) {

        AccountUserInnerBalanceMapper innerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
        //甲方+++ 修改account_user_inner
        int tacsta = innerBalanceMapper.updateIncreaseBalanceAgentByInnerId(trade.getAcId(), refundM);
        if (tacsta <= 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "甲方更冻结金额失败-ProjecTransactionServiceImpl");
        }

        //乙方-- 修改account_user_inner
        int agad = innerBalanceMapper.updateProjectBalanceAgentReduceByInnerId(trade.getToAcId(), refundM);

        if (agad <= 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "乙方减少项目冻结款失败-ProjecTransactionServiceImpl");
        }
    }
    /**
     * zc
     * 甲方流水 account_user_detail 新增记录
     *
     * @param sq
     * @param trade
     * @param accountTrade
     * @param refundM
     */
    public void pmRefundTradeDetail(SqlSession sq, AccountTradeProject trade, AccountTradeProject accountTrade, BigDecimal refundM) {

        TradeProjectToDetailModel as = new TradeProjectToDetailModel();
        as.setAccountTrade(accountTrade);
        as.setBalanceSta(AccountUserDetailSession.BalanceSta_add);
        as.setBalanceType(AccountUserDetailSession.BalanceType_balance_agent);
        as.setId(trade.getAcId());

        as.setTime(new Date());
        as.setTomoney(refundM);

        AccountUserDetailSession session = new AccountUserDetailSession();
        Boolean dtle = session.getOldInnerBanToNewDetailSq(as, sq);

        if (dtle == false) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "甲方流水(AccountUserInnerMapper)更新失败-ProjecTransactionServiceImpl");
        }

        TradeProjectToDetailModel bs = new TradeProjectToDetailModel();
        bs.setAccountTrade(accountTrade);
        bs.setBalanceSta(AccountUserDetailSession.BalanceSta_subtract);
        bs.setBalanceType(AccountUserDetailSession.BalanceType_project_balance_agent);
        bs.setId(trade.getToAcId());
        bs.setTime(new Date());
        bs.setTomoney(refundM);

        Boolean dd = session.getOldInnerBanToNewDetailSq(bs, sq);
        if (dd == false) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "乙方流水(AccountUserInnerMapper)更新失败-ProjecTransactionServiceImpl");
        }
    }

}
