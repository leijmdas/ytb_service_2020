package ytb.account.cashpay.alipay.service.settlement;

import org.apache.ibatis.session.SqlSession;

import ytb.account.wallet.dao.AccountPfDetailMapper;
import ytb.account.wallet.dao.transaction.AccountPfInnerBalanceMapper;
import ytb.account.wallet.dao.AccountPfInnerMapper;
import ytb.account.wallet.model.AccountPfDetail;
import ytb.account.wallet.model.AccountPfInner;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.service.AccountConst.AccountConst;

import java.util.Date;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * 羡慕自动结算方法
 * Date:2019/2/18
 */


public class AutoPfst {


    /*----------流水+賬單 - 充值----------*/

    public Boolean tradeByCharge(AccountTrade accountTrade, Date time, SqlSession sq) {

        Integer pfInnerId = 0;
        Short  acType = 0;

        if (accountTrade.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_BANK) {
            acType = AccountConst.bankCardAcType;
            pfInnerId = AccountConst.bankCardPfInnerId;

        } else if (accountTrade.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_ALIPAY) {
            acType = AccountConst.aliPayAcType;
            pfInnerId = AccountConst.aliPayPfInnerId;
        } else if (accountTrade.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_WE_CHAT) {
            acType = AccountConst.weChatAcType;
            pfInnerId = AccountConst.weChatPfInnerId;
        } else {
            return null;
        }


        AccountPfInnerMapper accountPfInnerMapper = sq.getMapper(AccountPfInnerMapper.class);
        AccountPfInner accountPfInner = accountPfInnerMapper.selectByPrimaryKey(pfInnerId);

        if (accountPfInner == null) {
            sq.rollback();
            return false;
        } else {
            Boolean a = tradeByChargeDetail(accountTrade, accountPfInner, time, sq);

            if (a) {
                AccountPfInnerBalanceMapper accountPfInnerBalanceMapper = sq.getMapper(AccountPfInnerBalanceMapper.class);
                int c = accountPfInnerBalanceMapper.updateBalanceIncreaseByInnerId(pfInnerId,
                        accountTrade.getBalance(), Integer.valueOf(acType));

                if (c > 0) {
                    return true;
                } else {

                    return false;
                }

            } else {
                sq.rollback();
                return false;
            }

        }


    }


    public Boolean tradeByChargeDetail(AccountTrade accountTrade, AccountPfInner accountPfInner, Date date, SqlSession sq) {

        AccountPfDetailMapper accountPfInnerMapper = sq.getMapper(AccountPfDetailMapper.class);


        AccountPfDetail accountPfDetail = new AccountPfDetail();
        accountPfDetail.setBalance(accountTrade.getBalance());
        accountPfDetail.setInTime(date);
        accountPfDetail.setTradeBalance(accountTrade.getBalance());
        accountPfDetail.setOriginalBalance(accountPfInner.getBalance());
        accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
        //   accountPfDetail.setTermId(Integer.valueOf(accountTrade.getTermId()));
        accountPfDetail.setTradeType(Integer.valueOf(accountTrade.getTradeSubtype()));
        accountPfDetail.setTradeSubtype(Integer.valueOf(accountTrade.getTradeSubtype()));
        int a = accountPfInnerMapper.insertSelective(accountPfDetail);
        if (a > 0) {
            return true;
        } else {
            return false;
        }


    }


}
