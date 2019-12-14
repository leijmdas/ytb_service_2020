package ytb.account.wallet.service.service.cashpay.transaction.user.payment;



import ytb.account.fastpay.model.wechat.WechatPayNotifyResult;
import ytb.account.wallet.model.AccountTrade;

import ytb.account.wallet.model.AccountTradeOut;
import ytb.account.wallet.service.AccountConst.AccountConst;


import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExternalPaymentService {


    /**
     * 第三方支付处理&入账
     */

    public void alipayRecharge(AccountTradeOut business) {

     /*   business.getOutTradeNo();
        business.getTotalAmount();
        business.getPassbackParams();
        business.getOutTradeNo();

*/
        String trade_status = "TRADE_SUCCESS";

        if (trade_status.equals(business.getOrderState())) {
            //获取原来的
            AccountTrade accountTrades = getAccountTrade(Integer.valueOf(business.getTradeId()));
//对比

            if (accountTrades != null) {
                if (accountTrades.getStatus() == AccountConst.STATUS_AUDIT_PASS) {
                    Boolean sta = momeyTrueY2Y(business.getTotalAmount(), accountTrades.getBalance());

                    if (sta) {
                        successSql(accountTrades);
                    }
                }
            }

        }

    }


    public void wechatRecharge(WechatPayNotifyResult business) {

     /*   business.getOutTradeNo();
        business.getTotalAmount();
        business.getPassbackParams();
        business.getOutTradeNo();

*/
        String trade_status = "SUCCESS";

        if (trade_status.equals(business.getResultCode()) & trade_status.equals(business.getReturnCode())) {
            //获取原来的
            AccountTrade accountTrades = getAccountTrade(Integer.valueOf(String.valueOf(business.getOutTradeNo())));
//对比
            if (accountTrades != null) {
                if (accountTrades.getStatus() == AccountConst.STATUS_AUDIT_PASS) {

                    Boolean sta = momeyTrueF2Y(business.getTotalFee(), accountTrades.getBalance());

                    if (sta) {
                        successSql(accountTrades);

                        //返回状态 失败后应该存入日记 后续需补上
                    }
                }

            }

        }

    }


    public Boolean momeyTrueF2Y(Integer fen, BigDecimal mymoney) {

        BigDecimal comemomey = new BigDecimal(fen);
        BigDecimal feeNo = comemomey;//string 转 BigDecimal 分转元
        feeNo = feeNo.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);//分转元

        if (mymoney.compareTo(feeNo) == 0) {
            return true;
        } else {
            return false;
        }

    }


    public Boolean momeyTrueY2Y(BigDecimal comemomey, BigDecimal mymoney) {

        if (comemomey.compareTo(mymoney) == 0) {
            return true;
        } else {
            return false;
        }

    }


    public Boolean successSql(AccountTrade accountTrade) {


        AccountEntry accountTradeService = new AccountEntry();

        int sta = accountTradeService.AccountRechargeSuccess(accountTrade);

        if (sta > 0) {

            return true;


        } else {
            return false;
        }
    }


    public AccountTrade getAccountTrade(Integer tradeId) {
     /*   AccountTradeExample accountTradeExample = new AccountTradeExample();
        AccountTradeExample.Criteria criteria = accountTradeExample.createCriteria();
        //  criteria.andTradeNoEqualTo(weChatNotify.getOutTradeNo());
        criteria.andTradeIdEqualTo(tradeId);
*/

        AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
        AccountTrade accountTrades = accountTradeService.selectByPrimaryKey(tradeId);
        return accountTrades;
    }


}
