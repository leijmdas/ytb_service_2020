package ytb.account.wallet.dao.transaction;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountPfInnerBalanceMapper {

    /*------------balance  &  balanceOUT IN------------------*/


    /**
     * 操作减少金额 同时冻结金额 增加总支出金额   balance ----   balance_out_++++++     （支出 ）
     */
    int updateBalanceReduceByInnerId(@Param("pfInnerId") Integer pfInnerId, @Param("balance") BigDecimal balance, @Param("acType") Integer acType);


    /**
     * 操作增加金额  增加总收入金额 balance & balance_in +++++  （收入 非冻结）
     */

    int updateBalanceIncreaseByInnerId(@Param("pfInnerId") Integer pfInnerId, @Param("balance") BigDecimal balance, @Param("acType") Integer acType);

    /*------------balance  &  balanceOUT IN------------------*/

    /*-------------------------balance_agent------------------------*/

    /**
     * project_balance_agent   托管账户余额(收入 用户充值) 冻结 +++++
     */
    int updateBalanceAgentIncrease(@Param("pfInnerId") Integer pfInnerId, @Param("balance") BigDecimal balance, @Param("acType") Integer acType);

    /**
     * project_balance_agent   托管账户余额(收入 用户充值) 冻结 减少
     */
    int updateBalanceAgentReduce(@Param("pfInnerId") Integer pfInnerId, @Param("balance") BigDecimal balance, @Param("acType") Integer acType);


    /*-------------------------takeout_money------------------------*/


    int updateTakeoutMoneyIncrease(@Param("pfInnerId") Integer pfInnerId, @Param("balance") BigDecimal balance, @Param("acType") Integer acType);

    /**
     * project_balance_agent   托管账户余额支出(收入 用户提现) 冻结 减少
     */
    int updateTakeoutMoneyReduce(@Param("pfInnerId") Integer pfInnerId, @Param("balance") BigDecimal balance, @Param("acType") Integer acType);

    /*-------------------------balance_agent------------------------*/







    int updateBalanceReduceByWithdraw(@Param("pfInnerId") Integer pfInnerId, @Param("balance") BigDecimal balance, @Param("acType") Integer acType);

    /**
     * 余额-减少   balance_out+ +++
     */

    int updateTakeoutMoneyByInnerId(@Param("pfInnerId") Integer pfInnerId, @Param("balance") BigDecimal balance, @Param("acType") Integer acType);  /** 余额-减少   takeout_money+ +++  */


}