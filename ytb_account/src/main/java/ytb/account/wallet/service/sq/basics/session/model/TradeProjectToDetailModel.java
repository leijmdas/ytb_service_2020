package ytb.account.wallet.service.sq.basics.session.model;

import ytb.account.wallet.model.AccountTradeProject;
import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/28
 */
public class TradeProjectToDetailModel extends Ytb_Model {
    private AccountTradeProject accountTrade;

    private Date time;

    private BigDecimal tomoney;

    private Integer id;

    private int BalanceType;

    private String BalanceSta;

    public AccountTradeProject getAccountTrade() {
        return accountTrade;
    }

    public void setAccountTrade(AccountTradeProject accountTrade) {
        this.accountTrade = accountTrade;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getTomoney() {
        return tomoney;
    }

    public void setTomoney(BigDecimal tomoney) {
        this.tomoney = tomoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBalanceType() {
        return BalanceType;
    }

    public void setBalanceType(int balanceType) {
        BalanceType = balanceType;
    }

    public String getBalanceSta() {
        return BalanceSta;
    }

    public void setBalanceSta(String balanceSta) {
        BalanceSta = balanceSta;
    }
}
