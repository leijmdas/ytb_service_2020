package ytb.account.wallet.model.project;

import ytb.account.wallet.service.AccountConst.TradeConst;

public class TransferInfoAssist extends TransferInfo {
    public TransferInfoAssist(){
        this.serviceType= TradeConst.SERVICE_TYPE_assist;
    }

}
