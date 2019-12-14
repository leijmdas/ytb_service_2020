package ytb.account.cashpay.wechat.service;

import com.alibaba.fastjson.JSONObject;
import ytb.account.wallet.model.*;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.account.wallet.service.sq.business.user.impl.AccountUserInnerBalanceServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Package: ytb.manager.metadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public class WeCharNotifyService {

    public String accountInnerInfo() {
        return "<xml> <return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg> </xml>";

    }

    public String weChatNotify(String data) {

        /*XML 过来 再转义*/
        weChatNotify weChatNotify = JSONObject.parseObject(data, weChatNotify.class);


        AccountTrade accountTrade = new AccountTrade();

        /*  accountTrade.setTradeNo(weChatNotify.getOutTradeNo());
         */
        accountTrade.setTradeId(Integer.parseInt(weChatNotify.getOutTradeNo()));
        if (accountTrade.getTradeId() != null) {

            AccountTradeExample accountTradeExample = new AccountTradeExample();
            AccountTradeExample.Criteria criteria = accountTradeExample.createCriteria();
            /*     criteria.andTradeNoEqualTo(weChatNotify.getOutTradeNo());*/
            criteria.andTradeIdEqualTo(accountTrade.getTradeId());


            AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
            List<AccountTrade> accountTrades = accountTradeService.selectByExample(accountTradeExample);

            if (accountTrades.size() > 0) {
                switch (accountTrades.get(0).getTradeType()) {

                    case 101: {
                        if (accountTrades.get(0).getStatus() == 3) {
                            AccountUserInnerBalanceServiceImpl accountUserInnerBalanceService = new AccountUserInnerBalanceServiceImpl();


                            BigDecimal feeNo = new BigDecimal(weChatNotify.getTotalFee());//string 转 BigDecimal 分转元
                            feeNo = feeNo.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);//分转元

                            if (accountTrades.get(0).getBalance().compareTo(feeNo) == 0) {
                                int a = accountUserInnerBalanceService.updateIncreaseIncrease(accountTrades.get(0).getToAcId(), feeNo);
                                if (a > 0) {
                                    AccountTradeServiceImpl accountTradeServiceUP = new AccountTradeServiceImpl();

//                        accountTrade.setTradeId(accountTrades.get(0).getTradeId()     );
                                    accountTrade.setStatus((byte) 10);
                                    accountTradeServiceUP.updateByPrimaryKeySelective(accountTrade);
                                    return "<xml> <return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg> </xml>";

                                }


                            } else {
                                return "金额不对 此处理退款逻辑";
                            }

                        } else {
                            return "订单状态错误 ";
                        }
                    }
                    case 201: {

                    }


                    default: {
                        return "未定义的交易类型";
                    }


                }


            }

        }
        return "参数错误";

    }

}
