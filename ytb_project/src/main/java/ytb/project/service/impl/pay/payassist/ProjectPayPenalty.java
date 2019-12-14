package ytb.project.service.impl.pay.payassist;


import ytb.account.wallet.service.AccountConst.TradeConst;

//洽谈感谢金
public class ProjectPayPenalty extends ProjectPayAssist implements IProjectPayAssist {

    public ProjectPayPenalty(){
        serviceType = TradeConst.SERVICE_TYPE_penalty;
    }

    //违约金

}
