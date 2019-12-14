package ytb.account.wallet.service.service.autoSettlement;


import ytb.account.wallet.service.service.autoSettlement.impl.SettlementTradeService;

public class SettlementTrade {


    public boolean UserSettlementTrade() {
        //项目结算
        SettlementTradeService accountTradePfService = new SettlementTradeService();
        boolean sta = accountTradePfService.SettlementTrade();

        boolean projectTradesta = accountTradePfService.SettlementProjectTrade();

        return sta;
    }
}
