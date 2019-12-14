package ytb.account.wallet.pojo;

import ytb.account.wallet.model.AccountTradeProject;
import ytb.common.context.model.Ytb_Model;

import java.util.List;

public class ProjectBalanceProjectAgent extends Ytb_Model {

    private AccountTradeProject accountTrade;

    private List<AccountTradeProject> toAccountTrades;


    public AccountTradeProject getAccountTrade() {
        return accountTrade;
    }

    public void setAccountTrade(AccountTradeProject accountTrade) {
        this.accountTrade = accountTrade;
    }

    public List<AccountTradeProject> getToAccountTrades() {
        return toAccountTrades;
    }

    public void setToAccountTrades(List<AccountTradeProject> toAccountTrades) {
        this.toAccountTrades = toAccountTrades;
    }
}
