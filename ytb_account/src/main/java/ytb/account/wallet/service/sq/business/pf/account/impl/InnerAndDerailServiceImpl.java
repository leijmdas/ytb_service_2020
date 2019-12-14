package ytb.account.wallet.service.sq.business.pf.account.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.model.*;

import ytb.account.wallet.service.AccountConst.AccountConst;

import ytb.account.wallet.service.sq.business.pf.account.InnerAndDerailService;
import ytb.account.wallet.tool.MyBatisUtil;


import javax.websocket.Session;
import java.math.BigDecimal;

/**
 * 项目交易接口
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/19
 */
public class InnerAndDerailServiceImpl implements InnerAndDerailService {


    //增加
    public static final String increase = "increase";

    //reduce
    public static final String reduce = "reduce";

    /**
     * 自动加减流水
     * balance 要扣的钱
     * accountPfInner 原始信息
     */

    @Override
    public AccountPfDetail pfDetailAutoCalculation(BigDecimal balance, AccountPfInner accountPfInner, AccountPfDetail accountPfDetail, Short acType, String sta) {


        accountPfDetail.setOriginalBalance(accountPfInner.getBalance());

        accountPfDetail.setTradeBalance(balance);

        if (sta.equals(increase)) {

            if (acType == AccountConst.bankCardAcType) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(balance));

            } else if (acType == AccountConst.aliPayAcType) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(balance));

            } else if (acType == AccountConst.weChatAcType) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(balance));

            } else if (acType == AccountConst.generalAccount) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(balance));

            } else if (acType == AccountConst.serviceFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(balance));

            } else if (acType == AccountConst.chargeFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(balance));

            } else if (acType == AccountConst.taxFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(balance));

            }


        } else if (sta.equals(reduce)) {

            if (acType == AccountConst.bankCardAcType) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(balance));

            } else if (acType == AccountConst.aliPayAcType) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(balance));

            } else if (acType == AccountConst.weChatAcType) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(balance));

            } else if (acType == AccountConst.generalAccount) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(balance));

            } else if (acType == AccountConst.serviceFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(balance));

            } else if (acType == AccountConst.chargeFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(balance));

            } else if (acType == AccountConst.taxFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(balance));

            }

        } else {
            return null;
        }
        return accountPfDetail;
    }


    @Override
    public Boolean pfDetailAutoCalculation(AccountPfTrade accountTrade, SqlSession sq) {
        //    SqlSession sq = MyBatisUtil.getSession(false);
        accountTrade.getTradeType();
        accountTrade.getTradeSubtype();
        accountTrade.getStatus();
        accountTrade.getTradeId();






        return true;
    }

}
