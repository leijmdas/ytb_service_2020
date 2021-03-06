package ytb.account.wallet.service.sq.business.pf.account.impl;


import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import ytb.account.cashpay.alipay.model.ComeTransferAccountsModel;
import ytb.account.cashpay.alipay.model.TransferAccountsModel;
import ytb.account.cashpay.alipay.pojo.AliPayTransferAccounts;
import ytb.account.cashpay.integration.service.IntegratedPay;
import ytb.account.cashpay.integration.service.impl.IntegratedPayService;

import ytb.account.wallet.dao.*;
import ytb.account.wallet.dao.transaction.AccountPfInnerBalanceMapper;
import ytb.account.wallet.dao.transaction.AccountUserInnerBalanceMapper;
import ytb.account.wallet.model.*;

import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.PfInnerConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.sq.basics.AccountTradeService;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.account.wallet.service.sq.business.pf.account.AccountTradePfService;
import ytb.account.wallet.tool.MyBatisUtil;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.util.Date;


public class AccountTradePfServiceImpl implements AccountTradePfService {


    @Override
    public int withdrawCashAdopt(ComeTransferAccountsModel model) {

        try {
            SqlSession sq = MyBatisUtil.getSession(false);
            Date date = new Date();

            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            AccountTrade accountTrade = accountTradeMapper.selectByPrimaryKey(model.getTradeId());

            if (accountTrade.getTradeType() > 0 & accountTrade.getAcId() != null & accountTrade.getBalance() != null) {


/*
        1        AccountConst.bankCardAcType银行卡
        2        AccountConst.aliPayAcType支付宝
        3        AccountConst.weChatAcType财富通

        4       AccountConst.generalAccount平台虚拟账户--总帐户
        5        AccountConst.serviceFee平台虚拟账户--服务费
        6        AccountConst.chargeFee平台虚拟账户--手续费
        7        AccountConst.taxFee 税费
                */
                /*sub 4 平台*/

//税费
/*
                if (accountTrade.getTax().compareTo(BigDecimal.ZERO) == 1) {

                    Integer acType = AccountConst.taxFee;
                    Boolean sta = tradeFlow(accountTrade, date, acType, sq);
                    if (sta == false) {
                        sq.rollback();
                        return 0;
                    }
                }
//手续费
                if (accountTrade.getFee().compareTo(BigDecimal.ZERO) == 1) {

                    Integer acType = AccountConst.chargeFee;
                    Boolean sta = tradeFlow(accountTrade, date, acType, sq);
                    if (sta == false) {
                        sq.rollback();
                        return 0;
                    }

                }
                //   服务费

                if (accountTrade.getServiceFee().compareTo(BigDecimal.ZERO) == 1) {
                    Integer acType = AccountConst.serviceFee;
                    Boolean sta = tradeFlow(accountTrade, date, acType, sq);
                    if (sta == false) {
                        sq.rollback();
                        return 0;
                    }
                }*/


                if (accountTrade.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_BANK)/*-银行-*/ {


                    /// 此处需移到自动结算
                    Integer pfInnerId = Integer.valueOf(accountTrade.getTradeSubtype());
                    Short acType = AccountConst.bankCardAcType;

                    AccountPfInnerBalanceMapper accountPfInnerBalanceMapper = sq.getMapper(AccountPfInnerBalanceMapper.class);
                    int pfBalance = accountPfInnerBalanceMapper.updateTakeoutMoneyIncrease(pfInnerId,
                            accountTrade.getBalance(), Integer.valueOf(acType));
                    if (pfBalance > 0) {

                        Boolean sta = tradeFlow(accountTrade, date, acType, sq);

                        if (sta == false) {
                            sq.rollback();
                            return 0;
                        } else {
                            sq.commit();
                            return 1;
                        }

                    } else {
                        sq.rollback();
                    }


                }
            } else if (accountTrade.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_WE_CHAT) {


            } else if (accountTrade.getTradeSubtype() == AccountConst.TRADE_SUBTYPE_ALIPAY) {

                tradeSta(ADOPT, model.getTradeId(), null);

                AliPayTransferAccounts aliPayRefundModel = new AliPayTransferAccounts();

                aliPayRefundModel.setOutBizNo(String.valueOf(model.getTradeId()));//订单号
                aliPayRefundModel.setPayeeAccount(model.getCardHolderId());//手机号
                aliPayRefundModel.setAmount(model.getBalance());//钱
                aliPayRefundModel.setPayerShowName("youtobon");
                aliPayRefundModel.setPayeeRealName(model.getCardHolder());//真实姓名
                aliPayRefundModel.setRemark("youtobon 提现成功");


                try {
                    IntegratedPay aliPayService = new IntegratedPayService();
                    String from = aliPayService.transferFacility(aliPayRefundModel);


                    if (from.equals("") == true) {
                        tradeSta(ERROR, model.getTradeId(), null);
                        return 0;


                    } else {
                        TransferAccountsModel transferAccountsModel = JSONObject.parseObject(from, TransferAccountsModel.class);
//改变状态
                        tradeSta(SUCCESS, transferAccountsModel.getOut_biz_no(), transferAccountsModel.getOrder_id());


                        return 1;
                    }
                } catch (Exception e) {
                    tradeSta(ERROR, model.getTradeId(), null);
                    return 0;
                }


            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }














    /*----new----*/


    @Override
    public int transferService(AccountTrade accountTrade, String tradeItem) {

        SqlSession sq = MyBatisUtil.getSession(false);
        Configuration a = sq.getConfiguration();

        int accountTradeSta = 0;
        int acsta = 0;
        int tacsta = 0;

        int datae = 0;
        int dataf = 0;
        int sta = 0;
        try {

            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            accountTradeSta = accountTradeMapper.insertSelective(accountTrade);

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            /*AccountUserDetail流水记录*/

            AccountUserDetail accountUserDetail = new AccountUserDetail();
            accountUserDetail.setTermId(Short.valueOf(String.valueOf(accountTrade.getTradeId())));
            accountUserDetail.setInnerId(accountTrade.getAcId());
            accountUserDetail.setTradeId(accountTrade.getTradeId());
            accountUserDetail.setTradeItem(tradeItem);
            accountUserDetail.setTradeBalance(accountTrade.getBalance());//交易金额
            accountUserDetail.setInTime(new Date());
            accountUserDetail.setTradeSubtype((short) 4);//交易方式


            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);



            /*甲方流水*/

/*
            AccountUserInner ac = accountUserInnerMapper.selectByPrimaryKey(accountTrade.getAcId());
            accountUserDetail.setBalance(ac.getBalance().subtract(accountTrade.getBalance()));//减去流水总金额
            accountUserDetail.setInnerId(accountTrade.getAcId());//甲
            accountUserDetail.setTradeType(AccountConst.TRADE_TYPE_PAYOUT);//支出
            accountUserDetail.setOriginalBalance(ac.getBalance());
            datae = accountUserDetailMapper.insertSelective(accountUserDetail);

*/
            //乙方流水

            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(accountTrade.getToAcId());//乙方
            accountUserDetail.setBalance(toAc.getBalance().add(accountTrade.getBalance()));//加起来
            accountUserDetail.setInnerId(accountTrade.getToAcId());//甲
            accountUserDetail.setTradeType((short) 30);//收入
            accountUserDetail.setOriginalBalance(toAc.getBalance());//原来
            dataf = accountUserDetailMapper.insertSelective(accountUserDetail);


            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            /*甲方扣钱*/
            acsta = accountUserInnerBalanceMapper.updateBalanceAndOutReduce(accountTrade.getAcId(), accountTrade.getBalance());

            /*乙方加钱*/
            tacsta = accountUserInnerBalanceMapper.updateBalanceIncreaseByInnerId(accountTrade.getToAcId(), accountTrade.getBalance());

            if (accountTradeSta > 0 & acsta > 0 & tacsta > 0 & datae > 0 & dataf > 0) {
                sq.commit();
                sta = 1;
            } else {
                sq.rollback();
            }

        } catch (Exception e) {
            System.out.println("出错------------");
            e.printStackTrace();
            sq.rollback();
        } finally {
            sq.close();

        }
        return sta;

    }

    private static final Byte ADOPT = 3;

    private static final Byte SUCCESS = 10;

    private static final Byte ERROR = 11;


    public static Short acTypeToPfInnerId(Short acType) {


        Short pfInnerId = 0;

        //1234567对应inner表的1234567

        if (acType == AccountConst.bankCardAcType) {

            pfInnerId = PfInnerConst.bankCardPfInnerId;

        } else if (acType == AccountConst.aliPayAcType) {

            pfInnerId = PfInnerConst.aliPayPfInnerId;

        } else if (acType == AccountConst.weChatAcType) {

            pfInnerId = PfInnerConst.weChatPfInnerId;

        } else if (acType == AccountConst.generalAccount) {
            pfInnerId = PfInnerConst.generalAccountInnerId;
        } else if (acType == AccountConst.serviceFee) {

            pfInnerId = PfInnerConst.serviceFeeInnerId;

        } else if (acType == AccountConst.chargeFee) {

            pfInnerId = PfInnerConst.chargeFeeInnerId;

        } else if (acType == AccountConst.taxFee) {

            pfInnerId = PfInnerConst.taxFeeInnerId;
        } else {
            //           throw new YtbError("错误的innerId转换");F
        }
        return pfInnerId;
    }

    public static Integer acTypeToPfInnerIdInt(Short acType) {


        Short pfInnerId = 0;

        //1234567对应inner表的1234567

        if (acType == AccountConst.bankCardAcType) {

            pfInnerId = PfInnerConst.bankCardPfInnerId;

        } else if (acType == AccountConst.aliPayAcType) {

            pfInnerId = PfInnerConst.aliPayPfInnerId;

        } else if (acType == AccountConst.weChatAcType) {

            pfInnerId = PfInnerConst.weChatPfInnerId;

        } else if (acType == AccountConst.generalAccount) {
            pfInnerId = PfInnerConst.generalAccountInnerId;
        } else if (acType == AccountConst.serviceFee) {

            pfInnerId = PfInnerConst.serviceFeeInnerId;

        } else if (acType == AccountConst.chargeFee) {

            pfInnerId = PfInnerConst.chargeFeeInnerId;

        } else if (acType == AccountConst.taxFee) {

            pfInnerId = PfInnerConst.taxFeeInnerId;
        } else {
            //    throw new YtbError("错误的innerId转换");
        }
        return Integer.valueOf(pfInnerId);
    }

    public Boolean tradeFlow(AccountTrade accountTrade, Date date,
                             Short acType, SqlSession sq) {

        Short pfInnerId = 0;

        //1234567对应inner表的1234567

        if (acType == AccountConst.bankCardAcType) {

            pfInnerId = PfInnerConst.bankCardPfInnerId;

        } else if (acType == AccountConst.aliPayAcType) {

            pfInnerId = PfInnerConst.aliPayPfInnerId;

        } else if (acType == AccountConst.weChatAcType) {

            pfInnerId = PfInnerConst.weChatPfInnerId;

        } else if (acType == AccountConst.generalAccount) {
            pfInnerId = PfInnerConst.generalAccountInnerId;
        } else if (acType == AccountConst.serviceFee) {

            pfInnerId = PfInnerConst.serviceFeeInnerId;

        } else if (acType == AccountConst.chargeFee) {

            pfInnerId = PfInnerConst.chargeFeeInnerId;

        } else if (acType == AccountConst.taxFee) {

            pfInnerId = PfInnerConst.taxFeeInnerId;
        } else {
            return null;
        }


        Boolean tradeFlow = false;

        AccountPfTradeMapper accountPfTradeMapper = sq.getMapper(AccountPfTradeMapper.class);
        AccountPfDetailMapper accountPfDetailMapper = sq.getMapper(AccountPfDetailMapper.class);
        AccountPfInnerBalanceMapper accountPfInnerBalanceMapper = sq.getMapper(AccountPfInnerBalanceMapper.class);
        AccountPfInnerMapper accountPfInnerMapper = sq.getMapper(AccountPfInnerMapper.class);


        AccountPfTrade accountPfTrade = pfTrade(accountTrade, Integer.valueOf(pfInnerId), acType, sq);

        if (accountPfTrade == null) {
            return false;
        }

        //  int pfTrade = accountPfTradeMapper.insertSelective(accountPfTrade);


        AccountPfInner sel = accountPfInnerMapper.selectByPrimaryKey(Integer.valueOf(pfInnerId));
        AccountPfDetail accountPfDetail = pfDetail(accountTrade, accountPfTrade, sel, Integer.valueOf(pfInnerId), acType, date);
        int detailSta = accountPfDetailMapper.insertSelective(accountPfDetail);

        if (detailSta > 0) {

            int sta = 0;
            int bansta = 0;
            if (acType == AccountConst.bankCardAcType || acType == AccountConst.aliPayAcType || acType == AccountConst.weChatAcType) {

                sta = accountPfInnerBalanceMapper.updateTakeoutMoneyReduce(Integer.valueOf(pfInnerId), accountPfTrade.getBalance(), Integer.valueOf(acType));
                bansta = accountPfInnerBalanceMapper.updateBalanceReduceByInnerId(Integer.valueOf(pfInnerId), accountPfTrade.getBalance(), Integer.valueOf(acType));
            } else {
                sta = accountPfInnerBalanceMapper.updateTakeoutMoneyIncrease(Integer.valueOf(pfInnerId), accountPfTrade.getBalance(), Integer.valueOf(acType));
                bansta = accountPfInnerBalanceMapper.updateBalanceIncreaseByInnerId(Integer.valueOf(pfInnerId), accountPfTrade.getBalance(), Integer.valueOf(acType));
            }


            if (sta > 0 & bansta > 0) {
                sq.commit();
                tradeFlow = true;
            }


        } else {
            sq.rollback();
            tradeFlow = false;
        }


        return tradeFlow;
    }


    //*-----通用方法----*/
    //用来改变trade状态的方法
    private boolean tradeSta(Byte sta, Integer tradeId, String tradeNo) {

        try {
            AccountTradeService tradeService = new AccountTradeServiceImpl();
            AccountTrade dd = new AccountTrade();

            dd.setStatus(sta);
            dd.setTradeId(tradeId);
            //外部生成，银行，微信，支付宝、项目产生
            if (tradeNo != null || !tradeNo.equals("")) {
                dd.setTradeNo(tradeNo);

            }
            int trade = tradeService.updateByPrimaryKeySelective(dd);
            if (trade > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    public AccountPfTrade pfTrade(AccountTrade accountTrade, Integer pfInnerId, Short acType, SqlSession sq) {
        try {
            AccountPfTrade accountPfTrade = new AccountPfTrade();
            //存钱
            accountPfTrade.setBalance(balanceSetByAcType(accountTrade, acType));
            accountPfTrade.setTradeNoPre(accountTrade.getTradeId().toString());
            accountPfTrade.setAcId(pfInnerId);
            accountPfTrade.setOutId(accountTrade.getAcId());
            accountPfTrade.setTradeSubtype(accountTrade.getTradeSubtype());//银行
            accountPfTrade.setTradeType(Integer.valueOf(accountTrade.getTradeType()));
            accountPfTrade.setUserId(accountTrade.getUserId());
            accountPfTrade.setCompanyId(accountTrade.getCompanyId());
            accountPfTrade.setTradeNo(accountTrade.getTradeId().toString());
            accountPfTrade.setStatus(Short.valueOf(accountTrade.getStatus()));

            AccountPfTradeMapper accountPfTradeMapper = sq.getMapper(AccountPfTradeMapper.class);
            int sta = accountPfTradeMapper.insertSelective(accountPfTrade);

            if (sta <= 0) {
                sq.rollback();
                sq.close();
                //  throw new YtbError("构建PF交易失败");
            }

            return accountPfTrade;
        } catch (Exception e) {
            sq.rollback();
            sq.close();
            return null;
            //    throw new YtbError("构建PF交易失败");
        }
    }


    public BigDecimal balanceSetByAcType(AccountTrade accountTrade, Short acType) {

        BigDecimal bal = null;

        try {


            if (acType == AccountConst.bankCardAcType) {
                bal = accountTrade.getBalance();
            } else if (acType == AccountConst.aliPayAcType) {
                bal = accountTrade.getBalance();
            } else if (acType == AccountConst.weChatAcType) {
                bal = accountTrade.getBalance();
            } else if (acType == AccountConst.generalAccount) {
                bal = accountTrade.getBalance();
            } else if (acType == AccountConst.serviceFee) {
                bal = accountTrade.getServiceFee();
            } else if (acType == AccountConst.chargeFee) {//手续费
                bal = accountTrade.getFee();
            } else if (acType == AccountConst.taxFee) {
                bal = accountTrade.getTax();
            } else {
                throw new YtbError("获取结算信息失败");
            }
            return bal;
        } catch (Exception e) {
            throw new YtbError("获取结算信息失败");
        }
    }


    public AccountPfDetail pfDetail(AccountTrade accountTrade, AccountPfTrade accountPfTrade,
                                    AccountPfInner accountPfInner, Integer pfInnerId, Short acType, Date date) {
        AccountPfDetail accountPfDetail = new AccountPfDetail();

        accountPfDetail.setOriginalBalance(accountPfInner.getBalance());


        if (acType == AccountConst.bankCardAcType) {
            accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
            accountPfDetail.setTradeBalance(accountTrade.getBalance());
        } else if (acType == AccountConst.aliPayAcType) {
            accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
            accountPfDetail.setTradeBalance(accountTrade.getBalance());
        } else if (acType == AccountConst.weChatAcType) {
            accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
            accountPfDetail.setTradeBalance(accountTrade.getBalance());
        } else if (acType == AccountConst.generalAccount) {
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getBalance()));
                accountPfDetail.setTradeBalance(accountTrade.getBalance());
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getBalance()));
                accountPfDetail.setTradeBalance(accountTrade.getBalance());
            }

        } else if (acType == AccountConst.serviceFee) {
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getServiceFee()));
                accountPfDetail.setTradeBalance(accountTrade.getServiceFee());
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getServiceFee()));
                accountPfDetail.setTradeBalance(accountTrade.getServiceFee());
            }

        } else if (acType == AccountConst.chargeFee) {//
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getFee()));
                accountPfDetail.setTradeBalance(accountTrade.getFee());
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getFee()));
                accountPfDetail.setTradeBalance(accountTrade.getFee());
            }

        } else if (acType == AccountConst.taxFee) {
            if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT) {
                accountPfDetail.setBalance(accountPfInner.getBalance().add(accountTrade.getTax()));
                accountPfDetail.setTradeBalance(accountTrade.getTax());
            } else if (accountTrade.getTradeType() == TradeConst.TRADE_TYPE_PAYOUT_REFUND) {
                accountPfDetail.setBalance(accountPfInner.getBalance().subtract(accountTrade.getTax()));
                accountPfDetail.setTradeBalance(accountTrade.getTax());
            }

        } else {
            return null;
        }

        //accountPfDetail.setTradeId(accountPfTrade.getTradeId());
        accountPfDetail.setInTime(date);
        accountPfDetail.setPfInnerId(pfInnerId);
        accountPfDetail.setInnerId(accountTrade.getAcId());
        accountPfDetail.setTradeId(accountTrade.getTradeId());

        return accountPfDetail;
    }

}
