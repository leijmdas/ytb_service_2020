package ytb.account.wallet.service.AccountConst;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/20
 */
public class UserOutConst {
    /**
     * account_type	账户类型 	Tinyint	N			Y				1.银行
     * 2.支付宝
     * 3.微信
     */
    public static final Integer account_type_Bank = 1;

    public static final Integer account_type_Alipay = 2;

    public static final Integer account_type_WeChat = 3;


    /**
     * status	账户状态	Tinyint								0-有效
     * 1-无效(解除绑定)
     */


    public static final byte status_effective = 0;

    public static final byte status_invalid = 1;

}
