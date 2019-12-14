package ytb.account.wallet.rest.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.log.notify.model.TaskLog_TaskModel;
import ytb.log.notify.service.impl.TaskNotifyServiceImpl;

import ytb.account.wallet.model.*;

import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.sq.business.user.BalanceTransactionService;
import ytb.account.wallet.service.sq.business.user.ProjecTransactionService;
import ytb.account.wallet.service.sq.business.user.impl.AccountUserInnerBalanceServiceImpl;
import ytb.account.wallet.rest.impl.sq.SysAccountUserInnerServer;
import ytb.account.wallet.service.sq.business.user.impl.BalanceTransactionServiceImpl;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.account.wallet.service.sq.basics.impl.AccountUserInnerServiceImpl;
import ytb.account.wallet.service.sq.business.user.impl.ProjecTransactionServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 用户验证支付密码 消费金额并且记录
 */
public class SysAccountUserRechargeService {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;


//提现审核通过 向第三方发起提现
   /* public boolean withdrawCashAdopt(Integer tradeId) {


        boolean sta = false;

        AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
        AccountPfInnerServiceImpl accountPfInnerService = new AccountPfInnerServiceImpl();



        return sta;
    }
    */




    /*new*
     *
     *
     *
     * *
     *
     *
     *
     *
     *
     *
     *
     * */

    public MsgResponse accountReduce(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountUserInner data = JSONObject.parseObject(req.msgBody.toString(), AccountUserInner.class);


        String token = req.token;
        if (token != null) {

            if (loginSso != null) {


                if (loginSsoJson.getUserType() != null) {


                    List<AccountUserInner> accountUserInnerList = getAccountUserInnerList(loginSsoJson.getUserId(), data.getPayPassword());


                    if (accountUserInnerList != null) {
                        AccountUserInnerBalanceServiceImpl accountUserInnerBalanceService
                                = new AccountUserInnerBalanceServiceImpl();

                        int sta = accountUserInnerBalanceService.updateBalanceReduceByInnerId(accountUserInnerList
                                .get(0).getInnerId(), data.getAmountMoney());

                        if (sta > 0) {

                            AccountUserDetail accountUserDetail = new AccountUserDetail();
                            accountUserDetail.setBalance(data.getAmountMoney());
                            accountUserDetail.setInnerId(accountUserInnerList.get(0).getInnerId());
                            accountUserDetail.setInTime(new Date());


                            AccountTradeProject accountTrade = new AccountTradeProject();
                            accountTrade.setTradeType(AccountConst.TRADE_SUBTYPE_BALANCE_2_BALANCE);
                            accountTrade.setToAcId(accountUserInnerList.get(0).getInnerId());
                            accountTrade.setBalance(data.getAmountMoney());

                            ProjecTransactionService accountTradeService = new ProjecTransactionServiceImpl();
                            int a = accountTradeService.insertTradeAndUserDetailService(accountTrade, accountUserDetail);

                            if (a > 0) {
                                msgBody = "{\"list\":" + JSONArray.toJSONString(accountUserInnerList) + "}";
                            } else {
                                retcode = 1;
                                retmsg = "失败";
                            }
                        } else {
                            retcode = 1;
                            retmsg = "失败";
                        }
                    } else {
                        retcode = 1;
                        retmsg = "支付密码错误";
                    }

                } else {
                    retcode = 1;
                    retmsg = "未能获取userId";
                }
            } else {
                retcode = 1;
                retmsg = "Token数据获取失败";
            }
        } else {
            retcode = 1;
            retmsg = "请传token";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    public List<AccountUserInner> getAccountUserInnerList(Integer userId, String password) {
        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPayPasswordEqualTo(password);
        List<AccountUserInner> accountUserInnerList = accountUserInnerService
                .selectByExample(accountUserInnerExample);
        return accountUserInnerList;
    }









/*
    public List<AccountUserInner> getInnerIdByUser(Integer userId) {
        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();
        criteria.andUserIdEqualTo(userId);

        List<AccountUserInner> accountUserInnerList = accountUserInnerService.selectByExample(accountUserInnerExample);

        return accountUserInnerList;

    }
*/

    /*s使用密码*/
    public MsgResponse accountIncreases(MsgRequest req, RestHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountUserInner data = JSONObject.parseObject(req.msgBody.toString(), AccountUserInner.class);

        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();


        String token = req.token;
        if (token != null) {
            //LoginSso loginSso = SafeContext.getLog_sso(token);
            if (loginSso != null) {
                //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

                if (loginSsoJson.getUserId() != null) {
                    criteria.andUserIdEqualTo(loginSsoJson.getUserId());
                    criteria.andPayPasswordEqualTo(data.getPayPassword());
                    List<AccountUserInner> accountUserInnerList = accountUserInnerService
                            .selectByExample(accountUserInnerExample);

                    if (accountUserInnerList.size() > 0) {
                        AccountUserInnerBalanceServiceImpl accountUserInnerBalanceService
                                = new AccountUserInnerBalanceServiceImpl();

                        int sta = accountUserInnerBalanceService.updateIncreaseIncrease(accountUserInnerList
                                .get(0).getInnerId(), data.getAmountMoney());
                        if (sta > 0) {


                            /*AccountUserDetailServiceImpl accountUserDetailService = new AccountUserDetailServiceImpl();*/
                            AccountUserDetail accountUserDetail = new AccountUserDetail();
                            accountUserDetail.setBalance(data.getAmountMoney());
                            accountUserDetail.setInnerId(accountUserInnerList.get(0).getInnerId());
                            accountUserDetail.setInTime(new Date());
                            /*    int a = accountUserDetailService.insertSelective(accountUserDetail);*/

                            ProjecTransactionService accountTradeService = new ProjecTransactionServiceImpl();
                            AccountTradeProject accountTrade = new AccountTradeProject();
                            accountTrade.setTradeType((short) 101);
                            accountTrade.setToAcId(accountUserInnerList.get(0).getInnerId());
                            accountTrade.setBalance(data.getAmountMoney());

                            int a = accountTradeService.insertTradeAndUserDetailService(accountTrade, accountUserDetail);


                            if (a > 0) {
                                msgBody = "{\"list\":" + JSONArray.toJSONString(accountUserInnerList) + "}";
                            } else {
                                retcode = 1;
                                retmsg = "失败";
                            }
                        } else {
                            retcode = 1;
                            retmsg = "失败";
                        }
                    } else {
                        retcode = 1;
                        retmsg = "支付密码错误";
                    }

                } else {
                    retcode = 1;
                    retmsg = "未能获取userId";
                }
            } else {
                retcode = 1;
                retmsg = "Token数据获取失败";
            }
        } else {
            retcode = 1;
            retmsg = "请传token";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse customerReceivables(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        AccountUserInner data = JSONObject.parseObject(req.msgBody.toString(), AccountUserInner.class);


        data.getBalance();
        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse newOrder(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        try {
            AccountTradeProject data = JSONObject.parseObject(req.msgBody.toString(), AccountTradeProject.class);
            LoginSso loginSso = handler.getUserContext().getLoginSso();//.getLog_ssoAndApiKey(req.token);
            if (loginSso != null) {
                LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

                SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();
                AccountUserInner accountUserInnerList = sysAccountUserInnerServer.getInnerIdByUser(loginSsoJson);


                if (accountUserInnerList != null) {
                    data.setAcId(accountUserInnerList.getInnerId());
                    data.setUserId(loginSsoJson.getUserId());
                    data.setTradeType(TradeConst.TRADE_TYPE_PAYOUT);//103 甲方用户支付

                    //冻结金额
                    ProjecTransactionService accountTradeService = new ProjecTransactionServiceImpl();
                    int sta = accountTradeService.b2fPaPayProjectFirst(data);
                    if (sta > 0) {
                        //流水
                        retcode = 0;
                        retmsg = "成功 订单编号:" + sta;
                    } else {
                        retcode = 1;
                        retmsg = "订单支付失败";
                    }
                } else {
                    retcode = 1;
                    retmsg = "获取用户信息失败";
                }

            }


        } catch (Exception e) {
            retcode = 1;
            retmsg = "Error";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    public MsgResponse receivables(MsgRequest req, RestHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountTrade data = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);
        data.setTradeType(TradeConst.TRADE_TYPE_TRANSFER);

        //LoginSso loginSso = SafeContext.getLog_sso(req.token);
        if (loginSso != null) {
            //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            AccountUserInner accountUserInnerList = sysAccountUserInnerServer.getInnerIdByUser(loginSsoJson);


            if (accountUserInnerList != null) {


                AccountTradeExample accountTradeExample = new AccountTradeExample();
                AccountTradeExample.Criteria criteria = accountTradeExample.createCriteria();
                criteria.andTradeIdEqualTo(data.getTradeId());

                AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
                List<AccountTrade> accountTradeList = accountTradeService.selectByExample(accountTradeExample);

                BalanceTransactionService projecTransactionService = new BalanceTransactionServiceImpl();

//
//                int sta = projecTransactionService.receivables(data, accountTradeList.get(0).getAcId(), accountTradeList.get(0).getToAcId(), accountTradeList.get(0).getBalance());
//
//                if (sta > 0) {
//                    //流水
//                }

            } else {
                retcode = 1;
                retmsg = "Error";
            }

        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    public MsgResponse customerRefund(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountTrade data = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);
        data.setTradeType(TradeConst.TRADE_TYPE_RECHARGE_REFUND);

        //LoginSso loginSso = SafeContext.getLog_sso(req.token);
        if (loginSso != null) {
            //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            AccountUserInner accountUserInnerList = sysAccountUserInnerServer.getInnerIdByUser(loginSsoJson);


            if (accountUserInnerList != null) {
                AccountTradeExample accountTradeExample = new AccountTradeExample();
                AccountTradeExample.Criteria criteria = accountTradeExample.createCriteria();
                criteria.andTradeIdEqualTo(data.getTradeId());
                AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
                List<AccountTrade> accountTradeList = accountTradeService.selectByExample(accountTradeExample);
                ProjecTransactionService projecTransactionService = new ProjecTransactionServiceImpl();
                int sta = projecTransactionService.customerRefund(data, accountTradeList.get(0).getAcId(), accountTradeList.get(0).getToAcId(), accountTradeList.get(0).getBalance());
                if (sta > 0) {
//流水
                } else {
                    retcode = 1;
                    retmsg = "Error";
                }
            }
        }


        return handler.buildMsg(retcode, retmsg, msgBody);

    }

    public MsgResponse transfer(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountTrade data = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);
        data.setTradeType(TradeConst.TRADE_TYPE_TRANSFER);


        if (loginSso != null) {
            //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            AccountUserInner accountUserInnerList = sysAccountUserInnerServer.getInnerIdByUser(loginSsoJson);


            if (accountUserInnerList != null) {

                data.setAcId(accountUserInnerList.getInnerId());
                ProjecTransactionService projecTransactionService = new ProjecTransactionServiceImpl();
                int sta = projecTransactionService.transferService(data);

                if (sta > 0) {
                    //流水
                } else {
                    retcode = 1;
                    retmsg = "Error";
                }

            } else {
                retcode = 1;
                retmsg = "Error";
            }

        } else {
            retcode = 1;
            retmsg = "Error";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse businessRefund(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountTrade data = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);


        if (loginSso != null) {
            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            AccountUserInner accountUserInnerList = sysAccountUserInnerServer.getInnerIdByUser(loginSsoJson);


            if (accountUserInnerList != null) {

                AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();

                AccountTrade accountTrade = accountTradeService.selectByPrimaryKey(data.getTradeId());


                if (accountTrade.getBalance().compareTo(data.getBalance()) == 0
                        || accountTrade.getBalance().compareTo(data.getBalance()) == 1) {

                    data.setTradeType(TradeConst.TRADE_TYPE_RECHARGE_REFUND);
                    data.setBalance(data.getBalance());

                    ProjecTransactionService projecTransactionService = new ProjecTransactionServiceImpl();
                    int sta = projecTransactionService.customerRefund(data, accountTrade.getAcId(),
                            accountTrade.getToAcId(), data.getBalance());
                    if (sta > 0) {


                        AccountTrade newAccountTrades = accountTrade;

                        newAccountTrades.setTradeNoPre(accountTrade.getTradeId().toString());
                        newAccountTrades.setTradeId(null);

                        newAccountTrades.setCreateTime(new Date());
                        accountTradeService.insertSelective(newAccountTrades);


//流水
                    } else {
                        retcode = 1;
                        retmsg = "Error";
                    }

                } else {
                    retcode = 1;
                    retmsg = "退款金额不能大于原订单金额";
                }
            }
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    public MsgResponse customerRefundPart(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountTrade data = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);
        data.setTradeType(TradeConst.TRADE_TYPE_RECHARGE_REFUND);

        //LoginSso loginSso = SafeContext.getLog_sso(req.token);
        if (loginSso != null) {
            //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

            SysAccountUserInnerServer sysAccountUserInnerServer = new SysAccountUserInnerServer();

            AccountUserInner accountUserInnerList = sysAccountUserInnerServer.getInnerIdByUser(loginSsoJson);


            if (accountUserInnerList != null) {
            /*    AccountTradeExample accountTradeExample = new AccountTradeExample();
                AccountTradeExample.Criteria criteria = accountTradeExample.createCriteria();
                criteria.andTradeIdEqualTo(data.getTradeId());*/

                AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
                AccountTrade accountTrade = accountTradeService.selectByPrimaryKey(data.getTradeId());

                ProjecTransactionService projecTransactionService = new ProjecTransactionServiceImpl();
                if (accountTrade.getBalance().compareTo(data.getBalance()) == 0
                        || accountTrade.getBalance().compareTo(data.getBalance()) == 1) {
                    int sta = projecTransactionService.customerRefund(data, accountTrade.getAcId(), accountTrade.getToAcId(), data.getBalance());


                    if (sta > 0) {
//流水
                        AccountTrade newAccountTrades = accountTrade;

                        newAccountTrades.setTradeNoPre(accountTrade.getTradeId().toString());
                        newAccountTrades.setTradeId(null);
                        newAccountTrades.setBalance(data.getBalance());
                        newAccountTrades.setCreateTime(new Date());
                        accountTradeService.insertSelective(newAccountTrades);
                        retcode = 0;
                        retmsg = "成功";
                    } else {
                        retcode = 1;
                        retmsg = "Error";
                    }
                } else {
                    retcode = 1;
                    retmsg = "退款金额不能大于原订单金额";
                }


            }
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public boolean withdrawCashAdoptAndTaskLogTask(Integer tradeId, TaskLog_TaskModel taskLogTask) {


      /*
        TaskLog_TaskModel ttm = new TaskLog_TaskModel();
        ttm.setTaskRemark(task_remark);
        ttm.setFinishTime(new Date());
        ttm.setTaskStat(Integer.parseInt(task_stat));
*/

        boolean sta = false;
        /* Integer innerId = req.msgBody.getInteger("taskId");*/


        TaskNotifyServiceImpl taskNotifyService = new TaskNotifyServiceImpl();
        taskNotifyService.modifyTaskLogTask(taskLogTask);

        AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();

        AccountTrade accountTrade = accountTradeService.selectByPrimaryKey(tradeId);


        if (accountTrade.getTradeType() > 0 & accountTrade.getAcId() != null & accountTrade.getBalance() != null) {
            accountTrade.getAcId();
            accountTrade.getBalance();

            if (accountTrade.getTradeType() == AccountConst.TRADE_SUBTYPE_BANK) {
//银行
                //提现流程

                boolean withdrawCashAdopt = false;
                //流程提交
                withdrawCashAdopt = true;

                if (withdrawCashAdopt == true) {

                    Boolean asta = withdrawCashAdoptSta(tradeId, AccountConst.STATUS_AUDIT_PASS);
                    if (asta == true) {
                        sta = true;
                    } else {
                        sta = false;
                    }

                }


            } else if (accountTrade.getTradeType() == AccountConst.TRADE_SUBTYPE_WE_CHAT) {
                accountTrade.getAcId();
                accountTrade.getBalance();
//微信

                //提现流程

                boolean withdrawCashAdopt = false;
                //流程
                withdrawCashAdopt = true;

                if (withdrawCashAdopt == true) {

                    Boolean asta = withdrawCashAdoptSta(tradeId, AccountConst.STATUS_AUDIT_PASS);
                    if (asta == true) {
                        sta = true;
                    } else {
                        sta = false;
                    }

                }

            } else if (accountTrade.getTradeType() == AccountConst.TRADE_SUBTYPE_ALIPAY) {
                accountTrade.getAcId();
                accountTrade.getBalance();

//支付宝
                //提现流程

                boolean withdrawCashAdopt = false;
                //流程
                withdrawCashAdopt = true;

                if (withdrawCashAdopt == true) {

                    Boolean asta = withdrawCashAdoptSta(tradeId, AccountConst.STATUS_AUDIT_PASS);
                    if (asta == true) {
                        sta = true;
                    } else {
                        sta = false;
                    }

                }
            }
        } else {
            sta = false;
        }


        return sta;
    }


    private boolean withdrawCashAdoptSta(Integer tradeId, Byte sta) {
        try {
            AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
            AccountTrade trade = new AccountTrade();
            trade.setTradeId(tradeId);
            trade.setStatus(sta);
            int asta = accountTradeService.updateByPrimaryKeySelective(trade);

            if (asta > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }


}
