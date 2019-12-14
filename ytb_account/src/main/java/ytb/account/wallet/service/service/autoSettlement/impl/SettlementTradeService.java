package ytb.account.wallet.service.service.autoSettlement.impl;

import org.apache.ibatis.session.SqlSession;

import ytb.account.wallet.dao.AccountPfDetailMapper;
import ytb.account.wallet.dao.AccountTradeMapper;
import ytb.account.wallet.dao.transaction.AccountPfInnerBalanceMapper;
import ytb.account.wallet.dao.AccountPfInnerMapper;
import ytb.account.wallet.model.*;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeProjectServiceImpl;
import ytb.account.wallet.service.sq.business.pf.account.impl.AccountTradePfServiceImpl;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.account.wallet.tool.MyBatisUtil;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/18
 */
public class SettlementTradeService {
    private static Integer intDate(Date dt) {

        String s = DateFormat.getDateInstance(DateFormat.DEFAULT).format(dt).replaceAll("-", "");
        return Integer.parseInt(s);
    }

    private static Date oldOneDay(Date dt) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date d = cal.getTime();
        return d;
    }


    public boolean SettlementTrade() {


//获取结算信息

        AccountTradeExample example = new AccountTradeExample();
        AccountTradeExample.Criteria criteria = example.createCriteria();
        //   criteria.andIsAEqualTo(true);
        criteria.andTradeTypeEqualTo(TradeConst.TRADE_TYPE_PAYOUT);
        criteria.andTradeSubtypeEqualTo(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN);
        criteria.andServiceTypeEqualTo(TradeConst.SERVICE_TYPE_cash);
        criteria.andStatusEqualTo(TradeConst.status_success);
        criteria.andCalFlagEqualTo(false);
        criteria.andTermIdEqualTo(intDate(oldOneDay(new Date())));
        //     criteria.andTradeNoPreEqualTo(String.valueOf(0));

        List<AccountTrade> accountTrade = getUserTrade(example);


        Date date = new Date();
        if (accountTrade.size() > 0) {
            for (int i = 0; i < accountTrade.size(); i++) {
                SqlSession sq = MyBatisUtil.getSession(false);
                sq.getConfiguration().setDefaultStatementTimeout(4);
                SqlSession sqBlance = MyBatisUtil.getSession(false);
                sqBlance.getConfiguration().setDefaultStatementTimeout(4);
                //-税费
                if (accountTrade.get(i).getTax().compareTo(BigDecimal.ZERO) > 0) {

                    Short acType = AccountConst.taxFee;
                    Boolean sta = tradeFlowByPf(accountTrade.get(i), date, acType, sq, sqBlance);
                    if (sta == false) {
                        sq.rollback();
                        sq.close();
                        sqBlance.rollback();
                        sqBlance.close();
                        continue;
                    }

                }

                if (accountTrade.get(i).getBalance().compareTo(BigDecimal.ZERO) > 0) {

                    Short acType = AccountConst.generalAccount;
                    Boolean sta = tradeFlowByPf(accountTrade.get(i), date, acType, sq, sqBlance);
                    if (sta == false) {
                        sq.rollback();
                        sq.close();
                        sqBlance.rollback();
                        sqBlance.close();
                        continue;
                    }

                }


//手续费
                if (accountTrade.get(i).getFee().compareTo(BigDecimal.ZERO) > 0) {

                    Short acType = AccountConst.chargeFee;
                    Boolean sta = tradeFlowByPf(accountTrade.get(i), date, acType, sq, sqBlance);
                    if (sta == false) {
                        sq.rollback();
                        sq.close();
                        sqBlance.rollback();
                        sqBlance.close();
                        continue;
                    }

                }

                if (accountTrade.get(i).getServiceFee().compareTo(BigDecimal.ZERO) > 0) {

                    Short acType = AccountConst.chargeFee;
                    Boolean sta = tradeFlowByPf(accountTrade.get(i), date, acType, sq, sqBlance);
                    if (sta == false) {
                        sq.rollback();
                        sq.close();
                        sqBlance.rollback();
                        sqBlance.close();
                        continue;
                    }

                }

//                AccountTrade newAccountTrade = new AccountTrade();
//                newAccountTrade.setTradeId(accountTrade.get(i).getTradeId());
//                newAccountTrade.setCalFlag(true);
//                //     AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
//
//
//                AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
//
//                int sta = accountTradeMapper.updateByPrimaryKeySelective(newAccountTrade);

//完成结算状态进行改变
                int sta = setCalFlag(accountTrade.get(i).getTradeId(), sq);


                //          accountTradeService.updateByPrimaryKeySelective(newAccountTrade);
                if (sta > 0) {

                    sq.commit();
                    sq.close();
                    sqBlance.commit();
                    sqBlance.close();

                } else {
                    sq.rollback();
                    sq.close();
                    sqBlance.rollback();
                    sqBlance.close();

                    continue;

                }


            }
        }

        return true;
    }


    public int setCalFlag(Integer tradeId, SqlSession sq) {

        AccountTrade newAccountTrade = new AccountTrade();
        newAccountTrade.setTradeId(tradeId);
        newAccountTrade.setCalFlag(true);
        //     AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();


        AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);

        int sta = accountTradeMapper.updateByPrimaryKeySelective(newAccountTrade);
        return sta;
    }


    private List<AccountTrade> getUserTrade(AccountTradeExample example) {
        try {


            AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();


            return accountTradeService.selectByExample(example);
        } catch (Exception e) {
            throw new YtbError("获取结算信息失败");

        }
    }


    public Boolean tradeFlowByPf(AccountTrade accountTrade,
                                 Date date, Short acType, SqlSession sq, SqlSession qBlance) {
        try {


            Boolean tradeFlow = false;


            AccountTradePfServiceImpl accountTradePfService = new AccountTradePfServiceImpl();

//获取innerid 讲元数据进行相关操作
            Integer pfInnerId = accountTradePfService.acTypeToPfInnerIdInt(acType);

//创建pf交易订单
            AccountPfTrade accountPfTrade = accountTradePfService.pfTrade(accountTrade, pfInnerId, acType, sq);

            if (accountPfTrade == null) {
                qBlance.rollback();
                qBlance.close();
                sq.rollback();
                sq.close();
            }


//构建PF流水
            Boolean sta = pfDetail(accountTrade, accountPfTrade, pfInnerId, acType, date, sq, qBlance);


            // AccountPfInnerBalanceMapper accountPfInnerBalanceMapper = sq.getMapper(AccountPfInnerBalanceMapper.class);



/*
        int sta = 0;
        int bansta = 0;
        if (acType == AccountConst.bankCardAcType || acType == AccountConst.aliPayAcType || acType == AccountConst.weChatAcType) {

            sta = accountPfInnerBalanceMapper.updateTakeoutMoneyReduce(pfInnerId, accountPfTrade.getBalance()
                    , Integer.valueOf(acType));
            bansta = accountPfInnerBalanceMapper.updateBalanceReduceByInnerId(pfInnerId,
                    accountPfTrade.getBalance(), Integer.valueOf(acType));
            if (sta > 0 & bansta > 0) {
                sq.commit();
                tradeFlow = true;
            }

        } else if (acType == AccountConst.chargeFee || acType == AccountConst.taxFee) {

            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
                //   sta = accountPfInnerBalanceMapper.updateTakeoutMoneyIncrease(pfInnerId, accountPfTrade.getBalance(), acType);
                bansta = accountPfInnerBalanceMapper.updateBalanceIncreaseByInnerId(pfInnerId,
                        accountPfTrade.getBalance(), Integer.valueOf(acType));
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
                //sta = accountPfInnerBalanceMapper.updateTakeoutMoneyReduce(pfInnerId, accountPfTrade.getBalance(), acType);
                bansta = accountPfInnerBalanceMapper.updateBalanceReduceByInnerId(pfInnerId,
                        accountPfTrade.getBalance(), Integer.valueOf(acType));
            } else {
                sq.rollback();
                tradeFlow = false;
            }
            if (bansta > 0) {
                sq.commit();
                tradeFlow = true;
            }


        } else {
            sq.rollback();
            tradeFlow = false;
        }*/


            return sta;
        } catch (Exception e) {
            sq.rollback();
            sq.close();
            return false;
        }
    }


    /**
     * 构建流水   这个为
     *
     * <p>
     * /////现金的项目款结算方式
     * <p>
     * ///确认是甲方的
     */
    public Boolean pfDetail(AccountTrade accountTrade, AccountPfTrade accountPfTrade,
                            Integer pfInnerId, Short acType, Date date, SqlSession sq, SqlSession sqBlance) {
        AccountPfDetail accountPfDetail = new AccountPfDetail();

        AccountPfInnerMapper accountPfInnerMapper = sq.getMapper(AccountPfInnerMapper.class);
        AccountPfInnerBalanceMapper pfInnerBalanceMapper = sqBlance.getMapper(AccountPfInnerBalanceMapper.class);
        AccountPfInner accountPfInner = accountPfInnerMapper.selectByPrimaryKey(pfInnerId);


        accountPfDetail.setOriginalBalance(accountPfInner.getBalance());

        int blancesta = 0;

        if (acType == AccountConst.bankCardAcType) {

            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit
                    || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
                accountPfDetail.setTradeBalance(accountTrade.getBalance());
                blancesta = pfInnerBalanceMapper.updateBalanceReduceByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE
                    || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
                blancesta = pfInnerBalanceMapper.updateBalanceIncreaseByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            }


        } else if (acType == AccountConst.aliPayAcType) {
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit
                    || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
                accountPfDetail.setTradeBalance(accountTrade.getBalance());
                blancesta = pfInnerBalanceMapper.updateBalanceReduceByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE
                    || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
                blancesta = pfInnerBalanceMapper.updateBalanceIncreaseByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            }
        } else if (acType == AccountConst.weChatAcType) {
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit
                    || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
                accountPfDetail.setTradeBalance(accountTrade.getBalance());
                blancesta = pfInnerBalanceMapper.updateBalanceReduceByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE
                    || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
                blancesta = pfInnerBalanceMapper.updateBalanceIncreaseByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            }
        }

        ////上面代重复 为避免有其他业务场景 请不要||


        else if (acType == AccountConst.generalAccount) {


            accountPfDetail.setTradeBalance(accountTrade.getBalance());
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
                blancesta = pfInnerBalanceMapper.updateBalanceIncreaseByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
                blancesta = pfInnerBalanceMapper.updateBalanceReduceByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            }


        } else if (acType == AccountConst.serviceFee) {
            accountPfDetail.setTradeBalance(accountTrade.getServiceFee());
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getServiceFee()));
                blancesta = pfInnerBalanceMapper.updateBalanceIncreaseByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getServiceFee()));
                blancesta = pfInnerBalanceMapper.updateBalanceReduceByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            }

        } else if (acType == AccountConst.chargeFee) {
            accountPfDetail.setTradeBalance(accountTrade.getFee());
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getFee()));
                blancesta = pfInnerBalanceMapper.updateBalanceIncreaseByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getFee()));
                blancesta = pfInnerBalanceMapper.updateBalanceReduceByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            }

        } else if (acType == AccountConst.taxFee) {
            accountPfDetail.setTradeBalance(accountTrade.getTax());
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getTax()));
                blancesta = pfInnerBalanceMapper.updateBalanceIncreaseByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getTax()));
                blancesta = pfInnerBalanceMapper.updateBalanceReduceByInnerId(pfInnerId, accountTrade.getBalance(), Integer.valueOf(acType));

            }

        } else {
            sq.rollback();

            sq.close();
            sqBlance.rollback();
            sqBlance.close();
            throw new YtbError("获取结算信息失败");

        }

        //拦截扣钱
        if (blancesta < 0) {
            sq.rollback();
            sq.close();
            sqBlance.rollback();
            sqBlance.close();
            throw new YtbError("扣钱失败");
        }

        accountPfDetail.setTradeId(accountPfTrade.getTradeId());
        accountPfDetail.setInTime(date);
        accountPfDetail.setPfInnerId(pfInnerId);
        accountPfDetail.setInnerId(accountTrade.getAcId());
        accountPfDetail.setTradeId(accountTrade.getTradeId());
        accountPfDetail.setServiceType(Integer.valueOf(accountTrade.getServiceType()));

        accountPfDetail.setTradeType(accountPfTrade.getTradeType());
        accountPfDetail.setTradeSubtype(Integer.valueOf(accountTrade.getTradeSubtype()));
        accountPfDetail.setBalanceType(Integer.valueOf(acType));
        accountPfDetail.setStatus(accountTrade.getStatus());


        AccountPfDetailMapper accountPfDetailMapper = sq.getMapper(AccountPfDetailMapper.class);
        int detailSta = accountPfDetailMapper.insertSelective(accountPfDetail);
        if (detailSta <= 0) {
            sq.rollback();
            sq.close();
            sqBlance.rollback();
            sqBlance.close();
            throw new YtbError("获取结算信息失败");
        }


        return true;
    }


//
//
//
//    public AccountPfDetail pfDetail(AccountTrade accountTrade, AccountPfTrade accountPfTrade,
//                                    Integer pfInnerId, Short acType, Date date, SqlSession sq) {
//
//        AccountPfInnerMapper accountPfInnerMapper = sq.getMapper(AccountPfInnerMapper.class);
//
//        AccountPfInner accountPfInner = accountPfInnerMapper.selectByPrimaryKey(pfInnerId);
//
//        AccountPfDetail accountPfDetail = new AccountPfDetail();
//        accountPfDetail.setOriginalBalance(accountPfInner.getBalance());
//
//
//        if (acType == AccountConst.bankCardAcType
//                || acType == AccountConst.aliPayAcType
//                || acType == AccountConst.weChatAcType) {
//            accountPfDetail.setTradeBalance(accountTrade.getBalance());
//
//            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit
//                    || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE_REFUND) {
//                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
//
//            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE
//                    || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit_REFUND) {
//                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
//            } else {
//                throw new YtbError("不存在的业务场景");
//            }
//
//
//        }
//
//
//        if (acType == AccountConst.generalAccount) {
//
//        }
//
//
//
//
//
//
//
//
//
//
//
//        if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
//
//            if (acType == AccountConst.generalAccount) {
//                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
//                accountPfDetail.setTradeBalance(accountTrade.getBalance());
//            } else if (acType == AccountConst.serviceFee) {
//                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getServiceFee()));
//                accountPfDetail.setTradeBalance(accountTrade.getServiceFee());
//            } else if (acType == AccountConst.chargeFee) {//
//                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getFee()));
//                accountPfDetail.setTradeBalance(accountTrade.getFee());
//            } else if (acType == AccountConst.taxFee) {
//                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getTax()));
//                accountPfDetail.setTradeBalance(accountTrade.getTax());
//            }
//
//        } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
//            if (acType == AccountConst.generalAccount) {
//                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
//                accountPfDetail.setTradeBalance(accountTrade.getBalance());
//            } else if (acType == AccountConst.serviceFee) {
//                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getServiceFee()));
//                accountPfDetail.setTradeBalance(accountTrade.getServiceFee());
//            } else if (acType == AccountConst.chargeFee) {//
//                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getFee()));
//                accountPfDetail.setTradeBalance(accountTrade.getFee());
//            } else if (acType == AccountConst.taxFee) {
//                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getTax()));
//                accountPfDetail.setTradeBalance(accountTrade.getTax());
//            }
//
//        } else {
//            throw new YtbError("不存在的业务场景");
//        }
//
//        //accountPfDetail.setTradeId(accountPfTrade.getTradeId());
//        accountPfDetail.setInTime(date);
//        accountPfDetail.setPfInnerId(pfInnerId);
//        accountPfDetail.setInnerId(accountTrade.getAcId());
//        accountPfDetail.setTradeId(accountTrade.getTradeId());
//        AccountPfDetailMapper accountPfDetailMapper = sq.getMapper(AccountPfDetailMapper.class);
//        int detailSta = accountPfDetailMapper.insertSelective(accountPfDetail);
//        return accountPfDetail;
//    }
//


    public AccountPfDetail pfDetailByType(AccountTrade accountTrade, AccountPfTrade accountPfTrade,
                                          Integer pfInnerId, Short acType, Date date, SqlSession sq) {

        AccountPfInnerMapper accountPfInnerMapper = sq.getMapper(AccountPfInnerMapper.class);

        AccountPfInner accountPfInner = accountPfInnerMapper.selectByPrimaryKey(pfInnerId);

        AccountPfDetail accountPfDetail = new AccountPfDetail();
        accountPfDetail.setOriginalBalance(accountPfInner.getBalance());


        if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit
                || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE_REFUND) {
            accountPfDetail.setTradeBalance(accountTrade.getBalance());
            if (acType == AccountConst.bankCardAcType || acType == AccountConst.aliPayAcType || acType == AccountConst.weChatAcType) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
            } else {
                throw new YtbError("不存在的业务场景");
            }

        } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_RECHARGE
                || accountTrade.getTradeType() == TradeConst.TRADE_TYPE_Withdraw_Deposit_REFUND) {
            accountPfDetail.setTradeBalance(accountTrade.getBalance());
            if (acType == AccountConst.bankCardAcType || acType == AccountConst.aliPayAcType || acType == AccountConst.weChatAcType) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
            } else {
                throw new YtbError("不存在的业务场景");
            }
        } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {

            if (acType == AccountConst.generalAccount) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
                accountPfDetail.setTradeBalance(accountTrade.getBalance());
            } else if (acType == AccountConst.serviceFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getServiceFee()));
                accountPfDetail.setTradeBalance(accountTrade.getServiceFee());
            } else if (acType == AccountConst.chargeFee) {//
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getFee()));
                accountPfDetail.setTradeBalance(accountTrade.getFee());
            } else if (acType == AccountConst.taxFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getTax()));
                accountPfDetail.setTradeBalance(accountTrade.getTax());
            } else {
                throw new YtbError("不存在的业务场景");
            }

        } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
            if (acType == AccountConst.generalAccount) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
                accountPfDetail.setTradeBalance(accountTrade.getBalance());
            } else if (acType == AccountConst.serviceFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getServiceFee()));
                accountPfDetail.setTradeBalance(accountTrade.getServiceFee());
            } else if (acType == AccountConst.chargeFee) {//
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getFee()));
                accountPfDetail.setTradeBalance(accountTrade.getFee());
            } else if (acType == AccountConst.taxFee) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getTax()));
                accountPfDetail.setTradeBalance(accountTrade.getTax());
            } else {
                throw new YtbError("不存在的业务场景");
            }

        } else {
            throw new YtbError("不存在的业务场景");
        }

        //accountPfDetail.setTradeId(accountPfTrade.getTradeId());
        accountPfDetail.setInTime(date);
        accountPfDetail.setPfInnerId(pfInnerId);
        accountPfDetail.setInnerId(accountTrade.getAcId());
        accountPfDetail.setTradeId(accountTrade.getTradeId());
        AccountPfDetailMapper accountPfDetailMapper = sq.getMapper(AccountPfDetailMapper.class);
        int detailSta = accountPfDetailMapper.insertSelective(accountPfDetail);
        return accountPfDetail;
    }


    public AccountPfTrade pfTrade(AccountTrade accountTrade, Integer pfInnerId, Short acType, Short subInner) {

        AccountPfTrade accountPfTrade = new AccountPfTrade();

        if (acType == AccountConst.bankCardAcType) {
            accountPfTrade.setBalance(accountTrade.getBalance());
        } else if (acType == AccountConst.aliPayAcType) {
            accountPfTrade.setBalance(accountTrade.getBalance());
        } else if (acType == AccountConst.weChatAcType) {
            accountPfTrade.setBalance(accountTrade.getBalance());
        } else if (acType == AccountConst.generalAccount) {
            accountPfTrade.setBalance(accountTrade.getBalance());
        } else if (acType == AccountConst.serviceFee) {
            accountPfTrade.setBalance(accountTrade.getServiceFee());
        } else if (acType == AccountConst.chargeFee) {//手续费
            accountPfTrade.setBalance(accountTrade.getFee());
        } else if (acType == AccountConst.taxFee) {
            accountPfTrade.setBalance(accountTrade.getTax());
        } else {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
        }


        accountPfTrade.setTradeNoPre(accountTrade.getTradeId().toString());

        accountPfTrade.setAcId(accountTrade.getAcId());
        accountPfTrade.setToPfInnerId(pfInnerId);
        accountPfTrade.setTradeSubtype(subInner);//银行
        accountPfTrade.setTradeType(Integer.valueOf(accountTrade.getTradeType()));
        accountPfTrade.setUserId(accountTrade.getUserId());
        accountPfTrade.setCompanyId(accountTrade.getCompanyId());
        accountPfTrade.setTradeNo(accountTrade.getTradeId().toString());

        return accountPfTrade;
    }

    public boolean SettlementProjectTrade() {
        AccountTradeProjectExample example = new AccountTradeProjectExample();

        List<AccountTradeProject> accountTradeProjects = getProjectTrade(example);

        return true;
    }


    private List<AccountTradeProject> getProjectTrade(AccountTradeProjectExample example) {
        try {


            AccountTradeProjectServiceImpl accountTradeService = new AccountTradeProjectServiceImpl();


            return accountTradeService.selectByExample(example);
        } catch (Exception e) {
            throw new YtbError("获取结算信息失败");

        }
    }


}
