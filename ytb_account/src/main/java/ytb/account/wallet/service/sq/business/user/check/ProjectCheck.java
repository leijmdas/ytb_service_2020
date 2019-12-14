package ytb.account.wallet.service.sq.business.user.check;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.common.ytblog.YtbLog;
import ytb.account.wallet.model.AccountTrade;

import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;


import java.math.BigDecimal;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/12
 */
public class ProjectCheck {

    public static Boolean stagePayByPAToPBsCheck(AccountTrade tradePA) {

        if (tradePA.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT
                && tradePA.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN

                && ( tradePA.getServiceType() == TradeConst.SERVICE_TYPE_project
                ||  tradePA.getServiceType() == TradeConst.SERVICE_TYPE_penalty
                ||  tradePA.getServiceType() == TradeConst.SERVICE_TYPE_assist
                ||  tradePA.getServiceType() == TradeConst.SERVICE_TYPE_thank   )

                && tradePA.getStatus() == TradeConst.status_success ) {

            return true;
        }

        return false;

    }
    public static Boolean stagePayByPAToPBsCheck(AccountTradeProject tradePA) {

        if (tradePA.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT
                && tradePA.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN

                && ( tradePA.getServiceType() == TradeConst.SERVICE_TYPE_project
                ||  tradePA.getServiceType() == TradeConst.SERVICE_TYPE_penalty
                ||  tradePA.getServiceType() == TradeConst.SERVICE_TYPE_assist
                ||  tradePA.getServiceType() == TradeConst.SERVICE_TYPE_thank   )

                && tradePA.getStatus() == TradeConst.status_success ) {

            return true;
        }

        return false;

    }

    public static Boolean BalancePlusminusCheck(BigDecimal ta) {

        if (ta.compareTo(BigDecimal.ZERO) != 1) {
            YtbLog.logDebug(ta);
            return false;
        } else {
            return true;

        }

    }


}
