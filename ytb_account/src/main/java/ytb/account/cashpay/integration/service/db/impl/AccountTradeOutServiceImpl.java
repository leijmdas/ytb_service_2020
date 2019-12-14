package ytb.account.cashpay.integration.service.db.impl;

import org.apache.ibatis.session.SqlSession;

import ytb.account.cashpay.integration.service.db.AccountTradeOutService;
import ytb.account.wallet.dao.AccountTradeOutMapper;
import ytb.account.wallet.model.AccountTradeOut;
import ytb.account.wallet.model.AccountTradeOutExample;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.List;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/1/22
 */
public class AccountTradeOutServiceImpl implements AccountTradeOutService {
    @Override
    public long countByExample(AccountTradeOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        long data = 0;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.countByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int deleteByExample(AccountTradeOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.deleteByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.deleteByPrimaryKey(id);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int insert(AccountTradeOut record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.insert(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int insertSelective(AccountTradeOut record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.insertSelective(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public List<AccountTradeOut> selectByExample(AccountTradeOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        List<AccountTradeOut> data = null;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.selectByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public AccountTradeOut selectByPrimaryKey(Integer id) {
        SqlSession sq = MyBatisUtil.getSession();
        AccountTradeOut data = null;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.selectByPrimaryKey(id);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByExampleSelective(AccountTradeOut record, AccountTradeOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.updateByExampleSelective(record, example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByExample(AccountTradeOut record, AccountTradeOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.updateByExample(record, example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountTradeOut record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.updateByPrimaryKeySelective(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByPrimaryKey(AccountTradeOut record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountTradeOutMapper mapper = sq.getMapper(AccountTradeOutMapper.class);
            data = mapper.updateByPrimaryKey(record);

        } finally {
            sq.close();
        }
        return data;
    }
}
