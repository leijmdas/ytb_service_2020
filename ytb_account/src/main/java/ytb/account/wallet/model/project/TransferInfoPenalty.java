package ytb.account.wallet.model.project;

import ytb.account.wallet.service.AccountConst.TradeConst;

public class TransferInfoPenalty extends TransferInfo {
    public TransferInfoPenalty(){
        this.serviceType= TradeConst.SERVICE_TYPE_penalty;
    }

}
