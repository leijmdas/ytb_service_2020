package ytb.project.service.project.stop.impl;

import ytb.project.context.UserProjectContext;
import ytb.manager.template.model.Dict_TemplateModel;

import java.math.BigDecimal;

public interface IComputeStop {

    boolean canStop(UserProjectContext context, StopType stopType);

    Dict_TemplateModel getStopTemplateDict(UserProjectContext context, StopType stopType);

    int compute(UserProjectContext context, StopType stopType);

    // 1 计算终止通知书
    int computeStopPTable(UserProjectContext context, StopType stopType);


    // 2 计算违约金Penalty
    long computePenalty(UserProjectContext context,StopType stopType);

    // 3 计算Q分
    BigDecimal computeQ (UserProjectContext context,StopType stopType);

    // 4 计算延期率
    long computeDelayRate(UserProjectContext context);


}
