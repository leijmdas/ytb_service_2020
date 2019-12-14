package ytb.account.wallet.service.sq.business.user;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountUserInnerBalanceService {


    int updateBalanceReduceByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);

    /**
     * 操作减少金额 同时冻结金额 增加总支出金额 balance_agent  balance_out ++++
     */

    //int updateBalanceReduceByWithdraw(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);

    /**
     * balance-减少    balance_out    支出增加    提现冻结费用takeout_money   增加    +
     */

    int updateBalanceIncreaseByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);

    /**
     * 操作增加金额  增加总收入金额 balance&balance_in+++
     */

    int updateTakeoutMoneyByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);

    /**
     * 余额-减少   takeoutMoney+ 提现冻结费用+
     */

    int updateIncreaseIncrease(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);

    /**
     * balance直接-增加    +
     */

    int updateBalanceReduce(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);  /** balance直接-减少    -*/

}