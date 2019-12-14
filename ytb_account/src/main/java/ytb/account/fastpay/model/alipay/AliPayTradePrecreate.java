package ytb.account.fastpay.model.alipay;

import com.alipay.api.response.AlipayTradePrecreateResponse;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class AliPayTradePrecreate {

    private AlipayTradePrecreateResponse alipay_trade_precreate_response;

    private String sign;

    public AlipayTradePrecreateResponse getAlipay_trade_precreate_response() {
        return alipay_trade_precreate_response;
    }

    public void setAlipay_trade_precreate_response(AlipayTradePrecreateResponse alipay_trade_precreate_response) {
        this.alipay_trade_precreate_response = alipay_trade_precreate_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
