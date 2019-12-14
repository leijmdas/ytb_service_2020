package ytb.account.wallet.service.sq.business.user;

import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.AccountUserDetail;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.account.wallet.model.project.TransferInfo;
import ytb.account.wallet.pojo.*;
import ytb.account.wallet.service.sq.business.user.pojo.projectRefunds;

import java.math.BigDecimal;
import java.util.List;

/**
 * 内部资金之间钱
 * 项目流程方法
 * Copyright@ CchuaprojectByPAFirstPay
 * Author:Cchua
 * Date:2019/2/19
 */
public interface ProjecTransactionService {


    //余额到余额支出转账 协助金，感谢金等 A余额-》B余额 103
    int b2bPayTransfer(TransferInfo transferInfo);

    int b2fPaPayProject(TradeInfo info, Byte serviceType);

    //项目预付款 甲方余额->冻结款项 101
    int b2fPaPayProject(TradeInfo tradeInfo);

    //协助
    int b2fPaPayProjectAssist(TradeInfo info);

    //阶段支付f2f 甲方-》乙方冻结款 102
    ProjectBalanceProjectAgent f2fTransferPayPa2Pb(TradeInfo outInfo, List<TradeInfo> toInfos);

    ProjectBalanceProjectAgent f2fTransferPayPa2PbAssist(TradeInfo outInfo, List<TradeInfo> toInfos);

    ProjectBalanceProjectAgent f2fTransferPayPa2Pb(TradeInfo outTradeInfo, List<TradeInfo> toTradeInfos, Byte servieType);

    List<AccountTradeProject> f2bProjectPbUnfreeze(List<TradeInfo> tradeInfos);

    List<AccountTradeProject> f2bProjectPaUnfreeze(List<TradeInfo> tradeInfos, Byte serviceType);
    List<AccountTradeProject> f2bProjectPbUnfreeze(List<TradeInfo> tradeInfos, Byte serviceType);

    /**
     * 甲方钱减少->系统
     * 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     * 当前使用场景:
     * 甲方首次付款
     *
     * @param accountTrade
     */
    int b2fPaPayProjectFirst(AccountTradeProject accountTrade);

    /**
     * 甲方结算-》乙方钱
     * 当前使用场景：
     * 项目付款后 阶段支付  甲方一个人给多个乙方钱
     *
     * @param pbOutAc
     * @param pbAgentList
     */

    ProjectBalanceProjectAgent phasePayPa2Pb(ProjectBalanceOutAc pbOutAc, List<ProjectBalanceToAc> pbAgentList);

    ProjectBalanceProjectAgent phasePayPa2Pb(Byte serviceType, ProjectBalanceOutAc pbOutAc, List<ProjectBalanceToAc> pbAgentList);

    /**
     * 甲方结算-》乙方钱
     * 当前使用场景：
     * 项目冻结款入账
     * 乙方项目金额冻结--》总金额
     */

    List<AccountTradeProject> projectPbUnfreeze(List<ProjectBalanceToAc> list);

    List<AccountTradeProject> projectPaUnfreeze(List<ProjectBalanceToAc> list, Byte serviceType);
    List<AccountTradeProject> projectPbUnfreeze(List<ProjectBalanceToAc> list, Byte serviceType);

    /**
     * balance-减少
     * 业务场景:
     * 提现审核 提现冻结
     **/

    int withdrawCash(AccountTrade record, Integer innerId, BigDecimal balance);


    /**
     * 直接转账到账  甲方流水- -， 资金- - 支出++ , 乙方收入++资金++ 收入++
     **/
    int transferService(AccountTradeProject accountTrade);

    int transferService(AccountTrade accountTrade);

    /**
     * 项目阶段退款
     **/

    Boolean projectRefund(Integer tradeId, BigDecimal RefundM);


    /**
     * 项目阶段退款多个
     **/
    List<Integer> projectRefunds(List<projectRefunds> projectRefund);

    /***
     *
     * 冻结金入账
     * balance_agent -》balance
     * */

    Integer balanceAgentToBalance(Integer innerId, BigDecimal RefundM);
    //计算方法


    /**
     * 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     * * 冻结金额
     * * 金额balance--
     * * 支付冻结balance_agent++
     * * 总出账balance_out ++++
     **/
    Integer frozenAmount(AccountTradeProject accountTrade);


    /**
     * 甲方：balance-减少    balance_out    支出增加    冻结资金   增加    +（ 产生订单 甲方冻结金额）
     * * 冻结金额
     * * 金额balance--
     * * 支付冻结balance_agent++
     * * 总出账balance_out ++++
     **/
    Integer frozenAmount(AccountTrade accountTrade);


    /**
     * 直接保存accountTrade  accountUserDetail
     */
    Integer insertTradeAndUserDetailService(AccountTradeProject accountTrade, AccountUserDetail accountUserDetail);


//暂时用不上

    /**
     * customerRefund
     */
    int customerRefund(AccountTrade data, Integer outAcId, Integer inAcId, BigDecimal balance);

    //项目完结冻结资金-》总金额
    ProjectBalanceAgent projectBalanceRefundToList(ProjectBalanceOutAc projectBalanceOutAc, List<ProjectBalanceToAc> projectBalanceAgentList);

    /***
     *  zc
     * 项目阶段退款
     * 甲方支付项目预付款-退款
     * 从甲方的钱包冻结款转到余额
     * tradeId:预付款订单的trade_id
     * refund:退款金额
     * UserId:用户ID
     * companyId:公司ID
     * localip:IP地址
     * balance_agent -》balance
     * 返回Integer是用户交易记录表新增记录的trade_id
     * */
    // Integer balanceAgentToBalance(Integer tradeId, BigDecimal refund, Integer userId, Integer companyId,
    //  String payPassword);
    //Integer balanceAgentToBalance(TradeInfo tradeInfo);

    /**
     * 免密码 甲方解冻
     */
    Integer paPayoutAgent2Balance(TradeInfo tradeInfo);


    /**
     * zc
     * 转帐 退款
     * 从甲方的钱包冻结款转到乙方和组员的冻结款的退款
     * 从乙方和组员的冻结款到甲方的钱包冻结款转
     * projectRefunds - accountTrade:从甲方的钱包冻结款转到乙方和组员的冻结款订单的trade_id
     * projectRefunds - money:退款金额
     * UserId:用户ID
     * companyId:公司ID
     * 乙方的 project_balance_agent --》甲方的 balance_agent
     * 返回List<Integer>是用户交易记录表新增记录的trade_id
     **/
    //List<Integer> projectRefunds(List<projectRefunds> projectRefundsList, TradeInfo info);



    /**
     * 免密码
     */
    List<Integer> projectRefundsNoPwd(List<TradeInfo> outInfos, TradeInfo toInfo);


    /**
     * 校验密码
     */
    boolean checkAccountPassword(int userId, int companyId,
                                 String payPassword);
    /**
     * 获取数据
     */
    AccountUserInner getAccountInfo(int userId, int companyId,
                                    String payPassword);

    /**
     * 获取数据免密
     */
    AccountUserInner  getAccountInfo(int userId, int companyId);


}