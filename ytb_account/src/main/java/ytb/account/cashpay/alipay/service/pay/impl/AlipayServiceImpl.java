package ytb.account.cashpay.alipay.service.pay.impl;



import ytb.account.cashpay.alipay.pojo.AliPayTransferAccounts;
import ytb.account.cashpay.alipay.service.pay.AlipayService;
import ytb.account.fastpay.model.alipay.AliPayConfigure;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/1/21
 */
public class AlipayServiceImpl implements AlipayService {

    AliPayConfigure aliPayConfigure;

    public AlipayServiceImpl(AliPayConfigure configure) {
        aliPayConfigure = configure;
    }




    //转账
    @Override
    public String transferFacility(AliPayTransferAccounts aliPayRefundModel) {

        return new AliPayQuickService().transferFacility(aliPayRefundModel, aliPayConfigure);

    }



}
