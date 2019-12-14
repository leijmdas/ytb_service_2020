package ytb.project.service.impl.pay.payassist.paytalk;

import ytb.project.context.UserProjectContext;
import ytb.project.model.UserAssistModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 申请时支付
 * Package: ytb.project.service.impl.pay
 * Author: ZCS
 * Date: Created in 2019/3/28 19:35
 */
public final class PayTalkApply extends ProjectPayTalk {
    //    洽谈支付申请
    //    乙方申请，
    //    甲方同意则预留冻结款，
    //    甲方不同意则不预留。
    // 甲方同意申请则预留冻结款
    public int paConfirmApply_payPre(UserProjectContext context, String payPassword,
                                     int templateId, List<UserAssistModel> monies) throws UnsupportedEncodingException {

        return payAssistPre(context, payPassword,  monies);
    }
}
