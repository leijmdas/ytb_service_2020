package ytb.account.wallet.service.sq.business.user.impl;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import ytb.common.ytblog.YtbLog;
import ytb.common.ytblog.AccountYtbLog;
import ytb.log.notify.service.impl.TaskNotifyServiceImpl;
import ytb.user.model.CompanyInfoModel;
import ytb.user.model.UserInfoModel;
import ytb.user.model.UserLoginModel;
import ytb.user.service.impl.CompanyCenterServiceImpl;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.account.wallet.dao.*;
import ytb.account.wallet.dao.transaction.AccountUserInnerBalanceMapper;
import ytb.account.wallet.model.*;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.account.wallet.model.project.TransferInfo;
import ytb.account.wallet.pojo.*;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.service.calculation.serviceFee.impl.ServiceBalanceServiceImpl;
import ytb.account.wallet.service.sq.basics.session.model.TradeProjectToDetailModel;
import ytb.account.wallet.service.sq.business.user.ProjecTransactionService;
import ytb.account.wallet.service.service.calculation.serviceFee.ServiceBalanceService;
import ytb.account.wallet.service.sq.basics.session.AccountUserDetailSession;
import ytb.account.wallet.service.sq.business.user.check.AccounTraceCheck;
import ytb.account.wallet.service.sq.business.user.check.AccounTraceProjectCheck;
import ytb.account.wallet.service.sq.business.user.check.ProjectCheck;
import ytb.account.wallet.service.sq.business.user.impl.F2fRefund.PmRefund;
import ytb.account.wallet.service.sq.business.user.impl.F2fRefund.Refund2Pa;
import ytb.account.wallet.service.sq.business.user.pojo.projectRefunds;
import ytb.account.wallet.tool.MyBatisUtil;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目交易接口
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/19
 */
public class ProjecTransactionServiceImpl implements ProjecTransactionService {


    //支出转账余额至余额，协助金，感谢金
    public int b2bPayTransfer(TransferInfo info) {

        AccountTradeProject accountTrade = info.toAccountTrade(info.getServiceType());

        accountTrade.setTradeType(TradeConst.TRADE_TYPE_TRANSFER);
        accountTrade.setTradeSubtype(Short.valueOf(info.getTradeSubType()));
        accountTrade.setServiceType(info.getServiceType());


        return transferService(accountTrade);
    }

    //项目预付款 甲方余额->冻结款项 101
    public int b2fPaPayProjectAssist(TradeInfo info) {
        return b2fPaPayProject(info, TradeConst.SERVICE_TYPE_assist);

    }

    //项目预付款 甲方余额->冻结款项 101
    //项目预付款 甲方余额->冻结款项 101
    public int b2fPaPayProject(TradeInfo info) {
        return b2fPaPayProject(info, TradeConst.SERVICE_TYPE_project);
    }

    public int b2fPaPayProject(TradeInfo info, Byte serviceType) {

        AccountTradeProject acTrade = info.toAccountTrade(serviceType);
        acTrade.setBalanceType(info.getBalanceFlag());
        acTrade.setTradeType(TradeConst.TRADE_TYPE_PAYOUT);
        acTrade.setTradeSubtype(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN);
        acTrade.setStatus(TradeConst.status_success);

        return b2fPaPayProjectFirst(acTrade);

    }

    public List<AccountTradeProject> f2bProjectPbUnfreeze(List<TradeInfo> tradeInfos) {
        return f2bProjectPbUnfreeze(tradeInfos, TradeConst.SERVICE_TYPE_project);
    }

    public List<AccountTradeProject> f2bProjectPaUnfreeze(List<TradeInfo> tradeInfos, Byte serviceType) {

        List<ProjectBalanceToAc> toAcs = new ArrayList<>();
        for (TradeInfo tradeInfo : tradeInfos) {
            ProjectBalanceToAc toAc = tradeInfo.toProjectBalanceToAc();
            toAc.setBalanceType(tradeInfo.getBalanceFlag());
            toAcs.add(toAc);
        }

        return projectPaUnfreeze(toAcs, serviceType);


    }

    public List<AccountTradeProject> f2bProjectPbUnfreeze(List<TradeInfo> tradeInfos, Byte serviceType) {

        List<ProjectBalanceToAc> toAcs = new ArrayList<>();
        for (TradeInfo tradeInfo : tradeInfos) {
            ProjectBalanceToAc toAc = tradeInfo.toProjectBalanceToAc();
            toAc.setBalanceType(tradeInfo.getBalanceFlag());
            toAcs.add(toAc);
        }

        return projectPbUnfreeze(toAcs, serviceType);


    }

    public ProjectBalanceProjectAgent f2fTransferPayPa2Pb(TradeInfo outTradeInfo, List<TradeInfo> toTradeInfos, Byte servieType) {
        outTradeInfo.setServiceType(servieType);
        //甲方参数
        ProjectBalanceOutAc paOutAc = outTradeInfo.toProjectBalanceOutAc();

        //乙方参数
        List<ProjectBalanceToAc> pbAcList = new ArrayList<ProjectBalanceToAc>();
        for (TradeInfo tradeInfo : toTradeInfos) {
            tradeInfo.setServiceType(servieType);
            ProjectBalanceToAc pbToAc = tradeInfo.toProjectBalanceToAc();
            pbAcList.add(pbToAc);
        }

        //执行交易
        return phasePayPa2Pb(servieType, paOutAc, pbAcList);
    }


    public ProjectBalanceProjectAgent f2fTransferPayPa2PbAssist(TradeInfo outInfo, List<TradeInfo> toInfos) {

        //执行交易
        return f2fTransferPayPa2Pb(outInfo, toInfos, TradeConst.SERVICE_TYPE_assist);
    }

    public ProjectBalanceProjectAgent f2fTransferPayPa2Pb(TradeInfo outInfo, List<TradeInfo> toInfos) {

        //执行交易
        return f2fTransferPayPa2Pb(outInfo, toInfos, TradeConst.SERVICE_TYPE_project);
    }


    @Override
    public ProjectBalanceProjectAgent phasePayPa2Pb(ProjectBalanceOutAc pbOutAc, List<ProjectBalanceToAc> pbAgentList) {
        return phasePayPa2Pb(TradeConst.SERVICE_TYPE_project, pbOutAc, pbAgentList);
    }

    @Override
    public ProjectBalanceProjectAgent phasePayPa2Pb(Byte serviceType,
                                                    ProjectBalanceOutAc pbOutAc,
                                                    List<ProjectBalanceToAc> pbAgentList) {


        BigDecimal total = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
        for (ProjectBalanceToAc balanceToAc : pbAgentList) {
            if (ProjectCheck.BalancePlusminusCheck(balanceToAc.getBalance())) {
                total = total.add(balanceToAc.getBalance());
            } else {
                throw new YtbError(YtbError.CODE_FAIL, "值不能为负或者0:" + balanceToAc.getBalance());
            }
        }
        //total=balance+fe+,,,
        ProjectBalanceProjectAgent projectBalanceAgent = new ProjectBalanceProjectAgent();
        List<AccountTradeProject> toAcList = new ArrayList<AccountTradeProject>(pbAgentList.size());
        SqlSession sqlSession = MyBatisUtil.getSession(false);
        try {

            if (pbOutAc.getBalance().compareTo(BigDecimal.ZERO) != 1) {
                sqlSession.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负");
            }

            Date time = new Date();
            Boolean dataa = false;
            int sta = 0;

            //查出订单
            AccountTradeProjectMapper accountTradeMapper = sqlSession.getMapper(AccountTradeProjectMapper.class);
            AccountUserInnerMapper accountUserInnerMapper = sqlSession.getMapper(AccountUserInnerMapper.class);
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sqlSession.getMapper(AccountUserInnerBalanceMapper.class);

            AccountTradeProject tradePA = accountTradeMapper.selectByPrimaryKey(pbOutAc.getTradeId());

            if (tradePA == null) {
                sqlSession.rollback();
                YtbLog.logError("pbOutAc getTradeId", pbOutAc.getTradeId());
                throw new YtbError(YtbError.CODE_FAIL, "AccountTradeProject订单获取失败/");

            }
            if (!ProjectCheck.stagePayByPAToPBsCheck(tradePA)) {
                sqlSession.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "订单类型不正确！-ProjecTransactionServiceImpl");
            }

            AccountUserInner OutAc = accountUserInnerMapper.selectByPrimaryKey(tradePA.getAcId());//甲方

            if (OutAc != null) {

//                if (!OutAc.getPayPassword().equals(pbOutAc.getPassword())) {
//                    sqlSession.rollback();
//                    throw new YtbError(YtbError.CODE_INVALID_USER, "密码不正确!-ProjecTransactionServiceImpl");
//                }

                /*-------------------------《--乙-方--》-------------------------*/
                for (ProjectBalanceToAc balanceToAc : pbAgentList) {

                    if (balanceToAc.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
                        sqlSession.rollback();
                        throw new YtbError(YtbError.CODE_FAIL, "获取乙方变更金额获取失败-ProjecTransactionServiceImpl");

                    }


                    AccountTradeProject toAcTrade = new AccountTradeProject();
                    toAcTrade.setAcId(tradePA.getAcId());
                    //构建accountTrade--乙方
                    if (balanceToAc.getBalance() != null) {
                        toAcTrade.setTotalBalance(balanceToAc.getBalance());
                        toAcTrade.setBalance(balanceToAc.getBalance());
                        toAcTrade.setServiceFee(balanceToAc.getServiceFee());
                        toAcTrade.setTax(balanceToAc.getTax());
                        toAcTrade.setFee(balanceToAc.getFee());
                    }

                    if (balanceToAc.getProjectId() != null) {
                        toAcTrade.setProjectId(balanceToAc.getProjectId());
                        toAcTrade.setTalkId(balanceToAc.getTalkId());
                        toAcTrade.setPhase(Short.valueOf(balanceToAc.getPhase().toString()));
                    }

                    if (balanceToAc.getToInnerId() != null) {
                        toAcTrade.setToAcId(balanceToAc.getToInnerId());
                    }

                    toAcTrade.setUserId(balanceToAc.getUserId());
                    toAcTrade.setCompanyId(balanceToAc.getCompanyId());


                    toAcTrade.setAcId(tradePA.getAcId());
                    toAcTrade.setTradeType(TradeConst.TRADE_TYPE_INCOME);
                    toAcTrade.setTradeSubtype(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
                    toAcTrade.setServiceType(serviceType);
                    toAcTrade.setStatus(AccountConst.STATUS_OK);
                    toAcTrade.setTradeNoPre(String.valueOf(tradePA.getTradeId()));
                    toAcTrade.setCreateBy(0);
                    toAcTrade.setCreateTime(time);
                    toAcTrade.setAddTime(time);
                    int newTradeId = accountTradeMapper.insertSelective(toAcTrade);

                    if (newTradeId > 0) {
                        toAcList.add(toAcTrade);
                    } else {
                        sqlSession.rollback();
                        throw new YtbError(YtbError.CODE_FAIL, "toAcTrade");
                    }


                    TradeProjectToDetailModel as = new TradeProjectToDetailModel();
                    as.setAccountTrade(toAcTrade);
                    as.setBalanceSta(AccountUserDetailSession.BalanceSta_add);
                    as.setBalanceType(AccountUserDetailSession.BalanceType_project_balance_agent);

                    as.setId(balanceToAc.getToInnerId());
                    as.setTime(time);
                    as.setTomoney(balanceToAc.getBalance());


                    dataa = AccountUserDetailSession.getOldInnerBanToNewDetailSq(
                            as, sqlSession);

                    if (dataa == false) {
                        sqlSession.rollback();
                        throw new YtbError(YtbError.CODE_FAIL, "accountUserDetail-ProjecTransactionServiceImpl");

                    }



                    /*乙方加钱*/
                    sta = accountUserInnerBalanceMapper.updateProjectBalanceAgentIncreaseByInnerId(balanceToAc.getToInnerId(), balanceToAc.getBalance());
                    if (sta <= 0) {
                        sqlSession.rollback();
                        throw new YtbError(YtbError.CODE_FAIL, "accountUserInnerBalanceMapper-ProjecTransactionServiceImpl");

                    }

                }

                AccountTradeProject acTrade = new AccountTradeProject();
                acTrade.setProjectId(pbOutAc.getProjectId());
                acTrade.setTalkId(pbOutAc.getTalkId());
                acTrade.setPhase(Short.valueOf(pbOutAc.getPhase().toString()));

                acTrade.setUserId(tradePA.getUserId());
                acTrade.setCompanyId(tradePA.getCompanyId());

                acTrade.setStatus(AccountConst.STATUS_OK);

                acTrade.setBalance(pbOutAc.getBalance());
                acTrade.setTotalBalance(pbOutAc.getBalance());
                acTrade.setTradeType(TradeConst.TRADE_TYPE_PAYOUT);
                acTrade.setTradeSubtype(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
                acTrade.setServiceType(serviceType);
                acTrade.setAcId(tradePA.getAcId());
                acTrade.setTradeNoPre(String.valueOf(tradePA.getTradeId()));
                acTrade.setStatus(TradeConst.status_success);
                acTrade.setCreateBy(0);
                acTrade.setCreateTime(new Date());
                acTrade.setAddTime(time);

                //需要账期  acTrade.setTermId(intDate(time));
                if (pbOutAc.getFee() != null) {
                    acTrade.setFee(pbOutAc.getFee());
                }
                if (pbOutAc.getTax() != null) {
                    acTrade.setTax(pbOutAc.getTax());
                }
                if (pbOutAc.getServiceFee() != null) {
                    acTrade.setServiceFee(pbOutAc.getServiceFee());
                }
                if (pbOutAc.getProjectId() != null) {
                    acTrade.setProjectId(pbOutAc.getProjectId());
                }

                /*甲方扣冻结资金*/
                int updateNumber = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(
                        tradePA.getAcId(),
                        pbOutAc.getBalance());
                if (updateNumber <= 0) {

                    sqlSession.rollback();
                    AccountYtbLog.logError("phasePayPa2Pb", tradePA);
                    AccountYtbLog.logError("phasePayPa2Pb", pbOutAc);
                    throw new YtbError(YtbError.CODE_FAIL, "甲方扣除冻结金失败！-ProjecTransactionServiceImpl");

                }
                sta = accountTradeMapper.insertSelective(acTrade);
                if (sta <= 0) {
                    sqlSession.rollback();
                    throw new YtbError(YtbError.CODE_FAIL, "甲方创建交易失败-ProjecTransactionServiceImpl");

                }


                TradeProjectToDetailModel as = new TradeProjectToDetailModel();
                as.setAccountTrade(acTrade);
                as.setBalanceSta(AccountUserDetailSession.BalanceSta_subtract);
                as.setBalanceType(AccountUserDetailSession.BalanceType_balance_agent);
                as.setId(acTrade.getAcId());
                as.setTime(time);
                as.setTomoney(acTrade.getBalance());


                Boolean tacste = AccountUserDetailSession.getOldInnerBanToNewDetailSq(
                        as, sqlSession);

                if (tacste == false) {
                    sqlSession.rollback();
                    throw new YtbError(YtbError.CODE_FAIL, "更新流水失败-ProjecTransactionServiceImpl");
                }




                /*---------- -《--甲-方--》-------------------------*/
                if (sta > 0 & dataa == true & tacste == true) {
                    projectBalanceAgent.setToAccountTrades(toAcList);
                    projectBalanceAgent.setAccountTrade(acTrade);
                    sqlSession.commit();
                } else {
                    sqlSession.rollback();
                    throw new YtbError(YtbError.CODE_FAIL, "projectBalanceAgent-ProjecTransactionServiceImpl");

                }
            }

        } catch (Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return projectBalanceAgent;


    }


    @Override
    public List<AccountTradeProject> projectPbUnfreeze(List<ProjectBalanceToAc> pbAgentList) {
        return projectPbUnfreeze(pbAgentList, TradeConst.SERVICE_TYPE_project);
    }
    @Override
    public List<AccountTradeProject> projectPaUnfreeze(List<ProjectBalanceToAc> pbAgentList, Byte serviceType) {
        return projectUnfreeze(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN,pbAgentList,serviceType);
    }

    @Override
    public List<AccountTradeProject> projectPbUnfreeze(List<ProjectBalanceToAc> pbAgentList, Byte serviceType) {
        return projectUnfreeze(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN_INCOME, pbAgentList, serviceType);
    }

    //乙方项目金额冻结--》总金额

    List<AccountTradeProject> projectUnfreeze(Short tradeSubType, List<ProjectBalanceToAc> pbAgentList,
                                              Byte serviceType) {

        int datab = 0;
        List<AccountTradeProject> toAcList = new ArrayList<>(pbAgentList.size());

        try (SqlSession sq = MyBatisUtil.getSession(false)) {
            AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);
            AccountUserInnerBalanceMapper innerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);

            Date time = new Date();
            for (ProjectBalanceToAc b2ac : pbAgentList) {

                if (b2ac.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
                    sq.rollback();
                    throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");
                }

                AccountTradeProject toAccountTradeProject = new AccountTradeProject();
                toAccountTradeProject.setBalanceType(b2ac.getBalanceType());
                toAccountTradeProject.setTradeType(TradeConst.TRADE_TYPE_INCOME);
                toAccountTradeProject.setTradeSubtype(tradeSubType);
                toAccountTradeProject.setServiceType(serviceType);
                toAccountTradeProject.setStatus(AccountConst.STATUS_OK);

                if (b2ac.getTradeId() != null) {
                    toAccountTradeProject.setTradeId(b2ac.getTradeId());
                }
                toAccountTradeProject.setUserId(b2ac.getUserId());
                toAccountTradeProject.setCompanyId(b2ac.getCompanyId());

                //构建accountTrade--乙方
                toAccountTradeProject.setProjectId(b2ac.getProjectId());
                toAccountTradeProject.setTalkId(b2ac.getTalkId());
                toAccountTradeProject.setPhase(Short.valueOf(b2ac.getPhase().toString()));
                toAccountTradeProject.setCreateBy(0);
                toAccountTradeProject.setCreateTime(new Date());

                if (b2ac.getTotalBalance() != null) {
                    toAccountTradeProject.setTotalBalance(b2ac.getTotalBalance());
                    toAccountTradeProject.setBalance(b2ac.getBalance());
                    toAccountTradeProject.setServiceFee(b2ac.getServiceFee());
                    toAccountTradeProject.setTax(b2ac.getTax());
                    toAccountTradeProject.setFee(b2ac.getFee());
                }

                if (b2ac.getOutInnerId() != null) {
                    toAccountTradeProject.setOutId(b2ac.getOutInnerId());
                    toAccountTradeProject.setAcId(b2ac.getOutInnerId());
                }
                if (b2ac.getToInnerId() != null) {
                    toAccountTradeProject.setToAcId(b2ac.getToInnerId());
                }


                int num = accountTradeMapper.insertSelective(toAccountTradeProject);
                if (num <= 0) {
                    sq.rollback();
                    throw new YtbError(YtbError.CODE_INVALID_USER, "新数据插入AccountTradeProject执行失败!-ProjecTransactionServiceImpl");
                }
                toAcList.add(toAccountTradeProject);

                TradeProjectToDetailModel bb = new TradeProjectToDetailModel();
                bb.setAccountTrade(toAccountTradeProject);
                bb.setBalanceSta(AccountUserDetailSession.BalanceSta_add);
                bb.setBalanceType(AccountUserDetailSession.BalanceType_project_balance_agent);
                bb.setId(b2ac.getToInnerId());
                bb.setTime(time);
                bb.setTomoney(toAccountTradeProject.getBalance());
                Boolean ac = AccountUserDetailSession.getOldInnerBanToNewDetailSq(bb, sq);


                if (ac == false) {
                    sq.rollback();
                    throw new YtbError(YtbError.CODE_FAIL, "projectBalanceAgent-ProjecTransactionServiceImpl");

                }

                int updateNum = innerBalanceMapper.updatePbFrozen2Balance(b2ac);

                if (updateNum <= 0) {
                    YtbLog.logError("innerBalanceMapper.updatePbFrozen2Balance", b2ac);
                    throw new YtbError(YtbError.CODE_FAIL, "项目冻结金额入账失败-ProjecTransactionServiceImpl");
                }


                if (num > 0) {
                    TradeProjectToDetailModel as = new TradeProjectToDetailModel();
                    //   as.setAccountTrade(acTrade);
                    // TradeToDetailModel as = new TradeToDetailModel();
                    as.setAccountTrade(toAccountTradeProject);
                    as.setBalanceSta(AccountUserDetailSession.BalanceSta_add);
                    as.setBalanceType(AccountUserDetailSession.BalanceType_balance);
                    as.setId(b2ac.getToInnerId());
                    as.setTime(time);
                    as.setTomoney(toAccountTradeProject.getBalance());
                    Boolean tacste = AccountUserDetailSession.getOldInnerBanToNewDetailSq(as, sq);

                    if (tacste == false) {
                        sq.rollback();
                        throw new YtbError(YtbError.CODE_FAIL, "projectBalanceAgent-ProjecTransactionServiceImpl");

                    }
                    if (datab < 0) {
                        sq.rollback();
                        throw new YtbError(YtbError.CODE_INVALID_USER, "新数据插入执行失败!-ProjecTransactionServiceImpl");
                    }

                } else {
                    sq.rollback();
                    throw new YtbError(YtbError.CODE_INVALID_USER, "新数据插入执行失败!-ProjecTransactionServiceImpl");
                }
                //税费入账
            }
            sq.commit();
            return toAcList;
        }

    }


    @Override
    public int withdrawCash(AccountTrade record, Integer innerId, BigDecimal balance) {

        SqlSession sq = MyBatisUtil.getSession(false);
        int accountTradeSta = 0;
        int databal = 0;
        int sta = 0;
        // int datae = 0;
        Date time = new Date();
        try {

            AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);

            if (record.getBalance().compareTo(BigDecimal.ZERO) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");

            }

            AccountTradeProject accountTrade = new AccountTradeProject();
            accountTrade.setAcId(record.getAcId());
            accountTrade.setTradeType(TradeConst.TRADE_TYPE_Withdraw_Deposit);
            accountTrade.setTradeSubtype(record.getTradeSubtype());
            accountTrade.setServiceType(TradeConst.SERVICE_TYPE_cash);

            accountTrade.setTotalBalance(balance);
            ServiceBalanceService serviceBalanceService = new ServiceBalanceServiceImpl();
            /*计算服务费*/
            BigDecimal feeBalance = serviceBalanceService.serviceBalance(balance, record.getTradeSubtype());

            if (feeBalance == null) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "服务费计算失败-ProjecTransactionServiceImpl");


            }
            accountTrade.setBalance(balance.subtract(feeBalance));
            accountTrade.setServiceFee(BigDecimal.ZERO);
            accountTrade.setTax(BigDecimal.ZERO);
            accountTrade.setFee(feeBalance);
            accountTrade.setStatus(TradeConst.status_pending_trial);

            accountTrade.setCompanyId(record.getCompanyId());
            accountTrade.setUserId(record.getUserId());
            accountTrade.setOutId(record.getOutId());

            accountTradeSta = accountTradeMapper.insertSelective(accountTrade);

            if (accountTradeSta < 0) {
                sq.rollback();
                return sta;
            }


            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);

            databal = accountUserInnerBalanceMapper.updateBalanceReduceByWithdrawCash(innerId, balance);
            if (databal <= 0) {
                throw new YtbError(YtbError.CODE_FAIL, "金额支出失败-ProjecTransactionServiceImpl");
            }

            AccountUserDetailSession detailSession = new AccountUserDetailSession();


            TradeProjectToDetailModel tradeToDetailModel = new TradeProjectToDetailModel();
            tradeToDetailModel.setTomoney(accountTrade.getBalance());
            tradeToDetailModel.setTime(time);
            tradeToDetailModel.setId(accountTrade.getAcId());
            tradeToDetailModel.setBalanceType(AccountUserDetailSession.BalanceType_balance);
            tradeToDetailModel.setBalanceSta(AccountUserDetailSession.BalanceSta_subtract);
            tradeToDetailModel.setAccountTrade(accountTrade);


            Boolean accountUserDetail = detailSession
                    .getOldInnerBanToNewDetailSq(tradeToDetailModel, sq);

            if (accountUserDetail == false) {
                throw new YtbError(YtbError.CODE_FAIL, "流水更新失败-ProjecTransactionServiceImpl");
            }

            UserCenterServiceImpl userCenterService = new UserCenterServiceImpl();
            UserLoginModel userLoginModel = userCenterService.getUserLoginInfoById(record.getUserId());

            CompanyCenterServiceImpl companyCenterService = new CompanyCenterServiceImpl();
            CompanyInfoModel companyInfoModel = companyCenterService.getCompanyInfoByCompanyId(userLoginModel.getCompanyUserId());

            UserInfoModel userInfoModel
                    = userCenterService.getUserInfoById(userLoginModel.getUserId());

            String name = "";

            if (companyInfoModel != null) {
                name += "companyName:'" + companyInfoModel.getCompanyName() + "',";
            }
            if (userInfoModel != null) {
                name += ("realName:'" + userInfoModel.getRealName() + "',");
            }


            AccountUserOutMapper accountUserOutMapper = sq.getMapper(AccountUserOutMapper.class);

            AccountUserOut accountUserOut = accountUserOutMapper.selectByPrimaryKey(accountTrade.getOutId());


            TaskNotifyServiceImpl taskNotifyService = new TaskNotifyServiceImpl();


            WithdrawNotice withdrawNotice = tradeAndOutToWithdrawNotice(accountTrade, accountUserOut);


            int certificationUser = taskNotifyService.certificationUser(
                    record.getUserId()
                    , 51,
                    JSON.toJSONString(withdrawNotice));


            if (accountTradeSta > 0 & databal > 0 & certificationUser == 0) {
                sta = 1;
                sq.commit();
            } else {
                sq.rollback();
            }

        } catch (Exception e) {
            sq.rollback();
            throw e;
        } finally {
            sq.close();

        }
        return sta;

    }


    public WithdrawNotice tradeAndOutToWithdrawNotice(AccountTradeProject accountTrade, AccountUserOut accountUserOut) {
        WithdrawNotice withdrawNotice = new WithdrawNotice();

        try {
            withdrawNotice.setTradeId(accountTrade.getTradeId());
            withdrawNotice.setBalance(accountTrade.getBalance());
            withdrawNotice.setTotalBalance(accountTrade.getTotalBalance());
            withdrawNotice.setServiceFee(accountTrade.getServiceFee());
            withdrawNotice.setTax(accountTrade.getTax());
            withdrawNotice.setFee(accountTrade.getFee());
            withdrawNotice.setStatus(accountTrade.getStatus());
            withdrawNotice.setTradeType(accountTrade.getTradeType());
            withdrawNotice.setAcId(accountTrade.getAcId());
            withdrawNotice.setTradeOutId(accountTrade.getOutId());
            withdrawNotice.setTradeSubtype(accountTrade.getTradeSubtype());
            withdrawNotice.setUserId(accountTrade.getUserId());
            withdrawNotice.setCompanyId(accountTrade.getCompanyId());
            withdrawNotice.setInnerId(accountTrade.getAcId());
            withdrawNotice.setCardHolder(accountUserOut.getCardHolder());
            withdrawNotice.setBankName(accountUserOut.getBankName());
            withdrawNotice.setBranchName(accountUserOut.getBranchName());
            withdrawNotice.setCardHolderId(accountUserOut.getCardHolderId());
            withdrawNotice.setAccountType(accountUserOut.getAccountType());
            withdrawNotice.setOutId(accountUserOut.getAccountNo());
        } catch (Exception e) {

        }
        return withdrawNotice;
    }

    private static Integer intDate(Date dt) {

        String s = DateFormat.getDateInstance(DateFormat.DEFAULT).format(dt).replaceAll("-", "");
        return Integer.parseInt(s);
    }


    @Override
    public int b2fPaPayProjectFirst(AccountTradeProject accountTrade) {
        AccounTraceProjectCheck.checkParam(accountTrade);

        SqlSession sq = MyBatisUtil.getSession(false);
        Date time = new Date();
        accountTrade.setStatus(AccountConst.STATUS_OK);

        accountTrade.setServiceType(accountTrade.getServiceType());
        accountTrade.setTradeType(accountTrade.getTradeType());
        accountTrade.setTradeSubtype(accountTrade.getTradeSubtype());
        accountTrade.setTotalBalance(accountTrade.getBalance());

        int data = 0;
        int datab = 0;
        int sta = 0;
        try {

            AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);

            accountTrade.setAddTime(time);
            accountTrade.setTermId(intDate(time));
            data = accountTradeMapper.insertSelective(accountTrade);
            //System.out.println(accountTrade);
            if (accountTrade.getBalance().compareTo(BigDecimal.ZERO) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");
            }


            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            datab = accountUserInnerBalanceMapper.updateBalanceB2FMinusServiceFee(
                    accountTrade.getAcId(), accountTrade.getBalance());
            if (datab <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, accountTrade.getAcId() + "余额不足，冻结失败!");
            }


            AccountUserDetailSession session = new AccountUserDetailSession();


            TradeProjectToDetailModel as = new TradeProjectToDetailModel();
            as.setAccountTrade(accountTrade);
            as.setBalanceSta(AccountUserDetailSession.BalanceSta_subtract);
            as.setBalanceType(AccountUserDetailSession.BalanceType_balance);
            as.setId(accountTrade.getAcId());
            as.setTime(time);
            as.setTomoney(accountTrade.getBalance());


            Boolean dtl = session.getOldInnerBanToNewDetailSq(as, sq);
            if (dtl == false) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "流水更新失败-ProjecTransactionServiceImpl");
            }


            TradeProjectToDetailModel bs = new TradeProjectToDetailModel();
            bs.setAccountTrade(accountTrade);
            bs.setBalanceSta(AccountUserDetailSession.BalanceSta_add);
            bs.setBalanceType(AccountUserDetailSession.BalanceType_balance_agent);
            bs.setId(accountTrade.getAcId());
            bs.setTime(time);
            bs.setTomoney(accountTrade.getBalance());

            Boolean dtle = session.getOldInnerBanToNewDetailSq(bs, sq);

            if (dtle == false) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "bs'流水更新失败-ProjecTransactionServiceImpl");
            }


            if (data > 0 & datab > 0 & dtl == true & dtle == true) {

                sta = accountTrade.getTradeId();
                sq.commit();
            } else {
                sq.rollback();
            }

        } catch (Exception e) {
            sq.rollback();
            e.printStackTrace();
            throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");
        } finally {
            sq.close();
        }
        return sta;

    }

    @Override
    public Integer insertTradeAndUserDetailService(AccountTradeProject accountTrade, AccountUserDetail accountUserDetail) {

        AccounTraceProjectCheck.checkParam(accountTrade);
        SqlSession sq = MyBatisUtil.getSession(false);

        int tdata = 0;
        Integer tradeId = 0;
        int tdatab = 0;
        try {

            AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);
            tradeId = accountTradeMapper.insertSelective(accountTrade);
            if (tradeId <= 0) {
                throw new YtbError(YtbError.CODE_FAIL, "订单生成失败-ProjecTransactionServiceImpl");
            }

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            accountUserDetail.setTradeId(accountTrade.getTradeId());
            tdatab = accountUserDetailMapper.insertSelective(accountUserDetail);
            if (tdatab <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "流水更新失败-ProjecTransactionServiceImpl");
            }

            if (tdata > 0 && tdatab > 0) {
                sq.commit();
            } else {
                sq.rollback();
            }


        } catch (Exception e) {

            sq.rollback();
            throw e;
        } finally {
            sq.close();

        }


        return tradeId;

    }


    @Override
    public int customerRefund(AccountTrade record, Integer outAcId, Integer InAcId, BigDecimal balance) {

        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;
        int datab = 0;
        int datac = 0;
        int sta = 0;
        try {
            if (record.getBalance().compareTo(BigDecimal.ZERO) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");

            }
            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);

            data = accountTradeMapper.updateByPrimaryKeySelective(record);
            if (datac <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "更新订单失败-ProjecTransactionServiceImpl");
            }
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);

            datab = accountUserInnerBalanceMapper.updateBalanceAndOutReduce(InAcId, balance);
            if (datac <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "增出账失败-ProjecTransactionServiceImpl");
            }
            datac = accountUserInnerBalanceMapper.updateBalanceIncreaseByInnerId(outAcId, balance);
            if (datac <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "增加收入失败-ProjecTransactionServiceImpl");
            }


            if (data > 0 && datab > 0 && datac > 0) {
                sta = 1;
                sq.commit();
            } else {
                sq.rollback();
            }

        } catch (Exception e) {
            sq.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            sq.close();

        }
        return sta;

    }

    @Override
    public int transferService(AccountTradeProject accountTrade/*, String tradeItem*/) {
        AccounTraceProjectCheck.checkParam(accountTrade);
        SqlSession sq = MyBatisUtil.getSession(false);

        int accountTradeSta = 0;
        int acsta = 0;


        int datae = 0;
        int dataf = 0;
        int sta = 0;
        try {

            if (accountTrade.getBalance().compareTo(BigDecimal.ZERO) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");

            }
            AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);
            accountTradeSta = accountTradeMapper.insertSelective(accountTrade);

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            /*AccountUserDetail流水记录*/

            AccountUserDetail accountUserDetail = new AccountUserDetail();
            accountUserDetail.setStatus(AccountConst.STATUS_AUDIT_PASS);
            //  accountUserDetail.setTermId(accountTrade.getTradeId());
            accountUserDetail.setInnerId(accountTrade.getAcId());
            accountUserDetail.setTradeId(accountTrade.getTradeId());
            /* accountUserDetail.setTradeItem(tradeItem);*/
            accountUserDetail.setTradeBalance(accountTrade.getBalance());//交易金额
            accountUserDetail.setInTime(new Date());
            accountUserDetail.setTradeSubtype(accountTrade.getTradeSubtype());//交易方式


            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);



            /*甲方流水*/


            AccountUserInner ac = accountUserInnerMapper.selectByPrimaryKey(accountTrade.getAcId());
            accountUserDetail.setBalance(ac.getBalance().subtract(accountTrade.getBalance()));//减去流水总金额
            accountUserDetail.setInnerId(accountTrade.getAcId());//甲
            accountUserDetail.setTradeType(TradeConst.TRADE_TYPE_PAYOUT);//支出
            accountUserDetail.setBalanceType(AccountUserDetailSession.BalanceType_balance);
            accountUserDetail.setOriginalBalance(ac.getBalance());

            datae = accountUserDetailMapper.insertSelective(accountUserDetail);
            if (datae <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "甲方流水创建失败-ProjecTransactionServiceImpl");
            }


            //乙方流水

            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(accountTrade.getToAcId());//乙方
            accountUserDetail.setBalance(toAc.getBalance().add(accountTrade.getBalance()));//加起来
            accountUserDetail.setInnerId(accountTrade.getToAcId());//甲
            accountUserDetail.setBalanceType(AccountUserDetailSession.BalanceType_balance);
            accountUserDetail.setTradeType(TradeConst.TRADE_TYPE_INCOME);//收入
            accountUserDetail.setOriginalBalance(toAc.getBalance());//原来
            dataf = accountUserDetailMapper.insertSelective(accountUserDetail);
            if (dataf <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "乙方流水创建失败-ProjecTransactionServiceImpl");
            }

            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            /*甲方扣钱*/
            acsta = accountUserInnerBalanceMapper.updateBalanceAndOutReduce(accountTrade.getAcId(), accountTrade.getBalance());
            if (acsta <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "甲方扣费失败-ProjecTransactionServiceImpl");
            }
            /*乙方加钱*/
            int bdsa = accountUserInnerBalanceMapper.updateBalanceIncreaseByInnerId(accountTrade.getToAcId(), accountTrade.getBalance());
            if (bdsa <= 0) {
                throw new YtbError(YtbError.CODE_FAIL, "乙方加钱失败-ProjecTransactionServiceImpl");
            }
            if (accountTradeSta > 0 && acsta > 0 && datae > 0 && dataf > 0) {
                sq.commit();
                /* sta = 1;*/
                sta = accountTrade.getTradeId();
            } else {
                sq.rollback();
            }

        } catch (Exception e) {

            sq.rollback();
            throw e;
        } finally {
            sq.close();

        }
        return sta;

    }

    @Override
    public int transferService(AccountTrade accountTrade/*, String tradeItem*/) {
        AccounTraceCheck.checkParam(accountTrade);
        SqlSession sq = MyBatisUtil.getSession(false);

        int accountTradeSta = 0;
        int acsta = 0;


        int datae = 0;
        int dataf = 0;
        int sta = 0;
        try {

            if (accountTrade.getBalance().compareTo(BigDecimal.ZERO) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");

            }
            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            accountTradeSta = accountTradeMapper.insertSelective(accountTrade);

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            /*AccountUserDetail流水记录*/

            AccountUserDetail accountUserDetail = new AccountUserDetail();
            accountUserDetail.setStatus(AccountConst.STATUS_AUDIT_PASS);
            //  accountUserDetail.setTermId(accountTrade.getTradeId());
            accountUserDetail.setInnerId(accountTrade.getAcId());
            accountUserDetail.setTradeId(accountTrade.getTradeId());
            /* accountUserDetail.setTradeItem(tradeItem);*/
            accountUserDetail.setTradeBalance(accountTrade.getBalance());//交易金额
            accountUserDetail.setInTime(new Date());
            accountUserDetail.setTradeSubtype(accountTrade.getTradeSubtype());//交易方式


            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);



            /*甲方流水*/


            AccountUserInner ac = accountUserInnerMapper.selectByPrimaryKey(accountTrade.getAcId());
            accountUserDetail.setBalance(ac.getBalance().subtract(accountTrade.getBalance()));//减去流水总金额
            accountUserDetail.setInnerId(accountTrade.getAcId());//甲
            accountUserDetail.setTradeType(TradeConst.TRADE_TYPE_PAYOUT);//支出
            accountUserDetail.setBalanceType(AccountUserDetailSession.BalanceType_balance);
            accountUserDetail.setOriginalBalance(ac.getBalance());

            datae = accountUserDetailMapper.insertSelective(accountUserDetail);
            if (datae <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "甲方流水创建失败-ProjecTransactionServiceImpl");
            }


            //乙方流水

            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(accountTrade.getToAcId());//乙方
            accountUserDetail.setBalance(toAc.getBalance().add(accountTrade.getBalance()));//加起来
            accountUserDetail.setInnerId(accountTrade.getToAcId());//甲
            accountUserDetail.setBalanceType(AccountUserDetailSession.BalanceType_balance);
            accountUserDetail.setTradeType(TradeConst.TRADE_TYPE_INCOME);//收入
            accountUserDetail.setOriginalBalance(toAc.getBalance());//原来
            dataf = accountUserDetailMapper.insertSelective(accountUserDetail);
            if (dataf <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "乙方流水创建失败-ProjecTransactionServiceImpl");
            }

            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            /*甲方扣钱*/
            acsta = accountUserInnerBalanceMapper.updateBalanceAndOutReduce(accountTrade.getAcId(), accountTrade.getBalance());
            if (acsta <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "甲方扣费失败-ProjecTransactionServiceImpl");
            }
            /*乙方加钱*/
            int bdsa = accountUserInnerBalanceMapper.updateBalanceIncreaseByInnerId(accountTrade.getToAcId(), accountTrade.getBalance());
            if (bdsa <= 0) {
                throw new YtbError(YtbError.CODE_FAIL, "乙方加钱失败-ProjecTransactionServiceImpl");
            }
            if (accountTradeSta > 0 && acsta > 0 && datae > 0 && dataf > 0) {
                sq.commit();
                /* sta = 1;*/
                sta = accountTrade.getTradeId();
            } else {
                sq.rollback();
            }

        } catch (Exception e) {

            sq.rollback();
            throw e;
        } finally {
            sq.close();

        }
        return sta;

    }

    @Override
    public Boolean projectRefund(Integer tradeId, BigDecimal RefundM) {
        SqlSession sq = MyBatisUtil.getSession(false);
        try {


            if (ProjectCheck.BalancePlusminusCheck(RefundM) == false) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "钱值不能为负-ProjecTransactionServiceImpl");
            }


            Integer accTradeId = projectRefundSq(tradeId, RefundM, sq);
            if (accTradeId == null) {
                sq.commit();
                return true;
            } else {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "退款失败-ProjecTransactionServiceImpl");
            }

        } catch (Exception e) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "失败-ProjecTransactionServiceImpl");
        } finally {
            sq.close();
        }

    }


    public Integer projectRefundSq(Integer accountTrade, BigDecimal RefundM, SqlSession sq) {
        Integer accTradeId = null;
        try {
            Date time = new Date();

            AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);
            AccountTradeProject trade = accountTradeMapper.selectByPrimaryKey(accountTrade);

            if (trade == null) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "订单不存在-ProjecTransactionServiceImpl");
            }

            if (RefundM.compareTo(BigDecimal.ZERO) != 1 && trade.getBalance().compareTo(RefundM) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "退款金额错误-ProjecTransactionServiceImpl");
            }

            if ((trade.getRefundBalance().add(RefundM)).compareTo(trade.getBalance()) != -1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "退款总额超过单笔订单总金额");
            }


            AccountTradeProject uptr = new AccountTradeProject();
            uptr.setTradeId(accountTrade);
            uptr.setRefundBalance(trade.getRefundBalance().add(RefundM));
            int asfdas = accountTradeMapper.updateByPrimaryKeySelective(uptr);
            if (asfdas <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "更新订单失败-ProjecTransactionServiceImpl");
            }

            AccountTradeProject accTrade = new AccountTradeProject();
            accTrade.setTradeNoPre(String.valueOf(trade.getTradeId()));
            accTrade.setAcId(trade.getToAcId());
            accTrade.setToAcId(trade.getAcId());
            accTrade.setAddTime(new Date());
            accTrade.setTradeType(TradeConst.TRADE_TYPE_PAYOUT_REFUND);
            accTrade.setServiceType(TradeConst.SERVICE_TYPE_project);
            accTrade.setStatus(TradeConst.status_success);
            accTrade.setBalance(RefundM);
            accTrade.setTotalBalance(RefundM);
            accTrade.setTradeSubtype(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);

            accTrade.setProjectId(trade.getProjectId());
            accTrade.setTradeNoPre(String.valueOf(accountTrade));
            accTradeId = accountTradeMapper.insertSelective(accTrade);
            if (accTradeId < 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "用户交易表(account_Trade)添加记录失败-ProjecTransactionServiceImpl");
            }


    /*甲方加钱 * /


        / * 提现冻结费用+++
         * takeout_money+++   */


            AccountTradeProject neoutRefun = new AccountTradeProject();
            neoutRefun.setTradeNoPre(String.valueOf(trade.getTradeId()));
            neoutRefun.setAcId(trade.getToAcId());
            neoutRefun.setToAcId(trade.getAcId());
            neoutRefun.setAddTime(time);
            neoutRefun.setTradeType(TradeConst.TRADE_TYPE_PAYOUT_REFUND);
            neoutRefun.setServiceType(TradeConst.SERVICE_TYPE_project);
            neoutRefun.setStatus(TradeConst.status_success);
            neoutRefun.setBalance(RefundM);
            neoutRefun.setTotalBalance(RefundM);
            neoutRefun.setTradeSubtype(AccountConst.TRADE_SUBTYPE_FROZEN_2_FROZEN);
            int asd = accountTradeMapper.insertSelective(neoutRefun);

            //甲方流水
            TradeProjectToDetailModel as = new TradeProjectToDetailModel();
            as.setAccountTrade(neoutRefun);
            as.setBalanceSta(AccountUserDetailSession.BalanceSta_add);
            as.setBalanceType(AccountUserDetailSession.BalanceType_balance_agent);
            as.setId(trade.getAcId());

            as.setTime(time);
            as.setTomoney(RefundM);

            AccountUserDetailSession session = new AccountUserDetailSession();
            Boolean dtle = session.getOldInnerBanToNewDetailSq(as, sq);

            if (dtle == false) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "流水更新失败-ProjecTransactionServiceImpl");
            }

            //乙方方流水
            TradeProjectToDetailModel bs = new TradeProjectToDetailModel();
            bs.setAccountTrade(neoutRefun);
            bs.setBalanceSta(AccountUserDetailSession.BalanceSta_subtract);
            bs.setBalanceType(AccountUserDetailSession.BalanceType_project_balance_agent);
            bs.setId(trade.getToAcId());
            bs.setTime(time);
            bs.setTomoney(RefundM);


            Boolean dd = session.getOldInnerBanToNewDetailSq(bs, sq);
            if (dd == false) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "流水更新失败-ProjecTransactionServiceImpl");
            }


            AccountUserInnerBalanceMapper innerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);

//甲方+++
            int tacsta = innerBalanceMapper.updateIncreaseBalanceAgentByInnerId(trade.getAcId(), RefundM);

            if (tacsta <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "甲方更冻结金额失败-ProjecTransactionServiceImpl");
            }
//乙方--

            int agad = innerBalanceMapper.updateProjectBalanceAgentReduceByInnerId(trade.getToAcId(), RefundM);


            if (agad <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "乙方减少项目冻结款失败-ProjecTransactionServiceImpl");
            }

            ///出入账还要回滚

            return accTradeId;

        } catch (Exception e) {
            sq.rollback();
            throw e;
        }
    }


    @Override
    public List<Integer> projectRefunds(List<projectRefunds> projectRefund) {
        SqlSession sq = MyBatisUtil.getSession(false);
        List<Integer> tradeIdList = new ArrayList<>();
        try {


            for (int i = 0; i < projectRefund.size(); i++) {

                if (ProjectCheck.BalancePlusminusCheck(projectRefund.get(i).getBalance()) == false) {
                    sq.rollback();
                    throw new YtbError(YtbError.CODE_FAIL, "钱值不能为负-ProjecTransactionServiceImpl");
                }

                Integer tradeId = projectRefundSq(projectRefund.get(i).getTradeId(), projectRefund.get(i).getBalance(), sq);
                if (tradeId == null) {
                    sq.rollback();
                    sq.rollback();
                    throw new YtbError(YtbError.CODE_FAIL, "执行失败-ProjecTransactionServiceImpl");
                }
                tradeIdList.add(tradeId);
            }
            sq.commit();
            return tradeIdList;
        } catch (Exception e) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "执行失败-ProjecTransactionServiceImpl");
        } finally {
            sq.close();
        }

    }


    @Override
    public Integer balanceAgentToBalance(Integer innerId, BigDecimal RefundM) {
        SqlSession sq = MyBatisUtil.getSession(false);
        Integer tradeId = 0;
        try {
            if (RefundM.compareTo(BigDecimal.ZERO) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");
            }
            AccountTradeProjectMapper accountTradeMapperDao = sq.getMapper(AccountTradeProjectMapper.class);
//            Condition condition = new Condition();
//            condition.setTradeId(tradeId);
            AccountTradeProject accountTradeList = accountTradeMapperDao.selectByPrimaryKey(tradeId);
            if (accountTradeList == null) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "订单不存在-ProjecTransactionServiceImpl");
            }

            //用户交易表account_trade(db:ytb_account) 添加记录
            AccountTradeProject accountTrade = accountTradeList;
            AccountTradeProject accountTrade1 = new AccountTradeProject();
            accountTrade1.setTradeNoPre(String.valueOf(accountTrade.getTradeId()));
            accountTrade1.setProjectId(accountTrade.getProjectId());
            accountTrade1.setAcId(accountTrade.getAcId());
            accountTrade1.setToAcId(accountTrade.getAcId());
            accountTrade1.setTradeType(TradeConst.TRADE_TYPE_PAYOUT_REFUND);//支出退款
            accountTrade1.setTradeSubtype(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN);//冻结款 余额
            accountTrade1.setServiceType(TradeConst.SERVICE_TYPE_project);// 1--项目款
            accountTrade1.setBalance(RefundM);
            accountTrade1.setStatus(AccountConst.STATUS_OK);//10操作成功
            accountTrade1.setIsA(true);
            accountTrade1.setTotalBalance(RefundM);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(d);
            accountTrade1.setTermId(Integer.parseInt(date));
            accountTrade1.setIpAddress("");
            accountTrade1.setAddTime(d);
            tradeId = accountTradeMapperDao.insertSelective(accountTrade1);
            if (tradeId < 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "用户交易表account_trade添加记录失败-ProjecTransactionServiceImpl");
            }

            Date time = new Date();
            AccountUserDetail bbag = new AccountUserDetail();
            bbag.setInnerId(innerId);
            bbag.setBalance(RefundM);

            bbag.setTradeType(TradeConst.TRADE_TYPE_INCOME);


            AccountUserDetailSession detail = new AccountUserDetailSession();

            Boolean sta = detail.getOldInnerBanToNewDetail(
                    bbag, time, RefundM,
                    AccountUserDetailSession.BalanceType_balance_agent,
                    AccountUserDetailSession.BalanceSta_subtract, sq);

            if (sta == false) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "流水更新失败");
            }


            AccountUserDetail bbd = new AccountUserDetail();
            bbd.setBalance(RefundM);
            bbd.setInnerId(innerId);
            bbd.setTradeType(TradeConst.TRADE_TYPE_INCOME);
            Boolean stb = detail.getOldInnerBanToNewDetail(
                    bbd, time, RefundM,
                    AccountUserDetailSession.BalanceType_balance,
                    AccountUserDetailSession.BalanceSta_add, sq);
            if (stb == false) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "流水更新失败-ProjecTransactionServiceImpl");
            }
            //  AccountUserDetailSession.getOldInnerBanToNewDetail()
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);

            int bbg = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(innerId, RefundM);

            if (bbg <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额操作失败-ProjecTransactionServiceImpl");
            }
            int bb = accountUserInnerBalanceMapper.updateIncreaseIncrease(innerId, RefundM);
            if (bb <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额操作失败-ProjecTransactionServiceImpl");
            }

            sq.commit();
            return tradeId;
        } catch (Exception e) {
            sq.rollback();
            throw e;
        } finally {
            sq.close();
        }

    }

    @Override
    public Integer frozenAmount(AccountTradeProject accountTrade/*, String tradeItem*/) {

        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;
        int datab = 0;
        int datac = 0;
        Integer sta = 0;
        try {


            //  accountTrade.setStatus(AccountConst.STATUS_AUDIT_PASS);


            if (accountTrade.getBalance().compareTo(BigDecimal.ZERO) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");


            }
            AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);
            data = accountTradeMapper.insertSelective(accountTrade);

            if (data <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "新订单生成失败-ProjecTransactionServiceImpl");
            }

            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            datab = accountUserInnerBalanceMapper.updateBalanceReduceByInnerId(accountTrade.getAcId(), accountTrade.getBalance());


            if (datab <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "冻结金额失败-ProjecTransactionServiceImpl");
            }

            //甲方流水
            AccountUserDetail accountUserDetail = new AccountUserDetail();
            //   accountUserDetail.setTermId(accountTrade.getTradeId());
            accountUserDetail.setInnerId(accountTrade.getAcId());
            accountUserDetail.setTradeId(accountTrade.getTradeId());
            /* accountUserDetail.setTradeItem(tradeItem);*/
            accountUserDetail.setBalance(accountTrade.getBalance());
            accountUserDetail.setInTime(new Date());

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            datac = accountUserDetailMapper.insertSelective(accountUserDetail);
            if (datab <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "流水创建失败-ProjecTransactionServiceImpl");
            }

            if (data > 0 & datab > 0 & datac > 0) {
                sta = accountTrade.getTradeId();
                sq.commit();
            } else {
                sq.rollback();
            }

        } catch (Exception e) {
            sq.rollback();
            throw e;
        } finally {
            sq.close();
        }
        return sta;


    }


    @Override
    public Integer frozenAmount(AccountTrade accountTrade/*, String tradeItem*/) {

        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;
        int datab = 0;
        int datac = 0;
        Integer sta = 0;
        try {


            //  accountTrade.setStatus(AccountConst.STATUS_AUDIT_PASS);


            if (accountTrade.getBalance().compareTo(BigDecimal.ZERO) != 1) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负-ProjecTransactionServiceImpl");


            }
            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            data = accountTradeMapper.insertSelective(accountTrade);

            if (data <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "新订单生成失败-ProjecTransactionServiceImpl");
            }

            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            datab = accountUserInnerBalanceMapper.updateBalanceReduceByInnerId(accountTrade.getAcId(), accountTrade.getBalance());


            if (datab <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "冻结金额失败-ProjecTransactionServiceImpl");
            }

            //甲方流水
            AccountUserDetail accountUserDetail = new AccountUserDetail();
            //   accountUserDetail.setTermId(accountTrade.getTradeId());
            accountUserDetail.setInnerId(accountTrade.getAcId());
            accountUserDetail.setTradeId(accountTrade.getTradeId());
            /* accountUserDetail.setTradeItem(tradeItem);*/
            accountUserDetail.setBalance(accountTrade.getBalance());
            accountUserDetail.setInTime(new Date());

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            datac = accountUserDetailMapper.insertSelective(accountUserDetail);
            if (datab <= 0) {
                sq.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "流水创建失败-ProjecTransactionServiceImpl");
            }

            if (data > 0 & datab > 0 & datac > 0) {
                sta = accountTrade.getTradeId();
                sq.commit();
            } else {
                sq.rollback();
            }

        } catch (Exception e) {
            sq.rollback();
            throw e;
        } finally {
            sq.close();
        }
        return sta;


    }

    @Override
    public ProjectBalanceAgent projectBalanceRefundToList(ProjectBalanceOutAc  projectBalanceOutAc,
                                                          List<ProjectBalanceToAc> projectBalanceAgentList) {

        int datab = 0;
        List<AccountTradeProject> toAcList = new ArrayList<AccountTradeProject>(projectBalanceAgentList.size());
        SqlSession sq = MyBatisUtil.getSession(false);
        try {
            AccountTradeProjectMapper accountTradeMapper = sq.getMapper(AccountTradeProjectMapper.class);
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            for (int i = 0; i < projectBalanceAgentList.size(); i++) {


                AccountTradeProject toAccountTradeProject = new AccountTradeProject();
                //构建accountTrade--乙方
                if (projectBalanceAgentList.get(i).getBalance() != null) {
                    toAccountTradeProject.setBalance(projectBalanceAgentList.get(i).getBalance());
                }

                if (projectBalanceAgentList.get(i).getFee() != null) {
                    toAccountTradeProject.setFee(projectBalanceAgentList.get(i).getFee());

                }
                if (projectBalanceAgentList.get(i).getTax() != null) {
                    toAccountTradeProject.setTax(projectBalanceAgentList.get(i).getTax());
                }
                if (projectBalanceAgentList.get(i).getBalance() != null) {
                    toAccountTradeProject.setServiceFee(projectBalanceAgentList.get(i).getServiceFee());
                }
                if (projectBalanceAgentList.get(i).getProjectId() != null) {
                    toAccountTradeProject.setProjectId(projectBalanceAgentList.get(i).getProjectId());
                }
                if (projectBalanceAgentList.get(i).getOutInnerId() != null) {
                    toAccountTradeProject.setOutId(projectBalanceAgentList.get(i).getOutInnerId());
                }
                if (projectBalanceAgentList.get(i).getToInnerId() != null) {
                    toAccountTradeProject.setToAcId(projectBalanceAgentList.get(i).getToInnerId());
                }

                if (projectBalanceAgentList.get(i).getUserId() != null) {
                    toAccountTradeProject.setUserId(projectBalanceAgentList.get(i).getUserId());
                }

                if (projectBalanceAgentList.get(i).getCompanyId() != null) {
                    toAccountTradeProject.setCompanyId(projectBalanceAgentList.get(i).getCompanyId());
                }

                if (projectBalanceAgentList.get(i).getTradeId() != null) {
                    //emm 订单编号？？
                    toAccountTradeProject.setTradeId(projectBalanceAgentList.get(i).getTradeId());
                }

                toAccountTradeProject.setTradeType((short) 103);
                toAccountTradeProject.setStatus((byte) 3);
                int newTradeId = accountTradeMapper.insertSelective(toAccountTradeProject);

                if (newTradeId > 0) {
                    //    toAccountTradeProject.setTradeId(newTradeId);//记录这个ID list 然后返回
                    toAcList.add(toAccountTradeProject);

                } else {
                    sq.rollback();
                    throw new YtbError(YtbError.CODE_INVALID_USER, "新数据插入执行失败!-ProjecTransactionServiceImpl");
                }

                datab = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(projectBalanceAgentList.get(i).getToInnerId(), projectBalanceAgentList.get(i).getBalance());
                if (datab > 0) {
                    //    toAccountTradeProject.setTradeId(newTradeId);//记录这个ID list 然后返回

                } else {
                    sq.rollback();
                    throw new YtbError(YtbError.CODE_INVALID_USER, "新数据插入执行失败!-ProjecTransactionServiceImpl");
                }
                //税费入账

            }
            //   accountUserInnerBalanceMapper.updateProjectBalanceAgentReduceToBalanceByInnerId(projectBalanceOutAc.getInnerId(), projectBalanceOutAc.getBalance());
        } finally {
            sq.close();
        }

        throw new YtbError(YtbError.CODE_INVALID_USER, "新数据插入执行失败!");
    }

    /***
     *  zc
     * 甲方支付项目预付款-退款
     * 从甲方的钱包冻结款转到余额
     * balance_agent -》balance
     * */
    @Override
    public Integer paPayoutAgent2Balance(TradeInfo tradeInfo) {

        SqlSession session = MyBatisUtil.getSession(false);
        try {
            //校验userId,payPassword是否匹配
            // new AccountUserInnerBalanceServiceImpl().checkPassword(session, tradeInfo);

            //数据校验 account_Trade表 Balance比较 accountTradeList 根据条件查询account_Trade表得到的数据
            AccountTradeProject accountTrade = balanceAgentToBalanceCheckBalance(tradeInfo.getTradeIdPre(), tradeInfo.getTotalBalance(), session);
            List<AccountTradeProject> accountTrades = new ArrayList<>();
            accountTrades.add(accountTrade);
            //与account_User_Inner中的数据表比较 accountUserInnerList 根据条件查询account_User_Inner表得到的数据
            List<AccountUserInner> accountUserInnerList = checkBalanceAgentToBalance(accountTrades, tradeInfo.getTotalBalance(), session);


            //用户交易表account_trade(db:ytb_account) 添加记录 和 对账单流水-用户明细账户account_user_detail(db:ytb_account) 添加记录 两条
            accountTrade = paPayoutAgent2Balance(accountTrades, tradeInfo, session);

            //用户虚拟账户(内部)account_user_inner (db:ytb_account) 修改记录 balance_agent--> balance
            balanceAgentToBalanceUserInner(accountUserInnerList, accountTrade, tradeInfo.getBalance(), session);

            session.commit();
            return accountTrade.getTradeId();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    /**
     * zc
     * 户虚拟账户(内部)account_user_inner (db:ytb_account) 修改记录 balance_agent--> balance
     *
     * @param accountUserInnerList
     * @param accountTrade
     * @param refund
     * @param sq
     */
    public void balanceAgentToBalanceUserInner(List<AccountUserInner> accountUserInnerList, AccountTradeProject accountTrade, BigDecimal refund, SqlSession sq) {
        AccountUserInnerMapper accountUserInnerMapperDao = sq.getMapper(AccountUserInnerMapper.class);
        AccountUserInner accountUserInner = new AccountUserInner();
        accountUserInner.setInnerId(accountTrade.getAcId());
        accountUserInner.setPayoutAgent(accountUserInnerList.get(0).getPayoutAgent().subtract(refund));
        accountUserInner.setBalance(accountUserInnerList.get(0).getBalance().add(refund));
        accountUserInner.setBalanceOut(accountUserInnerList.get(0).getBalanceOut().subtract(refund));
        int accountUserInnerId = accountUserInnerMapperDao.updateByPrimaryKeySelective(accountUserInner);
        if (accountUserInnerId < 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "用户虚拟账户(内部)account_user_inner修改记录失败-ProjecTransactionServiceImpl");
        }
    }

    /**
     * zc
     * 对账单流水-用户明细账户account_user_detail(db:ytb_account) 添加记录 两条
     *
     * @param accountTrade
     * @param accountTrade1
     * @param refund
     * @param newestTradeId
     * @param sq
     */
    public void balanceAgentToBalanceUserDetail(AccountTradeProject accountTrade, AccountTradeProject accountTrade1, BigDecimal refund, Integer newestTradeId, SqlSession sq) {
        AccountUserDetail accountUserDetail = new AccountUserDetail();
        accountUserDetail.setInnerId(accountTrade.getAcId());
        accountUserDetail.setTradeId(newestTradeId);
        accountUserDetail.setTradeType(accountTrade1.getTradeType());
        accountUserDetail.setTradeSubtype(accountTrade1.getTradeSubtype());
        accountUserDetail.setStatus(accountTrade1.getStatus());
        AccountUserDetailSession detail = new AccountUserDetailSession();
        //用户明细账户account_user_detail添加balance_agent字段减少的记录
        Boolean sta = detail.getOldInnerBanToNewDetail(
                accountUserDetail, refund,
                AccountUserDetailSession.BalanceType_balance_agent,
                AccountUserDetailSession.BalanceSta_subtract, sq);

        if (sta == false) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "用户明细账户account_user_detail添加balance_agent记录失败-ProjecTransactionServiceImpl");
        }
        //用户明细账户account_user_detail添加balance增加的记录
        Boolean stb = detail.getOldInnerBanToNewDetail(
                accountUserDetail, refund,
                AccountUserDetailSession.BalanceType_balance,
                AccountUserDetailSession.BalanceSta_add, sq);

        if (stb == false) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "用户明细账户account_user_detail添加balance记录失败-ProjecTransactionServiceImpl");
        }
    }

    /**
     * zc
     * 用户交易表account_trade(db:ytb_account) 添加记录
     *
     * @param accountTradeList
     * @param refund
     * @param session
     */
    public AccountTradeProject paPayoutAgent2Balance(List<AccountTradeProject> accountTradeList, TradeInfo refund,
                                                          SqlSession session) {
        AccountTradeProjectMapper accountTradeMapperDao = session.getMapper(AccountTradeProjectMapper.class);
        AccountTradeProject accountTrade = accountTradeList.get(0);
        AccountTradeProject accountTrade1 = new AccountTradeProject();
        accountTrade1.setTradeNoPre(String.valueOf(accountTrade.getTradeId()));
        accountTrade1.setProjectId(accountTrade.getProjectId());
        accountTrade1.setTalkId(accountTrade.getTalkId());

        accountTrade1.setAcId(accountTrade.getAcId());
        accountTrade1.setToAcId(accountTrade.getAcId());
        accountTrade1.setTradeType(TradeConst.TRADE_TYPE_PAYOUT_REFUND);//支出退款
        accountTrade1.setTradeSubtype(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN);//冻结款 余额
        accountTrade1.setServiceType(refund.getServiceType());// 1--项目款
        accountTrade1.setBalance(refund.getBalance());
        accountTrade1.setTotalBalance(refund.getTotalBalance());
        accountTrade1.setServiceFee(refund.getServiceFee());
        accountTrade1.setStatus(AccountConst.STATUS_OK);//10操作成功
        accountTrade1.setIsA(true);
        accountTrade1.setUserId(refund.getUserId());
        accountTrade1.setCreateBy(refund.getUserId());
        accountTrade1.setCompanyId(refund.getCompanyId());
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(d);
        accountTrade1.setTermId(Integer.parseInt(date));
        accountTrade1.setIpAddress("");
        accountTrade1.setAddTime(d);
        int newestTradeId = accountTradeMapperDao.insertSelective(accountTrade1);
        if (newestTradeId < 0) {
            session.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "用户交易表account_trade添加记录失败-ProjecTransactionServiceImpl");
        }
        //对账单流水-用户明细账户account_user_detail(db:ytb_account) 添加记录 两条
        balanceAgentToBalanceUserDetail(accountTrade, accountTrade1, refund.getBalance(), newestTradeId, session);
        return accountTrade;

    }

    /**
     * zc
     * 与account_User_Inner中的数据表比较
     *
     * @param accountTradeList
     * @param refund
     * @param sq
     * @return
     */

    public List<AccountUserInner> checkBalanceAgentToBalance(List<AccountTradeProject> accountTradeList,
                                                             BigDecimal refund, SqlSession sq) {

        AccountUserInnerBalanceMapper mapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
        //WalletCondition walletCondition = new WalletCondition();
        //walletCondition.setInnerId(accountTradeList.get(0).getAcId());
        AccountUserInner userInner = mapper.queryAccountUserInnerByInnerId(accountTradeList.get(0).getAcId());
        if (userInner == null) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "未找到订单中转出账户-ProjecTransactionServiceImpl");
        }
        if (refund.compareTo(userInner.getPayoutAgent()) > 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "退款金额大于托管账户余额(支付冻结)金额-ProjecTransactionServiceImpl");
        }
        List<AccountUserInner> userInners = new ArrayList<>();
        userInners.add(userInner);
        return userInners;
    }

    /**
     * zc
     * 数据校验 account_Trade表 Balance比较
     *
     * @param tradeId
     * @param refund
     * @param sq
     * @return
     */
    public AccountTradeProject balanceAgentToBalanceCheckBalance(Integer tradeId, BigDecimal refund, SqlSession sq) {
        //退款金额为0或者负数
        if (refund.compareTo(BigDecimal.ZERO) == -1 || refund.compareTo(BigDecimal.ZERO) == 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "金额不能为负或者0-ProjecTransactionServiceImpl");
        }
        //订单不存在
        if (tradeId == null) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "订单号不能为空-ProjecTransactionServiceImpl");
        }
        AccountTradeProjectMapper accountTradeMapperDao = sq.getMapper(AccountTradeProjectMapper.class);
        Condition condition = new Condition();
        condition.setTradeId(tradeId);
        //System.out.println(tradeId);
        AccountTradeProject accountTrade = accountTradeMapperDao.selectByPrimaryKey(tradeId);
        if (accountTrade == null) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "订单不存在-ProjecTransactionServiceImpl");
        }

        //退款金额大于订单金额
        if (refund.compareTo(accountTrade.getBalance()) > 0) {

            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "退款金额大于订单金额-ProjecTransactionServiceImpl");
        }

        //退款金额大于用户冻结支出金额balance_agent account_user_inner
        if (accountTrade == null || accountTrade.getAcId() == 0) {
            sq.rollback();
            throw new YtbError(YtbError.CODE_FAIL, "甲方账户不存在");
        }

        return accountTrade;
    }


    @Override
    public List<Integer> projectRefundsNoPwd(List<TradeInfo> outInfos, TradeInfo toInfo) {
        SqlSession session = MyBatisUtil.getSession(false);
        List<Integer> tradeIdList = new ArrayList<>();
        try {

            new PmRefund().pmRefund(session, outInfos);

            //退款金额为0或者负数
            if (toInfo.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
                session.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "金额不能为负或者0-ProjecTransactionServiceImpl");
            }
            //将多个乙方（多条退款），每个单独执行退款操作
            Integer tradeId = new Refund2Pa().refund2Pa(session, toInfo);
            //info.getTradeIdPre(), info.getBalance(), info.getUserId(), info.getCompanyId()            );
            tradeIdList.add(tradeId);
            if (tradeId == null) {
                session.rollback();
                throw new YtbError(YtbError.CODE_FAIL, "执行失败-ProjecTransactionServiceImpl");
            }

            session.commit();
            return tradeIdList;
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    @Override
    public boolean checkAccountPassword(int userId, int companyId,
                                        String payPassword) {

        SqlSession sq = MyBatisUtil.getSession();

        try {
            AccountUserInnerBalanceMapper innerMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            AccountUserInner userInners = innerMapper.queryAccountUserInner(
                    companyId > 0 ? 0 : userId, companyId, payPassword);
            if (userInners == null) {
                AccountYtbLog.logError("checkAccountPassword",userId+"检查密码失败");
                throw new YtbError(YtbError.CODE_FAIL, "检查密码");
            }
            return true;
        } catch (Exception e) {
            sq.rollback();
            throw e;
        } finally {
            sq.close();
        }

    }

    @Override
    public AccountUserInner getAccountInfo(int userId, int companyId, String payPassword) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            AccountUserInnerBalanceMapper innerMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            AccountUserInner userInners = innerMapper.queryAccountUserInner(companyId > 0 ? 0 : userId, companyId, payPassword);
            if (userInners == null) {
                throw new YtbError(YtbError.CODE_FAIL, "userId和payPassword不匹配");
            }
            return userInners;
        } catch (Exception e) {
            sq.rollback();
            throw e;
        } finally {
            sq.close();
        }
    }

    @Override
    public AccountUserInner  getAccountInfo(int userId, int companyId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            AccountUserInnerMapper innerMapper = sq.getMapper(AccountUserInnerMapper.class);

            AccountUserInnerExample example = new AccountUserInnerExample();
            AccountUserInnerExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andCompanyIdEqualTo(companyId);

            List<AccountUserInner> userInners = innerMapper.selectByExample(example);


            if (userInners == null || userInners.size() == 0) {

                throw new YtbError(YtbError.CODE_FAIL, "userId和payPassword不匹配");
            }
            return userInners.get(0);

        } catch (Exception e) {
            sq.rollback();
            throw e;
        } finally {
            sq.close();
        }
    }


}
