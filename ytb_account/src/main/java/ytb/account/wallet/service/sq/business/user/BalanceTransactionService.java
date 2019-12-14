package ytb.account.wallet.service.sq.business.user;

import ytb.account.wallet.model.AccountTrade;

import java.math.BigDecimal;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/4
 */
public interface BalanceTransactionService {
    /**
     * 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     **/
    Integer frozenAmountAuto(Integer acId, Integer toAcId, BigDecimal balance);

    /***
     *  甲方：减冻结资金 -- 乙方：收款、总收入、产生交易流水++
     */
    boolean confirmAmount(Integer tradeId, BigDecimal paymentBalance);

    /***
     *  甲方：减冻结资金 -- 乙方：收款、总收入、产生交易流水++
     */
    boolean projectBalanceAgent(Integer tradeId, BigDecimal paymentBalance);

    //???
    Boolean frozenAmountboole(AccountTrade accountTrade);

    boolean transferServiceAuto(AccountTrade accountTrade/*, String tradeItem*/);

    int transfer(AccountTrade data);


    int receivables(AccountTrade record, Integer outAcId, Integer InAcId, BigDecimal balance);
}
