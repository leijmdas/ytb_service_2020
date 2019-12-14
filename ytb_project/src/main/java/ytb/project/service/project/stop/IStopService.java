package ytb.project.service.project.stop;

import ytb.project.context.UserProjectContext;
import ytb.project.service.project.stop.impl.StopType;
import ytb.manager.template.model.Dict_TemplateModel;
import java.math.BigDecimal;
import java.util.List;

public interface IStopService {

    // 1 判断能否终止
    boolean checkCanStop(UserProjectContext context, StopType stopType);

    // 2 计算终止各项目费用与Q分
    void compute(UserProjectContext context, StopType stopType);

    // 3 计算终止通知书
    int computeStopPTable(UserProjectContext context, StopType stopType);

    // 4 查找终止通知书模板
    Dict_TemplateModel getStopTemplate(UserProjectContext context, StopType stopType);

    // 5 终止支付差额
    BigDecimal selectPaPbPayFee(UserProjectContext context);

    // 6 支付后处理
    List<Integer> payAfter(UserProjectContext context);

    //int applyStopProject(UserProjectContext context, int userId, StopType stopType) throws
    // UnsupportedEncodingException;

    //String confirmStopProject(UserProjectContext context, int userId, int status);

    void noticePartyStopProject(UserProjectContext context);
}

