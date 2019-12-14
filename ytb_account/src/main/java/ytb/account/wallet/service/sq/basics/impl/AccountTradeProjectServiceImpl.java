package ytb.account.wallet.service.sq.basics.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountTradeProjectMapper;
import ytb.account.wallet.dao.AccountUserDetailMapper;
import ytb.account.wallet.dao.AccountUserInnerMapper;
import ytb.account.wallet.dao.transaction.AccountUserInnerBalanceMapper;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.AccountTradeProjectExample;
import ytb.account.wallet.model.AccountUserDetail;
import ytb.account.wallet.model.AccountUserInner;

import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;
import ytb.account.wallet.pojo.ProjectBalanceToAc;
import ytb.account.wallet.service.sq.basics.AccountTradeProjectService;
import ytb.account.wallet.service.sq.business.user.check.AccounTraceProjectCheck;

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
public class AccountTradeProjectServiceImpl implements AccountTradeProjectService {


    @Override
    public long countByExample(AccountTradeProjectExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AccountTradeProjectExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer tradeId) {
        return 0;
    }

    @Override
    public int insert(AccountTradeProject record) {
        AccounTraceProjectCheck.checkParam(record);
        return 0;
    }

    private static Integer intDate(Date dt) {

        String s = DateFormat.getDateInstance(DateFormat.DEFAULT).format(dt).replaceAll("-", "");
        return Integer.parseInt(s);
    }

    @Override
    public int insertSelective(AccountTradeProject record) {


        AccounTraceProjectCheck.checkParam(record);

        SqlSession sq = MyBatisUtil.getSession();

        int data = 0;
        try {

            AccountTradeProjectMapper AccountTradeProjectMapper = sq.getMapper(AccountTradeProjectMapper.class);
            data = AccountTradeProjectMapper.insertSelective(record);
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
    public List<AccountTradeProject> selectByExample(AccountTradeProjectExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountTradeProjectMapper AccountTradeProjectMapper = sq.getMapper(AccountTradeProjectMapper.class);
            List<AccountTradeProject> data = AccountTradeProjectMapper.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public AccountTradeProject selectByPrimaryKey(Integer tradeId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountTradeProjectMapper AccountTradeProjectMapper = sq.getMapper(AccountTradeProjectMapper.class);
            AccountTradeProject data = AccountTradeProjectMapper.selectByPrimaryKey(tradeId);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(AccountTradeProject record, AccountTradeProjectExample example) {
        return 0;
    }

    @Override
    public int updateByExample(AccountTradeProject record, AccountTradeProjectExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountTradeProject record) {
        AccounTraceProjectCheck.checkParam(record);

        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;
        try {
            AccountTradeProjectMapper AccountTradeProjectMapper = sq.getMapper(AccountTradeProjectMapper.class);
            data = AccountTradeProjectMapper.updateByPrimaryKeySelective(record);
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
    public int updateByPrimaryKey(AccountTradeProject record) {
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

            AccountTradeProjectMapper AccountTradeProjectMapper = sq.getMapper(AccountTradeProjectMapper.class);
            AccountTradeProject AccountTradeProject = AccountTradeProjectMapper.selectByPrimaryKey(tradeId);

            AccountTradeProject.setTotalBalance(AccountTradeProject.getBalance().add(paymentBalance));
            //乙方流水
            AccountUserDetail accountUserDetail = new AccountUserDetail();
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(AccountTradeProject.getToAcId());

            accountUserDetail.setBalance(toAc.getBalance().add(AccountTradeProject.getBalance()));
            accountUserDetail.setInnerId(AccountTradeProject.getToAcId());
            accountUserDetail.setTradeType((short) 300);
            accountUserDetail.setOriginalBalance(toAc.getBalance());

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            dataa = accountUserDetailMapper.insertSelective(accountUserDetail);


            /*乙方加钱*/
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            /*乙方加钱-> project_balance_agent++*/
            tacsta = accountUserInnerBalanceMapper.updateProjectBalanceAgentIncreaseByInnerId(AccountTradeProject.getToAcId(), paymentBalance);//给多少钱paymentBalance
            /*甲方扣冻结资金*/
            tacstb = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(AccountTradeProject.getAcId(), paymentBalance);//扣多少冻结资金

            tacstc = AccountTradeProjectMapper.updateByPrimaryKeySelective(AccountTradeProject);

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
            AccountTradeProjectMapper AccountTradeProjectMapper = sq.getMapper(AccountTradeProjectMapper.class);
            AccountTradeProject AccountTradeProject = AccountTradeProjectMapper.selectByPrimaryKey(tradeId);


            //乙方流水
            AccountUserDetail accountUserDetail = new AccountUserDetail();
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(AccountTradeProject.getToAcId());//乙方
            accountUserDetail.setBalance(toAc.getBalance().add(AccountTradeProject.getBalance()));//加起来
            accountUserDetail.setInnerId(AccountTradeProject.getToAcId());//甲
            accountUserDetail.setTradeType((short) 300);
            accountUserDetail.setOriginalBalance(toAc.getBalance());//原来

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            dataa = accountUserDetailMapper.insertSelective(accountUserDetail);


            /*乙方加钱*/
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            tacsta = accountUserInnerBalanceMapper.updateProjectBalanceAgentIncreaseByInnerId(AccountTradeProject.getToAcId(), AccountTradeProject.getBalance());

            /*甲方扣冻结资金*/
            tacstb = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(AccountTradeProject.getAcId(), AccountTradeProject.getBalance());

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
            AccountTradeProjectMapper AccountTradeProjectMapper = sq.getMapper(AccountTradeProjectMapper.class);
            AccountTradeProject AccountTradeProject = AccountTradeProjectMapper.selectByPrimaryKey(tradeId);


            //乙方流水
            AccountUserDetail accountUserDetail = new AccountUserDetail();
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner toAc = accountUserInnerMapper.selectByPrimaryKey(AccountTradeProject.getToAcId());//乙方
            accountUserDetail.setBalance(toAc.getBalance().add(AccountTradeProject.getBalance()));//加起来
            accountUserDetail.setInnerId(AccountTradeProject.getToAcId());//甲
            accountUserDetail.setTradeType((short) 300);//收入
            accountUserDetail.setOriginalBalance(toAc.getBalance());//原来

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            dataa = accountUserDetailMapper.insertSelective(accountUserDetail);


            /*乙方加钱*/
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            tacsta = accountUserInnerBalanceMapper.updateProjectBalanceAgentIncreaseByInnerId(AccountTradeProject.getToAcId(), AccountTradeProject.getBalance());

            /*甲方扣冻结资金*/
            tacstb = accountUserInnerBalanceMapper.f2fMinusPayoutAgent(AccountTradeProject.getAcId(), AccountTradeProject.getBalance());

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
    public ProjectBalanceProjectAgent projectBalanceCompleteToList(List<ProjectBalanceToAc> pbList) {

        ProjectBalanceProjectAgent projectBalanceAgent = new ProjectBalanceProjectAgent();
        //List<ProjectBalanceAgent> projectBalanceAgentList 构建乙方


        List<AccountTradeProject> toAcList = new ArrayList<AccountTradeProject>(pbList.size());

        AccountTradeProject acAccountTradeProject = new AccountTradeProject();

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

            /* AccountTradeProject AccountTradeProject = AccountTradeProjectMapper.selectByPrimaryKey(tradeId);*/

            AccountTradeProjectMapper AccountTradeProjectMapper = sq.getMapper(AccountTradeProjectMapper.class);
            AccountUserInnerMapper accountUserInnerMapper = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInnerBalanceMapper accountUserInnerBalanceMapper = sq.getMapper(AccountUserInnerBalanceMapper.class);
            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            /*-------------------------《--乙-方--》-------------------------*/
            for (int i = 0; i < pbList.size(); i++) {


                AccountTradeProject toAccountTradeProject = new AccountTradeProject();
                //构建AccountTradeProject--乙方
                if (pbList.get(i).getBalance() != null) {
                    toAccountTradeProject.setBalance(pbList.get(i).getBalance());
                }

                if (pbList.get(i).getFee() != null) {
                    toAccountTradeProject.setFee(pbList.get(i).getFee());

                }
                if (pbList.get(i).getTax() != null) {
                    toAccountTradeProject.setTax(pbList.get(i).getTax());
                }
                if (pbList.get(i).getBalance() != null) {
                    toAccountTradeProject.setServiceFee(pbList.get(i).getServiceFee());
                }
                if (pbList.get(i).getProjectId() != null) {
                    toAccountTradeProject.setProjectId(pbList.get(i).getProjectId());
                }
                if (pbList.get(i).getOutInnerId() != null) {
                    toAccountTradeProject.setOutId(pbList.get(i).getOutInnerId());
                }
                if (pbList.get(i).getToInnerId() != null) {
                    toAccountTradeProject.setToAcId(pbList.get(i).getToInnerId());
                }

                if (pbList.get(i).getUserId() != null) {
                    toAccountTradeProject.setUserId(pbList.get(i).getUserId());
                }

                if (pbList.get(i).getCompanyId() != null) {
                    toAccountTradeProject.setCompanyId(pbList.get(i).getCompanyId());
                }

                if (pbList.get(i).getTradeId() != null) {
                    //emm 订单编号？？
                    toAccountTradeProject.setTradeId(pbList.get(i).getTradeId());
                }

                toAccountTradeProject.setTradeType((short) 103);
                toAccountTradeProject.setStatus((byte) 3);
                int newTradeId = AccountTradeProjectMapper.insertSelective(toAccountTradeProject);

                if (newTradeId > 0) {
                    //    toAccountTradeProject.setTradeId(newTradeId);//记录这个ID list 然后返回
                    toAcList.add(toAccountTradeProject);

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
            if (AccountTradeProject.getBalance().compareTo(BigDecimal.valueOf(0)) != 0 || (AccountTradeProject.getBalance() != null)) {

            }
*/

            /*-------------------------《--甲-方--》-------------------------*/


            if (tacsta > 0 && tacstb > 0 & dataa > 0 & tacstd > 0 & tacste > 0) {
                projectBalanceAgent.setToAccountTrades(toAcList);
                //  acAccountTradeProject.setTradeId(tacstd);
                projectBalanceAgent.setAccountTrade(acAccountTradeProject);
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


    private AccountTradeProject projectBalanceAgentListToAccountTradeProject(ProjectBalanceToAc projectBalanceAgentList) {
        AccountTradeProject toAccountTradeProject = new AccountTradeProject();
        return toAccountTradeProject;
    }


}
