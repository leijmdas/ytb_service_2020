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

public class Refund2Pa {
    /**
     * zc
     * 转帐 退款 一个退款
     * 从甲方的钱包冻结款转到乙方和组员的冻结款的退款
     *
     * @param sq
     * @param toInfo        退款金额
     * @return
    refund pa+
     */
    public Integer refund2Pa(SqlSession sq, TradeInfo toInfo) {
        Integer accountTradeId = toInfo.getTradeIdPre();
        BigDecimal refundM = toInfo.getBalance();

        try {
            // 数据校验
            AccountTradeProject trade = r2fRefundCheck(sq, accountTradeId, refundM);

            // account_Trade 新增记录  AccountTradeProject
            AccountTradeProject accountTrade = refund2PaTrade(sq, trade,toInfo);
            //甲方流水 乙方方流水 account_user_detail 新增记录
            refund2PaTradeDetail(sq, trade, accountTrade, refundM);

            //甲方balance_agent++  乙方project_balance_agent-- 修改account_user_inner
            refund2PaInner(sq, trade, refundM);

            return accountTrade.getTradeId();
        } catch (Exception e) {
            sq.rollback();
            throw e;
        }

    }

    /**
     * zc
     * projectRefundSq 方法中的校验
     *
     * @param sq
     * @param accountTradeId
     * @param refundM
     * @return
     */
    public AccountTradeProject r2fRefundCheck(SqlSession sq, Integer accountTradeId, BigDecimal refundM) {
        AccountTradeProject trade = null;
        try {
            AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);
            trade = accountTradeMapper.selectByPrimaryKey(accountTradeId);

            if (trade == null) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "订单不存在-ProjecTransactionServiceImpl");
            }

            if (refundM.compareTo(BigDecimal.ZERO) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "退款金额错误-ProjecTransactionServiceImpl");
            }

            if ((trade.getRefundBalance().add(refundM)).compareTo(trade.getBalance()) != -1 && refundM.compareTo(trade.getBalance()) > 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "退款总额超过单笔订单总金额-ProjecTransactionServiceImpl");
            }


            AccountTradeProject uptr = new AccountTradeProject();
            uptr.setTradeId(accountTradeId);
            uptr.setRefundBalance(trade.getRefundBalance().add(refundM));
            int asfdas = accountTradeMapper.updateByPrimaryKeySelective(uptr);
            if (asfdas <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "更新订单失败-ProjecTransactionServiceImpl");
            }
        } catch (Exception e) {
            sq.rollback();
            e.printStackTrace();
        }
        return trade;
    }


    /**
     * zc
     * account_Trade 新增记录
     *
     * @param sq
     * @param trade
     * @param  toInfo
     */
    public AccountTradeProject refund2PaTrade(SqlSession sq, AccountTradeProject trade, TradeInfo toInfo)   {
        AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);

        //修改上一订单的退款金额
        AccountTradeProject uptr = new AccountTradeProject();
        uptr.setTradeId(toInfo.getTradeIdPre());
        uptr.setRefundBalance(trade.getRefundBalance().add(toInfo.getBalance()));
        int num = accountTradeMapper.updateByPrimaryKeySelective(uptr);
        if (num <= 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "更新订单失败-ProjecTransactionServiceImpl");
        }

        AccountTradeProject reTrade = new AccountTradeProject();
        reTrade.setTradeNoPre(String.valueOf(trade.getTradeId()));

        reTrade.setTradeNoPre(String.valueOf(toInfo.getTradeIdPre()));
        reTrade.setUserId(toInfo.getUserId());
        reTrade.setCompanyId(toInfo.getCompanyId());

        reTrade.setProjectId(trade.getProjectId());
        reTrade.setTalkId(trade.getTalkId());
        reTrade.setPhase(trade.getPhase());

        reTrade.setAcId(trade.getToAcId());
        reTrade.setToAcId(trade.getAcId());
        reTrade.setAddTime(new Date());
        //reTrade.setServiceType(trade.getServiceType());
        reTrade.setServiceType(toInfo.getServiceType());

        reTrade.setTradeType(TradeConst.TRADE_TYPE_PAYOUT_REFUND);
        reTrade.setTradeSubtype(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
        reTrade.setStatus(TradeConst.status_success);
        reTrade.setTotalBalance(toInfo.getTotalBalance());
        reTrade.setBalance(toInfo.getBalance());
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
    public void refund2PaInner(SqlSession sq, AccountTradeProject trade, BigDecimal refundM) {

        AccountUserInnerBalanceMapper innerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
        //甲方+++ 修改account_user_inner
        int tacsta = innerBalanceMapper.updateIncreaseBalanceAgentByInnerId(trade.getAcId(), refundM);
        if (tacsta <= 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "甲方更冻结金额失败-ProjecTransactionServiceImpl");
        }

        //乙方-- 修改account_user_inner
        int agad = innerBalanceMapper.f2fAddPayoutAgent(trade.getToAcId(), refundM);
        //updateProjectBalanceAgentIncreaseByInnerId
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
    public void refund2PaTradeDetail(SqlSession sq, AccountTradeProject trade,
                                     AccountTradeProject accountTrade, BigDecimal refundM) {

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
