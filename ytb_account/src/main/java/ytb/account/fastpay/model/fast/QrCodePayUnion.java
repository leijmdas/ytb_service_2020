package ytb.account.fastpay.model.fast;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class QrCodePayUnion {

    private PaymentInformationModel aliPayQrCode;

    private PaymentInformationModel weChatQrCode;

    public PaymentInformationModel getAliPayQrCode() {
        return aliPayQrCode;
    }

    public void setAliPayQrCode(PaymentInformationModel aliPayQrCode) {
        this.aliPayQrCode = aliPayQrCode;
    }

    public PaymentInformationModel getWeChatQrCode() {
        return weChatQrCode;
    }

    public void setWeChatQrCode(PaymentInformationModel weChatQrCode) {
        this.weChatQrCode = weChatQrCode;
    }


}
