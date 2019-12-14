package ytb.project.service.impl.pay;

import ytb.project.context.UserProjectContext;

import java.io.UnsupportedEncodingException;


public interface IFlowPayStop extends IFlowPay {


    //5 项目终止支付
    ViewPayResult paPayStop(UserProjectContext context) throws UnsupportedEncodingException;


}