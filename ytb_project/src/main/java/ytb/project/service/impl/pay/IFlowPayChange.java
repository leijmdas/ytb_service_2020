package ytb.project.service.impl.pay;

import ytb.project.context.UserProjectContext;
import ytb.project.service.impl.pay.IFlowPay;
import ytb.project.service.impl.pay.PayResult;
import java.io.UnsupportedEncodingException;


public interface IFlowPayChange extends IFlowPay {



    //4 项目变更支付：按新项目支付
    ViewPayResult paPayChange(UserProjectContext newContext, UserProjectContext oldContext, String password) throws UnsupportedEncodingException;


}