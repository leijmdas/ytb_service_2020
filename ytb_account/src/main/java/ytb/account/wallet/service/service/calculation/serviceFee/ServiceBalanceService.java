package ytb.account.wallet.service.service.calculation.serviceFee;

import java.math.BigDecimal;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/26
 */
public interface ServiceBalanceService {
    BigDecimal serviceBalance(BigDecimal balance, Short tradeSubtype);
}
