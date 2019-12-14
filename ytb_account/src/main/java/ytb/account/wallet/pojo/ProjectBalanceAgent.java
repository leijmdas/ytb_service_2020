package ytb.account.wallet.pojo;

import ytb.account.wallet.model.AccountTrade;
import ytb.common.context.model.Ytb_Model;

import java.util.List;

public class ProjectBalanceAgent extends Ytb_Model {

    private AccountTrade accountTrade;
    private List<AccountTrade> toAccountTrades;


    public AccountTrade getAccountTrade() {
        return accountTrade;
    }

    public void setAccountTrade(AccountTrade accountTrade) {
        this.accountTrade = accountTrade;
    }

    public List<AccountTrade> getToAccountTrades() {
        return toAccountTrades;
    }

    public void setToAccountTrades(List<AccountTrade> toAccountTrades) {
        this.toAccountTrades = toAccountTrades;
    }
}
