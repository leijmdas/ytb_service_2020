package ytb.account.wallet.service.sq.business.user.impl;

import ytb.account.wallet.model.*;

import ytb.account.wallet.pojo.ProjectBalanceAgent;
import ytb.account.wallet.pojo.ProjectBalanceOutAc;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;
import ytb.account.wallet.pojo.ProjectBalanceToAc;
import ytb.account.wallet.service.sq.business.user.ProjecTransactionService;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.account.wallet.service.sq.business.user.check.AccounTraceCheck;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.util.List;

/*
 * 后续可以删掉 迁移至projectInterface
 * */
public class SysAccountUserRechargeService {
    void checkParam(AccountTrade actTrade) {
        if (actTrade.getTradeType() <= 0
                || actTrade.getTradeSubtype() <= 0
                || actTrade.getServiceType() <= 0) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
        }
        AccounTraceCheck.checkParam(actTrade);
        if (actTrade.getBalance().compareTo(BigDecimal.ZERO) <= 0
                || actTrade.getServiceFee().compareTo(BigDecimal.ZERO) < 0
                || actTrade.getTax().compareTo(BigDecimal.ZERO) < 0) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "金额为负!");
        }

    }

    /**
     * 直接转账到账  甲方流水- -， 资金- - 支出++ , 乙方收入++资金++ 收入++
     **/

    public Integer transferService(AccountTrade accountTrade/*, String tradeItem*/) {

        try {

            ProjecTransactionService accountTradeService = new ProjecTransactionServiceImpl();

            int sta = accountTradeService.transferService(accountTrade/*, tradeItem*/);

            if (sta > 0) {
                return sta;
            } else {
                return 0;
            }

        } catch (Exception e) {
            return 0;
        }
    }


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


    /**
     * 甲方：balance-减少    balance_out    支出增加    冻结资金   增加
     * 业务场景:
     * +（ 产生订单 甲方冻结金额）
     **/
    public Integer frozenAmount(AccountTrade acTrade/*, String tradeItem*/) {
        checkParam(acTrade);
        try {

            ProjecTransactionService accountTradeService = new ProjecTransactionServiceImpl();
            Integer sta = accountTradeService.frozenAmount(acTrade/*, tradeItem*/);
            if (sta > 0) {
                return sta;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     **/
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

    //项目完结冻结资金-》总金额
    public ProjectBalanceProjectAgent projectBalanceAgentToList(ProjectBalanceOutAc projectBalanceOutAc, List<ProjectBalanceToAc> projectBalanceAgentList) {

        try {


            ProjecTransactionService accountTradeService = new ProjecTransactionServiceImpl();

            ProjectBalanceProjectAgent sta = accountTradeService.phasePayPa2Pb(projectBalanceOutAc, projectBalanceAgentList);

            return sta;

        } catch (Exception e) {
            return null;
        }
    }


    //项目完结冻结资金-》总金额
    public ProjectBalanceAgent projectBalanceRefundToList(ProjectBalanceOutAc projectBalanceOutAc, List<ProjectBalanceToAc> projectBalanceAgentList) {

        try {

            ProjecTransactionServiceImpl accountTradeService = new ProjecTransactionServiceImpl();

            ProjectBalanceAgent sta = accountTradeService.projectBalanceRefundToList(projectBalanceOutAc, projectBalanceAgentList);

            return sta;

        } catch (Exception e) {
            return null;
        }
    }


    //乙方项目金额冻结--》总金额
  /* public List<AccountTrade> projectBalanceTobalanceList(List<ProjectBalanceToAc> projectBalanceAgentList) {


        ProjecTransactionService accountTradeService = new ProjecTransactionServiceImpl();
        return accountTradeService.projectBalanceToBalanceList(projectBalanceAgentList);


    }
*/

}
