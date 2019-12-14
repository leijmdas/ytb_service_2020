package ytb.account.fastpay.model.alipay;

import com.alipay.api.response.AlipayTradeRefundResponse;


public class AlipayTradeRefund {
    private AlipayTradeRefundResponse alipay_trade_refund_response;

    private  String sign;

    public AlipayTradeRefundResponse getAlipay_trade_refund_response() {
        return alipay_trade_refund_response;
    }

    public void setAlipay_trade_refund_response(AlipayTradeRefundResponse alipay_trade_refund_response) {
        this.alipay_trade_refund_response = alipay_trade_refund_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
