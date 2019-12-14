package ytb.account.wallet.service.sq.basics.session;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountTradeMapper;
import ytb.account.wallet.dao.AccountUserDetailMapper;
import ytb.account.wallet.dao.transaction.AccountUserInnerBalanceMapper;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountUserDetail;
import ytb.account.wallet.service.sq.business.user.check.AccounTraceCheck;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/21
 */
public class AccountTradeSession {


    public Boolean insertSelective(AccountTrade record, SqlSession sq) {

        AccounTraceCheck.checkParam(record);

        try {

            AccountTradeMapper tradeMapper = sq.getMapper(AccountTradeMapper.class);

            int data = tradeMapper.insertSelective(record);

            if (data > 0) {
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 增加流水detail
     * &账户金额Balance
     * &账户总收入BalanceIn
     */

    public Boolean increaseMoneyDtAndIn(AccountTrade accountTrade, AccountUserDetail detail,
                                        SqlSession sq) {
        int data = 0;
        try {
            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            data = accountUserDetailMapper.insertSelective(detail);

            if (data <= 0) {

                return false;
            }

            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            //BalanceIn+
            data = accountUserInnerBalanceMapper.updateBalanceInIncreaseByInnerId(accountTrade.getToAcId(), accountTrade.getBalance());


            if (data <= 0) {
                return false;
            }
            //Balance+
            data = accountUserInnerBalanceMapper.updateIncreaseIncrease(accountTrade.getToAcId(), accountTrade.getBalance());


            if (data > 0) {
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            return false;

        }


    }

}
