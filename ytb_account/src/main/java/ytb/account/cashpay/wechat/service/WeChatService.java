package ytb.account.cashpay.wechat.service;


import ytb.account.fastpay.model.fast.OrderQueryRequest;
import ytb.account.fastpay.model.wechat.WxOrderQueryResponse;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/1/21
 */
public interface WeChatService {
    //微信二维码支付
    WxOrderQueryResponse orderQr(OrderQueryRequest orderQueryRequest);


}
