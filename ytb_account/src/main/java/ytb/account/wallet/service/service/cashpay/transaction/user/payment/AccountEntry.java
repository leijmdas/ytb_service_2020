package ytb.account.wallet.service.service.cashpay.transaction.user.payment;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountTradeMapper;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountUserDetail;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.sq.basics.session.AccountTradeSession;
import ytb.account.wallet.service.sq.basics.session.AccountUserDetailSession;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.Date;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/20
 */
public class AccountEntry {
    //充值成功

    public int AccountRechargeSuccess(AccountTrade accountTrade) {
        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;

        Date time = new Date();
        try {


            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            accountTrade.setStatus(TradeConst.status_success);
            data = accountTradeMapper.updateByPrimaryKeySelective(accountTrade);
            if (data <= 0) {
                sq.rollback();
            }


            //  AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            AccountTradeSession accountTradeSession = new AccountTradeSession();
            AccountUserDetailSession accountUserDetailSession = new AccountUserDetailSession();
            AccountUserDetail detail = accountUserDetailSession.getOldInnerBanToNewDetail(accountTrade, time
                    , accountTrade.getBalance(), AccountUserDetailSession.BalanceType_balance, AccountUserDetailSession.BalanceSta_add, sq);
            if (detail == null) {
                sq.rollback();
            }

            Boolean sta = accountTradeSession.increaseMoneyDtAndIn(accountTrade, detail, sq);

            if (sta) {
                sq.commit();
            } else {
                sq.rollback();
            }


        } catch (Exception e) {
            sq.rollback();
            e.printStackTrace();
        } finally {
            sq.close();

        }
        return data;
    }

}
