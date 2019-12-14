package ytb.account.wallet.service.sq.basics.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountPfOutMapper;
import ytb.account.wallet.model.*;
import ytb.account.wallet.service.sq.basics.AccountPfOutService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class AccountPfOutServiceImpl implements AccountPfOutService {


    @Override
    public long countByExample(AccountPfOutExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AccountPfOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            int data = 0;
            AccountPfOutMapper accountUserOut = sq.getMapper(AccountPfOutMapper.class);
            data = accountUserOut.deleteByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int deleteByPrimaryKey(Integer pfOutId) {
        return 0;
    }

    @Override
    public int insert(AccountPfOut record) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            int data = 0;
            AccountPfOutMapper accountUserOut = sq.getMapper(AccountPfOutMapper.class);
            data = accountUserOut.insert(record);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int insertSelective(AccountPfOut record) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            int data = 0;
            AccountPfOutMapper accountUserOut = sq.getMapper(AccountPfOutMapper.class);
            data = accountUserOut.insertSelective(record);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public List<AccountPfOut> selectByExample(AccountPfOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountPfOutMapper accountUserOut = sq.getMapper(AccountPfOutMapper.class);
            List<AccountPfOut> data = accountUserOut.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public AccountPfOut selectByPrimaryKey(Integer pfOutId) {
        return null;
    }

    @Override
    public int updateByExampleSelective(AccountPfOut record, AccountPfOutExample example) {


        SqlSession sq = MyBatisUtil.getSession();
        try {
            int data = 0;
            AccountPfOutMapper accountUserOut = sq.getMapper(AccountPfOutMapper.class);
            data = accountUserOut.updateByExampleSelective(record, example);
            return data;
        } finally {
            sq.close();

        }


    }

    @Override
    public int updateByExample(AccountPfOut record, AccountPfOutExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountPfOut record) {

        SqlSession sq = MyBatisUtil.getSession();
        try {
            int data = 0;
            AccountPfOutMapper accountUserOut = sq.getMapper(AccountPfOutMapper.class);
            data = accountUserOut.updateByPrimaryKeySelective(record);
            return data;
        } finally {
            sq.close();

        }

    }

    @Override
    public int updateByPrimaryKey(AccountPfOut record) {
        return 0;
    }
}
