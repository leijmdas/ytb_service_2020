package ytb.project.service.project.change.impl;

import ytb.project.context.UserProjectContext;

public interface IComputeChangeType {
      int computeChangeType(String changeItems) ;

    // 1 计算大小变更类型
    // int computeChangeType(int docType, UserProjectContext oldContext, UserProjectContext newContext);

    // 2 计算变更通知书
    int computeChange(UserProjectContext newContext, UserProjectContext oldContext);
    // 3
    void checkBigChange_valid(UserProjectContext oldContext);

}
