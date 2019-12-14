package ytb.project.service.impl.pay.projectpay.payfreeze;


//项目冻结款历史查询
//pa冻结帐户

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.VProjectTradeAccountModel;

import java.math.BigDecimal;
import java.util.List;

public final class PaPayFreezeAccount extends PayFreezeAccount {


    public BigDecimal sumFrozen(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();

        List<VProjectTradeAccountModel> models = selectListPa(pm.getProjectId(), pm.getUserId1());

        return sumFrozen(models);
    }
}
