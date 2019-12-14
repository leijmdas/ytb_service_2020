package ytb.account.wallet.model.project;

import ytb.account.wallet.service.AccountConst.TradeConst;

public class TransferInfoTalk extends TransferInfo {
    public TransferInfoTalk(){
        this.serviceType= TradeConst.SERVICE_TYPE_thank;
    }

}
