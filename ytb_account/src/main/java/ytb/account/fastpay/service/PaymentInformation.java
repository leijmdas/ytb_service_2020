package ytb.account.fastpay.service;


import ytb.account.fastpay.model.fast.PayOrderGenerate;
import ytb.account.fastpay.model.fast.PaymentInformationModel;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public interface PaymentInformation {

    PaymentInformationModel qrCodePay(PayOrderGenerate orderGenerate, String mode);




}
