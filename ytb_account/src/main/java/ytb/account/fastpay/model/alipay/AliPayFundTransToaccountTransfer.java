package ytb.account.fastpay.model.alipay;

import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class AliPayFundTransToaccountTransfer {
    private AlipayFundTransToaccountTransferResponse alipay_fund_trans_toaccount_transfer_response;

    private  String sign;

    public AlipayFundTransToaccountTransferResponse getAlipay_fund_trans_toaccount_transfer_response() {
        return alipay_fund_trans_toaccount_transfer_response;
    }

    public void setAlipay_fund_trans_toaccount_transfer_response(AlipayFundTransToaccountTransferResponse alipay_fund_trans_toaccount_transfer_response) {
        this.alipay_fund_trans_toaccount_transfer_response = alipay_fund_trans_toaccount_transfer_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
