package ytb.project.service.project.change.impl;

import ytb.project.context.UserProjectContext;
import ytb.project.service.project.change.ChangeFlow;

/**
 * Package: ytb.project.service.project.change
 * Author: ZCS
 * Date: Created in 2019/4/2 17:28
 */
public final class LittleChange extends ChangeFlow {
    //public BigDecimal selectFeeDiff(UserProjectContext newContext) {

//1.项目名称：  项目名称
//2.项目进度：  第p阶段
//3.费用结算（人民币元）：
//① 原计划总费用 =  原工作计划总费用x.xx
//② 现计划总费用 =  现工作计划总费用x.xx
//③ 费用差额(①-②) =  x.xx

    public int computeChange(UserProjectContext newContext, UserProjectContext oldContext) {
        return super.computeChangeLittle(oldContext, newContext);
    }
}
