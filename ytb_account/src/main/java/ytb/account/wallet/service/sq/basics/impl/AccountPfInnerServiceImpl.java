package ytb.account.wallet.service.sq.basics.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountPfDetailMapper;
import ytb.account.wallet.dao.transaction.AccountPfInnerBalanceMapper;
import ytb.account.wallet.dao.AccountPfInnerMapper;
import ytb.account.wallet.model.AccountPfDetail;
import ytb.account.wallet.model.AccountPfInner;
import ytb.account.wallet.model.AccountPfInnerExample;
import ytb.account.wallet.service.sq.basics.AccountPfInnerService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.Date;
import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class AccountPfInnerServiceImpl implements AccountPfInnerService {


    @Override
    public long countByExample(AccountPfInnerExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AccountPfInnerExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer pfInnerId) {
        return 0;
    }

    @Override
    public int insert(AccountPfInner record) {
        return 0;
    }

    @Override
    public int insertSelective(AccountPfInner record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountPfInnerMapper accountPfInnerMapper = sq.getMapper(AccountPfInnerMapper.class);
            data = accountPfInnerMapper.insertSelective(record);

        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public List<AccountPfInner> selectByExample(AccountPfInnerExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountPfInnerMapper accountPfInnerMapper = sq.getMapper(AccountPfInnerMapper.class);
            List<AccountPfInner> data = accountPfInnerMapper.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public AccountPfInner selectByPrimaryKey(Integer pfInnerId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountPfInnerMapper accountPfInnerMapper = sq.getMapper(AccountPfInnerMapper.class);
            AccountPfInner data = accountPfInnerMapper.selectByPrimaryKey(pfInnerId);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(AccountPfInner record, AccountPfInnerExample example) {
        return 0;
    }

    @Override
    public int updateByExample(AccountPfInner record, AccountPfInnerExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountPfInner record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(AccountPfInner record) {
        return 0;
    }


    @Override
    public int newRecordIn(AccountPfDetail data) {

        SqlSession sq = MyBatisUtil.getSession(false);
        int sta = 0;
        try {

            AccountPfDetailMapper accountPfDetailMapper = sq.getMapper(AccountPfDetailMapper.class);

            AccountPfInnerMapper pfInnerService = sq.getMapper(AccountPfInnerMapper.class);
            //++
            AccountPfInner selectByPrimaryKey = pfInnerService.selectByPrimaryKey(data.getPfInnerId());
            AccountPfDetail detail = new AccountPfDetail();
            detail.setOriginalBalance(selectByPrimaryKey.getBalance());
            detail.setBalance(selectByPrimaryKey.getBalance().add(data.getBalance()));
            detail.setTradeBalance(data.getBalance());
            detail.setPfInnerId(data.getPfInnerId());
            detail.setInTime(new Date());
            detail.setTradeSubtype(data.getTradeSubtype());
            detail.setTradeType(data.getTradeType());
//++

            sta = accountPfDetailMapper.insertSelective(detail);
            if (sta > 0) {
                AccountPfInnerBalanceMapper balanceMapper = sq.getMapper(AccountPfInnerBalanceMapper.class);
                sta = balanceMapper.updateBalanceIncreaseByInnerId(selectByPrimaryKey.getPfInnerId(),
                        detail.getTradeBalance(), Integer.valueOf(selectByPrimaryKey.getAcType()));
                if (sta > 0) {

                    sq.commit();

                } else {
                    sq.rollback();
                }
            }
            return sta;
        } finally {
            sq.close();

        }
    }


    @Override
    public int newRecordOut(AccountPfDetail data) {
        SqlSession sq = MyBatisUtil.getSession(false);

        int sta = 0;
        try {

            AccountPfDetailMapper accountPfDetailMapper = sq.getMapper(AccountPfDetailMapper.class);
            AccountPfInnerMapper pfInnerService = sq.getMapper(AccountPfInnerMapper.class);

            AccountPfInner inner = pfInnerService.selectByPrimaryKey(data.getPfInnerId());

            AccountPfDetail detail = new AccountPfDetail();
            detail.setOriginalBalance(inner.getBalance());
            detail.setBalance(inner.getBalance().subtract(data.getBalance()));
            detail.setTradeBalance(data.getBalance());
            detail.setPfInnerId(data.getPfInnerId());
            detail.setInTime(new Date());
            detail.setTradeSubtype(data.getTradeSubtype());
            detail.setTradeType(data.getTradeType());


            sta = accountPfDetailMapper.insertSelective(detail);

            if (sta > 0) {
                AccountPfInnerBalanceMapper balanceMapper = sq.getMapper(AccountPfInnerBalanceMapper.class);
                sta = balanceMapper.updateBalanceReduceByInnerId(inner.getPfInnerId(), detail.getTradeBalance(), Integer.valueOf(inner.getAcType()));
                if (sta > 0) {
                    sq.commit();

                } else {
                    sq.rollback();
                }


            }


            return sta;
        } finally {
            sq.close();

        }

    }


}
