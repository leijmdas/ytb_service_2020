package ytb.project.service.impl.pay.payassist.paytalk;

import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectInviteModel;
import ytb.project.model.UserAssistModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 邀请时支付
 * Package: ytb.project.service.impl.pay
 * Author: ZCS
 * Date: Created in 2019/3/28 19:33
 */
public final class PayTalkInvite extends PayTalkRequest {
  //  洽谈支付邀请  甲方邀请时预留冻结款;
  //  乙方同意则继续;
  //  乙方不同意则取消预留。

    //  乙方不同意则取消预留。
    public int pbCancelInvite_payCancel(UserProjectContext context, int preTradeId, String payPassword) throws UnsupportedEncodingException {
        ProjectInviteModel pim = context.getProjectInviteModel();
        if (pim.isInvite()) {

            UserAssistModel model = queryAssistMoney(preTradeId, pim.getUserId2());
            return cancel(context, preTradeId, model);
        }
        return 0;
    }


    // 甲方邀请时预留冻结
    public int paInvite_payPre(UserProjectContext context, String payPassword, int templateId,
                               List<UserAssistModel> monies) throws UnsupportedEncodingException {
        return payAssistPre(context, payPassword, monies);
    }
}
