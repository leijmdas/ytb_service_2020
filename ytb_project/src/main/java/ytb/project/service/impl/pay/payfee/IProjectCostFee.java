package ytb.project.service.impl.pay.payfee;

import ytb.project.context.UserProjectContext;
import ytb.project.model.tagtable.CostModel;
import java.math.BigDecimal;

public interface IProjectCostFee {

    CostModel getProjectCostTotal(UserProjectContext context);

    BigDecimal sumHistryPhaseCost(UserProjectContext context, CostModel costModel);

    BigDecimal sumUnpayPhaseCost(UserProjectContext context, CostModel costModel);

    BigDecimal getPhaseCost(CostModel costModel, int phase);

    //计算比例
    //获取阶段费用
    //costModelTotal.getCostPhase(oldPtm.getPhase()));

}
