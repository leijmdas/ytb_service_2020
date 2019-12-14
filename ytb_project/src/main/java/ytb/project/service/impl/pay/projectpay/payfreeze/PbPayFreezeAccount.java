package ytb.project.service.impl.pay.projectpay.payfreeze;


import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.VProjectTradeAccountModel;

import java.math.BigDecimal;
import java.util.List;

//项目冻结款历史查询
//pb冻结帐户
public final class PbPayFreezeAccount extends PayFreezeAccount {

    //pb all
    public BigDecimal sumFrozen(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        List<VProjectTradeAccountModel> models = selectListPb(pm.getProjectId(), pm.getUserId1());

        return sumFrozen(models);
    }

    public BigDecimal sumFrozen(UserProjectContext context, int pmUserId) {
        ProjectModel pm = context.getProjectModel();
        List<VProjectTradeAccountModel> models = selectList(pm.getProjectId(), pmUserId);

        return sumFrozen(models);
    }

}
