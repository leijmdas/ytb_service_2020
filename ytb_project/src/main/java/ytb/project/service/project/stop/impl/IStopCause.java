package ytb.project.service.project.stop.impl;

import ytb.project.context.UserProjectContext;

public interface IStopCause {


    // 200	甲方取消项目 final static Short SUBTYPE_PA_CANCEL = 200;
    void paCancel(UserProjectContext context, StopType stopType);
    // 300	甲方提出 乙方交付不合格    public final static Short SUBTYPE_PB_OFFGRADE = 300;
    // 300->500
    // 500	甲方不满意	（甲方超过3次审核,仅用于造型界面类）
    //public final static Short SUBTYPE_PA_Unsatisfy = 500;
    //void paUnsatisfy ();

    void paOffgrade(UserProjectContext context, StopType stopType);
    // 400	甲方提出 延期率>500%
    void paDelay500(UserProjectContext context, StopType stopType);
    //public final static Short SUBTYPE_DELAY_500 = 400;


    // 600	乙方不满意
    //public final static Short SUBTYPE_PB_Unsatisfy = 600;
    void pbUnsatisfy(UserProjectContext context, StopType stopType);

}
