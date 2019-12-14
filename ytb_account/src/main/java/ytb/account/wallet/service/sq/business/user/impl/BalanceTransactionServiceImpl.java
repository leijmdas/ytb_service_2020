package ytb.account.wallet.service.sq.business.user.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountTradeMapper;
import ytb.account.wallet.dao.transaction.AccountUserInnerBalanceMapper;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.account.wallet.service.sq.business.user.BalanceTransactionService;
import ytb.account.wallet.service.sq.business.user.ProjecTransactionService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.math.BigDecimal;

/**
 * 项目交易接口
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/19
 */
public class BalanceTransactionServiceImpl implements BalanceTransactionService {
    /**
     * 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     **/
    @Override
    public Integer frozenAmountAuto(Integer acId, Integer toAcId, BigDecimal balance/*, String tradeItem*/) {

        try {


            AccountTrade accountTrade = new AccountTrade();
            accountTrade.setTradeType((short) 103);
            accountTrade.setStatus((byte) 3);
            accountTrade.setAcId(acId);
            accountTrade.setToAcId(toAcId);
            accountTrade.setBalance(balance);

            ProjecTransactionService accountTradeService = new ProjecTransactionServiceImpl();

            int sta = accountTradeService.frozenAmount(accountTrade/*, tradeItem*/);

            if (sta > 0) {
                return sta;
            } else {
                return 0;
            }

        } catch (Exception e) {
            return 0;
        }
    }

    /***
     *  甲方：减冻结资金 -- 乙方：收款、总收入、产生交易流水++
     */
    @Override
    public boolean confirmAmount(Integer tradeId, BigDecimal paymentBalance) {

        try {

            AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();

            int sta = accountTradeService.confirmAmount(tradeId, paymentBalance);

            if (sta > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return true;
        }
    }

    /***
     *  甲方：减冻结资金 -- 乙方：收款、总收入、产生交易流水++
     */
    @Override
    public boolean projectBalanceAgent(Integer tradeId, BigDecimal paymentBalance) {

        try {

            AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();

            int sta = accountTradeService.confirmAmount(tradeId, paymentBalance);

            if (sta > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean transferServiceAuto(AccountTrade accountTrade/*, String tradeItem*/) {

        try {

            ProjecTransactionService accountTradeService = new ProjecTransactionServiceImpl();

            int sta = accountTradeService.transferService(accountTrade/*, tradeItem*/);

            if (sta > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean frozenAmountboole(AccountTrade accountTrade) {
        try {
            int sta = 0;


            SysAccountUserRechargeService rechargeService = new SysAccountUserRechargeService();

            sta = rechargeService.frozenAmount(accountTrade);
            if (sta > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int receivables(AccountTrade record, Integer outAcId, Integer InAcId, BigDecimal balance) {


        SqlSession sq = MyBatisUtil.getSession(false);

        int data = 0;
        int datab = 0;
        int datac = 0;
        int sta = 0;
        try {

            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            data = accountTradeMapper.updateByPrimaryKeySelective(record);

            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);

            datab = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(outAcId, balance);

            datac = accountUserInnerBalanceMapper.updateBalanceIncreaseByInnerId(InAcId, balance);


            if (data > 0 && datab > 0 && datac > 0) {
                sta = 1;
                sq.commit();
            } else {
                sq.rollback();
            }

        } catch (Exception e) {
            System.out.println("出错------------");
            e.printStackTrace();
            sq.rollback();
        } finally {
            sq.close();

        }
        return sta;

    }

    @Override
    public int transfer(AccountTrade record) {


        SqlSession sq = MyBatisUtil.getSession(false);

        int data = 0;
        int datab = 0;
        int datac = 0;
        int sta = 0;
        try {

            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            data = accountTradeMapper.insertSelective(record);

            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);

            datab = accountUserInnerBalanceMapper.updateBalanceAndOutReduce(record.getToAcId(), record.getBalance());

            datac = accountUserInnerBalanceMapper.updateBalanceIncreaseByInnerId(record.getAcId(), record.getBalance());

            if (data > 0 && datab > 0 && datac > 0) {
                sq.commit();
                sta = 1;
            } else {
                sq.rollback();
            }

        } catch (Exception e) {
            System.out.println("出错------------");
            e.printStackTrace();
            sq.rollback();
        } finally {
            sq.close();

        }
        return sta;

    }
}