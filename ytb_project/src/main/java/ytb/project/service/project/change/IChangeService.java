package ytb.project.service.project.change;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.ProjectChngPModel;
import ytb.project.service.project.change.impl.IComputeChangeType;
import ytb.project.view.model.ProjectChangeResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public interface IChangeService extends IComputeChangeType {

  // 0 小变更每个阶段都可以有,但一个阶段只能变一次
  ApplyResult newApplyChange(UserProjectContext context);

  // 1 甲方提出变更
  ApplyResult paApplyChange(UserProjectContext context);

  // 2 甲方确认变更
  ProjectChangeResult paAgreeChange(UserProjectContext context,String displayItems,String changeItems) throws
          UnsupportedEncodingException;

  // 3 重新申请 如从大变更变成小变更，资料删除重新生成
  ApplyResult reApplyChange(UserProjectContext context);
  // 4 查询新项目talk表
  ProjectTalkModel getNewProjectTalk(int newProjectId);
  // 5 计算大小变更类型 变更通知书
  int computeChangeReturnType(UserProjectContext newContext, UserProjectContext oldContext );

  // 6 乙方提交变更模板
  void pbSubmitChange(UserProjectContext oldContext,UserProjectContext newContect) throws
          IOException;

  // 7 审核甲方变更
  void paAuditChange(UserProjectContext context, int newTalkId, int status);

  // 8 取消变更，只修改状态
  void cancelChange(UserProjectContext context);

  // 9大变更结项
  void closeProject(UserProjectContext context);

  // 10 甲方去支付
  void gotoPay(UserProjectContext context);

  // 11 取消变更信息 尽量不使用，记录变更历史 removeChange //reCopyReqWorkplan
  void removeChange(UserProjectContext context);

  //12 重新申请时只copy需求计划与通知书
  void reCopyTemplates(UserProjectContext context);
  //13 void backupTemplates(UserProjectContext context);
  //14 void restoreTemplates(UserProjectContext context);

  //15  获取差额， 支付金额
  BigDecimal selectFeeDiff(UserProjectContext newContext);
  //16  大变更结项费用
  ProjectChngPModel selectFeeClose(UserProjectContext oldContext);
  //结项费用=②+（③-④负数取0：多退少不补）+⑤+⑥
  //② 已支付工时费=  原工作计划阶段支付工时费合计x.xx
  //⑤ 支付当前阶段工时费=  当前p阶段的工时费x.xx
  //⑥ 支付补偿金（②+⑤）*毛利率= x.xx
  //③ 采购和加工预算=  （p-3）*总预算/3（p≥4/5，p＜4，则为0）
  //④ 已采购和加工发票总额=   x.xx

}
