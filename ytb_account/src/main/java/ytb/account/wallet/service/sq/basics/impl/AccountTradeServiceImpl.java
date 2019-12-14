package ytb.account.wallet.service.sq.basics.impl;


import org.apache.ibatis.session.SqlSession;

import ytb.account.wallet.dao.*;
import ytb.account.wallet.dao.transaction.AccountUserInnerBalanceMapper;
import ytb.account.wallet.model.*;

import ytb.account.wallet.pojo.ProjectBalanceAgent;
import ytb.account.wallet.pojo.ProjectBalanceToAc;
import ytb.account.wallet.service.sq.basics.AccountTradeService;

import ytb.account.wallet.service.sq.business.user.check.AccounTraceCheck;
import ytb.account.wallet.tool.MyBatisUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class AccountTradeServiceImpl implements AccountTradeService {


    @Override
    public long countByExample(AccountTradeExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AccountTradeExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer tradeId) {
        return 0;
    }

    @Override
    public int insert(AccountTrade record) {
        AccounTraceCheck.checkParam(record);
        return 0;
    }

    private static Integer intDate(Date dt) {

        String s = DateFormat.getDateInstance(DateFormat.DEFAULT).format(dt).replaceAll("-", "");
        return Integer.parseInt(s);
    }

    @Override
    public int insertSelective(AccountTrade record) {


        AccounTraceCheck.checkParam(record);

        SqlSession sq = MyBatisUtil.getSession();

        int data = 0;
        try {

            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            data = accountTradeMapper.insertSelective(record);
            if (data > 0) {
                data = record.getTradeId();
            }


        } catch (Exception e) {
            System.out.println("出错------------");
            e.printStackTrace();
            sq.rollback();
        } finally {
            sq.close();

        }
        return data;

    }


    @Override
    public List<AccountTrade> selectByExample(AccountTradeExample example) {


        try(  SqlSession sq = MyBatisUtil.getSession()) {

            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            List<AccountTrade> data = accountTradeMapper.selectByExample(example);
            return data;
        }
    }

    @Override
    public AccountTrade selectByPrimaryKey(Integer tradeId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            AccountTrade data = accountTradeMapper.selectByPrimaryKey(tradeId);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(AccountTrade record, AccountTradeExample example) {
        return 0;
    }

    @Override
    public int updateByExample(AccountTrade record, AccountTradeExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountTrade record) {
        AccounTraceCheck.checkParam(record);

        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;
        try {
            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            data = accountTradeMapper.updateByPrimaryKeySelective(record);
            sq.commit();


        } catch (Exception e) {
            e.printStackTrace();
            sq.rollback();


        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public int updateByPrimaryKey(AccountTrade record) {
        return 0;
    }


    /***
     *甲方减冻结资金 乙方收款 产生交易流水
     */


    /***
     * 产生订单 甲方冻结金额
     */


    /***
     *甲方减冻结资金 乙方收款 产生交易流水
     */

    public int confirmAmount(Integer tradeId, BigDecimal paymentBalance) {
        SqlSession sq = MyBatisUtil.getSession(false);
        int sta = 0;
        try {


            int dataa = 0;
            int tacsta = 0;
            int tacstb = 0;
            int tacstc = 0;

            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            AccountTrade accountTrade = accountTradeMapper.selectByPrimaryKey(tradeId);

            accountTrade.setTotalBalance(accountTrade.getBalance().add(paymentBalance));
            //乙方流水
            AccountUserDetail accountUserDetail = new AccountUserDetail();
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(accountTrade.getToAcId());

            accountUserDetail.setBalance(toAc.getBalance().add(accountTrade.getBalance()));
            accountUserDetail.setInnerId(accountTrade.getToAcId());
            accountUserDetail.setTradeType((short) 300);
            accountUserDetail.setOriginalBalance(toAc.getBalance());

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            dataa = accountUserDetailMapper.insertSelective(accountUserDetail);


            /*乙方加钱*/
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            /*乙方加钱-> project_balance_agent++*/
            tacsta = accountUserInnerBalanceMapper.updateProjectBalanceAgentIncreaseByInnerId(accountTrade.getToAcId(), paymentBalance);//给多少钱paymentBalance
            /*甲方扣冻结资金*/
            tacstb = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(accountTrade.getAcId(), paymentBalance);//扣多少冻结资金

            tacstc = accountTradeMapper.updateByPrimaryKeySelective(accountTrade);

            if (tacsta > 0 && tacstb > 0 & dataa > 0 & tacstc > 0) {
                sta = 1;
                sq.commit();
            } else {
                sq.rollback();
            }


        } catch (Exception e) {
            e.printStackTrace();
            sq.rollback();
        } finally {
            sq.close();
        }

        return sta;
    }


    /***
     *甲方减冻结资金 乙方收款 产生交易流水
     */

    public int confirmAmountOLD(Integer tradeId) {
        SqlSession sq = MyBatisUtil.getSession(false);
        int sta = 0;
        try {


            int dataa = 0;
            int tacsta = 0;
            int tacstb = 0;
//查出订单
            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            AccountTrade accountTrade = accountTradeMapper.selectByPrimaryKey(tradeId);


            //乙方流水
            AccountUserDetail accountUserDetail = new AccountUserDetail();
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(accountTrade.getToAcId());//乙方
            accountUserDetail.setBalance(toAc.getBalance().add(accountTrade.getBalance()));//加起来
            accountUserDetail.setInnerId(accountTrade.getToAcId());//甲
            accountUserDetail.setTradeType((short) 300);
            accountUserDetail.setOriginalBalance(toAc.getBalance());//原来

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            dataa = accountUserDetailMapper.insertSelective(accountUserDetail);


            /*乙方加钱*/
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            tacsta = accountUserInnerBalanceMapper.updateProjectBalanceAgentIncreaseByInnerId(accountTrade.getToAcId(), accountTrade.getBalance());

            /*甲方扣冻结资金*/
            tacstb = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(accountTrade.getAcId(), accountTrade.getBalance());

            if (tacsta > 0 && tacstb > 0 & dataa > 0) {
                sta = 1;
                sq.commit();
            } else {
                sq.rollback();
            }


        } catch (Exception e) {
            sq.rollback();
        } finally {
            sq.close();
        }
        return sta;
    }


    /***
     *甲方减冻结资金 乙方收款 产生交易流水
     */

    public int projectBalanceAgent(Integer tradeId) {
        SqlSession sq = MyBatisUtil.getSession(false);
        int sta = 0;
        try {


            int dataa = 0;
            int tacsta = 0;
            int tacstb = 0;
//查出订单
            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            AccountTrade accountTrade = accountTradeMapper.selectByPrimaryKey(tradeId);


            //乙方流水
            AccountUserDetail accountUserDetail = new AccountUserDetail();
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(accountTrade.getToAcId());//乙方
            accountUserDetail.setBalance(toAc.getBalance().add(accountTrade.getBalance()));//加起来
            accountUserDetail.setInnerId(accountTrade.getToAcId());//甲
            accountUserDetail.setTradeType((short) 300);//收入
            accountUserDetail.setOriginalBalance(toAc.getBalance());//原来

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            dataa = accountUserDetailMapper.insertSelective(accountUserDetail);


            /*乙方加钱*/
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            tacsta = accountUserInnerBalanceMapper.updateProjectBalanceAgentIncreaseByInnerId(accountTrade.getToAcId(), accountTrade.getBalance());

            /*甲方扣冻结资金*/
            tacstb = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(accountTrade.getAcId(), accountTrade.getBalance());

            if (tacsta > 0 && tacstb > 0 & dataa > 0) {
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


//    在高并发的情况下，Spring事物造成数据库死锁，后续操作超时抛出异常。
//    Mysql数据库采用InnoDB模式，默认参数:innodb_lock_wait_timeout设置锁等待的时间是50s，一旦数据库锁超过这个时间就会报错。
//
//    解决方案
//1、通过下面语句查找到为提交事务的数据，kill掉此线程即可。
//
//    select * from information_schema.innodb_trx
//1
//        2、增加锁等待时间，即增大下面配置项参数值，单位为秒（s）
//
//    innodb_lock_wait_timeout=500
//            1
//            3、优化存储过程,事务避免过长时间的等待。
//
//    参考信息
//1、锁等待超时。是当前事务在等待其它事务释放锁资源造成的。可以找出锁资源竞争的表和语句，优化SQL，创建索引等。如果还是不行，可以适当减少并发线程数。
//            2、事务在等待给某个表加锁时超时，估计是表正被另的进程锁住一直没有释放。
//    可以用 SHOW INNODB STATUS/G; 看一下锁的情况。
//            3、搜索解决之道，在管理节点的[ndbd default]区加：
//    TransactionDeadLockDetectionTimeOut=10000（设置 为10秒）默认是1200（1.2秒）
//            4、InnoDB会自动的检测死锁进行回滚，或者终止死锁的情况。
//            ---------------------


    //项目完结冻结资金-》总金额
    public ProjectBalanceAgent projectBalanceCompleteToList(List<ProjectBalanceToAc> pbList) {

        ProjectBalanceAgent projectBalanceAgent = new ProjectBalanceAgent();
        //List<ProjectBalanceAgent> projectBalanceAgentList 构建乙方


        List<AccountTrade> toAcList = new ArrayList<AccountTrade>(pbList.size());

        AccountTrade acAccountTrade = new AccountTrade();

        SqlSession sq = MyBatisUtil.getSession(false);


        try {

            Date time = new Date();
            int dataa = 0;
            int tacsta = 0;
            int tacstb = 0;
            int tacstc = 0;
            int tacstd = 0;
            int tacste = 0;
//查出订单

            /* AccountTrade accountTrade = accountTradeMapper.selectByPrimaryKey(tradeId);*/

            AccountTradeMapper accountTradeMapper = sq.getMapper(AccountTradeMapper.class);
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            /*-------------------------《--乙-方--》-------------------------*/
            for (int i = 0; i < pbList.size(); i++) {


                AccountTrade toAccountTrade = new AccountTrade();
                //构建accountTrade--乙方
                if (pbList.get(i).getBalance() != null) {
                    toAccountTrade.setBalance(pbList.get(i).getBalance());
                }

                if (pbList.get(i).getFee() != null) {
                    toAccountTrade.setFee(pbList.get(i).getFee());

                }
                if (pbList.get(i).getTax() != null) {
                    toAccountTrade.setTax(pbList.get(i).getTax());
                }
                if (pbList.get(i).getBalance() != null) {
                    toAccountTrade.setServiceFee(pbList.get(i).getServiceFee());
                }
                if (pbList.get(i).getProjectId() != null) {
                    toAccountTrade.setProjectId(pbList.get(i).getProjectId());
                }
                if (pbList.get(i).getOutInnerId() != null) {
                    toAccountTrade.setOutId(pbList.get(i).getOutInnerId());
                }
                if (pbList.get(i).getToInnerId() != null) {
                    toAccountTrade.setToAcId(pbList.get(i).getToInnerId());
                }

                if (pbList.get(i).getUserId() != null) {
                    toAccountTrade.setUserId(pbList.get(i).getUserId());
                }

                if (pbList.get(i).getCompanyId() != null) {
                    toAccountTrade.setCompanyId(pbList.get(i).getCompanyId());
                }

                if (pbList.get(i).getTradeId() != null) {
                    //emm 订单编号？？
                    toAccountTrade.setTradeId(pbList.get(i).getTradeId());
                }

                toAccountTrade.setTradeType((short) 103);
                toAccountTrade.setStatus((byte) 3);
                int newTradeId = accountTradeMapper.insertSelective(toAccountTrade);

                if (newTradeId > 0) {
                    //    toAccountTrade.setTradeId(newTradeId);//记录这个ID list 然后返回
                    toAcList.add(toAccountTrade);

                } else {
                    sq.rollback();
                    return null;
                }


                //乙方流水


                //查询账户原来金额
                AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(pbList.get(i).getToInnerId());//乙方

                AccountUserDetail accountUserDetail = new AccountUserDetail();

                accountUserDetail.setOriginalBalance(toAc.getBalance());//原金额
                accountUserDetail.setTradeBalance(pbList.get(i).getBalance());//交易金额
                accountUserDetail.setBalance(toAc.getBalance().add(pbList.get(i).getBalance()));//加起来交易资金变动
                accountUserDetail.setInnerId(pbList.get(i).getToInnerId());//乙方id
                accountUserDetail.setTradeType((short) 500);//收入 500项收款（乙方）
                accountUserDetail.setInTime(time);


                dataa = accountUserDetailMapper.insertSelective(accountUserDetail);

                if (dataa <= 0) {
                    sq.rollback();
                    return null;
                }

                /*乙方加钱*/

                tacsta = accountUserInnerBalanceMapper.updatePbFrozen2Balance(pbList.get(i));

                if (tacsta <= 0) {
                    sq.rollback();
                    return null;
                }

            }




            /*-------------------------《--乙-方--》-------------------------*/

            /*-------------------------《--甲-方--》-------------------------*/
/*//-1表示小于，0是等于，1是大于。
            if (accountTrade.getBalance().compareTo(BigDecimal.valueOf(0)) != 0 || (accountTrade.getBalance() != null)) {

            }
*/

            /*-------------------------《--甲-方--》-------------------------*/


            if (tacsta > 0 && tacstb > 0 & dataa > 0 & tacstd > 0 & tacste > 0) {
                projectBalanceAgent.setToAccountTrades(toAcList);
                //  acAccountTrade.setTradeId(tacstd);
                projectBalanceAgent.setAccountTrade(acAccountTrade);
                sq.commit();
            } else {

                sq.rollback();
                return null;
            }


        } catch (Exception e) {
            sq.rollback();
        } finally {
            sq.close();
        }

        return projectBalanceAgent;
    }


    private AccountTrade projectBalanceAgentListToAccountTrade(ProjectBalanceToAc projectBalanceAgentList) {
        AccountTrade toAccountTrade = new AccountTrade();
        return toAccountTrade;
    }


}
