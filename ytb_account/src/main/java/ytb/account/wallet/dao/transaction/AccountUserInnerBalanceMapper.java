package ytb.account.wallet.dao.transaction;

import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.pojo.ProjectBalanceToAc;

import java.math.BigDecimal;

public interface AccountUserInnerBalanceMapper {


    AccountUserInner queryAccountUserInner(@Param("userId") int userId, @Param("companyId") int companyId,
                                           @Param("payPassword") String payPassword);

    AccountUserInner queryAccountUserInnerByInnerId(@Param("innerId") int innerI);

    /**
     * 提现冻结费用+++
     * takeout_money+++    -
     */
    int updateTakeoutMoneyReduceByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);


    /**
     * 项目收入冻结金额+++
     * project_balance_agent+++    -
     */

    int updateProjectBalanceAgentIncreaseByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);


    /**
     * 项目收入冻结金额----
     * project_balance_agent---    -
     */

    int updateProjectBalanceAgentReduceByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);


    /**
     * balance直接-减少    -
     */
    int updateBalanceReduce(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);


    /**
     * 总金额Balance+
     */
    int updateIncreaseIncrease(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);

    /**
     * 总入账 BalanceIn +
     */
    int updateBalanceInIncreaseByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);


    /**
     * 托管账户余额(支付冻结) balance_agent--
     */
    int f2fMinusPayoutAgent(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);
    int f2fAddPayoutAgent(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);



    /**
     * 金额-balance-
     * 支付冻结- balance_agent+
     * 总出账-balance_out+
     * 主要作用:
     * 出账 、 交易支付
     */
    // int updateBalanceReduceByWithdraw(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);


    /**
     * 冻结金额
     * 金额balance--
     * 支付冻结balance_agent++
     * 总出账balance_out ++++
     */

    int updateBalanceReduceByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);

    int updateBalanceB2FMinusServiceFee(
            @Param("innerId") Integer innerId,
            @Param("balance") BigDecimal balance );


    /**
     * 金额 - balance--
     * 提现冻结- takeout_money++
     * 出账-balance_out++
     * 主要作用:
     * 提现出账
     */

    int updateBalanceReduceByWithdrawCash(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);


    /**
     * 操作增加金额
     * 金额balance --
     * 提现冻结takeout_money++
     */

    int updateTakeoutMoneyByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);


    /**
     * 项目冻结金额 - project_balance_agent--
     * 金额 - balance ++
     * 总入账  - balance_in ++
     * 项目冻结金额-》入账
     */

    int updatePbFrozen2Balance(ProjectBalanceToAc balanceToAc);


    /**
     * 操作增加金额  增加总收入金额
     * &balance++
     * &balance_in+++
     */
    int updateBalanceIncreaseByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);

    /**
     * 出账
     * 金额balance--
     * 总出账balance_out++
     */
    int updateBalanceAndOutReduce(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);

    /**
     * 出账
     * <p>
     * 总出账balance_agent++
     */

    int updateIncreaseBalanceAgentByInnerId(@Param("innerId") Integer innerId, @Param("balance") BigDecimal balance);





}


