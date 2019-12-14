package ytb.account.wallet.service.AccountConst;

import ytb.common.context.model.YtbError;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * mofied by:leijm
 * Date:2019/2/27
 * modified date :2019/3/6
 */
public class AccountConst {
    //0 初始
    public static final Byte STATUS_INIT = 0;
    //1 提交待审
    public static final Byte STATUS_SUBMIT = 0;
    //2 未过
    public static final Byte STATUS_NOPASS = 0;
    //3通过
    public static final Byte STATUS_AUDIT_PASS = 3;
    //4等待外部接口操作结果中
    public static final Byte STATUS_WAITING = 4;
    //10操作成功
    public static final Byte STATUS_OK = 10;
    //11操作失败
    public static final Byte STATUS_FAIL = 11;


    public static final Short bankCardAcType = 1;
    public static final Short weChatAcType = 2;
    public static final Short aliPayAcType = 3;
    public static final Integer bankCardPfInnerId = Integer.valueOf(bankCardAcType);
    public static final Integer weChatPfInnerId = Integer.valueOf(weChatAcType);
    public static final Integer aliPayPfInnerId = Integer.valueOf(aliPayAcType);
    public static final Short generalAccount = 801;
    public static final Short serviceFee = 802;
    public static final Short chargeFee = 803;
    public static final Short taxFee = 804;

    /*外部帐户与虚拟帐户交易子类型(充值 提现)：
    1银行
    2微信
    3支付宝 */
    public static final Short TRADE_SUBTYPE_BANK =  bankCardAcType;
    public static final Short TRADE_SUBTYPE_WE_CHAT = weChatAcType;
    public static final Short TRADE_SUBTYPE_ALIPAY = aliPayAcType;

    //支出冻结款和 余额
    public static final Short TRADE_SUBTYPE_BALANCE_2_FROZEN = 100;
    //收入冻结款和 余额
    public static final Short TRADE_SUBTYPE_BALANCE_2_FROZEN_INCOME = 101;


    //冻结款与冻结款
    public static final Short TRADE_SUBTYPE_FROZEN_2_FROZEN = 102;
    //余额与余额
    public static final Short TRADE_SUBTYPE_BALANCE_2_BALANCE = 103;

    public static void checkTradeSubtype(Short subType){
        if(subType.equals(TRADE_SUBTYPE_BANK)){
            return;
        }
        if(subType.equals(TRADE_SUBTYPE_WE_CHAT)){
            return;
        }
        if(subType.equals(TRADE_SUBTYPE_ALIPAY)){
            return;
        }
        if(subType.equals(TRADE_SUBTYPE_BALANCE_2_FROZEN)){
            return;
        }
        if(subType.equals(TRADE_SUBTYPE_FROZEN_2_FROZEN)){
            return;
        }
        if(subType.equals(TRADE_SUBTYPE_BALANCE_2_BALANCE)){
            return;
        }

        throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG,"tradeSubtype");

    }


    //未结算
    public static final Boolean cal_flag_no = false;




/*
trade_type
个人、公司账户
10-充值
11
20-提现
31
30-收入
31-收入退款
40-支出
41-支出退款
50-转帐
转帐退款

trade_subtype

业务填写
外部帐户与虚拟帐户交易子类型(充值 提现)：
1银行
2微信
3支付宝
虚拟帐户与虚拟帐户交易子类型（收入支出）：
101余额与冻结款（同一人帐户）
收入：冻结款-》余额
支出：余额-》冻结款
102冻结款与冻结款
（甲乙方）
收入：冻结款-》冻结款
支出：冻结款《-冻结款
103：余额与余额
（甲乙方）
余额与余额如业务没需求暂不做
甲乙方间只有冻结款的交易或者余额间的交易
xxx（甲乙方）冻结款与余额间交易不支持,
因为冻结款是不能直接使用的，通过流程控制。
余额与余额是直接使用,平台没有控制和信用保证。
帐号，交易金额，对方帐号（102，103 是平台转帐）
由于有多人，结构变成多条交易记录实现转账，此时项目产生一个订单号才能知道是一个订单
status
0 初始
1待审
2未过
3 通过
4 wait
10成功
11失败

*/


}
