package ytb.account.wallet.service.sq.business.user.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.transaction.AccountUserInnerBalanceMapper;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.account.wallet.service.sq.business.user.AccountUserInnerBalanceService;
import ytb.account.wallet.tool.MyBatisUtil;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */

public class AccountUserInnerBalanceServiceImpl implements AccountUserInnerBalanceService {


    @Override
    public int updateBalanceReduceByInnerId(Integer innerId, BigDecimal balance) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            int data = 0;
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            data = accountUserInnerBalanceMapper.updateBalanceReduceByInnerId(innerId, balance);
            return data;
        } finally {
            sq.close();

        }
    }



    @Override
    public int updateBalanceIncreaseByInnerId(Integer innerId, BigDecimal balance) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            int data = 0;
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            data = accountUserInnerBalanceMapper.updateBalanceIncreaseByInnerId(innerId, balance);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateTakeoutMoneyByInnerId(Integer innerId, BigDecimal balance) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            int data = 0;
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            data = accountUserInnerBalanceMapper.updateTakeoutMoneyByInnerId(innerId, balance);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateIncreaseIncrease(Integer innerId, BigDecimal balance) {
        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;
        try {

            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            data = accountUserInnerBalanceMapper.updateIncreaseIncrease(innerId, balance);
            sq.commit();


        } catch (Exception e) {
            e.printStackTrace();
            sq.rollback();


        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public int updateBalanceReduce(Integer innerId, BigDecimal balance) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            int data = 0;
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            data = accountUserInnerBalanceMapper.updateBalanceReduce(innerId, balance);
            return data;
        } finally {
            sq.close();

        }
    }

    /**
     * 校验 userId和payPassword 是否匹配
     * @param session
     * @param tradeInfo
     * @return
     */
    public boolean checkPassword(SqlSession session, TradeInfo tradeInfo) {

        AccountUserInnerBalanceMapper innerMapper = session.getMapper(AccountUserInnerBalanceMapper.class);
        AccountUserInner userInners = innerMapper.queryAccountUserInner(
                tradeInfo.getCompanyId()>0?0:tradeInfo.getUserId(),tradeInfo.getCompanyId(),tradeInfo.getPassword());
        if (userInners == null) {
            session.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "userId和payPassword不匹配");
        }
        return true;
    }
}
