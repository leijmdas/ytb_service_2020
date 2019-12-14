package ytb.account.cashpay.wechat.service.impl;



import ytb.account.cashpay.wechat.service.WeChatService;
import ytb.account.fastpay.model.fast.OrderQueryRequest;
import ytb.account.fastpay.model.wechat.WechatConfigure;
import ytb.account.fastpay.model.wechat.WxOrderQueryResponse;

public class WeChatServiceImpl implements WeChatService {

    WechatConfigure wechatConfigure = null;

    public WeChatServiceImpl(WechatConfigure configure) {
        wechatConfigure = configure;
    }

    @Override
    public WxOrderQueryResponse orderQr(OrderQueryRequest orderQueryRequest) {
        return null
                ;
    }



}
