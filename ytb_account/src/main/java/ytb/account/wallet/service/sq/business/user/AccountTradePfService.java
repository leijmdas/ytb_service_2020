package ytb.account.wallet.service.sq.business.user;


import ytb.account.cashpay.alipay.model.ComeTransferAccountsModel;
import ytb.account.wallet.model.AccountTrade;


public interface AccountTradePfService {

    int transferService(AccountTrade accountTrade, String tradeItem);

    //int withdrawCashAdopt(Integer tradeId);

    int withdrawCashAdopt(ComeTransferAccountsModel data);

//    boolean SettlementTrade();

}