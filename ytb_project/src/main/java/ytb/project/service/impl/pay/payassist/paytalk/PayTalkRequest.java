package ytb.project.service.impl.pay.payassist.paytalk;

import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.model.UserAssistModel;
import ytb.account.wallet.model.AccountTradeProject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PayTalkRequest extends ProjectPayTalk {
    public  int paConfirmTalk_payCancel(UserProjectContext context, int preTradeId) throws UnsupportedEncodingException {
        UserAssistModel model = queryAssistMoney(preTradeId, context.getProjectTalkModel().getUserId2());
        return cancel(context, preTradeId,  model);

    }

    int cancel(UserProjectContext context, int preTradeId,   UserAssistModel money) throws UnsupportedEncodingException {
        List<UserAssistModel> monies = new ArrayList<>();
        monies.add(money);
        return payAssistCancel(context, preTradeId,  monies);
    }


    //洽谈确认则取消此乙方的预留冻结款
    public int paConfirmTalk_payCancel(UserProjectContext context, int preTradeId,
                                       List<UserAssistModel> monies) throws UnsupportedEncodingException {
        return payAssistCancel(context, preTradeId,  monies);
    }

    //乙方终止洽谈取消预留的冻结款感谢金
    public int pbStopTalk_payCancel(UserProjectContext context, int preTradeId,
                                    List<UserAssistModel> monies) throws UnsupportedEncodingException {
        return payAssistCancel(context, preTradeId,  monies);
    }

    //甲方终止洽谈支付感谢金
    public List<AccountTradeProject> paStopTalk_payConfirm(UserProjectContext context, int preTradeId, String payPassword,
                                                           List<UserAssistModel> monies) throws UnsupportedEncodingException {

        return payAssistConfrim(context, preTradeId,  monies);
    }
}
