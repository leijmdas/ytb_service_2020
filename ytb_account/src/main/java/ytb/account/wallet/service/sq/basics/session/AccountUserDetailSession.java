package ytb.account.wallet.service.sq.basics.session;

import org.apache.ibatis.session.SqlSession;
import ytb.common.ytblog.ProjectYtbLog;
import ytb.account.wallet.dao.AccountUserDetailMapper;
import ytb.account.wallet.dao.AccountUserInnerMapper;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountUserDetail;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.service.sq.basics.session.model.TradeProjectToDetailModel;
import ytb.account.wallet.service.sq.basics.session.model.TradeToDetailModel;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/21
 */
public class AccountUserDetailSession {


    /**
     * 使用 accountTrade 构建流水 自动获取旧流水金
     * BalanceType 要操作的钱
     * BalanceSta:
     * add加钱
     * reduce扣钱
     */


    public static final String BalanceSta_add = "add";

    public static final String BalanceSta_subtract = "subtract";

    /***
     *可用余额
     */
    public static final int BalanceType_balance = 1;
    /**
     * takeout_money
     * 提现冻结费用
     */
    public static final int BalanceType_balance_takeout = 2;
    /**
     * project_balance_agent
     * 项目收入冻结金额
     */
    public static final int BalanceType_project_balance_agent = 3;

    /**
     * project_balance_agent
     * 托管账户余额(支付冻结)
     */
    public static final int BalanceType_balance_agent = 4;

    /**
     * ]
     * 使用订单bal构建流水对象
     */
    public AccountUserDetail getOldInnerBanToNewDetail(AccountTrade record, Date time
            , BigDecimal mo, int BalanceType, String BalanceSta, SqlSession sq) {
        try {


            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(record.getToAcId());
            AccountUserDetail detail = new AccountUserDetail();
            detail.setBalanceType(BalanceType);
            detail.setInnerId(record.getToAcId());
            detail.setTradeId(record.getTradeId());
            detail.setOriginalBalance(toAc.getBalance());
            detail.setTradeBalance(record.getBalance());

            BigDecimal a = null;

            BigDecimal c = null;

            if (BalanceType_balance == BalanceType) {
                a = toAc.getBalance();

            }

            if (BalanceType_balance_takeout == BalanceType) {
                a = toAc.getTakeoutMoney();
            }

            if (BalanceType_project_balance_agent == BalanceType) {
                a = toAc.getIncomeAgent();
            }

            if (BalanceType_balance_agent == BalanceType) {
                a = toAc.getPayoutAgent();
            }


            if (BalanceSta.equals(BalanceSta_add)) {
                c = a.add(mo);
            }
            if (BalanceSta.equals(BalanceSta_subtract)) {
                c = a.subtract(mo);
            }

            detail.setOriginalBalance(a);
            detail.setBalance(c);
            detail.setBalance(toAc.getBalance().add(record.getBalance()));
            detail.setTradeType(record.getTradeType());
            detail.setTradeSubtype(record.getTradeSubtype());
            detail.setInTime(time);


            return detail;
        } catch (Exception e) {
            return null;
        }

    }

    public Boolean getOldInnerBanToNewDetail(AccountUserDetail detail, Date time
            , BigDecimal mo, int BalanceType, String BalanceSta, SqlSession sq) {
        try {


            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(detail.getInnerId());

            detail.setTradeBalance(mo);
            detail.setBalanceType(BalanceType);
            detail.setOriginalBalance(toAc.getBalance());


            BigDecimal a = null;

            BigDecimal c = null;

            if (BalanceType_balance == BalanceType) {
                a = toAc.getBalance();

            }

            if (BalanceType_balance_takeout == BalanceType) {
                a = toAc.getTakeoutMoney();
            }

            if (BalanceType_project_balance_agent == BalanceType) {
                a = toAc.getIncomeAgent();
            }

            if (BalanceType_balance_agent == BalanceType) {
                a = toAc.getPayoutAgent();
            }


            if (BalanceSta.equals(BalanceSta_add)) {
                c = a.add(mo);
            }
            if (BalanceSta.equals(BalanceSta_subtract)) {
                c = a.subtract(mo);
            }

            detail.setOriginalBalance(a);
            detail.setBalance(c);
        //    detail.setBalance(toAc.getBalance().add(mo));
            //     detail.setTradeType(record.getTradeType());
            //    detail.setTradeSubtype(record.getTradeSubtype());
            detail.setInTime(time);


            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            int sta = accountUserDetailMapper.insertSelective(detail);

            if (sta > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return null;
        }

    }

    /**
     * zc
     * @param detail 实体类
     * @param mo 交易金额
     * @param BalanceType  交易金额类型
     * @param BalanceSta   add加钱  reduce扣钱
     * @param sq
     * @return
     */
    public Boolean getOldInnerBanToNewDetail(AccountUserDetail detail,
             BigDecimal mo, int BalanceType, String BalanceSta, SqlSession sq) {
        try {


            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(detail.getInnerId());

            detail.setTradeBalance(mo);
            detail.setBalanceType(BalanceType);
            detail.setOriginalBalance(toAc.getBalance());


            BigDecimal a = null;

            BigDecimal c = null;

            if (BalanceType_balance == BalanceType) {
                a = toAc.getBalance();

            }

            if (BalanceType_balance_takeout == BalanceType) {
                a = toAc.getTakeoutMoney();
            }

            if (BalanceType_project_balance_agent == BalanceType) {
                a = toAc.getIncomeAgent();
            }

            if (BalanceType_balance_agent == BalanceType) {
                a = toAc.getPayoutAgent();
            }


            if (BalanceSta.equals(BalanceSta_add)) {
                c = a.add(mo);
            }
            if (BalanceSta.equals(BalanceSta_subtract)) {
                c = a.subtract(mo);
            }

            detail.setOriginalBalance(a);
            detail.setBalance(c);
            //    detail.setBalance(toAc.getBalance().add(mo));
            //     detail.setTradeType(record.getTradeType());
            //    detail.setTradeSubtype(record.getTradeSubtype());
            Date time = new Date();
            detail.setInTime(time);


            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            int sta = accountUserDetailMapper.insertSelective(detail);

            if (sta > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return null;
        }

    }



    public Boolean insertSelective(AccountUserDetail record, SqlSession sq) {


        try {

            AccountUserDetailMapper accountPfDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            int data = accountPfDetailMapper.insertSelective(record);
            if (data > 0) {
                return
                        true;

            } else {
                return
                        false;
            }
        } finally {
            sq.close();

        }
    }

    public Boolean updateByPrimaryKeySelective(AccountUserDetail record, SqlSession sq) {


        try {

            AccountUserDetailMapper accountPfDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            int data = accountPfDetailMapper.updateByPrimaryKeySelective(record);
            if (data > 0) {
                return
                        true;

            } else {
                return
                        false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean getOldInnerBanToNewDetailSq(TradeToDetailModel model, SqlSession sq) {
        try {

            int sta = 0;
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(model.getId());

            if (toAc == null) {
                throw new YtbError(YtbError.CODE_FAIL, "流水更新失败,未取得用户信息");
            }


            AccountUserDetail detail = new AccountUserDetail();
            detail.setBalanceType(model.getBalanceType());
            detail.setInnerId(model.getId());
            detail.setTradeId(model.getAccountTrade().getTradeId());

            detail.setTradeBalance(model.getAccountTrade().getBalance());

            BigDecimal a = null;

            BigDecimal c = null;

            if (BalanceType_balance == model.getBalanceType()) {
                a = toAc.getBalance();


            }

            if (BalanceType_balance_takeout == model.getBalanceType()) {
                a = toAc.getTakeoutMoney();

            }

            if (BalanceType_project_balance_agent == model.getBalanceType()) {
                a = toAc.getIncomeAgent();

            }

            if (BalanceType_balance_agent == model.getBalanceType()) {
                a = toAc.getPayoutAgent();

            }

            if (model.getBalanceSta().equals(BalanceSta_add)) {
                c = a.add(model.getTomoney());
            }
            if (model.getBalanceSta().equals(BalanceSta_subtract)) {
                c = a.subtract(model.getTomoney());
            }

            detail.setOriginalBalance(a);
            detail.setBalance(c);
            //     detail.setBalance(toAc.getBalance().add(record.getBalance()));
            detail.setTradeType(model.getAccountTrade().getTradeType());
            detail.setTradeSubtype(model.getAccountTrade().getTradeSubtype());
            detail.setInTime(model.getTime());
            detail.setStatus(model.getAccountTrade().getStatus());


            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            sta = accountUserDetailMapper.insertSelective(detail);

            if (sta > 0) {
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            return false;
        }


    }

    public static Boolean getOldInnerBanToNewDetailSq(TradeProjectToDetailModel model, SqlSession sq) {
        try {

            int sta = 0;
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(model.getId());

            if (toAc == null) {
                ProjectYtbLog.logError("TradeProjectToDetailModel model",model);
                throw new YtbError(YtbError.CODE_FAIL, "流水更新失败,未取得用户信息");
            }


            AccountUserDetail detail = new AccountUserDetail();
            detail.setBalanceType(model.getBalanceType());
            detail.setInnerId(model.getId());
            detail.setTradeId(model.getAccountTrade().getTradeId());

            detail.setTradeBalance(model.getAccountTrade().getBalance());

            BigDecimal a = null;

            BigDecimal c = null;

            if (BalanceType_balance == model.getBalanceType()) {
                a = toAc.getBalance();


            }

            if (BalanceType_balance_takeout == model.getBalanceType()) {
                a = toAc.getTakeoutMoney();

            }

            if (BalanceType_project_balance_agent == model.getBalanceType()) {
                a = toAc.getIncomeAgent();

            }

            if (BalanceType_balance_agent == model.getBalanceType()) {
                a = toAc.getPayoutAgent();

            }

            if (model.getBalanceSta().equals(BalanceSta_add)) {
                c = a.add(model.getTomoney());
            }
            if (model.getBalanceSta().equals(BalanceSta_subtract)) {
                c = a.subtract(model.getTomoney());
            }

            detail.setOriginalBalance(a);
            detail.setBalance(c);
            //     detail.setBalance(toAc.getBalance().add(record.getBalance()));
            detail.setTradeType(model.getAccountTrade().getTradeType());
            detail.setTradeSubtype(model.getAccountTrade().getTradeSubtype());
            detail.setInTime(model.getTime());
            detail.setStatus(model.getAccountTrade().getStatus());


            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            sta = accountUserDetailMapper.insertSelective(detail);

            if (sta > 0) {
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            return false;
        }


    }
}
