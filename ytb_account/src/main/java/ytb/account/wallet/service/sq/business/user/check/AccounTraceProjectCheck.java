package ytb.account.wallet.service.sq.business.user.check;



import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.context.model.YtbError;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/7
 */
public class AccounTraceProjectCheck {

    public static void checkParam(AccountTradeProject accountTrade) {
        if (accountTrade.getTradeType() <= 0
                || accountTrade.getTradeSubtype() <= 0
                || accountTrade.getServiceType() <= 0) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
        }

        //10-21不判断project talk
        // tradetype=10-充值
        //11-充值退款
        //20-提现
        //21-提现退款
        //30-收入
        //31-收入退款
        //40-支出
        //41-支出退款
        //50-转帐
        //51-转帐退款


        if (accountTrade.getTradeType() >= 10 && accountTrade.getTradeType() <= 21) {
        } else {
            if (accountTrade.getTalkId() == null) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "talkId==null");
            }
        }


        TradeConst.checkTradeType(accountTrade.getTradeType());
        AccountConst.checkTradeSubtype(accountTrade.getTradeSubtype());
        TradeConst.checkServiceType(accountTrade.getServiceType());

    }
}
