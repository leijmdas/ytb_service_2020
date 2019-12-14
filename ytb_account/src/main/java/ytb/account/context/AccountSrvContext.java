package ytb.account.context;

import ytb.account.wallet.service.sq.basics.AccountTradeService;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.account.wallet.service.sq.business.user.ProjecTransactionService;
import ytb.account.wallet.service.sq.business.user.impl.ProjecTransactionServiceImpl;

public class AccountSrvContext {
    static AccountSrvContext inst = new AccountSrvContext();

    public static AccountSrvContext getInst() {
        return inst;
    }

    public   ProjecTransactionService getProjecTransactionService() {
        return projecTransactionService;
    }
    public AccountTradeService getAccountTradeService() {
        return accountTradeService;
    }


    ProjecTransactionService projecTransactionService = new ProjecTransactionServiceImpl();
    AccountTradeService accountTradeService = new AccountTradeServiceImpl();




}
