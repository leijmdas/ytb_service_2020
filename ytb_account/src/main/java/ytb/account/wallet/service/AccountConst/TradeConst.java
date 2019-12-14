package ytb.account.wallet.service.AccountConst;

import ytb.common.context.model.YtbError;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/20
 */
public class TradeConst {
    /**
     * ac_type	账户类型	int	N
     * 1银行卡
     * 2财富通
     * 3支付宝
     * 平台虚拟账户
     * 801平台虚拟账户--总帐户
     * 802平台虚拟账户--服务费
     * 803平台虚拟账户--手续费
     * 804平台虚拟账户--税费
     */
    public static final Short ac_type_bank = AccountConst.TRADE_SUBTYPE_BANK;
    public static final Short ac_type_WeChat = AccountConst.TRADE_SUBTYPE_WE_CHAT;
    public static final Short ac_type_alipay = AccountConst.TRADE_SUBTYPE_ALIPAY;
    public static final Integer ac_type_innerAc = 801;
    public static final Integer ac_type_serviceFee = 802;
    public static final Integer ac_type_chargeFee = 803;
    public static final Integer ac_type_taxFee = 804;

    /**
     * trade_type	交易类型	Int	 个人、公司账户
     * 10-充值 外部帐户至钱包
     * 11-充值退款
     * 20-提现 钱包至外部帐户
     * 21=提现退款
     * 30-收入
     * 31-收入退款
     * 40-支出
     * 41-支出退款
     * 50-转帐
     * 51-转帐退款
     */
    //10-充值
    public static final short TRADE_TYPE_RECHARGE = 10;
    //1-充值退款
    public static final short TRADE_TYPE_RECHARGE_REFUND = 11;
    //20-提现
    public static final Short TRADE_TYPE_Withdraw_Deposit = 20;
    //21=提现退款
    public static final Short TRADE_TYPE_Withdraw_Deposit_REFUND = 21;

   //收入：别人的存入冻结，自己的冻结存入余额
    public static final Short TRADE_TYPE_INCOME = 30;
    //收入退款
    public static final Short TRADE_TYPE_INCOME_REFUND = 31;
    //自己的余额到冻结为支出，自己的冻结至别人的冻结为支出
    public static final Short TRADE_TYPE_PAYOUT = 40;
    //收入：支出退款
    public static final Short TRADE_TYPE_PAYOUT_REFUND = 41;
    //转帐
    public static final Short TRADE_TYPE_TRANSFER = 50;
    //转帐退款
    public static final Short TRADE_TYPE_TRANSFER_REFUND = 51;

    public static void checkTradeType(Short tradeType) {
        if (tradeType.equals(TRADE_TYPE_RECHARGE)) {
            return;
        }
        if (tradeType.equals(TRADE_TYPE_RECHARGE_REFUND)) {
            return;
        }
        if (tradeType.equals(TRADE_TYPE_Withdraw_Deposit)) {
            return;
        }
        if (tradeType.equals(TRADE_TYPE_Withdraw_Deposit_REFUND)) {
            return;
        }
        if (tradeType.equals(TRADE_TYPE_INCOME)) {
            return;
        }
        if (tradeType.equals(TRADE_TYPE_INCOME_REFUND)) {
            return;
        }
        if (tradeType.equals(TRADE_TYPE_PAYOUT)) {
            return;
        }
        if (tradeType.equals(TRADE_TYPE_PAYOUT_REFUND)) {
            return;
        }
        if (tradeType.equals(TRADE_TYPE_TRANSFER)) {
            return;
        }
        if (tradeType.equals(TRADE_TYPE_TRANSFER_REFUND)) {
            return;
        }
        throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "tradeType");

    }

    /**
     * trade_subtype
     * 交易子类型
     *
     */


    /**
     * service_type
     * 1--项目款
     * 2--违约金
     * 3--协助金
     * 4--感谢金
     * 5-cash
     * 6-stop
     * 7-change
     */
    // start
    public static final Byte SERVICE_TYPE_START = 1;
    // 1--项目款
    public static final Byte SERVICE_TYPE_project = SERVICE_TYPE_START;
    // 2--违约金
    public static final Byte SERVICE_TYPE_penalty = 2;
    // 3--协助金
    public static final Byte SERVICE_TYPE_assist = 3;
    //4--洽谈感谢金
    public static final Byte SERVICE_TYPE_thank = 4;
    //外部与钱包间转帐 现金
    public static final Byte SERVICE_TYPE_cash = 5;
    //项目补偿款
    public static final Byte SERVICE_TYPE_project_REPAY = 6;
    //项目变更款
    public static final Byte SERVICE_TYPE_project_CHANGE = 7;
    //项目终止款
    public static final Byte SERVICE_TYPE_project_STOP = 8;

    // end
    public static final Byte SERVICE_TYPE_END = SERVICE_TYPE_project_STOP ;

    public static void checkServiceType(Byte servieType) {
        //YtbUtils.testLog(servieType);

        if (servieType.compareTo(SERVICE_TYPE_START)>=0
            && servieType.compareTo(SERVICE_TYPE_END)<=0) {
            return ;
        }

        throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "servieType");

    }

    /**
     * 0 初始
     * 1待审
     * 2未过
     * 3 通过
     * 10成功
     * 11失败
     * <p>
     * * 0 initial
     * <p>
     * * 1 pending trial
     * <p>
     * * 2 has not yet passed
     * <p>
     * * 3 pass
     * <p>
     * * 10 success
     * <p>
     * * 11 failure
     */

    public static final byte status_initial = AccountConst.STATUS_INIT;


    public static final byte status_pending_trial = AccountConst.STATUS_SUBMIT;


    public static final byte status_has_not_passed = AccountConst.STATUS_NOPASS;


    public static final byte status_passed = AccountConst.STATUS_AUDIT_PASS;


    public static final byte status_success = AccountConst.STATUS_OK;


    public static final byte status_failure = AccountConst.STATUS_FAIL;


    public static final Byte STATUS_AUDIT_PASS = 3;
    //4等待外部接口操作结果中
    //public static final Byte STATUS_WAITING = 4;

}
